package com.google.android.exoplayer.upstream.cache;

import com.google.obf.ly;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CacheSpan implements Comparable<CacheSpan> {
    private static final String SUFFIX = ".v1.exo";
    private static final String SUFFIX_ESCAPED = "\\.v1\\.exo";
    private static final Pattern cacheFilePattern = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)(\\.v1\\.exo)$");
    public final File file;
    public final boolean isCached;
    public final String key;
    public final long lastAccessTimestamp;
    public final long length;
    public final long position;

    public static File getCacheFileName(File file, String str, long j, long j2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(ly.a);
        stringBuilder.append(j);
        stringBuilder.append(ly.a);
        stringBuilder.append(j2);
        stringBuilder.append(SUFFIX);
        return new File(file, stringBuilder.toString());
    }

    public static CacheSpan createLookup(String str, long j) {
        return new CacheSpan(str, j, -1, false, -1, null);
    }

    public static CacheSpan createOpenHole(String str, long j) {
        return new CacheSpan(str, j, -1, false, -1, null);
    }

    public static CacheSpan createClosedHole(String str, long j, long j2) {
        return new CacheSpan(str, j, j2, false, -1, null);
    }

    public static CacheSpan createCacheEntry(File file) {
        Matcher matcher = cacheFilePattern.matcher(file.getName());
        if (matcher.matches()) {
            return createCacheEntry(matcher.group(1), Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(3)), file);
        }
        return null;
    }

    private static CacheSpan createCacheEntry(String str, long j, long j2, File file) {
        return new CacheSpan(str, j, file.length(), true, j2, file);
    }

    CacheSpan(String str, long j, long j2, boolean z, long j3, File file) {
        this.key = str;
        this.position = j;
        this.length = j2;
        this.isCached = z;
        this.file = file;
        this.lastAccessTimestamp = j3;
    }

    public final boolean isOpenEnded() {
        return this.length == -1;
    }

    public final CacheSpan touch() {
        long currentTimeMillis = System.currentTimeMillis();
        File cacheFileName = getCacheFileName(this.file.getParentFile(), this.key, this.position, currentTimeMillis);
        this.file.renameTo(cacheFileName);
        return createCacheEntry(this.key, this.position, currentTimeMillis, cacheFileName);
    }

    public final int compareTo(CacheSpan cacheSpan) {
        if (!this.key.equals(cacheSpan.key)) {
            return this.key.compareTo(cacheSpan.key);
        }
        long j = this.position - cacheSpan.position;
        if (j == 0) {
            return 0;
        }
        return j < 0 ? -1 : 1;
    }
}
