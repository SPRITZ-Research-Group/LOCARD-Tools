package com.facebook.react.views.toolbar;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.MeasureSpec;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.p;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import javax.annotation.Nullable;

public class ReactToolbar extends Toolbar {
    private final com.facebook.drawee.view.b e = com.facebook.drawee.view.b.a(w());
    private final com.facebook.drawee.view.b f = com.facebook.drawee.view.b.a(w());
    private final com.facebook.drawee.view.b g = com.facebook.drawee.view.b.a(w());
    private final com.facebook.drawee.view.c<com.facebook.drawee.d.a> h = new com.facebook.drawee.view.c();
    private b i = new b(this, this.e) {
        final /* synthetic */ ReactToolbar a;

        protected final void a(Drawable d) {
            this.a.setLogo(d);
        }
    };
    private b j = new b(this, this.f) {
        final /* synthetic */ ReactToolbar a;

        protected final void a(Drawable d) {
            this.a.setNavigationIcon(d);
        }
    };
    private b k = new b(this, this.g) {
        final /* synthetic */ ReactToolbar a;

        protected final void a(Drawable d) {
            this.a.setOverflowIcon(d);
        }
    };
    private final Runnable l = new Runnable(this) {
        final /* synthetic */ ReactToolbar a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.measure(MeasureSpec.makeMeasureSpec(this.a.getWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(this.a.getHeight(), ErrorDialogData.SUPPRESSED));
            this.a.layout(this.a.getLeft(), this.a.getTop(), this.a.getRight(), this.a.getBottom());
        }
    };

    private abstract class b extends BaseControllerListener<ImageInfo> {
        private final com.facebook.drawee.view.b a;
        final /* synthetic */ ReactToolbar b;
        private c c;

        protected abstract void a(Drawable drawable);

        public /* synthetic */ void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
            ImageInfo imageInfo = (ImageInfo) obj;
            super.onFinalImageSet(str, imageInfo, animatable);
            if (this.c != null) {
                imageInfo = this.c;
            }
            a(new a(this.a.f(), imageInfo));
        }

        public b(ReactToolbar reactToolbar, com.facebook.drawee.view.b holder) {
            this.b = reactToolbar;
            this.a = holder;
        }

        public final void a(c iconImageInfo) {
            this.c = iconImageInfo;
        }
    }

    private class a extends b {
        final /* synthetic */ ReactToolbar a;
        private final MenuItem c;

        a(ReactToolbar reactToolbar, MenuItem item, com.facebook.drawee.view.b holder) {
            this.a = reactToolbar;
            super(reactToolbar, holder);
            this.c = item;
        }

        protected final void a(Drawable d) {
            this.c.setIcon(d);
        }
    }

    private static class c implements ImageInfo {
        private int a;
        private int b;

        public c(int width, int height) {
            this.a = width;
            this.b = height;
        }

        public final int a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }
    }

    public ReactToolbar(Context context) {
        super(context);
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.l);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        u();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        v();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        v();
    }

    private void u() {
        this.e.c();
        this.f.c();
        this.g.c();
        this.h.b();
    }

    private void v() {
        this.e.b();
        this.f.b();
        this.g.b();
        this.h.a();
    }

    final void a(@Nullable am source) {
        a(source, this.i, this.e);
    }

    final void b(@Nullable am source) {
        a(source, this.j, this.f);
    }

    final void c(@Nullable am source) {
        a(source, this.k, this.g);
    }

    final void a(@Nullable al actions) {
        Menu menu = m();
        menu.clear();
        this.h.c();
        if (actions != null) {
            for (int i = 0; i < actions.size(); i++) {
                int showAsAction;
                am action = actions.getMap(i);
                MenuItem item = menu.add(0, 0, i, action.getString("title"));
                if (action.hasKey("icon")) {
                    am map = action.getMap("icon");
                    com.facebook.drawee.interfaces.a w = w();
                    getContext();
                    com.facebook.drawee.view.b a = com.facebook.drawee.view.b.a(w);
                    b aVar = new a(this, item, a);
                    aVar.a(d(map));
                    a(map, aVar, a);
                    this.h.a(a);
                }
                if (action.hasKey("show")) {
                    showAsAction = action.getInt("show");
                } else {
                    showAsAction = 0;
                }
                if (action.hasKey("showWithText") && action.getBoolean("showWithText")) {
                    showAsAction |= 4;
                }
                item.setShowAsAction(showAsAction);
            }
        }
    }

    private void a(am source, b controllerListener, com.facebook.drawee.view.b holder) {
        String uri;
        Drawable drawable = null;
        if (source != null) {
            uri = source.getString(ReactVideoViewManager.PROP_SRC_URI);
        } else {
            uri = null;
        }
        if (uri == null) {
            controllerListener.a(null);
        } else if (uri.startsWith("http://") || uri.startsWith("https://") || uri.startsWith("file://")) {
            controllerListener.a(d(source));
            holder.a(((com.facebook.drawee.backends.pipeline.c) ((com.facebook.drawee.backends.pipeline.c) Fresco.a().a(Uri.parse(uri)).a((ControllerListener) controllerListener)).a(holder.d())).h());
            holder.f().setVisible(true, true);
            return;
        } else if (a(uri) != 0) {
            drawable = getResources().getDrawable(a(uri));
        }
        controllerListener.a(drawable);
    }

    private com.facebook.drawee.d.a w() {
        return new com.facebook.drawee.d.b(getResources()).e(com.facebook.drawee.c.q.b.c).a(0).s();
    }

    private int a(String name) {
        return getResources().getIdentifier(name, "drawable", getContext().getPackageName());
    }

    private static c d(am source) {
        if (source.hasKey("width") && source.hasKey("height")) {
            return new c(Math.round(p.a((float) source.getInt("width"))), Math.round(p.a((float) source.getInt("height"))));
        }
        return null;
    }
}
