package android.support.design.widget;

import android.view.animation.Interpolator;

final class s {
    private final e a;

    interface c {
        void a(s sVar);
    }

    interface a {
        void a();

        void b();
    }

    static class b implements a {
        b() {
        }

        public void a() {
        }

        public void b() {
        }
    }

    interface d {
        s a();
    }

    static abstract class e {

        interface b {
            void a();
        }

        interface a {
            void a();

            void b();
        }

        abstract void a();

        abstract void a(float f, float f2);

        abstract void a(int i);

        abstract void a(int i, int i2);

        abstract void a(a aVar);

        abstract void a(b bVar);

        abstract void a(Interpolator interpolator);

        abstract boolean b();

        abstract int c();

        abstract float d();

        abstract void e();

        abstract float f();

        abstract long g();

        e() {
        }
    }

    s(e impl) {
        this.a = impl;
    }

    public final void a() {
        this.a.a();
    }

    public final boolean b() {
        return this.a.b();
    }

    public final void a(Interpolator interpolator) {
        this.a.a(interpolator);
    }

    public final void a(final c updateListener) {
        this.a.a(new b(this) {
            final /* synthetic */ s b;

            public final void a() {
                updateListener.a(this.b);
            }
        });
    }

    public final void a(final a listener) {
        this.a.a(new a(this) {
            final /* synthetic */ s b;

            public final void a() {
                listener.a();
            }

            public final void b() {
                listener.b();
            }
        });
    }

    public final void a(int from, int to) {
        this.a.a(from, to);
    }

    public final int c() {
        return this.a.c();
    }

    public final void a(float from, float to) {
        this.a.a(from, to);
    }

    public final float d() {
        return this.a.d();
    }

    public final void a(int duration) {
        this.a.a(duration);
    }

    public final void e() {
        this.a.e();
    }

    public final float f() {
        return this.a.f();
    }

    public final long g() {
        return this.a.g();
    }
}
