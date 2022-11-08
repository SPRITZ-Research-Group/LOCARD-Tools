package com.skype.soundplayer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes.Builder;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.an;
import com.microsoft.skype.a.a.b;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RNMediaSoundPlayer extends RNSoundPlayer implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener {
    private static final com.microsoft.skype.a.a f = com.microsoft.skype.a.a.a("RNMediaSoundPlayerQueue", b.DEFAULT);
    private final MediaPlayer g;
    private boolean h = false;
    private boolean i = false;
    private final Set<b> j = new HashSet();
    private int k;
    private c l;
    private final Handler m = new Handler(Looper.getMainLooper());

    private class a implements Runnable {
        final /* synthetic */ RNMediaSoundPlayer a;

        private a(RNMediaSoundPlayer rNMediaSoundPlayer) {
            this.a = rNMediaSoundPlayer;
        }

        /* synthetic */ a(RNMediaSoundPlayer x0, byte b) {
            this(x0);
        }

        public final void run() {
            if (this.a.l != null && this.a.g != null) {
                if (this.a.h && (this.a.k != 100 || this.a.g.isPlaying())) {
                    this.a.l.a(this.a, this.a.c(), this.a.b());
                }
                this.a.m.postDelayed(this, 1000);
            }
        }
    }

    static /* synthetic */ void a(RNMediaSoundPlayer x0, RNSoundPlayerException x1) {
        an.a(com.microsoft.skype.a.a.a(f), "Must execute on soundQueue");
        x0.h = false;
        x0.i = false;
        for (b a : x0.j) {
            a.a(null, x1);
        }
        x0.j.clear();
    }

    protected RNMediaSoundPlayer(@NonNull ag context, @NonNull String sound, @NonNull RNSoundType soundType, @NonNull b preparedCallback, @Nullable a completionCallback, int causeId) {
        Uri soundUri;
        String error;
        super(sound, soundType, preparedCallback, completionCallback);
        Map<String, String> headers = null;
        boolean urlResource = URLUtil.isValidUrl(sound);
        if (urlResource) {
            FLog.i("RNMediaSoundPlayer", "'%s' is a valid URL (causeId %x)", (Object) sound, Integer.valueOf(causeId));
            soundUri = Uri.parse(sound);
            String cookie = CookieManager.getInstance().getCookie(sound);
            if (cookie != null) {
                headers = new HashMap();
                headers.put("Cookie", cookie);
            }
        } else {
            FLog.i("RNMediaSoundPlayer", "Attempting to load '%s' as a bundled resource (causeId %x)", (Object) sound, Integer.valueOf(causeId));
            int resourceId = RNSoundPlayer.a((Context) context, sound);
            if (resourceId == 0) {
                this.g = null;
                error = String.format("Failed to load %s (causeId %x)", new Object[]{g(), Integer.valueOf(causeId)});
                preparedCallback.a(null, new RNSoundPlayerException(error));
                FLog.e("RNMediaSoundPlayer", error);
                return;
            }
            soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + resourceId);
        }
        this.g = a(context, soundUri, headers, soundType, causeId);
        if (this.g == null) {
            error = String.format("Failed to load %s (causeId %x)", new Object[]{g(), Integer.valueOf(causeId)});
            preparedCallback.a(null, new RNSoundPlayerException(error));
            FLog.e("RNMediaSoundPlayer", error);
            return;
        }
        if (!urlResource) {
            this.k = 100;
        }
        a(preparedCallback);
        FLog.i("RNMediaSoundPlayer", "Initialized %s (causeId: %x)", g(), Integer.valueOf(causeId));
    }

    private MediaPlayer a(@NonNull ag context, @NonNull Uri uri, @Nullable Map<String, String> headers, @NonNull RNSoundType soundType, int causeId) {
        int i = 1;
        FLog.i("RNMediaSoundPlayer", "createMediaPlayer %s %s (causeId %x)", (Object) uri, (Object) soundType, Integer.valueOf(causeId));
        MediaPlayer mp = new MediaPlayer();
        int i2 = Integer.MIN_VALUE;
        Activity j = context.j();
        if (j != null) {
            i2 = j.getVolumeControlStream();
        }
        if (VERSION.SDK_INT < 21) {
            a(mp, soundType, i2, causeId);
        } else {
            Object obj;
            switch (soundType) {
                case RINGTONE:
                    if (i2 != 0) {
                        i = 6;
                        break;
                    }
                    i = 2;
                    break;
            }
            String str = "RNMediaSoundPlayer";
            String str2 = "configureMediaPlayerNew setting AudioAttributes.usage to %s (current stream is %s) (causeId %x)";
            switch (i) {
                case 0:
                    obj = "USAGE_UNKNOWN";
                    break;
                case 1:
                    obj = "USAGE_MEDIA";
                    break;
                case 2:
                    obj = "USAGE_VOICE_COMMUNICATION";
                    break;
                case 3:
                    obj = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                    break;
                case 4:
                    obj = "USAGE_ALARM";
                    break;
                case 5:
                    obj = "USAGE_NOTIFICATION";
                    break;
                case 6:
                    obj = "USAGE_NOTIFICATION_RINGTONE";
                    break;
                case 7:
                    obj = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                    break;
                case 8:
                    obj = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                    break;
                case 9:
                    obj = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                    break;
                case 10:
                    obj = "USAGE_NOTIFICATION_EVENT";
                    break;
                case 11:
                    obj = "USAGE_ASSISTANCE_ACCESSIBILITY";
                    break;
                case 12:
                    obj = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                    break;
                case 13:
                    obj = "USAGE_ASSISTANCE_SONIFICATION";
                    break;
                case 14:
                    obj = "USAGE_GAME";
                    break;
                default:
                    obj = "UNKNOWN";
                    break;
            }
            FLog.i(str, str2, obj, c(i2), Integer.valueOf(causeId));
            mp.setAudioAttributes(new Builder().setContentType(4).setUsage(i).build());
            a(mp, soundType, i2, causeId);
        }
        mp.setOnErrorListener(this);
        mp.setOnCompletionListener(this);
        mp.setOnInfoListener(this);
        mp.setOnPreparedListener(this);
        mp.setOnBufferingUpdateListener(this);
        try {
            mp.setDataSource(context, uri, headers);
            return mp;
        } catch (Throwable ex) {
            FLog.e("RNMediaSoundPlayer", "Failed to configure MediaPlayer", ex);
            return null;
        }
    }

    private static void a(@NonNull MediaPlayer mp, @NonNull RNSoundType soundType, int currentStreamType, int causeId) {
        int stream;
        switch (soundType) {
            case MEDIA:
                stream = 3;
                break;
            case RINGTONE:
                if (currentStreamType != 0) {
                    stream = 2;
                    break;
                } else {
                    stream = 0;
                    break;
                }
            default:
                stream = Integer.MIN_VALUE;
                break;
        }
        FLog.i("RNMediaSoundPlayer", "configureMediaPlayerStream setting MediaPlayer.audioStreamType to %s (current stream is %s) (causeId %x)", c(stream), c(currentStreamType), Integer.valueOf(causeId));
        mp.setAudioStreamType(stream);
    }

    private static String c(int audioStream) {
        switch (audioStream) {
            case Integer.MIN_VALUE:
                return "USE_DEFAULT_STREAM_TYPE";
            case 0:
                return "VOICE_CALL";
            case 1:
                return "SYSTEM";
            case 2:
                return "RING";
            case 3:
                return "MUSIC";
            case 4:
                return "ALARM";
            case 5:
                return "NOTIFICATION";
            case 8:
                return "DTMF";
            default:
                return "UNKNOWN";
        }
    }

    private void a(@Nullable final b onPrepared) {
        f.c(new Runnable(this) {
            final /* synthetic */ RNMediaSoundPlayer b;

            public final void run() {
                if (!this.b.h) {
                    if (onPrepared != null) {
                        this.b.j.add(onPrepared);
                    }
                    if (!this.b.i) {
                        this.b.i = true;
                        try {
                            this.b.g.prepareAsync();
                        } catch (Exception e) {
                            if (e instanceof IOException) {
                                RNMediaSoundPlayer.a(this.b, new RNSoundPlayerException(e));
                                return;
                            }
                            throw e;
                        }
                    }
                } else if (onPrepared != null) {
                    onPrepared.a(this.b, null);
                }
            }
        });
    }

    public final void a(final boolean loop, @Nullable final c progress, final int causeId) {
        a(new b(this) {
            final /* synthetic */ RNMediaSoundPlayer d;

            public final void a(@Nullable RNSoundPlayer player, @Nullable RNSoundPlayerException error) {
                an.a(com.microsoft.skype.a.a.a(RNMediaSoundPlayer.f), "Must execute on soundQueue");
                if (error != null) {
                    FLog.w("RNMediaSoundPlayer", "Ignoring play %s - prepare failed (causeId %x)", this.d.g(), Integer.valueOf(causeId));
                    return;
                }
                if (progress != null) {
                    this.d.l = progress;
                    this.d.m.post(new a(this.d, (byte) 0));
                }
                if (this.d.g.isPlaying()) {
                    FLog.i("RNMediaSoundPlayer", "Ignoring play %s - already playing (causeId %x)", this.d.g(), Integer.valueOf(causeId));
                    return;
                }
                FLog.i("RNMediaSoundPlayer", "Playing %s%s%s (causeId %x)", this.d.g(), loop ? " (looping)" : "", progress != null ? " (with updates)" : "", Integer.valueOf(causeId));
                this.d.g.setVolume(1.0f, 1.0f);
                this.d.g.setLooping(loop);
                this.d.g.start();
            }
        });
    }

    public final void a(final int causeId) {
        f.b(new Runnable(this) {
            final /* synthetic */ RNMediaSoundPlayer b;

            public final void run() {
                if (!this.b.h) {
                    FLog.e("RNMediaSoundPlayer", "Failed to pause %s - sound was not prepared (causeId %x)", this.b.g(), Integer.valueOf(causeId));
                } else if (this.b.g.isPlaying()) {
                    FLog.i("RNMediaSoundPlayer", "Pausing %s(causeId %x)", this.b.g(), Integer.valueOf(causeId));
                    this.b.g.pause();
                } else {
                    FLog.i("RNMediaSoundPlayer", "Ignoring pause %s - not playing (causeId %x)", this.b.g(), Integer.valueOf(causeId));
                }
            }
        });
    }

    public final void b(final int causeId) {
        f.b(new Runnable(this) {
            final /* synthetic */ RNMediaSoundPlayer b;

            public final void run() {
                if (this.b.h) {
                    FLog.i("RNMediaSoundPlayer", "Stopping %s (causeId %x)", this.b.g(), Integer.valueOf(causeId));
                    this.b.l = null;
                    this.b.g.stop();
                    this.b.h = false;
                    return;
                }
                FLog.i("RNMediaSoundPlayer", "Ignoring stop %s - sound was not prepared (causeId %x)", this.b.g(), Integer.valueOf(causeId));
            }
        });
    }

    public final void a(final int position, final int causeId) {
        f.b(new Runnable(this) {
            final /* synthetic */ RNMediaSoundPlayer c;

            public final void run() {
                if (this.c.h) {
                    FLog.i("RNMediaSoundPlayer", "Seeking %s to %d (causeId %x)", this.c.g(), Integer.valueOf(position), Integer.valueOf(causeId));
                    this.c.g.seekTo((int) TimeUnit.SECONDS.toMillis((long) position));
                    return;
                }
                FLog.e("RNMediaSoundPlayer", "Failed to seek %s - sound was not prepared (causeId %x)", this.c.g(), Integer.valueOf(causeId));
            }
        });
    }

    public final void a() {
        f.b(new Runnable(this) {
            final /* synthetic */ RNMediaSoundPlayer a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.h = false;
                this.a.i = false;
                this.a.j.clear();
                this.a.g.release();
            }
        });
    }

    public final int b() {
        return (int) TimeUnit.MILLISECONDS.toSeconds((long) ((int) (((float) this.g.getDuration()) * (((float) this.k) / 100.0f))));
    }

    public final int c() {
        return (int) TimeUnit.MILLISECONDS.toSeconds((long) this.g.getCurrentPosition());
    }

    public final int d() {
        return (int) TimeUnit.MILLISECONDS.toSeconds((long) this.g.getDuration());
    }

    private String g() {
        return String.format("[token: %d name: %s]", new Object[]{Integer.valueOf(this.c), this.a});
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        if (a("onBufferingUpdate", mp)) {
            FLog.v("RNMediaSoundPlayer", "onBufferingUpdate %s %%%d", g(), Integer.valueOf(percent));
            this.k = percent;
        }
    }

    public void onCompletion(MediaPlayer mp) {
        if (a("onCompletion", mp)) {
            FLog.i("RNMediaSoundPlayer", "onCompletion %s", g());
            f.c(new Runnable(this) {
                final /* synthetic */ RNMediaSoundPlayer a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    if (this.a.e != null) {
                        this.a.e.a(this.a);
                    }
                }
            });
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        String whatString;
        switch (what) {
            case 1:
                whatString = "MEDIA_ERROR_UNKNOWN";
                break;
            case 100:
                whatString = "MEDIA_ERROR_SERVER_DIED";
                break;
            default:
                whatString = String.format("Unknown <%d>", new Object[]{Integer.valueOf(what)});
                break;
        }
        final String extraString = d(extra);
        FLog.e("RNMediaSoundPlayer", "onError %s w=%s e=%s", g(), whatString, extraString);
        f.c(new Runnable(this) {
            final /* synthetic */ RNMediaSoundPlayer c;

            public final void run() {
                RNMediaSoundPlayer.a(this.c, new RNSoundPlayerException(String.format("Failed to prepare %s w=%s e=%s", new Object[]{this.c.g(), whatString, extraString})));
            }
        });
        return true;
    }

    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        com.microsoft.skype.b.a<String, Integer> whatAndLevel;
        switch (what) {
            case 1:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_UNKNOWN", Integer.valueOf(5));
                break;
            case 3:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_VIDEO_RENDERING_START", Integer.valueOf(4));
                break;
            case 700:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_VIDEO_TRACK_LAGGING", Integer.valueOf(5));
                break;
            case 701:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_BUFFERING_START", Integer.valueOf(4));
                break;
            case 702:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_BUFFERING_END", Integer.valueOf(4));
                break;
            case 800:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_BAD_INTERLEAVING", Integer.valueOf(5));
                break;
            case 801:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_NOT_SEEKABLE", Integer.valueOf(5));
                break;
            case 802:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_METADATA_UPDATE", Integer.valueOf(4));
                break;
            case 901:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_UNSUPPORTED_SUBTITLE", Integer.valueOf(5));
                break;
            case 902:
                whatAndLevel = com.microsoft.skype.b.a.a("MEDIA_INFO_SUBTITLE_TIMED_OUT", Integer.valueOf(5));
                break;
            default:
                whatAndLevel = com.microsoft.skype.b.a.a(String.format("Unknown <%d>", new Object[]{Integer.valueOf(what)}), Integer.valueOf(4));
                break;
        }
        String extraString = d(extra);
        String message = String.format("onInfo %s w=%s e=%s", new Object[]{g(), whatAndLevel.a, extraString});
        if (((Integer) whatAndLevel.b).intValue() == 5) {
            FLog.w("RNMediaSoundPlayer", message);
        } else {
            FLog.i("RNMediaSoundPlayer", message);
        }
        return false;
    }

    public void onPrepared(MediaPlayer mp) {
        if (a("onPrepared", mp)) {
            FLog.i("RNMediaSoundPlayer", "onPrepared %s", g());
            f.c(new Runnable(this) {
                final /* synthetic */ RNMediaSoundPlayer a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    RNMediaSoundPlayer.j(this.a);
                }
            });
        }
    }

    private boolean a(String event, MediaPlayer player) {
        if (this.g == player) {
            return true;
        }
        String str = "RNMediaSoundPlayer";
        FLog.e(str, String.format("Received event %s for unexpected MediaPlayer %s", new Object[]{event, this.g}));
        return false;
    }

    @NonNull
    private static String d(int extra) {
        switch (extra) {
            case -1010:
                return "MEDIA_ERROR_UNSUPPORTED";
            case -1007:
                return "MEDIA_ERROR_MALFORMED";
            case -1004:
                return "MEDIA_ERROR_IO";
            case -110:
                return "MEDIA_ERROR_TIMED_OUT";
            default:
                return String.format("Unknown <%d>", new Object[]{Integer.valueOf(extra)});
        }
    }

    static /* synthetic */ void j(RNMediaSoundPlayer x0) {
        an.a(com.microsoft.skype.a.a.a(f), "Must execute on soundQueue");
        x0.h = true;
        for (b a : x0.j) {
            a.a(x0, null);
        }
        x0.j.clear();
    }
}
