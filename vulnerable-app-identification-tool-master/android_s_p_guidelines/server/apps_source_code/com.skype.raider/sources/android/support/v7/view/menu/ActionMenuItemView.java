package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.a.j;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.as;
import android.support.v7.widget.w;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class ActionMenuItemView extends AppCompatTextView implements android.support.v7.view.menu.o.a, android.support.v7.widget.ActionMenuView.a, OnClickListener {
    i a;
    android.support.v7.view.menu.g.b b;
    b c;
    private CharSequence d;
    private Drawable e;
    private w f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;

    private class a extends w {
        final /* synthetic */ ActionMenuItemView a;

        public a(ActionMenuItemView actionMenuItemView) {
            this.a = actionMenuItemView;
            super(actionMenuItemView);
        }

        public final r a() {
            if (this.a.c != null) {
                return this.a.c.a();
            }
            return null;
        }

        protected final boolean b() {
            if (this.a.b == null || !this.a.b.a(this.a.a)) {
                return false;
            }
            r popup = a();
            if (popup == null || !popup.f()) {
                return false;
            }
            return true;
        }
    }

    public static abstract class b {
        public abstract r a();
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Resources res = context.getResources();
        this.g = h();
        TypedArray a = context.obtainStyledAttributes(attrs, j.ActionMenuItemView, defStyle, 0);
        this.i = a.getDimensionPixelSize(j.ActionMenuItemView_android_minWidth, 0);
        a.recycle();
        this.k = (int) ((32.0f * res.getDisplayMetrics().density) + 0.5f);
        setOnClickListener(this);
        this.j = -1;
        setSaveEnabled(false);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.g = h();
        i();
    }

    private boolean h() {
        Configuration config = getContext().getResources().getConfiguration();
        int widthDp = config.screenWidthDp;
        return widthDp >= 480 || ((widthDp >= 640 && config.screenHeightDp >= 480) || config.orientation == 2);
    }

    public void setPadding(int l, int t, int r, int b) {
        this.j = l;
        super.setPadding(l, t, r, b);
    }

    public final i b() {
        return this.a;
    }

    public final void a(i itemData) {
        this.a = itemData;
        setIcon(itemData.getIcon());
        setTitle(itemData.a((android.support.v7.view.menu.o.a) this));
        setId(itemData.getItemId());
        setVisibility(itemData.isVisible() ? 0 : 8);
        setEnabled(itemData.isEnabled());
        if (itemData.hasSubMenu() && this.f == null) {
            this.f = new a(this);
        }
    }

    public boolean onTouchEvent(MotionEvent e) {
        if (this.a.hasSubMenu() && this.f != null && this.f.onTouch(this, e)) {
            return true;
        }
        return super.onTouchEvent(e);
    }

    public void onClick(View v) {
        if (this.b != null) {
            this.b.a(this.a);
        }
    }

    public void setItemInvoker(android.support.v7.view.menu.g.b invoker) {
        this.b = invoker;
    }

    public void setPopupCallback(b popupCallback) {
        this.c = popupCallback;
    }

    public final boolean c() {
        return true;
    }

    public void setCheckable(boolean checkable) {
    }

    public void setChecked(boolean checked) {
    }

    public void setExpandedFormat(boolean expandedFormat) {
        if (this.h != expandedFormat) {
            this.h = expandedFormat;
            if (this.a != null) {
                this.a.a.j();
            }
        }
    }

    private void i() {
        int i;
        CharSequence charSequence;
        int i2 = 0;
        CharSequence charSequence2 = null;
        if (TextUtils.isEmpty(this.d)) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.e == null || (this.a.k() && (this.g || this.h))) {
            i2 = 1;
        }
        boolean visible = i & i2;
        if (visible) {
            charSequence = this.d;
        } else {
            charSequence = null;
        }
        setText(charSequence);
        CharSequence contentDescription = this.a.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(visible ? null : this.a.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.a.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (!visible) {
                charSequence2 = this.a.getTitle();
            }
            as.a(this, charSequence2);
            return;
        }
        as.a(this, tooltipText);
    }

    public void setIcon(Drawable icon) {
        this.e = icon;
        if (icon != null) {
            float scale;
            int width = icon.getIntrinsicWidth();
            int height = icon.getIntrinsicHeight();
            if (width > this.k) {
                scale = ((float) this.k) / ((float) width);
                width = this.k;
                height = (int) (((float) height) * scale);
            }
            if (height > this.k) {
                scale = ((float) this.k) / ((float) height);
                height = this.k;
                width = (int) (((float) width) * scale);
            }
            icon.setBounds(0, 0, width, height);
        }
        setCompoundDrawables(icon, null, null, null);
        i();
    }

    public final boolean e() {
        return !TextUtils.isEmpty(getText());
    }

    public void setShortcut(boolean showShortcut, char shortcutKey) {
    }

    public void setTitle(CharSequence title) {
        this.d = title;
        i();
    }

    public final boolean f() {
        return e() && this.a.getIcon() == null;
    }

    public final boolean g() {
        return e();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean textVisible = e();
        if (textVisible && this.j >= 0) {
            super.setPadding(this.j, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int oldMeasuredWidth = getMeasuredWidth();
        int targetWidth = widthMode == Integer.MIN_VALUE ? Math.min(widthSize, this.i) : this.i;
        if (widthMode != ErrorDialogData.SUPPRESSED && this.i > 0 && oldMeasuredWidth < targetWidth) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(targetWidth, ErrorDialogData.SUPPRESSED), heightMeasureSpec);
        }
        if (!textVisible && this.e != null) {
            super.setPadding((getMeasuredWidth() - this.e.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(null);
    }
}
