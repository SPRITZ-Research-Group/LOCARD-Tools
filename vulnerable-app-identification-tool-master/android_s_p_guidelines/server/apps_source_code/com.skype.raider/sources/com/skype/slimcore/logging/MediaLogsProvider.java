package com.skype.slimcore.logging;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaLogsProvider implements LogsProvider {
    private final Context a;

    public MediaLogsProvider(Context context) {
        this.a = context;
    }

    public final File a() {
        File mostRecentLogFile = null;
        List<File> allLogs = b();
        for (int i = 0; i < allLogs.size(); i++) {
            File file = (File) allLogs.get(i);
            if (file.isFile() && (mostRecentLogFile == null || mostRecentLogFile.lastModified() < file.lastModified())) {
                mostRecentLogFile = file;
            }
        }
        return mostRecentLogFile;
    }

    public final List<File> b() {
        List<File> logs = new ArrayList();
        File logDir = this.a.getExternalCacheDir();
        if (logDir == null) {
            return new ArrayList();
        }
        for (File file : logDir.listFiles()) {
            Object obj;
            if (file != null && file.isFile()) {
                String name = file.getName();
                if (name.contains(".htrace0") || name.contains(".htrace1") || (name.contains("msrtc") && name.contains("blog"))) {
                    obj = 1;
                    if (obj != null) {
                        logs.add(file);
                    }
                }
            }
            obj = null;
            if (obj != null) {
                logs.add(file);
            }
        }
        return logs;
    }
}
