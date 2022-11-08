package com.microsoft.dl.utils;

public final class Clock {
    public static native long getAbsoluteTime(long j);

    public static native long getDurationUs(long j, long j2);

    public static native long getPlatformTime();

    private Clock() {
    }
}
