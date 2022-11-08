package com.facebook.common.g;

import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.l;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class a {
    private static a a;
    private static final long b = TimeUnit.MINUTES.toMillis(2);
    private volatile StatFs c = null;
    private volatile File d;
    private volatile StatFs e = null;
    private volatile File f;
    @GuardedBy("lock")
    private long g;
    private final Lock h = new ReentrantLock();
    private volatile boolean i = false;

    public enum a {
        INTERNAL,
        EXTERNAL
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    protected a() {
    }

    private void b() {
        if (!this.i) {
            this.h.lock();
            try {
                if (!this.i) {
                    this.d = Environment.getDataDirectory();
                    this.f = Environment.getExternalStorageDirectory();
                    c();
                    this.i = true;
                }
                this.h.unlock();
            } catch (Throwable th) {
                this.h.unlock();
            }
        }
    }

    public final boolean a(a storageType, long freeSpaceThreshold) {
        long availableStorageSpace;
        b();
        b();
        if (this.h.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.g > b) {
                    c();
                }
                this.h.unlock();
            } catch (Throwable th) {
                this.h.unlock();
            }
        }
        StatFs statFs = storageType == a.INTERNAL ? this.c : this.e;
        if (statFs != null) {
            long blockSizeLong;
            long availableBlocksLong;
            if (VERSION.SDK_INT >= 18) {
                blockSizeLong = statFs.getBlockSizeLong();
                availableBlocksLong = statFs.getAvailableBlocksLong();
            } else {
                blockSizeLong = (long) statFs.getBlockSize();
                availableBlocksLong = (long) statFs.getAvailableBlocks();
            }
            availableStorageSpace = blockSizeLong * availableBlocksLong;
        } else {
            availableStorageSpace = 0;
        }
        if (availableStorageSpace > 0) {
            return availableStorageSpace < freeSpaceThreshold;
        } else {
            return true;
        }
    }

    @GuardedBy("lock")
    private void c() {
        this.c = a(this.c, this.d);
        this.e = a(this.e, this.f);
        this.g = SystemClock.uptimeMillis();
    }

    private static StatFs a(@Nullable StatFs statfs, @Nullable File dir) {
        if (dir == null || !dir.exists()) {
            return null;
        }
        if (statfs == null) {
            try {
                return new StatFs(dir.getAbsolutePath());
            } catch (IllegalArgumentException e) {
                return null;
            } catch (Throwable th) {
                RuntimeException b = l.b(th);
            }
        } else {
            statfs.restat(dir.getAbsolutePath());
            return statfs;
        }
    }
}
