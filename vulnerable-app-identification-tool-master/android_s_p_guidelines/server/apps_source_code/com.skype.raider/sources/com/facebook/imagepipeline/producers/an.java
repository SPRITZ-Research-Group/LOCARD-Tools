package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import com.facebook.common.e.i;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.IOException;
import java.util.concurrent.Executor;

public final class an extends z {
    private final ContentResolver a;

    public an(Executor executor, i pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.a = contentResolver;
    }

    protected final e a(b imageRequest) throws IOException {
        return b(this.a.openInputStream(imageRequest.b()), -1);
    }

    protected final String a() {
        return "QualifiedResourceFetchProducer";
    }
}
