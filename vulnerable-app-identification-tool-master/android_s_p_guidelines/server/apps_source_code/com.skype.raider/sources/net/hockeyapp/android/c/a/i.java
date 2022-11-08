package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import net.hockeyapp.android.c.c;

public final class i implements Serializable, g {
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

    public final void a(String value) {
        this.a = value;
    }

    public final void a(Map<String, String> map) {
        if (this.a != null) {
            map.put("ai.internal.sdkVersion", this.a);
        }
        if (this.b != null) {
            map.put("ai.internal.agentVersion", this.b);
        }
        if (this.c != null) {
            map.put("ai.internal.dataCollectorReceivedTime", this.c);
        }
        if (this.d != null) {
            map.put("ai.internal.profileId", this.d);
        }
        if (this.e != null) {
            map.put("ai.internal.profileClassId", this.e);
        }
        if (this.f != null) {
            map.put("ai.internal.accountId", this.f);
        }
        if (this.g != null) {
            map.put("ai.internal.applicationName", this.g);
        }
        if (this.h != null) {
            map.put("ai.internal.instrumentationKey", this.h);
        }
        if (this.i != null) {
            map.put("ai.internal.telemetryItemId", this.i);
        }
        if (this.j != null) {
            map.put("ai.internal.applicationType", this.j);
        }
        if (this.k != null) {
            map.put("ai.internal.requestSource", this.k);
        }
        if (this.l != null) {
            map.put("ai.internal.flowType", this.l);
        }
        if (this.m != null) {
            map.put("ai.internal.isAudit", this.m);
        }
        if (this.n != null) {
            map.put("ai.internal.trackingSourceId", this.n);
        }
        if (this.o != null) {
            map.put("ai.internal.trackingType", this.o);
        }
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        String str = "";
        if (this.a != null) {
            writer.write(str + "\"ai.internal.sdkVersion\":");
            writer.write(c.a(this.a));
            str = ",";
        }
        if (this.b != null) {
            writer.write(str + "\"ai.internal.agentVersion\":");
            writer.write(c.a(this.b));
            str = ",";
        }
        if (this.c != null) {
            writer.write(str + "\"ai.internal.dataCollectorReceivedTime\":");
            writer.write(c.a(this.c));
            str = ",";
        }
        if (this.d != null) {
            writer.write(str + "\"ai.internal.profileId\":");
            writer.write(c.a(this.d));
            str = ",";
        }
        if (this.e != null) {
            writer.write(str + "\"ai.internal.profileClassId\":");
            writer.write(c.a(this.e));
            str = ",";
        }
        if (this.f != null) {
            writer.write(str + "\"ai.internal.accountId\":");
            writer.write(c.a(this.f));
            str = ",";
        }
        if (this.g != null) {
            writer.write(str + "\"ai.internal.applicationName\":");
            writer.write(c.a(this.g));
            str = ",";
        }
        if (this.h != null) {
            writer.write(str + "\"ai.internal.instrumentationKey\":");
            writer.write(c.a(this.h));
            str = ",";
        }
        if (this.i != null) {
            writer.write(str + "\"ai.internal.telemetryItemId\":");
            writer.write(c.a(this.i));
            str = ",";
        }
        if (this.j != null) {
            writer.write(str + "\"ai.internal.applicationType\":");
            writer.write(c.a(this.j));
            str = ",";
        }
        if (this.k != null) {
            writer.write(str + "\"ai.internal.requestSource\":");
            writer.write(c.a(this.k));
            str = ",";
        }
        if (this.l != null) {
            writer.write(str + "\"ai.internal.flowType\":");
            writer.write(c.a(this.l));
            str = ",";
        }
        if (this.m != null) {
            writer.write(str + "\"ai.internal.isAudit\":");
            writer.write(c.a(this.m));
            str = ",";
        }
        if (this.n != null) {
            writer.write(str + "\"ai.internal.trackingSourceId\":");
            writer.write(c.a(this.n));
            str = ",";
        }
        if (this.o != null) {
            writer.write(str + "\"ai.internal.trackingType\":");
            writer.write(c.a(this.o));
        }
        writer.write(125);
    }
}
