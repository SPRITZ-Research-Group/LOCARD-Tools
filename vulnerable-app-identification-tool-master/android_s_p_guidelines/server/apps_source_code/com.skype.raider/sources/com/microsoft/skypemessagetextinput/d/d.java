package com.microsoft.skypemessagetextinput.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import com.microsoft.applications.telemetry.LogConfiguration;
import java.util.concurrent.ScheduledFuture;

public abstract class d {
    private static final int[] b = new int[]{Constants.ONE_SECOND, LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS, 5000};
    private static final int[] c = new int[]{LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS};
    protected Context a;
    private a d;
    private ScheduledFuture<?> e;
    private int f = 0;
    private ScheduledFuture<?> g;
    private int h = 0;
    private int i = 0;

    public interface a {
        void a(int i);

        void a(Bitmap bitmap);
    }

    protected abstract ScheduledFuture<?> a(Runnable runnable, long j);

    protected abstract void a(Uri uri);

    protected abstract ScheduledFuture<?> b(Runnable runnable, long j);

    protected d(Context context, a callback) {
        this.a = context;
        this.d = callback;
    }

    public void a() {
        this.d = null;
    }

    public final int b() {
        return this.i;
    }

    public final void b(Uri forUri) {
        e();
        a(forUri);
        c();
    }

    protected final void a(Bitmap bitmap) {
        e();
        if (this.d != null) {
            this.d.a(bitmap);
        }
    }

    protected final void c(final Uri forUri) {
        FLog.e("SkypeMsgTextInput/ImgProvider", "Error loading image! Uri=" + forUri.toString());
        if (this.d != null) {
            int retrialTimeout;
            if (this.f < b.length) {
                int[] iArr = b;
                int i = this.f;
                this.f = i + 1;
                retrialTimeout = iArr[i];
            } else {
                retrialTimeout = b[b.length - 1];
            }
            d();
            final d _this = this;
            this.e = a(new Runnable(this) {
                final /* synthetic */ d c;

                public final void run() {
                    if (_this.d != null) {
                        _this.a(forUri);
                    }
                }
            }, (long) retrialTimeout);
        }
    }

    private void c() {
        if (this.h < c.length) {
            final d _this = this;
            int[] iArr = c;
            int i = this.h;
            this.h = i + 1;
            this.g = b(new Runnable(this) {
                final /* synthetic */ d b;

                public final void run() {
                    if (_this.d != null) {
                        _this.i = _this.i + 1;
                        _this.d.a(_this.i);
                        _this.c();
                    }
                }
            }, (long) iArr[i]);
        }
    }

    private void d() {
        if (this.e != null) {
            this.e.cancel(false);
            this.e = null;
        }
    }

    private void e() {
        d();
        if (this.g != null) {
            this.g.cancel(false);
            this.g = null;
        }
        this.f = 0;
        this.h = 0;
        this.i = 0;
    }
}
