package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.id3.a;

public interface b {
    public static final b a = new b() {
        public final boolean a(Format format) {
            String str = format.g;
            return MimeTypes.APPLICATION_ID3.equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str);
        }

        public final a b(Format format) {
            Object obj;
            String str = format.g;
            int hashCode = str.hashCode();
            if (hashCode != -1248341703) {
                if (hashCode != 1154383568) {
                    if (hashCode == 1652648887 && str.equals("application/x-scte35")) {
                        obj = 2;
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
                } else if (str.equals("application/x-emsg")) {
                    obj = 1;
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
            } else if (str.equals(MimeTypes.APPLICATION_ID3)) {
                obj = null;
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
            obj = -1;
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
