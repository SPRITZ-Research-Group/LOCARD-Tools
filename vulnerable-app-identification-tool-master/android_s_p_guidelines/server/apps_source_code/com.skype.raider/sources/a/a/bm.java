package a.a;

import a.a.bi.a;
import android.net.Uri;
import com.appboy.f.c;
import java.util.Map;
import org.json.JSONObject;

public final class bm extends bk {
    private static final String b = c.a(bm.class);
    private final bi c;

    public bm(String str) {
        this(str, new a().c());
    }

    public bm(String str, bi biVar) {
        super(Uri.parse(str + "data"));
        this.c = biVar;
        a(biVar);
    }

    public final fu i() {
        return fu.POST;
    }

    public final void a(b bVar, at atVar) {
    }

    public final boolean h() {
        return this.c.b() && super.h();
    }

    public final void a(Map<String, String> map) {
        super.a((Map) map);
        if (!this.c.b()) {
            map.put("X-Braze-DataRequest", "true");
            if (this.c.e()) {
                map.put("X-Braze-FeedRequest", "true");
            }
            if (this.c.d()) {
                map.put("X-Braze-TriggersRequest", "true");
            }
        }
    }

    public final JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            if (!this.c.b()) {
                g.put("respond_with", this.c.a());
            }
            return g;
        } catch (Throwable e) {
            c.c(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }
}
