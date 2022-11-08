package androidx.recyclerview.widget;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class f<T> {
    private static final Object d = new Object();
    private static Executor e = null;
    private Executor a;
    private Executor b;
    private final s<T> c;

    public f(s<T> sVar) {
        this.c = sVar;
    }

    public final e<T> a() {
        if (this.b == null) {
            synchronized (d) {
                if (e == null) {
                    e = Executors.newFixedThreadPool(2);
                }
            }
            this.b = e;
        }
        return new e(this.a, this.b, this.c);
    }
}
