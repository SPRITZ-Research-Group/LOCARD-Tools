package androidx.swiperefreshlayout.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.shapes.OvalShape;

final class b extends OvalShape {
    final /* synthetic */ a a;
    private RadialGradient b;
    private Paint c = new Paint();

    b(a aVar, int i) {
        this.a = aVar;
        aVar.a = i;
        a((int) rect().width());
    }

    protected final void onResize(float f, float f2) {
        super.onResize(f, f2);
        a((int) f);
    }

    public final void draw(Canvas canvas, Paint paint) {
        int width = this.a.getWidth() / 2;
        float f = (float) width;
        float height = (float) (this.a.getHeight() / 2);
        canvas.drawCircle(f, height, f, this.c);
        canvas.drawCircle(f, height, (float) (width - this.a.a), paint);
    }

    private void a(int i) {
        float f = (float) (i / 2);
        this.b = new RadialGradient(f, f, (float) this.a.a, new int[]{1023410176, 0}, null, TileMode.CLAMP);
        this.c.setShader(this.b);
    }
}
