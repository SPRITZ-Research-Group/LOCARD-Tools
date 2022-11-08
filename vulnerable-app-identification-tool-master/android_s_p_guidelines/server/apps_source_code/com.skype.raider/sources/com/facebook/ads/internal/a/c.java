package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.q.a.a;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final String a = c.class.getSimpleName();

    @Nullable
    public static b a(Context context, com.facebook.ads.internal.m.c cVar, String str, Uri uri, Map<String, String> map) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        String queryParameter = uri.getQueryParameter("video_url");
        if (!TextUtils.isEmpty(uri.getQueryParameter("data"))) {
            try {
                JSONObject jSONObject = new JSONObject(uri.getQueryParameter("data"));
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    map.put(str2, jSONObject.getString(str2));
                }
            } catch (JSONException e) {
            }
        }
        l a = l.a(cVar, a.a());
        Object obj = -1;
        switch (authority.hashCode()) {
            case -1458789996:
                if (authority.equals("passthrough")) {
                    obj = 2;
                    break;
                }
                break;
            case 109770977:
                if (authority.equals("store")) {
                    obj = null;
                    break;
                }
                break;
            case 1546100943:
                if (authority.equals("open_link")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return queryParameter != null ? null : new f(context, cVar, str, uri, map, a);
            case 1:
                return new i(context, cVar, str, uri, map, a);
            case 2:
                return new j(context, cVar, str, uri, map);
            default:
                return new k(context, cVar, str, uri);
        }
    }

    public static boolean a(String str) {
        return "store".equalsIgnoreCase(str) || "open_link".equalsIgnoreCase(str);
    }
}
