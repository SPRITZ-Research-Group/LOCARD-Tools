package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.g;
import com.facebook.imagepipeline.core.h;
import com.facebook.imagepipeline.core.j;
import com.facebook.imagepipeline.l.b;
import com.facebook.soloader.SoLoader;
import javax.annotation.Nullable;

public class Fresco {
    private static final Class<?> a = Fresco.class;
    private static d b;
    private static volatile boolean c = false;

    private Fresco() {
    }

    public static c a() {
        return b.b();
    }

    public static void a(Context context, @Nullable h imagePipelineConfig) {
        b.a();
        if (c) {
            FLog.w(a, "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
        } else {
            c = true;
        }
        try {
            b.a();
            SoLoader.a(context);
            b.a();
            Context applicationContext = context.getApplicationContext();
            if (imagePipelineConfig == null) {
                j.a(applicationContext);
            } else {
                j.a(imagePipelineConfig);
            }
            b.a();
            com.facebook.common.internal.j dVar = new d(applicationContext);
            b = dVar;
            SimpleDraweeView.initialize(dVar);
            b.a();
            b.a();
        } catch (Throwable e) {
            b.a();
            throw new RuntimeException("Could not initialize SoLoader", e);
        }
    }

    public static g b() {
        return j.a().c();
    }
}
