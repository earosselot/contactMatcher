package com.earosselot;

import com.earosselot.model.Contact;
import com.earosselot.model.ContactListMatcher;
import com.earosselot.model.ContactMatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Must provide csv as arg");
        }
        String csvFile = args[0];
        List<Contact> contacts = readContactsFromFile(csvFile);

        ContactListMatcher contactListMatcher = ContactListMatcher.defaultContactListMatcher(contacts);
        List<ContactMatch> matches = contactListMatcher.match();

        System.out.printf("Found %d matches\n", matches.size());
        writeContactMatchesToCSV(matches, "output.csv");
    }

    private static List<Contact> readContactsFromFile(String csvFile) {
        String line;
        String regexAvoidCommasBetweenQuotes = ",(?=([^\"]|\"[^\"]*\")*$)";

        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // header line
            while ((line = br.readLine()) != null) {
                String[] f = new String[6];
                String[] fields = line.split(regexAvoidCommasBetweenQuotes, -1);

                // Assuming the CSV has five columns: id, firstName, lastName, email, zipCode, Address
                int id = Integer.parseInt(fields[0]);
                String firstName = fields[1];
                String lastName = fields[2];
                String email = fields[3];
                String zipCode = fields[4];
                String address = fields[5];

                Contact contact = new Contact.Builder(id).firstName(firstName).lastName(lastName)
                        .email(email).zipCode(zipCode).address(address).build();
                contacts.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    public static void writeContactMatchesToCSV(List<ContactMatch> contactMatches, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Escribir encabezados
            writer.append("sourceId,matchId,accuracy\n");

            // Escribir datos
            for (ContactMatch match : contactMatches) {
                writer.append(String.valueOf(match.getSourceId()))
                        .append(",")
                        .append(String.valueOf(match.getMatchId()))
                        .append(",")
                        .append(match.getAccuracy())
                        .append("\n");
            }

            System.out.println("CSV file generated: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}