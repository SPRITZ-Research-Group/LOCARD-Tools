package a.a;

import com.appboy.f.c;

public class ce implements ch {
    private static final String a = c.a(ce.class);
    private final ch b;
    private final b c;

    public ce(ch chVar, b bVar) {
        this.b = chVar;
        this.c = bVar;
    }

    public final void a(az azVar) {
        try {
            this.b.a(azVar);
        } catch (Throwable e) {
            c.d(a, "Failed to upsert active session in the storage.", e);
            a(this.c, e);
        }
    }

    public final az a() {
        try {
            return this.b.a();
        } catch (Throwable e) {
            c.d(a, "Failed to get the active session from the storage.", e);
            a(this.c, e);
            return null;
        }
    }

    public final void b(az azVar) {
        try {
            this.b.b(azVar);
        } catch (Throwable e) {
            c.d(a, "Failed to delete the sealed session from the storage.", e);
            a(this.c, e);
        }
    }

    private static void a(b bVar, Throwable th) {
        try {
            bVar.a(new q("A database exception has occurred. Please view the stack trace for more details.", th), q.class);
        } catch (Throwable e) {
            c.d(a, "Failed to log throwable.", e);
        }
    }
}
