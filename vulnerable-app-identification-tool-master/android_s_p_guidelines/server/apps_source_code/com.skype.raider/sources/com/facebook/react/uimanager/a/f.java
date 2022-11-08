package com.facebook.react.uimanager.a;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class f {
    private final a a = new i();
    private final a b = new k();
    private final a c = new j();
    private boolean d;

    public final void a(@Nullable am config) {
        int globalDuration = 0;
        if (config == null) {
            a();
            return;
        }
        this.d = false;
        if (config.hasKey("duration")) {
            globalDuration = config.getInt("duration");
        }
        if (config.hasKey(h.CREATE.toString())) {
            this.a.a(config.getMap(h.CREATE.toString()), globalDuration);
            this.d = true;
        }
        if (config.hasKey(h.UPDATE.toString())) {
            this.b.a(config.getMap(h.UPDATE.toString()), globalDuration);
            this.d = true;
        }
        if (config.hasKey(h.DELETE.toString())) {
            this.c.a(config.getMap(h.DELETE.toString()), globalDuration);
            this.d = true;
        }
    }

    public final void a() {
        this.a.b();
        this.b.b();
        this.c.b();
        this.d = false;
    }

    public final boolean a(View viewToAnimate) {
        return this.d && viewToAnimate.getParent() != null;
    }

    public final void a(View view, int x, int y, int width, int height) {
        ap.b();
        a aVar = (view.getWidth() == 0 || view.getHeight() == 0) ? this.a : this.b;
        Animation animation = aVar.b(view, x, y, width, height);
        if (animation == null || !(animation instanceof d)) {
            view.layout(x, y, x + width, y + height);
        }
        if (animation != null) {
            view.startAnimation(animation);
        }
    }

    public final void a(View view, final g listener) {
        ap.b();
        Animation animation = this.c.b(view, view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (animation != null) {
            b(view);
            animation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ f b;

                public final void onAnimationStart(Animation anim) {
                }

                public final void onAnimationRepeat(Animation anim) {
                }

                public final void onAnimationEnd(Animation anim) {
                    listener.a();
                }
            });
            view.startAnimation(animation);
            return;
        }
        listener.a();
    }

    private void b(View view) {
        view.setClickable(false);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                b(viewGroup.getChildAt(i));
            }
        }
    }
}
