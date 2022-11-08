package com.microsoft.dl.utils;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Trace;
import com.microsoft.dl.BuildInfo;
import com.microsoft.dl.BuildInfo.Flavour;
import com.microsoft.dl.PackageInfo;

public final class Systrace {
    private static final Section[] a = Section.values();
    private static final boolean b;

    public enum Section {
        CaptureVideo,
        DeliverVideo,
        IssueFakeVideoFrame,
        ReadFakeVideoFrame,
        CopyFakeVideoFrame,
        PreviewFakeVideoFrame
    }

    static {
        boolean z = BuildInfo.FLAVOUR != Flavour.RELEASE && VERSION.SDK_INT >= 18;
        b = z;
    }

    private Systrace() {
    }

    @TargetApi(18)
    public static void begin(Section section) {
        if (b) {
            Trace.beginSection(section.toString());
        }
    }

    public static void begin(int key) {
        try {
            begin(a[key]);
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
        }
    }

    @TargetApi(18)
    public static void end() {
        try {
            if (b) {
                Trace.endSection();
            }
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
        }
    }
}
