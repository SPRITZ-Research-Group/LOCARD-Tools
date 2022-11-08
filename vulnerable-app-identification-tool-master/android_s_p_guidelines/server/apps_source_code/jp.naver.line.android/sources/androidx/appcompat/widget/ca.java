package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import defpackage.p;
import defpackage.r;
import defpackage.s;
import defpackage.u;

final class ca {
    private final Context a;
    private final View b;
    private final TextView c;
    private final LayoutParams d = new LayoutParams();
    private final Rect e = new Rect();
    private final int[] f = new int[2];
    private final int[] g = new int[2];

    ca(Context context) {
        this.a = context;
        this.b = LayoutInflater.from(this.a).inflate(s.abc_tooltip, null);
        this.c = (TextView) this.b.findViewById(r.message);
        this.d.setTitle(getClass().getSimpleName());
        this.d.packageName = this.a.getPackageName();
        this.d.type = 1002;
        this.d.width = -2;
        this.d.height = -2;
        this.d.format = -3;
        this.d.windowAnimations = u.Animation_AppCompat_Tooltip;
        this.d.flags = 24;
    }

    final void a(View view, int i, int i2, boolean z, CharSequence charSequence) {
        int i3;
        if (b()) {
            a();
        }
        this.c.setText(charSequence);
        LayoutParams layoutParams = this.d;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(p.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(p.tooltip_precise_anchor_extra_offset);
            i3 = i2 + dimensionPixelOffset;
            i2 -= dimensionPixelOffset;
        } else {
            i3 = view.getHeight();
            i2 = 0;
        }
        layoutParams.gravity = 49;
        dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(z ? p.tooltip_y_offset_touch : p.tooltip_y_offset_non_touch);
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
        if (!(layoutParams2 instanceof LayoutParams) || ((LayoutParams) layoutParams2).type != 2) {
            for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
                if (context instanceof Activity) {
                    rootView = ((Activity) context).getWindow().getDecorView();
                    break;
                }
            }
        }
        if (rootView == null) {
            Log.e("TooltipPopup", "Cannot find app view");
        } else {
            rootView.getWindowVisibleDisplayFrame(this.e);
            if (this.e.left < 0 && this.e.top < 0) {
                Resources resources = this.a.getResources();
                int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                identifier = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                this.e.set(0, identifier, displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
            rootView.getLocationOnScreen(this.g);
            view.getLocationOnScreen(this.f);
            int[] iArr = this.f;
            iArr[0] = iArr[0] - this.g[0];
            iArr = this.f;
            iArr[1] = iArr[1] - this.g[1];
            layoutParams.x = (this.f[0] + i) - (rootView.getWidth() / 2);
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            this.b.measure(makeMeasureSpec, makeMeasureSpec);
            makeMeasureSpec = this.b.getMeasuredHeight();
            i = ((this.f[1] + i2) - dimensionPixelOffset) - makeMeasureSpec;
            i2 = (this.f[1] + i3) + dimensionPixelOffset;
            if (z) {
                if (i < 0) {
                    layoutParams.y = i2;
                }
            } else if (makeMeasureSpec + i2 <= this.e.height()) {
                layoutParams.y = i2;
            }
            layoutParams.y = i;
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
