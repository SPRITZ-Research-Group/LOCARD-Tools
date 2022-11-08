package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;

@RestrictTo({a.LIBRARY_GROUP})
public final class k {
    @NonNull
    public static <T> T a(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }
}
