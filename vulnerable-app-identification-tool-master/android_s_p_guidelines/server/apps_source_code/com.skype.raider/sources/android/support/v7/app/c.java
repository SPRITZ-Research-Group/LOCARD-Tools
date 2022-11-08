package android.support.v7.app;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import android.view.Window.Callback;
import java.util.List;

@RequiresApi(24)
final class c extends f {

    class a extends a {
        final /* synthetic */ c b;

        a(c this$0, Callback callback) {
            this.b = this$0;
            super(this$0, callback);
        }

        public final void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
            PanelFeatureState panel = this.b.g(0);
            if (panel == null || panel.j == null) {
                super.onProvideKeyboardShortcuts(data, menu, deviceId);
            } else {
                super.onProvideKeyboardShortcuts(data, panel.j, deviceId);
            }
        }
    }

    c(Context context, Window window, a callback) {
        super(context, window, callback);
    }

    final Callback a(Callback callback) {
        return new a(this, callback);
    }
}
