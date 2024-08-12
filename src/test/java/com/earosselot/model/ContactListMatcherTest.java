package com.earosselot.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactListMatcherTest {

    @Test
    void givenTwoHighMatchingContacts_whenDefaultContactListMatcherMatch_expectAHighMatch() {

        var c1 = new Contact.Builder(1).firstName("John").lastName("Doe").email("jhond@gmail.com").build();
        var c2 = new Contact.Builder(2).firstName("John").lastName("Doe").email("jhond@gmail.com").build();
        ContactListMatcher contactListMatcher = ContactListMatcher.defaultContactListMatcher(List.of(c1, c2));

        var expectedMatch = List.of(new ContactMatch(1, 2, "HIGH"));

        assertEquals(expectedMatch, contactListMatcher.match());
    }

    @Test
    void givenTwoMidMatchingContacts_whenDefaultContactListMatcherMatch_expectAMidMatch() {

        var c1 = new Contact.Builder(1).firstName("John").lastName("Doe").build();
        var c2 = new Contact.Builder(2).firstName("John").lastName("Doe").build();
        ContactListMatcher contactListMatcher = ContactListMatcher.defaultContactListMatcher(List.of(c1, c2));

        var expectedMatch = List.of(new ContactMatch(1, 2, "MEDIUM"));

        assertEquals(expectedMatch, contactListMatcher.match());
    }

    @Test
    void givenTwoLowMatchingContacts_whenDefaultContactListMatcherMatch_expectALowMatch() {

        var c1 = new Contact.Builder(1).firstName("John").address("13 Street").build();
        var c2 = new Contact.Builder(2).firstName("John").address("13 Street").build();
        ContactListMatcher contactListMatcher = ContactListMatcher.defaultContactListMatcher(List.of(c1, c2));

        var expectedMatch = List.of(new ContactMatch(1, 2, "LOW"));

        assertEquals(expectedMatch, contactListMatcher.match());
    }

    @Test
    void givenSeveralMatchingContacts_whenDefaultContactListMatcherMatch_expectAnIdEitherInSourceOrMatch() {

        var c1 = new Contact.Builder(1).firstName("John").lastName("Doe").build();
        var c2 = new Contact.Builder(2).firstName("John").lastName("Doe").build();
        var c3 = new Contact.Builder(3).firstName("John").lastName("Doe").build();
        ContactListMatcher contactListMatcher = ContactListMatcher.defaultContactListMatcher(List.of(c1, c2, c3));

        var expectedMatch = List.of(
                new ContactMatch(1, 2, "MEDIUM"),
                new ContactMatch(1, 3, "MEDIUM")
        );

        assertEquals(expectedMatch, contactListMatcher.match());
    }

}