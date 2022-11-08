package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.e;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public interface af<FETCH_STATE extends s> {

    public interface a {
        void a();

        void a(InputStream inputStream, int i) throws IOException;

        void a(Throwable th);
    }

    FETCH_STATE a(Consumer<e> consumer, ak akVar);

    @Nullable
    Map<String, String> a(FETCH_STATE fetch_state, int i);

    void a(FETCH_STATE fetch_state);

    void a(FETCH_STATE fetch_state, a aVar);
}
