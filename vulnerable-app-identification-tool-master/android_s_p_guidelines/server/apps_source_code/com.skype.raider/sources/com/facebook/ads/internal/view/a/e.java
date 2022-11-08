package com.facebook.ads.internal.view.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.q.b.b;
import com.facebook.ads.internal.q.b.c;

@TargetApi(19)
public class e extends LinearLayout {
    private TextView a = new TextView(getContext());
    private TextView b;
    private Drawable c;

    public e(Context context) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.a.setTextColor(-16777216);
        this.a.setTextSize(2, 20.0f);
        this.a.setEllipsize(TruncateAt.END);
        this.a.setSingleLine(true);
        this.a.setVisibility(8);
        addView(this.a, layoutParams);
        this.b = new TextView(getContext());
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.b.setAlpha(0.5f);
        this.b.setTextColor(-16777216);
        this.b.setTextSize(2, 15.0f);
        this.b.setCompoundDrawablePadding((int) (f * 5.0f));
        this.b.setEllipsize(TruncateAt.END);
        this.b.setSingleLine(true);
        this.b.setVisibility(8);
        addView(this.b, layoutParams);
    }

    public void setSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.setText(null);
            this.b.setVisibility(8);
            return;
        }
        Drawable drawable;
        Uri parse = Uri.parse(str);
        this.b.setText(parse.getHost());
        TextView textView = this.b;
        if (Constants.SCHEME.equals(parse.getScheme())) {
            if (this.c == null) {
                this.c = new BitmapDrawable(getContext().getResources(), c.a(b.BROWSER_PADLOCK));
            }
            drawable = this.c;
        } else {
            drawable = null;
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
        this.b.setVisibility(0);
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.a.setText(null);
            this.a.setVisibility(8);
            return;
        }
        this.a.setText(str);
        this.a.setVisibility(0);
    }
}
