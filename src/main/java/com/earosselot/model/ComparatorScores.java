package com.earosselot.model;

public class ComparatorScores {

    FieldName fieldName;
    Integer equalScore;
    Integer similarScore;
    Integer similarThreshold;
    boolean initialEnabled;
    Integer initialScore;

    public ComparatorScores(Builder builder) {
        this.fieldName = builder.fieldName;
        this.equalScore = builder.equalScore;
        this.similarScore = builder.similarScore;
        this.similarThreshold = builder.similarThreshold;
        this.initialEnabled = builder.initialEnabled;
        this.initialScore = builder.initialScore;
    }

    public static class Builder {

        private final FieldName fieldName;
        private Integer equalScore = 5;
        private Integer similarScore = 2;
        private Integer similarThreshold = 1;
        private boolean initialEnabled = false;
        private Integer initialScore = 0;

        public Builder(FieldName fieldName) {
            this.fieldName = fieldName;
        }

        public ComparatorScores buildDefaultImportantWithInitialScores() {

            return this.equalScore(30)
                    .similarScoreAndThreshold(25, 1)
                    .enableInital(15)
                    .build();
        }

        public ComparatorScores buildDefaultImportantWithoutInitialScores() {
            return this.equalScore(30)
                    .similarScoreAndThreshold(25, 1)
                    .build();
        }

        public Builder equalScore(Integer equalScore) {
            this.equalScore = equalScore;
            return this;
        }

        public Builder similarScoreAndThreshold(Integer similarScore, Integer similarThreshold) {
            this.similarScore = similarScore;
            this.similarThreshold = similarThreshold;
            return this;
        }

        public Builder enableInital(Integer initialScore) {
            this.initialEnabled = true;
            this.initialScore = initialScore;
            return this;
        }

        public ComparatorScores build() {
            return new ComparatorScores(this);
        }

    }
}
