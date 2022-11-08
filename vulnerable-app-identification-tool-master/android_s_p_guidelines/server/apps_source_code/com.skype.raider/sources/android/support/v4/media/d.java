package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;

@RestrictTo({a.LIBRARY_GROUP})
public final class d {
    public static boolean a(Bundle options1, Bundle options2) {
        if (options1 == options2) {
            return true;
        }
        if (options1 == null) {
            if (options2.getInt("android.media.browse.extra.PAGE", -1) == -1 && options2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
                return true;
            }
            return false;
        } else if (options2 == null) {
            if (options1.getInt("android.media.browse.extra.PAGE", -1) == -1 && options1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
                return true;
            }
            return false;
        } else if (options1.getInt("android.media.browse.extra.PAGE", -1) == options2.getInt("android.media.browse.extra.PAGE", -1) && options1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == options2.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
            return true;
        } else {
            return false;
        }
    }
}
