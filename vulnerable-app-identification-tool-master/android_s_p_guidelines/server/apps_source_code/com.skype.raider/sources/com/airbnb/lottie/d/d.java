package com.airbnb.lottie.d;

import android.support.annotation.Nullable;
import android.util.JsonReader;
import com.airbnb.lottie.c.a.a;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.c;
import com.airbnb.lottie.c.a.g;
import com.airbnb.lottie.c.a.h;
import com.airbnb.lottie.c.a.j;
import com.airbnb.lottie.e;
import com.airbnb.lottie.e.f;
import java.io.IOException;
import java.util.List;

public final class d {
    public static b a(JsonReader reader, e composition) throws IOException {
        return a(reader, composition, true);
    }

    public static b a(JsonReader reader, e composition, boolean isDp) throws IOException {
        return new b(a(reader, isDp ? f.a() : 1.0f, composition, k.a));
    }

    static com.airbnb.lottie.c.a.d b(JsonReader reader, e composition) throws IOException {
        return new com.airbnb.lottie.c.a.d(a(reader, composition, o.a));
    }

    static com.airbnb.lottie.c.a.f c(JsonReader reader, e composition) throws IOException {
        return new com.airbnb.lottie.c.a.f(a(reader, f.a(), composition, x.a));
    }

    static g d(JsonReader reader, e composition) throws IOException {
        return new g(a(reader, composition, ab.a));
    }

    static h e(JsonReader reader, e composition) throws IOException {
        return new h(a(reader, f.a(), composition, ac.a));
    }

    static j f(JsonReader reader, e composition) throws IOException {
        return new j(a(reader, composition, j.a));
    }

    static a g(JsonReader reader, e composition) throws IOException {
        return new a(a(reader, composition, h.a));
    }

    static c a(JsonReader reader, e composition, int points) throws IOException {
        return new c(a(reader, composition, new l(points)));
    }

    @Nullable
    private static <T> List<com.airbnb.lottie.f.a<T>> a(JsonReader reader, e composition, ah<T> valueParser) throws IOException {
        return r.a(reader, composition, 1.0f, valueParser);
    }

    @Nullable
    private static <T> List<com.airbnb.lottie.f.a<T>> a(JsonReader reader, float scale, e composition, ah<T> valueParser) throws IOException {
        return r.a(reader, composition, scale, valueParser);
    }
}
