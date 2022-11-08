package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bp implements OnClickListener {
    final /* synthetic */ bo a;

    bp(bo boVar) {
        this.a = boVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.b.continueVideo();
        this.a.a.b.resumeReportRewardTask();
    }
}
