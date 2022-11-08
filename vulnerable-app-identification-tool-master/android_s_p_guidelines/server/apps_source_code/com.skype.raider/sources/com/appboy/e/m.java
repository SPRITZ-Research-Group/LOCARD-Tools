package com.appboy.e;

import a.a.am;
import a.a.cv;
import com.appboy.b.a.b;
import com.appboy.b.a.f;
import com.appboy.b.a.h;
import com.appboy.b.a.i;
import org.json.JSONException;
import org.json.JSONObject;

public final class m extends f {
    private h j;
    private int k;

    public final /* synthetic */ Object h() {
        return H();
    }

    public m() {
        this.j = h.BOTTOM;
        this.f = i.START;
    }

    public m(JSONObject object, am appboyManager) {
        this(object, appboyManager, (h) cv.a(object, "slide_from", h.class, h.BOTTOM), object.optInt("close_btn_color"));
    }

    private m(JSONObject object, am appboyManager, h slideFrom, int chevronColor) {
        super(object, appboyManager);
        this.j = h.BOTTOM;
        this.j = slideFrom;
        if (this.j == null) {
            this.j = h.BOTTOM;
        }
        this.k = chevronColor;
        this.e = (b) cv.a(object, "crop_type", b.class, b.FIT_CENTER);
        this.f = (i) cv.a(object, "text_align_message", i.class, i.START);
    }

    public final h E() {
        return this.j;
    }

    public final int F() {
        return this.k;
    }

    public final JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject H = super.H();
            H.putOpt("slide_from", this.j.toString());
            H.put("close_btn_color", this.k);
            H.put("type", f.SLIDEUP.name());
            return H;
        } catch (JSONException e) {
            return null;
        }
    }
}
