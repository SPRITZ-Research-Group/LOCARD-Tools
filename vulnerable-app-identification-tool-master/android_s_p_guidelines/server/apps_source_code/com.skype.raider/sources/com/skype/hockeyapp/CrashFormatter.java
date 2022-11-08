package com.skype.hockeyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import com.facebook.common.logging.FLog;
import com.skype.utils.FileUtil;
import com.skype.utils.ZipUtil;
import com.slowpath.hockeyapp.c;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.hockeyapp.android.a;

public class CrashFormatter implements DumpConstants {
    private static final FilenameFilter a = new FilenameFilter() {
        public final boolean accept(File dir, String filename) {
            return filename.startsWith("memory.info");
        }
    };
    private final File b;
    private final SkypeCrashListener c;
    private boolean d = false;
    private c e;
    private final ConcurrentMap<File, AsyncTask<Context, Void, Void>> f = new ConcurrentHashMap();

    public CrashFormatter(File rootDirectory, SkypeCrashListener crashListener) {
        this.b = rootDirectory;
        this.c = crashListener;
    }

    private File a() {
        File crashDir = new File(this.b, UUID.randomUUID().toString() + ".crash");
        if (crashDir.exists()) {
            FLog.i("AndroidRuntime", "it looks like there is a crash with the same UUID. Overriding ...");
            FileUtil.a(crashDir);
        }
        if (!crashDir.mkdirs()) {
            FLog.e("AndroidRuntime", "Can't locate root crash log directory: " + crashDir.getPath());
        }
        return crashDir;
    }

    public final void a(boolean attachLogsToCrash) {
        this.d = attachLogsToCrash;
    }

    public final void a(c logProvider) {
        this.e = logProvider;
    }

    public final File a(File minidump) {
        File newCrashDir = a();
        a(new NativeCrashDescriptor(newCrashDir, minidump));
        return newCrashDir;
    }

    public final void a(Throwable ex) {
        a(new JavaCrashDescriptor(a(), ex));
    }

    private void a(CrashDescriptor descriptor) {
        Throwable e;
        File crashDir = descriptor.b();
        try {
            FLog.i("AndroidRuntime", "writing crash data to: " + crashDir.getPath());
            if (crashDir.exists()) {
                VersionInfo versionInfo = descriptor.a();
                Closeable writer = null;
                try {
                    Closeable writer2 = new PrintWriter(new CompositeWriter(new BufferedWriter(new FileWriter(new File(crashDir, "crash.log"))), new StringWriter()));
                    try {
                        writer2.write(String.format("Package: %s\n", new Object[]{a.c}));
                        writer2.write(String.format("Version: %s\n", new Object[]{versionInfo.a()}));
                        writer2.write(String.format("Version name: %s\n", new Object[]{versionInfo.b()}));
                        writer2.write(String.format("Android: %s\n", new Object[]{a.d}));
                        writer2.write(String.format("Fingerprint: %s\n", new Object[]{Build.FINGERPRINT}));
                        writer2.write(String.format("Manufacturer: %s\n", new Object[]{a.g}));
                        writer2.write(String.format("Model: %s\n", new Object[]{a.f}));
                        writer2.write("Date: " + new Date() + "\n");
                        writer2.write(String.format("CrashReporter Key: %s\n", new Object[]{a.a().get()}));
                    } catch (Throwable e2) {
                        FLog.e("AndroidRuntime", "Can not get the deviceId as a result of an InterruptedException", e2);
                    } catch (Throwable e22) {
                        FLog.e("AndroidRuntime", "Can not get the deviceId as a result of an ExecutionException", e22);
                    } catch (Throwable th) {
                        e22 = th;
                        writer = writer2;
                    }
                    writer2.write("\n\r");
                    descriptor.a(writer2);
                    FLog.i("AndroidRuntime", logWriter.toString());
                    FileUtil.a(writer2);
                    b(crashDir);
                    FileUtil.a(crashDir, "crash.description", this.c.a());
                    FileUtil.a(crashDir, "crash.contact", this.c.c());
                    FileUtil.a(crashDir, "crash.user", this.c.b());
                    return;
                } catch (Throwable th2) {
                    e22 = th2;
                    FileUtil.a(writer);
                    throw e22;
                }
            }
            FLog.e("AndroidRuntime", "Can't locate root crash log directory: " + crashDir.getPath());
        } catch (Throwable e3) {
            FLog.e("AndroidRuntime", "failed to dump crash", e3);
        }
    }

    public final void b(File crashDir) {
        List<File> logFiles = new ArrayList();
        File[] listFiles = crashDir.listFiles(a);
        if (listFiles != null) {
            Collections.addAll(logFiles, listFiles);
        }
        File file = new File(crashDir, "dump.error.log");
        if (file.exists()) {
            logFiles.add(file);
        }
        if (this.d && this.e != null) {
            logFiles.addAll(this.e.a());
        }
        if (logFiles.size() > 0) {
            File attachFile = new File(crashDir, "attach.zip");
            try {
                ZipUtil.a(logFiles, attachFile);
            } catch (Throwable e) {
                FLog.w("AndroidRuntime", "failed to compress logs", e);
                attachFile.delete();
            }
        }
    }
}
