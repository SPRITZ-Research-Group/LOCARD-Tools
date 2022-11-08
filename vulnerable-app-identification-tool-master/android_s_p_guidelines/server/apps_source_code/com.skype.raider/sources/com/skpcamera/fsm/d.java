package com.skpcamera.fsm;

import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.ae;
import com.skpcamera.SkypeCameraViewManager;
import com.skpcamera.c;
import com.skypecam.obscura.b.h;
import com.skypecam.obscura.b.i;
import com.skypecam.obscura.b.r;
import com.skypecam.obscura.c.j.a;
import com.skypecam.obscura.view.CameraView;

public final class d implements c<SkypeCameraView> {
    public final /* synthetic */ void a(ViewGroup viewGroup, Integer num) {
        Object obj;
        h e = h.e();
        if (num.intValue() == 1) {
            obj = i.FRONT;
        } else {
            obj = i.BACK;
        }
        e.a(obj, true);
    }

    public final /* synthetic */ void a(ViewGroup viewGroup, boolean z) {
        CameraView cameraView = (SkypeCameraView) viewGroup;
        if (z) {
            h.e().a(cameraView);
        }
    }

    public final /* synthetic */ void b(ViewGroup viewGroup, boolean z) {
        final SkypeCameraView skypeCameraView = (SkypeCameraView) viewGroup;
        if (z) {
            h.e().a(new a(this) {
                final /* synthetic */ d b;

                public final void a(String result) {
                    FLog.i("FSMCameraViewManager", "onHasQRCode");
                    ar event = new WritableNativeMap();
                    event.putString("string", result);
                    SkypeCameraViewManager.sendEvent(SkypeCameraViewManager.QR_CODE_DETECT_EVENT_NAME, skypeCameraView, event);
                }
            });
        }
    }

    public final /* synthetic */ void a(boolean z) {
        h.e().a(z);
    }

    public final /* synthetic */ void f(float f) {
        h.e().c((int) f);
    }

    public final /* synthetic */ void e(float f) {
        h.e().c((float) ((int) f));
    }

    public final /* synthetic */ void d(float f) {
        h.e().e((int) f);
    }

    public final /* synthetic */ void c(float f) {
        h.e().d((int) f);
    }

    public final /* synthetic */ void a(int i) {
        h.e().b(i);
    }

    public final /* synthetic */ void b(float f) {
        h.e().b(f);
    }

    public final /* synthetic */ void a(float f) {
        h.e().a((int) f);
    }

    public final /* synthetic */ void a(Integer num) {
        h.e().a(num.intValue() == 1 ? r.ON : r.OFF);
    }

    public final /* synthetic */ ViewGroup a(ae aeVar) {
        ViewGroup skypeCameraView = new SkypeCameraView(aeVar);
        a.a().a(skypeCameraView);
        return skypeCameraView;
    }
}
