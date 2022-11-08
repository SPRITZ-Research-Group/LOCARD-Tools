package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.c;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.b.e;
import com.airbnb.lottie.c.b.f;
import com.airbnb.lottie.c.b.p;
import com.airbnb.lottie.c.b.p.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class n {
    static e a(JsonReader reader, com.airbnb.lottie.e composition) throws IOException {
        String name = null;
        c color = null;
        d opacity = null;
        f gradientType = null;
        com.airbnb.lottie.c.a.f startPoint = null;
        com.airbnb.lottie.c.a.f endPoint = null;
        b width = null;
        a capType = null;
        p.b joinType = null;
        b offset = null;
        List<b> lineDashPattern = new ArrayList();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 100:
                    if (nextName.equals("d")) {
                        obj = 9;
                        break;
                    }
                    break;
                case 101:
                    if (nextName.equals("e")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 103:
                    if (nextName.equals("g")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 115:
                    if (nextName.equals("s")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 116:
                    if (nextName.equals("t")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 119:
                    if (nextName.equals("w")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 3447:
                    if (nextName.equals("lc")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 3454:
                    if (nextName.equals("lj")) {
                        obj = 8;
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
                    int points = -1;
                    reader.beginObject();
                    while (reader.hasNext()) {
                        nextName = reader.nextName();
                        obj = -1;
                        switch (nextName.hashCode()) {
                            case 107:
                                if (nextName.equals("k")) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 112:
                                if (nextName.equals("p")) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
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
                    width = d.a(reader, composition);
                    break;
                case 7:
                    capType = a.values()[reader.nextInt() - 1];
                    break;
                case 8:
                    joinType = p.b.values()[reader.nextInt() - 1];
                    break;
                case 9:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        String n = null;
                        b val = null;
                        reader.beginObject();
                        while (reader.hasNext()) {
                            nextName = reader.nextName();
                            obj = -1;
                            switch (nextName.hashCode()) {
                                case 110:
                                    if (nextName.equals("n")) {
                                        obj = null;
                                        break;
                                    }
                                    break;
                                case 118:
                                    if (nextName.equals("v")) {
                                        obj = 1;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj) {
                                case null:
                                    n = reader.nextString();
                                    break;
                                case 1:
                                    val = d.a(reader, composition);
                                    break;
                                default:
                                    reader.skipValue();
                                    break;
                            }
                        }
                        reader.endObject();
                        if (n.equals("o")) {
                            offset = val;
                        } else if (n.equals("d") || n.equals("g")) {
                            lineDashPattern.add(val);
                        }
                    }
                    reader.endArray();
                    if (lineDashPattern.size() != 1) {
                        break;
                    }
                    lineDashPattern.add(lineDashPattern.get(0));
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new e(name, gradientType, color, opacity, startPoint, endPoint, width, capType, joinType, lineDashPattern, offset);
    }
}
