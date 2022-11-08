package a.a;

import android.support.annotation.NonNull;
import com.appboy.f.c;
import java.util.Collection;
import java.util.concurrent.Callable;

public class ca implements cg {
    private static final String a = c.a(ca.class);
    private final cg b;
    private final u c;
    private boolean d = false;

    public ca(cg cgVar, u uVar) {
        this.b = cgVar;
        this.c = uVar;
    }

    public final void a(final av avVar) {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not adding event: " + avVar);
        } else {
            this.c.execute(new Runnable(this) {
                final /* synthetic */ ca b;

                public final void run() {
                    this.b.b.a(avVar);
                }
            });
        }
    }

    public final void b(final av avVar) {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not deleting event: " + avVar);
        } else {
            this.c.execute(new Runnable(this) {
                final /* synthetic */ ca b;

                public final void run() {
                    this.b.b.b(avVar);
                }
            });
        }
    }

    @NonNull
    public final synchronized Collection<av> a() {
        Collection<av> collection;
        if (this.d) {
            c.f(a, "Storage provider is closed. Not getting all events.");
            collection = null;
        } else {
            try {
                collection = (Collection) this.c.submit(new Callable<Collection<av>>(this) {
                    final /* synthetic */ ca a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ Object call() {
                        return this.a.b.a();
                    }
                }).get();
            } catch (Throwable e) {
                throw new RuntimeException("Error while trying to asynchronously get all events.", e);
            }
        }
        return collection;
    }

    public final synchronized void b() {
        c.f(a, "Setting this provider and internal storage provider to closed. Cancelling all queued storage provider work.");
        this.d = true;
        this.b.b();
        this.c.shutdownNow();
    }
}
