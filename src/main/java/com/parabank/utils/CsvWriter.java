package com.parabank.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvWriter {

    private static final String FILE_PATH = "src/test/resources/registered_users.csv";

    public static void saveCredentials(String username, String password) {
        boolean fileExists = Files.exists(Paths.get(FILE_PATH));

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            // write header only if file is new
            if (!fileExists) {
                writer.println("username,password,timestamp");
            }
            writer.println(username + "," + password + "," + System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to save credentials to CSV: " + e.getMessage());
        }
    }
}