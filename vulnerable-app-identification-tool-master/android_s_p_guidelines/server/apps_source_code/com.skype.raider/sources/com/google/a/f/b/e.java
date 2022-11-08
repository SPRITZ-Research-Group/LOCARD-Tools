package com.google.a.f.b;

import com.google.a.b.a;
import com.google.a.f.a.c;
import com.google.a.h;

final class e {
    private static final int[][] a = new int[][]{new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = new int[][]{new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] c = new int[][]{new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] d = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    private static void a(c version, b matrix) throws h {
        if (version.a() >= 7) {
            a versionInfoBits = new a();
            versionInfoBits.a(version.a(), 6);
            versionInfoBits.a(a(version.a(), 7973), 12);
            if (versionInfoBits.a() != 18) {
                throw new h("should not happen but we got: " + versionInfoBits.a());
            }
            int bitIndex = 17;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    boolean bit = versionInfoBits.a(bitIndex);
                    bitIndex--;
                    matrix.a(i, (matrix.a() - 11) + j, bit);
                    matrix.a((matrix.a() - 11) + j, i, bit);
                }
            }
        }
    }

    private static void a(a dataBits, int maskPattern, b matrix) throws h {
        int bitIndex = 0;
        int direction = -1;
        int x = matrix.b() - 1;
        int y = matrix.a() - 1;
        while (x > 0) {
            if (x == 6) {
                x--;
            }
            while (y >= 0 && y < matrix.a()) {
                for (int i = 0; i < 2; i++) {
                    int xx = x - i;
                    if (a(matrix.a(xx, y))) {
                        boolean bit;
                        if (bitIndex < dataBits.a()) {
                            bit = dataBits.a(bitIndex);
                            bitIndex++;
                        } else {
                            bit = false;
                        }
                        if (maskPattern != -1) {
                            int i2;
                            Object obj;
                            switch (maskPattern) {
                                case 0:
                                    i2 = (y + xx) & 1;
                                    break;
                                case 1:
                                    i2 = y & 1;
                                    break;
                                case 2:
                                    i2 = xx % 3;
                                    break;
                                case 3:
                                    i2 = (y + xx) % 3;
                                    break;
                                case 4:
                                    i2 = ((y / 2) + (xx / 3)) & 1;
                                    break;
                                case 5:
                                    i2 = y * xx;
                                    i2 = (i2 % 3) + (i2 & 1);
                                    break;
                                case 6:
                                    i2 = y * xx;
                                    i2 = ((i2 % 3) + (i2 & 1)) & 1;
                                    break;
                                case 7:
                                    i2 = (((y * xx) % 3) + ((y + xx) & 1)) & 1;
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid mask pattern: " + maskPattern);
                            }
                            if (i2 == 0) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            if (obj != null) {
                                bit = !bit;
                            }
                        }
                        matrix.a(xx, y, bit);
                    }
                }
                y += direction;
            }
            direction = -direction;
            y += direction;
            x -= 2;
        }
        if (bitIndex != dataBits.a()) {
            throw new h("Not all bits consumed: " + bitIndex + '/' + dataBits.a());
        }
    }

    private static int a(int value, int poly) {
        if (poly == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int msbSetInPoly = 32 - Integer.numberOfLeadingZeros(poly);
        value <<= msbSetInPoly - 1;
        while (32 - Integer.numberOfLeadingZeros(value) >= msbSetInPoly) {
            value ^= poly << ((32 - Integer.numberOfLeadingZeros(value)) - msbSetInPoly);
        }
        return value;
    }

    private static boolean a(int value) {
        return value == -1;
    }

    private static void a(int xStart, int yStart, b matrix) throws h {
        int x = 0;
        while (x < 8) {
            if (a(matrix.a(xStart + x, yStart))) {
                matrix.a(xStart + x, yStart, 0);
                x++;
            } else {
                throw new h();
            }
        }
    }

    private static void b(int xStart, int yStart, b matrix) throws h {
        int y = 0;
        while (y < 7) {
            if (a(matrix.a(xStart, yStart + y))) {
                matrix.a(xStart, yStart + y, 0);
                y++;
            } else {
                throw new h();
            }
        }
    }

    private static void c(int xStart, int yStart, b matrix) {
        for (int y = 0; y < 7; y++) {
            int[] patternY = a[y];
            for (int x = 0; x < 7; x++) {
                matrix.a(xStart + x, yStart + y, patternY[x]);
            }
        }
    }

    static void a(a dataBits, com.google.a.f.a.a ecLevel, c version, int maskPattern, b matrix) throws h {
        matrix.d();
        int length = a[0].length;
        c(0, 0, matrix);
        c(matrix.b() - length, 0, matrix);
        c(0, matrix.b() - length, matrix);
        a(0, 7, matrix);
        a(matrix.b() - 8, 7, matrix);
        a(0, matrix.b() - 8, matrix);
        b(7, 0, matrix);
        b((matrix.a() - 7) - 1, 0, matrix);
        b(7, matrix.a() - 7, matrix);
        if (matrix.a(8, matrix.a() - 8) == (byte) 0) {
            throw new h();
        }
        int i;
        Object obj;
        matrix.a(8, matrix.a() - 8, 1);
        if (version.a() >= 2) {
            int[] iArr = c[version.a() - 1];
            for (int i2 : iArr) {
                if (i2 >= 0) {
                    for (int length2 : iArr) {
                        if (length2 >= 0 && a(matrix.a(length2, i2))) {
                            int i3 = length2 - 2;
                            int i4 = i2 - 2;
                            length2 = 0;
                            while (true) {
                                i = length2;
                                if (i >= 5) {
                                    break;
                                }
                                int[] iArr2 = b[i];
                                for (length2 = 0; length2 < 5; length2++) {
                                    matrix.a(i3 + length2, i4 + i, iArr2[length2]);
                                }
                                length2 = i + 1;
                            }
                        }
                    }
                }
            }
        }
        for (length2 = 8; length2 < matrix.b() - 8; length2++) {
            i = (length2 + 1) % 2;
            if (a(matrix.a(length2, 6))) {
                matrix.a(length2, 6, i);
            }
            if (a(matrix.a(6, length2))) {
                matrix.a(6, length2, i);
            }
        }
        a aVar = new a();
        if (maskPattern < 0 || maskPattern >= 8) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            throw new h("Invalid mask pattern");
        }
        length2 = (ecLevel.a() << 3) | maskPattern;
        aVar.a(length2, 5);
        aVar.a(a(length2, 1335), 10);
        a aVar2 = new a();
        aVar2.a(21522, 15);
        aVar.b(aVar2);
        if (aVar.a() != 15) {
            throw new h("should not happen but we got: " + aVar.a());
        }
        for (length2 = 0; length2 < aVar.a(); length2++) {
            boolean a = aVar.a((aVar.a() - 1) - length2);
            int[] iArr3 = d[length2];
            matrix.a(iArr3[0], iArr3[1], a);
            if (length2 < 8) {
                matrix.a((matrix.b() - length2) - 1, 8, a);
            } else {
                matrix.a(8, (matrix.a() - 7) + (length2 - 8), a);
            }
        }
        a(version, matrix);
        a(dataBits, maskPattern, matrix);
    }
}
