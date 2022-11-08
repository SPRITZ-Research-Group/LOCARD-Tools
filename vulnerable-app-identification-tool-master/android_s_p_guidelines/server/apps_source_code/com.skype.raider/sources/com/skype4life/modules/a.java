package com.skype4life.modules;

import android.content.Context;
import com.skype4life.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class a implements b {
    private Context a;

    public a(Context context) {
        this.a = context;
    }

    public final File a() {
        File mostRecentLogFile = null;
        File cacheDir = this.a.getExternalCacheDir();
        for (int i = 0; i < 4; i++) {
            File logFile = a(cacheDir, i);
            if (logFile.isFile() && (mostRecentLogFile == null || mostRecentLogFile.lastModified() < logFile.lastModified())) {
                mostRecentLogFile = logFile;
            }
        }
        return mostRecentLogFile;
    }

    public final List<File> b() {
        File cacheDir = this.a.getExternalCacheDir();
        List<File> result = new ArrayList();
        for (int i = 0; i < 4; i++) {
            File logFile = a(cacheDir, i);
            if (logFile.isFile()) {
                result.add(logFile);
            }
        }
        return result;
    }

    private static File a(File dir, int index) {
        return new File(dir, String.format(Locale.US, "%s.%d.log", new Object[]{"com.skype.raider", Integer.valueOf(index)}));
    }
}
