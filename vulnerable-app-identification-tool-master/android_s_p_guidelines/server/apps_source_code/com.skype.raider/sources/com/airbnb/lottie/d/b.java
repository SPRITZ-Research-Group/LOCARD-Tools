package com.airbnb.lottie.d;

import android.util.JsonReader;
import com.airbnb.lottie.c.a.a;
import com.airbnb.lottie.c.a.k;
import com.airbnb.lottie.e;
import java.io.IOException;

public final class b {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static k a(JsonReader reader, e composition) throws IOException {
        k anim = null;
        reader.beginObject();
        while (reader.hasNext()) {
            Object obj;
            String nextName = reader.nextName();
            switch (nextName.hashCode()) {
                case 97:
                    if (nextName.equals("a")) {
                        obj = null;
                        break;
                    }
                default:
                    obj = -1;
                    break;
            }
            switch (obj) {
                case null:
                    reader.beginObject();
                    com.airbnb.lottie.c.a.b bVar = null;
                    com.airbnb.lottie.c.a.b bVar2 = null;
                    a aVar = null;
                    a aVar2 = null;
                    while (reader.hasNext()) {
                        Object obj2;
                        String nextName2 = reader.nextName();
                        switch (nextName2.hashCode()) {
                            case 116:
                                if (nextName2.equals("t")) {
                                    obj2 = 3;
                                    break;
                                }
                            case 3261:
                                if (nextName2.equals("fc")) {
                                    obj2 = null;
                                    break;
                                }
                            case 3664:
                                if (nextName2.equals("sc")) {
                                    obj2 = 1;
                                    break;
                                }
                            case 3684:
                                if (nextName2.equals("sw")) {
                                    obj2 = 2;
                                    break;
                                }
                            default:
                                obj2 = -1;
                                break;
                        }
                        switch (obj2) {
                            case null:
                                aVar2 = d.g(reader, composition);
                                break;
                            case 1:
                                aVar = d.g(reader, composition);
                                break;
                            case 2:
                                bVar2 = d.a(reader, composition);
                                break;
                            case 3:
                                bVar = d.a(reader, composition);
                                break;
                            default:
                                reader.skipValue();
                                break;
                        }
                    }
                    reader.endObject();
                    anim = new k(aVar2, aVar, bVar2, bVar);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        if (anim == null) {
            return new k(null, null, null, null);
        }
        return anim;
    }
}
