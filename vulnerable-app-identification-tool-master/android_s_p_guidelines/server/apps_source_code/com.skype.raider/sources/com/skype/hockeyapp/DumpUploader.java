package com.skype.hockeyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.network.e;
import com.skype.utils.FileUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DumpUploader {
    private static final FileFilter a = new FileFilter() {
        public final boolean accept(File file) {
            return file.isFile() && file.getName().endsWith("dmp");
        }
    };
    private static final FileFilter b = new FileFilter() {
        public final boolean accept(File file) {
            return file.isDirectory() && file.getName().endsWith(".crash");
        }
    };
    private final ConnectivityManager c;
    private final File d;
    private final CrashFormatter e;
    private final CountDownLatch f = new CountDownLatch(1);
    private final SharedPreferences g;
    private volatile boolean h = false;
    private AtomicBoolean i = new AtomicBoolean(false);

    public DumpUploader(Context context, File crashesDirectory, CrashFormatter crashFormatter) {
        this.c = (ConnectivityManager) context.getSystemService("connectivity");
        this.d = crashesDirectory;
        this.e = crashFormatter;
        this.g = context.getSharedPreferences("DumpUploader", 0);
        final File file = this.d;
        if (this.i.compareAndSet(false, true)) {
            FLog.i("DumpUploader", "Preparing check for new crash dumps");
            new Thread(new Runnable(this) {
                final /* synthetic */ DumpUploader b;

                public final void run() {
                    try {
                        FLog.i("DumpUploader", "Started check for new crash dumps");
                        List<File> allDumps = new ArrayList(Arrays.asList(file.listFiles(DumpUploader.a)));
                        allDumps.addAll(Arrays.asList(file.listFiles(DumpUploader.b)));
                        this.b.h = !this.b.g.getStringSet("KnownDumps", new HashSet()).containsAll(DumpUploader.c(allDumps));
                        this.b.a((List) allDumps);
                    } catch (Exception e) {
                    } finally {
                        this.b.f.countDown();
                    }
                }
            }, "DumpUploader").start();
        }
    }

    final boolean a() {
        try {
            this.f.await();
        } catch (Throwable e) {
            FLog.e("DumpUploader", "Interrupted while waiting for new crashes check", e);
        }
        return this.h;
    }

    private static boolean a(File dumpDir) {
        Throwable e;
        Throwable th;
        File retry = new File(dumpDir, "retry.log");
        if (!retry.exists()) {
            return a(retry, 1);
        }
        Closeable reader = null;
        int retryCount = -1;
        try {
            Closeable reader2 = new FileReader(retry);
            try {
                retryCount = reader2.read();
                FileUtil.a(reader2);
                reader = reader2;
            } catch (IOException e2) {
                e = e2;
                reader = reader2;
                try {
                    FLog.w("DumpUploader", "failed to read retry data", e);
                    FileUtil.a(reader);
                    if (retryCount >= 3) {
                    }
                    FLog.i("DumpUploader", "max retry count is reached: " + retryCount);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    FileUtil.a(reader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                FileUtil.a(reader);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            FLog.w("DumpUploader", "failed to read retry data", e);
            FileUtil.a(reader);
            if (retryCount >= 3) {
            }
            FLog.i("DumpUploader", "max retry count is reached: " + retryCount);
            return false;
        }
        if (retryCount >= 3 && retryCount > 0) {
            return a(retry, retryCount + 1);
        }
        FLog.i("DumpUploader", "max retry count is reached: " + retryCount);
        return false;
    }

    private static boolean a(File retry, int value) {
        Throwable th;
        Closeable writer = null;
        try {
            Closeable writer2 = new FileWriter(retry);
            try {
                writer2.write(value);
                FileUtil.a(writer2);
                writer = writer2;
                return true;
            } catch (IOException e) {
                writer = writer2;
                try {
                    FLog.w("DumpUploader", "Can't update retry file", retry.getPath());
                    FileUtil.a(writer);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    FileUtil.a(writer);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                writer = writer2;
                FileUtil.a(writer);
                throw th;
            }
        } catch (IOException e2) {
            FLog.w("DumpUploader", "Can't update retry file", retry.getPath());
            FileUtil.a(writer);
            return false;
        }
    }

    private static boolean a(Builder builder, String fieldName, File file) {
        if (!file.exists()) {
            return false;
        }
        builder.addFormDataPart(fieldName, file.getName(), RequestBody.create(MediaType.parse("text/csv"), file));
        return true;
    }

    private static boolean b(Builder builder, String fieldName, File file) {
        IOException e;
        Throwable th;
        boolean result = false;
        if (file.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[4096];
            Closeable inputStream = null;
            try {
                Closeable inputStream2 = new FileInputStream(file);
                while (true) {
                    try {
                        int bytesRead = inputStream2.read(buffer, 0, 4096);
                        if (bytesRead <= 0) {
                            result = true;
                            FileUtil.a(inputStream2);
                            inputStream = inputStream2;
                            break;
                        }
                        stringBuilder.append(new String(buffer, 0, bytesRead));
                    } catch (IOException e2) {
                        e = e2;
                        inputStream = inputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = inputStream2;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    FileUtil.a(inputStream);
                    builder.addFormDataPart(fieldName, stringBuilder.toString());
                    return result;
                } catch (Throwable th3) {
                    th = th3;
                    FileUtil.a(inputStream);
                    throw th;
                }
            }
            builder.addFormDataPart(fieldName, stringBuilder.toString());
        }
        return result;
    }

    private void a(List<File> dumps) {
        Set<String> knownDumps = new HashSet(this.g.getStringSet("KnownDumps", new HashSet()));
        List<String> toAdd = c(dumps);
        knownDumps.addAll(toAdd);
        FLog.i("DumpUploader", "Marking dumps as known: %s", TextUtils.join(",", toAdd));
        Editor editor = this.g.edit();
        editor.putStringSet("KnownDumps", knownDumps);
        editor.apply();
    }

    private void b(List<File> dumps) {
        Set<String> knownDumps = new HashSet(this.g.getStringSet("KnownDumps", new HashSet()));
        List<String> toRemove = c(dumps);
        knownDumps.removeAll(toRemove);
        FLog.i("DumpUploader", "Removing dumps from known: %s", TextUtils.join(",", toRemove));
        Editor editor = this.g.edit();
        editor.putStringSet("KnownDumps", knownDumps);
        editor.apply();
    }

    private static List<String> c(List<File> files) {
        List<String> names = new ArrayList();
        if (files != null) {
            for (File file : files) {
                names.add(file.getName());
            }
        }
        return names;
    }

    public final void a(final String appId) {
        Object obj;
        NetworkInfo activeNetworkInfo = this.c.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            new Thread(new Runnable(this) {
                final /* synthetic */ DumpUploader b;

                public final void run() {
                    try {
                        DumpUploader.a(this.b, appId, DumpUploader.a(this.b, this.b.d));
                    } catch (Throwable e) {
                        FLog.e("DumpUploader", "Interrupted while uploading crashes", e);
                    }
                }
            }, "DumpUploader").start();
        }
    }

    static /* synthetic */ List a(DumpUploader x0, File x1) throws InterruptedException {
        Object arrayList = new ArrayList();
        if (x1 != null && x1.exists()) {
            if (!x0.f.await(5, TimeUnit.SECONDS)) {
                FLog.w("DumpUploader", "Check for existing dump files took too long");
            }
            File[] listFiles = x1.listFiles(a);
            if (listFiles != null) {
                for (File file : listFiles) {
                    x0.a(Collections.singletonList(x0.e.a(file)));
                    x0.b(Collections.singletonList(file));
                }
            }
            File[] listFiles2 = x1.listFiles(b);
            if (listFiles2 != null) {
                Collections.addAll(arrayList, listFiles2);
            }
        }
        return arrayList;
    }

    static /* synthetic */ void a(DumpUploader x0, String x1, Collection x2) {
        String str = "https://rink.hockeyapp.net/api/2/apps/" + x1 + "/crashes/upload";
        for (final File file : x2) {
            if (file.exists()) {
                File file2 = new File(file, "crash.log");
                File file3 = new File(file, "minidump.dmp");
                File file4 = new File(file, "attach.zip");
                File file5 = new File(file, "crash.description");
                File file6 = new File(file, "crash.contact");
                File file7 = new File(file, "crash.user");
                if (!file2.exists()) {
                    FLog.w("DumpUploader", "There is no crash dump, cleaning all existing files");
                    FileUtil.a(file);
                    x0.b(Collections.singletonList(file));
                } else if (a(file)) {
                    Builder type = new Builder().setType(MultipartBody.FORM);
                    String str2 = a(type, "attachment0", file3) ? "attachment1" : "attachment0";
                    a(type, "log", file2);
                    if (!file4.exists()) {
                        x0.e.b(file);
                    }
                    a(type, str2, file4);
                    a(type, "description", file5);
                    b(type, "contact", file6);
                    b(type, "userID", file7);
                    e.a().newCall(new Request.Builder().url(str).post(type.build()).build()).enqueue(new Callback(x0) {
                        final /* synthetic */ DumpUploader b;

                        public final void onFailure(Call call, IOException e) {
                            FLog.w("DumpUploader", "request failed", (Throwable) e);
                        }

                        public final void onResponse(Call call, Response response) throws IOException {
                            FLog.i("DumpUploader", "crash upload response: " + response.code());
                            FileUtil.a(file);
                            this.b.b(Collections.singletonList(file));
                            response.close();
                        }
                    });
                } else {
                    FileUtil.a(file);
                }
            }
        }
    }
}
