package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class b {
    private static Method a;

    static List<File> a(Context context, ApplicationInfo applicationInfo, File dexDir, boolean forceReload) throws IOException {
        List<File> files;
        Object obj = null;
        new StringBuilder("MultiDexExtractor.load(").append(applicationInfo.sourceDir).append(", ").append(forceReload).append(")");
        File sourceApk = new File(applicationInfo.sourceDir);
        long currentCrc = c.a(sourceApk);
        if (currentCrc == -1) {
            currentCrc--;
        }
        if (!forceReload) {
            SharedPreferences a = a(context);
            if (!(a.getLong("timestamp", -1) == b(sourceApk) && a.getLong("crc", -1) == currentCrc)) {
                obj = 1;
            }
            if (obj == null) {
                try {
                    files = a(context, sourceApk, dexDir);
                } catch (IOException e) {
                }
                new StringBuilder("load found ").append(files.size()).append(" secondary dex files");
                return files;
            }
        }
        files = a(sourceApk, dexDir);
        long b = b(sourceApk);
        int size = files.size() + 1;
        Editor edit = a(context).edit();
        edit.putLong("timestamp", b);
        edit.putLong("crc", currentCrc);
        edit.putInt("dex.number", size);
        if (a != null) {
            try {
                a.invoke(edit, new Object[0]);
            } catch (InvocationTargetException e2) {
            } catch (IllegalAccessException e3) {
            }
            new StringBuilder("load found ").append(files.size()).append(" secondary dex files");
            return files;
        }
        edit.commit();
        new StringBuilder("load found ").append(files.size()).append(" secondary dex files");
        return files;
    }

    private static List<File> a(Context context, File sourceApk, File dexDir) throws IOException {
        String extractedFilePrefix = sourceApk.getName() + ".classes";
        int totalDexNumber = a(context).getInt("dex.number", 1);
        List<File> files = new ArrayList(totalDexNumber);
        int secondaryNumber = 2;
        while (secondaryNumber <= totalDexNumber) {
            File extractedFile = new File(dexDir, extractedFilePrefix + secondaryNumber + ".zip");
            if (extractedFile.isFile()) {
                files.add(extractedFile);
                if (a(extractedFile)) {
                    secondaryNumber++;
                } else {
                    new StringBuilder("Invalid zip file: ").append(extractedFile);
                    throw new IOException("Invalid ZIP file.");
                }
            }
            throw new IOException("Missing extracted secondary dex file '" + extractedFile.getPath() + "'");
        }
        return files;
    }

    private static long b(File archive) {
        long timeStamp = archive.lastModified();
        if (timeStamp == -1) {
            return timeStamp - 1;
        }
        return timeStamp;
    }

    private static List<File> a(File sourceApk, File dexDir) throws IOException {
        String extractedFilePrefix = sourceApk.getName() + ".classes";
        a(dexDir, extractedFilePrefix);
        List<File> files = new ArrayList();
        ZipFile apk = new ZipFile(sourceApk);
        int secondaryNumber = 2;
        try {
            ZipEntry dexFile = apk.getEntry("classes2.dex");
            while (dexFile != null) {
                File extractedFile = new File(dexDir, extractedFilePrefix + secondaryNumber + ".zip");
                files.add(extractedFile);
                new StringBuilder("Extraction is needed for file ").append(extractedFile);
                int numAttempts = 0;
                boolean isExtractionSuccessful = false;
                while (numAttempts < 3 && !isExtractionSuccessful) {
                    numAttempts++;
                    a(apk, dexFile, extractedFile, extractedFilePrefix);
                    isExtractionSuccessful = a(extractedFile);
                    new StringBuilder("Extraction ").append(isExtractionSuccessful ? "success" : "failed").append(" - length ").append(extractedFile.getAbsolutePath()).append(": ").append(extractedFile.length());
                    if (!isExtractionSuccessful) {
                        extractedFile.delete();
                        if (extractedFile.exists()) {
                            new StringBuilder("Failed to delete corrupted secondary dex '").append(extractedFile.getPath()).append("'");
                        }
                    }
                }
                if (isExtractionSuccessful) {
                    secondaryNumber++;
                    dexFile = apk.getEntry("classes" + secondaryNumber + ".dex");
                } else {
                    throw new IOException("Could not create zip file " + extractedFile.getAbsolutePath() + " for secondary dex (" + secondaryNumber + ")");
                }
            }
            return files;
        } finally {
            try {
                apk.close();
            } catch (IOException e) {
            }
        }
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("multidex.version", VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private static void a(File dexDir, final String extractedFilePrefix) throws IOException {
        dexDir.mkdirs();
        if (dexDir.isDirectory()) {
            File[] files = dexDir.listFiles(new FileFilter() {
                public final boolean accept(File pathname) {
                    return !pathname.getName().startsWith(extractedFilePrefix);
                }
            });
            if (files == null) {
                new StringBuilder("Failed to list secondary dex dir content (").append(dexDir.getPath()).append(").");
                return;
            }
            File[] arr$ = files;
            int len$ = files.length;
            for (int i$ = 0; i$ < len$; i$++) {
                File oldFile = arr$[i$];
                new StringBuilder("Trying to delete old file ").append(oldFile.getPath()).append(" of size ").append(oldFile.length());
                if (oldFile.delete()) {
                    new StringBuilder("Deleted old file ").append(oldFile.getPath());
                } else {
                    new StringBuilder("Failed to delete old file ").append(oldFile.getPath());
                }
            }
            return;
        }
        throw new IOException("Failed to create dex directory " + dexDir.getPath());
    }

    private static void a(ZipFile apk, ZipEntry dexFile, File extractTo, String extractedFilePrefix) throws IOException, FileNotFoundException {
        Closeable in = apk.getInputStream(dexFile);
        File tmp = File.createTempFile(extractedFilePrefix, ".zip", extractTo.getParentFile());
        new StringBuilder("Extracting ").append(tmp.getPath());
        ZipOutputStream out;
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
            ZipEntry classesDex = new ZipEntry("classes.dex");
            classesDex.setTime(dexFile.getTime());
            out.putNextEntry(classesDex);
            byte[] buffer = new byte[16384];
            for (int length = in.read(buffer); length != -1; length = in.read(buffer)) {
                out.write(buffer, 0, length);
            }
            out.closeEntry();
            out.close();
            new StringBuilder("Renaming to ").append(extractTo.getPath());
            if (tmp.renameTo(extractTo)) {
                a(in);
                tmp.delete();
                return;
            }
            throw new IOException("Failed to rename \"" + tmp.getAbsolutePath() + "\" to \"" + extractTo.getAbsolutePath() + "\"");
        } catch (Throwable th) {
            a(in);
            tmp.delete();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(File file) {
        try {
            new ZipFile(file).close();
            return true;
        } catch (ZipException e) {
            new StringBuilder("File ").append(file.getAbsolutePath()).append(" is not a valid zip file.");
        } catch (IOException e2) {
            new StringBuilder("Got an IOException trying to open zip file: ").append(file.getAbsolutePath());
        }
        return false;
    }

    private static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    static {
        try {
            a = Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            a = null;
        }
    }
}
