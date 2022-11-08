package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import java.util.Map;

@TargetApi(16)
public interface a<T extends c> {

    public static class a extends Exception {
        public a(Exception e) {
            super(e);
        }
    }

    boolean a(String str);

    int b();

    T c();

    a d();

    Map<String, String> e();
}
