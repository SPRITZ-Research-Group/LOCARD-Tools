package com.appboy.e;

import a.a.am;
import a.a.cv;
import com.appboy.b.a.b;
import com.appboy.b.a.f;
import org.json.JSONException;
import org.json.JSONObject;

public final class h extends k {
    public final /* synthetic */ Object h() {
        return H();
    }

    public h() {
        this.e = b.CENTER_CROP;
    }

    public h(JSONObject object, am appboyManager) {
        super(object, appboyManager);
        this.e = (b) cv.a(object, "crop_type", b.class, b.CENTER_CROP);
    }

    public final JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject H = super.H();
            H.put("type", f.FULL.name());
            return H;
        } catch (JSONException e) {
            return null;
        }
    }
}
