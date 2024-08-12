package com.earosselot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreMapperTest {

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberAboveHighThreshold_thenHigh() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("HIGH", scoreMapper.getMatchAccuracy(76));
    }

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberAtHighThreshold_thenHigh() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("HIGH", scoreMapper.getMatchAccuracy(75));
    }

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberAboveMedThreshold_thenMedium() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("MEDIUM", scoreMapper.getMatchAccuracy(51));
    }

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberAtMedThreshold_thenMedium() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("MEDIUM", scoreMapper.getMatchAccuracy(50));
    }

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberAboveLowThreshold_thenLow() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("LOW", scoreMapper.getMatchAccuracy(21));
    }

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberAtLowThreshold_thenLow() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("LOW", scoreMapper.getMatchAccuracy(20));
    }

    @Test
    void givenScoreMapWithThresholds_whenAskForNumberBelowLowThreshold_thenNone() {
        var scoreMapper = new ScoreMapper(20, 50, 75);

        assertEquals("NONE", scoreMapper.getMatchAccuracy(19));
    }
}