package com.microsoft.react.push.gcm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.microsoft.react.push.c.b;
import com.microsoft.react.push.d;

public final class a implements b, d {
    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public final void setToken(Context context, String token) {
        boolean registered;
        if (TextUtils.isEmpty(token)) {
            registered = false;
        } else {
            registered = true;
        }
        FLog.i("GcmTokenImpl", "setToken: registered: " + registered);
        context.getSharedPreferences("Push", 0).edit().putString("com.microsoft.react.push.PushConstants.extra.token", token).commit();
    }

    public final String getToken(Context context) {
        return context.getSharedPreferences("Push", 0).getString("com.microsoft.react.push.PushConstants.extra.token", "");
    }
}
