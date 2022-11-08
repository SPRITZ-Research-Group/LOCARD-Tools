package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.view.i;
import android.support.v7.view.menu.g;
import android.support.v7.widget.aq;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;
import java.lang.Thread.UncaughtExceptionHandler;

@RequiresApi(14)
abstract class b extends AppCompatDelegate {
    private static boolean m = true;
    private static final boolean n;
    private static final int[] o = new int[]{16842836};
    final Context a;
    final Window b;
    final Callback c = this.b.getCallback();
    final Callback d;
    final a e;
    ActionBar f;
    MenuInflater g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    private CharSequence p;
    private boolean q;
    private boolean r;

    class a extends i {
        final /* synthetic */ b a;

        a(b this$0, Callback callback) {
            this.a = this$0;
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent event) {
            return this.a.a(event) || super.dispatchKeyEvent(event);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent event) {
            return super.dispatchKeyShortcutEvent(event) || this.a.a(event.getKeyCode(), event);
        }

        public boolean onCreatePanelMenu(int featureId, Menu menu) {
            if (featureId != 0 || (menu instanceof g)) {
                return super.onCreatePanelMenu(featureId, menu);
            }
            return false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int featureId, View view, Menu menu) {
            g mb = menu instanceof g ? (g) menu : null;
            if (featureId == 0 && mb == null) {
                return false;
            }
            if (mb != null) {
                mb.c(true);
            }
            boolean handled = super.onPreparePanel(featureId, view, menu);
            if (mb == null) {
                return handled;
            }
            mb.c(false);
            return handled;
        }

        public boolean onMenuOpened(int featureId, Menu menu) {
            super.onMenuOpened(featureId, menu);
            this.a.e(featureId);
            return true;
        }

        public void onPanelClosed(int featureId, Menu menu) {
            super.onPanelClosed(featureId, menu);
            this.a.d(featureId);
        }
    }

    abstract android.support.v7.view.b a(android.support.v7.view.b.a aVar);

    abstract boolean a(int i, KeyEvent keyEvent);

    abstract boolean a(KeyEvent keyEvent);

    abstract void b(CharSequence charSequence);

    abstract void d(int i);

    abstract boolean e(int i);

    abstract void m();

    static {
        boolean z;
        if (VERSION.SDK_INT < 21) {
            z = true;
        } else {
            z = false;
        }
        n = z;
        if (z && !m) {
            final UncaughtExceptionHandler defHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                public final void uncaughtException(Thread thread, Throwable thowable) {
                    Object obj = null;
                    if (thowable instanceof NotFoundException) {
                        String message = thowable.getMessage();
                        if (message != null && (message.contains("drawable") || message.contains("Drawable"))) {
                            obj = 1;
                        }
                    }
                    if (obj != null) {
                        Throwable wrapped = new NotFoundException(thowable.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        wrapped.initCause(thowable.getCause());
                        wrapped.setStackTrace(thowable.getStackTrace());
                        defHandler.uncaughtException(thread, wrapped);
                        return;
                    }
                    defHandler.uncaughtException(thread, thowable);
                }
            });
        }
    }

    b(Context context, Window window, a callback) {
        this.a = context;
        this.b = window;
        this.e = callback;
        if (this.c instanceof a) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.d = a(this.c);
        this.b.setCallback(this.d);
        aq a = aq.a(context, null, o);
        Drawable winBg = a.b(0);
        if (winBg != null) {
            this.b.setBackgroundDrawable(winBg);
        }
        a.a();
    }

    Callback a(Callback callback) {
        return new a(this, callback);
    }

    public final ActionBar a() {
        m();
        return this.f;
    }

    public final MenuInflater b() {
        if (this.g == null) {
            m();
            this.g = new android.support.v7.view.g(this.f != null ? this.f.b() : this.a);
        }
        return this.g;
    }

    final Context n() {
        Context context = null;
        ActionBar ab = a();
        if (ab != null) {
            context = ab.b();
        }
        if (context == null) {
            return this.a;
        }
        return context;
    }

    public void d() {
        this.q = true;
    }

    public void e() {
        this.q = false;
    }

    public void h() {
        this.r = true;
    }

    public boolean o() {
        return false;
    }

    public boolean j() {
        return false;
    }

    final boolean p() {
        return this.r;
    }

    public final void a(CharSequence title) {
        this.p = title;
        b(title);
    }

    public void b(Bundle outState) {
    }

    final CharSequence q() {
        if (this.c instanceof Activity) {
            return ((Activity) this.c).getTitle();
        }
        return this.p;
    }
}
