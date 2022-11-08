package a.a;

import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONObject;

public final class dh extends dr {
    private static final String b = c.a(dh.class);
    private String c;

    public final /* synthetic */ Object h() {
        return a();
    }

    public dh(JSONObject jSONObject) {
        super(jSONObject);
        this.c = jSONObject.getJSONObject("data").getString("event_name");
    }

    public final boolean a(ed edVar) {
        if (edVar instanceof ec) {
            ec ecVar = (ec) edVar;
            if (!i.c(ecVar.a()) && ecVar.a().equals(this.c)) {
                return super.a(edVar);
            }
        }
        return false;
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        try {
            a.put("type", "custom_event_property");
            JSONObject jSONObject = a.getJSONObject("data");
            jSONObject.put("event_name", this.c);
            a.put("data", jSONObject);
        } catch (Throwable e) {
            c.d(b, "Caught exception creating CustomEventWithPropertiesTriggerCondition Json.", e);
        }
        return a;
    }
}
