package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.e;

public interface d {

    public interface a {
        void a(ExoPlaybackException exoPlaybackException);

        void a(boolean z, int i);
    }

    public interface b {
        void a(int i, Object obj) throws ExoPlaybackException;
    }

    public static final class c {
        public final b a;
        public final int b;
        public final Object c;

        public c(b target, int messageType, Object message) {
            this.a = target;
            this.b = messageType;
            this.c = message;
        }
    }

    void a(long j);

    void a(a aVar);

    void a(e eVar);

    void a(boolean z);

    void a(c... cVarArr);

    boolean a();

    void b();

    void b(c... cVarArr);

    void c();

    void d();

    long e();

    long f();

    int g();
}
