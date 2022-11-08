package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.id3.a;

public interface c {
    public static final c a = new c() {
        public final boolean a(Format format) {
            String mimeType = format.f;
            return "application/id3".equals(mimeType) || "application/x-emsg".equals(mimeType) || "application/x-scte35".equals(mimeType);
        }

        public final a b(Format format) {
            String str = format.f;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1248341703:
                    if (str.equals("application/id3")) {
                        obj = null;
                        break;
                    }
                    break;
                case 1154383568:
                    if (str.equals("application/x-emsg")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1652648887:
                    if (str.equals("application/x-scte35")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return new a();
                case 1:
                    return new com.google.android.exoplayer2.metadata.emsg.a();
                case 2:
                    return new com.google.android.exoplayer2.metadata.scte35.a();
                default:
                    throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
            }
        }
    };

    boolean a(Format format);

    a b(Format format);
}
