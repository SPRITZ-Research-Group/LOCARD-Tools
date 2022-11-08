package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.a.g;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.n;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.TintManager;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TabLayout extends HorizontalScrollView {
    private final ArrayList<c> a;
    private c b;
    private final b c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private ColorStateList i;
    private float j;
    private float k;
    private final int l;
    private int m;
    private final int n;
    private final int o;
    private final int p;
    private int q;
    private int r;
    private int s;
    private a t;
    private OnClickListener u;
    private s v;
    private s w;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    public interface a {
        void a(c cVar);
    }

    private class b extends LinearLayout {
        final /* synthetic */ TabLayout a;
        private int b;
        private final Paint c;
        private int d = -1;
        private float e;
        private int f = -1;
        private int g = -1;
        private s h;

        b(TabLayout tabLayout, Context context) {
            this.a = tabLayout;
            super(context);
            setWillNotDraw(false);
            this.c = new Paint();
        }

        final void a(int color) {
            if (this.c.getColor() != color) {
                this.c.setColor(color);
                ViewCompat.d(this);
            }
        }

        final void b(int height) {
            if (this.b != height) {
                this.b = height;
                ViewCompat.d(this);
            }
        }

        final void a(int position, float positionOffset) {
            this.d = position;
            this.e = positionOffset;
            a();
        }

        protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (MeasureSpec.getMode(widthMeasureSpec) == ErrorDialogData.SUPPRESSED && this.a.s == 1 && this.a.r == 1) {
                int i;
                int count = getChildCount();
                int largestTabWidth = 0;
                for (i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    if (child.getVisibility() == 0) {
                        largestTabWidth = Math.max(largestTabWidth, child.getMeasuredWidth());
                    }
                }
                if (largestTabWidth > 0) {
                    boolean remeasure = false;
                    if (largestTabWidth * count <= getMeasuredWidth() - (this.a.c(16) * 2)) {
                        for (i = 0; i < count; i++) {
                            LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
                            if (lp.width != largestTabWidth || lp.weight != 0.0f) {
                                lp.width = largestTabWidth;
                                lp.weight = 0.0f;
                                remeasure = true;
                            }
                        }
                    } else {
                        this.a.r = 0;
                        this.a.a(false);
                        remeasure = true;
                    }
                    if (remeasure) {
                        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                    }
                }
            }
        }

        protected final void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            if (this.h == null || !this.h.b()) {
                a();
                return;
            }
            this.h.e();
            a(this.d, Math.round((1.0f - this.h.f()) * ((float) this.h.g())));
        }

        private void a() {
            int right;
            int left;
            View selectedTitle = getChildAt(this.d);
            if (selectedTitle == null || selectedTitle.getWidth() <= 0) {
                right = -1;
                left = -1;
            } else {
                left = selectedTitle.getLeft();
                right = selectedTitle.getRight();
                if (this.e > 0.0f && this.d < getChildCount() - 1) {
                    View nextTitle = getChildAt(this.d + 1);
                    left = (int) ((this.e * ((float) nextTitle.getLeft())) + ((1.0f - this.e) * ((float) left)));
                    right = (int) ((this.e * ((float) nextTitle.getRight())) + ((1.0f - this.e) * ((float) right)));
                }
            }
            b(left, right);
        }

        private void b(int left, int right) {
            if (left != this.f || right != this.g) {
                this.f = left;
                this.g = right;
                ViewCompat.d(this);
            }
        }

        final void a(final int position, int duration) {
            int startLeft;
            int startRight;
            boolean isRtl = ViewCompat.f(this) == 1;
            View targetView = getChildAt(position);
            final int targetLeft = targetView.getLeft();
            final int targetRight = targetView.getRight();
            if (Math.abs(position - this.d) <= 1) {
                startLeft = this.f;
                startRight = this.g;
            } else {
                int offset = this.a.c(24);
                if (position < this.d) {
                    if (!isRtl) {
                        startRight = targetRight + offset;
                        startLeft = startRight;
                    }
                } else if (isRtl) {
                    startRight = targetRight + offset;
                    startLeft = startRight;
                }
                startRight = targetLeft - offset;
                startLeft = startRight;
            }
            if (startLeft != targetLeft || startRight != targetRight) {
                s animator = this.a.w = z.a();
                animator.a(a.b);
                animator.a(duration);
                animator.a(0.0f, 1.0f);
                animator.a(new c(this) {
                    final /* synthetic */ b e;

                    public final void a(s animator) {
                        float fraction = animator.f();
                        this.e.b(a.a(startLeft, targetLeft, fraction), a.a(startRight, targetRight, fraction));
                    }
                });
                animator.a(new b(this) {
                    final /* synthetic */ b b;

                    public final void a() {
                        this.b.d = position;
                        this.b.e = 0.0f;
                    }

                    public final void b() {
                        this.b.d = position;
                        this.b.e = 0.0f;
                    }
                });
                animator.a();
                this.h = animator;
            }
        }

        public final void draw(Canvas canvas) {
            super.draw(canvas);
            if (this.f >= 0 && this.g > this.f) {
                canvas.drawRect((float) this.f, (float) (getHeight() - this.b), (float) this.g, (float) getHeight(), this.c);
            }
        }
    }

    public static final class c {
        private Drawable a;
        private CharSequence b;
        private CharSequence c;
        private int d = -1;
        private View e;
        private final TabLayout f;

        c(TabLayout parent) {
            this.f = parent;
        }

        @Nullable
        public final View a() {
            return this.e;
        }

        @Nullable
        public final Drawable b() {
            return this.a;
        }

        public final int c() {
            return this.d;
        }

        final void a(int position) {
            this.d = position;
        }

        @Nullable
        public final CharSequence d() {
            return this.b;
        }

        @NonNull
        public final c e() {
            this.b = null;
            if (this.d >= 0) {
                this.f.b(this.d);
            }
            return this;
        }

        public final void f() {
            this.f.a(this);
        }

        @Nullable
        public final CharSequence g() {
            return this.c;
        }
    }

    public static class d implements android.support.v4.view.ViewPager.e {
        private final WeakReference<TabLayout> a;
        private int b;
        private int c;

        public d(TabLayout tabLayout) {
            this.a = new WeakReference(tabLayout);
        }

        public final void a(int state) {
            this.b = this.c;
            this.c = state;
        }

        public final void a(int position, float positionOffset) {
            boolean updateText = true;
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null) {
                if (!(this.c == 1 || (this.c == 2 && this.b == 1))) {
                    updateText = false;
                }
                tabLayout.setScrollPosition(position, positionOffset, updateText);
            }
        }

        public final void b(int position) {
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null && tabLayout.a() != position) {
                tabLayout.a(tabLayout.a(position), this.c == 0);
            }
        }
    }

    class e extends LinearLayout implements OnLongClickListener {
        final /* synthetic */ TabLayout a;
        private final c b;
        private TextView c;
        private ImageView d;
        private View e;
        private TextView f;
        private ImageView g;
        private int h = 2;

        public e(TabLayout tabLayout, Context context, c tab) {
            this.a = tabLayout;
            super(context);
            this.b = tab;
            if (tabLayout.l != 0) {
                setBackgroundDrawable(TintManager.getDrawable(context, tabLayout.l));
            }
            ViewCompat.b(this, tabLayout.d, tabLayout.e, tabLayout.f, tabLayout.g);
            setGravity(17);
            setOrientation(1);
            a();
        }

        public final void setSelected(boolean selected) {
            boolean changed = isSelected() != selected;
            super.setSelected(selected);
            if (changed && selected) {
                sendAccessibilityEvent(4);
                if (this.c != null) {
                    this.c.setSelected(selected);
                }
                if (this.d != null) {
                    this.d.setSelected(selected);
                }
            }
        }

        @TargetApi(14)
        public final void onInitializeAccessibilityEvent(AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(event);
            event.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        @TargetApi(14)
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        public final void onMeasure(int origWidthMeasureSpec, int origHeightMeasureSpec) {
            int widthMeasureSpec;
            int specWidthSize = MeasureSpec.getSize(origWidthMeasureSpec);
            int specWidthMode = MeasureSpec.getMode(origWidthMeasureSpec);
            int maxWidth = this.a.m;
            if (maxWidth <= 0 || (specWidthMode != 0 && specWidthSize <= maxWidth)) {
                widthMeasureSpec = origWidthMeasureSpec;
            } else {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.a.m, specWidthMode);
            }
            super.onMeasure(widthMeasureSpec, origHeightMeasureSpec);
            if (this.c != null) {
                getResources();
                float textSize = this.a.j;
                int maxLines = this.h;
                if (this.d != null && this.d.getVisibility() == 0) {
                    maxLines = 1;
                } else if (this.c != null && this.c.getLineCount() > 1) {
                    textSize = this.a.k;
                }
                float curTextSize = this.c.getTextSize();
                int curLineCount = this.c.getLineCount();
                int curMaxLines = TextViewCompat.a(this.c);
                if (textSize != curTextSize || (curMaxLines >= 0 && maxLines != curMaxLines)) {
                    boolean updateTextView = true;
                    if (this.a.s == 1 && textSize > curTextSize && curLineCount == 1) {
                        Layout layout = this.c.getLayout();
                        if (layout == null || layout.getLineWidth(0) * (textSize / layout.getPaint().getTextSize()) > ((float) layout.getWidth())) {
                            updateTextView = false;
                        }
                    }
                    if (updateTextView) {
                        this.c.setTextSize(0, textSize);
                        this.c.setMaxLines(maxLines);
                        super.onMeasure(widthMeasureSpec, origHeightMeasureSpec);
                    }
                }
            }
        }

        final void a() {
            c tab = this.b;
            View custom = tab.a();
            if (custom != null) {
                e customParent = custom.getParent();
                if (customParent != this) {
                    if (customParent != null) {
                        customParent.removeView(custom);
                    }
                    addView(custom);
                }
                this.e = custom;
                if (this.c != null) {
                    this.c.setVisibility(8);
                }
                if (this.d != null) {
                    this.d.setVisibility(8);
                    this.d.setImageDrawable(null);
                }
                this.f = (TextView) custom.findViewById(16908308);
                if (this.f != null) {
                    this.h = TextViewCompat.a(this.f);
                }
                this.g = (ImageView) custom.findViewById(16908294);
            } else {
                if (this.e != null) {
                    removeView(this.e);
                    this.e = null;
                }
                this.f = null;
                this.g = null;
            }
            if (this.e == null) {
                if (this.d == null) {
                    ImageView iconView = (ImageView) LayoutInflater.from(getContext()).inflate(g.design_layout_tab_icon, this, false);
                    addView(iconView, 0);
                    this.d = iconView;
                }
                if (this.c == null) {
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(g.design_layout_tab_text, this, false);
                    addView(textView);
                    this.c = textView;
                    this.h = TextViewCompat.a(this.c);
                }
                this.c.setTextAppearance(getContext(), this.a.h);
                if (this.a.i != null) {
                    this.c.setTextColor(this.a.i);
                }
                a(tab, this.c, this.d);
            } else if (this.f != null || this.g != null) {
                a(tab, this.f, this.g);
            }
        }

        private void a(c tab, TextView textView, ImageView iconView) {
            boolean hasText;
            Drawable icon = tab.b();
            CharSequence text = tab.d();
            if (iconView != null) {
                if (icon != null) {
                    iconView.setImageDrawable(icon);
                    iconView.setVisibility(0);
                    setVisibility(0);
                } else {
                    iconView.setVisibility(8);
                    iconView.setImageDrawable(null);
                }
                iconView.setContentDescription(tab.g());
            }
            if (TextUtils.isEmpty(text)) {
                hasText = false;
            } else {
                hasText = true;
            }
            if (textView != null) {
                if (hasText) {
                    textView.setText(text);
                    textView.setContentDescription(tab.g());
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText(null);
                }
            }
            if (iconView != null) {
                MarginLayoutParams lp = (MarginLayoutParams) iconView.getLayoutParams();
                int bottomMargin = 0;
                if (hasText && iconView.getVisibility() == 0) {
                    bottomMargin = this.a.c(8);
                }
                if (bottomMargin != lp.bottomMargin) {
                    lp.bottomMargin = bottomMargin;
                    iconView.requestLayout();
                }
            }
            if (hasText || TextUtils.isEmpty(tab.g())) {
                setOnLongClickListener(null);
                setLongClickable(false);
                return;
            }
            setOnLongClickListener(this);
        }

        public final boolean onLongClick(View v) {
            int[] screenPos = new int[2];
            getLocationOnScreen(screenPos);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            Toast cheatSheet = Toast.makeText(context, this.b.g(), 0);
            cheatSheet.setGravity(49, (screenPos[0] + (width / 2)) - (screenWidth / 2), height);
            cheatSheet.show();
            return true;
        }

        public final c b() {
            return this.b;
        }
    }

    public static class f implements a {
        private final ViewPager a;

        public f(ViewPager viewPager) {
            this.a = viewPager;
        }

        public final void a(c tab) {
            this.a.setCurrentItem(tab.c());
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.a = new ArrayList();
        this.m = Integer.MAX_VALUE;
        r.a(context);
        setHorizontalScrollBarEnabled(false);
        this.c = new b(this, context);
        addView(this.c, -2, -1);
        TypedArray a = context.obtainStyledAttributes(attrs, j.TabLayout, defStyleAttr, i.Widget_Design_TabLayout);
        this.c.b(a.getDimensionPixelSize(j.TabLayout_tabIndicatorHeight, 0));
        this.c.a(a.getColor(j.TabLayout_tabIndicatorColor, 0));
        int dimensionPixelSize = a.getDimensionPixelSize(j.TabLayout_tabPadding, 0);
        this.g = dimensionPixelSize;
        this.f = dimensionPixelSize;
        this.e = dimensionPixelSize;
        this.d = dimensionPixelSize;
        this.d = a.getDimensionPixelSize(j.TabLayout_tabPaddingStart, this.d);
        this.e = a.getDimensionPixelSize(j.TabLayout_tabPaddingTop, this.e);
        this.f = a.getDimensionPixelSize(j.TabLayout_tabPaddingEnd, this.f);
        this.g = a.getDimensionPixelSize(j.TabLayout_tabPaddingBottom, this.g);
        this.h = a.getResourceId(j.TabLayout_tabTextAppearance, i.TextAppearance_Design_Tab);
        TypedArray ta = context.obtainStyledAttributes(this.h, j.TextAppearance);
        try {
            this.j = (float) ta.getDimensionPixelSize(j.TextAppearance_android_textSize, 0);
            this.i = ta.getColorStateList(j.TextAppearance_android_textColor);
            if (a.hasValue(j.TabLayout_tabTextColor)) {
                this.i = a.getColorStateList(j.TabLayout_tabTextColor);
            }
            if (a.hasValue(j.TabLayout_tabSelectedTextColor)) {
                this.i = a(this.i.getDefaultColor(), a.getColor(j.TabLayout_tabSelectedTextColor, 0));
            }
            this.n = a.getDimensionPixelSize(j.TabLayout_tabMinWidth, -1);
            this.o = a.getDimensionPixelSize(j.TabLayout_tabMaxWidth, -1);
            this.l = a.getResourceId(j.TabLayout_tabBackground, 0);
            this.q = a.getDimensionPixelSize(j.TabLayout_tabContentStart, 0);
            this.s = a.getInt(j.TabLayout_tabMode, 1);
            this.r = a.getInt(j.TabLayout_tabGravity, 0);
            a.recycle();
            Resources res = getResources();
            this.k = (float) res.getDimensionPixelSize(android.support.design.a.d.design_tab_text_size_2line);
            this.p = res.getDimensionPixelSize(android.support.design.a.d.design_tab_scrollable_min_width);
            b();
        } finally {
            ta.recycle();
        }
    }

    public void setSelectedTabIndicatorColor(@ColorInt int color) {
        this.c.a(color);
    }

    public void setSelectedTabIndicatorHeight(int height) {
        this.c.b(height);
    }

    public void setScrollPosition(int position, float positionOffset, boolean updateSelectedText) {
        if ((this.w == null || !this.w.b()) && position >= 0 && position < this.c.getChildCount()) {
            this.c.a(position, positionOffset);
            scrollTo(a(position, positionOffset), 0);
            if (updateSelectedText) {
                e(Math.round(((float) position) + positionOffset));
            }
        }
    }

    public void setOnTabSelectedListener(a onTabSelectedListener) {
        this.t = onTabSelectedListener;
    }

    @Nullable
    public final c a(int index) {
        return (c) this.a.get(index);
    }

    public final int a() {
        return this.b != null ? this.b.c() : -1;
    }

    public void setTabMode(int mode) {
        if (mode != this.s) {
            this.s = mode;
            b();
        }
    }

    public void setTabGravity(int gravity) {
        if (this.r != gravity) {
            this.r = gravity;
            b();
        }
    }

    public void setTabTextColors(@Nullable ColorStateList textColor) {
        if (this.i != textColor) {
            this.i = textColor;
            int childCount = this.c.getChildCount();
            for (int i = 0; i < childCount; i++) {
                b(i);
            }
        }
    }

    public void setTabTextColors(int normalColor, int selectedColor) {
        setTabTextColors(a(normalColor, selectedColor));
    }

    public void setupWithViewPager(@NonNull ViewPager viewPager) {
        n adapter = viewPager.a();
        if (adapter == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }
        setTabsFromPagerAdapter(adapter);
        viewPager.a(new d(this));
        setOnTabSelectedListener(new f(viewPager));
        if (adapter.a() > 0) {
            int curItem = viewPager.b();
            if (a() != curItem) {
                a(a(curItem), true);
            }
        }
    }

    public void setTabsFromPagerAdapter(@NonNull n adapter) {
        this.c.removeAllViews();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(-1);
            it.remove();
        }
        this.b = null;
        int count = adapter.a();
        for (int i = 0; i < count; i++) {
            c e = new c(this).e();
            boolean isEmpty = this.a.isEmpty();
            if (e.f != this) {
                throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
            }
            View eVar = new e(this, getContext(), e);
            eVar.setFocusable(true);
            eVar.setMinimumWidth(c());
            if (this.u == null) {
                this.u = new OnClickListener(this) {
                    final /* synthetic */ TabLayout a;

                    {
                        this.a = r1;
                    }

                    public final void onClick(View view) {
                        ((e) view).b().f();
                    }
                };
            }
            eVar.setOnClickListener(this.u);
            b bVar = this.c;
            LayoutParams layoutParams = new LayoutParams(-2, -1);
            a(layoutParams);
            bVar.addView(eVar, layoutParams);
            if (isEmpty) {
                eVar.setSelected(true);
            }
            a(e, this.a.size());
            if (isEmpty) {
                e.f();
            }
        }
    }

    private void a(c tab, int position) {
        tab.a(position);
        this.a.add(position, tab);
        int count = this.a.size();
        for (int i = position + 1; i < count; i++) {
            ((c) this.a.get(i)).a(i);
        }
    }

    private void a(LayoutParams lp) {
        if (this.s == 1 && this.r == 0) {
            lp.width = 0;
            lp.weight = 1.0f;
            return;
        }
        lp.width = -2;
        lp.weight = 0.0f;
    }

    private int c(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * ((float) dps));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = (c) this.a.get(i2);
            if (cVar != null && cVar.b() != null && !TextUtils.isEmpty(cVar.d())) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i != 0) {
            i = 72;
        } else {
            i = 48;
        }
        int idealHeight = (c(i) + getPaddingTop()) + getPaddingBottom();
        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case Integer.MIN_VALUE:
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.min(idealHeight, MeasureSpec.getSize(heightMeasureSpec)), ErrorDialogData.SUPPRESSED);
                break;
            case 0:
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(idealHeight, ErrorDialogData.SUPPRESSED);
                break;
        }
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) != 0) {
            if (this.o > 0) {
                i = this.o;
            } else {
                i = specWidth - c(56);
            }
            this.m = i;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 1) {
            View child = getChildAt(0);
            boolean remeasure = false;
            switch (this.s) {
                case 0:
                    if (child.getMeasuredWidth() >= getMeasuredWidth()) {
                        remeasure = false;
                        break;
                    } else {
                        remeasure = true;
                        break;
                    }
                case 1:
                    if (child.getMeasuredWidth() == getMeasuredWidth()) {
                        remeasure = false;
                        break;
                    } else {
                        remeasure = true;
                        break;
                    }
            }
            if (remeasure) {
                child.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), ErrorDialogData.SUPPRESSED), getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), child.getLayoutParams().height));
            }
        }
    }

    private void d(int newPosition) {
        boolean z = false;
        if (newPosition != -1) {
            if (getWindowToken() != null && ViewCompat.B(this)) {
                b bVar = this.c;
                int childCount = bVar.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    if (bVar.getChildAt(i).getWidth() <= 0) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    int startScrollX = getScrollX();
                    int targetScrollX = a(newPosition, 0.0f);
                    if (startScrollX != targetScrollX) {
                        if (this.v == null) {
                            this.v = z.a();
                            this.v.a(a.b);
                            this.v.a(300);
                            this.v.a(new c(this) {
                                final /* synthetic */ TabLayout a;

                                {
                                    this.a = r1;
                                }

                                public final void a(s animator) {
                                    this.a.scrollTo(animator.c(), 0);
                                }
                            });
                        }
                        this.v.a(startScrollX, targetScrollX);
                        this.v.a();
                    }
                    this.c.a(newPosition, 300);
                    return;
                }
            }
            setScrollPosition(newPosition, 0.0f, true);
        }
    }

    private void e(int position) {
        int tabCount = this.c.getChildCount();
        if (position < tabCount && !this.c.getChildAt(position).isSelected()) {
            int i = 0;
            while (i < tabCount) {
                this.c.getChildAt(i).setSelected(i == position);
                i++;
            }
        }
    }

    final void a(c tab) {
        a(tab, true);
    }

    final void a(c tab, boolean updateIndicator) {
        if (this.b != tab) {
            if (updateIndicator) {
                int newPosition = tab != null ? tab.c() : -1;
                if (newPosition != -1) {
                    e(newPosition);
                }
                if ((this.b == null || this.b.c() == -1) && newPosition != -1) {
                    setScrollPosition(newPosition, 0.0f, true);
                } else {
                    d(newPosition);
                }
            }
            this.b = tab;
            if (this.b != null && this.t != null) {
                this.t.a(this.b);
            }
        } else if (this.b != null) {
            d(tab.c());
        }
    }

    private int a(int position, float positionOffset) {
        int nextWidth = 0;
        if (this.s != 0) {
            return 0;
        }
        int selectedWidth;
        View selectedChild = this.c.getChildAt(position);
        View nextChild = position + 1 < this.c.getChildCount() ? this.c.getChildAt(position + 1) : null;
        if (selectedChild != null) {
            selectedWidth = selectedChild.getWidth();
        } else {
            selectedWidth = 0;
        }
        if (nextChild != null) {
            nextWidth = nextChild.getWidth();
        }
        return ((selectedChild.getLeft() + ((int) ((((float) (selectedWidth + nextWidth)) * positionOffset) * 0.5f))) + (selectedChild.getWidth() / 2)) - (getWidth() / 2);
    }

    private void b() {
        int paddingStart = 0;
        if (this.s == 0) {
            paddingStart = Math.max(0, this.q - this.d);
        }
        ViewCompat.b(this.c, paddingStart, 0, 0, 0);
        switch (this.s) {
            case 0:
                this.c.setGravity(8388611);
                break;
            case 1:
                this.c.setGravity(1);
                break;
        }
        a(true);
    }

    private void a(boolean requestLayout) {
        for (int i = 0; i < this.c.getChildCount(); i++) {
            View child = this.c.getChildAt(i);
            child.setMinimumWidth(c());
            a((LayoutParams) child.getLayoutParams());
            if (requestLayout) {
                child.requestLayout();
            }
        }
    }

    private static ColorStateList a(int defaultColor, int selectedColor) {
        states = new int[2][];
        int[] colors = new int[]{SELECTED_STATE_SET, selectedColor};
        states[1] = EMPTY_STATE_SET;
        colors[1] = defaultColor;
        return new ColorStateList(states, colors);
    }

    private int c() {
        if (this.n != -1) {
            return this.n;
        }
        return this.s == 0 ? this.p : 0;
    }

    private void b(int position) {
        e view = (e) this.c.getChildAt(position);
        if (view != null) {
            view.a();
        }
    }
}
