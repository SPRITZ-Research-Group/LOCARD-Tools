package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.facebook.common.internal.g;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.a;
import com.facebook.imagepipeline.l.b;
import javax.annotation.Nullable;

public class DraweeView<DH extends a> extends ImageView {
    private static boolean sGlobalLegacyVisibilityHandlingEnabled = false;
    private float mAspectRatio = 0.0f;
    private b<DH> mDraweeHolder;
    private boolean mInitialised = false;
    private boolean mLegacyVisibilityHandlingEnabled = false;
    private final a.a mMeasureSpec = new a.a();

    public static void setGlobalLegacyVisibilityHandlingEnabled(boolean legacyVisibilityHandlingEnabled) {
        sGlobalLegacyVisibilityHandlingEnabled = legacyVisibilityHandlingEnabled;
    }

    public DraweeView(Context context) {
        super(context);
        init(context);
    }

    public DraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @TargetApi(21)
    public DraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        boolean z = true;
        try {
            b.a();
            if (!this.mInitialised) {
                this.mInitialised = true;
                this.mDraweeHolder = b.a(null);
                if (VERSION.SDK_INT >= 21) {
                    ColorStateList imageTintList = getImageTintList();
                    if (imageTintList == null) {
                        b.a();
                        return;
                    }
                    setColorFilter(imageTintList.getDefaultColor());
                }
                if (!sGlobalLegacyVisibilityHandlingEnabled || context.getApplicationInfo().targetSdkVersion < 24) {
                    z = false;
                }
                this.mLegacyVisibilityHandlingEnabled = z;
                b.a();
            }
        } finally {
            b.a();
        }
    }

    public void setHierarchy(DH hierarchy) {
        this.mDraweeHolder.b(hierarchy);
        super.setImageDrawable(this.mDraweeHolder.f());
    }

    public DH getHierarchy() {
        return this.mDraweeHolder.e();
    }

    @Nullable
    public Drawable getTopLevelDrawable() {
        return this.mDraweeHolder.f();
    }

    public void setController(@Nullable DraweeController draweeController) {
        this.mDraweeHolder.a(draweeController);
        super.setImageDrawable(this.mDraweeHolder.f());
    }

    @Nullable
    public DraweeController getController() {
        return this.mDraweeHolder.d();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        maybeOverrideVisibilityHandling();
        onAttach();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        maybeOverrideVisibilityHandling();
        onDetach();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        maybeOverrideVisibilityHandling();
        onDetach();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        maybeOverrideVisibilityHandling();
        onAttach();
    }

    protected void onAttach() {
        doAttach();
    }

    protected void onDetach() {
        doDetach();
    }

    protected void doAttach() {
        this.mDraweeHolder.b();
    }

    protected void doDetach() {
        this.mDraweeHolder.c();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mDraweeHolder.a(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Deprecated
    public void setImageDrawable(Drawable drawable) {
        init(getContext());
        this.mDraweeHolder.a(null);
        super.setImageDrawable(drawable);
    }

    @Deprecated
    public void setImageBitmap(Bitmap bm) {
        init(getContext());
        this.mDraweeHolder.a(null);
        super.setImageBitmap(bm);
    }

    @Deprecated
    public void setImageResource(int resId) {
        init(getContext());
        this.mDraweeHolder.a(null);
        super.setImageResource(resId);
    }

    @Deprecated
    public void setImageURI(Uri uri) {
        init(getContext());
        this.mDraweeHolder.a(null);
        super.setImageURI(uri);
    }

    public void setAspectRatio(float aspectRatio) {
        if (aspectRatio != this.mAspectRatio) {
            this.mAspectRatio = aspectRatio;
            requestLayout();
        }
    }

    public void setLegacyVisibilityHandlingEnabled(boolean legacyVisibilityHandlingEnabled) {
        this.mLegacyVisibilityHandlingEnabled = legacyVisibilityHandlingEnabled;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mMeasureSpec.a = widthMeasureSpec;
        this.mMeasureSpec.b = heightMeasureSpec;
        a.a(this.mMeasureSpec, this.mAspectRatio, getLayoutParams(), getPaddingLeft() + getPaddingRight(), getPaddingTop() + getPaddingBottom());
        super.onMeasure(this.mMeasureSpec.a, this.mMeasureSpec.b);
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        maybeOverrideVisibilityHandling();
    }

    private void maybeOverrideVisibilityHandling() {
        if (this.mLegacyVisibilityHandlingEnabled) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                boolean z;
                if (getVisibility() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                drawable.setVisible(z, false);
            }
        }
    }

    public String toString() {
        return g.a(this).a("holder", this.mDraweeHolder != null ? this.mDraweeHolder.toString() : "<no holder set>").toString();
    }
}
