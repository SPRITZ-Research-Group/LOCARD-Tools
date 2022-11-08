package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.StrictMode.ThreadPolicy;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

public final class b extends h {
    private Context a;
    private int b;
    private c c;

    public b(Context context, int flags) {
        this.a = context.getApplicationContext();
        if (this.a == null) {
            this.a = context;
        }
        this.b = flags;
        this.c = new c(new File(this.a.getApplicationInfo().nativeLibraryDir), flags);
    }

    public final boolean a() throws IOException {
        try {
            File nativeLibDir = this.c.a;
            Context updatedContext = this.a.createPackageContext(this.a.getPackageName(), 0);
            File updatedNativeLibDir = new File(updatedContext.getApplicationInfo().nativeLibraryDir);
            if (nativeLibDir.equals(updatedNativeLibDir)) {
                return false;
            }
            new StringBuilder("Native library directory updated from ").append(nativeLibDir).append(" to ").append(updatedNativeLibDir);
            this.b |= 1;
            this.c = new c(updatedNativeLibDir, this.b);
            this.c.a(this.b);
            this.a = updatedContext;
            return true;
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public final int a(String soName, int loadFlags, ThreadPolicy threadPolicy) throws IOException {
        return this.c.a(soName, loadFlags, threadPolicy);
    }

    @Nullable
    public final File a(String soName) throws IOException {
        return this.c.a(soName);
    }

    protected final void a(int flags) throws IOException {
        this.c.a(flags);
    }

    public final String toString() {
        return this.c.toString();
    }
}
