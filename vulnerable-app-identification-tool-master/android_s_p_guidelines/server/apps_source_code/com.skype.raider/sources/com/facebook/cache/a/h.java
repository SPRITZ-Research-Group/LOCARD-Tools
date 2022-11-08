package com.facebook.cache.a;

import android.net.Uri;

public final class h implements c {
    final String a;

    public h(String key) {
        this.a = (String) com.facebook.common.internal.h.a((Object) key);
    }

    public final String toString() {
        return this.a;
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof h)) {
            return false;
        }
        return this.a.equals(((h) o).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean a(Uri uri) {
        return this.a.contains(uri.toString());
    }

    public final String a() {
        return this.a;
    }
}
