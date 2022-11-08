package androidx.versionedparcelable;

import android.os.Parcelable;

public abstract class a {
    protected abstract void a();

    protected abstract void a(int i);

    protected abstract void a(Parcelable parcelable);

    protected abstract void a(String str);

    protected abstract void a(byte[] bArr);

    protected abstract a b();

    protected abstract boolean b(int i);

    protected abstract int c();

    protected abstract void c(int i);

    protected abstract String d();

    protected abstract byte[] e();

    protected abstract <T extends Parcelable> T f();

    public final void b(byte[] bArr) {
        c(2);
        a(bArr);
    }

    public final void a(int i, int i2) {
        c(i2);
        a(i);
    }

    public final void b(String str) {
        c(7);
        a(str);
    }

    public final void a(Parcelable parcelable, int i) {
        c(i);
        a(parcelable);
    }

    public final int b(int i, int i2) {
        if (b(i2)) {
            return c();
        }
        return i;
    }

    public final String c(String str) {
        if (b(7)) {
            return d();
        }
        return str;
    }

    public final byte[] c(byte[] bArr) {
        if (b(2)) {
            return e();
        }
        return bArr;
    }

    public final <T extends Parcelable> T b(T t, int i) {
        if (b(i)) {
            return f();
        }
        return t;
    }

    public final void a(c cVar) {
        c(1);
        b(cVar);
    }

    protected final void b(c cVar) {
        if (cVar == null) {
            a(null);
            return;
        }
        d(cVar);
        a b = b();
        a(cVar, b);
        b.a();
    }

    private void d(c cVar) {
        try {
            a(a(cVar.getClass()).getName());
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(cVar.getClass().getSimpleName());
            stringBuilder.append(" does not have a Parcelizer");
            throw new RuntimeException(stringBuilder.toString(), e);
        }
    }

    public final <T extends c> T c(T t) {
        if (b(1)) {
            return g();
        }
        return t;
    }

    protected final <T extends c> T g() {
        String d = d();
        if (d == null) {
            return null;
        }
        return a(d, b());
    }

    private static <T extends c> T a(String str, a aVar) {
        try {
            return (c) Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", new Class[]{a.class}).invoke(null, new Object[]{aVar});
        } catch (Throwable e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (Throwable e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (Throwable e22) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e22);
        } catch (Throwable e222) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e222);
        }
    }

    private static Class a(Class<? extends c> cls) throws ClassNotFoundException {
        return Class.forName(String.format("%s.%sParcelizer", new Object[]{cls.getPackage().getName(), cls.getSimpleName()}), false, cls.getClassLoader());
    }

    private static <T extends c> void a(T t, a aVar) {
        try {
            a(t.getClass()).getDeclaredMethod("write", new Class[]{t.getClass(), a.class}).invoke(null, new Object[]{t, aVar});
        } catch (Throwable e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (Throwable e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (Throwable e22) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e22);
        } catch (Throwable e222) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e222);
        }
    }
}
