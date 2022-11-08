package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ai;
import android.support.v4.app.ai.a;
import android.support.v4.app.r;
import android.support.v7.widget.av;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class AppCompatActivity extends FragmentActivity implements a, a {
    private AppCompatDelegate a;
    private int b = 0;
    private Resources c;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate delegate = c();
        delegate.i();
        delegate.a(savedInstanceState);
        if (delegate.j() && this.b != 0) {
            if (VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.b, false);
            } else {
                setTheme(this.b);
            }
        }
        super.onCreate(savedInstanceState);
    }

    public void setTheme(@StyleRes int resid) {
        super.setTheme(resid);
        this.b = resid;
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        c().c();
    }

    public MenuInflater getMenuInflater() {
        return c().b();
    }

    public void setContentView(@LayoutRes int layoutResID) {
        c().b(layoutResID);
    }

    public void setContentView(View view) {
        c().a(view);
    }

    public void setContentView(View view, LayoutParams params) {
        c().a(view, params);
    }

    public void addContentView(View view, LayoutParams params) {
        c().b(view, params);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        c().a(newConfig);
        if (this.c != null) {
            this.c.updateConfiguration(newConfig, super.getResources().getDisplayMetrics());
        }
    }

    protected void onPostResume() {
        super.onPostResume();
        c().f();
    }

    protected void onStart() {
        super.onStart();
        c().d();
    }

    protected void onStop() {
        super.onStop();
        c().e();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return c().a(id);
    }

    public final boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (super.onMenuItemSelected(featureId, item)) {
            return true;
        }
        ActionBar ab = c().a();
        if (item.getItemId() != 16908332 || ab == null || (ab.a() & 4) == 0) {
            return false;
        }
        return b();
    }

    protected void onDestroy() {
        super.onDestroy();
        c().h();
    }

    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        c().a(title);
    }

    public void supportInvalidateOptionsMenu() {
        c().g();
    }

    public void invalidateOptionsMenu() {
        c().g();
    }

    private boolean b() {
        boolean z = false;
        Intent upIntent = r.a(this);
        if (upIntent == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 16) {
            z = shouldUpRecreateTask(upIntent);
        } else {
            String action = getIntent().getAction();
            if (!(action == null || action.equals("android.intent.action.MAIN"))) {
                z = true;
            }
        }
        if (z) {
            ai b = ai.a((Context) this);
            b.a((Activity) this);
            b.a();
            try {
                android.support.v4.app.a.a(this);
                return true;
            } catch (IllegalStateException e) {
                finish();
                return true;
            }
        } else if (VERSION.SDK_INT >= 16) {
            navigateUpTo(upIntent);
            return true;
        } else {
            upIntent.addFlags(67108864);
            startActivity(upIntent);
            finish();
            return true;
        }
    }

    @Nullable
    public final Intent a() {
        return r.a(this);
    }

    public void onContentChanged() {
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        c().b(outState);
    }

    @NonNull
    private AppCompatDelegate c() {
        if (this.a == null) {
            this.a = AppCompatDelegate.a((Activity) this, (a) this);
        }
        return this.a;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        ActionBar actionBar = c().a();
        if (keyCode == 82 && actionBar != null && actionBar.a(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public Resources getResources() {
        if (this.c == null && av.a()) {
            this.c = new av(this, super.getResources());
        }
        return this.c == null ? super.getResources() : this.c;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean z;
        if (!(VERSION.SDK_INT >= 26 || event.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(event.getMetaState()) || event.getRepeatCount() != 0 || KeyEvent.isModifierKey(event.getKeyCode()))) {
            Window window = getWindow();
            if (!(window == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(event))) {
                z = true;
                if (z) {
                    return super.onKeyDown(keyCode, event);
                }
                return true;
            }
        }
        z = false;
        if (z) {
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    public void openOptionsMenu() {
        ActionBar actionBar = c().a();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (actionBar == null || !actionBar.c()) {
            super.openOptionsMenu();
        }
    }

    public void closeOptionsMenu() {
        ActionBar actionBar = c().a();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (actionBar == null || !actionBar.d()) {
            super.closeOptionsMenu();
        }
    }
}
