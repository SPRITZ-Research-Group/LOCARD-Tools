package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;

final class a extends ImageView {
    int a;
    private AnimationListener b;

    a(Context context) {
        Drawable shapeDrawable;
        super(context);
        float f = getContext().getResources().getDisplayMetrics().density;
        int i = (int) (1.75f * f);
        int i2 = (int) (BitmapDescriptorFactory.HUE_RED * f);
        this.a = (int) (3.5f * f);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            hs.a((View) this, f * 4.0f);
        } else {
            Drawable shapeDrawable2 = new ShapeDrawable(new b(this, this.a));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer((float) this.a, (float) i2, (float) i, 503316480);
            int i3 = this.a;
            setPadding(i3, i3, i3, i3);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(-328966);
        hs.a((View) this, shapeDrawable);
    }

    private static boolean a() {
        return VERSION.SDK_INT >= 21;
    }

    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!a()) {
            setMeasuredDimension(getMeasuredWidth() + (this.a * 2), getMeasuredHeight() + (this.a * 2));
        }
    }

    public final void a(AnimationListener animationListener) {
        this.b = animationListener;
    }

    public final void onAnimationStart() {
        super.onAnimationStart();
        if (this.b != null) {
            this.b.onAnimationStart(getAnimation());
        }
    }

    public final void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.b != null) {
            this.b.onAnimationEnd(getAnimation());
        }
    }

    public final void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }
}
