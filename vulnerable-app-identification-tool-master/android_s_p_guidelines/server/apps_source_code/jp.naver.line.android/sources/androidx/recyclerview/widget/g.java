package androidx.recyclerview.widget;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public final class g<T> {
    private static final Executor e = new h();
    final e<T> a;
    final Executor b;
    int c;
    private final au d;
    private List<T> f;
    private List<T> g = Collections.emptyList();

    public g(au auVar, e<T> eVar) {
        this.d = auVar;
        this.a = eVar;
        if (eVar.a() != null) {
            this.b = eVar.a();
        } else {
            this.b = e;
        }
    }

    public final List<T> a() {
        return this.g;
    }

    public final void a(final List<T> list) {
        final int i = this.c + 1;
        this.c = i;
        if (list != this.f) {
            if (list == null) {
                int size = this.f.size();
                this.f = null;
                this.g = Collections.emptyList();
                this.d.b(0, size);
            } else if (this.f == null) {
                this.f = list;
                this.g = Collections.unmodifiableList(list);
                this.d.a(0, list.size());
            } else {
                final List list2 = this.f;
                this.a.b().execute(new Runnable(this) {
                    final /* synthetic */ g d;

                    public final void run() {
                        final r a = p.a(new q(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public final int a() {
                                return list2.size();
                            }

                            public final int b() {
                                return list.size();
                            }

                            public final boolean a(int i, int i2) {
                                Object obj = list2.get(i);
                                Object obj2 = list.get(i2);
                                if (obj == null || obj2 == null) {
                                    return obj == null && obj2 == null;
                                } else {
                                    return this.a.d.a.c().a(obj, obj2);
                                }
                            }

                            public final boolean b(int i, int i2) {
                                Object obj = list2.get(i);
                                Object obj2 = list.get(i2);
                                if (obj != null && obj2 != null) {
                                    return this.a.d.a.c().b(obj, obj2);
                                }
                                if (obj == null && obj2 == null) {
                                    return true;
                                }
                                throw new AssertionError();
                            }

                            public final Object c(int i, int i2) {
                                Object obj = list2.get(i);
                                Object obj2 = list.get(i2);
                                if (obj != null && obj2 != null) {
                                    return null;
                                }
                                throw new AssertionError();
                            }
                        });
                        this.d.b.execute(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public final void run() {
                                if (this.b.d.c == i) {
                                    this.b.d.a(list, a);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    final void a(List<T> list, r rVar) {
        this.f = list;
        this.g = Collections.unmodifiableList(list);
        rVar.a(this.d);
    }
}
