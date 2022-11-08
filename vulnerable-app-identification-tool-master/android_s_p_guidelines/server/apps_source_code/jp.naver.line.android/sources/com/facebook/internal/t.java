package com.facebook.internal;

import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import org.json.JSONArray;
import org.json.JSONObject;

public final class t {
    private String a;
    private String b;
    private Uri c;
    private int[] d;

    public static t a(JSONObject jSONObject) {
        String optString = jSONObject.optString("name");
        Uri uri = null;
        if (bj.a(optString)) {
            return null;
        }
        String[] split = optString.split("\\|");
        if (split.length != 2) {
            return null;
        }
        String str = split[0];
        optString = split[1];
        if (bj.a(str) || bj.a(optString)) {
            return null;
        }
        String optString2 = jSONObject.optString(ImagesContract.URL);
        if (!bj.a(optString2)) {
            uri = Uri.parse(optString2);
        }
        return new t(str, optString, uri, a(jSONObject.optJSONArray("versions")));
    }

    private static int[] a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            int optInt = jSONArray.optInt(i, -1);
            if (optInt == -1) {
                String optString = jSONArray.optString(i);
                if (!bj.a(optString)) {
                    try {
                        optInt = Integer.parseInt(optString);
                    } catch (Exception e) {
                        bj.a("FacebookSDK", e);
                        optInt = -1;
                    }
                }
            }
            iArr[i] = optInt;
        }
        return iArr;
    }

    private t(String str, String str2, Uri uri, int[] iArr) {
        this.a = str;
        this.b = str2;
        this.c = uri;
        this.d = iArr;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final Uri c() {
        return this.c;
    }

    public final int[] d() {
        return this.d;
    }
}
