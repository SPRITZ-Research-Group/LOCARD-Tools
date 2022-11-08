package com.microsoft.applications.telemetry.core;

public final class BuildConfig {
    public static final String BUILD_TYPE = "release";
    public static boolean DEBUG = false;
    public static final String EXP = "ECS";
    public static final String FLAVOR = "exp";
    public static final String PACKAGE_NAME = "com.microsoft.applications.telemetry";
    public static final String VERSION_CODE = Integer.toString(1);
    public static final String VERSION_NAME = "3.0.5.0";

    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }
}
