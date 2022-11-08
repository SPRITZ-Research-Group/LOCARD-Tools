package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.extractor.h;
import java.util.Collections;
import java.util.List;

public interface t {

    public interface c {
        SparseArray<t> a();

        t a(int i, b bVar);
    }

    public static final class a {
        public final String a;
        public final int b;
        public final byte[] c;

        public a(String language, int type, byte[] initializationData) {
            this.a = language;
            this.b = type;
            this.c = initializationData;
        }
    }

    public static final class b {
        public final int a;
        public final String b;
        public final List<a> c;
        public final byte[] d;

        public b(int streamType, String language, List<a> dvbSubtitleInfos, byte[] descriptorBytes) {
            List emptyList;
            this.a = streamType;
            this.b = language;
            if (dvbSubtitleInfos == null) {
                emptyList = Collections.emptyList();
            } else {
                emptyList = Collections.unmodifiableList(dvbSubtitleInfos);
            }
            this.c = emptyList;
            this.d = descriptorBytes;
        }
    }

    public static final class d {
        private final String a;
        private final int b;
        private final int c;
        private int d;
        private String e;

        public d(int firstTrackId, int trackIdIncrement) {
            this(Integer.MIN_VALUE, firstTrackId, trackIdIncrement);
        }

        public d(int programNumber, int firstTrackId, int trackIdIncrement) {
            this.a = programNumber != Integer.MIN_VALUE ? programNumber + "/" : "";
            this.b = firstTrackId;
            this.c = trackIdIncrement;
            this.d = Integer.MIN_VALUE;
        }

        public final void a() {
            this.d = this.d == Integer.MIN_VALUE ? this.b : this.d + this.c;
            this.e = this.a + this.d;
        }

        public final int b() {
            d();
            return this.d;
        }

        public final String c() {
            d();
            return this.e;
        }

        private void d() {
            if (this.d == Integer.MIN_VALUE) {
                throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
            }
        }
    }

    void a();

    void a(k kVar, boolean z);

    void a(q qVar, h hVar, d dVar);
}
