package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.widget.l;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;

@d
public class PagerTitleStrip extends ViewGroup {
    private static final int[] n = new int[]{16842804, 16842901, 16842904, 16842927};
    private static final int[] o = new int[]{16843660};
    ViewPager a;
    TextView b;
    TextView c;
    TextView d;
    float e;
    int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private final b l;
    private WeakReference<a> m;
    private int p;

    private static void a(TextView textView) {
        textView.setTransformationMethod(new c(textView.getContext()));
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = -1;
        this.e = -1.0f;
        this.l = new b(this);
        View textView = new TextView(context);
        this.b = textView;
        addView(textView);
        textView = new TextView(context);
        this.c = textView;
        addView(textView);
        textView = new TextView(context);
        this.d = textView;
        addView(textView);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, n);
        boolean z = false;
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            l.a(this.b, resourceId);
            l.a(this.c, resourceId);
            l.a(this.d, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, (float) dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            dimensionPixelSize = obtainStyledAttributes.getColor(2, 0);
            this.b.setTextColor(dimensionPixelSize);
            this.c.setTextColor(dimensionPixelSize);
            this.d.setTextColor(dimensionPixelSize);
        }
        this.i = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.f = this.c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.b.setEllipsize(TruncateAt.END);
        this.c.setEllipsize(TruncateAt.END);
        this.d.setEllipsize(TruncateAt.END);
        if (resourceId != 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(resourceId, o);
            z = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
        }
        if (z) {
            a(this.b);
            a(this.c);
            a(this.d);
        } else {
            this.b.setSingleLine();
            this.c.setSingleLine();
            this.d.setSingleLine();
        }
        this.h = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    public void setTextSpacing(int i) {
        this.h = i;
        requestLayout();
    }

    public final int b() {
        return this.h;
    }

    public void setNonPrimaryAlpha(float f) {
        this.p = ((int) (f * 255.0f)) & 255;
        int i = (this.p << 24) | (this.f & 16777215);
        this.b.setTextColor(i);
        this.d.setTextColor(i);
    }

    public void setTextColor(int i) {
        this.f = i;
        this.c.setTextColor(i);
        i = (this.p << 24) | (this.f & 16777215);
        this.b.setTextColor(i);
        this.d.setTextColor(i);
    }

    public void setTextSize(int i, float f) {
        this.b.setTextSize(i, f);
        this.c.setTextSize(i, f);
        this.d.setTextSize(i, f);
    }

    public void setGravity(int i) {
        this.i = i;
        requestLayout();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            a adapter = viewPager.getAdapter();
            viewPager.setInternalPageChangeListener(this.l);
            viewPager.addOnAdapterChangeListener(this.l);
            this.a = viewPager;
            a(this.m != null ? (a) this.m.get() : null, adapter);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            a(this.a.getAdapter(), null);
            this.a.setInternalPageChangeListener(null);
            this.a.removeOnAdapterChangeListener(this.l);
            this.a = null;
        }
    }

    final void a(int i, a aVar) {
        int count = aVar != null ? aVar.getCount() : 0;
        this.j = true;
        CharSequence charSequence = null;
        CharSequence pageTitle = (i <= 0 || aVar == null) ? null : aVar.getPageTitle(i - 1);
        this.b.setText(pageTitle);
        TextView textView = this.c;
        CharSequence pageTitle2 = (aVar == null || i >= count) ? null : aVar.getPageTitle(i);
        textView.setText(pageTitle2);
        int i2 = i + 1;
        if (i2 < count && aVar != null) {
            charSequence = aVar.getPageTitle(i2);
        }
        this.d.setText(charSequence);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f)), Integer.MIN_VALUE);
        count = MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.b.measure(makeMeasureSpec, count);
        this.c.measure(makeMeasureSpec, count);
        this.d.measure(makeMeasureSpec, count);
        this.g = i;
        if (!this.k) {
            a(i, this.e, false);
        }
        this.j = false;
    }

    public void requestLayout() {
        if (!this.j) {
            super.requestLayout();
        }
    }

    final void a(a aVar, a aVar2) {
        if (aVar != null) {
            aVar.unregisterDataSetObserver(this.l);
            this.m = null;
        }
        if (aVar2 != null) {
            aVar2.registerDataSetObserver(this.l);
            this.m = new WeakReference(aVar2);
        }
        if (this.a != null) {
            this.g = -1;
            this.e = -1.0f;
            a(this.a.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    void a(int i, float f, boolean z) {
        int i2 = i;
        float f2 = f;
        if (i2 != this.g) {
            a(i2, r0.a.getAdapter());
        } else if (!z && f2 == r0.e) {
            return;
        }
        r0.k = true;
        i2 = r0.b.getMeasuredWidth();
        int measuredWidth = r0.c.getMeasuredWidth();
        int measuredWidth2 = r0.d.getMeasuredWidth();
        int i3 = measuredWidth / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i4 = paddingRight + i3;
        int i5 = (width - (paddingLeft + i3)) - i4;
        float f3 = 0.5f + f2;
        if (f3 > 1.0f) {
            f3 -= 1.0f;
        }
        i4 = ((width - i4) - ((int) (((float) i5) * f3))) - i3;
        measuredWidth += i4;
        i3 = r0.b.getBaseline();
        i5 = r0.c.getBaseline();
        int baseline = r0.d.getBaseline();
        int max = Math.max(Math.max(i3, i5), baseline);
        i3 = max - i3;
        i5 = max - i5;
        max -= baseline;
        int i6 = measuredWidth2;
        measuredWidth2 = r0.d.getMeasuredHeight() + max;
        int max2 = Math.max(Math.max(r0.b.getMeasuredHeight() + i3, r0.c.getMeasuredHeight() + i5), measuredWidth2);
        measuredWidth2 = r0.i & 112;
        if (measuredWidth2 == 16) {
            height = (((height - paddingTop) - paddingBottom) - max2) / 2;
            i3 += height;
            i5 += height;
            paddingTop = height + max;
        } else if (measuredWidth2 != 80) {
            i3 += paddingTop;
            i5 += paddingTop;
            paddingTop += max;
        } else {
            height = (height - paddingBottom) - max2;
            i3 += height;
            i5 += height;
            paddingTop = height + max;
        }
        r0.c.layout(i4, i5, measuredWidth, r0.c.getMeasuredHeight() + i5);
        max2 = Math.min(paddingLeft, (i4 - r0.h) - i2);
        r0.b.layout(max2, i3, i2 + max2, r0.b.getMeasuredHeight() + i3);
        i2 = Math.max((width - paddingRight) - i6, measuredWidth + r0.h);
        r0.d.layout(i2, paddingTop, i2 + i6, r0.d.getMeasuredHeight() + paddingTop);
        r0.e = f;
        r0.k = false;
    }

    protected void onMeasure(int i, int i2) {
        if (MeasureSpec.getMode(i) == 1073741824) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
            int size = MeasureSpec.getSize(i);
            i = getChildMeasureSpec(i, (int) (((float) size) * 0.2f), -2);
            this.b.measure(i, childMeasureSpec);
            this.c.measure(i, childMeasureSpec);
            this.d.measure(i, childMeasureSpec);
            if (MeasureSpec.getMode(i2) == 1073741824) {
                i = MeasureSpec.getSize(i2);
            } else {
                i = Math.max(a(), this.c.getMeasuredHeight() + paddingTop);
            }
            setMeasuredDimension(size, View.resolveSizeAndState(i, i2, this.c.getMeasuredState() << 16));
            return;
        }
        throw new IllegalStateException("Must measure with an exact width");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.a != null) {
            float f = this.e;
            float f2 = BitmapDescriptorFactory.HUE_RED;
            if (f >= BitmapDescriptorFactory.HUE_RED) {
                f2 = this.e;
            }
            a(this.g, f2, true);
        }
    }

    int a() {
        Drawable background = getBackground();
        return background != null ? background.getIntrinsicHeight() : 0;
    }
}
