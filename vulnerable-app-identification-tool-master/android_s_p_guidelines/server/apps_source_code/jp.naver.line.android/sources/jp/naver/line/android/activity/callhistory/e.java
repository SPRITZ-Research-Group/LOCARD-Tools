package jp.naver.line.android.activity.callhistory;

import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.rmv;
import jp.naver.line.android.activity.main.a;

public final class e {
    final /* synthetic */ CallHistoryFragment a;

    public e(CallHistoryFragment callHistoryFragment) {
        this.a = callHistoryFragment;
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onCurrentTabButtonClicked(rmv rmv) {
        if (rmv.a() == a.CALL && this.a.q()) {
            this.a.a.smoothScrollToPosition(0);
        }
    }
}
