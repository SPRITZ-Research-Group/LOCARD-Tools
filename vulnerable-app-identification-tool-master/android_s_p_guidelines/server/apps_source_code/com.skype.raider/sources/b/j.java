package b;

final class j {
    private h<?> a;

    public j(h<?> task) {
        this.a = task;
    }

    protected final void finalize() throws Throwable {
        try {
            h faultedTask = this.a;
            if (!(faultedTask == null || h.a() == null)) {
                k kVar = new k(faultedTask.e());
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final void a() {
        this.a = null;
    }
}
