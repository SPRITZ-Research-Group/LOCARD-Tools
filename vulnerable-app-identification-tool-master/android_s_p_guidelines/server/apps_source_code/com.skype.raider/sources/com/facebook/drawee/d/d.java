package com.facebook.drawee.d;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.drawee.c.g;
import com.facebook.drawee.c.t;
import com.facebook.drawee.c.u;
import javax.annotation.Nullable;

public final class d extends g implements t {
    @VisibleForTesting
    @Nullable
    Drawable a = null;
    @Nullable
    private u c;

    public d(Drawable drawable) {
        super(drawable);
    }

    public final int getIntrinsicWidth() {
        return -1;
    }

    public final int getIntrinsicHeight() {
        return -1;
    }

    public final void a(@Nullable u visibilityCallback) {
        this.c = visibilityCallback;
    }

    public final boolean setVisible(boolean visible, boolean restart) {
        if (this.c != null) {
            this.c.a(visible);
        }
        return super.setVisible(visible, restart);
    }

    @SuppressLint({"WrongCall"})
    public final void draw(Canvas canvas) {
        if (isVisible()) {
            if (this.c != null) {
                this.c.a();
            }
            super.draw(canvas);
            if (this.a != null) {
                this.a.setBounds(getBounds());
                this.a.draw(canvas);
            }
        }
    }
}
