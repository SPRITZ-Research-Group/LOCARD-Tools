package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class g implements Callable<SharedPreferences> {
    private final /* synthetic */ Context a;

    g(Context context) {
        this.a = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        return this.a.getSharedPreferences("google_sdk_flags", 0);
    }
}
