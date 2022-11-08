package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import java.lang.ref.WeakReference;

public class b extends c {
    private WeakReference<OnAudioFocusChangeListener> a = null;
    private final com.facebook.ads.internal.view.f.b.c b = new com.facebook.ads.internal.view.f.b.c(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            ((AudioManager) this.a.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.a.a == null ? null : (OnAudioFocusChangeListener) this.a.a.get());
        }
    };
    private final i c = new i(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            ((AudioManager) this.a.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.a.a == null ? null : (OnAudioFocusChangeListener) this.a.a.get());
        }
    };
    private final k d = new k(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.a == null || this.a.a.get() == null) {
                this.a.a = new WeakReference(new OnAudioFocusChangeListener(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public final void onAudioFocusChange(final int i) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public final void run() {
                                if (this.b.a.a.a() != null && i <= 0) {
                                    this.b.a.a.a().a(false);
                                }
                            }
                        });
                    }
                });
            }
            ((AudioManager) this.a.getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus((OnAudioFocusChangeListener) this.a.a.get(), 3, 1);
        }
    };

    public b(Context context) {
        super(context);
    }

    protected final void b() {
        super.b();
        if (a() != null) {
            a().a().a(this.d, this.b, this.c);
        }
    }

    protected final void c() {
        if (a() != null) {
            a().a().b(this.c, this.b, this.d);
        }
        super.c();
    }

    protected void onDetachedFromWindow() {
        ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.a == null ? null : (OnAudioFocusChangeListener) this.a.get());
        super.onDetachedFromWindow();
    }
}
