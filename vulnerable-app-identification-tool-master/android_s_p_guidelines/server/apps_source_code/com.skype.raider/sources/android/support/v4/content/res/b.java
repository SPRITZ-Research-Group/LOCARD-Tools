package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({a.LIBRARY_GROUP})
public final class b {
    public static boolean a(@NonNull XmlPullParser parser, @NonNull String attrName) {
        return parser.getAttributeValue("http://schemas.android.com/apk/res/android", attrName) != null;
    }

    public static float a(@NonNull TypedArray a, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int resId, float defaultValue) {
        return !a(parser, attrName) ? defaultValue : a.getFloat(resId, defaultValue);
    }

    public static int a(@NonNull TypedArray a, @NonNull XmlPullParser parser, String attrName, @StyleableRes int resId, int defaultValue) {
        return !a(parser, attrName) ? defaultValue : a.getInt(resId, defaultValue);
    }

    @ColorInt
    public static int b(@NonNull TypedArray a, @NonNull XmlPullParser parser, String attrName, @StyleableRes int resId, @ColorInt int defaultValue) {
        return !a(parser, attrName) ? defaultValue : a.getColor(resId, defaultValue);
    }

    @AnyRes
    public static int a(@NonNull TypedArray a, @NonNull XmlPullParser parser, String attrName, @StyleableRes int resId) {
        if (a(parser, attrName)) {
            return a.getResourceId(resId, 0);
        }
        return 0;
    }

    public static String b(@NonNull TypedArray a, @NonNull XmlPullParser parser, String attrName, @StyleableRes int resId) {
        if (a(parser, attrName)) {
            return a.getString(resId);
        }
        return null;
    }

    public static TypedValue a(TypedArray a, XmlPullParser parser, String attrName) {
        if (a(parser, attrName)) {
            return a.peekValue(0);
        }
        return null;
    }

    public static TypedArray a(Resources res, Theme theme, AttributeSet set, int[] attrs) {
        if (theme == null) {
            return res.obtainAttributes(set, attrs);
        }
        return theme.obtainStyledAttributes(set, attrs, 0, 0);
    }
}
