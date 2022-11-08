package androidx.media;

public class g<T> {
    private final Object a;
    private boolean b;
    private boolean c;
    private boolean d;
    private int e;

    void a() {
    }

    g(Object obj) {
        this.a = obj;
    }

    public final void c() {
        if (this.c || this.d) {
            StringBuilder stringBuilder = new StringBuilder("sendResult() called when either sendResult() or sendError() had already been called for: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.c = true;
        a();
    }

    public final void d() {
        if (this.c || this.d) {
            StringBuilder stringBuilder = new StringBuilder("sendError() called when either sendResult() or sendError() had already been called for: ");
            stringBuilder.append(this.a);
            throw new IllegalStateException(stringBuilder.toString());
        }
        this.d = true;
        b();
    }

    final boolean e() {
        return this.b || this.c || this.d;
    }

    final void a(int i) {
        this.e = i;
    }

    final int f() {
        return this.e;
    }

    void b() {
        StringBuilder stringBuilder = new StringBuilder("It is not supported to send an error for ");
        stringBuilder.append(this.a);
        throw new UnsupportedOperationException(stringBuilder.toString());
    }
}
