package androidx.fragment.app;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

final class ai implements OnAttachStateChangeListener, OnPreDrawListener {
    private final View a;
    private ViewTreeObserver b;
    private final Runnable c;

    private ai(View view, Runnable runnable) {
        this.a = view;
        this.b = view.getViewTreeObserver();
        this.c = runnable;
    }

    public static ai a(View view, Runnable runnable) {
        ai aiVar = new ai(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(aiVar);
        view.addOnAttachStateChangeListener(aiVar);
        return aiVar;
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

    public final void onViewAttachedToWindow(View view) {
        this.b = view.getViewTreeObserver();
    }

    public final void onViewDetachedFromWindow(View view) {
        a();
    }
}
