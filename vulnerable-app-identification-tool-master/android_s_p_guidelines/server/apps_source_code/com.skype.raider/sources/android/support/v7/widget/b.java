package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;

@RequiresApi(9)
class b extends Drawable {
    final ActionBarContainer a;

    public b(ActionBarContainer container) {
        this.a = container;
    }

    public void draw(Canvas canvas) {
        if (!this.a.d) {
            if (this.a.a != null) {
                this.a.a.draw(canvas);
            }
            if (this.a.b != null && this.a.e) {
                this.a.b.draw(canvas);
            }
        } else if (this.a.c != null) {
            this.a.c.draw(canvas);
        }
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter cf) {
    }

    public int getOpacity() {
        return 0;
    }
}
