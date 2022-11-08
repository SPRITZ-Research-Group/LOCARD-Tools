package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.facebook.common.e.i;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.IOException;
import java.util.concurrent.Executor;

public final class v extends z {
    private final AssetManager a;

    public v(Executor executor, i pooledByteBufferFactory, AssetManager assetManager) {
        super(executor, pooledByteBufferFactory);
        this.a = assetManager;
    }

    protected final e a(b imageRequest) throws IOException {
        return b(this.a.open(c(imageRequest), 2), b(imageRequest));
    }

    private int b(b imageRequest) {
        AssetFileDescriptor fd = null;
        try {
            fd = this.a.openFd(c(imageRequest));
            int length = (int) fd.getLength();
            if (fd == null) {
                return length;
            }
            try {
                fd.close();
                return length;
            } catch (IOException e) {
                return length;
            }
        } catch (IOException e2) {
            if (fd != null) {
                try {
                    fd.close();
                } catch (IOException e3) {
                }
            }
            return -1;
        } catch (Throwable th) {
            if (fd != null) {
                try {
                    fd.close();
                } catch (IOException e4) {
                }
            }
        }
    }

    protected final String a() {
        return "LocalAssetFetchProducer";
    }

    private static String c(b imageRequest) {
        return imageRequest.b().getPath().substring(1);
    }
}
