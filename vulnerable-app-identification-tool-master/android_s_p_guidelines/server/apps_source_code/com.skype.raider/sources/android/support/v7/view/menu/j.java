package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@RequiresApi(14)
@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class j extends c<android.support.v4.internal.view.b> implements MenuItem {
    private Method c;

    class a extends android.support.v4.view.b {
        final ActionProvider a;
        final /* synthetic */ j b;

        public a(j this$0, Context context, ActionProvider inner) {
            this.b = this$0;
            super(context);
            this.a = inner;
        }

        public final View a() {
            return this.a.onCreateActionView();
        }

        public final boolean d() {
            return this.a.onPerformDefaultAction();
        }

        public final boolean e() {
            return this.a.hasSubMenu();
        }

        public final void a(SubMenu subMenu) {
            this.a.onPrepareSubMenu(this.b.a(subMenu));
        }
    }

    static class b extends FrameLayout implements android.support.v7.view.c {
        final CollapsibleActionView a;

        b(View actionView) {
            super(actionView.getContext());
            this.a = (CollapsibleActionView) actionView;
            addView(actionView);
        }

        public final void a() {
            this.a.onActionViewExpanded();
        }

        public final void b() {
            this.a.onActionViewCollapsed();
        }
    }

    private class c extends d<OnActionExpandListener> implements OnActionExpandListener {
        final /* synthetic */ j a;

        c(j jVar, OnActionExpandListener object) {
            this.a = jVar;
            super(object);
        }

        public final boolean onMenuItemActionExpand(MenuItem item) {
            return ((OnActionExpandListener) this.b).onMenuItemActionExpand(this.a.a(item));
        }

        public final boolean onMenuItemActionCollapse(MenuItem item) {
            return ((OnActionExpandListener) this.b).onMenuItemActionCollapse(this.a.a(item));
        }
    }

    private class d extends d<OnMenuItemClickListener> implements OnMenuItemClickListener {
        final /* synthetic */ j a;

        d(j jVar, OnMenuItemClickListener object) {
            this.a = jVar;
            super(object);
        }

        public final boolean onMenuItemClick(MenuItem item) {
            return ((OnMenuItemClickListener) this.b).onMenuItemClick(this.a.a(item));
        }
    }

    j(Context context, android.support.v4.internal.view.b object) {
        super(context, object);
    }

    public int getItemId() {
        return ((android.support.v4.internal.view.b) this.b).getItemId();
    }

    public int getGroupId() {
        return ((android.support.v4.internal.view.b) this.b).getGroupId();
    }

    public int getOrder() {
        return ((android.support.v4.internal.view.b) this.b).getOrder();
    }

    public MenuItem setTitle(CharSequence title) {
        ((android.support.v4.internal.view.b) this.b).setTitle(title);
        return this;
    }

    public MenuItem setTitle(int title) {
        ((android.support.v4.internal.view.b) this.b).setTitle(title);
        return this;
    }

    public CharSequence getTitle() {
        return ((android.support.v4.internal.view.b) this.b).getTitle();
    }

    public MenuItem setTitleCondensed(CharSequence title) {
        ((android.support.v4.internal.view.b) this.b).setTitleCondensed(title);
        return this;
    }

    public CharSequence getTitleCondensed() {
        return ((android.support.v4.internal.view.b) this.b).getTitleCondensed();
    }

    public MenuItem setIcon(Drawable icon) {
        ((android.support.v4.internal.view.b) this.b).setIcon(icon);
        return this;
    }

    public MenuItem setIcon(int iconRes) {
        ((android.support.v4.internal.view.b) this.b).setIcon(iconRes);
        return this;
    }

    public Drawable getIcon() {
        return ((android.support.v4.internal.view.b) this.b).getIcon();
    }

    public MenuItem setIntent(Intent intent) {
        ((android.support.v4.internal.view.b) this.b).setIntent(intent);
        return this;
    }

    public Intent getIntent() {
        return ((android.support.v4.internal.view.b) this.b).getIntent();
    }

    public MenuItem setShortcut(char numericChar, char alphaChar) {
        ((android.support.v4.internal.view.b) this.b).setShortcut(numericChar, alphaChar);
        return this;
    }

    public MenuItem setShortcut(char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
        ((android.support.v4.internal.view.b) this.b).setShortcut(numericChar, alphaChar, numericModifiers, alphaModifiers);
        return this;
    }

    public MenuItem setNumericShortcut(char numericChar) {
        ((android.support.v4.internal.view.b) this.b).setNumericShortcut(numericChar);
        return this;
    }

    public MenuItem setNumericShortcut(char numericChar, int numericModifiers) {
        ((android.support.v4.internal.view.b) this.b).setNumericShortcut(numericChar, numericModifiers);
        return this;
    }

    public char getNumericShortcut() {
        return ((android.support.v4.internal.view.b) this.b).getNumericShortcut();
    }

    public int getNumericModifiers() {
        return ((android.support.v4.internal.view.b) this.b).getNumericModifiers();
    }

    public MenuItem setAlphabeticShortcut(char alphaChar) {
        ((android.support.v4.internal.view.b) this.b).setAlphabeticShortcut(alphaChar);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char alphaChar, int alphaModifiers) {
        ((android.support.v4.internal.view.b) this.b).setAlphabeticShortcut(alphaChar, alphaModifiers);
        return this;
    }

    public char getAlphabeticShortcut() {
        return ((android.support.v4.internal.view.b) this.b).getAlphabeticShortcut();
    }

    public int getAlphabeticModifiers() {
        return ((android.support.v4.internal.view.b) this.b).getAlphabeticModifiers();
    }

    public MenuItem setCheckable(boolean checkable) {
        ((android.support.v4.internal.view.b) this.b).setCheckable(checkable);
        return this;
    }

    public boolean isCheckable() {
        return ((android.support.v4.internal.view.b) this.b).isCheckable();
    }

    public MenuItem setChecked(boolean checked) {
        ((android.support.v4.internal.view.b) this.b).setChecked(checked);
        return this;
    }

    public boolean isChecked() {
        return ((android.support.v4.internal.view.b) this.b).isChecked();
    }

    public MenuItem setVisible(boolean visible) {
        return ((android.support.v4.internal.view.b) this.b).setVisible(visible);
    }

    public boolean isVisible() {
        return ((android.support.v4.internal.view.b) this.b).isVisible();
    }

    public MenuItem setEnabled(boolean enabled) {
        ((android.support.v4.internal.view.b) this.b).setEnabled(enabled);
        return this;
    }

    public boolean isEnabled() {
        return ((android.support.v4.internal.view.b) this.b).isEnabled();
    }

    public boolean hasSubMenu() {
        return ((android.support.v4.internal.view.b) this.b).hasSubMenu();
    }

    public SubMenu getSubMenu() {
        return a(((android.support.v4.internal.view.b) this.b).getSubMenu());
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
        ((android.support.v4.internal.view.b) this.b).setOnMenuItemClickListener(menuItemClickListener != null ? new d(this, menuItemClickListener) : null);
        return this;
    }

    public ContextMenuInfo getMenuInfo() {
        return ((android.support.v4.internal.view.b) this.b).getMenuInfo();
    }

    public void setShowAsAction(int actionEnum) {
        ((android.support.v4.internal.view.b) this.b).setShowAsAction(actionEnum);
    }

    public MenuItem setShowAsActionFlags(int actionEnum) {
        ((android.support.v4.internal.view.b) this.b).setShowAsActionFlags(actionEnum);
        return this;
    }

    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new b(view);
        }
        ((android.support.v4.internal.view.b) this.b).setActionView(view);
        return this;
    }

    public MenuItem setActionView(int resId) {
        ((android.support.v4.internal.view.b) this.b).setActionView(resId);
        View actionView = ((android.support.v4.internal.view.b) this.b).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((android.support.v4.internal.view.b) this.b).setActionView(new b(actionView));
        }
        return this;
    }

    public View getActionView() {
        View actionView = ((android.support.v4.internal.view.b) this.b).getActionView();
        return actionView instanceof b ? (View) ((b) actionView).a : actionView;
    }

    public MenuItem setActionProvider(ActionProvider provider) {
        ((android.support.v4.internal.view.b) this.b).a(provider != null ? a(provider) : null);
        return this;
    }

    public ActionProvider getActionProvider() {
        android.support.v4.view.b provider = ((android.support.v4.internal.view.b) this.b).a();
        if (provider instanceof a) {
            return ((a) provider).a;
        }
        return null;
    }

    public boolean expandActionView() {
        return ((android.support.v4.internal.view.b) this.b).expandActionView();
    }

    public boolean collapseActionView() {
        return ((android.support.v4.internal.view.b) this.b).collapseActionView();
    }

    public boolean isActionViewExpanded() {
        return ((android.support.v4.internal.view.b) this.b).isActionViewExpanded();
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
        ((android.support.v4.internal.view.b) this.b).setOnActionExpandListener(listener != null ? new c(this, listener) : null);
        return this;
    }

    public MenuItem setContentDescription(CharSequence contentDescription) {
        ((android.support.v4.internal.view.b) this.b).a(contentDescription);
        return this;
    }

    public CharSequence getContentDescription() {
        return ((android.support.v4.internal.view.b) this.b).getContentDescription();
    }

    public MenuItem setTooltipText(CharSequence tooltipText) {
        ((android.support.v4.internal.view.b) this.b).b(tooltipText);
        return this;
    }

    public CharSequence getTooltipText() {
        return ((android.support.v4.internal.view.b) this.b).getTooltipText();
    }

    public MenuItem setIconTintList(ColorStateList tint) {
        ((android.support.v4.internal.view.b) this.b).setIconTintList(tint);
        return this;
    }

    public ColorStateList getIconTintList() {
        return ((android.support.v4.internal.view.b) this.b).getIconTintList();
    }

    public MenuItem setIconTintMode(Mode tintMode) {
        ((android.support.v4.internal.view.b) this.b).setIconTintMode(tintMode);
        return this;
    }

    public Mode getIconTintMode() {
        return ((android.support.v4.internal.view.b) this.b).getIconTintMode();
    }

    public final void b() {
        try {
            if (this.c == null) {
                this.c = ((android.support.v4.internal.view.b) this.b).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            }
            this.c.invoke(this.b, new Object[]{Boolean.valueOf(true)});
        } catch (Exception e) {
        }
    }

    a a(ActionProvider provider) {
        return new a(this, this.a, provider);
    }
}
