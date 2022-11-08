package com.google.android.exoplayer2.video;

import android.view.Surface;
import com.google.android.exoplayer2.Format;
import defpackage.atg;

public interface l {

    public final /* synthetic */ class -CC {
        public static void $default$a(l lVar, int i, long j) {
        }

        public static void $default$a(l lVar, Surface surface) {
        }

        public static void $default$a(l lVar, atg atg) {
        }

        public static void $default$a(l lVar, Format format) {
        }

        public static void $default$a(l lVar, String str, long j, long j2) {
        }

        public static void $default$b(l lVar, atg atg) {
        }

        public static void $default$onVideoSizeChanged(l lVar, int i, int i2, int i3, float f) {
        }
    }

    void a(int i, long j);

    void a(Surface surface);

    void a(atg atg);

    void a(Format format);

    void a(String str, long j, long j2);

    void b(atg atg);

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
