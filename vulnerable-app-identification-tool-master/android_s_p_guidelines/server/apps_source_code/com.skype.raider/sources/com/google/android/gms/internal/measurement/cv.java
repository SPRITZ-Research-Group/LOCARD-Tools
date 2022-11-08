package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class cv extends et {
    final Context a;

    @VisibleForTesting
    public cv(Context context) {
        ab.a((Object) context);
        Object applicationContext = context.getApplicationContext();
        ab.a(applicationContext);
        this.a = applicationContext;
    }
}
