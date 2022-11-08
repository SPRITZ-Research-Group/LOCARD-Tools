package com.facebook.ads.internal.q.c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.t.b;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public final class g {
    private static void a(Context context, Uri uri) {
        context.startActivity(b(context, uri));
    }

    public static void a(Context context, Uri uri, String str) {
        Intent b;
        Object obj = 1;
        Object obj2 = (a(uri.getScheme()) && uri.getHost().equals("play.google.com")) ? 1 : null;
        if (uri.getScheme().equals("market") || obj2 != null) {
            try {
                for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/")), 0)) {
                    if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    throw new c();
                }
                b = b(context, uri);
                b.setPackage("com.android.vending");
                context.startActivity(b);
                return;
            } catch (c e) {
            }
        }
        if (a(uri.getScheme()) && a.g(context)) {
            b = new Intent();
            b.setClassName(context.getPackageName(), "com.facebook.ads.AudienceNetworkActivity");
            b.addFlags(ErrorDialogData.BINDER_CRASH);
            b.putExtra("viewType", b.a.BROWSER);
            b.putExtra("browserURL", uri.toString());
            b.putExtra("clientToken", str);
            b.putExtra("handlerTime", System.currentTimeMillis());
            try {
                context.startActivity(b);
                return;
            } catch (ActivityNotFoundException e2) {
                b.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
                try {
                    context.startActivity(b);
                    return;
                } catch (ActivityNotFoundException e3) {
                    a(context, uri);
                    return;
                }
            }
        }
        a(context, uri);
    }

    private static boolean a(String str) {
        return "http".equalsIgnoreCase(str) || Constants.SCHEME.equalsIgnoreCase(str);
    }

    private static Intent b(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent(null);
        if (VERSION.SDK_INT >= 15) {
            intent.setSelector(null);
        }
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("create_new_tab", false);
        return intent;
    }
}
