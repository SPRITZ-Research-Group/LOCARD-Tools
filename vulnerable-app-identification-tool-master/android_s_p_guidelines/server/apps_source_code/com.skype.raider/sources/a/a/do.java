package a.a;

import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONObject;

public final class do extends dr {
    private static final String b = c.a(do.class);
    private String c;

    public final /* synthetic */ Object h() {
        return a();
    }

    public do(JSONObject jSONObject) {
        super(jSONObject);
        this.c = jSONObject.getJSONObject("data").getString("product_id");
    }

    public final boolean a(ed edVar) {
        if (edVar instanceof eh) {
            if (i.c(this.c)) {
                return false;
            }
            eh ehVar = (eh) edVar;
            if (!i.c(ehVar.a()) && ehVar.a().equals(this.c)) {
                return super.a(edVar);
            }
        }
        return false;
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        try {
            a.put("type", "purchase_property");
            JSONObject jSONObject = a.getJSONObject("data");
            jSONObject.put("product_id", this.c);
            a.put("data", jSONObject);
        } catch (Throwable e) {
            c.d(b, "Caught exception creating Json.", e);
        }
        return a;
    }
}
