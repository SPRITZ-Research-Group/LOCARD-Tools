package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.n;
import java.util.concurrent.atomic.AtomicBoolean;

public class i extends c {
    private final a a;
    private final int b;
    private final String c;
    private final String d;
    private final AtomicBoolean e;
    private final f<n> f = new f<n>(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public final Class<n> a() {
            return n.class;
        }

        public final /* synthetic */ void a(d dVar) {
            if (!this.a.e.get() && this.a.a() != null) {
                int c = this.a.b - (this.a.a().d() / Constants.ONE_SECOND);
                if (c > 0) {
                    this.a.a.setText(this.a.c + ' ' + c);
                    return;
                }
                this.a.a.setText(this.a.d);
                this.a.e.set(true);
            }
        }
    };

    private static class a extends TextView {
        private final Paint a = new Paint();
        private final Paint b;
        private final RectF c;

        public a(Context context) {
            super(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            u.a((View) this, 0);
            setTextColor(-3355444);
            setPadding((int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f), (int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f));
            setTextSize(18.0f);
            this.a.setStyle(Style.STROKE);
            this.a.setColor(-10066330);
            this.a.setStrokeWidth(1.0f);
            this.a.setAntiAlias(true);
            this.b = new Paint();
            this.b.setStyle(Style.FILL);
            this.b.setColor(-1895825408);
            this.c = new RectF();
        }

        protected final void onDraw(Canvas canvas) {
            if (getText().length() != 0) {
                int width = getWidth();
                int height = getHeight();
                this.c.set(0.0f, 0.0f, (float) width, (float) height);
                canvas.drawRoundRect(this.c, 6.0f, 6.0f, this.b);
                this.c.set(2.0f, 2.0f, (float) (width - 2), (float) (height - 2));
                canvas.drawRoundRect(this.c, 6.0f, 6.0f, this.a);
                super.onDraw(canvas);
            }
        }
    }

    public i(Context context, int i, String str, String str2) {
        super(context);
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = new AtomicBoolean(false);
        this.a = new a(context);
        this.a.setText(this.c + ' ' + i);
        addView(this.a, new LayoutParams(-2, -2));
    }

    public final void b() {
        super.b();
        if (a() != null) {
            a().a().a(this.f);
        }
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (this.a.e.get() && this.a.a() != null) {
                    this.a.a().c();
                }
            }
        });
    }

    public final void c() {
        if (a() != null) {
            this.a.setOnClickListener(null);
            a().a().b(this.f);
        }
        super.c();
    }
}
