package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Writer;

public final class c<TDomain extends e> extends b implements h {
    private TDomain c;

    public c() {
        this.b = "com.microsoft.telemetry.Data";
        this.a.put("Description", "Data struct to contain both B and C sections.");
    }

    public final TDomain b() {
        return this.c;
    }

    public final void a(TDomain value) {
        this.c = value;
    }

    protected final String b(Writer writer) throws IOException {
        writer.write(super.b(writer) + "\"baseData\":");
        net.hockeyapp.android.c.c.a(writer, this.c);
        return ",";
    }

    protected final void a() {
        this.b = "com.microsoft.telemetry.Data";
    }
}
