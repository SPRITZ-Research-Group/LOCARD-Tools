package com.facebook.ads.internal.p.b.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class e implements a {
    private final ExecutorService a = Executors.newSingleThreadExecutor();

    private class a implements Callable<Void> {
        final /* synthetic */ e a;
        private final File b;

        public a(e eVar, File file) {
            this.a = eVar;
            this.b = file;
        }

        public final /* synthetic */ Object call() {
            e.a(this.a, this.b);
            return null;
        }
    }

    e() {
    }

    static /* synthetic */ void a(e eVar, File file) {
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!file.setLastModified(currentTimeMillis)) {
                long length = file.length();
                if (length != 0) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                    randomAccessFile.seek(length - 1);
                    byte readByte = randomAccessFile.readByte();
                    randomAccessFile.seek(length - 1);
                    randomAccessFile.write(readByte);
                    randomAccessFile.close();
                } else if (!(file.delete() && file.createNewFile())) {
                    throw new IOException("Error recreate zero-size file " + file);
                }
                if (file.lastModified() < currentTimeMillis) {
                    throw new IOException("Error set last modified date to " + file);
                }
            }
        }
        File parentFile = file.getParentFile();
        List linkedList = new LinkedList();
        File[] listFiles = parentFile.listFiles();
        if (listFiles != null) {
            linkedList = Arrays.asList(listFiles);
            Collections.sort(linkedList, new a());
        }
        eVar.a(linkedList);
    }

    public void a(File file) {
        this.a.submit(new a(this, file));
    }

    protected abstract boolean a(long j);

    private void a(List<File> list) {
        long j;
        long j2 = 0;
        Iterator it = list.iterator();
        while (true) {
            j = j2;
            if (!it.hasNext()) {
                break;
            }
            j2 = ((File) it.next()).length() + j;
        }
        list.size();
        for (File file : list) {
            if (!a(j)) {
                long length = file.length();
                if (file.delete()) {
                    j -= length;
                    new StringBuilder("Cache file ").append(file).append(" is deleted because it exceeds cache limit");
                } else {
                    new StringBuilder("Error deleting file ").append(file).append(" for trimming cache");
                }
            }
        }
    }
}
