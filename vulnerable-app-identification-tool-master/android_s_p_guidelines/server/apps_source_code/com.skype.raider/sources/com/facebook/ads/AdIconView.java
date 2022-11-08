package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.d;

public class AdIconView extends g {
    @Nullable
    private ImageView a;
    private boolean b;

    public AdIconView(Context context) {
        super(context);
        b();
    }

    public AdIconView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public AdIconView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        u.b(this.a);
        this.a = new ImageView(getContext());
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.a);
        this.b = true;
    }

    protected final View a() {
        return this.a;
    }

    public void addView(View view) {
        if (!this.b) {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (!this.b) {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (!this.b) {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.b) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.a) {
            super.bringChildToFront(view);
        }
    }

    final void a(g gVar) {
        bringChildToFront(this.a);
        gVar.a(this);
        new d(this.a).a().a(gVar.c().d().a());
    }
}
