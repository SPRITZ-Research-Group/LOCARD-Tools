package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.List;

public final class c {
    private static final a a;
    private final Object b;

    interface a {
        Object a(c cVar);
    }

    static class d implements a {
        d() {
        }

        public Object a(c compat) {
            return null;
        }
    }

    @RequiresApi(16)
    private static class b extends d {
        b() {
        }

        public final Object a(final c compat) {
            return new android.support.v4.view.accessibility.d.AnonymousClass1(new a(this) {
                final /* synthetic */ b b;

                public final boolean a() {
                    return c.c();
                }

                public final List<Object> b() {
                    c.d();
                    return null;
                }

                public final Object c() {
                    c.b();
                    return null;
                }
            });
        }
    }

    @RequiresApi(19)
    private static class c extends d {
        c() {
        }

        public final Object a(final c compat) {
            return new android.support.v4.view.accessibility.e.AnonymousClass1(new a(this) {
                final /* synthetic */ c b;

                public final boolean a() {
                    return c.c();
                }

                public final List<Object> b() {
                    c.d();
                    return null;
                }

                public final Object c() {
                    c.b();
                    return null;
                }

                public final Object d() {
                    c.e();
                    return null;
                }
            });
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new c();
        } else if (VERSION.SDK_INT >= 16) {
            a = new b();
        } else {
            a = new d();
        }
    }

    public c() {
        this.b = a.a(this);
    }

    public c(Object provider) {
        this.b = provider;
    }

    public final Object a() {
        return this.b;
    }

    @Nullable
    public static b b() {
        return null;
    }

    public static boolean c() {
        return false;
    }

    @Nullable
    public static List<b> d() {
        return null;
    }

    @Nullable
    public static b e() {
        return null;
    }
}
