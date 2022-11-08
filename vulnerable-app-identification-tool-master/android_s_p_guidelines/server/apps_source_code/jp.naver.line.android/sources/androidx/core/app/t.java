package androidx.core.app;

import android.content.ComponentName;
import android.content.Intent;

abstract class t {
    final ComponentName c;
    boolean d;
    int e;

    public void a() {
    }

    abstract void a(Intent intent);

    public void b() {
    }

    public void c() {
    }

    t(ComponentName componentName) {
        this.c = componentName;
    }

    final void a(int i) {
        if (!this.d) {
            this.d = true;
            this.e = i;
        } else if (this.e != i) {
            StringBuilder stringBuilder = new StringBuilder("Given job ID ");
            stringBuilder.append(i);
            stringBuilder.append(" is different than previous ");
            stringBuilder.append(this.e);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
