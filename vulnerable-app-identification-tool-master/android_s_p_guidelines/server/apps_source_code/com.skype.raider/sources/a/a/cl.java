package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONException;
import org.json.JSONObject;

public class cl implements ch {
    private static final String a = c.a(cl.class);
    private final SharedPreferences b;

    public cl(Context context, String str, String str2) {
        this.b = context.getSharedPreferences("com.appboy.storage.session_storage" + i.a(context, str, str2), 0);
    }

    public final void a(az azVar) {
        String bcVar = azVar.a().toString();
        JSONObject g = azVar.g();
        Editor edit = this.b.edit();
        if (!g.has("end_time")) {
            try {
                g.put("end_time", co.b());
            } catch (JSONException e) {
                c.g(a, "Failed to set end time to now for session json data");
            }
        }
        edit.putString(bcVar, g.toString());
        if (!azVar.d()) {
            edit.putString("current_open_session", bcVar);
        } else if (this.b.getString("current_open_session", "").equals(bcVar)) {
            edit.remove("current_open_session");
        }
        edit.apply();
    }

    public final az a() {
        Throwable e;
        if (this.b.contains("current_open_session")) {
            String string;
            Object jSONObject;
            try {
                string = this.b.getString("current_open_session", "");
                try {
                    jSONObject = new JSONObject(this.b.getString(string, ""));
                    try {
                        return new az(jSONObject);
                    } catch (JSONException e2) {
                        e = e2;
                        c.d(a, "Could not create new mutable session for open session with id: " + string + " and json data: " + jSONObject, e);
                        return null;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    jSONObject = null;
                    c.d(a, "Could not create new mutable session for open session with id: " + string + " and json data: " + jSONObject, e);
                    return null;
                }
            } catch (JSONException e4) {
                e = e4;
                jSONObject = null;
                string = null;
            }
        } else {
            c.b(a, "No stored open session in storage.");
            return null;
        }
    }

    public final void b(az azVar) {
        String string = this.b.getString("current_open_session", null);
        String bcVar = azVar.a().toString();
        Editor edit = this.b.edit();
        edit.remove(bcVar);
        if (bcVar.equals(string)) {
            edit.remove("current_open_session");
        }
        edit.apply();
    }
}
