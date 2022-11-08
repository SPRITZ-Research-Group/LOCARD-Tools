package com.microsoft.applications.telemetry;

import android.content.Context;
import com.microsoft.applications.telemetry.core.TraceHelper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.UUID;

public class InstrumentedExceptionHandler implements UncaughtExceptionHandler {
    private static final String LOG_TAG = ("[ACT]:" + InstrumentedExceptionHandler.class.getSimpleName().toUpperCase());
    private final ILogger logger;
    private final String tag;
    private final UncaughtExceptionHandler uncaughtExceptionHandler;

    public InstrumentedExceptionHandler(ILogger logger, Context appContext, UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (logger == null || appContext == null) {
            throw new IllegalArgumentException(String.format("Cannot have null parameters: logger:%s|appContext:%s", new Object[]{logger, appContext}));
        }
        this.logger = logger;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        this.tag = appContext.getPackageName();
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        String signature = ex.getClass().getName();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        pw.flush();
        pw.close();
        String detail = sw.toString();
        TraceHelper.TraceError(this.tag, detail);
        EventProperties properties = new EventProperties("");
        properties.setPriority(EventPriority.IMMEDIATE);
        this.logger.logFailure(signature, detail, "UnhandledException", UUID.randomUUID().toString(), properties);
        LogManager.pauseTransmission();
        if (this.uncaughtExceptionHandler != null) {
            this.uncaughtExceptionHandler.uncaughtException(thread, ex);
        }
    }
}
