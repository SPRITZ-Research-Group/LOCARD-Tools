package com.microsoft.react.push.adm;

import android.content.Context;
import com.microsoft.react.push.c.b;

public class AdmTokenImpl implements b {
    public String getToken(Context context) {
        return ADMPushRegistration.getInstance().getRegistrationToken(context);
    }

    public void setToken(Context context, String token) {
        if (token == null) {
            ADMPushRegistration.getInstance().unregister(context);
        }
    }
}
