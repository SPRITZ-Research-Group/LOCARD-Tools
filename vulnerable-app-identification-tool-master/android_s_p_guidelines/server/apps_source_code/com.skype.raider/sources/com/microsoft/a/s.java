package com.microsoft.a;

import com.microsoft.a.a.b;
import java.io.IOException;

public final class s implements c, d {

    public static class a {
        public static final o a;
        public static final h b;

        static {
            short s;
            h hVar = new h();
            b = hVar;
            hVar.a("Void");
            b.b("com.microsoft.bond.Void");
            o oVar = new o();
            a = oVar;
            q qVar = new q();
            qVar.a(a.BT_STRUCT);
            short s2 = (short) 0;
            while (true) {
                s = s2;
                if (s >= oVar.a().size()) {
                    p pVar = new p();
                    oVar.a().add(pVar);
                    pVar.a(b);
                    break;
                } else if (((p) oVar.a().get(s)).a() == b) {
                    break;
                } else {
                    s2 = (short) (s + 1);
                }
            }
            qVar.a(s);
            oVar.a(qVar);
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final void read(k reader) throws IOException {
        a(reader);
    }

    public static void a(k reader) throws IOException {
        if (reader.a(j.TAGGED)) {
            com.microsoft.a.k.a a;
            Object obj;
            while (true) {
                a = reader.a();
                if (a.b != a.BT_STOP && a.b != a.BT_STOP_BASE) {
                    reader.a(a.b);
                }
            }
            if (a.b == a.BT_STOP_BASE) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                b.a(reader);
            }
        }
    }

    public final void write(n writer) throws IOException {
        writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        writer.a(false);
    }
}
