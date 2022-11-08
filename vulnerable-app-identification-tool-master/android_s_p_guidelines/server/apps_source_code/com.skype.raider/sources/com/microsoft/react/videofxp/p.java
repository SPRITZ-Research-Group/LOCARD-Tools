package com.microsoft.react.videofxp;

import com.facebook.react.bridge.am;

final class p {
    q a = q.SD;
    double b = 0.2d;
    int c = 0;
    boolean d = false;
    int e = 640;
    int f = 480;
    int g = 96000;
    int h = 1100000;

    p(am map) {
        int profileIndex;
        if (map.hasKey("profile")) {
            profileIndex = map.getInt("profile");
        } else {
            profileIndex = 0;
        }
        if (profileIndex >= q.values().length) {
            profileIndex = 0;
        }
        this.a = q.values()[profileIndex];
        this.b = map.hasKey("minSizeDecreaseRatio") ? map.getDouble("minSizeDecreaseRatio") : this.b;
        this.c = map.hasKey("maxFileSize") ? map.getInt("maxFileSize") : this.c;
        this.d = map.hasKey("highQualitySource") ? map.getBoolean("highQualitySource") : this.d;
        this.e = map.hasKey("customWidth") ? map.getInt("customWidth") : this.e;
        this.f = map.hasKey("customHeight") ? map.getInt("customHeight") : this.f;
        this.g = map.hasKey("customAudioBitrate") ? map.getInt("customAudioBitrate") : this.g;
        this.h = map.hasKey("customVideoBitrate") ? map.getInt("customVideoBitrate") : this.h;
    }

    final int a() {
        switch (this.a) {
            case Custom:
                return this.g;
            default:
                return 96000;
        }
    }

    final int b() {
        switch (this.a) {
            case HD:
                return 2000000;
            case Custom:
                return this.h;
            default:
                return 1300000;
        }
    }
}
