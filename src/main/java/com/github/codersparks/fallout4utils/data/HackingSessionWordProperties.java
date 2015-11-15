package com.github.codersparks.fallout4utils.data;

/**
 * Created by codersparks on 15/11/2015.
 */
public class HackingSessionWordProperties {

    private int likeness = Integer.MAX_VALUE;
    private float averageLevenshteinDistance = Float.MAX_VALUE;
    private int sumLevenshteinDistance = Integer.MAX_VALUE;

    public int getLikeness() {
        return likeness;
    }

    public void setLikeness(int likeness) {
        this.likeness = likeness;
    }

    public float getAverageLevenshteinDistance() {
        return averageLevenshteinDistance;
    }

    public void setAverageLevenshteinDistance(float averageLevenshteinDistance) {
        this.averageLevenshteinDistance = averageLevenshteinDistance;
    }

    public int getSumLevenshteinDistance() {
        return sumLevenshteinDistance;
    }

    public void setSumLevenshteinDistance(int totalLevenshteinDistance) {
        this.sumLevenshteinDistance = totalLevenshteinDistance;
    }
}
