package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.google.firebase.analytics.connector.a;
import com.skype.camera.imagefilter.ImageFilterManager;

final class e {
    public static void a(Intent intent) {
        a("_nr", intent);
    }

    private static void a(String str, Intent intent) {
        Bundle bundle = new Bundle();
        String stringExtra = intent.getStringExtra("google.c.a.c_id");
        if (stringExtra != null) {
            bundle.putString("_nmid", stringExtra);
        }
        stringExtra = intent.getStringExtra("google.c.a.c_l");
        if (stringExtra != null) {
            bundle.putString("_nmn", stringExtra);
        }
        Object stringExtra2 = intent.getStringExtra("google.c.a.m_l");
        if (!TextUtils.isEmpty(stringExtra2)) {
            bundle.putString("label", stringExtra2);
        }
        stringExtra = intent.getStringExtra("from");
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            stringExtra = null;
        }
        if (stringExtra != null) {
            bundle.putString("_nt", stringExtra);
        }
        try {
            bundle.putInt("_nmt", Integer.parseInt(intent.getStringExtra("google.c.a.ts")));
        } catch (NumberFormatException e) {
        }
        if (intent.hasExtra("google.c.a.udt")) {
            try {
                bundle.putInt("_ndt", Integer.parseInt(intent.getStringExtra("google.c.a.udt")));
            } catch (NumberFormatException e2) {
            }
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            stringExtra = String.valueOf(bundle);
            new StringBuilder((String.valueOf(str).length() + 22) + String.valueOf(stringExtra).length()).append("Sending event=").append(str).append(" params=").append(stringExtra);
        }
        a aVar = (a) com.google.firebase.a.c().a(a.class);
        if (aVar != null) {
            aVar.a("fcm", str, bundle);
        }
    }

    public static void b(Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
                a aVar = (a) com.google.firebase.a.c().a(a.class);
                if (aVar != null) {
                    Object stringExtra = intent.getStringExtra("google.c.a.c_id");
                    aVar.a("fcm", "_ln", stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString(ImageFilterManager.PROP_SOURCE, "Firebase");
                    bundle.putString(Constants.MEDIUM, "notification");
                    bundle.putString("campaign", stringExtra);
                    aVar.a("fcm", "_cmp", bundle);
                }
            }
        }
        a("_no", intent);
    }

    public static void c(Intent intent) {
        a("_nd", intent);
    }

    public static void d(Intent intent) {
        a("_nf", intent);
    }
}
