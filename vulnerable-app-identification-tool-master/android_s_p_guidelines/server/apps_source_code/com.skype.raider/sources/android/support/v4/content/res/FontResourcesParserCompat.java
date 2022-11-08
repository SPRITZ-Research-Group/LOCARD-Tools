package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.a.a.h;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class FontResourcesParserCompat {

    @Retention(RetentionPolicy.SOURCE)
    public @interface FetchStrategy {
    }

    public interface a {
    }

    public static final class b implements a {
        @NonNull
        private final c[] a;

        public b(@NonNull c[] entries) {
            this.a = entries;
        }

        @NonNull
        public final c[] a() {
            return this.a;
        }
    }

    public static final class c {
        @NonNull
        private final String a;
        private int b;
        private boolean c;
        private int d;

        public c(@NonNull String fileName, int weight, boolean italic, int resourceId) {
            this.a = fileName;
            this.b = weight;
            this.c = italic;
            this.d = resourceId;
        }

        @NonNull
        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final boolean c() {
            return this.c;
        }

        public final int d() {
            return this.d;
        }
    }

    public static final class d implements a {
        @NonNull
        private final android.support.v4.provider.a a;
        private final int b;
        private final int c;

        public d(@NonNull android.support.v4.provider.a request, int strategy, int timeoutMs) {
            this.a = request;
            this.c = strategy;
            this.b = timeoutMs;
        }

        @NonNull
        public final android.support.v4.provider.a a() {
            return this.a;
        }

        public final int b() {
            return this.c;
        }

        public final int c() {
            return this.b;
        }
    }

    @Nullable
    public static a a(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        parser.require(2, null, "font-family");
        if (parser.getName().equals("font-family")) {
            return b(parser, resources);
        }
        a(parser);
        return null;
    }

    @Nullable
    private static a b(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray array = resources.obtainAttributes(Xml.asAttributeSet(parser), h.FontFamily);
        String authority = array.getString(h.FontFamily_fontProviderAuthority);
        String providerPackage = array.getString(h.FontFamily_fontProviderPackage);
        String query = array.getString(h.FontFamily_fontProviderQuery);
        int certsId = array.getResourceId(h.FontFamily_fontProviderCerts, 0);
        int strategy = array.getInteger(h.FontFamily_fontProviderFetchStrategy, 1);
        int timeoutMs = array.getInteger(h.FontFamily_fontProviderFetchTimeout, 500);
        array.recycle();
        if (authority == null || providerPackage == null || query == null) {
            List<c> fonts = new ArrayList();
            while (parser.next() != 3) {
                if (parser.getEventType() == 2) {
                    if (parser.getName().equals("font")) {
                        fonts.add(c(parser, resources));
                    } else {
                        a(parser);
                    }
                }
            }
            if (fonts.isEmpty()) {
                return null;
            }
            return new b((c[]) fonts.toArray(new c[fonts.size()]));
        }
        while (parser.next() != 3) {
            a(parser);
        }
        return new d(new android.support.v4.provider.a(authority, providerPackage, query, a(resources, certsId)), strategy, timeoutMs);
    }

    public static List<List<byte[]>> a(Resources resources, @ArrayRes int certsId) {
        List<List<byte[]>> certs = null;
        if (certsId != 0) {
            TypedArray typedArray = resources.obtainTypedArray(certsId);
            if (typedArray.length() > 0) {
                int i;
                certs = new ArrayList();
                if (typedArray.getResourceId(0, 0) != 0) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i != 0) {
                    for (int i2 = 0; i2 < typedArray.length(); i2++) {
                        certs.add(a(resources.getStringArray(typedArray.getResourceId(i2, 0))));
                    }
                } else {
                    certs.add(a(resources.getStringArray(certsId)));
                }
            }
            typedArray.recycle();
        }
        return certs != null ? certs : Collections.emptyList();
    }

    private static List<byte[]> a(String[] stringArray) {
        List<byte[]> result = new ArrayList();
        for (String item : stringArray) {
            result.add(Base64.decode(item, 0));
        }
        return result;
    }

    private static c c(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
        boolean isItalic = true;
        TypedArray array = resources.obtainAttributes(Xml.asAttributeSet(parser), h.FontFamilyFont);
        int weight = array.getInt(h.FontFamilyFont_fontWeight, 400);
        if (1 != array.getInt(h.FontFamilyFont_fontStyle, 0)) {
            isItalic = false;
        }
        int resourceId = array.getResourceId(h.FontFamilyFont_font, 0);
        String filename = array.getString(h.FontFamilyFont_font);
        array.recycle();
        while (parser.next() != 3) {
            a(parser);
        }
        return new c(filename, weight, isItalic, resourceId);
    }

    private static void a(XmlPullParser parser) throws XmlPullParserException, IOException {
        int depth = 1;
        while (depth > 0) {
            switch (parser.next()) {
                case 2:
                    depth++;
                    break;
                case 3:
                    depth--;
                    break;
                default:
                    break;
            }
        }
    }
}
