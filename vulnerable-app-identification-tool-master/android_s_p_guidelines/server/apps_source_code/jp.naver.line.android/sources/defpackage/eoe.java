package defpackage;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.linecorp.rxeventbus.a;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import jp.naver.line.android.activity.pushdialog.PushDialogActivity;
import jp.naver.line.android.activity.schemeservice.LineSchemeServiceActivity;

/* renamed from: eoe */
public final class eoe implements ActivityLifecycleCallbacks {
    private final Context a;
    private final a b;
    private final Set<Integer> c = new CopyOnWriteArraySet();

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public eoe(Context context, a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        a(activity, eod.CREATED);
    }

    public final void onActivityStarted(Activity activity) {
        a(activity, eod.STARTED);
        if (!eoe.a(activity)) {
            int size = this.c.size();
            this.c.add(Integer.valueOf(activity.hashCode()));
            int size2 = this.c.size();
            if (size == 0 && size2 == 1) {
                this.b.a(eof.FOREGROUND);
            }
        }
    }

    public final void onActivityResumed(Activity activity) {
        a(activity, eod.RESUMED);
    }

    public final void onActivityPaused(Activity activity) {
        a(activity, eod.PAUSED);
    }

    public final void onActivityStopped(Activity activity) {
        a(activity, eod.STOPPED);
        if (!eoe.a(activity)) {
            int size = this.c.size();
            this.c.remove(Integer.valueOf(activity.hashCode()));
            int size2 = this.c.size();
            if (size == 1 && size2 == 0) {
                this.b.a(eof.BACKGROUND);
            }
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        a(activity, eod.DESTROYED);
    }

    private static boolean a(Activity activity) {
        return (activity instanceof PushDialogActivity) || (activity instanceof LineSchemeServiceActivity);
    }

    private void a(Activity activity, eod eod) {
        this.b.a(new eoc(activity.getClass(), eod));
    }

    public final boolean a() {
        return !this.c.isEmpty();
    }
}
