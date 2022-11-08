package com.facebook.binaryresource;

import com.facebook.common.internal.h;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class b implements a {
    private final File a;

    private b(File file) {
        this.a = (File) h.a((Object) file);
    }

    public final File c() {
        return this.a;
    }

    public final InputStream a() throws IOException {
        return new FileInputStream(this.a);
    }

    public final long b() {
        return this.a.length();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return this.a.equals(((b) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public static b a(File file) {
        return file != null ? new b(file) : null;
    }
}
