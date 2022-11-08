package defpackage;

/* renamed from: acxg */
public abstract class acxg<T> {
    private static final Object a = new Object() {
    };

    public abstract T a();

    protected static Object a(T t) {
        return t == null ? a : t;
    }

    protected static T b(Object obj) {
        return obj == a ? null : obj;
    }
}
