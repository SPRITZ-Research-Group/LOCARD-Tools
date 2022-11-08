package androidx.databinding;

public final class x {
    public final String[][] a;
    public final int[][] b;
    public final int[][] c;

    public x(int i) {
        this.a = new String[i][];
        this.b = new int[i][];
        this.c = new int[i][];
    }

    public final void a(int i, String[] strArr, int[] iArr, int[] iArr2) {
        this.a[i] = strArr;
        this.b[i] = iArr;
        this.c[i] = iArr2;
    }
}
