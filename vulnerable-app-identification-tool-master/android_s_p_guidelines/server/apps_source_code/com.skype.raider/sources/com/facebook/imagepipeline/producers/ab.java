package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.facebook.common.e.i;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.IOException;
import java.util.concurrent.Executor;

public final class ab extends z {
    private final Resources a;

    public ab(Executor executor, i pooledByteBufferFactory, Resources resources) {
        super(executor, pooledByteBufferFactory);
        this.a = resources;
    }

    protected final e a(b imageRequest) throws IOException {
        return b(this.a.openRawResource(c(imageRequest)), b(imageRequest));
    }

    private int b(b imageRequest) {
        AssetFileDescriptor fd = null;
        try {
            fd = this.a.openRawResourceFd(c(imageRequest));
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
        } catch (NotFoundException e2) {
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
        return "LocalResourceFetchProducer";
    }

    private static int c(b imageRequest) {
        return Integer.parseInt(imageRequest.b().getPath().substring(1));
    }
}
