package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import net.hockeyapp.android.c.c;

public final class d implements Serializable, g {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;

    public final void a(String value) {
        this.a = value;
    }

    public final void b(String value) {
        this.c = value;
    }

    public final void c(String value) {
        this.d = value;
    }

    public final String a() {
        return this.e;
    }

    public final void d(String value) {
        this.e = value;
    }

    public final void e(String value) {
        this.h = value;
    }

    public final void f(String value) {
        this.i = value;
    }

    public final void g(String value) {
        this.j = value;
    }

    public final void h(String value) {
        this.m = value;
    }

    public final void i(String value) {
        this.n = value;
    }

    public final void a(Map<String, String> map) {
        if (this.a != null) {
            map.put("ai.device.id", this.a);
        }
        if (this.b != null) {
            map.put("ai.device.ip", this.b);
        }
        if (this.c != null) {
            map.put("ai.device.language", this.c);
        }
        if (this.d != null) {
            map.put("ai.device.locale", this.d);
        }
        if (this.e != null) {
            map.put("ai.device.model", this.e);
        }
        if (this.f != null) {
            map.put("ai.device.network", this.f);
        }
        if (this.g != null) {
            map.put("ai.device.networkName", this.g);
        }
        if (this.h != null) {
            map.put("ai.device.oemName", this.h);
        }
        if (this.i != null) {
            map.put("ai.device.os", this.i);
        }
        if (this.j != null) {
            map.put("ai.device.osVersion", this.j);
        }
        if (this.k != null) {
            map.put("ai.device.roleInstance", this.k);
        }
        if (this.l != null) {
            map.put("ai.device.roleName", this.l);
        }
        if (this.m != null) {
            map.put("ai.device.screenResolution", this.m);
        }
        if (this.n != null) {
            map.put("ai.device.type", this.n);
        }
        if (this.o != null) {
            map.put("ai.device.machineName", this.o);
        }
        if (this.p != null) {
            map.put("ai.device.vmName", this.p);
        }
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        String str = "";
        if (this.a != null) {
            writer.write(str + "\"ai.device.id\":");
            writer.write(c.a(this.a));
            str = ",";
        }
        if (this.b != null) {
            writer.write(str + "\"ai.device.ip\":");
            writer.write(c.a(this.b));
            str = ",";
        }
        if (this.c != null) {
            writer.write(str + "\"ai.device.language\":");
            writer.write(c.a(this.c));
            str = ",";
        }
        if (this.d != null) {
            writer.write(str + "\"ai.device.locale\":");
            writer.write(c.a(this.d));
            str = ",";
        }
        if (this.e != null) {
            writer.write(str + "\"ai.device.model\":");
            writer.write(c.a(this.e));
            str = ",";
        }
        if (this.f != null) {
            writer.write(str + "\"ai.device.network\":");
            writer.write(c.a(this.f));
            str = ",";
        }
        if (this.g != null) {
            writer.write(str + "\"ai.device.networkName\":");
            writer.write(c.a(this.g));
            str = ",";
        }
        if (this.h != null) {
            writer.write(str + "\"ai.device.oemName\":");
            writer.write(c.a(this.h));
            str = ",";
        }
        if (this.i != null) {
            writer.write(str + "\"ai.device.os\":");
            writer.write(c.a(this.i));
            str = ",";
        }
        if (this.j != null) {
            writer.write(str + "\"ai.device.osVersion\":");
            writer.write(c.a(this.j));
            str = ",";
        }
        if (this.k != null) {
            writer.write(str + "\"ai.device.roleInstance\":");
            writer.write(c.a(this.k));
            str = ",";
        }
        if (this.l != null) {
            writer.write(str + "\"ai.device.roleName\":");
            writer.write(c.a(this.l));
            str = ",";
        }
        if (this.m != null) {
            writer.write(str + "\"ai.device.screenResolution\":");
            writer.write(c.a(this.m));
            str = ",";
        }
        if (this.n != null) {
            writer.write(str + "\"ai.device.type\":");
            writer.write(c.a(this.n));
            str = ",";
        }
        if (this.o != null) {
            writer.write(str + "\"ai.device.machineName\":");
            writer.write(c.a(this.o));
            str = ",";
        }
        if (this.p != null) {
            writer.write(str + "\"ai.device.vmName\":");
            writer.write(c.a(this.p));
        }
        writer.write(125);
    }
}
