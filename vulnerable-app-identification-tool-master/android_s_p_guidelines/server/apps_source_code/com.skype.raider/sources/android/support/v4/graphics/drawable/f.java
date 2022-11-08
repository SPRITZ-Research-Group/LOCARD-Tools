package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.ColorInt;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;

@RestrictTo({a.LIBRARY_GROUP})
public interface f {
    void setTint(@ColorInt int i);

    void setTintList(ColorStateList colorStateList);

    void setTintMode(Mode mode);
}
