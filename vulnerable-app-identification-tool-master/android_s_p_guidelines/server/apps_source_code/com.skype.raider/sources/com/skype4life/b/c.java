package com.skype4life.b;

import com.facebook.common.logging.FLog;
import com.facebook.common.logging.b;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public final class c {
    private static FileHandler a;

    public static void a(File file) {
        b delegate = new b();
        delegate.a(4);
        FLog.setLoggingDelegate(delegate);
        if (a == null) {
            try {
                FileHandler fileHandler = new FileHandler(file.getAbsolutePath(), 2097152, 4, true);
                a = fileHandler;
                fileHandler.setFormatter(new a());
                Logger global = Logger.getLogger("");
                for (Handler loggerHandler : global.getHandlers()) {
                    global.removeHandler(loggerHandler);
                }
                global.addHandler(a);
            } catch (IOException e) {
            }
        }
    }
}
