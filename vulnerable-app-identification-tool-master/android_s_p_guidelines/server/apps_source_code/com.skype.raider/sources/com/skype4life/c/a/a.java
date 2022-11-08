package com.skype4life.c.a;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

public final class a extends OrientationEventListener {
    private final boolean a;
    private boolean b = false;
    private Context c;
    private b d;
    private c e;
    private a f;

    public interface a {
        void a(b bVar, c cVar);
    }

    public enum b {
        PORTRAIT(1),
        LANDSCAPE(2);
        
        private final int c;

        private b(int value) {
            this.c = value;
        }

        public static b a(int orientation) {
            return orientation == 1 ? PORTRAIT : LANDSCAPE;
        }

        public final int a() {
            return this.c;
        }
    }

    public enum c {
        UNKNOWN(0),
        PORTRAIT(1),
        PORTRAIT_UPSIDE_DOWN(2),
        LANDSCAPE_LEFT(3),
        LANDSCAPE_RIGHT(4);
        
        private final int f;

        private c(int value) {
            this.f = value;
        }

        public static c a(int rotation, boolean isNaturalOrientationLandscape) {
            switch (rotation) {
                case 0:
                    return isNaturalOrientationLandscape ? LANDSCAPE_LEFT : PORTRAIT;
                case 1:
                    return isNaturalOrientationLandscape ? PORTRAIT_UPSIDE_DOWN : LANDSCAPE_LEFT;
                case 2:
                    return isNaturalOrientationLandscape ? LANDSCAPE_RIGHT : PORTRAIT_UPSIDE_DOWN;
                case 3:
                    return isNaturalOrientationLandscape ? PORTRAIT : LANDSCAPE_RIGHT;
                default:
                    return UNKNOWN;
            }
        }

        public final int a() {
            return this.f;
        }
    }

    public a(Context context) {
        super(context, 3);
        this.c = context;
        this.a = false;
    }

    public final void onOrientationChanged(int orientation) {
        b configOrientation = c();
        c orientationInSpace = null;
        if (orientation != -1) {
            int surfaceRotation = -1;
            if (a(orientation, 0)) {
                surfaceRotation = 0;
            } else if (a(orientation, 90)) {
                surfaceRotation = 3;
            } else if (a(orientation, 180)) {
                surfaceRotation = 2;
            } else if (a(orientation, 270)) {
                surfaceRotation = 1;
            }
            if (surfaceRotation != -1) {
                orientationInSpace = c.a(surfaceRotation, this.a);
            }
        }
        if (this.d != configOrientation || (orientationInSpace != null && this.e != orientationInSpace)) {
            this.d = configOrientation;
            if (orientationInSpace != null) {
                this.e = orientationInSpace;
            }
            if (this.f != null) {
                this.f.a(this.d, this.e);
            }
        }
    }

    public final void enable() {
        super.enable();
        this.b = true;
    }

    public final void disable() {
        super.disable();
        this.b = false;
    }

    public final void a(a listener) {
        this.f = listener;
    }

    public final b a() {
        if (this.b) {
            return this.d;
        }
        return c();
    }

    public final c b() {
        if (this.b) {
            return this.e;
        }
        return c.a(((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getRotation(), this.a);
    }

    private b c() {
        return b.a(this.c.getResources().getConfiguration().orientation);
    }

    private static boolean a(int orientation, int target) {
        return orientation >= target + -30 && orientation <= target + 30;
    }
}
