package com.applovin.adview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ AppLovinConfirmationActivity a;

    a(AppLovinConfirmationActivity appLovinConfirmationActivity) {
        this.a = appLovinConfirmationActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.finish();
    }
}
