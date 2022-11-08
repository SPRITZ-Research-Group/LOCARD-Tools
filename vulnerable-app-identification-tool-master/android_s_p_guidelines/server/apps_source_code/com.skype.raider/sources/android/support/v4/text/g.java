package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

public final class g {
    @Deprecated
    public static final Locale a = new Locale("", "");

    public static int a(@Nullable Locale locale) {
        if (VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (!(locale == null || locale.equals(a))) {
            String scriptSubtag = b.a(locale);
            if (scriptSubtag == null) {
                switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
                    case (byte) 1:
                    case (byte) 2:
                        return 1;
                    default:
                        return 0;
                }
            } else if (scriptSubtag.equalsIgnoreCase("Arab") || scriptSubtag.equalsIgnoreCase("Hebr")) {
                return 1;
            }
        }
        return 0;
    }
}
