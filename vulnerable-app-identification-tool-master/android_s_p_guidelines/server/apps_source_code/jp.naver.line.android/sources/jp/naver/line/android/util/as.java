package jp.naver.line.android.util;

import defpackage.aciu;
import java.util.concurrent.ExecutionException;

public abstract class as implements Runnable {
    final /* synthetic */ ar a;

    protected abstract String b();

    protected as(ar arVar) {
        this.a = arVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        boolean z;
        Throwable cause;
        String b = b();
        Thread.currentThread().setName(b);
        while (true) {
            Runnable a = this.a.a();
            if (a != null) {
                bq bqVar;
                ar arVar;
                if (this.a.b) {
                    Thread.interrupted();
                }
                boolean z2 = false;
                if (a instanceof bq) {
                    bqVar = (bq) a;
                    if (!bqVar.isDone()) {
                        z = bqVar.c;
                        StringBuilder b2 = aciu.a().b();
                        b2.append(b);
                        b2.append(bqVar.a);
                        String stringBuilder = b2.toString();
                        aciu.a().a(b2);
                        Thread.currentThread().setName(stringBuilder);
                    }
                    Thread.currentThread().setName(b);
                } else {
                    bqVar = null;
                    a.getClass().getName();
                    z = false;
                }
                System.currentTimeMillis();
                try {
                    this.a.b(a);
                    a.run();
                    if (!(bqVar == null || bqVar.e)) {
                        bqVar.get();
                    }
                    arVar = this.a;
                } catch (ExecutionException e) {
                    cause = e.getCause();
                    if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else if (cause instanceof InterruptedException) {
                        arVar = this.a;
                    } else {
                        throw (cause instanceof RuntimeException ? (RuntimeException) cause : new RuntimeException(cause));
                    }
                } catch (OutOfMemoryError e2) {
                    cause = e2;
                    if (z) {
                        System.runFinalization();
                        System.gc();
                    } else {
                        cause = a((OutOfMemoryError) cause);
                    }
                    if (cause != null) {
                        if (!z) {
                            z2 = true;
                        }
                        ar.a(cause, (Object) a, z2);
                    }
                    arVar = this.a;
                } catch (RuntimeException e3) {
                    cause = e3;
                    if (!z) {
                        cause = a((RuntimeException) cause);
                    }
                    if (cause != null) {
                        if (!z) {
                            z2 = true;
                        }
                        ar.a(cause, (Object) a, z2);
                    }
                    arVar = this.a;
                } catch (Throwable th) {
                    this.a.b();
                }
                arVar.a(a);
                Thread.currentThread().setName(b);
            } else {
                this.a.b();
                return;
            }
        }
    }

    protected OutOfMemoryError a(OutOfMemoryError outOfMemoryError) throws OutOfMemoryError {
        throw outOfMemoryError;
    }

    protected RuntimeException a(RuntimeException runtimeException) throws RuntimeException {
        throw runtimeException;
    }
}
