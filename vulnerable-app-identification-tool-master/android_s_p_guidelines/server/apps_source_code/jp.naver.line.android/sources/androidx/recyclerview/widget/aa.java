package androidx.recyclerview.widget;

import defpackage.fk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class aa implements Runnable {
    static final ThreadLocal<aa> a = new ThreadLocal();
    static Comparator<ac> e = new Comparator<ac>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            ac acVar = (ac) obj;
            ac acVar2 = (ac) obj2;
            if ((acVar.d == null ? 1 : null) != (acVar2.d == null ? 1 : null)) {
                return acVar.d == null ? 1 : -1;
            } else {
                if (acVar.a != acVar2.a) {
                    return acVar.a ? -1 : 1;
                } else {
                    int i = acVar2.b - acVar.b;
                    if (i != 0) {
                        return i;
                    }
                    int i2 = acVar.c - acVar2.c;
                    if (i2 != 0) {
                        return i2;
                    }
                    return 0;
                }
            }
        }
    };
    ArrayList<RecyclerView> b = new ArrayList();
    long c;
    long d;
    private ArrayList<ac> f = new ArrayList();

    aa() {
    }

    final void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.c == 0) {
            this.c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        ab abVar = recyclerView.mPrefetchRegistry;
        abVar.a = i;
        abVar.b = i2;
    }

    public final void run() {
        Throwable th;
        aa aaVar = this;
        long j = 0;
        try {
            fk.a("RV Prefetch");
            if (aaVar.b.isEmpty()) {
                aaVar.c = 0;
                fk.a();
                return;
            }
            int size = aaVar.b.size();
            long j2 = 0;
            for (int i = 0; i < size; i++) {
                RecyclerView recyclerView = (RecyclerView) aaVar.b.get(i);
                if (recyclerView.getWindowVisibility() == 0) {
                    j2 = Math.max(recyclerView.getDrawingTime(), j2);
                }
            }
            if (j2 == 0) {
                aaVar.c = 0;
                fk.a();
                return;
            }
            int i2;
            long toNanos = TimeUnit.MILLISECONDS.toNanos(j2) + aaVar.d;
            size = aaVar.b.size();
            int i3 = 0;
            for (i2 = 0; i2 < size; i2++) {
                RecyclerView recyclerView2 = (RecyclerView) aaVar.b.get(i2);
                if (recyclerView2.getWindowVisibility() == 0) {
                    recyclerView2.mPrefetchRegistry.a(recyclerView2, false);
                    i3 += recyclerView2.mPrefetchRegistry.d;
                }
            }
            aaVar.f.ensureCapacity(i3);
            i3 = 0;
            for (i2 = 0; i2 < size; i2++) {
                RecyclerView recyclerView3 = (RecyclerView) aaVar.b.get(i2);
                if (recyclerView3.getWindowVisibility() == 0) {
                    ab abVar = recyclerView3.mPrefetchRegistry;
                    int abs = Math.abs(abVar.a) + Math.abs(abVar.b);
                    int i4 = i3;
                    i3 = 0;
                    while (i3 < abVar.d * 2) {
                        ac acVar;
                        if (i4 >= aaVar.f.size()) {
                            acVar = new ac();
                            aaVar.f.add(acVar);
                        } else {
                            acVar = (ac) aaVar.f.get(i4);
                        }
                        int i5 = abVar.c[i3 + 1];
                        try {
                            acVar.a = i5 <= abs;
                            acVar.b = abs;
                            acVar.c = i5;
                            acVar.d = recyclerView3;
                            acVar.e = abVar.c[i3];
                            i4++;
                            i3 += 2;
                        } catch (Throwable th2) {
                            th = th2;
                            j = 0;
                        }
                    }
                    i3 = i4;
                }
            }
            Collections.sort(aaVar.f, e);
            for (size = 0; size < aaVar.f.size(); size++) {
                ac acVar2 = (ac) aaVar.f.get(size);
                if (acVar2.d == null) {
                    break;
                }
                cb a = a(acVar2.d, acVar2.e, acVar2.a ? Long.MAX_VALUE : toNanos);
                if (!(a == null || a.mNestedRecyclerView == null || !a.isBound() || a.isInvalid())) {
                    RecyclerView recyclerView4 = (RecyclerView) a.mNestedRecyclerView.get();
                    if (recyclerView4 != null) {
                        if (recyclerView4.mDataSetHasChangedAfterLayout && recyclerView4.mChildHelper.b() != 0) {
                            recyclerView4.removeAndRecycleViews();
                        }
                        ab abVar2 = recyclerView4.mPrefetchRegistry;
                        abVar2.a(recyclerView4, true);
                        if (abVar2.d != 0) {
                            fk.a("RV Nested Prefetch");
                            by byVar = recyclerView4.mState;
                            az azVar = recyclerView4.mAdapter;
                            byVar.d = 1;
                            byVar.e = azVar.getItemCount();
                            byVar.g = false;
                            byVar.h = false;
                            byVar.i = false;
                            for (i3 = 0; i3 < abVar2.d * 2; i3 += 2) {
                                a(recyclerView4, abVar2.c[i3], toNanos);
                            }
                            fk.a();
                        }
                    }
                }
                acVar2.a = false;
                acVar2.b = 0;
                acVar2.c = 0;
                acVar2.d = null;
                acVar2.e = 0;
            }
            aaVar.c = 0;
            fk.a();
        } catch (Throwable th3) {
            th = th3;
            aaVar.c = j;
            fk.a();
            throw th;
        }
    }

    private static cb a(RecyclerView recyclerView, int i, long j) {
        Object obj;
        int b = recyclerView.mChildHelper.b();
        for (int i2 = 0; i2 < b; i2++) {
            cb childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.c(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            return null;
        }
        bs bsVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            cb a = bsVar.a(i, j);
            if (a != null) {
                if (!a.isBound() || a.isInvalid()) {
                    bsVar.a(a, false);
                } else {
                    bsVar.a(a.itemView);
                }
            }
            recyclerView.onExitLayoutOrScroll(false);
            return a;
        } catch (Throwable th) {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }
}
