package com.appboy.e;

import a.a.am;
import com.appboy.b.a.f;
import org.json.JSONException;
import org.json.JSONObject;

public final class j extends i {
    public final /* synthetic */ Object h() {
        return H();
    }

    public j(JSONObject object, am appboyManager) {
        super(object, appboyManager);
    }

    public final JSONObject H() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject H = super.H();
            H.put("type", f.HTML_FULL.name());
            return H;
        } catch (JSONException e) {
            return null;
        }
    }
}
