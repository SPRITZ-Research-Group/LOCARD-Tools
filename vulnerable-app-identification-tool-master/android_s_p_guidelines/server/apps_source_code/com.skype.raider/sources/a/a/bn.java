package a.a;

import android.net.Uri;
import com.appboy.c.d;
import com.appboy.c.e;
import com.appboy.e.b.b;
import com.appboy.e.o;
import com.appboy.f.c;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bn extends bk {
    private static final String b = c.a(bn.class);
    private final b c;

    public bn(String str, b bVar) {
        super(Uri.parse(str + "data"));
        this.c = bVar;
    }

    public final fu i() {
        return fu.POST;
    }

    public final void a(b bVar, at atVar) {
        bVar.a(new e(this.c), e.class);
    }

    public final boolean h() {
        return false;
    }

    public final void a(b bVar, o oVar) {
        super.a(bVar, oVar);
        bVar.a(new d(this.c, oVar), d.class);
    }

    public final JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.c.a());
            g.put("feedback", jSONArray);
            return g;
        } catch (Throwable e) {
            c.c(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }
}
