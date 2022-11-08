package com.appboy.b;

public enum e implements com.appboy.e.e<String> {
    MALE,
    FEMALE,
    OTHER,
    UNKNOWN,
    NOT_APPLICABLE,
    PREFER_NOT_TO_SAY;

    public final String a() {
        switch (this) {
            case MALE:
                return "m";
            case FEMALE:
                return "f";
            case OTHER:
                return "o";
            case UNKNOWN:
                return "u";
            case NOT_APPLICABLE:
                return "n";
            case PREFER_NOT_TO_SAY:
                return "p";
            default:
                return null;
        }
    }
}
