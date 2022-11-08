package com.google.firebase.iid;

import java.util.concurrent.TimeUnit;

final /* synthetic */ class am implements Runnable {
    private final aj a;

    am(aj ajVar) {
        this.a = ajVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        aj ajVar = this.a;
        while (true) {
            synchronized (ajVar) {
                if (ajVar.a != 2) {
                    return;
                } else if (ajVar.d.isEmpty()) {
                    ajVar.a();
                    return;
                } else {
                    c cVar = (c) ajVar.d.poll();
                    ajVar.e.put(cVar.a, cVar);
                    ajVar.f.c.schedule(new an(ajVar, cVar), 30, TimeUnit.SECONDS);
                }
            }
        }
    }
}
