package com.microsoft.a;

import com.microsoft.a.a.b;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public final class p implements c, d {
    private h a = new h();
    private q b = null;
    private ArrayList<g> c;

    public static class a {
        public static final o a;
        public static final h b;
        private static final h c;
        private static final h d;
        private static final h e;

        static {
            h hVar = new h();
            b = hVar;
            hVar.a("StructDef");
            b.b("com.microsoft.bond.StructDef");
            hVar = new h();
            c = hVar;
            hVar.a("metadata");
            hVar = new h();
            d = hVar;
            hVar.a("base_def");
            hVar = new h();
            e = hVar;
            hVar.a("fields");
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
            gVar2.b().a(a.BT_LIST);
            gVar2.b().a(new q());
            gVar2.b().a(com.microsoft.a.q.a.a(schema));
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 2);
            gVar2.a(e);
            gVar2.b().a(a.BT_LIST);
            gVar2.b().a(new q());
            gVar2.b().a(com.microsoft.a.g.a.a(schema));
            pVar.b().add(gVar2);
            type.a(s);
            return type;
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final h a() {
        return this.a;
    }

    public final void a(h value) {
        this.a = value;
    }

    public final ArrayList<g> b() {
        return this.c;
    }

    public p() {
        if (this.c == null) {
            this.c = new ArrayList();
        } else {
            this.c.clear();
        }
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
                            a(reader, a.b);
                            break;
                        case 2:
                            b(reader, a.b);
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
        a(reader, a.BT_LIST);
        b(reader, a.BT_LIST);
    }

    private void a(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        if (tag1.a == 1) {
            if (this.b == null) {
                this.b = new q();
            }
            this.b.a(reader);
        }
    }

    private void b(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        this.c.ensureCapacity(tag1.a);
        for (int i3 = 0; i3 < tag1.a; i3++) {
            g element2 = new g();
            element2.a(reader);
            this.c.add(element2);
        }
    }

    public final void write(n writer) throws IOException {
        a(writer);
    }

    public final void a(n writer) throws IOException {
        int size1;
        a aVar;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        a aVar2 = a.BT_STRUCT;
        a.c;
        writer.a(aVar2, 0);
        this.a.a(writer);
        if (this.b != null) {
            size1 = 1;
        } else {
            size1 = 0;
        }
        if (canOmitFields && size1 == 0) {
            aVar = a.BT_LIST;
            a.d;
        } else {
            aVar2 = a.BT_LIST;
            a.d;
            writer.a(aVar2, 1);
            writer.a(size1, a.BT_STRUCT);
            if (size1 != 0) {
                this.b.a(writer);
            }
        }
        int size2 = this.c.size();
        if (canOmitFields && size2 == 0) {
            aVar = a.BT_LIST;
            a.e;
        } else {
            aVar = a.BT_LIST;
            a.e;
            writer.a(aVar, 2);
            writer.a(size2, a.BT_STRUCT);
            Iterator i$ = this.c.iterator();
            while (i$.hasNext()) {
                ((g) i$.next()).a(writer);
            }
        }
        writer.a(false);
    }
}
