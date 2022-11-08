package jp.naver.line.android.activity.search;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventTypes;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.rxeventbus.a;
import defpackage.imq;
import defpackage.rpq;
import defpackage.rpr;
import defpackage.rpu;
import defpackage.rpw;
import defpackage.rpy;
import defpackage.rqi;
import defpackage.rqj;
import defpackage.rql;
import defpackage.rvm;
import defpackage.rwe;
import defpackage.rwt;
import defpackage.rwv;
import defpackage.rww;
import defpackage.rxr;
import defpackage.rxs;
import defpackage.rxt;
import defpackage.skp;
import defpackage.skt;
import defpackage.sll;
import defpackage.slz;
import defpackage.sma;
import defpackage.smb;
import defpackage.uoa;
import defpackage.uzg;
import defpackage.van;
import defpackage.vaq;
import defpackage.vbt;
import defpackage.xnr;
import defpackage.zdg;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.BaseAppCompatActivity;
import jp.naver.line.android.activity.chatlist.o;
import jp.naver.line.android.activity.location.e;
import jp.naver.line.android.activity.location.r;
import jp.naver.line.android.common.passlock.d;
import jp.naver.line.android.model.bo;
import jp.naver.line.android.util.aw;

public final class g {
    private final BaseAppCompatActivity a;
    private final Handler b;
    private final a c;
    private final van d;
    private final m e;
    private b f;
    private o g;
    private String h;
    private String i;
    private String j;

    /* renamed from: jp.naver.line.android.activity.search.g$1 */
    final /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] d = new int[zdg.values().length];

        static {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.search.g.1.<clinit>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r0 = defpackage.zdg.values();
            r0 = r0.length;
            r0 = new int[r0];
            d = r0;
            r0 = 1;
            r1 = d;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = defpackage.zdg.ADD_CONTACT;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = defpackage.imi.values();
            r1 = r1.length;
            r1 = new int[r1];
            c = r1;
            r1 = c;	 Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = defpackage.imi.INSTALL_APP;	 Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0027 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0027 }
        L_0x0027:
            r1 = 2;
            r2 = c;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r3 = defpackage.imi.ADD_FRIEND;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x0032 }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x0032 }
        L_0x0032:
            r2 = 3;
            r3 = c;	 Catch:{ NoSuchFieldError -> 0x003d }
            r4 = defpackage.imi.JUMP_URL;	 Catch:{ NoSuchFieldError -> 0x003d }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x003d }
            r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x003d }
        L_0x003d:
            r3 = 4;
            r4 = c;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r5 = defpackage.imi.UNKNOWN;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0048 }
            r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0048 }
        L_0x0048:
            r4 = defpackage.skt.values();
            r4 = r4.length;
            r4 = new int[r4];
            b = r4;
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x005b }
            r5 = defpackage.skt.FRIEND;	 Catch:{ NoSuchFieldError -> 0x005b }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x005b }
            r4[r5] = r0;	 Catch:{ NoSuchFieldError -> 0x005b }
        L_0x005b:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x0065 }
            r5 = defpackage.skt.GROUP;	 Catch:{ NoSuchFieldError -> 0x0065 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0065 }
            r4[r5] = r1;	 Catch:{ NoSuchFieldError -> 0x0065 }
        L_0x0065:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x006f }
            r5 = defpackage.skt.INVITED_GROUP;	 Catch:{ NoSuchFieldError -> 0x006f }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x006f }
            r4[r5] = r2;	 Catch:{ NoSuchFieldError -> 0x006f }
        L_0x006f:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x0079 }
            r5 = defpackage.skt.CHAT_ROOM;	 Catch:{ NoSuchFieldError -> 0x0079 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0079 }
            r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0079 }
        L_0x0079:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x0084 }
            r5 = defpackage.skt.MESSAGE;	 Catch:{ NoSuchFieldError -> 0x0084 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0084 }
            r6 = 5;	 Catch:{ NoSuchFieldError -> 0x0084 }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x0084 }
        L_0x0084:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x008f }
            r5 = defpackage.skt.FUNCTION;	 Catch:{ NoSuchFieldError -> 0x008f }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x008f }
            r6 = 6;	 Catch:{ NoSuchFieldError -> 0x008f }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x008f }
        L_0x008f:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x009a }
            r5 = defpackage.skt.OFFICIAL_ACCOUNT;	 Catch:{ NoSuchFieldError -> 0x009a }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x009a }
            r6 = 7;	 Catch:{ NoSuchFieldError -> 0x009a }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x009a }
        L_0x009a:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r5 = defpackage.skt.SERVICE;	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r6 = 8;	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00a6 }
        L_0x00a6:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00b2 }
            r5 = defpackage.skt.STICKER;	 Catch:{ NoSuchFieldError -> 0x00b2 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00b2 }
            r6 = 9;	 Catch:{ NoSuchFieldError -> 0x00b2 }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00b2 }
        L_0x00b2:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00be }
            r5 = defpackage.skt.YELLOW_PAGE;	 Catch:{ NoSuchFieldError -> 0x00be }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00be }
            r6 = 10;	 Catch:{ NoSuchFieldError -> 0x00be }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00be }
        L_0x00be:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00ca }
            r5 = defpackage.skt.RECENT_KEYWORD;	 Catch:{ NoSuchFieldError -> 0x00ca }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ca }
            r6 = 11;	 Catch:{ NoSuchFieldError -> 0x00ca }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00ca }
        L_0x00ca:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00d6 }
            r5 = defpackage.skt.SHORTCUT;	 Catch:{ NoSuchFieldError -> 0x00d6 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00d6 }
            r6 = 12;	 Catch:{ NoSuchFieldError -> 0x00d6 }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00d6 }
        L_0x00d6:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00e2 }
            r5 = defpackage.skt.SQUARE;	 Catch:{ NoSuchFieldError -> 0x00e2 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00e2 }
            r6 = 13;	 Catch:{ NoSuchFieldError -> 0x00e2 }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00e2 }
        L_0x00e2:
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x00ee }
            r5 = defpackage.skt.PORTAL_SEARCH_SUMMARY;	 Catch:{ NoSuchFieldError -> 0x00ee }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ee }
            r6 = 14;	 Catch:{ NoSuchFieldError -> 0x00ee }
            r4[r5] = r6;	 Catch:{ NoSuchFieldError -> 0x00ee }
        L_0x00ee:
            r4 = defpackage.skq.values();
            r4 = r4.length;
            r4 = new int[r4];
            a = r4;
            r4 = a;	 Catch:{ NoSuchFieldError -> 0x0101 }
            r5 = defpackage.skq.MORE;	 Catch:{ NoSuchFieldError -> 0x0101 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0101 }
            r4[r5] = r0;	 Catch:{ NoSuchFieldError -> 0x0101 }
        L_0x0101:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x010b }
            r4 = defpackage.skq.DATA;	 Catch:{ NoSuchFieldError -> 0x010b }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x010b }
            r0[r4] = r1;	 Catch:{ NoSuchFieldError -> 0x010b }
        L_0x010b:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0115 }
            r1 = defpackage.skq.ID_SEARCH;	 Catch:{ NoSuchFieldError -> 0x0115 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0115 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0115 }
        L_0x0115:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x011f }
            r1 = defpackage.skq.LOCATION_ONOFF;	 Catch:{ NoSuchFieldError -> 0x011f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x011f }
            r0[r1] = r3;	 Catch:{ NoSuchFieldError -> 0x011f }
        L_0x011f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.search.g.1.<clinit>():void");
        }
    }

    public g(BaseAppCompatActivity baseAppCompatActivity, Handler handler, a aVar, m mVar) {
        this.a = baseAppCompatActivity;
        this.b = handler;
        this.c = aVar;
        this.e = mVar;
        this.d = new i(this, handler);
    }

    public final void a() {
        if (this.f != null) {
            this.f.d();
        }
    }

    public final void b() {
        if (this.f != null) {
            this.f.c();
        }
    }

    public final g a(String str) {
        this.h = str;
        return this;
    }

    public final g b(String str) {
        this.i = str;
        return this;
    }

    public final String c() {
        return this.i;
    }

    public final g c(String str) {
        this.j = str;
        return this;
    }

    private rxr a(skt skt) {
        return new rxr(this.i).a(rxt.b(skt));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.view.View r18, defpackage.skp r19, boolean r20) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.search.g.a(android.view.View, skp, boolean):boolean. bs: []
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
        r17 = this;
        r1 = r17;
        r0 = r18;
        r2 = r19;
        r3 = 0;
        r4 = jp.naver.line.android.activity.search.g.AnonymousClass1.a;	 Catch:{ all -> 0x0362 }
        r5 = r19.a();	 Catch:{ all -> 0x0362 }
        r5 = r5.ordinal();	 Catch:{ all -> 0x0362 }
        r4 = r4[r5];	 Catch:{ all -> 0x0362 }
        r5 = 3;
        r6 = 0;
        r7 = 1;
        switch(r4) {
            case 1: goto L_0x0343;
            case 2: goto L_0x0082;
            case 3: goto L_0x0054;
            case 4: goto L_0x0020;
            default: goto L_0x0019;
        };
    L_0x0019:
        if (r20 != 0) goto L_0x0361;
    L_0x001b:
        r1.a(r2, r3);
        goto L_0x0361;
    L_0x0020:
        if (r20 != 0) goto L_0x004e;
    L_0x0022:
        r0 = defpackage.rqm.d();	 Catch:{ all -> 0x0362 }
        if (r0 != 0) goto L_0x0030;	 Catch:{ all -> 0x0362 }
    L_0x0028:
        r4 = r1.a;	 Catch:{ all -> 0x0362 }
        r4 = jp.naver.line.android.util.cn.b(r4);	 Catch:{ all -> 0x0362 }
        if (r4 == 0) goto L_0x004e;	 Catch:{ all -> 0x0362 }
    L_0x0030:
        r4 = r1.c;	 Catch:{ all -> 0x0362 }
        if (r4 == 0) goto L_0x004e;	 Catch:{ all -> 0x0362 }
    L_0x0034:
        r4 = r1.c;	 Catch:{ all -> 0x0362 }
        if (r0 == 0) goto L_0x0042;	 Catch:{ all -> 0x0362 }
    L_0x0038:
        r0 = new rps;	 Catch:{ all -> 0x0362 }
        r5 = defpackage.rpt.LOCATION_SWITCH_OFF_SELECTED;	 Catch:{ all -> 0x0362 }
        r6 = jp.naver.line.android.activity.search.a.MAIN;	 Catch:{ all -> 0x0362 }
        r0.<init>(r5, r6);	 Catch:{ all -> 0x0362 }
        goto L_0x004b;	 Catch:{ all -> 0x0362 }
    L_0x0042:
        r0 = new rps;	 Catch:{ all -> 0x0362 }
        r5 = defpackage.rpt.LOCATION_SWITCH_ON_SELECTED;	 Catch:{ all -> 0x0362 }
        r6 = jp.naver.line.android.activity.search.a.MAIN;	 Catch:{ all -> 0x0362 }
        r0.<init>(r5, r6);	 Catch:{ all -> 0x0362 }
    L_0x004b:
        r4.a(r0);	 Catch:{ all -> 0x0362 }
    L_0x004e:
        if (r20 != 0) goto L_0x0053;
    L_0x0050:
        r1.a(r2, r3);
    L_0x0053:
        return r7;
    L_0x0054:
        r4 = defpackage.rvm.ID_SEARCH;	 Catch:{ all -> 0x0362 }
        r0 = r1.a;	 Catch:{ all -> 0x007e }
        r3 = new android.content.Intent;	 Catch:{ all -> 0x007e }
        r8 = r1.a;	 Catch:{ all -> 0x007e }
        r9 = r19.f();	 Catch:{ all -> 0x007e }
        r6 = jp.naver.line.android.activity.addfriend.AddFriendByUserIdActivity.a(r8, r9, r6);	 Catch:{ all -> 0x007e }
        r3.<init>(r6);	 Catch:{ all -> 0x007e }
        r0.startActivity(r3);	 Catch:{ all -> 0x007e }
        r0 = r1.c;	 Catch:{ all -> 0x007e }
        r3 = new rpy;	 Catch:{ all -> 0x007e }
        r6 = r19.f();	 Catch:{ all -> 0x007e }
        r3.<init>(r6, r5);	 Catch:{ all -> 0x007e }
        r0.a(r3);	 Catch:{ all -> 0x007e }
        if (r20 != 0) goto L_0x007d;
    L_0x007a:
        r1.a(r2, r4);
    L_0x007d:
        return r7;
    L_0x007e:
        r0 = move-exception;
        r3 = r4;
        goto L_0x0363;
    L_0x0082:
        r4 = r1.c;	 Catch:{ all -> 0x0362 }
        r8 = new rpy;	 Catch:{ all -> 0x0362 }
        r9 = r19.f();	 Catch:{ all -> 0x0362 }
        r8.<init>(r9, r5);	 Catch:{ all -> 0x0362 }
        r4.a(r8);	 Catch:{ all -> 0x0362 }
        r4 = jp.naver.line.android.activity.search.g.AnonymousClass1.b;	 Catch:{ all -> 0x0362 }
        r5 = r19.c();	 Catch:{ all -> 0x0362 }
        r5 = r5.c();	 Catch:{ all -> 0x0362 }
        r5 = r5.ordinal();	 Catch:{ all -> 0x0362 }
        r4 = r4[r5];	 Catch:{ all -> 0x0362 }
        switch(r4) {
            case 1: goto L_0x0314;
            case 2: goto L_0x02e6;
            case 3: goto L_0x02c3;
            case 4: goto L_0x0262;
            case 5: goto L_0x01ea;
            case 6: goto L_0x01d6;
            case 7: goto L_0x01b7;
            case 8: goto L_0x018b;
            case 9: goto L_0x0157;
            case 10: goto L_0x011c;
            case 11: goto L_0x0105;
            case 12: goto L_0x00f9;
            case 13: goto L_0x00d8;
            case 14: goto L_0x00aa;
            default: goto L_0x00a3;
        };
    L_0x00a3:
        if (r20 != 0) goto L_0x0342;
    L_0x00a5:
        r1.a(r2, r3);
        goto L_0x0342;
    L_0x00aa:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.slt) r0;	 Catch:{ all -> 0x0362 }
        r0 = r0.b();	 Catch:{ all -> 0x0362 }
        r4 = r1.a;	 Catch:{ ActivityNotFoundException -> 0x00c2 }
        r5 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x00c2 }
        r6 = "android.intent.action.VIEW";	 Catch:{ ActivityNotFoundException -> 0x00c2 }
        r0 = android.net.Uri.parse(r0);	 Catch:{ ActivityNotFoundException -> 0x00c2 }
        r5.<init>(r6, r0);	 Catch:{ ActivityNotFoundException -> 0x00c2 }
        r4.startActivity(r5);	 Catch:{ ActivityNotFoundException -> 0x00c2 }
        goto L_0x00d2;
    L_0x00c2:
        r0 = move-exception;
        r4 = "SearchItemClickHelper";	 Catch:{ all -> 0x0362 }
        r5 = "onPortalSearchSummaryClick Exception:";	 Catch:{ all -> 0x0362 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0362 }
        r0 = r5.concat(r0);	 Catch:{ all -> 0x0362 }
        android.util.Log.w(r4, r0);	 Catch:{ all -> 0x0362 }
    L_0x00d2:
        if (r20 != 0) goto L_0x00d7;
    L_0x00d4:
        r1.a(r2, r3);
    L_0x00d7:
        return r7;
    L_0x00d8:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.sme) r0;	 Catch:{ all -> 0x0362 }
        r4 = defpackage.rts.a();	 Catch:{ all -> 0x0362 }
        r5 = jp.naver.line.android.analytics.ga.fc.SQUARE_MAIN_SEARCH_CLICK;	 Catch:{ all -> 0x0362 }
        r4.a(r5);	 Catch:{ all -> 0x0362 }
        r4 = r1.a;	 Catch:{ all -> 0x0362 }
        r5 = r1.a;	 Catch:{ all -> 0x0362 }
        r0 = r0.j();	 Catch:{ all -> 0x0362 }
        r0 = com.linecorp.square.chat.ui.view.home.SquareHomeActivity.a(r5, r0);	 Catch:{ all -> 0x0362 }
        r4.startActivity(r0);	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x00f8;
    L_0x00f5:
        r1.a(r2, r3);
    L_0x00f8:
        return r7;
    L_0x00f9:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.smb) r0;	 Catch:{ all -> 0x0362 }
        r1.a(r0);	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x0104;
    L_0x0101:
        r1.a(r2, r3);
    L_0x0104:
        return r7;
    L_0x0105:
        if (r20 != 0) goto L_0x0116;
    L_0x0107:
        r0 = r1.c;	 Catch:{ all -> 0x0362 }
        r4 = new rpy;	 Catch:{ all -> 0x0362 }
        r5 = r2;	 Catch:{ all -> 0x0362 }
        r5 = (defpackage.smf) r5;	 Catch:{ all -> 0x0362 }
        r5 = r5.a;	 Catch:{ all -> 0x0362 }
        r4.<init>(r5, r7);	 Catch:{ all -> 0x0362 }
        r0.a(r4);	 Catch:{ all -> 0x0362 }
    L_0x0116:
        if (r20 != 0) goto L_0x011b;
    L_0x0118:
        r1.a(r2, r3);
    L_0x011b:
        return r7;
    L_0x011c:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.smg) r0;	 Catch:{ all -> 0x0362 }
        r4 = defpackage.uoa.h();	 Catch:{ all -> 0x0362 }
        if (r4 == 0) goto L_0x012a;	 Catch:{ all -> 0x0362 }
    L_0x0125:
        r4 = r4.b();	 Catch:{ all -> 0x0362 }
        goto L_0x012b;	 Catch:{ all -> 0x0362 }
    L_0x012a:
        r4 = r3;	 Catch:{ all -> 0x0362 }
    L_0x012b:
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0362 }
        if (r5 == 0) goto L_0x0133;	 Catch:{ all -> 0x0362 }
    L_0x0131:
        r4 = "81";	 Catch:{ all -> 0x0362 }
    L_0x0133:
        r5 = r1.a;	 Catch:{ all -> 0x0362 }
        r8 = r0.m();	 Catch:{ all -> 0x0362 }
        r0 = r0.j();	 Catch:{ all -> 0x0362 }
        r9 = new android.util.Pair[r7];	 Catch:{ all -> 0x0362 }
        r10 = new android.util.Pair;	 Catch:{ all -> 0x0362 }
        r11 = "utm_source";	 Catch:{ all -> 0x0362 }
        r12 = "linesearch";	 Catch:{ all -> 0x0362 }
        r10.<init>(r11, r12);	 Catch:{ all -> 0x0362 }
        r9[r6] = r10;	 Catch:{ all -> 0x0362 }
        r0 = defpackage.nrz.a(r8, r4, r0, r9);	 Catch:{ all -> 0x0362 }
        r5.startActivity(r0);	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x0156;
    L_0x0153:
        r1.a(r2, r3);
    L_0x0156:
        return r7;
    L_0x0157:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.sly) r0;	 Catch:{ all -> 0x0362 }
        r4 = r0.q();	 Catch:{ all -> 0x0362 }
        if (r4 == 0) goto L_0x0170;	 Catch:{ all -> 0x0362 }
    L_0x0160:
        r4 = r1.a;	 Catch:{ all -> 0x0362 }
        r5 = r1.a;	 Catch:{ all -> 0x0362 }
        r8 = r0.i();	 Catch:{ all -> 0x0362 }
        r0 = jp.naver.line.android.activity.shop.sticker.ae.a(r5, r8, r7);	 Catch:{ all -> 0x0362 }
        r4.startActivity(r0);	 Catch:{ all -> 0x0362 }
        goto L_0x0185;	 Catch:{ all -> 0x0362 }
    L_0x0170:
        r4 = r0.r();	 Catch:{ all -> 0x0362 }
        if (r4 == 0) goto L_0x0185;	 Catch:{ all -> 0x0362 }
    L_0x0176:
        r4 = r1.a;	 Catch:{ all -> 0x0362 }
        r0 = r0.j();	 Catch:{ all -> 0x0362 }
        r0 = com.linecorp.shop.sticon.ui.activity.SticonDetailActivity.a(r4, r0);	 Catch:{ all -> 0x0362 }
        r4 = r1.a;	 Catch:{ all -> 0x0362 }
        r4.startActivity(r0);	 Catch:{ all -> 0x0362 }
    L_0x0185:
        if (r20 != 0) goto L_0x018a;
    L_0x0187:
        r1.a(r2, r3);
    L_0x018a:
        return r7;
    L_0x018b:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.sll) r0;	 Catch:{ all -> 0x0362 }
        r4 = r0.l();	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        if (r4 == 0) goto L_0x01a4;	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
    L_0x0194:
        r4 = r0.j();	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        r5 = r0.h();	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        r0 = r0.b();	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        r1.a(r4, r5, r0);	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        goto L_0x01b1;	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
    L_0x01a4:
        r0 = r0.h();	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        r4 = r1.a;	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        r4.startActivity(r0);	 Catch:{ Exception -> 0x01b0, all -> 0x01ae }
        goto L_0x01b1;
    L_0x01ae:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0362 }
    L_0x01b1:
        if (r20 != 0) goto L_0x01b6;
    L_0x01b3:
        r1.a(r2, r3);
    L_0x01b6:
        return r7;
    L_0x01b7:
        r0 = r19.c();	 Catch:{ all -> 0x0362 }
        r4 = r2;	 Catch:{ all -> 0x0362 }
        r4 = (defpackage.slr) r4;	 Catch:{ all -> 0x0362 }
        r4 = r4.b();	 Catch:{ all -> 0x0362 }
        r5 = new jp.naver.line.android.activity.search.h;	 Catch:{ all -> 0x0362 }
        r5.<init>(r1, r4, r0);	 Catch:{ all -> 0x0362 }
        r0 = jp.naver.line.android.util.at.b();	 Catch:{ all -> 0x0362 }
        r4 = new java.lang.Void[r6];	 Catch:{ all -> 0x0362 }
        r5.executeOnExecutor(r0, r4);	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x01d5;
    L_0x01d2:
        r1.a(r2, r3);
    L_0x01d5:
        return r7;
    L_0x01d6:
        r0 = r2;	 Catch:{ all -> 0x0362 }
        r0 = (defpackage.slg) r0;	 Catch:{ all -> 0x0362 }
        r0 = r0.i();	 Catch:{ Exception -> 0x01e4 }
        if (r0 == 0) goto L_0x01e4;	 Catch:{ Exception -> 0x01e4 }
    L_0x01df:
        r4 = r1.a;	 Catch:{ Exception -> 0x01e4 }
        r4.startActivity(r0);	 Catch:{ Exception -> 0x01e4 }
    L_0x01e4:
        if (r20 != 0) goto L_0x01e9;
    L_0x01e6:
        r1.a(r2, r3);
    L_0x01e9:
        return r7;
    L_0x01ea:
        r4 = r2;	 Catch:{ all -> 0x0362 }
        r4 = (defpackage.slq) r4;	 Catch:{ all -> 0x0362 }
        if (r20 == 0) goto L_0x01f0;	 Catch:{ all -> 0x0362 }
    L_0x01ef:
        goto L_0x025c;	 Catch:{ all -> 0x0362 }
    L_0x01f0:
        r5 = r1.h;	 Catch:{ all -> 0x0362 }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0362 }
        if (r5 == 0) goto L_0x01f9;	 Catch:{ all -> 0x0362 }
    L_0x01f8:
        goto L_0x025c;	 Catch:{ all -> 0x0362 }
    L_0x01f9:
        r5 = r1.h;	 Catch:{ all -> 0x0362 }
        r5 = defpackage.utv.a(r5);	 Catch:{ all -> 0x0362 }
        r5 = r5.toLowerCase();	 Catch:{ all -> 0x0362 }
        r10 = defpackage.utv.b(r5);	 Catch:{ all -> 0x0362 }
        r5 = r4.i();	 Catch:{ all -> 0x0362 }
        r8 = r4.h();	 Catch:{ all -> 0x0362 }
        if (r8 != r7) goto L_0x023b;	 Catch:{ all -> 0x0362 }
    L_0x0211:
        r0 = r4.b();	 Catch:{ all -> 0x0362 }
        r0 = jp.naver.line.android.activity.chathistory.ChatHistoryRequest.b(r0);	 Catch:{ all -> 0x0362 }
        r0.a(r7);	 Catch:{ all -> 0x0362 }
        r4 = r5.b;	 Catch:{ all -> 0x0362 }
        r4 = r4.get(r6);	 Catch:{ all -> 0x0362 }
        r4 = (java.lang.Long) r4;	 Catch:{ all -> 0x0362 }
        r4 = r4.longValue();	 Catch:{ all -> 0x0362 }
        r12 = 0;	 Catch:{ all -> 0x0362 }
        r13 = new long[r7];	 Catch:{ all -> 0x0362 }
        r13[r6] = r4;	 Catch:{ all -> 0x0362 }
        r8 = r0;	 Catch:{ all -> 0x0362 }
        r9 = r10;	 Catch:{ all -> 0x0362 }
        r10 = r4;	 Catch:{ all -> 0x0362 }
        r8.a(r9, r10, r12, r13);	 Catch:{ all -> 0x0362 }
        r4 = r17.e();	 Catch:{ all -> 0x0362 }
        r4.b(r0);	 Catch:{ all -> 0x0362 }
        goto L_0x025b;	 Catch:{ all -> 0x0362 }
    L_0x023b:
        r6 = r0 instanceof jp.naver.line.android.activity.chatlist.ChatListRowView;	 Catch:{ all -> 0x0362 }
        if (r6 == 0) goto L_0x025b;	 Catch:{ all -> 0x0362 }
    L_0x023f:
        r0 = (jp.naver.line.android.activity.chatlist.ChatListRowView) r0;	 Catch:{ all -> 0x0362 }
        r8 = r17.e();	 Catch:{ all -> 0x0362 }
        r9 = r1.h;	 Catch:{ all -> 0x0362 }
        r11 = r4.b();	 Catch:{ all -> 0x0362 }
        r12 = r0.h();	 Catch:{ all -> 0x0362 }
        r13 = r5.b;	 Catch:{ all -> 0x0362 }
        r14 = r0.i();	 Catch:{ all -> 0x0362 }
        r0 = r1.j;	 Catch:{ all -> 0x0362 }
        r15 = r0;	 Catch:{ all -> 0x0362 }
        r8.a(r9, r10, r11, r12, r13, r14, r15);	 Catch:{ all -> 0x0362 }
    L_0x025b:
        r6 = 1;
    L_0x025c:
        if (r20 != 0) goto L_0x0261;
    L_0x025e:
        r1.a(r2, r3);
    L_0x0261:
        return r6;
    L_0x0262:
        r4 = r2 instanceof defpackage.sla;	 Catch:{ all -> 0x0362 }
        if (r4 == 0) goto L_0x02bd;	 Catch:{ all -> 0x0362 }
    L_0x0266:
        r4 = r2;	 Catch:{ all -> 0x0362 }
        r4 = (defpackage.sla) r4;	 Catch:{ all -> 0x0362 }
        r4 = r4.b();	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x0282;	 Catch:{ all -> 0x0362 }
    L_0x026f:
        r0 = r4.v();	 Catch:{ all -> 0x0362 }
        r0 = jp.naver.line.android.activity.chathistory.ChatHistoryRequest.b(r0);	 Catch:{ all -> 0x0362 }
        r0.a(r7);	 Catch:{ all -> 0x0362 }
        r4 = r17.e();	 Catch:{ all -> 0x0362 }
        r4.a(r0);	 Catch:{ all -> 0x0362 }
        goto L_0x02bd;	 Catch:{ all -> 0x0362 }
    L_0x0282:
        r5 = r0 instanceof jp.naver.line.android.activity.chatlist.ChatListRowView;	 Catch:{ all -> 0x0362 }
        if (r5 == 0) goto L_0x02bd;	 Catch:{ all -> 0x0362 }
    L_0x0286:
        r5 = r4 instanceof com.linecorp.square.chat.db.model.SquareChatDto;	 Catch:{ all -> 0x0362 }
        if (r5 == 0) goto L_0x0296;	 Catch:{ all -> 0x0362 }
    L_0x028a:
        r5 = r4;	 Catch:{ all -> 0x0362 }
        r5 = (com.linecorp.square.chat.db.model.SquareChatDto) r5;	 Catch:{ all -> 0x0362 }
        r5 = r5.p();	 Catch:{ all -> 0x0362 }
        if (r5 == 0) goto L_0x0296;	 Catch:{ all -> 0x0362 }
    L_0x0293:
        r16 = 1;	 Catch:{ all -> 0x0362 }
        goto L_0x0298;	 Catch:{ all -> 0x0362 }
    L_0x0296:
        r16 = 0;	 Catch:{ all -> 0x0362 }
    L_0x0298:
        r0 = (jp.naver.line.android.activity.chatlist.ChatListRowView) r0;	 Catch:{ all -> 0x0362 }
        r8 = r17.e();	 Catch:{ all -> 0x0362 }
        r9 = r4.v();	 Catch:{ all -> 0x0362 }
        r10 = r4.w();	 Catch:{ all -> 0x0362 }
        r11 = r4.d();	 Catch:{ all -> 0x0362 }
        r12 = r4.J();	 Catch:{ all -> 0x0362 }
        r13 = r0.k();	 Catch:{ all -> 0x0362 }
        r14 = r0.i();	 Catch:{ all -> 0x0362 }
        r15 = r0.j();	 Catch:{ all -> 0x0362 }
        r8.a(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ all -> 0x0362 }
    L_0x02bd:
        if (r20 != 0) goto L_0x02c2;
    L_0x02bf:
        r1.a(r2, r3);
    L_0x02c2:
        return r7;
    L_0x02c3:
        r0 = r19.c();	 Catch:{ all -> 0x0362 }
        r4 = r2;	 Catch:{ all -> 0x0362 }
        r4 = (jp.naver.line.android.customview.friend.c) r4;	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x02e0;	 Catch:{ all -> 0x0362 }
    L_0x02cc:
        r5 = r17.d();	 Catch:{ all -> 0x0362 }
        r4 = r4.b(r3);	 Catch:{ all -> 0x0362 }
        r0 = r0.c();	 Catch:{ all -> 0x0362 }
        r0 = r1.a(r0);	 Catch:{ all -> 0x0362 }
        r5.b(r4, r0);	 Catch:{ all -> 0x0362 }
        r6 = 1;
    L_0x02e0:
        if (r20 != 0) goto L_0x02e5;
    L_0x02e2:
        r1.a(r2, r3);
    L_0x02e5:
        return r6;
    L_0x02e6:
        r0 = r19.c();	 Catch:{ all -> 0x0362 }
        r4 = r2;	 Catch:{ all -> 0x0362 }
        r4 = (jp.naver.line.android.customview.friend.c) r4;	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x0303;	 Catch:{ all -> 0x0362 }
    L_0x02ef:
        r5 = r17.d();	 Catch:{ all -> 0x0362 }
        r4 = r4.b(r3);	 Catch:{ all -> 0x0362 }
        r0 = r0.c();	 Catch:{ all -> 0x0362 }
        r0 = r1.a(r0);	 Catch:{ all -> 0x0362 }
        r5.a(r4, r0);	 Catch:{ all -> 0x0362 }
        goto L_0x030e;	 Catch:{ all -> 0x0362 }
    L_0x0303:
        r0 = r17.d();	 Catch:{ all -> 0x0362 }
        r4 = r4.b(r3);	 Catch:{ all -> 0x0362 }
        r0.a(r4);	 Catch:{ all -> 0x0362 }
    L_0x030e:
        if (r20 != 0) goto L_0x0313;
    L_0x0310:
        r1.a(r2, r3);
    L_0x0313:
        return r7;
    L_0x0314:
        r0 = r19.c();	 Catch:{ all -> 0x0362 }
        r4 = r2;	 Catch:{ all -> 0x0362 }
        r4 = (jp.naver.line.android.customview.friend.c) r4;	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x0331;	 Catch:{ all -> 0x0362 }
    L_0x031d:
        r5 = r17.d();	 Catch:{ all -> 0x0362 }
        r4 = r4.b(r3);	 Catch:{ all -> 0x0362 }
        r0 = r0.c();	 Catch:{ all -> 0x0362 }
        r0 = r1.a(r0);	 Catch:{ all -> 0x0362 }
        r5.c(r4, r0);	 Catch:{ all -> 0x0362 }
        goto L_0x033c;	 Catch:{ all -> 0x0362 }
    L_0x0331:
        r0 = r17.d();	 Catch:{ all -> 0x0362 }
        r4 = r4.b(r3);	 Catch:{ all -> 0x0362 }
        r0.b(r4);	 Catch:{ all -> 0x0362 }
    L_0x033c:
        if (r20 != 0) goto L_0x0341;
    L_0x033e:
        r1.a(r2, r3);
    L_0x0341:
        return r7;
    L_0x0342:
        return r6;
    L_0x0343:
        r0 = r1.c;	 Catch:{ all -> 0x0362 }
        r4 = new rpy;	 Catch:{ all -> 0x0362 }
        r6 = r19.f();	 Catch:{ all -> 0x0362 }
        r4.<init>(r6, r5);	 Catch:{ all -> 0x0362 }
        r0.a(r4);	 Catch:{ all -> 0x0362 }
        r0 = r1.c;	 Catch:{ all -> 0x0362 }
        r4 = new rqd;	 Catch:{ all -> 0x0362 }
        r4.<init>(r2);	 Catch:{ all -> 0x0362 }
        r0.a(r4);	 Catch:{ all -> 0x0362 }
        if (r20 != 0) goto L_0x0360;
    L_0x035d:
        r1.a(r2, r3);
    L_0x0360:
        return r7;
    L_0x0361:
        return r6;
    L_0x0362:
        r0 = move-exception;
    L_0x0363:
        if (r20 != 0) goto L_0x0368;
    L_0x0365:
        r1.a(r2, r3);
    L_0x0368:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.search.g.a(android.view.View, skp, boolean):boolean");
    }

    private b d() {
        if (this.f == null) {
            this.f = new b(this.a, this.b, this.c);
            this.f.d();
        }
        return this.f;
    }

    private o e() {
        if (this.g == null) {
            this.g = new o(this.a, this.b, this.c);
        }
        return this.g;
    }

    private String a(String str, Intent intent, String str2) {
        if (aw.c(this.a, str)) {
            intent.addFlags(268435456);
            this.a.startActivity(intent);
            d.a().c();
            return null;
        }
        d(str2);
        return str2;
    }

    private void d(java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.search.g.d(java.lang.String):void. bs: []
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
        r1 = this;
        r0 = r1.a;	 Catch:{ Exception -> 0x000d }
        jp.naver.line.android.util.aw.a(r0, r2);	 Catch:{ Exception -> 0x000d }
        r2 = jp.naver.line.android.common.passlock.d.a();	 Catch:{ Exception -> 0x000d }
        r2.c();	 Catch:{ Exception -> 0x000d }
        return;
    L_0x000d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.search.g.d(java.lang.String):void");
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onClickOAItemAdd(rpw rpw) {
        skp a = rpw.a();
        this.c.a(new rpy(a.f(), 3));
        String b = a.b();
        if (!TextUtils.isEmpty(b)) {
            this.a.e.e();
            uzg vbt = new vbt(b, null, null, this.d);
            xnr j = a.j();
            Object obj = (j == xnr.OFFICIAL || j == xnr.LINE_AT || j == xnr.LINE_AT_0) ? 1 : null;
            if (obj != null) {
                vbt.d(rwt.b(new rww("searchResultAddButton", b, rwv.ADD).a("native").b(AppLovinEventTypes.USER_EXECUTED_SEARCH).a()).toString());
            }
            vaq.a().a(vbt);
        }
        a(a, rvm.ADD_FRIEND);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onShortcutItemEvent(rqj rqj) {
        a(rqj.a());
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onShortcutButtonEvent(rqi rqi) {
        a(rqi.a(), rqi.c(), rqi.b());
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onAutoCompleteEvent(rpq rpq) {
        String str = this.i;
        String str2 = this.j;
        rxs rxs = new rxs(rpq.b(), str, String.valueOf(imq.AUTOCOMPLETE.a()), rvm.ITEM, str2);
        rxs.a(rpq.a());
        rxs.a(rpq.c());
        rwe.a(rxs);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onLocationSettingEvent(rpu rpu) {
        e.a(this.a, 1, R.string.line_search_setlocation_searchnearpin, false, null, r.SearchResult);
    }

    private void a(smb smb) {
        a((int) BaseClientBuilder.API_PRIORITY_OTHER, smb, null);
    }

    private void a(int i, smb smb, sma sma) {
        slz b;
        if (sma == null) {
            b = smb.b();
        } else {
            b = sma.a();
        }
        a(i, smb, b);
    }

    private void a(int r5, defpackage.smb r6, defpackage.slz r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.activity.search.g.a(int, smb, slz):void. bs: []
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
        r4 = this;
        r0 = r7.f();
        r1 = r7.a();
        if (r1 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r1 = jp.naver.line.android.activity.search.g.AnonymousClass1.c;
        r0 = r0.ordinal();
        r0 = r1[r0];
        r1 = 0;
        switch(r0) {
            case 1: goto L_0x008a;
            case 2: goto L_0x0036;
            case 3: goto L_0x0021;
            default: goto L_0x0017;
        };
    L_0x0017:
        r7 = r4.a;
        r0 = 2131828032; // 0x7f111d40 float:1.9288993E38 double:1.0532629935E-314;
        defpackage.tfu.b(r7, r0, r1);
        goto L_0x009c;
    L_0x0021:
        r7 = r7.d();
        r0 = r4.a;	 Catch:{ Exception -> 0x009c }
        r1 = new android.content.Intent;	 Catch:{ Exception -> 0x009c }
        r2 = "android.intent.action.VIEW";	 Catch:{ Exception -> 0x009c }
        r7 = android.net.Uri.parse(r7);	 Catch:{ Exception -> 0x009c }
        r1.<init>(r2, r7);	 Catch:{ Exception -> 0x009c }
        r0.startActivity(r1);	 Catch:{ Exception -> 0x009c }
        goto L_0x009c;
    L_0x0036:
        r7 = r7.e();
        r0 = jp.naver.line.android.bo.z.a();
        r0 = r0.b(r7);
        if (r0 == 0) goto L_0x004a;
    L_0x0044:
        r0 = r0.f();
        if (r0 != 0) goto L_0x009c;
    L_0x004a:
        r0 = r4.a;
        r0 = r0.e;
        r0.e();
        r0 = new vbt;
        r2 = r4.d;
        r0.<init>(r7, r1, r1, r2);
        r1 = defpackage.twm.MAIN;
        r1 = defpackage.twj.b(r1);
        r1 = defpackage.rwt.a(r1, r7);
        if (r1 == 0) goto L_0x0082;
    L_0x0064:
        r1 = new rww;
        r2 = "friendSearch";
        r3 = defpackage.rwv.ADD;
        r1.<init>(r2, r7, r3);
        r7 = "native";
        r7 = r1.a(r7);
        r7 = r7.a();
        r7 = defpackage.rwt.b(r7);
        r7 = r7.toString();
        r0.d(r7);
    L_0x0082:
        r7 = defpackage.vaq.a();
        r7.a(r0);
        goto L_0x009c;
    L_0x008a:
        r0 = r7.g();
        r1 = r7.b();
        r7 = r7.c();
        r4.a(r0, r7, r1);	 Catch:{ all -> 0x009a }
        goto L_0x009c;
    L_0x009a:
        r5 = move-exception;
        throw r5;
    L_0x009c:
        r5 = a(r5);
        r4.a(r6, r5);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.activity.search.g.a(int, smb, slz):void");
    }

    private static rvm a(int i) {
        switch (i) {
            case 0:
                return rvm.BUTTON1;
            case 1:
                return rvm.BUTTON2;
            case 2:
                return rvm.BUTTON3;
            default:
                return rvm.ITEM;
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onClickServiceAppInstall(rpr rpr) {
        sll a = rpr.a();
        this.c.a(new rpy(a.f(), 3));
        if (a != null && !TextUtils.isEmpty(a.b())) {
            d(a.b());
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onClickYPFreeCallEvent(rql rql) {
        skp a = rql.a();
        this.c.a(new rpy(a.f(), 3));
        bo h = uoa.h();
        Intent a2 = com.linecorp.voip.ui.paidcall.a.a(this.a, h != null ? h.g() : "", a.m(), a.l(), null, a.j());
        if (a2 != null) {
            this.a.startActivity(a2);
        }
        a(a, rvm.FREE_CALL);
    }

    private void a(skp skp, rvm rvm) {
        if (skp != null) {
            rxt.a(this.h, skp, this.i, rvm, this.j);
        }
    }
}
