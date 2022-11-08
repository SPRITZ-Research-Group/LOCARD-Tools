package com.customkeyboard;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.p;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.HashMap;
import java.util.Map;

public final class b implements OnGlobalFocusChangeListener, OnGlobalLayoutListener {
    private static int j = 0;
    private static double k = 0.45d;
    private static String l = "CustomKeyboards";
    private static int m = j;
    private static int n = j;
    private final Activity a;
    private final c b;
    private final a c;
    private final InputMethodManager d;
    private View e;
    private Map<String, CustomKeyboardView> f = new HashMap();
    private EditText g;
    private CustomKeyboardView h;
    private boolean i = false;

    public interface a {
        void contextTerminated(Activity activity, b bVar);

        void onCustomKeyboardHidden();

        void onCustomKeyboardSeen(double d);
    }

    b(Activity activity, c metrics, a contextListener) {
        this.a = activity;
        this.b = metrics;
        this.c = contextListener;
        this.d = (InputMethodManager) activity.getSystemService("input_method");
        this.e = activity.getWindow().getDecorView().findViewById(16908290);
        int[] b = e() == 2 ? new int[]{c.b(this.e)[1], c.b(this.e)[0]} : c.b(this.e);
        if (m == j) {
            m = (int) Math.floor(((double) b[0]) * k);
        }
        if (n == j) {
            n = (int) Math.floor(((double) b[1]) * k);
        }
        ViewTreeObserver viewTreeObserver = this.e.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(this);
        viewTreeObserver.addOnGlobalFocusChangeListener(this);
    }

    private int e() {
        return this.a.getResources().getConfiguration().orientation;
    }

    private int f() {
        switch (e()) {
            case 1:
                return n;
            case 2:
                return m;
            default:
                FLog.w(l, "SoftInput height not available yet");
                return 0;
        }
    }

    private void g() {
        if (this.g != null) {
            this.d.hideSoftInputFromWindow(this.g.getWindowToken(), 0);
        }
    }

    private void h() {
        this.i = true;
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.g();
                if (this.a.i()) {
                    this.a.h();
                } else {
                    this.a.i = false;
                }
            }
        }, 3);
    }

    private boolean i() {
        return this.b.a(this.e) > 0;
    }

    public final void onGlobalLayout() {
        if (i() && !this.i) {
            int a = this.b.a(this.e);
            int i = c.b(this.e)[1];
            int e = e();
            if (a == j) {
                a = f();
            }
            if (a == j) {
                a = (int) Math.floor(((double) i) * k);
            }
            if (a > i) {
                a = i;
            }
            switch (e) {
                case 1:
                    n = a;
                    break;
                case 2:
                    m = a;
                    break;
            }
            if (this.h != null) {
                g();
            }
        }
    }

    public final void a(final String keyboardName, final CustomKeyboardView keyboardView) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ b c;

            public final void run() {
                if (this.c.f.containsKey(keyboardName)) {
                    FLog.w(b.l, String.format("Re-registering keyboard with name %s. Possible high memory use.", new Object[]{keyboardName}));
                }
                this.c.f.put(keyboardName, keyboardView);
            }
        });
    }

    public final void a(final String keyboardName) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ b b;

            public final void run() {
                this.b.f.remove(keyboardName);
                if (this.b.f.isEmpty()) {
                    b.f(this.b);
                    this.b.c.contextTerminated(this.b.a, this.b);
                }
            }
        });
    }

    public final void b(final String keyboardName) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ b b;

            public final void run() {
                if (!(this.b.h == null || this.b.h.a.equals(keyboardName))) {
                    this.b.h.setVisible(false);
                }
                this.b.h = (CustomKeyboardView) this.b.f.get(keyboardName);
                if (this.b.h != null) {
                    int height = this.b.f();
                    this.b.h.setDefaultHeight(height);
                    this.b.h.setVisible(true);
                    this.b.g();
                    this.b.c.onCustomKeyboardSeen((double) p.b((float) height));
                }
            }
        });
    }

    public final void a() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final void run() {
                b.k(this.a);
                if (this.a.g != null) {
                    this.a.d.showSoftInput(this.a.g, 0);
                }
            }
        });
    }

    public final void b() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final void run() {
                b.k(this.a);
            }
        });
    }

    public final boolean c() {
        return this.h != null;
    }

    public final void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (newFocus instanceof EditText) {
            this.g = (EditText) newFocus;
            this.g.setImeOptions(this.g.getImeOptions() | ErrorDialogData.BINDER_CRASH);
            if (VERSION.SDK_INT > 21 && this.h != null) {
                boolean z;
                EditText editText = this.g;
                if (this.h.b) {
                    z = false;
                } else {
                    z = true;
                }
                editText.setShowSoftInputOnFocus(z);
            }
        }
        if (oldFocus == newFocus) {
            h();
        } else if (this.h != null && oldFocus == null) {
            h();
        }
    }

    static /* synthetic */ void f(b x0) {
        ViewTreeObserver viewTreeObserver = x0.e.getViewTreeObserver();
        viewTreeObserver.removeOnGlobalLayoutListener(x0);
        viewTreeObserver.removeOnGlobalFocusChangeListener(x0);
    }

    static /* synthetic */ void k(b x0) {
        if (x0.h != null) {
            x0.h.setVisible(false);
            x0.h = null;
            x0.c.onCustomKeyboardHidden();
        }
    }
}
