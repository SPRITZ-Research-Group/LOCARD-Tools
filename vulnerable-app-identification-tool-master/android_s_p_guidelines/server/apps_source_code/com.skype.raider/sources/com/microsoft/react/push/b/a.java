package com.microsoft.react.push.b;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.microsoft.react.push.adm.ADMPushRegistration;
import com.microsoft.react.push.d;

public final class a implements d {
    public static boolean a(Context context) {
        try {
            if (GoogleApiAvailability.a().a(context) == 0) {
                return true;
            }
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }

    public static boolean b(Context context) {
        return ADMPushRegistration.getInstance().isSupported(context);
    }
}
