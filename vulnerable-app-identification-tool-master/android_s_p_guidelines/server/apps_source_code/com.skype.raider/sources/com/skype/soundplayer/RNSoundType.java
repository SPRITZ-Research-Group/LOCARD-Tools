package com.skype.soundplayer;

import android.support.annotation.NonNull;
import java.util.Locale;

public enum RNSoundType {
    DEFAULT(0),
    MEDIA(1),
    RINGTONE(2),
    SYSTEM(3);
    
    public final int e;

    private RNSoundType(int value) {
        this.e = value;
    }

    @NonNull
    public static RNSoundType a(int value) {
        switch (value) {
            case 0:
                return DEFAULT;
            case 1:
                return MEDIA;
            case 2:
                return RINGTONE;
            case 3:
                return SYSTEM;
            default:
                throw new RuntimeException(String.format(Locale.US, "Unexpected SoundType type %d", new Object[]{Integer.valueOf(value)}));
        }
    }
}
