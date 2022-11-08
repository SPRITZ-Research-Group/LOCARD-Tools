package net.hockeyapp.android.c;

import com.microsoft.applications.telemetry.LogConfiguration;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import net.hockeyapp.android.c.a.b;
import net.hockeyapp.android.c.a.c;
import net.hockeyapp.android.c.a.f;
import net.hockeyapp.android.c.a.m;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.f.j;

final class a {
    protected final List<String> a = new LinkedList();
    protected final g b;
    private final e c;
    private final Timer d;
    private a e;

    private class a extends TimerTask {
        final /* synthetic */ a a;

        a(a aVar) {
            this.a = aVar;
        }

        public final void run() {
            this.a.a();
        }
    }

    public a(g telemetryContext, e persistence) {
        this.b = telemetryContext;
        this.c = persistence;
        this.d = new Timer("HockeyApp User Metrics Sender Queue", true);
    }

    private synchronized void a(String serializedItem) {
        if (serializedItem != null) {
            if (this.a.add(serializedItem)) {
                int i;
                int size = this.a.size();
                if (j.b()) {
                    i = 5;
                } else {
                    i = 50;
                }
                if (size >= i) {
                    a();
                } else if (this.a.size() == 1) {
                    this.e = new a(this);
                    Timer timer = this.d;
                    TimerTask timerTask = this.e;
                    if (j.b()) {
                        i = LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS;
                    } else {
                        i = 15000;
                    }
                    timer.schedule(timerTask, (long) i);
                }
            } else {
                e.a("HockeyApp-Metrics");
            }
        }
    }

    protected final void a() {
        if (this.e != null) {
            this.e.cancel();
        }
        String[] data = null;
        synchronized (this) {
            if (!this.a.isEmpty()) {
                data = new String[this.a.size()];
                this.a.toArray(data);
                this.a.clear();
            }
        }
        if (this.c != null && data != null) {
            this.c.a(data);
        }
    }

    public final void a(b data) {
        f envelope = null;
        try {
            data = (c) data;
            f envelope2 = new f();
            envelope2.a(data);
            net.hockeyapp.android.c.a.e b = data.b();
            if (b instanceof m) {
                envelope2.a(((m) b).a());
            }
            this.b.a();
            envelope2.b(j.a(new Date()));
            envelope2.c(this.b.c());
            envelope2.a(this.b.b());
            envelope = envelope2;
        } catch (ClassCastException e) {
            e.b("HockeyApp-Metrics");
        }
        if (envelope != null) {
            a(a(envelope));
            new StringBuilder("enqueued telemetry: ").append(envelope.a());
            e.b("HockeyApp-Metrics");
        }
    }

    private static String a(f envelope) {
        try {
            Writer stringWriter = new StringWriter();
            envelope.a(stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            new StringBuilder("Failed to save data with exception: ").append(e.toString());
            e.b("HockeyApp-Metrics");
            return null;
        }
    }
}
