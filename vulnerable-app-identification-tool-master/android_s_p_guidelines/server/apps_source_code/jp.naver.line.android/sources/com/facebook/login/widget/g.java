package com.facebook.login.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.login.q;
import com.facebook.login.r;

final class g extends FrameLayout {
    final /* synthetic */ f a;
    private ImageView b = ((ImageView) findViewById(q.com_facebook_tooltip_bubble_view_top_pointer));
    private ImageView c = ((ImageView) findViewById(q.com_facebook_tooltip_bubble_view_bottom_pointer));
    private View d = findViewById(q.com_facebook_body_frame);
    private ImageView e = ((ImageView) findViewById(q.com_facebook_button_xout));

    public g(f fVar, Context context) {
        this.a = fVar;
        super(context);
        LayoutInflater.from(getContext()).inflate(r.com_facebook_tooltip_bubble, this);
    }

    public final void a() {
        this.b.setVisibility(0);
        this.c.setVisibility(4);
    }

    public final void b() {
        this.b.setVisibility(4);
        this.c.setVisibility(0);
    }
}
