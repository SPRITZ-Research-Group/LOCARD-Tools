package androidx.transition;

import android.view.View;
import android.view.ViewGroup;

public final class v {
    private ViewGroup a;
    private Runnable b;

    public final void a() {
        if (a(this.a) == this && this.b != null) {
            this.b.run();
        }
    }

    static v a(View view) {
        return (v) view.getTag(t.transition_current_scene);
    }
}
