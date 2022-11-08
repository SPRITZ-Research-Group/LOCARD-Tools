package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.b.b;
import android.arch.lifecycle.c;
import android.arch.lifecycle.d;
import android.arch.lifecycle.f;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.util.l;

@RestrictTo({a.LIBRARY_GROUP})
public class SupportActivity extends Activity implements c {
    private l<Class<? extends Object>, Object> mExtraDataMap = new l();
    private d mLifecycleRegistry = new d(this);

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f.a((Activity) this);
    }

    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        this.mLifecycleRegistry.a(b.CREATED);
        super.onSaveInstanceState(outState);
    }

    public android.arch.lifecycle.b getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
