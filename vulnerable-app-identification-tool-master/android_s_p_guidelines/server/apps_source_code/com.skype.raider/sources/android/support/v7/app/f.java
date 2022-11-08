package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(23)
class f extends e {
    private final UiModeManager t;

    class a extends a {
        final /* synthetic */ f d;

        a(f this$0, Callback callback) {
            this.d = this$0;
            super(this$0, callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
            if (this.d.o()) {
                switch (type) {
                    case 0:
                        return a(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, type);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }
    }

    f(Context context, Window window, a callback) {
        super(context, window, callback);
        this.t = (UiModeManager) context.getSystemService("uimode");
    }

    Callback a(Callback callback) {
        return new a(this, callback);
    }

    final int f(int mode) {
        if (mode == 0 && this.t.getNightMode() == 0) {
            return -1;
        }
        return super.f(mode);
    }
}
