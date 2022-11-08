package com.microsoft.applications.telemetry.core;

import java.util.ArrayList;
import java.util.Iterator;

public final class Preconditions {
    private Preconditions() {
        throw new AssertionError();
    }

    public static String isNotNullOrEmpty(String object, String message) throws IllegalArgumentException {
        if (object != null && !object.isEmpty()) {
            return object;
        }
        throw new IllegalArgumentException(message);
    }

    public static String isNullOrEmpty(String object, String message) throws IllegalArgumentException {
        if (object == null || object.isEmpty()) {
            return object;
        }
        throw new IllegalArgumentException(message);
    }

    public static <T> T isNotNull(T object, String message) throws IllegalArgumentException {
        if (object != null) {
            return object;
        }
        throw new IllegalArgumentException(message);
    }

    public static void isTrue(boolean condition, String message) throws IllegalArgumentException {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void areEqual(long expected, long actual, String message) throws IllegalArgumentException {
        boolean z;
        if (expected == actual) {
            z = true;
        } else {
            z = false;
        }
        isTrue(z, String.format("[Failed %d==%d] : %s", new Object[]{Long.valueOf(expected), Long.valueOf(actual), message}));
    }

    public static void validateKey(String key, String message) {
        boolean isFirstCharacterAlphanumeric;
        isNotNullOrEmpty(key, message);
        boolean isValid = true;
        if (key.length() > 100) {
            isValid = false;
        }
        if (Character.isDigit(key.charAt(0)) || isCharacterASCIILetter(key.charAt(0))) {
            isFirstCharacterAlphanumeric = true;
        } else {
            isFirstCharacterAlphanumeric = false;
        }
        boolean isLastCharacterAlphanumeric;
        if (Character.isDigit(key.charAt(key.length() - 1)) || isCharacterASCIILetter(key.charAt(key.length() - 1))) {
            isLastCharacterAlphanumeric = true;
        } else {
            isLastCharacterAlphanumeric = false;
        }
        if (!(isFirstCharacterAlphanumeric && isLastCharacterAlphanumeric)) {
            isValid = false;
        }
        int i = 1;
        while (i < key.length() - 1) {
            if (!(Character.isDigit(key.charAt(i)) || isCharacterASCIILetter(key.charAt(i)) || key.charAt(i) == '_' || key.charAt(i) == '.')) {
                isValid = false;
            }
            i++;
        }
        if (!isValid) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isValidNameAndType(String input) {
        boolean isFirstCharacterAlphanumeric;
        boolean isValid = true;
        if (input == null || input.isEmpty()) {
            isValid = false;
        }
        if (input.length() < 4) {
            isValid = false;
        }
        if (input.length() > 100) {
            isValid = false;
        }
        if (Character.isDigit(input.charAt(0)) || isCharacterASCIILetter(input.charAt(0))) {
            isFirstCharacterAlphanumeric = true;
        } else {
            isFirstCharacterAlphanumeric = false;
        }
        boolean isLastCharacterAlphanumeric;
        if (Character.isDigit(input.charAt(input.length() - 1)) || isCharacterASCIILetter(input.charAt(input.length() - 1))) {
            isLastCharacterAlphanumeric = true;
        } else {
            isLastCharacterAlphanumeric = false;
        }
        if (!(isFirstCharacterAlphanumeric && isLastCharacterAlphanumeric)) {
            isValid = false;
        }
        int i = 1;
        while (i < input.length() - 1) {
            if (!(Character.isDigit(input.charAt(i)) || isCharacterASCIILetter(input.charAt(i)) || input.charAt(i) == '_')) {
                isValid = false;
            }
            i++;
        }
        return isValid;
    }

    public static String isValidToken(String appToken, String message) {
        isNotNullOrEmpty(appToken, message);
        int checksumOffset = appToken.lastIndexOf("-");
        if (checksumOffset == -1 || checksumOffset + 1 == appToken.length()) {
            throw new IllegalArgumentException(message);
        }
        String checksum = appToken.substring(checksumOffset + 1);
        if (checksumOffset < 0) {
            checksumOffset = 0;
        }
        if (checksum.equals(computeChecksum(appToken.substring(0, checksumOffset)))) {
            return appToken;
        }
        throw new IllegalArgumentException(message);
    }

    private static String computeChecksum(String input) {
        int row;
        int inputLength = input.length();
        ArrayList<Integer> evenNumbers = new ArrayList();
        ArrayList<Integer> oddNumbers = new ArrayList();
        for (row = 0; row < inputLength; row++) {
            int charAsInt = input.charAt(row);
            if (charAsInt % 2 == 0) {
                evenNumbers.add(Integer.valueOf(charAsInt));
            } else {
                oddNumbers.add(Integer.valueOf(charAsInt));
            }
        }
        int checksum = 0;
        Iterator it = oddNumbers.iterator();
        while (it.hasNext()) {
            checksum += ((Integer) it.next()).intValue();
        }
        Iterator it2 = evenNumbers.iterator();
        while (it2.hasNext()) {
            String numberString = String.valueOf(((Integer) it2.next()).intValue() * 2);
            int numberDigitsSum = 0;
            for (row = 0; row < numberString.length(); row++) {
                numberDigitsSum += numberString.charAt(row);
            }
            checksum += numberDigitsSum;
        }
        return String.valueOf(checksum);
    }

    private static boolean isCharacterASCIILetter(int c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }
}
