package a.a;

import android.net.Uri;
import com.appboy.f.c;
import org.json.JSONObject;

public final class bo extends bk {
    private static final String b = c.a(bo.class);
    private final av c;

    public bo(String str, aw awVar) {
        super(Uri.parse(str + "geofence/request"));
        this.c = be.a(awVar);
    }

    public final fu i() {
        return fu.POST;
    }

    public final void a(b bVar, at atVar) {
        c.b(b, "GeofenceRefreshRequest executed successfully.");
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
                g.put("location_event", this.c.h());
            }
            return g;
        } catch (Throwable e) {
            c.c(b, "Experienced JSONException while creating geofence refresh request. Returning null.", e);
            return null;
        }
    }
}
