package a.a;

import a.a.bi.a;
import android.net.Uri;
import android.support.annotation.VisibleForTesting;
import com.appboy.b.a.e;
import com.appboy.e.o;
import com.appboy.f.c;
import com.appboy.f.i;
import org.json.JSONObject;

public class bw extends bk {
    private static final String b = c.a(bw.class);
    private final String c;
    private final long d;
    private final String e;
    private final ed f;
    private final bi g;
    private final am h;

    public bw(String str, de deVar, ed edVar, am amVar, String str2) {
        super(Uri.parse(str + "template"));
        this.c = deVar.g();
        this.d = deVar.f();
        this.e = deVar.i();
        this.f = edVar;
        this.g = new a().a(str2).c();
        this.h = amVar;
    }

    public final fu i() {
        return fu.POST;
    }

    public final void a(b bVar, at atVar) {
        if (atVar == null || !atVar.b()) {
            k();
        } else if (!i.c(this.e)) {
            atVar.h().a(this.e);
        }
    }

    public final void a(b bVar, o oVar) {
        super.a(bVar, oVar);
        k();
    }

    public final boolean h() {
        return false;
    }

    public final long j() {
        return this.d;
    }

    public final JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trigger_id", this.c);
            jSONObject.put("trigger_event_type", this.f.b());
            if (this.f.e() != null) {
                jSONObject.put("data", this.f.e().h());
            }
            g.put("template", jSONObject);
            if (this.g.f()) {
                g.put("respond_with", this.g.a());
            }
            return g;
        } catch (Throwable e) {
            c.c(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }

    @VisibleForTesting
    private void k() {
        c.d(b, "Template request failed. Attempting to log in-app message template request failure.");
        if (i.c(this.c)) {
            c.b(b, "Trigger ID not found. Not logging in-app message template request failure.");
        } else if (this.h == null) {
            c.g(b, "Cannot log an in-app message template request failure because the IAppboyManager is null.");
        } else {
            try {
                this.h.a(be.a(null, null, this.c, e.TEMPLATE_REQUEST));
            } catch (Throwable e) {
                this.h.a(e);
            }
        }
    }
}
