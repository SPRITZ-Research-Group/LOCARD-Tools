package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import com.appboy.f.c;

public class ah {
    private static final String d = c.a(ah.class);
    @VisibleForTesting
    final SharedPreferences a;
    @VisibleForTesting
    final b b;
    @VisibleForTesting
    boolean c = false;
    private final ck e;

    public ah(Context context, b bVar, ck ckVar) {
        this.b = bVar;
        this.e = ckVar;
        this.a = context.getSharedPreferences("com.appboy.storage.sessions.messaging_session", 0);
    }

    final void a() {
        boolean z = false;
        long i = this.e.i();
        if (!(i == -1 || this.c)) {
            long j = this.a.getLong("messaging_session_timestamp", -1);
            long a = co.a();
            c.b(d, "Messaging session timeout: " + i + ", current diff: " + (a - j));
            if (i + j < a) {
                z = true;
            }
        }
        if (z) {
            c.b(d, "Publishing new messaging session event.");
            this.b.a(g.a, g.class);
            this.c = true;
            return;
        }
        c.b(d, "Messaging session not started.");
    }

    final void b() {
        long a = co.a();
        c.b(d, "Messaging session stopped. Adding new messaging session timestamp: " + a);
        this.a.edit().putLong("messaging_session_timestamp", a).apply();
        this.c = false;
    }
}
