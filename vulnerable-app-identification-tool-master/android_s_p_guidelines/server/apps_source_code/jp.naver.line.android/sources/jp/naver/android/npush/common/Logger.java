package jp.naver.android.npush.common;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Logger {
    public static boolean DEBUG_MODE = false;
    private static final int THREAD_POOL_SIZE = 1;
    private static ThreadPoolExecutor excutor;
    private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue();

    private static boolean isDebugMode() {
        if (DEBUG_MODE) {
            return true;
        }
        if (NPushConstant.CLIENT_TYPE == null) {
            return false;
        }
        switch (NPushConstant.CLIENT_TYPE) {
            case REAL_DEBUG:
            case BETA:
                return true;
            default:
                return false;
        }
    }

    public static void m(String str, String str2) {
        Log.i(str, str2);
    }

    public static void v(String str) {
        if (isDebugMode()) {
            formatLog(str);
        }
    }

    public static void d(String str, String str2) {
        if (isDebugMode()) {
            writeLogToFile("[D]".concat(formatLog(str2)));
        }
    }

    public static void d(String str) {
        d(NPushConstant.TAG, str);
    }

    public static void i(String str) {
        if (isDebugMode()) {
            str = formatLog(str);
            Log.i(NPushConstant.TAG, str);
            writeLogToFile("[I]".concat(str));
        }
    }

    public static void w(String str) {
        if (isDebugMode()) {
            str = formatLog(str);
            Log.w(NPushConstant.TAG, str);
            writeLogToFile("[W]".concat(str));
        }
    }

    public static void e(String str) {
        if (isDebugMode()) {
            str = formatLog(str);
            Log.e(NPushConstant.TAG, str);
            writeLogToFile("[E]".concat(str));
        }
    }

    public static void e(Throwable th) {
        e(getStringFromThrowable(th));
    }

    public static void e(String str, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("\n");
        stringBuilder.append(getStringFromThrowable(th));
        e(stringBuilder.toString());
    }

    public static String getStringFromThrowable(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    static void writeLogToFile(final String str) {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            queue.offer(new Runnable() {
                public final void run() {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), NPushConstant.TAG);
                    if (!file.canWrite()) {
                        file.mkdirs();
                    }
                    if (file.isDirectory()) {
                        File file2 = new File(file.getAbsolutePath(), "jp.naver.android.npush");
                        if (!file2.canWrite()) {
                            file2.mkdirs();
                        }
                        if (file2.canWrite()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(Build.MODEL);
                            stringBuilder.append(new SimpleDateFormat("_yyyy_MM_dd").format(new Date()));
                            stringBuilder.append(".txt");
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(new File(file2, stringBuilder.toString()), true);
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(str);
                                stringBuilder2.append("\n");
                                fileOutputStream.write(stringBuilder2.toString().getBytes());
                                fileOutputStream.close();
                            } catch (Throwable e) {
                                Log.e(NPushConstant.TAG, "Could not write filen", e);
                            }
                        }
                    }
                }
            });
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, queue);
        excutor = threadPoolExecutor;
        threadPoolExecutor.prestartAllCoreThreads();
    }

    public static String formatLog(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
        stringBuilder.append(" [");
        stringBuilder.append(Thread.currentThread().getId());
        stringBuilder.append("] ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
