package jp.naver.myhome.android.activity.mediaviewer.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.linecorp.line.profile.user.post.mediaviewer.UserProfileMediaViewerActivity;
import com.linecorp.line.timeline.hashtag.grid.HashTagGridListActivity;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import com.linecorp.square.chat.SquareChatUtils;
import defpackage.fjx;
import defpackage.rvi;
import defpackage.rwe;
import defpackage.ugn;
import defpackage.uoa;
import defpackage.vyw;
import defpackage.vzc;
import defpackage.whe;
import defpackage.wmw;
import defpackage.wnc;
import defpackage.wnp;
import defpackage.wns;
import defpackage.wnu;
import defpackage.wro;
import defpackage.wsa;
import defpackage.wsb;
import defpackage.wsk;
import defpackage.wsl;
import defpackage.wsx;
import defpackage.wta;
import jp.naver.line.android.R;
import jp.naver.line.android.bo.bt;
import jp.naver.line.android.bo.z;
import jp.naver.line.android.common.view.media.j;
import jp.naver.line.android.customview.thumbnail.ThumbImageView;
import jp.naver.line.android.customview.thumbnail.f;
import jp.naver.line.android.db.main.model.ContactDto;
import jp.naver.line.android.util.cn;
import jp.naver.myhome.android.activity.comment.CommentEndActivity;
import jp.naver.myhome.android.activity.mediaviewer.MediaViewerActivity;
import jp.naver.myhome.android.activity.mediaviewer.d;
import jp.naver.myhome.android.activity.mediaviewer.g;
import jp.naver.myhome.android.model.User;
import jp.naver.myhome.android.model.ag;
import jp.naver.myhome.android.model.x;
import jp.naver.myhome.android.model2.bo;
import jp.naver.myhome.android.model2.bx;
import jp.naver.myhome.android.view.bb;
import jp.naver.myhome.android.view.post.PostTextView;
import jp.naver.myhome.android.view.post.au;

public class a implements OnClickListener, OnLongClickListener, AnimationListener {
    private AlphaAnimation A;
    private AlphaAnimation B;
    private boolean C = false;
    private final e D = new -$$Lambda$a$6notZNFIXrAUASr21_PXx_5StIM();
    private RelativeLayout E;
    private ViewPropertyAnimator F;
    private final wmw G;
    private jp.naver.line.android.activity.chathistory.message.viewall.a H;
    private final wsb I;
    private b J;
    public View a;
    private final Activity b;
    private final g c;
    private final au d = new au(this) {
        final /* synthetic */ a a;

        public final boolean a(View view, String str, String str2) {
            return false;
        }

        public final boolean a(View view, bo boVar) {
            return false;
        }

        public final boolean a(View view, bo boVar, User user) {
            return false;
        }

        public final boolean a(bo boVar) {
            return false;
        }

        public final boolean b(View view, bo boVar) {
            return false;
        }

        public final boolean b(bo boVar, User user) {
            return false;
        }

        public final boolean c(View view, bo boVar) {
            return false;
        }

        public final boolean n(View view, bo boVar) {
            return false;
        }

        {
            this.a = r1;
        }

        public final void a_(View view, bo boVar) {
            this.a.i();
        }

        public final boolean a(jp.naver.myhome.android.model2.bo r1, android.content.Intent r2) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.mediaviewer.view.a.1.a(jp.naver.myhome.android.model2.bo, android.content.Intent):boolean. bs: []
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
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r0 = this;
            r1 = r0.a;	 Catch:{ Exception -> 0x000b }
            r1 = r1.b;	 Catch:{ Exception -> 0x000b }
            r1.startActivity(r2);	 Catch:{ Exception -> 0x000b }
            r1 = 1;
            return r1;
        L_0x000b:
            r1 = 0;
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.mediaviewer.view.a.1.a(jp.naver.myhome.android.model2.bo, android.content.Intent):boolean");
        }

        public final boolean a(String str, View view, bo boVar) {
            view.getContext().startActivity(HashTagGridListActivity.a(view.getContext(), str, boVar.c, boVar.t));
            return true;
        }

        public final boolean a(bo boVar, User user) {
            bo f = this.a.c.f();
            if (this.a.H.a(this.a.b, user)) {
                return true;
            }
            x h = this.a.c.h();
            if (!wsk.a((ag) user) || !wsk.a(user.b)) {
                return false;
            }
            if (wsk.a(boVar.s) || f == null || !user.b.equals(f.e.b) || h != x.MYHOME) {
                fjx.a(this.a.b, this.a.c.h(), user, f);
            } else {
                this.a.b.finish();
            }
            return true;
        }

        public final void a_(View view, bo boVar, String str, String str2) {
            rwe.a(view.getContext(), boVar, str, str2);
        }
    };
    private TextView e;
    private View f;
    private View g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private View k;
    private TextView l;
    private TextView m;
    private TextView n;
    private bo o;
    private View p;
    private PostTextView q;
    private View r;
    private ThumbImageView s;
    private TextView t;
    private TextView u;
    private PostBodyScrollView v;
    private TransitionDrawable w;
    private final f x = new f(this) {
        final /* synthetic */ a a;
        private final Object b = new Object();
        private boolean c = false;

        public final void b() {
        }

        {
            this.a = r1;
        }

        public final void a() {
            synchronized (this.b) {
                if (!this.c) {
                    this.c = true;
                    this.a.w.resetTransition();
                    this.a.w.startTransition(500);
                    this.a.F = this.a.E.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration(500).setListener(new AnimatorListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        public final void onAnimationRepeat(Animator animator) {
                        }

                        public final void onAnimationStart(Animator animator) {
                        }

                        {
                            this.a = r1;
                        }

                        public final void onAnimationEnd(Animator animator) {
                            this.a.a.F = null;
                        }

                        public final void onAnimationCancel(Animator animator) {
                            this.a.a.F = null;
                        }
                    });
                }
            }
        }

        public final void c() {
            synchronized (this.b) {
                if (this.c) {
                    this.c = false;
                    this.a.w.reverseTransition(500);
                    if (this.a.F != null) {
                        this.a.F.cancel();
                    }
                    this.a.E.setAlpha(1.0f);
                }
            }
        }
    };
    private boolean y;
    private boolean z;

    /* renamed from: jp.naver.myhome.android.activity.mediaviewer.view.a$3 */
    final /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] b = new int[wnu.values().length];

        static {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.myhome.android.activity.mediaviewer.view.a.3.<clinit>():void. bs: []
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
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r0 = defpackage.wnu.values();
            r0 = r0.length;
            r0 = new int[r0];
            b = r0;
            r0 = 1;
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = defpackage.wnu.CREATE_POST_LIKE;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = b;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = defpackage.wnu.CANCEL_POST_LIKE;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = jp.naver.myhome.android.activity.mediaviewer.view.d.values();
            r2 = r2.length;
            r2 = new int[r2];
            a = r2;
            r2 = a;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r3 = jp.naver.myhome.android.activity.mediaviewer.view.d.GONE;	 Catch:{ NoSuchFieldError -> 0x0032 }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x0032 }
            r2[r3] = r0;	 Catch:{ NoSuchFieldError -> 0x0032 }
        L_0x0032:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x003c }
            r2 = jp.naver.myhome.android.activity.mediaviewer.view.d.VISIBLE_NOT_ENABLE;	 Catch:{ NoSuchFieldError -> 0x003c }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x003c }
            r0[r2] = r1;	 Catch:{ NoSuchFieldError -> 0x003c }
        L_0x003c:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r1 = jp.naver.myhome.android.activity.mediaviewer.view.d.VISIBLE_ENABLE;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0047 }
            r2 = 3;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0047 }
        L_0x0047:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: jp.naver.myhome.android.activity.mediaviewer.view.a.3.<clinit>():void");
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    private /* synthetic */ void a(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            j();
        }
    }

    public a(Activity activity, g gVar) {
        this.b = activity;
        this.G = new wmw(activity);
        this.G.a(new c());
        this.H = new jp.naver.line.android.activity.chathistory.message.viewall.a();
        this.I = new wsb();
        this.c = gVar;
        this.a = this.b.findViewById(R.id.photoviewer_overlay);
        this.e = (TextView) this.b.findViewById(R.id.photo_viewer_title_text_for_glow);
        this.f = this.b.findViewById(R.id.photo_viewer_title_save_button);
        this.f.setOnClickListener(this);
        c();
        this.g = this.b.findViewById(R.id.overlay_comment_like);
        this.i = (ImageView) this.b.findViewById(R.id.photo_viewer_close_like);
        this.h = (ImageView) this.b.findViewById(R.id.photo_viewer_do_like);
        this.h.setOnClickListener(this);
        this.j = (ImageView) this.b.findViewById(R.id.photo_viewer_write_comment);
        this.j.setOnClickListener(this);
        this.k = this.b.findViewById(R.id.post_reaction_summary_area);
        this.k.setOnClickListener(this);
        this.l = (TextView) this.b.findViewById(R.id.post_like_text);
        this.m = (TextView) this.b.findViewById(R.id.post_comment_text);
        this.n = (TextView) this.b.findViewById(R.id.post_share_text);
        PhotoViewerBodyContainerView photoViewerBodyContainerView = (PhotoViewerBodyContainerView) this.b.findViewById(R.id.photo_viewer_body_container);
        this.r = this.b.findViewById(R.id.photo_viewer_empty_view);
        this.q = (PostTextView) this.b.findViewById(R.id.photo_viewer_body);
        this.q.a();
        this.q.setOnPostTextViewListener(this.d);
        this.s = (ThumbImageView) this.b.findViewById(R.id.photo_viewer_user_profile_view);
        this.s.setOnClickListener(this);
        this.t = (TextView) this.b.findViewById(R.id.photo_viewer_user_name);
        this.t.setOnClickListener(this);
        this.u = (TextView) this.b.findViewById(R.id.photo_viewer_post_time);
        photoViewerBodyContainerView.setOnPhotoViewerLayoutChangeListener(this.D);
        View findViewById = this.b.findViewById(R.id.photo_viewer_bg_dim_view);
        this.w = new TransitionDrawable(new ColorDrawable[]{new ColorDrawable(0), new ColorDrawable(Integer.MIN_VALUE)});
        findViewById.setBackground(this.w);
        this.p = this.b.findViewById(R.id.photo_viewer_body_area);
        this.p.setVisibility(4);
        findViewById = this.b.findViewById(R.id.photo_viewer_body_click_area);
        findViewById.setOnClickListener(new -$$Lambda$a$hnq6bb8Dw2BzlQwcbviSqBFammg());
        this.v = (PostBodyScrollView) this.b.findViewById(R.id.photo_viewer_scrollview);
        this.v.setOnScrollChangeListener(this.x);
        this.v.setChildViews(findViewById);
        this.E = (RelativeLayout) this.b.findViewById(R.id.photo_viewer_body_bg);
        this.y = true;
        this.A = new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 0.75f);
        this.A.setDuration(100);
        this.A.setAnimationListener(this);
        this.B = new AlphaAnimation(0.75f, BitmapDescriptorFactory.HUE_RED);
        this.B.setDuration(500);
        this.B.setAnimationListener(this);
    }

    private /* synthetic */ void a(View view) {
        i();
    }

    private void i() {
        if (this.b instanceof j) {
            ((j) this.b).onSingleTapUp();
        }
    }

    public final void a(bo boVar) {
        this.o = boVar;
        this.C = true;
        b();
        b(boVar);
        this.a.setVisibility(0);
    }

    public final void a(int i, int i2) {
        TextView textView = this.e;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i + 1);
        stringBuilder.append("/");
        stringBuilder.append(i2);
        textView.setText(stringBuilder.toString());
    }

    public final void a(String str) {
        this.e.setText(str);
    }

    public final void b(bo boVar) {
        Object obj;
        int i;
        long g;
        bo boVar2 = boVar;
        this.o = boVar2;
        bx bxVar = boVar2.r;
        View view = this.g;
        int i2 = (bxVar.b || bxVar.a) ? 0 : 8;
        view.setVisibility(i2);
        if (boVar2.r.a) {
            r0.h.setVisibility(0);
            r0.h.setImageResource(boVar2.C ? R.drawable.timeline_viewer_ic_like_on : R.drawable.timeline_viewer_ic_like_off);
            r0.h.setOnLongClickListener(whe.a() ? r0 : null);
            i2 = boVar2.w.c;
            if (i2 > 0) {
                r0.l.setText(wsl.a((long) i2, (int) R.plurals.timeline_reaction_likes));
                r0.l.setVisibility(0);
                obj = 1;
                if (boVar2.r.b) {
                    r0.j.setVisibility(8);
                    r0.m.setVisibility(8);
                } else {
                    r0.j.setVisibility(0);
                    i = boVar2.x.c;
                    if (i <= 0) {
                        r0.m.setText(wsl.a((long) i, (int) R.plurals.timeline_reaction_comments));
                        r0.m.setVisibility(0);
                        obj = 1;
                    } else {
                        r0.m.setVisibility(8);
                    }
                }
                if (boVar2.r.d) {
                    r0.n.setVisibility(8);
                } else {
                    g = wsx.g(boVar);
                    if (g <= 0) {
                        r0.n.setText(wsl.a(g, (int) R.plurals.timeline_reaction_shares));
                        r0.n.setVisibility(0);
                        obj = 1;
                    } else {
                        r0.n.setVisibility(8);
                    }
                }
                r0.j.setVisibility(boVar2.r.b ? 0 : 8);
                r0.k.setVisibility(obj == null ? 0 : 8);
                r0.t.setText(boVar2.e.a());
                r0.u.setText(wsa.b(boVar2.h));
                if (SquareChatUtils.b(boVar2.e.b)) {
                    r0.s.setSquareGroupMemberPreviewImageByUrl(boVar2.e.e, 0);
                } else if (!c(boVar)) {
                    r0.s.setProfileImage(boVar2.e.b, boVar2.e.e, f.TALK_FROM);
                }
                if (TextUtils.isEmpty(boVar.c())) {
                    r0.v.setEnabled(true);
                    r0.q.a(boVar, boVar.c(), boVar2.n.f, bb.k, boVar2.n.g, bb.l, bb.j, true, false, boVar2.n.h, bb.i, null);
                    r0.q.setVisibility(0);
                } else {
                    r0.v.setEnabled(false);
                    r0.q.setVisibility(8);
                }
                j();
            }
            r0.l.setVisibility(8);
        } else {
            r0.h.setVisibility(8);
            r0.h.clearAnimation();
            r0.l.setVisibility(8);
        }
        obj = null;
        if (boVar2.r.b) {
            r0.j.setVisibility(8);
            r0.m.setVisibility(8);
        } else {
            r0.j.setVisibility(0);
            i = boVar2.x.c;
            if (i <= 0) {
                r0.m.setVisibility(8);
            } else {
                r0.m.setText(wsl.a((long) i, (int) R.plurals.timeline_reaction_comments));
                r0.m.setVisibility(0);
                obj = 1;
            }
        }
        if (boVar2.r.d) {
            r0.n.setVisibility(8);
        } else {
            g = wsx.g(boVar);
            if (g <= 0) {
                r0.n.setVisibility(8);
            } else {
                r0.n.setText(wsl.a(g, (int) R.plurals.timeline_reaction_shares));
                r0.n.setVisibility(0);
                obj = 1;
            }
        }
        if (boVar2.r.b) {
        }
        r0.j.setVisibility(boVar2.r.b ? 0 : 8);
        if (obj == null) {
        }
        r0.k.setVisibility(obj == null ? 0 : 8);
        r0.t.setText(boVar2.e.a());
        r0.u.setText(wsa.b(boVar2.h));
        if (SquareChatUtils.b(boVar2.e.b)) {
            r0.s.setSquareGroupMemberPreviewImageByUrl(boVar2.e.e, 0);
        } else if (c(boVar)) {
            r0.s.setProfileImage(boVar2.e.b, boVar2.e.e, f.TALK_FROM);
        }
        if (TextUtils.isEmpty(boVar.c())) {
            r0.v.setEnabled(true);
            r0.q.a(boVar, boVar.c(), boVar2.n.f, bb.k, boVar2.n.g, bb.l, bb.j, true, false, boVar2.n.h, bb.i, null);
            r0.q.setVisibility(0);
        } else {
            r0.v.setEnabled(false);
            r0.q.setVisibility(8);
        }
        j();
    }

    private boolean c(bo boVar) {
        String str = boVar.e.b;
        if (!bt.e(str) || bt.b(bt.d(str))) {
            return false;
        }
        Object obj = null;
        if (TextUtils.equals(uoa.g().a().m(), str)) {
            obj = uoa.g().a().j();
        } else {
            ContactDto b = z.a().b(str);
            if (b != null) {
                obj = b.d();
            }
        }
        if (TextUtils.isEmpty(obj)) {
            return false;
        }
        this.s.setVideoProfile(str, obj, f.TALK_FROM, ugn.b(boVar.d));
        return true;
    }

    public final void a() {
        this.u.setVisibility(8);
    }

    public final void a(b bVar) {
        this.J = bVar;
    }

    private void b(boolean z) {
        this.h.setEnabled(z);
        this.j.setEnabled(z);
        this.k.setEnabled(z);
    }

    private void j() {
        if (this.C) {
            this.r.post(new -$$Lambda$a$ovSpS7lW-ADaIkvH-Sz2E_iUkfQ());
        }
    }

    private /* synthetic */ void m() {
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen.photoviewer_overlay_body_min_height);
        int height = this.v.getHeight();
        int height2 = this.p.getHeight();
        if (height2 > 0) {
            dimensionPixelSize = Math.min(dimensionPixelSize, height2);
        }
        LayoutParams layoutParams = (LayoutParams) this.r.getLayoutParams();
        layoutParams.height = height - dimensionPixelSize;
        layoutParams.weight = BitmapDescriptorFactory.HUE_RED;
        this.r.setLayoutParams(layoutParams);
        this.p.setVisibility(0);
    }

    public final void b() {
        d j = this.c.j();
        this.f.setVisibility(0);
        switch (j) {
            case GONE:
                this.f.setVisibility(8);
                return;
            case VISIBLE_NOT_ENABLE:
                this.f.setEnabled(false);
                return;
            case VISIBLE_ENABLE:
                this.f.setEnabled(true);
                break;
        }
    }

    public final void c() {
        this.f.setEnabled(false);
    }

    public void onClick(View view) {
        if (view == this.s || view == this.t) {
            if (((this.b instanceof MediaViewerActivity) || (this.b instanceof UserProfileMediaViewerActivity)) && wsk.a(this.o)) {
                String b = this.o.b();
                if (SquareChatUtils.b(b)) {
                    this.H.a(this.b, b, false, null);
                    return;
                }
                if (!TextUtils.isEmpty(b)) {
                    fjx.a(this.b, b, x.PHOTOVIEWER, this.o);
                }
            } else if (this.b instanceof j) {
                ((j) this.b).onSingleTapUp();
            }
        } else if (view == this.f) {
            if (cn.a(this.b, vyw.d, 1001)) {
                k();
            }
        } else if (view == this.h) {
            this.G.a(this.c.f(), view, -1, null, x.PHOTOVIEWER, false);
        } else if (view == this.j) {
            rwe.a(view.getContext(), this.c.f(), rvi.COMMENT.name, null);
            wta.a(this.b, this.c.f(), new -$$Lambda$a$fRxA2td8NpUNnT5h441Fi3YeOUE());
        } else if (view == this.k) {
            c(false);
        }
    }

    private /* synthetic */ void l() {
        c(true);
    }

    public boolean onLongClick(View view) {
        if (view != this.h) {
            return false;
        }
        this.G.a(this.c.f(), view, -1, null, x.PHOTOVIEWER, true);
        return true;
    }

    public final void a(int i, String[] strArr, int[] iArr) {
        if (i == 1001 && cn.a(strArr, iArr)) {
            k();
        }
    }

    private void k() {
        this.c.l();
    }

    public final void d() {
        if (this.c.i() == d.NORMAL) {
            boolean z = this.a.getVisibility() != 0;
            if (a(z, false)) {
                this.y = z;
            }
        }
    }

    public final void a(boolean z) {
        if (this.c.i() == d.NORMAL) {
            if (z) {
                a(false, true);
            } else {
                a(this.y, true);
            }
        }
    }

    private boolean a(boolean z, boolean z2) {
        if (z) {
            if (this.a.getVisibility() == 0) {
                return false;
            }
            if (!z2 && this.z) {
                return false;
            }
            this.a.clearAnimation();
            this.a.startAnimation(this.A);
        } else if (this.a.getVisibility() == 4) {
            return false;
        } else {
            if (!z2 && this.z) {
                return false;
            }
            this.a.clearAnimation();
            this.a.startAnimation(this.B);
        }
        return true;
    }

    public void onAnimationEnd(Animation animation) {
        int i = 0;
        this.z = false;
        boolean z = true;
        if (this.c.i() == d.NORMAL) {
            b(true);
        }
        if (animation != this.A) {
            z = false;
        }
        View view = this.a;
        if (!z) {
            i = 4;
        }
        view.setVisibility(i);
        if (this.J != null) {
            this.J.a(z);
        }
    }

    public void onAnimationStart(Animation animation) {
        this.z = true;
        b(false);
    }

    private void c(boolean z) {
        rwe.a(this.b, this.o, rvi.COMMENT_LAYER_OPEN.name, null);
        boolean z2 = z;
        this.b.startActivityForResult(CommentEndActivity.a(this.b, this.c.f(), z2, wro.a(this.b, this.c.f()), this.c.h(), true, null), 60303);
    }

    public final void e() {
        if (whe.a()) {
            this.G.b();
        }
        this.G.a();
        this.H.a();
    }

    public final boolean f() {
        if (whe.a() && this.G.b()) {
            return true;
        }
        return this.G.a();
    }

    public final void g() {
        vzc.d().b(this);
    }

    public final void h() {
        if (whe.a()) {
            this.G.b();
        }
        this.G.a();
        this.G.c();
        vzc.d().c(this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeSelectDialogEvent(wnp wnp) {
        if (wsk.a(this.o) && wsk.a(wnp.a) && TextUtils.equals(wnp.a.d, this.o.d)) {
            bx bxVar = this.o.r;
            View view = bxVar.a ? this.h : null;
            View view2 = bxVar.b ? this.j : null;
            if (wnp.b) {
                wnc.a(this.i, view, view2, null, new View[0]);
                return;
            }
            wnc.b(this.i, view, view2, null, new View[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeTaskEvent(wns wns) {
        if (wsk.a(this.o) && wsk.a(wns.a()) && TextUtils.equals(wns.a().d, this.o.d)) {
            this.h.startAnimation(wsa.a());
        }
    }
}
