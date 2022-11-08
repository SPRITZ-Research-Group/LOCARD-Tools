package jp.naver.myhome.android.activity.timeline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.bp;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.linecorp.line.timeline.ad.i;
import com.linecorp.line.timeline.floatingmenu.FloatingMenuButton;
import com.linecorp.line.timeline.floatingmenu.b;
import com.linecorp.multimedia.ui.r;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.mcw;
import defpackage.rmw;
import defpackage.rnh;
import defpackage.rni;
import defpackage.rvh;
import defpackage.rvi;
import defpackage.rvu;
import defpackage.rwe;
import defpackage.tfq;
import defpackage.tiu;
import defpackage.tls;
import defpackage.tlt;
import defpackage.tyg;
import defpackage.tyh;
import defpackage.ufh;
import defpackage.uuk;
import defpackage.vzc;
import defpackage.wbb;
import defpackage.wbc;
import defpackage.wcw;
import defpackage.wdj;
import defpackage.wdw;
import defpackage.wei;
import defpackage.wej;
import defpackage.wek;
import defpackage.wha;
import defpackage.whm;
import defpackage.wkk;
import defpackage.wkn;
import defpackage.wru;
import defpackage.wsa;
import defpackage.wsb;
import defpackage.wse;
import defpackage.wsk;
import defpackage.wsp;
import defpackage.wtt;
import defpackage.wub;
import defpackage.wuz;
import defpackage.wve;
import defpackage.wvf;
import defpackage.wvk;
import defpackage.wvq;
import defpackage.wvu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.R;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.cf;
import jp.naver.myhome.android.activity.feedsgroup.z;
import jp.naver.myhome.android.activity.mememaker.MemeMainActivity;
import jp.naver.myhome.android.activity.mememaker.ah;
import jp.naver.myhome.android.activity.write.group.k;
import jp.naver.myhome.android.api.f;
import jp.naver.myhome.android.model.ag;
import jp.naver.myhome.android.model.x;
import jp.naver.myhome.android.model2.bo;
import jp.naver.myhome.android.model2.bt;
import jp.naver.myhome.android.view.post.ad.a;
import kotlin.y;

public final class h {
    private boolean A = false;
    private wbb B;
    private ViewGroup C;
    private rnh D;
    private wkn E;
    private wru F;
    private b G;
    private Observable H;
    private RecyclerView I;
    private aj J;
    private LinearLayoutManager K;
    private WebView L;
    private i M;
    private final wbc N = new wbc(this) {
        final /* synthetic */ h a;
        private a b;
        private com.linecorp.line.timeline.ad.b c;

        {
            this.a = r1;
        }

        public final void a(bo boVar, boolean z) {
            int[] iArr;
            if (z) {
                iArr = new int[]{R.string.myhome_copy, R.string.timeline_hide_ad_post, R.string.timeline_hide_ad_account};
            } else {
                iArr = new int[]{R.string.timeline_hide_ad_post, R.string.timeline_hide_ad_account};
            }
            CharSequence[] charSequenceArr = new String[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                charSequenceArr[i] = this.a.a.getResources().getString(iArr[i]);
            }
            new tfq(this.a.a).b(charSequenceArr, new i(this.a, boVar, iArr)).f();
        }

        public final void a(View view, bo boVar, wse wse, int i) {
            if (AnonymousClass2.a(boVar, i)) {
                vzc.d().a(new wej(boVar, Boolean.valueOf(wbc.a(view, boVar, ((wdw) boVar.H.get(i)).g().d(), wse)), "ACTION_BUTTON", i));
                wdj.a().d();
            }
        }

        public final void b(View view, bo boVar, wse wse, int i) {
            if (AnonymousClass2.a(boVar, i)) {
                vzc.d().a(new wej(boVar, Boolean.valueOf(wbc.a(view, boVar, ((wdw) boVar.H.get(i)).g().d(), wse)), "TITLE", i));
                wdj.a().d();
            }
        }

        public final void c(View view, bo boVar, wse wse, int i) {
            Object obj = (boVar.e() && wsk.a(boVar.H) && boVar.H.size() > i && i >= 0 && wsk.a(((wdw) boVar.H.get(i)).i())) ? 1 : null;
            if (obj != null) {
                vzc.d().a(new wej(boVar, Boolean.valueOf(wbc.a(view, boVar, ((wdw) boVar.H.get(i)).i(), wse)), "IMAGE", i));
                wdj.a().d();
            }
        }

        public final void a(bo boVar) {
            this.b = new a(this.a.a, boVar, new -$$Lambda$h$2$I1e8VdXaG4G4kgK-0B0SRdMFJag());
            this.b.a();
        }

        public final void b(bo boVar) {
            new a(this.a.a, boVar, new -$$Lambda$h$2$I1e8VdXaG4G4kgK-0B0SRdMFJag()).c();
        }

        public final void c(bo boVar) {
            this.b = new a(this.a.a, boVar, new -$$Lambda$h$2$I1e8VdXaG4G4kgK-0B0SRdMFJag());
            this.b.a();
        }

        public final void a(String str, String str2) {
            if (this.a.M != null) {
                this.a.J.b().a(str2);
                if (!TextUtils.isEmpty(str)) {
                    i.a(this.a.J.b(), str);
                }
                this.a.j();
            }
        }

        public final void a(bo boVar, Boolean bool) {
            if (this.a.M != null) {
                this.c = new com.linecorp.line.timeline.ad.b(this.a.a, boVar, new -$$Lambda$h$2$fQNYEWZBO1-wh_WMvrjscdzQSkU());
                this.c.a(bool.booleanValue());
            }
        }

        private /* synthetic */ void a(y yVar) {
            a();
        }

        @Subscribe(a = SubscriberType.MAIN)
        public final void onMainActivityOnNewIntentEvent(rmw rmw) {
            if (this.b != null) {
                this.b.b();
                this.b = null;
            }
            if (this.c != null) {
                this.c = null;
            }
        }

        private void a() {
            this.a.j();
        }

        private static boolean a(bo boVar, int i) {
            return boVar.e() && wsk.a(boVar.H) && boVar.H.size() > i && i >= 0 && wsk.a((ag) boVar.H.get(i)) && wsk.a(((wdw) boVar.H.get(i)).g()) && wsk.a(((wdw) boVar.H.get(i)).g().d());
        }
    };
    final Activity a;
    TimelineExtraInfoView b;
    private final Handler c = new Handler();
    private final ViewGroup d;
    private final wsb e = new wsb();
    private final wkk f = new wkk();
    private final mcw g = new mcw(this.f, (byte) 0);
    private final q h = new q();
    private wsp i;
    private a j;
    private boolean k = false;
    private SwipeRefreshLayout l;
    private f m;
    private ai n;
    private ag o;
    private wtt p;
    private wei q;
    private wub r;
    private wcw s;
    private AsyncTask<Void, Void, Void> t;
    private AsyncTask<Void, Void, Void> u;
    private AsyncTask<Void, Void, Void> v;
    private f<Boolean> w;
    private k x;
    private boolean y = true;
    private boolean z = false;

    public h(Activity activity, ViewGroup viewGroup) {
        this.a = activity;
        this.d = viewGroup;
        this.B = new wbb(this.a, this.f, this.g, x.TIMELINE);
        this.o = new ag(this.a, new s());
        this.n = new ai(this.a, this.o);
        this.C = (ViewGroup) this.a.getWindow().getDecorView().findViewById(R.id.timeline_newpost_layout);
        this.m = new f(this, this.C);
        this.j = new a(this.a, this, new m());
        this.l = (SwipeRefreshLayout) this.d.findViewById(R.id.timeline_swipe_refresh_layout);
        this.I = (RecyclerView) this.d.findViewById(R.id.timeline_list);
        this.J = new aj(this.a, this, this.B, new r());
        this.I.setAdapter(this.J);
        this.K = new LinearLayoutManager(1, false);
        this.I.setLayoutManager(this.K);
        this.p = new wtt(this.I, this.K);
        this.r = new wub(new j(), new r(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final Context a() {
                return this.a.a;
            }

            public final int b() {
                return this.a.I.getChildCount();
            }

            public final View a(int i) {
                return this.a.I.getChildAt(i);
            }

            public final int a(View view) {
                return this.a.I.indexOfChild(view);
            }

            public final View c() {
                return this.a.I;
            }
        });
        this.r.j();
        this.D = new rnh(jp.naver.line.android.activity.main.a.TIMELINE, vzc.d());
        this.q = new wei(this.J);
        this.H = jp.naver.line.android.activity.main.x.a(this.a).i();
        wve wuz = new wuz(this.r, this.B);
        wve wvk = new wvk(this.r);
        wve wvq = new wvq(this.r, x.TIMELINE);
        wve wvf = new wvf(this.a, this.r, this.B);
        wve wvu = new wvu(this.r);
        this.r.a(wuz);
        this.r.a(wvq);
        this.r.a(wvf);
        this.r.a(wvu);
        this.s = new wcw(this.I);
        this.s.a(this.H);
        this.B.a(this.r.h());
        this.B.a(this.s.a());
        this.B.a(new l());
        this.i = new wsp(this.a, x.TIMELINE, new w(this, this.J.b(), this.r), null);
        int dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(R.dimen.main_others_tab_scroll_height);
        this.l.setProgressViewOffset(false, dimensionPixelOffset, this.a.getResources().getDimensionPixelOffset(R.dimen.main_tab_fragments_top_padding) + (dimensionPixelOffset / 2));
        wsa.a(tlt.h(), this.l);
        this.b = (TimelineExtraInfoView) this.d.findViewById(R.id.timeline_extra_infoview);
        this.b.setContentsView(this.l, this.I);
        this.E = new wkn(this.a, (ViewGroup) this.d.findViewById(R.id.timeline_popup_sticker_container));
        this.E.a();
        this.f.a(this.E);
        tlt.h().a(this.d, tls.MYHOME_BACKGROUND);
        if (uuk.INSTANCE.settings.ak) {
            i.a(LineApplication.a(this.a).r());
            this.M = new i(this.a);
            this.r.a(wvk);
        } else {
            wdj.a();
            vzc.d().b(this.q);
        }
        vzc.d().b(this.o);
        vzc.d().b(this.N);
        this.r.l();
        this.I.setPadding(0, this.a.getResources().getDimensionPixelOffset(R.dimen.main_tab_fragments_top_padding), 0, 0);
        dimensionPixelOffset = this.a.getResources().getDimensionPixelOffset(R.dimen.main_others_tab_scroll_height);
        int dimensionPixelOffset2 = this.a.getResources().getDimensionPixelOffset(R.dimen.main_tab_fragments_top_padding);
        this.l.setProgressViewOffset(false, dimensionPixelOffset, (dimensionPixelOffset / 2) + dimensionPixelOffset2);
        new rni(jp.naver.line.android.activity.main.a.TIMELINE, vzc.d()).b(dimensionPixelOffset2);
        this.G = new b((FloatingMenuButton) this.d.findViewById(R.id.timeline_floating_button), r());
        this.G.a(0);
        this.G.a(new -$$Lambda$h$bOhDou8RzTJtozOIk7fdtZti8kU());
        this.G.a(new -$$Lambda$h$atfRltnoUPrAaiG65ICABfmou_s());
        this.I.addOnScrollListener(new o());
        this.I.addOnScrollListener(this.D);
        this.I.addOnScrollListener(this.s);
        this.I.addOnScrollListener(new bp(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final void a(RecyclerView recyclerView, int i) {
                super.a(recyclerView, i);
                this.a.r.a(i);
            }

            public final void a(RecyclerView recyclerView, int i, int i2) {
                i = ((LinearLayoutManager) recyclerView.getLayoutManager()).o();
                i2 = recyclerView.getChildCount();
                recyclerView.getLayoutManager().L();
                this.a.r.a(i, i2);
            }
        });
        this.F = new wru(this.a, this.J, jp.naver.line.android.activity.main.x.a(this.d.getContext()).i());
        this.B.a(this.N).a(this.j).a(new -$$Lambda$h$fZMlfeE110MGBHJkpF-gmo0aprU()).a(this.i).a(this.r.k()).a(this.r.i());
        this.l.setOnRefreshListener(new -$$Lambda$h$HUSjZ4D213cXzSwB2lJwWyqbcps());
        vzc.d().b(this);
    }

    public final void a(boolean r5, boolean r6, defpackage.eod r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.timeline.h.a(boolean, boolean, eod):void. bs: []
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
        r4 = this;
        r0 = r4.c;
        r1 = r4.x;
        r0.removeCallbacks(r1);
        r0 = 1;
        r4.k = r0;
        r1 = r4.J;
        r1 = r1.c();
        r1.a();
        r1 = r4.m;
        r1.a();
        r1 = r4.C;
        r2 = 0;
        r1.setVisibility(r2);
        r1 = r4.r;
        r1.d();
        r1 = r4.s;
        r1.d();
        r1 = r4.L;
        if (r1 == 0) goto L_0x0033;
    L_0x002c:
        r1 = r4.L;
        r3 = 8;
        r1.setVisibility(r3);
    L_0x0033:
        r1 = r4.y;
        if (r1 == 0) goto L_0x004c;
    L_0x0037:
        r4.y = r2;
        r5 = new jp.naver.myhome.android.activity.timeline.n;
        r5.<init>(r4, r6);
        r4.t = r5;
        r5 = r4.t;
        r6 = jp.naver.line.android.util.at.b();
        r7 = new java.lang.Void[r2];
        r5.executeOnExecutor(r6, r7);
        return;
    L_0x004c:
        r1 = r4.b;
        r1 = r1.g();
        if (r1 == 0) goto L_0x0060;
    L_0x0054:
        r1 = r4.b;
        r1 = r1.h();
        if (r1 != 0) goto L_0x0060;
    L_0x005c:
        r4.a(r0, r2);
        return;
    L_0x0060:
        if (r6 != 0) goto L_0x010b;
    L_0x0062:
        r6 = r4.b;
        r6 = r6.f();
        if (r6 == 0) goto L_0x006c;
    L_0x006a:
        goto L_0x010b;
    L_0x006c:
        r6 = n();
        if (r6 == 0) goto L_0x0081;
    L_0x0072:
        if (r5 != 0) goto L_0x007c;
    L_0x0074:
        r5 = r4.b;
        r5 = r5.e();
        if (r5 == 0) goto L_0x0081;
    L_0x007c:
        r4.a(r0, r2);
        goto L_0x010e;
    L_0x0081:
        r5 = r4.m;
        r6 = r4.a;
        r1 = new jp.naver.myhome.android.activity.timeline.p;
        r1.<init>(r4, r2);
        r5.a(r6, r0, r1);
        r5 = r4.p();
        if (r5 == 0) goto L_0x010e;
    L_0x0093:
        r5 = r4.M;
        if (r5 == 0) goto L_0x00a3;
    L_0x0097:
        r5 = r4.M;
        r6 = r4.J;
        r6 = r6.b();
        r5.a(r6, r7);
        goto L_0x010e;
    L_0x00a3:
        r5 = defpackage.wdj.a();
        r6 = r4.J;
        r6 = r6.d();
        r5 = r5.a(r6);
        if (r5 == 0) goto L_0x010e;
    L_0x00b3:
        r6 = r4.J;
        r6 = r6.b();
        r7 = new java.util.ArrayList;
        r7.<init>();
        r1 = r6.c();
        if (r1 == 0) goto L_0x00e4;
    L_0x00c4:
        r3 = r1.size();
        r3 = r3 - r0;
    L_0x00c9:
        if (r3 < 0) goto L_0x00e4;
    L_0x00cb:
        r0 = r1.get(r3);
        r0 = (jp.naver.myhome.android.model2.bo) r0;
        r0 = r0.e();
        if (r0 == 0) goto L_0x00e1;
    L_0x00d7:
        r6.c(r3);
        r0 = java.lang.Integer.valueOf(r3);
        r7.add(r2, r0);
    L_0x00e1:
        r3 = r3 + -1;
        goto L_0x00c9;
    L_0x00e4:
        r5 = r5.second;
        r5 = (java.util.List) r5;
        r6 = 0;
    L_0x00e9:
        r0 = r5.size();
        if (r6 >= r0) goto L_0x010e;
    L_0x00ef:
        r0 = r5.get(r6);
        r0 = (jp.naver.myhome.android.model2.bo) r0;
        r1 = r4.J;
        r1 = r1.b();
        r3 = r7.get(r6);
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        r1.a(r3, r0);
        r6 = r6 + 1;
        goto L_0x00e9;
    L_0x010b:
        r4.a(r0, r2);
    L_0x010e:
        r5 = r4.A;
        if (r5 != 0) goto L_0x0199;
    L_0x0112:
        r5 = jp.naver.myhome.android.activity.timeline.ad.a();
        r5 = r5.c();
        r6 = r5.isEmpty();
        if (r6 != 0) goto L_0x0199;
    L_0x0120:
        r5 = r5.entrySet();
        r5 = r5.iterator();
    L_0x0128:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x0199;
    L_0x012e:
        r6 = r5.next();
        r6 = (java.util.Map.Entry) r6;
        r6 = r6.getValue();	 Catch:{ Exception -> 0x0197 }
        r6 = (android.os.Bundle) r6;	 Catch:{ Exception -> 0x0197 }
        r7 = "dataType";	 Catch:{ Exception -> 0x0197 }
        r7 = r6.getString(r7);	 Catch:{ Exception -> 0x0197 }
        if (r7 != 0) goto L_0x0145;	 Catch:{ Exception -> 0x0197 }
    L_0x0142:
        r7 = jp.naver.myhome.android.activity.timeline.af.UNDEFINED;	 Catch:{ Exception -> 0x0197 }
        goto L_0x014f;	 Catch:{ Exception -> 0x0197 }
    L_0x0145:
        r7 = "dataType";	 Catch:{ Exception -> 0x0197 }
        r7 = r6.getString(r7);	 Catch:{ Exception -> 0x0197 }
        r7 = jp.naver.myhome.android.activity.timeline.af.valueOf(r7);	 Catch:{ Exception -> 0x0197 }
    L_0x014f:
        r0 = jp.naver.myhome.android.activity.timeline.h.AnonymousClass4.a;	 Catch:{ Exception -> 0x0197 }
        r7 = r7.ordinal();	 Catch:{ Exception -> 0x0197 }
        r7 = r0[r7];	 Catch:{ Exception -> 0x0197 }
        switch(r7) {
            case 1: goto L_0x0172;
            case 2: goto L_0x015d;
            default: goto L_0x015a;
        };	 Catch:{ Exception -> 0x0197 }
    L_0x015a:
        r7 = "post";	 Catch:{ Exception -> 0x0197 }
        goto L_0x0183;	 Catch:{ Exception -> 0x0197 }
    L_0x015d:
        r7 = "postId";	 Catch:{ Exception -> 0x0197 }
        r6 = r6.getString(r7);	 Catch:{ Exception -> 0x0197 }
        if (r6 == 0) goto L_0x0128;	 Catch:{ Exception -> 0x0197 }
    L_0x0165:
        r7 = r4.J;	 Catch:{ Exception -> 0x0197 }
        r7 = r7.b();	 Catch:{ Exception -> 0x0197 }
        r7.a(r6);	 Catch:{ Exception -> 0x0197 }
        r5.remove();	 Catch:{ Exception -> 0x0197 }
        goto L_0x0128;	 Catch:{ Exception -> 0x0197 }
    L_0x0172:
        r7 = "post";	 Catch:{ Exception -> 0x0197 }
        r6 = r6.getSerializable(r7);	 Catch:{ Exception -> 0x0197 }
        r6 = (jp.naver.myhome.android.model2.bo) r6;	 Catch:{ Exception -> 0x0197 }
        if (r6 == 0) goto L_0x0128;	 Catch:{ Exception -> 0x0197 }
    L_0x017c:
        r4.a(r6, r2);	 Catch:{ Exception -> 0x0197 }
        r5.remove();	 Catch:{ Exception -> 0x0197 }
        goto L_0x0128;	 Catch:{ Exception -> 0x0197 }
    L_0x0183:
        r6 = r6.getSerializable(r7);	 Catch:{ Exception -> 0x0197 }
        r6 = (jp.naver.myhome.android.model2.bo) r6;	 Catch:{ Exception -> 0x0197 }
        if (r6 == 0) goto L_0x0128;	 Catch:{ Exception -> 0x0197 }
    L_0x018b:
        r7 = r4.J;	 Catch:{ Exception -> 0x0197 }
        r7 = r7.b();	 Catch:{ Exception -> 0x0197 }
        r0 = r6.d;	 Catch:{ Exception -> 0x0197 }
        r7.a(r0, r6);	 Catch:{ Exception -> 0x0197 }
        goto L_0x0128;
        goto L_0x0128;
    L_0x0199:
        r4.j();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.timeline.h.a(boolean, boolean, eod):void");
    }

    private static boolean n() {
        return wha.c() || wha.g();
    }

    public final void a() {
        this.A = false;
        this.k = false;
        this.J.c().b();
        this.m.b();
        this.r.e();
        this.s.c();
        ufh.f().a("ani_play_sound_sticker");
        this.p.b();
        this.n.a();
        this.B.c();
        this.j.dismiss();
        this.x = new k();
        this.c.postDelayed(this.x, 1000);
        this.C.setVisibility(8);
        this.F.b();
        this.E.c();
        this.G.a();
        bt c = this.J.b().c();
        if (c != null) {
            whm.a(c);
        }
    }

    public final void b() {
        this.c.removeCallbacks(this.x);
        this.r.f();
        this.s.b();
        this.f.c();
        this.f.d();
        this.g.a();
        tiu.a(this.t);
        tiu.a(this.u);
        tiu.a(this.v);
        vzc.d().c(this.q);
        vzc.d().c(this.o);
        vzc.d().c(this.N);
        this.r.m();
        this.B.b();
        vzc.d().c(this);
        if (this.M != null) {
            i.a();
        }
    }

    public final boolean c() {
        if (this.n.b()) {
            this.n.a();
            return true;
        } else if (this.E.b()) {
            this.E.c();
            return true;
        } else if (this.B.d() || this.G.b()) {
            return true;
        } else {
            if (this.J.e() || this.K.o() <= 0) {
                return false;
            }
            if (this.J.a(this.J.a(this.K.o())) < 10) {
                this.I.smoothScrollToPosition(0);
            } else {
                this.K.e(20);
                this.c.post(new -$$Lambda$h$m2InR12UgQlCPDtootVmNII5aK8());
            }
            if (this.m.d()) {
                a(true, true);
            }
            new rni(jp.naver.line.android.activity.main.a.TIMELINE, vzc.d()).a();
            return true;
        }
    }

    private /* synthetic */ void v() {
        this.I.smoothScrollToPosition(0);
    }

    public final void d() {
        wek.a().b();
        this.B.aF_();
    }

    public final boolean a(int i, int i2, Intent intent) {
        this.A = true;
        if (this.o.a(i, i2, intent) || this.i.a(i, i2, intent)) {
            return true;
        }
        return false;
    }

    public final boolean e() {
        return this.k;
    }

    public final void f() {
        if (this.k) {
            this.p.a();
        }
    }

    final void a(boolean z, boolean z2) {
        if (!this.a.isFinishing() && !tiu.b(this.t) && !tiu.b(this.u)) {
            if (z) {
                this.b.a();
            }
            this.m.c();
            this.u = new u(this, z, z2);
            this.u.executeOnExecutor(at.b(), new Void[0]);
        }
    }

    final aj g() {
        return this.J;
    }

    final boolean h() {
        return this.z;
    }

    final wsb i() {
        return this.e;
    }

    public final int a(bo boVar) {
        return boVar == null ? -1 : this.J.a(boVar);
    }

    private /* synthetic */ void u() {
        a(false, false);
    }

    private /* synthetic */ int b(bo boVar) {
        return this.J.a(boVar);
    }

    final void j() {
        if (!this.a.isFinishing()) {
            if (this.J.e()) {
                this.J.c().a(8);
                this.b.a(this.h);
            } else {
                this.J.c().a(0);
                this.b.d();
                o();
            }
            this.J.notifyDataSetChanged();
            this.F.a();
        }
    }

    private void o() {
        bt d = this.J.d();
        int i = 0;
        if (d == null || d.size() == 0) {
            this.J.a(false);
            return;
        }
        Iterator it = d.iterator();
        while (it.hasNext()) {
            if (((bo) it.next()).i()) {
                i = 1;
                break;
            }
        }
        this.J.a(i ^ true);
    }

    final void k() {
        if (!tiu.b(this.t) && !tiu.b(this.u) && !tiu.b(this.v)) {
            this.v = new t(this);
            this.v.executeOnExecutor(at.b(), new Void[0]);
        }
    }

    private boolean p() {
        boolean booleanValue = tyh.a(tyg.TIMELINE_AD_POST_COUNT_MINIMUM_EXCEEDED, Boolean.FALSE).booleanValue();
        return this.M != null ? i.g() && booleanValue : wdj.a().b() && booleanValue;
    }

    private void a(jp.naver.myhome.android.model2.bo r5, boolean r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.timeline.h.a(jp.naver.myhome.android.model2.bo, boolean):void. bs: []
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
        r4 = this;
        r0 = r4.b;
        r0 = r0.f();
        r1 = 0;
        if (r0 == 0) goto L_0x000e;
    L_0x0009:
        r5 = 1;
        r4.a(r5, r1);
        return;
    L_0x000e:
        if (r5 == 0) goto L_0x0046;
    L_0x0010:
        r0 = r4.J;
        r0 = r0.getItemCount();
        r2 = r4.J;
        r2 = r2.b();
        r3 = r4.q();
        r2.a(r3, r5);
        r4.j();
        if (r6 == 0) goto L_0x0033;
    L_0x0028:
        r6 = r4.I;
        r0 = new jp.naver.myhome.android.activity.timeline.-$$Lambda$h$i2iJcQflKaPeHHwOK6NVYp7rnaY;
        r0.<init>(r4);
        r6.post(r0);
        goto L_0x0036;
    L_0x0033:
        r4.a(r0);
    L_0x0036:
        r6 = r5.n;	 Catch:{ Exception -> 0x0046 }
        r6 = r6.d;	 Catch:{ Exception -> 0x0046 }
        r6 = r6.get(r1);	 Catch:{ Exception -> 0x0046 }
        r6 = (jp.naver.myhome.android.model.y) r6;	 Catch:{ Exception -> 0x0046 }
        r5 = r5.d;	 Catch:{ Exception -> 0x0046 }
        defpackage.wsl.a(r6, r5);	 Catch:{ Exception -> 0x0046 }
        return;
    L_0x0046:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.timeline.h.a(jp.naver.myhome.android.model2.bo, boolean):void");
    }

    private /* synthetic */ void t() {
        this.K.e(0);
        this.D.a();
    }

    private int q() {
        bt d = this.J.d();
        return (d.size() <= 0 || !((bo) d.get(0)).a(jp.naver.myhome.android.model2.ag.FRIENDS_UPDATE)) ? 0 : 1;
    }

    private void a(int i) {
        int itemCount = this.J.getItemCount() - i;
        i = this.K.o();
        int i2 = 0;
        View childAt = this.I.getChildAt(0);
        if (childAt != null) {
            i2 = childAt.getTop();
        }
        this.J.notifyDataSetChanged();
        if (i != 0) {
            this.K.d(i + itemCount, i2);
            this.D.b();
        }
    }

    public static String l() {
        return rvh.TIMELINE.name;
    }

    private /* synthetic */ void e(View view) {
        this.G.a(r());
    }

    private ArrayList<com.linecorp.line.timeline.floatingmenu.f> r() {
        ArrayList<com.linecorp.line.timeline.floatingmenu.f> arrayList = new ArrayList();
        arrayList.add(new com.linecorp.line.timeline.floatingmenu.f(this.a.getResources().getString(R.string.myhome_write_timeline_menu), R.drawable.floating_ic_write_02, new -$$Lambda$h$66aCiUThJiSJehMSpRBJDeNevcU()));
        arrayList.add(new com.linecorp.line.timeline.floatingmenu.f(this.a.getResources().getString(R.string.myhome_camera_timeline_menu), R.drawable.floating_ic_camera, new -$$Lambda$h$k4fvfRWkyXLMq0yJELjGL5uKBO4()));
        if (!k.b()) {
            arrayList.add(new com.linecorp.line.timeline.floatingmenu.f(this.a.getResources().getString(R.string.timeline_menu_relay), R.drawable.floating_ic_relay, new -$$Lambda$h$Z2Wb5kUZQjUiEnTQ85JV9gtq9ZE()));
        }
        if (uuk.a().settings.b()) {
            arrayList.add(new com.linecorp.line.timeline.floatingmenu.f(this.a.getResources().getString(R.string.timeline_mememaker_button_mememaker), R.drawable.floating_ic_meme, new -$$Lambda$h$7R85u3DGe-8xNz8qcfVJBcxw2xw()));
        }
        return arrayList;
    }

    private /* synthetic */ void d(View view) {
        this.o.a();
        rwe.a(this.a, rvi.WRITE_FLOATING_WRITE);
    }

    private /* synthetic */ void c(View view) {
        this.o.a(false, null);
        rwe.a(this.a, rvi.WRITE_FLOATING_CAMERA);
    }

    private /* synthetic */ void b(View view) {
        this.o.b();
        rwe.a(this.a, rvi.WRITE_FLOATING_RELAY);
    }

    private /* synthetic */ void a(View view) {
        this.a.startActivity(MemeMainActivity.a(this.a, "", ah.FLOATING, "", "", ""));
        rwe.a(this.a, rvi.WRITE_FLOATING_MEME);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onNeedToRefreshFeed(z zVar) {
        a(true, true);
    }

    private /* synthetic */ Boolean s() {
        if (!cf.a(this.a)) {
            return Boolean.FALSE;
        }
        this.o.a();
        rwe.a(this.a, rvi.WRITE_FLOATING_WRITE);
        return Boolean.TRUE;
    }

    static /* synthetic */ void a(h hVar, int i) {
        boolean z = i >= 20;
        if (hVar.p() && !z) {
            rwe.a(-1, rvu.AD_NO_TRY);
        }
        tyh.b(tyg.TIMELINE_AD_POST_COUNT_MINIMUM_EXCEEDED, z);
    }
}
