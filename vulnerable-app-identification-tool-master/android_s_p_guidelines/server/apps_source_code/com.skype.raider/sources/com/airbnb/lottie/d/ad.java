package com.airbnb.lottie.d;

import android.graphics.Path.FillType;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.a;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.b.m;
import com.airbnb.lottie.e;
import java.io.IOException;

final class ad {
    static m a(JsonReader reader, e composition) throws IOException {
        a color = null;
        boolean fillEnabled = false;
        d opacity = null;
        String name = null;
        int fillTypeInt = 1;
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case -396065730:
                    if (nextName.equals("fillEnabled")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 99:
                    if (nextName.equals("c")) {
                        int obj2 = 1;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
                        obj2 = 2;
                        break;
                    }
                    break;
                case 114:
                    if (nextName.equals("r")) {
                        obj2 = 4;
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
                    color = d.g(reader, composition);
                    break;
                case 2:
                    opacity = d.b(reader, composition);
                    break;
                case 3:
                    fillEnabled = reader.nextBoolean();
                    break;
                case 4:
                    fillTypeInt = reader.nextInt();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new m(name, fillEnabled, fillTypeInt == 1 ? FillType.WINDING : FillType.EVEN_ODD, color, opacity);
    }
}
