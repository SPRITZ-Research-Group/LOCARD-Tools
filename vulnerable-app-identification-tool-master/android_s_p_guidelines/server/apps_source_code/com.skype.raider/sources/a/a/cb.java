package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class cb extends by<bg> {
    private static final String b = c.a(cb.class);
    @VisibleForTesting
    final SharedPreferences a;
    private bg c;

    @VisibleForTesting
    @NonNull
    final /* synthetic */ Object a() {
        return d();
    }

    final /* synthetic */ void a(Object obj, boolean z) {
        bg bgVar = (bg) obj;
        if (z && bgVar != null) {
            try {
                JSONObject jSONObject = new JSONObject(this.a.getString("cached_device", "{}"));
                JSONObject a = bgVar.a();
                Editor edit = this.a.edit();
                edit.putString("cached_device", cv.a(jSONObject, a).toString());
                edit.apply();
            } catch (Throwable e) {
                c.a(b, "Caught exception confirming and unlocking device cache.", e);
            }
        }
    }

    public cb(Context context) {
        this(context, null, null);
    }

    public cb(Context context, String str, String str2) {
        this.c = null;
        this.a = context.getSharedPreferences("com.appboy.storage.device_cache.v3" + i.a(context, str, str2), 0);
    }

    @VisibleForTesting
    @NonNull
    private bg d() {
        JSONObject a = this.c.a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = new JSONObject(this.a.getString("cached_device", "{}"));
        } catch (Throwable e) {
            c.d(b, "Caught exception confirming and unlocking Json objects.", e);
        }
        JSONObject jSONObject2 = new JSONObject();
        Iterator keys = a.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = a.opt(str);
            Object opt2 = jSONObject.opt(str);
            if ((opt instanceof JSONObject) || (opt instanceof JSONArray)) {
                if (opt2 != null) {
                    try {
                        if (!ex.a(String.valueOf(opt), String.valueOf(opt2), ey.NON_EXTENSIBLE).b()) {
                        }
                    } catch (Throwable e2) {
                        c.a(b, "Caught json exception creating dirty outbound device on a jsonObject value. Returning the whole device.", e2);
                        return this.c;
                    }
                }
                jSONObject2.put(str, opt);
            } else if (opt.equals(opt2)) {
                continue;
            } else {
                try {
                    jSONObject2.put(str, opt);
                } catch (Throwable e22) {
                    c.d(b, "Caught json exception creating dirty outbound device. Returning the whole device.", e22);
                    return this.c;
                }
            }
        }
        try {
            return bg.a(jSONObject2);
        } catch (Throwable e222) {
            c.a(b, "Caught json exception creating device from json. Returning the whole device.", e222);
            return this.c;
        }
    }

    public final void a(bg bgVar) {
        this.c = bgVar;
    }
}
