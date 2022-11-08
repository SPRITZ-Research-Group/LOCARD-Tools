package com.linecorp.line.timeline.ad;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.linecorp.line.ad.timeline.view.AdPostReactionView;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.acry;
import defpackage.dsw;
import defpackage.dxm;
import defpackage.dxq;
import defpackage.ebd;
import defpackage.ebf;
import defpackage.ebs;
import defpackage.lil;
import defpackage.oyn;
import defpackage.ozn;
import defpackage.paj;
import defpackage.pap;
import defpackage.qas;
import defpackage.qaz;
import defpackage.tls;
import defpackage.tlt;
import defpackage.tlu;
import defpackage.vzc;
import defpackage.wdx;
import defpackage.whe;
import defpackage.wnc;
import defpackage.wnp;
import defpackage.wns;
import defpackage.wsa;
import defpackage.wsk;
import defpackage.wsx;
import defpackage.wwx;
import jp.naver.myhome.android.annotation.PostItemViewAttr;
import jp.naver.myhome.android.model.ag;
import jp.naver.myhome.android.model2.bo;
import kotlin.Metadata;

@PostItemViewAttr(b = {0.0f, 0.0f, 0.0f, 0.0f})
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002\"#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0014\u0010\u0012\u001a\u00020\u00102\n\u0010\u0013\u001a\u00060\u0014R\u00020\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0017H\u0007J\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nJ&\u0010\u0019\u001a\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u001e\u0010\u001f\u001a\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010!\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/linecorp/line/timeline/ad/LadPostContainerView;", "Landroid/widget/RelativeLayout;", "cxt", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adPostViewManager", "Lcom/linecorp/line/ad/timeline/AdPostViewManager;", "disposableSet", "Lcom/linecorp/rxjava/DisposableSet;", "listener", "Ljp/naver/myhome/android/view/post/listener/PostListener;", "post", "Ljp/naver/myhome/android/model2/Post;", "reactionView", "Lcom/linecorp/line/ad/timeline/view/AdPostReactionView;", "onAttachedToWindow", "", "onDetachedFromWindow", "onLikeSelectDialogEvent", "event", "Ljp/naver/myhome/android/like/LikeSelectDialog$LikeSelectDialogEvent;", "Ljp/naver/myhome/android/like/LikeSelectDialog;", "onLikeTaskEvent", "Ljp/naver/myhome/android/like/LikeTaskEvent;", "setOnPostListener", "subscribeSubject", "subject", "Lio/reactivex/subjects/Subject;", "Lcom/linecorp/line/ad/timeline/video/Action;", "adObject", "Lcom/linecorp/line/ad/core/datamanager/model/response/session/AdObject;", "subscribeVideoActionSubject", "update", "updateLike", "LadPostListener", "OnPostLadListener", "app_productionRelease"}, k = 1, mv = {1, 1, 13})
public final class LadPostContainerView extends RelativeLayout {
    private final lil a = new lil();
    private bo b;
    private wwx c;
    private ebd d;
    private AdPostReactionView e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/linecorp/line/ad/timeline/video/LadToTimelineVideoAction;", "test"}, k = 3, mv = {1, 1, 13})
    final class a<T> implements pap<ebs> {
        final /* synthetic */ dsw a;

        a(dsw dsw) {
            this.a = dsw;
        }

        public final /* synthetic */ boolean test(Object obj) {
            return acry.a(((ebs) obj).a(), this.a.p().c());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
    final class b<T> implements paj<ozn> {
        final /* synthetic */ LadPostContainerView a;

        b(LadPostContainerView ladPostContainerView) {
            this.a = ladPostContainerView;
        }

        public final /* synthetic */ void accept(Object obj) {
            this.a.a.a((ozn) obj);
        }
    }

    public LadPostContainerView(Context context) {
        super(context);
    }

    public final void a(bo boVar) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        this.b = boVar;
        qaz p = qas.p();
        this.a.a();
        wdx wdx = boVar.G;
        if (wdx != null) {
            dsw dsw = wdx.a;
            if (dsw != null) {
                oyn c = p.a(ebs.class).a((pap) new a(dsw)).c((paj) new b(this));
                String c2 = dsw.p().c();
                wwx wwx = this.c;
                if (wwx == null) {
                    acry.a(CastExtraArgs.LISTENER);
                }
                this.a.a(c.d((paj) new f(boVar, c2, wwx, p)));
            }
        }
        j jVar = i.a;
        dxm f = i.h;
        if (f != null) {
            dxq d = f.d();
            if (d != null) {
                Context context = getContext();
                Context context2 = getContext();
                wwx wwx2 = this.c;
                if (wwx2 == null) {
                    acry.a(CastExtraArgs.LISTENER);
                }
                this.d = new ebd(context, d, new d(context2, wwx2, boVar), p);
            }
        }
        wdx wdx2 = boVar.G;
        if (wdx2 != null) {
            dsw dsw2 = wdx2.a;
            if (dsw2 != null) {
                ebd ebd = this.d;
                if (ebd == null) {
                    acry.a("adPostViewManager");
                }
                ebf a = ebd.a(dsw2, whe.a());
                this.e = a.a();
                addView(a.b());
                tlu tlu = tlt.b;
                View view = this;
                tlu.a().a(view, tls.MYHOME_POST_HEADER);
                tlu = tlt.b;
                tlu.a().a(view, tls.MYHOME_POST_BOTTOM);
            }
        }
    }

    public final void setOnPostListener(wwx wwx) {
        this.c = wwx;
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        vzc.d().b(this);
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        vzc.d().c(this);
        this.a.a();
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onLikeSelectDialogEvent(wnp wnp) {
        if (wsk.a((ag) this.b) && wsk.a((ag) wnp.a)) {
            CharSequence charSequence = wnp.a.d;
            bo boVar = this.b;
            if (TextUtils.equals(charSequence, boVar != null ? boVar.d : null)) {
                AdPostReactionView adPostReactionView;
                View b;
                AdPostReactionView adPostReactionView2;
                if (wnp.b) {
                    adPostReactionView = this.e;
                    b = adPostReactionView != null ? adPostReactionView.b() : null;
                    adPostReactionView2 = this.e;
                    wnc.a(b, adPostReactionView2 != null ? adPostReactionView2.a() : null, null, null, new View[0]);
                    b(this.b);
                    return;
                }
                adPostReactionView = this.e;
                b = adPostReactionView != null ? adPostReactionView.b() : null;
                adPostReactionView2 = this.e;
                wnc.b(b, adPostReactionView2 != null ? adPostReactionView2.a() : null, null, null, new View[0]);
                adPostReactionView = this.e;
                if (adPostReactionView != null) {
                    TextView c = adPostReactionView.c();
                    if (c != null) {
                        c.setVisibility(8);
                    }
                }
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public final void onLikeTaskEvent(wns wns) {
        if (wsk.a((ag) this.b) && wsk.a((ag) wns.a())) {
            bo a = wns.a();
            if (a != null) {
                CharSequence charSequence = a.d;
                bo boVar = this.b;
                if (TextUtils.equals(charSequence, boVar != null ? boVar.d : null)) {
                    b(a);
                    AdPostReactionView adPostReactionView = this.e;
                    if (adPostReactionView != null) {
                        ImageView a2 = adPostReactionView.a();
                        if (a2 != null) {
                            a2.startAnimation(wsa.a());
                        }
                    }
                    adPostReactionView = this.e;
                    if (adPostReactionView != null) {
                        adPostReactionView.setLikeAnimationStarted(true);
                    }
                }
            }
        }
    }

    private final void b(bo boVar) {
        if (boVar != null) {
            int d = wsx.d(boVar);
            AdPostReactionView adPostReactionView = this.e;
            if (adPostReactionView != null) {
                adPostReactionView.a(boVar.C, d);
            }
        }
    }
}
