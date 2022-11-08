package android.support.v4.util;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.io.Writer;

@RestrictTo({a.LIBRARY_GROUP})
public final class e extends Writer {
    private final String a;
    private StringBuilder b = new StringBuilder(128);

    public e(String tag) {
        this.a = tag;
    }

    public final void close() {
        a();
    }

    public final void flush() {
        a();
    }

    public final void write(char[] buf, int offset, int count) {
        for (int i = 0; i < count; i++) {
            char c = buf[offset + i];
            if (c == 10) {
                a();
            } else {
                this.b.append(c);
            }
        }
    }

    private void a() {
        if (this.b.length() > 0) {
            this.b.delete(0, this.b.length());
        }
    }
}
