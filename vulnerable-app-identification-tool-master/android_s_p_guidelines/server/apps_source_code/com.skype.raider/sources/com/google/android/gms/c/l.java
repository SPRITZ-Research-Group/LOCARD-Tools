package com.google.android.gms.c;

final class l implements Runnable {
    private final /* synthetic */ g a;
    private final /* synthetic */ k b;

    l(k kVar, g gVar) {
        this.b = kVar;
        this.a = gVar;
    }

    public final void run() {
        if (this.a.c()) {
            this.b.c.f();
            return;
        }
        try {
            this.b.c.a(this.b.b.a(this.a));
        } catch (Exception e) {
            if (e.getCause() instanceof Exception) {
                this.b.c.a((Exception) e.getCause());
            } else {
                this.b.c.a(e);
            }
        } catch (Exception e2) {
            this.b.c.a(e2);
        }
    }
}
