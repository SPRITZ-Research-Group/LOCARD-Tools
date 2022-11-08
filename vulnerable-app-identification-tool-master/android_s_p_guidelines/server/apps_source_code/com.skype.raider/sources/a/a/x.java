package a.a;

import a.a.w.a;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class x {
    final String a;
    final long[] b;
    boolean c;
    a d;
    long e;
    private int f;
    private File g;

    x(String str, int i, File file) {
        this.a = str;
        this.f = i;
        this.g = file;
        this.b = new long[i];
    }

    public final String a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (long append : this.b) {
            stringBuilder.append(' ').append(append);
        }
        return stringBuilder.toString();
    }

    final void a(String[] strArr) {
        if (strArr.length != this.f) {
            throw b(strArr);
        }
        int i = 0;
        while (i < strArr.length) {
            try {
                this.b[i] = Long.parseLong(strArr[i]);
                i++;
            } catch (NumberFormatException e) {
                throw b(strArr);
            }
        }
    }

    private static IOException b(String[] strArr) {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    public final File a(int i) {
        return new File(this.g, this.a + "." + i);
    }

    public final File b(int i) {
        return new File(this.g, this.a + "." + i + FileType.TEMP);
    }
}
