package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.o;

public class n extends View implements b {
    private final Paint a = new Paint();
    private final Rect b;
    private float c;
    private final o d = new o(this) {
        final /* synthetic */ n a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.f != null) {
                int f = this.a.f.f();
                if (f > 0) {
                    this.a.c = ((float) this.a.f.d()) / ((float) f);
                } else {
                    this.a.c = 0.0f;
                }
                this.a.postInvalidate();
            }
        }
    };
    private final c e = new c(this) {
        final /* synthetic */ n a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.f != null) {
                this.a.c = 0.0f;
                this.a.postInvalidate();
            }
        }
    };
    @Nullable
    private a f;

    public n(Context context) {
        super(context);
        this.a.setStyle(Style.FILL);
        this.a.setColor(-9528840);
        this.b = new Rect();
    }

    public final void a(a aVar) {
        this.f = aVar;
        aVar.a().a(this.d, this.e);
    }

    public final void b(a aVar) {
        aVar.a().b(this.e, this.d);
        this.f = null;
    }

    public void draw(Canvas canvas) {
        this.b.set(0, 0, (int) (((float) getWidth()) * this.c), getHeight());
        canvas.drawRect(this.b, this.a);
        super.draw(canvas);
    }
}
