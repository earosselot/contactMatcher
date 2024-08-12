package com.earosselot.model;

import java.util.List;

public class ContactComparator {

    private final List<ComparatorScores> scoresList;

    public ContactComparator(List<ComparatorScores> scoresList) {
        this.scoresList = scoresList;
    }

    public Integer matchLikelihoodScore(Contact contact1, Contact contact2) {

        Integer matchLikelihoodScore = 0;
        for(ComparatorScores score : scoresList) {
            if (contact1.hasSame(score.fieldName, contact2)) {
                matchLikelihoodScore += score.equalScore;
            } else if (contact1.hasSimilar(score.fieldName, contact2, score.similarThreshold)) {
                matchLikelihoodScore += score.similarScore;
            } else if (score.initialEnabled && contact1.canBeInitial(score.fieldName, contact2)) {
                matchLikelihoodScore += score.initialScore;
            }
        }

        return matchLikelihoodScore;
    }
}
