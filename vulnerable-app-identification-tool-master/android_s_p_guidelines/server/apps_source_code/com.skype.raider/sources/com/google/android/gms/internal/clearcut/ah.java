package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ah<MessageType extends ah<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends b<MessageType, BuilderType> {
    private static Map<Object, ah<?, ?>> zzjr = new ConcurrentHashMap();
    protected cs zzjp = cs.a();
    private int zzjq = -1;

    public static abstract class a<MessageType extends ah<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends c<MessageType, BuilderType> {
        protected MessageType a;
        protected boolean b = false;
        private final MessageType c;

        protected a(MessageType messageType) {
            this.c = messageType;
            this.a = (ah) messageType.b(e.d);
        }

        private static void a(MessageType messageType, MessageType messageType2) {
            bv.a().a((Object) messageType).b(messageType, messageType2);
        }

        public final /* synthetic */ c a() {
            return (a) clone();
        }

        protected final /* synthetic */ c a(b bVar) {
            return a((ah) bVar);
        }

        public final boolean c() {
            return ah.a(this.a);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            a aVar = (a) this.c.b(e.e);
            aVar.a((ah) b());
            return aVar;
        }

        public final /* synthetic */ bk d() {
            boolean z;
            Object obj = (ah) b();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) obj.b(e.a)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                z = bv.a().a(obj).d(obj);
                if (booleanValue) {
                    obj.b(e.b);
                }
            }
            if (z) {
                return obj;
            }
            throw new cq();
        }

        public final /* synthetic */ bk f() {
            return this.c;
        }

        public final BuilderType a(MessageType messageType) {
            if (this.b) {
                ah ahVar = (ah) this.a.b(e.d);
                a(ahVar, this.a);
                this.a = ahVar;
                this.b = false;
            }
            a(this.a, messageType);
            return this;
        }

        public final /* synthetic */ bk b() {
            if (this.b) {
                return this.a;
            }
            Object obj = this.a;
            bv.a().a(obj).c(obj);
            this.b = true;
            return this.a;
        }
    }

    public static class b<T extends ah<T, ?>> extends d<T> {
        private T a;

        public b(T t) {
            this.a = t;
        }
    }

    public static abstract class c<MessageType extends c<MessageType, BuilderType>, BuilderType extends Object<MessageType, BuilderType>> extends ah<MessageType, BuilderType> implements bm {
        protected aa<d> zzjv = aa.a();
    }

    static final class d implements ac<d> {
        final int a;
        final de b;

        public final int a() {
            return this.a;
        }

        public final bl a(bl blVar, bk bkVar) {
            return ((a) blVar).a((ah) bkVar);
        }

        public final de b() {
            return this.b;
        }

        public final dj c() {
            return this.b.a();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return this.a - ((d) obj).a;
        }

        public final bq d() {
            throw new UnsupportedOperationException();
        }
    }

    public enum e {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 1;
        public static final int i = 2;
        public static final int j = 1;
        public static final int k = 2;
        private static final /* synthetic */ int[] l = new int[]{a, b, c, d, e, f, g};
        private static final /* synthetic */ int[] m = new int[]{h, i};
        private static final /* synthetic */ int[] n = new int[]{j, k};

        public static int[] a() {
            return (int[]) l.clone();
        }
    }

    static <T extends ah<?, ?>> T a(Class<T> cls) {
        T t = (ah) zzjr.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (ah) zzjr.get(cls);
            } catch (Throwable e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected static Object a(bk bkVar, String str, Object[] objArr) {
        return new bw(bkVar, str, objArr);
    }

    static Object a(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    protected static <T extends ah<?, ?>> void a(Class<T> cls, T t) {
        zzjr.put(cls, t);
    }

    protected static final <T extends ah<T, ?>> boolean a(T t) {
        byte byteValue = ((Byte) t.b(e.a)).byteValue();
        return byteValue == (byte) 1 ? true : byteValue == (byte) 0 ? false : bv.a().a((Object) t).d(t);
    }

    final void a(int i) {
        this.zzjq = i;
    }

    public final void a(s sVar) throws IOException {
        bv.a().a(getClass()).a((Object) this, t.a(sVar));
    }

    final int b() {
        return this.zzjq;
    }

    protected abstract Object b(int i);

    public final boolean c() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) b(e.a)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        boolean d = bv.a().a((Object) this).d(this);
        if (!booleanValue) {
            return d;
        }
        b(e.b);
        return d;
    }

    public final int d() {
        if (this.zzjq == -1) {
            this.zzjq = bv.a().a((Object) this).b(this);
        }
        return this.zzjq;
    }

    public final /* synthetic */ bl e() {
        a aVar = (a) b(e.e);
        aVar.a(this);
        return aVar;
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !((ah) b(e.f)).getClass().isInstance(obj) ? false : bv.a().a((Object) this).a((Object) this, (ah) obj);
    }

    public final /* synthetic */ bk f() {
        return (ah) b(e.f);
    }

    public int hashCode() {
        if (this.zzex != 0) {
            return this.zzex;
        }
        this.zzex = bv.a().a((Object) this).a(this);
        return this.zzex;
    }

    public String toString() {
        return bn.a(this, super.toString());
    }
}
