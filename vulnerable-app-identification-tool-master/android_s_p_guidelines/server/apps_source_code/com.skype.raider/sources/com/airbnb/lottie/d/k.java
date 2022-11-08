package com.airbnb.lottie.d;

import android.util.JsonReader;
import java.io.IOException;

public final class k implements ah<Float> {
    public static final k a = new k();

    private k() {
    }

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        return Float.valueOf(p.b(jsonReader) * f);
    }
}
