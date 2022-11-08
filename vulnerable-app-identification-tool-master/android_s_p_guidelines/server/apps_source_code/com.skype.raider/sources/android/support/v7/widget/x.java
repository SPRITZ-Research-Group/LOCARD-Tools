package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.s;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

final class x implements Runnable {
    static final ThreadLocal<x> a = new ThreadLocal();
    static Comparator<b> e = new Comparator<b>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            b bVar = (b) obj;
            b bVar2 = (b) obj2;
            if ((bVar.d == null ? 1 : 0) != (bVar2.d == null ? 1 : 0)) {
                if (bVar.d == null) {
                    return 1;
                }
                return -1;
            } else if (bVar.a == bVar2.a) {
                int i = bVar2.b - bVar.b;
                if (i != 0) {
                    return i;
                }
                i = bVar.c - bVar2.c;
                if (i == 0) {
                    return 0;
                }
                return i;
            } else if (bVar.a) {
                return -1;
            } else {
                return 1;
            }
        }
    };
    ArrayList<RecyclerView> b = new ArrayList();
    long c;
    long d;
    private ArrayList<b> f = new ArrayList();

    static class a implements android.support.v7.widget.RecyclerView.g.a {
        int a;
        int b;
        int[] c;
        int d;

        a() {
        }

        final void a(RecyclerView view, boolean nested) {
            this.d = 0;
            if (this.c != null) {
                Arrays.fill(this.c, -1);
            }
            g layout = view.m;
            if (view.l != null && layout != null && layout.p()) {
                if (nested) {
                    if (!view.e.d()) {
                        layout.a(view.l.a(), (android.support.v7.widget.RecyclerView.g.a) this);
                    }
                } else if (!view.o()) {
                    layout.a(this.a, this.b, view.B, (android.support.v7.widget.RecyclerView.g.a) this);
                }
                if (this.d > layout.x) {
                    layout.x = this.d;
                    layout.y = nested;
                    view.d.b();
                }
            }
        }

        public final void a(int layoutPosition, int pixelDistance) {
            if (layoutPosition < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            } else if (pixelDistance < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            } else {
                int storagePosition = this.d * 2;
                if (this.c == null) {
                    this.c = new int[4];
                    Arrays.fill(this.c, -1);
                } else if (storagePosition >= this.c.length) {
                    int[] oldArray = this.c;
                    this.c = new int[(storagePosition * 2)];
                    System.arraycopy(oldArray, 0, this.c, 0, oldArray.length);
                }
                this.c[storagePosition] = layoutPosition;
                this.c[storagePosition + 1] = pixelDistance;
                this.d++;
            }
        }

        final boolean a(int position) {
            if (this.c != null) {
                int count = this.d * 2;
                for (int i = 0; i < count; i += 2) {
                    if (this.c[i] == position) {
                        return true;
                    }
                }
            }
            return false;
        }

        final void a() {
            if (this.c != null) {
                Arrays.fill(this.c, -1);
            }
            this.d = 0;
        }
    }

    static class b {
        public boolean a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;

        b() {
        }
    }

    x() {
    }

    final void a(RecyclerView recyclerView, int prefetchDx, int prefetchDy) {
        if (recyclerView.isAttachedToWindow() && this.c == 0) {
            this.c = RecyclerView.q();
            recyclerView.post(this);
        }
        a aVar = recyclerView.A;
        aVar.a = prefetchDx;
        aVar.b = prefetchDy;
    }

    private static s a(RecyclerView view, int position, long deadlineNs) {
        boolean z;
        int b = view.f.b();
        for (int i = 0; i < b; i++) {
            s d = RecyclerView.d(view.f.c(i));
            if (d.c == position && !d.j()) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            return null;
        }
        m recycler = view.d;
        try {
            view.i();
            s holder = recycler.a(position, deadlineNs);
            if (holder != null) {
                if (!holder.l() || holder.j()) {
                    recycler.a(holder, false);
                } else {
                    recycler.a(holder.a);
                }
            }
            view.b(false);
            return holder;
        } catch (Throwable th) {
            view.b(false);
        }
    }

    public final void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r19 = this;
        r9 = "RV Prefetch";	 Catch:{ all -> 0x01d1 }
        android.support.v4.os.d.a(r9);	 Catch:{ all -> 0x01d1 }
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.b;	 Catch:{ all -> 0x01d1 }
        r9 = r9.isEmpty();	 Catch:{ all -> 0x01d1 }
        if (r9 == 0) goto L_0x0019;
    L_0x000f:
        r10 = 0;
        r0 = r19;
        r0.c = r10;
        android.support.v4.os.d.a();
    L_0x0018:
        return;
    L_0x0019:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.b;	 Catch:{ all -> 0x01d1 }
        r3 = r9.size();	 Catch:{ all -> 0x01d1 }
        r4 = 0;	 Catch:{ all -> 0x01d1 }
        r2 = 0;	 Catch:{ all -> 0x01d1 }
    L_0x0024:
        if (r2 >= r3) goto L_0x0041;	 Catch:{ all -> 0x01d1 }
    L_0x0026:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.b;	 Catch:{ all -> 0x01d1 }
        r8 = r9.get(r2);	 Catch:{ all -> 0x01d1 }
        r8 = (android.support.v7.widget.RecyclerView) r8;	 Catch:{ all -> 0x01d1 }
        r9 = r8.getWindowVisibility();	 Catch:{ all -> 0x01d1 }
        if (r9 != 0) goto L_0x003e;	 Catch:{ all -> 0x01d1 }
    L_0x0036:
        r10 = r8.getDrawingTime();	 Catch:{ all -> 0x01d1 }
        r4 = java.lang.Math.max(r10, r4);	 Catch:{ all -> 0x01d1 }
    L_0x003e:
        r2 = r2 + 1;
        goto L_0x0024;
    L_0x0041:
        r10 = 0;
        r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r9 != 0) goto L_0x0051;
    L_0x0047:
        r10 = 0;
        r0 = r19;
        r0.c = r10;
        android.support.v4.os.d.a();
        goto L_0x0018;
    L_0x0051:
        r9 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x01d1 }
        r10 = r9.toNanos(r4);	 Catch:{ all -> 0x01d1 }
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r12 = r0.d;	 Catch:{ all -> 0x01d1 }
        r6 = r10 + r12;	 Catch:{ all -> 0x01d1 }
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.b;	 Catch:{ all -> 0x01d1 }
        r15 = r9.size();	 Catch:{ all -> 0x01d1 }
        r10 = 0;	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r11 = r9;	 Catch:{ all -> 0x01d1 }
    L_0x0068:
        if (r11 >= r15) goto L_0x008a;	 Catch:{ all -> 0x01d1 }
    L_0x006a:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.b;	 Catch:{ all -> 0x01d1 }
        r9 = r9.get(r11);	 Catch:{ all -> 0x01d1 }
        r9 = (android.support.v7.widget.RecyclerView) r9;	 Catch:{ all -> 0x01d1 }
        r12 = r9.getWindowVisibility();	 Catch:{ all -> 0x01d1 }
        if (r12 != 0) goto L_0x01ea;	 Catch:{ all -> 0x01d1 }
    L_0x007a:
        r12 = r9.A;	 Catch:{ all -> 0x01d1 }
        r13 = 0;	 Catch:{ all -> 0x01d1 }
        r12.a(r9, r13);	 Catch:{ all -> 0x01d1 }
        r9 = r9.A;	 Catch:{ all -> 0x01d1 }
        r9 = r9.d;	 Catch:{ all -> 0x01d1 }
        r9 = r9 + r10;	 Catch:{ all -> 0x01d1 }
    L_0x0085:
        r10 = r11 + 1;	 Catch:{ all -> 0x01d1 }
        r11 = r10;	 Catch:{ all -> 0x01d1 }
        r10 = r9;	 Catch:{ all -> 0x01d1 }
        goto L_0x0068;	 Catch:{ all -> 0x01d1 }
    L_0x008a:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.f;	 Catch:{ all -> 0x01d1 }
        r9.ensureCapacity(r10);	 Catch:{ all -> 0x01d1 }
        r10 = 0;	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r14 = r9;	 Catch:{ all -> 0x01d1 }
    L_0x0094:
        if (r14 >= r15) goto L_0x011c;	 Catch:{ all -> 0x01d1 }
    L_0x0096:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.b;	 Catch:{ all -> 0x01d1 }
        r9 = r9.get(r14);	 Catch:{ all -> 0x01d1 }
        r9 = (android.support.v7.widget.RecyclerView) r9;	 Catch:{ all -> 0x01d1 }
        r11 = r9.getWindowVisibility();	 Catch:{ all -> 0x01d1 }
        if (r11 != 0) goto L_0x01e7;	 Catch:{ all -> 0x01d1 }
    L_0x00a6:
        r0 = r9.A;	 Catch:{ all -> 0x01d1 }
        r16 = r0;	 Catch:{ all -> 0x01d1 }
        r0 = r16;	 Catch:{ all -> 0x01d1 }
        r11 = r0.a;	 Catch:{ all -> 0x01d1 }
        r11 = java.lang.Math.abs(r11);	 Catch:{ all -> 0x01d1 }
        r0 = r16;	 Catch:{ all -> 0x01d1 }
        r12 = r0.b;	 Catch:{ all -> 0x01d1 }
        r12 = java.lang.Math.abs(r12);	 Catch:{ all -> 0x01d1 }
        r17 = r11 + r12;	 Catch:{ all -> 0x01d1 }
        r11 = 0;	 Catch:{ all -> 0x01d1 }
        r13 = r11;	 Catch:{ all -> 0x01d1 }
        r11 = r10;	 Catch:{ all -> 0x01d1 }
    L_0x00bf:
        r0 = r16;	 Catch:{ all -> 0x01d1 }
        r10 = r0.d;	 Catch:{ all -> 0x01d1 }
        r10 = r10 * 2;	 Catch:{ all -> 0x01d1 }
        if (r13 >= r10) goto L_0x0115;	 Catch:{ all -> 0x01d1 }
    L_0x00c7:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r10 = r0.f;	 Catch:{ all -> 0x01d1 }
        r10 = r10.size();	 Catch:{ all -> 0x01d1 }
        if (r11 < r10) goto L_0x0107;	 Catch:{ all -> 0x01d1 }
    L_0x00d1:
        r10 = new android.support.v7.widget.x$b;	 Catch:{ all -> 0x01d1 }
        r10.<init>();	 Catch:{ all -> 0x01d1 }
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r12 = r0.f;	 Catch:{ all -> 0x01d1 }
        r12.add(r10);	 Catch:{ all -> 0x01d1 }
        r12 = r10;	 Catch:{ all -> 0x01d1 }
    L_0x00de:
        r0 = r16;	 Catch:{ all -> 0x01d1 }
        r10 = r0.c;	 Catch:{ all -> 0x01d1 }
        r18 = r13 + 1;	 Catch:{ all -> 0x01d1 }
        r18 = r10[r18];	 Catch:{ all -> 0x01d1 }
        r0 = r18;	 Catch:{ all -> 0x01d1 }
        r1 = r17;	 Catch:{ all -> 0x01d1 }
        if (r0 > r1) goto L_0x0113;	 Catch:{ all -> 0x01d1 }
    L_0x00ec:
        r10 = 1;	 Catch:{ all -> 0x01d1 }
    L_0x00ed:
        r12.a = r10;	 Catch:{ all -> 0x01d1 }
        r0 = r17;	 Catch:{ all -> 0x01d1 }
        r12.b = r0;	 Catch:{ all -> 0x01d1 }
        r0 = r18;	 Catch:{ all -> 0x01d1 }
        r12.c = r0;	 Catch:{ all -> 0x01d1 }
        r12.d = r9;	 Catch:{ all -> 0x01d1 }
        r0 = r16;	 Catch:{ all -> 0x01d1 }
        r10 = r0.c;	 Catch:{ all -> 0x01d1 }
        r10 = r10[r13];	 Catch:{ all -> 0x01d1 }
        r12.e = r10;	 Catch:{ all -> 0x01d1 }
        r11 = r11 + 1;	 Catch:{ all -> 0x01d1 }
        r10 = r13 + 2;	 Catch:{ all -> 0x01d1 }
        r13 = r10;	 Catch:{ all -> 0x01d1 }
        goto L_0x00bf;	 Catch:{ all -> 0x01d1 }
    L_0x0107:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r10 = r0.f;	 Catch:{ all -> 0x01d1 }
        r10 = r10.get(r11);	 Catch:{ all -> 0x01d1 }
        r10 = (android.support.v7.widget.x.b) r10;	 Catch:{ all -> 0x01d1 }
        r12 = r10;	 Catch:{ all -> 0x01d1 }
        goto L_0x00de;	 Catch:{ all -> 0x01d1 }
    L_0x0113:
        r10 = 0;	 Catch:{ all -> 0x01d1 }
        goto L_0x00ed;	 Catch:{ all -> 0x01d1 }
    L_0x0115:
        r9 = r11;	 Catch:{ all -> 0x01d1 }
    L_0x0116:
        r10 = r14 + 1;	 Catch:{ all -> 0x01d1 }
        r14 = r10;	 Catch:{ all -> 0x01d1 }
        r10 = r9;	 Catch:{ all -> 0x01d1 }
        goto L_0x0094;	 Catch:{ all -> 0x01d1 }
    L_0x011c:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.f;	 Catch:{ all -> 0x01d1 }
        r10 = e;	 Catch:{ all -> 0x01d1 }
        java.util.Collections.sort(r9, r10);	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r11 = r9;	 Catch:{ all -> 0x01d1 }
    L_0x0127:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.f;	 Catch:{ all -> 0x01d1 }
        r9 = r9.size();	 Catch:{ all -> 0x01d1 }
        if (r11 >= r9) goto L_0x01dc;	 Catch:{ all -> 0x01d1 }
    L_0x0131:
        r0 = r19;	 Catch:{ all -> 0x01d1 }
        r9 = r0.f;	 Catch:{ all -> 0x01d1 }
        r9 = r9.get(r11);	 Catch:{ all -> 0x01d1 }
        r0 = r9;	 Catch:{ all -> 0x01d1 }
        r0 = (android.support.v7.widget.x.b) r0;	 Catch:{ all -> 0x01d1 }
        r10 = r0;	 Catch:{ all -> 0x01d1 }
        r9 = r10.d;	 Catch:{ all -> 0x01d1 }
        if (r9 == 0) goto L_0x01dc;	 Catch:{ all -> 0x01d1 }
    L_0x0141:
        r9 = r10.a;	 Catch:{ all -> 0x01d1 }
        if (r9 == 0) goto L_0x01b3;	 Catch:{ all -> 0x01d1 }
    L_0x0145:
        r12 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;	 Catch:{ all -> 0x01d1 }
    L_0x014a:
        r9 = r10.d;	 Catch:{ all -> 0x01d1 }
        r14 = r10.e;	 Catch:{ all -> 0x01d1 }
        r9 = a(r9, r14, r12);	 Catch:{ all -> 0x01d1 }
        if (r9 == 0) goto L_0x01b8;	 Catch:{ all -> 0x01d1 }
    L_0x0154:
        r12 = r9.b;	 Catch:{ all -> 0x01d1 }
        if (r12 == 0) goto L_0x01b8;	 Catch:{ all -> 0x01d1 }
    L_0x0158:
        r12 = r9.l();	 Catch:{ all -> 0x01d1 }
        if (r12 == 0) goto L_0x01b8;	 Catch:{ all -> 0x01d1 }
    L_0x015e:
        r12 = r9.j();	 Catch:{ all -> 0x01d1 }
        if (r12 != 0) goto L_0x01b8;	 Catch:{ all -> 0x01d1 }
    L_0x0164:
        r9 = r9.b;	 Catch:{ all -> 0x01d1 }
        r9 = r9.get();	 Catch:{ all -> 0x01d1 }
        r9 = (android.support.v7.widget.RecyclerView) r9;	 Catch:{ all -> 0x01d1 }
        if (r9 == 0) goto L_0x01b8;	 Catch:{ all -> 0x01d1 }
    L_0x016e:
        r12 = r9.w;	 Catch:{ all -> 0x01d1 }
        if (r12 == 0) goto L_0x017d;	 Catch:{ all -> 0x01d1 }
    L_0x0172:
        r12 = r9.f;	 Catch:{ all -> 0x01d1 }
        r12 = r12.b();	 Catch:{ all -> 0x01d1 }
        if (r12 == 0) goto L_0x017d;	 Catch:{ all -> 0x01d1 }
    L_0x017a:
        r9.b();	 Catch:{ all -> 0x01d1 }
    L_0x017d:
        r13 = r9.A;	 Catch:{ all -> 0x01d1 }
        r12 = 1;	 Catch:{ all -> 0x01d1 }
        r13.a(r9, r12);	 Catch:{ all -> 0x01d1 }
        r12 = r13.d;	 Catch:{ all -> 0x01d1 }
        if (r12 == 0) goto L_0x01b8;
    L_0x0187:
        r12 = "RV Nested Prefetch";	 Catch:{ all -> 0x01cc }
        android.support.v4.os.d.a(r12);	 Catch:{ all -> 0x01cc }
        r12 = r9.B;	 Catch:{ all -> 0x01cc }
        r14 = r9.l;	 Catch:{ all -> 0x01cc }
        r15 = 1;	 Catch:{ all -> 0x01cc }
        r12.c = r15;	 Catch:{ all -> 0x01cc }
        r14 = r14.a();	 Catch:{ all -> 0x01cc }
        r12.d = r14;	 Catch:{ all -> 0x01cc }
        r14 = 0;	 Catch:{ all -> 0x01cc }
        r12.f = r14;	 Catch:{ all -> 0x01cc }
        r14 = 0;	 Catch:{ all -> 0x01cc }
        r12.g = r14;	 Catch:{ all -> 0x01cc }
        r14 = 0;	 Catch:{ all -> 0x01cc }
        r12.h = r14;	 Catch:{ all -> 0x01cc }
        r12 = 0;	 Catch:{ all -> 0x01cc }
    L_0x01a3:
        r14 = r13.d;	 Catch:{ all -> 0x01cc }
        r14 = r14 * 2;	 Catch:{ all -> 0x01cc }
        if (r12 >= r14) goto L_0x01b5;	 Catch:{ all -> 0x01cc }
    L_0x01a9:
        r14 = r13.c;	 Catch:{ all -> 0x01cc }
        r14 = r14[r12];	 Catch:{ all -> 0x01cc }
        a(r9, r14, r6);	 Catch:{ all -> 0x01cc }
        r12 = r12 + 2;
        goto L_0x01a3;
    L_0x01b3:
        r12 = r6;
        goto L_0x014a;
    L_0x01b5:
        android.support.v4.os.d.a();	 Catch:{ all -> 0x01d1 }
    L_0x01b8:
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r10.a = r9;	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r10.b = r9;	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r10.c = r9;	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r10.d = r9;	 Catch:{ all -> 0x01d1 }
        r9 = 0;	 Catch:{ all -> 0x01d1 }
        r10.e = r9;	 Catch:{ all -> 0x01d1 }
        r9 = r11 + 1;	 Catch:{ all -> 0x01d1 }
        r11 = r9;	 Catch:{ all -> 0x01d1 }
        goto L_0x0127;	 Catch:{ all -> 0x01d1 }
    L_0x01cc:
        r9 = move-exception;	 Catch:{ all -> 0x01d1 }
        android.support.v4.os.d.a();	 Catch:{ all -> 0x01d1 }
        throw r9;	 Catch:{ all -> 0x01d1 }
    L_0x01d1:
        r9 = move-exception;
        r10 = 0;
        r0 = r19;
        r0.c = r10;
        android.support.v4.os.d.a();
        throw r9;
    L_0x01dc:
        r10 = 0;
        r0 = r19;
        r0.c = r10;
        android.support.v4.os.d.a();
        goto L_0x0018;
    L_0x01e7:
        r9 = r10;
        goto L_0x0116;
    L_0x01ea:
        r9 = r10;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.x.run():void");
    }
}
