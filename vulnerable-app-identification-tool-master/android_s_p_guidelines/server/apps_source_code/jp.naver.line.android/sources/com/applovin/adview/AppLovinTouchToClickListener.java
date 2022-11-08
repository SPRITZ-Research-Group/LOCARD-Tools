package com.applovin.adview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class AppLovinTouchToClickListener implements OnTouchListener {
    private long a;
    private float b;
    private float c;
    private Context d;
    private OnClickListener e;

    public AppLovinTouchToClickListener(Context context, OnClickListener onClickListener) {
        this.d = context;
        this.e = onClickListener;
    }

    private float a(float f) {
        return f / this.d.getResources().getDisplayMetrics().density;
    }

    private float a(float f, float f2, float f3, float f4) {
        f -= f3;
        f2 -= f4;
        return a((float) Math.sqrt((double) ((f * f) + (f2 * f2))));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a = System.currentTimeMillis();
                this.b = motionEvent.getX();
                this.c = motionEvent.getY();
                break;
            case 1:
                if (System.currentTimeMillis() - this.a < 1000 && a(this.b, this.c, motionEvent.getX(), motionEvent.getY()) < 10.0f) {
                    this.e.onClick(view);
                    break;
                }
        }
        return true;
    }
}
