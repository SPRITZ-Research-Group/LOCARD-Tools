package com.microsoft.applications.telemetry.core;

class LibraryInfo {
    private static String libraryExperimentation = "ECS";
    private static String libraryLanguage = "Java";
    private static String libraryName = (libraryType + "-" + libraryPlatform + "-" + libraryLanguage + "-" + libraryProjection);
    private static String libraryPlatform = "Android";
    private static String libraryProjection = "no";
    private static String libraryType = "ACT";
    private static String libraryVersion = "3.0.5.0";

    LibraryInfo() {
    }

    public static String getLibraryVersion() {
        return libraryVersion;
    }

    public static String getLibraryName() {
        return libraryName;
    }

    public static String getLibraryType() {
        return libraryType;
    }

    public static String getLibraryLanguage() {
        return libraryLanguage;
    }

    public static String getLibraryPlatform() {
        return libraryPlatform;
    }

    public static String getLibraryProjection() {
        return libraryProjection;
    }

    public static String getLibraryExperimentation() {
        return libraryExperimentation;
    }
}
