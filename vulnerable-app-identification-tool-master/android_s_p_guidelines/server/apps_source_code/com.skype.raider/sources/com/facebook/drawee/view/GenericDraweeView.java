package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.d.a;
import com.facebook.drawee.d.c;
import com.facebook.imagepipeline.l.b;
import javax.annotation.Nullable;

public class GenericDraweeView extends DraweeView<a> {
    public GenericDraweeView(Context context, a hierarchy) {
        super(context);
        setHierarchy(hierarchy);
    }

    public GenericDraweeView(Context context) {
        super(context);
        inflateHierarchy(context, null);
    }

    public GenericDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateHierarchy(context, attrs);
    }

    public GenericDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflateHierarchy(context, attrs);
    }

    @TargetApi(21)
    public GenericDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateHierarchy(context, attrs);
    }

    protected void inflateHierarchy(Context context, @Nullable AttributeSet attrs) {
        b.a();
        b.a();
        com.facebook.drawee.d.b builder = c.a(new com.facebook.drawee.d.b(context.getResources()), context, attrs);
        b.a();
        setAspectRatio(builder.c());
        setHierarchy(builder.s());
        b.a();
    }
}
