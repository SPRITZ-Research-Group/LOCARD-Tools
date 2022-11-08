package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.a;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.internal.q.a.u;
import java.util.ArrayList;
import java.util.List;

public class d extends LinearLayout {
    private final int a;
    private final int b;
    private final int c;
    private int d = -1;
    private List<GradientDrawable> e;

    public d(Context context, com.facebook.ads.internal.adapters.a.d dVar, int i) {
        super(context);
        setOrientation(0);
        setGravity(17);
        float f = u.b;
        int i2 = (int) (8.0f * f);
        int i3 = (int) (6.0f * f);
        this.c = (int) (f * 1.0f);
        this.a = dVar.a(false);
        this.b = a.b(this.a, 128);
        this.e = new ArrayList();
        for (int i4 = 0; i4 < i; i4++) {
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(i2, i2);
            gradientDrawable.setStroke(this.c, 0);
            View imageView = new ImageView(context);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i3, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(gradientDrawable);
            this.e.add(gradientDrawable);
            addView(imageView);
        }
        a(0);
    }

    public final void a(int i) {
        if (this.d != i) {
            this.d = i;
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                int i3;
                int i4;
                if (i2 == i) {
                    int i5 = this.a;
                    i3 = this.a;
                    i4 = i5;
                } else {
                    i3 = this.b;
                    Object obj = null;
                }
                ((GradientDrawable) this.e.get(i2)).setStroke(this.c, i3);
                ((GradientDrawable) this.e.get(i2)).setColor(i4);
                ((GradientDrawable) this.e.get(i2)).invalidateSelf();
            }
        }
    }
}
