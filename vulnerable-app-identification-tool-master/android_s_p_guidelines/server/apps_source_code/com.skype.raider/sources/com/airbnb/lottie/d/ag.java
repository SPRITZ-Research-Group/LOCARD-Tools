package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.b.q;
import com.airbnb.lottie.c.b.q.a;
import com.airbnb.lottie.e;
import java.io.IOException;

final class ag {
    static q a(JsonReader reader, e composition) throws IOException {
        String name = null;
        a type = null;
        b start = null;
        b end = null;
        b offset = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            boolean z = true;
            switch (nextName.hashCode()) {
                case 101:
                    if (nextName.equals("e")) {
                        z = true;
                        break;
                    }
                    break;
                case 109:
                    if (nextName.equals("m")) {
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
                case 115:
                    if (nextName.equals("s")) {
                        z = false;
                        break;
                    }
                    break;
                case 3519:
                    if (nextName.equals("nm")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    start = d.a(reader, composition, false);
                    break;
                case true:
                    end = d.a(reader, composition, false);
                    break;
                case true:
                    offset = d.a(reader, composition, false);
                    break;
                case true:
                    name = reader.nextString();
                    break;
                case true:
                    type = a.a(reader.nextInt());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new q(name, type, start, end, offset);
    }
}
