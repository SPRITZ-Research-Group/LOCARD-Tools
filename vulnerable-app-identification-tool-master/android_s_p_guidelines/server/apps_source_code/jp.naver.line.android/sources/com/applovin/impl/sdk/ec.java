package com.applovin.impl.sdk;

public class ec<T> implements Comparable {
    private static int a;
    private final int b;
    private final String c;
    private final T d;

    private ec(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (t != null) {
            this.c = str;
            this.d = t;
            this.b = a;
            a++;
        } else {
            throw new IllegalArgumentException("No default value specified");
        }
    }

    /* synthetic */ ec(String str, Object obj, eb ebVar) {
        this(str, obj);
    }

    public int a() {
        return this.b;
    }

    T a(Object obj) {
        return this.d.getClass().cast(obj);
    }

    public String b() {
        return this.c;
    }

    public T c() {
        return this.d;
    }

    public int compareTo(Object obj) {
        return (obj == null || !(obj instanceof ec)) ? 0 : this.c.compareTo(((ec) obj).b());
    }
}
