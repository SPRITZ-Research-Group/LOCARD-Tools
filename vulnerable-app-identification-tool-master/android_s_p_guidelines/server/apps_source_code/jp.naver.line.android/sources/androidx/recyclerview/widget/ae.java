package androidx.recyclerview.widget;

import android.util.SparseIntArray;

public abstract class ae {
    final SparseIntArray a = new SparseIntArray();
    private boolean b = false;

    public abstract int a(int i);

    final int b(int i, int i2) {
        if (!this.b) {
            return a(i, i2);
        }
        int i3 = this.a.get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        i2 = a(i, i2);
        this.a.put(i, i2);
        return i2;
    }

    public int a(int i, int i2) {
        int a = a(i);
        if (a == i2) {
            return 0;
        }
        int size;
        int i3;
        int i4;
        if (this.b && this.a.size() > 0) {
            size = this.a.size() - 1;
            i3 = 0;
            while (i3 <= size) {
                i4 = (i3 + size) >>> 1;
                if (this.a.keyAt(i4) < i) {
                    i3 = i4 + 1;
                } else {
                    size = i4 - 1;
                }
            }
            i3--;
            i3 = (i3 < 0 || i3 >= this.a.size()) ? -1 : this.a.keyAt(i3);
            if (i3 >= 0) {
                size = this.a.get(i3) + a(i3);
                i3++;
                while (i3 < i) {
                    i4 = a(i3);
                    size += i4;
                    if (size == i2) {
                        size = 0;
                    } else if (size > i2) {
                        size = i4;
                    }
                    i3++;
                }
                if (a + size > i2) {
                    return size;
                }
                return 0;
            }
        }
        i3 = 0;
        size = 0;
        while (i3 < i) {
            i4 = a(i3);
            size += i4;
            if (size == i2) {
                size = 0;
            } else if (size > i2) {
                size = i4;
            }
            i3++;
        }
        if (a + size > i2) {
            return 0;
        }
        return size;
    }

    public final int c(int i, int i2) {
        int a = a(i);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int a2 = a(i5);
            i3 += a2;
            if (i3 == i2) {
                i4++;
                i3 = 0;
            } else if (i3 > i2) {
                i4++;
                i3 = a2;
            }
        }
        return i3 + a > i2 ? i4 + 1 : i4;
    }
}
