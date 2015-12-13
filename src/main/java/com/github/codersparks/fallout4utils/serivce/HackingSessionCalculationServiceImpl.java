package com.github.codersparks.fallout4utils.serivce;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.data.HackingSessionWordProperties;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;
import com.github.codersparks.fallout4utils.interfaces.HackingSessionCalculationService;
import com.github.codersparks.fallout4utils.repository.HackingSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by codersparks on 15/11/2015.
 */
public class HackingSessionCalculationServiceImpl implements HackingSessionCalculationService {

    private static final Logger logger = LoggerFactory.getLogger(HackingSessionCalculationService.class);

    private HackingSessionRepository repository;

    @Autowired
    public HackingSessionCalculationServiceImpl(HackingSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public HackingSession calculateLevenshteinDistances(HackingSession session) throws HackingSessionException {

        HackingSession.updateLevenShteinDistances(session);

        repository.save(session);

        return session;

    }

    @Override
    public List<String> calculateBestChoice(HackingSession hackingSession) throws HackingSessionException {

        Map<String, HackingSessionWordProperties> propertiesMap = hackingSession.getCandidateDetails();

        int lowestSumDistance = Integer.MAX_VALUE;
        List<String> candidateValues = new ArrayList<>();

        for (String candidate : propertiesMap.keySet()) {
            HackingSessionWordProperties candiateProperties = propertiesMap.get(candidate);

            if( ! candiateProperties.isPossibleSolution()) {
                logger.debug("Candidate '" + candidate + "' is not a possible solution skipping");
            }

            int sumDistance = candiateProperties.getSumLevenshteinDistance();

            if(propertiesMap.get(candidate).getLikeness() < candidate.length()) {
                // we have obviously already checked this one so don't add it
                continue;
            }

            if (sumDistance > lowestSumDistance) {
                continue;
            } else {
                // If it is less than the lowest then we remove the current contents
                if (sumDistance < lowestSumDistance) {
                    candidateValues.clear();
                    lowestSumDistance = sumDistance;
                }
                candidateValues.add(candidate);

            }
        }

        return candidateValues;

    }

    @Override
    public HackingSession updateLikeness(HackingSession session, String candidate, int likeness) throws HackingSessionException {

        Map<String, HackingSessionWordProperties> propertiesMap = session.getCandidateDetails();

        // first we update the likeness for the candidate that we have
        propertiesMap.get(candidate).setLikeness(likeness);
        // Set it as not a possible solution
        propertiesMap.get(candidate).setPossibleSolution(false);

        List<String> removalList = new ArrayList<>();

        // We also now loop through the candidates to workout those that have the same number of common chars
        // If they do not then we can remove them from the list
        for (String comparison : propertiesMap.keySet()) {

            HackingSessionWordProperties comparisionProperties = propertiesMap.get(comparison);
            if(! comparisionProperties.isPossibleSolution()) {
                logger.debug("Comparison: '" + comparison + "' is marked as not possible solution, skipping...");
                continue;
            }

            logger.debug("Comparing '" + candidate + "' with candidate: " + comparison + "'");

            if (comparison.equalsIgnoreCase(candidate)) {

                logger.debug("Found candidate in map, ignoring...");
                continue;
            }


            int totalMatches = 0;

            // We now check to see if it shares the same number of letters as the likeness
            for (int i = 0; i < candidate.length(); i++) {
                if (candidate.toLowerCase().charAt(i) == comparison.toLowerCase().charAt(i)) {
                    logger.debug("Found shared charater at index: " + i);
                    totalMatches++;
                }

            }

            logger.debug("Total matches found: " + totalMatches);

            // Now we can remove if the commonchars are not equal to likeness
            if (totalMatches != likeness) {
                logger.debug("Updating '" + comparison + "' to no longer be a possible solution");
                propertiesMap.get(comparison).setPossibleSolution(false);
            }

        }

        session.setCandidateDetails(propertiesMap);

        logger.info("Session details after processing likeness: " + session);

        // now lets update the distances (which will also save the session)
        HackingSession updatedSession = this.calculateLevenshteinDistances(session);

        return updatedSession;
    }


}
