package defpackage;

/* renamed from: aciu */
public final class aciu {
    private static volatile aciu a;
    private final StringBuilder[] b = new StringBuilder[7];
    private final int c = 512;
    private int d = 0;

    private aciu() {
    }

    public static aciu a() {
        aciu aciu = a;
        if (aciu != null) {
            return aciu;
        }
        aciu = new aciu();
        a = aciu;
        return aciu;
    }

    public final StringBuilder b() {
        StringBuilder stringBuilder;
        synchronized (this) {
            if (this.d > 0) {
                StringBuilder[] stringBuilderArr = this.b;
                int i = this.d - 1;
                this.d = i;
                stringBuilder = stringBuilderArr[i];
                this.b[this.d] = null;
            } else {
                stringBuilder = null;
            }
        }
        if (stringBuilder == null) {
            return new StringBuilder(this.c);
        }
        stringBuilder.setLength(0);
        return stringBuilder;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(StringBuilder stringBuilder) {
        if (stringBuilder != null && this.c >= stringBuilder.capacity() && this.d < this.b.length) {
            synchronized (this) {
                if (this.d < this.b.length) {
                    StringBuilder[] stringBuilderArr = this.b;
                    int i = this.d;
                    this.d = i + 1;
                    stringBuilderArr[i] = stringBuilder;
                }
            }
        }
    }
}
