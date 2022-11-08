package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.c.a;
import com.airbnb.lottie.c.b.l;
import com.airbnb.lottie.e.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ac implements ah<l> {
    public static final ac a = new ac();

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        List list = null;
        if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
            jsonReader.beginArray();
        }
        jsonReader.beginObject();
        List list2 = null;
        List list3 = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            List a;
            String nextName = jsonReader.nextName();
            boolean z2 = true;
            switch (nextName.hashCode()) {
                case 99:
                    if (nextName.equals("c")) {
                        z2 = false;
                        break;
                    }
                    break;
                case 105:
                    if (nextName.equals("i")) {
                        z2 = true;
                        break;
                    }
                    break;
                case 111:
                    if (nextName.equals("o")) {
                        z2 = true;
                        break;
                    }
                    break;
                case 118:
                    if (nextName.equals("v")) {
                        z2 = true;
                        break;
                    }
                    break;
            }
            switch (z2) {
                case false:
                    z = jsonReader.nextBoolean();
                    continue;
                case true:
                    list3 = p.a(jsonReader, f);
                    continue;
                case true:
                    list2 = p.a(jsonReader, f);
                    continue;
                case true:
                    a = p.a(jsonReader, f);
                    break;
                default:
                    a = list;
                    break;
            }
            list = a;
        }
        jsonReader.endObject();
        if (jsonReader.peek() == JsonToken.END_ARRAY) {
            jsonReader.endArray();
        }
        if (list3 == null || list2 == null || list == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        } else if (list3.isEmpty()) {
            return new l(new PointF(), false, Collections.emptyList());
        } else {
            PointF pointF;
            int size = list3.size();
            PointF pointF2 = (PointF) list3.get(0);
            List arrayList = new ArrayList(size);
            for (int i = 1; i < size; i++) {
                pointF = (PointF) list3.get(i);
                arrayList.add(new a(e.a((PointF) list3.get(i - 1), (PointF) list.get(i - 1)), e.a(pointF, (PointF) list2.get(i)), pointF));
            }
            if (z) {
                pointF = (PointF) list3.get(0);
                arrayList.add(new a(e.a((PointF) list3.get(size - 1), (PointF) list.get(size - 1)), e.a(pointF, (PointF) list2.get(0)), pointF));
            }
            return new l(pointF2, z, arrayList);
        }
    }

    private ac() {
    }
}
