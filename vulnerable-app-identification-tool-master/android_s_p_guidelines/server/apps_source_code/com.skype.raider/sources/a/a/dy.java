package a.a;

import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import org.json.JSONException;
import org.json.JSONObject;

public final class dy implements dw {
    private final long a;
    private final long b;
    private final int c;
    private final int d;
    private final int e;
    private final dv f;
    private final int g;

    public final /* synthetic */ Object h() {
        return i();
    }

    public dy(JSONObject jSONObject) {
        this.a = jSONObject.optLong("start_time", -1);
        this.b = jSONObject.optLong("end_time", -1);
        this.c = jSONObject.optInt(EventsEntry.COLUMN_NAME_PRIORITY, 0);
        this.g = jSONObject.optInt("min_seconds_since_last_trigger", -1);
        this.d = jSONObject.optInt("delay", 0);
        this.e = jSONObject.optInt("timeout", -1);
        this.f = new dx(jSONObject);
    }

    public final long a() {
        return this.a;
    }

    public final long b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final int d() {
        return this.d;
    }

    public final int e() {
        return this.e;
    }

    public final dv f() {
        return this.f;
    }

    public final int g() {
        return this.g;
    }

    private JSONObject i() {
        try {
            JSONObject jSONObject = (JSONObject) this.f.h();
            jSONObject.put("start_time", this.a);
            jSONObject.put("end_time", this.b);
            jSONObject.put(EventsEntry.COLUMN_NAME_PRIORITY, this.c);
            jSONObject.put("min_seconds_since_last_trigger", this.g);
            jSONObject.put("timeout", this.e);
            jSONObject.put("delay", this.d);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
