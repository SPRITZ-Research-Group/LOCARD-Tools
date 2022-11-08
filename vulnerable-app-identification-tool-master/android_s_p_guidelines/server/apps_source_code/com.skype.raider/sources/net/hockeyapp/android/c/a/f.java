package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import net.hockeyapp.android.c.c;

public final class f implements g {
    private int a = 1;
    private String b;
    private String c;
    private int d = 100;
    private String e;
    private long f;
    private String g;
    private long h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private Map<String, String> n;
    private Map<String, Object> o;
    private b p;

    public final String a() {
        return this.b;
    }

    public final void a(String value) {
        this.b = value;
    }

    public final void b(String value) {
        this.c = value;
    }

    public final void c(String value) {
        this.g = value;
    }

    public final void a(Map<String, String> value) {
        this.n = value;
    }

    public final void a(b value) {
        this.p = value;
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        writer.write("" + "\"ver\":");
        writer.write(c.a(Integer.valueOf(this.a)));
        writer.write("," + "\"name\":");
        writer.write(c.a(this.b));
        writer.write("," + "\"time\":");
        writer.write(c.a(this.c));
        String str = ",";
        if (((double) this.d) > 0.0d) {
            writer.write(str + "\"sampleRate\":");
            writer.write(c.a(Integer.valueOf(this.d)));
            str = ",";
        }
        if (this.e != null) {
            writer.write(str + "\"epoch\":");
            writer.write(c.a(this.e));
            str = ",";
        }
        if (this.f != 0) {
            writer.write(str + "\"seqNum\":");
            writer.write(c.a(Long.valueOf(this.f)));
            str = ",";
        }
        if (this.g != null) {
            writer.write(str + "\"iKey\":");
            writer.write(c.a(this.g));
            str = ",";
        }
        if (this.h != 0) {
            writer.write(str + "\"flags\":");
            writer.write(c.a(Long.valueOf(this.h)));
            str = ",";
        }
        if (this.i != null) {
            writer.write(str + "\"os\":");
            writer.write(c.a(this.i));
            str = ",";
        }
        if (this.j != null) {
            writer.write(str + "\"osVer\":");
            writer.write(c.a(this.j));
            str = ",";
        }
        if (this.k != null) {
            writer.write(str + "\"appId\":");
            writer.write(c.a(this.k));
            str = ",";
        }
        if (this.l != null) {
            writer.write(str + "\"appVer\":");
            writer.write(c.a(this.l));
            str = ",";
        }
        if (this.m != null) {
            writer.write(str + "\"cV\":");
            writer.write(c.a(this.m));
            str = ",";
        }
        if (this.n != null) {
            writer.write(str + "\"tags\":");
            c.a(writer, this.n);
            str = ",";
        }
        if (this.o != null) {
            writer.write(str + "\"ext\":");
            c.a(writer, this.o);
            str = ",";
        }
        if (this.p != null) {
            writer.write(str + "\"data\":");
            c.a(writer, this.p);
        }
        writer.write(125);
    }
}
