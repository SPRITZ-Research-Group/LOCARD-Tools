package com.skpcamera.a;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ae;
import com.skpcamera.c;
import com.skypecam.camera2.CameraView;
import com.skypecam.camera2.m;

public final class b implements c<CameraView> {
    public final /* synthetic */ void b(ViewGroup viewGroup, boolean z) {
        m anonymousClass1;
        final CameraView cameraView = (CameraView) viewGroup;
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        if (z) {
            anonymousClass1 = new m(this) {
                final /* synthetic */ b b;
            };
        } else {
            anonymousClass1 = null;
        }
        com.skypecam.camera2.b.a(anonymousClass1);
    }

    public final /* synthetic */ void b(boolean z) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.c(z);
    }

    public final /* synthetic */ void a(boolean z) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.b(z);
    }

    public final /* bridge */ /* synthetic */ void f(float f) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.f(f);
    }

    public final /* bridge */ /* synthetic */ void e(float f) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.e(f);
    }

    public final /* bridge */ /* synthetic */ void d(float f) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.d(f);
    }

    public final /* bridge */ /* synthetic */ void c(float f) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.c(f);
    }

    public final /* synthetic */ void a(int i) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.c(i);
    }

    public final /* synthetic */ void a(Integer num) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.b(num.intValue());
    }

    public final /* synthetic */ void a(ViewGroup viewGroup, Integer num) {
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.a(num.intValue());
    }

    public final /* synthetic */ ViewGroup a(ae aeVar) {
        CameraView cameraView = new CameraView(aeVar);
        com.skypecam.camera2.b bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.a((Context) aeVar);
        bVar = com.skypecam.camera2.b.a;
        com.skypecam.camera2.b.a(cameraView);
        return cameraView;
    }
}
