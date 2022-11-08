package com.facebook.react;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.facebook.infer.annotation.a;
import com.facebook.react.devsupport.c;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.core.d;
import javax.annotation.Nullable;

public class i {
    private final int a = 1111;
    @Nullable
    private final Activity b;
    @Nullable
    private final FragmentActivity c;
    @Nullable
    private final String d;
    @Nullable
    private ReactRootView e;
    @Nullable
    private c f;
    @Nullable
    private d g;
    @Nullable
    private com.facebook.react.bridge.d h;

    public i(Activity activity, @Nullable String mainComponentName) {
        this.b = activity;
        this.d = mainComponentName;
        this.c = null;
    }

    public i(FragmentActivity fragmentActivity) {
        this.c = fragmentActivity;
        this.d = null;
        this.b = null;
    }

    protected ReactRootView a() {
        return new ReactRootView(i());
    }

    protected final void b() {
        h();
        if (this.d != null) {
            a(this.d);
        }
        this.f = new c();
    }

    private void a(String appKey) {
        if (this.e != null) {
            throw new IllegalStateException("Cannot loadApp while app is already running.");
        }
        this.e = a();
        this.e.a(h().a(), appKey);
        ((Activity) i()).setContentView(this.e);
    }

    protected final void c() {
        if (h().b()) {
            h().a().a((Activity) i());
        }
    }

    protected final void d() {
        if (h().b()) {
            h().a().a((Activity) i(), (b) ((Activity) i()));
        }
        if (this.h != null) {
            this.h.invoke(new Object[0]);
            this.h = null;
        }
    }

    protected final void e() {
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        if (h().b()) {
            h().a().b((Activity) i());
        }
    }

    public final void a(int requestCode, int resultCode, Intent data) {
        if (h().b()) {
            h().a().a((Activity) i(), requestCode, resultCode, data);
        } else if (requestCode == 1111 && VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(i())) {
            if (this.d != null) {
                a(this.d);
            }
            Toast.makeText(i(), "Overlay permissions have been granted.", 1).show();
        }
    }

    public final boolean f() {
        if (h().b()) {
            h();
        }
        return false;
    }

    public final boolean g() {
        if (!h().b()) {
            return false;
        }
        h().a().e();
        return true;
    }

    public final boolean a(Intent intent) {
        if (!h().b()) {
            return false;
        }
        h().a().a(intent);
        return true;
    }

    @TargetApi(23)
    public final void a(String[] permissions, int requestCode, d listener) {
        this.g = listener;
        ((Activity) i()).requestPermissions(permissions, requestCode);
    }

    public final void a(final int requestCode, final String[] permissions, final int[] grantResults) {
        this.h = new com.facebook.react.bridge.d(this) {
            final /* synthetic */ i d;

            public final void invoke(Object... args) {
                if (this.d.g != null && this.d.g.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
                    this.d.g = null;
                }
            }
        };
    }

    private Context i() {
        if (this.b != null) {
            return this.b;
        }
        return (Context) a.a(this.c);
    }

    private p h() {
        return ((j) ((Activity) i()).getApplication()).a();
    }
}
