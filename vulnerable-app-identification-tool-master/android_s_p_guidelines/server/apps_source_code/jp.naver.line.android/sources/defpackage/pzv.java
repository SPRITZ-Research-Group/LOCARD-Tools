package defpackage;

import java.util.concurrent.Callable;

/* renamed from: pzv */
public final class pzv {
    static volatile paj<? super Throwable> a;
    static volatile pak<? super Runnable, ? extends Runnable> b;
    static volatile pak<? super Callable<oyu>, ? extends oyu> c;
    static volatile pak<? super Callable<oyu>, ? extends oyu> d;
    static volatile pak<? super Callable<oyu>, ? extends oyu> e;
    static volatile pak<? super Callable<oyu>, ? extends oyu> f;
    static volatile pak<? super oyu, ? extends oyu> g;
    static volatile pak<? super oyu, ? extends oyu> h;
    static volatile pak<? super oyu, ? extends oyu> i;
    static volatile pak<? super oyu, ? extends oyu> j;
    static volatile pak<? super oyc, ? extends oyc> k;
    static volatile pak<? super pac, ? extends pac> l;
    static volatile pak<? super oyn, ? extends oyn> m;
    static volatile pak<? super pzp, ? extends pzp> n;
    static volatile pak<? super oyg, ? extends oyg> o;
    static volatile pak<? super oyz, ? extends oyz> p;
    static volatile pak<? super oxw, ? extends oxw> q;
    static volatile paf<? super oyc, ? super adnk, ? extends adnk> r;
    static volatile paf<? super oyg, ? super oyk, ? extends oyk> s;
    static volatile paf<? super oyn, ? super oyt, ? extends oyt> t;
    static volatile paf<? super oyz, ? super ozc, ? extends ozc> u;
    static volatile paf<? super oxw, ? super oxy, ? extends oxy> v;
    static volatile pah w;
    static volatile boolean x;
    static volatile boolean y;

    public static boolean a() {
        return y;
    }

    public static oyu a(Callable<oyu> callable) {
        pbv.a((Object) callable, "Scheduler Callable can't be null");
        pak pak = c;
        if (pak == null) {
            return pzv.e(callable);
        }
        return pzv.a(pak, (Callable) callable);
    }

    public static oyu b(Callable<oyu> callable) {
        pbv.a((Object) callable, "Scheduler Callable can't be null");
        pak pak = e;
        if (pak == null) {
            return pzv.e(callable);
        }
        return pzv.a(pak, (Callable) callable);
    }

    public static oyu c(Callable<oyu> callable) {
        pbv.a((Object) callable, "Scheduler Callable can't be null");
        pak pak = f;
        if (pak == null) {
            return pzv.e(callable);
        }
        return pzv.a(pak, (Callable) callable);
    }

    public static oyu d(Callable<oyu> callable) {
        pbv.a((Object) callable, "Scheduler Callable can't be null");
        pak pak = d;
        if (pak == null) {
            return pzv.e(callable);
        }
        return pzv.a(pak, (Callable) callable);
    }

    public static oyu a(oyu oyu) {
        pak pak = g;
        if (pak == null) {
            return oyu;
        }
        return (oyu) pzv.a(pak, (Object) oyu);
    }

    public static void a(Throwable th) {
        paj paj = a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else {
            Object obj = 1;
            if (!((th instanceof ozz) || (th instanceof ozy) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof ozs))) {
                obj = null;
            }
            if (obj == null) {
                th = new pab(th);
            }
        }
        if (paj != null) {
            try {
                paj.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                pzv.b(th2);
            }
        }
        th.printStackTrace();
        pzv.b(th);
    }

    private static void b(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static oyu b(oyu oyu) {
        pak pak = i;
        if (pak == null) {
            return oyu;
        }
        return (oyu) pzv.a(pak, (Object) oyu);
    }

    public static oyu c(oyu oyu) {
        pak pak = j;
        if (pak == null) {
            return oyu;
        }
        return (oyu) pzv.a(pak, (Object) oyu);
    }

    public static Runnable a(Runnable runnable) {
        pbv.a((Object) runnable, "run is null");
        pak pak = b;
        if (pak == null) {
            return runnable;
        }
        return (Runnable) pzv.a(pak, (Object) runnable);
    }

    public static oyu d(oyu oyu) {
        pak pak = h;
        if (pak == null) {
            return oyu;
        }
        return (oyu) pzv.a(pak, (Object) oyu);
    }

    public static void a(paj<? super Throwable> paj) {
        if (x) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        a = paj;
    }

    public static <T> adnk<? super T> a(oyc<T> oyc, adnk<? super T> adnk) {
        paf paf = r;
        return paf != null ? (adnk) pzv.a(paf, oyc, adnk) : adnk;
    }

    public static <T> oyt<? super T> a(oyn<T> oyn, oyt<? super T> oyt) {
        paf paf = t;
        return paf != null ? (oyt) pzv.a(paf, oyn, oyt) : oyt;
    }

    public static <T> ozc<? super T> a(oyz<T> oyz, ozc<? super T> ozc) {
        paf paf = u;
        return paf != null ? (ozc) pzv.a(paf, oyz, ozc) : ozc;
    }

    public static oxy a(oxw oxw, oxy oxy) {
        paf paf = v;
        return paf != null ? (oxy) pzv.a(paf, oxw, oxy) : oxy;
    }

    public static <T> oyk<? super T> a(oyg<T> oyg, oyk<? super T> oyk) {
        paf paf = s;
        return paf != null ? (oyk) pzv.a(paf, oyg, oyk) : oyk;
    }

    public static <T> oyg<T> a(oyg<T> oyg) {
        pak pak = o;
        return pak != null ? (oyg) pzv.a(pak, (Object) oyg) : oyg;
    }

    public static <T> oyc<T> a(oyc<T> oyc) {
        pak pak = k;
        return pak != null ? (oyc) pzv.a(pak, (Object) oyc) : oyc;
    }

    public static <T> pac<T> a(pac<T> pac) {
        pak pak = l;
        return pak != null ? (pac) pzv.a(pak, (Object) pac) : pac;
    }

    public static <T> oyn<T> a(oyn<T> oyn) {
        pak pak = m;
        return pak != null ? (oyn) pzv.a(pak, (Object) oyn) : oyn;
    }

    public static <T> pzp<T> a(pzp<T> pzp) {
        pak pak = n;
        return pak != null ? (pzp) pzv.a(pak, (Object) pzp) : pzp;
    }

    public static <T> oyz<T> a(oyz<T> oyz) {
        pak pak = p;
        return pak != null ? (oyz) pzv.a(pak, (Object) oyz) : oyz;
    }

    public static oxw a(oxw oxw) {
        pak pak = q;
        return pak != null ? (oxw) pzv.a(pak, (Object) oxw) : oxw;
    }

    public static boolean b() {
        pah pah = w;
        if (pah == null) {
            return false;
        }
        try {
            return pah.a();
        } catch (Throwable th) {
            RuntimeException a = pzf.a(th);
        }
    }

    private static <T, R> R a(pak<T, R> pak, T t) {
        try {
            return pak.apply(t);
        } catch (Throwable th) {
            RuntimeException a = pzf.a(th);
        }
    }

    private static <T, U, R> R a(paf<T, U, R> paf, T t, U u) {
        try {
            return paf.apply(t, u);
        } catch (Throwable th) {
            RuntimeException a = pzf.a(th);
        }
    }

    private static oyu e(Callable<oyu> callable) {
        try {
            return (oyu) pbv.a(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            RuntimeException a = pzf.a(th);
        }
    }

    private static oyu a(pak<? super Callable<oyu>, ? extends oyu> pak, Callable<oyu> callable) {
        return (oyu) pbv.a(pzv.a((pak) pak, (Object) callable), "Scheduler Callable result can't be null");
    }
}
