package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Track {
    public final int a;
    public final int b;
    public final long c;
    public final long d;
    public final long e;
    public final Format f;
    public final int g;
    public final i[] h;
    public final long[] i;
    public final long[] j;
    public final int k;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Transformation {
    }

    public Track(int id, int type, long timescale, long movieTimescale, long durationUs, Format format, int sampleTransformation, i[] sampleDescriptionEncryptionBoxes, int nalUnitLengthFieldLength, long[] editListDurations, long[] editListMediaTimes) {
        this.a = id;
        this.b = type;
        this.c = timescale;
        this.d = movieTimescale;
        this.e = durationUs;
        this.f = format;
        this.g = sampleTransformation;
        this.h = sampleDescriptionEncryptionBoxes;
        this.k = nalUnitLengthFieldLength;
        this.i = editListDurations;
        this.j = editListMediaTimes;
    }
}
