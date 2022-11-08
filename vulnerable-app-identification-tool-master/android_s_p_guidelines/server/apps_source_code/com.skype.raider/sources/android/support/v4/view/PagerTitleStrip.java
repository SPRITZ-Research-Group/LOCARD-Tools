package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.view.ViewPager.DecorView;
import android.support.v4.view.ViewPager.d;
import android.support.v4.view.ViewPager.e;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils.TruncateAt;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.ref.WeakReference;
import java.util.Locale;

@DecorView
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
    private final a l;
    private WeakReference<n> m;
    private int p;

    private class a extends DataSetObserver implements d, e {
        final /* synthetic */ PagerTitleStrip a;
        private int b;

        a(PagerTitleStrip pagerTitleStrip) {
            this.a = pagerTitleStrip;
        }

        public final void a(int position, float positionOffset) {
            if (positionOffset > 0.5f) {
                position++;
            }
            this.a.a(position, positionOffset, false);
        }

        public final void b(int position) {
            float offset = 0.0f;
            if (this.b == 0) {
                this.a.a(this.a.a.c, this.a.a.a());
                if (this.a.e >= 0.0f) {
                    offset = this.a.e;
                }
                this.a.a(this.a.a.c, offset, true);
            }
        }

        public final void a(int state) {
            this.b = state;
        }

        public final void a(n oldAdapter, n newAdapter) {
            this.a.a(oldAdapter, newAdapter);
        }

        public final void onChanged() {
            float offset = 0.0f;
            this.a.a(this.a.a.c, this.a.a.a());
            if (this.a.e >= 0.0f) {
                offset = this.a.e;
            }
            this.a.a(this.a.a.c, offset, true);
        }
    }

    private static class b extends SingleLineTransformationMethod {
        private Locale a;

        b(Context context) {
            this.a = context.getResources().getConfiguration().locale;
        }

        public final CharSequence getTransformation(CharSequence source, View view) {
            source = super.getTransformation(source, view);
            return source != null ? source.toString().toUpperCase(this.a) : null;
        }
    }

    private static void a(TextView text) {
        text.setTransformationMethod(new b(text.getContext()));
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.g = -1;
        this.e = -1.0f;
        this.l = new a(this);
        View textView = new TextView(context);
        this.b = textView;
        addView(textView);
        textView = new TextView(context);
        this.c = textView;
        addView(textView);
        textView = new TextView(context);
        this.d = textView;
        addView(textView);
        TypedArray a = context.obtainStyledAttributes(attrs, n);
        int textAppearance = a.getResourceId(0, 0);
        if (textAppearance != 0) {
            TextViewCompat.a(this.b, textAppearance);
            TextViewCompat.a(this.c, textAppearance);
            TextViewCompat.a(this.d, textAppearance);
        }
        int textSize = a.getDimensionPixelSize(1, 0);
        if (textSize != 0) {
            setTextSize(0, (float) textSize);
        }
        if (a.hasValue(2)) {
            int textColor = a.getColor(2, 0);
            this.b.setTextColor(textColor);
            this.c.setTextColor(textColor);
            this.d.setTextColor(textColor);
        }
        this.i = a.getInteger(3, 80);
        a.recycle();
        this.f = this.c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.b.setEllipsize(TruncateAt.END);
        this.c.setEllipsize(TruncateAt.END);
        this.d.setEllipsize(TruncateAt.END);
        boolean allCaps = false;
        if (textAppearance != 0) {
            TypedArray ta = context.obtainStyledAttributes(textAppearance, o);
            allCaps = ta.getBoolean(0, false);
            ta.recycle();
        }
        if (allCaps) {
            a(this.b);
            a(this.c);
            a(this.d);
        } else {
            this.b.setSingleLine();
            this.c.setSingleLine();
            this.d.setSingleLine();
        }
        this.h = (int) (16.0f * context.getResources().getDisplayMetrics().density);
    }

    public void setTextSpacing(int spacingPixels) {
        this.h = spacingPixels;
        requestLayout();
    }

    public final int b() {
        return this.h;
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0d, to = 1.0d) float alpha) {
        this.p = ((int) (255.0f * alpha)) & 255;
        int transparentColor = (this.p << 24) | (this.f & 16777215);
        this.b.setTextColor(transparentColor);
        this.d.setTextColor(transparentColor);
    }

    public void setTextColor(@ColorInt int color) {
        this.f = color;
        this.c.setTextColor(color);
        int transparentColor = (this.p << 24) | (this.f & 16777215);
        this.b.setTextColor(transparentColor);
        this.d.setTextColor(transparentColor);
    }

    public void setTextSize(int unit, float size) {
        this.b.setTextSize(unit, size);
        this.c.setTextSize(unit, size);
        this.d.setTextSize(unit, size);
    }

    public void setGravity(int gravity) {
        this.i = gravity;
        requestLayout();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager pager = (ViewPager) parent;
            n adapter = pager.a();
            pager.b(this.l);
            pager.a(this.l);
            this.a = pager;
            a(this.m != null ? (n) this.m.get() : null, adapter);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            a(this.a.a(), null);
            this.a.b(null);
            this.a.b(this.l);
            this.a = null;
        }
    }

    final void a(int currentItem, n adapter) {
        if (adapter != null) {
            adapter.a();
        }
        this.j = true;
        this.b.setText(null);
        this.c.setText(null);
        this.d.setText(null);
        int childWidthSpec = MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f)), Integer.MIN_VALUE);
        int childHeightSpec = MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.b.measure(childWidthSpec, childHeightSpec);
        this.c.measure(childWidthSpec, childHeightSpec);
        this.d.measure(childWidthSpec, childHeightSpec);
        this.g = currentItem;
        if (!this.k) {
            a(currentItem, this.e, false);
        }
        this.j = false;
    }

    public void requestLayout() {
        if (!this.j) {
            super.requestLayout();
        }
    }

    final void a(n oldAdapter, n newAdapter) {
        if (oldAdapter != null) {
            oldAdapter.b(this.l);
            this.m = null;
        }
        if (newAdapter != null) {
            newAdapter.a(this.l);
            this.m = new WeakReference(newAdapter);
        }
        if (this.a != null) {
            this.g = -1;
            this.e = -1.0f;
            a(this.a.c, newAdapter);
            requestLayout();
        }
    }

    void a(int position, float positionOffset, boolean force) {
        int prevTop;
        int currTop;
        int nextTop;
        if (position != this.g) {
            a(position, this.a.a());
        } else if (!force && positionOffset == this.e) {
            return;
        }
        this.k = true;
        int prevWidth = this.b.getMeasuredWidth();
        int currWidth = this.c.getMeasuredWidth();
        int nextWidth = this.d.getMeasuredWidth();
        int halfCurrWidth = currWidth / 2;
        int stripWidth = getWidth();
        int stripHeight = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int textPaddedRight = paddingRight + halfCurrWidth;
        int contentWidth = (stripWidth - (paddingLeft + halfCurrWidth)) - textPaddedRight;
        float currOffset = positionOffset + 0.5f;
        if (currOffset > 1.0f) {
            currOffset -= 1.0f;
        }
        int currLeft = ((stripWidth - textPaddedRight) - ((int) (((float) contentWidth) * currOffset))) - halfCurrWidth;
        int currRight = currLeft + currWidth;
        int prevBaseline = this.b.getBaseline();
        int currBaseline = this.c.getBaseline();
        int nextBaseline = this.d.getBaseline();
        int maxBaseline = Math.max(Math.max(prevBaseline, currBaseline), nextBaseline);
        int prevTopOffset = maxBaseline - prevBaseline;
        int currTopOffset = maxBaseline - currBaseline;
        int nextTopOffset = maxBaseline - nextBaseline;
        int alignedNextHeight = nextTopOffset + this.d.getMeasuredHeight();
        int maxTextHeight = Math.max(Math.max(prevTopOffset + this.b.getMeasuredHeight(), currTopOffset + this.c.getMeasuredHeight()), alignedNextHeight);
        switch (this.i & 112) {
            case 16:
                int centeredTop = (((stripHeight - paddingTop) - paddingBottom) - maxTextHeight) / 2;
                prevTop = centeredTop + prevTopOffset;
                currTop = centeredTop + currTopOffset;
                nextTop = centeredTop + nextTopOffset;
                break;
            case 80:
                int bottomGravTop = (stripHeight - paddingBottom) - maxTextHeight;
                prevTop = bottomGravTop + prevTopOffset;
                currTop = bottomGravTop + currTopOffset;
                nextTop = bottomGravTop + nextTopOffset;
                break;
            default:
                prevTop = paddingTop + prevTopOffset;
                currTop = paddingTop + currTopOffset;
                nextTop = paddingTop + nextTopOffset;
                break;
        }
        this.c.layout(currLeft, currTop, currRight, this.c.getMeasuredHeight() + currTop);
        int prevLeft = Math.min(paddingLeft, (currLeft - this.h) - prevWidth);
        this.b.layout(prevLeft, prevTop, prevLeft + prevWidth, this.b.getMeasuredHeight() + prevTop);
        int nextLeft = Math.max((stripWidth - paddingRight) - nextWidth, this.h + currRight);
        this.d.layout(nextLeft, nextTop, nextLeft + nextWidth, this.d.getMeasuredHeight() + nextTop);
        this.e = positionOffset;
        this.k = false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) != ErrorDialogData.SUPPRESSED) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int height;
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, heightPadding, -2);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, (int) (((float) widthSize) * 0.2f), -2);
        this.b.measure(childWidthSpec, childHeightSpec);
        this.c.measure(childWidthSpec, childHeightSpec);
        this.d.measure(childWidthSpec, childHeightSpec);
        if (MeasureSpec.getMode(heightMeasureSpec) == ErrorDialogData.SUPPRESSED) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height = Math.max(a(), this.c.getMeasuredHeight() + heightPadding);
        }
        setMeasuredDimension(widthSize, View.resolveSizeAndState(height, heightMeasureSpec, this.c.getMeasuredState() << 16));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        float offset = 0.0f;
        if (this.a != null) {
            if (this.e >= 0.0f) {
                offset = this.e;
            }
            a(this.g, offset, true);
        }
    }

    int a() {
        Drawable bg = getBackground();
        if (bg != null) {
            return bg.getIntrinsicHeight();
        }
        return 0;
    }
}
