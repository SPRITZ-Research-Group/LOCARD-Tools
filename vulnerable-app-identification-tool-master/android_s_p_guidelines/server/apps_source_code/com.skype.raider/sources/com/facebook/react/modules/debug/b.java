package com.facebook.react.modules.debug;

import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.annotation.Nullable;

public final class b extends com.facebook.react.modules.core.a.a {
    private final com.facebook.react.modules.core.a a;
    private final ai b;
    private final UIManagerModule c;
    private final a d;
    private boolean e = false;
    private long f = -1;
    private long g = -1;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private boolean l = false;
    @Nullable
    private TreeMap<Long, a> m;

    public static class a {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final double e;
        public final double f;
        public final int g;

        public a(int totalFrames, int totalJsFrames, int totalExpectedFrames, int total4PlusFrameStutters, double fps, double jsFps, int totalTimeMs) {
            this.a = totalFrames;
            this.b = totalJsFrames;
            this.c = totalExpectedFrames;
            this.d = total4PlusFrameStutters;
            this.e = fps;
            this.f = jsFps;
            this.g = totalTimeMs;
        }
    }

    public b(com.facebook.react.modules.core.a choreographer, ai reactContext) {
        this.a = choreographer;
        this.b = reactContext;
        this.c = (UIManagerModule) reactContext.b(UIManagerModule.class);
        this.d = new a();
    }

    public final void b(long l) {
        if (!this.e) {
            if (this.f == -1) {
                this.f = l;
            }
            long lastFrameStartTime = this.g;
            this.g = l;
            if (this.d.a(lastFrameStartTime, l)) {
                this.k++;
            }
            this.h++;
            int expectedNumFrames = i();
            if ((expectedNumFrames - this.i) - 1 >= 4) {
                this.j++;
            }
            if (this.l) {
                com.facebook.infer.annotation.a.a(this.m);
                this.m.put(Long.valueOf(System.currentTimeMillis()), new a(this.h - 1, this.k - 1, expectedNumFrames, this.j, f(), g(), l()));
            }
            this.i = expectedNumFrames;
            this.a.a(this);
        }
    }

    public final void c() {
        this.e = false;
        this.b.a().addBridgeIdleDebugListener(this.d);
        this.c.setViewHierarchyUpdateDebugListener(this.d);
        this.a.a(this);
    }

    public final void d() {
        this.m = new TreeMap();
        this.l = true;
        c();
    }

    public final void e() {
        this.e = true;
        this.b.a().removeBridgeIdleDebugListener(this.d);
        this.c.setViewHierarchyUpdateDebugListener(null);
    }

    public final double f() {
        if (this.g == this.f) {
            return 0.0d;
        }
        return (((double) (this.h - 1)) * 1.0E9d) / ((double) (this.g - this.f));
    }

    public final double g() {
        if (this.g == this.f) {
            return 0.0d;
        }
        return (((double) (this.k - 1)) * 1.0E9d) / ((double) (this.g - this.f));
    }

    public final int h() {
        return this.h - 1;
    }

    public final int i() {
        return (int) ((((double) l()) / 16.9d) + 1.0d);
    }

    public final int j() {
        return this.j;
    }

    private int l() {
        return ((int) (((double) this.g) - ((double) this.f))) / 1000000;
    }

    @Nullable
    public final a a(long upToTimeMs) {
        com.facebook.infer.annotation.a.a(this.m, "FPS was not recorded at each frame!");
        Entry<Long, a> bestEntry = this.m.floorEntry(Long.valueOf(upToTimeMs));
        if (bestEntry == null) {
            return null;
        }
        return (a) bestEntry.getValue();
    }

    public final void k() {
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.j = 0;
        this.k = 0;
        this.l = false;
        this.m = null;
    }
}
