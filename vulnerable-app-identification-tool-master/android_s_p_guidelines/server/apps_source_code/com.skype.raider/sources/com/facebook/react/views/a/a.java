package com.facebook.react.views.a;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import javax.annotation.Nullable;

public final class a {
    @Nullable
    private Uri a;
    private String b;
    private double c;
    private boolean d;

    public a(Context context, String source, double width, double height) {
        this.b = source;
        this.c = width * height;
        this.a = a(context);
    }

    public a(Context context, String source) {
        this(context, source, 0.0d, 0.0d);
    }

    public final String a() {
        return this.b;
    }

    public final Uri b() {
        return (Uri) com.facebook.infer.annotation.a.a(this.a);
    }

    public final double c() {
        return this.c;
    }

    public final boolean d() {
        return this.d;
    }

    private Uri a(Context context) {
        try {
            Uri uri = Uri.parse(this.b);
            if (uri.getScheme() == null) {
                return b(context);
            }
            return uri;
        } catch (Exception e) {
            return b(context);
        }
    }

    private Uri b(Context context) {
        this.d = true;
        int a = c.a().a(context, this.b);
        return a > 0 ? new Builder().scheme("res").path(String.valueOf(a)).build() : Uri.EMPTY;
    }
}
