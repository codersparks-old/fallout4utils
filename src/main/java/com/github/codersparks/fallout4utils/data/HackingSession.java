package com.github.codersparks.fallout4utils.data;

import com.github.codersparks.fallout4utils.exception.HackingSessionAlreadyExistsException;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class HackingSession {

    private static final Logger logger = LoggerFactory.getLogger(HackingSession.class);

    public HackingSession(String id) {
        this.id = id;
    }

    public HackingSession() {
        this.id = UUID.randomUUID().toString();
    }

    @Id
    private String id;

    Map<String, HackingSessionWordProperties> candidateDetails;

    public Map<String, HackingSessionWordProperties> getCandidateDetails() {

        return candidateDetails;
    }

    public void setCandidateDetails(Map<String, HackingSessionWordProperties> candidateDetails) {
        this.candidateDetails = candidateDetails;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void initialiseCandidates(List<String> candidates) throws HackingSessionException {

        logger.info("Initialiseing candidates for " + this.id);

        if(candidateDetails == null) {
            candidateDetails = new HashMap<>();
        } else {
            throw new HackingSessionAlreadyExistsException("Candidate details already exist therefore cannot be initialised");
        }

        for(String candidate : candidates) {
            candidateDetails.put(candidate, new HackingSessionWordProperties());
        }

        // We also update the initial levenShteinDistances
        HackingSession.updateLevenShteinDistances(this);
    }


    public static void updateLevenShteinDistances(HackingSession session) throws HackingSessionException {
        // We now are coing to calculate the total and average levenshtein distances to all of the other candidates
        Map<String, HackingSessionWordProperties> propertiesMap = session.getCandidateDetails();

        // If the properties map has not already been created we will throw an exception
        if(propertiesMap == null) {
            throw new HackingSessionException("Session: " + session.getId() + " not initialised");
        }

        for(String candidate : propertiesMap.keySet()) {
            int count = 0;
            int sumLevenShetein = 0;
            HackingSessionWordProperties properties = session.getCandidateDetails().get(candidate);

            // Now we loop again ignoring the candidate
            for(String comparison : propertiesMap.keySet()) {

                logger.debug("Calculating levenshtein distance for '" + comparison + "' against candidate: " + candidate);

                if(comparison.equals(candidate)) {
                    logger.debug("Ignoring as candidate same as comparision");
                    continue;
                }

                int levenshteinValue = calulateLevenshtein(candidate, comparison);

                logger.debug("Levenshtein value: " + levenshteinValue);

                sumLevenShetein += levenshteinValue;
                count++;

            }

            logger.info("Sum of Levenshteing distance: " + sumLevenShetein);
            float averageLevenshtein =  ((float)sumLevenShetein)/count;
            logger.info("Average of Levenshtein distance: " + averageLevenshtein);

            properties.setAverageLevenshteinDistance(averageLevenshtein);
            properties.setSumLevenshteinDistance(sumLevenShetein);

            propertiesMap.put(candidate, properties);

        }

        session.setCandidateDetails(propertiesMap);
    }


    public static int calulateLevenshtein(String lhs, String rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.toLowerCase().charAt(i - 1) == rhs.toLowerCase().charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

}
