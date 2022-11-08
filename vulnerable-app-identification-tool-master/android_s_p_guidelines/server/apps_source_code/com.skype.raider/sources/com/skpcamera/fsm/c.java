package com.skpcamera.fsm;

import android.hardware.Camera;
import android.net.Uri;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skpcamera.SkypeCameraViewManager;
import com.skpcamera.a;
import com.skype.camera.imagefilter.ImageFilterManager;
import com.skypecam.obscura.b.h;
import com.skypecam.obscura.e.b;
import com.skypecam.obscura.e.d;
import com.skypecam.obscura.e.e;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.view.CameraView;
import java.util.Map;
import java.util.Map.Entry;

public final class c implements v, a, com.skypecam.obscura.d.a {
    ag a;
    private int b = -1;

    public final void a(ag context) {
        g.a(new b());
        context.a((v) this);
        this.a = context;
        h.e().a((com.skypecam.obscura.d.a) this);
    }

    public final void a() {
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void a(final ae promise) {
        h.e().a(new b<Boolean, CameraView>(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void a(Object obj, Object obj2) {
                Boolean bool = (Boolean) obj;
                CameraView cameraView = (CameraView) obj2;
                FLog.i("FSMCameraModule", "startRecording change " + bool);
                ar writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("isRecording", bool.booleanValue() ? 1 : 0);
                SkypeCameraViewManager.sendEvent(SkypeCameraViewManager.RECORDING_CHANGE_EVENT_NAME, cameraView, writableNativeMap);
            }
        }, new com.skypecam.obscura.e.a<e>(this) {
            final /* synthetic */ c b;

            public final /* synthetic */ void a(Object obj) {
                e eVar = (e) obj;
                FLog.i("FSMCameraModule", "startRecording succeeded " + eVar.a);
                Object writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(ReactVideoViewManager.PROP_SRC_URI, Uri.fromFile(eVar.a).toString());
                writableNativeMap.putString("thumbnailUri", eVar.a());
                writableNativeMap.putInt("width", eVar.b);
                writableNativeMap.putInt("height", eVar.c);
                writableNativeMap.putInt("fileSize", (int) eVar.a.length());
                promise.a(writableNativeMap);
            }
        }, new com.skypecam.obscura.e.a<Throwable>(this) {
            final /* synthetic */ c b;

            public final /* synthetic */ void a(Object obj) {
                Throwable th = (Throwable) obj;
                FLog.e("FSMCameraModule", "startRecording failed ", th);
                promise.a(th);
            }
        });
    }

    public final void d() {
        h.e().a();
    }

    public final void e() {
        h.e().d();
    }

    public final void a(@Deprecated boolean freeze, final ae promise) {
        h.e().a(new com.skypecam.obscura.e.a<d>(this) {
            final /* synthetic */ c b;

            public final /* synthetic */ void a(Object obj) {
                d dVar = (d) obj;
                FLog.i("FSMCameraModule", "capture resolve: " + dVar.a);
                String uri = Uri.fromFile(dVar.a).toString();
                Object writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(ReactVideoViewManager.PROP_SRC_URI, uri);
                writableNativeMap.putInt("fileSize", (int) dVar.a.length());
                writableNativeMap.putInt("width", dVar.b);
                writableNativeMap.putInt("height", dVar.c);
                promise.a(writableNativeMap);
            }
        }, new com.skypecam.obscura.e.a<Throwable>(this) {
            final /* synthetic */ c b;

            public final /* synthetic */ void a(Object obj) {
                Throwable th = (Throwable) obj;
                FLog.w("FSMCameraModule", "capture reject: " + th.getLocalizedMessage());
                promise.a(th);
            }
        });
    }

    public final void a(int x, int y) {
        h.e().a((float) x, (float) y);
    }

    public final void a(boolean complete, int d, int d0) {
        h.e().a(((float) d) / ((float) d0));
    }

    public final void f() {
    }

    public final void b(ae promise) {
        promise.a(Integer.valueOf(Camera.getNumberOfCameras()));
    }

    public final void c(ae promise) {
        promise.a(Boolean.valueOf(true));
    }

    public final void g() {
    }

    public final void onHostResume() {
        h e = h.e();
        g.a().b("CameraStateMachine", "onHostResume");
        e.b(com.skypecam.obscura.b.d.ACTIVITY, true);
    }

    public final void onHostPause() {
        h e = h.e();
        g.a().b("CameraStateMachine", "onHostPause");
        e.b(com.skypecam.obscura.b.d.ACTIVITY, false);
        e.a(com.skypecam.obscura.b.g.RELEASED);
    }

    public final void onHostDestroy() {
        h e = h.e();
        g.a().b("CameraStateMachine", "onHostDestroy");
        e.a(null);
    }

    public final void a(com.skypecam.obscura.d.b failure) {
        FLog.i("FSMCameraModule", "reportFailure " + failure);
        if (failure != null) {
            ar data = new WritableNativeMap();
            data.putString("failure", failure.name());
            a("CameraFailureEvent", data);
        }
    }

    private void a(String eventName, ar params) {
        params.putString(ImageFilterManager.PROP_SOURCE, "FSM");
        ((RCTNativeAppEventEmitter) this.a.a(RCTNativeAppEventEmitter.class)).emit(eventName, params);
    }

    public final void h() {
    }

    public final void a(String eventName, Map<String, String> params) {
        ar data = new WritableNativeMap();
        for (Entry<String, String> entry : params.entrySet()) {
            data.putDouble((String) entry.getKey(), Double.parseDouble((String) entry.getValue()));
        }
        a(eventName, data);
    }
}
