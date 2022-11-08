package com.microsoft.a.a;

import com.microsoft.a.a;
import com.microsoft.a.k;
import com.microsoft.a.k.b;
import com.microsoft.a.s;
import java.io.IOException;

public final class c {
    public static void a(k reader, a type) throws IOException {
        int i;
        switch (type) {
            case BT_BOOL:
                reader.d();
                return;
            case BT_UINT8:
                reader.i();
                return;
            case BT_UINT16:
                reader.j();
                return;
            case BT_UINT32:
                reader.k();
                return;
            case BT_UINT64:
                reader.l();
                return;
            case BT_FLOAT:
                reader.g();
                return;
            case BT_DOUBLE:
                reader.h();
                return;
            case BT_STRING:
                reader.e();
                return;
            case BT_STRUCT:
                s sVar = new s();
                s.a(reader);
                return;
            case BT_LIST:
            case BT_SET:
                b tag = reader.b();
                for (i = 0; i < tag.a; i++) {
                    reader.a(tag.b);
                }
                return;
            case BT_MAP:
                com.microsoft.a.k.c tag2 = reader.c();
                for (i = 0; i < tag2.a; i++) {
                    reader.a(tag2.b);
                    reader.a(tag2.c);
                }
                return;
            case BT_INT8:
                reader.m();
                return;
            case BT_INT16:
                reader.n();
                return;
            case BT_INT32:
                reader.o();
                return;
            case BT_INT64:
                reader.p();
                return;
            case BT_WSTRING:
                reader.f();
                return;
            default:
                throw new com.microsoft.a.b("Unknown type to skip: " + type.toString());
        }
    }
}
