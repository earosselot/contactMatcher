package com.earosselot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {


    @Test
    void givenContactsWithSameName_whenAskSameName_thenTrue() {
        Contact lukeSkywalker = new Contact.Builder(1).firstName("Luke").lastName("Skywalker").build();
        Contact lukePerez = new Contact.Builder(1).firstName("Luke").lastName("Perez").build();

        assertTrue(lukeSkywalker.hasSame(FieldName.FIRST_NAME, lukePerez));
    }

    @Test
    void givenContactsWithDifferentLastName_whenAskSameLastName_thenFalse() {
        Contact lukeSkywalker = new Contact.Builder(1).firstName("Luke").lastName("Skywalker").build();
        Contact lukePerez = new Contact.Builder(1).firstName("Luke").lastName("Perez").build();

        assertFalse(lukeSkywalker.hasSame(FieldName.LAST_NAME, lukePerez));
    }

    @Test
    void givenContactsWithEmptyNames_whenAskSameName_thenFalse() {
        Contact emptySkywalker = new Contact.Builder(1).firstName("").lastName("Skywalker").build();
        Contact empty = new Contact.Builder(1).firstName("").lastName("").build();

        assertFalse(emptySkywalker.hasSame(FieldName.FIRST_NAME, empty));
        assertFalse(empty.hasSame(FieldName.FIRST_NAME, emptySkywalker));
        assertFalse(emptySkywalker.hasSame(FieldName.LAST_NAME, empty));
        assertFalse(empty.hasSame(FieldName.LAST_NAME, emptySkywalker));
    }

    @Test
    void givenContactsNullName_whenAskSameName_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact nullNameContact = new Contact.Builder(1).firstName(null).build();

        assertFalse(nullNameContact.hasSame(FieldName.FIRST_NAME, luke));
        assertFalse(luke.hasSame(FieldName.LAST_NAME, nullNameContact));
    }

    @Test
    void givenContactsWithNoFields_whenAskSameName_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact perez = new Contact.Builder(1).lastName("Perez").build();

        assertFalse(luke.hasSame(FieldName.FIRST_NAME, perez));
        assertFalse(luke.hasSame(FieldName.LAST_NAME, perez));
        assertFalse(luke.hasSame(FieldName.EMAIL, perez));
    }

    @Test
    void givenContactsWithSameNameWithDifferentCase_whenAskSameName_thenTrue() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact capitalLuke = new Contact.Builder(1).firstName("LUKE").build();
        Contact lowerCaseLuke = new Contact.Builder(1).firstName("luke").build();

        assertTrue(luke.hasSame(FieldName.FIRST_NAME, capitalLuke));
        assertTrue(luke.hasSame(FieldName.FIRST_NAME, lowerCaseLuke));
    }

    @Test
    void givenNullContact_whenAskSameName_thenFalse() {

        Contact luke = new Contact.Builder(1).firstName("Luke").build();

        assertFalse(luke.hasSame(FieldName.FIRST_NAME, null));
    }

    @Test
    void givenNullFieldName_whenAskSameName_thenThrow() {

        Contact luke = new Contact.Builder(1).firstName("Luke").build();

        assertThrows(IllegalArgumentException.class, () ->
                luke.hasSame(null, luke));
    }

    @Test
    void givenSameFirstNameContacts_whenAskSimilarFirstName_thenTrue() {

        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact luka = new Contact.Builder(1).firstName("Luka").build();

        assertTrue(luke.hasSimilar(FieldName.FIRST_NAME, luka, 1));
    }

    @Test
    void givenDifferentFirstNameContacts_whenAskSimilarFirstName_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact greg = new Contact.Builder(1).firstName("Greg").build();

        assertFalse(luke.hasSimilar(FieldName.FIRST_NAME, greg, 2));
    }

    @Test
    void givenNullFirstNameContacts_whenAskSimilarFirstName_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact nullFirstContact = new Contact.Builder(1).firstName(null).build();

        assertFalse(luke.hasSimilar(FieldName.FIRST_NAME, nullFirstContact, 2));
        assertFalse(nullFirstContact.hasSimilar(FieldName.FIRST_NAME, luke, 2));
    }

    @Test
    void givenNullFieldName_whenAskSimilarFirstName_thenThrow() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();

        assertThrows(IllegalArgumentException.class, () ->
                luke.hasSimilar(null, luke, 2));
    }

    @Test
    void givenContactsWithFirstNameAndInitial_whenAskCanBeInitial_thenTrue() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact l = new Contact.Builder(1).firstName("L").build();

        assertTrue(luke.canBeInitial(FieldName.FIRST_NAME, l));
        assertTrue(l.canBeInitial(FieldName.FIRST_NAME, luke));
    }

    @Test
    void givenDifferentFirstNamesButSameInitial_whenAskCanBeInitial_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact leto = new Contact.Builder(1).firstName("Leto").build();

        assertFalse(luke.canBeInitial(FieldName.FIRST_NAME, leto));
        assertFalse(leto.canBeInitial(FieldName.FIRST_NAME, luke));
    }

    @Test
    void givenEmptyName_whenAskCanBeInitial_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact empty = new Contact.Builder(1).firstName("").build();

        assertFalse(luke.canBeInitial(FieldName.FIRST_NAME, empty));
        assertFalse(empty.canBeInitial(FieldName.FIRST_NAME, luke));
    }

    @Test
    void givenNullNameContact_whenAskCanBeInitial_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();
        Contact nullContact = new Contact.Builder(1).firstName(null).build();

        assertFalse(luke.canBeInitial(FieldName.FIRST_NAME, nullContact));
        assertFalse(nullContact.canBeInitial(FieldName.FIRST_NAME, luke));
    }

    @Test
    void givenNullContact_whenAskCanBeInitial_thenFalse() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();

        assertFalse(luke.canBeInitial(FieldName.FIRST_NAME, null));
    }

    @Test
    void givenNullFieldName_whenAskCanBeInitial_thenThrow() {
        Contact luke = new Contact.Builder(1).firstName("Luke").build();

        assertThrows(IllegalArgumentException.class, () ->
                luke.canBeInitial(null, luke));
    }
}