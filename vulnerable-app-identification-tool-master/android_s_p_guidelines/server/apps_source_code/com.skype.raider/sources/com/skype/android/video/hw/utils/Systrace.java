package com.skype.android.video.hw.utils;

import android.os.Build.VERSION;
import android.os.Trace;

public final class Systrace {

    public enum Section {
        CaptureVideo,
        RenderVideoPreview,
        RenderVideoTarget
    }

    private Systrace() {
    }

    public static void begin(Section section) {
        if ((SliqBuild.DEBUG || SliqBuild.INTERNAL) && VERSION.SDK_INT >= 18) {
            Trace.beginSection(section.toString());
        }
    }

    public static void end() {
        if ((SliqBuild.DEBUG || SliqBuild.INTERNAL) && VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
