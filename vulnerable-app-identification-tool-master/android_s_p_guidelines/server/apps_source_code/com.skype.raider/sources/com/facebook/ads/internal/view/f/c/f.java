package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.q.b.c;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.w;

public class f extends ImageView implements b {
    private static final int a = ((int) (4.0f * Resources.getSystem().getDisplayMetrics().density));
    private final Paint b = new Paint();
    @Nullable
    private a c;
    private final w d = new w(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            this.a.a();
        }
    };

    public f(Context context) {
        super(context);
        this.b.setColor(-1728053248);
        setColorFilter(-1);
        setPadding(a, a, a, a);
        c();
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (this.a.c != null) {
                    if (this.a.b()) {
                        this.a.c.setVolume(1.0f);
                    } else {
                        this.a.c.setVolume(0.0f);
                    }
                    this.a.a();
                }
            }
        });
    }

    private boolean b() {
        return this.c != null && this.c.l() == 0.0f;
    }

    private void c() {
        setImageBitmap(c.a(com.facebook.ads.internal.q.b.b.SOUND_ON));
    }

    public final void a() {
        if (this.c != null) {
            if (b()) {
                setImageBitmap(c.a(com.facebook.ads.internal.q.b.b.SOUND_OFF));
            } else {
                c();
            }
        }
    }

    public final void a(a aVar) {
        this.c = aVar;
        if (this.c != null) {
            this.c.a().a(this.d);
        }
    }

    public final void b(a aVar) {
        if (this.c != null) {
            this.c.a().b(this.d);
        }
        this.c = null;
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), this.b);
        super.onDraw(canvas);
    }
}
