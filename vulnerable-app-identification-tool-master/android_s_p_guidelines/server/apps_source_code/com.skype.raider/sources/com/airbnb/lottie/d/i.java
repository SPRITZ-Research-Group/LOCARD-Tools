package com.airbnb.lottie.d;

import android.support.annotation.Nullable;
import android.util.JsonReader;
import com.airbnb.lottie.c.b.b;
import com.airbnb.lottie.c.b.n;
import com.airbnb.lottie.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class i {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Nullable
    static b a(JsonReader reader, e composition) throws IOException {
        String str = null;
        String type = null;
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("ty")) {
                type = reader.nextString();
                break;
            }
            reader.skipValue();
        }
        if (type == null) {
            return null;
        }
        Object obj;
        int obj2;
        b model = null;
        switch (type.hashCode()) {
            case 3239:
                if (type.equals("el")) {
                    obj2 = 7;
                    break;
                }
            case 3270:
                if (type.equals("fl")) {
                    obj2 = 3;
                    break;
                }
            case 3295:
                if (type.equals("gf")) {
                    obj2 = 4;
                    break;
                }
            case 3307:
                if (type.equals("gr")) {
                    obj2 = null;
                    break;
                }
            case 3308:
                if (type.equals("gs")) {
                    obj2 = 2;
                    break;
                }
            case 3488:
                if (type.equals("mm")) {
                    obj2 = 11;
                    break;
                }
            case 3633:
                if (type.equals("rc")) {
                    obj2 = 8;
                    break;
                }
            case 3646:
                if (type.equals("rp")) {
                    obj2 = 12;
                    break;
                }
            case 3669:
                if (type.equals("sh")) {
                    obj2 = 6;
                    break;
                }
            case 3679:
                if (type.equals("sr")) {
                    obj2 = 10;
                    break;
                }
            case 3681:
                if (type.equals("st")) {
                    obj2 = 1;
                    break;
                }
            case 3705:
                if (type.equals("tm")) {
                    obj2 = 9;
                    break;
                }
            case 3710:
                if (type.equals("tr")) {
                    obj2 = 5;
                    break;
                }
            default:
                obj2 = -1;
                break;
        }
        switch (obj2) {
            case null:
                List arrayList = new ArrayList();
                while (reader.hasNext()) {
                    String nextName = reader.nextName();
                    switch (nextName.hashCode()) {
                        case 3371:
                            if (nextName.equals("it")) {
                                obj2 = 1;
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
                            str = reader.nextString();
                            break;
                        case 1:
                            reader.beginArray();
                            while (reader.hasNext()) {
                                b a = a(reader, composition);
                                if (a != null) {
                                    arrayList.add(a);
                                }
                            }
                            reader.endArray();
                            break;
                        default:
                            reader.skipValue();
                            break;
                    }
                }
                model = new n(str, arrayList);
                break;
            case 1:
                model = af.a(reader, composition);
                break;
            case 2:
                model = n.a(reader, composition);
                break;
            case 3:
                model = ad.a(reader, composition);
                break;
            case 4:
                model = m.a(reader, composition);
                break;
            case 5:
                model = c.a(reader, composition);
                break;
            case 6:
                model = ae.a(reader, composition);
                break;
            case 7:
                model = g.a(reader, composition);
                break;
            case 8:
                model = z.a(reader, composition);
                break;
            case 9:
                model = ag.a(reader, composition);
                break;
            case 10:
                model = y.a(reader, composition);
                break;
            case 11:
                model = v.a(reader);
                composition.a("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case 12:
                model = aa.a(reader, composition);
                break;
        }
        while (reader.hasNext()) {
            reader.skipValue();
        }
        reader.endObject();
        return model;
    }
}
