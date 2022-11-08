package defpackage;

import android.text.TextUtils;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jp.naver.line.android.activity.chathistory.z;
import jp.naver.line.android.bo.g;
import jp.naver.line.android.db.main.model.aj;
import jp.naver.line.android.db.main.model.am;
import jp.naver.line.android.model.cz;
import jp.naver.line.android.model.h;
import kotlin.Metadata;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 S2\u00020\u0001:\u0004RSTUB;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0012\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J$\u0010>\u001a\u0004\u0018\u00010\u001f2\b\u0010?\u001a\u0004\u0018\u00010\u001a2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u001fH\u0003J\u0018\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020E2\u0006\u0010@\u001a\u00020AH\u0003J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020AJ\u0010\u0010G\u001a\u0002052\u0006\u0010H\u001a\u00020IH\u0003J\u000e\u0010J\u001a\u0002052\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010K\u001a\u000205J\u0010\u0010L\u001a\u0002052\u0006\u0010M\u001a\u00020NH\u0007J\u0018\u0010O\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020AH\u0003J\b\u0010P\u001a\u000205H\u0007J\b\u0010Q\u001a\u000205H\u0007R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001e8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0012\u0010+\u001a\u00060,R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010-\u001a\u00060.R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010/\u001a\u0002008F¢\u0006\u0006\u001a\u0004\b/\u00101R\u0011\u00102\u001a\u0002008F¢\u0006\u0006\u001a\u0004\b2\u00101R&\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020504X.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006V"}, d2 = {"Ljp/naver/line/android/activity/chathistory/call/GroupCallDataManager;", "", "callServiceClient", "Ljp/naver/line/android/thrift/client/CallServiceClient;", "chatBo", "Ljp/naver/line/android/bo/ChatBO;", "eventBus", "Lcom/linecorp/rxeventbus/EventBus;", "backgroundScheduler", "Lio/reactivex/Scheduler;", "intervalScheduler", "operationProcessor", "Ljp/naver/line/android/talkop/processor/ReceiveOperationProcessor;", "(Ljp/naver/line/android/thrift/client/CallServiceClient;Ljp/naver/line/android/bo/ChatBO;Lcom/linecorp/rxeventbus/EventBus;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;Ljp/naver/line/android/talkop/processor/ReceiveOperationProcessor;)V", "callStatusPollingDisposable", "Lio/reactivex/disposables/Disposable;", "callStatusPollingObservable", "Lio/reactivex/Observable;", "Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus;", "chatHistoryContext", "Ljp/naver/line/android/activity/chathistory/ChatHistoryContext;", "getChatHistoryContext", "()Ljp/naver/line/android/activity/chathistory/ChatHistoryContext;", "setChatHistoryContext", "(Ljp/naver/line/android/activity/chathistory/ChatHistoryContext;)V", "chatId", "", "getChatId", "()Ljava/lang/String;", "chatMembers", "", "Ljp/naver/line/android/model/UserData;", "getChatMembers", "()Ljava/util/List;", "chatType", "Ljp/naver/line/android/model/ChatData$ChatType;", "getChatType", "()Ljp/naver/line/android/model/ChatData$ChatType;", "groupCallStatus", "getGroupCallStatus", "()Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus;", "setGroupCallStatus", "(Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus;)V", "groupInvitationOperationListener", "Ljp/naver/line/android/activity/chathistory/call/GroupCallDataManager$GroupInvitationOperationListener;", "groupKickedOutOperationListener", "Ljp/naver/line/android/activity/chathistory/call/GroupCallDataManager$GroupKickedOutOperationListener;", "isGroupCallAvailable", "", "()Z", "isGroupOrRoom", "onGroupCallStateUpdated", "Lkotlin/Function1;", "", "getOnGroupCallStateUpdated", "()Lkotlin/jvm/functions/Function1;", "setOnGroupCallStateUpdated", "(Lkotlin/jvm/functions/Function1;)V", "convertCallType", "Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus$CallType;", "thriftMediaType", "Ljp/naver/talk/protocol/thriftv1/GroupCallMediaType;", "convertMidToContactDto", "targetMid", "contactCache", "Ljp/naver/line/android/bo/ContactCache;", "currentUserData", "convertThriftToGroupCallStatus", "thrift", "Ljp/naver/talk/protocol/thriftv1/GroupCall;", "createGroupCallStatusPollingObservable", "maybeStartGroupCallPollingInternal", "groupCallingType", "Ljp/naver/line/android/db/main/model/GroupCallingType;", "onChatHistoryUpdated", "onDestroy", "onGroupCallStatusChangedEvent", "event", "Ljp/naver/line/android/bo/groupcall/GroupCallStatusChangedEvent;", "requestGroupCallStatus", "startGroupCallPolling", "stopGroupCallPolling", "BaseOperationListener", "Companion", "GroupInvitationOperationListener", "GroupKickedOutOperationListener", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
/* renamed from: qqe */
public final class qqe {
    public static final qqg b = new qqg();
    private static final long n = TimeUnit.SECONDS.toMillis(20);
    public acqr<? super qqk, y> a;
    private qqk c = qqp.d;
    private z d;
    private final qqh e = new qqh(this);
    private final qqi f = new qqi(this);
    private oyn<qqk> g;
    private ozn h;
    private final vjy i;
    private final g j;
    private final a k;
    private final oyu l;
    private final oyu m;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus;", "it", "", "apply", "(Ljava/lang/Long;)Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus;"}, k = 3, mv = {1, 1, 13})
    /* renamed from: qqe$a */
    final class a<T, R> implements pak<T, R> {
        final /* synthetic */ qqe a;
        final /* synthetic */ String b;
        final /* synthetic */ jp.naver.line.android.bo.z c;

        a(qqe qqe, String str, jp.naver.line.android.bo.z zVar) {
            this.a = qqe;
            this.b = str;
            this.c = zVar;
        }

        public final /* synthetic */ Object apply(Object obj) {
            return this.a.a(this.b, this.c);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljp/naver/line/android/activity/chathistory/call/GroupCallStatus;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
    /* renamed from: qqe$b */
    final class b<T> implements paj<qqk> {
        final /* synthetic */ qqe a;

        b(qqe qqe) {
            this.a = qqe;
        }

        public final /* synthetic */ void accept(Object obj) {
            this.a.a((qqk) obj);
            this.a.c().invoke(this.a.a());
        }
    }

    public qqe(vjy vjy, g gVar, a aVar, oyu oyu, oyu oyu2, vai vai) {
        this.i = vjy;
        this.j = gVar;
        this.k = aVar;
        this.l = oyu;
        this.m = oyu2;
        this.k.b(this);
        vai.a(this.e, this.e.b());
        vai.a(this.f, this.f.b());
    }

    public final qqk a() {
        return this.c;
    }

    public final void a(qqk qqk) {
        this.c = qqk;
    }

    public final z b() {
        return this.d;
    }

    public final acqr<qqk, y> c() {
        acqr<qqk, y> acqr = this.a;
        if (acqr == null) {
            acry.a("onGroupCallStateUpdated");
        }
        return acqr;
    }

    public final String d() {
        z zVar = this.d;
        if (zVar != null) {
            String k = zVar.k();
            if (k != null) {
                return k;
            }
        }
        return "";
    }

    private h k() {
        z zVar = this.d;
        return zVar != null ? zVar.m() : null;
    }

    public final List<cz> e() {
        z zVar = this.d;
        if (zVar != null) {
            List<cz> q = zVar.q();
            if (q != null) {
                return q;
            }
        }
        return acob.a;
    }

    public final boolean f() {
        return k() == h.GROUP || k() == h.ROOM;
    }

    public final boolean g() {
        z zVar = this.d;
        h hVar = null;
        CharSequence k = zVar != null ? zVar.k() : null;
        Object obj = (k == null || addb.a(k)) ? 1 : null;
        if (obj != null) {
            return false;
        }
        if (zVar != null) {
            hVar = zVar.m();
        }
        if (hVar != null) {
            switch (qqj.a[hVar.ordinal()]) {
                case 1:
                    List w = zVar.w();
                    return w != null && w.size() >= 2;
                case 2:
                    am x = zVar.x();
                    if (x != null) {
                        return x.h();
                    }
                    break;
            }
        }
        return false;
    }

    public final void a(z zVar) {
        this.d = zVar;
        if (g()) {
            this.g = oyn.a(500, n, TimeUnit.MILLISECONDS, this.m).a(this.l).i(new a(this, d(), jp.naver.line.android.bo.z.a()));
            h();
            return;
        }
        this.c = qqk.a;
        acqr acqr = this.a;
        if (acqr == null) {
            acry.a("onGroupCallStateUpdated");
        }
        acqr.invoke(this.c);
    }

    public final void h() {
        String d = d();
        if (!f() || TextUtils.isEmpty(d)) {
            i();
        } else {
            a(this.j.n(d));
        }
    }

    private final void a(aj ajVar) {
        i();
        if (ajVar == aj.NONE) {
            this.c = qql.d;
            acqr acqr = this.a;
            if (acqr == null) {
                acry.a("onGroupCallStateUpdated");
            }
            acqr.invoke(this.c);
            return;
        }
        oyn oyn = this.g;
        if (oyn != null) {
            this.h = oyn.a(ozh.a()).d((paj) new b(this));
        }
    }

    public final void i() {
        ozn ozn = this.h;
        if (ozn != null) {
            ozn.dispose();
        }
        this.h = null;
    }

    public final void j() {
        this.k.c(this);
        i();
        vai a = vai.a();
        a.a((vaf) this.e);
        a.a((vaf) this.f);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onGroupCallStatusChangedEvent(siz siz) {
        Object a = siz.a();
        CharSequence charSequence = (CharSequence) a;
        Object obj = (charSequence == null || charSequence.length() == 0) ? 1 : null;
        if (obj != null || (acry.a(a, d()) ^ 1) != 0) {
            return;
        }
        if (six.a()) {
            h();
            return;
        }
        this.c = qqk.a;
        acqr acqr = this.a;
        if (acqr == null) {
            acry.a("onGroupCallStateUpdated");
        }
        acqr.invoke(this.c);
        i();
    }

    private final defpackage.qqk a(java.lang.String r6, jp.naver.line.android.bo.z r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:qqe.a(java.lang.String, jp.naver.line.android.bo.z):qqk. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = r5.i;	 Catch:{ adfz -> 0x0087 }
        r6 = r0.b(r6);	 Catch:{ adfz -> 0x0087 }
        r0 = jp.naver.line.android.db.main.model.ag.a();	 Catch:{ adfz -> 0x0087 }
        r1 = r6.a();	 Catch:{ adfz -> 0x0087 }
        if (r1 != 0) goto L_0x0016;	 Catch:{ adfz -> 0x0087 }
    L_0x0010:
        r6 = defpackage.qql.d;	 Catch:{ adfz -> 0x0087 }
        r6 = (defpackage.qqk) r6;	 Catch:{ adfz -> 0x0087 }
        goto L_0x008b;	 Catch:{ adfz -> 0x0087 }
    L_0x0016:
        r1 = r6.d;	 Catch:{ adfz -> 0x0087 }
        if (r1 == 0) goto L_0x0047;	 Catch:{ adfz -> 0x0087 }
    L_0x001a:
        r1 = (java.lang.Iterable) r1;	 Catch:{ adfz -> 0x0087 }
        r2 = new java.util.ArrayList;	 Catch:{ adfz -> 0x0087 }
        r3 = 10;	 Catch:{ adfz -> 0x0087 }
        r3 = defpackage.acns.a(r1, r3);	 Catch:{ adfz -> 0x0087 }
        r2.<init>(r3);	 Catch:{ adfz -> 0x0087 }
        r2 = (java.util.Collection) r2;	 Catch:{ adfz -> 0x0087 }
        r1 = r1.iterator();	 Catch:{ adfz -> 0x0087 }
    L_0x002d:
        r3 = r1.hasNext();	 Catch:{ adfz -> 0x0087 }
        if (r3 == 0) goto L_0x0044;	 Catch:{ adfz -> 0x0087 }
    L_0x0033:
        r3 = r1.next();	 Catch:{ adfz -> 0x0087 }
        r3 = (java.lang.String) r3;	 Catch:{ adfz -> 0x0087 }
        r4 = r0;	 Catch:{ adfz -> 0x0087 }
        r4 = (jp.naver.line.android.model.cz) r4;	 Catch:{ adfz -> 0x0087 }
        r3 = defpackage.qqe.a(r3, r7, r4);	 Catch:{ adfz -> 0x0087 }
        r2.add(r3);	 Catch:{ adfz -> 0x0087 }
        goto L_0x002d;	 Catch:{ adfz -> 0x0087 }
    L_0x0044:
        r2 = (java.util.List) r2;	 Catch:{ adfz -> 0x0087 }
        goto L_0x004c;	 Catch:{ adfz -> 0x0087 }
    L_0x0047:
        r1 = defpackage.acob.a;	 Catch:{ adfz -> 0x0087 }
        r2 = r1;	 Catch:{ adfz -> 0x0087 }
        r2 = (java.util.List) r2;	 Catch:{ adfz -> 0x0087 }
    L_0x004c:
        r1 = new qqo;	 Catch:{ adfz -> 0x0087 }
        r3 = r6.f;	 Catch:{ adfz -> 0x0087 }
        if (r3 != 0) goto L_0x0055;	 Catch:{ adfz -> 0x0087 }
    L_0x0052:
        r3 = defpackage.qqm.VOICE;	 Catch:{ adfz -> 0x0087 }
        goto L_0x006b;	 Catch:{ adfz -> 0x0087 }
    L_0x0055:
        r4 = defpackage.qqj.b;	 Catch:{ adfz -> 0x0087 }
        r3 = r3.ordinal();	 Catch:{ adfz -> 0x0087 }
        r3 = r4[r3];	 Catch:{ adfz -> 0x0087 }
        switch(r3) {
            case 1: goto L_0x0069;
            case 2: goto L_0x0066;
            case 3: goto L_0x0063;
            default: goto L_0x0060;
        };	 Catch:{ adfz -> 0x0087 }
    L_0x0060:
        r6 = new kotlin.k;	 Catch:{ adfz -> 0x0087 }
        goto L_0x0083;	 Catch:{ adfz -> 0x0087 }
    L_0x0063:
        r3 = defpackage.qqm.LIVE;	 Catch:{ adfz -> 0x0087 }
        goto L_0x006b;	 Catch:{ adfz -> 0x0087 }
    L_0x0066:
        r3 = defpackage.qqm.VIDEO;	 Catch:{ adfz -> 0x0087 }
        goto L_0x006b;	 Catch:{ adfz -> 0x0087 }
    L_0x0069:
        r3 = defpackage.qqm.VOICE;	 Catch:{ adfz -> 0x0087 }
    L_0x006b:
        r6 = r6.c;	 Catch:{ adfz -> 0x0087 }
        r4 = r0;	 Catch:{ adfz -> 0x0087 }
        r4 = (jp.naver.line.android.model.cz) r4;	 Catch:{ adfz -> 0x0087 }
        r6 = defpackage.qqe.a(r6, r7, r4);	 Catch:{ adfz -> 0x0087 }
        r7 = r0.a();	 Catch:{ adfz -> 0x0087 }
        r7 = defpackage.qqg.a(r2, r7);	 Catch:{ adfz -> 0x0087 }
        r1.<init>(r3, r6, r7);	 Catch:{ adfz -> 0x0087 }
        r6 = r1;	 Catch:{ adfz -> 0x0087 }
        r6 = (defpackage.qqk) r6;	 Catch:{ adfz -> 0x0087 }
        goto L_0x008b;	 Catch:{ adfz -> 0x0087 }
    L_0x0083:
        r6.<init>();	 Catch:{ adfz -> 0x0087 }
        throw r6;	 Catch:{ adfz -> 0x0087 }
    L_0x0087:
        r6 = defpackage.qqp.d;
        r6 = (defpackage.qqk) r6;
    L_0x008b:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: qqe.a(java.lang.String, jp.naver.line.android.bo.z):qqk");
    }

    private static cz a(String str, jp.naver.line.android.bo.z zVar, cz czVar) {
        if (acry.a((Object) str, czVar.a())) {
            return czVar;
        }
        return str != null ? zVar.b(str) : null;
    }
}
