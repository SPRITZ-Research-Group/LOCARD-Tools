package com.microsoft.a;

import com.microsoft.a.a.b;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.io.IOException;

public final class q implements c, d {
    private a a = a.BT_STRUCT;
    private short b = (short) 0;
    private q c = null;
    private q d = null;
    private boolean e = false;

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
            hVar.a("TypeDef");
            b.b("com.microsoft.bond.TypeDef");
            hVar = new h();
            c = hVar;
            hVar.a("id");
            c.a().a((long) a.BT_STRUCT.a());
            hVar = new h();
            d = hVar;
            hVar.a("struct_def");
            d.a().b();
            hVar = new h();
            e = hVar;
            hVar.a("element");
            hVar = new h();
            f = hVar;
            hVar.a(PropertiesEntry.COLUMN_NAME_KEY);
            hVar = new h();
            g = hVar;
            hVar.a("bonded_type");
            g.a().b();
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
            gVar.b().a(a.BT_INT32);
            pVar.b().add(gVar);
            g gVar2 = new g();
            gVar2.a((short) 1);
            gVar2.a(d);
            gVar2.b().a(a.BT_UINT16);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 2);
            gVar2.a(e);
            gVar2.b().a(a.BT_LIST);
            gVar2.b().a(new q());
            gVar2.b().a(a(schema));
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 3);
            gVar2.a(f);
            gVar2.b().a(a.BT_LIST);
            gVar2.b().a(new q());
            gVar2.b().a(a(schema));
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 4);
            gVar2.a(g);
            gVar2.b().a(a.BT_BOOL);
            pVar.b().add(gVar2);
            type.a(s);
            return type;
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final void a(a value) {
        this.a = value;
    }

    public final void a(short value) {
        this.b = value;
    }

    public final q a() {
        return this.c;
    }

    public final void a(q value) {
        this.c = value;
    }

    public final q b() {
        return this.d;
    }

    public final void b(q value) {
        this.d = value;
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
                            this.a = a.a(b.e(reader, a.b));
                            break;
                        case 1:
                            this.b = b.d(reader, a.b);
                            break;
                        case 2:
                            a(reader, a.b);
                            break;
                        case 3:
                            b(reader, a.b);
                            break;
                        case 4:
                            this.e = b.a(reader, a.b);
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
        this.a = a.a(reader.o());
        this.b = reader.j();
        a(reader, a.BT_LIST);
        b(reader, a.BT_LIST);
        this.e = reader.d();
    }

    private void a(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        if (tag1.a == 1) {
            if (this.c == null) {
                this.c = new q();
            }
            this.c.a(reader);
        }
    }

    private void b(k reader, a typeInPayload) throws IOException {
        b.a(typeInPayload, a.BT_LIST);
        k.b tag1 = reader.b();
        b.a(tag1.b, a.BT_STRUCT);
        if (tag1.a == 1) {
            if (this.d == null) {
                this.d = new q();
            }
            this.d.a(reader);
        }
    }

    public final void write(n writer) throws IOException {
        a(writer);
    }

    public final void a(n writer) throws IOException {
        a aVar;
        int size3;
        int size4;
        a aVar2;
        boolean z = true;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        if (canOmitFields && ((long) this.a.a()) == a.c.a().c()) {
            aVar = a.BT_INT32;
            a.c;
        } else {
            aVar = a.BT_INT32;
            a.c;
            writer.a(aVar, 0);
            writer.a(this.a.a());
        }
        if (canOmitFields && ((long) this.b) == a.d.a().a()) {
            aVar = a.BT_UINT16;
            a.d;
        } else {
            aVar = a.BT_UINT16;
            a.d;
            writer.a(aVar, 1);
            writer.a(this.b);
        }
        if (this.c != null) {
            size3 = 1;
        } else {
            size3 = 0;
        }
        if (canOmitFields && size3 == 0) {
            aVar = a.BT_LIST;
            a.e;
        } else {
            aVar = a.BT_LIST;
            a.e;
            writer.a(aVar, 2);
            writer.a(size3, a.BT_STRUCT);
            if (size3 != 0) {
                this.c.a(writer);
            }
        }
        if (this.d != null) {
            size4 = 1;
        } else {
            size4 = 0;
        }
        if (canOmitFields && size4 == 0) {
            aVar = a.BT_LIST;
            a.f;
        } else {
            aVar = a.BT_LIST;
            a.f;
            writer.a(aVar, 3);
            writer.a(size4, a.BT_STRUCT);
            if (size4 != 0) {
                this.d.a(writer);
            }
        }
        if (canOmitFields) {
            boolean z2 = this.e;
            if (a.g.a().a() == 0) {
                z = false;
            }
            if (z2 == z) {
                aVar2 = a.BT_BOOL;
                a.g;
                writer.a(false);
            }
        }
        aVar2 = a.BT_BOOL;
        a.g;
        writer.a(aVar2, 4);
        writer.b(this.e);
        writer.a(false);
    }
}
