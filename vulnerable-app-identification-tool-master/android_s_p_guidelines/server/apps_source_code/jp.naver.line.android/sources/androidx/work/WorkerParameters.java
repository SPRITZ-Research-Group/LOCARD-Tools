package androidx.work;

import defpackage.qi;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

public final class WorkerParameters {
    private UUID a;
    private h b;
    private Set<String> c;
    private ai d;
    private int e;
    private Executor f;
    private qi g;
    private ah h;

    public WorkerParameters(UUID uuid, h hVar, Collection<String> collection, ai aiVar, int i, Executor executor, qi qiVar, ah ahVar) {
        this.a = uuid;
        this.b = hVar;
        this.c = new HashSet(collection);
        this.d = aiVar;
        this.e = i;
        this.f = executor;
        this.g = qiVar;
        this.h = ahVar;
    }

    public final UUID a() {
        return this.a;
    }

    public final h b() {
        return this.b;
    }

    public final Executor c() {
        return this.f;
    }
}
