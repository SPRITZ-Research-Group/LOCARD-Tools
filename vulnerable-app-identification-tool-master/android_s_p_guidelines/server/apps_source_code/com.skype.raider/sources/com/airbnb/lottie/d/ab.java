package com.airbnb.lottie.d;

import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.lottie.f.d;
import java.io.IOException;

public final class ab implements ah<d> {
    public static final ab a = new ab();

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        Object obj = jsonReader.peek() == JsonToken.BEGIN_ARRAY ? 1 : null;
        if (obj != null) {
            jsonReader.beginArray();
        }
        float nextDouble = (float) jsonReader.nextDouble();
        float nextDouble2 = (float) jsonReader.nextDouble();
        while (jsonReader.hasNext()) {
            jsonReader.skipValue();
        }
        if (obj != null) {
            jsonReader.endArray();
        }
        return new d((nextDouble / 100.0f) * f, (nextDouble2 / 100.0f) * f);
    }

    private ab() {
    }
}
