package a.a;

import com.appboy.f.c;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public final class w {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final String c = c.a(w.class);
    private static final OutputStream q = new OutputStream() {
        public final void write(int intToWrite) {
        }
    };
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File d;
    private final File e;
    private final File f;
    private final File g;
    private final int h;
    private long i;
    private final int j;
    private long k = 0;
    private Writer l;
    private final LinkedHashMap<String, x> m = new LinkedHashMap(0, 0.75f, true);
    private int n;
    private long o = 0;
    private final Callable<Void> p = new Callable<Void>(this) {
        final /* synthetic */ w a;

        {
            this.a = r1;
        }

        public final /* synthetic */ Object call() {
            return a();
        }

        private Void a() {
            synchronized (this.a) {
                if (this.a.l == null) {
                } else {
                    this.a.h();
                    if (this.a.e()) {
                        this.a.d();
                        this.a.n = 0;
                    }
                }
            }
            return null;
        }
    };

    public final class a {
        final /* synthetic */ w a;
        private final x b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        class a extends FilterOutputStream {
            final /* synthetic */ a a;

            /* synthetic */ a(a aVar, OutputStream outputStream, byte b) {
                this(aVar, outputStream);
            }

            private a(a aVar, OutputStream outputStream) {
                this.a = aVar;
                super(outputStream);
            }

            public final void write(int oneByte) {
                try {
                    this.out.write(oneByte);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public final void write(byte[] buffer, int offset, int length) {
                try {
                    this.out.write(buffer, offset, length);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public final void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }
        }

        /* synthetic */ a(w wVar, x xVar, byte b) {
            this(wVar, xVar);
        }

        private a(w wVar, x xVar) {
            this.a = wVar;
            this.b = xVar;
            this.c = xVar.c ? null : new boolean[wVar.j];
        }

        public final OutputStream a() {
            if (this.a.j <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + this.a.j);
            }
            OutputStream a;
            synchronized (this.a) {
                if (this.b.d != this) {
                    throw new IllegalStateException();
                }
                OutputStream fileOutputStream;
                if (!this.b.c) {
                    this.c[0] = true;
                }
                File b = this.b.b(0);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    this.a.d.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        a = w.q;
                    }
                }
                a = new a(this, fileOutputStream, (byte) 0);
            }
            return a;
        }

        public final void b() {
            if (this.d) {
                this.a.a(this, false);
                this.a.c(this.b.a);
            } else {
                this.a.a(this, true);
            }
            this.e = true;
        }

        public final void c() {
            this.a.a(this, false);
        }
    }

    public final class b implements Closeable {
        final /* synthetic */ w a;
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        /* synthetic */ b(w wVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b) {
            this(wVar, str, j, inputStreamArr, jArr);
        }

        private b(w wVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.a = wVar;
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public final InputStream a() {
            return this.d[0];
        }

        public final void close() {
            for (Closeable a : this.d) {
                z.a(a);
            }
        }
    }

    private w(File file) {
        this.d = file;
        this.h = 1;
        this.e = new File(file, "journal");
        this.f = new File(file, "journal.tmp");
        this.g = new File(file, "journal.bkp");
        this.j = 1;
        this.i = 52428800;
    }

    public static w a(File file) {
        if (52428800 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        w wVar = new w(file);
        if (wVar.e.exists()) {
            try {
                wVar.b();
                wVar.c();
                return wVar;
            } catch (IOException e) {
                c.g(c, "DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                wVar.g();
                z.a(wVar.d);
            }
        }
        file.mkdirs();
        wVar = new w(file);
        wVar.d();
        return wVar;
    }

    private void b() {
        int i = 0;
        Closeable yVar = new y(new FileInputStream(this.e), z.a);
        int i2;
        try {
            String a = yVar.a();
            String a2 = yVar.a();
            String a3 = yVar.a();
            String a4 = yVar.a();
            String a5 = yVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.h).equals(a3) && Integer.toString(this.j).equals(a4) && "".equals(a5)) {
                while (true) {
                    i2 = i;
                    a3 = yVar.a();
                    int indexOf = a3.indexOf(32);
                    if (indexOf == -1) {
                        throw new IOException("unexpected journal line: " + a3);
                    }
                    i = indexOf + 1;
                    int indexOf2 = a3.indexOf(32, i);
                    x xVar;
                    if (indexOf2 != -1) {
                        a = a3.substring(i, indexOf2);
                        xVar = (x) this.m.get(a);
                        if (xVar == null) {
                            xVar = new x(a, this.j, this.d);
                            this.m.put(a, xVar);
                        }
                        if (indexOf2 == -1) {
                        }
                        if (indexOf2 != -1) {
                        }
                        if (indexOf2 == -1) {
                            break;
                        }
                        break;
                    }
                    String substring = a3.substring(i);
                    if (indexOf == 6 && a3.startsWith("REMOVE")) {
                        this.m.remove(substring);
                    } else {
                        a = substring;
                        xVar = (x) this.m.get(a);
                        if (xVar == null) {
                            xVar = new x(a, this.j, this.d);
                            this.m.put(a, xVar);
                        }
                        if (indexOf2 == -1 && indexOf == 5 && a3.startsWith("CLEAN")) {
                            String[] split = a3.substring(indexOf2 + 1).split(" ");
                            xVar.c = true;
                            xVar.d = null;
                            xVar.a(split);
                        } else if (indexOf2 != -1 && indexOf == 5 && a3.startsWith("DIRTY")) {
                            xVar.d = new a(this, xVar, (byte) 0);
                        } else if (indexOf2 == -1 && indexOf == 4 && a3.startsWith("READ")) {
                        }
                    }
                    i = i2 + 1;
                }
                throw new IOException("unexpected journal line: " + a3);
            }
            throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
        } catch (EOFException e) {
            this.n = i2 - this.m.size();
            if (yVar.b()) {
                d();
            } else {
                this.l = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e, true), z.a));
            }
            z.a(yVar);
        } catch (Throwable th) {
            z.a(yVar);
        }
    }

    private void c() {
        b(this.f);
        Iterator it = this.m.values().iterator();
        while (it.hasNext()) {
            x xVar = (x) it.next();
            int i;
            if (xVar.d == null) {
                for (i = 0; i < this.j; i++) {
                    this.k += xVar.b[i];
                }
            } else {
                xVar.d = null;
                for (i = 0; i < this.j; i++) {
                    b(xVar.a(i));
                    b(xVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void d() {
        if (this.l != null) {
            this.l.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f), z.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (x xVar : this.m.values()) {
                if (xVar.d != null) {
                    bufferedWriter.write("DIRTY " + xVar.a + 10);
                } else {
                    bufferedWriter.write("CLEAN " + xVar.a + xVar.a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.e.exists()) {
                a(this.e, this.g, true);
            }
            a(this.f, this.e, false);
            this.g.delete();
            this.l = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e, true), z.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void b(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) {
        if (z) {
            b(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public final synchronized b a(String str) {
        b bVar = null;
        synchronized (this) {
            f();
            e(str);
            x xVar = (x) this.m.get(str);
            if (xVar != null) {
                if (xVar.c) {
                    InputStream[] inputStreamArr = new InputStream[this.j];
                    int i = 0;
                    while (i < this.j) {
                        try {
                            inputStreamArr[i] = new FileInputStream(xVar.a(i));
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.j && inputStreamArr[i2] != null) {
                                z.a(inputStreamArr[i2]);
                                i2++;
                            }
                        }
                    }
                    this.n++;
                    this.l.append("READ " + str + 10);
                    if (e()) {
                        this.b.submit(this.p);
                    }
                    bVar = new b(this, str, xVar.e, inputStreamArr, xVar.b, (byte) 0);
                }
            }
        }
        return bVar;
    }

    public final a b(String str) {
        return d(str);
    }

    private synchronized a d(String str) {
        a aVar;
        f();
        e(str);
        x xVar = (x) this.m.get(str);
        if (-1 == -1 || (xVar != null && xVar.e == -1)) {
            x xVar2;
            if (xVar == null) {
                xVar = new x(str, this.j, this.d);
                this.m.put(str, xVar);
                xVar2 = xVar;
            } else if (xVar.d != null) {
                aVar = null;
            } else {
                xVar2 = xVar;
            }
            aVar = new a(this, xVar2, (byte) 0);
            xVar2.d = aVar;
            this.l.write("DIRTY " + str + 10);
            this.l.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    private synchronized void a(a aVar, boolean z) {
        int i = 0;
        synchronized (this) {
            x a = aVar.b;
            if (a.d != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.c) {
                    int i2 = 0;
                    while (i2 < this.j) {
                        if (!aVar.c[i2]) {
                            aVar.c();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.c();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.j) {
                File b = a.b(i);
                if (!z) {
                    b(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.b[i];
                    long length = a2.length();
                    a.b[i] = length;
                    this.k = (this.k - j) + length;
                }
                i++;
            }
            this.n++;
            a.d = null;
            if ((a.c | z) != 0) {
                a.c = true;
                this.l.write("CLEAN " + a.a + a.a() + 10);
                if (z) {
                    long j2 = this.o;
                    this.o = 1 + j2;
                    a.e = j2;
                }
            } else {
                this.m.remove(a.a);
                this.l.write("REMOVE " + a.a + 10);
            }
            this.l.flush();
            if (this.k > this.i || e()) {
                this.b.submit(this.p);
            }
        }
    }

    private boolean e() {
        return this.n >= 2000 && this.n >= this.m.size();
    }

    public final synchronized boolean c(String str) {
        boolean z;
        int i = 0;
        synchronized (this) {
            f();
            e(str);
            x xVar = (x) this.m.get(str);
            if (xVar == null || xVar.d != null) {
                z = false;
            } else {
                while (i < this.j) {
                    File a = xVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.k -= xVar.b[i];
                        xVar.b[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.n++;
                this.l.append("REMOVE " + str + 10);
                this.m.remove(str);
                if (e()) {
                    this.b.submit(this.p);
                }
                z = true;
            }
        }
        return z;
    }

    private void f() {
        if (this.l == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private synchronized void g() {
        if (this.l != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                x xVar = (x) it.next();
                if (xVar.d != null) {
                    xVar.d.c();
                }
            }
            h();
            this.l.close();
            this.l = null;
        }
    }

    private void h() {
        while (this.k > this.i) {
            c((String) ((Entry) this.m.entrySet().iterator().next()).getKey());
        }
    }

    private static void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
