package com.skpcamera.antediluvian;

import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.ae;
import com.skpcamera.SkypeCameraViewManager;
import com.skpcamera.antediluvian.SkypeCameraView.a;
import com.skpcamera.c;

public final class d implements a, c<SkypeCameraView> {
    public final /* synthetic */ void a(ViewGroup viewGroup, Integer num) {
        SkypeCameraView skypeCameraView = (SkypeCameraView) viewGroup;
        int c = t.c();
        FLog.i("AntediluvianCameraViewManager", "chooseCamera with causeId " + c);
        skypeCameraView.setCameraType(num.intValue(), c);
    }

    public final /* bridge */ /* synthetic */ void b(ViewGroup viewGroup, boolean z) {
    }

    public final void a(SkypeCameraView view, int type) {
        ar event = new WritableNativeMap();
        event.putInt("camera", type);
        SkypeCameraViewManager.sendEvent(SkypeCameraViewManager.CAMERA_MODE_EVENT_NAME, view, event);
    }

    public final /* synthetic */ void a(boolean z) {
        t.b().a(z);
    }

    public final /* synthetic */ void f(float f) {
        t.b().e(f);
    }

    public final /* synthetic */ void e(float f) {
        t.b().d(f);
    }

    public final /* synthetic */ void d(float f) {
        t.b().c(f);
    }

    public final /* synthetic */ void c(float f) {
        t.b().b(f);
    }

    public final /* synthetic */ void a(int i) {
        s.c(i);
    }

    public final /* synthetic */ void b(float f) {
        s.a(f);
    }

    public final /* synthetic */ void a(float f) {
        t.b().a(f);
    }

    public final /* synthetic */ void a(ViewGroup viewGroup, boolean z) {
        FLog.i("AntediluvianCameraViewManager", "setSessionActive " + z + " with causeId " + t.c());
        t.b().b(z);
    }

    public final /* synthetic */ void a(Integer num) {
        t.b().f(num.intValue());
    }

    public final /* synthetic */ ViewGroup a(ae aeVar) {
        return new SkypeCameraView(aeVar, this);
    }
}
