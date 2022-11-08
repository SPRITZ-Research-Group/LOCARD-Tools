package defpackage;

/* renamed from: aczv */
public final class aczv<K, V> {
    private static final aczv<Object, Object> a = new aczv(aczx.a(), 0);
    private final aczx<aczt<aczy<K, V>>> b;
    private final int c;

    public static <K, V> aczv<K, V> a() {
        return a;
    }

    private aczv(aczx<aczt<aczy<K, V>>> aczx, int i) {
        this.b = aczx;
        this.c = i;
    }

    public final V a(Object obj) {
        aczt a = a(obj.hashCode());
        while (a != null && a.b() > 0) {
            aczy aczy = (aczy) a.a;
            if (aczy.a.equals(obj)) {
                return aczy.b;
            }
            a = a.b;
        }
        return null;
    }

    public final aczv<K, V> a(K k, V v) {
        aczt a = a(k.hashCode());
        int b = a.b();
        aczt aczt = a;
        int i = 0;
        while (aczt != null && aczt.b() > 0) {
            if (((aczy) aczt.a).a.equals(k)) {
                break;
            }
            aczt = aczt.b;
            i++;
        }
        i = -1;
        if (i != -1) {
            a = a.a(i);
        }
        aczt a2 = a.a(new aczy(k, v));
        return new aczv(this.b.a(k.hashCode(), a2), (this.c - b) + a2.b());
    }

    private aczt<aczy<K, V>> a(int i) {
        aczt<aczy<K, V>> aczt = (aczt) this.b.a(i);
        return aczt == null ? aczt.a() : aczt;
    }
}
