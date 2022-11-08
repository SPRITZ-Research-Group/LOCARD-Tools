package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.f.a;
import com.facebook.common.internal.d;
import com.facebook.common.internal.j;
import com.facebook.drawee.backends.pipeline.info.e;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.b;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.g;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.Set;
import javax.annotation.Nullable;

public final class c extends b<c, com.facebook.imagepipeline.k.b, a<com.facebook.imagepipeline.image.c>, ImageInfo> {
    private final g a;
    private final e b;
    @Nullable
    private d<com.facebook.imagepipeline.g.a> c;
    @Nullable
    private com.facebook.drawee.backends.pipeline.info.b d;
    @Nullable
    private e e;

    protected final /* synthetic */ com.facebook.datasource.c a(DraweeController draweeController, Object obj, Object obj2, b.a aVar) {
        com.facebook.imagepipeline.k.b.b bVar;
        com.facebook.imagepipeline.h.c a;
        com.facebook.imagepipeline.k.b bVar2 = (com.facebook.imagepipeline.k.b) obj;
        g gVar = this.a;
        switch (aVar) {
            case FULL_FETCH:
                bVar = com.facebook.imagepipeline.k.b.b.FULL_FETCH;
                break;
            case DISK_CACHE:
                bVar = com.facebook.imagepipeline.k.b.b.DISK_CACHE;
                break;
            case BITMAP_MEMORY_CACHE:
                bVar = com.facebook.imagepipeline.k.b.b.BITMAP_MEMORY_CACHE;
                break;
            default:
                throw new RuntimeException("Cache level" + aVar + "is not supported. ");
        }
        if (draweeController instanceof b) {
            a = ((b) draweeController).a();
        } else {
            a = null;
        }
        return gVar.a(bVar2, obj2, bVar, a);
    }

    protected final /* synthetic */ com.facebook.drawee.controller.a a() {
        return k();
    }

    public final /* synthetic */ com.facebook.drawee.interfaces.c b(@Nullable Uri uri) {
        return a(uri);
    }

    public c(Context context, e pipelineDraweeControllerFactory, g imagePipeline, Set<ControllerListener> boundControllerListeners) {
        super(context, boundControllerListeners);
        this.a = imagePipeline;
        this.b = pipelineDraweeControllerFactory;
    }

    public final c a(@Nullable Uri uri) {
        if (uri == null) {
            return (c) super.b(null);
        }
        return (c) super.b(com.facebook.imagepipeline.k.c.a(uri).a(RotationOptions.b()).q());
    }

    private b k() {
        com.facebook.imagepipeline.l.b.a();
        try {
            b controller;
            com.facebook.cache.a.c cVar;
            DraweeController oldController = g();
            String controllerId = b.i();
            if (oldController instanceof b) {
                controller = (b) oldController;
            } else {
                controller = this.b.a();
            }
            j a = a(controller, controllerId);
            com.facebook.imagepipeline.k.b bVar = (com.facebook.imagepipeline.k.b) d();
            f d = this.a.d();
            if (d == null || bVar == null) {
                cVar = null;
            } else if (bVar.q() != null) {
                cVar = d.b(bVar, c());
            } else {
                cVar = d.a(bVar, c());
            }
            controller.a(a, controllerId, cVar, c(), this.c, this.d);
            controller.a(this.e);
            return controller;
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
    }
}
