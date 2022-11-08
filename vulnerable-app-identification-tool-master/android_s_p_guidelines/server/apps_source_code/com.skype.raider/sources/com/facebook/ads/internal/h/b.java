package com.facebook.ads.internal.h;

import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    public String a;
    public String b;
    public String c;
    public Date d;
    public boolean e;

    private b(String str, String str2, String str3, Date date) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = date;
        boolean z = (str2 == null || str3 == null) ? false : true;
        this.e = z;
    }

    private b(JSONObject jSONObject) {
        this(jSONObject.optString(j.FRAGMENT_URL), jSONObject.optString(PropertiesEntry.COLUMN_NAME_KEY), jSONObject.optString(PropertiesEntry.COLUMN_NAME_VALUE), new Date(jSONObject.optLong("expiration") * 1000));
    }

    public static List<b> a(String str) {
        if (str == null) {
            return null;
        }
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e) {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        List<b> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject;
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e2) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                arrayList.add(new b(jSONObject));
            }
        }
        return arrayList;
    }
}
