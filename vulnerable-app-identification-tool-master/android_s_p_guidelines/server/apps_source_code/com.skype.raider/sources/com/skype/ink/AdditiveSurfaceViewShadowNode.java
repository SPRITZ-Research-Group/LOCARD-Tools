package com.skype.ink;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.h;
import javax.annotation.Nullable;

public class AdditiveSurfaceViewShadowNode extends h {
    @Nullable
    private Bitmap a;

    public final boolean a() {
        return false;
    }

    public final boolean b() {
        return true;
    }

    public final void a(al uiUpdater) {
        super.a(uiUpdater);
        int A = A();
        if (this.a == null) {
            this.a = Bitmap.createBitmap((int) P(), (int) Q(), Config.ARGB_8888);
        }
        uiUpdater.a(A, this.a);
    }
}
