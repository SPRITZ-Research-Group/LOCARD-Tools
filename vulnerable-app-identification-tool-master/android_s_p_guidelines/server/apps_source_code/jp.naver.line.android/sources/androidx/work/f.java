package androidx.work;

import android.net.Uri;
import java.util.HashSet;
import java.util.Set;

public final class f {
    private final Set<g> a = new HashSet();

    public final void a(Uri uri, boolean z) {
        this.a.add(new g(uri, z));
    }

    public final Set<g> a() {
        return this.a;
    }

    public final int b() {
        return this.a.size();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((f) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
