package com.airbnb.lottie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.JsonReader;
import android.util.SparseArray;
import android.view.View.BaseSavedState;
import android.widget.ImageView;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.f.c;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LottieAnimationView extends ImageView {
    public static a a = a.Weak;
    private static final String b = LottieAnimationView.class.getSimpleName();
    private static final SparseArray<e> c = new SparseArray();
    private static final SparseArray<WeakReference<e>> d = new SparseArray();
    private static final Map<String, e> e = new HashMap();
    private static final Map<String, WeakReference<e>> f = new HashMap();
    private final h g = new h(this) {
        final /* synthetic */ LottieAnimationView a;

        {
            this.a = this$0;
        }

        public final void a(@Nullable e composition) {
            if (composition != null) {
                this.a.setComposition(composition);
            }
            this.a.p = null;
        }
    };
    private final LottieDrawable h = new LottieDrawable();
    private a i;
    private String j;
    private String k;
    @RawRes
    private int l;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    @Nullable
    private a p;
    @Nullable
    private e q;

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (byte) 0);
            }
        };
        String a;
        int b;
        float c;
        boolean d;
        String e;
        int f;
        int g;

        /* synthetic */ SavedState(Parcel x0, byte b) {
            this(x0);
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            boolean z = true;
            super(in);
            this.a = in.readString();
            this.c = in.readFloat();
            if (in.readInt() != 1) {
                z = false;
            }
            this.d = z;
            this.e = in.readString();
            this.f = in.readInt();
            this.g = in.readInt();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.a);
            out.writeFloat(this.c);
            out.writeInt(this.d ? 1 : 0);
            out.writeString(this.e);
            out.writeInt(this.f);
            out.writeInt(this.g);
        }
    }

    public enum a {
        None,
        Weak,
        Strong
    }

    public LottieAnimationView(Context context) {
        super(context);
        a(null);
    }

    public LottieAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        a(attrs);
    }

    public LottieAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        a(attrs);
    }

    private void a(@Nullable AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, com.airbnb.lottie.j.a.LottieAnimationView);
        this.i = a.values()[ta.getInt(com.airbnb.lottie.j.a.LottieAnimationView_lottie_cacheStrategy, a.ordinal())];
        if (!isInEditMode()) {
            boolean hasRawRes = ta.hasValue(com.airbnb.lottie.j.a.LottieAnimationView_lottie_rawRes);
            boolean hasFileName = ta.hasValue(com.airbnb.lottie.j.a.LottieAnimationView_lottie_fileName);
            if (hasRawRes && hasFileName) {
                throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use use only one at once.");
            } else if (hasRawRes) {
                int rawResId = ta.getResourceId(com.airbnb.lottie.j.a.LottieAnimationView_lottie_rawRes, 0);
                if (rawResId != 0) {
                    setAnimation(rawResId);
                }
            } else if (hasFileName) {
                String fileName = ta.getString(com.airbnb.lottie.j.a.LottieAnimationView_lottie_fileName);
                if (fileName != null) {
                    setAnimation(fileName);
                }
            }
        }
        if (ta.getBoolean(com.airbnb.lottie.j.a.LottieAnimationView_lottie_autoPlay, false)) {
            this.h.e();
            this.n = true;
        }
        if (ta.getBoolean(com.airbnb.lottie.j.a.LottieAnimationView_lottie_loop, false)) {
            this.h.e(-1);
        }
        if (ta.hasValue(com.airbnb.lottie.j.a.LottieAnimationView_lottie_repeatMode)) {
            setRepeatMode(ta.getInt(com.airbnb.lottie.j.a.LottieAnimationView_lottie_repeatMode, 1));
        }
        if (ta.hasValue(com.airbnb.lottie.j.a.LottieAnimationView_lottie_repeatCount)) {
            setRepeatCount(ta.getInt(com.airbnb.lottie.j.a.LottieAnimationView_lottie_repeatCount, -1));
        }
        setImageAssetsFolder(ta.getString(com.airbnb.lottie.j.a.LottieAnimationView_lottie_imageAssetsFolder));
        setProgress(ta.getFloat(com.airbnb.lottie.j.a.LottieAnimationView_lottie_progress, 0.0f));
        a(ta.getBoolean(com.airbnb.lottie.j.a.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        if (ta.hasValue(com.airbnb.lottie.j.a.LottieAnimationView_lottie_colorFilter)) {
            k filter = new k(ta.getColor(com.airbnb.lottie.j.a.LottieAnimationView_lottie_colorFilter, 0));
            e keyPath = new e("**");
            c<ColorFilter> callback = new c(filter);
            this.h.a(keyPath, g.x, callback);
        }
        if (ta.hasValue(com.airbnb.lottie.j.a.LottieAnimationView_lottie_scale)) {
            this.h.e(ta.getFloat(com.airbnb.lottie.j.a.LottieAnimationView_lottie_scale, 1.0f));
        }
        ta.recycle();
        j();
    }

    public void setImageResource(int resId) {
        g();
        h();
        super.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        a(drawable, true);
    }

    private void a(Drawable drawable, boolean recycle) {
        if (recycle && drawable != this.h) {
            g();
        }
        h();
        super.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bm) {
        g();
        h();
        super.setImageBitmap(bm);
    }

    public void invalidateDrawable(@NonNull Drawable dr) {
        if (getDrawable() == this.h) {
            super.invalidateDrawable(this.h);
        } else {
            super.invalidateDrawable(dr);
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.a = this.j;
        ss.b = this.l;
        ss.c = this.h.m();
        ss.d = this.h.h();
        ss.e = this.h.b();
        ss.f = this.h.f();
        ss.g = this.h.g();
        return ss;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.j = ss.a;
            if (!TextUtils.isEmpty(this.j)) {
                setAnimation(this.j);
            }
            this.l = ss.b;
            if (this.l != 0) {
                setAnimation(this.l);
            }
            setProgress(ss.c);
            if (ss.d) {
                a();
            }
            this.h.a(ss.e);
            setRepeatMode(ss.f);
            setRepeatCount(ss.g);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.n && this.m) {
            a();
        }
    }

    private void g() {
        if (this.h != null) {
            this.h.c();
        }
    }

    public final void a(boolean enable) {
        this.h.a(enable);
    }

    public final void b(boolean use) {
        this.o = use;
        j();
    }

    public void setAnimation(@RawRes int animationResId) {
        setAnimation(animationResId, this.i);
    }

    public void setAnimation(@RawRes final int animationResId, final a cacheStrategy) {
        this.l = animationResId;
        this.j = null;
        if (d.indexOfKey(animationResId) > 0) {
            e ref = (e) ((WeakReference) d.get(animationResId)).get();
            if (ref != null) {
                setComposition(ref);
                return;
            }
        } else if (c.indexOfKey(animationResId) > 0) {
            setComposition((e) c.get(animationResId));
            return;
        }
        i();
        h();
        Context context = getContext();
        this.p = com.airbnb.lottie.e.a.a(context.getResources().openRawResource(animationResId), null, new h(this) {
            final /* synthetic */ LottieAnimationView c;

            public final void a(e composition) {
                if (cacheStrategy == a.Strong) {
                    LottieAnimationView.c.put(animationResId, composition);
                } else if (cacheStrategy == a.Weak) {
                    LottieAnimationView.d.put(animationResId, new WeakReference(composition));
                }
                this.c.setComposition(composition);
            }
        });
    }

    public void setAnimation(String animationName) {
        setAnimation(animationName, null, this.i);
    }

    public void setAnimation(String animationName, JSONObject templateContext, final a cacheStrategy) {
        this.j = animationName;
        this.l = 0;
        JSONObject parameters = null;
        this.k = animationName;
        if (templateContext != null) {
            String key = templateContext.optString(PropertiesEntry.COLUMN_NAME_KEY);
            parameters = templateContext.optJSONObject("parameters");
            if (key != null) {
                this.k += key;
            }
        }
        if (f.containsKey(this.k)) {
            e ref = (e) ((WeakReference) f.get(animationName)).get();
            if (ref != null) {
                setComposition(ref);
                return;
            }
        } else if (e.containsKey(this.k)) {
            setComposition((e) e.get(this.k));
            return;
        }
        i();
        h();
        this.p = com.airbnb.lottie.e.a.a(getContext(), animationName, parameters, new h(this) {
            final /* synthetic */ LottieAnimationView b;

            public final void a(e composition) {
                if (cacheStrategy == a.Strong) {
                    LottieAnimationView.e.put(this.b.k, composition);
                } else if (cacheStrategy == a.Weak) {
                    LottieAnimationView.f.put(this.b.k, new WeakReference(composition));
                }
                this.b.setComposition(composition);
            }
        });
    }

    @Deprecated
    public void setAnimation(JSONObject json) {
        setAnimation(new JsonReader(new StringReader(json.toString())));
    }

    public void setAnimationFromJson(String jsonString) {
        setAnimation(new JsonReader(new StringReader(jsonString)));
    }

    public void setAnimation(JsonReader reader) {
        i();
        h();
        this.p = com.airbnb.lottie.e.a.a(reader, this.g);
    }

    private void h() {
        if (this.p != null) {
            this.p.a();
            this.p = null;
        }
    }

    public void setComposition(@NonNull e composition) {
        this.h.setCallback(this);
        this.q = composition;
        boolean isNewComposition = this.h.a(composition);
        j();
        if (getDrawable() != this.h || isNewComposition) {
            setImageDrawable(null);
            setImageDrawable(this.h);
            requestLayout();
        }
    }

    public final void a() {
        this.h.e();
        j();
    }

    public void setMinFrame(int startFrame) {
        this.h.a(startFrame);
    }

    public void setMinProgress(float startProgress) {
        this.h.a(startProgress);
    }

    public void setMaxFrame(int endFrame) {
        this.h.b(endFrame);
    }

    public void setMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float endProgress) {
        this.h.b(endProgress);
    }

    public void setMinAndMaxFrame(int minFrame, int maxFrame) {
        this.h.a(minFrame, maxFrame);
    }

    public void setMinAndMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float minProgress, @FloatRange(from = 0.0d, to = 1.0d) float maxProgress) {
        this.h.a(minProgress, maxProgress);
    }

    public void setSpeed(float speed) {
        this.h.c(speed);
    }

    @Deprecated
    public final void c(boolean loop) {
        this.h.e(loop ? -1 : 0);
    }

    public void setRepeatMode(int mode) {
        this.h.d(mode);
    }

    public void setRepeatCount(int count) {
        this.h.e(count);
    }

    public void setImageAssetsFolder(String imageAssetsFolder) {
        this.h.a(imageAssetsFolder);
    }

    public void setImageAssetDelegate(c assetDelegate) {
        this.h.a(assetDelegate);
    }

    public void setFontAssetDelegate(b assetDelegate) {
        this.h.a(assetDelegate);
    }

    public void setTextDelegate(l textDelegate) {
        this.h.b = textDelegate;
    }

    public void setScale(float scale) {
        this.h.e(scale);
        if (getDrawable() == this.h) {
            a(null, false);
            a(this.h, false);
        }
    }

    public final void b() {
        this.h.l();
        j();
    }

    public void setFrame(int frame) {
        this.h.c(frame);
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float progress) {
        this.h.d(progress);
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.h.b(enabled);
    }

    private void i() {
        this.q = null;
        this.h.d();
    }

    private void j() {
        int i = 1;
        boolean useHardwareLayer = this.o && this.h.h();
        if (useHardwareLayer) {
            i = 2;
        }
        setLayerType(i, null);
    }

    protected void onDetachedFromWindow() {
        if (this.h.h()) {
            b();
            this.m = true;
        }
        g();
        super.onDetachedFromWindow();
    }
}
