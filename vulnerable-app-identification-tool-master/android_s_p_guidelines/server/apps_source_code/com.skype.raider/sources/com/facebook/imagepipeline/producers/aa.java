package com.facebook.imagepipeline.producers;

import com.facebook.common.e.i;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

public final class aa extends z {
    public aa(Executor executor, i pooledByteBufferFactory) {
        super(executor, pooledByteBufferFactory);
    }

    protected final e a(b imageRequest) throws IOException {
        return b(new FileInputStream(imageRequest.p().toString()), (int) imageRequest.p().length());
    }

    protected final String a() {
        return "LocalFileFetchProducer";
    }
}
