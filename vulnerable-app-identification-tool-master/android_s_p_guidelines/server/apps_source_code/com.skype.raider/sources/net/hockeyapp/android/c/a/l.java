package net.hockeyapp.android.c.a;

import java.io.IOException;
import java.io.Writer;
import net.hockeyapp.android.c.c;

public final class l extends m {
    private int c;
    private k d;

    public l() {
        this.c = 2;
        this.d = k.START;
        this.b = "com.microsoft.applicationinsights.contracts.SessionStateData";
    }

    public final String a() {
        return "Microsoft.ApplicationInsights.SessionState";
    }

    public final String b() {
        return "SessionStateData";
    }

    public final void a(k value) {
        this.d = value;
    }

    protected final String b(Writer writer) throws IOException {
        writer.write(super.b(writer) + "\"ver\":");
        writer.write(c.a(Integer.valueOf(this.c)));
        writer.write("," + "\"state\":");
        writer.write(c.a(Integer.valueOf(this.d.a())));
        return ",";
    }

    protected final void c() {
        this.b = "com.microsoft.applicationinsights.contracts.SessionStateData";
    }
}
