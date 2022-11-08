package com.facebook.react.uimanager.a;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

final class m extends Animation implements d {
    private final View a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;

    public m(View view, int x, int y, int width, int height) {
        this.a = view;
        this.b = view.getX() - view.getTranslationX();
        this.c = view.getY() - view.getTranslationY();
        this.f = view.getWidth();
        this.g = view.getHeight();
        this.d = ((float) x) - this.b;
        this.e = ((float) y) - this.c;
        this.h = width - this.f;
        this.i = height - this.g;
    }

    protected final void applyTransformation(float interpolatedTime, Transformation t) {
        float newX = this.b + (this.d * interpolatedTime);
        float newY = this.c + (this.e * interpolatedTime);
        this.a.layout(Math.round(newX), Math.round(newY), Math.round(newX + (((float) this.f) + (((float) this.h) * interpolatedTime))), Math.round(newY + (((float) this.g) + (((float) this.i) * interpolatedTime))));
    }

    public final boolean willChangeBounds() {
        return true;
    }
}
