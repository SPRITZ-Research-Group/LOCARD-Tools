package com.microsoft.a.a;

import com.microsoft.a.a;
import com.microsoft.a.k;
import java.io.IOException;

public final class b {
    public static void a(a typeInPayload, a expectedType) {
        if (typeInPayload != expectedType) {
            a aVar = a.BT_UNAVAILABLE;
        }
    }

    public static boolean a(k reader, a typeInPayload) throws IOException {
        a(typeInPayload, a.BT_BOOL);
        return reader.d();
    }

    public static String b(k reader, a typeInPayload) throws IOException {
        a(typeInPayload, a.BT_STRING);
        return reader.e();
    }

    public static double c(k reader, a typeInPayload) throws IOException {
        if (typeInPayload == a.BT_DOUBLE || typeInPayload == a.BT_UNAVAILABLE) {
            return reader.h();
        }
        if (typeInPayload == a.BT_FLOAT) {
            return (double) reader.g();
        }
        a aVar = a.BT_DOUBLE;
        return 0.0d;
    }

    public static short d(k reader, a typeInPayload) throws IOException {
        if (typeInPayload == a.BT_UINT16 || typeInPayload == a.BT_UNAVAILABLE) {
            return reader.j();
        }
        if (typeInPayload == a.BT_UINT8) {
            return (short) reader.i();
        }
        a aVar = a.BT_UINT16;
        return (short) 0;
    }

    public static int e(k reader, a typeInPayload) throws IOException {
        if (typeInPayload == a.BT_INT32 || typeInPayload == a.BT_UNAVAILABLE) {
            return reader.o();
        }
        if (typeInPayload == a.BT_INT16) {
            return reader.n();
        }
        if (typeInPayload == a.BT_INT8) {
            return reader.m();
        }
        a aVar = a.BT_INT32;
        return 0;
    }

    public static long f(k reader, a typeInPayload) throws IOException {
        if (typeInPayload == a.BT_INT64 || typeInPayload == a.BT_UNAVAILABLE) {
            return reader.p();
        }
        if (typeInPayload == a.BT_INT32) {
            return (long) reader.o();
        }
        if (typeInPayload == a.BT_INT16) {
            return (long) reader.n();
        }
        if (typeInPayload == a.BT_INT8) {
            return (long) reader.m();
        }
        a aVar = a.BT_INT64;
        return 0;
    }

    public static void a(k reader) throws IOException {
        while (true) {
            k.a tag = reader.a();
            if (tag.b != a.BT_STOP && tag.b != a.BT_STOP_BASE) {
                reader.a(tag.b);
            } else if (a.BT_STOP == tag.b) {
                return;
            }
        }
    }
}
