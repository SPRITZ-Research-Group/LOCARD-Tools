package jp.naver.line.android.activity.chathistory;

import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.event.bo.chat.SquareChatEventBo.SquareChatPushEvent;

final class cy {
    final /* synthetic */ cx a;

    private cy(cx cxVar) {
        this.a = cxVar;
    }

    /* synthetic */ cy(cx cxVar, byte b) {
        this(cxVar);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onChatPushEventReceived(SquareChatPushEvent squareChatPushEvent) {
        if (!squareChatPushEvent.a().equals(this.a.e)) {
            return;
        }
        if (this.a.j) {
            this.a.k = true;
        } else {
            this.a.a(false);
        }
    }
}
