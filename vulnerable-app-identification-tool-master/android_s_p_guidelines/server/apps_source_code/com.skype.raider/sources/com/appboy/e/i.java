package com.appboy.e;

import a.a.am;
import a.a.be;
import a.a.ef;
import com.appboy.f.c;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class i extends f implements c {
    private String j;
    private String k;
    private boolean l;
    private String m;

    public /* synthetic */ Object h() {
        return H();
    }

    protected i() {
        this.l = false;
        this.m = null;
        this.g = true;
    }

    public i(JSONObject object, am appboyManager) {
        super(object, appboyManager);
        this.l = false;
        this.m = null;
        if (!com.appboy.f.i.c(object.optString("zipped_assets_url"))) {
            this.j = object.optString("zipped_assets_url");
        }
        this.g = object.optBoolean("use_webview", true);
    }

    public final String E() {
        return this.k;
    }

    public final String F() {
        return this.j;
    }

    public final void c(String assetsLocalDirectoryUrl) {
        this.k = assetsLocalDirectoryUrl;
    }

    public final boolean b(String buttonId) {
        if (com.appboy.f.i.b(this.b) && com.appboy.f.i.b(this.c) && com.appboy.f.i.b(this.d)) {
            c.b(a, "Campaign, card, and trigger Ids not found. Not logging html in-app message click.");
            return false;
        } else if (com.appboy.f.i.c(buttonId)) {
            c.d(a, "Button Id was null or blank for this html in-app message. Ignoring.");
            return false;
        } else if (this.l) {
            c.d(a, "Button click already logged for this html in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            c.g(a, "Cannot log an html in-app message button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a(be.a(this.b, this.c, this.d, buttonId));
                this.m = buttonId;
                this.l = true;
                return true;
            } catch (Throwable e) {
                this.i.a(e);
                return false;
            }
        }
    }

    public final void C() {
        super.C();
        if (this.l && !com.appboy.f.i.c(this.d) && !com.appboy.f.i.c(this.m)) {
            this.i.a(new ef(this.d, this.m));
        }
    }

    public JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject H = super.H();
            H.putOpt("zipped_assets_url", this.j);
            return H;
        } catch (JSONException e) {
            return null;
        }
    }

    public final String s() {
        return this.j;
    }

    public final void a(String localUri) {
        this.k = localUri;
    }
}
