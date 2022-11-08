package com.google.android.exoplayer2.d;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class t {
    public static boolean a(XmlPullParser xpp, String name) throws XmlPullParserException {
        boolean z;
        if (xpp.getEventType() == 2) {
            z = true;
        } else {
            z = false;
        }
        return z && xpp.getName().equals(name);
    }

    public static String b(XmlPullParser xpp, String attributeName) {
        int attributeCount = xpp.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (attributeName.equals(xpp.getAttributeName(i))) {
                return xpp.getAttributeValue(i);
            }
        }
        return null;
    }
}
