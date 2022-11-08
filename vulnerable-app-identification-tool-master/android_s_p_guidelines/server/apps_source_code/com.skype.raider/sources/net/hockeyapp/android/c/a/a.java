package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import net.hockeyapp.android.c.c;

public final class a implements Serializable, g {
    private String a;
    private String b;
    private String c;

    public final void a(String value) {
        this.a = value;
    }

    public final void a(Map<String, String> map) {
        if (this.a != null) {
            map.put("ai.application.ver", this.a);
        }
        if (this.b != null) {
            map.put("ai.application.build", this.b);
        }
        if (this.c != null) {
            map.put("ai.application.typeId", this.c);
        }
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        String str = "";
        if (this.a != null) {
            writer.write(str + "\"ai.application.ver\":");
            writer.write(c.a(this.a));
            str = ",";
        }
        if (this.b != null) {
            writer.write(str + "\"ai.application.build\":");
            writer.write(c.a(this.b));
            str = ",";
        }
        if (this.c != null) {
            writer.write(str + "\"ai.application.typeId\":");
            writer.write(c.a(this.c));
        }
        writer.write(125);
    }
}
