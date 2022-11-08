package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import javax.annotation.Nullable;

public final class e implements Closeable {
    private final FileOutputStream a;
    @Nullable
    private final FileLock b;

    public static e a(File lockFile) throws IOException {
        return new e(lockFile);
    }

    private e(File lockFile) throws IOException {
        this.a = new FileOutputStream(lockFile);
        try {
            FileLock lock = this.a.getChannel().lock();
            if (lock == null) {
            }
            this.b = lock;
        } finally {
            this.a.close();
        }
    }

    public final void close() throws IOException {
        try {
            if (this.b != null) {
                this.b.release();
            }
            this.a.close();
        } catch (Throwable th) {
            this.a.close();
        }
    }
}
