package com.facebook.imagepipeline.producers;

import com.facebook.common.b.h;
import java.util.Map;

public abstract class ar<T> extends h<T> {
    private final Consumer<T> b;
    private final am c;
    private final String d;
    private final String e;

    protected abstract void b(T t);

    public ar(Consumer<T> consumer, am producerListener, String producerName, String requestId) {
        this.b = consumer;
        this.c = producerListener;
        this.d = producerName;
        this.e = requestId;
        this.c.a(this.e, this.d);
    }

    protected void a(T result) {
        this.c.a(this.e, this.d, this.c.b(this.e) ? c(result) : null);
        this.b.b(result, 1);
    }

    protected void a(Exception e) {
        am amVar = this.c;
        String str = this.e;
        String str2 = this.d;
        this.c.b(this.e);
        amVar.a(str, str2, e, null);
        this.b.b((Throwable) e);
    }

    protected void b() {
        am amVar = this.c;
        String str = this.e;
        String str2 = this.d;
        this.c.b(this.e);
        amVar.b(str, str2, null);
        this.b.b();
    }

    protected Map<String, String> c(T t) {
        return null;
    }
}
