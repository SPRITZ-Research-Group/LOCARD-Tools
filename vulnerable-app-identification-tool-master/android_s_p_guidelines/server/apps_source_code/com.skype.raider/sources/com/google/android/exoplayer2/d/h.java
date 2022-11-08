package com.google.android.exoplayer2.d;

public final class h {
    public static boolean a(String mimeType) {
        return "audio".equals(d(mimeType));
    }

    public static boolean b(String mimeType) {
        return "video".equals(d(mimeType));
    }

    public static boolean c(String mimeType) {
        return "text".equals(d(mimeType));
    }

    private static String d(String mimeType) {
        if (mimeType == null) {
            return null;
        }
        int indexOfSlash = mimeType.indexOf(47);
        if (indexOfSlash != -1) {
            return mimeType.substring(0, indexOfSlash);
        }
        throw new IllegalArgumentException("Invalid mime type: " + mimeType);
    }
}
