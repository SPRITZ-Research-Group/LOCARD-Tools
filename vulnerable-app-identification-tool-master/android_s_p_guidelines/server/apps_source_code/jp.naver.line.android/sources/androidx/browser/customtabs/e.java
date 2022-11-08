package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import defpackage.h;

public abstract class e implements ServiceConnection {
    public abstract void onCustomTabsServiceConnected(ComponentName componentName, b bVar);

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        onCustomTabsServiceConnected(componentName, new b(this, h.a(iBinder), componentName) {
            final /* synthetic */ e a;
        });
    }
}
