package com.facebook.soloader;

import android.os.StrictMode.ThreadPolicy;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

public abstract class h {
    public abstract int a(String str, int i, ThreadPolicy threadPolicy) throws IOException;

    @Nullable
    public abstract File a(String str) throws IOException;

    protected void a(int flags) throws IOException {
    }

    public String toString() {
        return getClass().getName();
    }
}
