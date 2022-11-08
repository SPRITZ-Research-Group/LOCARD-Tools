package defpackage;

import java.util.concurrent.Callable;

/* renamed from: ozg */
public final class ozg {
    private static volatile pak<Callable<oyu>, oyu> a;
    private static volatile pak<oyu, oyu> b;

    public static oyu a(Callable<oyu> callable) {
        pak pak = a;
        if (pak == null) {
            return ozg.b(callable);
        }
        oyu oyu = (oyu) ozg.a(pak, callable);
        if (oyu != null) {
            return oyu;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    public static oyu a(oyu oyu) {
        if (oyu != null) {
            pak pak = b;
            if (pak == null) {
                return oyu;
            }
            return (oyu) ozg.a(pak, oyu);
        }
        throw new NullPointerException("scheduler == null");
    }

    private static oyu b(Callable<oyu> callable) {
        try {
            oyu oyu = (oyu) callable.call();
            if (oyu != null) {
                return oyu;
            }
            throw new NullPointerException("Scheduler Callable returned null");
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
}
