package android.support.v7.widget;

final class ah {
    private int a = 0;
    private int b = 0;
    private int c = Integer.MIN_VALUE;
    private int d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    ah() {
    }

    public final int a() {
        return this.g ? this.b : this.a;
    }

    public final int b() {
        return this.g ? this.a : this.b;
    }

    public final void a(int start, int end) {
        this.c = start;
        this.d = end;
        this.h = true;
        if (this.g) {
            if (end != Integer.MIN_VALUE) {
                this.a = end;
            }
            if (start != Integer.MIN_VALUE) {
                this.b = start;
                return;
            }
            return;
        }
        if (start != Integer.MIN_VALUE) {
            this.a = start;
        }
        if (end != Integer.MIN_VALUE) {
            this.b = end;
        }
    }

    public final void b(int left, int right) {
        this.h = false;
        if (left != Integer.MIN_VALUE) {
            this.e = left;
            this.a = left;
        }
        if (right != Integer.MIN_VALUE) {
            this.f = right;
            this.b = right;
        }
    }

    public final void a(boolean isRtl) {
        if (isRtl != this.g) {
            this.g = isRtl;
            if (!this.h) {
                this.a = this.e;
                this.b = this.f;
            } else if (isRtl) {
                this.a = this.d != Integer.MIN_VALUE ? this.d : this.e;
                this.b = this.c != Integer.MIN_VALUE ? this.c : this.f;
            } else {
                this.a = this.c != Integer.MIN_VALUE ? this.c : this.e;
                this.b = this.d != Integer.MIN_VALUE ? this.d : this.f;
            }
        }
    }
}
