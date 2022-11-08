package a.a;

import android.net.Uri;
import com.appboy.a;
import com.appboy.b.h;
import com.appboy.e.o;
import com.appboy.f.c;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public abstract class bk extends bu implements ax, bq {
    private static final String b = c.a(bk.class);
    private Long c;
    private String d;
    private String e;
    private String f;
    private bg g;
    private String h;
    private h i;
    private bj j;
    private bi k;
    private au l;

    protected bk(Uri uri) {
        super(uri);
    }

    public final Uri a() {
        return a.a(this.a);
    }

    public final void a(String str) {
        this.d = str;
    }

    public final bg c() {
        return this.g;
    }

    public final void a(bg bgVar) {
        this.g = bgVar;
    }

    public final void a(long j) {
        this.c = Long.valueOf(j);
    }

    public final void b(String str) {
        this.e = str;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final void a(h hVar) {
        this.i = hVar;
    }

    public final void d(String str) {
        this.h = str;
    }

    public final bj d() {
        return this.j;
    }

    public final void a(bj bjVar) {
        this.j = bjVar;
    }

    public final void a(bi biVar) {
        this.k = biVar;
    }

    public final bi e() {
        return this.k;
    }

    public final void a(au auVar) {
        this.l = auVar;
    }

    public final au f() {
        return this.l;
    }

    public void a(b bVar, o oVar) {
        c.g(b, "Error occurred while executing Braze request: " + oVar.a());
    }

    public JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.h != null) {
                jSONObject.put("app_version", this.h);
            }
            if (this.d != null) {
                jSONObject.put("device_id", this.d);
            }
            if (this.c != null) {
                jSONObject.put("time", this.c);
            }
            if (this.e != null) {
                jSONObject.put("api_key", this.e);
            }
            if (this.f != null) {
                jSONObject.put("sdk_version", this.f);
            }
            if (!(this.g == null || this.g.b())) {
                jSONObject.put("device", this.g.a());
            }
            if (!(this.j == null || this.j.b())) {
                jSONObject.put("attributes", this.j.c());
            }
            if (!(this.l == null || this.l.b())) {
                jSONObject.put(EventsEntry.TABLE_NAME, cv.a(this.l.a()));
            }
            if (this.i == null) {
                return jSONObject;
            }
            jSONObject.put("sdk_flavor", this.i.a());
            return jSONObject;
        } catch (Throwable e) {
            c.c(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }

    public boolean h() {
        return b();
    }

    public final boolean b() {
        List<ax> arrayList = new ArrayList();
        arrayList.add(this.g);
        arrayList.add(this.j);
        arrayList.add(this.l);
        for (ax axVar : arrayList) {
            if (axVar != null && !axVar.b()) {
                return false;
            }
        }
        return true;
    }

    public final void a(b bVar) {
        if (this.j != null) {
            bVar.a(new h(this.j), h.class);
        }
        if (this.g != null) {
            bVar.a(new e(this.g), e.class);
        }
    }

    public void a(Map<String, String> map) {
        map.put("X-Braze-Api-Key", this.e);
    }
}
