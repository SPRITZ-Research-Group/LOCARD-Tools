package a.a;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ez {
    private boolean a;
    private StringBuilder b;
    private String c;
    private Object d;
    private Object e;
    private final List<ew> f;
    private final List<ew> g;
    private final List<ew> h;

    public ez() {
        this((byte) 0);
    }

    private ez(byte b) {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.a = true;
        this.b = new StringBuilder("");
    }

    public final boolean a() {
        return this.a;
    }

    public final boolean b() {
        return !this.a;
    }

    public final String c() {
        return this.b.toString();
    }

    public final void a(String str) {
        this.a = false;
        if (this.b.length() == 0) {
            this.b.append(str);
        } else {
            this.b.append(" ; ").append(str);
        }
    }

    public final ez a(String str, Object obj, Object obj2) {
        this.f.add(new ew(str, obj, obj2));
        this.c = str;
        this.d = obj;
        this.e = obj2;
        a(str + "\nExpected: " + a(obj) + "\n     got: " + a(obj2) + "\n");
        return this;
    }

    public final ez a(String str, Object obj) {
        this.g.add(new ew(str, obj, null));
        a(str + "\nExpected: " + a(obj) + "\n     but none found\n");
        return this;
    }

    public final ez b(String str, Object obj) {
        this.h.add(new ew(str, null, obj));
        a(str + "\nUnexpected: " + a(obj) + "\n");
        return this;
    }

    private static String a(Object obj) {
        if (obj instanceof JSONArray) {
            return "a JSON array";
        }
        if (obj instanceof JSONObject) {
            return "a JSON object";
        }
        return obj.toString();
    }

    public final String toString() {
        return this.b.toString();
    }
}
