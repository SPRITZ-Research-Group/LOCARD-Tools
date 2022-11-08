package com.google.a.e;

import com.google.a.b.b;
import com.google.a.c;
import com.google.a.e.a.d;
import com.google.a.e.a.e;
import com.google.a.g;
import com.google.a.h;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class a implements g {
    public final b a(String contents, com.google.a.a format, int width, int height, Map<c, ?> hints) throws h {
        if (format != com.google.a.a.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got " + format);
        }
        Object obj;
        byte[][] a;
        e encoder = new e();
        int margin = 30;
        int errorCorrectionLevel = 2;
        if (hints != null) {
            if (hints.containsKey(c.PDF417_COMPACT)) {
                encoder.a(Boolean.valueOf(hints.get(c.PDF417_COMPACT).toString()).booleanValue());
            }
            if (hints.containsKey(c.PDF417_COMPACTION)) {
                encoder.a(com.google.a.e.a.c.valueOf(hints.get(c.PDF417_COMPACTION).toString()));
            }
            if (hints.containsKey(c.PDF417_DIMENSIONS)) {
                d dimensions = (d) hints.get(c.PDF417_DIMENSIONS);
                encoder.a(dimensions.b(), dimensions.a(), dimensions.d(), dimensions.c());
            }
            if (hints.containsKey(c.MARGIN)) {
                margin = Integer.parseInt(hints.get(c.MARGIN).toString());
            }
            if (hints.containsKey(c.ERROR_CORRECTION)) {
                errorCorrectionLevel = Integer.parseInt(hints.get(c.ERROR_CORRECTION).toString());
            }
            if (hints.containsKey(c.CHARACTER_SET)) {
                encoder.a(Charset.forName(hints.get(c.CHARACTER_SET).toString()));
            }
        }
        encoder.a(contents, errorCorrectionLevel);
        byte[][] a2 = encoder.a().a(1, 4);
        Object obj2 = height > width ? 1 : null;
        if (a2[0].length < a2.length) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj2 != obj) {
            a = a(a2);
            obj2 = 1;
        } else {
            obj2 = null;
            a = a2;
        }
        int length = width / a[0].length;
        int length2 = height / a.length;
        if (length >= length2) {
            length = length2;
        }
        if (length <= 1) {
            return a(a, margin);
        }
        byte[][] a3;
        a = encoder.a().a(length, length << 2);
        if (obj2 != null) {
            a3 = a(a);
        } else {
            a3 = a;
        }
        return a(a3, margin);
    }

    private static b a(byte[][] input, int margin) {
        b output = new b(input[0].length + (margin * 2), input.length + (margin * 2));
        output.a();
        int y = 0;
        int yOutput = (output.c() - margin) - 1;
        while (y < input.length) {
            byte[] inputY = input[y];
            for (int x = 0; x < input[0].length; x++) {
                if (inputY[x] == (byte) 1) {
                    output.b(x + margin, yOutput);
                }
            }
            y++;
            yOutput--;
        }
        return output;
    }

    private static byte[][] a(byte[][] bitarray) {
        byte[][] temp = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{bitarray[0].length, bitarray.length});
        for (int ii = 0; ii < bitarray.length; ii++) {
            int inverseii = (bitarray.length - ii) - 1;
            for (int jj = 0; jj < bitarray[0].length; jj++) {
                temp[jj][inverseii] = bitarray[ii][jj];
            }
        }
        return temp;
    }
}
