package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.a;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.b.p;
import com.airbnb.lottie.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class af {
    static p a(JsonReader reader, e composition) throws IOException {
        String name = null;
        a color = null;
        b width = null;
        d opacity = null;
        p.a capType = null;
        p.b joinType = null;
        b offset = null;
        List<b> lineDashPattern = new ArrayList();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            Object obj = -1;
            switch (nextName.hashCode()) {
                case 99:
                    if (nextName.equals("c")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 100:
                    if (nextName.equals("d")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 119:
                    if (nextName.equals("w")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 3447:
                    if (nextName.equals("lc")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 3454:
                    if (nextName.equals("lj")) {
                        obj = 5;
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
                    color = d.g(reader, composition);
                    break;
                case 2:
                    width = d.a(reader, composition);
                    break;
                case 3:
                    opacity = d.b(reader, composition);
                    break;
                case 4:
                    capType = p.a.values()[reader.nextInt() - 1];
                    break;
                case 5:
                    joinType = p.b.values()[reader.nextInt() - 1];
                    break;
                case 6:
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
                        obj = -1;
                        switch (n.hashCode()) {
                            case 100:
                                if (n.equals("d")) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 103:
                                if (n.equals("g")) {
                                    obj = 2;
                                    break;
                                }
                                break;
                            case 111:
                                if (n.equals("o")) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                                offset = val;
                                break;
                            case 1:
                            case 2:
                                lineDashPattern.add(val);
                                break;
                            default:
                                break;
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
        return new p(name, offset, lineDashPattern, color, opacity, width, capType, joinType);
    }
}
