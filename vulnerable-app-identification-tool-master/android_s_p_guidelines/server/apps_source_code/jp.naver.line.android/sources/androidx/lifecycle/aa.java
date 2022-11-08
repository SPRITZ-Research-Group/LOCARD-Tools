package androidx.lifecycle;

import java.util.HashMap;
import java.util.Map;

public final class aa {
    private Map<String, Integer> a = new HashMap();

    public final boolean a(String str, int i) {
        Integer num = (Integer) this.a.get(str);
        int intValue = num != null ? num.intValue() : 0;
        Object obj = (intValue & i) != 0 ? 1 : null;
        this.a.put(str, Integer.valueOf(i | intValue));
        if (obj == null) {
            return true;
        }
        return false;
    }
}
