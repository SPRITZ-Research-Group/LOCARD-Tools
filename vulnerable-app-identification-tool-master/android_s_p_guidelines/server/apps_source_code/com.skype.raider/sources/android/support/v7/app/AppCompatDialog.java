package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class AppCompatDialog extends Dialog implements a {
    private AppCompatDelegate a;

    protected void onCreate(Bundle savedInstanceState) {
        a().i();
        super.onCreate(savedInstanceState);
        a().a(savedInstanceState);
    }

    public void setContentView(@LayoutRes int layoutResID) {
        a().b(layoutResID);
    }

    public void setContentView(View view) {
        a().a(view);
    }

    public void setContentView(View view, LayoutParams params) {
        a().a(view, params);
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int id) {
        return a().a(id);
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        a().a(title);
    }

    public void setTitle(int titleId) {
        super.setTitle(titleId);
        a().a(getContext().getString(titleId));
    }

    public void addContentView(View view, LayoutParams params) {
        a().b(view, params);
    }

    protected void onStop() {
        super.onStop();
        a().e();
    }

    @RestrictTo({a.LIBRARY_GROUP})
    public void invalidateOptionsMenu() {
        a().g();
    }

    public final AppCompatDelegate a() {
        if (this.a == null) {
            this.a = AppCompatDelegate.a((Dialog) this, (a) this);
        }
        return this.a;
    }

    public AppCompatDialog(Context context, int theme) {
        if (theme == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.dialogTheme, typedValue, true);
            theme = typedValue.resourceId;
        }
        super(context, theme);
        a().a(null);
        a().j();
    }
}
