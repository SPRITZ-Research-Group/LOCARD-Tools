package com.skypecam.obscura.a;

import android.util.SparseArray;

enum g {
    CONFIGURE,
    START,
    STOP,
    RELEASE,
    ENCODE_CREATE_SURFACE,
    ENCODE_UNMIRROR,
    ENCODE_SURFACE_FRAME,
    ENCODE_PREVIEW_FRAME,
    ENCODE_REQUEST_KEY_FRAME,
    DEQUEUE_BUFFER,
    QUEUE_BUFFER,
    RETURN_BUFFER,
    WRITE_SAMPLE,
    CHANGE_MEDIA_FORMAT,
    DECODE_FRAME,
    ENCODE_DECODER_FRAME,
    EXTRACT_SAMPLE,
    END_OF_STREAM;
    
    private static SparseArray<g> s;

    static {
        s = new SparseArray();
        g[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            g c = values[i];
            s.put(c.ordinal(), c);
            i++;
        }
    }

    public static g a(int value) {
        return (g) s.get(value);
    }
}
