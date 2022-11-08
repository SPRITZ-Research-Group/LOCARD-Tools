package a.a;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class bx extends bk {
    private static final String b = c.a(bx.class);
    private final long c;
    private final List<String> d;
    private final String e;

    public bx(String str, @NonNull List<String> list, long j, String str2) {
        super(Uri.parse(str + "data"));
        this.c = j;
        this.d = list;
        this.e = str2;
    }

    public final fu i() {
        return fu.POST;
    }

    public final boolean h() {
        return this.d.isEmpty() && super.h();
    }

    public final void a(b bVar, at atVar) {
    }

    public final JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("time", this.c);
            if (!i.c(this.e)) {
                jSONObject.put("user_id", this.e);
            }
            if (!this.d.isEmpty()) {
                jSONObject.put("device_logs", new JSONArray(this.d));
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            g.put("test_user_data", jSONArray);
            return g;
        } catch (Throwable e) {
            c.d(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }
}
