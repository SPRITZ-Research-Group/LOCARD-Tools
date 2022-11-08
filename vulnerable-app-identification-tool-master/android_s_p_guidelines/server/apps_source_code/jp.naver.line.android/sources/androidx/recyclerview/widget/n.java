package androidx.recyclerview.widget;

final class n {
    public cb a;
    public cb b;
    public int c;
    public int d;
    public int e;
    public int f;

    private n(cb cbVar, cb cbVar2) {
        this.a = cbVar;
        this.b = cbVar2;
    }

    n(cb cbVar, cb cbVar2, int i, int i2, int i3, int i4) {
        this(cbVar, cbVar2);
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("ChangeInfo{oldHolder=");
        stringBuilder.append(this.a);
        stringBuilder.append(", newHolder=");
        stringBuilder.append(this.b);
        stringBuilder.append(", fromX=");
        stringBuilder.append(this.c);
        stringBuilder.append(", fromY=");
        stringBuilder.append(this.d);
        stringBuilder.append(", toX=");
        stringBuilder.append(this.e);
        stringBuilder.append(", toY=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
