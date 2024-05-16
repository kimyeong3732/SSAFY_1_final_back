package com.mycom.springboot.common;

public class SimpleHasher {
    public static int simpleHash(String password) {
        int hashValue = 0;
        for (char c : password.toCharArray()) {
            hashValue = (hashValue * 31 + c) % 1000000007;
        }
        return hashValue;
    }

    public static boolean checkPassword(String password, int storedHash) {
        int hashedPassword = simpleHash(password);
        return hashedPassword == storedHash;
    }
}