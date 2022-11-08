package com.skype.android.video.hw.utils;

public final class SliqBuild {
    public static final boolean DEBUG = isDebug();
    public static final boolean INTERNAL = isInternal();
    public static final boolean RELEASE = isRelease();

    private static native boolean isDebug();

    private static native boolean isInternal();

    private static native boolean isRelease();

    private SliqBuild() {
    }
}
