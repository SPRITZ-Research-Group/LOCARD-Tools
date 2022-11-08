package com.skpcamera.antediluvian;

import android.content.Context;
import android.view.TextureView;
import com.facebook.common.logging.FLog;

final class u extends TextureView {
    private static String a = "SkypeCameraViewFinder";
    private Context b;
    private int c = 0;
    private int d = 0;

    public interface a {
        void a(int i);

        void a(int i, int i2);
    }

    public final void a(int viewFinderPaddingX, int viewFinderPaddingY) {
        this.c = viewFinderPaddingX;
        this.d = viewFinderPaddingY;
    }

    public final int a() {
        return this.c;
    }

    public final int b() {
        return this.d;
    }

    public u(Context context, int type, SkypeCameraView view, int causeId) {
        super(context);
        this.b = context;
        t instance = t.b();
        FLog.i(a, "new viewfinder dimensions " + getWidth() + " " + getHeight() + "  (causeId " + causeId + ")");
        instance.a(type, this, causeId);
        instance.a(view);
        instance.a((a) view);
    }

    public final void a(int type, a callback, int causeId) {
        t.b().a(callback);
        t.b().a(type, this, this.b, causeId);
    }
}
