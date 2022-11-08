package com.facebook.react.views.switchview;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;

final class a extends SwitchCompat {
    private boolean b = true;

    public a(Context context) {
        super(context);
    }

    public final void setChecked(boolean checked) {
        if (this.b) {
            this.b = false;
            super.setChecked(checked);
        }
    }

    final void a(boolean on) {
        if (isChecked() != on) {
            super.setChecked(on);
        }
        this.b = true;
    }
}
