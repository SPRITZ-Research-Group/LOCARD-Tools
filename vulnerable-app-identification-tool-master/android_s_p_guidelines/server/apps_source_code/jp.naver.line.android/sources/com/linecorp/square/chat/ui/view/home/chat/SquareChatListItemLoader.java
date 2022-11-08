package com.linecorp.square.chat.ui.view.home.chat;

import android.text.TextUtils;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.SquareExecutor;
import com.linecorp.square.chat.SquareChatConsts;
import com.linecorp.square.chat.bo.SquareChatBo;
import com.linecorp.square.chat.dao.SquareChatDao;
import com.linecorp.square.chat.dao.SquareChatTableAdapterImpl;
import com.linecorp.square.chat.event.SquareChatEventProcessFinishEvent;
import defpackage.acns;
import defpackage.acob;
import defpackage.acru;
import defpackage.acry;
import defpackage.oyz;
import defpackage.ozh;
import defpackage.paj;
import defpackage.pak;
import defpackage.qaa;
import defpackage.rhp;
import defpackage.rib;
import defpackage.swy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.bo.g;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 22\u00020\u0001:\u000223B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0&H\u0002J\u001c\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120(2\u0006\u0010$\u001a\u00020\u000eH\u0002J\u000e\u0010)\u001a\u00020*2\u0006\u0010$\u001a\u00020\u000eJ\u000e\u0010+\u001a\u00020*2\u0006\u0010$\u001a\u00020\u000eJ\u000e\u0010,\u001a\u00020*2\u0006\u0010$\u001a\u00020\u000eJ\u0018\u0010,\u001a\u00020*2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u0010H\u0002J\u0010\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u000200H\u0007J\u000e\u00101\u001a\u00020*2\u0006\u0010$\u001a\u00020\u000eR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/linecorp/square/chat/ui/view/home/chat/SquareChatListItemLoader;", "", "lineApplication", "Ljp/naver/line/android/LineApplication;", "listener", "Lcom/linecorp/square/chat/ui/view/home/chat/SquareChatListItemLoader$SquareChatListLoaderListener;", "(Ljp/naver/line/android/LineApplication;Lcom/linecorp/square/chat/ui/view/home/chat/SquareChatListItemLoader$SquareChatListLoaderListener;)V", "chatBO", "Ljp/naver/line/android/bo/ChatBO;", "getChatBO", "()Ljp/naver/line/android/bo/ChatBO;", "setChatBO", "(Ljp/naver/line/android/bo/ChatBO;)V", "continuationToken", "", "isLoadingPublicChats", "", "joinedChatList", "", "Ljp/naver/line/android/activity/chatlist/model/SquareChatItem;", "messageDataSearcher", "Ljp/naver/line/android/chathistory/MessageDataSearcher;", "squareChatBo", "Lcom/linecorp/square/chat/bo/SquareChatBo;", "getSquareChatBo", "()Lcom/linecorp/square/chat/bo/SquareChatBo;", "setSquareChatBo", "(Lcom/linecorp/square/chat/bo/SquareChatBo;)V", "squareChatDao", "Lcom/linecorp/square/chat/dao/SquareChatDao;", "squareExecutor", "Lcom/linecorp/square/SquareExecutor;", "getSquareExecutor", "()Lcom/linecorp/square/SquareExecutor;", "setSquareExecutor", "(Lcom/linecorp/square/SquareExecutor;)V", "squareMid", "getChatIdSetWithFailedMessage", "", "getChatListSingle", "Lio/reactivex/Single;", "loadJoinedChatList", "", "loadMorePublicChatList", "loadPublicChatList", "refresh", "onSquareChatEventProcessFinishEvent", "e", "Lcom/linecorp/square/chat/event/SquareChatEventProcessFinishEvent;", "setSquareMid", "Companion", "SquareChatListLoaderListener", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class SquareChatListItemLoader {
    public static final Companion d = new Companion();
    private static final String l;
    public SquareExecutor a;
    public g b;
    public SquareChatBo c;
    private final swy e;
    private final SquareChatDao f = new SquareChatDao();
    private String g;
    private boolean h;
    private List<rib> i = acob.a;
    private String j;
    private final SquareChatListLoaderListener k;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/linecorp/square/chat/ui/view/home/chat/SquareChatListItemLoader$Companion;", "", "()V", "REQUEST_OPEN_CHAT_LIMIT", "", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J.\u0010\n\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&¨\u0006\u0010"}, d2 = {"Lcom/linecorp/square/chat/ui/view/home/chat/SquareChatListItemLoader$SquareChatListLoaderListener;", "", "onJoinedChatListLoadFinished", "", "chatList", "", "Ljp/naver/line/android/activity/chatlist/model/SquareChatItem;", "onPublicChatListLoadFailed", "throwable", "", "onPublicChatListLoadFinished", "totalSquareChatCount", "", "hasMore", "", "wasRefreshed", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
    public interface SquareChatListLoaderListener {
        void a(Throwable th);

        void a(List<rib> list);

        void a(List<rib> list, int i, boolean z, boolean z2);
    }

    public SquareChatListItemLoader(LineApplication lineApplication, SquareChatListLoaderListener squareChatListLoaderListener) {
        this.k = squareChatListLoaderListener;
        this.e = lineApplication.f().b(true).f();
        InjectableBean_SquareChatListItemLoader.a(lineApplication.g().b(), this);
    }

    public final void b(String str) {
        this.g = null;
        a(str, true);
    }

    public final void c(String str) {
        a(str, false);
    }

    private final void a(String str, boolean z) {
        if (!this.h) {
            this.h = true;
            SquareChatBo squareChatBo = this.c;
            if (squareChatBo == null) {
                acry.a("squareChatBo");
            }
            squareChatBo.b(str, this.g, new SquareChatListItemLoader$loadPublicChatList$1(this, z), new SquareChatListItemLoader$loadPublicChatList$2(this));
        }
    }

    public final void d(String str) {
        this.j = str;
    }

    @Subscribe(a = SubscriberType.BACKGROUND)
    public final void onSquareChatEventProcessFinishEvent(SquareChatEventProcessFinishEvent squareChatEventProcessFinishEvent) {
        if (!squareChatEventProcessFinishEvent.a().isEmpty()) {
            String str = this.j;
            if (str == null) {
                acry.a("squareMid");
            }
            if (!TextUtils.isEmpty(str)) {
                g gVar = this.b;
                if (gVar == null) {
                    acry.a("chatBO");
                }
                SquareChatTableAdapterImpl a = gVar.a().a();
                String str2 = this.j;
                if (str2 == null) {
                    acry.a("squareMid");
                }
                List g = a.g(str2);
                for (Object next : squareChatEventProcessFinishEvent.a()) {
                    Object obj;
                    String str3 = (String) next;
                    Iterable<rib> iterable = this.i;
                    Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
                    for (rib a2 : iterable) {
                        arrayList.add(rhp.a(a2).a());
                    }
                    if (((List) arrayList).contains(str3) || g.contains(str3)) {
                        obj = 1;
                        continue;
                    } else {
                        obj = null;
                        continue;
                    }
                    if (obj != null) {
                        break;
                    }
                }
                Object next2 = null;
                if (((String) next2) != null) {
                    String str4 = this.j;
                    if (str4 == null) {
                        acry.a("squareMid");
                    }
                    a(str4);
                    str4 = this.j;
                    if (str4 == null) {
                        acry.a("squareMid");
                    }
                    b(str4);
                }
            }
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SquareChatConsts.a);
        stringBuilder.append(".SquareChatListItemLoader");
        l = stringBuilder.toString();
    }

    public final void a(String str) {
        oyz b = oyz.b((Callable) new SquareChatListItemLoader$getChatListSingle$1(this, str));
        SquareExecutor squareExecutor = this.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        b = b.b(qaa.a(squareExecutor.b())).d((pak) new SquareChatListItemLoader$loadJoinedChatList$1(this));
        squareExecutor = this.a;
        if (squareExecutor == null) {
            acry.a("squareExecutor");
        }
        b.b(qaa.a(squareExecutor.b())).a(ozh.a()).a((paj) new SquareChatListItemLoader$loadJoinedChatList$2(this), (paj) SquareChatListItemLoader$loadJoinedChatList$3.a);
    }
}
