package android.support.v4.view;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

public final class l {
    private final ViewGroup a;
    private int b;

    public l(@NonNull ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public final int a() {
        return this.b;
    }

    public final void a(int axes) {
        this.b = axes;
    }

    public final void b() {
        this.b = 0;
    }
}
