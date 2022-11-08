package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.annotation.StyleableRes;
import android.support.v7.content.res.b;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

@RestrictTo({a.LIBRARY_GROUP})
public final class aq {
    private final Context a;
    private final TypedArray b;
    private TypedValue c;

    public static aq a(Context context, AttributeSet set, int[] attrs) {
        return new aq(context, context.obtainStyledAttributes(set, attrs));
    }

    public static aq a(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes) {
        return new aq(context, context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes));
    }

    public static aq a(Context context, int resid, int[] attrs) {
        return new aq(context, context.obtainStyledAttributes(resid, attrs));
    }

    private aq(Context context, TypedArray array) {
        this.a = context;
        this.b = array;
    }

    public final Drawable a(int index) {
        if (this.b.hasValue(index)) {
            int resourceId = this.b.getResourceId(index, 0);
            if (resourceId != 0) {
                return b.b(this.a, resourceId);
            }
        }
        return this.b.getDrawable(index);
    }

    public final Drawable b(int index) {
        if (this.b.hasValue(index)) {
            int resourceId = this.b.getResourceId(index, 0);
            if (resourceId != 0) {
                return h.a().a(this.a, resourceId, true);
            }
        }
        return null;
    }

    @Nullable
    public final Typeface a(@StyleableRes int index, int style, @NonNull TextView targetView) {
        int resourceId = this.b.getResourceId(index, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return android.support.v4.content.res.a.a(this.a, resourceId, this.c, style, targetView);
    }

    public final CharSequence c(int index) {
        return this.b.getText(index);
    }

    public final String d(int index) {
        return this.b.getString(index);
    }

    public final boolean a(int index, boolean defValue) {
        return this.b.getBoolean(index, defValue);
    }

    public final int a(int index, int defValue) {
        return this.b.getInt(index, defValue);
    }

    public final float e(int index) {
        return this.b.getFloat(index, -1.0f);
    }

    public final int b(int index, int defValue) {
        return this.b.getColor(index, defValue);
    }

    public final ColorStateList f(int index) {
        if (this.b.hasValue(index)) {
            int resourceId = this.b.getResourceId(index, 0);
            if (resourceId != 0) {
                ColorStateList value = b.a(this.a, resourceId);
                if (value != null) {
                    return value;
                }
            }
        }
        return this.b.getColorStateList(index);
    }

    public final int c(int index, int defValue) {
        return this.b.getInteger(index, defValue);
    }

    public final int d(int index, int defValue) {
        return this.b.getDimensionPixelOffset(index, defValue);
    }

    public final int e(int index, int defValue) {
        return this.b.getDimensionPixelSize(index, defValue);
    }

    public final int f(int index, int defValue) {
        return this.b.getLayoutDimension(index, defValue);
    }

    public final int g(int index, int defValue) {
        return this.b.getResourceId(index, defValue);
    }

    public final CharSequence[] g(int index) {
        return this.b.getTextArray(index);
    }

    public final boolean h(int index) {
        return this.b.hasValue(index);
    }

    public final void a() {
        this.b.recycle();
    }
}
