package com.airbnb.lottie.d;

import android.graphics.Color;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class p {

    /* renamed from: com.airbnb.lottie.d.p$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[JsonToken.values().length];

        static {
            try {
                a[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[JsonToken.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @ColorInt
    static int a(JsonReader reader) throws IOException {
        reader.beginArray();
        int r = (int) (reader.nextDouble() * 255.0d);
        int g = (int) (reader.nextDouble() * 255.0d);
        int b = (int) (reader.nextDouble() * 255.0d);
        while (reader.hasNext()) {
            reader.skipValue();
        }
        reader.endArray();
        return Color.argb(255, r, g, b);
    }

    static List<PointF> a(JsonReader reader, float scale) throws IOException {
        List<PointF> points = new ArrayList();
        reader.beginArray();
        while (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            points.add(b(reader, scale));
            reader.endArray();
        }
        reader.endArray();
        return points;
    }

    static PointF b(JsonReader reader, float scale) throws IOException {
        switch (AnonymousClass1.a[reader.peek().ordinal()]) {
            case 1:
                float nextDouble = (float) reader.nextDouble();
                float nextDouble2 = (float) reader.nextDouble();
                while (reader.hasNext()) {
                    reader.skipValue();
                }
                return new PointF(nextDouble * scale, nextDouble2 * scale);
            case 2:
                return c(reader, scale);
            case 3:
                return d(reader, scale);
            default:
                throw new IllegalArgumentException("Unknown point starts with " + reader.peek());
        }
    }

    private static PointF c(JsonReader reader, float scale) throws IOException {
        reader.beginArray();
        float x = (float) reader.nextDouble();
        float y = (float) reader.nextDouble();
        while (reader.peek() != JsonToken.END_ARRAY) {
            reader.skipValue();
        }
        reader.endArray();
        return new PointF(x * scale, y * scale);
    }

    private static PointF d(JsonReader reader, float scale) throws IOException {
        float x = 0.0f;
        float y = 0.0f;
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 120:
                    if (nextName.equals("x")) {
                        obj = null;
                        break;
                    }
                    break;
                case 121:
                    if (nextName.equals("y")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    x = b(reader);
                    break;
                case 1:
                    y = b(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new PointF(x * scale, y * scale);
    }

    static float b(JsonReader reader) throws IOException {
        JsonToken token = reader.peek();
        switch (AnonymousClass1.a[token.ordinal()]) {
            case 1:
                return (float) reader.nextDouble();
            case 2:
                reader.beginArray();
                float val = (float) reader.nextDouble();
                while (reader.hasNext()) {
                    reader.skipValue();
                }
                reader.endArray();
                return val;
            default:
                throw new IllegalArgumentException("Unknown value for token of type " + token);
        }
    }
}
