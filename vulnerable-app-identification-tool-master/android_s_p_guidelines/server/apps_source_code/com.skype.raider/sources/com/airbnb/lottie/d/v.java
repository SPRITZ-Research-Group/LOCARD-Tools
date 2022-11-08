package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.b.h;
import com.airbnb.lottie.c.b.h.a;
import java.io.IOException;

final class v {
    static h a(JsonReader reader) throws IOException {
        String name = null;
        a mode = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 3488:
                    if (nextName.equals("mm")) {
                        obj = 1;
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
                    mode = a.a(reader.nextInt());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new h(name, mode);
    }
}
