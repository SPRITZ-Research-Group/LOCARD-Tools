package a.a;

import com.appboy.e.e;
import com.appboy.f.i;
import org.json.JSONException;
import org.json.JSONObject;

public final class bi implements ax, e<JSONObject> {
    private final String a;
    private final Boolean b;
    private final Boolean c;
    private final bh d;

    public static class a {
        private String a;
        private Boolean b;
        private Boolean c;
        private bh d;

        public final a a(String str) {
            this.a = str;
            return this;
        }

        public final a a() {
            this.b = Boolean.valueOf(true);
            return this;
        }

        public final a b() {
            this.c = Boolean.valueOf(true);
            return this;
        }

        public final a a(bh bhVar) {
            this.d = bhVar;
            return this;
        }

        public final bi c() {
            return new bi(this.a, this.b, this.c, this.d, (byte) 0);
        }
    }

    /* synthetic */ bi(String str, Boolean bool, Boolean bool2, bh bhVar, byte b) {
        this(str, bool, bool2, bhVar);
    }

    public final /* synthetic */ Object h() {
        return a();
    }

    private bi(String str, Boolean bool, Boolean bool2, bh bhVar) {
        this.a = str;
        this.b = bool;
        this.c = bool2;
        this.d = bhVar;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!i.b(this.a)) {
                jSONObject.put("user_id", this.a);
            }
            if (this.b != null) {
                jSONObject.put("feed", this.b);
            }
            if (this.c != null) {
                jSONObject.put("triggers", this.c);
            }
            if (this.d == null) {
                return jSONObject;
            }
            jSONObject.put("config", this.d.a());
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public final boolean c() {
        return this.d != null;
    }

    public final boolean d() {
        return this.c != null;
    }

    public final boolean e() {
        return this.b != null;
    }

    public final boolean f() {
        return !i.b(this.a);
    }

    public final boolean b() {
        JSONObject a = a();
        if (a.length() == 0) {
            return true;
        }
        if (a.length() == 1) {
            return a.has("user_id");
        }
        return false;
    }
}
