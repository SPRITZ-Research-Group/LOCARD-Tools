package com.facebook.react.views.a;

import com.facebook.imagepipeline.core.g;
import com.facebook.imagepipeline.core.j;
import java.util.List;
import javax.annotation.Nullable;

public final class b {

    public static class a {
        @Nullable
        private final a a;
        @Nullable
        private final a b;

        /* synthetic */ a(a x0, a x1, byte b) {
            this(x0, x1);
        }

        private a(@Nullable a bestResult, @Nullable a bestResultInCache) {
            this.a = bestResult;
            this.b = bestResultInCache;
        }

        @Nullable
        public final a a() {
            return this.a;
        }

        @Nullable
        public final a b() {
            return this.b;
        }
    }

    public static a a(int width, int height, List<a> sources) {
        if (sources.isEmpty()) {
            return new a(null, null, (byte) 0);
        }
        if (sources.size() == 1) {
            return new a((a) sources.get(0), null, (byte) 0);
        }
        if (width <= 0 || height <= 0) {
            return new a(null, null, (byte) 0);
        }
        g imagePipeline = j.a().c();
        a best = null;
        a bestCached = null;
        double viewArea = ((double) (width * height)) * 1.0d;
        double bestPrecision = Double.MAX_VALUE;
        double bestCachePrecision = Double.MAX_VALUE;
        for (a source : sources) {
            double precision = Math.abs(1.0d - (source.c() / viewArea));
            if (precision < bestPrecision) {
                bestPrecision = precision;
                best = source;
            }
            if (precision < bestCachePrecision && (imagePipeline.b(source.b()) || imagePipeline.c(source.b()))) {
                bestCachePrecision = precision;
                bestCached = source;
            }
        }
        if (!(bestCached == null || best == null || !bestCached.a().equals(best.a()))) {
            bestCached = null;
        }
        return new a(best, bestCached, (byte) 0);
    }
}
