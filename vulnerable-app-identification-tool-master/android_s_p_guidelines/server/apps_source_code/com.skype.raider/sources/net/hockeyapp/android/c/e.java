package net.hockeyapp.android.c;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.UUID;

final class e {
    private static final Integer b = Integer.valueOf(50);
    ArrayList<File> a = new ArrayList(b.intValue() + 1);
    private final WeakReference<Context> c;
    private WeakReference<f> d;

    e(Context context, f sender) {
        this.c = new WeakReference(context);
        this.d = new WeakReference(sender);
    }

    protected final void a(String[] data) {
        if (d()) {
            StringBuilder buffer = new StringBuilder();
            for (String aData : data) {
                if (buffer.length() > 0) {
                    buffer.append(10);
                }
                buffer.append(aData);
            }
            if (!a(buffer.toString())) {
                return;
            }
        }
        net.hockeyapp.android.f.e.d("HA-MetricsPersistence");
        f sender = c();
        if (sender != null) {
            sender.a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(String data) {
        Boolean isSuccess;
        FileOutputStream outputStream;
        Throwable th;
        File dir = e();
        if (dir == null) {
            return false;
        }
        String uuid = UUID.randomUUID().toString();
        isSuccess = Boolean.valueOf(false);
        outputStream = null;
        try {
            synchronized (this) {
                try {
                    File file = new File(dir, uuid);
                    FileOutputStream outputStream2 = new FileOutputStream(file, true);
                    try {
                        outputStream2.write(data.getBytes());
                        new StringBuilder("Saving data to: ").append(file.toString());
                        net.hockeyapp.android.f.e.d("HA-MetricsPersistence");
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = outputStream2;
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        } catch (Exception e) {
        }
        return isSuccess.booleanValue();
        try {
            net.hockeyapp.android.f.e.e("HA-MetricsPersistence");
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e2) {
                }
            }
            return isSuccess.booleanValue();
        } catch (Throwable th4) {
            th = th4;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final String a(File file) {
        Throwable th;
        StringBuilder buffer = new StringBuilder();
        if (file != null) {
            BufferedReader reader = null;
            try {
                synchronized (this) {
                    try {
                        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        while (true) {
                            try {
                                int c = reader2.read();
                                if (c == -1) {
                                    break;
                                }
                                buffer.append((char) c);
                            } catch (Throwable th2) {
                                th = th2;
                                reader = reader2;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            } catch (Exception e) {
                net.hockeyapp.android.f.e.e("HA-MetricsPersistence");
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (Throwable th4) {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e3) {
                    }
                }
            }
        }
        return buffer.toString();
    }

    protected final boolean a() {
        File file = b();
        c(file);
        return file != null;
    }

    protected final synchronized File b() {
        File file;
        File[] files;
        File dir = e();
        if (dir != null) {
            files = dir.listFiles();
        } else {
            files = null;
        }
        if (files != null) {
            for (File file2 : files) {
                if (!this.a.contains(file2)) {
                    new StringBuilder("The directory ").append(file2).append(" (ADDING TO SERVED AND RETURN)");
                    net.hockeyapp.android.f.e.c("HA-MetricsPersistence");
                    this.a.add(file2);
                    break;
                }
                new StringBuilder("The directory ").append(file2).append(" (WAS ALREADY SERVED)");
                net.hockeyapp.android.f.e.c("HA-MetricsPersistence");
            }
        }
        new StringBuilder("The directory ").append(dir).append(" did not contain any unserved files");
        net.hockeyapp.android.f.e.c("HA-MetricsPersistence");
        file2 = null;
        return file2;
    }

    protected final synchronized void b(File file) {
        if (file == null) {
            net.hockeyapp.android.f.e.d("HA-MetricsPersistence");
        } else if (file.delete()) {
            new StringBuilder("Successfully deleted telemetry file at: ").append(file.toString());
            net.hockeyapp.android.f.e.d("HA-MetricsPersistence");
            this.a.remove(file);
        } else {
            new StringBuilder("Error deleting telemetry file ").append(file.toString());
            net.hockeyapp.android.f.e.d("HA-MetricsPersistence");
        }
    }

    protected final synchronized void c(File file) {
        if (file != null) {
            this.a.remove(file);
        }
    }

    private synchronized boolean d() {
        boolean z;
        File dir = e();
        File[] files = dir != null ? dir.listFiles() : null;
        z = files != null && files.length < b.intValue();
        return z;
    }

    protected final f c() {
        return this.d != null ? (f) this.d.get() : null;
    }

    private File e() {
        Context context = (Context) this.c.get();
        if (!(context == null || context.getFilesDir() == null)) {
            File dir = new File(context.getFilesDir(), "/net.hockeyapp.android/telemetry/");
            if (dir.exists() || dir.mkdirs()) {
                return dir;
            }
            net.hockeyapp.android.f.e.e();
        }
        return null;
    }
}
