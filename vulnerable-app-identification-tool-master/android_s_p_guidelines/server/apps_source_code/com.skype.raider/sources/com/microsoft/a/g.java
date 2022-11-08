package com.microsoft.a;

import com.microsoft.a.a.b;
import java.io.IOException;

public final class g implements c, d {
    private h a = new h();
    private short b = (short) 0;
    private q c = new q();

    public static class a {
        public static final o a;
        public static final h b;
        private static final h c;
        private static final h d;
        private static final h e;

        static {
            h hVar = new h();
            b = hVar;
            hVar.a("FieldDef");
            b.b("com.microsoft.bond.FieldDef");
            hVar = new h();
            c = hVar;
            hVar.a("metadata");
            hVar = new h();
            d = hVar;
            hVar.a("id");
            d.a().b();
            hVar = new h();
            e = hVar;
            hVar.a("type");
            o oVar = new o();
            a = oVar;
            oVar.a(a(oVar));
        }

        public static q a(o schema) {
            q type = new q();
            type.a(a.BT_STRUCT);
            short s = (short) 0;
            while (s < schema.a().size()) {
                if (((p) schema.a().get(s)).a() == b) {
                    break;
                }
                s = (short) (s + 1);
            }
            p pVar = new p();
            schema.a().add(pVar);
            pVar.a(b);
            g gVar = new g();
            gVar.a((short) 0);
            gVar.a(c);
            gVar.a(com.microsoft.a.h.a.a(schema));
            pVar.b().add(gVar);
            g gVar2 = new g();
            gVar2.a((short) 1);
            gVar2.a(d);
            gVar2.b().a(a.BT_UINT16);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 2);
            gVar2.a(e);
            gVar2.a(com.microsoft.a.q.a.a(schema));
            pVar.b().add(gVar2);
            type.a(s);
            return type;
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final void a(h value) {
        this.a = value;
    }

    public final short a() {
        return this.b;
    }

    public final void a(short value) {
        this.b = value;
    }

    public final q b() {
        return this.c;
    }

    public final void a(q value) {
        this.c = value;
    }

    public final void read(k reader) throws IOException {
        a(reader);
    }

    public final void a(k reader) throws IOException {
        if (reader.a(j.TAGGED)) {
            com.microsoft.a.k.a a;
            Object obj;
            while (true) {
                a = reader.a();
                if (a.b != a.BT_STOP && a.b != a.BT_STOP_BASE) {
                    switch (a.a) {
                        case 0:
                            b.a(a.b, a.BT_STRUCT);
                            this.a.a(reader);
                            break;
                        case 1:
                            this.b = b.d(reader, a.b);
                            break;
                        case 2:
                            b.a(a.b, a.BT_STRUCT);
                            this.c.a(reader);
                            break;
                        default:
                            reader.a(a.b);
                            break;
                    }
                }
            }
            if (a.b == a.BT_STOP_BASE) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                b.a(reader);
                return;
            }
            return;
        }
        reader.a(j.CAN_OMIT_FIELDS);
        this.a.read(reader);
        this.b = reader.j();
        this.c.read(reader);
    }

    public final void write(n writer) throws IOException {
        a(writer);
    }

    public final void a(n writer) throws IOException {
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        a aVar = a.BT_STRUCT;
        a.c;
        writer.a(aVar, 0);
        this.a.a(writer);
        if (canOmitFields && ((long) this.b) == a.d.a().a()) {
            aVar = a.BT_UINT16;
            a.d;
        } else {
            aVar = a.BT_UINT16;
            a.d;
            writer.a(aVar, 1);
            writer.a(this.b);
        }
        aVar = a.BT_STRUCT;
        a.e;
        writer.a(aVar, 2);
        this.c.a(writer);
        writer.a(false);
    }
}
