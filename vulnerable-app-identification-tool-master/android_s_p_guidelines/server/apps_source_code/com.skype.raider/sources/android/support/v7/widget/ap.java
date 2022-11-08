package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

final class ap extends ag {
    private final WeakReference<Context> a;

    public ap(@NonNull Context context, @NonNull Resources res) {
        super(res);
        this.a = new WeakReference(context);
    }

    public final Drawable getDrawable(int id) throws NotFoundException {
        Drawable d = super.getDrawable(id);
        Context context = (Context) this.a.get();
        if (!(d == null || context == null)) {
            h.a();
            h.a(context, id, d);
        }
        return d;
    }
}
