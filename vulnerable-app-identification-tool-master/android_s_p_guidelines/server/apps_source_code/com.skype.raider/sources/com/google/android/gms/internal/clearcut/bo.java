package com.google.android.gms.internal.clearcut;

import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.internal.clearcut.ah.e;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class bo<T> implements bz<T> {
    private static final Unsafe a = cx.c();
    private final int[] b;
    private final Object[] c;
    private final int d;
    private final int e;
    private final int f;
    private final bk g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final int[] l;
    private final int[] m;
    private final int[] n;
    private final br o;
    private final aw p;
    private final cr<?, ?> q;
    private final w<?> r;
    private final bf s;

    private bo(int[] iArr, Object[] objArr, int i, int i2, int i3, bk bkVar, boolean z, int[] iArr2, int[] iArr3, int[] iArr4, br brVar, aw awVar, cr<?, ?> crVar, w<?> wVar, bf bfVar) {
        this.b = iArr;
        this.c = objArr;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.i = bkVar instanceof ah;
        this.j = z;
        boolean z2 = wVar != null && wVar.a(bkVar);
        this.h = z2;
        this.k = false;
        this.l = iArr2;
        this.m = iArr3;
        this.n = iArr4;
        this.o = brVar;
        this.p = awVar;
        this.q = crVar;
        this.r = wVar;
        this.g = bkVar;
        this.s = bfVar;
    }

    private static <UT, UB> int a(cr<UT, UB> crVar, T t) {
        return crVar.d(crVar.a(t));
    }

    static <T> bo<T> a(bi biVar, br brVar, aw awVar, cr<?, ?> crVar, w<?> wVar, bf bfVar) {
        if (biVar instanceof bw) {
            int i;
            int i2;
            int i3;
            bw bwVar = (bw) biVar;
            boolean z = bwVar.a() == e.i;
            if (bwVar.g() == 0) {
                i = 0;
                i2 = 0;
                i3 = 0;
            } else {
                i = bwVar.e();
                i2 = bwVar.f();
                i3 = bwVar.k();
            }
            int[] iArr = new int[(i3 << 2)];
            Object[] objArr = new Object[(i3 << 1)];
            int[] iArr2 = bwVar.h() > 0 ? new int[bwVar.h()] : null;
            int[] iArr3 = bwVar.i() > 0 ? new int[bwVar.i()] : null;
            int i4 = 0;
            int i5 = 0;
            bx d = bwVar.d();
            if (d.a()) {
                int b = d.b();
                i3 = 0;
                while (true) {
                    int i6;
                    if (b >= bwVar.l() || i3 >= ((b - i) << 2)) {
                        int a;
                        if (d.d()) {
                            a = (int) cx.a(d.e());
                            b = (int) cx.a(d.f());
                            i6 = 0;
                        } else {
                            a = (int) cx.a(d.g());
                            if (d.h()) {
                                b = (int) cx.a(d.i());
                                i6 = d.j();
                            } else {
                                b = 0;
                                i6 = 0;
                            }
                        }
                        iArr[i3] = d.b();
                        iArr[i3 + 1] = a | (((d.l() ? ErrorDialogData.DYNAMITE_CRASH : 0) | (d.k() ? ErrorDialogData.BINDER_CRASH : 0)) | (d.c() << 20));
                        iArr[i3 + 2] = b | (i6 << 20);
                        if (d.o() != null) {
                            objArr[(i3 / 4) << 1] = d.o();
                            if (d.m() != null) {
                                objArr[((i3 / 4) << 1) + 1] = d.m();
                            } else if (d.n() != null) {
                                objArr[((i3 / 4) << 1) + 1] = d.n();
                            }
                        } else if (d.m() != null) {
                            objArr[((i3 / 4) << 1) + 1] = d.m();
                        } else if (d.n() != null) {
                            objArr[((i3 / 4) << 1) + 1] = d.n();
                        }
                        b = d.c();
                        if (b == ad.MAP.ordinal()) {
                            b = i4 + 1;
                            iArr2[i4] = i3;
                            i4 = b;
                        } else if (b >= 18 && b <= 49) {
                            b = i5 + 1;
                            iArr3[i5] = iArr[i3 + 1] & 1048575;
                            i5 = b;
                        }
                        if (!d.a()) {
                            break;
                        }
                        b = d.b();
                    } else {
                        for (i6 = 0; i6 < 4; i6++) {
                            iArr[i3 + i6] = -1;
                        }
                    }
                    i3 += 4;
                }
            }
            return new bo(iArr, objArr, i, i2, bwVar.l(), bwVar.c(), z, bwVar.j(), iArr2, iArr3, brVar, awVar, crVar, wVar, bfVar);
        }
        ((cm) biVar).a();
        throw new NoSuchMethodError();
    }

    private final bz a(int i) {
        int i2 = (i / 4) << 1;
        bz bzVar = (bz) this.c[i2];
        if (bzVar != null) {
            return bzVar;
        }
        bzVar = bv.a().a((Class) this.c[i2 + 1]);
        this.c[i2] = bzVar;
        return bzVar;
    }

    private static <E> List<E> a(Object obj, long j) {
        return (List) cx.f(obj, j);
    }

    private static void a(int i, Object obj, dk dkVar) throws IOException {
        if (obj instanceof String) {
            dkVar.a(i, (String) obj);
        } else {
            dkVar.a(i, (h) obj);
        }
    }

    private static <UT, UB> void a(cr<UT, UB> crVar, T t, dk dkVar) throws IOException {
        crVar.a(crVar.a(t), dkVar);
    }

    private final <K, V> void a(dk dkVar, int i, Object obj) throws IOException {
        if (obj != null) {
            dkVar.a(i, this.s.a(), this.s.a(obj));
        }
    }

    private final void a(T t, T t2, int i) {
        long b = (long) (b(i) & 1048575);
        if (a((Object) t2, i)) {
            Object f = cx.f(t, b);
            Object f2 = cx.f(t2, b);
            if (f != null && f2 != null) {
                cx.a((Object) t, b, aj.a(f, f2));
                b((Object) t, i);
            } else if (f2 != null) {
                cx.a((Object) t, b, f2);
                b((Object) t, i);
            }
        }
    }

    private final boolean a(T t, int i) {
        int b;
        if (this.j) {
            b = b(i);
            long j = (long) (b & 1048575);
            switch ((b & 267386880) >>> 20) {
                case 0:
                    return cx.e(t, j) != 0.0d;
                case 1:
                    return cx.d(t, j) != 0.0f;
                case 2:
                    return cx.b(t, j) != 0;
                case 3:
                    return cx.b(t, j) != 0;
                case 4:
                    return cx.a((Object) t, j) != 0;
                case 5:
                    return cx.b(t, j) != 0;
                case 6:
                    return cx.a((Object) t, j) != 0;
                case 7:
                    return cx.c(t, j);
                case 8:
                    Object f = cx.f(t, j);
                    if (f instanceof String) {
                        return !((String) f).isEmpty();
                    } else {
                        if (f instanceof h) {
                            return !h.a.equals(f);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                case 9:
                    return cx.f(t, j) != null;
                case 10:
                    return !h.a.equals(cx.f(t, j));
                case 11:
                    return cx.a((Object) t, j) != 0;
                case 12:
                    return cx.a((Object) t, j) != 0;
                case 13:
                    return cx.a((Object) t, j) != 0;
                case 14:
                    return cx.b(t, j) != 0;
                case 15:
                    return cx.a((Object) t, j) != 0;
                case 16:
                    return cx.b(t, j) != 0;
                case 17:
                    return cx.f(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        b = c(i);
        return (cx.a((Object) t, (long) (b & 1048575)) & (1 << (b >>> 20))) != 0;
    }

    private final boolean a(T t, int i, int i2) {
        return cx.a((Object) t, (long) (c(i2) & 1048575)) == i;
    }

    private final boolean a(T t, int i, int i2, int i3) {
        return this.j ? a((Object) t, i) : (i2 & i3) != 0;
    }

    private static boolean a(Object obj, int i, bz bzVar) {
        return bzVar.d(cx.f(obj, (long) (1048575 & i)));
    }

    private static <T> double b(T t, long j) {
        return ((Double) cx.f(t, j)).doubleValue();
    }

    private final int b(int i) {
        return this.b[i + 1];
    }

    private final void b(T t, int i) {
        if (!this.j) {
            int c = c(i);
            long j = (long) (c & 1048575);
            cx.a((Object) t, j, cx.a((Object) t, j) | (1 << (c >>> 20)));
        }
    }

    private final void b(T t, int i, int i2) {
        cx.a((Object) t, (long) (c(i2) & 1048575), i);
    }

    private final void b(T t, dk dkVar) throws IOException {
        Iterator it = null;
        Entry entry = null;
        if (this.h) {
            aa a = this.r.a((Object) t);
            if (!a.b()) {
                it = a.e();
                entry = (Entry) it.next();
            }
        }
        int length = this.b.length;
        Unsafe unsafe = a;
        int i = 0;
        int i2 = -1;
        Entry entry2 = entry;
        int i3 = 0;
        while (i < length) {
            Entry entry3;
            int i4;
            int b = b(i);
            int i5 = this.b[i];
            int i6 = (267386880 & b) >>> 20;
            int i7 = 0;
            if (this.j || i6 > 17) {
                entry3 = entry2;
                i4 = i2;
                i2 = i3;
            } else {
                int i8 = this.b[i + 2];
                i7 = 1048575 & i8;
                if (i7 != i2) {
                    i3 = unsafe.getInt(t, (long) i7);
                } else {
                    i7 = i2;
                }
                i2 = 1 << (i8 >>> 20);
                entry3 = entry2;
                i4 = i7;
                i7 = i2;
                i2 = i3;
            }
            while (entry3 != null && this.r.a(entry3) <= i5) {
                this.r.a(dkVar, entry3);
                entry3 = it.hasNext() ? (Entry) it.next() : null;
            }
            long j = (long) (1048575 & b);
            switch (i6) {
                case 0:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, cx.e(t, j));
                    break;
                case 1:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, cx.d(t, j));
                    break;
                case 2:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, unsafe.getLong(t, j));
                    break;
                case 3:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.c(i5, unsafe.getLong(t, j));
                    break;
                case 4:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.c(i5, unsafe.getInt(t, j));
                    break;
                case 5:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.d(i5, unsafe.getLong(t, j));
                    break;
                case 6:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.d(i5, unsafe.getInt(t, j));
                    break;
                case 7:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, cx.c(t, j));
                    break;
                case 8:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    a(i5, unsafe.getObject(t, j), dkVar);
                    break;
                case 9:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, unsafe.getObject(t, j), a(i));
                    break;
                case 10:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, (h) unsafe.getObject(t, j));
                    break;
                case 11:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.e(i5, unsafe.getInt(t, j));
                    break;
                case 12:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.b(i5, unsafe.getInt(t, j));
                    break;
                case 13:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.a(i5, unsafe.getInt(t, j));
                    break;
                case 14:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.b(i5, unsafe.getLong(t, j));
                    break;
                case 15:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.f(i5, unsafe.getInt(t, j));
                    break;
                case 16:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.e(i5, unsafe.getLong(t, j));
                    break;
                case 17:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    dkVar.b(i5, unsafe.getObject(t, j), a(i));
                    break;
                case 18:
                    cb.a(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 19:
                    cb.b(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 20:
                    cb.c(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 21:
                    cb.d(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 22:
                    cb.h(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 23:
                    cb.f(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 24:
                    cb.k(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 25:
                    cb.n(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 26:
                    cb.a(this.b[i], (List) unsafe.getObject(t, j), dkVar);
                    break;
                case 27:
                    cb.a(this.b[i], (List) unsafe.getObject(t, j), dkVar, a(i));
                    break;
                case 28:
                    cb.b(this.b[i], (List) unsafe.getObject(t, j), dkVar);
                    break;
                case 29:
                    cb.i(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 30:
                    cb.m(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 31:
                    cb.l(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 32:
                    cb.g(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 33:
                    cb.j(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 34:
                    cb.e(this.b[i], (List) unsafe.getObject(t, j), dkVar, false);
                    break;
                case 35:
                    cb.a(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 36:
                    cb.b(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 37:
                    cb.c(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 38:
                    cb.d(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 39:
                    cb.h(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 40:
                    cb.f(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 41:
                    cb.k(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 42:
                    cb.n(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 43:
                    cb.i(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 44:
                    cb.m(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 45:
                    cb.l(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 46:
                    cb.g(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 47:
                    cb.j(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 48:
                    cb.e(this.b[i], (List) unsafe.getObject(t, j), dkVar, true);
                    break;
                case 49:
                    cb.b(this.b[i], (List) unsafe.getObject(t, j), dkVar, a(i));
                    break;
                case 50:
                    a(dkVar, i5, unsafe.getObject(t, j));
                    break;
                case 51:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, b((Object) t, j));
                    break;
                case 52:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, c(t, j));
                    break;
                case 53:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, e(t, j));
                    break;
                case 54:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.c(i5, e(t, j));
                    break;
                case 55:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.c(i5, d(t, j));
                    break;
                case 56:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.d(i5, e(t, j));
                    break;
                case 57:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.d(i5, d(t, j));
                    break;
                case 58:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, f(t, j));
                    break;
                case 59:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    a(i5, unsafe.getObject(t, j), dkVar);
                    break;
                case 60:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, unsafe.getObject(t, j), a(i));
                    break;
                case 61:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, (h) unsafe.getObject(t, j));
                    break;
                case 62:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.e(i5, d(t, j));
                    break;
                case 63:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.b(i5, d(t, j));
                    break;
                case 64:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.a(i5, d(t, j));
                    break;
                case 65:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.b(i5, e(t, j));
                    break;
                case 66:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.f(i5, d(t, j));
                    break;
                case 67:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.e(i5, e(t, j));
                    break;
                case 68:
                    if (!a((Object) t, i5, i)) {
                        break;
                    }
                    dkVar.b(i5, unsafe.getObject(t, j), a(i));
                    break;
                default:
                    break;
            }
            i += 4;
            i3 = i2;
            i2 = i4;
            entry2 = entry3;
        }
        for (entry = entry2; entry != null; entry = it.hasNext() ? (Entry) it.next() : null) {
            this.r.a(dkVar, entry);
        }
        a(this.q, (Object) t, dkVar);
    }

    private final void b(T t, T t2, int i) {
        int b = b(i);
        int i2 = this.b[i];
        long j = (long) (b & 1048575);
        if (a((Object) t2, i2, i)) {
            Object f = cx.f(t, j);
            Object f2 = cx.f(t2, j);
            if (f != null && f2 != null) {
                cx.a((Object) t, j, aj.a(f, f2));
                b((Object) t, i2, i);
            } else if (f2 != null) {
                cx.a((Object) t, j, f2);
                b((Object) t, i2, i);
            }
        }
    }

    private static <T> float c(T t, long j) {
        return ((Float) cx.f(t, j)).floatValue();
    }

    private final int c(int i) {
        return this.b[i + 2];
    }

    private final boolean c(T t, T t2, int i) {
        return a((Object) t, i) == a((Object) t2, i);
    }

    private static <T> int d(T t, long j) {
        return ((Integer) cx.f(t, j)).intValue();
    }

    private static <T> long e(T t, long j) {
        return ((Long) cx.f(t, j)).longValue();
    }

    private static <T> boolean f(T t, long j) {
        return ((Boolean) cx.f(t, j)).booleanValue();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(T t) {
        int b;
        int length = this.b.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            b = b(i);
            int i3 = this.b[i];
            long j = (long) (1048575 & b);
            Object f;
            switch ((b & 267386880) >>> 20) {
                case 0:
                    b = (i2 * 53) + aj.a(Double.doubleToLongBits(cx.e(t, j)));
                    break;
                case 1:
                    b = (i2 * 53) + Float.floatToIntBits(cx.d(t, j));
                    break;
                case 2:
                    b = (i2 * 53) + aj.a(cx.b(t, j));
                    break;
                case 3:
                    b = (i2 * 53) + aj.a(cx.b(t, j));
                    break;
                case 4:
                    b = (i2 * 53) + cx.a((Object) t, j);
                    break;
                case 5:
                    b = (i2 * 53) + aj.a(cx.b(t, j));
                    break;
                case 6:
                    b = (i2 * 53) + cx.a((Object) t, j);
                    break;
                case 7:
                    b = (i2 * 53) + aj.a(cx.c(t, j));
                    break;
                case 8:
                    b = ((String) cx.f(t, j)).hashCode() + (i2 * 53);
                    break;
                case 9:
                    f = cx.f(t, j);
                    b = (f != null ? f.hashCode() : 37) + (i2 * 53);
                    break;
                case 10:
                    b = (i2 * 53) + cx.f(t, j).hashCode();
                    break;
                case 11:
                    b = (i2 * 53) + cx.a((Object) t, j);
                    break;
                case 12:
                    b = (i2 * 53) + cx.a((Object) t, j);
                    break;
                case 13:
                    b = (i2 * 53) + cx.a((Object) t, j);
                    break;
                case 14:
                    b = (i2 * 53) + aj.a(cx.b(t, j));
                    break;
                case 15:
                    b = (i2 * 53) + cx.a((Object) t, j);
                    break;
                case 16:
                    b = (i2 * 53) + aj.a(cx.b(t, j));
                    break;
                case 17:
                    f = cx.f(t, j);
                    b = (f != null ? f.hashCode() : 37) + (i2 * 53);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    b = (i2 * 53) + cx.f(t, j).hashCode();
                    break;
                case 50:
                    b = (i2 * 53) + cx.f(t, j).hashCode();
                    break;
                case 51:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(Double.doubleToLongBits(b((Object) t, j)));
                        break;
                    }
                case 52:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + Float.floatToIntBits(c(t, j));
                        break;
                    }
                case 53:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(e(t, j));
                        break;
                    }
                case 54:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(e(t, j));
                        break;
                    }
                case 55:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + d(t, j);
                        break;
                    }
                case 56:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(e(t, j));
                        break;
                    }
                case 57:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + d(t, j);
                        break;
                    }
                case 58:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(f(t, j));
                        break;
                    }
                case 59:
                    if (a((Object) t, i3, i)) {
                        b = ((String) cx.f(t, j)).hashCode() + (i2 * 53);
                        break;
                    }
                case 60:
                    if (a((Object) t, i3, i)) {
                        b = cx.f(t, j).hashCode() + (i2 * 53);
                        break;
                    }
                case 61:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + cx.f(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + d(t, j);
                        break;
                    }
                case 63:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + d(t, j);
                        break;
                    }
                case 64:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + d(t, j);
                        break;
                    }
                case 65:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(e(t, j));
                        break;
                    }
                case 66:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + d(t, j);
                        break;
                    }
                case 67:
                    if (a((Object) t, i3, i)) {
                        b = (i2 * 53) + aj.a(e(t, j));
                        break;
                    }
                case 68:
                    if (a((Object) t, i3, i)) {
                        b = cx.f(t, j).hashCode() + (i2 * 53);
                        break;
                    }
                default:
                    b = i2;
                    break;
            }
            i += 4;
            i2 = b;
        }
        b = (i2 * 53) + this.q.a(t).hashCode();
        return this.h ? (b * 53) + this.r.a((Object) t).hashCode() : b;
    }

    public final void a(T t, dk dkVar) throws IOException {
        Iterator it;
        Entry entry;
        aa a;
        int length;
        int b;
        int i;
        Entry entry2;
        if (dkVar.a() == e.k) {
            a(this.q, (Object) t, dkVar);
            it = null;
            entry = null;
            if (this.h) {
                a = this.r.a((Object) t);
                if (!a.b()) {
                    it = a.f();
                    entry = (Entry) it.next();
                }
            }
            length = this.b.length - 4;
            while (length >= 0) {
                b = b(length);
                i = this.b[length];
                entry2 = entry;
                while (entry2 != null && this.r.a(entry2) > i) {
                    this.r.a(dkVar, entry2);
                    entry2 = it.hasNext() ? (Entry) it.next() : null;
                }
                switch ((267386880 & b) >>> 20) {
                    case 0:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, cx.e(t, (long) (1048575 & b)));
                        break;
                    case 1:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, cx.d(t, (long) (1048575 & b)));
                        break;
                    case 2:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, cx.b(t, (long) (1048575 & b)));
                        break;
                    case 3:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.c(i, cx.b(t, (long) (1048575 & b)));
                        break;
                    case 4:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.c(i, cx.a((Object) t, (long) (1048575 & b)));
                        break;
                    case 5:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.d(i, cx.b(t, (long) (1048575 & b)));
                        break;
                    case 6:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.d(i, cx.a((Object) t, (long) (1048575 & b)));
                        break;
                    case 7:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, cx.c(t, (long) (1048575 & b)));
                        break;
                    case 8:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        a(i, cx.f(t, (long) (1048575 & b)), dkVar);
                        break;
                    case 9:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, cx.f(t, (long) (1048575 & b)), a(length));
                        break;
                    case 10:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, (h) cx.f(t, (long) (1048575 & b)));
                        break;
                    case 11:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.e(i, cx.a((Object) t, (long) (1048575 & b)));
                        break;
                    case 12:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.b(i, cx.a((Object) t, (long) (1048575 & b)));
                        break;
                    case 13:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i, cx.a((Object) t, (long) (1048575 & b)));
                        break;
                    case 14:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.b(i, cx.b(t, (long) (1048575 & b)));
                        break;
                    case 15:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.f(i, cx.a((Object) t, (long) (1048575 & b)));
                        break;
                    case 16:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.e(i, cx.b(t, (long) (1048575 & b)));
                        break;
                    case 17:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.b(i, cx.f(t, (long) (1048575 & b)), a(length));
                        break;
                    case 18:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 19:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 20:
                        cb.c(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 21:
                        cb.d(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 22:
                        cb.h(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 23:
                        cb.f(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 24:
                        cb.k(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 25:
                        cb.n(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 26:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar);
                        break;
                    case 27:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, a(length));
                        break;
                    case 28:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar);
                        break;
                    case 29:
                        cb.i(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 30:
                        cb.m(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 31:
                        cb.l(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 32:
                        cb.g(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 33:
                        cb.j(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 34:
                        cb.e(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, false);
                        break;
                    case 35:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 36:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 37:
                        cb.c(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 38:
                        cb.d(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 39:
                        cb.h(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 40:
                        cb.f(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 41:
                        cb.k(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 42:
                        cb.n(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 43:
                        cb.i(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 44:
                        cb.m(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 45:
                        cb.l(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 46:
                        cb.g(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 47:
                        cb.j(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 48:
                        cb.e(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, true);
                        break;
                    case 49:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & b)), dkVar, a(length));
                        break;
                    case 50:
                        a(dkVar, i, cx.f(t, (long) (1048575 & b)));
                        break;
                    case 51:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, b((Object) t, (long) (1048575 & b)));
                        break;
                    case 52:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, c(t, (long) (1048575 & b)));
                        break;
                    case 53:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, e(t, (long) (1048575 & b)));
                        break;
                    case 54:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.c(i, e(t, (long) (1048575 & b)));
                        break;
                    case 55:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.c(i, d(t, (long) (1048575 & b)));
                        break;
                    case 56:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.d(i, e(t, (long) (1048575 & b)));
                        break;
                    case 57:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.d(i, d(t, (long) (1048575 & b)));
                        break;
                    case 58:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, f(t, (long) (1048575 & b)));
                        break;
                    case 59:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        a(i, cx.f(t, (long) (1048575 & b)), dkVar);
                        break;
                    case 60:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, cx.f(t, (long) (1048575 & b)), a(length));
                        break;
                    case 61:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, (h) cx.f(t, (long) (1048575 & b)));
                        break;
                    case 62:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.e(i, d(t, (long) (1048575 & b)));
                        break;
                    case 63:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.b(i, d(t, (long) (1048575 & b)));
                        break;
                    case 64:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.a(i, d(t, (long) (1048575 & b)));
                        break;
                    case 65:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.b(i, e(t, (long) (1048575 & b)));
                        break;
                    case 66:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.f(i, d(t, (long) (1048575 & b)));
                        break;
                    case 67:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.e(i, e(t, (long) (1048575 & b)));
                        break;
                    case 68:
                        if (!a((Object) t, i, length)) {
                            break;
                        }
                        dkVar.b(i, cx.f(t, (long) (1048575 & b)), a(length));
                        break;
                    default:
                        break;
                }
                length -= 4;
                entry = entry2;
            }
            while (entry != null) {
                this.r.a(dkVar, entry);
                entry = it.hasNext() ? (Entry) it.next() : null;
            }
        } else if (this.j) {
            it = null;
            entry = null;
            if (this.h) {
                a = this.r.a((Object) t);
                if (!a.b()) {
                    it = a.e();
                    entry = (Entry) it.next();
                }
            }
            b = this.b.length;
            length = 0;
            while (length < b) {
                i = b(length);
                int i2 = this.b[length];
                entry2 = entry;
                while (entry2 != null && this.r.a(entry2) <= i2) {
                    this.r.a(dkVar, entry2);
                    entry2 = it.hasNext() ? (Entry) it.next() : null;
                }
                switch ((267386880 & i) >>> 20) {
                    case 0:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.e(t, (long) (1048575 & i)));
                        break;
                    case 1:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.d(t, (long) (1048575 & i)));
                        break;
                    case 2:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.b(t, (long) (1048575 & i)));
                        break;
                    case 3:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.c(i2, cx.b(t, (long) (1048575 & i)));
                        break;
                    case 4:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.c(i2, cx.a((Object) t, (long) (1048575 & i)));
                        break;
                    case 5:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.d(i2, cx.b(t, (long) (1048575 & i)));
                        break;
                    case 6:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.d(i2, cx.a((Object) t, (long) (1048575 & i)));
                        break;
                    case 7:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.c(t, (long) (1048575 & i)));
                        break;
                    case 8:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        a(i2, cx.f(t, (long) (1048575 & i)), dkVar);
                        break;
                    case 9:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.f(t, (long) (1048575 & i)), a(length));
                        break;
                    case 10:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, (h) cx.f(t, (long) (1048575 & i)));
                        break;
                    case 11:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.e(i2, cx.a((Object) t, (long) (1048575 & i)));
                        break;
                    case 12:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.b(i2, cx.a((Object) t, (long) (1048575 & i)));
                        break;
                    case 13:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.a((Object) t, (long) (1048575 & i)));
                        break;
                    case 14:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.b(i2, cx.b(t, (long) (1048575 & i)));
                        break;
                    case 15:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.f(i2, cx.a((Object) t, (long) (1048575 & i)));
                        break;
                    case 16:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.e(i2, cx.b(t, (long) (1048575 & i)));
                        break;
                    case 17:
                        if (!a((Object) t, length)) {
                            break;
                        }
                        dkVar.b(i2, cx.f(t, (long) (1048575 & i)), a(length));
                        break;
                    case 18:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 19:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 20:
                        cb.c(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 21:
                        cb.d(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 22:
                        cb.h(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 23:
                        cb.f(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 24:
                        cb.k(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 25:
                        cb.n(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 26:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar);
                        break;
                    case 27:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, a(length));
                        break;
                    case 28:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar);
                        break;
                    case 29:
                        cb.i(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 30:
                        cb.m(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 31:
                        cb.l(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 32:
                        cb.g(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 33:
                        cb.j(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 34:
                        cb.e(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, false);
                        break;
                    case 35:
                        cb.a(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 36:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 37:
                        cb.c(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 38:
                        cb.d(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 39:
                        cb.h(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 40:
                        cb.f(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 41:
                        cb.k(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 42:
                        cb.n(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 43:
                        cb.i(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 44:
                        cb.m(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 45:
                        cb.l(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 46:
                        cb.g(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 47:
                        cb.j(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 48:
                        cb.e(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, true);
                        break;
                    case 49:
                        cb.b(this.b[length], (List) cx.f(t, (long) (1048575 & i)), dkVar, a(length));
                        break;
                    case 50:
                        a(dkVar, i2, cx.f(t, (long) (1048575 & i)));
                        break;
                    case 51:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, b((Object) t, (long) (1048575 & i)));
                        break;
                    case 52:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, c(t, (long) (1048575 & i)));
                        break;
                    case 53:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, e(t, (long) (1048575 & i)));
                        break;
                    case 54:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.c(i2, e(t, (long) (1048575 & i)));
                        break;
                    case 55:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.c(i2, d(t, (long) (1048575 & i)));
                        break;
                    case 56:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.d(i2, e(t, (long) (1048575 & i)));
                        break;
                    case 57:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.d(i2, d(t, (long) (1048575 & i)));
                        break;
                    case 58:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, f(t, (long) (1048575 & i)));
                        break;
                    case 59:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        a(i2, cx.f(t, (long) (1048575 & i)), dkVar);
                        break;
                    case 60:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, cx.f(t, (long) (1048575 & i)), a(length));
                        break;
                    case 61:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, (h) cx.f(t, (long) (1048575 & i)));
                        break;
                    case 62:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.e(i2, d(t, (long) (1048575 & i)));
                        break;
                    case 63:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.b(i2, d(t, (long) (1048575 & i)));
                        break;
                    case 64:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.a(i2, d(t, (long) (1048575 & i)));
                        break;
                    case 65:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.b(i2, e(t, (long) (1048575 & i)));
                        break;
                    case 66:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.f(i2, d(t, (long) (1048575 & i)));
                        break;
                    case 67:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.e(i2, e(t, (long) (1048575 & i)));
                        break;
                    case 68:
                        if (!a((Object) t, i2, length)) {
                            break;
                        }
                        dkVar.b(i2, cx.f(t, (long) (1048575 & i)), a(length));
                        break;
                    default:
                        break;
                }
                length += 4;
                entry = entry2;
            }
            while (entry != null) {
                this.r.a(dkVar, entry);
                entry = it.hasNext() ? (Entry) it.next() : null;
            }
            a(this.q, (Object) t, dkVar);
        } else {
            b((Object) t, dkVar);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(T t, T t2) {
        int length = this.b.length;
        for (int i = 0; i < length; i += 4) {
            boolean z;
            int b = b(i);
            long j = (long) (b & 1048575);
            switch ((b & 267386880) >>> 20) {
                case 0:
                    if (!(c(t, t2, i) && cx.b(t, j) == cx.b(t2, j))) {
                        z = false;
                        break;
                    }
                case 1:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 2:
                    if (!(c(t, t2, i) && cx.b(t, j) == cx.b(t2, j))) {
                        z = false;
                        break;
                    }
                case 3:
                    if (!(c(t, t2, i) && cx.b(t, j) == cx.b(t2, j))) {
                        z = false;
                        break;
                    }
                case 4:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 5:
                    if (!(c(t, t2, i) && cx.b(t, j) == cx.b(t2, j))) {
                        z = false;
                        break;
                    }
                case 6:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 7:
                    if (!(c(t, t2, i) && cx.c(t, j) == cx.c(t2, j))) {
                        z = false;
                        break;
                    }
                case 8:
                    if (!(c(t, t2, i) && cb.a(cx.f(t, j), cx.f(t2, j)))) {
                        z = false;
                        break;
                    }
                case 9:
                    if (!(c(t, t2, i) && cb.a(cx.f(t, j), cx.f(t2, j)))) {
                        z = false;
                        break;
                    }
                case 10:
                    if (!(c(t, t2, i) && cb.a(cx.f(t, j), cx.f(t2, j)))) {
                        z = false;
                        break;
                    }
                case 11:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 12:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 13:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 14:
                    if (!(c(t, t2, i) && cx.b(t, j) == cx.b(t2, j))) {
                        z = false;
                        break;
                    }
                case 15:
                    if (!(c(t, t2, i) && cx.a((Object) t, j) == cx.a((Object) t2, j))) {
                        z = false;
                        break;
                    }
                case 16:
                    if (!(c(t, t2, i) && cx.b(t, j) == cx.b(t2, j))) {
                        z = false;
                        break;
                    }
                case 17:
                    if (!(c(t, t2, i) && cb.a(cx.f(t, j), cx.f(t2, j)))) {
                        z = false;
                        break;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = cb.a(cx.f(t, j), cx.f(t2, j));
                    break;
                case 50:
                    z = cb.a(cx.f(t, j), cx.f(t2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    b = c(i);
                    if (!(cx.a((Object) t, (long) (b & 1048575)) == cx.a((Object) t2, (long) (b & 1048575)) && cb.a(cx.f(t, j), cx.f(t2, j)))) {
                        z = false;
                        break;
                    }
                default:
                    z = true;
                    break;
            }
            if (!z) {
                return false;
            }
        }
        return this.q.a(t).equals(this.q.a(t2)) ? this.h ? this.r.a((Object) t).equals(this.r.a((Object) t2)) : true : false;
    }

    public final int b(T t) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Object f;
        if (this.j) {
            Unsafe unsafe = a;
            i = 0;
            i2 = 0;
            while (true) {
                i3 = i;
                if (i3 >= this.b.length) {
                    return a(this.q, (Object) t) + i2;
                }
                i = b(i3);
                i4 = (267386880 & i) >>> 20;
                i5 = this.b[i3];
                long j = (long) (i & 1048575);
                i6 = (i4 < ad.DOUBLE_LIST_PACKED.a() || i4 > ad.SINT64_LIST_PACKED.a()) ? 0 : this.b[i3 + 2] & 1048575;
                switch (i4) {
                    case 0:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.j(i5);
                        break;
                    case 1:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.i(i5);
                        break;
                    case 2:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.d(i5, cx.b(t, j));
                        break;
                    case 3:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.e(i5, cx.b(t, j));
                        break;
                    case 4:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.f(i5, cx.a((Object) t, j));
                        break;
                    case 5:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.g(i5);
                        break;
                    case 6:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.e(i5);
                        break;
                    case 7:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.k(i5);
                        break;
                    case 8:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        f = cx.f(t, j);
                        if (!(f instanceof h)) {
                            i2 += s.b(i5, (String) f);
                            break;
                        }
                        i2 += s.c(i5, (h) f);
                        break;
                    case 9:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += cb.a(i5, cx.f(t, j), a(i3));
                        break;
                    case 10:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.c(i5, (h) cx.f(t, j));
                        break;
                    case 11:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.g(i5, cx.a((Object) t, j));
                        break;
                    case 12:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.i(i5, cx.a((Object) t, j));
                        break;
                    case 13:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.f(i5);
                        break;
                    case 14:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.h(i5);
                        break;
                    case 15:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.h(i5, cx.a((Object) t, j));
                        break;
                    case 16:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.f(i5, cx.b(t, j));
                        break;
                    case 17:
                        if (!a((Object) t, i3)) {
                            break;
                        }
                        i2 += s.c(i5, (bk) cx.f(t, j), a(i3));
                        break;
                    case 18:
                        i2 += cb.i(i5, a((Object) t, j));
                        break;
                    case 19:
                        i2 += cb.h(i5, a((Object) t, j));
                        break;
                    case 20:
                        i2 += cb.a(i5, a((Object) t, j));
                        break;
                    case 21:
                        i2 += cb.b(i5, a((Object) t, j));
                        break;
                    case 22:
                        i2 += cb.e(i5, a((Object) t, j));
                        break;
                    case 23:
                        i2 += cb.i(i5, a((Object) t, j));
                        break;
                    case 24:
                        i2 += cb.h(i5, a((Object) t, j));
                        break;
                    case 25:
                        i2 += cb.j(i5, a((Object) t, j));
                        break;
                    case 26:
                        i2 += cb.k(i5, a((Object) t, j));
                        break;
                    case 27:
                        i2 += cb.a(i5, a((Object) t, j), a(i3));
                        break;
                    case 28:
                        i2 += cb.l(i5, a((Object) t, j));
                        break;
                    case 29:
                        i2 += cb.f(i5, a((Object) t, j));
                        break;
                    case 30:
                        i2 += cb.d(i5, a((Object) t, j));
                        break;
                    case 31:
                        i2 += cb.h(i5, a((Object) t, j));
                        break;
                    case 32:
                        i2 += cb.i(i5, a((Object) t, j));
                        break;
                    case 33:
                        i2 += cb.g(i5, a((Object) t, j));
                        break;
                    case 34:
                        i2 += cb.c(i5, a((Object) t, j));
                        break;
                    case 35:
                        i = cb.i((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 36:
                        i = cb.h((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 37:
                        i = cb.a((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 38:
                        i = cb.b((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 39:
                        i = cb.e((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 40:
                        i = cb.i((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 41:
                        i = cb.h((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 42:
                        i = cb.j((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 43:
                        i = cb.f((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 44:
                        i = cb.d((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 45:
                        i = cb.h((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 46:
                        i = cb.i((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 47:
                        i = cb.g((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 48:
                        i = cb.c((List) unsafe.getObject(t, j));
                        if (i > 0) {
                            if (this.k) {
                                unsafe.putInt(t, (long) i6, i);
                            }
                            i2 += i + (s.l(i5) + s.n(i));
                            break;
                        }
                        break;
                    case 49:
                        i2 += cb.b(i5, a((Object) t, j), a(i3));
                        break;
                    case 50:
                        i2 += this.s.c(cx.f(t, j));
                        break;
                    case 51:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.j(i5);
                        break;
                    case 52:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.i(i5);
                        break;
                    case 53:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.d(i5, e(t, j));
                        break;
                    case 54:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.e(i5, e(t, j));
                        break;
                    case 55:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.f(i5, d(t, j));
                        break;
                    case 56:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.g(i5);
                        break;
                    case 57:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.e(i5);
                        break;
                    case 58:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.k(i5);
                        break;
                    case 59:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        f = cx.f(t, j);
                        if (!(f instanceof h)) {
                            i2 += s.b(i5, (String) f);
                            break;
                        }
                        i2 += s.c(i5, (h) f);
                        break;
                    case 60:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += cb.a(i5, cx.f(t, j), a(i3));
                        break;
                    case 61:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.c(i5, (h) cx.f(t, j));
                        break;
                    case 62:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.g(i5, d(t, j));
                        break;
                    case 63:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.i(i5, d(t, j));
                        break;
                    case 64:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.f(i5);
                        break;
                    case 65:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.h(i5);
                        break;
                    case 66:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.h(i5, d(t, j));
                        break;
                    case 67:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.f(i5, e(t, j));
                        break;
                    case 68:
                        if (!a((Object) t, i5, i3)) {
                            break;
                        }
                        i2 += s.c(i5, (bk) cx.f(t, j), a(i3));
                        break;
                    default:
                        break;
                }
                i = i3 + 4;
            }
        } else {
            Unsafe unsafe2 = a;
            i2 = -1;
            int i7 = 0;
            i3 = 0;
            i = 0;
            while (i3 < this.b.length) {
                int b = b(i3);
                int i8 = this.b[i3];
                int i9 = (267386880 & b) >>> 20;
                i4 = 0;
                if (i9 <= 17) {
                    i5 = this.b[i3 + 2];
                    i6 = 1048575 & i5;
                    i4 = 1 << (i5 >>> 20);
                    if (i6 != i2) {
                        i = unsafe2.getInt(t, (long) i6);
                        i2 = i6;
                    }
                    i6 = i2;
                    i2 = i;
                    i = i4;
                    i4 = i5;
                } else if (!this.k || i9 < ad.DOUBLE_LIST_PACKED.a() || i9 > ad.SINT64_LIST_PACKED.a()) {
                    i6 = i2;
                    i2 = i;
                    i = 0;
                } else {
                    i4 = this.b[i3 + 2] & 1048575;
                    i6 = i2;
                    i2 = i;
                    i = 0;
                }
                long j2 = (long) (1048575 & b);
                switch (i9) {
                    case 0:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.j(i8);
                        break;
                    case 1:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.i(i8);
                        break;
                    case 2:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.d(i8, unsafe2.getLong(t, j2));
                        break;
                    case 3:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.e(i8, unsafe2.getLong(t, j2));
                        break;
                    case 4:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.f(i8, unsafe2.getInt(t, j2));
                        break;
                    case 5:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.g(i8);
                        break;
                    case 6:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.e(i8);
                        break;
                    case 7:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.k(i8);
                        break;
                    case 8:
                        if ((i & i2) == 0) {
                            break;
                        }
                        f = unsafe2.getObject(t, j2);
                        if (!(f instanceof h)) {
                            i7 += s.b(i8, (String) f);
                            break;
                        }
                        i7 += s.c(i8, (h) f);
                        break;
                    case 9:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += cb.a(i8, unsafe2.getObject(t, j2), a(i3));
                        break;
                    case 10:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.c(i8, (h) unsafe2.getObject(t, j2));
                        break;
                    case 11:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.g(i8, unsafe2.getInt(t, j2));
                        break;
                    case 12:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.i(i8, unsafe2.getInt(t, j2));
                        break;
                    case 13:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.f(i8);
                        break;
                    case 14:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.h(i8);
                        break;
                    case 15:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.h(i8, unsafe2.getInt(t, j2));
                        break;
                    case 16:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.f(i8, unsafe2.getLong(t, j2));
                        break;
                    case 17:
                        if ((i & i2) == 0) {
                            break;
                        }
                        i7 += s.c(i8, (bk) unsafe2.getObject(t, j2), a(i3));
                        break;
                    case 18:
                        i7 += cb.i(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 19:
                        i7 += cb.h(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 20:
                        i7 += cb.a(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 21:
                        i7 += cb.b(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 22:
                        i7 += cb.e(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 23:
                        i7 += cb.i(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 24:
                        i7 += cb.h(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 25:
                        i7 += cb.j(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 26:
                        i7 += cb.k(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 27:
                        i7 += cb.a(i8, (List) unsafe2.getObject(t, j2), a(i3));
                        break;
                    case 28:
                        i7 += cb.l(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 29:
                        i7 += cb.f(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 30:
                        i7 += cb.d(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 31:
                        i7 += cb.h(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 32:
                        i7 += cb.i(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 33:
                        i7 += cb.g(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 34:
                        i7 += cb.c(i8, (List) unsafe2.getObject(t, j2));
                        break;
                    case 35:
                        i = cb.i((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 36:
                        i = cb.h((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 37:
                        i = cb.a((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 38:
                        i = cb.b((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 39:
                        i = cb.e((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 40:
                        i = cb.i((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 41:
                        i = cb.h((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 42:
                        i = cb.j((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 43:
                        i = cb.f((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 44:
                        i = cb.d((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 45:
                        i = cb.h((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 46:
                        i = cb.i((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 47:
                        i = cb.g((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 48:
                        i = cb.c((List) unsafe2.getObject(t, j2));
                        if (i > 0) {
                            if (this.k) {
                                unsafe2.putInt(t, (long) i4, i);
                            }
                            i7 += i + (s.l(i8) + s.n(i));
                            break;
                        }
                        break;
                    case 49:
                        i7 += cb.b(i8, (List) unsafe2.getObject(t, j2), a(i3));
                        break;
                    case 50:
                        i7 += this.s.c(unsafe2.getObject(t, j2));
                        break;
                    case 51:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.j(i8);
                        break;
                    case 52:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.i(i8);
                        break;
                    case 53:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.d(i8, e(t, j2));
                        break;
                    case 54:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.e(i8, e(t, j2));
                        break;
                    case 55:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.f(i8, d(t, j2));
                        break;
                    case 56:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.g(i8);
                        break;
                    case 57:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.e(i8);
                        break;
                    case 58:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.k(i8);
                        break;
                    case 59:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        f = unsafe2.getObject(t, j2);
                        if (!(f instanceof h)) {
                            i7 += s.b(i8, (String) f);
                            break;
                        }
                        i7 += s.c(i8, (h) f);
                        break;
                    case 60:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += cb.a(i8, unsafe2.getObject(t, j2), a(i3));
                        break;
                    case 61:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.c(i8, (h) unsafe2.getObject(t, j2));
                        break;
                    case 62:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.g(i8, d(t, j2));
                        break;
                    case 63:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.i(i8, d(t, j2));
                        break;
                    case 64:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.f(i8);
                        break;
                    case 65:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.h(i8);
                        break;
                    case 66:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.h(i8, d(t, j2));
                        break;
                    case 67:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.f(i8, e(t, j2));
                        break;
                    case 68:
                        if (!a((Object) t, i8, i3)) {
                            break;
                        }
                        i7 += s.c(i8, (bk) unsafe2.getObject(t, j2), a(i3));
                        break;
                    default:
                        break;
                }
                i3 += 4;
                i = i2;
                i2 = i6;
            }
            i = a(this.q, (Object) t) + i7;
            return this.h ? i + this.r.a((Object) t).h() : i;
        }
    }

    public final void b(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.b.length; i += 4) {
            int b = b(i);
            long j = (long) (1048575 & b);
            int i2 = this.b[i];
            switch ((b & 267386880) >>> 20) {
                case 0:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.e(t2, j));
                    b((Object) t, i);
                    break;
                case 1:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.d(t2, j));
                    b((Object) t, i);
                    break;
                case 2:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.b(t2, j));
                    b((Object) t, i);
                    break;
                case 3:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.b(t2, j));
                    b((Object) t, i);
                    break;
                case 4:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.a((Object) t2, j));
                    b((Object) t, i);
                    break;
                case 5:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.b(t2, j));
                    b((Object) t, i);
                    break;
                case 6:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.a((Object) t2, j));
                    b((Object) t, i);
                    break;
                case 7:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.c(t2, j));
                    b((Object) t, i);
                    break;
                case 8:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.f(t2, j));
                    b((Object) t, i);
                    break;
                case 9:
                    a((Object) t, (Object) t2, i);
                    break;
                case 10:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.f(t2, j));
                    b((Object) t, i);
                    break;
                case 11:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.a((Object) t2, j));
                    b((Object) t, i);
                    break;
                case 12:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.a((Object) t2, j));
                    b((Object) t, i);
                    break;
                case 13:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.a((Object) t2, j));
                    b((Object) t, i);
                    break;
                case 14:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.b(t2, j));
                    b((Object) t, i);
                    break;
                case 15:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.a((Object) t2, j));
                    b((Object) t, i);
                    break;
                case 16:
                    if (!a((Object) t2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.b(t2, j));
                    b((Object) t, i);
                    break;
                case 17:
                    a((Object) t, (Object) t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.p.a(t, t2, j);
                    break;
                case 50:
                    cb.a(this.s, (Object) t, (Object) t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!a((Object) t2, i2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.f(t2, j));
                    b((Object) t, i2, i);
                    break;
                case 60:
                    b((Object) t, (Object) t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!a((Object) t2, i2, i)) {
                        break;
                    }
                    cx.a((Object) t, j, cx.f(t2, j));
                    b((Object) t, i2, i);
                    break;
                case 68:
                    b((Object) t, (Object) t2, i);
                    break;
                default:
                    break;
            }
        }
        if (!this.j) {
            cb.a(this.q, (Object) t, (Object) t2);
            if (this.h) {
                cb.a(this.r, (Object) t, (Object) t2);
            }
        }
    }

    public final void c(T t) {
        int i = 0;
        if (this.m != null) {
            for (int b : this.m) {
                long b2 = (long) (b(b) & 1048575);
                Object f = cx.f(t, b2);
                if (f != null) {
                    cx.a((Object) t, b2, this.s.b(f));
                }
            }
        }
        if (this.n != null) {
            int[] iArr = this.n;
            int length = iArr.length;
            while (i < length) {
                this.p.a(t, (long) iArr[i]);
                i++;
            }
        }
        this.q.b(t);
        if (this.h) {
            this.r.c(t);
        }
    }

    public final boolean d(T t) {
        if (this.l == null || this.l.length == 0) {
            return true;
        }
        int i = -1;
        int i2 = 0;
        int[] iArr = this.l;
        int length = iArr.length;
        int i3 = 0;
        while (i3 < length) {
            int i4;
            int i5;
            int i6;
            int i7;
            List list;
            bz a;
            Object obj;
            Map a2;
            bz bzVar;
            int i8 = iArr[i3];
            if (i8 >= this.d) {
                if (i8 < this.f) {
                    i4 = (i8 - this.d) << 2;
                    i5 = this.b[i4] == i8 ? i4 : -1;
                } else if (i8 <= this.e) {
                    i6 = this.f - this.d;
                    i5 = (this.b.length / 4) - 1;
                    while (i6 <= i5) {
                        i7 = (i5 + i6) >>> 1;
                        i4 = i7 << 2;
                        int i9 = this.b[i4];
                        if (i8 == i9) {
                            i5 = i4;
                        } else if (i8 < i9) {
                            i5 = i7 - 1;
                        } else {
                            i6 = i7 + 1;
                        }
                    }
                    i5 = -1;
                }
                i7 = b(i5);
                i4 = 0;
                if (!this.j) {
                    i4 = this.b[i5 + 2];
                    i6 = 1048575 & i4;
                    i4 = 1 << (i4 >>> 20);
                    if (i6 != i) {
                        i = i6;
                        i6 = a.getInt(t, (long) i6);
                        i2 = i4;
                        if (((ErrorDialogData.BINDER_CRASH & i7) == 0 ? 1 : null) == null && !a(t, i5, i6, i2)) {
                            return false;
                        }
                        switch ((267386880 & i7) >>> 20) {
                            case 9:
                            case 17:
                                if (a(t, i5, i6, i2) && !a((Object) t, i7, a(i5))) {
                                    return false;
                                }
                            case 27:
                            case 49:
                                list = (List) cx.f(t, (long) (1048575 & i7));
                                if (!list.isEmpty()) {
                                    a = a(i5);
                                    for (i5 = 0; i5 < list.size(); i5++) {
                                        if (a.d(list.get(i5))) {
                                            obj = null;
                                            if (obj != null) {
                                                break;
                                            }
                                            return false;
                                        }
                                    }
                                }
                                obj = 1;
                                if (obj != null) {
                                    return false;
                                }
                            case 50:
                                a2 = this.s.a(cx.f(t, (long) (1048575 & i7)));
                                if (!a2.isEmpty() && this.s.a().b.a() == dj.MESSAGE) {
                                    bzVar = null;
                                    for (Object next : a2.values()) {
                                        if (bzVar == null) {
                                            bzVar = bv.a().a(next.getClass());
                                        }
                                        if (!bzVar.d(next)) {
                                            obj = null;
                                            if (obj != null) {
                                                break;
                                            }
                                            return false;
                                        }
                                    }
                                }
                                obj = 1;
                                if (obj != null) {
                                    return false;
                                }
                            case 60:
                            case 68:
                                if (a((Object) t, i8, i5) && !a((Object) t, i7, a(i5))) {
                                    return false;
                                }
                            default:
                                break;
                        }
                        i3++;
                        i2 = i6;
                    }
                }
                i6 = i2;
                i2 = i4;
                if ((ErrorDialogData.BINDER_CRASH & i7) == 0) {
                }
                if (((ErrorDialogData.BINDER_CRASH & i7) == 0 ? 1 : null) == null) {
                }
                switch ((267386880 & i7) >>> 20) {
                    case 9:
                    case 17:
                        return false;
                    case 27:
                    case 49:
                        list = (List) cx.f(t, (long) (1048575 & i7));
                        if (list.isEmpty()) {
                            a = a(i5);
                            for (i5 = 0; i5 < list.size(); i5++) {
                                if (a.d(list.get(i5))) {
                                    obj = null;
                                    if (obj != null) {
                                        break;
                                    }
                                    return false;
                                }
                            }
                        }
                        obj = 1;
                        if (obj != null) {
                            return false;
                        }
                    case 50:
                        a2 = this.s.a(cx.f(t, (long) (1048575 & i7)));
                        bzVar = null;
                        for (Object next2 : a2.values()) {
                            if (bzVar == null) {
                                bzVar = bv.a().a(next2.getClass());
                            }
                            if (bzVar.d(next2)) {
                                obj = null;
                                if (obj != null) {
                                    break;
                                }
                                return false;
                            }
                        }
                        obj = 1;
                        if (obj != null) {
                            return false;
                        }
                        break;
                    case 60:
                    case 68:
                        return false;
                    default:
                        break;
                }
                i3++;
                i2 = i6;
            }
            i5 = -1;
            i7 = b(i5);
            i4 = 0;
            if (this.j) {
                i4 = this.b[i5 + 2];
                i6 = 1048575 & i4;
                i4 = 1 << (i4 >>> 20);
                if (i6 != i) {
                    i = i6;
                    i6 = a.getInt(t, (long) i6);
                    i2 = i4;
                    if ((ErrorDialogData.BINDER_CRASH & i7) == 0) {
                    }
                    if (((ErrorDialogData.BINDER_CRASH & i7) == 0 ? 1 : null) == null) {
                    }
                    switch ((267386880 & i7) >>> 20) {
                        case 9:
                        case 17:
                            return false;
                        case 27:
                        case 49:
                            list = (List) cx.f(t, (long) (1048575 & i7));
                            if (list.isEmpty()) {
                                a = a(i5);
                                for (i5 = 0; i5 < list.size(); i5++) {
                                    if (a.d(list.get(i5))) {
                                        obj = null;
                                        if (obj != null) {
                                            break;
                                        }
                                        return false;
                                    }
                                }
                            }
                            obj = 1;
                            if (obj != null) {
                                return false;
                            }
                        case 50:
                            a2 = this.s.a(cx.f(t, (long) (1048575 & i7)));
                            bzVar = null;
                            for (Object next22 : a2.values()) {
                                if (bzVar == null) {
                                    bzVar = bv.a().a(next22.getClass());
                                }
                                if (bzVar.d(next22)) {
                                    obj = null;
                                    if (obj != null) {
                                        break;
                                    }
                                    return false;
                                }
                            }
                            obj = 1;
                            if (obj != null) {
                                return false;
                            }
                            break;
                        case 60:
                        case 68:
                            return false;
                        default:
                            break;
                    }
                    i3++;
                    i2 = i6;
                }
            }
            i6 = i2;
            i2 = i4;
            if ((ErrorDialogData.BINDER_CRASH & i7) == 0) {
            }
            if (((ErrorDialogData.BINDER_CRASH & i7) == 0 ? 1 : null) == null) {
            }
            switch ((267386880 & i7) >>> 20) {
                case 9:
                case 17:
                    return false;
                case 27:
                case 49:
                    list = (List) cx.f(t, (long) (1048575 & i7));
                    if (list.isEmpty()) {
                        a = a(i5);
                        for (i5 = 0; i5 < list.size(); i5++) {
                            if (a.d(list.get(i5))) {
                                obj = null;
                                if (obj != null) {
                                    break;
                                }
                                return false;
                            }
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        return false;
                    }
                case 50:
                    a2 = this.s.a(cx.f(t, (long) (1048575 & i7)));
                    bzVar = null;
                    for (Object next222 : a2.values()) {
                        if (bzVar == null) {
                            bzVar = bv.a().a(next222.getClass());
                        }
                        if (bzVar.d(next222)) {
                            obj = null;
                            if (obj != null) {
                                break;
                            }
                            return false;
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        return false;
                    }
                    break;
                case 60:
                case 68:
                    return false;
                default:
                    break;
            }
            i3++;
            i2 = i6;
        }
        return !this.h || this.r.a((Object) t).g();
    }
}
