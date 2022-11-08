package com.skype.audiomanager;

final class a {
    static String a(int stateValue) {
        switch (stateValue) {
            case 0:
                return "STATE_DISCONNECTED";
            case 1:
                return "STATE_CONNECTING";
            case 2:
                return "STATE_CONNECTED";
            case 3:
                return "STATE_DISCONNECTING";
            default:
                return "UNKNOWN";
        }
    }

    static String b(int stateValue) {
        switch (stateValue) {
            case 10:
                return "STATE_AUDIO_DISCONNECTED";
            case 11:
                return "STATE_NOT_PLAYING";
            case 12:
                return "STATE_NOT_PLAYING";
            default:
                return "UNKNOWN";
        }
    }

    static String c(int stateValue) {
        switch (stateValue) {
            case 10:
                return "STATE_PLAYING";
            case 11:
                return "STATE_NOT_PLAYING";
            default:
                return "UNKNOWN";
        }
    }

    static String d(int stateValue) {
        switch (stateValue) {
            case 0:
                return "STATE_DISCONNECTED";
            case 1:
                return "STATE_CONNECTED";
            case 2:
                return "STATE_CONNECTING";
            default:
                return "UNKNOWN";
        }
    }
}
