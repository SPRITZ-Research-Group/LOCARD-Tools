package defpackage;

/* renamed from: pzm */
public final class pzm<T> {
    final float a;
    int b;
    int c;
    int d;
    T[] e;

    private static int a(int i) {
        i *= -1640531527;
        return i ^ (i >>> 16);
    }

    public pzm() {
        this(16, (byte) 0);
    }

    public pzm(int i) {
        this(i, (byte) 0);
    }

    private pzm(int i, byte b) {
        this.a = 0.75f;
        i = pzn.a(i);
        this.b = i - 1;
        this.d = (int) (((float) i) * 0.75f);
        this.e = new Object[i];
    }

    public final boolean a(T t) {
        Object[] objArr = this.e;
        int i = this.b;
        int a = pzm.a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj != null) {
            if (obj.equals(t)) {
                return false;
            }
            do {
                a = (a + 1) & i;
                obj = objArr[a];
                if (obj != null) {
                }
            } while (!obj.equals(t));
            return false;
        }
        objArr[a] = t;
        int i2 = this.c + 1;
        this.c = i2;
        if (i2 >= this.d) {
            Object[] objArr2 = this.e;
            int length = objArr2.length;
            i = length << 1;
            a = i - 1;
            Object[] objArr3 = new Object[i];
            int i3 = this.c;
            while (true) {
                int i4 = i3 - 1;
                if (i3 == 0) {
                    break;
                }
                do {
                    length--;
                } while (objArr2[length] == null);
                i3 = pzm.a(objArr2[length].hashCode()) & a;
                if (objArr3[i3] != null) {
                    do {
                        i3 = (i3 + 1) & a;
                    } while (objArr3[i3] != null);
                }
                objArr3[i3] = objArr2[length];
                i3 = i4;
            }
            this.b = a;
            this.d = (int) (((float) i) * this.a);
            this.e = objArr3;
        }
        return true;
    }

    public final boolean b(T t) {
        Object[] objArr = this.e;
        int i = this.b;
        int a = pzm.a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj == null) {
            return false;
        }
        if (obj.equals(t)) {
            return a(a, objArr, i);
        }
        do {
            a = (a + 1) & i;
            obj = objArr[a];
            if (obj == null) {
                return false;
            }
        } while (!obj.equals(t));
        return a(a, objArr, i);
    }

    private boolean a(int i, T[] tArr, int i2) {
        this.c--;
        while (true) {
            Object obj;
            int i3 = i + 1;
            while (true) {
                i3 &= i2;
                obj = tArr[i3];
                if (obj == null) {
                    tArr[i] = null;
                    return true;
                }
                int a = pzm.a(obj.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= a || a > i3) {
                        break;
                    }
                    i3++;
                } else {
                    if (i >= a && a > i3) {
                        break;
                    }
                    i3++;
                }
            }
            tArr[i] = obj;
            i = i3;
        }
    }

    public final Object[] a() {
        return this.e;
    }

    public final int b() {
        return this.c;
    }
}
