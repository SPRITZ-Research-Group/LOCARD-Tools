package com.microsoft.a;

import com.microsoft.a.a.b;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public final class o implements c, d {
    private ArrayList<p> a;
    private q b;

    public static class a {
        public static final o a;
        public static final h b;
        private static final h c;
        private static final h d;

        static {
            h hVar = new h();
            b = hVar;
            hVar.a("SchemaDef");
            b.b("com.microsoft.bond.SchemaDef");
            hVar = new h();
            c = hVar;
            hVar.a("structs");
            hVar = new h();
            d = hVar;
            hVar.a("root");
            o oVar = new o();
            a = oVar;
            q qVar = new q();
            qVar.a(a.BT_STRUCT);
            short s = (short) 0;
            while (s < oVar.a().size()) {
                if (((p) oVar.a().get(s)).a() == b) {
                    break;
                }
                s = (short) (s + 1);
            }
            p pVar = new p();
            oVar.a().add(pVar);
            pVar.a(b);
            g gVar = new g();
            gVar.a((short) 0);
            gVar.a(c);
            gVar.b().a(a.BT_LIST);
            gVar.b().a(new q());
            gVar.b().a(com.microsoft.a.p.a.a(oVar));
            pVar.b().add(gVar);
            g gVar2 = new g();
            gVar2.a((short) 1);
            gVar2.a(d);
            gVar2.a(com.microsoft.a.q.a.a(oVar));
            pVar.b().add(gVar2);
            qVar.a(s);
            oVar.a(qVar);
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final ArrayList<p> a() {
        return this.a;
    }

    public final void a(q value) {
        this.b = value;
    }

    public o() {
        if (this.a == null) {
            this.a = new ArrayList();
        } else {
            this.a.clear();
        }
        this.b = new q();
    }

    private void a(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        this.a.ensureCapacity(tag1.a);
        for (int i3 = 0; i3 < tag1.a; i3++) {
            p element2 = new p();
            element2.a(reader);
            this.a.add(element2);
        }
    }

    public final void write(n writer) throws IOException {
        a aVar;
        boolean a = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        int size = this.a.size();
        if (a && size == 0) {
            aVar = a.BT_LIST;
            a.c;
        } else {
            aVar = a.BT_LIST;
            a.c;
            writer.a(aVar, 0);
            writer.a(size, a.BT_STRUCT);
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((p) it.next()).a(writer);
            }
        }
        aVar = a.BT_STRUCT;
        a.d;
        writer.a(aVar, 1);
        this.b.a(writer);
        writer.a(false);
    }

    public final void read(k reader) throws IOException {
        if (reader.a(j.TAGGED)) {
            com.microsoft.a.k.a a;
            Object obj;
            while (true) {
                a = reader.a();
                if (a.b != a.BT_STOP && a.b != a.BT_STOP_BASE) {
                    switch (a.a) {
                        case 0:
                            a(reader, a.b);
                            break;
                        case 1:
                            b.a(a.b, a.BT_STRUCT);
                            this.b.a(reader);
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
        a(reader, a.BT_LIST);
        this.b.read(reader);
    }
}
