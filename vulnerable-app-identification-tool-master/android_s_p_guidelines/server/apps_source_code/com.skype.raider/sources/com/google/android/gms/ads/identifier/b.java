package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.k;

public final class b {
    private SharedPreferences a;

    public b(Context context) {
        try {
            Context e = k.e(context);
            this.a = e == null ? null : e.getSharedPreferences("google_ads_flags", 0);
        } catch (Throwable th) {
            this.a = null;
        }
    }

    final String a(String str, String str2) {
        try {
            return this.a == null ? str2 : this.a.getString(str, str2);
        } catch (Throwable th) {
            return str2;
        }
    }

    public final boolean a(String str) {
        try {
            return this.a == null ? false : this.a.getBoolean(str, false);
        } catch (Throwable th) {
            return false;
        }
    }

    final float b(String str) {
        try {
            return this.a == null ? 0.0f : this.a.getFloat(str, 0.0f);
        } catch (Throwable th) {
            return 0.0f;
        }
    }
}
