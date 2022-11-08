package jp.naver.myhome.android.view.post;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.mnt;
import defpackage.qcj;
import defpackage.tls;
import defpackage.tlt;
import defpackage.tmd;
import defpackage.vzc;
import defpackage.wnc;
import defpackage.wne;
import defpackage.wnp;
import defpackage.wns;
import defpackage.wsa;
import defpackage.wsk;
import defpackage.wsl;
import jp.naver.line.android.R;
import jp.naver.myhome.android.model.c;
import jp.naver.myhome.android.model2.bo;
import jp.naver.myhome.android.model2.bx;

@Deprecated
public class PostCommentLikeStatusView extends RelativeLayout implements OnClickListener, OnLongClickListener {
    private bo a;
    private p b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private View g;
    private View h;
    private TextView i;
    private TextView j;
    private TextView k;
    private PostDividerView l;
    private ViewStub m;
    private ViewStub n;
    private View o;
    private View[] p;
    private View q;
    private View r;
    private boolean s = false;
    private boolean t;
    private boolean u;
    private boolean v;

    public PostCommentLikeStatusView(Context context) {
        super(context);
        inflate(context, R.layout.post_bottom_status, this);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setWillNotCacheDrawing(true);
        this.g = findViewById(R.id.post_reaction_area);
        this.c = (ImageView) findViewById(R.id.post_reaction_like_icon);
        this.d = (ImageView) findViewById(R.id.post_reaction_comment_icon);
        this.e = (ImageView) findViewById(R.id.post_reaction_share_icon);
        this.f = (ImageView) findViewById(R.id.post_reaction_close_icon);
        this.h = findViewById(R.id.post_reaction_summary_area);
        this.l = (PostDividerView) findViewById(R.id.post_reaction_summary_divider);
        this.m = (ViewStub) findViewById(R.id.post_reaction_count_layout);
        this.n = (ViewStub) findViewById(R.id.post_reaction_simple_count_layout);
        ImageView imageView = this.c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.access_timeline_like_good));
        stringBuilder.append("-");
        stringBuilder.append(getResources().getString(R.string.access_open_menu));
        imageView.setContentDescription(stringBuilder.toString());
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        tlt.h().a((View) this, tls.MYHOME_POST_BOTTOM);
        setBackgroundColor(-1);
        wne.a().c();
    }

    private void a() {
        if (this.q != null && this.r != null) {
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            Object obj = this.i.getVisibility() == 0 ? 1 : null;
            if (this.j.getVisibility() == 0) {
                if (obj != null) {
                    this.q.setVisibility(0);
                }
                obj = 1;
            }
            if (this.k.getVisibility() == 0 && obj != null) {
                this.r.setVisibility(0);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        vzc.d().b(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        vzc.d().c(this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeSelectDialogEvent(wnp wnp) {
        if (wsk.a(this.a) && wsk.a(wnp.a) && TextUtils.equals(wnp.a.d, this.a.d)) {
            a(wnp.a);
            bx bxVar = this.a.r;
            View view = null;
            View view2 = bxVar.a ? this.c : null;
            View view3 = bxVar.b ? this.d : null;
            if (bxVar.d) {
                view = this.e;
            }
            if (wnp.b) {
                wnc.a(this.f, view2, view3, view, new View[0]);
                return;
            }
            wnc.b(this.f, view2, view3, view, new View[0]);
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeTaskEvent(wns wns) {
        if (wsk.a(this.a) && wsk.a(wns.a())) {
            bo a = wns.a();
            if (a != null && TextUtils.equals(a.d, this.a.d)) {
                a(a);
                this.c.startAnimation(wsa.a());
                this.s = true;
            }
        }
    }

    public void setOnPostBottomStatusViewListener(p pVar) {
        this.b = pVar;
    }

    public final void a(bo boVar, boolean z, boolean z2, boolean z3) {
        this.t = z;
        this.v = z3;
        this.u = z2;
        if (this.i == null && this.j == null && this.k == null) {
            View inflate;
            if (z3) {
                inflate = this.m.inflate();
                this.i = (TextView) inflate.findViewById(R.id.post_like_text);
                this.q = inflate.findViewById(R.id.like_and_comment_comma);
                this.j = (TextView) inflate.findViewById(R.id.post_comment_text);
                this.r = inflate.findViewById(R.id.comment_and_share_comma);
                this.k = (TextView) inflate.findViewById(R.id.post_share_text);
                this.l.setVisibility(0);
                tlt.h().a(this.q, tls.MYHOME_POST_BOTTOM, (int) R.id.myhome_comma_tint);
                tlt.h().a(this.r, tls.MYHOME_POST_BOTTOM, (int) R.id.myhome_comma_tint);
            } else {
                inflate = this.n.inflate();
                inflate.getLayoutParams().height = tmd.a(getContext(), 42.0f);
                this.i = (TextView) inflate.findViewById(R.id.post_like_text);
                this.j = (TextView) inflate.findViewById(R.id.post_comment_text);
                this.k = (TextView) inflate.findViewById(R.id.post_share_text);
                this.o = inflate.findViewById(R.id.top_likes);
                this.p = new View[3];
                this.p[0] = inflate.findViewById(R.id.like0);
                this.p[1] = inflate.findViewById(R.id.like1);
                this.p[2] = inflate.findViewById(R.id.like2);
                this.l.setVisibility(8);
            }
            tlt.h().a((View) this, tls.MYHOME_POST_BOTTOM);
        }
        a(boVar);
    }

    private void a(bo boVar) {
        int i;
        int a;
        if (this.s && this.a != boVar) {
            this.s = false;
            this.c.clearAnimation();
        }
        Object obj = 1;
        Object obj2 = (this.a == null || !this.a.d.equals(boVar.d) || b()) ? 1 : null;
        this.a = boVar;
        bx bxVar = boVar.r;
        int i2 = 8;
        if (bxVar.a) {
            this.c.setVisibility(0);
            this.c.setSelected(this.a.C);
        } else {
            this.c.setVisibility(8);
        }
        this.d.setVisibility(bxVar.b ? 0 : 8);
        this.e.setVisibility(bxVar.d ? 0 : 8);
        if (obj2 != null) {
            a(this.f, false);
            a(this.c, bxVar.a);
            a(this.d, bxVar.b);
            a(this.e, bxVar.d);
        }
        int i3 = this.a.w.c;
        if (!bxVar.a || i3 <= 0) {
            mnt.a(this.o, false);
            this.i.setVisibility(8);
            obj2 = null;
        } else {
            boolean z = this.t && this.o != null && qcj.b(boVar.z);
            mnt.a(this.o, z);
            if (z) {
                i = 0;
                while (i < 3) {
                    boolean z2 = boVar.z.size() > i;
                    mnt.a(this.p[i], z2);
                    if (z2) {
                        this.p[i].setBackgroundResource(((c) boVar.z.get(i)).profileLikeDrawableId);
                    }
                    i++;
                }
            }
            this.i.setText(wsl.a((long) i3, (int) R.plurals.timeline_reaction_likes));
            this.i.setVisibility(0);
            obj2 = 1;
        }
        i = this.a.x.c;
        if (!bxVar.b || i <= 0) {
            this.j.setVisibility(8);
        } else {
            this.j.setText(wsl.a((long) i, (int) R.plurals.timeline_reaction_comments));
            this.j.setVisibility(0);
            obj2 = 1;
        }
        long j = this.a.B + this.a.A;
        if (!bxVar.d || j <= 0) {
            this.k.setVisibility(8);
        } else {
            this.k.setText(wsl.a(j, (int) R.plurals.timeline_reaction_shares));
            this.k.setVisibility(0);
            obj2 = 1;
        }
        if (obj2 == null || this.v) {
            a = tmd.a(getContext(), 15.0f);
        } else {
            a = 0;
        }
        this.g.setPadding(0, 0, 0, a);
        View view = this.h;
        if (obj2 != null) {
            i2 = 0;
        }
        view.setVisibility(i2);
        if (this.v) {
            if (!(wsk.a(boVar.w) && boVar.w.c > 0 && boVar.r.a)) {
                obj = null;
            }
            this.h.setPadding(0, 0, 0, obj != null ? 0 : tmd.a(getContext(), 17.0f));
        } else {
            View view2 = this.h;
            i3 = (!this.u || this.a.y == null) ? tmd.a(getContext(), 4.0f) : 0;
            view2.setPadding(0, 0, 0, i3);
        }
        a();
    }

    private boolean b() {
        return this.f.getAlpha() == BitmapDescriptorFactory.HUE_RED;
    }

    private static void a(View view, boolean z) {
        if (z) {
            view.setAlpha(1.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return;
        }
        view.setAlpha(BitmapDescriptorFactory.HUE_RED);
        view.setScaleX(BitmapDescriptorFactory.HUE_RED);
        view.setScaleY(BitmapDescriptorFactory.HUE_RED);
    }

    public void onClick(View view) {
        if (this.b != null) {
            switch (view.getId()) {
                case R.id.post_reaction_comment_icon:
                    this.b.j(view, this.a);
                    return;
                case R.id.post_reaction_like_icon:
                    this.b.h(view, this.a);
                    return;
                case R.id.post_reaction_share_icon:
                    this.b.g(view, this.a);
                    return;
                case R.id.post_reaction_summary_area:
                    this.b.k(view, this.a);
                    return;
                default:
                    this.b.a_(view, this.a);
                    return;
            }
        }
    }

    public boolean onLongClick(View view) {
        return this.b.b(view, this.a);
    }
}
