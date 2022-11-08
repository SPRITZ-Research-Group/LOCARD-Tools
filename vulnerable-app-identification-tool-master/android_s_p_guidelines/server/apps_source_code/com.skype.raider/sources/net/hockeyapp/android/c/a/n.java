package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import net.hockeyapp.android.c.c;

public final class n implements Serializable, g {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public final void a(String value) {
        this.d = value;
    }

    public final void a(Map<String, String> map) {
        if (this.a != null) {
            map.put("ai.user.accountAcquisitionDate", this.a);
        }
        if (this.b != null) {
            map.put("ai.user.accountId", this.b);
        }
        if (this.c != null) {
            map.put("ai.user.userAgent", this.c);
        }
        if (this.d != null) {
            map.put("ai.user.id", this.d);
        }
        if (this.e != null) {
            map.put("ai.user.storeRegion", this.e);
        }
        if (this.f != null) {
            map.put("ai.user.authUserId", this.f);
        }
        if (this.g != null) {
            map.put("ai.user.anonUserAcquisitionDate", this.g);
        }
        if (this.h != null) {
            map.put("ai.user.authUserAcquisitionDate", this.h);
        }
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        String str = "";
        if (this.a != null) {
            writer.write(str + "\"ai.user.accountAcquisitionDate\":");
            writer.write(c.a(this.a));
            str = ",";
        }
        if (this.b != null) {
            writer.write(str + "\"ai.user.accountId\":");
            writer.write(c.a(this.b));
            str = ",";
        }
        if (this.c != null) {
            writer.write(str + "\"ai.user.userAgent\":");
            writer.write(c.a(this.c));
            str = ",";
        }
        if (this.d != null) {
            writer.write(str + "\"ai.user.id\":");
            writer.write(c.a(this.d));
            str = ",";
        }
        if (this.e != null) {
            writer.write(str + "\"ai.user.storeRegion\":");
            writer.write(c.a(this.e));
            str = ",";
        }
        if (this.f != null) {
            writer.write(str + "\"ai.user.authUserId\":");
            writer.write(c.a(this.f));
            str = ",";
        }
        if (this.g != null) {
            writer.write(str + "\"ai.user.anonUserAcquisitionDate\":");
            writer.write(c.a(this.g));
            str = ",";
        }
        if (this.h != null) {
            writer.write(str + "\"ai.user.authUserAcquisitionDate\":");
            writer.write(c.a(this.h));
        }
        writer.write(125);
    }
}
