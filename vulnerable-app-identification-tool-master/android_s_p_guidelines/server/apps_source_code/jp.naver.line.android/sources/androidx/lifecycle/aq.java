package androidx.lifecycle;

import java.util.HashMap;

public final class aq {
    private final HashMap<String, ak> a = new HashMap();

    final void a(String str, ak akVar) {
        ak akVar2 = (ak) this.a.put(str, akVar);
        if (akVar2 != null) {
            akVar2.ak_();
        }
    }

    final ak a(String str) {
        return (ak) this.a.get(str);
    }

    public final void a() {
        for (ak ak_ : this.a.values()) {
            ak_.ak_();
        }
        this.a.clear();
    }
}
