package androidx.work;

import defpackage.po;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class ag<B extends ag, W extends af> {
    boolean a = false;
    UUID b = UUID.randomUUID();
    po c;
    Set<String> d = new HashSet();

    abstract W a();

    ag(Class<? extends ListenableWorker> cls) {
        this.c = new po(this.b.toString(), cls.getName());
        this.d.add(cls.getName());
    }

    public final W b() {
        W a = a();
        this.b = UUID.randomUUID();
        this.c = new po(this.c);
        this.c.a = this.b.toString();
        return a;
    }
}
