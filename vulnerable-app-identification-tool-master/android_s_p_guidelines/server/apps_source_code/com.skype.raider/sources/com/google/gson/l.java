package com.google.gson;

import com.google.gson.a.g;
import java.util.Map.Entry;
import java.util.Set;

public final class l extends i {
    private final g<String, i> a = new g();

    public final void a(String property, i value) {
        Object value2;
        if (value2 == null) {
            value2 = k.a;
        }
        this.a.put(property, value2);
    }

    public final Set<Entry<String, i>> h() {
        return this.a.entrySet();
    }

    public final boolean equals(Object o) {
        return o == this || ((o instanceof l) && ((l) o).a.equals(this.a));
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
