package androidx.lifecycle;

final class ai implements Runnable {
    final j a;
    private final p b;
    private boolean c = false;

    ai(p pVar, j jVar) {
        this.b = pVar;
        this.a = jVar;
    }

    public final void run() {
        if (!this.c) {
            this.b.a(this.a);
            this.c = true;
        }
    }
}
