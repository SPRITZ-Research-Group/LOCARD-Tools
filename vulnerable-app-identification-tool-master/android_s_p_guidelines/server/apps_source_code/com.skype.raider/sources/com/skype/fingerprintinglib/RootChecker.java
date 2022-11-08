package com.skype.fingerprintinglib;

import java.io.File;

public class RootChecker {
    private static final String[] a = new String[]{"/su/bin/", "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};

    public static boolean a() {
        for (String where : a) {
            if (new File(where + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
