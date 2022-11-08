package com.facebook.ads.internal.view.d;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.component.h;

public class a extends RelativeLayout {
    private static final int a = ((int) (72.0f * u.b));
    private static final int b = ((int) (16.0f * u.b));
    private static final LayoutParams c = new LayoutParams(-1, -1);
    private final k d;

    public a(Context context, k kVar) {
        super(context);
        this.d = kVar;
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        ImageView eVar = new e(getContext());
        u.a((View) eVar, 0);
        eVar.setRadius(50);
        new d(eVar).a().a(this.d.a().b());
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        View hVar = new h(getContext(), this.d.d().a(), true, false, true);
        hVar.a(this.d.b().a(), this.d.b().b(), false, true);
        hVar.setAlignment(17);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, b, 0, b);
        View textView = new TextView(getContext());
        textView.setTextColor(-1);
        u.a(textView, false, 16);
        textView.setText(this.d.e().i().c());
        textView.setPadding(b, b / 2, b, b / 2);
        ViewGroup.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(0, b / 2, 0, 0);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100.0f);
        gradientDrawable.setColor(469762047);
        u.a(textView, gradientDrawable);
        linearLayout.addView(eVar, layoutParams);
        linearLayout.addView(hVar, layoutParams2);
        linearLayout.addView(textView, layoutParams3);
        u.a((View) this, -14473425);
        addView(linearLayout, c);
    }
}
