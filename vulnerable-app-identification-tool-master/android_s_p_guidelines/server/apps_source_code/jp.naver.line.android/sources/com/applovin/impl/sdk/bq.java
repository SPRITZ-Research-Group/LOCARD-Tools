package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bq implements OnClickListener {
    final /* synthetic */ bo a;

    bq(bo boVar) {
        this.a = boVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.b.skipVideo();
        this.a.a.b.resumeReportRewardTask();
    }
}
