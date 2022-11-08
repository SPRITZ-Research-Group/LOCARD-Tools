package defpackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: pzf */
public final class pzf {
    public static final Throwable a = new pzg();

    public static RuntimeException a(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        } else {
            return new RuntimeException(th);
        }
    }

    public static <T> boolean a(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        Object obj;
        do {
            th2 = (Throwable) atomicReference.get();
            if (th2 == a) {
                return false;
            }
            if (th2 == null) {
                obj = th;
            } else {
                obj = new ozs(th2, th);
            }
        } while (!atomicReference.compareAndSet(th2, obj));
        return true;
    }

    public static <T> Throwable a(AtomicReference<Throwable> atomicReference) {
        Throwable th = (Throwable) atomicReference.get();
        return th != a ? (Throwable) atomicReference.getAndSet(a) : th;
    }

    public static <E extends Throwable> Exception b(Throwable th) throws Throwable {
        if (th instanceof Exception) {
            return (Exception) th;
        }
        throw th;
    }

    public static String a(long j, TimeUnit timeUnit) {
        StringBuilder stringBuilder = new StringBuilder("The source did not signal an event for ");
        stringBuilder.append(j);
        stringBuilder.append(" ");
        stringBuilder.append(timeUnit.toString().toLowerCase());
        stringBuilder.append(" and has been terminated.");
        return stringBuilder.toString();
    }
}
