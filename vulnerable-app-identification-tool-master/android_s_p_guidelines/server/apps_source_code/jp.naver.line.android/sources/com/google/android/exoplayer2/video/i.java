package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;

@TargetApi(17)
final class i implements DisplayListener {
    final /* synthetic */ h a;
    private final DisplayManager b;

    public final void onDisplayAdded(int i) {
    }

    public final void onDisplayRemoved(int i) {
    }

    public i(h hVar, DisplayManager displayManager) {
        this.a = hVar;
        this.b = displayManager;
    }

    public final void a() {
        this.b.registerDisplayListener(this, null);
    }

    public final void b() {
        this.b.unregisterDisplayListener(this);
    }

    public final void onDisplayChanged(int i) {
        if (i == 0) {
            this.a.c();
        }
    }
}
