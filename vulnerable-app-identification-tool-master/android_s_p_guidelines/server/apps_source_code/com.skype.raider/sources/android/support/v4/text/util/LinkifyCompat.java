package android.support.v4.text.util;

import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;

public final class LinkifyCompat {
    private static final String[] a = new String[0];
    private static final Comparator<a> b = new Comparator<a>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            a aVar = (a) obj;
            a aVar2 = (a) obj2;
            if (aVar.a < aVar2.a) {
                return -1;
            }
            if (aVar.a > aVar2.a) {
                return 1;
            }
            if (aVar.b < aVar2.b) {
                return 1;
            }
            if (aVar.b <= aVar2.b) {
                return 0;
            }
            return -1;
        }
    };

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LinkifyMask {
    }

    private static class a {
        int a;
        int b;

        a() {
        }
    }
}
