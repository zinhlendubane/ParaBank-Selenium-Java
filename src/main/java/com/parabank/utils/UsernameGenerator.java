package com.parabank.utils;

public class UsernameGenerator {
    public static String generateUniqueUsername() {
        long timestamp = System.currentTimeMillis();
        return "ParaBanker" + timestamp;
    }
}
