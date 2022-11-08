package com.appboy.e;

import a.a.am;
import a.a.be;
import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONObject;

public final class g extends f {
    private boolean j = false;

    public g(JSONObject object, am appboyManager) {
        super(object, appboyManager);
    }

    public final boolean A() {
        if (this.j) {
            c.d(a, "Control impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (i.b(this.d)) {
            c.f(a, "Trigger Id not found. Not logging in-app message control impression.");
            return false;
        } else if (this.i == null) {
            c.g(a, "Cannot log an in-app message control impression because the AppboyManager is null.");
            return false;
        } else {
            try {
                c.a(a, "Logging control in-app message impression event");
                this.i.a(be.a(this.b, this.c, this.d));
                this.j = true;
                return true;
            } catch (Throwable e) {
                this.i.a(e);
                return false;
            }
        }
    }
}
