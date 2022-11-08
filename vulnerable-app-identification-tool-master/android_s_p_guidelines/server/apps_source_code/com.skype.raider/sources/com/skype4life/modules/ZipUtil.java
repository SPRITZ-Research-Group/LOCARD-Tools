package com.skype4life.modules;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;
import com.facebook.react.module.annotations.ReactModule;
import com.microsoft.skype.a.a;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@ReactModule(name = "ZipUtil")
public class ZipUtil extends ReactContextBaseJavaModule {
    private static final String RN_CLASS = "ZipUtil";
    private ag applicationContext;

    public ZipUtil(ag reactContext) {
        super(reactContext);
        this.applicationContext = reactContext;
    }

    public String getName() {
        return RN_CLASS;
    }

    private static void unzip(String zipFile, String targetDirectory) throws IOException {
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
        FileOutputStream fout;
        try {
            byte[] buffer = new byte[8192];
            while (true) {
                ZipEntry ze = zis.getNextEntry();
                if (ze != null) {
                    File file = new File(targetDirectory, ze.getName());
                    File dir = ze.isDirectory() ? file : file.getParentFile();
                    if (!ze.isDirectory()) {
                        fout = new FileOutputStream(file, false);
                        while (true) {
                            int count = zis.read(buffer);
                            if (count == -1) {
                                fout.close();
                                break;
                            }
                            fout.write(buffer, 0, count);
                        }
                    } else if (!dir.isDirectory()) {
                        dir.mkdirs();
                    }
                } else {
                    zis.close();
                    return;
                }
            }
        } catch (Throwable th) {
            zis.close();
        }
    }

    @ReactMethod
    public void uncompress(final String logFilesURI, final String outputDirURI, final ae promise) {
        a.c.b(new Runnable(this) {
            final /* synthetic */ ZipUtil d;

            public final void run() {
                if (logFilesURI.length() == 0) {
                    promise.a(null);
                    return;
                }
                ap.c();
                String inpath = URI.create(logFilesURI).getPath();
                Object outpath = URI.create(outputDirURI).getPath();
                try {
                    ZipUtil.unzip(inpath, outpath);
                    promise.a(outpath);
                } catch (IOException e) {
                    promise.a(null, "Failed to unzip log files.", e);
                }
            }
        });
    }
}
