package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public final class br extends ContextWrapper {
    private static final Object a = new Object();
    private static ArrayList<WeakReference<br>> b;
    private final Resources c;
    private final Theme d;

    private br(Context context) {
        super(context);
        if (cb.a()) {
            this.c = new cb(this, context.getResources());
            this.d = this.c.newTheme();
            this.d.setTo(context.getTheme());
            return;
        }
        this.c = new bt(this, context.getResources());
        this.d = null;
    }

    public final Theme getTheme() {
        return this.d == null ? super.getTheme() : this.d;
    }

    public final void setTheme(int i) {
        if (this.d == null) {
            super.setTheme(i);
        } else {
            this.d.applyStyle(i, true);
        }
    }

    public final Resources getResources() {
        return this.c;
    }

    public final AssetManager getAssets() {
        return this.c.getAssets();
    }

    public static Context a(Context context) {
        Object obj = null;
        if (!((context instanceof br) || (context.getResources() instanceof bt) || (context.getResources() instanceof cb) || (VERSION.SDK_INT >= 21 && !cb.a()))) {
            obj = 1;
        }
        if (obj == null) {
            return context;
        }
        synchronized (a) {
            if (b == null) {
                b = new ArrayList();
            } else {
                int size;
                for (size = b.size() - 1; size >= 0; size--) {
                    WeakReference weakReference = (WeakReference) b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        b.remove(size);
                    }
                }
                size = b.size() - 1;
                while (size >= 0) {
                    WeakReference weakReference2 = (WeakReference) b.get(size);
                    Context context2 = weakReference2 != null ? (br) weakReference2.get() : null;
                    if (context2 == null || context2.getBaseContext() != context) {
                        size--;
                    } else {
                        return context2;
                    }
                }
            }
            Context brVar = new br(context);
            b.add(new WeakReference(brVar));
            return brVar;
        }
    }
}
