package com.skype4life;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ai;
import com.facebook.react.i;
import javax.annotation.Nullable;

public final class c extends i {
    private volatile ai a;
    private ReactRootView b;

    public c(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    protected final ReactRootView a() {
        ReactRootView rootView = super.a();
        if (this.a == null) {
            rootView.setBackground(new f(rootView));
            this.b = rootView;
        }
        return rootView;
    }

    public final void a(Drawable drawable) {
        if (this.b != null) {
            this.b.setBackground(drawable);
        }
    }

    public final void a(ai context) {
        this.a = context;
    }
}
