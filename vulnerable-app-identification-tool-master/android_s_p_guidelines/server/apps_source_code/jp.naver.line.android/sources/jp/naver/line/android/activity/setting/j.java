package jp.naver.line.android.activity.setting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.acnz;
import defpackage.acob;
import defpackage.acoy;
import defpackage.acqq;
import defpackage.acrz;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import defpackage.cmp;
import defpackage.cpm;
import defpackage.mnt;
import defpackage.rzd;
import defpackage.rzf;
import defpackage.rzu;
import defpackage.sal;
import defpackage.sba;
import defpackage.sbb;
import defpackage.sbc;
import defpackage.tfq;
import defpackage.tlb;
import defpackage.tlc;
import defpackage.tld;
import defpackage.tlt;
import defpackage.tlu;
import defpackage.vim;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import jp.naver.line.android.R;
import jp.naver.line.android.common.view.header.Header;
import jp.naver.line.android.customview.settings.BaseSettingCategoryTitleView;
import jp.naver.line.android.customview.settings.SettingButton;
import jp.naver.line.android.util.ca;
import jp.naver.line.android.util.dv;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 ?2\u00020\u0001:\u0001?B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0011H\u0002J\u0010\u0010-\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0011H\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0007J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020/H\u0002J\u0006\u00106\u001a\u00020/J\u0016\u00107\u001a\u00020/2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\b\u00109\u001a\u00020/H\u0002J\u0016\u0010:\u001a\u00020/2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\b\u0010<\u001a\u00020/H\u0002J\u0014\u0010=\u001a\u00020/*\u00020+2\u0006\u0010>\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b\"\u0010\u000e\u001a\u0004\b!\u0010\u0015R!\u0010#\u001a\u00020$8BX\u0002¢\u0006\u0012\n\u0004\b)\u0010\u000e\u0012\u0004\b%\u0010&\u001a\u0004\b'\u0010(¨\u0006@"}, d2 = {"Ljp/naver/line/android/activity/setting/SettingsAutoSuggestLanguageViewController;", "", "activity", "Landroid/app/Activity;", "dataManager", "Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionDictionaryDataManager;", "rootContainer", "Landroid/view/ViewGroup;", "(Landroid/app/Activity;Ljp/naver/line/android/autosuggestion2/datamanager/AutoSuggestionDictionaryDataManager;Landroid/view/ViewGroup;)V", "addLanguagesTitleView", "Ljp/naver/line/android/customview/settings/BaseSettingCategoryTitleView;", "getAddLanguagesTitleView", "()Ljp/naver/line/android/customview/settings/BaseSettingCategoryTitleView;", "addLanguagesTitleView$delegate", "Lkotlin/Lazy;", "cachedDictionaries", "", "Ljp/naver/line/android/autosuggestion2/db/model/DictionaryResource;", "downloadedDictionaryContainer", "Landroid/widget/LinearLayout;", "getDownloadedDictionaryContainer", "()Landroid/widget/LinearLayout;", "downloadedDictionaryContainer$delegate", "header", "Ljp/naver/line/android/common/view/header/Header;", "getHeader", "()Ljp/naver/line/android/common/view/header/Header;", "header$delegate", "headerViewPresenter", "Ljp/naver/line/android/common/view/header/HeaderViewPresenter;", "isEditMode", "", "notDownloadedDictionaryContainer", "getNotDownloadedDictionaryContainer", "notDownloadedDictionaryContainer$delegate", "progressDialog", "Landroid/app/ProgressDialog;", "progressDialog$annotations", "()V", "getProgressDialog", "()Landroid/app/ProgressDialog;", "progressDialog$delegate", "createDownloadedSettingButton", "Ljp/naver/line/android/customview/settings/SettingButton;", "dictionaryResource", "createNotDownloadedSettingButton", "onDictionaryStateChanged", "", "event", "Ljp/naver/line/android/autosuggestion2/event/AutoSuggestionDictionaryStateChangedEvent;", "showRetryAndCancelDialog", "dictionaryKey", "", "toggleEditMode", "update", "updateDownloadedDictionaryContainer", "downloadedDictionaries", "updateHeader", "updateNotDownloadedDictionaryContainer", "notDownloadedDictionaries", "updateViews", "updateProgressBar", "shouldShow", "Companion", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class j {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsi(acso.a(j.class), "header", "getHeader()Ljp/naver/line/android/common/view/header/Header;")), acso.a(new acsi(acso.a(j.class), "downloadedDictionaryContainer", "getDownloadedDictionaryContainer()Landroid/widget/LinearLayout;")), acso.a(new acsi(acso.a(j.class), "addLanguagesTitleView", "getAddLanguagesTitleView()Ljp/naver/line/android/customview/settings/BaseSettingCategoryTitleView;")), acso.a(new acsi(acso.a(j.class), "notDownloadedDictionaryContainer", "getNotDownloadedDictionaryContainer()Landroid/widget/LinearLayout;")), acso.a(new acsi(acso.a(j.class), "progressDialog", "getProgressDialog()Landroid/app/ProgressDialog;"))};
    public static final k b = new k();
    private static final tlc[] m;
    private final kotlin.e c;
    private final kotlin.e d;
    private final kotlin.e e;
    private final kotlin.e f;
    private final jp.naver.line.android.common.view.header.f g = new jp.naver.line.android.common.view.header.f();
    private final kotlin.e h = h.a(new d(this));
    private boolean i;
    private List<sal> j = acob.a;
    private final Activity k;
    private final rzu l;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
    final class a implements OnClickListener {
        final /* synthetic */ j a;
        final /* synthetic */ sal b;

        a(j jVar, sal sal) {
            this.a = jVar;
            this.b = sal;
        }

        public final void onClick(View view) {
            this.a.l.a(this.b.b()).a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 13})
    final class b implements OnCheckedChangeListener {
        final /* synthetic */ j a;
        final /* synthetic */ sal b;

        b(j jVar, sal sal) {
            this.a = jVar;
            this.b = sal;
        }

        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.a.l.a(this.b.b(), z).a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
    final class c implements OnClickListener {
        final /* synthetic */ j a;
        final /* synthetic */ SettingButton b;
        final /* synthetic */ rzf c;

        c(j jVar, SettingButton settingButton, rzf rzf) {
            this.a = jVar;
            this.b = settingButton;
            this.c = rzf;
        }

        public final void onClick(View view) {
            if (!this.b.f()) {
                j.a(this.b, true);
                this.a.l.a((rzd) this.c);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 13})
    final class e implements DialogInterface.OnClickListener {
        final /* synthetic */ j a;
        final /* synthetic */ String b;

        e(j jVar, String str) {
            this.a = jVar;
            this.b = str;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.a.l.a((rzd) new rzf(this.b));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 13})
    final class f implements DialogInterface.OnClickListener {
        final /* synthetic */ j a;

        f(j jVar) {
            this.a = jVar;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.a.a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 1, 13})
    public final class h<T> implements Comparator<T> {
        public final int compare(T t, T t2) {
            return acoy.a(Integer.valueOf(((sal) t).h()), Integer.valueOf(((sal) t2).h()));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 1, 13})
    public final class i<T> implements Comparator<T> {
        public final int compare(T t, T t2) {
            return acoy.a(Integer.valueOf(((sal) t).h()), Integer.valueOf(((sal) t2).h()));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "dictionaries", "", "Ljp/naver/line/android/autosuggestion2/db/model/DictionaryResource;", "accept"}, k = 3, mv = {1, 1, 13})
    final class g<T> implements cpm<P> {
        final /* synthetic */ j a;

        g(j jVar) {
            this.a = jVar;
        }

        public final /* synthetic */ void accept(Object obj) {
            this.a.j = (List) obj;
            this.a.e();
            if (this.a.d().isShowing()) {
                this.a.d().dismiss();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/app/ProgressDialog;", "invoke"}, k = 3, mv = {1, 1, 13})
    final class d extends acrz implements acqq<ProgressDialog> {
        final /* synthetic */ j a;

        d(j jVar) {
            this.a = jVar;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            ProgressDialog progressDialog = new ProgressDialog(this.a.k);
            progressDialog.setMessage(this.a.k.getString(R.string.progress));
            progressDialog.setCancelable(false);
            return progressDialog;
        }
    }

    private final LinearLayout b() {
        return (LinearLayout) this.d.d();
    }

    private final LinearLayout c() {
        return (LinearLayout) this.f.d();
    }

    private final ProgressDialog d() {
        return (ProgressDialog) this.h.d();
    }

    public j(Activity activity, rzu rzu, ViewGroup viewGroup) {
        this.k = activity;
        this.l = rzu;
        View view = viewGroup;
        this.c = dv.e(view, R.id.header);
        this.d = dv.e(view, R.id.downloaded_dictionary_container);
        this.e = dv.e(view, R.id.add_languages_title);
        this.f = dv.e(view, R.id.not_downloaded_dictionary_container);
        this.g.a((Header) this.c.d());
        this.g.a(this.k.getString(R.string.settings_auto_suggest_languages));
        this.g.a(jp.naver.line.android.common.view.header.e.RIGHT, (OnClickListener) new OnClickListener(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                j.e(this.a);
            }
        });
        tlu tlu = tlt.b;
        tlt a = tlu.a();
        tlc[] tlcArr = m;
        a.a(view, (tlc[]) Arrays.copyOf(tlcArr, tlcArr.length));
        d().show();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onDictionaryStateChanged(sba sba) {
        if (sba instanceof sbc) {
            a();
            return;
        }
        if (sba instanceof sbb) {
            new tfq(this.k).a((CharSequence) this.k.getString(R.string.error)).b((CharSequence) this.k.getString(R.string.settings_auto_suggest_download_failed)).a((int) R.string.retry, (DialogInterface.OnClickListener) new e(this, sba.a())).b((int) R.string.cancel, (DialogInterface.OnClickListener) new f(this)).a(false).b(false).e().show();
        }
    }

    public final void a() {
        this.l.d().a((cmp) ca.a((cpm) new g(this))).a();
    }

    private final void e() {
        Collection arrayList = new ArrayList();
        Iterator it = this.j.iterator();
        while (true) {
            Object obj = 1;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((sal) next).d() == -1) {
                obj = null;
            }
            if (obj != null) {
                arrayList.add(next);
            }
        }
        List list = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : this.j) {
            if ((((sal) next2).d() == -1 ? 1 : null) != null) {
                arrayList2.add(next2);
            }
        }
        List list2 = (List) arrayList2;
        if (this.i && list.isEmpty()) {
            this.i = false;
        }
        jp.naver.line.android.common.view.header.f fVar = this.g;
        if (this.i) {
            fVar.a(jp.naver.line.android.common.view.header.e.RIGHT, (CharSequence) this.k.getString(R.string.btn_done));
            fVar.a(jp.naver.line.android.common.view.header.e.RIGHT, 0);
        } else {
            fVar.a(jp.naver.line.android.common.view.header.e.RIGHT, (CharSequence) this.k.getString(R.string.edit));
        }
        a(list);
        b(list2);
    }

    private final void a(List<sal> list) {
        b().removeAllViews();
        for (sal sal : acnz.a((Iterable) list, (Comparator) new h())) {
            LinearLayout b = b();
            SettingButton settingButton = new SettingButton((Context) this.k, sal.c());
            if (this.i) {
                settingButton.d((OnClickListener) new a(this, sal));
            } else {
                settingButton.k(sal.g());
                settingButton.a((OnCheckedChangeListener) new b(this, sal));
            }
            b.addView(settingButton, -1, -2);
        }
        mnt.a((View) b(), list.isEmpty() ^ 1);
    }

    private final void b(List<sal> list) {
        c().removeAllViews();
        for (sal sal : acnz.a((Iterable) list, (Comparator) new i())) {
            LinearLayout c = c();
            rzf rzf = new rzf(sal.b());
            SettingButton settingButton = new SettingButton((Context) this.k, sal.c());
            a(settingButton, this.l.b(rzf.a()));
            settingButton.setOnClickListener(new c(this, settingButton, rzf));
            c.addView(settingButton, -1, -2);
        }
        Collection collection = list;
        mnt.a((View) (BaseSettingCategoryTitleView) this.e.d(), collection.isEmpty() ^ 1);
        mnt.a((View) c(), collection.isEmpty() ^ 1);
    }

    private static void a(SettingButton settingButton, boolean z) {
        if (z) {
            settingButton.j(-1);
            settingButton.h(true);
            return;
        }
        settingButton.j((int) R.drawable.setting_ic_download);
        settingButton.h(false);
    }

    static {
        tlc[] tlcArr = new tlc[1];
        tld tld = new tld(R.id.main_topbar_shadow);
        tlb[] tlbArr = vim.c;
        tlcArr[0] = tld.a((tlb[]) Arrays.copyOf(tlbArr, tlbArr.length)).a();
        m = tlcArr;
    }

    public static final /* synthetic */ void e(j jVar) {
        jVar.i ^= 1;
        jVar.e();
    }
}
