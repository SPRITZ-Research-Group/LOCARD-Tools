package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.f;
import android.support.v7.widget.RecyclerView.s;

final class aw {
    @VisibleForTesting
    final android.support.v4.util.a<s, a> a = new android.support.v4.util.a();
    @VisibleForTesting
    final f<s> b = new f();

    interface b {
        void a(s sVar);

        void a(s sVar, @NonNull android.support.v7.widget.RecyclerView.ItemAnimator.b bVar, @Nullable android.support.v7.widget.RecyclerView.ItemAnimator.b bVar2);

        void b(s sVar, @Nullable android.support.v7.widget.RecyclerView.ItemAnimator.b bVar, android.support.v7.widget.RecyclerView.ItemAnimator.b bVar2);

        void c(s sVar, @NonNull android.support.v7.widget.RecyclerView.ItemAnimator.b bVar, @NonNull android.support.v7.widget.RecyclerView.ItemAnimator.b bVar2);
    }

    static class a {
        static android.support.v4.util.j.a<a> d = new android.support.v4.util.j.b(20);
        int a;
        @Nullable
        android.support.v7.widget.RecyclerView.ItemAnimator.b b;
        @Nullable
        android.support.v7.widget.RecyclerView.ItemAnimator.b c;

        private a() {
        }

        static a a() {
            a aVar = (a) d.a();
            return aVar == null ? new a() : aVar;
        }

        static void a(a record) {
            record.a = 0;
            record.b = null;
            record.c = null;
            d.a(record);
        }

        static void b() {
            do {
            } while (d.a() != null);
        }
    }

    aw() {
    }

    final void a() {
        this.a.clear();
        this.b.b();
    }

    final void a(s holder, android.support.v7.widget.RecyclerView.ItemAnimator.b info) {
        a record = (a) this.a.get(holder);
        if (record == null) {
            record = a.a();
            this.a.put(holder, record);
        }
        record.b = info;
        record.a |= 4;
    }

    final boolean a(s holder) {
        a record = (a) this.a.get(holder);
        return (record == null || (record.a & 1) == 0) ? false : true;
    }

    @Nullable
    final android.support.v7.widget.RecyclerView.ItemAnimator.b b(s vh) {
        return a(vh, 4);
    }

    @Nullable
    final android.support.v7.widget.RecyclerView.ItemAnimator.b c(s vh) {
        return a(vh, 8);
    }

    private android.support.v7.widget.RecyclerView.ItemAnimator.b a(s vh, int flag) {
        android.support.v7.widget.RecyclerView.ItemAnimator.b info = null;
        int index = this.a.a((Object) vh);
        if (index >= 0) {
            a record = (a) this.a.c(index);
            if (!(record == null || (record.a & flag) == 0)) {
                record.a &= flag ^ -1;
                if (flag == 4) {
                    info = record.b;
                } else if (flag == 8) {
                    info = record.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((record.a & 12) == 0) {
                    this.a.d(index);
                    a.a(record);
                }
            }
        }
        return info;
    }

    final void a(long key, s holder) {
        this.b.a(key, holder);
    }

    final void b(s holder, android.support.v7.widget.RecyclerView.ItemAnimator.b info) {
        a record = (a) this.a.get(holder);
        if (record == null) {
            record = a.a();
            this.a.put(holder, record);
        }
        record.c = info;
        record.a |= 8;
    }

    final void d(s holder) {
        a record = (a) this.a.get(holder);
        if (record == null) {
            record = a.a();
            this.a.put(holder, record);
        }
        record.a |= 1;
    }

    final void e(s holder) {
        a record = (a) this.a.get(holder);
        if (record != null) {
            record.a &= -2;
        }
    }

    final void a(b callback) {
        for (int index = this.a.size() - 1; index >= 0; index--) {
            s viewHolder = (s) this.a.b(index);
            a record = (a) this.a.d(index);
            if ((record.a & 3) == 3) {
                callback.a(viewHolder);
            } else if ((record.a & 1) != 0) {
                if (record.b == null) {
                    callback.a(viewHolder);
                } else {
                    callback.a(viewHolder, record.b, record.c);
                }
            } else if ((record.a & 14) == 14) {
                callback.b(viewHolder, record.b, record.c);
            } else if ((record.a & 12) == 12) {
                callback.c(viewHolder, record.b, record.c);
            } else if ((record.a & 4) != 0) {
                callback.a(viewHolder, record.b, null);
            } else if ((record.a & 8) != 0) {
                callback.b(viewHolder, record.b, record.c);
            } else {
                int i = record.a;
            }
            a.a(record);
        }
    }

    final void f(s holder) {
        for (int i = this.b.a() - 1; i >= 0; i--) {
            if (holder == this.b.c(i)) {
                this.b.a(i);
                break;
            }
        }
        a info = (a) this.a.remove(holder);
        if (info != null) {
            a.a(info);
        }
    }
}
