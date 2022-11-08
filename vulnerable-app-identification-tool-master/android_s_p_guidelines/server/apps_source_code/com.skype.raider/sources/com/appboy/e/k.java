package com.appboy.e;

import a.a.am;
import a.a.av;
import a.a.be;
import a.a.cv;
import a.a.ef;
import com.appboy.b.a.d;
import com.appboy.b.a.i;
import com.appboy.f.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class k extends f implements d {
    protected d j;
    private String k;
    private int l;
    private int m;
    private List<n> n;
    private Integer o;
    private i p;
    private boolean q;
    private String r;

    public /* synthetic */ Object h() {
        return H();
    }

    protected k() {
        this.l = 0;
        this.m = 0;
        this.j = d.TOP;
        this.o = null;
        this.p = i.CENTER;
        this.r = null;
    }

    public k(JSONObject object, am appboyManager) {
        this(object, appboyManager, object.optString("header"), object.optInt("header_text_color"), object.optInt("close_btn_color"), (d) cv.a(object, "image_style", d.class, d.TOP), (i) cv.a(object, "text_align_header", i.class, i.CENTER), (i) cv.a(object, "text_align_message", i.class, i.CENTER));
        if (object.optJSONArray("btns") != null) {
            List arrayList = new ArrayList();
            JSONArray optJSONArray = object.optJSONArray("btns");
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new n(optJSONArray.optJSONObject(i)));
            }
            this.n = arrayList;
        }
    }

    private k(JSONObject object, am appboyManager, String header, int headerTextColor, int closeButtonColor, d imageStyle, i headerTextAlign, i messageTextAlign) {
        super(object, appboyManager);
        this.l = 0;
        this.m = 0;
        this.j = d.TOP;
        this.o = null;
        this.p = i.CENTER;
        this.r = null;
        this.k = header;
        this.l = headerTextColor;
        this.m = closeButtonColor;
        if (object.has("frame_color")) {
            this.o = Integer.valueOf(object.optInt("frame_color"));
        }
        this.j = imageStyle;
        this.p = headerTextAlign;
        this.f = messageTextAlign;
    }

    public final String I() {
        return this.k;
    }

    public final int J() {
        return this.l;
    }

    public final int K() {
        return this.m;
    }

    public final List<n> E() {
        return this.n;
    }

    public final Integer L() {
        return this.o;
    }

    public final d F() {
        return this.j;
    }

    public final i M() {
        return this.p;
    }

    public final boolean a(n messageButton) {
        if (com.appboy.f.i.c(this.b) && com.appboy.f.i.c(this.c) && com.appboy.f.i.c(this.d)) {
            c.b(a, "Campaign, trigger, and card Ids not found. Not logging button click.");
            return false;
        } else if (messageButton == null) {
            c.f(a, "Message button was null. Ignoring button click.");
            return false;
        } else if (this.q) {
            c.d(a, "Button click already logged for this message. Ignoring.");
            return false;
        } else if (this.i == null) {
            c.g(a, "Cannot log a button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                av a = be.a(this.b, this.c, this.d, messageButton);
                this.r = be.a(messageButton);
                this.i.a(a);
                this.q = true;
                return true;
            } catch (Throwable e) {
                this.i.a(e);
                return false;
            }
        }
    }

    public final void C() {
        super.C();
        if (this.q && !com.appboy.f.i.c(this.d) && !com.appboy.f.i.c(this.r)) {
            this.i.a(new ef(this.d, this.r));
        }
    }

    public JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject H = super.H();
            H.putOpt("header", this.k);
            H.put("header_text_color", this.l);
            H.put("close_btn_color", this.m);
            H.putOpt("image_style", this.j.toString());
            H.putOpt("text_align_header", this.p.toString());
            if (this.o != null) {
                H.put("frame_color", this.o.intValue());
            }
            if (this.n != null) {
                JSONArray jSONArray = new JSONArray();
                for (n i : this.n) {
                    jSONArray.put(i.i());
                }
                H.put("btns", jSONArray);
            }
            return H;
        } catch (JSONException e) {
            return null;
        }
    }
}
