package a.a;

import android.support.annotation.NonNull;
import com.appboy.f.c;
import java.util.concurrent.Executor;

public final class ac {
    private static final String a = c.a(ai.class);
    private final cg b;
    private boolean c = false;

    public ac(cg cgVar) {
        this.b = cgVar;
    }

    public final void a(@NonNull av avVar) {
        if (this.c) {
            c.f(a, "Storage manager is closed. Not adding event: " + avVar);
        } else {
            this.b.a(avVar);
        }
    }

    public final void b(@NonNull av avVar) {
        if (this.c) {
            c.f(a, "Storage manager is closed. Not deleting event: " + avVar);
        } else {
            this.b.b(avVar);
        }
    }

    public final void a(Executor executor, final fp fpVar) {
        if (this.c) {
            c.f(a, "Storage manager is closed. Not starting offline recovery.");
        } else {
            executor.execute(new Runnable(this) {
                final /* synthetic */ ac b;

                public final void run() {
                    c.b(ac.a, "Started offline AppboyEvent recovery task.");
                    for (av a : this.b.b.a()) {
                        fpVar.a(a);
                    }
                }
            });
        }
    }

    public final void a() {
        this.c = true;
        this.b.b();
    }
}
