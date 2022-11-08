package com.facebook.datasource;

public abstract class b<T> implements e<T> {
    protected abstract void e(c<T> cVar);

    protected abstract void f(c<T> cVar);

    public final void a(c<T> dataSource) {
        boolean shouldClose = dataSource.b();
        try {
            e(dataSource);
        } finally {
            if (shouldClose) {
                dataSource.h();
            }
        }
    }

    public final void b(c<T> dataSource) {
        try {
            f(dataSource);
        } finally {
            dataSource.h();
        }
    }

    public final void c(c<T> cVar) {
    }

    public void d(c<T> cVar) {
    }
}
