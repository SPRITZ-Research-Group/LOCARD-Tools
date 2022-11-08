package com.google.android.gms.internal.clearcut;

import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class cx {
    private static final Logger a = Logger.getLogger(cx.class.getName());
    private static final Unsafe b = c();
    private static final Class<?> c = f.b();
    private static final boolean d = c(Long.TYPE);
    private static final boolean e = c(Integer.TYPE);
    private static final d f;
    private static final boolean g = f();
    private static final boolean h = e();
    private static final long i = ((long) a(byte[].class));
    private static final long j = ((long) a(boolean[].class));
    private static final long k = ((long) b(boolean[].class));
    private static final long l = ((long) a(int[].class));
    private static final long m = ((long) b(int[].class));
    private static final long n = ((long) a(long[].class));
    private static final long o = ((long) b(long[].class));
    private static final long p = ((long) a(float[].class));
    private static final long q = ((long) b(float[].class));
    private static final long r = ((long) a(double[].class));
    private static final long s = ((long) b(double[].class));
    private static final long t = ((long) a(Object[].class));
    private static final long u = ((long) b(Object[].class));
    private static final long v = b(g());
    private static final long w;
    private static final boolean x = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static abstract class d {
        Unsafe a;

        d(Unsafe unsafe) {
            this.a = unsafe;
        }

        public abstract byte a(Object obj, long j);

        public final long a(Field field) {
            return this.a.objectFieldOffset(field);
        }

        public abstract void a(Object obj, long j, byte b);

        public abstract void a(Object obj, long j, double d);

        public abstract void a(Object obj, long j, float f);

        public final void a(Object obj, long j, int i) {
            this.a.putInt(obj, j, i);
        }

        public final void a(Object obj, long j, long j2) {
            this.a.putLong(obj, j, j2);
        }

        public abstract void a(Object obj, long j, boolean z);

        public abstract boolean b(Object obj, long j);

        public abstract float c(Object obj, long j);

        public abstract double d(Object obj, long j);

        public final int e(Object obj, long j) {
            return this.a.getInt(obj, j);
        }

        public final long f(Object obj, long j) {
            return this.a.getLong(obj, j);
        }
    }

    static final class a extends d {
        a(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte a(Object obj, long j) {
            return cx.x ? cx.k(obj, j) : cx.l(obj, j);
        }

        public final void a(Object obj, long j, byte b) {
            if (cx.x) {
                cx.c(obj, j, b);
            } else {
                cx.d(obj, j, b);
            }
        }

        public final void a(Object obj, long j, double d) {
            a(obj, j, Double.doubleToLongBits(d));
        }

        public final void a(Object obj, long j, float f) {
            a(obj, j, Float.floatToIntBits(f));
        }

        public final void a(Object obj, long j, boolean z) {
            if (cx.x) {
                cx.b(obj, j, z);
            } else {
                cx.c(obj, j, z);
            }
        }

        public final boolean b(Object obj, long j) {
            return cx.x ? cx.i(obj, j) : cx.j(obj, j);
        }

        public final float c(Object obj, long j) {
            return Float.intBitsToFloat(e(obj, j));
        }

        public final double d(Object obj, long j) {
            return Double.longBitsToDouble(f(obj, j));
        }
    }

    static final class b extends d {
        b(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte a(Object obj, long j) {
            return cx.x ? cx.k(obj, j) : cx.l(obj, j);
        }

        public final void a(Object obj, long j, byte b) {
            if (cx.x) {
                cx.c(obj, j, b);
            } else {
                cx.d(obj, j, b);
            }
        }

        public final void a(Object obj, long j, double d) {
            a(obj, j, Double.doubleToLongBits(d));
        }

        public final void a(Object obj, long j, float f) {
            a(obj, j, Float.floatToIntBits(f));
        }

        public final void a(Object obj, long j, boolean z) {
            if (cx.x) {
                cx.b(obj, j, z);
            } else {
                cx.c(obj, j, z);
            }
        }

        public final boolean b(Object obj, long j) {
            return cx.x ? cx.i(obj, j) : cx.j(obj, j);
        }

        public final float c(Object obj, long j) {
            return Float.intBitsToFloat(e(obj, j));
        }

        public final double d(Object obj, long j) {
            return Double.longBitsToDouble(f(obj, j));
        }
    }

    static final class c extends d {
        c(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte a(Object obj, long j) {
            return this.a.getByte(obj, j);
        }

        public final void a(Object obj, long j, byte b) {
            this.a.putByte(obj, j, b);
        }

        public final void a(Object obj, long j, double d) {
            this.a.putDouble(obj, j, d);
        }

        public final void a(Object obj, long j, float f) {
            this.a.putFloat(obj, j, f);
        }

        public final void a(Object obj, long j, boolean z) {
            this.a.putBoolean(obj, j, z);
        }

        public final boolean b(Object obj, long j) {
            return this.a.getBoolean(obj, j);
        }

        public final float c(Object obj, long j) {
            return this.a.getFloat(obj, j);
        }

        public final double d(Object obj, long j) {
            return this.a.getDouble(obj, j);
        }
    }

    static {
        Field field = null;
        d bVar = b == null ? null : f.a() ? d ? new b(b) : e ? new a(b) : null : new c(b);
        f = bVar;
        Field a = a(String.class, PropertiesEntry.COLUMN_NAME_VALUE);
        if (a != null && a.getType() == char[].class) {
            field = a;
        }
        w = b(field);
    }

    private cx() {
    }

    static byte a(byte[] bArr, long j) {
        return f.a(bArr, i + j);
    }

    private static int a(Class<?> cls) {
        return h ? f.a.arrayBaseOffset(cls) : -1;
    }

    static int a(Object obj, long j) {
        return f.e(obj, j);
    }

    static long a(Field field) {
        return f.a(field);
    }

    private static Field a(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    static void a(Object obj, long j, double d) {
        f.a(obj, j, d);
    }

    static void a(Object obj, long j, float f) {
        f.a(obj, j, f);
    }

    static void a(Object obj, long j, int i) {
        f.a(obj, j, i);
    }

    static void a(Object obj, long j, long j2) {
        f.a(obj, j, j2);
    }

    static void a(Object obj, long j, Object obj2) {
        f.a.putObject(obj, j, obj2);
    }

    static void a(Object obj, long j, boolean z) {
        f.a(obj, j, z);
    }

    static void a(byte[] bArr, long j, byte b) {
        f.a((Object) bArr, i + j, b);
    }

    static boolean a() {
        return h;
    }

    private static int b(Class<?> cls) {
        return h ? f.a.arrayIndexScale(cls) : -1;
    }

    static long b(Object obj, long j) {
        return f.f(obj, j);
    }

    private static long b(Field field) {
        return (field == null || f == null) ? -1 : f.a(field);
    }

    static boolean b() {
        return g;
    }

    static Unsafe c() {
        try {
            return (Unsafe) AccessController.doPrivileged(new cy());
        } catch (Throwable th) {
            return null;
        }
    }

    private static void c(Object obj, long j, byte b) {
        int i = ((((int) j) ^ -1) & 3) << 3;
        a(obj, j & -4, (a(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    private static boolean c(Class<?> cls) {
        if (!f.a()) {
            return false;
        }
        try {
            Class cls2 = c;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static boolean c(Object obj, long j) {
        return f.b(obj, j);
    }

    static float d(Object obj, long j) {
        return f.c(obj, j);
    }

    private static void d(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        a(obj, j & -4, (a(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    static double e(Object obj, long j) {
        return f.d(obj, j);
    }

    private static boolean e() {
        if (b == null) {
            return false;
        }
        try {
            Class cls = b.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (f.a()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            a.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    static Object f(Object obj, long j) {
        return f.a.getObject(obj, j);
    }

    private static boolean f() {
        if (b == null) {
            return false;
        }
        try {
            Class cls = b.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (g() == null) {
                return false;
            }
            if (f.a()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            a.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static Field g() {
        Field a;
        if (f.a()) {
            a = a(Buffer.class, "effectiveDirectAddress");
            if (a != null) {
                return a;
            }
        }
        a = a(Buffer.class, "address");
        return (a == null || a.getType() != Long.TYPE) ? null : a;
    }

    private static byte k(Object obj, long j) {
        return (byte) (a(obj, -4 & j) >>> ((int) (((-1 ^ j) & 3) << 3)));
    }

    private static byte l(Object obj, long j) {
        return (byte) (a(obj, -4 & j) >>> ((int) ((3 & j) << 3)));
    }

    static /* synthetic */ boolean i(Object obj, long j) {
        return k(obj, j) != (byte) 0;
    }

    static /* synthetic */ boolean j(Object obj, long j) {
        return l(obj, j) != (byte) 0;
    }

    static /* synthetic */ void b(Object obj, long j, boolean z) {
        c(obj, j, (byte) (z ? 1 : 0));
    }

    static /* synthetic */ void c(Object obj, long j, boolean z) {
        d(obj, j, (byte) (z ? 1 : 0));
    }
}
