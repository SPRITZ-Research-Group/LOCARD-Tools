package com.skype.slimcore.logging;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SkyLibLogsProvider implements LogsProvider {
    private Context a;

    public SkyLibLogsProvider(Context context) {
        this.a = context;
    }

    public final File a() {
        List<File> allLogs = b();
        return allLogs.isEmpty() ? null : (File) allLogs.get(0);
    }

    public final List<File> b() {
        List<File> logs = new ArrayList();
        File logDir = this.a.getExternalCacheDir();
        if (logDir == null) {
            return new ArrayList();
        }
        File[] logFiles = logDir.listFiles();
        if (logFiles == null) {
            return new ArrayList();
        }
        for (File file : logFiles) {
            Object obj;
            if (file != null && file.isFile()) {
                String name = file.getName();
                int lastIndexOf = name.lastIndexOf(".");
                if (lastIndexOf != -1) {
                    String substring = name.substring(lastIndexOf);
                    if ((name.contains("skylib") || name.contains("slimcore")) && (substring.contains(".log") || substring.contains(".blog"))) {
                        obj = 1;
                        if (obj != null) {
                            logs.add(file);
                        }
                    }
                }
            }
            obj = null;
            if (obj != null) {
                logs.add(file);
            }
        }
        Collections.sort(logs, new Comparator<File>(this) {
            final /* synthetic */ SkyLibLogsProvider a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                File file = (File) obj2;
                long lastModified = ((File) obj).lastModified();
                long lastModified2 = file.lastModified();
                if (lastModified < lastModified2) {
                    return -1;
                }
                return lastModified == lastModified2 ? 0 : 1;
            }
        });
        return logs;
    }
}
