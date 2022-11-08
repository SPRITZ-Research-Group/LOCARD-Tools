package com.skype.android.jipc.omx.message;

import android.os.Build.VERSION;

public enum OmxMessageType {
    EVENT(16, 12),
    EMPTY_BUFFER_DONE(16, 4);
    
    private static int g;
    final int e;
    final int f;

    static {
        int i;
        EVENT = new OmxMessageType("EVENT", 0, 16, 12);
        EMPTY_BUFFER_DONE = new OmxMessageType("EMPTY_BUFFER_DONE", 1, 16, 4);
        String str = "FILL_BUFFER_DONE";
        if (VERSION.SDK_INT < 21) {
            i = 8;
        } else {
            i = 0;
        }
        c = new OmxMessageType(str, 2, 16, i + 24);
        d = new OmxMessageType("FRAME_RENDERED", 3, 23, 16);
        h = new OmxMessageType[]{EVENT, EMPTY_BUFFER_DONE, c, d};
    }

    private OmxMessageType(int apiLevel, int size) {
        this.e = apiLevel;
        this.f = size;
    }

    public static int a() {
        if (g == 0) {
            for (OmxMessageType type : values()) {
                Object obj;
                if (VERSION.SDK_INT >= type.e) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null && g < type.f) {
                    g = type.f;
                }
            }
        }
        return g;
    }
}
