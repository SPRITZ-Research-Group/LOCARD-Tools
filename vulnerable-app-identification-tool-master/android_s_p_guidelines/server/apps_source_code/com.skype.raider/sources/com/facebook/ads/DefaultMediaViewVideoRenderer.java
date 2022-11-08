package com.facebook.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.n.c;
import com.facebook.ads.internal.n.e;
import java.lang.ref.WeakReference;

public final class DefaultMediaViewVideoRenderer extends MediaViewVideoRenderer {
    private c d;

    private static class a implements com.facebook.ads.internal.n.c.a {
        private WeakReference<e> a;

        public a(e eVar) {
            this.a = new WeakReference(eVar);
        }

        public final void a(boolean z) {
            if (this.a.get() != null) {
                ((e) this.a.get()).a(z, false);
            }
        }
    }

    public DefaultMediaViewVideoRenderer(Context context) {
        super(context);
        this.d = new c(context, this);
        setVolume(0.0f);
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new c(context, this);
        setVolume(0.0f);
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new c(context, this);
        setVolume(0.0f);
    }

    protected final void a() {
        super.a();
        this.d.a();
    }

    protected final void a(g gVar) {
        super.a(gVar);
        this.d.a(gVar.c(), new a(gVar.c()));
    }

    public final void b() {
        super.b();
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DefaultMediaViewVideoRenderer a;

            {
                this.a = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.d.b();
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d.c();
    }

    protected final void onDetachedFromWindow() {
        this.d.d();
        super.onDetachedFromWindow();
    }

    protected final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.d.e();
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.d.f();
    }
}
