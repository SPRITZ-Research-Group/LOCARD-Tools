package com.facebook.share.widget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.facebook.common.b;
import com.facebook.common.c;
import com.facebook.common.i;
import com.facebook.internal.ad;
import com.facebook.internal.bj;
import com.facebook.n;
import com.facebook.share.internal.LikeBoxCountView;
import com.facebook.share.internal.LikeButton;
import com.facebook.share.internal.e;
import com.facebook.share.internal.u;
import defpackage.lj;

@Deprecated
public class LikeView extends FrameLayout {
    private String a;
    private e b;
    private LinearLayout c;
    private LikeButton d;
    private LikeBoxCountView e;
    private TextView f;
    private e g;
    private f h;
    private BroadcastReceiver i;
    private c j;
    private g k = g.DEFAULT;
    private b l = b.DEFAULT;
    private a m = a.DEFAULT;
    private int n = -1;
    private int o;
    private int p;
    private ad q;
    private boolean r = true;

    @Deprecated
    public LikeView(Context context) {
        super(context);
        a(context);
    }

    @Deprecated
    public LikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!(attributeSet == null || getContext() == null)) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.com_facebook_like_view);
            if (obtainStyledAttributes != null) {
                this.a = bj.a(obtainStyledAttributes.getString(i.com_facebook_like_view_com_facebook_object_id), null);
                this.b = e.a(obtainStyledAttributes.getInt(i.com_facebook_like_view_com_facebook_object_type, e.DEFAULT.a()));
                this.k = g.a(obtainStyledAttributes.getInt(i.com_facebook_like_view_com_facebook_style, g.DEFAULT.intValue));
                if (this.k != null) {
                    this.m = a.a(obtainStyledAttributes.getInt(i.com_facebook_like_view_com_facebook_auxiliary_view_position, a.DEFAULT.intValue));
                    if (this.m != null) {
                        this.l = b.a(obtainStyledAttributes.getInt(i.com_facebook_like_view_com_facebook_horizontal_alignment, b.DEFAULT.intValue));
                        if (this.l != null) {
                            this.n = obtainStyledAttributes.getColor(i.com_facebook_like_view_com_facebook_foreground_color, -1);
                            obtainStyledAttributes.recycle();
                        } else {
                            throw new IllegalArgumentException("Unsupported value for LikeView 'horizontal_alignment'");
                        }
                    }
                    throw new IllegalArgumentException("Unsupported value for LikeView 'auxiliary_view_position'");
                }
                throw new IllegalArgumentException("Unsupported value for LikeView 'style'");
            }
        }
        a(context);
    }

    @Deprecated
    public void setObjectIdAndType(String str, e eVar) {
        str = bj.a(str, null);
        if (eVar == null) {
            eVar = e.DEFAULT;
        }
        if (!bj.a((Object) str, this.a) || eVar != this.b) {
            a(str, eVar);
            b();
        }
    }

    @Deprecated
    public void setLikeViewStyle(g gVar) {
        if (gVar == null) {
            gVar = g.DEFAULT;
        }
        if (this.k != gVar) {
            this.k = gVar;
            c();
        }
    }

    @Deprecated
    public void setAuxiliaryViewPosition(a aVar) {
        if (aVar == null) {
            aVar = a.DEFAULT;
        }
        if (this.m != aVar) {
            this.m = aVar;
            c();
        }
    }

    @Deprecated
    public void setHorizontalAlignment(b bVar) {
        if (bVar == null) {
            bVar = b.DEFAULT;
        }
        if (this.l != bVar) {
            this.l = bVar;
            c();
        }
    }

    @Deprecated
    public void setForegroundColor(int i) {
        if (this.n != i) {
            this.f.setTextColor(i);
        }
    }

    @Deprecated
    public void setOnErrorListener(f fVar) {
        this.h = fVar;
    }

    @Deprecated
    public void setFragment(Fragment fragment) {
        this.q = new ad(fragment);
    }

    @Deprecated
    public void setFragment(android.app.Fragment fragment) {
        this.q = new ad(fragment);
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.r = true;
        b();
    }

    protected void onDetachedFromWindow() {
        setObjectIdAndType(null, e.UNKNOWN);
        super.onDetachedFromWindow();
    }

    private void a(Context context) {
        this.o = getResources().getDimensionPixelSize(c.com_facebook_likeview_edge_padding);
        this.p = getResources().getDimensionPixelSize(c.com_facebook_likeview_internal_padding);
        if (this.n == -1) {
            this.n = getResources().getColor(b.com_facebook_likeview_text_color);
        }
        setBackgroundColor(0);
        this.c = new LinearLayout(context);
        this.c.setLayoutParams(new LayoutParams(-2, -2));
        b(context);
        c(context);
        d(context);
        this.c.addView(this.d);
        this.c.addView(this.f);
        this.c.addView(this.e);
        addView(this.c);
        a(this.a, this.b);
        b();
    }

    private void b(Context context) {
        boolean z = this.g != null && this.g.c();
        this.d = new LikeButton(context, z);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LikeView a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                LikeView.a(this.a);
            }
        });
        this.d.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    }

    private void c(Context context) {
        this.f = new TextView(context);
        this.f.setTextSize(0, getResources().getDimension(c.com_facebook_likeview_text_size));
        this.f.setMaxLines(2);
        this.f.setTextColor(this.n);
        this.f.setGravity(17);
        this.f.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
    }

    private void d(Context context) {
        this.e = new LikeBoxCountView(context);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    private void a(String str, e eVar) {
        a();
        this.a = str;
        this.b = eVar;
        if (!bj.a(str)) {
            this.j = new c();
            if (!isInEditMode()) {
                e.a(str, eVar, this.j);
            }
        }
    }

    private void a() {
        if (this.i != null) {
            lj.a(getContext()).a(this.i);
            this.i = null;
        }
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
        this.g = null;
    }

    private void b() {
        boolean z = this.r ^ 1;
        if (this.g == null) {
            this.d.setSelected(false);
            this.f.setText(null);
            this.e.setText(null);
        } else {
            this.d.setSelected(this.g.c());
            this.f.setText(this.g.b());
            this.e.setText(this.g.a());
            z = false;
        }
        super.setEnabled(z);
        this.d.setEnabled(z);
        c();
    }

    private void c() {
        View view;
        LayoutParams layoutParams = (LayoutParams) this.c.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.d.getLayoutParams();
        int i = this.l == b.LEFT ? 3 : this.l == b.CENTER ? 1 : 5;
        layoutParams.gravity = i | 48;
        layoutParams2.gravity = i;
        this.f.setVisibility(8);
        this.e.setVisibility(8);
        if (this.k == g.STANDARD && this.g != null && !bj.a(this.g.b())) {
            view = this.f;
        } else if (this.k == g.BOX_COUNT && this.g != null && !bj.a(this.g.a())) {
            d();
            view = this.e;
        } else {
            return;
        }
        int i2 = 0;
        view.setVisibility(0);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity = i;
        LinearLayout linearLayout = this.c;
        if (this.m != a.INLINE) {
            i2 = 1;
        }
        linearLayout.setOrientation(i2);
        if (this.m == a.TOP || (this.m == a.INLINE && this.l == b.RIGHT)) {
            this.c.removeView(this.d);
            this.c.addView(this.d);
        } else {
            this.c.removeView(view);
            this.c.addView(view);
        }
        switch (this.m) {
            case TOP:
                view.setPadding(this.o, this.o, this.o, this.p);
                return;
            case BOTTOM:
                view.setPadding(this.o, this.p, this.o, this.o);
                return;
            case INLINE:
                if (this.l != b.RIGHT) {
                    view.setPadding(this.p, this.o, this.o, this.o);
                    break;
                } else {
                    view.setPadding(this.o, this.o, this.p, this.o);
                    return;
                }
        }
    }

    private void d() {
        LikeBoxCountView likeBoxCountView;
        u uVar;
        switch (this.m) {
            case TOP:
                likeBoxCountView = this.e;
                uVar = u.BOTTOM;
                break;
            case BOTTOM:
                likeBoxCountView = this.e;
                uVar = u.TOP;
                break;
            case INLINE:
                likeBoxCountView = this.e;
                if (this.l != b.RIGHT) {
                    uVar = u.LEFT;
                    break;
                } else {
                    uVar = u.RIGHT;
                    break;
                }
            default:
                return;
        }
        likeBoxCountView.setCaretPosition(uVar);
    }

    static /* synthetic */ void a(LikeView likeView) {
        if (likeView.g != null) {
            if (likeView.q == null) {
                boolean z;
                Context context = likeView.getContext();
                while (true) {
                    z = context instanceof Activity;
                    if (!z && (context instanceof ContextWrapper)) {
                        context = ((ContextWrapper) context).getBaseContext();
                    } else if (!z) {
                        throw new n("Unable to get Activity.");
                    }
                }
                if (z) {
                    throw new n("Unable to get Activity.");
                }
            }
            e eVar = likeView.g;
            Bundle bundle = new Bundle();
            bundle.putString(TtmlNode.TAG_STYLE, likeView.k.toString());
            bundle.putString("auxiliary_position", likeView.m.toString());
            bundle.putString("horizontal_alignment", likeView.l.toString());
            bundle.putString("object_id", bj.a(likeView.a, ""));
            bundle.putString("object_type", likeView.b.toString());
            eVar.a(bundle);
        }
    }

    static /* synthetic */ void a(LikeView likeView, e eVar) {
        likeView.g = eVar;
        likeView.i = new d(likeView, (byte) 0);
        lj a = lj.a(likeView.getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.LikeActionController.UPDATED");
        intentFilter.addAction("com.facebook.sdk.LikeActionController.DID_ERROR");
        intentFilter.addAction("com.facebook.sdk.LikeActionController.DID_RESET");
        a.a(likeView.i, intentFilter);
    }
}
