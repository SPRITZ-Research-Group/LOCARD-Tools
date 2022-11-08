package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.e.h;
import com.facebook.common.e.i;
import com.facebook.common.e.j;
import com.facebook.common.i.f;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import com.facebook.imageutils.a;
import com.facebook.imageutils.c;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class y implements ax<e> {
    private final Executor a;
    private final i b;
    private final ContentResolver c;

    public y(Executor executor, i pooledByteBufferFactory, ContentResolver contentResolver) {
        this.a = executor;
        this.b = pooledByteBufferFactory;
        this.c = contentResolver;
    }

    public final boolean a(com.facebook.imagepipeline.common.e resizeOptions) {
        return ay.a(512, 512, resizeOptions);
    }

    public final void a(Consumer<e> consumer, ak producerContext) {
        am listener = producerContext.c();
        String requestId = producerContext.b();
        final b imageRequest = producerContext.a();
        final ar cancellableProducerRunnable = new ar<e>(this, consumer, listener, "LocalExifThumbnailProducer", requestId) {
            final /* synthetic */ y c;

            protected final /* synthetic */ void b(Object obj) {
                e.d((e) obj);
            }

            protected final /* synthetic */ Map c(Object obj) {
                boolean z;
                String str = "createdThumbnail";
                if (((e) obj) != null) {
                    z = true;
                } else {
                    z = false;
                }
                return com.facebook.common.internal.e.a(str, Boolean.toString(z));
            }

            protected final /* synthetic */ Object c() throws Exception {
                ExifInterface a = this.c.a(imageRequest.b());
                if (a == null || !a.hasThumbnail()) {
                    return null;
                }
                return y.b(this.c.b.a(a.getThumbnail()), a);
            }
        };
        producerContext.a(new e(this) {
            final /* synthetic */ y b;

            public final void a() {
                cancellableProducerRunnable.a();
            }
        });
        this.a.execute(cancellableProducerRunnable);
    }

    @VisibleForTesting
    @Nullable
    final ExifInterface a(Uri uri) {
        Object obj;
        String realPath = f.a(this.c, uri);
        if (realPath != null) {
            try {
                File file = new File(realPath);
                if (file.exists() && file.canRead()) {
                    obj = 1;
                    if (obj != null) {
                        return new ExifInterface(realPath);
                    }
                    return null;
                }
            } catch (IOException e) {
            } catch (StackOverflowError e2) {
                FLog.e(y.class, "StackOverflowError in ExifInterface constructor");
            }
        }
        obj = null;
        if (obj != null) {
            return new ExifInterface(realPath);
        }
        return null;
    }

    private static e b(h imageBytes, ExifInterface exifInterface) {
        int width;
        int height = -1;
        Pair<Integer, Integer> dimensions = a.a(new j(imageBytes));
        int rotationAngle = c.a(Integer.parseInt(exifInterface.getAttribute("Orientation")));
        if (dimensions != null) {
            width = ((Integer) dimensions.first).intValue();
        } else {
            width = -1;
        }
        if (dimensions != null) {
            height = ((Integer) dimensions.second).intValue();
        }
        com.facebook.common.f.a closeableByteBuffer = com.facebook.common.f.a.a((Closeable) imageBytes);
        try {
            e encodedImage = new e(closeableByteBuffer);
            encodedImage.a(com.facebook.imageformat.b.a);
            encodedImage.c(rotationAngle);
            encodedImage.b(width);
            encodedImage.a(height);
            return encodedImage;
        } finally {
            com.facebook.common.f.a.c(closeableByteBuffer);
        }
    }
}
