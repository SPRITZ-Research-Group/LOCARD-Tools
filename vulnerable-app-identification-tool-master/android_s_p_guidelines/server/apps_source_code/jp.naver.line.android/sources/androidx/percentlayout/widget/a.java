package androidx.percentlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hi;
import defpackage.hs;
import defpackage.lv;

@Deprecated
public final class a {
    private final ViewGroup a;

    public a(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public static void a(LayoutParams layoutParams, TypedArray typedArray, int i, int i2) {
        layoutParams.width = typedArray.getLayoutDimension(i, 0);
        layoutParams.height = typedArray.getLayoutDimension(i2, 0);
    }

    public final void a(int i, int i2) {
        i = (MeasureSpec.getSize(i) - this.a.getPaddingLeft()) - this.a.getPaddingRight();
        i2 = (MeasureSpec.getSize(i2) - this.a.getPaddingTop()) - this.a.getPaddingBottom();
        int childCount = this.a.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.a.getChildAt(i3);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams instanceof c) {
                b a = ((c) layoutParams).a();
                if (a != null) {
                    if (layoutParams instanceof MarginLayoutParams) {
                        Object obj;
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                        a.a(marginLayoutParams, i, i2);
                        a.j.leftMargin = marginLayoutParams.leftMargin;
                        a.j.topMargin = marginLayoutParams.topMargin;
                        a.j.rightMargin = marginLayoutParams.rightMargin;
                        a.j.bottomMargin = marginLayoutParams.bottomMargin;
                        hi.a(a.j, hi.a(marginLayoutParams));
                        hi.b(a.j, hi.b(marginLayoutParams));
                        if (a.c >= BitmapDescriptorFactory.HUE_RED) {
                            marginLayoutParams.leftMargin = Math.round(((float) i) * a.c);
                        }
                        if (a.d >= BitmapDescriptorFactory.HUE_RED) {
                            marginLayoutParams.topMargin = Math.round(((float) i2) * a.d);
                        }
                        if (a.e >= BitmapDescriptorFactory.HUE_RED) {
                            marginLayoutParams.rightMargin = Math.round(((float) i) * a.e);
                        }
                        if (a.f >= BitmapDescriptorFactory.HUE_RED) {
                            marginLayoutParams.bottomMargin = Math.round(((float) i2) * a.f);
                        }
                        if (a.g >= BitmapDescriptorFactory.HUE_RED) {
                            hi.a(marginLayoutParams, Math.round(((float) i) * a.g));
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (a.h >= BitmapDescriptorFactory.HUE_RED) {
                            hi.b(marginLayoutParams, Math.round(((float) i) * a.h));
                            obj = 1;
                        }
                        if (!(obj == null || childAt == null)) {
                            int g = hs.g(childAt);
                            if (VERSION.SDK_INT >= 17) {
                                marginLayoutParams.resolveLayoutDirection(g);
                            }
                        }
                    } else {
                        a.a(layoutParams, i, i2);
                    }
                }
            }
        }
    }

    public static b a(Context context, AttributeSet attributeSet) {
        b bVar;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, lv.PercentLayout_Layout);
        float fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_widthPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            bVar = new b();
            bVar.a = fraction;
        } else {
            bVar = null;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_heightPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.b = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.c = fraction;
            bVar.d = fraction;
            bVar.e = fraction;
            bVar.f = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginLeftPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.c = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginTopPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.d = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginRightPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.e = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginBottomPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.f = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginStartPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.g = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_marginEndPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.h = fraction;
        }
        fraction = obtainStyledAttributes.getFraction(lv.PercentLayout_Layout_layout_aspectRatio, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (bVar == null) {
                bVar = new b();
            }
            bVar.i = fraction;
        }
        obtainStyledAttributes.recycle();
        return bVar;
    }

    public final void a() {
        int childCount = this.a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = this.a.getChildAt(i).getLayoutParams();
            if (layoutParams instanceof c) {
                b a = ((c) layoutParams).a();
                if (a != null) {
                    if (layoutParams instanceof MarginLayoutParams) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                        a.a(marginLayoutParams);
                        marginLayoutParams.leftMargin = a.j.leftMargin;
                        marginLayoutParams.topMargin = a.j.topMargin;
                        marginLayoutParams.rightMargin = a.j.rightMargin;
                        marginLayoutParams.bottomMargin = a.j.bottomMargin;
                        hi.a(marginLayoutParams, hi.a(a.j));
                        hi.b(marginLayoutParams, hi.b(a.j));
                    } else {
                        a.a(layoutParams);
                    }
                }
            }
        }
    }

    public final boolean b() {
        int childCount = this.a.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.a.getChildAt(i);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams instanceof c) {
                b a = ((c) layoutParams).a();
                if (a != null) {
                    Object obj = ((childAt.getMeasuredWidthAndState() & -16777216) == 16777216 && a.a >= BitmapDescriptorFactory.HUE_RED && a.j.width == -2) ? 1 : null;
                    if (obj != null) {
                        layoutParams.width = -2;
                        z = true;
                    }
                    Object obj2 = ((childAt.getMeasuredHeightAndState() & -16777216) == 16777216 && a.b >= BitmapDescriptorFactory.HUE_RED && a.j.height == -2) ? 1 : null;
                    if (obj2 != null) {
                        layoutParams.height = -2;
                        z = true;
                    }
                }
            }
        }
        return z;
    }
}
