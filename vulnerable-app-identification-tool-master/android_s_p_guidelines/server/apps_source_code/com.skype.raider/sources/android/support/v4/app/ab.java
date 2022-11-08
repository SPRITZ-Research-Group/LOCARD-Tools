package android.support.v4.app;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

final class ab implements OnAttachStateChangeListener, OnPreDrawListener {
    private final View a;
    private ViewTreeObserver b;
    private final Runnable c;

    private ab(View view, Runnable runnable) {
        this.a = view;
        this.b = view.getViewTreeObserver();
        this.c = runnable;
    }

    public static ab a(View view, Runnable runnable) {
        ab listener = new ab(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(listener);
        view.addOnAttachStateChangeListener(listener);
        return listener;
    }

    public final boolean onPreDraw() {
        a();
        this.c.run();
        return true;
    }

    private void a() {
        if (this.b.isAlive()) {
            this.b.removeOnPreDrawListener(this);
        } else {
            this.a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.a.removeOnAttachStateChangeListener(this);
    }

    public final void onViewAttachedToWindow(View v) {
        this.b = v.getViewTreeObserver();
    }

    public final void onViewDetachedFromWindow(View v) {
        a();
    }
}
