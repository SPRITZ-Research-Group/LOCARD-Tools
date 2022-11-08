package com.facebook.imagepipeline.i;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.os.MemoryFile;
import com.facebook.common.e.h;
import com.facebook.common.e.j;
import com.facebook.common.internal.l;
import com.facebook.common.j.a;
import com.facebook.common.j.b;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class c extends DalvikPurgeableDecoder {
    private static Method a;
    @Nullable
    private final a b = b.a();

    protected final Bitmap decodeByteArrayAsPurgeable(com.facebook.common.f.a<h> bytesRef, Options options) {
        return b(bytesRef, ((h) bytesRef.a()).a(), null);
    }

    protected final Bitmap decodeJPEGByteArrayAsPurgeable(com.facebook.common.f.a<h> bytesRef, int length, Options options) {
        return b(bytesRef, length, DalvikPurgeableDecoder.endsWithEOI(bytesRef, length) ? null : EOI);
    }

    private static MemoryFile a(com.facebook.common.f.a<h> bytesRef, int inputLength, @Nullable byte[] suffix) throws IOException {
        Throwable th;
        MemoryFile memoryFile = new MemoryFile(null, inputLength + (suffix == null ? 0 : suffix.length));
        memoryFile.allowPurging(false);
        InputStream pbbIs = null;
        InputStream is = null;
        try {
            InputStream pbbIs2 = new j((h) bytesRef.a());
            try {
                InputStream is2 = new com.facebook.common.h.a(pbbIs2, inputLength);
                try {
                    Closeable os = memoryFile.getOutputStream();
                    com.facebook.common.internal.a.a(is2, os);
                    if (suffix != null) {
                        memoryFile.writeBytes(suffix, 0, inputLength, suffix.length);
                    }
                    com.facebook.common.f.a.c(bytesRef);
                    com.facebook.common.internal.b.a(pbbIs2);
                    com.facebook.common.internal.b.a(is2);
                    com.facebook.common.internal.b.a(os);
                    return memoryFile;
                } catch (Throwable th2) {
                    th = th2;
                    is = is2;
                    pbbIs = pbbIs2;
                    com.facebook.common.f.a.c(bytesRef);
                    com.facebook.common.internal.b.a(pbbIs);
                    com.facebook.common.internal.b.a(is);
                    com.facebook.common.internal.b.a((Closeable) null);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                pbbIs = pbbIs2;
                com.facebook.common.f.a.c(bytesRef);
                com.facebook.common.internal.b.a(pbbIs);
                com.facebook.common.internal.b.a(is);
                com.facebook.common.internal.b.a((Closeable) null);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            com.facebook.common.f.a.c(bytesRef);
            com.facebook.common.internal.b.a(pbbIs);
            com.facebook.common.internal.b.a(is);
            com.facebook.common.internal.b.a((Closeable) null);
            throw th;
        }
    }

    private synchronized Method a() {
        if (a == null) {
            try {
                a = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Throwable e) {
                throw l.b(e);
            }
        }
        return a;
    }

    private FileDescriptor a(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) a().invoke(memoryFile, new Object[0]);
        } catch (Throwable e) {
            throw l.b(e);
        }
    }

    private Bitmap b(com.facebook.common.f.a<h> bytesRef, int inputLength, byte[] suffix) {
        MemoryFile memoryFile = null;
        try {
            memoryFile = a(bytesRef, inputLength, suffix);
            a(memoryFile);
            if (this.b != null) {
                Bitmap bitmap = (Bitmap) com.facebook.common.internal.h.a(this.b.a(), (Object) "BitmapFactory returned null");
                memoryFile.close();
                return bitmap;
            }
            throw new IllegalStateException("WebpBitmapFactory is null");
        } catch (Throwable e) {
            throw l.b(e);
        } catch (Throwable th) {
            if (memoryFile != null) {
                memoryFile.close();
            }
        }
    }
}
