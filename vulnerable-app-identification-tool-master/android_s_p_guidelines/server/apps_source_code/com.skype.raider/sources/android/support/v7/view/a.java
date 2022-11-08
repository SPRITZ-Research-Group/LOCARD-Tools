package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.a.b;
import android.support.v7.appcompat.a.d;
import android.support.v7.appcompat.a.j;
import android.view.ViewConfiguration;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class a {
    private Context a;

    public static a a(Context context) {
        return new a(context);
    }

    private a(Context context) {
        this.a = context;
    }

    public final int a() {
        Configuration configuration = this.a.getResources().getConfiguration();
        int widthDp = configuration.screenWidthDp;
        int heightDp = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || widthDp > 600 || ((widthDp > 960 && heightDp > 720) || (widthDp > 720 && heightDp > 960))) {
            return 5;
        }
        if (widthDp >= 500 || ((widthDp > 640 && heightDp > 480) || (widthDp > 480 && heightDp > 640))) {
            return 4;
        }
        if (widthDp >= 360) {
            return 3;
        }
        return 2;
    }

    public final boolean b() {
        if (VERSION.SDK_INT < 19 && ViewConfiguration.get(this.a).hasPermanentMenuKey()) {
            return false;
        }
        return true;
    }

    public final int c() {
        return this.a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public final boolean d() {
        return this.a.getResources().getBoolean(b.abc_action_bar_embed_tabs);
    }

    public final int e() {
        TypedArray a = this.a.obtainStyledAttributes(null, j.ActionBar, android.support.v7.appcompat.a.a.actionBarStyle, 0);
        int height = a.getLayoutDimension(j.ActionBar_height, 0);
        Resources r = this.a.getResources();
        if (!d()) {
            height = Math.min(height, r.getDimensionPixelSize(d.abc_action_bar_stacked_max_height));
        }
        a.recycle();
        return height;
    }

    public final boolean f() {
        return this.a.getApplicationInfo().targetSdkVersion < 14;
    }

    public final int g() {
        return this.a.getResources().getDimensionPixelSize(d.abc_action_bar_stacked_tab_max_width);
    }
}
