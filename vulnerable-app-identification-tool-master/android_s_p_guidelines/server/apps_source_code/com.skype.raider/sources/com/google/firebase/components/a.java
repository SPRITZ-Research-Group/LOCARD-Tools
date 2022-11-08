package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@KeepForSdk
public final class a<T> {
    private final Set<Class<? super T>> a;
    private final Set<e> b;
    private final int c;
    private final c<T> d;

    @KeepForSdk
    public static class a<T> {
        private final Set<Class<? super T>> a;
        private final Set<e> b;
        private int c;
        private c<T> d;

        private a(Class<T> cls, Class<? super T>... clsArr) {
            int i = 0;
            this.a = new HashSet();
            this.b = new HashSet();
            this.c = 0;
            q.a((Object) cls, "Null interface");
            this.a.add(cls);
            int length = clsArr.length;
            while (i < length) {
                q.a(clsArr[i], "Null interface");
                i++;
            }
            Collections.addAll(this.a, clsArr);
        }

        /* synthetic */ a(Class cls, Class[] clsArr, byte b) {
            this(cls, clsArr);
        }

        @KeepForSdk
        public final a<T> a() {
            q.a(this.c == 0, "Instantiation type has already been set.");
            this.c = 1;
            return this;
        }

        @KeepForSdk
        public final a<T> a(c<T> cVar) {
            this.d = (c) q.a((Object) cVar, "Null factory");
            return this;
        }

        @KeepForSdk
        public final a<T> a(e eVar) {
            q.a((Object) eVar, "Null dependency");
            String str = "Components are not allowed to depend on interfaces they themselves provide.";
            if ((!this.a.contains(eVar.a()) ? 1 : null) == null) {
                throw new IllegalArgumentException(str);
            }
            this.b.add(eVar);
            return this;
        }

        @KeepForSdk
        public final a<T> b() {
            q.a(this.d != null, "Missing required property: factory.");
            return new a(new HashSet(this.a), new HashSet(this.b), this.c, this.d, (byte) 0);
        }
    }

    private a(Set<Class<? super T>> set, Set<e> set2, int i, c<T> cVar) {
        this.a = Collections.unmodifiableSet(set);
        this.b = Collections.unmodifiableSet(set2);
        this.c = i;
        this.d = cVar;
    }

    /* synthetic */ a(Set set, Set set2, int i, c cVar, byte b) {
        this(set, set2, i, cVar);
    }

    @KeepForSdk
    public static <T> a<T> a(Class<T> cls) {
        return new a(cls, new Class[0], (byte) 0);
    }

    @KeepForSdk
    public static <T> a<T> a(Class<T> cls, T t) {
        return a(cls).a(new i(t)).b();
    }

    public final Set<Class<? super T>> a() {
        return this.a;
    }

    public final Set<e> b() {
        return this.b;
    }

    public final c<T> c() {
        return this.d;
    }

    public final boolean d() {
        return this.c == 1;
    }

    public final boolean e() {
        return this.c == 2;
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.a.toArray()) + ">{" + this.c + ", deps=" + Arrays.toString(this.b.toArray()) + "}";
    }
}
