package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.ab;

public final class bn {
    private final bq a;

    public bn(bq bqVar) {
        ab.a((Object) bqVar);
        this.a = bqVar;
    }

    public static boolean a(Context context) {
        ab.a((Object) context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);
            return receiverInfo != null && receiverInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @MainThread
    public final void a(Context context, Intent intent) {
        eo a = bx.a(context);
        av q = a.q();
        if (intent == null) {
            q.y().a("Receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        q.C().a("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            q.C().a("Starting wakeful intent.");
            this.a.a(context, className);
        } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                a.p().a(new bo(a, q));
            } catch (Exception e) {
                q.y().a("Install Referrer Reporter encountered a problem", e);
            }
            PendingResult a2 = this.a.a();
            action = intent.getStringExtra(Constants.REFERRER);
            if (action == null) {
                q.C().a("Install referrer extras are null");
                if (a2 != null) {
                    a2.finish();
                    return;
                }
                return;
            }
            q.A().a("Install referrer extras are", action);
            if (!action.contains("?")) {
                String str = "?";
                action = String.valueOf(action);
                action = action.length() != 0 ? str.concat(action) : new String(str);
            }
            Bundle a3 = a.n().a(Uri.parse(action));
            if (a3 == null) {
                q.C().a("No campaign defined in install referrer broadcast");
                if (a2 != null) {
                    a2.finish();
                    return;
                }
                return;
            }
            long longExtra = intent.getLongExtra("referrer_timestamp_seconds", 0) * 1000;
            if (longExtra == 0) {
                q.y().a("Install referrer is missing timestamp");
            }
            a.p().a(new bp(a, longExtra, a3, context, q, a2));
        }
    }
}
