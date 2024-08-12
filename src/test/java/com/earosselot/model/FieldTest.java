package com.earosselot.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private static Field matt;

    @BeforeAll
    static void setUp() {

        matt = new Field("Matt");
    }

    @Test
    public void givenEquivalentFields_whenAskSimilar_thenTrue() {
        Field otherMatt = new Field("Matt");

        assertTrue(matt.isSimilar(otherMatt, 0));
        assertTrue(matt.isSimilar(otherMatt, 2));
        assertTrue(matt.isSimilar(matt, 0));
    }

    @Test
    public void givenDifferentFields_whenAskSimilar_thenFalse() {
        Field charles = new Field("Charles");

        assertFalse(matt.isSimilar(charles, 2));
    }

    @Test
    public void givenSimilarFieldsWithinThreshold_whenAskSimilar_thenTrue() {
        Field metty = new Field("Matthy");

        assertTrue(matt.isSimilar(metty, 2));
    }

    @Test
    public void givenSimilarFieldsOutsideThreshold_whenAskSimilar_thenFalse() {
        Field metty = new Field("Matthy");

        assertFalse(matt.isSimilar(metty, 1));
    }

    @Test
    public void givenFieldAndInitial_whenAskSimilar_thenFalse() {
        Field m = new Field("M");

        assertFalse(matt.isSimilar(m, 2));
    }

    @Test
    void givenDifferentFieldsAndHighThreshold_whenAskSimilar_thenTrue() {
        Field charles = new Field("Charles");

        assertTrue(matt.isSimilar(charles, 6));
    }

    @Test
    void givenTwoEmptyFields_whenAskSimilar_thenFalse() {
        Field empty = new Field("");
        Field empty2 = new Field("");

        assertFalse(empty.isSimilar(empty2, 0));
    }

    @Test
    void givenEmptyAndNotEmptyFields_whenAskSimilar_thenFalse() {
        Field empty = new Field("");

        assertFalse(empty.isSimilar(matt, 4));
        assertFalse(matt.isSimilar(empty, 4));
    }
    @Test
    void givenSimilarFieldsWithSideSpaces_whenAskSimilar_thenTrue() {
        Field mattWithSpaces = new Field("  Matt    ");

        assertTrue(matt.isSimilar(mattWithSpaces, 0));
    }

    @Test
    void givenSimilarFieldsWithDifferentCases_whenAskSimilar_thenTrue() {
        Field upperCaseMatt = new Field("MATT");
        Field lowerCaseMatt = new Field("matt");

        assertTrue(matt.isSimilar(upperCaseMatt, 0));
        assertTrue(matt.isSimilar(lowerCaseMatt, 0));
    }

    @Test
    void givenSimilarNumericalFields_whenAskSimilar_thenTrue() {
        Field numerical = new Field("1234");
        Field numerical2 = new Field("12345");

        assertTrue(numerical.isSimilar(numerical, 0));
        assertTrue(numerical.isSimilar(numerical2, 1));
    }

    @Test
    void givenSimilarEmailFields_whenAskSimilar_thenTrue() {
        Field email = new Field("luke@gmail.com");
        Field email2 = new Field("luke12@gmail.com");

        assertTrue(email.isSimilar(email, 0));
        assertTrue(email.isSimilar(email2, 2));
    }

    @Test
    void givenNullFields_whenAskSimilar_thenFalse() {
        Field nullField = new Field(null);

        assertFalse(matt.isSimilar(nullField, 2));
        assertFalse(nullField.isSimilar(matt, 2));
        assertFalse(matt.isSimilar(null, 2));
    }

    @Test
    void givenSameInitialFields_whenAskSameInitial_thenTrue() {
        Field m = new Field("M");

        assertTrue(matt.sameInitial(m));
        assertTrue(m.sameInitial(matt));
    }

    @Test
    void givenDifferentInitialFields_whenAskSameInitial_thenFalse() {
        Field luke = new Field("Luke");
        Field l = new Field("L");

        assertFalse(luke.sameInitial(matt));
        assertFalse(l.sameInitial(matt));
    }

    @Test
    void givenSameInitialFieldsWithDifferentCase_whenAskSameInitial_thenTrue() {
        Field lowerCaseM = new Field("m");

        assertTrue(matt.sameInitial(lowerCaseM));
        assertTrue(lowerCaseM.sameInitial(matt));
    }

    @Test
    void givenEmptyFields_whenAskSameInitial_thenFalse() {
        Field empty = new Field("");

        assertFalse(empty.sameInitial(empty));
        assertFalse(empty.sameInitial(matt));
        assertFalse(matt.sameInitial(empty));
    }

    @Test
    void givenNullFields_whenAskSameInitial_thenFalse() {
        Field nullField = new Field(null);

        assertFalse(matt.sameInitial(nullField));
        assertFalse(nullField.sameInitial(matt));
        assertFalse(matt.sameInitial(null));
    }

}