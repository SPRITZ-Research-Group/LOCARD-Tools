package com.facebook.imagepipeline.d;

import android.net.Uri;
import com.facebook.cache.a.c;
import com.facebook.cache.a.h;
import com.facebook.imagepipeline.k.b;
import com.facebook.imagepipeline.k.d;

public final class j implements f {
    private static j a = null;

    protected j() {
    }

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                a = new j();
            }
            jVar = a;
        }
        return jVar;
    }

    public final c a(b request, Object callerContext) {
        return new c(request.b().toString(), request.f(), request.g(), request.i(), null, null, callerContext);
    }

    public final c b(b request, Object callerContext) {
        c postprocessorCacheKey;
        String postprocessorName;
        d postprocessor = request.q();
        if (postprocessor != null) {
            postprocessorCacheKey = postprocessor.a();
            postprocessorName = postprocessor.getClass().getName();
        } else {
            postprocessorCacheKey = null;
            postprocessorName = null;
        }
        return new c(request.b().toString(), request.f(), request.g(), request.i(), postprocessorCacheKey, postprocessorName, callerContext);
    }

    public final c a(b request) {
        return a(request.b());
    }

    public final c a(Uri sourceUri) {
        return new h(sourceUri.toString());
    }
}
