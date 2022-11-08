package com.google.a.c;

import com.google.a.b.b;
import com.google.a.c;
import com.google.a.c.a.e;
import com.google.a.c.a.i;
import com.google.a.c.a.j;
import com.google.a.c.a.k;
import com.google.a.c.a.l;
import com.google.a.g;
import java.util.Map;

public final class a implements g {
    public final b a(String contents, com.google.a.a format, int width, int height, Map<c, ?> hints) {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != com.google.a.a.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + format);
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions can't be negative: " + width + 'x' + height);
        } else {
            l shape = l.FORCE_NONE;
            com.google.a.b minSize = null;
            com.google.a.b maxSize = null;
            if (hints != null) {
                l requestedShape = (l) hints.get(c.DATA_MATRIX_SHAPE);
                if (requestedShape != null) {
                    shape = requestedShape;
                }
                com.google.a.b requestedMinSize = (com.google.a.b) hints.get(c.MIN_SIZE);
                if (requestedMinSize != null) {
                    minSize = requestedMinSize;
                }
                com.google.a.b requestedMaxSize = (com.google.a.b) hints.get(c.MAX_SIZE);
                if (requestedMaxSize != null) {
                    maxSize = requestedMaxSize;
                }
            }
            String encoded = j.a(contents, shape, minSize, maxSize);
            k symbolInfo = k.a(encoded.length(), shape, minSize, maxSize);
            e placement = new e(i.a(encoded, symbolInfo), symbolInfo.b(), symbolInfo.c());
            placement.a();
            return a(placement, symbolInfo, width, height);
        }
    }

    private static b a(e placement, k symbolInfo, int width, int height) {
        b bVar;
        int symbolWidth = symbolInfo.b();
        int symbolHeight = symbolInfo.c();
        com.google.a.f.b.b matrix = new com.google.a.f.b.b(symbolInfo.d(), symbolInfo.e());
        int matrixY = 0;
        for (int y = 0; y < symbolHeight; y++) {
            int matrixX;
            int x;
            if (y % symbolInfo.c == 0) {
                matrixX = 0;
                for (x = 0; x < symbolInfo.d(); x++) {
                    matrix.a(matrixX, matrixY, x % 2 == 0);
                    matrixX++;
                }
                matrixY++;
            }
            matrixX = 0;
            for (x = 0; x < symbolWidth; x++) {
                if (x % symbolInfo.b == 0) {
                    matrix.a(matrixX, matrixY, true);
                    matrixX++;
                }
                matrix.a(matrixX, matrixY, placement.a(x, y));
                matrixX++;
                if (x % symbolInfo.b == symbolInfo.b - 1) {
                    matrix.a(matrixX, matrixY, y % 2 == 0);
                    matrixX++;
                }
            }
            matrixY++;
            if (y % symbolInfo.c == symbolInfo.c - 1) {
                matrixX = 0;
                for (x = 0; x < symbolInfo.d(); x++) {
                    matrix.a(matrixX, matrixY, true);
                    matrixX++;
                }
                matrixY++;
            }
        }
        int b = matrix.b();
        int a = matrix.a();
        int max = Math.max(width, b);
        int max2 = Math.max(height, a);
        int min = Math.min(max / b, max2 / a);
        int i = (max - (b * min)) / 2;
        max2 = (max2 - (a * min)) / 2;
        if (height < a || width < b) {
            i = 0;
            max2 = 0;
            bVar = new b(b, a);
        } else {
            bVar = new b(width, height);
        }
        bVar.a();
        int i2 = 0;
        while (true) {
            int i3 = max2;
            int i4 = i2;
            if (i4 >= a) {
                return bVar;
            }
            i2 = 0;
            max2 = i;
            while (i2 < b) {
                if (matrix.a(i2, i4) == (byte) 1) {
                    bVar.a(max2, i3, min, min);
                }
                i2++;
                max2 += min;
            }
            i2 = i4 + 1;
            max2 = i3 + min;
        }
    }
}
