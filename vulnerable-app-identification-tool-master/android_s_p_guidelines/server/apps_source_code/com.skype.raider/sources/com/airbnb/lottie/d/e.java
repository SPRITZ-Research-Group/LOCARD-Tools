package com.airbnb.lottie.d;

import android.os.AsyncTask;
import android.util.JsonReader;
import com.airbnb.lottie.a;
import com.airbnb.lottie.h;
import java.io.IOException;

public final class e extends AsyncTask<JsonReader, Void, com.airbnb.lottie.e> implements a {
    private final h a;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((JsonReader[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        this.a.a((com.airbnb.lottie.e) obj);
    }

    public e(h loadedListener) {
        this.a = loadedListener;
    }

    private static com.airbnb.lottie.e a(JsonReader... params) {
        try {
            return t.a(params[0]);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public final void a() {
        cancel(true);
    }
}
