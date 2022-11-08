package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.cache.a.c;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b.b;

public final class q extends ad<Pair<c, b>, e> {
    private final f b;

    public q(f cacheKeyFactory, aj inputProducer) {
        super(inputProducer);
        this.b = cacheKeyFactory;
    }

    protected final /* synthetic */ Object a(ak akVar) {
        return Pair.create(this.b.a(akVar.a()), akVar.e());
    }
}
