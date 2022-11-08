package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.c;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class a {
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static Typeface a(@NonNull Context context, @FontRes int id, TypedValue value, int style, @Nullable TextView targetView) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface a = a(context, resources, value, id, style, targetView);
        if (a != null) {
            return a;
        }
        throw new NotFoundException("Font resource ID #0x" + Integer.toHexString(id));
    }

    private static Typeface a(@NonNull Context context, Resources wrapper, TypedValue value, int id, int style, @Nullable TextView targetView) {
        if (value.string == null) {
            throw new NotFoundException("Resource \"" + wrapper.getResourceName(id) + "\" (" + Integer.toHexString(id) + ") is not a Font: " + value);
        }
        String file = value.string.toString();
        if (!file.startsWith("res/")) {
            return null;
        }
        Typeface cached = c.a(wrapper, id, style);
        if (cached != null) {
            return cached;
        }
        try {
            if (!file.toLowerCase().endsWith(".xml")) {
                return c.a(context, wrapper, id, file, style);
            }
            android.support.v4.content.res.FontResourcesParserCompat.a familyEntry = FontResourcesParserCompat.a(wrapper.getXml(id), wrapper);
            if (familyEntry == null) {
                return null;
            }
            return c.a(context, familyEntry, wrapper, id, style, targetView);
        } catch (XmlPullParserException e) {
            return null;
        } catch (IOException e2) {
            return null;
        }
    }
}
