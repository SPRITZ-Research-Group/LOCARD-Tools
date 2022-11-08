package com.google.android.gms.internal.clearcut;

public final class do implements Cloneable {
    private static final dp a = new dp();
    private boolean b;
    private int[] c;
    private dp[] d;
    private int e;

    do() {
        this(10);
    }

    private do(int i) {
        this.b = false;
        int i2 = i << 2;
        for (int i3 = 4; i3 < 32; i3++) {
            if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            }
        }
        i2 /= 4;
        this.c = new int[i2];
        this.d = new dp[i2];
        this.e = 0;
    }

    public final boolean a() {
        return this.e == 0;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.e;
        do doVar = new do(i);
        System.arraycopy(this.c, 0, doVar.c, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.d[i2] != null) {
                doVar.d[i2] = (dp) this.d[i2].clone();
            }
        }
        doVar.e = i;
        return doVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof do)) {
            return false;
        }
        do doVar = (do) obj;
        if (this.e != doVar.e) {
            return false;
        }
        int i;
        boolean z;
        int[] iArr = this.c;
        int[] iArr2 = doVar.c;
        int i2 = this.e;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != iArr2[i]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            dp[] dpVarArr = this.d;
            dp[] dpVarArr2 = doVar.d;
            i2 = this.e;
            for (i = 0; i < i2; i++) {
                if (!dpVarArr[i].equals(dpVarArr2[i])) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.e; i2++) {
            i = (((i * 31) + this.c[i2]) * 31) + this.d[i2].hashCode();
        }
        return i;
    }
}
