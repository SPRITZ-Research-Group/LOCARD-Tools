package jp.naver.line.android.activity.chathistory;

import android.app.Activity;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.stl;
import defpackage.usn;

final class n {
    private final Activity a;
    private final usn b;

    n(Activity activity) {
        this(activity, new usn());
    }

    private n(Activity activity, usn usn) {
        this.a = activity;
        this.b = usn;
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onBuddyDetailLoaded(stl stl) {
        int g = stl.a().g();
        if (stl.b() && g >= 0 && !usn.a()) {
            new o(this.a).a();
        }
    }
}
