package com.facebook.soloader;

import android.os.StrictMode.ThreadPolicy;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.Nullable;

public class c extends h {
    protected final File a;
    protected final int b;

    public c(File soDirectory, int flags) {
        this.a = soDirectory;
        this.b = flags;
    }

    public int a(String soName, int loadFlags, ThreadPolicy threadPolicy) throws IOException {
        return a(soName, loadFlags, this.a, threadPolicy);
    }

    protected final int a(String soName, int loadFlags, File libDir, ThreadPolicy threadPolicy) throws IOException {
        File soFile = new File(libDir, soName);
        if (soFile.exists()) {
            new StringBuilder().append(soName).append(" found on ").append(libDir.getCanonicalPath());
            if ((loadFlags & 1) == 0 || (this.b & 2) == 0) {
                if ((this.b & 1) != 0) {
                    a(soFile, loadFlags, threadPolicy);
                }
                try {
                    SoLoader.b.a(soFile.getAbsolutePath(), loadFlags);
                    return 1;
                } catch (UnsatisfiedLinkError e) {
                    if (e.getMessage().contains("bad ELF magic")) {
                        return 3;
                    }
                    throw e;
                }
            }
            new StringBuilder().append(soName).append(" loaded implicitly");
            return 2;
        }
        new StringBuilder().append(soName).append(" not found on ").append(libDir.getCanonicalPath());
        return 0;
    }

    private static void a(File soFile, int loadFlags, ThreadPolicy threadPolicy) throws IOException {
        String[] dependencies = a(soFile);
        new StringBuilder("Loading lib dependencies: ").append(Arrays.toString(dependencies));
        for (String dependency : dependencies) {
            if (!dependency.startsWith("/")) {
                SoLoader.a(dependency, loadFlags | 1, threadPolicy);
            }
        }
    }

    private static String[] a(File soFile) throws IOException {
        if (SoLoader.a) {
            Api18TraceUtils.a("SoLoader.getElfDependencies[" + soFile.getName() + "]");
        }
        try {
            String[] a = f.a(soFile);
            return a;
        } finally {
            if (SoLoader.a) {
                Api18TraceUtils.a();
            }
        }
    }

    @Nullable
    public final File a(String soName) throws IOException {
        File soFile = new File(this.a, soName);
        return soFile.exists() ? soFile : null;
    }

    public String toString() {
        String path;
        try {
            path = String.valueOf(this.a.getCanonicalPath());
        } catch (IOException e) {
            path = this.a.getName();
        }
        return getClass().getName() + "[root = " + path + " flags = " + this.b + ']';
    }
}
