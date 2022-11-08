package androidx.work;

import defpackage.po;
import java.util.Set;
import java.util.UUID;

public abstract class af {
    private UUID a;
    private po b;
    private Set<String> c;

    protected af(UUID uuid, po poVar, Set<String> set) {
        this.a = uuid;
        this.b = poVar;
        this.c = set;
    }

    public final String a() {
        return this.a.toString();
    }

    public final po b() {
        return this.b;
    }

    public final Set<String> c() {
        return this.c;
    }
}
