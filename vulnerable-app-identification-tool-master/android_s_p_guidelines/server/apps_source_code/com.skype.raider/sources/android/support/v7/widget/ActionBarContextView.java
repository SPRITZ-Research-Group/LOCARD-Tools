package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.s;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.g;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.b;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({a.LIBRARY_GROUP})
public class ActionBarContextView extends a {
    private CharSequence g;
    private CharSequence h;
    private View i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private boolean p;
    private int q;

    public final /* bridge */ /* synthetic */ s a(int i, long j) {
        return super.a(i, j);
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.a.a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        aq a = aq.a(context, attrs, j.ActionMode, defStyle, 0);
        ViewCompat.a((View) this, a.a(j.ActionMode_background));
        this.n = a.g(j.ActionMode_titleTextStyle, 0);
        this.o = a.g(j.ActionMode_subtitleTextStyle, 0);
        this.e = a.f(j.ActionMode_height, 0);
        this.q = a.g(j.ActionMode_closeItemLayout, g.abc_action_mode_close_item_material);
        a.a();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.h();
            this.d.j();
        }
    }

    public void setContentHeight(int height) {
        this.e = height;
    }

    public void setCustomView(View view) {
        if (this.j != null) {
            removeView(this.j);
        }
        this.j = view;
        if (!(view == null || this.k == null)) {
            removeView(this.k);
            this.k = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence title) {
        this.g = title;
        g();
    }

    public void setSubtitle(CharSequence subtitle) {
        this.h = subtitle;
        g();
    }

    public final CharSequence b() {
        return this.g;
    }

    public final CharSequence c() {
        return this.h;
    }

    private void g() {
        boolean hasTitle;
        boolean hasSubtitle;
        int i;
        int i2 = 8;
        if (this.k == null) {
            LayoutInflater.from(getContext()).inflate(g.abc_action_bar_title_item, this);
            this.k = (LinearLayout) getChildAt(getChildCount() - 1);
            this.l = (TextView) this.k.findViewById(f.action_bar_title);
            this.m = (TextView) this.k.findViewById(f.action_bar_subtitle);
            if (this.n != 0) {
                this.l.setTextAppearance(getContext(), this.n);
            }
            if (this.o != 0) {
                this.m.setTextAppearance(getContext(), this.o);
            }
        }
        this.l.setText(this.g);
        this.m.setText(this.h);
        if (TextUtils.isEmpty(this.g)) {
            hasTitle = false;
        } else {
            hasTitle = true;
        }
        if (TextUtils.isEmpty(this.h)) {
            hasSubtitle = false;
        } else {
            hasSubtitle = true;
        }
        TextView textView = this.m;
        if (hasSubtitle) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        LinearLayout linearLayout = this.k;
        if (hasTitle || hasSubtitle) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        if (this.k.getParent() == null) {
            addView(this.k);
        }
    }

    public final void a(final b mode) {
        if (this.i == null) {
            this.i = LayoutInflater.from(getContext()).inflate(this.q, this, false);
            addView(this.i);
        } else if (this.i.getParent() == null) {
            addView(this.i);
        }
        this.i.findViewById(f.action_mode_close_button).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ActionBarContextView b;

            public final void onClick(View v) {
                mode.c();
            }
        });
        android.support.v7.view.menu.g menu = (android.support.v7.view.menu.g) mode.b();
        if (this.d != null) {
            this.d.i();
        }
        this.d = new ActionMenuPresenter(getContext());
        this.d.f();
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        menu.a(this.d, this.b);
        this.c = (ActionMenuView) this.d.a((ViewGroup) this);
        ViewCompat.a(this.c, null);
        addView(this.c, layoutParams);
    }

    public final void d() {
        if (this.i == null) {
            e();
        }
    }

    public final void e() {
        removeAllViews();
        this.j = null;
        this.c = null;
    }

    public final boolean a() {
        if (this.d != null) {
            return this.d.g();
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (MeasureSpec.getMode(heightMeasureSpec) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        } else {
            int maxHeight;
            int contentWidth = MeasureSpec.getSize(widthMeasureSpec);
            if (this.e > 0) {
                maxHeight = this.e;
            } else {
                maxHeight = MeasureSpec.getSize(heightMeasureSpec);
            }
            int verticalPadding = getPaddingTop() + getPaddingBottom();
            int availableWidth = (contentWidth - getPaddingLeft()) - getPaddingRight();
            int height = maxHeight - verticalPadding;
            int childSpecHeight = MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE);
            if (this.i != null) {
                MarginLayoutParams lp = (MarginLayoutParams) this.i.getLayoutParams();
                availableWidth = a.a(this.i, availableWidth, childSpecHeight) - (lp.leftMargin + lp.rightMargin);
            }
            if (this.c != null && this.c.getParent() == this) {
                availableWidth = a.a(this.c, availableWidth, childSpecHeight);
            }
            if (this.k != null && this.j == null) {
                if (this.p) {
                    this.k.measure(MeasureSpec.makeMeasureSpec(0, 0), childSpecHeight);
                    int titleWidth = this.k.getMeasuredWidth();
                    boolean titleFits = titleWidth <= availableWidth;
                    if (titleFits) {
                        availableWidth -= titleWidth;
                    }
                    this.k.setVisibility(titleFits ? 0 : 8);
                } else {
                    availableWidth = a.a(this.k, availableWidth, childSpecHeight);
                }
            }
            if (this.j != null) {
                int customWidth;
                int customHeight;
                LayoutParams lp2 = this.j.getLayoutParams();
                int customWidthMode = lp2.width != -2 ? ErrorDialogData.SUPPRESSED : Integer.MIN_VALUE;
                if (lp2.width >= 0) {
                    customWidth = Math.min(lp2.width, availableWidth);
                } else {
                    customWidth = availableWidth;
                }
                int customHeightMode = lp2.height != -2 ? ErrorDialogData.SUPPRESSED : Integer.MIN_VALUE;
                if (lp2.height >= 0) {
                    customHeight = Math.min(lp2.height, height);
                } else {
                    customHeight = height;
                }
                this.j.measure(MeasureSpec.makeMeasureSpec(customWidth, customWidthMode), MeasureSpec.makeMeasureSpec(customHeight, customHeightMode));
            }
            if (this.e <= 0) {
                int measuredHeight = 0;
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    int paddedViewHeight = getChildAt(i).getMeasuredHeight() + verticalPadding;
                    if (paddedViewHeight > measuredHeight) {
                        measuredHeight = paddedViewHeight;
                    }
                }
                setMeasuredDimension(contentWidth, measuredHeight);
                return;
            }
            setMeasuredDimension(contentWidth, maxHeight);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        boolean isLayoutRtl = ax.a(this);
        int x = isLayoutRtl ? (r - l) - getPaddingRight() : getPaddingLeft();
        int y = getPaddingTop();
        int contentHeight = ((b - t) - getPaddingTop()) - getPaddingBottom();
        if (!(this.i == null || this.i.getVisibility() == 8)) {
            MarginLayoutParams lp = (MarginLayoutParams) this.i.getLayoutParams();
            int startMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
            int endMargin = isLayoutRtl ? lp.leftMargin : lp.rightMargin;
            x = a.a(x, startMargin, isLayoutRtl);
            x = a.a(a.a(this.i, x, y, contentHeight, isLayoutRtl) + x, endMargin, isLayoutRtl);
        }
        if (!(this.k == null || this.j != null || this.k.getVisibility() == 8)) {
            x += a.a(this.k, x, y, contentHeight, isLayoutRtl);
        }
        if (this.j != null) {
            a.a(this.j, x, y, contentHeight, isLayoutRtl);
        }
        x = isLayoutRtl ? getPaddingLeft() : (r - l) - getPaddingRight();
        if (this.c != null) {
            a.a(this.c, x, y, contentHeight, !isLayoutRtl);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 32) {
            event.setSource(this);
            event.setClassName(getClass().getName());
            event.setPackageName(getContext().getPackageName());
            event.setContentDescription(this.g);
            return;
        }
        super.onInitializeAccessibilityEvent(event);
    }

    public void setTitleOptional(boolean titleOptional) {
        if (titleOptional != this.p) {
            requestLayout();
        }
        this.p = titleOptional;
    }

    public final boolean f() {
        return this.p;
    }
}
