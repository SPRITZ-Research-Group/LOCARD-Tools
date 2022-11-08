package a.a;

import android.support.annotation.NonNull;
import com.appboy.b.a.e;
import com.appboy.e.b.a;
import com.appboy.e.n;
import com.appboy.f.c;
import com.appboy.f.i;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public final class be implements av {
    private static final String a = c.a(be.class);
    private final fs b;
    private final JSONObject c;
    private final double d;
    private final String e;
    private String f;
    private bc g;

    public final /* synthetic */ Object h() {
        return k();
    }

    private be(@NonNull fs fsVar, @NonNull JSONObject jSONObject) {
        this(fsVar, jSONObject, co.b());
    }

    private be(@NonNull fs fsVar, @NonNull JSONObject jSONObject, double d) {
        this(fsVar, jSONObject, d, UUID.randomUUID().toString());
    }

    private be(@NonNull fs fsVar, @NonNull JSONObject jSONObject, double d, String str) {
        this.f = null;
        this.g = null;
        if (jSONObject == null) {
            throw new NullPointerException("Event data cannot be null");
        } else if (fsVar.a() == null) {
            throw new NullPointerException("Event type cannot be null");
        } else {
            this.b = fsVar;
            this.c = jSONObject;
            this.d = d;
            this.e = str;
        }
    }

    private be(@NonNull fs fsVar, @NonNull JSONObject jSONObject, double d, String str, String str2, String str3) {
        this.f = null;
        this.g = null;
        if (fsVar.a() == null) {
            throw new NullPointerException("Event type cannot be null");
        }
        this.b = fsVar;
        this.c = jSONObject;
        this.d = d;
        this.e = str;
        this.f = str2;
        if (str3 != null) {
            this.g = bc.a(str3);
        }
    }

    public static be a(String str, a aVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("n", i.a(str));
        if (aVar != null && aVar.a() > 0) {
            jSONObject.put("p", aVar.b());
        }
        return new be(fs.CUSTOM_EVENT, jSONObject);
    }

    public static be a(String str, String str2, BigDecimal bigDecimal, int i, a aVar) {
        BigDecimal a = cw.a(bigDecimal);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", str);
        jSONObject.put("c", str2);
        jSONObject.put("p", a.doubleValue());
        jSONObject.put("q", i);
        if (aVar != null && aVar.a() > 0) {
            jSONObject.put("pr", aVar.b());
        }
        return new be(fs.PURCHASE, jSONObject);
    }

    public static be a(aw awVar) {
        return new be(fs.LOCATION_RECORDED, (JSONObject) awVar.h());
    }

    public static be a(Throwable th, bc bcVar) {
        String b = b(th, bcVar);
        StringBuilder append = new StringBuilder(b).append("\n").append(a(th));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", append.toString());
        return new be(fs.INTERNAL_ERROR, jSONObject);
    }

    public static be a(q qVar, bc bcVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("nop", true);
        String b = b((Throwable) qVar, bcVar);
        jSONObject.put("e", "\n" + a((Throwable) qVar));
        return new be(fs.INTERNAL_ERROR, jSONObject);
    }

    public static be a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        jSONObject.put("a", str2);
        return new be(fs.PUSH_STORY_PAGE_CLICK, jSONObject);
    }

    public static be b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        return new be(fs.PUSH_NOTIFICATION_TRACKING, jSONObject);
    }

    public static be b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        jSONObject.put("a", str2);
        return new be(fs.PUSH_NOTIFICATION_ACTION_TRACKING, jSONObject);
    }

    public static be c(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new be(fs.CARD_IMPRESSION, jSONObject);
    }

    public static be d(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new be(fs.CARD_CLICK, jSONObject);
    }

    public static be c(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("geo_id", str);
        jSONObject.put("event_type", str2);
        return new be(fs.GEOFENCE, jSONObject);
    }

    public static be a(String str, String str2, String str3) {
        return new be(fs.INAPP_MESSAGE_CONTROL_IMPRESSION, d(str, str2, str3));
    }

    public static be b(String str, String str2, String str3) {
        return new be(fs.INAPP_MESSAGE_IMPRESSION, d(str, str2, str3));
    }

    public static be c(String str, String str2, String str3) {
        return new be(fs.INAPP_MESSAGE_CLICK, d(str, str2, str3));
    }

    public static be a(String str, String str2, String str3, n nVar) {
        return new be(fs.INAPP_MESSAGE_BUTTON_CLICK, a(str, str2, str3, a(nVar), null));
    }

    public static be a(String str, String str2, String str3, String str4) {
        return new be(fs.INAPP_MESSAGE_BUTTON_CLICK, a(str, str2, str3, str4, null));
    }

    private static JSONObject d(String str, String str2, String str3) {
        return a(str, str2, str3, null, null);
    }

    private static JSONObject a(String str, String str2, String str3, String str4, e eVar) {
        JSONArray jSONArray;
        JSONObject jSONObject = new JSONObject();
        if (!i.b(str)) {
            jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("campaign_ids", jSONArray);
        }
        if (!i.b(str2)) {
            jSONArray = new JSONArray();
            jSONArray.put(str2);
            jSONObject.put("card_ids", jSONArray);
        }
        if (!i.b(str3)) {
            jSONArray = new JSONArray();
            jSONArray.put(str3);
            jSONObject.put("trigger_ids", jSONArray);
        }
        if (!i.b(str4)) {
            jSONObject.put("bid", str4);
        }
        if (eVar != null) {
            String a = eVar.a();
            if (!i.b(a)) {
                jSONObject.put("error_code", a);
            }
        }
        return jSONObject;
    }

    public static String a(n nVar) {
        if (nVar != null) {
            return String.valueOf(nVar.a());
        }
        return null;
    }

    public static be g() {
        return g("feed_displayed");
    }

    public static be i() {
        return g("feedback_displayed");
    }

    private static be g(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("n", str);
        return new be(fs.INTERNAL, jSONObject);
    }

    public static be e(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PropertiesEntry.COLUMN_NAME_KEY, str);
        jSONObject.put(PropertiesEntry.COLUMN_NAME_VALUE, 1);
        return new be(fs.INCREMENT, jSONObject);
    }

    public static be d(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PropertiesEntry.COLUMN_NAME_KEY, str);
        jSONObject.put(PropertiesEntry.COLUMN_NAME_VALUE, str2);
        return new be(fs.ADD_TO_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static be e(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PropertiesEntry.COLUMN_NAME_KEY, str);
        jSONObject.put(PropertiesEntry.COLUMN_NAME_VALUE, str2);
        return new be(fs.REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static be a(String str, String[] strArr) {
        JSONArray jSONArray = strArr == null ? null : new JSONArray();
        if (strArr != null) {
            for (Object put : strArr) {
                jSONArray.put(put);
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PropertiesEntry.COLUMN_NAME_KEY, str);
        if (strArr == null) {
            jSONObject.put(PropertiesEntry.COLUMN_NAME_VALUE, JSONObject.NULL);
        } else {
            jSONObject.put(PropertiesEntry.COLUMN_NAME_VALUE, jSONArray);
        }
        return new be(fs.SET_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static be j() {
        return new be(fs.SESSION_START, new JSONObject());
    }

    public static be a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("d", j);
        return new be(fs.SESSION_END, jSONObject);
    }

    public static be f(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        return new be(fs.PUSH_DELIVERY, jSONObject);
    }

    public static be a(String str, String str2, double d, String str3, String str4, String str5) {
        fs a = fs.a(str);
        if (a != null) {
            return new be(a, new JSONObject(str2), d, str3, str4, str5);
        }
        throw new IllegalArgumentException("Cannot parse eventType " + str);
    }

    public final fs b() {
        return this.b;
    }

    public final JSONObject c() {
        return this.c;
    }

    public final double a() {
        return this.d;
    }

    private static String b(Throwable th, bc bcVar) {
        StringBuilder stringBuilder = new StringBuilder();
        String th2 = th.toString();
        if (th2.length() > 5000) {
            th2 = th2.substring(0, 5000);
        }
        stringBuilder.append("exception_class: ").append(th2).append(",");
        stringBuilder.append("session_id: ").append(bcVar != null ? bcVar.toString() : null);
        return stringBuilder.toString();
    }

    private static String a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        if (obj.length() > 5000) {
            return obj.substring(0, 5000);
        }
        return obj;
    }

    public final void a(bc bcVar) {
        if (this.g == null) {
            this.g = bcVar;
        } else {
            c.b(a, "Session id can only be set once. Doing nothing. Given session id: " + bcVar);
        }
    }

    public final void a(String str) {
        if (this.f == null) {
            this.f = str;
        } else {
            c.b(a, "User id can only be set once. Doing nothing. Given user id: " + str);
        }
    }

    public final bc f() {
        return this.g;
    }

    public final String e() {
        return this.f;
    }

    public final String d() {
        return this.e;
    }

    private JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.b.a());
            jSONObject.put("data", this.c);
            jSONObject.put("time", this.d);
            if (!i.b(this.f)) {
                jSONObject.put("user_id", this.f);
            }
            if (this.g != null) {
                jSONObject.put("session_id", this.g.a());
            }
        } catch (Throwable e) {
            c.d(a, "Caught exception creating Braze event Json.", e);
        }
        return jSONObject;
    }

    public final String toString() {
        JSONObject k = k();
        if (k.length() > 0) {
            return k.toString();
        }
        return "";
    }

    public static be a(String str, String str2, String str3, e eVar) {
        return new be(fs.INAPP_MESSAGE_DISPLAY_FAILURE, a(str, str2, str3, null, eVar));
    }
}
