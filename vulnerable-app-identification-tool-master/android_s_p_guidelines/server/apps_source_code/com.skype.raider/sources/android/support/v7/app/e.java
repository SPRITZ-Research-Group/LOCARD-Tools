package android.support.v7.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(14)
class e extends d {
    private int t = -100;
    private boolean u;
    private boolean v = true;
    private b w;

    class a extends a {
        final /* synthetic */ e c;

        a(e this$0, Callback callback) {
            this.c = this$0;
            super(this$0, callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (this.c.o()) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final ActionMode a(ActionMode.Callback callback) {
            android.support.v7.view.f.a callbackWrapper = new android.support.v7.view.f.a(this.c.a, callback);
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = this.c;
            if (appCompatDelegateImplV9.m != null) {
                appCompatDelegateImplV9.m.c();
            }
            android.support.v7.view.b.a bVar = new b(appCompatDelegateImplV9, callbackWrapper);
            ActionBar a = appCompatDelegateImplV9.a();
            if (a != null) {
                appCompatDelegateImplV9.m = a.a(bVar);
            }
            if (appCompatDelegateImplV9.m == null) {
                appCompatDelegateImplV9.m = appCompatDelegateImplV9.a(bVar);
            }
            android.support.v7.view.b supportActionMode = appCompatDelegateImplV9.m;
            if (supportActionMode != null) {
                return callbackWrapper.b(supportActionMode);
            }
            return null;
        }
    }

    @VisibleForTesting
    final class b {
        final /* synthetic */ e a;
        private k b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;

        b(e this$0, @NonNull k twilightManager) {
            this.a = this$0;
            this.b = twilightManager;
            this.c = twilightManager.a();
        }

        final int a() {
            this.c = this.b.a();
            return this.c ? 2 : 1;
        }

        final void b() {
            boolean isNight = this.b.a();
            if (isNight != this.c) {
                this.c = isNight;
                this.a.j();
            }
        }

        final void c() {
            d();
            if (this.d == null) {
                this.d = new BroadcastReceiver(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = this$1;
                    }

                    public final void onReceive(Context context, Intent intent) {
                        this.a.b();
                    }
                };
            }
            if (this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            this.a.a.registerReceiver(this.d, this.e);
        }

        final void d() {
            if (this.d != null) {
                this.a.a.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }

    e(Context context, Window window, a callback) {
        super(context, window, callback);
    }

    public final void a(Bundle savedInstanceState) {
        super.a(savedInstanceState);
        if (savedInstanceState != null && this.t == -100) {
            this.t = savedInstanceState.getInt("appcompat:local_night_mode", -100);
        }
    }

    Callback a(Callback callback) {
        return new a(this, callback);
    }

    public final boolean o() {
        return this.v;
    }

    public final boolean j() {
        int nightMode;
        boolean applied = false;
        if (this.t != -100) {
            nightMode = this.t;
        } else {
            nightMode = AppCompatDelegate.k();
        }
        int modeToApply = f(nightMode);
        if (modeToApply != -1) {
            Resources resources = this.a.getResources();
            Configuration configuration = resources.getConfiguration();
            int i = configuration.uiMode & 48;
            int i2 = modeToApply == 2 ? 32 : 16;
            if (i != i2) {
                if (w()) {
                    ((Activity) this.a).recreate();
                } else {
                    Configuration configuration2 = new Configuration(configuration);
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    configuration2.uiMode = i2 | (configuration2.uiMode & -49);
                    resources.updateConfiguration(configuration2, displayMetrics);
                    if (VERSION.SDK_INT < 26) {
                        h.a(resources);
                    }
                }
                applied = true;
            } else {
                applied = false;
            }
        }
        if (nightMode == 0) {
            v();
            this.w.c();
        }
        this.u = true;
        return applied;
    }

    public final void d() {
        super.d();
        j();
    }

    public final void e() {
        super.e();
        if (this.w != null) {
            this.w.d();
        }
    }

    int f(int mode) {
        switch (mode) {
            case -100:
                return -1;
            case 0:
                v();
                return this.w.a();
            default:
                return mode;
        }
    }

    public final void b(Bundle outState) {
        super.b(outState);
        if (this.t != -100) {
            outState.putInt("appcompat:local_night_mode", this.t);
        }
    }

    public final void h() {
        super.h();
        if (this.w != null) {
            this.w.d();
        }
    }

    private void v() {
        if (this.w == null) {
            this.w = new b(this, k.a(this.a));
        }
    }

    private boolean w() {
        if (!this.u || !(this.a instanceof Activity)) {
            return false;
        }
        try {
            if ((this.a.getPackageManager().getActivityInfo(new ComponentName(this.a, this.a.getClass()), 0).configChanges & 512) == 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return true;
        }
    }
}
