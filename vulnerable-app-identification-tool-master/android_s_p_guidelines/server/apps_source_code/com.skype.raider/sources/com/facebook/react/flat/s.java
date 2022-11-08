package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.w;

abstract class s extends r {
    private int d;
    private int f;

    protected abstract void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, boolean z);

    protected abstract void b(SpannableStringBuilder spannableStringBuilder);

    s() {
    }

    protected void a(boolean shouldRemeasure) {
        w parent = C();
        if (parent instanceof s) {
            ((s) parent).a(shouldRemeasure);
        }
    }

    public boolean a() {
        return true;
    }

    final void a(SpannableStringBuilder builder) {
        this.d = builder.length();
        b(builder);
        this.f = builder.length();
    }

    boolean l() {
        return false;
    }

    boolean m() {
        return false;
    }

    final void a(SpannableStringBuilder builder, boolean isEditable) {
        if (this.d != this.f || l()) {
            a(builder, this.d, this.f, isEditable);
        }
    }
}
