package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.bp;
import defpackage.bq;
import defpackage.bs;
import defpackage.bt;

public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = new int[]{16842801};
    private static final e IMPL;
    private final d mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    public void setPadding(int i, int i2, int i3, int i4) {
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            IMPL = new b();
        } else if (VERSION.SDK_INT >= 17) {
            IMPL = new a();
        } else {
            IMPL = new c();
        }
        IMPL.a();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, bp.cardViewStyle);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        int color;
        super(context, attributeSet, i);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.mCardViewDelegate = new d(this) {
            final /* synthetic */ CardView a;
            private Drawable b;

            {
                this.a = r1;
            }

            public final void a(Drawable drawable) {
                this.b = drawable;
                this.a.setBackgroundDrawable(drawable);
            }

            public final boolean a() {
                return this.a.getUseCompatPadding();
            }

            public final boolean b() {
                return this.a.getPreventCornerOverlap();
            }

            public final void a(int i, int i2, int i3, int i4) {
                this.a.mShadowBounds.set(i, i2, i3, i4);
                CardView cardView = this.a;
                super.setPadding(i + cardView.mContentPadding.left, i2 + this.a.mContentPadding.top, i3 + this.a.mContentPadding.right, i4 + this.a.mContentPadding.bottom);
            }

            public final void a(int i, int i2) {
                if (i > this.a.mUserSetMinWidth) {
                    super.setMinimumWidth(i);
                }
                if (i2 > this.a.mUserSetMinHeight) {
                    super.setMinimumHeight(i2);
                }
            }

            public final Drawable c() {
                return this.b;
            }

            public final View d() {
                return this.a;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, bt.CardView, i, bs.CardView);
        if (obtainStyledAttributes.hasValue(bt.CardView_cardBackgroundColor)) {
            colorStateList = obtainStyledAttributes.getColorStateList(bt.CardView_cardBackgroundColor);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            if (fArr[2] > 0.5f) {
                i = getResources().getColor(bq.cardview_light_background);
            } else {
                i = getResources().getColor(bq.cardview_dark_background);
            }
            colorStateList = ColorStateList.valueOf(i);
        }
        ColorStateList colorStateList2 = colorStateList;
        float dimension = obtainStyledAttributes.getDimension(bt.CardView_cardCornerRadius, BitmapDescriptorFactory.HUE_RED);
        float dimension2 = obtainStyledAttributes.getDimension(bt.CardView_cardElevation, BitmapDescriptorFactory.HUE_RED);
        float dimension3 = obtainStyledAttributes.getDimension(bt.CardView_cardMaxElevation, BitmapDescriptorFactory.HUE_RED);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(bt.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = obtainStyledAttributes.getBoolean(bt.CardView_cardPreventCornerOverlap, true);
        color = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_contentPadding, 0);
        this.mContentPadding.left = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_contentPaddingLeft, color);
        this.mContentPadding.top = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_contentPaddingTop, color);
        this.mContentPadding.right = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_contentPaddingRight, color);
        this.mContentPadding.bottom = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_contentPaddingBottom, color);
        float f = dimension2 > dimension3 ? dimension2 : dimension3;
        this.mUserSetMinWidth = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = obtainStyledAttributes.getDimensionPixelSize(bt.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        IMPL.a(this.mCardViewDelegate, context, colorStateList2, dimension, dimension2, f);
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    public void setUseCompatPadding(boolean z) {
        if (this.mCompatPadding != z) {
            this.mCompatPadding = z;
            IMPL.g(this.mCardViewDelegate);
        }
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.mContentPadding.set(i, i2, i3, i4);
        IMPL.f(this.mCardViewDelegate);
    }

    protected void onMeasure(int i, int i2) {
        if (IMPL instanceof b) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i = MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) IMPL.b(this.mCardViewDelegate)), MeasureSpec.getSize(i)), mode);
        }
        mode = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i2 = MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) IMPL.c(this.mCardViewDelegate)), MeasureSpec.getSize(i2)), mode);
        }
        super.onMeasure(i, i2);
    }

    public void setMinimumWidth(int i) {
        this.mUserSetMinWidth = i;
        super.setMinimumWidth(i);
    }

    public void setMinimumHeight(int i) {
        this.mUserSetMinHeight = i;
        super.setMinimumHeight(i);
    }

    public void setCardBackgroundColor(int i) {
        IMPL.a(this.mCardViewDelegate, ColorStateList.valueOf(i));
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        IMPL.a(this.mCardViewDelegate, colorStateList);
    }

    public ColorStateList getCardBackgroundColor() {
        return IMPL.i(this.mCardViewDelegate);
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public void setRadius(float f) {
        IMPL.a(this.mCardViewDelegate, f);
    }

    public float getRadius() {
        return IMPL.d(this.mCardViewDelegate);
    }

    public void setCardElevation(float f) {
        IMPL.c(this.mCardViewDelegate, f);
    }

    public float getCardElevation() {
        return IMPL.e(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float f) {
        IMPL.b(this.mCardViewDelegate, f);
    }

    public float getMaxCardElevation() {
        return IMPL.a(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = z;
            IMPL.h(this.mCardViewDelegate);
        }
    }
}
