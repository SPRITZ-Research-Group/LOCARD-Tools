package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.a;
import java.util.HashMap;

public class e extends c {
    private final String a;
    private final TextView b = new TextView(getContext());
    private final com.facebook.ads.internal.m.c c;
    private final String d;
    private final Paint e;
    private final RectF f;

    public e(Context context, String str, com.facebook.ads.internal.m.c cVar, String str2, String str3) {
        super(context);
        this.a = str;
        this.c = cVar;
        this.d = str2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.b.setTextColor(-3355444);
        this.b.setTextSize(16.0f);
        this.b.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f));
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setColor(-16777216);
        this.e.setAlpha(178);
        this.f = new RectF();
        u.a((View) this, 0);
        this.b.setText(str3);
        addView(this.b, new LayoutParams(-2, -2));
    }

    protected final void b() {
        super.b();
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (this.a.a() != null) {
                    Uri parse = Uri.parse(this.a.a);
                    this.a.a().a().a(new a(parse));
                    b a = com.facebook.ads.internal.a.c.a(this.a.getContext(), this.a.c, this.a.d, parse, new HashMap());
                    if (a != null) {
                        a.b();
                    }
                }
            }
        });
    }

    protected final void c() {
        this.b.setOnClickListener(null);
        super.c();
    }

    protected void onDraw(Canvas canvas) {
        this.f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f, 0.0f, 0.0f, this.e);
        super.onDraw(canvas);
    }
}
