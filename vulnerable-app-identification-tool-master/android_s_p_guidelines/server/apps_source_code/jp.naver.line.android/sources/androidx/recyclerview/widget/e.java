package androidx.recyclerview.widget;

import java.util.concurrent.Executor;

public final class e<T> {
    private final Executor a;
    private final Executor b;
    private final s<T> c;

    e(Executor executor, Executor executor2, s<T> sVar) {
        this.a = executor;
        this.b = executor2;
        this.c = sVar;
    }

    public final Executor a() {
        return this.a;
    }

    public final Executor b() {
        return this.b;
    }

    public final s<T> c() {
        return this.c;
    }
}
