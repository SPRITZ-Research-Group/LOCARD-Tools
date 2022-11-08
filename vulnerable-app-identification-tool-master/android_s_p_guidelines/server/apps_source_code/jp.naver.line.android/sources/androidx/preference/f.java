package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import androidx.core.content.a;

public final class f {
    private Context a;
    private SharedPreferences b;
    private d c;
    private Editor d;
    private boolean e;
    private String f;
    private int g;
    private int h;
    private g i;

    public final d a() {
        return this.c;
    }

    final Editor c() {
        if (this.c != null) {
            return null;
        }
        if (!this.e) {
            return b().edit();
        }
        if (this.d == null) {
            this.d = b().edit();
        }
        return this.d;
    }

    final boolean d() {
        return !this.e;
    }

    public final g e() {
        return this.i;
    }

    public final SharedPreferences b() {
        if (this.c != null) {
            return null;
        }
        if (this.b == null) {
            Context context;
            if (this.h != 1) {
                context = this.a;
            } else {
                context = a.d(this.a);
            }
            this.b = context.getSharedPreferences(this.f, this.g);
        }
        return this.b;
    }
}
