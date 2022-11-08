package com.appboy.e;

import a.a.cv;
import android.net.Uri;
import com.appboy.b.a.a;
import com.appboy.f.c;
import com.appboy.f.i;
import com.brentvatne.react.ReactVideoViewManager;
import org.json.JSONException;
import org.json.JSONObject;

public class n implements e<JSONObject> {
    private static final String a = c.a(n.class);
    private JSONObject b;
    private int c;
    private a d;
    private Uri e;
    private String f;
    private int g;
    private int h;
    private boolean i;

    public final /* synthetic */ Object h() {
        return i();
    }

    public n() {
        this.c = -1;
        this.d = a.NONE;
        this.g = 0;
        this.h = 0;
    }

    public n(JSONObject object) {
        this(object, object.optInt("id", -1), (a) cv.a(object, "click_action", a.class, a.NEWS_FEED), object.optString(ReactVideoViewManager.PROP_SRC_URI), object.optString("text"), object.optInt("bg_color"), object.optInt("text_color"), object.optBoolean("use_webview", false));
    }

    private n(JSONObject jsonObject, int id, a clickAction, String uri, String text, int backgroundColor, int textColor, boolean openUriInWebview) {
        this.c = -1;
        this.d = a.NONE;
        this.g = 0;
        this.h = 0;
        this.b = jsonObject;
        this.c = id;
        this.d = clickAction;
        if (this.d == a.URI && !i.c(uri)) {
            this.e = Uri.parse(uri);
        }
        this.f = text;
        this.g = backgroundColor;
        this.h = textColor;
        this.i = openUriInWebview;
    }

    public final int a() {
        return this.c;
    }

    public final a b() {
        return this.d;
    }

    public final Uri c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final int e() {
        return this.g;
    }

    public final int f() {
        return this.h;
    }

    public final boolean g() {
        return this.i;
    }

    public final JSONObject i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.c);
            jSONObject.put("click_action", this.d.toString());
            if (this.e != null) {
                jSONObject.put(ReactVideoViewManager.PROP_SRC_URI, this.e.toString());
            }
            jSONObject.putOpt("text", this.f);
            jSONObject.put("bg_color", this.g);
            jSONObject.put("text_color", this.h);
            jSONObject.put("use_webview", this.i);
            return jSONObject;
        } catch (JSONException e) {
            return this.b;
        }
    }
}
