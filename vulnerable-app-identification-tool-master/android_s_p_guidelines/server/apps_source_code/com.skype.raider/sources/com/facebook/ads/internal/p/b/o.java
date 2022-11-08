package com.facebook.ads.internal.p.b;

import android.content.Context;
import android.os.Environment;
import java.io.File;

final class o {
    public static File a(Context context) {
        return new File(b(context), "video-cache");
    }

    private static File b(Context context) {
        Object externalStorageState;
        File file;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        if ("mounted".equals(externalStorageState)) {
            file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
            if (!(file.exists() || file.mkdirs())) {
                file = null;
            }
        } else {
            file = null;
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        new StringBuilder("Can't define system cache directory! '").append(str).append("%s' will be used.");
        return new File(str);
    }
}
