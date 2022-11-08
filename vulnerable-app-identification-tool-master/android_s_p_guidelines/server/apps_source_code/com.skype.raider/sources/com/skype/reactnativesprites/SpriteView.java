package com.skype.reactnativesprites;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;
import com.facebook.common.b.i;
import com.facebook.common.e.h;
import com.facebook.common.e.j;
import com.facebook.common.f.a;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.c;
import com.facebook.imagepipeline.core.g;
import com.facebook.imagepipeline.image.d;
import com.facebook.imagepipeline.k.b.b;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SpriteView extends ImageView {
    private final Context a;
    private SpriteAnimation b = new SpriteAnimation();
    private c<a<h>> c;
    private volatile SpriteViewProperties d;

    public SpriteView(Context ctx) {
        super(ctx);
        this.a = ctx;
    }

    public void setProperties(SpriteViewProperties properties) {
        this.d = properties;
    }

    public final SpriteViewProperties a() {
        return this.d;
    }

    public final void a(g imagePipeline) {
        if (this.d.b() != null) {
            final c<a<com.facebook.imagepipeline.image.c>> staticImageDataSource = imagePipeline.a(com.facebook.imagepipeline.k.c.a(Uri.parse(this.d.b())).a(b.DISK_CACHE).a(com.facebook.imagepipeline.k.b.a.SMALL).q(), null);
            staticImageDataSource.a(new com.facebook.datasource.b<a<com.facebook.imagepipeline.image.c>>(this) {
                final /* synthetic */ SpriteView b;

                public final void e(c<a<com.facebook.imagepipeline.image.c>> dataSource) {
                    if (dataSource.b()) {
                        try {
                            a<com.facebook.imagepipeline.image.c> result = (a) dataSource.d();
                            if (result != null) {
                                final d bitmap = (d) result.a();
                                result.close();
                                if (!(bitmap == null || bitmap.a() == 0 || bitmap.b() == 0)) {
                                    i.b().execute(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 b;

                                        public final void run() {
                                            if (this.b.b.getDrawable() == null) {
                                                this.b.b.setImageDrawable(new BitmapDrawable(this.b.b.getResources(), bitmap.f()));
                                            }
                                        }
                                    });
                                }
                            }
                            staticImageDataSource.h();
                        } catch (Throwable th) {
                            staticImageDataSource.h();
                        }
                    }
                }

                public final void f(c<a<com.facebook.imagepipeline.image.c>> cVar) {
                    staticImageDataSource.h();
                }
            }, com.facebook.common.b.a.a());
        }
    }

    public void setSpriteAnimation(SpriteAnimation value) {
        this.b = value;
    }

    public final c<a<h>> b() {
        return this.c;
    }

    public void setAnimatedImageDataSource(c<a<h>> value) {
        this.c = value;
    }

    public final void a(final g imagePipeline, final com.facebook.imagepipeline.k.b imageRequest) {
        this.c = imagePipeline.a(imageRequest);
        this.c.a(new com.facebook.datasource.b<a<h>>(this) {
            final /* synthetic */ SpriteView c;

            public final void e(c<a<h>> dataSource) {
                Exception e;
                Throwable th;
                if (this.c.b.a(this.c)) {
                    this.c.c.h();
                } else if (dataSource.b()) {
                    a<h> result = (a) dataSource.d();
                    if (result != null) {
                        InputStream ios = null;
                        try {
                            InputStream ios2 = new BufferedInputStream(new j((h) result.a()));
                            try {
                                this.c.b.a(this.c.a, this.c.d, ios2);
                                if (!this.c.b.a(this.c)) {
                                    FLog.w("ReactSprites", "failed to apply animation to view: " + imageRequest.b());
                                    this.c.a(imagePipeline);
                                }
                                try {
                                    ios2.close();
                                } catch (IOException e2) {
                                }
                                this.c.c.h();
                            } catch (Exception e3) {
                                e = e3;
                                ios = ios2;
                                try {
                                    FLog.w("ReactSprites", "Exception: " + imageRequest.b() + ", message:" + e.toString());
                                    if (ios != null) {
                                        try {
                                            ios.close();
                                        } catch (IOException e4) {
                                        }
                                    }
                                    this.c.c.h();
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (ios != null) {
                                        try {
                                            ios.close();
                                        } catch (IOException e5) {
                                        }
                                    }
                                    this.c.c.h();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                ios = ios2;
                                if (ios != null) {
                                    ios.close();
                                }
                                this.c.c.h();
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            FLog.w("ReactSprites", "Exception: " + imageRequest.b() + ", message:" + e.toString());
                            if (ios != null) {
                                ios.close();
                            }
                            this.c.c.h();
                        }
                    }
                } else {
                    FLog.w("ReactSprites", "data source not finished: " + imageRequest.b());
                }
            }

            public final void f(c<a<h>> cVar) {
                FLog.w("ReactSprites", "failed: " + imageRequest.b());
                this.c.a(imagePipeline);
                this.c.c.h();
            }
        }, com.facebook.common.b.a.a());
    }
}
