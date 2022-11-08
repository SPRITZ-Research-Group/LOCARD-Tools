package com.google.gson;

public enum c implements d {
    ;

    static String a(String name, String separator) {
        StringBuilder translation = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            if (Character.isUpperCase(character) && translation.length() != 0) {
                translation.append(separator);
            }
            translation.append(character);
        }
        return translation.toString();
    }

    static String a(String name) {
        StringBuilder fieldNameBuilder = new StringBuilder();
        int index = 0;
        char firstCharacter = name.charAt(0);
        while (index < name.length() - 1 && !Character.isLetter(firstCharacter)) {
            fieldNameBuilder.append(firstCharacter);
            index++;
            firstCharacter = name.charAt(index);
        }
        if (index == name.length()) {
            return fieldNameBuilder.toString();
        }
        if (Character.isUpperCase(firstCharacter)) {
            return name;
        }
        String modifiedTarget;
        char toUpperCase = Character.toUpperCase(firstCharacter);
        index++;
        if (index < name.length()) {
            modifiedTarget = toUpperCase + name.substring(index);
        } else {
            modifiedTarget = String.valueOf(toUpperCase);
        }
        return fieldNameBuilder.append(modifiedTarget).toString();
    }
}
