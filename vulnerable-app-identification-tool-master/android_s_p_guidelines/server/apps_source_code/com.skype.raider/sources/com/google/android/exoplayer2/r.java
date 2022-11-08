package com.google.android.exoplayer2;

public abstract class r {
    public static final r a = new r() {
        public final int b() {
            return 0;
        }

        public final b a(int windowIndex, b window, long defaultPositionProjectionUs) {
            throw new IndexOutOfBoundsException();
        }

        public final int c() {
            return 0;
        }

        public final a a(int periodIndex, a period, boolean setIds) {
            throw new IndexOutOfBoundsException();
        }

        public final int a(Object uid) {
            return -1;
        }
    };

    public static final class a {
        public Object a;
        public Object b;
        public int c;
        public long d;
        public boolean e;
        private long f;

        public final a a(Object id, Object uid, long durationUs, long positionInWindowUs) {
            this.a = id;
            this.b = uid;
            this.c = 0;
            this.d = durationUs;
            this.f = positionInWindowUs;
            this.e = false;
            return this;
        }

        public final long a() {
            return C.a(this.f);
        }
    }

    public static final class b {
        public Object a;
        public long b;
        public long c;
        public boolean d;
        public boolean e;
        public int f;
        public int g;
        public long h;
        public long i;
        public long j;
    }

    public abstract int a(Object obj);

    public abstract a a(int i, a aVar, boolean z);

    public abstract b a(int i, b bVar, long j);

    public abstract int b();

    public abstract int c();

    public final boolean a() {
        return b() == 0;
    }
}
