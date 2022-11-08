package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.a.b;

final class r {
    private static final int[] a = new int[]{b.colorPrimary};

    static void a(Context context) {
        boolean failed = false;
        TypedArray a = context.obtainStyledAttributes(a);
        if (!a.hasValue(0)) {
            failed = true;
        }
        if (a != null) {
            a.recycle();
        }
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        }
    }
}
