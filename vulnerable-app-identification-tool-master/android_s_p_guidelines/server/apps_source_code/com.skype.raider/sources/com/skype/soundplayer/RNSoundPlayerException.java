package com.skype.soundplayer;

public class RNSoundPlayerException extends Exception {
    public RNSoundPlayerException(String message) {
        super(message);
    }

    public RNSoundPlayerException(Exception ex) {
        super(ex);
    }
}
