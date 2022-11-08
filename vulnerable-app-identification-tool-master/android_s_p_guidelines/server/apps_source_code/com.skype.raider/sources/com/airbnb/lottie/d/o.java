package com.airbnb.lottie.d;

import android.util.JsonReader;
import java.io.IOException;

public final class o implements ah<Integer> {
    public static final o a = new o();

    private o() {
    }

    public final /* synthetic */ Object a(JsonReader jsonReader, float f) throws IOException {
        return Integer.valueOf(Math.round(p.b(jsonReader) * f));
    }
}
