package com.skype.hockeyapp;

import com.facebook.common.logging.FLog;
import com.skype.utils.FileUtil;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import net.hockeyapp.android.a;

public class NativeCrashDescriptor implements CrashDescriptor {
    private File a;
    private File b;
    private File c;

    public NativeCrashDescriptor(File crashDirectory, File minidump) {
        this.a = crashDirectory;
        this.b = minidump;
        this.c = new File(minidump.getAbsolutePath() + ".version");
    }

    public final VersionInfo a() {
        IOException e;
        Throwable th;
        String versionName = a.b;
        String versionCode = a.a;
        Closeable fileReader = null;
        Closeable reader = null;
        try {
            Closeable reader2;
            Closeable fileReader2 = new FileReader(this.c);
            try {
                reader2 = new BufferedReader(fileReader2);
            } catch (IOException e2) {
                e = e2;
                fileReader = fileReader2;
                try {
                    FLog.i("NativeCrashDescriptor", e.getMessage());
                    FileUtil.a(reader);
                    FileUtil.a(fileReader);
                    return new VersionInfo(versionName, versionCode);
                } catch (Throwable th2) {
                    th = th2;
                    FileUtil.a(reader);
                    FileUtil.a(fileReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileReader = fileReader2;
                FileUtil.a(reader);
                FileUtil.a(fileReader);
                throw th;
            }
            try {
                String tmp = reader2.readLine();
                if (tmp != null) {
                    versionName = tmp;
                }
                tmp = reader2.readLine();
                if (tmp != null) {
                    versionCode = tmp;
                }
                FileUtil.a(reader2);
                FileUtil.a(fileReader2);
                reader = reader2;
                fileReader = fileReader2;
            } catch (IOException e3) {
                e = e3;
                reader = reader2;
                fileReader = fileReader2;
                FLog.i("NativeCrashDescriptor", e.getMessage());
                FileUtil.a(reader);
                FileUtil.a(fileReader);
                return new VersionInfo(versionName, versionCode);
            } catch (Throwable th4) {
                th = th4;
                reader = reader2;
                fileReader = fileReader2;
                FileUtil.a(reader);
                FileUtil.a(fileReader);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            FLog.i("NativeCrashDescriptor", e.getMessage());
            FileUtil.a(reader);
            FileUtil.a(fileReader);
            return new VersionInfo(versionName, versionCode);
        }
        return new VersionInfo(versionName, versionCode);
    }

    public final void a(PrintWriter writer) throws IOException {
        writer.write("MinidumpContainer");
        this.b.renameTo(new File(this.a, "minidump.dmp"));
        this.c.delete();
    }

    public final File b() {
        return this.a;
    }
}
