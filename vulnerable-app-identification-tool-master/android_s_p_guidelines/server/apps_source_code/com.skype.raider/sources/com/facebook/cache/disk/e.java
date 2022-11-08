package com.facebook.cache.disk;

import com.facebook.cache.disk.c.b;
import com.facebook.common.c.c;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

public class e implements c {
    private static final Class<?> b = e.class;
    @VisibleForTesting
    volatile a a = new a(null, null);
    private final int c;
    private final j<File> d;
    private final String e;
    private final com.facebook.cache.a.a f;

    @VisibleForTesting
    static class a {
        @Nullable
        public final c a;
        @Nullable
        public final File b;

        @VisibleForTesting
        a(@Nullable File rootDirectory, @Nullable c delegate) {
            this.a = delegate;
            this.b = rootDirectory;
        }
    }

    public e(int version, j<File> baseDirectoryPathSupplier, String baseDirectoryName, com.facebook.cache.a.a cacheErrorLogger) {
        this.c = version;
        this.f = cacheErrorLogger;
        this.d = baseDirectoryPathSupplier;
        this.e = baseDirectoryName;
    }

    public final boolean a() {
        try {
            return e().a();
        } catch (IOException e) {
            return false;
        }
    }

    public final com.facebook.binaryresource.a b(String resourceId, Object debugInfo) throws IOException {
        return e().b(resourceId, debugInfo);
    }

    public final boolean c(String resourceId, Object debugInfo) throws IOException {
        return e().c(resourceId, debugInfo);
    }

    public final void b() {
        try {
            e().b();
        } catch (Throwable ioe) {
            FLog.e(b, "purgeUnexpectedResources", ioe);
        }
    }

    public final b a(String resourceId, Object debugInfo) throws IOException {
        return e().a(resourceId, debugInfo);
    }

    public final Collection<com.facebook.cache.disk.c.a> d() throws IOException {
        return e().d();
    }

    public final long a(com.facebook.cache.disk.c.a entry) throws IOException {
        return e().a(entry);
    }

    public final long b(String resourceId) throws IOException {
        return e().b(resourceId);
    }

    public final void c() throws IOException {
        e().c();
    }

    @VisibleForTesting
    private synchronized c e() throws IOException {
        Object obj;
        a aVar = this.a;
        if (aVar.a == null || aVar.b == null || !aVar.b.exists()) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            if (!(this.a.a == null || this.a.b == null)) {
                com.facebook.common.c.a.b(this.a.b);
            }
            File file = new File((File) this.d.a(), this.e);
            try {
                c.a(file);
                FLog.d(b, "Created cache directory %s", file.getAbsolutePath());
                this.a = new a(file, new DefaultDiskStorage(file, this.c, this.f));
            } catch (com.facebook.common.c.c.a e) {
                com.facebook.cache.a.a.a aVar2 = com.facebook.cache.a.a.a.WRITE_CREATE_DIR;
                throw e;
            }
        }
        return (c) h.a(this.a.a);
    }
}
