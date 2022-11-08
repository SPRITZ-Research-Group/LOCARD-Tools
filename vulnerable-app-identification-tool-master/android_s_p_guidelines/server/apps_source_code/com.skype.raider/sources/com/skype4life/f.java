package com.skype4life;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import com.skype.raider.R;

final class f extends Drawable {
    private View a;
    private Drawable b;
    private Drawable c;
    private Drawable d;

    f(View v) {
        this.a = v;
        this.b = v.getContext().getResources().getDrawable(R.drawable.splash_background);
        this.c = v.getContext().getResources().getDrawable(R.drawable.splash_logo_static);
        this.d = v.getContext().getResources().getDrawable(R.drawable.splash_microsoft_logo_white);
    }

    public final void draw(@NonNull Canvas canvas) {
        int w = this.b.getIntrinsicWidth();
        int h = this.b.getIntrinsicHeight();
        int view_w = this.a.getWidth();
        int view_h = this.a.getHeight();
        float scale = ((float) view_h) / ((float) view_w) > ((float) h) / ((float) w) ? ((float) view_h) / ((float) h) : ((float) view_w) / ((float) w);
        int scaled_width = (int) (((float) w) * scale);
        int scaled_height = (int) (((float) h) * scale);
        int backgroundLeft = (view_w - scaled_width) / 2;
        int backgroundTop = (view_h - scaled_height) / 2;
        this.b.setBounds(backgroundLeft, backgroundTop, backgroundLeft + scaled_width, backgroundTop + scaled_height);
        int logoTop = (view_h - this.c.getIntrinsicHeight()) / 2;
        int logoLeft = (view_w - this.c.getIntrinsicWidth()) / 2;
        this.c.setBounds(logoLeft, logoTop, logoLeft + this.c.getIntrinsicWidth(), logoTop + this.c.getIntrinsicHeight());
        int msLogoWidth = this.d.getIntrinsicWidth() / 2;
        int msLogoHeight = this.d.getIntrinsicHeight() / 2;
        int msLogoTop = (view_h - msLogoHeight) - ((int) (40.0f * this.a.getContext().getResources().getDisplayMetrics().density));
        int msLogoLeft = (view_w - msLogoWidth) / 2;
        this.d.setBounds(msLogoLeft, msLogoTop, msLogoLeft + msLogoWidth, msLogoTop + msLogoHeight);
        new LayerDrawable(new Drawable[]{this.b, this.c, this.d}).draw(canvas);
    }

    public final int getOpacity() {
        return this.b.getOpacity();
    }

    public final void setAlpha(int alpha) {
        this.b.setAlpha(alpha);
    }

    public final void setColorFilter(ColorFilter cf) {
        this.b.setColorFilter(cf);
    }
}
