package com.facebook.ads.internal.p.b;

import android.text.TextUtils;
import com.adjust.sdk.Constants;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class d {
    private static final Pattern d = Pattern.compile("[R,r]ange:[ ]?bytes=(\\d*)-");
    private static final Pattern e = Pattern.compile("GET /(.*) HTTP");
    public final String a;
    public final long b;
    public final boolean c;

    private d(String str) {
        j.a(str);
        Matcher matcher = d.matcher(str);
        long parseLong = matcher.find() ? Long.parseLong(matcher.group(1)) : -1;
        this.b = Math.max(0, parseLong);
        this.c = parseLong >= 0;
        matcher = e.matcher(str);
        if (matcher.find()) {
            this.a = matcher.group(1);
            return;
        }
        throw new IllegalArgumentException("Invalid request `" + str + "`: url not found!");
    }

    public static d a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Constants.ENCODING));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            Object readLine = bufferedReader.readLine();
            if (TextUtils.isEmpty(readLine)) {
                return new d(stringBuilder.toString());
            }
            stringBuilder.append(readLine).append(10);
        }
    }

    public final String toString() {
        return "GetRequest{rangeOffset=" + this.b + ", partial=" + this.c + ", uri='" + this.a + '\'' + '}';
    }
}
