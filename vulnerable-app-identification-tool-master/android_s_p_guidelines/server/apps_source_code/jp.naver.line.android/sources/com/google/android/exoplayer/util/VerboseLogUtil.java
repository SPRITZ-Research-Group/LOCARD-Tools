package com.google.android.exoplayer.util;

public final class VerboseLogUtil {
    private static volatile boolean enableAllTags;
    private static volatile String[] enabledTags;

    private VerboseLogUtil() {
    }

    public static void setEnabledTags(String... strArr) {
        enabledTags = strArr;
        enableAllTags = false;
    }

    public static void setEnableAllTags(boolean z) {
        enableAllTags = z;
    }

    public static boolean isTagEnabled(String str) {
        if (enableAllTags) {
            return true;
        }
        String[] strArr = enabledTags;
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areAllTagsEnabled() {
        return enableAllTags;
    }
}
