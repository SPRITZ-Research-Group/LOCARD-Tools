package com.google.a;

import com.google.a.b.b;
import com.google.a.d.d;
import com.google.a.d.f;
import com.google.a.d.h;
import com.google.a.d.j;
import com.google.a.d.k;
import com.google.a.d.l;
import com.google.a.d.o;
import com.google.a.d.s;
import com.google.a.f.a;
import java.util.Map;

public final class e implements g {
    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        g writer;
        switch (format) {
            case EAN_8:
                writer = new k();
                break;
            case UPC_E:
                writer = new s();
                break;
            case EAN_13:
                writer = new j();
                break;
            case UPC_A:
                writer = new o();
                break;
            case QR_CODE:
                writer = new a();
                break;
            case CODE_39:
                writer = new f();
                break;
            case CODE_93:
                writer = new h();
                break;
            case CODE_128:
                writer = new d();
                break;
            case ITF:
                writer = new l();
                break;
            case PDF_417:
                writer = new com.google.a.e.a();
                break;
            case CODABAR:
                writer = new com.google.a.d.b();
                break;
            case DATA_MATRIX:
                writer = new com.google.a.c.a();
                break;
            case AZTEC:
                writer = new com.google.a.a.a();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + format);
        }
        return writer.a(contents, format, width, height, hints);
    }
}
