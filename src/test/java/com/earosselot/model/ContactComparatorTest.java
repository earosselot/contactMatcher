package com.earosselot.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactComparatorTest {

    @Test
    void givenContactsWithSameDataAndDefaultComparator_whenMatchLikelihoodScore_then100() {
        var comparatorScores = getDefaultComparatorScores();
        Contact c1 = new Contact.Builder(1).firstName("Luke")
                .lastName("Skywalker").email("luke@gmail.com")
                .zipCode("1234").address("4123 Helm Str.").build();
        Contact c2 = new Contact.Builder(1).firstName("Luke")
                .lastName("Skywalker").email("luke@gmail.com")
                .zipCode("1234").address("4123 Helm Str.").build();
        ContactComparator cc = new ContactComparator(comparatorScores);
        assertEquals(100, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithSameNameAndEmailAndDefaultComparator_whenMatchLikelihoodScore_then90() {
        var comparatorScores = getDefaultComparatorScores();
        Contact c1 = new Contact.Builder(1).firstName("Luke")
                .lastName("Skywalker").email("luke@gmail.com").build();
        Contact c2 = new Contact.Builder(1).firstName("Luke")
                .lastName("Skywalker").email("luke@gmail.com").build();
        ContactComparator cc = new ContactComparator(comparatorScores);

        assertEquals(90, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithSameFirstNameAndDefaultComparator_whenMatchLikelihoodScore_then30() {
        var comparatorScores = getDefaultComparatorScores();
        Contact c1 = new Contact.Builder(1).firstName("Luke")
                .lastName("Skywalker").email("luke@gmail.com").build();
        Contact c2 = new Contact.Builder(1).firstName("Luke")
                .lastName("Perez").email("perez@gmail.com").build();
        ContactComparator cc = new ContactComparator(comparatorScores);

        assertEquals(30, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithSameAddressAndZipCodeAndDefaultComparator_whenMatchLikelihoodScore_then10() {
        Contact c1 = new Contact.Builder(1).firstName("Luke")
                .lastName("Skywalker").email("luke@gmail.com")
                .zipCode("1234").address("4123 Helm Str.").build();
        Contact c2 = new Contact.Builder(1).firstName("Brian")
                .lastName("Perez").email("perez@gmail.com")
                .zipCode("1234").address("4123 Helm Str.").build();
        ContactComparator cc = new ContactComparator(getDefaultComparatorScores());

        assertEquals(10, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithSimilarNameAndDefaultComparator_whenMatchLikelihoodScore_then25() {
        Contact c1 = new Contact.Builder(1).firstName("Luke").build();
        Contact c2 = new Contact.Builder(1).firstName("Luk").build();

        ContactComparator cc = new ContactComparator(getDefaultComparatorScores());

        assertEquals(25, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithSimilarLastNameAndDefaultComparator_whenMatchLikelihoodScore_then25() {
        Contact c1 = new Contact.Builder(1).lastName("Skywalker").build();
        Contact c2 = new Contact.Builder(1).lastName("Skywaker").build();

        ContactComparator cc = new ContactComparator(getDefaultComparatorScores());

        assertEquals(25, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithInitialLastNameAndDefaultComparator_whenMatchLikelihoodScore_then15() {
        Contact c1 = new Contact.Builder(1).lastName("Skywalker").build();
        Contact c2 = new Contact.Builder(1).lastName("S").build();

        ContactComparator cc = new ContactComparator(getDefaultComparatorScores());

        assertEquals(15, cc.matchLikelihoodScore(c1, c2));
    }

    @Test
    void givenContactsWithInitialEmailAndDefaultComparator_whenMatchLikelihoodScore_then0() {
        Contact c1 = new Contact.Builder(1).email("skywalker@gmail.com").build();
        Contact c2 = new Contact.Builder(1).email("s").build();

        ContactComparator cc = new ContactComparator(getDefaultComparatorScores());

        assertEquals(0, cc.matchLikelihoodScore(c1, c2));
    }

    private static List<ComparatorScores> getDefaultComparatorScores() {
        var scoresFirstName = new ComparatorScores.Builder(FieldName.FIRST_NAME)
                .buildDefaultImportantWithInitialScores();
        var scoresLastName = new ComparatorScores.Builder(FieldName.LAST_NAME)
                .buildDefaultImportantWithInitialScores();
        var scoresEmail = new ComparatorScores.Builder(FieldName.EMAIL)
                .buildDefaultImportantWithoutInitialScores();
        var scoresZipCode = new ComparatorScores.Builder(FieldName.ZIP_CODE).build();
        var scoresAddress = new ComparatorScores.Builder(FieldName.ADDRESS).build();

        return List.of(scoresFirstName, scoresLastName, scoresEmail, scoresZipCode, scoresAddress);
    }

}