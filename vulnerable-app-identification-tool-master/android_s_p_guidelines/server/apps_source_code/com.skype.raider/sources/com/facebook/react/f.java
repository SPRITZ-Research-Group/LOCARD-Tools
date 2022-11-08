package com.facebook.react;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.bridge.w;
import com.facebook.react.bridge.x;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class f {
    private final Set<x> a = Collections.synchronizedSet(new LinkedHashSet());
    private final ComponentCallbacks2 b = new ComponentCallbacks2(this) {
        final /* synthetic */ f a;

        {
            this.a = this$0;
        }

        public final void onTrimMemory(int level) {
            f.a(this.a, level);
        }

        public final void onConfigurationChanged(Configuration newConfig) {
        }

        public final void onLowMemory() {
        }
    };

    f(Context context) {
        context.getApplicationContext().registerComponentCallbacks(this.b);
    }

    public final void a(x listener) {
        this.a.add(listener);
    }

    public final void b(x listener) {
        this.a.remove(listener);
    }

    public final void a(Context context) {
        context.getApplicationContext().unregisterComponentCallbacks(this.b);
    }

    private void a(w level) {
        for (x handleMemoryPressure : (x[]) this.a.toArray(new x[this.a.size()])) {
            handleMemoryPressure.handleMemoryPressure(level);
        }
    }

    static /* synthetic */ void a(f x0, int x1) {
        if (x1 >= 80) {
            x0.a(w.CRITICAL);
        } else if (x1 >= 40 || x1 == 15) {
            x0.a(w.MODERATE);
        } else if (x1 == 20) {
            x0.a(w.UI_HIDDEN);
        }
    }
}
