package a.a;

import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class de extends df implements dc {
    private static final String a = c.a(de.class);
    private am b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g = -1;

    public final /* synthetic */ Object h() {
        return e();
    }

    public de(JSONObject jSONObject, am amVar) {
        super(jSONObject);
        c.b(a, "Parsing templated triggered action with JSON: " + cv.a(jSONObject));
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        this.c = jSONObject2.getString("trigger_id");
        JSONArray optJSONArray = jSONObject2.optJSONArray("prefetch_image_urls");
        if (optJSONArray != null) {
            this.d = optJSONArray.getString(0);
        }
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("prefetch_zip_urls");
        if (optJSONArray2 != null) {
            this.e = optJSONArray2.getString(0);
        }
        this.b = amVar;
    }

    public final et d() {
        if (!i.c(this.d)) {
            return new et(ea.IMAGE, this.d);
        }
        if (i.c(this.e)) {
            return null;
        }
        return new et(ea.ZIP, this.e);
    }

    public final void a(String str) {
        this.f = str;
    }

    public final void a(b bVar, ed edVar, long j) {
        if (this.b != null) {
            this.g = j;
            c.b(a, "Posting templating request after delay of " + c().d() + " seconds.");
            this.b.a(this, edVar);
        }
    }

    public final long f() {
        return this.g;
    }

    public final String g() {
        return this.c;
    }

    public final String i() {
        return this.f;
    }

    public final JSONObject e() {
        try {
            JSONObject e = super.e();
            e.put("type", "templated_iam");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("trigger_id", this.c);
            JSONArray jSONArray = new JSONArray();
            if (!i.c(this.d)) {
                jSONArray.put(this.d);
                jSONObject.put("prefetch_image_urls", jSONArray);
            }
            jSONArray = new JSONArray();
            if (!i.c(this.e)) {
                jSONArray.put(this.e);
                jSONObject.put("prefetch_zip_urls", jSONArray);
            }
            e.put("data", jSONObject);
            return e;
        } catch (JSONException e2) {
            return null;
        }
    }
}
