package a.a;

import com.appboy.c.a;
import com.appboy.e.o;
import com.appboy.f.c;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class bl implements Runnable {
    private static final String a = c.a(bl.class);
    private final bq b;
    private final b c;
    private final b d;
    private final Map<String, String> e;
    private final cc f;
    private final cf g;
    private final ck h;
    private final am i;

    public bl(bq bqVar, cc ccVar, b bVar, b bVar2, cf cfVar, am amVar, ck ckVar) {
        this.b = bqVar;
        this.c = bVar;
        this.d = bVar2;
        Map hashMap = new HashMap();
        hashMap.put("Accept-Encoding", "gzip, deflate");
        hashMap.put("Content-Type", "application/json");
        this.e = hashMap;
        this.b.a(this.e);
        this.f = ccVar;
        this.g = cfVar;
        this.i = amVar;
        this.h = ckVar;
    }

    public final void run() {
        try {
            at atVar;
            URI a = ct.a(this.b.a());
            switch (this.b.i()) {
                case GET:
                    atVar = new at(this.f.a(a, this.e), this.i);
                    break;
                case POST:
                    JSONObject g = this.b.g();
                    if (g != null) {
                        atVar = new at(this.f.a(a, this.e, g), this.i);
                        break;
                    }
                    c.g(a, "Could not parse request parameters for put request to [%s], canceling request.");
                    atVar = null;
                    break;
                default:
                    c.f(a, "Received a request with an unknown Http verb: [" + this.b.i() + "]");
                    atVar = null;
                    break;
            }
            if (atVar != null) {
                if (atVar.e()) {
                    c.g(a, "Received server error from request: " + atVar.l().a());
                    this.b.a(this.d, atVar.l());
                } else {
                    this.b.a(this.d, atVar);
                }
                String d = this.i.d();
                if (atVar.a()) {
                    try {
                        a a2 = this.g.a(atVar.g(), d);
                        if (a2 != null) {
                            this.d.a(a2, a.class);
                        }
                    } catch (JSONException e) {
                        c.f(a, "Unable to update/publish feed.");
                    }
                }
                if (atVar.c()) {
                    this.h.a(atVar.i());
                    this.c.a(new i(atVar.i()), i.class);
                }
                if (atVar.d()) {
                    this.c.a(new p(atVar.j()), p.class);
                }
                if (atVar.b() && (this.b instanceof bw)) {
                    atVar.h().a(((bw) this.b).j());
                    this.d.a(new com.appboy.c.c(atVar.h(), d), com.appboy.c.c.class);
                }
                if (atVar.f()) {
                    this.c.a(new f(atVar.k()), f.class);
                }
                this.b.a(this.c);
                this.c.a(new d(this.b), d.class);
                return;
            }
            c.f(a, "Api response was null, failing task.");
            this.b.a(this.d, new o("An error occurred during request processing, resulting in no valid response being received. Check the error log for more details."));
            this.c.a(new c(this.b), c.class);
        } catch (Throwable e2) {
            c.c(a, "Experienced exception processing API response. Failing task.", e2);
        }
    }
}
