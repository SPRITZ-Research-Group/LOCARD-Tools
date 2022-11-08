package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build.VERSION;

public final class d {
    public static b a(Resources resources, Bitmap bitmap) {
        if (VERSION.SDK_INT >= 21) {
            return new c(resources, bitmap);
        }
        return new e(resources, bitmap);
    }
}
