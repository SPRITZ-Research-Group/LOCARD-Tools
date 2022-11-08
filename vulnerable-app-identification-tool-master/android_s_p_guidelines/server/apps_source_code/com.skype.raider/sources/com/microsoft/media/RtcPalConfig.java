package com.microsoft.media;

public class RtcPalConfig {
    private static String TAG = "RtcPalConfig";
    private static boolean bLogcat = false;

    private static void enableLogcat(int enable) {
        if (enable == 0) {
            bLogcat = false;
        } else {
            bLogcat = true;
        }
    }

    public static boolean isLogcatEnabled() {
        return bLogcat;
    }
}
