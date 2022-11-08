package com.facebook.internal;

import android.net.Uri;

final class ai {
    Uri a;
    Object b;

    ai(Uri uri, Object obj) {
        this.a = uri;
        this.b = obj;
    }

    public final int hashCode() {
        return ((this.a.hashCode() + 1073) * 37) + this.b.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ai)) {
            return false;
        }
        ai aiVar = (ai) obj;
        if (aiVar.a == this.a && aiVar.b == this.b) {
            return true;
        }
        return false;
    }
}
