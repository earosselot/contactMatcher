package com.earosselot.model;

import java.util.*;

public class ContactListMatcher {

    private final ScoreMapper scoreMapper;
    private final List<Contact> contacts;
    private final ContactComparator contactComparator;

    public ContactListMatcher(List<Contact> contacts, List<ComparatorScores> comparatorScores, ScoreMapper scoreMapper, ContactComparator contactComparator) {
        this.contacts = contacts;
        this.scoreMapper = scoreMapper;
        this.contactComparator = contactComparator;
    }

    public static ContactListMatcher defaultContactListMatcher(List<Contact> contacts) {

        var comparatorScores = List.of(
                new ComparatorScores.Builder(FieldName.FIRST_NAME).buildDefaultImportantWithInitialScores(),
                new ComparatorScores.Builder(FieldName.LAST_NAME).buildDefaultImportantWithInitialScores(),
                new ComparatorScores.Builder(FieldName.EMAIL).buildDefaultImportantWithoutInitialScores(),
                new ComparatorScores.Builder(FieldName.ZIP_CODE).build(),
                new ComparatorScores.Builder(FieldName.ADDRESS).build()
        );
        var contactComparator = new ContactComparator(comparatorScores);
        var scoreMapper = new ScoreMapper(35, 50, 75);
        return new ContactListMatcher(contacts, comparatorScores, scoreMapper, contactComparator);
    }

    public List<ContactMatch> match() {

        Set<Object> hasMatch = new HashSet<>();
        List<ContactMatch> matches = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            if (!hasMatch.contains(contacts.get(i).getId())) {

                for (int j = i + 1; j < contacts.size(); j++) {
                    var c1 = contacts.get(i);
                    var c2 = contacts.get(j);
                    var matchLikelihoodScore = contactComparator.matchLikelihoodScore(c1, c2);

                    if (scoreMapper.isMatch(matchLikelihoodScore)) {
                        hasMatch.add(contacts.get(j).getId());
                        String accuracy = scoreMapper.getMatchAccuracy(matchLikelihoodScore);
                        ContactMatch contactMatch = new ContactMatch(c1.getId(), c2.getId(), accuracy);
                        matches.add(contactMatch);
                    }
                }
            }
        }

        return matches;
    }


}
