package com.skpcamera.antediluvian;

import android.hardware.Camera;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.skpcamera.a;

public final class c implements a {
    private ag a;

    public final void a(ag context) {
        this.a = context;
        t.b().a(context);
    }

    public final void a() {
        t.b().c(t.c());
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void a(ae promise) {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "startRecording with causeId " + causeId);
        t.b().a(promise, causeId);
    }

    public final void d() {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "finishRecording with causeId " + causeId);
        t.b().d(causeId);
    }

    public final void e() {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "cancelRecording with causeId " + causeId);
        t.b().e(causeId);
    }

    public final void a(@Deprecated boolean freeze, ae promise) {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "capture with causeId " + causeId);
        t.b().a(this.a.getCacheDir(), freeze, promise, causeId);
    }

    public final void a(int x, int y) {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "focus with causeId " + causeId);
        t.b().a((float) x, (float) y, causeId);
    }

    public final void a(boolean complete, int d, int d0) {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "zoom with causeId " + causeId);
        t.b().a(complete, d, causeId);
    }

    public final void f() {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "cancel with causeId " + causeId);
        t.b().b(causeId);
    }

    public final void b(ae promise) {
        promise.a(Integer.valueOf(Camera.getNumberOfCameras()));
    }

    public final void c(ae promise) {
        promise.a(Boolean.valueOf(true));
    }

    public final void g() {
        int causeId = t.c();
        FLog.i("AntediluvianCameraModule", "enterPreview with causeId " + causeId);
        t.b().a(causeId);
    }

    public final void h() {
    }
}
