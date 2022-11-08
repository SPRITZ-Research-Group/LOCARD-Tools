package com.facebook.ads.internal.view.f.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.c.c;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.e;
import com.google.android.exoplayer2.q;
import com.google.android.exoplayer2.q.b;
import com.google.android.exoplayer2.upstream.i;
import com.google.android.exoplayer2.upstream.k;
import com.google.android.exoplayer2.upstream.p;

@TargetApi(14)
public class a extends TextureView implements SurfaceTextureListener, c, com.google.android.exoplayer2.d.a, b {
    private static final String a = a.class.getSimpleName();
    private Uri b;
    @Nullable
    private String c;
    private e d;
    private Surface e;
    @Nullable
    private q f;
    private MediaController g;
    private d h = d.IDLE;
    private d i = d.IDLE;
    private d j = d.IDLE;
    private boolean k = false;
    private View l;
    private boolean m = false;
    private boolean n = false;
    private long o;
    private long p;
    private long q;
    private int r;
    private int s;
    private float t = 1.0f;
    private int u = -1;
    private boolean v = false;
    private boolean w = false;
    private com.facebook.ads.internal.view.f.a.a x = com.facebook.ads.internal.view.f.a.a.NOT_STARTED;
    private boolean y = false;

    public a(Context context) {
        super(context);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a(d dVar) {
        if (dVar != this.h) {
            this.h = dVar;
            if (this.h == d.STARTED) {
                this.m = true;
            }
            if (this.d != null) {
                this.d.a(dVar);
            }
        }
    }

    private void n() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
        if (this.f != null) {
            this.f.d();
            this.f = null;
        }
        this.g = null;
        this.m = false;
        a(d.IDLE);
    }

    public final int a() {
        return this.f != null ? (int) this.f.f() : 0;
    }

    public final void a(int i) {
        if (this.f != null) {
            this.u = a();
            this.f.a((long) i);
            return;
        }
        this.q = (long) i;
    }

    public final void a(int i, int i2) {
        this.r = i;
        this.s = i2;
        if (this.r != 0 && this.s != 0) {
            requestLayout();
        }
    }

    public final void a(com.facebook.ads.internal.view.f.a.a aVar) {
        this.i = d.STARTED;
        this.x = aVar;
        if (this.f == null) {
            setup(this.b);
        } else if (this.h == d.PREPARED || this.h == d.PAUSED || this.h == d.PLAYBACK_COMPLETED) {
            this.f.a(true);
            a(d.STARTED);
        }
    }

    public final void a(ExoPlaybackException exoPlaybackException) {
        a(d.ERROR);
        exoPlaybackException.printStackTrace();
        com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(exoPlaybackException, "[ExoPlayer] Error during playback of ExoPlayer"));
    }

    public final void a(boolean z) {
        if (this.f != null) {
            this.f.a(false);
        } else {
            a(d.IDLE);
        }
    }

    public final void a(boolean z, int i) {
        switch (i) {
            case 1:
                a(d.IDLE);
                return;
            case 2:
                if (this.u >= 0) {
                    int i2 = this.u;
                    this.u = -1;
                    this.d.a(i2, a());
                    return;
                }
                return;
            case 3:
                if (this.o != 0) {
                    this.p = System.currentTimeMillis() - this.o;
                }
                setRequestedVolume(this.t);
                if (this.q > 0 && this.q < this.f.e()) {
                    this.f.a(this.q);
                    this.q = 0;
                }
                if (this.f.f() != 0 && !z && this.m) {
                    a(d.PAUSED);
                    return;
                } else if (!z && this.h != d.PLAYBACK_COMPLETED) {
                    a(d.PREPARED);
                    if (this.i == d.STARTED) {
                        a(this.x);
                        this.i = d.IDLE;
                        return;
                    }
                    return;
                } else {
                    return;
                }
            case 4:
                if (z) {
                    a(d.PLAYBACK_COMPLETED);
                }
                if (this.f != null) {
                    this.f.a(false);
                    if (!z) {
                        this.f.b();
                    }
                }
                this.m = false;
                return;
            default:
                return;
        }
    }

    public final void b() {
        a(d.PLAYBACK_COMPLETED);
        c();
        this.q = 0;
    }

    public final void c() {
        this.i = d.IDLE;
        if (this.f != null) {
            this.f.c();
            this.f.d();
            this.f = null;
        }
        a(d.IDLE);
    }

    public final long d() {
        return this.p;
    }

    public final int e() {
        return this.f == null ? 0 : (int) this.f.e();
    }

    public final d f() {
        return this.h;
    }

    public final com.facebook.ads.internal.view.f.a.a g() {
        return this.x;
    }

    public final boolean h() {
        return (this.f == null || this.f.h() == null) ? false : true;
    }

    public final int i() {
        return this.s;
    }

    public final int j() {
        return this.r;
    }

    public final View k() {
        return this;
    }

    public final float l() {
        return this.t;
    }

    public final void m() {
        n();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.e != null) {
            this.e.release();
        }
        this.e = new Surface(surfaceTexture);
        if (this.f != null) {
            this.f.a(this.e);
            this.k = false;
            if (this.h == d.PAUSED && this.j != d.PAUSED) {
                a(this.x);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.e != null) {
            this.e.release();
            this.e = null;
            if (this.f != null) {
                this.f.a(null);
            }
        }
        if (!this.k) {
            this.j = this.n ? d.STARTED : this.h;
            this.k = true;
        }
        if (this.h != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f != null) {
            if (this.g != null && this.g.isShowing()) {
                return;
            }
            if (z) {
                this.k = false;
                if (this.h == d.PAUSED && this.j != d.PAUSED) {
                    a(this.x);
                    return;
                }
                return;
            }
            if (!this.k) {
                this.j = this.n ? d.STARTED : this.h;
                this.k = true;
            }
            if (this.h != d.PAUSED && !this.w) {
                a(false);
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else {
            com.facebook.ads.internal.t.a.f();
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.w = z;
    }

    public void setControlsAnchorView(View view) {
        this.l = view;
        view.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (this.a.g != null && motionEvent.getAction() == 1) {
                    if (this.a.g.isShowing()) {
                        this.a.g.hide();
                    } else {
                        this.a.g.show();
                    }
                }
                return true;
            }
        });
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else {
            com.facebook.ads.internal.t.a.f();
        }
    }

    public void setFullScreen(boolean z) {
        this.n = z;
        if (z && !this.v) {
            setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (this.a.g != null && motionEvent.getAction() == 1) {
                        if (this.a.g.isShowing()) {
                            this.a.g.hide();
                        } else {
                            this.a.g.show();
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f) {
        this.t = f;
        if (this.f != null && this.h != d.PREPARING && this.h != d.IDLE) {
            this.f.a(f);
        }
    }

    public void setTestMode(boolean z) {
        this.y = z;
    }

    public void setVideoMPD(@Nullable String str) {
        this.c = str;
    }

    public void setVideoStateChangeListener(e eVar) {
        this.d = eVar;
    }

    public void setup(Uri uri) {
        if (this.f != null) {
            n();
        }
        this.b = uri;
        setSurfaceTextureListener(this);
        p iVar = new i();
        this.f = e.a(new DefaultRenderersFactory(getContext()), new c(new com.google.android.exoplayer2.c.a.a(iVar)), new com.google.android.exoplayer2.b());
        this.f.a((b) this);
        this.f.a((com.google.android.exoplayer2.d.a) this);
        this.f.a(false);
        if (this.n && !this.v) {
            this.g = new MediaController(getContext());
            this.g.setAnchorView(this.l == null ? this : this.l);
            this.g.setMediaPlayer(new MediaPlayerControl(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final boolean canPause() {
                    return true;
                }

                public final boolean canSeekBackward() {
                    return true;
                }

                public final boolean canSeekForward() {
                    return true;
                }

                public final int getAudioSessionId() {
                    return this.a.f != null ? this.a.f.i() : 0;
                }

                public final int getBufferPercentage() {
                    return this.a.f != null ? this.a.f.g() : 0;
                }

                public final int getCurrentPosition() {
                    return this.a.a();
                }

                public final int getDuration() {
                    return this.a.e();
                }

                public final boolean isPlaying() {
                    return this.a.f != null && this.a.f.a();
                }

                public final void pause() {
                    this.a.a(true);
                }

                public final void seekTo(int i) {
                    this.a.a(i);
                }

                public final void start() {
                    this.a.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
                }
            });
            this.g.setEnabled(true);
        }
        if (this.c == null || this.c.length() == 0 || this.y) {
            this.f.a(new com.google.android.exoplayer2.source.c(this.b, new k(getContext(), s.a(getContext(), "ads"), iVar), new com.google.android.exoplayer2.extractor.c()));
        }
        a(d.PREPARING);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
