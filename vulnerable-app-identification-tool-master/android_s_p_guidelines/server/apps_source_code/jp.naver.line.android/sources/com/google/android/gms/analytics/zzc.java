package com.google.android.gms.analytics;

import android.content.BroadcastReceiver.PendingResult;

final class zzc implements Runnable {
    private final /* synthetic */ PendingResult zzrf;

    zzc(CampaignTrackingReceiver campaignTrackingReceiver, PendingResult pendingResult) {
        this.zzrf = pendingResult;
    }

    public final void run() {
        if (this.zzrf != null) {
            this.zzrf.finish();
        }
    }
}
