package com.skypecam.obscura.b;

import com.skypecam.obscura.e.a;
import com.skypecam.obscura.e.b;
import com.skypecam.obscura.e.d;
import com.skypecam.obscura.e.g;
import java.io.IOException;
import java.util.EnumSet;
import java.util.UUID;
import java.util.concurrent.Executor;

final class j implements q {
    final UUID a;
    private final Runnable b;
    private final Executor c;
    private boolean d = false;
    private final a<d> e;
    private final b<Throwable, com.skypecam.obscura.d.b> f;

    j(a<d> success, b<Throwable, com.skypecam.obscura.d.b> failure, Runnable cleanup, Executor executor) {
        this.e = success;
        this.f = failure;
        this.b = cleanup;
        this.c = executor;
        this.a = UUID.randomUUID();
    }

    public final boolean a(final Object feedback) {
        g.a().b("CaptureStill", "CaptureStill feedback " + feedback.getClass());
        if (feedback.equals(this.a)) {
            g.a().d("CaptureStill", "CaptureStill TIMEOUT");
            a();
            this.f.a(new Throwable("CaptureStill TIMEOUT"), com.skypecam.obscura.d.b.STILL_TIMEOUT);
            return true;
        }
        if (feedback instanceof g) {
            boolean z;
            if (feedback == g.CAPTURABLE) {
                z = true;
            } else {
                z = false;
            }
            this.d = z;
            if (!EnumSet.of(g.CAPTURABLE, g.FOCUSABLE, g.LIGHTABLE, g.LIT).contains(feedback)) {
                g.a().b("CaptureStill", "CaptureStill dropping listener");
                return true;
            }
        }
        if (!this.d || !(feedback instanceof d)) {
            return false;
        }
        a();
        g.a().b("CaptureStill", "CaptureStill feedback result");
        this.c.execute(new Runnable(this) {
            final /* synthetic */ j b;

            public final void run() {
                try {
                    ((d) feedback).a();
                    this.b.e.a((d) feedback);
                } catch (IOException exception) {
                    this.b.f.a(exception, com.skypecam.obscura.d.b.IMAGE_PROCESSING);
                }
            }
        });
        return true;
    }

    private void a() {
        this.b.run();
        this.d = false;
    }

    public final boolean b(Object feedback) {
        return false;
    }
}
