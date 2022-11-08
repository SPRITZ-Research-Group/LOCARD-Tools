package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(17)
final class n extends m {
    private ao b;
    private ao c;

    n(TextView view) {
        super(view);
    }

    final void a(AttributeSet attrs, int defStyleAttr) {
        super.a(attrs, defStyleAttr);
        Context context = this.a.getContext();
        h drawableManager = h.a();
        TypedArray a = context.obtainStyledAttributes(attrs, j.AppCompatTextHelper, defStyleAttr, 0);
        if (a.hasValue(j.AppCompatTextHelper_android_drawableStart)) {
            this.b = m.a(context, drawableManager, a.getResourceId(j.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (a.hasValue(j.AppCompatTextHelper_android_drawableEnd)) {
            this.c = m.a(context, drawableManager, a.getResourceId(j.AppCompatTextHelper_android_drawableEnd, 0));
        }
        a.recycle();
    }

    final void a() {
        super.a();
        if (this.b != null || this.c != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawablesRelative();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[2], this.c);
        }
    }
}
