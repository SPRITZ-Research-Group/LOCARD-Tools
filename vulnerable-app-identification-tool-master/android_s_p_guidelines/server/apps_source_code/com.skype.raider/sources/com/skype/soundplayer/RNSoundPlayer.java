package com.skype.soundplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.ag;

public abstract class RNSoundPlayer {
    private static int f = 1;
    protected final String a;
    protected final RNSoundType b;
    protected final int c;
    protected final b d;
    protected final a e;

    interface b {
        void a(@Nullable RNSoundPlayer rNSoundPlayer, @Nullable RNSoundPlayerException rNSoundPlayerException);
    }

    interface a {
        void a(@NonNull RNSoundPlayer rNSoundPlayer);
    }

    interface c {
        void a(@NonNull RNSoundPlayer rNSoundPlayer, int i, int i2);
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(int i, int i2);

    public abstract void a(boolean z, @Nullable c cVar, int i);

    public abstract void b(int i);

    public abstract int d();

    public static RNSoundPlayer a(@NonNull ag context, @NonNull String sound, @NonNull RNSoundType soundType, @NonNull b preparedCallback, @Nullable a completionCallback, int causeId) {
        if (soundType == RNSoundType.SYSTEM) {
            return new RNPoolSoundPlayer(context, sound, soundType, preparedCallback, completionCallback, causeId);
        }
        return new RNMediaSoundPlayer(context, sound, soundType, preparedCallback, completionCallback, causeId);
    }

    protected RNSoundPlayer(@NonNull String sound, @NonNull RNSoundType soundType, @NonNull b preparedCallback, @Nullable a completionCallback) {
        this.a = sound;
        this.b = soundType;
        int i = f;
        f = i + 1;
        this.c = i;
        this.d = preparedCallback;
        this.e = completionCallback;
    }

    public final int f() {
        return this.c;
    }

    protected static int a(@NonNull Context context, String sound) {
        String packageName = context.getPackageName();
        int lastIndexOf = sound.lastIndexOf(".");
        if (lastIndexOf > 0) {
            sound = sound.substring(0, lastIndexOf);
        }
        return context.getResources().getIdentifier(sound.toLowerCase(), "raw", packageName);
    }
}
