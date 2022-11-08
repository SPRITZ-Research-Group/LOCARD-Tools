package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.widget.TextView;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.b.n;
import java.util.concurrent.TimeUnit;

public class c extends com.facebook.ads.internal.view.f.a.c {
    private final TextView a;
    private final String b;
    private final f<n> c = new f<n>(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public final Class<n> a() {
            return n.class;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.a() != null) {
                this.a.a.setText(c.a(this.a, (long) (this.a.a().f() - this.a.a().d())));
            }
        }
    };

    public c(Context context, String str) {
        super(context);
        this.a = new TextView(context);
        this.b = str;
        addView(this.a);
    }

    static /* synthetic */ String a(c cVar, long j) {
        if (j <= 0) {
            return "00:00";
        }
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(j);
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(j % 60000);
        if (cVar.b.isEmpty()) {
            return String.format("%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(toSeconds)});
        }
        return cVar.b.replace("{{REMAINING_TIME}}", String.format("%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(toSeconds)}));
    }

    protected final void b() {
        super.b();
        if (a() != null) {
            a().a().a(this.c);
        }
    }

    protected final void c() {
        if (a() != null) {
            a().a().b(this.c);
        }
        super.c();
    }

    public void setCountdownTextColor(int i) {
        this.a.setTextColor(i);
    }
}
