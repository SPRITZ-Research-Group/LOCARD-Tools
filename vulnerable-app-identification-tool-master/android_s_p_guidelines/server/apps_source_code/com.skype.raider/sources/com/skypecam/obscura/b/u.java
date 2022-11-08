package com.skypecam.obscura.b;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import com.skypecam.obscura.e.g;
import java.util.concurrent.atomic.AtomicBoolean;

final class u {
    private final Runnable a;
    private final Runnable b;
    private final Handler c;
    private AtomicBoolean d = new AtomicBoolean(false);
    private final Camera e;
    private final Runnable f = new Runnable(this) {
        final /* synthetic */ u a;

        {
            this.a = this$0;
        }

        public final void run() {
            g.a().b("LenientAutoFocuser", "LenientAutoFocuser timeout");
            if (!this.a.d.getAndSet(true)) {
                g.a().b("LenientAutoFocuser", "LenientAutoFocuser timeout acknowledge FOCUS");
                this.a.b.run();
                this.a.a.run();
            }
        }
    };

    u(Handler handler, Camera camera, Runnable focused, Runnable expired) {
        this.e = camera;
        this.a = focused;
        this.b = expired;
        this.c = handler;
    }

    final void a() {
        g.a().b("LenientAutoFocuser", "LenientAutoFocuser attemptAutoFocus");
        this.e.autoFocus(new AutoFocusCallback(this) {
            final /* synthetic */ u a;

            {
                this.a = this$0;
            }

            public final void onAutoFocus(boolean success, Camera camera) {
                g.a().b("LenientAutoFocuser", "LenientAutoFocuser onAutoFocus " + success);
                if (!this.a.d.getAndSet(true)) {
                    g.a().b("LenientAutoFocuser", "LenientAutoFocuser onAutoFocus acknowledge FOCUS");
                    this.a.a.run();
                }
            }
        });
        this.c.postDelayed(this.f, 3000);
    }

    final void b() {
        g.a().b("LenientAutoFocuser", "LenientAutoFocuser abort");
        this.d.set(true);
        this.c.removeCallbacks(this.f);
    }
}
