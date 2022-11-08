package com.microsoft.media;

import com.microsoft.media.NGCPcmHost.AudioRoute;
import com.microsoft.media.RtcPalEnvironment.AudioEndpoint;

public class Interop {
    public static AudioEndpoint SkypeToLyncEndpoint(AudioRoute route) {
        switch (route) {
            case EARPIECE:
                return AudioEndpoint.EARPIECE;
            case HEADSET_WITH_MIC:
                return AudioEndpoint.HEADSET_WITH_MIC;
            case HEADSET_WITHOUT_MIC:
                return AudioEndpoint.HEADSET_WITHOUT_MIC;
            case SPEAKER:
                return AudioEndpoint.SPEAKER;
            case BLUETOOTH:
                return AudioEndpoint.BLUETOOTH;
            default:
                return AudioEndpoint.NON_SPEAKER;
        }
    }
}
