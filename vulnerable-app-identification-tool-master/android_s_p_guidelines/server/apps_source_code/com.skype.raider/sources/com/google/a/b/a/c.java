package com.google.a.b.a;

import java.util.ArrayList;
import java.util.List;

public final class c {
    private final a a;
    private final List<b> b = new ArrayList();

    public c(a field) {
        this.a = field;
        this.b.add(new b(field, new int[]{1}));
    }

    private b a(int degree) {
        if (degree >= this.b.size()) {
            b lastGenerator = (b) this.b.get(this.b.size() - 1);
            for (int d = this.b.size(); d <= degree; d++) {
                b nextGenerator = lastGenerator.a(new b(this.a, new int[]{1, this.a.a((d - 1) + this.a.b())}));
                this.b.add(nextGenerator);
                lastGenerator = nextGenerator;
            }
        }
        return (b) this.b.get(degree);
    }

    public final void a(int[] toEncode, int ecBytes) {
        if (ecBytes == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int dataBytes = toEncode.length - ecBytes;
        if (dataBytes <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b generator = a(ecBytes);
        int[] infoCoefficients = new int[dataBytes];
        System.arraycopy(toEncode, 0, infoCoefficients, 0, dataBytes);
        int[] coefficients = new b(this.a, infoCoefficients).a(ecBytes, 1).b(generator)[1].a();
        int numZeroCoefficients = ecBytes - coefficients.length;
        for (int i = 0; i < numZeroCoefficients; i++) {
            toEncode[dataBytes + i] = 0;
        }
        System.arraycopy(coefficients, 0, toEncode, dataBytes + numZeroCoefficients, coefficients.length);
    }
}
