package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemSelectedListener {
    private static final Interpolator k = new DecelerateInterpolator();
    Runnable a;
    LinearLayoutCompat b;
    int c;
    int d;
    protected ViewPropertyAnimator e;
    protected final c f = new c(this);
    private Spinner g;
    private boolean h;
    private int i;
    private int j;

    private class a extends BaseAdapter {
        final /* synthetic */ ScrollingTabContainerView a;

        a(ScrollingTabContainerView scrollingTabContainerView) {
            this.a = scrollingTabContainerView;
        }

        public final int getCount() {
            return this.a.b.getChildCount();
        }

        public final Object getItem(int position) {
            return ((b) this.a.b.getChildAt(position)).a();
        }

        public final long getItemId(int position) {
            return (long) position;
        }

        public final View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                return this.a.a((android.support.v7.app.ActionBar.b) getItem(position));
            }
            ((b) convertView).a((android.support.v7.app.ActionBar.b) getItem(position));
            return convertView;
        }
    }

    private class b extends LinearLayoutCompat {
        final /* synthetic */ ScrollingTabContainerView a;
        private final int[] b = new int[]{16842964};
        private android.support.v7.app.ActionBar.b c;
        private TextView d;
        private ImageView e;
        private View f;

        public b(ScrollingTabContainerView scrollingTabContainerView, Context context, android.support.v7.app.ActionBar.b tab) {
            this.a = scrollingTabContainerView;
            super(context, null, android.support.v7.appcompat.a.a.actionBarTabStyle);
            this.c = tab;
            aq a = aq.a(context, null, this.b, android.support.v7.appcompat.a.a.actionBarTabStyle, 0);
            if (a.h(0)) {
                setBackgroundDrawable(a.a(0));
            }
            a.a();
            setGravity(8388627);
            b();
        }

        public final void a(android.support.v7.app.ActionBar.b tab) {
            this.c = tab;
            b();
        }

        public final void setSelected(boolean selected) {
            boolean changed = isSelected() != selected;
            super.setSelected(selected);
            if (changed && selected) {
                sendAccessibilityEvent(4);
            }
        }

        public final void onInitializeAccessibilityEvent(AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(event);
            event.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (this.a.c > 0 && getMeasuredWidth() > this.a.c) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.a.c, ErrorDialogData.SUPPRESSED), heightMeasureSpec);
            }
        }

        private void b() {
            CharSequence charSequence = null;
            android.support.v7.app.ActionBar.b tab = this.c;
            View custom = tab.c();
            if (custom != null) {
                b customParent = custom.getParent();
                if (customParent != this) {
                    if (customParent != null) {
                        customParent.removeView(custom);
                    }
                    addView(custom);
                }
                this.f = custom;
                if (this.d != null) {
                    this.d.setVisibility(8);
                }
                if (this.e != null) {
                    this.e.setVisibility(8);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            android.support.v7.widget.LinearLayoutCompat.a lp;
            boolean hasText;
            if (this.f != null) {
                removeView(this.f);
                this.f = null;
            }
            Drawable icon = tab.a();
            CharSequence text = tab.b();
            if (icon != null) {
                if (this.e == null) {
                    ImageView iconView = new AppCompatImageView(getContext());
                    lp = new android.support.v7.widget.LinearLayoutCompat.a(-2, -2);
                    lp.h = 16;
                    iconView.setLayoutParams(lp);
                    addView(iconView, 0);
                    this.e = iconView;
                }
                this.e.setImageDrawable(icon);
                this.e.setVisibility(0);
            } else if (this.e != null) {
                this.e.setVisibility(8);
                this.e.setImageDrawable(null);
            }
            if (TextUtils.isEmpty(text)) {
                hasText = false;
            } else {
                hasText = true;
            }
            if (hasText) {
                if (this.d == null) {
                    TextView textView = new AppCompatTextView(getContext(), null, android.support.v7.appcompat.a.a.actionBarTabTextStyle);
                    textView.setEllipsize(TruncateAt.END);
                    lp = new android.support.v7.widget.LinearLayoutCompat.a(-2, -2);
                    lp.h = 16;
                    textView.setLayoutParams(lp);
                    addView(textView);
                    this.d = textView;
                }
                this.d.setText(text);
                this.d.setVisibility(0);
            } else if (this.d != null) {
                this.d.setVisibility(8);
                this.d.setText(null);
            }
            if (this.e != null) {
                this.e.setContentDescription(tab.d());
            }
            if (!hasText) {
                charSequence = tab.d();
            }
            as.a(this, charSequence);
        }

        public final android.support.v7.app.ActionBar.b a() {
            return this.c;
        }
    }

    protected class c extends AnimatorListenerAdapter {
        final /* synthetic */ ScrollingTabContainerView a;
        private boolean b = false;
        private int c;

        protected c(ScrollingTabContainerView this$0) {
            this.a = this$0;
        }

        public final void onAnimationStart(Animator animator) {
            this.a.setVisibility(0);
            this.b = false;
        }

        public final void onAnimationEnd(Animator animator) {
            if (!this.b) {
                this.a.e = null;
                this.a.setVisibility(this.c);
            }
        }

        public final void onAnimationCancel(Animator animator) {
            this.b = true;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        setHorizontalScrollBarEnabled(false);
        android.support.v7.view.a abp = android.support.v7.view.a.a(context);
        setContentHeight(abp.e());
        this.d = abp.g();
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, android.support.v7.appcompat.a.a.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new android.support.v7.widget.LinearLayoutCompat.a(-2, -1));
        this.b = linearLayoutCompat;
        addView(this.b, new LayoutParams(-2, -1));
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean lockedExpanded;
        int i = 1;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == ErrorDialogData.SUPPRESSED) {
            lockedExpanded = true;
        } else {
            lockedExpanded = false;
        }
        setFillViewport(lockedExpanded);
        int childCount = this.b.getChildCount();
        if (childCount <= 1 || !(widthMode == ErrorDialogData.SUPPRESSED || widthMode == Integer.MIN_VALUE)) {
            this.c = -1;
        } else {
            if (childCount > 2) {
                this.c = (int) (((float) MeasureSpec.getSize(widthMeasureSpec)) * 0.4f);
            } else {
                this.c = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
            this.c = Math.min(this.c, this.d);
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.i, ErrorDialogData.SUPPRESSED);
        if (lockedExpanded || !this.h) {
            i = 0;
        }
        if (i != 0) {
            this.b.measure(0, heightMeasureSpec);
            if (this.b.getMeasuredWidth() <= MeasureSpec.getSize(widthMeasureSpec)) {
                b();
            } else if (!a()) {
                if (this.g == null) {
                    Spinner appCompatSpinner = new AppCompatSpinner(getContext(), null, android.support.v7.appcompat.a.a.actionDropDownStyle);
                    appCompatSpinner.setLayoutParams(new android.support.v7.widget.LinearLayoutCompat.a(-2, -1));
                    appCompatSpinner.setOnItemSelectedListener(this);
                    this.g = appCompatSpinner;
                }
                removeView(this.b);
                addView(this.g, new LayoutParams(-2, -1));
                if (this.g.getAdapter() == null) {
                    this.g.setAdapter(new a(this));
                }
                if (this.a != null) {
                    removeCallbacks(this.a);
                    this.a = null;
                }
                this.g.setSelection(this.j);
            }
        } else {
            b();
        }
        int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newWidth = getMeasuredWidth();
        if (lockedExpanded && oldWidth != newWidth) {
            setTabSelected(this.j);
        }
    }

    private boolean a() {
        return this.g != null && this.g.getParent() == this;
    }

    public void setAllowCollapse(boolean allowCollapse) {
        this.h = allowCollapse;
    }

    private boolean b() {
        if (a()) {
            removeView(this.g);
            addView(this.b, new LayoutParams(-2, -1));
            setTabSelected(this.g.getSelectedItemPosition());
        }
        return false;
    }

    public void setTabSelected(int position) {
        this.j = position;
        int tabCount = this.b.getChildCount();
        int i = 0;
        while (i < tabCount) {
            View child = this.b.getChildAt(i);
            boolean isSelected = i == position;
            child.setSelected(isSelected);
            if (isSelected) {
                final View childAt = this.b.getChildAt(position);
                if (this.a != null) {
                    removeCallbacks(this.a);
                }
                this.a = new Runnable(this) {
                    final /* synthetic */ ScrollingTabContainerView b;

                    public final void run() {
                        this.b.smoothScrollTo(childAt.getLeft() - ((this.b.getWidth() - childAt.getWidth()) / 2), 0);
                        this.b.a = null;
                    }
                };
                post(this.a);
            }
            i++;
        }
        if (this.g != null && position >= 0) {
            this.g.setSelection(position);
        }
    }

    public void setContentHeight(int contentHeight) {
        this.i = contentHeight;
        requestLayout();
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        android.support.v7.view.a abp = android.support.v7.view.a.a(getContext());
        setContentHeight(abp.e());
        this.d = abp.g();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            post(this.a);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            removeCallbacks(this.a);
        }
    }

    final b a(android.support.v7.app.ActionBar.b tab) {
        b tabView = new b(this, getContext(), tab);
        tabView.setBackgroundDrawable(null);
        tabView.setLayoutParams(new AbsListView.LayoutParams(-1, this.i));
        return tabView;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
