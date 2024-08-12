package com.earosselot.model;

import java.util.Objects;

public class Field {

    final String value;

    public Field(String value) {
        this.value = value == null ? "" : value.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(value, field.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public boolean isSimilar(Field otherField, int threshold) {
        if (otherField == null) return false;
        if (this.value.isEmpty()) return false;
        return otherField.isSimilar(this.value, threshold);
    }

    private boolean isSimilar(String value, int threshold) {
        if (this.value.isEmpty()) return false;
        return levenshteinDistance(this.value, value) <= threshold;
    }

    private int levenshteinDistance(String string1, String string2) {
        return levenshteinDistance(string1.toCharArray(), string2.toCharArray());
    }

    /**
     * Levenshtein Distance
     * <p>
     * Returns the minimum number of operations to obtain string2 from string1.
     * <p>
     * Available operations are:
     *  1. Change a character
     *  2. Remove a character
     *  3. Add a character
     * <p>
     * In other words it returns how different string1 if from string2
     * <p>
     * Ej:
     * string1 = "abc" / string2 = "abd"
     * returns 1, we only need 1 change operation c -> d
     * <p>
     * string1 = "abc" / string2 = "abc"
     * returns 0, no operations needed
     * <p>
     * string1 = "xabc" / string2 = "bcf"
     * returns 3, 2 remove operations "xabc" -> "bc" + 1 add operation "bc" -> "bcf"
     */
    private int levenshteinDistance(char[] string1, char[] string2) {

        int[][] distance = new int[string1.length + 1][string2.length + 1];

        for (int i = 0; i <= string1.length; i++)
            distance[i][0] = i;

        for (int j = 0; j <= string2.length; j++)
            distance[0][j] = j;

        for (int i = 1; i <= string1.length; i++) {
            for (int j = 1; j <= string2.length; j++) {
                distance[i][j] = min(distance[i - 1][j] + 1,
                                    distance[i][j - 1] + 1,
                                    distance[i - 1][j - 1] +
                                            (string1[i - 1] == string2[j - 1] ? 0 : 1));
            }
        }

        return distance[string1.length][string2.length];
    }

    private int min(int distance1, int distance2, int distance3) {
        return Math.min(Math.min(distance1, distance2), distance3);
    }

    public boolean sameInitial(Field field) {
        if (field == null) return false;
        if (this.value.isEmpty()) return false;
        return field.sameInitial(this.value.substring(0, 1));
    }

    private boolean sameInitial(String initial) {
        if (this.value.isEmpty()) return false;
        return initial.equals(this.value.substring(0, 1));
    }

    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    public boolean isInitial() {
        return this.value.length() == 1;
    }
}
