package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appboy.b.e;
import com.appboy.b.f;
import com.appboy.b.g;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.f.j;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class cm extends by<bj> {
    private static final String c = c.a(cm.class);
    @VisibleForTesting
    final SharedPreferences a;
    @VisibleForTesting
    final SharedPreferences b;
    private final aq d;
    private final ck e;
    private final String f;
    private final cj g;

    @NonNull
    public final /* synthetic */ Object a() {
        return e();
    }

    final /* synthetic */ void a(Object obj, boolean z) {
        bj bjVar = (bj) obj;
        if (bjVar == null || bjVar.a() == null) {
            c.b(c, "Tried to confirm with a null outbound user. Doing nothing.");
            return;
        }
        JSONObject a = bjVar.a();
        if (!z) {
            JSONObject f = f();
            JSONObject a2 = cv.a(a, f);
            a2.remove("push_token");
            f = f.optJSONObject("custom");
            a = a.optJSONObject("custom");
            if (f != null && a != null) {
                try {
                    a2.put("custom", cv.a(a, f));
                } catch (Throwable e) {
                    c.c(c, "Failed to add merged custom attributes back to user object.", e);
                }
            } else if (f != null) {
                a2.put("custom", f);
            } else if (a != null) {
                a2.put("custom", a);
            }
            Editor edit = this.a.edit();
            edit.putString("user_cache_attributes_object", a2.toString());
            edit.apply();
        } else if (a.has("push_token")) {
            Editor edit2 = this.b.edit();
            edit2.putString("push_token", a.optString("push_token"));
            edit2.apply();
        }
    }

    public cm(Context context, aq aqVar, ck ckVar, cj cjVar) {
        this(context, null, null, aqVar, ckVar, cjVar);
    }

    public cm(Context context, String str, String str2, aq aqVar, ck ckVar, cj cjVar) {
        String a = i.a(context, str, str2);
        this.a = context.getSharedPreferences("com.appboy.storage.user_cache.v3" + a, 0);
        this.b = context.getSharedPreferences("com.appboy.storage.user_cache.push_token_store" + a, 0);
        this.d = aqVar;
        this.e = ckVar;
        this.f = str;
        this.g = cjVar;
    }

    public final synchronized void a(String str) {
        b("user_id", str);
    }

    public final synchronized void b(String str) {
        b("first_name", str);
    }

    public final synchronized void c(String str) {
        b("last_name", str);
    }

    public final synchronized boolean d(String str) {
        boolean b;
        if (str != null) {
            str = str.trim();
        }
        if (str == null || j.a(str)) {
            b = b("email", str);
        } else {
            c.f(c, "Email address is not valid: " + str);
            b = false;
        }
        return b;
    }

    public final synchronized void a(e eVar) {
        if (eVar == null) {
            b("gender", null);
        } else {
            b("gender", eVar.a());
        }
    }

    public final synchronized boolean a(int i, f fVar, int i2) {
        boolean z;
        if (fVar == null) {
            c.f(c, "Month cannot be null.");
            z = false;
        } else {
            z = b("dob", co.a(co.a(i, fVar.a(), i2), fq.SHORT));
        }
        return z;
    }

    public final synchronized void e(String str) {
        b("country", str);
    }

    public final synchronized void f(String str) {
        b("home_city", str);
    }

    public final synchronized void g(String str) {
        b("language", str);
    }

    public final synchronized void a(g gVar) {
        if (gVar == null) {
            b("email_subscribe", null);
        } else {
            b("email_subscribe", gVar.a());
        }
    }

    public final synchronized void b(g gVar) {
        if (gVar == null) {
            b("push_subscribe", null);
        } else {
            b("push_subscribe", gVar.a());
        }
    }

    public final synchronized boolean h(String str) {
        boolean b;
        if (str != null) {
            str = str.trim();
        }
        if (str == null || j.b(str)) {
            b = b("phone", str);
        } else {
            c.f(c, "Phone number contains invalid characters (allowed are digits, spaces, or any of the following +.-()): " + str);
            b = false;
        }
        return b;
    }

    public final synchronized boolean a(String str, Object obj) {
        boolean z = false;
        synchronized (this) {
            if (com.appboy.f.e.a(str, this.e.m())) {
                String c = j.c(str);
                if ((obj instanceof Boolean) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Long) || (obj instanceof Double)) {
                    z = c(c, obj);
                } else if (obj instanceof String) {
                    z = c(c, j.c((String) obj));
                } else if (obj instanceof Date) {
                    z = c(c, co.a((Date) obj, fq.LONG));
                } else if (obj instanceof String[]) {
                    z = c(c, cv.a((String[]) obj));
                } else {
                    c.f(c, "Could not add unsupported custom attribute type with key: " + c + " and value: " + obj);
                }
            } else {
                c.f(c, "Custom attribute key cannot be null.");
            }
        }
        return z;
    }

    public final synchronized void d() {
        c.a(c, "Push token cache cleared.");
        this.b.edit().clear().apply();
    }

    @NonNull
    private bj e() {
        if (!i.b(this.f)) {
            a(this.f);
        }
        JSONObject f = f();
        try {
            String a = this.d.a();
            if (a == null) {
                c.b(c, "Cannot add null push token to attributes object.");
            } else {
                String string = this.b.getString("push_token", null);
                if (string == null || !a.equals(string)) {
                    f.put("push_token", a);
                }
            }
        } catch (Throwable e) {
            c.d(c, "Couldn't add push token to outbound json", e);
        }
        this.a.edit().clear().apply();
        return new bj(f);
    }

    @VisibleForTesting
    private JSONObject f() {
        String string = this.a.getString("user_cache_attributes_object", null);
        if (string == null) {
            return new JSONObject();
        }
        try {
            return new JSONObject(string);
        } catch (Throwable e) {
            c.d(c, "Failed to load user object json from prefs with json string: " + string, e);
            return new JSONObject();
        }
    }

    private JSONObject g() {
        JSONObject f = f();
        if (f.has("custom")) {
            try {
                f = f.getJSONObject("custom");
            } catch (Throwable e) {
                c.d(c, "Could not create custom attributes json object from preferences.", e);
            }
            if (f != null) {
                return new JSONObject();
            }
            return f;
        }
        f = null;
        if (f != null) {
            return f;
        }
        return new JSONObject();
    }

    private boolean b(String str, Object obj) {
        JSONObject f = f();
        if (obj == null) {
            try {
                f.put(str, JSONObject.NULL);
            } catch (JSONException e) {
                c.f(c, "Failed to write to user object json from prefs with key: [" + str + "] value: [" + obj + "] ");
                return false;
            }
        }
        f.put(str, obj);
        if (this.g.a()) {
            c.f(c, "SDK is disabled. Not writing to user cache.");
            return false;
        }
        Editor edit = this.a.edit();
        edit.putString("user_cache_attributes_object", f.toString());
        edit.apply();
        return true;
    }

    @VisibleForTesting
    private boolean c(String str, Object obj) {
        JSONObject g = g();
        if (obj == null) {
            try {
                g.put(str, JSONObject.NULL);
            } catch (Throwable e) {
                c.c(c, "Could not write to custom attributes json object with key: [" + str + "] value: [" + obj + "] ", e);
                return false;
            }
        }
        g.put(str, obj);
        return b("custom", g);
    }
}
