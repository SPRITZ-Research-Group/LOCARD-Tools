package androidx.recyclerview.widget;

import android.util.SparseArray;

public final class by {
    int a = -1;
    int b = 0;
    int c = 0;
    int d = 1;
    int e = 0;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    boolean i = false;
    boolean j = false;
    boolean k = false;
    int l;
    long m;
    int n;
    int o;
    int p;
    private SparseArray<Object> q;

    final void a(int i) {
        if ((this.d & i) == 0) {
            StringBuilder stringBuilder = new StringBuilder("Layout state should be one of ");
            stringBuilder.append(Integer.toBinaryString(i));
            stringBuilder.append(" but it is ");
            stringBuilder.append(Integer.toBinaryString(this.d));
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.g ? this.b - this.c : this.e;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("State{mTargetPosition=");
        stringBuilder.append(this.a);
        stringBuilder.append(", mData=");
        stringBuilder.append(this.q);
        stringBuilder.append(", mItemCount=");
        stringBuilder.append(this.e);
        stringBuilder.append(", mIsMeasuring=");
        stringBuilder.append(this.i);
        stringBuilder.append(", mPreviousLayoutItemCount=");
        stringBuilder.append(this.b);
        stringBuilder.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        stringBuilder.append(this.c);
        stringBuilder.append(", mStructureChanged=");
        stringBuilder.append(this.f);
        stringBuilder.append(", mInPreLayout=");
        stringBuilder.append(this.g);
        stringBuilder.append(", mRunSimpleAnimations=");
        stringBuilder.append(this.j);
        stringBuilder.append(", mRunPredictiveAnimations=");
        stringBuilder.append(this.k);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
