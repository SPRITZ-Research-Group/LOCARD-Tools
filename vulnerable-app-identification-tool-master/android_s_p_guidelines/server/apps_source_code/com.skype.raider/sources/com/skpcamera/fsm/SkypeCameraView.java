package com.skpcamera.fsm;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.skypecam.obscura.b.h;
import com.skypecam.obscura.view.CameraView;

public class SkypeCameraView extends CameraView {
    public SkypeCameraView(Context context) {
        super(context);
        FLog.i("SkypeCameraView", "new SkypeCameraView");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FLog.i("SkypeCameraView", "onDetachedFromWindow");
        a.a().b(this);
        h.e().a(a.a().b());
    }
}
