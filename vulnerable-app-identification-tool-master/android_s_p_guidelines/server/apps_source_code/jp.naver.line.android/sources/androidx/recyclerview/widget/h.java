package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

final class h implements Executor {
    final Handler a = new Handler(Looper.getMainLooper());

    h() {
    }

    public final void execute(Runnable runnable) {
        this.a.post(runnable);
    }
}
