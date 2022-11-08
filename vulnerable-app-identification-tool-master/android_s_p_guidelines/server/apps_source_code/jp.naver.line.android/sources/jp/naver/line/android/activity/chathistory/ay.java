package jp.naver.line.android.activity.chathistory;

import android.annotation.SuppressLint;
import android.content.Context;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.shop.sticon.ui.activity.SticonDetailActivity;
import defpackage.acqq;
import defpackage.acqr;
import defpackage.acrz;
import defpackage.cpm;
import defpackage.lil;
import defpackage.liu;
import defpackage.liv;
import defpackage.mee;
import defpackage.qaa;
import defpackage.qrw;
import defpackage.qsa;
import defpackage.qtr;
import defpackage.qxm;
import defpackage.swv;
import defpackage.sxa;
import defpackage.sza;
import defpackage.sze;
import defpackage.szo;
import defpackage.tbq;
import defpackage.tbs;
import defpackage.tcm;
import defpackage.tco;
import defpackage.tfu;
import defpackage.ulm;
import java.io.File;
import java.util.EnumSet;
import java.util.Set;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.chathistory.list.at;
import jp.naver.line.android.activity.chathistory.list.f;
import jp.naver.line.android.activity.chathistory.messageinput.x;
import jp.naver.line.android.obs.model.ObjectInfo;
import jp.naver.line.android.util.ca;
import kotlin.Metadata;
import kotlin.y;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 22\u00020\u0001:\u0003234B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0014J*\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0006\u0010!\u001a\u00020\u0014J*\u0010\"\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0018\u0010%\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J.\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010$2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0003J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0007J:\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020'2\u0006\u0010(\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J:\u00100\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u00102\u0006\u0010/\u001a\u00020'2\u0006\u0010(\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J2\u00101\u001a\u00020\u00142\u0006\u0010/\u001a\u00020'2\u0006\u0010(\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Ljp/naver/line/android/activity/chathistory/ChatHistoryRowViewHolderEventListener;", "", "activity", "Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;", "messageDataManager", "Ljp/naver/line/android/chathistory/MessageDataManager;", "sticonDataManager", "Lcom/linecorp/shop/sticon/SticonDataManager;", "(Ljp/naver/line/android/activity/chathistory/ChatHistoryActivity;Ljp/naver/line/android/chathistory/MessageDataManager;Lcom/linecorp/shop/sticon/SticonDataManager;)V", "disposableSet", "Lcom/linecorp/rxjava/DisposableSet;", "convert", "Ljp/naver/line/android/obs/net/OBSRequestParamsBuilder$OBJECT_TYPE;", "msgViewType", "Ljp/naver/line/android/activity/chathistory/list/MessageViewType;", "getObjectInfo", "Ljp/naver/line/android/activity/chathistory/ChatHistoryRowViewHolderEventListener$ObsItemInfoQueryResponse;", "messageViewData", "Ljp/naver/line/android/activity/chathistory/list/MessageViewData;", "onDownloadedUnpaidSticonClick", "", "sticonPackageId", "", "onInvalidataChatHistory", "onMessageClickForEditMode", "originalFilePath", "", "fileContentFileName", "adapterData", "Ljp/naver/line/android/activity/chathistory/list/ChatHistoryAdapterData;", "onMessageEditModeButtonClicked", "e", "Ljp/naver/line/android/activity/chathistory/event/MessageEditModeButtonClickedEvent;", "onStop", "setEditModeAndToggleCheck", "editType", "Ljp/naver/line/android/activity/chathistory/dialog/MessageEditType;", "setEditModeWithoutToggling", "shouldCheckEnableObsCopy", "", "type", "showSticonInputOrDetailPage", "stickerPackageId", "", "toggleSelectionState", "response", "Ljp/naver/line/android/activity/chathistory/ChatHistoryRowViewHolderEventListener$ObsItemInfoQueryResponse$Result;", "isLongClick", "toggleSelectionStateOrShowError", "toggleSelectionStateOrShowErrorAsync", "Companion", "ObsAvailabilityStatus", "ObsItemInfoQueryResponse", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class ay {
    public static final az a = new az();
    private static final Set<qrw> f = EnumSet.of(qrw.DELETE, new qrw[]{qrw.FORWARD, qrw.SAVE_TO_NOTE, qrw.SAVE_TO_ALBUM, qrw.KEEP, qrw.DESTROY_MESSAGE});
    private final lil b = new lil();
    private final ChatHistoryActivity c;
    private final swv d;
    private final mee e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "accept", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 1, 13})
    final class b<T> implements cpm<Integer> {
        final /* synthetic */ ay a;

        b(ay ayVar) {
            this.a = ayVar;
        }

        public final /* synthetic */ void accept(Object obj) {
            ay.a(this.a, ((Integer) obj).intValue());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "updater", "Ljp/naver/line/android/chathistory/MessageDataUpdater;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class a extends acrz implements acqr<sxa, y> {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            ((sxa) obj).a((tcm) new tco(this.a));
            return y.a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljp/naver/line/android/activity/chathistory/ChatHistoryRowViewHolderEventListener$ObsItemInfoQueryResponse;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class c extends acrz implements acqq<bb> {
        final /* synthetic */ ay a;
        final /* synthetic */ at b;

        c(ay ayVar, at atVar) {
            this.a = ayVar;
            this.b = atVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return this.a.a(this.b);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "result", "Ljp/naver/line/android/activity/chathistory/ChatHistoryRowViewHolderEventListener$ObsItemInfoQueryResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    final class d extends acrz implements acqr<bb, y> {
        final /* synthetic */ ay a;
        final /* synthetic */ boolean b;
        final /* synthetic */ qrw c;
        final /* synthetic */ at d;
        final /* synthetic */ String e;
        final /* synthetic */ f f;

        d(ay ayVar, boolean z, qrw qrw, at atVar, String str, f fVar) {
            this.a = ayVar;
            this.b = z;
            this.c = qrw;
            this.d = atVar;
            this.e = str;
            this.f = fVar;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            bb bbVar = (bb) obj;
            this.a.c.e.h();
            ay.a(this.a, bbVar, this.b, this.c, this.d, this.e, this.f);
            return y.a;
        }
    }

    public ay(ChatHistoryActivity chatHistoryActivity, swv swv, mee mee) {
        this.c = chatHistoryActivity;
        this.d = swv;
        this.e = mee;
    }

    public final void a(at atVar, String str, f fVar) {
        qxm e = fVar.r().e();
        qsa qsa = qrw.Companion;
        qrw qrw = (qrw) qrw.REVERSE_TYPE_MAP.get(e);
        boolean a = a(qrw, atVar, str, fVar);
        if (qrw == null || !a) {
            fVar.r().a(atVar, null);
        } else {
            a(false, qrw, atVar, null, fVar);
        }
    }

    public final void a() {
        this.b.b();
    }

    @SuppressLint({"CheckResult"})
    private static boolean a(qrw qrw, at atVar, String str, f fVar) {
        if (atVar == null) {
            return false;
        }
        long a = atVar.a();
        if (qrw == null || !qrw.a().a() || a == -1 || !atVar.O().d()) {
            return false;
        }
        if ((qrw != qrw.KEEP && str != null && new File(str).isFile()) || fVar.r().a(a)) {
            return false;
        }
        ObjectInfo e = fVar.e(a);
        if (e == null || (qrw == qrw.KEEP && !e.d())) {
            return true;
        }
        return false;
    }

    private final void a(boolean z, qrw qrw, at atVar, String str, f fVar) {
        this.c.e.g();
        this.b.b(liu.a(liv.a(qaa.b(), (acqq) new c(this, atVar)), new d(this, z, qrw, atVar, str, fVar)));
    }

    private final jp.naver.line.android.activity.chathistory.bb a(jp.naver.line.android.activity.chathistory.list.at r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.chathistory.ay.a(jp.naver.line.android.activity.chathistory.list.at):jp.naver.line.android.activity.chathistory.bb. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r6 = this;
        r0 = r7.h();
        r0 = r0.a();
        r1 = r7.c();
        r2 = r7.O();
        r3 = jp.naver.line.android.activity.chathistory.bf.a;
        r2 = r2.ordinal();
        r2 = r3[r2];
        r3 = 0;
        switch(r2) {
            case 1: goto L_0x0027;
            case 2: goto L_0x0024;
            case 3: goto L_0x0021;
            case 4: goto L_0x001e;
            default: goto L_0x001c;
        };
    L_0x001c:
        r2 = r3;
        goto L_0x0029;
    L_0x001e:
        r2 = jp.naver.line.android.obs.net.ad.OBJECTTYPE_FILE;
        goto L_0x0029;
    L_0x0021:
        r2 = jp.naver.line.android.obs.net.ad.OBJECTTYPE_AUDIO;
        goto L_0x0029;
    L_0x0024:
        r2 = jp.naver.line.android.obs.net.ad.OBJECTTYPE_VIDEO;
        goto L_0x0029;
    L_0x0027:
        r2 = jp.naver.line.android.obs.net.ad.OBJECTTYPE_IMAGE;
    L_0x0029:
        if (r0 == 0) goto L_0x00a8;
    L_0x002b:
        r4 = r0.b();
        if (r4 != 0) goto L_0x00a8;
    L_0x0031:
        r4 = r7.e();
        r4 = r4.b();
        if (r4 == 0) goto L_0x00a8;
    L_0x003b:
        r4 = defpackage.syq.b;
        r4 = r7.b();
        r4 = defpackage.syr.a(r4);
        if (r4 == 0) goto L_0x00a8;
    L_0x0047:
        if (r2 != 0) goto L_0x004a;
    L_0x0049:
        goto L_0x00a8;
    L_0x004a:
        r4 = r7.d();
        r4 = com.linecorp.square.chat.SquareChatUtils.a(r4);
        r0 = r0.a();
        r0 = jp.naver.line.android.obs.service.g.a(r4, r0);
        r4 = new jp.naver.line.android.obs.net.OBSRequestParamsBuilder;
        r4.<init>();
        r4 = r4.e(r1);
        r2 = r4.a(r2);
        r0 = jp.naver.line.android.obs.net.f.b(r0, r2, r3);	 Catch:{ IOException -> 0x00a3, Exception -> 0x009e }
        if (r0 == 0) goto L_0x007f;
    L_0x006d:
        r2 = r0.c();
        if (r2 != 0) goto L_0x007f;
    L_0x0073:
        r2 = r6.d;
        r3 = new jp.naver.line.android.activity.chathistory.ay$a;
        r3.<init>(r1);
        r3 = (defpackage.acqr) r3;
        r2.a(r3);
    L_0x007f:
        if (r0 == 0) goto L_0x0094;
    L_0x0081:
        r1 = r0.c();
        if (r1 != 0) goto L_0x0088;
    L_0x0087:
        goto L_0x0094;
    L_0x0088:
        r1 = r0.e();
        if (r1 == 0) goto L_0x0091;
    L_0x008e:
        r1 = jp.naver.line.android.activity.chathistory.ba.ENCODING;
        goto L_0x0096;
    L_0x0091:
        r1 = jp.naver.line.android.activity.chathistory.ba.VALID;
        goto L_0x0096;
    L_0x0094:
        r1 = jp.naver.line.android.activity.chathistory.ba.INVALID;
    L_0x0096:
        r2 = new jp.naver.line.android.activity.chathistory.bd;
        r2.<init>(r0, r7, r1);
        r2 = (jp.naver.line.android.activity.chathistory.bb) r2;
        return r2;
    L_0x009e:
        r7 = jp.naver.line.android.activity.chathistory.be.a;
        r7 = (jp.naver.line.android.activity.chathistory.bb) r7;
        return r7;
    L_0x00a3:
        r7 = jp.naver.line.android.activity.chathistory.bc.a;
        r7 = (jp.naver.line.android.activity.chathistory.bb) r7;
        return r7;
    L_0x00a8:
        r0 = new jp.naver.line.android.activity.chathistory.bd;
        r1 = jp.naver.line.android.activity.chathistory.ba.INVALID;
        r0.<init>(r3, r7, r1);
        r0 = (jp.naver.line.android.activity.chathistory.bb) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.chathistory.ay.a(jp.naver.line.android.activity.chathistory.list.at):jp.naver.line.android.activity.chathistory.bb");
    }

    private final void a(at atVar, qrw qrw, String str, f fVar) {
        if (f.contains(qrw)) {
            fVar.a(qrw.a(), this.c);
            fVar.r().a(atVar, str);
            this.c.a(qrw.a());
        }
    }

    private final void a(qrw qrw, f fVar) {
        if (f.contains(qrw)) {
            fVar.a(qrw.a(), this.c);
            this.c.a(qrw.a());
        }
    }

    public final void b() {
        this.c.i.q();
    }

    public final void a(long j) {
        int a = this.e.a(j);
        if (this.e.c(a)) {
            ca.a((cpm) new b(this)).a((Object) Integer.valueOf(a));
            return;
        }
        ulm a2 = this.e.u().a(a);
        if (a2 != null && !this.c.isFinishing()) {
            ChatHistoryActivity chatHistoryActivity = this.c;
            com.linecorp.shop.sticon.ui.activity.a aVar = SticonDetailActivity.a;
            chatHistoryActivity.startActivity(com.linecorp.shop.sticon.ui.activity.a.a(this.c, a2.b(), null, false, false, 28));
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onMessageEditModeButtonClicked(qtr qtr) {
        qrw a = qtr.a();
        at b = qtr.b();
        f e = qtr.e();
        if (e != null) {
            if (b == null) {
                a(a, e);
            } else if (a(a, b, qtr.c(), e)) {
                a(true, a, b, qtr.d(), e);
            } else {
                a(b, a, qtr.d(), e);
            }
        }
    }

    public static final /* synthetic */ void a(ay ayVar, bb bbVar, boolean z, qrw qrw, at atVar, String str, f fVar) {
        if ((bbVar instanceof bc) || (bbVar instanceof be)) {
            tfu.d(ayVar.c, null);
            return;
        }
        if (bbVar instanceof bd) {
            bd bdVar = (bd) bbVar;
            sza h = bdVar.b().h();
            ba c = bdVar.c();
            if (c != ba.VALID) {
                int i;
                if (c == ba.ENCODING) {
                    i = R.string.e_encoding_in_progress;
                } else {
                    if (!(h instanceof szo)) {
                        if (h instanceof tbq) {
                            i = R.string.chat_edit_alert_unavailable_movie;
                        } else if (h instanceof tbs) {
                            i = R.string.chat_edit_alert_unavailable_audio;
                        } else if (h instanceof sze) {
                            i = R.string.chat_edit_alert_unavailable_file;
                        }
                    }
                    i = R.string.chat_edit_alert_unavailable_picture;
                }
                tfu.b((Context) ayVar.c, i, null);
                ayVar.c.i.i();
                if (z) {
                    ayVar.a(qrw, fVar);
                }
                return;
            }
            long a = bdVar.b().a();
            ObjectInfo a2 = bdVar.a();
            if (a2 != null) {
                fVar.a(a, a2);
            }
            if (z) {
                ayVar.a(atVar, qrw, str, fVar);
            } else {
                fVar.r().a(atVar, str);
            }
            ayVar.c.i.q();
        }
    }

    public static final /* synthetic */ void a(ay ayVar, int i) {
        x P = ayVar.c.P();
        if (P != null && !ayVar.c.isFinishing() && i != -1) {
            P.a(i);
        }
    }
}
