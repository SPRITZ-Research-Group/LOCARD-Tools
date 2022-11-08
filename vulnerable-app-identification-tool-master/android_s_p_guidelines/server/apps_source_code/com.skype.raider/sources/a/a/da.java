package a.a;

import com.appboy.f.c;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

final class da implements cc {
    private static final String a = c.a(da.class);
    private final cc b;

    public da(cc ccVar) {
        this.b = ccVar;
    }

    private static void a(JSONObject jSONObject) {
        String str;
        if (jSONObject == null) {
            try {
                str = "none";
            } catch (Throwable e) {
                c.a(a, "Exception while logging result: ", e);
                return;
            }
        }
        str = cv.a(jSONObject);
        c.b(a, "Result [" + str + "]");
    }

    private static String a(Map<String, String> map) {
        List<String> arrayList = new ArrayList();
        for (Entry entry : map.entrySet()) {
            arrayList.add("(" + ((String) entry.getKey()) + " / " + ((String) entry.getValue()) + ")");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    public final JSONObject a(URI uri, Map<String, String> map) {
        try {
            c.b(a, "Making request to [" + uri.toString() + "], with headers: [" + a((Map) map) + "]");
        } catch (Throwable e) {
            c.a(a, "Exception while logging request: ", e);
        }
        JSONObject a = this.b.a(uri, map);
        a(a);
        return a;
    }

    public final JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        try {
            c.b(a, "Making request to [" + uri.toString() + "], with headers: [" + a((Map) map) + "] and JSON parameters: [" + cv.a(jSONObject) + "]");
        } catch (Throwable e) {
            c.a(a, "Exception while logging request: ", e);
        }
        JSONObject a = this.b.a(uri, map, jSONObject);
        a(a);
        return a;
    }
}
