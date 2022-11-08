package com.airbnb.lottie.d;

import android.graphics.Path.FillType;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.c;
import com.airbnb.lottie.c.b.d;
import com.airbnb.lottie.c.b.f;
import com.airbnb.lottie.e;
import java.io.IOException;

final class m {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static d a(JsonReader reader, e composition) throws IOException {
        String name = null;
        c color = null;
        com.airbnb.lottie.c.a.d opacity = null;
        f gradientType = null;
        com.airbnb.lottie.c.a.f startPoint = null;
        com.airbnb.lottie.c.a.f endPoint = null;
        FillType fillType = null;
        while (reader.hasNext()) {
            Object obj;
            int obj2;
            String nextName = reader.nextName();
            switch (nextName.hashCode()) {
                case 101:
                    if (nextName.equals("e")) {
                        obj2 = 5;
                        break;
                    }
                case 103:
                    if (nextName.equals("g")) {
                        obj2 = 1;
                        break;
                    }
                case 111:
                    if (nextName.equals("o")) {
                        obj2 = 2;
                        break;
                    }
                case 114:
                    if (nextName.equals("r")) {
                        obj2 = 6;
                        break;
                    }
                case 115:
                    if (nextName.equals("s")) {
                        obj2 = 4;
                        break;
                    }
                case 116:
                    if (nextName.equals("t")) {
                        obj2 = 3;
                        break;
                    }
                case 3519:
                    if (nextName.equals("nm")) {
                        obj2 = null;
                        break;
                    }
                default:
                    obj2 = -1;
                    break;
            }
            switch (obj2) {
                case null:
                    name = reader.nextString();
                    break;
                case 1:
                    int points = -1;
                    reader.beginObject();
                    while (reader.hasNext()) {
                        nextName = reader.nextName();
                        switch (nextName.hashCode()) {
                            case 107:
                                if (nextName.equals("k")) {
                                    obj2 = 1;
                                    break;
                                }
                            case 112:
                                if (nextName.equals("p")) {
                                    obj2 = null;
                                    break;
                                }
                            default:
                                obj2 = -1;
                                break;
                        }
                        switch (obj2) {
                            case null:
                                points = reader.nextInt();
                                break;
                            case 1:
                                color = d.a(reader, composition, points);
                                break;
                            default:
                                reader.skipValue();
                                break;
                        }
                    }
                    reader.endObject();
                    break;
                case 2:
                    opacity = d.b(reader, composition);
                    break;
                case 3:
                    gradientType = reader.nextInt() == 1 ? f.Linear : f.Radial;
                    break;
                case 4:
                    startPoint = d.c(reader, composition);
                    break;
                case 5:
                    endPoint = d.c(reader, composition);
                    break;
                case 6:
                    fillType = reader.nextInt() == 1 ? FillType.WINDING : FillType.EVEN_ODD;
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new d(name, gradientType, fillType, color, opacity, startPoint, endPoint);
    }
}
