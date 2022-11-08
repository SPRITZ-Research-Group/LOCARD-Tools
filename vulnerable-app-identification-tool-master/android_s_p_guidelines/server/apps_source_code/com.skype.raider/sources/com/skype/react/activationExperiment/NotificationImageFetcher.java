package com.skype.react.activationExperiment;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.skype.react.activationExperiment.ImageFetcher.ImageFetcherCallback;

public class NotificationImageFetcher {
    NotificationImageFetcherCallback a;
    private String b;
    private String c;
    private Context d;
    private boolean e;
    private boolean f;
    private Bitmap g;
    private Bitmap h;
    private boolean i;
    private a j;

    public interface NotificationImageFetcherCallback {
        void a(Bitmap bitmap, Bitmap bitmap2);

        void a(a aVar);
    }

    public NotificationImageFetcher(Context context, String bigPictureImageURI, String iconImageURI) {
        boolean z;
        boolean z2 = true;
        this.d = context;
        this.b = bigPictureImageURI;
        this.c = iconImageURI;
        if (TextUtils.isEmpty(bigPictureImageURI)) {
            z = false;
        } else {
            z = true;
        }
        this.e = z;
        if (TextUtils.isEmpty(iconImageURI)) {
            z2 = false;
        }
        this.f = z2;
    }

    public final void a(NotificationImageFetcherCallback callback) {
        this.a = callback;
        if (this.e) {
            new ImageFetcher(this.d, this.b).a(new ImageFetcherCallback(this) {
                final /* synthetic */ NotificationImageFetcher b;

                public final void a(Bitmap bitmap) {
                    this.e = false;
                    this.g = bitmap;
                    NotificationImageFetcher.b(this);
                }

                public final void a(a fetchError) {
                    this.e = false;
                    this.i = true;
                    this.j = fetchError;
                    NotificationImageFetcher.b(this);
                }
            });
        }
        if (this.f) {
            new ImageFetcher(this.d, this.c).a(new ImageFetcherCallback(this) {
                final /* synthetic */ NotificationImageFetcher b;

                public final void a(Bitmap bitmap) {
                    this.f = false;
                    this.h = bitmap;
                    NotificationImageFetcher.b(this);
                }

                public final void a(a fetchError) {
                    this.f = false;
                    this.i = true;
                    this.j = fetchError;
                    NotificationImageFetcher.b(this);
                }
            });
        }
    }

    static /* synthetic */ void b(NotificationImageFetcher x0) {
        if (!x0.e && !x0.f) {
            if (x0.i) {
                x0.a.a(x0.j);
            } else {
                x0.a.a(x0.g, x0.h);
            }
        }
    }
}
