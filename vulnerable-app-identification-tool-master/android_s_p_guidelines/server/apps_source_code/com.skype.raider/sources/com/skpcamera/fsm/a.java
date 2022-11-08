package com.skpcamera.fsm;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Iterator;

public final class a {
    private static final a a = new a();
    private final ArrayList<SkypeCameraView> b = new ArrayList();

    public static a a() {
        return a;
    }

    public final void a(SkypeCameraView view) {
        if (this.b.contains(view)) {
            FLog.i("ActiveCameraViewBookkeeper", "addView removing first");
            this.b.remove(view);
        }
        this.b.add(0, view);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            SkypeCameraView v = (SkypeCameraView) it.next();
            FLog.i("ActiveCameraViewBookkeeper", "addView attached:" + v.isAttachedToWindow() + " vis:" + v.getVisibility());
        }
    }

    public final void b(SkypeCameraView view) {
        this.b.remove(view);
    }

    @Nullable
    public final SkypeCameraView b() {
        FLog.i("ActiveCameraViewBookkeeper", "prune size:" + this.b.size());
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            SkypeCameraView skypeCameraView = (SkypeCameraView) it.next();
            FLog.i("ActiveCameraViewBookkeeper", "prune view:" + System.identityHashCode(skypeCameraView) + " attached:" + skypeCameraView.isAttachedToWindow() + " visible:" + skypeCameraView.getVisibility());
            if (!skypeCameraView.isAttachedToWindow()) {
                it.remove();
            }
        }
        FLog.i("ActiveCameraViewBookkeeper", "prune done size: " + this.b.size());
        return this.b.size() > 0 ? (SkypeCameraView) this.b.get(0) : null;
    }
}
