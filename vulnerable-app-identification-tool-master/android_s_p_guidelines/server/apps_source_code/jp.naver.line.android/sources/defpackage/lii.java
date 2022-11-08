package defpackage;

import android.util.Log;
import com.linecorp.rxeventbus.SubscriberType;
import java.lang.reflect.Method;

/* renamed from: lii */
public final class lii {
    private lij a;

    public lii(lij lij) {
        this.a = lij;
    }

    public final void a(Object obj, Method method, SubscriberType subscriberType, Object obj2) {
        if (this.a.a(obj2.getClass())) {
            Log.println(this.a.a(), "lib.eventbus.".concat(obj2.getClass().getSimpleName()), String.format("callback invocation - %s.%s(%s) on %s", new Object[]{obj.getClass().getSimpleName(), method.getName(), obj2.toString(), subscriberType.name()}));
        }
    }
}
