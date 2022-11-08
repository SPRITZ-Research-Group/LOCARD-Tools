package a.a;

import com.appboy.f.c;
import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

final class fb implements cc {
    private static final String a = c.a(fb.class);
    private final cc b;

    public fb(cc ccVar) {
        this.b = ccVar;
    }

    public final JSONObject a(URI uri, Map<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject a = this.b.a(uri, map);
            return a;
        } finally {
            c.b(a, "Request Executed in [" + (System.currentTimeMillis() - currentTimeMillis) + "ms] [" + fu.GET.toString() + ":" + uri.toString() + "]");
        }
    }

    public final JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject a = this.b.a(uri, map, jSONObject);
            return a;
        } finally {
            c.b(a, "Request Executed in [" + (System.currentTimeMillis() - currentTimeMillis) + "ms] [" + fu.POST.toString() + ":" + uri.toString() + "]");
        }
    }
}
