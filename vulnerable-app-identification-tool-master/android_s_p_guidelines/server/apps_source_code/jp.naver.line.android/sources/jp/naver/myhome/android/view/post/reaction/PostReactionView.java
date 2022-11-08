package jp.naver.myhome.android.view.post.reaction;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.mnt;
import defpackage.qcj;
import defpackage.tls;
import defpackage.tlt;
import defpackage.vzc;
import defpackage.whe;
import defpackage.wnc;
import defpackage.wne;
import defpackage.wnp;
import defpackage.wns;
import defpackage.wsa;
import defpackage.wsk;
import defpackage.wsl;
import defpackage.wsx;
import defpackage.wwz;
import jp.naver.line.android.R;
import jp.naver.myhome.android.annotation.PostItemViewAttr;
import jp.naver.myhome.android.model.c;
import jp.naver.myhome.android.model2.bo;
import jp.naver.myhome.android.model2.bx;

@PostItemViewAttr(b = {0.0f, 0.0f, 0.0f, 0.0f})
public class PostReactionView extends RelativeLayout implements OnClickListener, OnLongClickListener {
    private bo a;
    private wwz b;
    private b c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private View i;
    private View j;
    private View[] k;
    private View l;
    private View m;
    private boolean n = false;

    public PostReactionView(Context context, b bVar) {
        super(context);
        this.c = bVar;
        inflate(context, R.layout.post_reaction, this);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setWillNotCacheDrawing(true);
        this.i = findViewById(R.id.post_reaction_area);
        this.i.setOnClickListener(this);
        this.d = (ImageView) findViewById(R.id.post_reaction_like_icon);
        this.e = (ImageView) findViewById(R.id.post_reaction_comment_icon);
        this.f = (ImageView) findViewById(R.id.post_reaction_share_icon);
        this.g = (ImageView) findViewById(R.id.post_reaction_close_icon);
        this.h = (TextView) findViewById(R.id.post_like_text);
        this.h.setOnClickListener(this);
        this.j = findViewById(R.id.top_likes);
        this.j.setOnClickListener(this);
        this.k = new View[3];
        this.k[0] = findViewById(R.id.like0);
        this.k[1] = findViewById(R.id.like1);
        this.k[2] = findViewById(R.id.like2);
        this.l = findViewById(R.id.post_reaction_top_divider);
        this.m = findViewById(R.id.post_reaction_bottom_padding);
        ImageView imageView = this.d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.access_timeline_like_good));
        stringBuilder.append("-");
        stringBuilder.append(getResources().getString(R.string.access_open_menu));
        imageView.setContentDescription(stringBuilder.toString());
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        tlt.h().a((View) this, tls.MYHOME_POST_BOTTOM);
        setBackgroundColor(-1);
        wne.a().c();
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
            bx bxVar = this.a.r;
            View view = null;
            View view2 = bxVar.a ? this.d : null;
            View view3 = bxVar.b ? this.e : null;
            if (bxVar.d) {
                view = this.f;
            }
            if (wnp.b) {
                wnc.a(this.g, view2, view3, view, new View[0]);
                a();
                return;
            }
            wnc.b(this.g, view2, view3, view, new View[0]);
            mnt.a(this.j, false);
            this.h.setVisibility(8);
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeTaskEvent(wns wns) {
        if (wsk.a(this.a) && wsk.a(wns.a())) {
            bo a = wns.a();
            if (a != null && TextUtils.equals(a.d, this.a.d)) {
                this.a = a;
                this.d.setSelected(a.C);
                a();
                this.d.startAnimation(wsa.a());
                this.n = true;
            }
        }
    }

    public final void a(bo boVar, wwz wwz) {
        this.b = wwz;
        setTag(R.id.key_data, boVar);
        if (this.n && this.a != boVar) {
            this.n = false;
            this.d.clearAnimation();
        }
        this.a = boVar;
        if (wwz.r) {
            this.l.setVisibility(0);
            this.m.setVisibility(0);
        } else {
            this.l.setVisibility(8);
            this.m.setVisibility(8);
        }
        bx bxVar = boVar.r;
        if (bxVar.a) {
            this.d.setSelected(this.a.C);
            this.d.setOnLongClickListener(whe.a() ? this : null);
        }
        a(this.g, false);
        a(this.d, bxVar.a);
        a(this.e, bxVar.b);
        a(this.f, bxVar.d);
        a();
    }

    private void a() {
        if (wsx.c(this.a) && this.b.A) {
            boolean b = qcj.b(this.a.z);
            mnt.a(this.j, b);
            if (b) {
                int i = 0;
                while (i < 3) {
                    boolean z = this.a.z.size() > i;
                    mnt.a(this.k[i], z);
                    if (z) {
                        this.k[i].setBackgroundResource(((c) this.a.z.get(i)).profileLikeDrawableId);
                    }
                    i++;
                }
            }
            this.h.setText(wsl.a((long) this.a.w.c, (int) R.plurals.timeline_reaction_likes));
            this.h.setVisibility(0);
            return;
        }
        mnt.a(this.j, false);
        this.h.setVisibility(8);
    }

    private static void a(View view, boolean z) {
        if (z) {
            view.setAlpha(1.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            view.setVisibility(0);
            return;
        }
        view.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_like_text:
            case R.id.top_likes:
                this.c.k(view, this.a);
                return;
            case R.id.post_reaction_comment_icon:
                this.c.j(view, this.a);
                return;
            case R.id.post_reaction_like_icon:
                this.c.h(view, this.a);
                return;
            case R.id.post_reaction_share_icon:
                this.c.g(view, this.a);
                return;
            default:
                if (this.a.k() || this.a.l()) {
                    this.c.d(view, this.a);
                    return;
                } else {
                    this.c.a_(view, this.a);
                    return;
                }
        }
    }

    public boolean onLongClick(View view) {
        if (view.getId() != R.id.post_reaction_like_icon) {
            return this.c.b(view, this.a);
        }
        this.c.i(view, this.a);
        return true;
    }
}
