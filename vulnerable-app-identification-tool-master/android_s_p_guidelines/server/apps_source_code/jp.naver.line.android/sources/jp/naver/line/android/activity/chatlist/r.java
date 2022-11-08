package jp.naver.line.android.activity.chatlist;

import android.text.TextUtils;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import com.linecorp.square.chat.db.model.SquareChatDto;
import com.linecorp.square.chat.event.SquareChatEventProcessFinishEvent;
import com.linecorp.square.group.event.UpdateSquareGroupEvent;
import defpackage.rhl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.bo.g;
import jp.naver.line.android.bo.k;
import jp.naver.line.android.bo.l;
import jp.naver.line.android.bo.m;

@Deprecated
public final class r {
    private final a a;
    private final g b;
    private final g c;
    private final k d;
    private List<jp.naver.line.android.model.g> e;

    public r(LineApplication lineApplication) {
        this(lineApplication.a(), lineApplication.f().b(false).d(), lineApplication.f().b(true).d(), new k(lineApplication, lineApplication.f().b(false).d(), lineApplication.f().b(true).d()));
    }

    private r(a aVar, g gVar, g gVar2, k kVar) {
        this.e = new ArrayList();
        this.a = aVar;
        this.b = gVar;
        this.c = gVar2;
        this.d = kVar;
    }

    public final void a() {
        this.a.a(new s());
    }

    public final void a(String str) {
        this.a.a(new s(str));
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onLoadChatList(s sVar) {
        l a;
        List a2;
        a aVar = this.a;
        System.currentTimeMillis();
        if (sVar.a == t.MAIN_TAB) {
            a = this.d.a();
        } else {
            a = this.d.a(sVar.a());
        }
        List a3 = a.a();
        List b = a.b();
        List c = a.c();
        if (sVar.a == t.MAIN_TAB) {
            a2 = m.a();
        } else {
            a2 = Collections.emptyList();
        }
        aVar.a((Object) new rhl(c, a.d(), a2, a3, b));
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onChatListLoadFinishEvent(rhl rhl) {
        this.e = rhl.c();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onSquareChatEventProcessFinishEvent(SquareChatEventProcessFinishEvent squareChatEventProcessFinishEvent) {
        if (!squareChatEventProcessFinishEvent.a().isEmpty()) {
            a();
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onUpdateSquareGroupEvent(UpdateSquareGroupEvent updateSquareGroupEvent) {
        if (updateSquareGroupEvent.a(1)) {
            for (jp.naver.line.android.model.g gVar : this.e) {
                if (TextUtils.equals(updateSquareGroupEvent.a, ((SquareChatDto) gVar).x())) {
                    a();
                    return;
                }
            }
        }
    }
}
