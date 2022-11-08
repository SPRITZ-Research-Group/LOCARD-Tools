package com.skype.reactnativesprites;

import android.content.Context;
import com.facebook.common.b.i;
import java.io.IOException;
import java.io.InputStream;

public final class SpriteAnimation implements SpritesConstants {
    private volatile AnimationFrames a;

    public final boolean a(final SpriteView view) {
        final AnimationFrames frames = this.a;
        boolean runAnimation = frames != null;
        if (runAnimation) {
            i.b().execute(new Runnable(this) {
                final /* synthetic */ SpriteAnimation c;

                public final void run() {
                    frames.a(view);
                }
            });
        }
        return runAnimation;
    }

    public final void a(Context context, SpriteViewProperties properties, InputStream ios) throws IOException {
        Throwable th;
        ManagedRegionDecoder decoder = null;
        try {
            ManagedRegionDecoder decoder2 = new ManagedRegionDecoder(context.getResources(), ios);
            try {
                int width = decoder2.b();
                int height = decoder2.a();
                if (!(width == 0 || height == 0)) {
                    this.a = new AnimationFrames(decoder2, width, height, properties);
                }
                decoder2.c();
            } catch (Throwable th2) {
                th = th2;
                decoder = decoder2;
                if (decoder != null) {
                    decoder.c();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (decoder != null) {
                decoder.c();
            }
            throw th;
        }
    }
}
