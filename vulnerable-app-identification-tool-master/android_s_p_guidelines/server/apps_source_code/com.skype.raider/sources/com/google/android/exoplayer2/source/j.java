package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.a;
import java.util.Arrays;

public final class j {
    public final int a = 1;
    private final Format[] b;
    private int c;

    public j(Format... formats) {
        a.b(true);
        this.b = formats;
    }

    public final Format a(int index) {
        return this.b[index];
    }

    public final int a(Format format) {
        for (int i = 0; i < this.b.length; i++) {
            if (format == this.b[i]) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
        }
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j other = (j) obj;
        if (this.a == other.a && Arrays.equals(this.b, other.b)) {
            return true;
        }
        return false;
    }
}
