package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.appcompat.a.d;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.g;
import android.support.v7.appcompat.a.i;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

@RestrictTo({a.LIBRARY_GROUP})
final class au {
    private final Context a;
    private final View b;
    private final TextView c;
    private final LayoutParams d = new LayoutParams();
    private final Rect e = new Rect();
    private final int[] f = new int[2];
    private final int[] g = new int[2];

    au(Context context) {
        this.a = context;
        this.b = LayoutInflater.from(this.a).inflate(g.tooltip, null);
        this.c = (TextView) this.b.findViewById(f.message);
        this.d.setTitle(getClass().getSimpleName());
        this.d.packageName = this.a.getPackageName();
        this.d.type = 1002;
        this.d.width = -2;
        this.d.height = -2;
        this.d.format = -3;
        this.d.windowAnimations = i.Animation_AppCompat_Tooltip;
        this.d.flags = 24;
    }

    final void a(View anchorView, int anchorX, int anchorY, boolean fromTouch, CharSequence tooltipText) {
        int i;
        int i2;
        View decorView;
        if (b()) {
            a();
        }
        this.c.setText(tooltipText);
        LayoutParams layoutParams = this.d;
        int dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(d.tooltip_precise_anchor_threshold);
        if (anchorView.getWidth() < dimensionPixelOffset) {
            anchorX = anchorView.getWidth() / 2;
        }
        if (anchorView.getHeight() >= dimensionPixelOffset) {
            dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(d.tooltip_precise_anchor_extra_offset);
            i = anchorY + dimensionPixelOffset;
            i2 = anchorY - dimensionPixelOffset;
        } else {
            i = anchorView.getHeight();
            i2 = 0;
        }
        layoutParams.gravity = 49;
        Resources resources = this.a.getResources();
        if (fromTouch) {
            dimensionPixelOffset = d.tooltip_y_offset_touch;
        } else {
            dimensionPixelOffset = d.tooltip_y_offset_non_touch;
        }
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(dimensionPixelOffset);
        for (Context context = anchorView.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                decorView = ((Activity) context).getWindow().getDecorView();
                break;
            }
        }
        decorView = anchorView.getRootView();
        if (decorView != null) {
            decorView.getWindowVisibleDisplayFrame(this.e);
            if (this.e.left < 0 && this.e.top < 0) {
                Resources resources2 = this.a.getResources();
                dimensionPixelOffset = resources2.getIdentifier("status_bar_height", "dimen", "android");
                if (dimensionPixelOffset != 0) {
                    dimensionPixelOffset = resources2.getDimensionPixelSize(dimensionPixelOffset);
                } else {
                    dimensionPixelOffset = 0;
                }
                DisplayMetrics displayMetrics = resources2.getDisplayMetrics();
                this.e.set(0, dimensionPixelOffset, displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
            decorView.getLocationOnScreen(this.g);
            anchorView.getLocationOnScreen(this.f);
            int[] iArr = this.f;
            iArr[0] = iArr[0] - this.g[0];
            iArr = this.f;
            iArr[1] = iArr[1] - this.g[1];
            layoutParams.x = (this.f[0] + anchorX) - (this.e.width() / 2);
            dimensionPixelOffset = MeasureSpec.makeMeasureSpec(0, 0);
            this.b.measure(dimensionPixelOffset, dimensionPixelOffset);
            dimensionPixelOffset = this.b.getMeasuredHeight();
            i2 = ((i2 + this.f[1]) - dimensionPixelOffset2) - dimensionPixelOffset;
            i = (i + this.f[1]) + dimensionPixelOffset2;
            if (fromTouch) {
                if (i2 < 0) {
                    layoutParams.y = i;
                }
            } else if (dimensionPixelOffset + i <= this.e.height()) {
                layoutParams.y = i;
            }
            layoutParams.y = i2;
        }
        ((WindowManager) this.a.getSystemService("window")).addView(this.b, this.d);
    }

    final void a() {
        if (b()) {
            ((WindowManager) this.a.getSystemService("window")).removeView(this.b);
        }
    }

    private boolean b() {
        return this.b.getParent() != null;
    }
}
