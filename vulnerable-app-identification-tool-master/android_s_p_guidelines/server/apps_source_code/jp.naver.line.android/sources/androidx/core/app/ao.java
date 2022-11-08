package androidx.core.app;

import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

public final class ao {
    private final String a;
    private final Set<String> b = new HashSet();
    private final Bundle c = new Bundle();
    private CharSequence d;
    private CharSequence[] e;
    private boolean f = true;

    public ao(String str) {
        this.a = str;
    }

    public final ao a(CharSequence charSequence) {
        this.d = charSequence;
        return this;
    }

    public final an a() {
        return new an(this.a, this.d, this.e, this.f, this.c, this.b);
    }
}
