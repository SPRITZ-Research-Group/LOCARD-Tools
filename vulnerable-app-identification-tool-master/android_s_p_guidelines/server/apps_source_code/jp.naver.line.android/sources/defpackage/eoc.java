package defpackage;

import android.app.Activity;
import com.linecorp.rxeventbus.EnableSticky;

@EnableSticky
/* renamed from: eoc */
public final class eoc {
    private final Class<? extends Activity> a;
    private final eod b;

    public eoc(Class<? extends Activity> cls, eod eod) {
        this.a = cls;
        this.b = eod;
    }

    public final eod a() {
        return this.b;
    }
}
