package com.microsoft.skypemessagetextinput.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.facebook.common.b.i;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.e.b;
import com.microsoft.skypemessagetextinput.d.d.a;
import java.util.concurrent.ScheduledFuture;

public final class c extends d {
    private a b = new a();

    public c(Context context, a callback) {
        super(context, callback);
    }

    public final void a() {
        this.b.a();
        super.a();
    }

    protected final void a(final Uri forUri) {
        final com.facebook.datasource.c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(forUri).q(), this.a);
        final d _this = this;
        dataSource.a(new b(this) {
            final /* synthetic */ c d;

            protected final void a(@Nullable Bitmap bitmap) {
                if (dataSource != null) {
                    dataSource.h();
                }
                _this.a(bitmap);
            }

            protected final void f(com.facebook.datasource.c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> dataSource) {
                if (dataSource != null) {
                    dataSource.h();
                }
                _this.c(forUri);
            }
        }, i.b());
    }

    protected final ScheduledFuture<?> a(Runnable task, long timeoutInMilliseconds) {
        return this.b.a(task, timeoutInMilliseconds);
    }

    protected final ScheduledFuture<?> b(Runnable task, long timeoutInMilliseconds) {
        return this.b.a(task, timeoutInMilliseconds);
    }
}
