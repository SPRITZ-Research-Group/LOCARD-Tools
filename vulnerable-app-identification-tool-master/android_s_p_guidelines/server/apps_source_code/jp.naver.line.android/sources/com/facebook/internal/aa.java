package com.facebook.internal;

import java.io.File;

final class aa implements Comparable<aa> {
    private final File a;
    private final long b;

    public final /* synthetic */ int compareTo(Object obj) {
        return a((aa) obj);
    }

    aa(File file) {
        this.a = file;
        this.b = file.lastModified();
    }

    final File a() {
        return this.a;
    }

    final long b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof aa) && a((aa) obj) == 0;
    }

    public final int hashCode() {
        return ((this.a.hashCode() + 1073) * 37) + ((int) (this.b % 2147483647L));
    }

    private int a(aa aaVar) {
        if (this.b < aaVar.b) {
            return -1;
        }
        if (this.b > aaVar.b) {
            return 1;
        }
        return this.a.compareTo(aaVar.a);
    }
}
