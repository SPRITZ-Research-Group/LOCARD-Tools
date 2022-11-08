package jp.naver.myhome.android.view.post.carousel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
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
import defpackage.wts;
import defpackage.wwx;
import jp.naver.line.android.R;
import jp.naver.myhome.android.annotation.ViewId;
import jp.naver.myhome.android.model.ag;
import jp.naver.myhome.android.model2.bo;

public class PostCarouselContentReactionView extends RelativeLayout implements OnClickListener, OnLongClickListener {
    private bo a;
    private bo b;
    private int c;
    @ViewId(a = 2131362524)
    private ImageView d;
    @ViewId(a = 2131362523)
    private ImageView e;
    @ViewId(a = 2131362525)
    private ImageView f;
    @ViewId(a = 2131362522)
    private TextView g;
    @ViewId(a = 2131362527)
    private TextView h;
    private RecyclerView i;
    private wwx j;
    private boolean k;

    public PostCarouselContentReactionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PostCarouselContentReactionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        OnLongClickListener onLongClickListener = null;
        this.a = null;
        this.b = null;
        this.c = 0;
        this.k = false;
        inflate(context, R.layout.post_carousel_content_item_reaction, this);
        wts.a((Object) this, (View) this);
        setWillNotCacheDrawing(true);
        this.f.setOnClickListener(this);
        ImageView imageView = this.d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.access_timeline_like_good));
        stringBuilder.append("-");
        stringBuilder.append(getResources().getString(R.string.access_open_menu));
        imageView.setContentDescription(stringBuilder.toString());
        this.d.setOnClickListener(this);
        imageView = this.d;
        if (whe.a()) {
            onLongClickListener = this;
        }
        imageView.setOnLongClickListener(onLongClickListener);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        wne.a().c();
    }

    public void setPostListener(wwx wwx) {
        this.j = wwx;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.i = recyclerView;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        vzc.d().b(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        vzc.d().c(this);
    }

    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeSelectDialogEvent(wnp wnp) {
        if (wsk.a(this.b) && wsk.a(wnp.a) && this.b == wnp.a) {
            View view = this.b.r.d ? this.f : null;
            if (wnp.b) {
                wnc.a(this.e, this.d, null, view, new View[0]);
                if (this.g.getVisibility() == 4) {
                    this.g.setVisibility(0);
                }
                if (this.h.getVisibility() == 4) {
                    this.h.setVisibility(0);
                    return;
                }
            }
            wnc.b(this.e, this.d, null, view, new View[0]);
            this.g.setVisibility(4);
            if (this.h.getVisibility() == 0) {
                this.h.setVisibility(4);
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeTaskEvent(wns wns) {
        if (wsk.a(wns.a()) && TextUtils.equals(wns.a().d, this.b.d)) {
            this.b = wns.a();
            this.d.setSelected(this.b.C);
            this.g.setText(this.b.w.c > 0 ? wsl.a((long) this.b.w.c, (int) R.plurals.timeline_reaction_likes) : "");
            this.d.startAnimation(wsa.a());
        }
    }

    public final void a(bo boVar, bo boVar2, int i) {
        if (wsk.a((ag) boVar) && wsk.a((ag) boVar2)) {
            this.a = boVar;
            this.b = boVar2;
            this.c = i;
            if (boVar2.r.a) {
                this.d.setSelected(boVar2.C);
                a(this.d);
                this.g.setVisibility(0);
                this.g.setText(boVar2.w.c > 0 ? wsl.a((long) boVar2.w.c, (int) R.plurals.timeline_reaction_likes) : "");
                this.e.setVisibility(4);
            } else {
                this.d.setVisibility(8);
                this.g.setVisibility(8);
                this.e.setVisibility(8);
            }
            if (boVar2.r.d) {
                this.f.setVisibility(0);
                a(this.f);
                this.h.setVisibility(0);
                long g = wsx.g(boVar2);
                this.h.setText(g > 0 ? wsl.a(g, (int) R.plurals.timeline_reaction_shares) : "");
                return;
            }
            this.f.setVisibility(8);
            this.h.setVisibility(8);
        }
    }

    private static void a(View view) {
        view.setVisibility(0);
        view.setAlpha(1.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    private boolean a(boolean z) {
        this.k = z;
        if (((LinearLayoutManager) this.i.getLayoutManager()).p() != this.c) {
            return false;
        }
        if (this.k) {
            this.j.f(this, this.a, this.c);
        } else {
            this.j.e(this, this.a, this.c);
        }
        return true;
    }

    public void onClick(View view) {
        if (view == this.d) {
            a(false);
        } else if (view == this.f) {
            this.j.g(view, this.a, this.c);
        } else {
            if (view == this.h || view == this.g) {
                this.j.h(view, this.a, this.c);
            }
        }
    }

    public boolean onLongClick(View view) {
        return view == this.d && a(true);
    }
}
