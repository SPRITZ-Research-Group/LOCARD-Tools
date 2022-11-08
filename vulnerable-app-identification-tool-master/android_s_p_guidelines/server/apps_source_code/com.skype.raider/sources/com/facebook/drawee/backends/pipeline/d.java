package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.b.i;
import com.facebook.common.internal.j;
import com.facebook.drawee.a.a;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.core.g;
import java.util.Set;

public final class d implements j<c> {
    private final Context a;
    private final g b;
    private final e c;
    private final Set<ControllerListener> d;

    public final /* synthetic */ Object a() {
        return b();
    }

    public d(Context context) {
        this(context, com.facebook.imagepipeline.core.j.a());
    }

    private d(Context context, com.facebook.imagepipeline.core.j imagePipelineFactory) {
        this(context, imagePipelineFactory, (byte) 0);
    }

    private d(Context context, com.facebook.imagepipeline.core.j imagePipelineFactory, byte b) {
        this.a = context;
        this.b = imagePipelineFactory.c();
        this.c = new e();
        this.c.a(context.getResources(), a.a(), imagePipelineFactory.b(context), i.b(), this.b.c(), null, null);
        this.d = null;
    }

    public final c b() {
        return new c(this.a, this.c, this.b, this.d);
    }
}
