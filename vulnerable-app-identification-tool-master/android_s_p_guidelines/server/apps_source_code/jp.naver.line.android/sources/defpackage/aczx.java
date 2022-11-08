package defpackage;

/* renamed from: aczx */
final class aczx<V> {
    private static final aczx<Object> a = new aczx(aczw.a);
    private final aczw<V> b;

    public static <V> aczx<V> a() {
        return a;
    }

    private aczx(aczw<V> aczw) {
        this.b = aczw;
    }

    public final V a(int i) {
        return this.b.a((long) i);
    }

    public final aczx<V> a(int i, V v) {
        aczw a = this.b.a((long) i, (Object) v);
        if (a == this.b) {
            return this;
        }
        return new aczx(a);
    }
}
