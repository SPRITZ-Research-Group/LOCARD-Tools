package com.appboy.e;

import a.a.am;
import a.a.cv;
import com.appboy.b.a.b;
import com.appboy.b.a.d;
import com.appboy.b.a.f;
import org.json.JSONException;
import org.json.JSONObject;

public final class l extends k {
    public final /* synthetic */ Object h() {
        return H();
    }

    public l(JSONObject object, am appboyManager) {
        super(object, appboyManager);
        if (this.j.equals(d.GRAPHIC)) {
            this.e = (b) cv.a(object, "crop_type", b.class, b.CENTER_CROP);
        } else {
            this.e = (b) cv.a(object, "crop_type", b.class, b.FIT_CENTER);
        }
    }

    public final JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject H = super.H();
            H.put("type", f.MODAL.name());
            return H;
        } catch (JSONException e) {
            return null;
        }
    }
}
