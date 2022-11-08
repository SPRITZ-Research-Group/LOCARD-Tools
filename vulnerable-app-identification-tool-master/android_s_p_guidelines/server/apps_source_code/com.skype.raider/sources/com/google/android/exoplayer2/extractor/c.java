package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.a.b;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.f;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.extractor.ts.a;
import com.google.android.exoplayer2.extractor.ts.o;
import java.lang.reflect.Constructor;

public final class c implements i {
    private static final Constructor<? extends f> a;
    private int b;
    private int c;
    private int d;
    private int e = 1;
    private int f;

    static {
        Constructor<? extends f> flacExtractorConstructor = null;
        try {
            flacExtractorConstructor = Class.forName("com.google.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(f.class).getConstructor(new Class[0]);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        }
        a = flacExtractorConstructor;
    }

    public final synchronized f[] a() {
        f[] extractors;
        int i = 11;
        synchronized (this) {
            if (a != null) {
                i = 12;
            }
            extractors = new f[i];
            extractors[0] = new MatroskaExtractor(this.b);
            extractors[1] = new FragmentedMp4Extractor(this.c);
            extractors[2] = new f();
            extractors[3] = new Mp3Extractor(this.d);
            extractors[4] = new com.google.android.exoplayer2.extractor.ts.c();
            extractors[5] = new a();
            extractors[6] = new TsExtractor(this.e, this.f);
            extractors[7] = new b();
            extractors[8] = new com.google.android.exoplayer2.extractor.b.c();
            extractors[9] = new o();
            extractors[10] = new com.google.android.exoplayer2.extractor.c.a();
            if (a != null) {
                try {
                    extractors[11] = (f) a.newInstance(new Object[0]);
                } catch (Exception e) {
                    throw new IllegalStateException("Unexpected error creating FLAC extractor", e);
                }
            }
        }
        return extractors;
    }
}
