package com.skype.react.activationExperiment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.c;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.g;
import com.facebook.imagepipeline.k.b;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class ImageFetcher {
    private static final long a = TimeUnit.SECONDS.toMillis(10);
    private Handler b;
    private c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> c;
    private ImageFetcherCallback d;
    private String e;
    private Context f;
    private Callback g = new Callback(this) {
        final /* synthetic */ ImageFetcher a;

        {
            this.a = this$0;
        }

        public final boolean handleMessage(Message message) {
            if (1 == message.what || 2 == message.what) {
                FLog.i("ImageFetcher", "Could not fetch the image in time, falling back to text style notification");
                if (!(this.a.c == null || this.a.c.a())) {
                    this.a.c.h();
                    this.a.c = null;
                }
                if (2 == message.what && this.a.d != null) {
                    this.a.d.a(a.NetworkTimeout);
                    if (this.a.b != null) {
                        this.a.b.removeCallbacksAndMessages(null);
                    }
                }
                return true;
            }
            FLog.w("ImageFetcher", String.format("handleMessage received unexpected message %s", new Object[]{message}));
            return false;
        }
    };

    public interface ImageFetcherCallback {
        void a(Bitmap bitmap);

        void a(a aVar);
    }

    enum a {
        NetworkError,
        NetworkTimeout,
        InvalidImageUri,
        Unknown
    }

    ImageFetcher(Context context, String imageURL) {
        this.e = imageURL;
        this.f = context;
    }

    final void a(final ImageFetcherCallback callback) {
        if (this.f == null || this.e == null) {
            callback.a(a.InvalidImageUri);
            return;
        }
        this.d = callback;
        Uri uri = Uri.parse(this.e);
        b imageRequest = com.facebook.imagepipeline.k.c.a(uri).q();
        this.b = new Handler(Looper.getMainLooper(), this.g);
        this.b.sendMessageDelayed(this.b.obtainMessage(2), a);
        g imagePipeline = Fresco.b();
        imagePipeline.a(uri);
        this.c = imagePipeline.a(imageRequest, this.f);
        this.c.a(new com.facebook.imagepipeline.e.b(this) {
            final /* synthetic */ ImageFetcher b;

            protected final void a(@Nullable Bitmap fetchedBitmap) {
                ImageFetcher.a(this.b);
                if (!this.b.c.b() || fetchedBitmap == null) {
                    callback.a(a.NetworkError);
                } else {
                    callback.a(fetchedBitmap);
                }
            }

            protected final void f(c<com.facebook.common.f.a<com.facebook.imagepipeline.image.c>> dataSource) {
                ImageFetcher.a(this.b);
                if (dataSource != null) {
                    dataSource.h();
                }
                FLog.i("ImageFetcher", "Failed to fetch the image from network");
                callback.a(a.NetworkError);
            }
        }, com.facebook.common.b.a.a());
    }

    static /* synthetic */ void a(ImageFetcher x0) {
        if (x0.b != null) {
            x0.b.removeMessages(1);
            x0.b.removeCallbacksAndMessages(null);
        }
    }
}
