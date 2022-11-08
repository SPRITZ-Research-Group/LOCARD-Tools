package androidx.work;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class i {
    private Map<String, Object> a = new HashMap();

    public final i a(String str, String str2) {
        this.a.put(str, str2);
        return this;
    }

    public final i a(h hVar) {
        a(hVar.b);
        return this;
    }

    public final i a(Map<String, Object> map) {
        for (Entry entry : map.entrySet()) {
            a((String) entry.getKey(), entry.getValue());
        }
        return this;
    }

    private i a(String str, Object obj) {
        if (obj == null) {
            this.a.put(str, null);
        } else {
            Class cls = obj.getClass();
            if (cls == Boolean.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                this.a.put(str, obj);
            } else if (cls == boolean[].class) {
                this.a.put(str, h.a((boolean[]) obj));
            } else if (cls == int[].class) {
                this.a.put(str, h.a((int[]) obj));
            } else if (cls == long[].class) {
                this.a.put(str, h.a((long[]) obj));
            } else if (cls == float[].class) {
                this.a.put(str, h.a((float[]) obj));
            } else if (cls == double[].class) {
                this.a.put(str, h.a((double[]) obj));
            } else {
                throw new IllegalArgumentException(String.format("Key %s has invalid type %s", new Object[]{str, cls}));
            }
        }
        return this;
    }

    public final h a() {
        h hVar = new h(this.a);
        h.a(hVar);
        return hVar;
    }
}
