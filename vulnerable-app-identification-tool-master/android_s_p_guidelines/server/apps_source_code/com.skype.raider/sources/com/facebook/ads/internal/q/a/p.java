package com.facebook.ads.internal.q.a;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public final class p implements OnSystemUiVisibilityChangeListener {
    private final View a;
    private int b;
    @Nullable
    private Window c;
    private a d = a.DEFAULT;
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ p a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.a(false);
        }
    };

    public enum a {
        DEFAULT,
        FULL_SCREEN
    }

    public p(View view) {
        this.a = view;
        this.a.setOnSystemUiVisibilityChangeListener(this);
    }

    private void a(int i, boolean z) {
        if (this.c != null) {
            LayoutParams attributes = this.c.getAttributes();
            if (z) {
                attributes.flags |= i;
            } else {
                attributes.flags &= i ^ -1;
            }
            this.c.setAttributes(attributes);
        }
    }

    private void a(boolean z) {
        if (!a.DEFAULT.equals(this.d)) {
            int i = 3840;
            if (!z) {
                i = 3847;
            }
            Handler handler = this.a.getHandler();
            if (handler != null && z) {
                handler.removeCallbacks(this.e);
                handler.postDelayed(this.e, 2000);
            }
            this.a.setSystemUiVisibility(i);
        }
    }

    public final void a() {
        this.c = null;
    }

    public final void a(Window window) {
        this.c = window;
    }

    public final void a(a aVar) {
        this.d = aVar;
        switch (this.d) {
            case FULL_SCREEN:
                a(67108864, true);
                a(134217728, true);
                a(false);
                return;
            default:
                a(67108864, false);
                a(134217728, false);
                this.a.setSystemUiVisibility(0);
                return;
        }
    }

    public final void onSystemUiVisibilityChange(int i) {
        int i2 = this.b ^ i;
        this.b = i;
        if ((i2 & 2) != 0 && (i & 2) == 0) {
            a(true);
        }
    }
}
