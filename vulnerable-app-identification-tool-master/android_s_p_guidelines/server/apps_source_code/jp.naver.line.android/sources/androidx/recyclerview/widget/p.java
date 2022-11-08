package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class p {
    private static final Comparator<v> a = new Comparator<v>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            v vVar = (v) obj;
            v vVar2 = (v) obj2;
            int i = vVar.a - vVar2.a;
            return i == 0 ? vVar.b - vVar2.b : i;
        }
    };

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static v a(q qVar, int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        q qVar2 = qVar;
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i6 <= 0 || i7 <= 0) {
            return null;
        }
        int i8 = i6 - i7;
        int i9 = 1;
        int i10 = ((i6 + i7) + 1) / 2;
        int i11 = (i5 - i10) - 1;
        int i12 = (i5 + i10) + 1;
        Arrays.fill(iArr3, i11, i12, 0);
        Arrays.fill(iArr4, i11 + i8, i12 + i8, i6);
        Object obj = i8 % 2 != 0 ? 1 : null;
        i12 = 0;
        while (i12 <= i10) {
            boolean z;
            int i13;
            int i14;
            int i15;
            v vVar;
            int i16 = -i12;
            int i17 = i16;
            while (i17 <= i12) {
                if (i17 != i16) {
                    if (i17 != i12) {
                        int i18 = i5 + i17;
                    }
                    i9 = iArr3[(i5 + i17) - 1] + 1;
                    z = true;
                    i13 = i10;
                    i10 = i9 - i17;
                    while (i9 < i6 && i10 < i7) {
                        i14 = i6;
                        i15 = i7;
                        if (qVar2.a(i + i9, i3 + i10)) {
                            break;
                        }
                        i9++;
                        i10++;
                        i6 = i14;
                        i7 = i15;
                    }
                    i14 = i6;
                    i15 = i7;
                    i6 = i5 + i17;
                    iArr3[i6] = i9;
                    if (obj != null || i17 < (i8 - i12) + 1 || i17 > (i8 + i12) - 1 || iArr3[i6] < iArr4[i6]) {
                        i17 += 2;
                        i10 = i13;
                        i6 = i14;
                        i7 = i15;
                        i9 = 1;
                    } else {
                        vVar = new v();
                        vVar.a = iArr4[i6];
                        vVar.b = vVar.a - i17;
                        vVar.c = iArr3[i6] - iArr4[i6];
                        vVar.d = z;
                        vVar.e = false;
                        return vVar;
                    }
                }
                i9 = iArr3[(i5 + i17) + 1];
                z = false;
                i13 = i10;
                i10 = i9 - i17;
                while (i9 < i6) {
                    i14 = i6;
                    i15 = i7;
                    if (qVar2.a(i + i9, i3 + i10)) {
                        break;
                    }
                    i9++;
                    i10++;
                    i6 = i14;
                    i7 = i15;
                }
                i14 = i6;
                i15 = i7;
                i6 = i5 + i17;
                iArr3[i6] = i9;
                if (obj != null) {
                }
                i17 += 2;
                i10 = i13;
                i6 = i14;
                i7 = i15;
                i9 = 1;
            }
            i14 = i6;
            i15 = i7;
            i13 = i10;
            for (i7 = i16; i7 <= i12; i7 += 2) {
                int i19;
                i10 = i7 + i8;
                if (i10 != i12 + i8) {
                    if (i10 != i16 + i8) {
                        i9 = i5 + i10;
                    }
                    i9 = iArr4[(i5 + i10) + 1] - 1;
                    z = true;
                    i17 = i9 - i10;
                    while (i9 > 0 && i17 > 0 && qVar2.a((i + i9) - 1, (i3 + i17) - 1)) {
                        i9--;
                        i17--;
                    }
                    i19 = i5 + i10;
                    iArr4[i19] = i9;
                    if (obj == null || i10 < i16 || i10 > i12 || iArr3[i19] < iArr4[i19]) {
                    } else {
                        vVar = new v();
                        vVar.a = iArr4[i19];
                        vVar.b = vVar.a - i10;
                        vVar.c = iArr3[i19] - iArr4[i19];
                        vVar.d = z;
                        vVar.e = true;
                        return vVar;
                    }
                }
                i9 = iArr4[(i5 + i10) - 1];
                z = false;
                i17 = i9 - i10;
                while (i9 > 0) {
                    i9--;
                    i17--;
                }
                i19 = i5 + i10;
                iArr4[i19] = i9;
                if (obj == null) {
                }
            }
            i12++;
            i10 = i13;
            i6 = i14;
            i7 = i15;
            i9 = 1;
        }
        throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }

    public static r a(q qVar) {
        int a = qVar.a();
        int b = qVar.b();
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList2.add(new u(a, b));
        a = Math.abs(a - b) + (a + b);
        b = a * 2;
        int[] iArr = new int[b];
        int[] iArr2 = new int[b];
        List arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            u uVar = (u) arrayList2.remove(arrayList2.size() - 1);
            v a2 = a(qVar, uVar.a, uVar.b, uVar.c, uVar.d, iArr, iArr2, a);
            if (a2 != null) {
                if (a2.c > 0) {
                    arrayList.add(a2);
                }
                a2.a += uVar.a;
                a2.b += uVar.c;
                u uVar2 = arrayList3.isEmpty() ? new u() : (u) arrayList3.remove(arrayList3.size() - 1);
                uVar2.a = uVar.a;
                uVar2.c = uVar.c;
                if (a2.e) {
                    uVar2.b = a2.a;
                    uVar2.d = a2.b;
                } else if (a2.d) {
                    uVar2.b = a2.a - 1;
                    uVar2.d = a2.b;
                } else {
                    uVar2.b = a2.a;
                    uVar2.d = a2.b - 1;
                }
                arrayList2.add(uVar2);
                if (!a2.e) {
                    uVar.a = a2.a + a2.c;
                    uVar.c = a2.b + a2.c;
                } else if (a2.d) {
                    uVar.a = (a2.a + a2.c) + 1;
                    uVar.c = a2.b + a2.c;
                } else {
                    uVar.a = a2.a + a2.c;
                    uVar.c = (a2.b + a2.c) + 1;
                }
                arrayList2.add(uVar);
            } else {
                arrayList3.add(uVar);
            }
        }
        Collections.sort(arrayList, a);
        return new r(qVar, arrayList, iArr, iArr2);
    }
}
