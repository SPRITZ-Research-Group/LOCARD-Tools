package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.StateListDrawable;
import android.support.design.a.b;
import android.support.design.a.d;
import android.support.design.a.f;
import android.support.design.a.g;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.o.a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;

public class NavigationMenuItemView extends ForegroundLinearLayout implements a {
    private static final int[] c = new int[]{16842912};
    private final int d;
    private final CheckedTextView e;
    private FrameLayout f;
    private i g;
    private ColorStateList h;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    public NavigationMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(0);
        LayoutInflater.from(context).inflate(g.design_navigation_menu_item, this, true);
        this.d = context.getResources().getDimensionPixelSize(d.design_navigation_icon_size);
        this.e = (CheckedTextView) findViewById(f.design_menu_item_text);
        this.e.setDuplicateParentStateEnabled(true);
    }

    public final void a(i itemData) {
        this.g = itemData;
        setVisibility(itemData.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            Drawable stateListDrawable;
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(b.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(c, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            setBackgroundDrawable(stateListDrawable);
        }
        setCheckable(itemData.isCheckable());
        setChecked(itemData.isChecked());
        setEnabled(itemData.isEnabled());
        setTitle(itemData.getTitle());
        setIcon(itemData.getIcon());
        View actionView = itemData.getActionView();
        if (this.f == null) {
            this.f = (FrameLayout) ((ViewStub) findViewById(f.design_menu_item_action_area_stub)).inflate();
        }
        this.f.removeAllViews();
        if (actionView != null) {
            this.f.addView(actionView);
        }
    }

    public final void a() {
        if (this.f != null) {
            this.f.removeAllViews();
        }
        this.e.setCompoundDrawables(null, null, null, null);
    }

    public final i b() {
        return this.g;
    }

    public void setTitle(CharSequence title) {
        this.e.setText(title);
    }

    public void setCheckable(boolean checkable) {
        refreshDrawableState();
    }

    public void setChecked(boolean checked) {
        refreshDrawableState();
        this.e.setChecked(checked);
    }

    public void setShortcut(boolean showShortcut, char shortcutKey) {
    }

    public void setIcon(Drawable icon) {
        if (icon != null) {
            ConstantState state = icon.getConstantState();
            if (state != null) {
                icon = state.newDrawable();
            }
            icon = android.support.v4.graphics.drawable.a.f(icon).mutate();
            icon.setBounds(0, 0, this.d, this.d);
            android.support.v4.graphics.drawable.a.a(icon, this.h);
        }
        TextViewCompat.a(this.e, icon);
    }

    public final boolean c() {
        return false;
    }

    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (this.g != null && this.g.isCheckable() && this.g.isChecked()) {
            mergeDrawableStates(drawableState, c);
        }
        return drawableState;
    }

    final void a(ColorStateList tintList) {
        this.h = tintList;
        if (this.g != null) {
            setIcon(this.g.getIcon());
        }
    }

    public void setTextAppearance(Context context, int textAppearance) {
        this.e.setTextAppearance(context, textAppearance);
    }

    public void setTextColor(ColorStateList colors) {
        this.e.setTextColor(colors);
    }
}
