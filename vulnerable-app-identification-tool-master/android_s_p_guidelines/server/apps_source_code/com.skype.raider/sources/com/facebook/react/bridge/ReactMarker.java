package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
public class ReactMarker {
    @Nullable
    private static a sMarkerListener = null;

    public interface a {
    }

    public static void initialize(a listener) {
        if (sMarkerListener == null) {
            sMarkerListener = listener;
        }
    }

    @DoNotStrip
    public static void clearMarkerListener() {
        sMarkerListener = null;
    }

    @DoNotStrip
    public static void logMarker(String name) {
        logMarker(name, null);
    }

    @DoNotStrip
    public static void logMarker(String name, int instanceKey) {
        logMarker(name, null, instanceKey);
    }

    @DoNotStrip
    public static void logMarker(String name, @Nullable String tag) {
        logMarker(name, tag, 0);
    }

    @DoNotStrip
    public static void logMarker(String name, @Nullable String tag, int instanceKey) {
        if (sMarkerListener != null) {
            aj.valueOf(name);
        }
    }

    @DoNotStrip
    public static void logMarker(aj name) {
        logMarker(name, null, 0);
    }

    @DoNotStrip
    public static void logMarker(aj name, int instanceKey) {
        logMarker(name, null, instanceKey);
    }

    @DoNotStrip
    public static void logMarker(aj name, @Nullable String tag) {
        logMarker(name, null, 0);
    }

    @DoNotStrip
    public static void logMarker(aj name, @Nullable String tag, int instanceKey) {
    }
}
