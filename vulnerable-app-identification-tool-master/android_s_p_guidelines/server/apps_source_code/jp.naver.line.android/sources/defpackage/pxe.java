package defpackage;

/* renamed from: pxe */
final class pxe implements Runnable {
    final /* synthetic */ pxd a;
    private final pxf b;

    pxe(pxd pxd, pxf pxf) {
        this.a = pxd;
        this.b = pxf;
    }

    public final void run() {
        pas.c(this.b.b, this.a.a(this.b));
    }
}
