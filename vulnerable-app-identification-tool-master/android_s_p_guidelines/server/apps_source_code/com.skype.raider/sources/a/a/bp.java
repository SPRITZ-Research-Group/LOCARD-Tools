package a.a;

import android.net.Uri;
import com.appboy.f.c;
import org.json.JSONObject;

public final class bp extends bk {
    private static final String b = c.a(bp.class);
    private final av c;

    public bp(String str, av avVar) {
        super(Uri.parse(str + "geofence/report"));
        this.c = avVar;
    }

    public final fu i() {
        return fu.POST;
    }

    public final void a(b bVar, at atVar) {
        c.b(b, "GeofenceReportRequest executed successfully.");
    }

    public final boolean h() {
        return false;
    }

    public final JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            if (this.c != null) {
                g.put("geofence_event", this.c.h());
            }
            return g;
        } catch (Throwable e) {
            c.c(b, "Experienced JSONException while creating geofence report request. Returning null.", e);
            return null;
        }
    }
}
