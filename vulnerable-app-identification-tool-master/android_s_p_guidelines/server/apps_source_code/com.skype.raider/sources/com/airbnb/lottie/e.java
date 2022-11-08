package com.airbnb.lottie;

import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.util.f;
import android.support.v4.util.m;
import android.util.JsonReader;
import com.airbnb.lottie.c.c;
import com.airbnb.lottie.c.c.d;
import com.airbnb.lottie.d.t;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class e {
    private final i a = new i();
    private final HashSet<String> b = new HashSet();
    private Map<String, List<d>> c;
    private Map<String, f> d;
    private Map<String, c> e;
    private m<com.airbnb.lottie.c.d> f;
    private f<d> g;
    private List<d> h;
    private Rect i;
    private float j;
    private float k;
    private float l;

    public static class a {
        public static a a(Context context, String fileName, JSONObject parameters, h listener) {
            try {
                return a(context.getAssets().open(fileName), parameters, listener);
            } catch (IOException e) {
                throw new IllegalArgumentException("Unable to find file " + fileName, e);
            }
        }

        public static a a(InputStream stream, JSONObject parameters, h listener) {
            if (parameters == null) {
                return a(new JsonReader(new InputStreamReader(stream)), listener);
            }
            a fVar = new com.airbnb.lottie.d.f(parameters, listener);
            fVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new InputStream[]{stream});
            return fVar;
        }

        public static a a(JsonReader reader, h listener) {
            com.airbnb.lottie.d.e loader = new com.airbnb.lottie.d.e(listener);
            loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JsonReader[]{reader});
            return loader;
        }

        public static e a(String string) {
            try {
                return t.a(new JsonReader(new StringReader(string)));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public final void a(Rect bounds, float startFrame, float endFrame, float frameRate, List<d> layers, f<d> layerMap, Map<String, List<d>> precomps, Map<String, f> images, m<com.airbnb.lottie.c.d> characters, Map<String, c> fonts) {
        this.i = bounds;
        this.j = startFrame;
        this.k = endFrame;
        this.l = frameRate;
        this.h = layers;
        this.g = layerMap;
        this.c = precomps;
        this.d = images;
        this.f = characters;
        this.e = fonts;
    }

    public final void a(String warning) {
        this.b.add(warning);
    }

    public final void a(boolean enabled) {
        this.a.a(enabled);
    }

    public final i a() {
        return this.a;
    }

    public final d a(long id) {
        return (d) this.g.a(id);
    }

    public final Rect b() {
        return this.i;
    }

    public final float c() {
        return (float) ((long) ((k() / this.l) * 1000.0f));
    }

    public final float d() {
        return this.j;
    }

    public final float e() {
        return this.k;
    }

    public final float f() {
        return this.l;
    }

    public final List<d> g() {
        return this.h;
    }

    @Nullable
    public final List<d> b(String id) {
        return (List) this.c.get(id);
    }

    public final m<com.airbnb.lottie.c.d> h() {
        return this.f;
    }

    public final Map<String, c> i() {
        return this.e;
    }

    public final Map<String, f> j() {
        return this.d;
    }

    public final float k() {
        return this.k - this.j;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        for (d layer : this.h) {
            sb.append(layer.a("\t"));
        }
        return sb.toString();
    }
}
