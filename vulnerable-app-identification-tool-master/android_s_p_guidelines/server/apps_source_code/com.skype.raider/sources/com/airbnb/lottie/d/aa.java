package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.b.k;
import com.airbnb.lottie.e;
import java.io.IOException;

final class aa {
    static k a(JsonReader reader, e composition) throws IOException {
        String name = null;
        b copies = null;
        b offset = null;
        l transform = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            boolean z = true;
            switch (nextName.hashCode()) {
                case 99:
                    if (nextName.equals("c")) {
                        z = true;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
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
                case 3710:
                    if (nextName.equals("tr")) {
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
                    copies = d.a(reader, composition, false);
                    break;
                case true:
                    offset = d.a(reader, composition, false);
                    break;
                case true:
                    transform = c.a(reader, composition);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new k(name, copies, offset, transform);
    }
}
