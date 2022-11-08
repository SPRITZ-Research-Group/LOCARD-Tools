package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.drawee.controller.b;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.g;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.f;
import com.facebook.react.views.text.k;
import java.util.Locale;
import javax.annotation.Nullable;

public class a extends f {
    @Nullable
    private Uri a;
    private am b;
    private final b c;
    @Nullable
    private final Object d;
    private float f = Float.NaN;
    private float g = Float.NaN;

    public a(b draweeControllerBuilder, @Nullable Object callerContext) {
        this.c = draweeControllerBuilder;
        this.d = callerContext;
    }

    @ReactProp(name = "src")
    public void setSource(@Nullable al sources) {
        String source;
        if (sources == null || sources.size() == 0) {
            source = null;
        } else {
            source = sources.getMap(0).getString(ReactVideoViewManager.PROP_SRC_URI);
        }
        Uri uri = null;
        if (source != null) {
            try {
                uri = Uri.parse(source);
                if (uri.getScheme() == null) {
                    uri = null;
                }
            } catch (Exception e) {
            }
            if (uri == null) {
                Context E = E();
                if (source == null || source.isEmpty()) {
                    uri = null;
                } else {
                    uri = new Builder().scheme("res").path(String.valueOf(E.getResources().getIdentifier(source.toLowerCase(Locale.getDefault()).replace("-", "_"), "drawable", E.getPackageName()))).build();
                }
            }
        }
        if (uri != this.a) {
            i();
        }
        this.a = uri;
    }

    @ReactProp(name = "headers")
    public void setHeaders(am headers) {
        this.b = headers;
    }

    public void setWidth(g width) {
        if (width.d() == ReadableType.Number) {
            this.f = (float) width.b();
            return;
        }
        throw new n("Inline images must not have percentage based width");
    }

    public void setHeight(g height) {
        if (height.d() == ReadableType.Number) {
            this.g = (float) height.b();
            return;
        }
        throw new n("Inline images must not have percentage based height");
    }

    public final boolean a() {
        return true;
    }

    public final k c() {
        return new b(E().getResources(), (int) Math.ceil((double) this.g), (int) Math.ceil((double) this.f), this.a, this.b, this.c, this.d);
    }
}
