package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.d.i;
import com.google.android.exoplayer2.d.k;
import java.util.Collections;
import java.util.List;

public final class b {
    public final List<byte[]> a;
    public final int b;

    public static b a(k data) throws com.google.android.exoplayer2.k {
        try {
            int i;
            int numberOfNalUnits;
            int j;
            int nalUnitLength;
            data.d(21);
            int lengthSizeMinusOne = data.g() & 3;
            int numberOfArrays = data.g();
            int csdLength = 0;
            int csdStartPosition = data.d();
            for (i = 0; i < numberOfArrays; i++) {
                data.d(1);
                numberOfNalUnits = data.h();
                for (j = 0; j < numberOfNalUnits; j++) {
                    nalUnitLength = data.h();
                    csdLength += nalUnitLength + 4;
                    data.d(nalUnitLength);
                }
            }
            data.c(csdStartPosition);
            byte[] buffer = new byte[csdLength];
            int bufferPosition = 0;
            for (i = 0; i < numberOfArrays; i++) {
                data.d(1);
                numberOfNalUnits = data.h();
                for (j = 0; j < numberOfNalUnits; j++) {
                    nalUnitLength = data.h();
                    System.arraycopy(i.a, 0, buffer, bufferPosition, i.a.length);
                    bufferPosition += i.a.length;
                    System.arraycopy(data.a, data.d(), buffer, bufferPosition, nalUnitLength);
                    bufferPosition += nalUnitLength;
                    data.d(nalUnitLength);
                }
            }
            return new b(csdLength == 0 ? null : Collections.singletonList(buffer), lengthSizeMinusOne + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new com.google.android.exoplayer2.k("Error parsing HEVC config", e);
        }
    }

    private b(List<byte[]> initializationData, int nalUnitLengthFieldLength) {
        this.a = initializationData;
        this.b = nalUnitLengthFieldLength;
    }
}
