package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.d.i;
import com.google.android.exoplayer2.d.i.b;
import com.google.android.exoplayer2.d.k;
import java.util.ArrayList;
import java.util.List;

public final class a {
    public final List<byte[]> a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public static a a(k data) throws com.google.android.exoplayer2.k {
        try {
            data.d(4);
            int nalUnitLengthFieldLength = (data.g() & 3) + 1;
            if (nalUnitLengthFieldLength == 3) {
                throw new IllegalStateException();
            }
            int j;
            List<byte[]> initializationData = new ArrayList();
            int numSequenceParameterSets = data.g() & 31;
            for (j = 0; j < numSequenceParameterSets; j++) {
                initializationData.add(b(data));
            }
            int numPictureParameterSets = data.g();
            for (j = 0; j < numPictureParameterSets; j++) {
                initializationData.add(b(data));
            }
            int width = -1;
            int height = -1;
            float pixelWidthAspectRatio = 1.0f;
            if (numSequenceParameterSets > 0) {
                b spsData = i.a((byte[]) initializationData.get(0), nalUnitLengthFieldLength, ((byte[]) initializationData.get(0)).length);
                width = spsData.b;
                height = spsData.c;
                pixelWidthAspectRatio = spsData.d;
            }
            return new a(initializationData, nalUnitLengthFieldLength, width, height, pixelWidthAspectRatio);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new com.google.android.exoplayer2.k("Error parsing AVC config", e);
        }
    }

    private a(List<byte[]> initializationData, int nalUnitLengthFieldLength, int width, int height, float pixelWidthAspectRatio) {
        this.a = initializationData;
        this.b = nalUnitLengthFieldLength;
        this.c = width;
        this.d = height;
        this.e = pixelWidthAspectRatio;
    }

    private static byte[] b(k data) {
        int length = data.h();
        int offset = data.d();
        data.d(length);
        return com.google.android.exoplayer2.d.b.a(data.a, offset, length);
    }
}
