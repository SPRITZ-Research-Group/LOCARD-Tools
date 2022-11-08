package com.facebook.imagepipeline.a.d;

public final class a {
    public static void a(int[] frameDurationMs) {
        for (int i = 0; i < frameDurationMs.length; i++) {
            if (frameDurationMs[i] < 11) {
                frameDurationMs[i] = 100;
            }
        }
    }

    public static int b(int[] frameDurationMs) {
        int totalMs = 0;
        for (int i : frameDurationMs) {
            totalMs += i;
        }
        return totalMs;
    }

    public static int[] c(int[] frameDurationsMs) {
        int[] frameTimestampsMs = new int[frameDurationsMs.length];
        int accumulatedDurationMs = 0;
        for (int i = 0; i < frameDurationsMs.length; i++) {
            frameTimestampsMs[i] = accumulatedDurationMs;
            accumulatedDurationMs += frameDurationsMs[i];
        }
        return frameTimestampsMs;
    }
}
