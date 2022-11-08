package com.facebook.ads.internal.view.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.b.b;
import com.facebook.ads.internal.q.b.c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.List;

@TargetApi(19)
public class a extends LinearLayout {
    private static final int a = Color.rgb(224, 224, 224);
    private static final Uri b = Uri.parse("http://www.facebook.com");
    private static final OnTouchListener c = new OnTouchListener() {
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    u.a(view, a.d);
                    break;
                case 1:
                    u.a(view, 0);
                    break;
            }
            return false;
        }
    };
    private static final int d = Color.argb(34, 0, 0, 0);
    private ImageView e;
    private e f;
    private ImageView g;
    private a h;
    private String i;

    public interface a {
        void a();
    }

    public a(Context context) {
        Bitmap bitmap;
        super(context);
        float f = getResources().getDisplayMetrics().density;
        int i = (int) (50.0f * f);
        int i2 = (int) (f * 4.0f);
        u.a((View) this, -1);
        setGravity(16);
        this.e = new ImageView(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        this.e.setScaleType(ScaleType.CENTER);
        this.e.setImageBitmap(c.a(b.BROWSER_CLOSE));
        this.e.setOnTouchListener(c);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (this.a.h != null) {
                    this.a.h.a();
                }
            }
        });
        addView(this.e, layoutParams);
        this.f = new e(context);
        layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        this.f.setPadding(0, i2, 0, i2);
        addView(this.f, layoutParams);
        this.g = new ImageView(context);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i, i);
        this.g.setScaleType(ScaleType.CENTER);
        this.g.setOnTouchListener(c);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.i) && !"about:blank".equals(this.a.i)) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.a.i));
                    intent.addFlags(ErrorDialogData.BINDER_CRASH);
                    this.a.getContext().startActivity(intent);
                }
            }
        });
        addView(this.g, layoutParams2);
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", b), 65536);
        if (queryIntentActivities.size() == 0) {
            this.g.setVisibility(8);
            bitmap = null;
        } else {
            bitmap = (queryIntentActivities.size() == 1 && "com.android.chrome".equals(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName)) ? c.a(b.BROWSER_LAUNCH_CHROME) : c.a(b.BROWSER_LAUNCH_NATIVE);
        }
        this.g.setImageBitmap(bitmap);
    }

    public void setListener(a aVar) {
        this.h = aVar;
    }

    public void setTitle(String str) {
        this.f.setTitle(str);
    }

    public void setUrl(String str) {
        this.i = str;
        if (TextUtils.isEmpty(str) || "about:blank".equals(str)) {
            this.f.setSubtitle(null);
            this.g.setEnabled(false);
            this.g.setColorFilter(new PorterDuffColorFilter(a, Mode.SRC_IN));
            return;
        }
        this.f.setSubtitle(str);
        this.g.setEnabled(true);
        this.g.setColorFilter(null);
    }
}
