package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.i;

final class aj implements Runnable {
    private final /* synthetic */ i a;
    private final /* synthetic */ ai b;

    aj(ai aiVar, i iVar) {
        this.b = aiVar;
        this.a = iVar;
    }

    @WorkerThread
    public final void run() {
        e eVar;
        try {
            BasePendingResult.a.set(Boolean.valueOf(true));
            this.b.h.sendMessage(this.b.h.obtainMessage(0, this.b.a.a()));
            BasePendingResult.a.set(Boolean.valueOf(false));
            eVar = (e) this.b.g.get();
            if (eVar != null) {
                eVar.c();
            }
        } catch (RuntimeException e) {
            this.b.h.sendMessage(this.b.h.obtainMessage(1, e));
            BasePendingResult.a.set(Boolean.valueOf(false));
            eVar = (e) this.b.g.get();
            if (eVar != null) {
                eVar.c();
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            BasePendingResult.a.set(Boolean.valueOf(false));
            eVar = (e) this.b.g.get();
            if (eVar != null) {
                eVar.c();
            }
        }
    }
}
