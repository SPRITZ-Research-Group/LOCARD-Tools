package androidx.recyclerview.widget;

final class ck {
    int a = 0;
    int b;
    int c;
    int d;
    int e;

    private static int a(int i, int i2) {
        return i > i2 ? 1 : i == i2 ? 2 : 4;
    }

    ck() {
    }

    final void a(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    final void a(int i) {
        this.a = i | this.a;
    }

    final boolean a() {
        if ((this.a & 7) != 0 && (this.a & (a(this.d, this.b) << 0)) == 0) {
            return false;
        }
        if ((this.a & 112) != 0 && (this.a & (a(this.d, this.c) << 4)) == 0) {
            return false;
        }
        if ((this.a & 1792) != 0 && (this.a & (a(this.e, this.b) << 8)) == 0) {
            return false;
        }
        if ((this.a & 28672) == 0 || (this.a & (a(this.e, this.c) << 12)) != 0) {
            return true;
        }
        return false;
    }
}
