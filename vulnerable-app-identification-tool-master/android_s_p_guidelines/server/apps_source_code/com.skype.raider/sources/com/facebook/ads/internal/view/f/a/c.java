package com.facebook.ads.internal.view.f.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.f.a;

public abstract class c extends RelativeLayout implements b {
    @Nullable
    private a a;

    public c(Context context) {
        super(context);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    @Nullable
    protected final a a() {
        return this.a;
    }

    public final void a(a aVar) {
        this.a = aVar;
        b();
    }

    protected void b() {
    }

    public final void b(a aVar) {
        c();
        this.a = null;
    }

    protected void c() {
    }
}
