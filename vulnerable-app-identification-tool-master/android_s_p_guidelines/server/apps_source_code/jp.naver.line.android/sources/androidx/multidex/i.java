package androidx.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class i implements Closeable {
    private final File a;
    private final long b;
    private final File c;
    private final RandomAccessFile d;
    private final FileChannel e;
    private final FileLock f;

    i(File file, File file2) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("MultiDexExtractor(");
        stringBuilder.append(file.getPath());
        stringBuilder.append(", ");
        stringBuilder.append(file2.getPath());
        stringBuilder.append(")");
        Log.i("MultiDex", stringBuilder.toString());
        this.a = file;
        this.c = file2;
        this.b = b(file);
        file = new File(file2, "MultiDex.lock");
        this.d = new RandomAccessFile(file, "rw");
        try {
            this.e = this.d.getChannel();
            StringBuilder stringBuilder2 = new StringBuilder("Blocking on lock ");
            stringBuilder2.append(file.getPath());
            Log.i("MultiDex", stringBuilder2.toString());
            this.f = this.e.lock();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file.getPath());
            stringBuilder2.append(" locked");
            Log.i("MultiDex", stringBuilder2.toString());
        } catch (IOException e) {
            a(this.e);
            throw e;
        } catch (IOException e2) {
            a(this.d);
            throw e2;
        }
    }

    final List<? extends File> a(Context context, String str, boolean z) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("MultiDexExtractor.load(");
        stringBuilder.append(this.a.getPath());
        stringBuilder.append(", ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        stringBuilder.append(")");
        Log.i("MultiDex", stringBuilder.toString());
        if (this.f.isValid()) {
            List<? extends File> a;
            StringBuilder stringBuilder2;
            if (!z) {
                Object obj;
                File file = this.a;
                long j = this.b;
                SharedPreferences a2 = a(context);
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append(Param.TIMESTAMP);
                if (a2.getLong(stringBuilder3.toString(), -1) == a(file)) {
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(str);
                    stringBuilder4.append("crc");
                    if (a2.getLong(stringBuilder4.toString(), -1) == j) {
                        obj = null;
                        if (obj == null) {
                            try {
                                a = a(context, str);
                            } catch (Throwable e) {
                                Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e);
                            }
                            stringBuilder2 = new StringBuilder("load found ");
                            stringBuilder2.append(a.size());
                            stringBuilder2.append(" secondary dex files");
                            Log.i("MultiDex", stringBuilder2.toString());
                            return a;
                        }
                    }
                }
                obj = 1;
                if (obj == null) {
                    a = a(context, str);
                    stringBuilder2 = new StringBuilder("load found ");
                    stringBuilder2.append(a.size());
                    stringBuilder2.append(" secondary dex files");
                    Log.i("MultiDex", stringBuilder2.toString());
                    return a;
                }
            }
            if (z) {
                Log.i("MultiDex", "Forced extraction must be performed.");
            } else {
                Log.i("MultiDex", "Detected that extraction must be performed.");
            }
            a = a();
            a(context, str, a(this.a), this.b, a);
            stringBuilder2 = new StringBuilder("load found ");
            stringBuilder2.append(a.size());
            stringBuilder2.append(" secondary dex files");
            Log.i("MultiDex", stringBuilder2.toString());
            return a;
        }
        throw new IllegalStateException("MultiDexExtractor was closed");
    }

    public final void close() throws IOException {
        this.f.release();
        this.e.close();
        this.d.close();
    }

    private List<j> a(Context context, String str) throws IOException {
        String str2 = str;
        Log.i("MultiDex", "loading existing secondary dex files");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.getName());
        stringBuilder.append(".classes");
        String stringBuilder2 = stringBuilder.toString();
        SharedPreferences a = a(context);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str2);
        stringBuilder3.append("dex.number");
        int i = a.getInt(stringBuilder3.toString(), 1);
        List<j> arrayList = new ArrayList(i - 1);
        int i2 = 2;
        while (i2 <= i) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(stringBuilder2);
            stringBuilder4.append(i2);
            stringBuilder4.append(".zip");
            File jVar = new j(r0.c, stringBuilder4.toString());
            if (jVar.isFile()) {
                jVar.a = b(jVar);
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(str2);
                stringBuilder4.append("dex.crc.");
                stringBuilder4.append(i2);
                long j = a.getLong(stringBuilder4.toString(), -1);
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(str2);
                stringBuilder4.append("dex.time.");
                stringBuilder4.append(i2);
                long j2 = a.getLong(stringBuilder4.toString(), -1);
                long lastModified = jVar.lastModified();
                if (j2 == lastModified) {
                    String str3 = stringBuilder2;
                    SharedPreferences sharedPreferences = a;
                    if (j == jVar.a) {
                        arrayList.add(jVar);
                        i2++;
                        stringBuilder2 = str3;
                        a = sharedPreferences;
                    }
                }
                StringBuilder stringBuilder5 = new StringBuilder("Invalid extracted dex: ");
                stringBuilder5.append(jVar);
                stringBuilder5.append(" (key \"");
                stringBuilder5.append(str2);
                stringBuilder5.append("\"), expected modification time: ");
                stringBuilder5.append(j2);
                stringBuilder5.append(", modification time: ");
                stringBuilder5.append(lastModified);
                stringBuilder5.append(", expected crc: ");
                stringBuilder5.append(j);
                stringBuilder5.append(", file crc: ");
                stringBuilder5.append(jVar.a);
                throw new IOException(stringBuilder5.toString());
            }
            stringBuilder = new StringBuilder("Missing extracted secondary dex file '");
            stringBuilder.append(jVar.getPath());
            stringBuilder.append("'");
            throw new IOException(stringBuilder.toString());
        }
        return arrayList;
    }

    private static long a(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    private static long b(File file) throws IOException {
        long a = k.a(file);
        return a == -1 ? a - 1 : a;
    }

    private List<j> a() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.getName());
        stringBuilder.append(".classes");
        String stringBuilder2 = stringBuilder.toString();
        b();
        List<j> arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.a);
        int i = 2;
        File jVar;
        Object obj;
        StringBuilder stringBuilder3;
        try {
            ZipEntry entry = zipFile.getEntry("classes2.dex");
            while (entry != null) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(stringBuilder2);
                stringBuilder4.append(i);
                stringBuilder4.append(".zip");
                jVar = new j(this.c, stringBuilder4.toString());
                arrayList.add(jVar);
                Log.i("MultiDex", "Extraction is needed for file ".concat(String.valueOf(jVar)));
                int i2 = 0;
                obj = null;
                while (i2 < 3 && obj == null) {
                    i2++;
                    a(zipFile, entry, jVar, stringBuilder2);
                    jVar.a = b(jVar);
                    obj = 1;
                    String str = "MultiDex";
                    stringBuilder3 = new StringBuilder("Extraction ");
                    stringBuilder3.append(obj != null ? "succeeded" : "failed");
                    stringBuilder3.append(" '");
                    stringBuilder3.append(jVar.getAbsolutePath());
                    stringBuilder3.append("': length ");
                    stringBuilder3.append(jVar.length());
                    stringBuilder3.append(" - crc: ");
                    stringBuilder3.append(jVar.a);
                    Log.i(str, stringBuilder3.toString());
                    if (obj == null) {
                        jVar.delete();
                        if (jVar.exists()) {
                            stringBuilder3 = new StringBuilder("Failed to delete corrupted secondary dex '");
                            stringBuilder3.append(jVar.getPath());
                            stringBuilder3.append("'");
                            Log.w("MultiDex", stringBuilder3.toString());
                        }
                    }
                }
                if (obj != null) {
                    i++;
                    StringBuilder stringBuilder5 = new StringBuilder("classes");
                    stringBuilder5.append(i);
                    stringBuilder5.append(".dex");
                    entry = zipFile.getEntry(stringBuilder5.toString());
                } else {
                    StringBuilder stringBuilder6 = new StringBuilder("Could not create zip file ");
                    stringBuilder6.append(jVar.getAbsolutePath());
                    stringBuilder6.append(" for secondary dex (");
                    stringBuilder6.append(i);
                    stringBuilder6.append(")");
                    throw new IOException(stringBuilder6.toString());
                }
            }
            try {
                zipFile.close();
            } catch (Throwable e) {
                Log.w("MultiDex", "Failed to close resource", e);
            }
            return arrayList;
        } catch (Throwable e2) {
            stringBuilder3 = new StringBuilder("Failed to read crc from ");
            stringBuilder3.append(jVar.getAbsolutePath());
            Log.w("MultiDex", stringBuilder3.toString(), e2);
            obj = null;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (Throwable e3) {
                Log.w("MultiDex", "Failed to close resource", e3);
            }
        }
    }

    private static void a(Context context, String str, long j, long j2, List<j> list) {
        Editor edit = a(context).edit();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(Param.TIMESTAMP);
        edit.putLong(stringBuilder.toString(), j);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("crc");
        edit.putLong(stringBuilder2.toString(), j2);
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("dex.number");
        edit.putInt(stringBuilder2.toString(), list.size() + 1);
        int i = 2;
        for (j jVar : list) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str);
            stringBuilder3.append("dex.crc.");
            stringBuilder3.append(i);
            edit.putLong(stringBuilder3.toString(), jVar.a);
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str);
            stringBuilder3.append("dex.time.");
            stringBuilder3.append(i);
            edit.putLong(stringBuilder3.toString(), jVar.lastModified());
            i++;
        }
        edit.commit();
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("multidex.version", VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private void b() {
        File[] listFiles = this.c.listFiles(new FileFilter(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public final boolean accept(File file) {
                return !file.getName().equals("MultiDex.lock");
            }
        });
        if (listFiles == null) {
            StringBuilder stringBuilder = new StringBuilder("Failed to list secondary dex dir content (");
            stringBuilder.append(this.c.getPath());
            stringBuilder.append(").");
            Log.w("MultiDex", stringBuilder.toString());
            return;
        }
        for (File file : listFiles) {
            StringBuilder stringBuilder2 = new StringBuilder("Trying to delete old file ");
            stringBuilder2.append(file.getPath());
            stringBuilder2.append(" of size ");
            stringBuilder2.append(file.length());
            Log.i("MultiDex", stringBuilder2.toString());
            if (file.delete()) {
                stringBuilder2 = new StringBuilder("Deleted old file ");
                stringBuilder2.append(file.getPath());
                Log.i("MultiDex", stringBuilder2.toString());
            } else {
                stringBuilder2 = new StringBuilder("Failed to delete old file ");
                stringBuilder2.append(file.getPath());
                Log.w("MultiDex", stringBuilder2.toString());
            }
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        Closeable inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile("tmp-".concat(String.valueOf(str)), ".zip", file.getParentFile());
        StringBuilder stringBuilder = new StringBuilder("Extracting ");
        stringBuilder.append(createTempFile.getPath());
        Log.i("MultiDex", stringBuilder.toString());
        ZipOutputStream zipOutputStream;
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            StringBuilder stringBuilder2;
            if (createTempFile.setReadOnly()) {
                stringBuilder2 = new StringBuilder("Renaming to ");
                stringBuilder2.append(file.getPath());
                Log.i("MultiDex", stringBuilder2.toString());
                if (createTempFile.renameTo(file)) {
                    a(inputStream);
                    createTempFile.delete();
                    return;
                }
                stringBuilder2 = new StringBuilder("Failed to rename \"");
                stringBuilder2.append(createTempFile.getAbsolutePath());
                stringBuilder2.append("\" to \"");
                stringBuilder2.append(file.getAbsolutePath());
                stringBuilder2.append("\"");
                throw new IOException(stringBuilder2.toString());
            }
            stringBuilder2 = new StringBuilder("Failed to mark readonly \"");
            stringBuilder2.append(createTempFile.getAbsolutePath());
            stringBuilder2.append("\" (tmp of \"");
            stringBuilder2.append(file.getAbsolutePath());
            stringBuilder2.append("\")");
            throw new IOException(stringBuilder2.toString());
        } catch (Throwable th) {
            a(inputStream);
            createTempFile.delete();
        }
    }

    private static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (Throwable e) {
            Log.w("MultiDex", "Failed to close resource", e);
        }
    }
}
