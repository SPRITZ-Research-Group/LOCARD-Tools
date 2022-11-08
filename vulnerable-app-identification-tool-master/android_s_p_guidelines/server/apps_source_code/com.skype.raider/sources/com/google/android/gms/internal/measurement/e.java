package com.google.android.gms.internal.measurement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class e<M extends d<M>, T> {
    protected final Class<T> a;
    public final int b;
    protected final boolean c;
    private final int d;

    private final Object a(a aVar) {
        String valueOf;
        Class componentType = this.c ? this.a.getComponentType() : this.a;
        try {
            j jVar;
            switch (this.d) {
                case 10:
                    jVar = (j) componentType.newInstance();
                    aVar.a(jVar, this.b >>> 3);
                    return jVar;
                case 11:
                    jVar = (j) componentType.newInstance();
                    aVar.a(jVar);
                    return jVar;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.d);
            }
        } catch (Throwable e) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException("Error reading extension field", e22);
        }
    }

    protected final int a(Object obj) {
        int i = this.b >>> 3;
        switch (this.d) {
            case 10:
                return (b.c(i << 3) << 1) + ((j) obj).d();
            case 11:
                return b.b(i, (j) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.d);
        }
    }

    final T a(List<l> list) {
        int i = 0;
        if (list == null) {
            return null;
        }
        if (this.c) {
            int i2;
            List arrayList = new ArrayList();
            for (i2 = 0; i2 < list.size(); i2++) {
                l lVar = (l) list.get(i2);
                if (lVar.b.length != 0) {
                    arrayList.add(a(a.a(lVar.b)));
                }
            }
            i2 = arrayList.size();
            if (i2 == 0) {
                return null;
            }
            T cast = this.a.cast(Array.newInstance(this.a.getComponentType(), i2));
            while (i < i2) {
                Array.set(cast, i, arrayList.get(i));
                i++;
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.a.cast(a(a.a(((l) list.get(list.size() - 1)).b)));
        }
    }

    protected final void a(Object obj, b bVar) {
        try {
            bVar.b(this.b);
            switch (this.d) {
                case 10:
                    int i = this.b >>> 3;
                    ((j) obj).a(bVar);
                    bVar.c(i, 4);
                    return;
                case 11:
                    bVar.a((j) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.d);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.d == eVar.d && this.a == eVar.a && this.b == eVar.b && this.c == eVar.c;
    }

    public final int hashCode() {
        return (this.c ? 1 : 0) + ((((((this.d + 1147) * 31) + this.a.hashCode()) * 31) + this.b) * 31);
    }
}
