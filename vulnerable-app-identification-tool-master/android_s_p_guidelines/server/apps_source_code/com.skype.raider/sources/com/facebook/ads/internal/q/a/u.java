package com.facebook.ads.internal.q.a;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicInteger;

public final class u {
    public static final DisplayMetrics a;
    public static final float b;
    private static final AtomicInteger c = new AtomicInteger(1);

    static {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        a = displayMetrics;
        b = displayMetrics.density;
    }

    public static int a() {
        int i;
        int i2;
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }

    public static void a(View view) {
        if (VERSION.SDK_INT >= 17) {
            view.setId(View.generateViewId());
        } else {
            view.setId(a());
        }
    }

    public static void a(View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(new ColorDrawable(i));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(i));
        }
    }

    public static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(TextView textView, boolean z, int i) {
        Typeface create = z ? VERSION.SDK_INT >= 21 ? Typeface.create("sans-serif-medium", 0) : Typeface.create(Typeface.SANS_SERIF, 1) : Typeface.create(Typeface.SANS_SERIF, 0);
        textView.setTypeface(create);
        textView.setTextSize(2, (float) i);
    }

    public static void a(View... viewArr) {
        for (View b : viewArr) {
            b(b);
        }
    }

    public static int b() {
        return (int) TypedValue.applyDimension(2, 16.0f, a);
    }

    public static void b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }
}
