package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class e implements g {
    public LinkedHashMap<String, String> a = new LinkedHashMap();
    public String b;

    public e() {
        c();
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
        return "";
    }

    protected void c() {
    }
}
