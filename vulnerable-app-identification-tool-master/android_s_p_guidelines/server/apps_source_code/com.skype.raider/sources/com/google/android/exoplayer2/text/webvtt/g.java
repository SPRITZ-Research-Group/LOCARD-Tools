package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.text.e;
import java.util.regex.Pattern;

public final class g {
    private static final Pattern a = Pattern.compile("^NOTE(( |\t).*)?$");
    private static final Pattern b = Pattern.compile("^﻿?WEBVTT(( |\t).*)?$");

    public static void a(k input) throws e {
        String line = input.x();
        if (line == null || !b.matcher(line).matches()) {
            throw new e("Expected WEBVTT. Got " + line);
        }
    }

    public static long a(String timestamp) throws NumberFormatException {
        int i = 0;
        long value = 0;
        String[] parts = timestamp.split("\\.", 2);
        String[] split = parts[0].split(":");
        while (i < split.length) {
            value = (60 * value) + Long.parseLong(split[i]);
            i++;
        }
        return ((value * 1000) + Long.parseLong(parts[1])) * 1000;
    }

    public static float b(String s) throws NumberFormatException {
        if (s.endsWith("%")) {
            return Float.parseFloat(s.substring(0, s.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }
}
