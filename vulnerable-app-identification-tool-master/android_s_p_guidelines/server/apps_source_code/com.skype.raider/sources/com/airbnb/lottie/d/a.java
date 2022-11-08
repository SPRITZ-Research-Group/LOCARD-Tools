package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.a.b.h;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.e;
import com.airbnb.lottie.c.a.i;
import com.airbnb.lottie.c.a.m;
import com.airbnb.lottie.e.f;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class a {
    public static e a(JsonReader reader, com.airbnb.lottie.e composition) throws IOException {
        List<com.airbnb.lottie.f.a<PointF>> keyframes = new ArrayList();
        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            while (reader.hasNext()) {
                keyframes.add(new h(composition, q.a(reader, composition, f.a(), w.a, reader.peek() == JsonToken.BEGIN_OBJECT)));
            }
            reader.endArray();
            r.a(keyframes);
        } else {
            keyframes.add(new com.airbnb.lottie.f.a(p.b(reader, f.a())));
        }
        return new e(keyframes);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static m<PointF, PointF> b(JsonReader reader, com.airbnb.lottie.e composition) throws IOException {
        e pathAnimation = null;
        b xAnimation = null;
        b yAnimation = null;
        boolean hasExpressions = false;
        reader.beginObject();
        while (reader.peek() != JsonToken.END_OBJECT) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 107:
                    if (nextName.equals("k")) {
                        obj = null;
                        break;
                    }
                    break;
                case 120:
                    if (nextName.equals("x")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 121:
                    if (nextName.equals("y")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    pathAnimation = a(reader, composition);
                    break;
                case 1:
                    if (reader.peek() != JsonToken.STRING) {
                        xAnimation = d.a(reader, composition);
                        break;
                    }
                    hasExpressions = true;
                case 2:
                    if (reader.peek() != JsonToken.STRING) {
                        yAnimation = d.a(reader, composition);
                        break;
                    }
                    hasExpressions = true;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        if (hasExpressions) {
            composition.a("Lottie doesn't support expressions.");
        }
        return pathAnimation != null ? pathAnimation : new i(xAnimation, yAnimation);
    }
}
