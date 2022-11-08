package com.facebook.datasource;

import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class f<T> implements j<c<T>> {
    private final List<j<c<T>>> a;

    @ThreadSafe
    private class a extends a<T> {
        final /* synthetic */ f a;
        private int b = 0;
        private c<T> c = null;
        private c<T> d = null;

        private class a implements e<T> {
            final /* synthetic */ a a;

            private a(a aVar) {
                this.a = aVar;
            }

            /* synthetic */ a(a x0, byte b) {
                this(x0);
            }

            public final void b(c<T> dataSource) {
                a.a(this.a, dataSource);
            }

            public final void c(c<T> cVar) {
            }

            public final void a(c<T> dataSource) {
                if (dataSource.c()) {
                    a.b(this.a, dataSource);
                } else if (dataSource.b()) {
                    a.a(this.a, dataSource);
                }
            }

            public final void d(c<T> dataSource) {
                this.a.a(Math.max(this.a.g(), dataSource.g()));
            }
        }

        static /* synthetic */ void b(a x0, c x1) {
            boolean b = x1.b();
            synchronized (x0) {
                if (x1 != x0.c || x1 == x0.d) {
                } else {
                    c cVar;
                    if (x0.d == null || b) {
                        cVar = x0.d;
                        x0.d = x1;
                    } else {
                        cVar = null;
                    }
                    c(cVar);
                }
            }
            if (x1 == x0.k()) {
                x0.a(null, x1.b());
            }
        }

        public a(f fVar) {
            this.a = fVar;
            if (!i()) {
                a(new RuntimeException("No data source supplier or supplier returned null."));
            }
        }

        @Nullable
        public final synchronized T d() {
            c<T> dataSourceWithResult;
            dataSourceWithResult = k();
            return dataSourceWithResult != null ? dataSourceWithResult.d() : null;
        }

        public final synchronized boolean c() {
            boolean z;
            c<T> dataSourceWithResult = k();
            z = dataSourceWithResult != null && dataSourceWithResult.c();
            return z;
        }

        public final boolean h() {
            synchronized (this) {
                if (super.h()) {
                    c<T> currentDataSource = this.c;
                    this.c = null;
                    c<T> dataSourceWithResult = this.d;
                    this.d = null;
                    c(dataSourceWithResult);
                    c(currentDataSource);
                    return true;
                }
                return false;
            }
        }

        private boolean i() {
            j<c<T>> dataSourceSupplier = j();
            c<T> dataSource = dataSourceSupplier != null ? (c) dataSourceSupplier.a() : null;
            if (!a(dataSource) || dataSource == null) {
                c(dataSource);
                return false;
            }
            dataSource.a(new a(), com.facebook.common.b.a.a());
            return true;
        }

        @Nullable
        private synchronized j<c<T>> j() {
            j<c<T>> jVar;
            if (a() || this.b >= this.a.a.size()) {
                jVar = null;
            } else {
                List a = this.a.a;
                int i = this.b;
                this.b = i + 1;
                jVar = (j) a.get(i);
            }
            return jVar;
        }

        private synchronized boolean a(c<T> dataSource) {
            boolean z;
            if (a()) {
                z = false;
            } else {
                this.c = dataSource;
                z = true;
            }
            return z;
        }

        private synchronized boolean b(c<T> dataSource) {
            boolean z;
            if (a() || dataSource != this.c) {
                z = false;
            } else {
                this.c = null;
                z = true;
            }
            return z;
        }

        @Nullable
        private synchronized c<T> k() {
            return this.d;
        }

        private static void c(c<T> dataSource) {
            if (dataSource != null) {
                dataSource.h();
            }
        }

        static /* synthetic */ void a(a x0, c x1) {
            if (x0.b(x1)) {
                if (x1 != x0.k()) {
                    c(x1);
                }
                if (!x0.i()) {
                    x0.a(x1.f());
                }
            }
        }
    }

    private f(List<j<c<T>>> dataSourceSuppliers) {
        h.a(!dataSourceSuppliers.isEmpty(), (Object) "List of suppliers is empty!");
        this.a = dataSourceSuppliers;
    }

    public static <T> f<T> a(List<j<c<T>>> dataSourceSuppliers) {
        return new f(dataSourceSuppliers);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof f)) {
            return false;
        }
        return g.a(this.a, ((f) other).a);
    }

    public final String toString() {
        return g.a(this).a("list", this.a).toString();
    }

    public final /* synthetic */ Object a() {
        return new a(this);
    }
}
