package a.a;

import java.util.concurrent.Executor;

public final class bv implements bs {
    private final a a;
    private final cc b;
    private final b c;
    private final b d;
    private final Executor e;
    private final cf f;
    private final ck g;
    private am h;

    public bv(a aVar, cc ccVar, b bVar, b bVar2, Executor executor, cf cfVar, ck ckVar) {
        this.a = aVar;
        this.b = ccVar;
        this.c = bVar;
        this.d = bVar2;
        this.e = executor;
        this.f = cfVar;
        this.g = ckVar;
    }

    public final void a(am amVar) {
        this.h = amVar;
    }

    public final void a(br brVar) {
        this.e.execute(new bl((bq) brVar, this.b, this.c, this.d, this.f, this.h, this.g));
    }

    public final void b(br brVar) {
        new bl((bq) brVar, this.b, this.c, this.d, this.f, this.h, this.g).run();
    }
}
