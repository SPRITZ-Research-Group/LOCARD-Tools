package com.skype.slimcore.video;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import com.skype.SkyLib;
import com.skype.slimcore.skylib.SkyLibManager.SkyLibExecution;
import com.skype.slimcore.skylib.SkyLibProvider;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class VideoOrientationManager {
    private static final Random a = new Random();
    private static VideoOrientationManager b;
    private boolean c;
    private int d = -1;
    private OrientationEventListener e;
    private WindowManager f;
    private WeakReference<SkyLibProvider> g;
    private Map<Integer, OrientationChangedCallback> h;

    public interface OrientationChangedCallback {
        void b(int i);
    }

    public static VideoOrientationManager a(Context context, WeakReference<SkyLibProvider> skylibProvider) {
        if (b == null) {
            b = new VideoOrientationManager(context, skylibProvider);
        }
        return b;
    }

    private VideoOrientationManager(Context context, WeakReference<SkyLibProvider> skylibProvider) {
        this.f = (WindowManager) context.getSystemService("window");
        if (this.f != null) {
            this.e = new OrientationEventListener(this, context) {
                final /* synthetic */ VideoOrientationManager a;

                public final void onOrientationChanged(int orientation) {
                    int causeId = VideoOrientationManager.a.nextInt();
                    if (orientation != this.a.d) {
                        FLog.d("VideoOrientationManager", "onOrientationChanged %d causeId: %x", Integer.valueOf(orientation), Integer.valueOf(causeId));
                        VideoOrientationManager.a(this.a, causeId);
                    }
                }
            };
        } else {
            FLog.e("VideoOrientationManager", "Could not get WindowManager for WINDOW_SERVICE");
        }
        this.g = skylibProvider;
        this.h = new HashMap();
    }

    public final void a(int videoObjectId, OrientationChangedCallback orientationChangedCallback) {
        this.h.put(Integer.valueOf(videoObjectId), orientationChangedCallback);
    }

    public final void a(int videoObjectId) {
        this.h.remove(Integer.valueOf(videoObjectId));
    }

    public final void a() {
        int causeId = a.nextInt();
        if (this.e == null || !this.e.canDetectOrientation()) {
            FLog.w("VideoOrientationManager", "can not detect orientation events causeId: %x", Integer.valueOf(causeId));
        } else if (this.c) {
            FLog.i("VideoOrientationManager", "Orientation event listener already enabled causeId: %x", Integer.valueOf(causeId));
        } else {
            this.e.enable();
            FLog.i("VideoOrientationManager", "enable orientation event listener");
            this.c = true;
            b(causeId);
        }
    }

    public final void b() {
        if (this.c) {
            this.e.disable();
            this.c = false;
            FLog.i("VideoOrientationManager", "enable orientation event listener");
        }
    }

    private void b(int causeId) {
        for (Entry value : this.h.entrySet()) {
            ((OrientationChangedCallback) value.getValue()).b(causeId);
        }
    }

    public final int c() {
        return this.d;
    }

    static /* synthetic */ void a(VideoOrientationManager x0, int x1) {
        final int rotation = x0.f.getDefaultDisplay().getRotation() * 90;
        if (x0.d != rotation) {
            FLog.i("VideoOrientationManager", "updateVideoRotation %d causeId: %x", Integer.valueOf(rotation), Integer.valueOf(x1));
            x0.d = rotation;
            SkyLibProvider skyLibProvider = (SkyLibProvider) x0.g.get();
            if (skyLibProvider == null) {
                FLog.i("VideoOrientationManager", "updateVideoRotation: skylibProvider is null causeId: %x", Integer.valueOf(x1));
            } else {
                skyLibProvider.d().a(new SkyLibExecution(x0) {
                    final /* synthetic */ VideoOrientationManager b;

                    public final void a(SkyLib skyLib) {
                        if (skyLib != null) {
                            skyLib.setDeviceOrientation((360 - rotation) % 360);
                        }
                    }
                });
            }
            x0.b(x1);
        }
    }
}
