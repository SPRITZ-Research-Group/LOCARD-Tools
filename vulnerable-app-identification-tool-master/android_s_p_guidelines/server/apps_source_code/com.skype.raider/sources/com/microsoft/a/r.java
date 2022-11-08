package com.microsoft.a;

import com.microsoft.a.a.b;
import java.io.IOException;

public final class r implements c, d {
    private long a = 0;
    private long b = 0;
    private double c = 0.0d;
    private String d = "";
    private String e = "";
    private boolean f = false;

    public static class a {
        public static final o a;
        public static final h b;
        private static final h c;
        private static final h d;
        private static final h e;
        private static final h f;
        private static final h g;
        private static final h h;

        static {
            h hVar = new h();
            b = hVar;
            hVar.a("Variant");
            b.b("com.microsoft.bond.Variant");
            hVar = new h();
            c = hVar;
            hVar.a("uint_value");
            c.a().b();
            hVar = new h();
            d = hVar;
            hVar.a("int_value");
            d.a().a(0);
            hVar = new h();
            e = hVar;
            hVar.a("double_value");
            e.a().d();
            hVar = new h();
            f = hVar;
            hVar.a("string_value");
            hVar = new h();
            g = hVar;
            hVar.a("wstring_value");
            hVar = new h();
            h = hVar;
            hVar.a("nothing");
            h.a().b();
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
            gVar.b().a(a.BT_UINT64);
            pVar.b().add(gVar);
            g gVar2 = new g();
            gVar2.a((short) 1);
            gVar2.a(d);
            gVar2.b().a(a.BT_INT64);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 2);
            gVar2.a(e);
            gVar2.b().a(a.BT_DOUBLE);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 3);
            gVar2.a(f);
            gVar2.b().a(a.BT_STRING);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 4);
            gVar2.a(g);
            gVar2.b().a(a.BT_WSTRING);
            pVar.b().add(gVar2);
            gVar2 = new g();
            gVar2.a((short) 5);
            gVar2.a(h);
            gVar2.b().a(a.BT_BOOL);
            pVar.b().add(gVar2);
            type.a(s);
            return type;
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return null;
    }

    public final long a() {
        return this.a;
    }

    public final void b() {
        this.a = 0;
    }

    public final long c() {
        return this.b;
    }

    public final void a(long value) {
        this.b = value;
    }

    public final void d() {
        this.c = 0.0d;
    }

    public final String e() {
        return this.d;
    }

    public final void f() {
        this.f = true;
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
                            long l;
                            a aVar = a.b;
                            if (aVar == a.BT_UINT64 || aVar == a.BT_UNAVAILABLE) {
                                l = reader.l();
                            } else if (aVar == a.BT_UINT32) {
                                l = (long) reader.k();
                            } else if (aVar == a.BT_UINT16) {
                                l = (long) reader.j();
                            } else if (aVar == a.BT_UINT8) {
                                l = (long) reader.i();
                            } else {
                                aVar = a.BT_UINT64;
                                l = 0;
                            }
                            this.a = l;
                            break;
                        case 1:
                            this.b = b.f(reader, a.b);
                            break;
                        case 2:
                            this.c = b.c(reader, a.b);
                            break;
                        case 3:
                            this.d = b.b(reader, a.b);
                            break;
                        case 4:
                            b.a(a.b, a.BT_WSTRING);
                            this.e = reader.f();
                            break;
                        case 5:
                            this.f = b.a(reader, a.b);
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
        this.a = reader.l();
        this.b = reader.p();
        this.c = reader.h();
        this.d = reader.e();
        this.e = reader.f();
        this.f = reader.d();
    }

    public final void write(n writer) throws IOException {
        a(writer);
    }

    public final void a(n writer) throws IOException {
        a aVar;
        a aVar2;
        boolean z = true;
        boolean canOmitFields = writer.a(j.CAN_OMIT_FIELDS);
        h hVar = a.b;
        if (canOmitFields && this.a == a.c.a().a) {
            aVar = a.BT_UINT64;
            a.c;
        } else {
            aVar = a.BT_UINT64;
            a.c;
            writer.a(aVar, 0);
            writer.a(this.a);
        }
        if (canOmitFields && this.b == a.d.a().b) {
            aVar = a.BT_INT64;
            a.d;
        } else {
            aVar = a.BT_INT64;
            a.d;
            writer.a(aVar, 1);
            writer.b(this.b);
        }
        if (canOmitFields && this.c == a.e.a().c) {
            aVar = a.BT_DOUBLE;
            a.e;
        } else {
            aVar = a.BT_DOUBLE;
            a.e;
            writer.a(aVar, 2);
            writer.a(this.c);
        }
        if (canOmitFields && this.d == a.f.a().d) {
            aVar = a.BT_STRING;
            a.f;
        } else {
            aVar = a.BT_STRING;
            a.f;
            writer.a(aVar, 3);
            writer.a(this.d);
        }
        if (canOmitFields && this.e == a.g.a().e) {
            aVar = a.BT_WSTRING;
            a.g;
        } else {
            aVar = a.BT_WSTRING;
            a.g;
            writer.a(aVar, 4);
            writer.b(this.e);
        }
        if (canOmitFields) {
            boolean z2 = this.f;
            if (a.h.a().a == 0) {
                z = false;
            }
            if (z2 == z) {
                aVar2 = a.BT_BOOL;
                a.h;
                writer.a(false);
            }
        }
        aVar2 = a.BT_BOOL;
        a.h;
        writer.a(aVar2, 5);
        writer.b(this.f);
        writer.a(false);
    }
}
