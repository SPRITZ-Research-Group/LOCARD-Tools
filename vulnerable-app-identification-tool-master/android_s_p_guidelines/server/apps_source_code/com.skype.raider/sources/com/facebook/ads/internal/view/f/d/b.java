package com.facebook.ads.internal.view.f.d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer.TrackInfo;
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
import com.facebook.ads.internal.view.f.a.a;
import java.io.IOException;

@TargetApi(14)
public class b extends TextureView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, SurfaceTextureListener, c {
    private static final String t = b.class.getSimpleName();
    private Uri a;
    private e b;
    private Surface c;
    @Nullable
    private MediaPlayer d;
    private MediaController e;
    private d f = d.IDLE;
    private d g = d.IDLE;
    private d h = d.IDLE;
    private boolean i = false;
    private View j;
    private int k = 0;
    private long l;
    private int m = 0;
    private int n = 0;
    private float o = 1.0f;
    private boolean p = false;
    private int q = 3;
    private boolean r = false;
    private boolean s = false;
    private int u = 0;
    private boolean v = false;
    private a w = a.NOT_STARTED;
    private final MediaPlayerControl x = new MediaPlayerControl(this) {
        final /* synthetic */ b a;

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
            return this.a.d != null ? this.a.d.getAudioSessionId() : 0;
        }

        public final int getBufferPercentage() {
            return 0;
        }

        public final int getCurrentPosition() {
            return this.a.a();
        }

        public final int getDuration() {
            return this.a.e();
        }

        public final boolean isPlaying() {
            return this.a.d != null && this.a.d.isPlaying();
        }

        public final void pause() {
            this.a.a(true);
        }

        public final void seekTo(int i) {
            this.a.a(i);
        }

        public final void start() {
            this.a.a(a.USER_STARTED);
        }
    };

    public b(Context context) {
        super(context);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a(d dVar) {
        if (dVar != this.f) {
            this.f = dVar;
            if (this.b != null) {
                this.b.a(dVar);
            }
        }
    }

    private boolean a(@Nullable Surface surface) {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.setSurface(surface);
            return true;
        } catch (Exception e) {
            com.facebook.ads.internal.q.d.a.a(getContext(), "player", com.facebook.ads.internal.q.d.b.s, e);
            return false;
        }
    }

    private boolean n() {
        return this.f == d.PREPARED || this.f == d.STARTED || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED;
    }

    private boolean o() {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.reset();
            return true;
        } catch (Exception e) {
            com.facebook.ads.internal.q.d.a.a(getContext(), "player", com.facebook.ads.internal.q.d.b.t, e);
            return false;
        }
    }

    public final int a() {
        return (this.d == null || !n()) ? 0 : this.d.getCurrentPosition();
    }

    public final void a(int i) {
        if (this.d == null || !n()) {
            this.k = i;
        } else if (i < e() && i > 0) {
            this.u = a();
            this.k = i;
            this.d.seekTo(i);
        }
    }

    public final void a(a aVar) {
        this.g = d.STARTED;
        this.w = aVar;
        if (this.f == d.STARTED || this.f == d.PREPARED || this.f == d.IDLE || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED) {
            if (this.d == null) {
                setup(this.a);
            } else {
                if (this.k > 0) {
                    this.d.seekTo(this.k);
                }
                this.d.start();
                if (this.f != d.PREPARED || this.s) {
                    a(d.STARTED);
                }
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    public final void a(boolean z) {
        this.g = d.PAUSED;
        if (this.d != null) {
            boolean z2 = (this.f == d.PREPARING || this.f == d.PREPARED) ? false : true;
            if (z2) {
                if (z) {
                    this.h = d.PAUSED;
                    this.i = true;
                }
                this.d.pause();
                if (this.f != d.PLAYBACK_COMPLETED) {
                    a(d.PAUSED);
                    return;
                }
                return;
            }
            return;
        }
        a(d.IDLE);
    }

    public final void b() {
        a(d.PLAYBACK_COMPLETED);
        c();
        this.k = 0;
    }

    public final void c() {
        this.g = d.IDLE;
        if (this.d != null) {
            int currentPosition = this.d.getCurrentPosition();
            if (currentPosition > 0) {
                this.k = currentPosition;
            }
            this.d.stop();
            o();
            this.d.release();
            this.d = null;
            if (this.e != null) {
                this.e.hide();
                this.e.setEnabled(false);
            }
        }
        a(d.IDLE);
    }

    public final long d() {
        return this.l;
    }

    public final int e() {
        return (this.d == null || !n()) ? 0 : this.d.getDuration();
    }

    public final d f() {
        return this.f;
    }

    public final a g() {
        return this.w;
    }

    @SuppressLint({"NewApi"})
    public final boolean h() {
        if (this.d == null || VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (TrackInfo trackType : this.d.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            return true;
        }
    }

    public final int i() {
        return this.n;
    }

    public final int j() {
        return this.m;
    }

    public final View k() {
        return this;
    }

    public final float l() {
        return this.o;
    }

    public final void m() {
        if (this.d != null) {
            a(null);
            this.d.setOnBufferingUpdateListener(null);
            this.d.setOnCompletionListener(null);
            this.d.setOnErrorListener(null);
            this.d.setOnInfoListener(null);
            this.d.setOnPreparedListener(null);
            this.d.setOnVideoSizeChangedListener(null);
            this.d.setOnSeekCompleteListener(null);
            o();
            this.d = null;
            a(d.IDLE);
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.d != null) {
            this.d.pause();
        }
        a(d.PLAYBACK_COMPLETED);
        a(0);
        this.k = 0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.q <= 0 || this.f != d.STARTED) {
            a(d.ERROR);
            c();
        } else {
            this.q--;
            c();
            a(this.w);
        }
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        boolean z = true;
        switch (i) {
            case 3:
                this.s = true;
                if (this.g != d.STARTED) {
                    return true;
                }
                a(d.STARTED);
                return true;
            case 701:
                a(d.BUFFERING);
                break;
            case 702:
                if (this.f == d.PREPARING || this.f == d.PREPARED) {
                    z = false;
                }
                if (z) {
                    a(d.STARTED);
                    break;
                }
                break;
        }
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        a(d.PREPARED);
        if (this.p && !this.v) {
            this.e = new MediaController(getContext());
            this.e.setAnchorView(this.j == null ? this : this.j);
            this.e.setMediaPlayer(this.x);
            this.e.setEnabled(true);
        }
        setRequestedVolume(this.o);
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.k > 0) {
            if (this.k >= this.d.getDuration()) {
                this.k = 0;
            }
            this.d.seekTo(this.k);
            this.k = 0;
        }
        if (this.g == d.STARTED) {
            a(this.w);
        }
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.b != null) {
            this.b.a(this.u, this.k);
            this.k = 0;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.c == null) {
            this.c = new Surface(surfaceTexture);
        }
        if (a(this.c)) {
            this.i = false;
            if (this.f == d.PAUSED && this.h != d.PAUSED) {
                a(this.w);
                return;
            }
            return;
        }
        a(d.ERROR);
        m();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a(null);
        if (this.c != null) {
            this.c.release();
            this.c = null;
        }
        if (!this.i) {
            this.h = this.p ? d.STARTED : this.f;
            this.i = true;
        }
        if (this.f != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.m != 0 && this.n != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.d != null) {
            if (this.e != null && this.e.isShowing()) {
                return;
            }
            if (z) {
                this.i = false;
                if (this.f == d.PAUSED && this.h != d.PAUSED) {
                    a(this.w);
                    return;
                }
                return;
            }
            if (!this.i) {
                this.h = this.p ? d.STARTED : this.f;
                this.i = true;
            }
            if (this.f != d.PAUSED && !this.r) {
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
        this.r = z;
    }

    public void setControlsAnchorView(View view) {
        this.j = view;
        view.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (!(this.a.v || this.a.e == null || motionEvent.getAction() != 1)) {
                    if (this.a.e.isShowing()) {
                        this.a.e.hide();
                    } else {
                        this.a.e.show();
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
        this.p = z;
        if (this.p && !this.v) {
            setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!(this.a.v || this.a.e == null || motionEvent.getAction() != 1)) {
                        if (this.a.e.isShowing()) {
                            this.a.e.hide();
                        } else {
                            this.a.e.show();
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f) {
        this.o = f;
        if (this.d != null && this.f != d.PREPARING && this.f != d.IDLE) {
            this.d.setVolume(f, f);
        }
    }

    public void setVideoMPD(@Nullable String str) {
    }

    public void setVideoStateChangeListener(e eVar) {
        this.b = eVar;
    }

    public void setup(Uri uri) {
        MediaPlayer mediaPlayer;
        Object e;
        Throwable th;
        AssetFileDescriptor assetFileDescriptor = null;
        this.s = false;
        this.a = uri;
        if (this.d != null) {
            o();
            a(null);
            mediaPlayer = this.d;
            a(d.IDLE);
        } else {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (uri.getScheme().equals("asset")) {
                try {
                    AssetFileDescriptor openFd = getContext().getAssets().openFd(uri.getPath().substring(1));
                    try {
                        mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                        if (openFd != null) {
                            try {
                                openFd.close();
                            } catch (IOException e2) {
                                new StringBuilder("Unable to close").append(e2);
                            }
                        }
                    } catch (SecurityException e3) {
                        e = e3;
                        assetFileDescriptor = openFd;
                        try {
                            new StringBuilder("Failed to open assets ").append(e);
                            a(d.ERROR);
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e22) {
                                    new StringBuilder("Unable to close").append(e22);
                                }
                            }
                            mediaPlayer.setLooping(false);
                            mediaPlayer.setOnBufferingUpdateListener(this);
                            mediaPlayer.setOnCompletionListener(this);
                            mediaPlayer.setOnErrorListener(this);
                            mediaPlayer.setOnInfoListener(this);
                            mediaPlayer.setOnPreparedListener(this);
                            mediaPlayer.setOnVideoSizeChangedListener(this);
                            mediaPlayer.setOnSeekCompleteListener(this);
                            mediaPlayer.prepareAsync();
                            this.d = mediaPlayer;
                            a(d.PREPARING);
                            setSurfaceTextureListener(this);
                            if (!isAvailable()) {
                                onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e4) {
                                    new StringBuilder("Unable to close").append(e4);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        assetFileDescriptor = openFd;
                        new StringBuilder("Failed to open assets ").append(e);
                        a(d.ERROR);
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        mediaPlayer.setLooping(false);
                        mediaPlayer.setOnBufferingUpdateListener(this);
                        mediaPlayer.setOnCompletionListener(this);
                        mediaPlayer.setOnErrorListener(this);
                        mediaPlayer.setOnInfoListener(this);
                        mediaPlayer.setOnPreparedListener(this);
                        mediaPlayer.setOnVideoSizeChangedListener(this);
                        mediaPlayer.setOnSeekCompleteListener(this);
                        mediaPlayer.prepareAsync();
                        this.d = mediaPlayer;
                        a(d.PREPARING);
                        setSurfaceTextureListener(this);
                        if (!isAvailable()) {
                            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        assetFileDescriptor = openFd;
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        throw th;
                    }
                } catch (SecurityException e6) {
                    e = e6;
                } catch (IOException e7) {
                    e = e7;
                }
            } else {
                mediaPlayer.setDataSource(uri.toString());
            }
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.prepareAsync();
            this.d = mediaPlayer;
            a(d.PREPARING);
        } catch (Exception e8) {
            a(d.ERROR);
            mediaPlayer.release();
            new StringBuilder("Cannot prepare media player with SurfaceTexture: ").append(e8);
        }
        setSurfaceTextureListener(this);
        if (!isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
