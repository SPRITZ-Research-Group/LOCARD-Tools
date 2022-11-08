package a.a;

import com.appboy.f.c;
import java.util.List;
import org.json.JSONArray;

public abstract class dt implements di {
    private static final String b = c.a(dt.class);
    protected List<di> a;

    public final /* synthetic */ Object h() {
        return a();
    }

    protected dt(List<di> list) {
        this.a = list;
    }

    public final JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (di h : this.a) {
                jSONArray.put(h.h());
            }
        } catch (Throwable e) {
            c.d(b, "Caught exception creating Json.", e);
        }
        return jSONArray;
    }
}
