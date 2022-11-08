package com.google.android.exoplayer2.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Arrays;

@TargetApi(21)
public final class b {
    public static final b a = new b(new int[]{2}, 2);
    private final int[] b;
    private final int c;

    public static b a(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        if (registerReceiver == null || registerReceiver.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            return a;
        }
        return new b(registerReceiver.getIntArrayExtra("android.media.extra.ENCODINGS"), registerReceiver.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
    }

    private b(int[] supportedEncodings, int maxChannelCount) {
        if (supportedEncodings != null) {
            this.b = Arrays.copyOf(supportedEncodings, supportedEncodings.length);
            Arrays.sort(this.b);
        } else {
            this.b = new int[0];
        }
        this.c = maxChannelCount;
    }

    public final boolean a(int encoding) {
        return Arrays.binarySearch(this.b, encoding) >= 0;
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof b)) {
            return false;
        }
        b audioCapabilities = (b) other;
        if (Arrays.equals(this.b, audioCapabilities.b) && this.c == audioCapabilities.c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.c + (Arrays.hashCode(this.b) * 31);
    }

    public final String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.c + ", supportedEncodings=" + Arrays.toString(this.b) + "]";
    }
}
