package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.internal.b;
import android.support.v4.os.c;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.g;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] a = new int[]{16842912};
    private static final int[] b = new int[]{-16842910};
    private final android.support.design.internal.a c;
    private final b d;
    private a e;
    private int f;
    private MenuInflater g;

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = android.support.v4.os.b.a(new c<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object a(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        });
        public Bundle a;

        public SavedState(Parcel in, ClassLoader loader) {
            super(in);
            this.a = in.readBundle(loader);
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(@NonNull Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeBundle(this.a);
        }
    }

    public interface a {
        boolean a();
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        ColorStateList itemIconTint;
        super(context, attrs, defStyleAttr);
        this.d = new b();
        r.a(context);
        this.c = new android.support.design.internal.a(context);
        TypedArray a = context.obtainStyledAttributes(attrs, j.NavigationView, defStyleAttr, i.Widget_Design_NavigationView);
        setBackgroundDrawable(a.getDrawable(j.NavigationView_android_background));
        if (a.hasValue(j.NavigationView_elevation)) {
            ViewCompat.c((View) this, (float) a.getDimensionPixelSize(j.NavigationView_elevation, 0));
        }
        ViewCompat.a((View) this, a.getBoolean(j.NavigationView_android_fitsSystemWindows, false));
        this.f = a.getDimensionPixelSize(j.NavigationView_android_maxWidth, 0);
        if (a.hasValue(j.NavigationView_itemIconTint)) {
            itemIconTint = a.getColorStateList(j.NavigationView_itemIconTint);
        } else {
            itemIconTint = a(16842808);
        }
        boolean textAppearanceSet = false;
        int textAppearance = 0;
        if (a.hasValue(j.NavigationView_itemTextAppearance)) {
            textAppearance = a.getResourceId(j.NavigationView_itemTextAppearance, 0);
            textAppearanceSet = true;
        }
        ColorStateList itemTextColor = null;
        if (a.hasValue(j.NavigationView_itemTextColor)) {
            itemTextColor = a.getColorStateList(j.NavigationView_itemTextColor);
        }
        if (!textAppearanceSet && itemTextColor == null) {
            itemTextColor = a(16842806);
        }
        Drawable itemBackground = a.getDrawable(j.NavigationView_itemBackground);
        this.c.a(new android.support.v7.view.menu.g.a(this) {
            final /* synthetic */ NavigationView a;

            {
                this.a = r1;
            }

            public final boolean a(g menu, MenuItem item) {
                return this.a.e != null && this.a.e.a();
            }

            public final void a(g menu) {
            }
        });
        this.d.c();
        this.d.a(context, this.c);
        this.d.a(itemIconTint);
        if (textAppearanceSet) {
            this.d.b(textAppearance);
        }
        this.d.b(itemTextColor);
        this.d.a(itemBackground);
        this.c.a(this.d);
        addView((View) this.d.a((ViewGroup) this));
        if (a.hasValue(j.NavigationView_menu)) {
            int resourceId = a.getResourceId(j.NavigationView_menu, 0);
            this.d.b(true);
            if (this.g == null) {
                this.g = new android.support.v7.view.g(getContext());
            }
            this.g.inflate(resourceId, this.c);
            this.d.b(false);
            this.d.a(false);
        }
        if (a.hasValue(j.NavigationView_headerLayout)) {
            this.d.a(a.getResourceId(j.NavigationView_headerLayout, 0));
        }
        a.recycle();
    }

    protected Parcelable onSaveInstanceState() {
        SavedState state = new SavedState(super.onSaveInstanceState());
        state.a = new Bundle();
        this.c.a(state.a);
        return state;
    }

    protected void onRestoreInstanceState(Parcelable savedState) {
        SavedState state = (SavedState) savedState;
        super.onRestoreInstanceState(state.getSuperState());
        this.c.b(state.a);
    }

    public void setNavigationItemSelectedListener(a listener) {
        this.e = listener;
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        switch (MeasureSpec.getMode(widthSpec)) {
            case Integer.MIN_VALUE:
                widthSpec = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(widthSpec), this.f), ErrorDialogData.SUPPRESSED);
                break;
            case 0:
                widthSpec = MeasureSpec.makeMeasureSpec(this.f, ErrorDialogData.SUPPRESSED);
                break;
        }
        super.onMeasure(widthSpec, heightSpec);
    }

    public void setItemIconTintList(@Nullable ColorStateList tint) {
        this.d.a(tint);
    }

    public void setItemTextColor(@Nullable ColorStateList textColor) {
        this.d.b(textColor);
    }

    public void setItemBackgroundResource(@DrawableRes int resId) {
        setItemBackground(android.support.v4.content.a.a(getContext(), resId));
    }

    public void setItemBackground(Drawable itemBackground) {
        this.d.a(itemBackground);
    }

    public void setCheckedItem(@IdRes int id) {
        MenuItem item = this.c.findItem(id);
        if (item != null) {
            this.d.c((android.support.v7.view.menu.i) item);
        }
    }

    public void setItemTextAppearance(@StyleRes int resId) {
        this.d.b(resId);
    }

    private ColorStateList a(int baseColorThemeAttr) {
        TypedValue value = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(baseColorThemeAttr, value, true)) {
            return null;
        }
        ColorStateList baseColor = getResources().getColorStateList(value.resourceId);
        if (!getContext().getTheme().resolveAttribute(android.support.design.a.b.colorPrimary, value, true)) {
            return null;
        }
        int colorPrimary = value.data;
        int defaultColor = baseColor.getDefaultColor();
        return new ColorStateList(new int[][]{b, a, EMPTY_STATE_SET}, new int[]{baseColor.getColorForState(b, defaultColor), colorPrimary, defaultColor});
    }
}
