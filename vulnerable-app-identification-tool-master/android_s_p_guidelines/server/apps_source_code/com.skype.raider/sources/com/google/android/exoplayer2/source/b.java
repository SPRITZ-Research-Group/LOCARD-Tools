package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import com.google.android.exoplayer2.d.d;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.o;
import java.io.EOFException;
import java.io.IOException;

final class b implements com.google.android.exoplayer2.extractor.d.c, h, d, com.google.android.exoplayer2.upstream.o.a<a> {
    private boolean A;
    private long B;
    private long C;
    private long D;
    private int E;
    private boolean F;
    private boolean G;
    private final Uri a;
    private final f b;
    private final int c;
    private final Handler d;
    private final com.google.android.exoplayer2.source.c.a e;
    private final com.google.android.exoplayer2.source.e.a f;
    private final com.google.android.exoplayer2.upstream.b g;
    private final String h;
    private final o i = new o("Loader:ExtractorMediaPeriod");
    private final b j;
    private final d k;
    private final Runnable l;
    private final Runnable m;
    private final Handler n;
    private final SparseArray<com.google.android.exoplayer2.extractor.d> o;
    private com.google.android.exoplayer2.source.d.a p;
    private m q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private int v;
    private k w;
    private long x;
    private boolean[] y;
    private boolean[] z;

    final class a implements com.google.android.exoplayer2.upstream.o.c {
        final /* synthetic */ b a;
        private final Uri b;
        private final f c;
        private final b d;
        private final d e;
        private final l f = new l();
        private volatile boolean g;
        private boolean h = true;
        private long i;
        private long j = -1;

        public a(b this$0, Uri uri, f dataSource, b extractorHolder, d loadCondition) {
            this.a = this$0;
            this.b = (Uri) com.google.android.exoplayer2.d.a.a((Object) uri);
            this.c = (f) com.google.android.exoplayer2.d.a.a((Object) dataSource);
            this.d = (b) com.google.android.exoplayer2.d.a.a((Object) extractorHolder);
            this.e = loadCondition;
        }

        public final void a(long position, long timeUs) {
            this.f.a = position;
            this.i = timeUs;
            this.h = true;
        }

        public final void a() {
            this.g = true;
        }

        public final boolean b() {
            return this.g;
        }

        public final void c() throws IOException, InterruptedException {
            g input;
            Throwable th;
            int result = 0;
            while (result == 0 && !this.g) {
                try {
                    long position = this.f.a;
                    this.j = this.c.a(new DataSpec(this.b, position, this.a.h));
                    if (this.j != -1) {
                        this.j += position;
                    }
                    input = new com.google.android.exoplayer2.extractor.b(this.c, position, this.j);
                    try {
                        com.google.android.exoplayer2.extractor.f extractor = this.d.a(input, this.c.a());
                        if (this.h) {
                            extractor.a(position, this.i);
                            this.h = false;
                        }
                        while (result == 0 && !this.g) {
                            this.e.c();
                            result = extractor.a(input, this.f);
                            if (input.c() > 1048576 + position) {
                                position = input.c();
                                this.e.b();
                                this.a.n.post(this.a.m);
                            }
                        }
                        if (result == 1) {
                            result = 0;
                        } else {
                            this.f.a = input.c();
                        }
                        s.a(this.c);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    input = null;
                }
            }
            return;
            if (!(result == 1 || input == null)) {
                this.f.a = input.c();
            }
            s.a(this.c);
            throw th;
        }
    }

    private static final class b {
        private final com.google.android.exoplayer2.extractor.f[] a;
        private final h b;
        private com.google.android.exoplayer2.extractor.f c;

        public b(com.google.android.exoplayer2.extractor.f[] extractors, h extractorOutput) {
            this.a = extractors;
            this.b = extractorOutput;
        }

        public final com.google.android.exoplayer2.extractor.f a(g input, Uri uri) throws IOException, InterruptedException {
            if (this.c != null) {
                return this.c;
            }
            com.google.android.exoplayer2.extractor.f[] fVarArr = this.a;
            int length = fVarArr.length;
            int i = 0;
            loop0:
            while (i < length) {
                com.google.android.exoplayer2.extractor.f extractor = fVarArr[i];
                try {
                    if (extractor.a(input)) {
                        this.c = extractor;
                        input.a();
                        break loop0;
                    }
                    i++;
                } catch (EOFException e) {
                    i++;
                } finally {
                    input.a();
                }
            }
            if (this.c == null) {
                throw new l("None of the available extractors (" + s.a(this.a) + ") could read the stream.", uri);
            }
            this.c.a(this.b);
            return this.c;
        }

        public final void a() {
            if (this.c != null) {
                this.c = null;
            }
        }
    }

    private final class c implements g {
        final /* synthetic */ b a;
        private final int b;

        public c(b bVar, int track) {
            this.a = bVar;
            this.b = track;
        }

        public final boolean a() {
            return this.a.b(this.b);
        }

        public final void b() throws IOException {
            this.a.h();
        }

        public final int a(com.google.android.exoplayer2.h formatHolder, DecoderInputBuffer buffer, boolean formatRequired) {
            return this.a.a(this.b, formatHolder, buffer, formatRequired);
        }

        public final void a(long positionUs) {
            this.a.a(this.b, positionUs);
        }
    }

    static /* synthetic */ void a(b x0) {
        if (!x0.G && !x0.s && x0.q != null && x0.r) {
            int size = x0.o.size();
            int i = 0;
            while (i < size) {
                if (((com.google.android.exoplayer2.extractor.d) x0.o.valueAt(i)).d() != null) {
                    i++;
                } else {
                    return;
                }
            }
            x0.k.b();
            j[] jVarArr = new j[size];
            x0.z = new boolean[size];
            x0.y = new boolean[size];
            x0.x = x0.q.b();
            for (i = 0; i < size; i++) {
                boolean z;
                jVarArr[i] = new j(((com.google.android.exoplayer2.extractor.d) x0.o.valueAt(i)).d());
                String str = r0.f;
                if (com.google.android.exoplayer2.d.h.b(str) || com.google.android.exoplayer2.d.h.a(str)) {
                    z = true;
                } else {
                    z = false;
                }
                x0.z[i] = z;
                x0.A = z | x0.A;
            }
            x0.w = new k(jVarArr);
            x0.s = true;
            x0.f.a(new i(x0.x, x0.q.i_()), null);
            x0.p.a(x0);
        }
    }

    public final /* synthetic */ int a(com.google.android.exoplayer2.upstream.o.c cVar, final IOException iOException) {
        a aVar = (a) cVar;
        a(aVar);
        if (!(this.d == null || this.e == null)) {
            this.d.post(new Runnable(this) {
                final /* synthetic */ b b;

                public final void run() {
                }
            });
        }
        if (iOException instanceof l) {
            return 3;
        }
        int i;
        if (j() > this.E) {
            i = 1;
        } else {
            i = 0;
        }
        if (this.B == -1 && (this.q == null || this.q.b() == -9223372036854775807L)) {
            this.C = 0;
            this.u = this.s;
            int size = this.o.size();
            for (int i2 = 0; i2 < size; i2++) {
                com.google.android.exoplayer2.extractor.d dVar = (com.google.android.exoplayer2.extractor.d) this.o.valueAt(i2);
                boolean z = !this.s || this.y[i2];
                dVar.a(z);
            }
            aVar.a(0, 0);
        }
        this.E = j();
        return i == 0 ? 0 : 1;
    }

    public final /* synthetic */ void a(com.google.android.exoplayer2.upstream.o.c cVar) {
        a((a) cVar);
        this.F = true;
        if (this.x == -9223372036854775807L) {
            long k = k();
            this.x = k == Long.MIN_VALUE ? 0 : k + 10000;
            this.f.a(new i(this.x, this.q.i_()), null);
        }
        this.p.a(this);
    }

    public final /* synthetic */ void a(com.google.android.exoplayer2.upstream.o.c cVar, boolean z) {
        a((a) cVar);
        if (!z && this.v > 0) {
            int size = this.o.size();
            for (int i = 0; i < size; i++) {
                ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).a(this.y[i]);
            }
            this.p.a(this);
        }
    }

    public b(Uri uri, f dataSource, com.google.android.exoplayer2.extractor.f[] extractors, int minLoadableRetryCount, Handler eventHandler, com.google.android.exoplayer2.source.c.a eventListener, com.google.android.exoplayer2.source.e.a sourceListener, com.google.android.exoplayer2.upstream.b allocator, String customCacheKey) {
        this.a = uri;
        this.b = dataSource;
        this.c = minLoadableRetryCount;
        this.d = eventHandler;
        this.e = eventListener;
        this.f = sourceListener;
        this.g = allocator;
        this.h = customCacheKey;
        this.j = new b(extractors, this);
        this.k = new d();
        this.l = new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final void run() {
                b.a(this.a);
            }
        };
        this.m = new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (!this.a.G) {
                    this.a.p.a(this.a);
                }
            }
        };
        this.n = new Handler();
        this.D = -9223372036854775807L;
        this.o = new SparseArray();
        this.B = -1;
    }

    public final void c() {
        final b extractorHolder = this.j;
        this.i.a(new Runnable(this) {
            final /* synthetic */ b b;

            public final void run() {
                extractorHolder.a();
                int trackCount = this.b.o.size();
                for (int i = 0; i < trackCount; i++) {
                    ((com.google.android.exoplayer2.extractor.d) this.b.o.valueAt(i)).b();
                }
            }
        });
        this.n.removeCallbacksAndMessages(null);
        this.G = true;
    }

    public final void a(com.google.android.exoplayer2.source.d.a callback) {
        this.p = callback;
        this.k.a();
        i();
    }

    public final k e() {
        return this.w;
    }

    public final long a(com.google.android.exoplayer2.c.f[] selections, boolean[] mayRetainStreamFlags, g[] streams, boolean[] streamResetFlags, long positionUs) {
        int track;
        com.google.android.exoplayer2.d.a.b(this.s);
        int i = 0;
        while (i < selections.length) {
            if (streams[i] != null && (selections[i] == null || !mayRetainStreamFlags[i])) {
                track = ((c) streams[i]).b;
                com.google.android.exoplayer2.d.a.b(this.y[track]);
                this.v--;
                this.y[track] = false;
                ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(track)).b();
                streams[i] = null;
            }
            i++;
        }
        boolean selectedNewTracks = false;
        i = 0;
        while (i < selections.length) {
            if (streams[i] == null && selections[i] != null) {
                com.google.android.exoplayer2.c.f selection = selections[i];
                com.google.android.exoplayer2.d.a.b(selection.b() == 1);
                com.google.android.exoplayer2.d.a.b(selection.b(0) == 0);
                track = this.w.a(selection.a());
                com.google.android.exoplayer2.d.a.b(!this.y[track]);
                this.v++;
                this.y[track] = true;
                streams[i] = new c(this, track);
                streamResetFlags[i] = true;
                selectedNewTracks = true;
            }
            i++;
        }
        if (!this.t) {
            int trackCount = this.o.size();
            for (i = 0; i < trackCount; i++) {
                if (!this.y[i]) {
                    ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).b();
                }
            }
        }
        if (this.v == 0) {
            this.u = false;
            if (this.i.a()) {
                this.i.b();
            }
        } else if (this.t ? !selectedNewTracks : positionUs == 0) {
            positionUs = b(positionUs);
            for (i = 0; i < streams.length; i++) {
                if (streams[i] != null) {
                    streamResetFlags[i] = true;
                }
            }
        }
        this.t = true;
        return positionUs;
    }

    public final boolean a(long playbackPositionUs) {
        if (this.F || (this.s && this.v == 0)) {
            return false;
        }
        boolean continuedLoading = this.k.a();
        if (this.i.a()) {
            return continuedLoading;
        }
        i();
        return true;
    }

    public final long a() {
        return this.v == 0 ? Long.MIN_VALUE : g();
    }

    public final long f() {
        if (!this.u) {
            return -9223372036854775807L;
        }
        this.u = false;
        return this.C;
    }

    public final long g() {
        if (this.F) {
            return Long.MIN_VALUE;
        }
        if (l()) {
            return this.D;
        }
        long largestQueuedTimestampUs;
        if (this.A) {
            largestQueuedTimestampUs = Long.MAX_VALUE;
            int trackCount = this.o.size();
            for (int i = 0; i < trackCount; i++) {
                if (this.z[i]) {
                    largestQueuedTimestampUs = Math.min(largestQueuedTimestampUs, ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).e());
                }
            }
        } else {
            largestQueuedTimestampUs = k();
        }
        return largestQueuedTimestampUs == Long.MIN_VALUE ? this.C : largestQueuedTimestampUs;
    }

    public final long b(long positionUs) {
        boolean seekInsideBuffer;
        if (!this.q.i_()) {
            positionUs = 0;
        }
        this.C = positionUs;
        int trackCount = this.o.size();
        if (l()) {
            seekInsideBuffer = false;
        } else {
            seekInsideBuffer = true;
        }
        int i = 0;
        while (seekInsideBuffer && i < trackCount) {
            if (this.y[i]) {
                seekInsideBuffer = ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).a(positionUs, false);
            }
            i++;
        }
        if (!seekInsideBuffer) {
            this.D = positionUs;
            this.F = false;
            if (this.i.a()) {
                this.i.b();
            } else {
                for (i = 0; i < trackCount; i++) {
                    ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).a(this.y[i]);
                }
            }
        }
        this.u = false;
        return positionUs;
    }

    final boolean b(int track) {
        return this.F || !(l() || ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(track)).c());
    }

    final void h() throws IOException {
        this.i.c();
    }

    final int a(int track, com.google.android.exoplayer2.h formatHolder, DecoderInputBuffer buffer, boolean formatRequired) {
        if (this.u || l()) {
            return -3;
        }
        return ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(track)).a(formatHolder, buffer, formatRequired, this.F, this.C);
    }

    final void a(int track, long positionUs) {
        com.google.android.exoplayer2.extractor.d sampleQueue = (com.google.android.exoplayer2.extractor.d) this.o.valueAt(track);
        if (!this.F || positionUs <= sampleQueue.e()) {
            sampleQueue.a(positionUs, true);
        } else {
            sampleQueue.f();
        }
    }

    public final n a(int id) {
        com.google.android.exoplayer2.extractor.d trackOutput = (com.google.android.exoplayer2.extractor.d) this.o.get(id);
        if (trackOutput != null) {
            return trackOutput;
        }
        trackOutput = new com.google.android.exoplayer2.extractor.d(this.g);
        trackOutput.a((com.google.android.exoplayer2.extractor.d.c) this);
        this.o.put(id, trackOutput);
        return trackOutput;
    }

    public final void b() {
        this.r = true;
        this.n.post(this.l);
    }

    public final void a(m seekMap) {
        this.q = seekMap;
        this.n.post(this.l);
    }

    public final void j_() {
        this.n.post(this.l);
    }

    private void a(a loadable) {
        if (this.B == -1) {
            this.B = loadable.j;
        }
    }

    private void i() {
        a loadable = new a(this, this.a, this.b, this.j, this.k);
        if (this.s) {
            com.google.android.exoplayer2.d.a.b(l());
            if (this.x == -9223372036854775807L || this.D < this.x) {
                loadable.a(this.q.a(this.D), this.D);
                this.D = -9223372036854775807L;
            } else {
                this.F = true;
                this.D = -9223372036854775807L;
                return;
            }
        }
        this.E = j();
        int minRetryCount = this.c;
        if (minRetryCount == -1) {
            minRetryCount = (this.s && this.B == -1 && (this.q == null || this.q.b() == -9223372036854775807L)) ? 6 : 3;
        }
        this.i.a(loadable, this, minRetryCount);
    }

    private int j() {
        int extractedSamplesCount = 0;
        for (int i = 0; i < this.o.size(); i++) {
            extractedSamplesCount += ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).a();
        }
        return extractedSamplesCount;
    }

    private long k() {
        long largestQueuedTimestampUs = Long.MIN_VALUE;
        int trackCount = this.o.size();
        for (int i = 0; i < trackCount; i++) {
            largestQueuedTimestampUs = Math.max(largestQueuedTimestampUs, ((com.google.android.exoplayer2.extractor.d) this.o.valueAt(i)).e());
        }
        return largestQueuedTimestampUs;
    }

    private boolean l() {
        return this.D != -9223372036854775807L;
    }

    public final void d() throws IOException {
        this.i.c();
    }
}
