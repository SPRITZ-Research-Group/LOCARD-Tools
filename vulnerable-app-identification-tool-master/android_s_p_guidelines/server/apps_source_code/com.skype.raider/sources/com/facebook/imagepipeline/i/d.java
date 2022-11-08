package com.facebook.imagepipeline.i;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.imagepipeline.memory.n;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(19)
@ThreadSafe
public final class d extends DalvikPurgeableDecoder {
    private final n a;

    public d(n flexByteArrayPool) {
        this.a = flexByteArrayPool;
    }

    protected final Bitmap decodeByteArrayAsPurgeable(a<h> bytesRef, Options options) {
        h pooledByteBuffer = (h) bytesRef.a();
        int length = pooledByteBuffer.a();
        a<byte[]> encodedBytesArrayRef = this.a.a(length);
        try {
            byte[] encodedBytesArray = (byte[]) encodedBytesArrayRef.a();
            pooledByteBuffer.a(0, encodedBytesArray, 0, length);
            Bitmap bitmap = (Bitmap) com.facebook.common.internal.h.a(BitmapFactory.decodeByteArray(encodedBytesArray, 0, length, options), (Object) "BitmapFactory returned null");
            return bitmap;
        } finally {
            a.c(encodedBytesArrayRef);
        }
    }

    protected final Bitmap decodeJPEGByteArrayAsPurgeable(a<h> bytesRef, int length, Options options) {
        boolean z = false;
        byte[] suffix = DalvikPurgeableDecoder.endsWithEOI(bytesRef, length) ? null : EOI;
        h pooledByteBuffer = (h) bytesRef.a();
        if (length <= pooledByteBuffer.a()) {
            z = true;
        }
        com.facebook.common.internal.h.a(z);
        a<byte[]> encodedBytesArrayRef = this.a.a(length + 2);
        try {
            byte[] encodedBytesArray = (byte[]) encodedBytesArrayRef.a();
            pooledByteBuffer.a(0, encodedBytesArray, 0, length);
            if (suffix != null) {
                encodedBytesArray[length] = (byte) -1;
                encodedBytesArray[length + 1] = (byte) -39;
                length += 2;
            }
            Bitmap bitmap = (Bitmap) com.facebook.common.internal.h.a(BitmapFactory.decodeByteArray(encodedBytesArray, 0, length, options), (Object) "BitmapFactory returned null");
            return bitmap;
        } finally {
            a.c(encodedBytesArrayRef);
        }
    }
}
