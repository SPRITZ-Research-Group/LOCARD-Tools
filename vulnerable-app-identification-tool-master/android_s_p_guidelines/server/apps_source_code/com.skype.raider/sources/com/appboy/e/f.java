package com.appboy.e;

import a.a.am;
import a.a.be;
import a.a.cv;
import a.a.ef;
import android.graphics.Bitmap;
import android.net.Uri;
import com.appboy.b.a.a;
import com.appboy.b.a.b;
import com.appboy.b.a.e;
import com.appboy.b.a.g;
import com.appboy.b.a.i;
import com.appboy.f.c;
import com.brentvatne.react.ReactVideoViewManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class f implements b {
    protected static final String a = c.a(f.class);
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private String E;
    private long F;
    String b;
    String c;
    String d;
    protected b e;
    protected i f;
    protected boolean g;
    protected JSONObject h;
    protected am i;
    private String j;
    private Map<String, String> k;
    private boolean l;
    private boolean m;
    private a n;
    private Uri o;
    private com.appboy.b.a.c p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private String v;
    private String w;
    private g x;
    private Bitmap y;
    private boolean z;

    public /* synthetic */ Object h() {
        return H();
    }

    protected f() {
        this.l = true;
        this.m = true;
        this.n = a.NONE;
        this.p = com.appboy.b.a.c.AUTO_DISMISS;
        this.q = 5000;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.x = g.ANY;
        this.z = false;
        this.e = b.FIT_CENTER;
        this.f = i.CENTER;
        this.g = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.F = -1;
    }

    public f(JSONObject object, am appboyManager) {
        this(object.optString("message"), cv.a(object.optJSONObject("extras"), new HashMap()), object.optBoolean("animate_in", true), object.optBoolean("animate_out", true), (a) cv.a(object, "click_action", a.class, a.NONE), object.optString(ReactVideoViewManager.PROP_SRC_URI), object.optInt("bg_color"), object.optInt("icon_color"), object.optInt("icon_bg_color"), object.optInt("text_color"), object.optString("icon"), object.optString("image_url"), (com.appboy.b.a.c) cv.a(object, "message_close", com.appboy.b.a.c.class, com.appboy.b.a.c.AUTO_DISMISS), object.optInt("duration"), object.optString("campaign_id"), object.optString("card_id"), object.optString("trigger_id"), (g) cv.a(object, "orientation", g.class, g.ANY), object.optBoolean("use_webview", false), object.optBoolean("is_control"), object, appboyManager);
    }

    private f(String message, Map<String, String> extras, boolean animateIn, boolean animateOut, a clickAction, String uri, int backgroundColor, int iconColor, int iconBackgroundColor, int messageTextColor, String icon, String remoteImageUrl, com.appboy.b.a.c dismissType, int durationInMilliseconds, String campaignId, String cardId, String triggerId, g orientation, boolean openUriInWebView, boolean isControl, JSONObject object, am appboyManager) {
        this.l = true;
        this.m = true;
        this.n = a.NONE;
        this.p = com.appboy.b.a.c.AUTO_DISMISS;
        this.q = 5000;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.x = g.ANY;
        this.z = false;
        this.e = b.FIT_CENTER;
        this.f = i.CENTER;
        this.g = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.F = -1;
        this.j = message;
        this.k = extras;
        this.l = animateIn;
        this.m = animateOut;
        this.n = clickAction;
        if (this.n == a.URI && !com.appboy.f.i.c(uri)) {
            this.o = Uri.parse(uri);
        }
        if (dismissType == com.appboy.b.a.c.SWIPE) {
            this.p = com.appboy.b.a.c.MANUAL;
        } else {
            this.p = dismissType;
        }
        if (durationInMilliseconds < 999) {
            this.q = 5000;
            c.f(a, "Requested in-app message duration " + durationInMilliseconds + " is lower than the minimum of 999. Defaulting to " + this.q + " milliseconds.");
        } else {
            this.q = durationInMilliseconds;
            c.b(a, "Set in-app message duration to " + this.q + " milliseconds.");
        }
        this.r = backgroundColor;
        this.t = iconColor;
        this.u = iconBackgroundColor;
        this.s = messageTextColor;
        this.v = icon;
        this.w = remoteImageUrl;
        this.x = orientation;
        this.b = campaignId;
        this.c = cardId;
        this.d = triggerId;
        this.A = false;
        this.B = false;
        this.g = openUriInWebView;
        this.D = isControl;
        this.h = object;
        this.i = appboyManager;
    }

    public final String a() {
        return this.j;
    }

    public final Map<String, String> b() {
        return this.k;
    }

    public final int c() {
        return this.q;
    }

    public final int d() {
        return this.r;
    }

    public final int e() {
        return this.t;
    }

    public final int f() {
        return this.u;
    }

    public final int g() {
        return this.s;
    }

    public final String i() {
        return this.v;
    }

    public final String j() {
        return this.w;
    }

    public final String k() {
        return this.E;
    }

    public final boolean l() {
        return this.l;
    }

    public final boolean m() {
        return this.m;
    }

    public final a n() {
        return this.n;
    }

    public final Uri o() {
        return this.o;
    }

    public final Bitmap p() {
        return this.y;
    }

    public final com.appboy.b.a.c q() {
        return this.p;
    }

    public final boolean r() {
        return this.z;
    }

    public final g t() {
        return this.x;
    }

    public final b u() {
        return this.e;
    }

    public final i G() {
        return this.f;
    }

    public final long v() {
        return this.F;
    }

    public final boolean w() {
        return this.g;
    }

    public final void a(long timestamp) {
        this.F = timestamp;
    }

    public final void x() {
        this.l = false;
    }

    public final void a(boolean animateOut) {
        this.m = animateOut;
    }

    public final void y() {
        this.E = null;
    }

    public final void a(Bitmap bitmap) {
        this.y = bitmap;
    }

    public final void z() {
        this.z = true;
    }

    public boolean A() {
        if (com.appboy.f.i.b(this.b) && com.appboy.f.i.b(this.c) && com.appboy.f.i.b(this.d)) {
            c.b(a, "Campaign, card, and trigger Ids not found. Not logging in-app message impression.");
            return false;
        } else if (this.A) {
            c.d(a, "Impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.C) {
            c.d(a, "Display failure already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            c.g(a, "Cannot log an in-app message impression because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a(be.b(this.b, this.c, this.d));
                this.A = true;
                return true;
            } catch (Throwable e) {
                this.i.a(e);
                return false;
            }
        }
    }

    public final boolean B() {
        if (com.appboy.f.i.c(this.b) && com.appboy.f.i.c(this.c) && com.appboy.f.i.c(this.d)) {
            c.b(a, "Campaign, card, and trigger Ids not found. Not logging in-app message click.");
            return false;
        } else if (this.B) {
            c.d(a, "Click already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.C) {
            c.d(a, "Display failure already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            c.g(a, "Cannot log an in-app message click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a(be.c(this.b, this.c, this.d));
                this.B = true;
                return true;
            } catch (Throwable e) {
                this.i.a(e);
                return false;
            }
        }
    }

    public void C() {
        if (this.B && !com.appboy.f.i.b(this.d)) {
            this.i.a(new ef(this.d));
        }
    }

    public final boolean D() {
        return this.D;
    }

    public final boolean a(e failureType) {
        if (com.appboy.f.i.c(this.b) && com.appboy.f.i.c(this.c) && com.appboy.f.i.c(this.d)) {
            c.b(a, "Campaign, card, and trigger Ids not found. Not logging in-app message display failure.");
            return false;
        } else if (this.C) {
            c.d(a, "Display failure already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.B) {
            c.d(a, "Click already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.A) {
            c.d(a, "Impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            c.g(a, "Cannot log an in-app message display failure because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a(be.a(this.b, this.c, this.d, failureType));
                this.C = true;
                return true;
            } catch (Throwable e) {
                this.i.a(e);
                return false;
            }
        }
    }

    public JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("message", this.j);
            jSONObject.put("duration", this.q);
            jSONObject.putOpt("campaign_id", this.b);
            jSONObject.putOpt("card_id", this.c);
            jSONObject.putOpt("trigger_id", this.d);
            jSONObject.putOpt("click_action", this.n.toString());
            jSONObject.putOpt("message_close", this.p.toString());
            if (this.o != null) {
                jSONObject.put(ReactVideoViewManager.PROP_SRC_URI, this.o.toString());
            }
            jSONObject.put("use_webview", this.g);
            jSONObject.put("animate_in", this.l);
            jSONObject.put("animate_out", this.m);
            jSONObject.put("bg_color", this.r);
            jSONObject.put("text_color", this.s);
            jSONObject.put("icon_color", this.t);
            jSONObject.put("icon_bg_color", this.u);
            jSONObject.putOpt("icon", this.v);
            jSONObject.putOpt("image_url", this.w);
            jSONObject.putOpt("crop_type", this.e.toString());
            jSONObject.putOpt("orientation", this.x.toString());
            jSONObject.putOpt("text_align_message", this.f.toString());
            jSONObject.putOpt("is_control", Boolean.valueOf(this.D));
            if (this.k != null) {
                JSONObject jSONObject2 = new JSONObject();
                for (String str : this.k.keySet()) {
                    jSONObject2.put(str, this.k.get(str));
                }
                jSONObject.put("extras", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public String s() {
        return this.w;
    }

    public void a(String localUri) {
        this.E = localUri;
    }
}
