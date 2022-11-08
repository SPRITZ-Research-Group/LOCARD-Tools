package androidx.recyclerview.widget;

import android.view.View;

final class an {
    boolean a = true;
    int b;
    int c;
    int d;
    int e;
    int f = 0;
    int g = 0;
    boolean h;
    boolean i;

    an() {
    }

    final boolean a(by byVar) {
        return this.c >= 0 && this.c < byVar.b();
    }

    final View a(bs bsVar) {
        View d = bsVar.d(this.c);
        this.c += this.d;
        return d;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("LayoutState{mAvailable=");
        stringBuilder.append(this.b);
        stringBuilder.append(", mCurrentPosition=");
        stringBuilder.append(this.c);
        stringBuilder.append(", mItemDirection=");
        stringBuilder.append(this.d);
        stringBuilder.append(", mLayoutDirection=");
        stringBuilder.append(this.e);
        stringBuilder.append(", mStartLine=");
        stringBuilder.append(this.f);
        stringBuilder.append(", mEndLine=");
        stringBuilder.append(this.g);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
