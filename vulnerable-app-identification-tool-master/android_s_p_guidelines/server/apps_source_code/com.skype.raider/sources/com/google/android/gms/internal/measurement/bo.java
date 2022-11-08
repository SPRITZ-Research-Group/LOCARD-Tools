package com.google.android.gms.internal.measurement;

final class bo implements Runnable {
    private final /* synthetic */ bx a;
    private final /* synthetic */ av b;

    bo(bx bxVar, av avVar) {
        this.a = bxVar;
        this.b = avVar;
    }

    public final void run() {
        if (this.a.g() == null) {
            this.b.v().a("Install Referrer Reporter is null");
        } else {
            this.a.g().a();
        }
    }
}
