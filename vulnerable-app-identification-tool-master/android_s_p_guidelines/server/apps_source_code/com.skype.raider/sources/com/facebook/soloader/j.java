package com.facebook.soloader;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.StrictMode.ThreadPolicy;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class j extends c {
    @Nullable
    private String[] c;
    private final Map<String, Object> d = new HashMap();
    protected final Context e;
    @Nullable
    protected String f;

    protected static abstract class e implements Closeable {
        protected abstract b b() throws IOException;

        protected abstract d c() throws IOException;

        protected e() {
        }

        public void close() throws IOException {
        }
    }

    public static class a {
        public final String c;
        public final String d;

        public a(String name, String hash) {
            this.c = name;
            this.d = hash;
        }
    }

    protected static abstract class d implements Closeable {
        public abstract boolean a();

        public abstract c b() throws IOException;

        protected d() {
        }

        public void close() throws IOException {
        }
    }

    public static final class b {
        public final a[] a;

        public b(a[] dsos) {
            this.a = dsos;
        }

        static final b a(DataInput xdi) throws IOException {
            if (xdi.readByte() != (byte) 1) {
                throw new RuntimeException("wrong dso manifest version");
            }
            int nrDso = xdi.readInt();
            if (nrDso < 0) {
                throw new RuntimeException("illegal number of shared libraries");
            }
            a[] dsos = new a[nrDso];
            for (int i = 0; i < nrDso; i++) {
                dsos[i] = new a(xdi.readUTF(), xdi.readUTF());
            }
            return new b(dsos);
        }

        public final void a(DataOutput xdo) throws IOException {
            xdo.writeByte(1);
            xdo.writeInt(this.a.length);
            for (int i = 0; i < this.a.length; i++) {
                xdo.writeUTF(this.a[i].c);
                xdo.writeUTF(this.a[i].d);
            }
        }
    }

    protected static final class c implements Closeable {
        public final a a;
        public final InputStream b;

        public c(a dso, InputStream content) {
            this.a = dso;
            this.b = content;
        }

        public final void close() throws IOException {
            this.b.close();
        }
    }

    protected abstract e a() throws IOException;

    public final void a(String[] abis) {
        this.c = abis;
    }

    private static void a(File stateFileName, byte state) throws IOException {
        Throwable th;
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        Throwable th2 = null;
        try {
            stateFile.seek(0);
            stateFile.write(state);
            stateFile.setLength(stateFile.getFilePointer());
            stateFile.getFD().sync();
            stateFile.close();
            return;
        } catch (Throwable th22) {
            Throwable th3 = th22;
            th22 = th;
            th = th3;
        }
        if (th22 != null) {
            try {
                stateFile.close();
            } catch (Throwable th4) {
                th22.addSuppressed(th4);
            }
        } else {
            stateFile.close();
        }
        throw th;
        throw th;
    }

    private void a(a[] dsos) throws IOException {
        String[] existingFiles = this.a.list();
        if (existingFiles == null) {
            throw new IOException("unable to list directory " + this.a);
        }
        for (String fileName : existingFiles) {
            if (!(fileName.equals("dso_state") || fileName.equals("dso_lock") || fileName.equals("dso_deps") || fileName.equals("dso_manifest"))) {
                boolean found = false;
                int j = 0;
                while (!found && j < dsos.length) {
                    if (dsos[j].c.equals(fileName)) {
                        found = true;
                    }
                    j++;
                }
                if (!found) {
                    File fileNameToDelete = new File(this.a, fileName);
                    new StringBuilder("deleting unaccounted-for file ").append(fileNameToDelete);
                    SysUtil.a(fileNameToDelete);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte state, b desiredManifest, d dsoIterator) throws IOException {
        b existingManifest;
        Throwable th;
        byte[] ioBuffer;
        c iDso;
        boolean obsolete;
        int i;
        File file;
        RandomAccessFile randomAccessFile;
        int available;
        FileDescriptor fd;
        long j;
        new StringBuilder("regenerating DSO store ").append(getClass().getName());
        Throwable th2 = "dso_manifest";
        DataInput manifestFile = new RandomAccessFile(new File(this.a, th2), "rw");
        b existingManifest2 = null;
        if (state == (byte) 1) {
            try {
                existingManifest = b.a(manifestFile);
            } catch (Exception e) {
            }
            if (existingManifest != null) {
                try {
                    existingManifest2 = new b(new a[0]);
                } catch (Throwable th3) {
                    th = th3;
                    th2 = null;
                }
            } else {
                existingManifest2 = existingManifest;
            }
            a(desiredManifest.a);
            ioBuffer = new byte[32768];
            while (dsoIterator.a()) {
                iDso = dsoIterator.b();
                th2 = null;
                obsolete = true;
                i = 0;
                while (obsolete) {
                    if (i < existingManifest2.a.length) {
                        break;
                    }
                    if (existingManifest2.a[i].c.equals(iDso.a.c) && existingManifest2.a[i].d.equals(iDso.a.d)) {
                        obsolete = false;
                    }
                    i++;
                }
                if (obsolete) {
                    new StringBuilder("extracting DSO ").append(iDso.a.c);
                    if (this.a.setWritable(true, true)) {
                        throw new IOException("cannot make directory writable for us: " + this.a);
                    }
                    file = new File(this.a, iDso.a.c);
                    try {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                    } catch (IOException e2) {
                        new StringBuilder("error overwriting ").append(file).append(" trying to delete and start over");
                        SysUtil.a(file);
                        randomAccessFile = new RandomAccessFile(file, "rw");
                    }
                    try {
                        available = iDso.b.available();
                        if (available > 1) {
                            fd = randomAccessFile.getFD();
                            j = (long) available;
                            if (VERSION.SDK_INT >= 21) {
                                LollipopSysdeps.fallocateIfSupported(fd, j);
                            }
                        }
                        SysUtil.a(randomAccessFile, iDso.b, ioBuffer);
                        randomAccessFile.setLength(randomAccessFile.getFilePointer());
                        if (file.setExecutable(true, false)) {
                            throw new IOException("cannot make file executable: " + file);
                        }
                        randomAccessFile.close();
                    } catch (IOException e3) {
                        SysUtil.a(file);
                        throw e3;
                    } catch (Throwable th4) {
                        randomAccessFile.close();
                    }
                }
                iDso.close();
            }
            manifestFile.close();
            new StringBuilder("Finished regenerating DSO store ").append(getClass().getName());
            return;
        }
        existingManifest = existingManifest2;
        if (existingManifest != null) {
            existingManifest2 = existingManifest;
        } else {
            existingManifest2 = new b(new a[0]);
        }
        a(desiredManifest.a);
        ioBuffer = new byte[32768];
        while (dsoIterator.a()) {
            iDso = dsoIterator.b();
            th2 = null;
            obsolete = true;
            i = 0;
            while (obsolete) {
                if (i < existingManifest2.a.length) {
                    break;
                }
                obsolete = false;
                i++;
            }
            if (obsolete) {
                new StringBuilder("extracting DSO ").append(iDso.a.c);
                if (this.a.setWritable(true, true)) {
                    file = new File(this.a, iDso.a.c);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    available = iDso.b.available();
                    if (available > 1) {
                        fd = randomAccessFile.getFD();
                        j = (long) available;
                        if (VERSION.SDK_INT >= 21) {
                            LollipopSysdeps.fallocateIfSupported(fd, j);
                        }
                    }
                    SysUtil.a(randomAccessFile, iDso.b, ioBuffer);
                    randomAccessFile.setLength(randomAccessFile.getFilePointer());
                    if (file.setExecutable(true, false)) {
                        randomAccessFile.close();
                    } else {
                        throw new IOException("cannot make file executable: " + file);
                    }
                }
                throw new IOException("cannot make directory writable for us: " + this.a);
            }
            iDso.close();
        }
        manifestFile.close();
        new StringBuilder("Finished regenerating DSO store ").append(getClass().getName());
        return;
        if (th2 != null) {
            iDso.close();
        } else {
            iDso.close();
        }
        throw th;
        throw th;
        throw th;
        try {
            throw th;
        } catch (Throwable th22) {
            Throwable th5 = th22;
            th22 = th;
            th = th5;
        }
        if (th22 != null) {
            try {
                manifestFile.close();
            } catch (Throwable th6) {
                th22.addSuppressed(th6);
            }
        } else {
            manifestFile.close();
        }
        throw th;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(e lock, int flags, byte[] deps) throws IOException {
        byte state;
        Throwable th;
        final File stateFileName = new File(this.a, "dso_state");
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        Throwable th2 = null;
        try {
            state = stateFile.readByte();
            if (state != (byte) 1) {
                new StringBuilder("dso store ").append(this.a).append(" regeneration interrupted: wiping clean");
                state = (byte) 0;
            }
        } catch (EOFException e) {
            state = (byte) 0;
        } catch (Throwable th22) {
            Throwable th3 = th22;
            th22 = th;
            th = th3;
        }
        stateFile.close();
        final File depsFileName = new File(this.a, "dso_deps");
        b desiredManifest = null;
        RandomAccessFile depsFile = new RandomAccessFile(depsFileName, "rw");
        try {
            byte[] existingDeps = new byte[((int) depsFile.length())];
            if (depsFile.read(existingDeps) != existingDeps.length) {
                state = (byte) 0;
            }
            if (!Arrays.equals(existingDeps, deps)) {
                state = (byte) 0;
            }
            if (state == (byte) 0 || (flags & 2) != 0) {
                a(stateFileName, (byte) 0);
                e u = a();
                try {
                    desiredManifest = u.b();
                    d idi = u.c();
                    a(state, desiredManifest, idi);
                    idi.close();
                    u.close();
                } catch (Throwable th4) {
                    th = th4;
                    th22 = null;
                    if (th22 == null) {
                        u.close();
                    } else {
                        u.close();
                    }
                    throw th;
                }
            }
            depsFile.close();
            if (desiredManifest == null) {
                return false;
            }
            final b manifest = desiredManifest;
            final byte[] bArr = deps;
            final e eVar = lock;
            Runnable syncer = new Runnable(this) {
                final /* synthetic */ j f;

                public final void run() {
                    Throwable th;
                    Throwable th2;
                    Throwable th3 = null;
                    try {
                        RandomAccessFile depsFile = new RandomAccessFile(depsFileName, "rw");
                        try {
                            depsFile.write(bArr);
                            depsFile.setLength(depsFile.getFilePointer());
                            depsFile.close();
                            DataOutput manifestFile = new RandomAccessFile(new File(this.f.a, "dso_manifest"), "rw");
                            try {
                                manifest.a(manifestFile);
                                manifestFile.close();
                                SysUtil.b(this.f.a);
                                j.a(stateFileName, (byte) 1);
                                new StringBuilder("releasing dso store lock for ").append(this.f.a).append(" (from syncer thread)");
                                eVar.close();
                                return;
                            } catch (Throwable th32) {
                                th2 = th32;
                                th32 = th;
                                th = th2;
                            }
                            throw th;
                            if (th32 != null) {
                                try {
                                    manifestFile.close();
                                } catch (Throwable th4) {
                                    th32.addSuppressed(th4);
                                }
                            } else {
                                manifestFile.close();
                            }
                            throw th;
                            throw th;
                            if (th32 != null) {
                                try {
                                    depsFile.close();
                                } catch (Throwable th42) {
                                    th32.addSuppressed(th42);
                                }
                            } else {
                                depsFile.close();
                            }
                            throw th;
                        } catch (Throwable th322) {
                            th2 = th322;
                            th322 = th;
                            th = th2;
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (Throwable th5) {
                        new StringBuilder("releasing dso store lock for ").append(this.f.a).append(" (from syncer thread)");
                        eVar.close();
                    }
                }
            };
            if ((flags & 1) != 0) {
                new Thread(syncer, "SoSync:" + this.a.getName()).start();
            } else {
                syncer.run();
            }
            return true;
        } catch (Throwable th5) {
            th = th5;
            th22 = null;
            if (th22 != null) {
                try {
                    depsFile.close();
                } catch (Throwable th6) {
                    th22.addSuppressed(th6);
                }
            } else {
                depsFile.close();
            }
            throw th;
        }
        if (th22 != null) {
            try {
                stateFile.close();
            } catch (Throwable th62) {
                th22.addSuppressed(th62);
            }
        } else {
            stateFile.close();
        }
        throw th;
        throw th;
    }

    protected byte[] b() throws IOException {
        Throwable th;
        Parcel parcel = Parcel.obtain();
        e u = a();
        Throwable th2 = null;
        try {
            a[] dsos = u.b().a;
            parcel.writeByte((byte) 1);
            parcel.writeInt(dsos.length);
            for (int i = 0; i < dsos.length; i++) {
                parcel.writeString(dsos[i].c);
                parcel.writeString(dsos[i].d);
            }
            u.close();
            byte[] depsBlock = parcel.marshall();
            parcel.recycle();
            return depsBlock;
        } catch (Throwable th22) {
            Throwable th3 = th22;
            th22 = th;
            th = th3;
        }
        throw th;
        if (th22 != null) {
            try {
                u.close();
            } catch (Throwable th4) {
                th22.addSuppressed(th4);
            }
        } else {
            u.close();
        }
        throw th;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void a(int flags) throws IOException {
        File file = this.a;
        if (file.mkdirs() || file.isDirectory()) {
            e lock = e.a(new File(this.a, "dso_lock"));
            try {
                new StringBuilder("locked dso store ").append(this.a);
                if (a(lock, flags, b())) {
                    lock = null;
                } else {
                    new StringBuilder("dso store is up-to-date: ").append(this.a);
                }
                if (lock != null) {
                    new StringBuilder("releasing dso store lock for ").append(this.a);
                    lock.close();
                    return;
                }
                new StringBuilder("not releasing dso store lock for ").append(this.a).append(" (syncer thread started)");
            } catch (Throwable th) {
                if (lock != null) {
                    new StringBuilder("releasing dso store lock for ").append(this.a);
                    lock.close();
                } else {
                    new StringBuilder("not releasing dso store lock for ").append(this.a).append(" (syncer thread started)");
                }
            }
        } else {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    private Object c(String soName) {
        Object lock;
        synchronized (this.d) {
            lock = this.d.get(soName);
            if (lock == null) {
                lock = new Object();
                this.d.put(soName, lock);
            }
        }
        return lock;
    }

    protected final synchronized void b(String soName) throws IOException {
        synchronized (c(soName)) {
            this.f = soName;
            a(2);
        }
    }

    public final int a(String soName, int loadFlags, ThreadPolicy threadPolicy) throws IOException {
        int a;
        synchronized (c(soName)) {
            a = a(soName, loadFlags, this.a, threadPolicy);
        }
        return a;
    }

    protected j(Context context, String name) {
        super(new File(context.getApplicationInfo().dataDir + "/" + name), 1);
        this.e = context;
    }
}
