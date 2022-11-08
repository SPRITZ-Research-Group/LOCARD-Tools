package a.a;

import com.appboy.e.b;
import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONException;
import org.json.JSONObject;

public final class dd extends df implements dc {
    private static final String a = c.a(dd.class);
    private b b;
    private am c;
    private String d;

    public final /* synthetic */ Object h() {
        return e();
    }

    public dd(JSONObject jSONObject, am amVar) {
        super(jSONObject);
        c.b(a, "Parsing in-app message triggered action with JSON: " + cv.a(jSONObject));
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2 != null) {
            this.c = amVar;
            this.b = cu.a(jSONObject2, this.c);
            return;
        }
        c.f(a, "InAppMessageTriggeredAction Json did not contain in-app message.");
    }

    public final et d() {
        if (i.c(this.b.s())) {
            return null;
        }
        if (this.b instanceof com.appboy.e.c) {
            return new et(ea.ZIP, this.b.s());
        }
        return new et(ea.IMAGE, this.b.s());
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(b bVar, ed edVar, long j) {
        try {
            c.b(a, "Attempting to publish in-app message after delay of " + c().d() + " seconds.");
            if (!i.c(this.d)) {
                this.b.a(this.d);
            }
            this.b.a(j);
            bVar.a(new com.appboy.c.c(this.b, this.c.d()), com.appboy.c.c.class);
        } catch (Throwable e) {
            c.c(a, "Caught exception while performing triggered action.", e);
        }
    }

    public final JSONObject e() {
        try {
            JSONObject e = super.e();
            e.put("data", this.b.h());
            e.put("type", "inapp");
            return e;
        } catch (JSONException e2) {
            return null;
        }
    }
}
