package com.google.android.gms.b;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final class i implements OnClickListener {
    private final /* synthetic */ Context a;
    private final /* synthetic */ Intent b;

    i(Context context, Intent intent) {
        this.a = context;
        this.b = intent;
    }

    public final void onClick(View view) {
        try {
            this.a.startActivity(this.b);
        } catch (ActivityNotFoundException e) {
        }
    }
}
