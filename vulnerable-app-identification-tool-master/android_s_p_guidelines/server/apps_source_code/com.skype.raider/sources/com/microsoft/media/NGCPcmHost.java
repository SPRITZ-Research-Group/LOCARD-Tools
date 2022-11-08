package com.microsoft.media;

import android.content.Context;
import com.microsoft.dl.audio.AudioPlatform;
import com.microsoft.dl.audio.AudioPlatform.clientCallback;

public class NGCPcmHost {
    public static final int PCMIF_ERROR = 1;
    public static final int PCMIF_ERROR_NOT_INITIALIZED = 3;
    public static final int PCMIF_ERROR_UNKNOWN_DEVICE = 4;
    public static final int PCMIF_OK = 0;
    private static String TAG = "NGCPcmHost";
    private final boolean m_manageAudioManagerModes;

    public enum AudioRoute {
        EARPIECE,
        SPEAKER,
        BLUETOOTH,
        HEADSET_WITH_MIC,
        HEADSET_WITHOUT_MIC
    }

    public NGCPcmHost(Context ctx, boolean hasPhone, boolean manageModes, boolean clientRouting, clientCallback callback) {
        this.m_manageAudioManagerModes = manageModes;
        AudioPlatform.setAudioContext(ctx);
        AudioPlatform.updateClientCallback(clientRouting, callback);
    }

    public int SetRoute(String str) {
        AudioRoute route;
        int result = 3;
        if (str.equals("EARPIECE") || str.equals("0_1") || str.equals("1_1") || str.equals("2_1")) {
            route = AudioRoute.EARPIECE;
        } else if (str.equals("SPEAKER") || str.equals("0_2") || str.equals("1_2") || str.equals("2_2")) {
            route = AudioRoute.SPEAKER;
        } else if (str.equals("BLUETOOTH") || str.equals("0_3") || str.equals("1_3") || str.equals("2_3")) {
            route = AudioRoute.BLUETOOTH;
        } else if (str.equals("HEADSET_WITH_MIC") || str.equals("0_4") || str.equals("1_4") || str.equals("2_4")) {
            route = AudioRoute.HEADSET_WITH_MIC;
        } else if (!str.equals("HEADSET_WITHOUT_MIC") && !str.equals("0_5") && !str.equals("1_5") && !str.equals("2_5")) {
            return 4;
        } else {
            route = AudioRoute.HEADSET_WITHOUT_MIC;
        }
        if (RtcPalEnvironment.IsPalInitialized()) {
            if (this.m_manageAudioManagerModes) {
                AudioPlatform.setMode(3);
            }
            try {
                RtcPalEnvironment.setActiveEndpoint(Interop.SkypeToLyncEndpoint(route));
                result = 0;
            } catch (UnsatisfiedLinkError e) {
            }
        }
        return result;
    }
}
