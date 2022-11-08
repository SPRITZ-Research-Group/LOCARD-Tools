package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;

@RestrictTo({a.LIBRARY_GROUP})
public interface m {
    @Nullable
    ColorStateList b();

    @Nullable
    Mode c();

    void setSupportImageTintList(@Nullable ColorStateList colorStateList);

    void setSupportImageTintMode(@Nullable Mode mode);
}
