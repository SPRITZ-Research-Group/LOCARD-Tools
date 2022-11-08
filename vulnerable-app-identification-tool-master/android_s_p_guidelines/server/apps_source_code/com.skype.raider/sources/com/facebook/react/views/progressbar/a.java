package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.adjust.sdk.Constants;
import com.facebook.react.bridge.n;
import javax.annotation.Nullable;

final class a extends FrameLayout {
    @Nullable
    private Integer a;
    private boolean b = true;
    private boolean c = true;
    private double d;
    @Nullable
    private ProgressBar e;

    public a(Context context) {
        super(context);
    }

    public final void a(@Nullable String styleName) {
        this.e = ReactProgressBarViewManager.createProgressBar(getContext(), ReactProgressBarViewManager.getStyleFromString(styleName));
        this.e.setMax(Constants.ONE_SECOND);
        removeAllViews();
        addView(this.e, new LayoutParams(-1, -1));
    }

    public final void a(@Nullable Integer color) {
        this.a = color;
    }

    public final void a(boolean indeterminate) {
        this.b = indeterminate;
    }

    public final void a(double progress) {
        this.d = progress;
    }

    public final void b(boolean animating) {
        this.c = animating;
    }

    public final void a() {
        if (this.e == null) {
            throw new n("setStyle() not called");
        }
        Drawable indeterminateDrawable;
        this.e.setIndeterminate(this.b);
        ProgressBar progressBar = this.e;
        if (progressBar.isIndeterminate()) {
            indeterminateDrawable = progressBar.getIndeterminateDrawable();
        } else {
            indeterminateDrawable = progressBar.getProgressDrawable();
        }
        if (indeterminateDrawable != null) {
            if (this.a != null) {
                indeterminateDrawable.setColorFilter(this.a.intValue(), Mode.SRC_IN);
            } else {
                indeterminateDrawable.clearColorFilter();
            }
        }
        this.e.setProgress((int) (this.d * 1000.0d));
        if (this.c) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }
}
