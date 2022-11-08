package a.a;

import android.util.Base64;
import com.appboy.f.c;
import com.appboy.f.i;

public abstract class ek implements ed {
    private static final String a = c.a(ek.class);
    private long b;
    private long c;
    private av d;

    protected ek() {
        this.c = co.c();
        this.b = this.c / 1000;
    }

    protected ek(av avVar) {
        this();
        this.d = avVar;
    }

    public final long c() {
        return this.b;
    }

    public final long d() {
        return this.c;
    }

    public final av e() {
        return this.d;
    }

    protected static String a(String str) {
        String str2 = null;
        if (i.c(str)) {
            return str2;
        }
        try {
            return new String(Base64.decode(str, 0)).split("_")[0];
        } catch (Throwable e) {
            c.d(a, "Unexpected error decoding Base64 encoded campaign Id " + str, e);
            return str2;
        }
    }
}
