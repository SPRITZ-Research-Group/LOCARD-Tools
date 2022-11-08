package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.app.AppCompatDelegate;
import java.lang.ref.WeakReference;

@RestrictTo({a.LIBRARY_GROUP})
public final class av extends Resources {
    private final WeakReference<Context> a;

    public static boolean a() {
        return AppCompatDelegate.l() && VERSION.SDK_INT <= 20;
    }

    public av(@NonNull Context context, @NonNull Resources res) {
        super(res.getAssets(), res.getDisplayMetrics(), res.getConfiguration());
        this.a = new WeakReference(context);
    }

    public final Drawable getDrawable(int id) throws NotFoundException {
        Context context = (Context) this.a.get();
        if (context != null) {
            return h.a().a(context, this, id);
        }
        return super.getDrawable(id);
    }

    final Drawable a(int id) {
        return super.getDrawable(id);
    }
}
