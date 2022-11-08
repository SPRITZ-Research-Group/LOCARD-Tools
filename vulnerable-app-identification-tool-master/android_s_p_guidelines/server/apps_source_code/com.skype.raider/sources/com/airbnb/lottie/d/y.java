package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.m;
import com.airbnb.lottie.c.b.i;
import com.airbnb.lottie.c.b.i.a;
import com.airbnb.lottie.e;
import java.io.IOException;

final class y {
    static i a(JsonReader reader, e composition) throws IOException {
        String name = null;
        a type = null;
        b points = null;
        m<PointF, PointF> position = null;
        b rotation = null;
        b outerRadius = null;
        b outerRoundedness = null;
        b innerRadius = null;
        b innerRoundedness = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            boolean z = true;
            switch (nextName.hashCode()) {
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
                case 3369:
                    if (nextName.equals("ir")) {
                        z = true;
                        break;
                    }
                    break;
                case 3370:
                    if (nextName.equals("is")) {
                        z = true;
                        break;
                    }
                    break;
                case 3519:
                    if (nextName.equals("nm")) {
                        z = false;
                        break;
                    }
                    break;
                case 3555:
                    if (nextName.equals("or")) {
                        z = true;
                        break;
                    }
                    break;
                case 3556:
                    if (nextName.equals("os")) {
                        z = true;
                        break;
                    }
                    break;
                case 3588:
                    if (nextName.equals("pt")) {
                        z = true;
                        break;
                    }
                    break;
                case 3686:
                    if (nextName.equals("sy")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    name = reader.nextString();
                    break;
                case true:
                    type = a.a(reader.nextInt());
                    break;
                case true:
                    points = d.a(reader, composition, false);
                    break;
                case true:
                    position = a.b(reader, composition);
                    break;
                case true:
                    rotation = d.a(reader, composition, false);
                    break;
                case true:
                    outerRadius = d.a(reader, composition);
                    break;
                case true:
                    outerRoundedness = d.a(reader, composition, false);
                    break;
                case true:
                    innerRadius = d.a(reader, composition);
                    break;
                case true:
                    innerRoundedness = d.a(reader, composition, false);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new i(name, type, points, position, rotation, innerRadius, outerRadius, innerRoundedness, outerRoundedness);
    }
}
