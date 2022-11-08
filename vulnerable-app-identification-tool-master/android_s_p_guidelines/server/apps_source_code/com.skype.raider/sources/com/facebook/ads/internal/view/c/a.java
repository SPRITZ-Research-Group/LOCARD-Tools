package com.facebook.ads.internal.view.c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import java.util.HashMap;

public class a extends RelativeLayout {
    private final String a;
    private com.facebook.ads.internal.view.f.a b;
    private final Paint c = new Paint();
    private final RectF d;

    public a(Context context, String str, String str2, int i, com.facebook.ads.internal.view.f.a aVar, final c cVar, final String str3) {
        super(context);
        this.a = str;
        this.b = aVar;
        View textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText(str2);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        addView(textView);
        this.c.setStyle(Style.FILL);
        this.c.setColor(i);
        this.d = new RectF();
        u.a((View) this, 0);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a c;

            public final void onClick(View view) {
                try {
                    Uri parse = Uri.parse(this.c.a);
                    this.c.b.a().a(new com.facebook.ads.internal.view.f.b.a(parse));
                    b a = com.facebook.ads.internal.a.c.a(this.c.getContext(), cVar, str3, parse, new HashMap());
                    if (a != null) {
                        a.b();
                    }
                } catch (ActivityNotFoundException e) {
                    String.valueOf(a.class);
                    new StringBuilder("Error while opening ").append(this.c.a);
                } catch (Exception e2) {
                    String.valueOf(a.class);
                }
            }
        });
    }

    protected void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.d, 10.0f * f, f * 10.0f, this.c);
        super.onDraw(canvas);
    }
}
