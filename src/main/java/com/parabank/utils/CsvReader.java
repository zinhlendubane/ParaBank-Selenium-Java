package com.parabank.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    private static final String FILE_PATH = "src/test/resources/registered_users.csv";

    public static String[] getLastRegisteredUser() {
        String lastLine = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("username")) { // skip header
                    lastLine = line;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read credentials from CSV: " + e.getMessage());
        }

        if (lastLine == null) {
            throw new RuntimeException("No registered users found in CSV");
        }

        return lastLine.split(",");  // returns [username, password, timestamp]
    }
}