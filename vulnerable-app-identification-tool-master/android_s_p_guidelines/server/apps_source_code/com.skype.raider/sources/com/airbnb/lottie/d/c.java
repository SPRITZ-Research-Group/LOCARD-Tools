package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.a.g;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.a.m;
import com.airbnb.lottie.e;
import java.io.IOException;

public final class c {
    public static l a(JsonReader reader, e composition) throws IOException {
        boolean isObject;
        com.airbnb.lottie.c.a.e anchorPoint = null;
        m<PointF, PointF> position = null;
        g scale = null;
        b rotation = null;
        d opacity = null;
        b startOpacity = null;
        b endOpacity = null;
        if (reader.peek() == JsonToken.BEGIN_OBJECT) {
            isObject = true;
        } else {
            isObject = false;
        }
        if (isObject) {
            reader.beginObject();
        }
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            boolean z = true;
            switch (nextName.hashCode()) {
                case 97:
                    if (nextName.equals("a")) {
                        z = false;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
                        z = true;
                        break;
                    }
                    break;
                case 112:
                    if (nextName.equals("p")) {
                        z = true;
                        break;
                    }
                    break;
                case 114:
                    if (nextName.equals("r")) {
                        z = true;
                        break;
                    }
                    break;
                case 115:
                    if (nextName.equals("s")) {
                        z = true;
                        break;
                    }
                    break;
                case 3242:
                    if (nextName.equals("eo")) {
                        z = true;
                        break;
                    }
                    break;
                case 3656:
                    if (nextName.equals("rz")) {
                        z = true;
                        break;
                    }
                    break;
                case 3676:
                    if (nextName.equals("so")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    reader.beginObject();
                    while (reader.hasNext()) {
                        if (reader.nextName().equals("k")) {
                            anchorPoint = a.a(reader, composition);
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    continue;
                case true:
                    position = a.b(reader, composition);
                    continue;
                case true:
                    scale = d.d(reader, composition);
                    continue;
                case true:
                    composition.a("Lottie doesn't support 3D layers.");
                    break;
                case true:
                    break;
                case true:
                    opacity = d.b(reader, composition);
                    continue;
                case true:
                    startOpacity = d.a(reader, composition, false);
                    continue;
                case true:
                    endOpacity = d.a(reader, composition, false);
                    continue;
                default:
                    reader.skipValue();
                    continue;
            }
            rotation = d.a(reader, composition, false);
        }
        if (isObject) {
            reader.endObject();
        }
        if (anchorPoint == null) {
            anchorPoint = new com.airbnb.lottie.c.a.e();
        }
        if (scale == null) {
            scale = new g(new com.airbnb.lottie.f.d(1.0f, 1.0f));
        }
        if (opacity == null) {
            opacity = new d();
        }
        return new l(anchorPoint, position, scale, rotation, opacity, startOpacity, endOpacity);
    }
}
