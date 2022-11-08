package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class r {
    private final List<v> a;
    private final int[] b;
    private final int[] c;
    private final q d;
    private final int e;
    private final int f;
    private final boolean g = true;

    r(q qVar, List<v> list, int[] iArr, int[] iArr2) {
        this.a = list;
        this.b = iArr;
        this.c = iArr2;
        Arrays.fill(this.b, 0);
        Arrays.fill(this.c, 0);
        this.d = qVar;
        this.e = qVar.a();
        this.f = qVar.b();
        v vVar = this.a.isEmpty() ? null : (v) this.a.get(0);
        if (!(vVar != null && vVar.a == 0 && vVar.b == 0)) {
            vVar = new v();
            vVar.a = 0;
            vVar.b = 0;
            vVar.d = false;
            vVar.c = 0;
            vVar.e = false;
            this.a.add(0, vVar);
        }
        a();
    }

    private void a() {
        int i = this.e;
        int i2 = this.f;
        for (int size = this.a.size() - 1; size >= 0; size--) {
            v vVar = (v) this.a.get(size);
            int i3 = vVar.a + vVar.c;
            int i4 = vVar.b + vVar.c;
            int i5 = 0;
            if (this.g) {
                while (i > i3) {
                    if (this.b[i - 1] == 0) {
                        a(i, i2, size, false);
                    }
                    i--;
                }
                while (i2 > i4) {
                    if (this.c[i2 - 1] == 0) {
                        a(i, i2, size, true);
                    }
                    i2--;
                }
            }
            while (i5 < vVar.c) {
                i = vVar.a + i5;
                i2 = vVar.b + i5;
                i3 = this.d.b(i, i2) ? 1 : 2;
                this.b[i] = (i2 << 5) | i3;
                this.c[i2] = (i << 5) | i3;
                i5++;
            }
            i = vVar.a;
            i2 = vVar.b;
        }
    }

    private boolean a(int i, int i2, int i3, boolean z) {
        int i4;
        int i5;
        if (z) {
            i2--;
            i4 = i;
            i5 = i2;
        } else {
            i4 = i - 1;
            i5 = i4;
        }
        while (i3 >= 0) {
            v vVar = (v) this.a.get(i3);
            int i6 = vVar.a + vVar.c;
            int i7 = vVar.b + vVar.c;
            int i8 = 4;
            if (z) {
                for (i4--; i4 >= i6; i4--) {
                    if (this.d.a(i4, i5)) {
                        if (this.d.b(i4, i5)) {
                            i8 = 8;
                        }
                        this.c[i5] = (i4 << 5) | 16;
                        this.b[i4] = (i5 << 5) | i8;
                        return true;
                    }
                }
                continue;
            } else {
                for (i2--; i2 >= i7; i2--) {
                    if (this.d.a(i5, i2)) {
                        if (this.d.b(i5, i2)) {
                            i8 = 8;
                        }
                        i--;
                        this.b[i] = (i2 << 5) | 16;
                        this.c[i2] = (i << 5) | i8;
                        return true;
                    }
                }
                continue;
            }
            i4 = vVar.a;
            i2 = vVar.b;
            i3--;
        }
        return false;
    }

    public final void a(az azVar) {
        a(new d(azVar));
    }

    public final void a(au auVar) {
        i iVar;
        if (auVar instanceof i) {
            iVar = (i) auVar;
        } else {
            iVar = new i(auVar);
        }
        ArrayList arrayList = new ArrayList();
        int i = this.e;
        int i2 = this.f;
        for (int size = this.a.size() - 1; size >= 0; size--) {
            v vVar = (v) this.a.get(size);
            int i3 = vVar.c;
            int i4 = vVar.a + i3;
            int i5 = vVar.b + i3;
            if (i4 < i) {
                b(arrayList, iVar, i4, i - i4, i4);
            }
            if (i5 < i2) {
                a(arrayList, iVar, i4, i2 - i5, i5);
            }
            for (i3--; i3 >= 0; i3--) {
                if ((this.b[vVar.a + i3] & 31) == 2) {
                    iVar.a(vVar.a + i3, 1, this.d.c(vVar.a + i3, vVar.b + i3));
                }
            }
            i = vVar.a;
            i2 = vVar.b;
        }
        iVar.a();
    }

    private static t a(List<t> list, int i, boolean z) {
        int size = list.size() - 1;
        while (size >= 0) {
            t tVar = (t) list.get(size);
            if (tVar.a == i && tVar.c == z) {
                list.remove(size);
                while (size < list.size()) {
                    t tVar2 = (t) list.get(size);
                    tVar2.b += z ? 1 : -1;
                    size++;
                }
                return tVar;
            }
            size--;
        }
        return null;
    }

    private void a(List<t> list, au auVar, int i, int i2, int i3) {
        if (this.g) {
            for (i2--; i2 >= 0; i2--) {
                int i4 = i3 + i2;
                int i5 = this.c[i4] & 31;
                if (i5 == 0) {
                    auVar.a(i, 1);
                    for (t tVar : list) {
                        tVar.b++;
                    }
                } else if (i5 == 4 || i5 == 8) {
                    int i6 = this.c[i4] >> 5;
                    auVar.c(a(list, i6, true).b, i);
                    if (i5 == 4) {
                        auVar.a(i, 1, this.d.c(i6, i4));
                    }
                } else if (i5 == 16) {
                    list.add(new t(i4, i, false));
                } else {
                    StringBuilder stringBuilder = new StringBuilder("unknown flag for pos ");
                    stringBuilder.append(i4);
                    stringBuilder.append(" ");
                    stringBuilder.append(Long.toBinaryString((long) i5));
                    throw new IllegalStateException(stringBuilder.toString());
                }
            }
            return;
        }
        auVar.a(i, i2);
    }

    private void b(List<t> list, au auVar, int i, int i2, int i3) {
        if (this.g) {
            for (i2--; i2 >= 0; i2--) {
                int i4 = i3 + i2;
                int i5 = this.b[i4] & 31;
                if (i5 == 0) {
                    auVar.b(i + i2, 1);
                    for (t tVar : list) {
                        tVar.b--;
                    }
                } else if (i5 == 4 || i5 == 8) {
                    int i6 = this.b[i4] >> 5;
                    t a = a(list, i6, false);
                    auVar.c(i + i2, a.b - 1);
                    if (i5 == 4) {
                        auVar.a(a.b - 1, 1, this.d.c(i4, i6));
                    }
                } else if (i5 == 16) {
                    list.add(new t(i4, i + i2, true));
                } else {
                    StringBuilder stringBuilder = new StringBuilder("unknown flag for pos ");
                    stringBuilder.append(i4);
                    stringBuilder.append(" ");
                    stringBuilder.append(Long.toBinaryString((long) i5));
                    throw new IllegalStateException(stringBuilder.toString());
                }
            }
            return;
        }
        auVar.b(i, i2);
    }
}
