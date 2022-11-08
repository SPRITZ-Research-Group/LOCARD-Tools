package com.microsoft.dl.utils;

import com.microsoft.dl.PackageInfo;

public final class Dummy {
    private Dummy() {
    }

    public static ClassLoader getClassLoader() {
        try {
            return Dummy.class.getClassLoader();
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return null;
        }
    }
}
