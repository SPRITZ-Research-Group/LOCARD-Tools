package com.facebook.cache.disk;

import com.facebook.cache.a.i;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.time.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class d implements h {
    private static final Class<?> b = d.class;
    private static final long c = TimeUnit.HOURS.toMillis(2);
    private static final long d = TimeUnit.MINUTES.toMillis(30);
    @GuardedBy("mLock")
    @VisibleForTesting
    final Set<String> a;
    private final long e;
    private final long f;
    private final CountDownLatch g;
    private long h;
    private final com.facebook.cache.a.b i;
    private long j;
    private final long k;
    private final com.facebook.common.g.a l;
    private final c m;
    private final g n;
    private final com.facebook.cache.a.a o;
    private final boolean p;
    private final a q;
    private final com.facebook.common.time.a r;
    private final Object s = new Object();
    private boolean t;

    @VisibleForTesting
    static class a {
        private boolean a = false;
        private long b = -1;
        private long c = -1;

        a() {
        }

        public final synchronized boolean a() {
            return this.a;
        }

        public final synchronized void b() {
            this.a = false;
            this.c = -1;
            this.b = -1;
        }

        public final synchronized void a(long size, long count) {
            this.c = count;
            this.b = size;
            this.a = true;
        }

        public final synchronized void b(long sizeIncrement, long countIncrement) {
            if (this.a) {
                this.b += sizeIncrement;
                this.c += countIncrement;
            }
        }

        public final synchronized long c() {
            return this.b;
        }

        public final synchronized long d() {
            return this.c;
        }
    }

    public static class b {
        public final long a;
        public final long b;
        public final long c;

        public b(long cacheSizeLimitMinimum, long lowDiskSpaceCacheSizeLimit, long defaultCacheSizeLimit) {
            this.a = cacheSizeLimitMinimum;
            this.b = lowDiskSpaceCacheSizeLimit;
            this.c = defaultCacheSizeLimit;
        }
    }

    public d(c diskStorage, g entryEvictionComparatorSupplier, b params, com.facebook.cache.a.b cacheEventListener, com.facebook.cache.a.a cacheErrorLogger, Executor executorForBackgrountInit, boolean indexPopulateAtStartupEnabled) {
        this.e = params.b;
        this.f = params.c;
        this.h = params.c;
        this.l = com.facebook.common.g.a.a();
        this.m = diskStorage;
        this.n = entryEvictionComparatorSupplier;
        this.j = -1;
        this.i = cacheEventListener;
        this.k = params.a;
        this.o = cacheErrorLogger;
        this.q = new a();
        this.r = c.b();
        this.p = indexPopulateAtStartupEnabled;
        this.a = new HashSet();
        if (this.p) {
            this.g = new CountDownLatch(1);
            executorForBackgrountInit.execute(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    synchronized (this.a.s) {
                        this.a.b();
                    }
                    this.a.t = true;
                    this.a.g.countDown();
                }
            });
            return;
        }
        this.g = new CountDownLatch(0);
    }

    public final com.facebook.binaryresource.a a(com.facebook.cache.a.c key) {
        String resourceId = null;
        i cacheEvent = i.a().a(key);
        try {
            com.facebook.binaryresource.a resource;
            synchronized (this.s) {
                resource = null;
                List<String> resourceIds = com.facebook.cache.a.d.a(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    resourceId = (String) resourceIds.get(i);
                    cacheEvent.a(resourceId);
                    resource = this.m.b(resourceId, key);
                    if (resource != null) {
                        break;
                    }
                }
                if (resource == null) {
                    this.a.remove(resourceId);
                } else {
                    this.a.add(resourceId);
                }
            }
            cacheEvent.b();
            return resource;
        } catch (IOException ioe) {
            try {
                com.facebook.cache.a.a.a aVar = com.facebook.cache.a.a.a.GENERIC_IO;
                cacheEvent.a(ioe);
                return null;
            } finally {
                cacheEvent.b();
            }
        }
    }

    private com.facebook.binaryresource.a a(com.facebook.cache.disk.c.b inserter, String resourceId) throws IOException {
        com.facebook.binaryresource.a resource;
        synchronized (this.s) {
            resource = inserter.a();
            this.a.add(resourceId);
            this.q.b(resource.b(), 1);
        }
        return resource;
    }

    public final com.facebook.binaryresource.a a(com.facebook.cache.a.c key, i callback) throws IOException {
        String resourceId;
        i cacheEvent = i.a().a(key);
        synchronized (this.s) {
            resourceId = com.facebook.cache.a.d.b(key);
        }
        cacheEvent.a(resourceId);
        com.facebook.cache.disk.c.b inserter;
        try {
            inserter = a(resourceId, key);
            inserter.a(callback);
            com.facebook.binaryresource.a resource = a(inserter, resourceId);
            cacheEvent.a(resource.b()).b(this.q.c());
            if (!inserter.b()) {
                FLog.e(b, "Failed to delete temp file");
            }
            cacheEvent.b();
            return resource;
        } catch (Throwable ioe) {
            try {
                cacheEvent.a((IOException) ioe);
                FLog.e(b, "Failed inserting a file into the cache", ioe);
                throw ioe;
            } catch (Throwable th) {
                cacheEvent.b();
            }
        } catch (Throwable th2) {
            if (!inserter.b()) {
                FLog.e(b, "Failed to delete temp file");
            }
        }
    }

    public final void b(com.facebook.cache.a.c key) {
        synchronized (this.s) {
            try {
                List<String> resourceIds = com.facebook.cache.a.d.a(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String resourceId = (String) resourceIds.get(i);
                    this.m.b(resourceId);
                    this.a.remove(resourceId);
                }
            } catch (IOException e) {
                com.facebook.cache.a.a.a aVar = com.facebook.cache.a.a.a.DELETE_FILE;
                new StringBuilder("delete: ").append(e.getMessage());
            }
        }
    }

    public final void a() {
        Exception e;
        com.facebook.cache.a.a.a aVar;
        synchronized (this.s) {
            try {
                this.m.c();
                this.a.clear();
            } catch (Exception e2) {
                e = e2;
                aVar = com.facebook.cache.a.a.a.EVICTION;
                new StringBuilder("clearAll: ").append(e.getMessage());
                this.q.b();
            } catch (Exception e22) {
                e = e22;
                aVar = com.facebook.cache.a.a.a.EVICTION;
                new StringBuilder("clearAll: ").append(e.getMessage());
                this.q.b();
            }
            this.q.b();
        }
    }

    public final boolean c(com.facebook.cache.a.c key) {
        boolean z;
        synchronized (this.s) {
            List<String> resourceIds = com.facebook.cache.a.d.a(key);
            for (int i = 0; i < resourceIds.size(); i++) {
                if (this.a.contains((String) resourceIds.get(i))) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public final boolean d(com.facebook.cache.a.c key) {
        synchronized (this.s) {
            if (c(key)) {
                return true;
            }
            try {
                List<String> resourceIds = com.facebook.cache.a.d.a(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String resourceId = (String) resourceIds.get(i);
                    if (this.m.c(resourceId, key)) {
                        this.a.add(resourceId);
                        return true;
                    }
                }
                return false;
            } catch (IOException e) {
                return false;
            }
        }
    }

    @GuardedBy("mLock")
    private boolean b() {
        long now = this.r.a();
        if (!this.q.a() || this.j == -1 || now - this.j > d) {
            return c();
        }
        return false;
    }

    @GuardedBy("mLock")
    private boolean c() {
        Set<String> tempResourceIndex;
        long size = 0;
        int count = 0;
        boolean foundFutureTimestamp = false;
        int numFutureFiles = 0;
        int sizeFutureFiles = 0;
        long maxTimeDelta = -1;
        long now = this.r.a();
        long timeThreshold = now + c;
        if (this.p && this.a.isEmpty()) {
            tempResourceIndex = this.a;
        } else if (this.p) {
            tempResourceIndex = new HashSet();
        } else {
            tempResourceIndex = null;
        }
        com.facebook.cache.a.a.a aVar;
        try {
            for (com.facebook.cache.disk.c.a entry : this.m.d()) {
                count++;
                size += entry.d();
                if (entry.b() > timeThreshold) {
                    foundFutureTimestamp = true;
                    numFutureFiles++;
                    sizeFutureFiles = (int) (((long) sizeFutureFiles) + entry.d());
                    maxTimeDelta = Math.max(entry.b() - now, maxTimeDelta);
                } else if (this.p) {
                    tempResourceIndex.add(entry.a());
                }
            }
            if (foundFutureTimestamp) {
                aVar = com.facebook.cache.a.a.a.READ_INVALID_ENTRY;
                new StringBuilder("Future timestamp found in ").append(numFutureFiles).append(" files , with a total size of ").append(sizeFutureFiles).append(" bytes, and a maximum time delta of ").append(maxTimeDelta).append("ms");
            }
            if (!(this.q.d() == ((long) count) && this.q.c() == size)) {
                if (this.p && this.a != tempResourceIndex) {
                    this.a.clear();
                    this.a.addAll(tempResourceIndex);
                }
                this.q.a(size, (long) count);
            }
            this.j = now;
            return true;
        } catch (IOException ioe) {
            aVar = com.facebook.cache.a.a.a.GENERIC_IO;
            new StringBuilder("calcFileCacheSize: ").append(ioe.getMessage());
            return false;
        }
    }

    private com.facebook.cache.disk.c.b a(String resourceId, com.facebook.cache.a.c key) throws IOException {
        synchronized (this.s) {
            boolean b = b();
            if (this.l.a(this.m.a() ? com.facebook.common.g.a.a.EXTERNAL : com.facebook.common.g.a.a.INTERNAL, this.f - this.q.c())) {
                this.h = this.e;
            } else {
                this.h = this.f;
            }
            long c = this.q.c();
            if (c > this.h && !b) {
                this.q.b();
                b();
            }
            if (c > this.h) {
                long j = (this.h * 9) / 10;
                com.facebook.cache.a.b.a aVar = com.facebook.cache.a.b.a.CACHE_FULL;
                try {
                    Collection<com.facebook.cache.disk.c.a> d = this.m.d();
                    c = this.r.a() + c;
                    Collection<com.facebook.cache.disk.c.a> arrayList = new ArrayList(d.size());
                    Collection arrayList2 = new ArrayList(d.size());
                    for (com.facebook.cache.disk.c.a aVar2 : d) {
                        if (aVar2.b() > c) {
                            arrayList.add(aVar2);
                        } else {
                            arrayList2.add(aVar2);
                        }
                    }
                    Collections.sort(arrayList2, this.n.a());
                    arrayList.addAll(arrayList2);
                    long c2 = this.q.c();
                    long j2 = c2 - j;
                    int i = 0;
                    c = 0;
                    for (com.facebook.cache.disk.c.a aVar22 : arrayList) {
                        if (c > j2) {
                            break;
                        }
                        long a = this.m.a(aVar22);
                        this.a.remove(aVar22.a());
                        if (a > 0) {
                            i++;
                            c += a;
                            i.a().a(aVar22.a()).a(aVar).a(a).b(c2 - c).c(j).b();
                        }
                        i = i;
                        c = c;
                    }
                    this.q.b(-c, (long) (-i));
                    this.m.b();
                } catch (IOException e) {
                    com.facebook.cache.a.a.a aVar3 = com.facebook.cache.a.a.a.EVICTION;
                    new StringBuilder("evictAboveSize: ").append(e.getMessage());
                    throw e;
                }
            }
        }
        return this.m.a(resourceId, key);
    }
}
