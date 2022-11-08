package androidx.work.impl.background.systemalarm;

import androidx.work.p;

final class l implements Runnable {
    private final j a;
    private final String b;

    l(j jVar, String str) {
        this.a = jVar;
        this.b = str;
    }

    public final void run() {
        synchronized (this.a.c) {
            if (((l) this.a.a.remove(this.b)) != null) {
                k kVar = (k) this.a.b.remove(this.b);
                if (kVar != null) {
                    kVar.a(this.b);
                }
            } else {
                p.a();
                String.format("Timer with %s is already marked as complete.", new Object[]{this.b});
                Throwable[] thArr = new Throwable[0];
            }
        }
    }
}
