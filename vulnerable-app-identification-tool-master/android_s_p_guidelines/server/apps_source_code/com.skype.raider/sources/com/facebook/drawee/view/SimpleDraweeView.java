package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import com.facebook.common.i.f;
import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import com.facebook.drawee.controller.b;
import com.facebook.drawee.d.a;
import javax.annotation.Nullable;

public class SimpleDraweeView extends GenericDraweeView {
    private static j<? extends b> sDraweecontrollerbuildersupplier;
    private b mControllerBuilder;

    public static void initialize(j<? extends b> draweeControllerBuilderSupplier) {
        sDraweecontrollerbuildersupplier = draweeControllerBuilderSupplier;
    }

    public SimpleDraweeView(Context context, a hierarchy) {
        super(context, hierarchy);
        init(context, null);
    }

    public SimpleDraweeView(Context context) {
        super(context);
        init(context, null);
    }

    public SimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public SimpleDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray gdhAttrs;
        try {
            com.facebook.imagepipeline.l.b.a();
            if (isInEditMode()) {
                ((a) getHierarchy()).a(null);
                getTopLevelDrawable().setVisible(true, false);
                getTopLevelDrawable().invalidateSelf();
            } else {
                h.a(sDraweecontrollerbuildersupplier, (Object) "SimpleDraweeView was not initialized!");
                this.mControllerBuilder = (b) sDraweecontrollerbuildersupplier.a();
            }
            if (attrs != null) {
                gdhAttrs = context.obtainStyledAttributes(attrs, com.facebook.drawee.a.a.SimpleDraweeView);
                if (gdhAttrs.hasValue(com.facebook.drawee.a.a.SimpleDraweeView_actualImageUri)) {
                    setImageURI(Uri.parse(gdhAttrs.getString(com.facebook.drawee.a.a.SimpleDraweeView_actualImageUri)), null);
                } else if (gdhAttrs.hasValue(com.facebook.drawee.a.a.SimpleDraweeView_actualImageResource)) {
                    int resId = gdhAttrs.getResourceId(com.facebook.drawee.a.a.SimpleDraweeView_actualImageResource, -1);
                    if (resId != -1) {
                        if (isInEditMode()) {
                            setImageResource(resId);
                        } else {
                            setActualImageResource(resId);
                        }
                    }
                }
                gdhAttrs.recycle();
            }
            com.facebook.imagepipeline.l.b.a();
        } catch (Throwable th) {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    public void setImageRequest(com.facebook.imagepipeline.k.b request) {
        setController(this.mControllerBuilder.b((Object) request).a(getController()).h());
    }

    public void setImageURI(Uri uri) {
        setImageURI(uri, null);
    }

    public void setImageURI(@Nullable String uriString) {
        setImageURI(uriString, null);
    }

    public void setImageURI(Uri uri, @Nullable Object callerContext) {
        setController(this.mControllerBuilder.a(callerContext).b(uri).b(getController()).j());
    }

    public void setImageURI(@Nullable String uriString, @Nullable Object callerContext) {
        setImageURI(uriString != null ? Uri.parse(uriString) : null, callerContext);
    }

    public void setActualImageResource(@DrawableRes int resourceId) {
        setActualImageResource(resourceId, null);
    }

    public void setActualImageResource(@DrawableRes int resourceId, @Nullable Object callerContext) {
        setImageURI(f.a(resourceId), callerContext);
    }

    public void setImageResource(int resId) {
        super.setImageResource(resId);
    }
}
