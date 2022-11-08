package com.google.android.exoplayer2.b;

import com.google.android.exoplayer2.b.d.b;

public interface c {
    public static final c a = new c() {
        public final a a(String mimeType, boolean requiresSecureDecoder) throws b {
            return d.a(mimeType, requiresSecureDecoder);
        }

        public final a a() throws b {
            return d.a();
        }
    };

    a a() throws b;

    a a(String str, boolean z) throws b;
}
