package com.airbnb.lottie.d;

import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.e;
import com.airbnb.lottie.f.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class r {
    static <T> List<a<T>> a(JsonReader reader, e composition, float scale, ah<T> valueParser) throws IOException {
        List<a<T>> keyframes = new ArrayList();
        if (reader.peek() == JsonToken.STRING) {
            composition.a("Lottie doesn't support expressions.");
        } else {
            reader.beginObject();
            while (reader.hasNext()) {
                String nextName = reader.nextName();
                boolean z = true;
                switch (nextName.hashCode()) {
                    case 107:
                        if (nextName.equals("k")) {
                            z = false;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        if (reader.peek() != JsonToken.BEGIN_ARRAY) {
                            keyframes.add(q.a(reader, composition, scale, valueParser, false));
                            break;
                        }
                        reader.beginArray();
                        if (reader.peek() == JsonToken.NUMBER) {
                            keyframes.add(q.a(reader, composition, scale, valueParser, false));
                        } else {
                            while (reader.hasNext()) {
                                keyframes.add(q.a(reader, composition, scale, valueParser, true));
                            }
                        }
                        reader.endArray();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
            a(keyframes);
        }
        return keyframes;
    }

    public static void a(List<? extends a<?>> keyframes) {
        int size = keyframes.size();
        for (int i = 0; i < size - 1; i++) {
            ((a) keyframes.get(i)).e = Float.valueOf(((a) keyframes.get(i + 1)).d);
        }
        a<?> lastKeyframe = (a) keyframes.get(size - 1);
        if (lastKeyframe.a == null) {
            keyframes.remove(lastKeyframe);
        }
    }
}
