package com.skype.soundplayer;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;

public class RNPoolSoundPlayer extends RNSoundPlayer {
    private static final a f = a.a("RNPoolSoundPlayerQueue", b.DEFAULT);
    private static SoundPool g;
    private static final SparseArray<RNPoolSoundPlayer> h = new SparseArray();
    private static final OnLoadCompleteListener i = new OnLoadCompleteListener() {
        public final void onLoadComplete(SoundPool soundPool, final int sampleId, final int status) {
            RNPoolSoundPlayer.f.b(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 c;

                public final void run() {
                    RNPoolSoundPlayer player = (RNPoolSoundPlayer) RNPoolSoundPlayer.h.get(sampleId);
                    if (player == null) {
                        FLog.w("SoundPool.OnLoadCompleteListener", "Received SoundPool.onLoadComplete for unknown sound ID %d", Integer.valueOf(sampleId));
                        return;
                    }
                    if (status == 0) {
                        FLog.i("SoundPool.OnLoadCompleteListener", "Successfully loaded sound %s", player.h());
                        player.d.a(player, null);
                    } else {
                        String error = String.format("Failed to load sound %s - %d", new Object[]{player.h(), Integer.valueOf(status)});
                        FLog.e("SoundPool.OnLoadCompleteListener", error);
                        player.d.a(null, new RNSoundPlayerException(error));
                    }
                    RNPoolSoundPlayer.h.remove(sampleId);
                }
            });
        }
    };
    private int j = 0;
    private int k = 0;

    protected RNPoolSoundPlayer(@NonNull final ag context, @NonNull String sound, @NonNull RNSoundType soundType, @NonNull b preparedCallback, @Nullable a completionCallback, final int causeId) {
        super(sound, soundType, preparedCallback, completionCallback);
        final int resourceId = RNSoundPlayer.a((Context) context, sound);
        if (resourceId == 0) {
            String error = String.format("Failed to load %s (causeId %x)", new Object[]{h(), Integer.valueOf(causeId)});
            preparedCallback.a(null, new RNSoundPlayerException(error));
            FLog.e("RNPoolSoundPlayer", error);
            return;
        }
        f.b(new Runnable(this) {
            final /* synthetic */ RNPoolSoundPlayer d;

            public final void run() {
                this.d.j = RNPoolSoundPlayer.g().load(context, resourceId, 1);
                RNPoolSoundPlayer.h.put(this.d.j, this.d);
                FLog.i("RNPoolSoundPlayer", "Initialized %s (causeId: %x)", this.d.h(), Integer.valueOf(causeId));
            }
        });
    }

    private static SoundPool g() {
        if (g == null) {
            SoundPool soundPool;
            if (VERSION.SDK_INT < 21) {
                soundPool = new SoundPool(1, 5, 0);
            } else {
                soundPool = new Builder().setAudioAttributes(new AudioAttributes.Builder().setContentType(4).setUsage(13).build()).setMaxStreams(1).build();
            }
            g = soundPool;
            soundPool.setOnLoadCompleteListener(i);
        }
        return g;
    }

    public final void a(boolean loop, @Nullable c progress, int causeId) {
        FLog.i("RNPoolSoundPlayer", "Playing %s (causeId %x)", h(), Integer.valueOf(causeId));
        this.k = g().play(this.j, 1.0f, 1.0f, 0, 0, 1.0f);
        if (this.k == 0) {
            FLog.e("RNPoolSoundPlayer", "Failed to play %s (causeId %x)", h(), Integer.valueOf(causeId));
        }
    }

    public final void a(int causeId) {
        if (this.k != 0) {
            FLog.i("RNPoolSoundPlayer", "Pausing %s(causeId %x)", h(), Integer.valueOf(causeId));
            g().pause(this.k);
        }
    }

    public final void b(int causeId) {
        if (this.k != 0) {
            FLog.i("RNPoolSoundPlayer", "Stopping %s (causeId %x)", h(), Integer.valueOf(causeId));
            g().stop(this.k);
            this.k = 0;
        }
    }

    public final void a(int position, int causeId) {
        FLog.w("RNPoolSoundPlayer", "Seek is not supported! %s (causeId %x)", h(), Integer.valueOf(causeId));
    }

    public final void a() {
        g().unload(this.j);
    }

    public final int d() {
        return 0;
    }

    private String h() {
        return String.format("[token: %d soundId: %d name: %s]", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.j), this.a});
    }
}
