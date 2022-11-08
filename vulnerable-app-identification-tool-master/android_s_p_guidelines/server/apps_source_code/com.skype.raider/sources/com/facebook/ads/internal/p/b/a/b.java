package com.facebook.ads.internal.p.b.a;

import com.facebook.ads.internal.p.b.a;
import com.facebook.ads.internal.p.b.l;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class b implements a {
    public File a;
    private final a b;
    private RandomAccessFile c;

    public b(File file, a aVar) {
        if (aVar == null) {
            try {
                throw new NullPointerException();
            } catch (Throwable e) {
                throw new l("Error using file " + file + " as disc cache", e);
            }
        }
        this.b = aVar;
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            if (!parentFile.isDirectory()) {
                throw new IOException("File " + parentFile + " is not directory!");
            }
        } else if (!parentFile.mkdirs()) {
            throw new IOException(String.format("Directory %s can't be created", new Object[]{parentFile.getAbsolutePath()}));
        }
        boolean exists = file.exists();
        this.a = exists ? file : new File(file.getParentFile(), file.getName() + ".download");
        this.c = new RandomAccessFile(this.a, exists ? "r" : "rw");
    }

    public final synchronized int a() {
        try {
        } catch (Throwable e) {
            throw new l("Error reading length of file " + this.a, e);
        }
        return (int) this.c.length();
    }

    public final synchronized int a(byte[] bArr, long j) {
        try {
            this.c.seek(j);
        } catch (Throwable e) {
            throw new l(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(8192), Long.valueOf(j), Integer.valueOf(a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.c.read(bArr, 0, 8192);
    }

    public final synchronized void a(byte[] bArr, int i) {
        try {
            if (d()) {
                throw new l("Error append cache: cache file " + this.a + " is completed!");
            }
            this.c.seek((long) a());
            this.c.write(bArr, 0, i);
        } catch (Throwable e) {
            throw new l(String.format("Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.c, Integer.valueOf(8192)}), e);
        }
    }

    public final synchronized void b() {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (Throwable e) {
            throw new l("Error closing file " + this.a, e);
        }
    }

    public final synchronized void c() {
        if (!d()) {
            b();
            File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - 9));
            if (this.a.renameTo(file)) {
                this.a = file;
                try {
                    this.c = new RandomAccessFile(this.a, "r");
                } catch (Throwable e) {
                    throw new l("Error opening " + this.a + " as disc cache", e);
                }
            }
            throw new l("Error renaming file " + this.a + " to " + file + " for completion!");
        }
    }

    public final synchronized boolean d() {
        return !this.a.getName().endsWith(".download");
    }
}
