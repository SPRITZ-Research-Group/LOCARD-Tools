package androidx.recyclerview.widget;

import android.view.View;

public final class bg {
    public int a;
    public int b;
    public int c;
    public int d;

    public final bg a(cb cbVar) {
        View view = cbVar.itemView;
        this.a = view.getLeft();
        this.b = view.getTop();
        this.c = view.getRight();
        this.d = view.getBottom();
        return this;
    }
}
