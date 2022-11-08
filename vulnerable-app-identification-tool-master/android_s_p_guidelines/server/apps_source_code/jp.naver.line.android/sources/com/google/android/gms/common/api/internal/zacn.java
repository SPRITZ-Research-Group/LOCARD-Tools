package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zacn implements Runnable {
    private final /* synthetic */ Result zaku;
    private final /* synthetic */ zacm zakv;

    zacn(zacm zacm, Result result) {
        this.zakv = zacm;
        this.zaku = result;
    }

    public final void run() {
        GoogleApiClient googleApiClient;
        try {
            BasePendingResult.zadm.set(Boolean.TRUE);
            this.zakv.zaks.sendMessage(this.zakv.zaks.obtainMessage(0, this.zakv.zakn.onSuccess(this.zaku)));
            BasePendingResult.zadm.set(Boolean.FALSE);
            zacm.zab(this.zaku);
            googleApiClient = (GoogleApiClient) this.zakv.zadp.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakv);
            }
        } catch (RuntimeException e) {
            this.zakv.zaks.sendMessage(this.zakv.zaks.obtainMessage(1, e));
            BasePendingResult.zadm.set(Boolean.FALSE);
            zacm.zab(this.zaku);
            googleApiClient = (GoogleApiClient) this.zakv.zadp.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakv);
            }
        } catch (Throwable th) {
            BasePendingResult.zadm.set(Boolean.FALSE);
            zacm.zab(this.zaku);
            GoogleApiClient googleApiClient2 = (GoogleApiClient) this.zakv.zadp.get();
            if (googleApiClient2 != null) {
                googleApiClient2.zab(this.zakv);
            }
            throw th;
        }
    }
}
