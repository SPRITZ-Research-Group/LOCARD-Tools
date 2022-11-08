package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import com.adjust.sdk.Constants;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.i.f;
import com.facebook.drawee.c.q.b;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.common.e;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.k.d;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.p;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

public class ReactImageView extends GenericDraweeView {
    private static float[] a = new float[4];
    private b b = b.AUTO;
    private final List<com.facebook.react.views.a.a> c;
    @Nullable
    private com.facebook.react.views.a.a d;
    @Nullable
    private com.facebook.react.views.a.a e;
    @Nullable
    private Drawable f;
    private int g;
    private int h;
    private float i;
    private float j = Float.NaN;
    @Nullable
    private float[] k;
    private b l = b.g;
    private boolean m;
    private final com.facebook.drawee.controller.b n;
    private final a o;
    @Nullable
    private com.facebook.imagepipeline.j.a p;
    @Nullable
    private ControllerListener q;
    @Nullable
    private ControllerListener r;
    @Nullable
    private final Object s;
    private int t = -1;
    private boolean u;
    private am v;
    private com.facebook.react.views.view.b w = new com.facebook.react.views.view.b();

    private class a extends com.facebook.imagepipeline.k.a {
        final /* synthetic */ ReactImageView b;

        private a(ReactImageView reactImageView) {
            this.b = reactImageView;
        }

        /* synthetic */ a(ReactImageView x0, byte b) {
            this(x0);
        }

        public final void a(Bitmap output, Bitmap source) {
            this.b.a(ReactImageView.a);
            output.setHasAlpha(true);
            if (c.a(ReactImageView.a[0], 0.0f) && c.a(ReactImageView.a[1], 0.0f) && c.a(ReactImageView.a[2], 0.0f) && c.a(ReactImageView.a[3], 0.0f)) {
                super.a(output, source);
                return;
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP));
            Canvas canvas = new Canvas(output);
            Path pathForBorderRadius = new Path();
            pathForBorderRadius.addRoundRect(new RectF(0.0f, 0.0f, (float) source.getWidth(), (float) source.getHeight()), ReactImageView.a, Direction.CW);
            canvas.drawPath(pathForBorderRadius, paint);
        }
    }

    public void setShouldNotifyLoadEvents(boolean shouldNotify) {
        if (shouldNotify) {
            final com.facebook.react.uimanager.events.c mEventDispatcher = ((UIManagerModule) ((ai) getContext()).b(UIManagerModule.class)).getEventDispatcher();
            this.q = new BaseControllerListener<ImageInfo>(this) {
                final /* synthetic */ ReactImageView b;

                public final /* synthetic */ void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
                    ImageInfo imageInfo = (ImageInfo) obj;
                    if (imageInfo != null) {
                        mEventDispatcher.a(new a(this.b.getId(), 2, this.b.d.a(), imageInfo.a(), imageInfo.b()));
                        mEventDispatcher.a(new a(this.b.getId(), 3));
                    }
                }

                public final void onSubmit(String id, Object callerContext) {
                    mEventDispatcher.a(new a(this.b.getId(), 4));
                }

                public final void onFailure(String id, Throwable throwable) {
                    mEventDispatcher.a(new a(this.b.getId(), 1));
                    mEventDispatcher.a(new a(this.b.getId(), 3));
                }
            };
        } else {
            this.q = null;
        }
        this.m = true;
    }

    public void setBlurRadius(float blurRadius) {
        if (blurRadius == 0.0f) {
            this.p = null;
        } else {
            this.p = new com.facebook.imagepipeline.j.a((int) p.a(blurRadius));
        }
        this.m = true;
    }

    public void setBorderColor(int borderColor) {
        this.g = borderColor;
        this.m = true;
    }

    public void setOverlayColor(int overlayColor) {
        this.h = overlayColor;
        this.m = true;
    }

    public void setBorderWidth(float borderWidth) {
        this.i = p.a(borderWidth);
        this.m = true;
    }

    public void setBorderRadius(float borderRadius) {
        if (!c.a(this.j, borderRadius)) {
            this.j = borderRadius;
            this.m = true;
        }
    }

    public void setBorderRadius(float borderRadius, int position) {
        if (this.k == null) {
            this.k = new float[4];
            Arrays.fill(this.k, Float.NaN);
        }
        if (!c.a(this.k[position], borderRadius)) {
            this.k[position] = borderRadius;
            this.m = true;
        }
    }

    public void setScaleType(b scaleType) {
        this.l = scaleType;
        this.m = true;
    }

    public void setResizeMethod(b resizeMethod) {
        this.b = resizeMethod;
        this.m = true;
    }

    public void setSource(@Nullable al sources) {
        this.c.clear();
        if (!(sources == null || sources.size() == 0)) {
            com.facebook.react.views.a.a imageSource;
            if (sources.size() == 1) {
                imageSource = new com.facebook.react.views.a.a(getContext(), sources.getMap(0).getString(ReactVideoViewManager.PROP_SRC_URI));
                this.c.add(imageSource);
                Uri.EMPTY.equals(imageSource.b());
            } else {
                for (int idx = 0; idx < sources.size(); idx++) {
                    am source = sources.getMap(idx);
                    imageSource = new com.facebook.react.views.a.a(getContext(), source.getString(ReactVideoViewManager.PROP_SRC_URI), source.getDouble("width"), source.getDouble("height"));
                    this.c.add(imageSource);
                    Uri.EMPTY.equals(imageSource.b());
                }
            }
        }
        this.m = true;
    }

    public void setLoadingIndicatorSource(@Nullable String name) {
        Drawable drawable;
        Drawable drawable2 = null;
        com.facebook.react.views.a.c a = com.facebook.react.views.a.c.a();
        Context context = getContext();
        int a2 = a.a(context, name);
        if (a2 > 0) {
            drawable = context.getResources().getDrawable(a2);
        } else {
            drawable = null;
        }
        if (drawable != null) {
            drawable2 = new com.facebook.drawee.c.b(drawable, Constants.ONE_SECOND);
        }
        this.f = drawable2;
        this.m = true;
    }

    public void setProgressiveRenderingEnabled(boolean enabled) {
        this.u = enabled;
    }

    public void setFadeDuration(int durationMs) {
        this.t = durationMs;
    }

    private void a(float[] computedCorners) {
        float f;
        float defaultBorderRadius = !com.facebook.yoga.a.a(this.j) ? this.j : 0.0f;
        if (this.k == null || com.facebook.yoga.a.a(this.k[0])) {
            f = defaultBorderRadius;
        } else {
            f = this.k[0];
        }
        computedCorners[0] = f;
        if (this.k == null || com.facebook.yoga.a.a(this.k[1])) {
            f = defaultBorderRadius;
        } else {
            f = this.k[1];
        }
        computedCorners[1] = f;
        if (this.k == null || com.facebook.yoga.a.a(this.k[2])) {
            f = defaultBorderRadius;
        } else {
            f = this.k[2];
        }
        computedCorners[2] = f;
        if (!(this.k == null || com.facebook.yoga.a.a(this.k[3]))) {
            defaultBorderRadius = this.k[3];
        }
        computedCorners[3] = defaultBorderRadius;
    }

    public void setHeaders(am headers) {
        this.v = headers;
    }

    public final void a() {
        e resizeOptions = null;
        if (!this.m) {
            return;
        }
        if (!c() || (getWidth() > 0 && getHeight() > 0)) {
            this.d = null;
            if (!this.c.isEmpty()) {
                if (c()) {
                    com.facebook.react.views.a.b.a a = com.facebook.react.views.a.b.a(getWidth(), getHeight(), this.c);
                    this.d = a.a();
                    this.e = a.b();
                } else {
                    this.d = (com.facebook.react.views.a.a) this.c.get(0);
                }
            }
            if (this.d != null) {
                boolean doResize;
                com.facebook.react.views.a.a aVar = this.d;
                if (this.b == b.AUTO) {
                    if (f.d(aVar.b()) || f.c(aVar.b())) {
                        doResize = true;
                    } else {
                        doResize = false;
                    }
                } else if (this.b == b.RESIZE) {
                    doResize = true;
                } else {
                    doResize = false;
                }
                if (!doResize || (getWidth() > 0 && getHeight() > 0)) {
                    boolean usePostprocessorScaling;
                    com.facebook.drawee.d.a hierarchy = (com.facebook.drawee.d.a) getHierarchy();
                    hierarchy.a(this.l);
                    if (this.f != null) {
                        hierarchy.a(this.f, b.e);
                    }
                    if (this.l == b.g || this.l == b.h) {
                        usePostprocessorScaling = false;
                    } else {
                        usePostprocessorScaling = true;
                    }
                    com.facebook.drawee.d.e roundingParams = hierarchy.e();
                    if (usePostprocessorScaling) {
                        roundingParams.a(0.0f);
                    } else {
                        a(a);
                        roundingParams.a(a[0], a[1], a[2], a[3]);
                    }
                    roundingParams.a(this.g, this.i);
                    if (this.h != 0) {
                        roundingParams.a(this.h);
                    } else {
                        roundingParams.a(com.facebook.drawee.d.e.a.BITMAP_ONLY);
                    }
                    hierarchy.a(roundingParams);
                    int i = this.t >= 0 ? this.t : this.d.d() ? 0 : 300;
                    hierarchy.a(i);
                    d postprocessor = null;
                    if (usePostprocessorScaling) {
                        postprocessor = this.o;
                    } else if (this.p != null) {
                        postprocessor = this.p;
                    }
                    if (doResize) {
                        resizeOptions = new e(getWidth(), getHeight());
                    }
                    Object imageRequest = com.facebook.react.modules.fresco.a.a(com.facebook.imagepipeline.k.c.a(this.d.b()).a(postprocessor).a(resizeOptions).c().a(this.u), this.v);
                    this.n.b();
                    this.n.f().a(this.s).a(getController()).b(imageRequest);
                    if (this.e != null) {
                        this.n.c(com.facebook.imagepipeline.k.c.a(this.e.b()).a(postprocessor).a(resizeOptions).c().a(this.u).q());
                    }
                    if (this.q != null && this.r != null) {
                        ControllerListener combinedListener = new com.facebook.drawee.controller.d();
                        combinedListener.a(this.q);
                        combinedListener.a(this.r);
                        this.n.a(combinedListener);
                    } else if (this.r != null) {
                        this.n.a(this.r);
                    } else if (this.q != null) {
                        this.n.a(this.q);
                    }
                    setController(this.n.h());
                    this.m = false;
                    this.n.b();
                }
            }
        }
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.r = controllerListener;
        this.m = true;
        a();
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
        this.w.b(didTakeFocus);
        return didTakeFocus;
    }

    public void clearFocus() {
        super.clearFocus();
        this.w.b();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.w.a((View) this, keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            boolean z = this.m || c();
            this.m = z;
            a();
        }
        this.w.a((View) this);
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    private boolean c() {
        return this.c.size() > 1;
    }

    public ReactImageView(Context context, com.facebook.drawee.controller.b draweeControllerBuilder, @Nullable Object callerContext) {
        super(context, new com.facebook.drawee.d.b(context.getResources()).a(com.facebook.drawee.d.e.e()).s());
        this.n = draweeControllerBuilder;
        this.o = new a();
        this.s = callerContext;
        this.c = new LinkedList();
    }
}
