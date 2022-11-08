package com.facebook.ads.internal.p.b.a;

import java.io.File;
import java.util.Comparator;

final class d {

    private static final class a implements Comparator<File> {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            File file = (File) obj2;
            long lastModified = ((File) obj).lastModified();
            long lastModified2 = file.lastModified();
            return lastModified < lastModified2 ? -1 : lastModified == lastModified2 ? 0 : 1;
        }
    }
}
