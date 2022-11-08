package com.facebook.datasource;

import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class g<T> implements j<c<T>> {
    private final List<j<c<T>>> a;
    private final boolean b;

    @ThreadSafe
    private class a extends a<T> {
        final /* synthetic */ g a;
        @GuardedBy("IncreasingQualityDataSource.this")
        @Nullable
        private ArrayList<c<T>> b;
        @GuardedBy("IncreasingQualityDataSource.this")
        private int c;
        private int d;
        private AtomicInteger e;
        @Nullable
        private Throwable f;

        private class a implements e<T> {
            final /* synthetic */ a a;
            private int b;

            public a(a aVar, int index) {
                this.a = aVar;
                this.b = index;
            }

            public final void a(c<T> dataSource) {
                if (dataSource.c()) {
                    a.a(this.a, this.b, dataSource);
                } else if (dataSource.b()) {
                    a.b(this.a, this.b, dataSource);
                }
            }

            public final void b(c<T> dataSource) {
                a.b(this.a, this.b, dataSource);
            }

            public final void c(c<T> cVar) {
            }

            public final void d(c<T> dataSource) {
                if (this.b == 0) {
                    this.a.a(dataSource.g());
                }
            }
        }

        public a(g gVar) {
            this.a = gVar;
            if (!gVar.b) {
                i();
            }
        }

        private void i() {
            if (this.e == null) {
                synchronized (this) {
                    if (this.e == null) {
                        this.e = new AtomicInteger(0);
                        int n = this.a.a.size();
                        this.d = n;
                        this.c = n;
                        this.b = new ArrayList(n);
                        for (int i = 0; i < n; i++) {
                            c<T> dataSource = (c) ((j) this.a.a.get(i)).a();
                            this.b.add(dataSource);
                            dataSource.a(new a(this, i), com.facebook.common.b.a.a());
                            if (dataSource.c()) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        @Nullable
        private synchronized c<T> a(int i) {
            c<T> cVar;
            cVar = (this.b == null || i >= this.b.size()) ? null : (c) this.b.get(i);
            return cVar;
        }

        @Nullable
        private synchronized c<T> b(int i) {
            c<T> cVar = null;
            synchronized (this) {
                if (this.b != null && i < this.b.size()) {
                    cVar = (c) this.b.set(i, null);
                }
            }
            return cVar;
        }

        @Nullable
        private synchronized c<T> j() {
            return a(this.c);
        }

        @Nullable
        public final synchronized T d() {
            c<T> dataSourceWithResult;
            if (this.a.b) {
                i();
            }
            dataSourceWithResult = j();
            return dataSourceWithResult != null ? dataSourceWithResult.d() : null;
        }

        public final synchronized boolean c() {
            boolean z;
            if (this.a.b) {
                i();
            }
            c<T> dataSourceWithResult = j();
            z = dataSourceWithResult != null && dataSourceWithResult.c();
            return z;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean h() {
            if (this.a.b) {
                i();
            }
            synchronized (this) {
                if (super.h()) {
                    ArrayList<c<T>> dataSources = this.b;
                    this.b = null;
                } else {
                    return false;
                }
            }
        }

        private void k() {
            if (this.e.incrementAndGet() == this.d && this.f != null) {
                a(this.f);
            }
        }

        @Nullable
        private synchronized c<T> a(int i, c<T> dataSource) {
            if (dataSource == j()) {
                dataSource = null;
            } else if (dataSource == a(i)) {
                dataSource = b(i);
            }
            return dataSource;
        }

        private static void a(c<T> dataSource) {
            if (dataSource != null) {
                dataSource.h();
            }
        }

        static /* synthetic */ void a(a x0, int x1, c x2) {
            boolean b = x2.b();
            synchronized (x0) {
                int i = x0.c;
                if (x2 != x0.a(x1) || x1 == x0.c) {
                } else {
                    if (x0.j() == null || (b && x1 < x0.c)) {
                        x0.c = x1;
                        i = x1;
                    }
                    for (int i2 = x0.c; i2 > i; i2--) {
                        a(x0.b(i2));
                    }
                }
            }
            if (x2 == x0.j()) {
                boolean z = x1 == 0 && x2.b();
                x0.a(null, z);
            }
            x0.k();
        }

        static /* synthetic */ void b(a x0, int x1, c x2) {
            a(x0.a(x1, x2));
            if (x1 == 0) {
                x0.f = x2.f();
            }
            x0.k();
        }
    }

    private g(List<j<c<T>>> dataSourceSuppliers) {
        boolean z;
        if (dataSourceSuppliers.isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        h.a(z, (Object) "List of suppliers is empty!");
        this.a = dataSourceSuppliers;
        this.b = false;
    }

    public static <T> g<T> a(List<j<c<T>>> dataSourceSuppliers) {
        return new g(dataSourceSuppliers);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof g)) {
            return false;
        }
        return com.facebook.common.internal.g.a(this.a, ((g) other).a);
    }

    public final String toString() {
        return com.facebook.common.internal.g.a(this).a("list", this.a).toString();
    }

    public final /* synthetic */ Object a() {
        return new a(this);
    }
}
