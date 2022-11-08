package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.f;
import com.airbnb.lottie.c.a.m;
import com.airbnb.lottie.c.b.j;
import com.airbnb.lottie.e;
import java.io.IOException;

final class z {
    static j a(JsonReader reader, e composition) throws IOException {
        String name = null;
        m<PointF, PointF> position = null;
        f size = null;
        b roundedness = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 112:
                    if (nextName.equals("p")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 114:
                    if (nextName.equals("r")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 115:
                    if (nextName.equals("s")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 3519:
                    if (nextName.equals("nm")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    name = reader.nextString();
                    break;
                case 1:
                    position = a.b(reader, composition);
                    break;
                case 2:
                    size = d.c(reader, composition);
                    break;
                case 3:
                    roundedness = d.a(reader, composition);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new j(name, position, size, roundedness);
    }
}
