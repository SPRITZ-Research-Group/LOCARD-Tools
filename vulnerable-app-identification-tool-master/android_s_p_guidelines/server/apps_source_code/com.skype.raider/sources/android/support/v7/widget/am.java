package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.a;
import android.util.TypedValue;

final class am {
    static final int[] a = new int[]{-16842910};
    static final int[] b = new int[]{16842908};
    static final int[] c = new int[]{16843518};
    static final int[] d = new int[]{16842919};
    static final int[] e = new int[]{16842912};
    static final int[] f = new int[]{16842913};
    static final int[] g = new int[]{-16842919, -16842908};
    static final int[] h = new int[0];
    private static final ThreadLocal<TypedValue> i = new ThreadLocal();
    private static final int[] j = new int[1];

    public static int a(Context context, int attr) {
        j[0] = attr;
        aq a = aq.a(context, null, j);
        try {
            int b = a.b(0, 0);
            return b;
        } finally {
            a.a();
        }
    }

    public static ColorStateList b(Context context, int attr) {
        j[0] = attr;
        aq a = aq.a(context, null, j);
        try {
            ColorStateList f = a.f(0);
            return f;
        } finally {
            a.a();
        }
    }

    public static int c(Context context, int attr) {
        ColorStateList csl = b(context, attr);
        if (csl != null && csl.isStateful()) {
            return csl.getColorForState(a, csl.getDefaultColor());
        }
        TypedValue tv;
        TypedValue typedValue = (TypedValue) i.get();
        if (typedValue == null) {
            tv = new TypedValue();
            i.set(tv);
        } else {
            tv = typedValue;
        }
        context.getTheme().resolveAttribute(16842803, tv, true);
        float disabledAlpha = tv.getFloat();
        int a = a(context, attr);
        return a.b(a, Math.round(((float) Color.alpha(a)) * disabledAlpha));
    }
}
