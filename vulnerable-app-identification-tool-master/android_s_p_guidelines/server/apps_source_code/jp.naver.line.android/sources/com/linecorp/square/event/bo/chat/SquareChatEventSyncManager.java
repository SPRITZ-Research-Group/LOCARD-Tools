package com.linecorp.square.event.bo.chat;

import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import com.linecorp.square.SquareConsts;
import com.linecorp.square.SquareExecutor;
import com.linecorp.square.base.SquareRxObserver;
import com.linecorp.square.chat.event.SyncSquareChatEvent;
import com.linecorp.square.event.bo.FetchResponse;
import com.linecorp.square.event.bo.SquareSubscriptionManager;
import defpackage.oyt;
import defpackage.oyu;
import defpackage.ozn;
import defpackage.qaq;
import defpackage.qaz;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SquareChatEventSyncManager {
    private static final String f;
    a a;
    SquareChatEventBo b;
    SquareExecutor c;
    SquareSubscriptionManager d;
    oyu e;
    private final Map<String, qaz<String>> g = new ConcurrentHashMap();
    private final oyt<String> h = new oyt<String>(this) {
        final /* synthetic */ SquareChatEventSyncManager a;

        public final void a() {
        }

        public void onError(Throwable th) {
        }

        public void onSubscribe(ozn ozn) {
        }

        {
            this.a = r1;
        }

        public /* synthetic */ void a_(Object obj) {
            this.a.b.a((String) obj, false, null, new SquareRxObserver<FetchResponse>(this) {
                final /* synthetic */ AnonymousClass1 a;

                public void a(Throwable th) {
                }

                {
                    this.a = r1;
                }
            });
        }
    };

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SquareConsts.a);
        stringBuilder.append(".bo.chat");
        f = stringBuilder.toString();
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public void onSyncChatEvent(SyncSquareChatEvent syncSquareChatEvent) {
        String a = syncSquareChatEvent.a();
        if (!this.d.a(a)) {
            qaz qaz = (qaz) this.g.get(a);
            if (qaz == null) {
                qaz = qaq.p();
                qaz.b(3000, TimeUnit.MILLISECONDS, this.e).b(this.h);
                this.g.put(a, qaz);
            }
            qaz.a_(a);
        }
    }
}
