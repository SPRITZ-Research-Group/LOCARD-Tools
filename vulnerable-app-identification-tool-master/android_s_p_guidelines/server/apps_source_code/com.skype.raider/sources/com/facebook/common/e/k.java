package com.facebook.common.e;

import com.facebook.common.internal.l;
import java.io.OutputStream;

public abstract class k extends OutputStream {
    public abstract h a();

    public abstract int b();

    public void close() {
        try {
            super.close();
        } catch (Throwable e) {
            l.b(e);
        }
    }
}
