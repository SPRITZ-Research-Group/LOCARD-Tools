package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.cache.a.c;
import com.facebook.common.f.a;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.k.b.b;
import java.io.Closeable;

public final class g extends ad<Pair<c, b>, a<com.facebook.imagepipeline.image.c>> {
    private final f b;

    public final /* synthetic */ Closeable a(Closeable closeable) {
        return a.b((a) closeable);
    }

    public g(f cacheKeyFactory, aj inputProducer) {
        super(inputProducer);
        this.b = cacheKeyFactory;
    }

    protected final /* synthetic */ Object a(ak akVar) {
        return Pair.create(this.b.a(akVar.a(), akVar.d()), akVar.e());
    }
}
