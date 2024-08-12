package com.earosselot.model;

import java.util.Objects;

public class ContactMatch {

    private final int sourceId;
    private final int matchId;
    private final String accuracy;

    public ContactMatch(int sourceId, int matchId, String accuracy) {
        this.sourceId = sourceId;
        this.matchId = matchId;
        this.accuracy = accuracy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactMatch that = (ContactMatch) o;
        return sourceId == that.sourceId && matchId == that.matchId && Objects.equals(accuracy, that.accuracy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceId, matchId, accuracy);
    }

    @Override
    public String toString() {
        return "ContactMatch{" +
                "sourceId=" + sourceId +
                ", matchId=" + matchId +
                ", accuracy='" + accuracy + '\'' +
                '}';
    }

    public int getSourceId() {
        return sourceId;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getAccuracy() {
        return accuracy;
    }
}
