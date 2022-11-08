package com.facebook.ads.internal.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.j.a.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.g;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends h {
    private static final String e = f.class.getSimpleName();
    private final Uri f;
    private final Map<String, String> g;

    public f(Context context, c cVar, String str, Uri uri, Map<String, String> map, l lVar) {
        super(context, cVar, str, lVar);
        this.f = uri;
        this.g = map;
    }

    private Intent a(g gVar) {
        if (TextUtils.isEmpty(gVar.a())) {
            return null;
        }
        if (!e.a(this.a, gVar.a())) {
            return null;
        }
        CharSequence c = gVar.c();
        if (!TextUtils.isEmpty(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty(gVar.b()) && TextUtils.isEmpty(c)) {
            return packageManager.getLaunchIntentForPackage(gVar.a());
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        if (!(TextUtils.isEmpty(gVar.a()) || TextUtils.isEmpty(gVar.b()))) {
            intent.setComponent(new ComponentName(gVar.a(), gVar.b()));
        }
        if (!TextUtils.isEmpty(gVar.c())) {
            intent.setData(Uri.parse(gVar.c()));
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (intent.getComponent() == null) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(gVar.a())) {
                    intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        return (queryIntentActivities.isEmpty() || intent.getComponent() == null) ? null : intent;
    }

    private List<g> d() {
        Object queryParameter = this.f.getQueryParameter("appsite_data");
        if (TextUtils.isEmpty(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        List<g> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray("android");
            if (optJSONArray == null) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                g a = g.a(optJSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            return arrayList;
        }
    }

    private boolean f() {
        g gVar = new g();
        try {
            Uri parse;
            Context context = this.a;
            Object queryParameter = this.f.getQueryParameter("store_url");
            if (TextUtils.isEmpty(queryParameter)) {
                String queryParameter2 = this.f.getQueryParameter("store_id");
                parse = Uri.parse(String.format("market://details?id=%s", new Object[]{queryParameter2}));
            } else {
                parse = Uri.parse(queryParameter);
            }
            g.a(context, parse, this.c);
            return true;
        } catch (Exception e) {
            new StringBuilder("Failed to open market url: ").append(this.f.toString());
            String queryParameter3 = this.f.getQueryParameter("store_url_web_fallback");
            if (queryParameter3 != null && queryParameter3.length() > 0) {
                g.a(this.a, Uri.parse(queryParameter3), this.c);
            }
            return false;
        }
    }

    public final a a() {
        return a.OPEN_STORE;
    }

    final void c() {
        a aVar = null;
        Object obj = "opened_deeplink";
        if (!e()) {
            try {
                obj = f() ? "opened_store_url" : "opened_store_fallback_url";
            } catch (Exception e) {
                aVar = a.CANNOT_OPEN;
            }
        }
        this.g.put(obj, "true");
        a(this.g, aVar);
    }

    private boolean e() {
        Intent a;
        List<g> d = d();
        List<Intent> arrayList = new ArrayList();
        if (d != null) {
            for (g a2 : d) {
                a = a(a2);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        }
        for (Intent a3 : arrayList) {
            try {
                this.a.startActivity(a3);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }
}
