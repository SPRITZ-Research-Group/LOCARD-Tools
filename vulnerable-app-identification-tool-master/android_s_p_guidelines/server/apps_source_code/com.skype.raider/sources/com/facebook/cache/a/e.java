package com.facebook.cache.a;

import android.net.Uri;
import java.util.List;

public final class e implements c {
    final List<c> a;

    public final String toString() {
        return "MultiCacheKey:" + this.a.toString();
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof e)) {
            return false;
        }
        return this.a.equals(((e) o).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean a(Uri uri) {
        for (int i = 0; i < this.a.size(); i++) {
            if (((c) this.a.get(i)).a(uri)) {
                return true;
            }
        }
        return false;
    }

    public final String a() {
        return ((c) this.a.get(0)).a();
    }
}
