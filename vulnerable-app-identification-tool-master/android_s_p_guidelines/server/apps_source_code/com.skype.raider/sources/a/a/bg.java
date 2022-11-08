package a.a;

import com.appboy.e.e;
import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONObject;

public final class bg implements ax, e<JSONObject> {
    private static final String a = c.a(bg.class);
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private String g;
    private final Boolean h;

    public final /* synthetic */ Object h() {
        return a();
    }

    public static bg a(JSONObject jSONObject) {
        Boolean bool = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        for (fr frVar : fr.values()) {
            switch (frVar) {
                case TIMEZONE:
                    str = i.d(jSONObject.optString(frVar.a()));
                    break;
                case CARRIER:
                    str5 = i.d(jSONObject.optString(frVar.a()));
                    break;
                case ANDROID_VERSION:
                    str6 = i.d(jSONObject.optString(frVar.a()));
                    break;
                case RESOLUTION:
                    str2 = i.d(jSONObject.optString(frVar.a()));
                    break;
                case LOCALE:
                    str3 = i.d(jSONObject.optString(frVar.a()));
                    break;
                case MODEL:
                    str4 = i.d(jSONObject.optString(frVar.a()));
                    break;
                case NOTIFICATIONS_ENABLED:
                    if (!jSONObject.has(frVar.a())) {
                        break;
                    }
                    bool = Boolean.valueOf(jSONObject.optBoolean(frVar.a(), true));
                    break;
                default:
                    c.g(a, "Unknown key encountered in Device createFromJson " + frVar);
                    break;
            }
        }
        return new bg(str6, str5, str4, str3, str, str2, bool);
    }

    public bg(String str, String str2, String str3, String str4, String str5, String str6, Boolean bool) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = str5;
        this.f = str6;
        this.h = bool;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(fr.ANDROID_VERSION.a(), this.b);
            jSONObject.putOpt(fr.CARRIER.a(), this.c);
            jSONObject.putOpt(fr.MODEL.a(), this.d);
            jSONObject.putOpt(fr.RESOLUTION.a(), this.f);
            jSONObject.putOpt(fr.LOCALE.a(), this.e);
            jSONObject.putOpt(fr.NOTIFICATIONS_ENABLED.a(), this.h);
            if (!i.c(this.g)) {
                jSONObject.put(fr.TIMEZONE.a(), this.g);
            }
        } catch (Throwable e) {
            c.d(a, "Caught exception creating device Json.", e);
        }
        return jSONObject;
    }

    public final boolean b() {
        return a().length() == 0;
    }
}
