package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

final class b extends ImageView {
    int a;
    private AnimationListener b;

    private class a extends OvalShape {
        final /* synthetic */ b a;
        private RadialGradient b;
        private Paint c = new Paint();

        a(b bVar, int shadowRadius) {
            this.a = bVar;
            bVar.a = shadowRadius;
            a((int) rect().width());
        }

        protected final void onResize(float width, float height) {
            super.onResize(width, height);
            a((int) width);
        }

        public final void draw(Canvas canvas, Paint paint) {
            int viewWidth = this.a.getWidth();
            int viewHeight = this.a.getHeight();
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) (viewWidth / 2), this.c);
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) ((viewWidth / 2) - this.a.a), paint);
        }

        private void a(int diameter) {
            this.b = new RadialGradient((float) (diameter / 2), (float) (diameter / 2), (float) this.a.a, new int[]{1023410176, 0}, null, TileMode.CLAMP);
            this.c.setShader(this.b);
        }
    }

    b(Context context) {
        Drawable circle;
        super(context);
        float density = getContext().getResources().getDisplayMetrics().density;
        int shadowYOffset = (int) (1.75f * density);
        int shadowXOffset = (int) (0.0f * density);
        this.a = (int) (3.5f * density);
        if (a()) {
            circle = new ShapeDrawable(new OvalShape());
            ViewCompat.c((View) this, 4.0f * density);
        } else {
            circle = new ShapeDrawable(new a(this, this.a));
            setLayerType(1, circle.getPaint());
            circle.getPaint().setShadowLayer((float) this.a, (float) shadowXOffset, (float) shadowYOffset, 503316480);
            int padding = this.a;
            setPadding(padding, padding, padding, padding);
        }
        circle.getPaint().setColor(-328966);
        ViewCompat.a((View) this, circle);
    }

    private static boolean a() {
        return VERSION.SDK_INT >= 21;
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!a()) {
            setMeasuredDimension(getMeasuredWidth() + (this.a * 2), getMeasuredHeight() + (this.a * 2));
        }
    }

    public final void a(AnimationListener listener) {
        this.b = listener;
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

    public final void setBackgroundColor(int color) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(color);
        }
    }
}
