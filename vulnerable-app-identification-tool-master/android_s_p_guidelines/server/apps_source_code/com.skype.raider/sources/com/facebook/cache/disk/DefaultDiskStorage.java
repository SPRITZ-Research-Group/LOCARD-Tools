package com.facebook.cache.disk;

import android.os.Environment;
import com.facebook.cache.a.i;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class DefaultDiskStorage implements c {
    static final long a = TimeUnit.MINUTES.toMillis(30);
    private static final Class<?> b = DefaultDiskStorage.class;
    private final File c;
    private final boolean d;
    private final File e;
    private final com.facebook.cache.a.a f;
    private final com.facebook.common.time.a g;

    public @interface FileType {
        public static final String CONTENT = ".cnt";
        public static final String TEMP = ".tmp";
    }

    private class a implements com.facebook.common.c.b {
        final /* synthetic */ DefaultDiskStorage a;
        private final List<com.facebook.cache.disk.c.a> b;

        private a(DefaultDiskStorage defaultDiskStorage) {
            this.a = defaultDiskStorage;
            this.b = new ArrayList();
        }

        /* synthetic */ a(DefaultDiskStorage x0, byte b) {
            this(x0);
        }

        public final void a(File directory) {
        }

        public final void b(File file) {
            c info = DefaultDiskStorage.a(this.a, file);
            if (info != null && info.a == FileType.CONTENT) {
                this.b.add(new b(info.b, file, (byte) 0));
            }
        }

        public final void c(File directory) {
        }

        public final List<com.facebook.cache.disk.c.a> a() {
            return Collections.unmodifiableList(this.b);
        }
    }

    @VisibleForTesting
    static class b implements com.facebook.cache.disk.c.a {
        private final String a;
        private final com.facebook.binaryresource.b b;
        private long c;
        private long d;

        /* synthetic */ b(String x0, File x1, byte b) {
            this(x0, x1);
        }

        private b(String id, File cachedFile) {
            h.a((Object) cachedFile);
            this.a = (String) h.a((Object) id);
            this.b = com.facebook.binaryresource.b.a(cachedFile);
            this.c = -1;
            this.d = -1;
        }

        public final String a() {
            return this.a;
        }

        public final long b() {
            if (this.d < 0) {
                this.d = this.b.c().lastModified();
            }
            return this.d;
        }

        public final com.facebook.binaryresource.b c() {
            return this.b;
        }

        public final long d() {
            if (this.c < 0) {
                this.c = this.b.b();
            }
            return this.c;
        }
    }

    private static class c {
        @FileType
        public final String a;
        public final String b;

        /* synthetic */ c(String x0, String x1, byte b) {
            this(x0, x1);
        }

        private c(@FileType String type, String resourceId) {
            this.a = type;
            this.b = resourceId;
        }

        public final String toString() {
            return this.a + "(" + this.b + ")";
        }

        @Nullable
        public static c a(File file) {
            String name = file.getName();
            int pos = name.lastIndexOf(46);
            if (pos <= 0) {
                return null;
            }
            String type = DefaultDiskStorage.c(name.substring(pos));
            if (type == null) {
                return null;
            }
            String resourceId = name.substring(0, pos);
            if (type.equals(FileType.TEMP)) {
                int numPos = resourceId.lastIndexOf(46);
                if (numPos <= 0) {
                    return null;
                }
                resourceId = resourceId.substring(0, numPos);
            }
            return new c(type, resourceId);
        }
    }

    private static class d extends IOException {
        public final long a;
        public final long b;

        public d(long expected, long actual) {
            super("File was not written completely. Expected: " + expected + ", found: " + actual);
            this.a = expected;
            this.b = actual;
        }
    }

    @VisibleForTesting
    class e implements com.facebook.cache.disk.c.b {
        @VisibleForTesting
        final File a;
        final /* synthetic */ DefaultDiskStorage b;
        private final String c;

        public e(DefaultDiskStorage this$0, String resourceId, File temporaryFile) {
            this.b = this$0;
            this.c = resourceId;
            this.a = temporaryFile;
        }

        public final void a(i callback) throws IOException {
            try {
                FileOutputStream fileStream = new FileOutputStream(this.a);
                try {
                    com.facebook.common.internal.c countingStream = new com.facebook.common.internal.c(fileStream);
                    callback.a(countingStream);
                    countingStream.flush();
                    long length = countingStream.a();
                    if (this.a.length() != length) {
                        throw new d(length, this.a.length());
                    }
                } finally {
                    fileStream.close();
                }
            } catch (FileNotFoundException fne) {
                this.b.f;
                com.facebook.cache.a.a.a aVar = com.facebook.cache.a.a.a.WRITE_UPDATE_FILE_NOT_FOUND;
                DefaultDiskStorage.b;
                throw fne;
            }
        }

        public final com.facebook.binaryresource.a a() throws IOException {
            Object targetFile = this.b.a(this.c);
            try {
                Object obj = this.a;
                h.a(obj);
                h.a(targetFile);
                targetFile.delete();
                if (obj.renameTo(targetFile)) {
                    if (targetFile.exists()) {
                        targetFile.setLastModified(this.b.g.a());
                    }
                    return com.facebook.binaryresource.b.a(targetFile);
                }
                Throwable th = null;
                if (targetFile.exists()) {
                    th = new com.facebook.common.c.c.b(targetFile.getAbsolutePath());
                } else if (!obj.getParentFile().exists()) {
                    th = new com.facebook.common.c.c.c(obj.getAbsolutePath());
                } else if (!obj.exists()) {
                    th = new FileNotFoundException(obj.getAbsolutePath());
                }
                throw new com.facebook.common.c.c.d("Unknown error renaming " + obj.getAbsolutePath() + " to " + targetFile.getAbsolutePath(), th);
            } catch (com.facebook.common.c.c.d re) {
                Throwable cause = re.getCause();
                com.facebook.cache.a.a.a aVar;
                if (cause == null) {
                    aVar = com.facebook.cache.a.a.a.WRITE_RENAME_FILE_OTHER;
                } else if (cause instanceof com.facebook.common.c.c.c) {
                    aVar = com.facebook.cache.a.a.a.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
                } else if (cause instanceof FileNotFoundException) {
                    aVar = com.facebook.cache.a.a.a.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
                } else {
                    aVar = com.facebook.cache.a.a.a.WRITE_RENAME_FILE_OTHER;
                }
                this.b.f;
                DefaultDiskStorage.b;
                throw re;
            }
        }

        public final boolean b() {
            return !this.a.exists() || this.a.delete();
        }
    }

    private class f implements com.facebook.common.c.b {
        final /* synthetic */ DefaultDiskStorage a;
        private boolean b;

        private f(DefaultDiskStorage defaultDiskStorage) {
            this.a = defaultDiskStorage;
        }

        /* synthetic */ f(DefaultDiskStorage x0, byte b) {
            this(x0);
        }

        public final void a(File directory) {
            if (!this.b && directory.equals(this.a.e)) {
                this.b = true;
            }
        }

        public final void b(File file) {
            boolean z = false;
            if (this.b) {
                c a = DefaultDiskStorage.a(this.a, file);
                if (a != null) {
                    if (a.a != FileType.TEMP) {
                        if (a.a == FileType.CONTENT) {
                            z = true;
                        }
                        h.b(z);
                        z = true;
                    } else if (file.lastModified() > this.a.g.a() - DefaultDiskStorage.a) {
                        z = true;
                    }
                }
                if (z) {
                    return;
                }
            }
            file.delete();
        }

        public final void c(File directory) {
            if (!(this.a.c.equals(directory) || this.b)) {
                directory.delete();
            }
            if (this.b && directory.equals(this.a.e)) {
                this.b = false;
            }
        }
    }

    public DefaultDiskStorage(File rootDirectory, int version, com.facebook.cache.a.a cacheErrorLogger) {
        int i = 1;
        h.a((Object) rootDirectory);
        this.c = rootDirectory;
        this.d = a(rootDirectory);
        this.e = new File(this.c, String.format(null, "%s.ols%d.%d", new Object[]{"v2", Integer.valueOf(100), Integer.valueOf(version)}));
        this.f = cacheErrorLogger;
        if (this.c.exists()) {
            if (this.e.exists()) {
                i = 0;
            } else {
                com.facebook.common.c.a.b(this.c);
            }
        }
        if (i != 0) {
            try {
                com.facebook.common.c.c.a(this.e);
            } catch (com.facebook.common.c.c.a e) {
                com.facebook.cache.a.a.a aVar = com.facebook.cache.a.a.a.WRITE_CREATE_DIR;
                new StringBuilder("version directory could not be created: ").append(this.e);
            }
        }
        this.g = com.facebook.common.time.c.b();
    }

    private static boolean a(File directory) {
        com.facebook.cache.a.a.a aVar;
        try {
            File extStoragePath = Environment.getExternalStorageDirectory();
            if (extStoragePath == null) {
                return false;
            }
            try {
                if (directory.getCanonicalPath().contains(extStoragePath.toString())) {
                    return true;
                }
                return false;
            } catch (IOException e) {
                aVar = com.facebook.cache.a.a.a.OTHER;
                return false;
            }
        } catch (Exception e2) {
            aVar = com.facebook.cache.a.a.a.OTHER;
            return false;
        }
    }

    public final boolean a() {
        return this.d;
    }

    @VisibleForTesting
    final File a(String resourceId) {
        c cVar = new c(FileType.CONTENT, resourceId, (byte) 0);
        return new File(d(cVar.b) + File.separator + cVar.b + cVar.a);
    }

    private String d(String resourceId) {
        return this.e + File.separator + String.valueOf(Math.abs(resourceId.hashCode() % 100));
    }

    private File e(String resourceId) {
        return new File(d(resourceId));
    }

    public final void b() {
        com.facebook.common.c.a.a(this.c, new f());
    }

    public final com.facebook.cache.disk.c.b a(String resourceId, Object debugInfo) throws IOException {
        c info = new c(FileType.TEMP, resourceId, (byte) 0);
        File parent = e(info.b);
        if (!parent.exists()) {
            try {
                com.facebook.common.c.c.a(parent);
            } catch (com.facebook.common.c.c.a e) {
                com.facebook.cache.a.a.a aVar = com.facebook.cache.a.a.a.WRITE_CREATE_DIR;
                throw e;
            }
        }
        try {
            return new e(this, resourceId, File.createTempFile(info.b + ".", FileType.TEMP, parent));
        } catch (IOException ioe) {
            com.facebook.cache.a.a.a aVar2 = com.facebook.cache.a.a.a.WRITE_CREATE_TEMPFILE;
            throw ioe;
        }
    }

    public final com.facebook.binaryresource.a b(String resourceId, Object debugInfo) {
        File file = a(resourceId);
        if (!file.exists()) {
            return null;
        }
        file.setLastModified(this.g.a());
        return com.facebook.binaryresource.b.a(file);
    }

    public final long a(com.facebook.cache.disk.c.a entry) {
        return b(((b) entry).c().c());
    }

    public final long b(String resourceId) {
        return b(a(resourceId));
    }

    private static long b(File contentFile) {
        if (!contentFile.exists()) {
            return 0;
        }
        return !contentFile.delete() ? -1 : contentFile.length();
    }

    public final void c() {
        com.facebook.common.c.a.a(this.c);
    }

    public final boolean c(String resourceId, Object debugInfo) {
        return a(resourceId).exists();
    }

    public final /* synthetic */ Collection d() throws IOException {
        Object aVar = new a();
        com.facebook.common.c.a.a(this.e, aVar);
        return aVar.a();
    }

    static /* synthetic */ c a(DefaultDiskStorage x0, File x1) {
        c a = c.a(x1);
        return (a == null || !x0.e(a.b).equals(x1.getParentFile())) ? null : a;
    }

    static /* synthetic */ String c(String x0) {
        if (FileType.CONTENT.equals(x0)) {
            return FileType.CONTENT;
        }
        if (FileType.TEMP.equals(x0)) {
            return FileType.TEMP;
        }
        return null;
    }
}
