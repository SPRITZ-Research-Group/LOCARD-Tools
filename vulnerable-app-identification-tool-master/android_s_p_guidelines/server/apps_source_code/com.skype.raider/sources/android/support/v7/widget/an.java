package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({a.LIBRARY_GROUP})
public final class an extends ContextWrapper {
    private static final Object a = new Object();
    private static ArrayList<WeakReference<an>> b;
    private final Resources c;
    private final Theme d;

    public static Context a(@NonNull Context context) {
        Object obj = null;
        if (!((context instanceof an) || (context.getResources() instanceof ap) || (context.getResources() instanceof av) || (VERSION.SDK_INT >= 21 && !av.a()))) {
            obj = 1;
        }
        if (obj == null) {
            return context;
        }
        synchronized (a) {
            an wrapper;
            if (b == null) {
                b = new ArrayList();
            } else {
                int i;
                WeakReference<an> ref;
                for (i = b.size() - 1; i >= 0; i--) {
                    ref = (WeakReference) b.get(i);
                    if (ref == null || ref.get() == null) {
                        b.remove(i);
                    }
                }
                i = b.size() - 1;
                while (i >= 0) {
                    ref = (WeakReference) b.get(i);
                    wrapper = ref != null ? (an) ref.get() : null;
                    if (wrapper == null || wrapper.getBaseContext() != context) {
                        i--;
                    } else {
                        return wrapper;
                    }
                }
            }
            wrapper = new an(context);
            b.add(new WeakReference(wrapper));
            return wrapper;
        }
    }

    private an(@NonNull Context base) {
        super(base);
        if (av.a()) {
            this.c = new av(this, base.getResources());
            this.d = this.c.newTheme();
            this.d.setTo(base.getTheme());
            return;
        }
        this.c = new ap(this, base.getResources());
        this.d = null;
    }

    public final Theme getTheme() {
        return this.d == null ? super.getTheme() : this.d;
    }

    public final void setTheme(int resid) {
        if (this.d == null) {
            super.setTheme(resid);
        } else {
            this.d.applyStyle(resid, true);
        }
    }

    public final Resources getResources() {
        return this.c;
    }

    public final AssetManager getAssets() {
        return this.c.getAssets();
    }
}
