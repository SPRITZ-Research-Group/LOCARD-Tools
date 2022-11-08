package a.a;

import android.support.annotation.NonNull;
import com.appboy.f.c;
import java.util.Collection;
import java.util.Collections;

public class cd implements cg {
    private static final String a = c.a(cd.class);
    private final cg b;
    private final b c;
    private boolean d = false;

    public cd(cg cgVar, b bVar) {
        this.b = cgVar;
        this.c = bVar;
    }

    public final void a(av avVar) {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not adding event: " + avVar);
            return;
        }
        try {
            this.b.a(avVar);
        } catch (Throwable e) {
            c.d(a, "Failed to insert event into storage.", e);
            a(this.c, e);
        }
    }

    public final void b(av avVar) {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not deleting event: " + avVar);
            return;
        }
        try {
            this.b.b(avVar);
        } catch (Throwable e) {
            c.d(a, "Failed to delete event from storage.", e);
            a(this.c, e);
        }
    }

    @NonNull
    public final Collection<av> a() {
        if (this.d) {
            c.f(a, "Storage provider is closed. Not getting all events.");
            return Collections.emptyList();
        }
        try {
            return this.b.a();
        } catch (Throwable e) {
            c.d(a, "Failed to get all events from storage.", e);
            a(this.c, e);
            return Collections.emptyList();
        }
    }

    public final void b() {
        c.f(a, "Setting this provider and internal storage provider to closed.");
        this.d = true;
        this.b.b();
    }

    private static void a(b bVar, Throwable th) {
        try {
            bVar.a(new q("A database exception has occurred. Please view the stack trace for more details.", th), q.class);
        } catch (Throwable e) {
            c.d(a, "Failed to log throwable.", e);
        }
    }
}
