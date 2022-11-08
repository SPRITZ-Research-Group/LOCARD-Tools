package a.a;

import com.appboy.b.a.f;
import com.appboy.e.b;
import com.appboy.e.g;
import com.appboy.e.h;
import com.appboy.e.j;
import com.appboy.e.l;
import com.appboy.e.m;
import com.appboy.f.c;
import org.json.JSONObject;

public final class cu {
    private static final String a = c.a(cu.class);

    public static b a(JSONObject jSONObject, am amVar) {
        if (jSONObject == null) {
            try {
                c.b(a, "In-app message Json was null. Not deserializing message.");
                return null;
            } catch (Throwable e) {
                c.c(a, "Encountered JSONException processing in-app message: " + cv.a(jSONObject), e);
                return null;
            } catch (Throwable e2) {
                c.d(a, "Failed to deserialize the in-app message: " + cv.a(jSONObject), e2);
                return null;
            }
        } else if (jSONObject.optBoolean("is_control", false)) {
            c.b(a, "Deserializing control in-app message.");
            return new g(jSONObject, amVar);
        } else {
            f fVar = (f) cv.a(jSONObject, "type", f.class, null);
            if (fVar == null) {
                c.d(a, "In-app message type was null. Not deserializing message: " + cv.a(jSONObject));
                return null;
            }
            switch (fVar) {
                case FULL:
                    return new h(jSONObject, amVar);
                case MODAL:
                    return new l(jSONObject, amVar);
                case SLIDEUP:
                    return new m(jSONObject, amVar);
                case HTML_FULL:
                    return new j(jSONObject, amVar);
                default:
                    c.g(a, "Unknown in-app message type. Not deserializing message: " + cv.a(jSONObject));
                    return null;
            }
        }
    }
}
