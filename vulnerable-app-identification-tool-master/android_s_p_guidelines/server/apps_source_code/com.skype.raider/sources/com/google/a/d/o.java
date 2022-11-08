package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.g;
import com.google.a.h;
import java.util.Map;

public final class o implements g {
    private final j a = new j();

    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        if (format == a.UPC_A) {
            return this.a.a("0" + contents, a.EAN_13, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + format);
    }
}
