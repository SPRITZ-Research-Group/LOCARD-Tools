package jp.naver.myhome.android.activity.relay.write;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.group.SquareGroupUtils;
import defpackage.addb;
import defpackage.mno;
import defpackage.mnt;
import defpackage.oyz;
import defpackage.ozh;
import defpackage.ozm;
import defpackage.qaa;
import defpackage.qcj;
import defpackage.rts;
import defpackage.rut;
import defpackage.rvh;
import defpackage.rvi;
import defpackage.rwe;
import defpackage.tfq;
import defpackage.tfu;
import defpackage.tjl;
import defpackage.wbz;
import defpackage.wcz;
import defpackage.whg;
import defpackage.who;
import defpackage.wiz;
import defpackage.wts;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import jp.naver.line.android.R;
import jp.naver.line.android.analytics.ga.fc;
import jp.naver.line.android.analytics.ga.ff;
import jp.naver.line.android.util.at;
import jp.naver.line.android.util.bd;
import jp.naver.line.android.util.e;
import jp.naver.line.android.util.text.l;
import jp.naver.myhome.android.activity.write.MediaUploadStatusViewerActivity;
import jp.naver.myhome.android.activity.write.group.Group;
import jp.naver.myhome.android.activity.write.group.GroupSelectActivity;
import jp.naver.myhome.android.activity.write.group.k;
import jp.naver.myhome.android.activity.write.writeform.model.MediaAttachmentModel;
import jp.naver.myhome.android.activity.write.writeform.model.UploadListModel;
import jp.naver.myhome.android.annotation.Click;
import jp.naver.myhome.android.annotation.ViewId;
import jp.naver.myhome.android.model.PrivacyGroup;
import jp.naver.myhome.android.model2.a;
import jp.naver.myhome.android.model2.bo;
import jp.naver.myhome.android.model2.cc;
import jp.naver.myhome.android.model2.ck;

final class h implements OnClickListener {
    private boolean A;
    private e B;
    private final ozm C = new ozm();
    private ProgressDialog D;
    private final TextWatcher E = new TextWatcher(this) {
        final /* synthetic */ h a;

        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        {
            this.a = r1;
        }

        public final void afterTextChanged(Editable editable) {
            this.a.k.setGravity(editable.length() > 0 ? 17 : 0);
            this.a.x = true;
            this.a.l();
        }
    };
    private final RelayWriteActivity a;
    private final bo b;
    private final String c;
    private final String d;
    private final String e;
    private final boolean f;
    private final mno<View> g;
    private o h;
    private View i;
    @ViewId(a = 2131363587)
    private View j;
    @ViewId(a = 2131368934)
    private EditText k;
    @ViewId(a = 2131363348)
    private View l;
    @ViewId(a = 2131363345)
    private View m;
    @ViewId(a = 2131363344)
    private ImageView n;
    @ViewId(a = 2131368730)
    private TextView o;
    @ViewId(a = 2131363530)
    private View p;
    @ViewId(a = 2131365591)
    private View q;
    @ViewId(a = 2131365618)
    private View r;
    @ViewId(a = 2131364344)
    private View s;
    @ViewId(a = 2131366971)
    private View t;
    private a u = a.FRIEND;
    private List<PrivacyGroup> v = Collections.emptyList();
    private Group w;
    private boolean x = false;
    private String y;
    private int z;

    h(RelayWriteActivity relayWriteActivity, Intent intent, ViewStub viewStub) {
        this.a = relayWriteActivity;
        this.b = (bo) intent.getSerializableExtra("relayPost");
        this.c = intent.getStringExtra("placeHolderTitle");
        this.d = intent.getStringExtra("placeHolderImageOid");
        this.e = intent.getStringExtra("selectedGroupHomeId");
        this.f = TextUtils.isEmpty(this.e) ^ 1;
        this.y = intent.getStringExtra("feedPublicJoinedPostId");
        this.w = (Group) intent.getParcelableExtra("group");
        this.g = new mno(viewStub, (byte) 0);
    }

    final void a() {
        if (!this.g.a()) {
            View f = this.g.f();
            wts.a((Object) this, f);
            this.h = new o(f.findViewById(R.id.write_header_view));
            this.B = new e(this.a, new wbz(), this.r, this.d, false);
            this.B.a(this.q, 0.2f);
            this.h.a();
            this.i = this.h.b();
            this.i.setOnClickListener(this);
            this.k.addTextChangedListener(this.E);
            this.k.setFilters(new InputFilter[]{new LengthFilter(70), new l()});
            this.k.setImeActionLabel(this.a.getString(R.string.done), 6);
            this.k.setOnEditorActionListener(new -$$Lambda$h$9Me2CHCNlluRY6oElSADo64Uwjk());
            if (!this.a.b()) {
                mnt.a(this.j, true);
                mnt.a(this.l, false);
            }
            if (this.b != null && this.b.n.k != null) {
                cc ccVar = this.b.n.k;
                this.k.setText(ccVar.a());
                this.a.f().postDelayed(new -$$Lambda$h$drlg9JPyhJqaBJnx5k552A8mC1A(), 200);
                this.z = ccVar.c();
                this.A = ccVar.d();
            } else if (!TextUtils.isEmpty(this.c)) {
                this.k.setText(this.c);
            }
            if (this.w == null && !TextUtils.isEmpty(this.e)) {
                this.w = a(this.e);
            }
            this.x = false;
            l();
            new i().executeOnExecutor(at.b(), new bo[]{this.b});
        }
        this.a.getWindow().setSoftInputMode(48);
        c();
    }

    final boolean b() {
        return this.g.e();
    }

    final void c() {
        if (this.a.b()) {
            ff.a(null, "timeline_relaypost_createform");
        }
    }

    private /* synthetic */ boolean a(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        bd.a(this.a, this.k);
        return true;
    }

    private /* synthetic */ void u() {
        this.k.setSelection(this.k.length());
        if (this.a.d()) {
            bd.a(this.a, this.k, 0);
        }
    }

    final jp.naver.myhome.android.activity.write.group.Group a(java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.relay.write.h.a(java.lang.String):jp.naver.myhome.android.activity.write.group.Group. bs: []
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
        r1 = this;
        r0 = com.linecorp.square.group.SquareGroupUtils.a;
        r0 = com.linecorp.square.group.SquareGroupUtils.a(r2);
        if (r0 == 0) goto L_0x002c;
    L_0x0008:
        r0 = r1.a;	 Catch:{ Exception -> 0x003c }
        r0 = r0.getApplication();	 Catch:{ Exception -> 0x003c }
        r0 = (jp.naver.line.android.LineApplication) r0;	 Catch:{ Exception -> 0x003c }
        r0 = r0.g();	 Catch:{ Exception -> 0x003c }
        r0 = r0.c();	 Catch:{ Exception -> 0x003c }
        r2 = r0.a(r2);	 Catch:{ Exception -> 0x003c }
        r2 = r2.e();	 Catch:{ Exception -> 0x003c }
        r2 = (com.linecorp.square.group.db.model.SquareGroupDto) r2;	 Catch:{ Exception -> 0x003c }
        r0 = new jp.naver.myhome.android.activity.write.group.Group;	 Catch:{ Exception -> 0x003c }
        r0.<init>();	 Catch:{ Exception -> 0x003c }
        r2 = r0.a(r2);	 Catch:{ Exception -> 0x003c }
        goto L_0x003d;
    L_0x002c:
        r2 = defpackage.wjh.g(r2);
        if (r2 == 0) goto L_0x003c;
    L_0x0032:
        r0 = new jp.naver.myhome.android.activity.write.group.Group;
        r0.<init>();
        r2 = r0.a(r2);
        goto L_0x003d;
    L_0x003c:
        r2 = 0;
    L_0x003d:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.relay.write.h.a(java.lang.String):jp.naver.myhome.android.activity.write.group.Group");
    }

    private void l() {
        boolean z = true;
        boolean a = addb.a(p()) ^ true;
        if (this.a.b()) {
            this.i.setEnabled(a);
            return;
        }
        View view = this.i;
        if (!(a && this.x)) {
            z = false;
        }
        view.setEnabled(z);
    }

    final boolean a(int i, int i2, Intent intent) {
        if (i == 1002) {
            if (i2 == -1 && intent != null) {
                this.x = true;
                if (intent.getBooleanExtra("relay_permission_selected", false)) {
                    this.v = intent.getParcelableArrayListExtra("selected_privacy_group_list");
                    this.u = (a) intent.getSerializableExtra("selected_allow_scope");
                }
                this.z = intent.getIntExtra("relay_duration", 30);
                this.A = intent.getBooleanExtra("relay_noti_flag", false);
                a(true);
            }
            return true;
        } else if (i != 1003) {
            return false;
        } else {
            if (i2 == -1) {
                o();
            } else if (MediaUploadStatusViewerActivity.a(intent)) {
                if (tjl.a()) {
                    tfu.b(this.a, (int) R.string.err_temporary_problem_occured, null);
                } else {
                    tfu.b(this.a, (int) R.string.myhome_err_conection_error_process, null);
                }
            }
            return true;
        }
    }

    private void a(boolean z) {
        if (!(this.f || k.a())) {
            this.u = a.FRIEND;
        }
        if (this.h != null) {
            this.h.a(this.z, this.u, this.v, this.w);
        }
        l();
        if (z) {
            q();
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onPrivacySettingsChanged(wcz wcz) {
        if (this.g.a()) {
            this.x = true;
            if (wcz.a) {
                this.v = wcz.b;
                this.u = wcz.c;
            } else {
                this.z = wcz.d;
                this.A = wcz.e;
            }
            a(true);
        }
    }

    public final void onClick(View view) {
        if (view == this.i) {
            if (this.a.b()) {
                rut rut = rut.EMPTY;
                if (this.a.o() != null) {
                    rut = rut.EXIST;
                }
                rwe.b(rvh.RELAYWRITE.name, rvi.RELAY_POST.name, rut.name);
                if (this.f || qcj.a(this.v)) {
                    m();
                } else {
                    n();
                }
            } else if (this.f || qcj.a(this.v)) {
                o();
            } else {
                n();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Click(a = {2131369501})
    public final void onClickHeaderView() {
        Intent a = GroupSelectActivity.a(this.a, this.v, this.u, Collections.emptyList());
        boolean z = false;
        boolean z2 = (this.b == null || this.b.t == null) && (this.w == null || !TextUtils.equals(this.w.a, this.e));
        long j = this.b != null ? this.b.g : 0;
        boolean b = this.a.b() ^ true;
        boolean z3 = this.u == null;
        if (this.w != null) {
            SquareGroupUtils squareGroupUtils = SquareGroupUtils.a;
        }
        z = true;
        a.putExtra("is_relay_writing_mode", true).putExtra("relay_is_edit_mode", b).putExtra("relay_is_first", z3).putExtra("relay_permission_enabled", z2).putExtra("relay_notification_enabled", z).putExtra("relay_noti_flag", this.A).putExtra("relay_created_time", j).putExtra("relay_duration", this.z);
        this.a.startActivityForResult(a, 1002);
        this.a.overridePendingTransition(-1, -1);
        rts.a().a(fc.RELAYPOST_WRITING_FORM_SHARETO);
    }

    @Click(a = {2131369500, 2131368934, 2131365591})
    public final void onClickDimmed() {
        if (this.B != null && this.B.a()) {
            this.B.b();
        }
    }

    @Click(a = {2131365618})
    public final void onClickAttachLayout() {
        if (!(mnt.a(this.p) || this.B == null)) {
            if (this.B.a()) {
                this.B.b();
            } else {
                bd.a(this.a);
                this.B.c();
            }
        }
    }

    @Click(a = {2131363530})
    public final void onClickDelete() {
        this.a.q();
    }

    final void d() {
        if (this.g.a()) {
            this.n.setImageBitmap(null);
            mnt.a(this.m, false);
            mnt.a(this.p, false);
            mnt.a(this.r, true);
            this.x = true;
        }
    }

    private void m() {
        UploadListModel h = jp.naver.myhome.android.activity.write.writeform.upload.k.a().h();
        if (h == null || h.b()) {
            o();
            return;
        }
        this.a.startActivityForResult(new Intent(this.a, MediaUploadStatusViewerActivity.class), 1003);
    }

    private void n() {
        this.D = e.a(this.a).c((int) R.string.progress);
        this.D.setCancelable(false);
        this.D.show();
        this.C.a(oyz.b(new -$$Lambda$h$QbTYspYUFRrgNnSeDxpWj3L-Dn4()).b(qaa.a(at.b())).a(ozh.a()).a(new -$$Lambda$h$cacyYzR41u3ddYjYP8iXb1nCi44(), new -$$Lambda$h$UaUjsI4zCPHlHZvVokrn4WuY2cU()));
    }

    private /* synthetic */ List t() throws Exception {
        return who.a(10, null);
    }

    private /* synthetic */ void a(List list) throws Exception {
        Collection arrayList = new ArrayList(this.v);
        for (PrivacyGroup privacyGroup : this.v) {
            if (!list.contains(privacyGroup)) {
                arrayList.remove(privacyGroup);
            }
        }
        this.v = arrayList;
        this.D.cancel();
        if (qcj.a(arrayList)) {
            s();
            tfu.b(this.a, (int) R.string.myhome_write_form_share_list_deleted_content, null);
        } else if (this.a.b()) {
            m();
        } else {
            if (this.a.d()) {
                o();
            }
        }
    }

    private /* synthetic */ void a(Throwable th) throws Exception {
        this.D.cancel();
    }

    @Click(a = {2131363345})
    public final void onClickContentLayout() {
        if (mnt.a(this.p)) {
            bd.a(this.a, this.k);
            this.a.p();
        }
    }

    private void o() {
        new j().executeOnExecutor(at.b(), new Void[]{null});
    }

    final void a(MediaAttachmentModel mediaAttachmentModel, ck ckVar) {
        boolean z = (mediaAttachmentModel == null && ckVar == null) ? false : true;
        mnt.a(this.n, mediaAttachmentModel != null);
        mnt.a(this.o, ckVar != null);
        mnt.a(this.m, z);
        mnt.a(this.p, z);
        mnt.a(this.r, z ^ 1);
        if (mediaAttachmentModel != null) {
            if (mediaAttachmentModel.c.a == wiz.IMAGE) {
                this.n.setImageURI(Uri.fromFile(new File(mediaAttachmentModel.c.d)));
                mnt.a(this.s, mediaAttachmentModel.c.d());
                mnt.a(this.t, false);
            } else {
                this.n.setImageBitmap(mediaAttachmentModel.b);
                mnt.a(this.s, false);
                mnt.a(this.t, true);
            }
        } else if (ckVar != null) {
            this.o.setText(ckVar.a());
            this.o.setBackgroundColor(ckVar.c());
            mnt.a(this.s, false);
            mnt.a(this.t, false);
        }
        a(this.p, z);
        a(this.t, z);
        a(this.s, z);
        a(this.i, z);
        this.x = true;
    }

    private static void a(View view, boolean z) {
        float f = 1.0f;
        view.setAlpha(z ? BitmapDescriptorFactory.HUE_RED : 1.0f);
        ViewPropertyAnimator duration = view.animate().setDuration(300);
        if (!z) {
            f = BitmapDescriptorFactory.HUE_RED;
        }
        duration.alpha(f).start();
    }

    final a e() {
        if (this.w != null) {
            return null;
        }
        return this.u;
    }

    final String f() {
        return this.g.a() ? p() : null;
    }

    final View g() {
        return this.n;
    }

    final View h() {
        return this.o;
    }

    private String p() {
        return this.k.getText().toString();
    }

    final boolean i() {
        if (!this.g.e()) {
            return false;
        }
        if (this.B == null || !this.B.a()) {
            boolean z = this.a.b() ? !TextUtils.isEmpty(p()) || mnt.a(this.p) : this.x;
            if (!z) {
                return false;
            }
            new tfq(this.a).b((int) R.string.timeline_relay_cancel).a((int) R.string.myhome_yes, new jp.naver.line.android.view.h(this.a)).b((int) R.string.myhome_no, null).f();
            return true;
        }
        this.B.b();
        return true;
    }

    final Pair<List<Long>, String> j() {
        Object obj = null;
        if (!this.g.a()) {
            return null;
        }
        List r = r();
        if (this.w != null) {
            obj = this.w.a;
        }
        return new Pair(r, obj);
    }

    private void q() {
        whg.d.a(this.A);
        whg.c.a(this.z);
        if (!(this.f || this.u == null || this.w != null)) {
            String name = this.u.name();
            if (this.u == a.GROUP && qcj.b(this.v)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Long l : r()) {
                    stringBuilder.append(",");
                    stringBuilder.append(l);
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(name);
                stringBuilder2.append(stringBuilder.toString());
                name = stringBuilder2.toString();
            }
            whg.e.a(name);
        }
    }

    private List<Long> r() {
        if (qcj.a(this.v)) {
            return Collections.emptyList();
        }
        List<Long> arrayList = new ArrayList(this.v.size());
        for (PrivacyGroup privacyGroup : this.v) {
            arrayList.add(Long.valueOf(privacyGroup.a));
        }
        return arrayList;
    }

    private void s() {
        if (!this.f) {
            this.w = null;
            this.u = a.FRIEND;
            this.v = Collections.emptyList();
            a(true);
        }
    }

    public final void k() {
        this.C.dispose();
    }

    static /* synthetic */ List k(h hVar) {
        List arrayList = new ArrayList();
        int i;
        if (qcj.b(hVar.v)) {
            for (i = 0; i < hVar.v.size(); i++) {
                arrayList.add(Long.valueOf(((PrivacyGroup) hVar.v.get(i)).a));
            }
        } else if (hVar.e() == a.GROUP) {
            if (hVar.b == null) {
                String[] split = whg.e.c().split(",");
                for (i = 1; i < split.length; i++) {
                    arrayList.add(Long.valueOf(split[i]));
                }
            } else {
                arrayList.addAll(hVar.b.r.o);
            }
        }
        return arrayList;
    }
}
