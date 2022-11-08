package com.google.android.exoplayer2.drm;

import android.util.Pair;
import java.util.Map;

public final class h {
    public static Pair<Long, Long> a(a<?> drmSession) {
        Map<String, String> keyStatus = drmSession.e();
        return new Pair(Long.valueOf(a(keyStatus, "LicenseDurationRemaining")), Long.valueOf(a(keyStatus, "PlaybackDurationRemaining")));
    }

    private static long a(Map<String, String> keyStatus, String property) {
        if (keyStatus != null) {
            try {
                String value = (String) keyStatus.get(property);
                if (value != null) {
                    return Long.parseLong(value);
                }
            } catch (NumberFormatException e) {
            }
        }
        return -9223372036854775807L;
    }
}
