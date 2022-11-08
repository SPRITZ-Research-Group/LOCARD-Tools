package com.airbnb.android.react.lottie;

import android.widget.ImageView.ScaleType;
import com.airbnb.lottie.LottieAnimationView;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private final WeakReference<LottieAnimationView> a;
    private JSONObject b;
    private JSONObject c;
    private Float d;
    private Boolean e;
    private Float f;
    private boolean g;
    private String h;
    private com.airbnb.lottie.LottieAnimationView.a i;
    private Boolean j;
    private ScaleType k;
    private String l;
    private Boolean m;

    public a(LottieAnimationView view) {
        this.a = new WeakReference(view);
    }

    public final void a(String animationName) {
        this.h = animationName;
        this.g = true;
    }

    public final void b(String json) {
        try {
            this.b = new JSONObject(json);
        } catch (JSONException e) {
            String str = LottieAnimationViewManager.TAG;
        }
    }

    public final void c(String json) {
        try {
            this.c = new JSONObject(json);
            this.g = true;
        } catch (JSONException e) {
            String str = LottieAnimationViewManager.TAG;
        }
    }

    public final void a(com.airbnb.lottie.LottieAnimationView.a strategy) {
        this.i = strategy;
        this.g = true;
    }

    public final void a(Float progress) {
        this.d = progress;
    }

    public final void a(float speed) {
        this.f = Float.valueOf(speed);
    }

    public final void a(boolean loop) {
        this.e = Boolean.valueOf(loop);
    }

    public final void b(boolean useHardwareAcceleration) {
        this.j = Boolean.valueOf(useHardwareAcceleration);
    }

    public final void a(ScaleType scaleType) {
        this.k = scaleType;
    }

    public final void d(String imageAssetsFolder) {
        this.l = imageAssetsFolder;
    }

    public final void c(boolean enableMergePaths) {
        this.m = Boolean.valueOf(enableMergePaths);
    }

    public final void a() {
        LottieAnimationView view = (LottieAnimationView) this.a.get();
        if (view != null) {
            if (this.b != null) {
                view.setAnimation(this.b);
            }
            if (this.g) {
                view.setAnimation(this.h, this.c, this.i);
                this.g = false;
            }
            if (this.d != null) {
                view.setProgress(this.d.floatValue());
                this.d = null;
            }
            if (this.e != null) {
                view.c(this.e.booleanValue());
                this.e = null;
            }
            if (this.f != null) {
                view.setSpeed(this.f.floatValue());
                this.f = null;
            }
            if (this.j != null) {
                view.b(this.j.booleanValue());
                this.j = null;
            }
            if (this.k != null) {
                view.setScaleType(this.k);
                this.k = null;
            }
            if (this.l != null) {
                view.setImageAssetsFolder(this.l);
                this.l = null;
            }
            if (this.m != null) {
                view.a(this.m.booleanValue());
                this.m = null;
            }
        }
    }
}
