package jp.naver.myhome.android.activity.write;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.group.SquareGroupUtils;
import com.linecorp.square.group.db.model.SquareGroupDto;
import defpackage.oyn;
import defpackage.oyp;
import defpackage.oyr;
import defpackage.ozh;
import defpackage.ozm;
import defpackage.qaa;
import defpackage.qcj;
import defpackage.rts;
import defpackage.tfq;
import defpackage.tfu;
import defpackage.tiq;
import defpackage.tyg;
import defpackage.tyh;
import defpackage.uuk;
import defpackage.vzc;
import defpackage.vzd;
import defpackage.wcy;
import defpackage.wcz;
import defpackage.weo;
import defpackage.weq;
import defpackage.wgo;
import defpackage.whg;
import defpackage.who;
import defpackage.wjh;
import defpackage.wji;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import jp.naver.line.android.LineApplication;
import jp.naver.line.android.R;
import jp.naver.line.android.activity.grouphome.OneToOneHomeGuideActivity;
import jp.naver.line.android.analytics.ga.fc;
import jp.naver.line.android.bo.z;
import jp.naver.line.android.db.main.model.ContactDto;
import jp.naver.line.android.util.at;
import jp.naver.line.android.view.h;
import jp.naver.myhome.android.activity.write.group.Group;
import jp.naver.myhome.android.activity.write.group.GroupSelectActivity;
import jp.naver.myhome.android.activity.write.group.k;
import jp.naver.myhome.android.activity.write.writeform.view.b;
import jp.naver.myhome.android.api.l;
import jp.naver.myhome.android.model.PrivacyGroup;
import jp.naver.myhome.android.model2.a;
import jp.naver.myhome.android.model2.bo;

final class q implements OnClickListener {
    private boolean a;
    private boolean b;
    private final Handler c = new Handler();
    private final Activity d;
    private final WriteHeaderView e;
    private final View f;
    private final View g;
    private a h;
    private List<Long> i;
    private WriteParams j;
    private List<PrivacyGroup> k = Collections.emptyList();
    private Group l;
    private a m = a.FRIEND;
    private com.linecorp.rxeventbus.a n;
    private PopupWindow o;
    private PopupWindow p;
    private r q;
    private final ozm r = new ozm();
    private final b s;

    private static /* synthetic */ void a(Object obj) throws Exception {
    }

    private static /* synthetic */ void a(Throwable th) throws Exception {
    }

    q(WriteHeaderView writeHeaderView, com.linecorp.rxeventbus.a aVar) {
        b c;
        a aVar2;
        this.e = writeHeaderView;
        this.d = (Activity) writeHeaderView.getContext();
        this.f = writeHeaderView.a();
        this.n = aVar;
        this.f.setOnClickListener(this);
        this.g = writeHeaderView.b();
        if (this.d instanceof jp.naver.myhome.android.activity.write.writeform.view.a) {
            c = ((jp.naver.myhome.android.activity.write.writeform.view.a) this.d).c();
        } else {
            c = b.NORMAL;
        }
        this.s = c;
        switch (k.c()) {
            case ONLY_ME:
                aVar2 = a.NONE;
                break;
            case FRIENDS:
                aVar2 = a.FRIEND;
                break;
            case PUBLIC:
                aVar2 = a.ALL;
                break;
            default:
                aVar2 = a.FRIEND;
                break;
        }
        this.m = aVar2;
        aVar.b(this);
    }

    final void a() {
        this.n.c(this);
        Object obj = (!k() || this.b) ? null : 1;
        if (obj != null) {
            String name = this.m.name();
            if (this.m == a.GROUP && qcj.b(this.k)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Long l : g()) {
                    stringBuilder.append(",");
                    stringBuilder.append(l);
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(name);
                stringBuilder2.append(stringBuilder.toString());
                name = stringBuilder2.toString();
            }
            tyh.c(tyg.HOME_WRITING_LAST_ALLOW_SCOPE, name);
        }
        this.c.removeCallbacksAndMessages(null);
    }

    final boolean a(int i, int i2, Intent intent) {
        if (i2 == 0) {
            return false;
        }
        switch (i) {
            case 20251:
                if (intent != null) {
                    a(intent.getParcelableArrayListExtra("selected_privacy_group_list"), (a) intent.getSerializableExtra("selected_allow_scope"));
                }
                return true;
            case 20252:
                f();
                return true;
            default:
                return false;
        }
    }

    final boolean b() {
        if (this.o == null || !this.o.isShowing()) {
            return false;
        }
        this.o.dismiss();
        return true;
    }

    final void a(boolean z) {
        this.f.setEnabled(z);
    }

    final void c() {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
        }
        if (this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
    }

    final a d() {
        return this.m;
    }

    final Group e() {
        return this.l;
    }

    final void f() {
        if (!k.b()) {
            this.c.postDelayed(new -$$Lambda$q$jmuw_7I1jENXexaIGFeXQy75d7g(), 200);
        }
    }

    private /* synthetic */ void n() {
        onClick(this.e.b());
    }

    final List<Long> g() {
        if (qcj.a(this.k)) {
            return Collections.emptyList();
        }
        List<Long> arrayList = new ArrayList(this.k.size());
        for (PrivacyGroup privacyGroup : this.k) {
            arrayList.add(Long.valueOf(privacyGroup.a));
        }
        return arrayList;
    }

    final void a(bo boVar, WriteParams writeParams) {
        boolean b;
        View inflate;
        View findViewById;
        View findViewById2;
        OnLayoutChangeListener -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg;
        OnClickListener -__lambda_q_ck_4eryw3hploqyrfoxohirds3y;
        this.j = writeParams;
        boolean z = false;
        if (boVar != null) {
            boolean a = a(boVar.r.n);
            this.h = boVar.r.n;
            this.i = boVar.r.o;
            String str = boVar.c;
            String str2 = boVar.e.b;
            if (str != null && str.length() > 0 && !str.equals(str2)) {
                a(str, null);
            } else if (qcj.b(boVar.r.o) && a) {
                a(boVar.r.o, false, false);
            }
        }
        if (writeParams != null) {
            char c = writeParams.a;
            if (c != 'g') {
                if (c != 'm') {
                    if (c == 't') {
                        b = k.b() ^ true;
                        this.e.setEditable(b);
                        if (b) {
                            this.g.setOnClickListener(this);
                        }
                        if (a(this.j)) {
                            a(a.ALL);
                        } else if (k() && !a(this.j.z)) {
                            a(tyh.a(tyg.HOME_WRITING_LAST_ALLOW_SCOPE, null));
                            if (this.j != null) {
                                switch (k.c()) {
                                    case ONLY_ME:
                                        break;
                                    case FRIENDS:
                                        if (!(this.j.r == 4 || this.j.r == 0)) {
                                            z = true;
                                            break;
                                        }
                                    default:
                                        z = this.j.r;
                                        break;
                                }
                            }
                            if (z) {
                                if (this.s == b.NORMAL) {
                                    switch (this.j.e) {
                                        case MYHOME:
                                            if (!whg.i.c() && uuk.a().settings.bG) {
                                                whg.i.a(true);
                                                inflate = LayoutInflater.from(this.d).inflate(R.layout.home_write_share_guide_tooltip, null);
                                                this.o = new PopupWindow(inflate, -1, -1);
                                                this.o.setOutsideTouchable(true);
                                                findViewById = inflate.findViewById(R.id.background_view);
                                                findViewById2 = inflate.findViewById(R.id.img_close);
                                                -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg = new -$$Lambda$q$MQ7p7hF2LVzT2Nq1S29s6PhAqcg(inflate.findViewById(R.id.txt_msg), inflate.findViewById(R.id.msg_container_scrollview));
                                                -__lambda_q_ck_4eryw3hploqyrfoxohirds3y = new -$$Lambda$q$cK_4eryw3hPLOqYrfoxohirdS3Y(this, findViewById, -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg);
                                                findViewById.addOnLayoutChangeListener(-__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg);
                                                findViewById2.setOnClickListener(-__lambda_q_ck_4eryw3hploqyrfoxohirds3y);
                                                findViewById.setOnClickListener(-__lambda_q_ck_4eryw3hploqyrfoxohirds3y);
                                                this.c.postDelayed(new -$$Lambda$q$O6iGrG8Uv6EU9SGiq-kg_Hk_zzY(), 200);
                                                break;
                                            }
                                        case POSTS_BY_HASHTAG:
                                            if (!(this.m == a.ALL || !k.a() || tyh.a(tyg.HOME_WRITING_HASHTAG_ALLOW_SCOPE_GUIDE_SHOWN, Boolean.FALSE).booleanValue())) {
                                                a((int) R.string.timeline_tag_results_write_guide);
                                                tyh.b(tyg.HOME_WRITING_HASHTAG_ALLOW_SCOPE_GUIDE_SHOWN, true);
                                                break;
                                            }
                                    }
                                }
                            }
                            if (z || this.m == a.ALL) {
                                if (z || this.m == a.ALL || this.m == a.FRIEND) {
                                    if (z || this.m == a.ALL || this.m == a.FRIEND) {
                                        if (z && this.m != a.ALL) {
                                            a((int) R.string.myhome_writing_change_permission_tooltip_user);
                                        }
                                    } else if (a(a.ALL)) {
                                        this.b = true;
                                        a((int) R.string.myhome_writing_change_permission_tooltip_friend_public);
                                    }
                                } else if (a(a.FRIEND)) {
                                    this.b = true;
                                    a((int) R.string.myhome_writing_change_permission_tooltip_friend);
                                }
                            } else if (a(a.ALL)) {
                                this.b = true;
                                a((int) R.string.myhome_writing_change_permission_tooltip_public);
                            }
                            if (this.r.b() > 0 && this.m != a.GROUP) {
                                this.r.dispose();
                            }
                        }
                        j();
                    }
                } else if (!(a(writeParams) || k.b())) {
                    b = true;
                    this.e.setEditable(b);
                    if (b) {
                        this.g.setOnClickListener(this);
                    }
                    if (a(this.j)) {
                        a(tyh.a(tyg.HOME_WRITING_LAST_ALLOW_SCOPE, null));
                        if (this.j != null) {
                            switch (k.c()) {
                                case ONLY_ME:
                                    break;
                                case FRIENDS:
                                    z = true;
                                    break;
                                default:
                                    z = this.j.r;
                                    break;
                            }
                        }
                        if (z) {
                            if (this.s == b.NORMAL) {
                                switch (this.j.e) {
                                    case MYHOME:
                                        whg.i.a(true);
                                        inflate = LayoutInflater.from(this.d).inflate(R.layout.home_write_share_guide_tooltip, null);
                                        this.o = new PopupWindow(inflate, -1, -1);
                                        this.o.setOutsideTouchable(true);
                                        findViewById = inflate.findViewById(R.id.background_view);
                                        findViewById2 = inflate.findViewById(R.id.img_close);
                                        -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg = new -$$Lambda$q$MQ7p7hF2LVzT2Nq1S29s6PhAqcg(inflate.findViewById(R.id.txt_msg), inflate.findViewById(R.id.msg_container_scrollview));
                                        -__lambda_q_ck_4eryw3hploqyrfoxohirds3y = new -$$Lambda$q$cK_4eryw3hPLOqYrfoxohirdS3Y(this, findViewById, -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg);
                                        findViewById.addOnLayoutChangeListener(-__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg);
                                        findViewById2.setOnClickListener(-__lambda_q_ck_4eryw3hploqyrfoxohirds3y);
                                        findViewById.setOnClickListener(-__lambda_q_ck_4eryw3hploqyrfoxohirds3y);
                                        this.c.postDelayed(new -$$Lambda$q$O6iGrG8Uv6EU9SGiq-kg_Hk_zzY(), 200);
                                        break;
                                    case POSTS_BY_HASHTAG:
                                        a((int) R.string.timeline_tag_results_write_guide);
                                        tyh.b(tyg.HOME_WRITING_HASHTAG_ALLOW_SCOPE_GUIDE_SHOWN, true);
                                        break;
                                }
                            }
                        }
                        if (z) {
                        }
                        if (z) {
                        }
                        if (z) {
                        }
                        a((int) R.string.myhome_writing_change_permission_tooltip_user);
                        this.r.dispose();
                    } else {
                        a(a.ALL);
                    }
                    j();
                }
            } else if (TextUtils.isEmpty(writeParams.c) && TextUtils.isEmpty(writeParams.d)) {
                wcy.a.f("group homeId or chatId are empty.");
                b(4020);
                this.d.finish();
                return;
            } else {
                a(writeParams.c, writeParams.d);
            }
        }
        b = false;
        this.e.setEditable(b);
        if (b) {
            this.g.setOnClickListener(this);
        }
        if (a(this.j)) {
            a(a.ALL);
        } else {
            a(tyh.a(tyg.HOME_WRITING_LAST_ALLOW_SCOPE, null));
            if (this.j != null) {
                switch (k.c()) {
                    case ONLY_ME:
                        break;
                    case FRIENDS:
                        z = true;
                        break;
                    default:
                        z = this.j.r;
                        break;
                }
            }
            if (z) {
                if (this.s == b.NORMAL) {
                    switch (this.j.e) {
                        case MYHOME:
                            whg.i.a(true);
                            inflate = LayoutInflater.from(this.d).inflate(R.layout.home_write_share_guide_tooltip, null);
                            this.o = new PopupWindow(inflate, -1, -1);
                            this.o.setOutsideTouchable(true);
                            findViewById = inflate.findViewById(R.id.background_view);
                            findViewById2 = inflate.findViewById(R.id.img_close);
                            -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg = new -$$Lambda$q$MQ7p7hF2LVzT2Nq1S29s6PhAqcg(inflate.findViewById(R.id.txt_msg), inflate.findViewById(R.id.msg_container_scrollview));
                            -__lambda_q_ck_4eryw3hploqyrfoxohirds3y = new -$$Lambda$q$cK_4eryw3hPLOqYrfoxohirdS3Y(this, findViewById, -__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg);
                            findViewById.addOnLayoutChangeListener(-__lambda_q_mq7p7hf2lvzt2nq1s29s6phaqcg);
                            findViewById2.setOnClickListener(-__lambda_q_ck_4eryw3hploqyrfoxohirds3y);
                            findViewById.setOnClickListener(-__lambda_q_ck_4eryw3hploqyrfoxohirds3y);
                            this.c.postDelayed(new -$$Lambda$q$O6iGrG8Uv6EU9SGiq-kg_Hk_zzY(), 200);
                            break;
                        case POSTS_BY_HASHTAG:
                            a((int) R.string.timeline_tag_results_write_guide);
                            tyh.b(tyg.HOME_WRITING_HASHTAG_ALLOW_SCOPE_GUIDE_SHOWN, true);
                            break;
                    }
                }
            }
            if (z) {
            }
            if (z) {
            }
            if (z) {
            }
            a((int) R.string.myhome_writing_change_permission_tooltip_user);
            this.r.dispose();
        }
        j();
    }

    final boolean h() {
        if (this.h != null) {
            if (this.h != this.m) {
                return true;
            }
            if (this.h == a.GROUP && this.i != null) {
                Collection g = g();
                if (!(this.i.size() == g.size() && this.i.containsAll(g))) {
                    return true;
                }
            }
        }
        return false;
    }

    final boolean i() {
        return !qcj.a(this.i) && this.a;
    }

    final boolean a(Exception exception) {
        DialogInterface.OnClickListener -__lambda_q_4ax1thbwgcxdskobr_xnrwseamo;
        boolean z = true;
        if (exception instanceof weq) {
            -__lambda_q_4ax1thbwgcxdskobr_xnrwseamo = new -$$Lambda$q$4aX1thbwGCxdskOBR_XnRwSeAMo(this, ((weq) exception).c());
        } else if ((exception instanceof weo) && ((weo) exception).b() == l.BLOCKED_USER) {
            -__lambda_q_4ax1thbwgcxdskobr_xnrwseamo = new -$$Lambda$q$VHPpqhM42S2wNBe1wAsYJ_6sk9Y();
        } else {
            -__lambda_q_4ax1thbwgcxdskobr_xnrwseamo = null;
            z = false;
        }
        if (z) {
            Dialog b = tfu.b(this.d, wgo.b(exception), -__lambda_q_4ax1thbwgcxdskobr_xnrwseamo);
            b.setCancelable(false);
            b.setCanceledOnTouchOutside(false);
        }
        return z;
    }

    private /* synthetic */ void a(Long[] lArr, DialogInterface dialogInterface, int i) {
        if (lArr != null) {
            this.k = (List) oyn.a(this.k).a(new -$$Lambda$q$3zyzpQ_YWxZ00C0xc5P5moCsKzM(Arrays.asList(lArr))).o().e();
            if (this.k.isEmpty()) {
                a(a.FRIEND);
            }
            j();
            oyn.a(new -$$Lambda$q$V1fNzI0Je4swPnKAFLLiT5sqZA0(this, lArr)).b(qaa.a(at.b())).a(-$$Lambda$q$o-hOwQwKOrX6PL35rxigVifcj2k.INSTANCE, -$$Lambda$q$GC9VQDP_JHPCynUIW8xjI0CCZo4.INSTANCE);
        }
    }

    private static /* synthetic */ boolean a(List list, PrivacyGroup privacyGroup) throws Exception {
        return !list.contains(Long.valueOf(privacyGroup.a));
    }

    private /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        h hVar = new h(this.d);
    }

    final void a(r rVar) {
        this.q = rVar;
    }

    public final void onClick(View view) {
        if (view == this.f) {
            if (this.q != null) {
                this.q.a();
            }
        } else if (view == this.g) {
            List arrayList = new ArrayList();
            if (this.q != null) {
                arrayList.addAll(this.q.b());
            }
            Intent a = GroupSelectActivity.a(view.getContext(), this.k, this.m, arrayList);
            Activity activity = (Activity) view.getContext();
            activity.startActivityForResult(a, 20251);
            activity.overridePendingTransition(-1, -1);
            if (this.s == b.SHARE) {
                rts.a().a(fc.TIMELINE_SHARE_FORM_SHARETO);
                return;
            }
            rts.a().a(fc.TIMELINE_WRITING_FORM_SHARETO);
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onAllowScopeChanged(wcz wcz) {
        if (wcz.a) {
            a(wcz.b, wcz.c);
        }
    }

    private void a(List<PrivacyGroup> list, a aVar) {
        this.k = list;
        this.a = false;
        a(aVar);
        j();
    }

    private void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            this.r.a(b(str, str2).a(new -$$Lambda$q$EqqBJuE5ka9qTBA3Uehl2YaW2UY(), new -$$Lambda$q$fO_My9sqMV8VYM7Z8YGUtL695-g(this, str2)));
        }
    }

    private /* synthetic */ void a(Group group) throws Exception {
        this.l = group;
        j();
    }

    private /* synthetic */ void a(String str, Throwable th) throws Exception {
        ContactDto b = z.a().b(str);
        Object obj = (b == null || !b.f()) ? null : 1;
        if (obj != null) {
            OneToOneHomeGuideActivity.a(this.d, str, this.j);
            this.d.finish();
        }
        tfu.b(this.d, (int) R.string.myhome_err_no_group_informations, new h(this.d));
    }

    private void a(List<Long> list, boolean z, boolean z2) {
        this.r.a(a((List) list, z2).a(new -$$Lambda$q$FQR9OuxVMRUuG-fHJ0ZGqwCCb9o(), new -$$Lambda$q$M4BfG-XrlKbHBUv-q4vjx3_RLLA(this, z, z2)));
    }

    private /* synthetic */ void d(List list) throws Exception {
        this.k = list;
        j();
    }

    private /* synthetic */ void a(boolean z, boolean z2, Throwable th) throws Exception {
        if (z && !z2) {
            tyh.c(tyg.HOME_WRITING_LAST_ALLOW_SCOPE, a.FRIEND.name());
            b(1014);
        }
        this.a = true;
        a(a.FRIEND);
        this.k = Collections.emptyList();
        j();
    }

    private static boolean a(WriteParams writeParams) {
        return (writeParams == null || writeParams.a != 'm' || vzd.a(writeParams.c)) ? false : true;
    }

    private void j() {
        this.e.a(this.m, this.k, this.l);
        if (this.q != null) {
            boolean z = false;
            if (!(this.l == null && this.m == a.NONE)) {
                z = true;
            }
            this.q.a(z, g(), this.l);
        }
    }

    private boolean k() {
        return (this.j == null || this.j.a == 'g' || (this.s != b.NORMAL && this.s != b.SHARE)) ? false : true;
    }

    private boolean a(java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.write.q.a(java.lang.String):boolean. bs: []
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
        r6 = this;
        r0 = android.text.TextUtils.isEmpty(r7);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = 0;
        r2 = jp.naver.myhome.android.model2.a.ALL;
        r2 = r2.name();
        r2 = r7.equalsIgnoreCase(r2);
        if (r2 == 0) goto L_0x001b;
    L_0x0015:
        r2 = jp.naver.myhome.android.model2.a.ALL;
        r6.a(r2);
        goto L_0x0060;
    L_0x001b:
        r2 = jp.naver.myhome.android.model2.a.FRIEND;
        r2 = r2.name();
        r2 = r7.equalsIgnoreCase(r2);
        if (r2 == 0) goto L_0x002d;
    L_0x0027:
        r2 = jp.naver.myhome.android.model2.a.FRIEND;
        r6.a(r2);
        goto L_0x0060;
    L_0x002d:
        r2 = jp.naver.myhome.android.model2.a.NONE;
        r2 = r2.name();
        r2 = r7.equalsIgnoreCase(r2);
        if (r2 == 0) goto L_0x003f;
    L_0x0039:
        r2 = jp.naver.myhome.android.model2.a.NONE;
        r6.a(r2);
        goto L_0x0060;
    L_0x003f:
        r0 = jp.naver.myhome.android.model2.a.GROUP;
        r0 = r0.name();
        r0 = r7.startsWith(r0);
        if (r0 == 0) goto L_0x0052;
    L_0x004b:
        r0 = ",";
        r0 = r7.split(r0);
        goto L_0x0060;
    L_0x0052:
        r0 = "SHARELIST";
        r0 = r7.startsWith(r0);
        if (r0 == 0) goto L_0x008c;
    L_0x005a:
        r0 = "\\|";
        r0 = r7.split(r0);
    L_0x0060:
        r2 = 1;
        if (r0 == 0) goto L_0x008b;
    L_0x0063:
        r3 = new java.util.ArrayList;	 Catch:{ NumberFormatException -> 0x008a }
        r3.<init>();	 Catch:{ NumberFormatException -> 0x008a }
        r4 = 1;	 Catch:{ NumberFormatException -> 0x008a }
    L_0x0069:
        r5 = r0.length;	 Catch:{ NumberFormatException -> 0x008a }
        if (r4 >= r5) goto L_0x0078;	 Catch:{ NumberFormatException -> 0x008a }
    L_0x006c:
        r5 = r0[r4];	 Catch:{ NumberFormatException -> 0x008a }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ NumberFormatException -> 0x008a }
        r3.add(r5);	 Catch:{ NumberFormatException -> 0x008a }
        r4 = r4 + 1;	 Catch:{ NumberFormatException -> 0x008a }
        goto L_0x0069;	 Catch:{ NumberFormatException -> 0x008a }
    L_0x0078:
        r0 = jp.naver.myhome.android.model2.a.GROUP;	 Catch:{ NumberFormatException -> 0x008a }
        r0 = r6.a(r0);	 Catch:{ NumberFormatException -> 0x008a }
        if (r0 == 0) goto L_0x008b;	 Catch:{ NumberFormatException -> 0x008a }
    L_0x0080:
        r0 = "SHARELIST";	 Catch:{ NumberFormatException -> 0x008a }
        r7 = r7.startsWith(r0);	 Catch:{ NumberFormatException -> 0x008a }
        r6.a(r3, r2, r7);	 Catch:{ NumberFormatException -> 0x008a }
        goto L_0x008b;
    L_0x008a:
        return r1;
    L_0x008b:
        return r2;
    L_0x008c:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.write.q.a(java.lang.String):boolean");
    }

    private boolean a(a aVar) {
        this.m = aVar;
        if (a(this.j)) {
            return true;
        }
        switch (k.c()) {
            case ONLY_ME:
                if (aVar == a.ALL || aVar == a.FRIEND || aVar == a.GROUP) {
                    this.m = a.NONE;
                    return false;
                }
            case FRIENDS:
                if (aVar == a.ALL) {
                    this.m = a.FRIEND;
                    return false;
                }
                break;
        }
        return true;
    }

    private static /* synthetic */ void a(View view, View view2, View view3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int bottom = view.getBottom() - view2.getHeight();
        if (bottom > 0) {
            view2.scrollTo(0, bottom);
        }
    }

    private /* synthetic */ void a(View view, OnLayoutChangeListener onLayoutChangeListener, View view2) {
        this.o.dismiss();
        view.removeOnLayoutChangeListener(onLayoutChangeListener);
    }

    private /* synthetic */ void m() {
        if (!tiq.a(this.d) && this.e.getWindowToken() != null) {
            this.o.showAtLocation(this.e, 119, 0, 0);
        }
    }

    private void a(int i) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.home_write_event_allowed_scope_tooltip, null);
        ((TextView) inflate.findViewById(R.id.guide_text_view)).setText(i);
        this.p = new PopupWindow(inflate, -1, -2);
        this.p.setBackgroundDrawable(new ColorDrawable(0));
        this.p.setOutsideTouchable(true);
        this.p.setFocusable(true);
        inflate.setOnClickListener(new -$$Lambda$q$7aKjUC1K4HiyM4FVBuZPVvsOOig());
        this.e.post(new -$$Lambda$q$XmeEo2FOibuWQa9qU25DMyLkZvI());
    }

    private /* synthetic */ void a(View view) {
        this.p.dismiss();
    }

    private /* synthetic */ void l() {
        int[] iArr = new int[2];
        this.e.getLocationOnScreen(iArr);
        this.p.showAtLocation(this.e, 0, 0, iArr[1]);
    }

    private void b(int i) {
        if (i == 1014) {
            new tfq(this.d).a((int) R.string.myhome_write_form_share_list_deleted).b((int) R.string.myhome_write_form_share_list_deleted_content).a((int) R.string.confirm, null).f();
        } else if (i == 4020) {
            tfu.a(this.d, (int) R.string.err_temporary_problem_occured, null).show();
        }
    }

    private oyn<Group> b(String str, String str2) {
        return oyn.a(new -$$Lambda$q$77lp9NjTFmUTpA0b-p5Nb-Wk0Qc(str, str2)).a(-$$Lambda$q$_ALiIyJE5Uhwr9KQMcHk6SGDcg0.INSTANCE).d(new -$$Lambda$q$gs7KgYoisxgYkBsWGMF_txuAUYU()).b(qaa.a(at.b())).a(ozh.a());
    }

    private static /* synthetic */ void a(String str, String str2, oyp oyp) throws Exception {
        if (TextUtils.isEmpty(str)) {
            oyp.a((Object) wjh.e(str2).a);
        } else {
            oyp.a((Object) str);
        }
        oyp.a();
    }

    private static /* synthetic */ boolean c(String str) throws Exception {
        return !TextUtils.isEmpty(str);
    }

    private /* synthetic */ oyr b(String str) throws Exception {
        SquareGroupUtils squareGroupUtils = SquareGroupUtils.a;
        return SquareGroupUtils.a(str) ? oyn.a(new -$$Lambda$q$ghkpEkf9GSAnGBCL-C2Eg-umfuo(str)) : oyn.a(new -$$Lambda$q$RK5OwaddmbWhIN94_SJbC5VEG1c(str));
    }

    private static /* synthetic */ void b(String str, oyp oyp) throws Exception {
        wji g = wjh.g(str);
        if (g == null) {
            oyp.a(new Exception("Group isn't found."));
        } else {
            oyp.a((Object) new Group().a(g));
        }
        oyp.a();
    }

    private static /* synthetic */ void a(String str, oyp oyp) throws Exception {
        SquareGroupDto squareGroupDto = (SquareGroupDto) ((LineApplication) vzc.c()).g().c().a(str).e();
        if (squareGroupDto == null) {
            oyp.a(new Exception("SquareGroup isn't found."));
        } else {
            oyp.a((Object) new Group().a(squareGroupDto));
        }
        oyp.a();
    }

    private /* synthetic */ void a(Long[] lArr, oyp oyp) throws Exception {
        who.a(null, lArr);
        oyp.a(null);
        oyp.a();
    }

    private oyn<List<PrivacyGroup>> a(List<Long> list, boolean z) {
        if (qcj.a((Collection) list)) {
            return oyn.c();
        }
        return a((List) list).d(z ? b((List) list) : oyn.c()).d(oyn.a(new Exception("PrivacyGroup isn't found."))).b(qaa.a(at.b())).a(ozh.a());
    }

    private static oyn<List<PrivacyGroup>> a(List<Long> list) {
        return oyn.b(who.a(10, (List) list)).d(-$$Lambda$q$j91hYf220u9xnIfelTThJ_I6SBE.INSTANCE);
    }

    private static /* synthetic */ oyr c(List list) throws Exception {
        if (list.isEmpty()) {
            return oyn.c();
        }
        return oyn.b((Object) list);
    }

    private oyn<List<PrivacyGroup>> b(List<Long> list) {
        return oyn.b(Boolean.valueOf(jp.naver.myhome.android.activity.privacygroup.controller.k.a().b())).d(new -$$Lambda$q$jSWDk8VoHvTKyWP9W3fzRFUxvhE(this, list));
    }

    private /* synthetic */ oyr a(List list, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return a(list);
        }
        return oyn.c();
    }
}
