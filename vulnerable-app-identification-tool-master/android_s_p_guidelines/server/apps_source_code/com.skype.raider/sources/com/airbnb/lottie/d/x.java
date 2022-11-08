package com.airbnb.lottie.d;

import android.graphics.PointF;
import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;

public final class x implements ah<PointF> {
    public static final x a = new x();

    private x() {
    }

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        JsonToken peek = jsonReader.peek();
        if (peek == JsonToken.BEGIN_ARRAY) {
            return p.b(jsonReader, f);
        }
        if (peek == JsonToken.BEGIN_OBJECT) {
            return p.b(jsonReader, f);
        }
        if (peek == JsonToken.NUMBER) {
            Object pointF = new PointF(((float) jsonReader.nextDouble()) * f, ((float) jsonReader.nextDouble()) * f);
            while (jsonReader.hasNext()) {
                jsonReader.skipValue();
            }
            return pointF;
        }
        throw new IllegalArgumentException("Cannot convert json to point. Next token is " + peek);
    }
}
