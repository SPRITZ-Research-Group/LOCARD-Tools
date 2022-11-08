package android.support.v7.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class a {
    @NonNull
    public static ColorStateList a(@NonNull Resources r, @NonNull XmlPullParser parser, @Nullable Theme theme) throws XmlPullParserException, IOException {
        int type;
        AttributeSet attrs = Xml.asAttributeSet(parser);
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = parser.getName();
        if (name.equals("selector")) {
            return a(r, parser, attrs, theme);
        }
        throw new XmlPullParserException(parser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    private static ColorStateList a(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) throws XmlPullParserException, IOException {
        int innerDepth = parser.getDepth() + 1;
        int[][] stateSpecList = new int[20][];
        int[] colorList = new int[20];
        int listSize = 0;
        while (true) {
            int type = parser.next();
            if (type == 1) {
                break;
            }
            int depth = parser.getDepth();
            if (depth < innerDepth && type == 3) {
                break;
            } else if (type == 2 && depth <= innerDepth && parser.getName().equals("item")) {
                TypedArray a;
                int j;
                int[] iArr = j.ColorStateListItem;
                if (theme == null) {
                    a = r.obtainAttributes(attrs, iArr);
                } else {
                    a = theme.obtainStyledAttributes(attrs, iArr, 0, 0);
                }
                int baseColor = a.getColor(j.ColorStateListItem_android_color, -65281);
                float alphaMod = 1.0f;
                if (a.hasValue(j.ColorStateListItem_android_alpha)) {
                    alphaMod = a.getFloat(j.ColorStateListItem_android_alpha, 1.0f);
                } else if (a.hasValue(j.ColorStateListItem_alpha)) {
                    alphaMod = a.getFloat(j.ColorStateListItem_alpha, 1.0f);
                }
                a.recycle();
                int j2 = 0;
                int numAttrs = attrs.getAttributeCount();
                int[] stateSpec = new int[numAttrs];
                int i = 0;
                while (true) {
                    j = j2;
                    if (i >= numAttrs) {
                        break;
                    }
                    int stateResId = attrs.getAttributeNameResource(i);
                    if (stateResId == 16843173 || stateResId == 16843551 || stateResId == android.support.v7.appcompat.a.a.alpha) {
                        j2 = j;
                    } else {
                        j2 = j + 1;
                        if (!attrs.getAttributeBooleanValue(i, false)) {
                            stateResId = -stateResId;
                        }
                        stateSpec[j] = stateResId;
                    }
                    i++;
                }
                Object stateSpec2 = StateSet.trimStateSet(stateSpec, j);
                colorList = c.a(colorList, listSize, android.support.v4.graphics.a.b(baseColor, Math.round(((float) Color.alpha(baseColor)) * alphaMod)));
                stateSpecList = (int[][]) c.a((Object[]) stateSpecList, listSize, stateSpec2);
                listSize++;
            }
        }
        int[] colors = new int[listSize];
        Object stateSpecs = new int[listSize][];
        System.arraycopy(colorList, 0, colors, 0, listSize);
        System.arraycopy(stateSpecList, 0, stateSpecs, 0, listSize);
        return new ColorStateList(stateSpecs, colors);
    }
}
