package com.google.a.e.a;

final class b {
    private final byte[] a;
    private int b = 0;

    b(int width) {
        this.a = new byte[width];
    }

    final void a(boolean black, int width) {
        for (int ii = 0; ii < width; ii++) {
            int i;
            int i2 = this.b;
            this.b = i2 + 1;
            byte[] bArr = this.a;
            if (black) {
                i = 1;
            } else {
                i = 0;
            }
            bArr[i2] = (byte) i;
        }
    }

    final byte[] a(int scale) {
        byte[] output = new byte[(this.a.length * scale)];
        for (int i = 0; i < output.length; i++) {
            output[i] = this.a[i / scale];
        }
        return output;
    }
}
