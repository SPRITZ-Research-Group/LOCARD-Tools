package b;

public final class i<TResult> {
    private final h<TResult> a = new h();

    public final h<TResult> a() {
        return this.a;
    }

    public final void b() {
        if (!this.a.f()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public final void a(TResult result) {
        if (!this.a.b((Object) result)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public final void a(Exception error) {
        if (!this.a.b(error)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
