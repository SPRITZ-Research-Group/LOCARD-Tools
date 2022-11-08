package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.f;
import com.airbnb.lottie.c.a.m;
import com.airbnb.lottie.c.b.a;
import com.airbnb.lottie.e;
import java.io.IOException;

final class g {
    static a a(JsonReader reader, e composition) throws IOException {
        String name = null;
        m<PointF, PointF> position = null;
        f size = null;
        boolean reversed = false;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            int obj2;
            switch (nextName.hashCode()) {
                case 100:
                    if (nextName.equals("d")) {
                        obj2 = 3;
                        break;
                    }
                    break;
                case 112:
                    if (nextName.equals("p")) {
                        obj2 = 1;
                        break;
                    }
                    break;
                case 115:
                    if (nextName.equals("s")) {
                        obj2 = 2;
                        break;
                    }
                    break;
                case 3519:
                    if (nextName.equals("nm")) {
                        obj2 = null;
                        break;
                    }
                    break;
            }
            switch (obj2) {
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
                    if (reader.nextInt() == 3) {
                        reversed = true;
                    } else {
                        reversed = false;
                    }
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new a(name, position, size, reversed);
    }
}
