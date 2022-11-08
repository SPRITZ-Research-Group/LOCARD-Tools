package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.h;
import com.airbnb.lottie.c.b.o;
import com.airbnb.lottie.e;
import java.io.IOException;

final class ae {
    static o a(JsonReader reader, e composition) throws IOException {
        String name = null;
        int ind = 0;
        h shape = null;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 3432:
                    if (nextName.equals("ks")) {
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
                case 104415:
                    if (nextName.equals("ind")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    name = reader.nextString();
                    break;
                case 1:
                    ind = reader.nextInt();
                    break;
                case 2:
                    shape = d.e(reader, composition);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new o(name, ind, shape);
    }
}
