package com.skype4life.modules;

import android.content.Context;
import android.os.Environment;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.ap;
import com.facebook.react.module.annotations.ReactModule;
import com.microsoft.skype.a.a;
import com.skype.utils.ZipUtil;
import com.skype4life.b;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@ReactModule(name = "ClientLogging")
public class ClientLoggingModule extends ReactContextBaseJavaModule {
    private static final AtomicInteger FileNameSuffix = new AtomicInteger(0);
    private static final String RN_CLASS = "ClientLogging";
    private static final String ZipFileName = "Logs.zip";
    private b appLogsProvider;
    private ag applicationContext;

    public ClientLoggingModule(ag reactContext, b provider) {
        super(reactContext);
        this.applicationContext = reactContext;
        this.appLogsProvider = provider;
    }

    public String getName() {
        return RN_CLASS;
    }

    @ReactMethod
    public void generateFileWithContents(final String contents, final String fileName, final ae promise) {
        a.c.b(new Runnable(this) {
            final /* synthetic */ ClientLoggingModule d;

            public final void run() {
                Throwable e;
                Throwable th;
                File output = new File(ClientLoggingModule.getOutputDirectory(this.d.applicationContext), String.format(Locale.US, "%s", new Object[]{fileName}));
                OutputStream outputStream = null;
                OutputStreamWriter outputWriter = null;
                try {
                    OutputStreamWriter outputWriter2;
                    OutputStream outputStream2 = new FileOutputStream(output);
                    try {
                        outputWriter2 = new OutputStreamWriter(outputStream2);
                    } catch (Exception e2) {
                        e = e2;
                        outputStream = outputStream2;
                        try {
                            FLog.e(ClientLoggingModule.RN_CLASS, "generateFileWithContents failed", e);
                            promise.a(e);
                            if (outputWriter == null) {
                                ClientLoggingModule.closeQuietly(outputWriter);
                            } else if (outputStream == null) {
                                ClientLoggingModule.closeQuietly(outputStream);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (outputWriter == null) {
                                ClientLoggingModule.closeQuietly(outputWriter);
                            } else if (outputStream != null) {
                                ClientLoggingModule.closeQuietly(outputStream);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = outputStream2;
                        if (outputWriter == null) {
                            ClientLoggingModule.closeQuietly(outputWriter);
                        } else if (outputStream != null) {
                            ClientLoggingModule.closeQuietly(outputStream);
                        }
                        throw th;
                    }
                    try {
                        outputWriter2.append(contents);
                        promise.a(output.getAbsolutePath());
                        ClientLoggingModule.closeQuietly(outputWriter2);
                        outputWriter = outputWriter2;
                        outputStream = outputStream2;
                    } catch (Exception e3) {
                        e = e3;
                        outputWriter = outputWriter2;
                        outputStream = outputStream2;
                        FLog.e(ClientLoggingModule.RN_CLASS, "generateFileWithContents failed", e);
                        promise.a(e);
                        if (outputWriter == null) {
                            ClientLoggingModule.closeQuietly(outputWriter);
                        } else if (outputStream == null) {
                            ClientLoggingModule.closeQuietly(outputStream);
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        outputWriter = outputWriter2;
                        outputStream = outputStream2;
                        if (outputWriter == null) {
                            ClientLoggingModule.closeQuietly(outputWriter);
                        } else if (outputStream != null) {
                            ClientLoggingModule.closeQuietly(outputStream);
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    FLog.e(ClientLoggingModule.RN_CLASS, "generateFileWithContents failed", e);
                    promise.a(e);
                    if (outputWriter == null) {
                        ClientLoggingModule.closeQuietly(outputWriter);
                    } else if (outputStream == null) {
                        ClientLoggingModule.closeQuietly(outputStream);
                    }
                }
            }
        });
    }

    @ReactMethod
    public void getMostRecentClientLogFile(ae promise) {
        File mostRecentLogFile = this.appLogsProvider.a();
        if (mostRecentLogFile != null) {
            promise.a(mostRecentLogFile.getAbsolutePath());
        } else {
            promise.a(null);
        }
    }

    @ReactMethod
    public void getZippedClientLogFiles(final al logFiles, final ae promise) {
        a.c.b(new Runnable(this) {
            final /* synthetic */ ClientLoggingModule c;

            public final void run() {
                if (logFiles.size() == 0) {
                    promise.a(null);
                    return;
                }
                ap.c();
                File logZipFile = new File(ClientLoggingModule.getOutputDirectory(this.c.applicationContext), String.format(Locale.US, "logs-%d.zip", new Object[]{Integer.valueOf(ClientLoggingModule.FileNameSuffix.getAndIncrement())}));
                ArrayList<File> files = new ArrayList();
                for (int i = 0; i < logFiles.size(); i++) {
                    files.add(new File(logFiles.getString(i)));
                }
                try {
                    ZipUtil.a(files, logZipFile);
                    Object map = new WritableNativeMap();
                    map.putString(ReactVideoViewManager.PROP_SRC_URI, logZipFile.toURI().toString());
                    map.putDouble("size", (double) logZipFile.length());
                    map.putString("name", ClientLoggingModule.ZipFileName);
                    map.putString("type", "log");
                    promise.a(map);
                } catch (Throwable e) {
                    try {
                        logZipFile.delete();
                    } catch (Exception e2) {
                    }
                    FLog.e(ClientLoggingModule.RN_CLASS, "getZippedClientLogFiles failed", e);
                    promise.a(null, "Failed to zip log files");
                }
            }
        });
    }

    private static File getOutputDirectory(Context context) {
        return Environment.getExternalStorageState() == "mounted" ? context.getExternalCacheDir() : context.getCacheDir();
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    @ReactMethod
    public void getClientLogFiles(ae promise) {
        Object logFilePaths = new WritableNativeArray();
        for (File file : this.appLogsProvider.b()) {
            logFilePaths.pushString(file.getAbsolutePath());
        }
        promise.a(logFilePaths);
    }
}
