package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import net.hockeyapp.android.c.c;

public class b implements g {
    public LinkedHashMap<String, String> a = new LinkedHashMap();
    public String b;
    private String c;

    public b() {
        a();
    }

    public final void a(String value) {
        this.c = value;
    }

    public final void a(Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        b(writer);
        writer.write(125);
    }

    protected String b(Writer writer) throws IOException {
        String prefix = "";
        if (this.c == null) {
            return prefix;
        }
        writer.write(prefix + "\"baseType\":");
        writer.write(c.a(this.c));
        return ",";
    }

    protected void a() {
    }
}
