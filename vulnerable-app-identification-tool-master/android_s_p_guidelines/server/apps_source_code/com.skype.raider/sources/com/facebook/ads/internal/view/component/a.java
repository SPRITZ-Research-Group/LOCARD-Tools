package com.facebook.ads.internal.view.component;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.e;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import java.util.Locale;
import java.util.Map;

public class a extends Button {
    public static final int a = ((int) (16.0f * u.b));
    private static final int b = ((int) (4.0f * u.b));
    private final Paint c = new Paint();
    private final RectF d;
    private final boolean e;
    private final String f;
    private final com.facebook.ads.internal.s.a g;
    private final s h;
    @Nullable
    private final c i;
    @Nullable
    private final com.facebook.ads.internal.view.a.a j;

    public a(Context context, boolean z, boolean z2, String str, d dVar, c cVar, com.facebook.ads.internal.view.a.a aVar, com.facebook.ads.internal.s.a aVar2, s sVar) {
        super(context);
        this.i = cVar;
        this.j = aVar;
        this.e = z;
        this.f = str;
        this.g = aVar2;
        this.h = sVar;
        u.a(this, false, 16);
        setGravity(17);
        setPadding(a, a, a, a);
        setTextColor(dVar.f(z2));
        int e = dVar.e(z2);
        int a = android.support.v4.graphics.a.a(e);
        this.c.setStyle(Style.FILL);
        this.c.setColor(e);
        this.d = new RectF();
        if (!z) {
            Drawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(a));
            stateListDrawable.addState(new int[0], new ColorDrawable(e));
            u.a((View) this, stateListDrawable);
        }
    }

    public final void a(e eVar, String str, Map<String, String> map) {
        a(eVar.b(), eVar.a(), str, map);
    }

    public final void a(String str, final String str2, final String str3, final Map<String, String> map) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.i == null) {
            setVisibility(8);
            return;
        }
        setText(str.toUpperCase(Locale.US));
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a d;

            public final void onClick(View view) {
                try {
                    Uri parse = Uri.parse(str2);
                    this.d.g.a(map);
                    map.put("touch", k.a(this.d.h.e()));
                    b a = com.facebook.ads.internal.a.c.a(this.d.getContext(), this.d.i, str3, parse, map);
                    if (a != null) {
                        a.b();
                    }
                    if (this.d.j != null) {
                        this.d.j.a(this.d.f);
                    }
                } catch (ActivityNotFoundException e) {
                    String.valueOf(a.class);
                    new StringBuilder("Error while opening ").append(str2);
                } catch (Exception e2) {
                    String.valueOf(a.class);
                }
            }
        });
    }

    protected void onDraw(Canvas canvas) {
        if (this.e) {
            this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            canvas.drawRoundRect(this.d, (float) b, (float) b, this.c);
        }
        super.onDraw(canvas);
    }
}
