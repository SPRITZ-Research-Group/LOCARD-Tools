package com.microsoft.a;

import com.microsoft.a.a.b;
import com.microsoft.a.k.c;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public final class h implements c, d {
    private String a = "";
    private String b = "";
    private HashMap<String, String> c;
    private i d;
    private r e;

    public static class a {
        public static final o a;
        public static final h b;
        private static final h c;
        private static final h d;
        private static final h e;
        private static final h f;
        private static final h g;

        static {
            h hVar = new h();
            b = hVar;
            hVar.a("Metadata");
            b.b("com.microsoft.bond.Metadata");
            hVar = new h();
            c = hVar;
            hVar.a("name");
            hVar = new h();
            d = hVar;
            hVar.a("qualified_name");
            hVar = new h();
            e = hVar;
            hVar.a("attributes");
            hVar = new h();
            f = hVar;
            hVar.a("modifier");
            f.a().a((long) i.Optional.a());
            hVar = new h();
            g = hVar;
            hVar.a("default_value");
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
            gVar.b().a(a.BT_STRING);
            pVar.b().add(gVar);
            g gVar2 = new g();
            gVar2.a((short) 1);
            gVar2.a(d);
            gVar2.b().a(a.BT_STRING);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 2);
            gVar2.a(e);
            gVar2.b().a(a.BT_MAP);
            gVar2.b().b(new q());
            gVar2.b().a(new q());
            gVar2.b().b().a(a.BT_STRING);
            gVar2.b().a().a(a.BT_STRING);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 3);
            gVar2.a(f);
            gVar2.b().a(a.BT_INT32);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 4);
            gVar2.a(g);
            gVar2.a(com.microsoft.a.r.a.a(schema));
            pVar.b().add(gVar2);
            type.a(s);
            return type;
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final void a(String value) {
        this.a = value;
    }

    public final void b(String value) {
        this.b = value;
    }

    public final r a() {
        return this.e;
    }

    public h() {
        if (this.c == null) {
            this.c = new HashMap();
        } else {
            this.c.clear();
        }
        this.d = i.Optional;
        this.e = new r();
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
                            this.a = b.b(reader, a.b);
                            break;
                        case 1:
                            this.b = b.b(reader, a.b);
                            break;
                        case 2:
                            a(reader, a.b);
                            break;
                        case 3:
                            this.d = i.a(b.e(reader, a.b));
                            break;
                        case 4:
                            b.a(a.b, a.BT_STRUCT);
                            this.e.a(reader);
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
        this.a = reader.e();
        this.b = reader.e();
        a(reader, a.BT_MAP);
        this.d = i.a(reader.o());
        this.e.read(reader);
    }

    private void a(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_MAP);
        c tag1 = reader.c();
        for (int i2 = 0; i2 < tag1.a; i2++) {
            this.c.put(b.b(reader, tag1.b), b.b(reader, tag1.c));
        }
    }

    public final void write(n writer) throws IOException {
        a(writer);
    }

    public final void a(n writer) throws IOException {
        a aVar;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        if (canOmitFields && this.a == a.c.e.e()) {
            aVar = a.BT_STRING;
            a.c;
        } else {
            aVar = a.BT_STRING;
            a.c;
            writer.a(aVar, 0);
            writer.a(this.a);
        }
        if (canOmitFields && this.b == a.d.e.e()) {
            aVar = a.BT_STRING;
            a.d;
        } else {
            aVar = a.BT_STRING;
            a.d;
            writer.a(aVar, 1);
            writer.a(this.b);
        }
        int size3 = this.c.size();
        if (canOmitFields && size3 == 0) {
            aVar = a.BT_MAP;
            a.e;
        } else {
            aVar = a.BT_MAP;
            a.e;
            writer.a(aVar, 2);
            writer.a(this.c.size(), a.BT_STRING, a.BT_STRING);
            for (Entry<String, String> e4 : this.c.entrySet()) {
                writer.a((String) e4.getKey());
                writer.a((String) e4.getValue());
            }
        }
        if (canOmitFields && ((long) this.d.a()) == a.f.e.c()) {
            aVar = a.BT_INT32;
            a.f;
        } else {
            aVar = a.BT_INT32;
            a.f;
            writer.a(aVar, 3);
            writer.a(this.d.a());
        }
        aVar = a.BT_STRUCT;
        a.g;
        writer.a(aVar, 4);
        this.e.a(writer);
        writer.a(false);
    }
}
