package com.brentvatne.react;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.webkit.CookieManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.microsoft.skype.a.a.b;
import com.yqritc.scalablevideoview.ScalableVideoView;
import com.yqritc.scalablevideoview.c;
import com.yqritc.scalablevideoview.d;
import com.yqritc.scalablevideoview.e;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReactVideoView extends ScalableVideoView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, v {
    private boolean A = false;
    private final com.microsoft.skype.a.a c = com.microsoft.skype.a.a.a("VideoViewQueue", b.DEFAULT);
    private ae d;
    private RCTEventEmitter e;
    private final Random f = new Random();
    private Handler g = new Handler();
    private Runnable h = null;
    private String i = null;
    private String j = "mp4";
    private boolean k = false;
    private boolean l = false;
    private c m = c.LEFT_TOP;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private float r = 1.0f;
    private float s = 1.0f;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private int x = 0;
    private int y = 0;
    private int z = 0;

    public enum a {
        EVENT_LOAD_START("onVideoLoadStart"),
        EVENT_LOAD("onVideoLoad"),
        EVENT_DISPLAY_READY("onReadyForDisplay"),
        EVENT_ERROR("onVideoError"),
        EVENT_PROGRESS("onVideoProgress"),
        EVENT_SEEK("onVideoSeek"),
        EVENT_END("onVideoEnd"),
        EVENT_WAITING("onVideoWaiting"),
        EVENT_PLAYING("onVideoPlaying");
        
        private final String j;

        private a(String name) {
            this.j = name;
        }

        public final String toString() {
            return this.j;
        }
    }

    public ReactVideoView(ae themedReactContext) {
        super(themedReactContext);
        this.d = themedReactContext;
        this.e = (RCTEventEmitter) themedReactContext.a(RCTEventEmitter.class);
        themedReactContext.a(this);
        f();
        setSurfaceTextureListener(this);
        this.h = new Runnable(this) {
            final /* synthetic */ ReactVideoView a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (this.a.v && this.a.a != null) {
                    int duration = this.a.k ? this.a.y : this.a.x;
                    this.a.z = this.a.a.getCurrentPosition();
                    ar event = new WritableNativeMap();
                    event.putDouble("currentTime", ((double) this.a.z) / 1000.0d);
                    event.putDouble("playableDuration", ((double) duration) / 1000.0d);
                    this.a.e.receiveEvent(this.a.getId(), a.EVENT_PROGRESS.toString(), event);
                    if (!this.a.p && !this.a.A) {
                        this.a.g.postDelayed(this.a.h, 250);
                    }
                }
            }
        };
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed && this.v) {
            int videoWidth = c();
            int videoHeight = b();
            if (videoWidth != 0 && videoHeight != 0) {
                Matrix matrix = new d(new e(getWidth(), getHeight()), new e(videoWidth, videoHeight)).a(this.b);
                if (matrix != null) {
                    setTransform(matrix);
                }
            }
        }
    }

    private void f() {
        if (this.a == null) {
            FLog.i("ReactVideoView", "initializing media player");
            this.v = false;
            this.a = new MediaPlayer();
            this.a.setScreenOnWhilePlaying(true);
            this.a.setOnVideoSizeChangedListener(this);
            this.a.setOnErrorListener(this);
            this.a.setOnPreparedListener(this);
            this.a.setOnBufferingUpdateListener(this);
            this.a.setOnCompletionListener(this);
            this.a.setOnInfoListener(this);
        }
    }

    public void setSrc(String uriString, String type, boolean isNetwork, boolean isAsset, String authToken, boolean shouldRedirectForHLS, String clientVersion, String mediaProvider) {
        final int causeId = this.f.nextInt();
        FLog.i("ReactVideoView", "setSrc with causeId " + causeId);
        this.i = uriString;
        this.j = type;
        this.k = isNetwork;
        this.l = isAsset;
        this.v = false;
        this.x = 0;
        this.y = 0;
        f();
        this.a.reset();
        if (isNetwork) {
            final String str = uriString;
            final String str2 = type;
            final boolean z = isNetwork;
            final Runnable anonymousClass2 = new Runnable(this) {
                final /* synthetic */ ReactVideoView e;

                public final void run() {
                    FLog.i("ReactVideoView", "prepareNetworkVideoAction completion block (causeId " + causeId + ")");
                    this.e.a(str, str2, z);
                    this.e.a(this.e);
                }
            };
            final a<String> completionPrepare = new a<String>(this) {
                final /* synthetic */ ReactVideoView c;

                public final /* synthetic */ void a(Object obj) {
                    String str = (String) obj;
                    FLog.i("ReactVideoView", "prepareNetworkVideoAction call (causeId " + causeId + ")");
                    Uri parse = Uri.parse(str);
                    Builder buildUpon = parse.buildUpon();
                    Map hashMap = new HashMap();
                    String cookie = CookieManager.getInstance().getCookie(buildUpon.build().toString());
                    if (cookie != null) {
                        FLog.i("ReactVideoView", "setSrc isNetwork w/cookie (causeId " + causeId + ")");
                        hashMap.put("Cookie", cookie);
                    }
                    try {
                        this.c.setDataSource(this.c.d, parse, hashMap);
                        anonymousClass2.run();
                    } catch (IOException e) {
                        FLog.w("ReactVideoView", "prepareNetworkVideoAction call IOException " + e.getLocalizedMessage() + " (causeId " + causeId + ")");
                    }
                }
            };
            if (shouldRedirectForHLS) {
                FLog.i("ReactVideoView", "setSrc Will run network video runnable on queue (causeId " + causeId + ")");
                str = uriString;
                str2 = authToken;
                final String str3 = clientVersion;
                final String str4 = mediaProvider;
                this.c.d(new Runnable(this) {
                    final /* synthetic */ ReactVideoView g;

                    public final void run() {
                        FLog.i("ReactVideoView", "setSrc Network runnable on queue (causeId " + causeId + ")");
                        ReactVideoView.a(str, str2, str3, str4, completionPrepare, causeId);
                    }
                });
                return;
            }
            FLog.i("ReactVideoView", "setSrc Will run network video completion in line (causeId " + causeId + ")");
            completionPrepare.a(uriString);
            return;
        }
        if (isAsset) {
            try {
                if (uriString.startsWith("content://")) {
                    FLog.i("ReactVideoView", "setSrc isAsset w/content (causeId " + causeId + ")");
                    setDataSource(this.d, Uri.parse(uriString));
                } else {
                    FLog.i("ReactVideoView", "setSrc isAsset wo/content (causeId " + causeId + ")");
                    setDataSource(uriString);
                }
            } catch (Throwable e) {
                FLog.e("ReactVideoView", e, "Exception " + e.getLocalizedMessage(), new Object[0]);
                a(1, -1);
                return;
            }
        }
        FLog.i("ReactVideoView", "setSrc raw (causeId " + causeId + ")");
        int id = this.d.getResources().getIdentifier(uriString, "raw", this.d.getPackageName());
        if (id == 0) {
            FLog.e("ReactVideoView", "video not found for local path: " + uriString);
            a(1, -1004);
            return;
        }
        setRawData(id);
        a(uriString, type, isNetwork);
        a((OnPreparedListener) this);
    }

    public void setResizeModeModifier(c resizeMode) {
        this.m = resizeMode;
        if (this.v) {
            setScalableType(resizeMode);
            invalidate();
        }
    }

    public void setRepeatModifier(boolean repeat) {
        this.n = repeat;
        if (this.v) {
            setLooping(repeat);
        }
    }

    public void setPausedModifier(boolean paused) {
        this.o = paused;
        a(paused);
    }

    private void a(boolean paused) {
        this.p = paused;
        if (!this.v) {
            return;
        }
        if (!this.p) {
            if (!this.a.isPlaying()) {
                e();
            }
            g();
        } else if (this.a.isPlaying()) {
            d();
        }
    }

    public void setMutedModifier(boolean muted) {
        this.q = muted;
        if (!this.v) {
            return;
        }
        if (this.q) {
            setVolume(0.0f, 0.0f);
        } else {
            setVolume(this.r, this.r);
        }
    }

    public void setVolumeModifier(float volume) {
        this.r = volume;
        setMutedModifier(this.q);
    }

    public void setRateModifier(float rate) {
        this.s = rate;
        if (this.v) {
            FLog.e("ReactVideoView", "Setting playback rate is not yet supported on Android");
        }
    }

    public void setPlayInBackground(boolean playInBackground) {
        this.t = playInBackground;
    }

    public void setResumeAfterForeground(boolean resume) {
        this.u = resume;
    }

    public void onPrepared(MediaPlayer mp) {
        FLog.i("ReactVideoView", "onPrepared");
        this.v = true;
        this.w = true;
        this.x = mp.getDuration();
        ar loadEvent = new WritableNativeMap();
        loadEvent.putDouble("duration", ((double) this.x) / 1000.0d);
        loadEvent.putDouble("currentTime", ((double) mp.getCurrentPosition()) / 1000.0d);
        loadEvent.putBoolean("canPlayFastForward", true);
        loadEvent.putBoolean("canPlaySlowForward", true);
        loadEvent.putBoolean("canPlaySlowReverse", true);
        loadEvent.putBoolean("canPlayReverse", true);
        loadEvent.putBoolean("canPlayFastForward", true);
        loadEvent.putBoolean("canStepBackward", true);
        loadEvent.putBoolean("canStepForward", true);
        this.e.receiveEvent(getId(), a.EVENT_LOAD.toString(), loadEvent);
        this.e.receiveEvent(getId(), a.EVENT_DISPLAY_READY.toString(), new WritableNativeMap());
        setResizeModeModifier(this.m);
        setRepeatModifier(this.n);
        a(this.p);
        setMutedModifier(this.q);
        g();
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        FLog.e("ReactVideoView", "onError " + what + " " + extra);
        a(what, extra);
        return true;
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        this.y = (int) Math.round(((double) (this.x * percent)) / 100.0d);
    }

    public final void a(int msec) {
        int causeId = this.f.nextInt();
        FLog.i("ReactVideoView", "seekTo " + msec + " with causeId " + causeId);
        if (this.v) {
            super.a(msec);
            if (this.A && this.x != 0 && msec < this.x) {
                this.A = false;
            }
            ar event = new WritableNativeMap();
            event.putDouble("currentTime", ((double) a()) / 1000.0d);
            event.putDouble("seekTime", ((double) msec) / 1000.0d);
            this.e.receiveEvent(getId(), a.EVENT_SEEK.toString(), event);
            g();
            return;
        }
        FLog.w("ReactVideoView", "seekTo wo/valid player (causeId " + causeId + ")");
    }

    public void onCompletion(MediaPlayer mp) {
        FLog.i("ReactVideoView", "onCompletion");
        this.A = true;
        this.e.receiveEvent(getId(), a.EVENT_END.toString(), null);
    }

    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case 701:
                this.e.receiveEvent(getId(), a.EVENT_WAITING.toString(), null);
                break;
            case 702:
                this.e.receiveEvent(getId(), a.EVENT_PLAYING.toString(), null);
                break;
        }
        return true;
    }

    protected void onDetachedFromWindow() {
        FLog.i("ReactVideoView", "onDetachedFromWindow");
        this.w = this.v;
        this.v = false;
        super.onDetachedFromWindow();
    }

    protected void onAttachedToWindow() {
        FLog.i("ReactVideoView", "onAttachedToWindow");
        super.onAttachedToWindow();
        this.v = this.w;
        f();
        g();
    }

    public void onHostPause() {
        if (this.a != null && !this.t) {
            FLog.i("ReactVideoView", "onHostPause");
            a(true);
            if (this.v) {
                this.z = this.a.getCurrentPosition();
            }
        }
    }

    public void onHostResume() {
        if (this.a != null && !this.t) {
            FLog.i("ReactVideoView", "onHostResume");
            if (this.v) {
                this.a.seekTo(this.z);
            }
            if (!this.o && !this.u) {
                a(true);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        FLog.i("ReactVideoView", "onSurfaceTextureDestroyed");
        if (this.a != null) {
            this.a.setSurface(null);
        }
        return false;
    }

    public void onHostDestroy() {
        this.d.b(this);
    }

    private void g() {
        this.g.post(this.h);
    }

    private void a(String uriString, String type, boolean isNetwork) {
        ar src = new WritableNativeMap();
        src.putString(ReactVideoViewManager.PROP_SRC_URI, uriString);
        src.putString("type", type);
        src.putBoolean(ReactVideoViewManager.PROP_SRC_IS_NETWORK, isNetwork);
        ar event = new WritableNativeMap();
        event.putMap(ReactVideoViewManager.PROP_SRC, src);
        this.e.receiveEvent(getId(), a.EVENT_LOAD_START.toString(), event);
    }

    private void a(int what, int extra) {
        ar error = new WritableNativeMap();
        error.putInt("what", what);
        error.putInt("extra", extra);
        ar event = new WritableNativeMap();
        event.putMap("error", error);
        this.e.receiveEvent(getId(), a.EVENT_ERROR.toString(), event);
    }

    static /* synthetic */ void a(String x1, String x2, String x3, String x4, a x5, int x6) {
        try {
            Object obj;
            FLog.i("ReactVideoView", "getRedirectUri " + x1);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(x1).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            FLog.i("ReactVideoView", "getRedirectUri w/mediaProvider " + (x4 != null ? x4 : "null") + " (causeId " + x6 + ")");
            if (x4 != null && x4.equals("AzureMS")) {
                if (x2 != null) {
                    FLog.i("ReactVideoView", "getRedirectUri w/authToken (causeId " + x6 + ")");
                    httpURLConnection.addRequestProperty("X-Skypetoken", x2);
                }
                if (x3 != null) {
                    FLog.i("ReactVideoView", "getRedirectUri w/clientVersion " + x3 + " (causeId " + x6 + ")");
                    httpURLConnection.addRequestProperty("X-Client-Version", x3);
                }
            }
            String headerField = httpURLConnection.getHeaderField("Location");
            int responseCode = httpURLConnection.getResponseCode();
            FLog.i("ReactVideoView", "getRedirectUri result " + responseCode + " " + headerField);
            httpURLConnection.disconnect();
            if (responseCode == 301 || responseCode == 302) {
                obj = headerField;
            } else {
                String obj2 = x1;
            }
            x5.a(obj2);
        } catch (IOException e) {
            FLog.w("ReactVideoView", "getRedirectUri IOException " + e.getLocalizedMessage());
            x5.a(x1);
        }
    }
}
