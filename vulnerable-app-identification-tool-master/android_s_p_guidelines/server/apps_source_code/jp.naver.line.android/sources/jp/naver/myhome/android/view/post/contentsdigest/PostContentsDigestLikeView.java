package jp.naver.myhome.android.view.post.contentsdigest;

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
import androidx.recyclerview.widget.bp;
import androidx.recyclerview.widget.cb;
import com.linecorp.rxeventbus.Subscribe;
import com.linecorp.rxeventbus.SubscriberType;
import defpackage.tmd;
import defpackage.vzc;
import defpackage.whe;
import defpackage.wnc;
import defpackage.wne;
import defpackage.wnp;
import defpackage.wns;
import defpackage.wsa;
import defpackage.wsk;
import defpackage.wsl;
import defpackage.wwx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.naver.line.android.R;
import jp.naver.line.android.bo.z;
import jp.naver.line.android.db.main.model.ContactDto;
import jp.naver.myhome.android.model2.af;
import jp.naver.myhome.android.model2.bh;
import jp.naver.myhome.android.model2.bj;
import jp.naver.myhome.android.model2.bo;
import jp.naver.myhome.android.model2.v;
import jp.naver.myhome.android.model2.w;

public class PostContentsDigestLikeView extends RelativeLayout implements OnClickListener, OnLongClickListener {
    private static Map<String, a> b = new HashMap();
    public wwx a;
    private bo c;
    private w d;
    private ImageView e;
    private ImageView f;
    private TextView g;
    private View h;
    private RecyclerView i;
    private boolean j;
    private final bp k;

    public PostContentsDigestLikeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PostContentsDigestLikeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = null;
        this.j = false;
        this.k = new bp(this) {
            int a = -1;
            final /* synthetic */ PostContentsDigestLikeView b;

            {
                this.b = r1;
            }

            public final void a(RecyclerView recyclerView, int i) {
                cb findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(this.b.d.s());
                if (findViewHolderForAdapterPosition == null) {
                    recyclerView.clearOnScrollListeners();
                } else if (i == 0) {
                    int[] iArr = new int[2];
                    findViewHolderForAdapterPosition.itemView.getLocationOnScreen(iArr);
                    int i2 = 0;
                    if (recyclerView.getAdapter().getItemCount() != this.b.d.s() + 1 ? iArr[0] != 0 : iArr[0] + findViewHolderForAdapterPosition.itemView.getWidth() != tmd.d()) {
                        i2 = 1;
                    }
                    if (i2 != 0) {
                        this.b.a();
                    }
                    if (this.a == -1 || this.a == i) {
                        recyclerView.clearOnScrollListeners();
                        return;
                    }
                    if (this.a == 1 || this.a == 2) {
                        if (i2 != 0) {
                            recyclerView.clearOnScrollListeners();
                            return;
                        }
                        this.a = i;
                    }
                } else {
                    this.a = i;
                }
            }
        };
        inflate(context, R.layout.post_contents_digest_like, this);
        setWillNotCacheDrawing(true);
        this.e = (ImageView) findViewById(R.id.like_icon);
        this.f = (ImageView) findViewById(R.id.like_close_icon);
        this.g = (TextView) findViewById(R.id.like_text);
        ImageView imageView = this.e;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.access_timeline_like_good));
        stringBuilder.append("-");
        stringBuilder.append(getResources().getString(R.string.access_open_menu));
        imageView.setContentDescription(stringBuilder.toString());
        this.e.setOnClickListener(this);
        wne.a().c();
    }

    public void setPostListener(wwx wwx) {
        this.a = wwx;
    }

    public void setAddFriendView(View view) {
        this.h = view;
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
        if (wsk.a(this.c) && wsk.a(wnp.a) && this.c == wnp.a) {
            if (wnp.b) {
                wnc.a(this.f, this.e, null, null, new View[0]);
                if (this.h.getVisibility() == 4) {
                    this.h.setVisibility(b() ? 8 : 0);
                }
                if (this.g.getVisibility() == 4) {
                    this.g.setVisibility(0);
                    return;
                }
            }
            wnc.b(this.f, this.e, null, null, new View[0]);
            if (this.h.getVisibility() == 0) {
                this.h.setVisibility(4);
            }
            if (this.g.getVisibility() == 0) {
                this.g.setVisibility(4);
            }
        }
    }

    @Subscribe(a = SubscriberType.MAIN)
    public void onLikeTaskEvent(wns wns) {
        if (wsk.a(this.c) && wsk.a(wns.a()) && TextUtils.equals(wns.a().d, this.c.d)) {
            bo a = wns.a();
            a(a);
            b.put(a.d, new a(this, a.C, a.w.c));
            this.e.startAnimation(wsa.a());
            if (this.h.getVisibility() == 0 && b()) {
                this.h.setVisibility(8);
            }
        }
    }

    private boolean b() {
        if (!TextUtils.isEmpty(this.d.i())) {
            ContactDto b = z.a().b(this.d.i());
            if (b != null && b.f() && this.h.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public void setContent(w wVar) {
        this.d = wVar;
        OnLongClickListener onLongClickListener = null;
        this.c = null;
        bo boVar = new bo();
        boVar.d = wVar.c();
        boVar.e.b = wVar.g();
        boVar.e.h = true;
        boVar.r.f = true;
        if (b.containsKey(boVar.d)) {
            a aVar = (a) b.get(boVar.d);
            boVar.w.c = aVar.b;
            boVar.C = aVar.a;
        } else {
            boVar.w.c = wVar.n();
            boVar.C = false;
        }
        af vVar = new v();
        List arrayList = new ArrayList();
        arrayList.add(wVar);
        vVar.a(arrayList);
        boVar.a(vVar);
        if (!TextUtils.isEmpty(wVar.i())) {
            boVar.s = new bh();
            boVar.s.a = bj.OFFICIAL;
        }
        a(boVar);
        if (this.g.getVisibility() == 4) {
            this.g.setVisibility(0);
        }
        this.e.setAlpha(1.0f);
        this.e.setScaleX(1.0f);
        this.e.setScaleY(1.0f);
        this.e.setVisibility(0);
        this.f.setVisibility(4);
        ImageView imageView = this.e;
        if (whe.a()) {
            onLongClickListener = this;
        }
        imageView.setOnLongClickListener(onLongClickListener);
    }

    private void a(bo boVar) {
        if (this.c == null) {
            this.c = boVar;
        } else {
            this.c.w.c = boVar.w.c;
            this.c.C = boVar.C;
        }
        this.e.setSelected(this.c.C);
        this.g.setText(this.c.w.c > 0 ? wsl.a((long) this.c.w.c, (int) R.plurals.timeline_reaction_likes) : "");
    }

    public final void a() {
        if (this.j) {
            this.a.i(this, this.c);
        } else {
            this.a.h(this, this.c);
        }
    }

    private boolean a(boolean z) {
        this.j = z;
        if (((LinearLayoutManager) this.i.getLayoutManager()).p() == this.d.s()) {
            a();
        } else {
            cb findViewHolderForAdapterPosition = this.i.findViewHolderForAdapterPosition(this.d.s());
            if (findViewHolderForAdapterPosition == null) {
                return false;
            }
            this.i.smoothScrollBy(findViewHolderForAdapterPosition.itemView.getLeft(), 0);
            this.i.clearOnScrollListeners();
            this.i.addOnScrollListener(this.k);
        }
        return true;
    }

    public void onClick(View view) {
        a(false);
    }

    public boolean onLongClick(View view) {
        return a(true);
    }
}
