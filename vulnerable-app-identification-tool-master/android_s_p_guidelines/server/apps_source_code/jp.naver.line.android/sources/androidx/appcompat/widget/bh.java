package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.a;
import defpackage.m;

final class bh extends LinearLayout {
    final /* synthetic */ ScrollingTabContainerView a;
    private final int[] b = new int[]{16842964};
    private a c;
    private TextView d;
    private ImageView e;
    private View f;

    public bh(ScrollingTabContainerView scrollingTabContainerView, Context context, a aVar) {
        this.a = scrollingTabContainerView;
        super(context, null, m.actionBarTabStyle);
        this.c = aVar;
        bu a = bu.a(context, null, this.b, m.actionBarTabStyle, 0);
        if (a.h(0)) {
            setBackgroundDrawable(a.a(0));
        }
        a.a();
        setGravity(8388627);
        b();
    }

    public final void a(a aVar) {
        this.c = aVar;
        b();
    }

    public final void setSelected(boolean z) {
        Object obj = isSelected() != z ? 1 : null;
        super.setSelected(z);
        if (obj != null && z) {
            sendAccessibilityEvent(4);
        }
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(a.class.getName());
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(a.class.getName());
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a.c > 0 && getMeasuredWidth() > this.a.c) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(this.a.c, 1073741824), i2);
        }
    }

    private void b() {
        a aVar = this.c;
        View c = aVar.c();
        CharSequence charSequence = null;
        if (c != null) {
            bh parent = c.getParent();
            if (parent != this) {
                if (parent != null) {
                    parent.removeView(c);
                }
                addView(c);
            }
            this.f = c;
            if (this.d != null) {
                this.d.setVisibility(8);
            }
            if (this.e != null) {
                this.e.setVisibility(8);
                this.e.setImageDrawable(null);
            }
            return;
        }
        if (this.f != null) {
            removeView(this.f);
            this.f = null;
        }
        Drawable a = aVar.a();
        CharSequence b = aVar.b();
        if (a != null) {
            if (this.e == null) {
                View appCompatImageView = new AppCompatImageView(getContext());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 16;
                appCompatImageView.setLayoutParams(layoutParams);
                addView(appCompatImageView, 0);
                this.e = appCompatImageView;
            }
            this.e.setImageDrawable(a);
            this.e.setVisibility(0);
        } else if (this.e != null) {
            this.e.setVisibility(8);
            this.e.setImageDrawable(null);
        }
        int isEmpty = TextUtils.isEmpty(b) ^ 1;
        if (isEmpty != 0) {
            if (this.d == null) {
                View appCompatTextView = new AppCompatTextView(getContext(), null, m.actionBarTabTextStyle);
                appCompatTextView.setEllipsize(TruncateAt.END);
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.gravity = 16;
                appCompatTextView.setLayoutParams(layoutParams2);
                addView(appCompatTextView);
                this.d = appCompatTextView;
            }
            this.d.setText(b);
            this.d.setVisibility(0);
        } else if (this.d != null) {
            this.d.setVisibility(8);
            this.d.setText(null);
        }
        if (this.e != null) {
            this.e.setContentDescription(aVar.d());
        }
        if (isEmpty == 0) {
            charSequence = aVar.d();
        }
        by.a(this, charSequence);
    }

    public final a a() {
        return this.c;
    }
}
