package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import net.hockeyapp.android.c.c;

public final class j implements Serializable, g {
    private String a;
    private String b;
    private String c;

    public final void a(String value) {
        this.a = value;
    }

    public final void b(String value) {
        this.b = value;
    }

    public final void c(String value) {
        this.c = value;
    }

    public final void a(Map<String, String> map) {
        if (this.a != null) {
            map.put("ai.session.id", this.a);
        }
        if (this.b != null) {
            map.put("ai.session.isFirst", this.b);
        }
        if (this.c != null) {
            map.put("ai.session.isNew", this.c);
        }
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        String str = "";
        if (this.a != null) {
            writer.write(str + "\"ai.session.id\":");
            writer.write(c.a(this.a));
            str = ",";
        }
        if (this.b != null) {
            writer.write(str + "\"ai.session.isFirst\":");
            writer.write(c.a(this.b));
            str = ",";
        }
        if (this.c != null) {
            writer.write(str + "\"ai.session.isNew\":");
            writer.write(c.a(this.c));
        }
        writer.write(125);
    }
}
