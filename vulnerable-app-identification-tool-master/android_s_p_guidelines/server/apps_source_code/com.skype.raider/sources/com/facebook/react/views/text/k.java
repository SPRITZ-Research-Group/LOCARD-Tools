package com.facebook.react.views.text;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ReplacementSpan;
import android.widget.TextView;
import javax.annotation.Nullable;

public abstract class k extends ReplacementSpan {
    @Nullable
    public abstract Drawable a();

    public abstract void a(TextView textView);

    public abstract void b();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public static void a(Spannable spannable, TextView view) {
        int i = 0;
        k[] kVarArr = (k[]) spannable.getSpans(0, spannable.length(), k.class);
        int length = kVarArr.length;
        while (i < length) {
            k span = kVarArr[i];
            span.d();
            span.a(view);
            i++;
        }
    }
}
