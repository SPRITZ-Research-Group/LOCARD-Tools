package com.imagepicker.c;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.react.bridge.am;

public final class c {
    @NonNull
    public static boolean a(@NonNull Class clazz, @NonNull am target, @NonNull String key) {
        if (!target.hasKey(key) || target.isNull(key)) {
            return false;
        }
        if (!String.class.equals(clazz)) {
            return true;
        }
        if (TextUtils.isEmpty(target.getString(key))) {
            return false;
        }
        return true;
    }
}
