package com.skype.slimcore.video;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.ViewGroup;
import com.facebook.react.views.view.e;
import javax.annotation.Nullable;

public class RNBackgroundDrawable {
    @Nullable
    private e a;

    public final void a(ViewGroup view, float borderRadius) {
        a(view).a(borderRadius);
    }

    public final void a(ViewGroup view, int color) {
        if (color != 0 || this.a != null) {
            a(view).a(color);
        }
    }

    private e a(ViewGroup view) {
        if (this.a == null) {
            view.getContext();
            this.a = new e((byte) 0);
            Drawable backgroundDrawable = view.getBackground();
            view.setBackground(null);
            if (backgroundDrawable == null) {
                view.setBackground(this.a);
            } else {
                view.setBackground(new LayerDrawable(new Drawable[]{this.a, backgroundDrawable}));
            }
        }
        return this.a;
    }
}
