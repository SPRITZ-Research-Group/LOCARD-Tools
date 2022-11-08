package androidx.lifecycle;

final class q {
    k a;
    h b;

    q(n nVar, k kVar) {
        this.b = s.a((Object) nVar);
        this.a = kVar;
    }

    final void a(o oVar, j jVar) {
        k b = p.b(jVar);
        this.a = p.a(this.a, b);
        this.b.a(oVar, jVar);
        this.a = b;
    }
}
