package com.earosselot.model;

public class ScoreMapper {

    private final int lowThreshold;
    private final int medThreshold;
    private final int highThreshold;

    public enum MatchLikelihood {
        NONE, LOW, MEDIUM, HIGH
    }

    public ScoreMapper(int lowThreshold, int medThreshold, int highThreshold) {
        this.lowThreshold = lowThreshold;
        this.medThreshold = medThreshold;
        this.highThreshold = highThreshold;
    }

    public String getMatchAccuracy(int score) {
        if (score >= highThreshold) {
            return MatchLikelihood.HIGH.name();
        } else if (score >= medThreshold) {
            return MatchLikelihood.MEDIUM.name();
        } else if (score >= lowThreshold) {
            return MatchLikelihood.LOW.name();
        } else {
            return MatchLikelihood.NONE.name();
        }
    }

    public boolean isMatch(int score) {
        return score >= lowThreshold;
    }
}
