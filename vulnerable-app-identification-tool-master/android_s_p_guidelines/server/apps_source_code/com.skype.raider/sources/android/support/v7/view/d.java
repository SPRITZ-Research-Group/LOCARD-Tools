package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.annotation.StyleRes;
import android.support.v7.appcompat.a.i;
import android.view.LayoutInflater;

@RestrictTo({a.LIBRARY_GROUP})
public final class d extends ContextWrapper {
    private int a;
    private Theme b;
    private LayoutInflater c;
    private Configuration d;
    private Resources e;

    public d() {
        super(null);
    }

    public d(Context base, @StyleRes int themeResId) {
        super(base);
        this.a = themeResId;
    }

    protected final void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public final void setTheme(int resid) {
        if (this.a != resid) {
            this.a = resid;
            b();
        }
    }

    public final int a() {
        return this.a;
    }

    public final Theme getTheme() {
        if (this.b != null) {
            return this.b;
        }
        if (this.a == 0) {
            this.a = i.Theme_AppCompat_Light;
        }
        b();
        return this.b;
    }

    public final Object getSystemService(String name) {
        if (!"layout_inflater".equals(name)) {
            return getBaseContext().getSystemService(name);
        }
        if (this.c == null) {
            this.c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.c;
    }

    private void b() {
        if (this.b == null) {
            this.b = getResources().newTheme();
            Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.b.setTo(theme);
            }
        }
        this.b.applyStyle(this.a, true);
    }

    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    public final Resources getResources() {
        if (this.e == null) {
            if (this.d == null) {
                this.e = super.getResources();
            } else if (VERSION.SDK_INT >= 17) {
                this.e = createConfigurationContext(this.d).getResources();
            }
        }
        return this.e;
    }
}
