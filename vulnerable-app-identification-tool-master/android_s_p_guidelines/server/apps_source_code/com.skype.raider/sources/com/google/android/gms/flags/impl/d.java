package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class d implements Callable<Integer> {
    private final /* synthetic */ SharedPreferences a;
    private final /* synthetic */ String b;
    private final /* synthetic */ Integer c;

    d(SharedPreferences sharedPreferences, String str, Integer num) {
        this.a = sharedPreferences;
        this.b = str;
        this.c = num;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Integer.valueOf(this.a.getInt(this.b, this.c.intValue()));
    }
}
