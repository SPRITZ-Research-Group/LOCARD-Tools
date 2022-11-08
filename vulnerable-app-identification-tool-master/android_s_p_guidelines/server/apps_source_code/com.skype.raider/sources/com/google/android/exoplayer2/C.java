package com.google.android.exoplayer2;

import com.google.android.exoplayer2.d.s;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

public final class C {
    public static final int a;
    public static final UUID b = new UUID(0, 0);
    public static final UUID c = new UUID(1186680826959645954L, -5988876978535335093L);
    public static final UUID d = new UUID(-1301668207276963122L, -6645017420763422227L);
    public static final UUID e = new UUID(-7348484286925749626L, -6083546864340672619L);

    @Retention(RetentionPolicy.SOURCE)
    public @interface BufferFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorRange {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorSpace {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorTransfer {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CryptoMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Encoding {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PcmEncoding {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SelectionFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StereoMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VideoScalingMode {
    }

    static {
        int i;
        if (s.a < 23) {
            i = 1020;
        } else {
            i = 6396;
        }
        a = i;
    }

    public static long a(long timeUs) {
        return timeUs == -9223372036854775807L ? -9223372036854775807L : timeUs / 1000;
    }

    public static long b(long timeMs) {
        return timeMs == -9223372036854775807L ? -9223372036854775807L : 1000 * timeMs;
    }
}
