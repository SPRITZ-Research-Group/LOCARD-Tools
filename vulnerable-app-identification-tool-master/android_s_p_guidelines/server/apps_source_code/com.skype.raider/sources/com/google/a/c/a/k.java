package com.google.a.c.a;

import com.google.a.b;
import com.skype.android.video.ControlUnit;

public class k {
    static final k[] a;
    private static k[] d;
    public final int b;
    public final int c;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;

    static {
        k[] kVarArr = new k[]{new k(false, 3, 5, 8, 8, 1), new k(false, 5, 7, 10, 10, 1), new k(true, 5, 7, 16, 6, 1), new k(false, 8, 10, 12, 12, 1), new k(true, 10, 11, 14, 6, 2), new k(false, 12, 12, 14, 14, 1), new k(true, 16, 14, 24, 10, 1), new k(false, 18, 14, 16, 16, 1), new k(false, 22, 18, 18, 18, 1), new k(true, 22, 18, 16, 10, 2), new k(false, 30, 20, 20, 20, 1), new k(true, 32, 24, 16, 14, 2), new k(false, 36, 24, 22, 22, 1), new k(false, 44, 28, 24, 24, 1), new k(true, 49, 28, 22, 14, 2), new k(false, 62, 36, 14, 14, 4), new k(false, 86, 42, 16, 16, 4), new k(false, 114, 48, 18, 18, 4), new k(false, 144, 56, 20, 20, 4), new k(false, 174, 68, 22, 22, 4), new k(false, 204, 84, 24, 24, 4, 102, 42), new k(false, 280, 112, 14, 14, 16, 140, 56), new k(false, 368, 144, 16, 16, 16, 92, 36), new k(false, 456, 192, 18, 18, 16, 114, 48), new k(false, 576, 224, 20, 20, 16, 144, 56), new k(false, 696, ControlUnit.STATE_PREVIEW_RESOLUTION_CHANGED, 22, 22, 16, 174, 68), new k(false, 816, 336, 24, 24, 16, 136, 56), new k(false, 1050, 408, 18, 18, 36, 175, 68), new k(false, 1304, 496, 20, 20, 36, 163, 62), new d()};
        a = kVarArr;
        d = kVarArr;
    }

    private k(boolean rectangular, int dataCapacity, int errorCodewords, int matrixWidth, int matrixHeight, int dataRegions) {
        this(rectangular, dataCapacity, errorCodewords, matrixWidth, matrixHeight, dataRegions, dataCapacity, errorCodewords);
    }

    k(boolean rectangular, int dataCapacity, int errorCodewords, int matrixWidth, int matrixHeight, int dataRegions, int rsBlockData, int rsBlockError) {
        this.e = rectangular;
        this.f = dataCapacity;
        this.g = errorCodewords;
        this.b = matrixWidth;
        this.c = matrixHeight;
        this.h = dataRegions;
        this.i = rsBlockData;
        this.j = rsBlockError;
    }

    public static k a(int dataCodewords, l shape, b minSize, b maxSize) {
        for (k symbol : d) {
            if ((shape != l.FORCE_SQUARE || !symbol.e) && ((shape != l.FORCE_RECTANGLE || symbol.e) && ((minSize == null || (symbol.d() >= minSize.a() && symbol.e() >= minSize.b())) && ((maxSize == null || (symbol.d() <= maxSize.a() && symbol.e() <= maxSize.b())) && dataCodewords <= symbol.f)))) {
                return symbol;
            }
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + dataCodewords);
    }

    private int i() {
        switch (this.h) {
            case 1:
                return 1;
            case 2:
            case 4:
                return 2;
            case 16:
                return 4;
            case 36:
                return 6;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    private int j() {
        switch (this.h) {
            case 1:
            case 2:
                return 1;
            case 4:
                return 2;
            case 16:
                return 4;
            case 36:
                return 6;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int b() {
        return i() * this.b;
    }

    public final int c() {
        return j() * this.c;
    }

    public final int d() {
        return b() + (i() << 1);
    }

    public final int e() {
        return c() + (j() << 1);
    }

    public int a() {
        return this.f / this.i;
    }

    public final int f() {
        return this.f;
    }

    public final int g() {
        return this.g;
    }

    public int a(int index) {
        return this.i;
    }

    public final int h() {
        return this.j;
    }

    public final String toString() {
        return (this.e ? "Rectangular Symbol:" : "Square Symbol:") + " data region " + this.b + 'x' + this.c + ", symbol size " + d() + 'x' + e() + ", symbol data size " + b() + 'x' + c() + ", codewords " + this.f + '+' + this.g;
    }
}
