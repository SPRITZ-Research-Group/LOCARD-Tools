package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.a.c;
import com.google.android.exoplayer2.text.ttml.a;
import com.google.android.exoplayer2.text.webvtt.b;

public interface f {
    public static final f a = new f() {
        public final boolean a(Format format) {
            String mimeType = format.f;
            return "text/vtt".equals(mimeType) || "application/ttml+xml".equals(mimeType) || "application/x-mp4-vtt".equals(mimeType) || "application/x-subrip".equals(mimeType) || "application/x-quicktime-tx3g".equals(mimeType) || "application/cea-608".equals(mimeType) || "application/x-mp4-cea-608".equals(mimeType) || "application/cea-708".equals(mimeType) || "application/dvbsubs".equals(mimeType);
        }

        public final d b(Format format) {
            String str = format.f;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals("application/dvbsubs")) {
                        obj = 8;
                        break;
                    }
                    break;
                case -1026075066:
                    if (str.equals("application/x-mp4-vtt")) {
                        obj = 1;
                        break;
                    }
                    break;
                case -1004728940:
                    if (str.equals("text/vtt")) {
                        obj = null;
                        break;
                    }
                    break;
                case 691401887:
                    if (str.equals("application/x-quicktime-tx3g")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 930165504:
                    if (str.equals("application/x-mp4-cea-608")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1566015601:
                    if (str.equals("application/cea-608")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 1566016562:
                    if (str.equals("application/cea-708")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 1668750253:
                    if (str.equals("application/x-subrip")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1693976202:
                    if (str.equals("application/ttml+xml")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return new com.google.android.exoplayer2.text.webvtt.f();
                case 1:
                    return new b();
                case 2:
                    return new a();
                case 3:
                    return new com.google.android.exoplayer2.text.c.a();
                case 4:
                    return new com.google.android.exoplayer2.text.d.a(format.h);
                case 5:
                case 6:
                    return new com.google.android.exoplayer2.text.a.a(format.f, format.z);
                case 7:
                    return new c(format.z);
                case 8:
                    return new com.google.android.exoplayer2.text.b.a(format.h);
                default:
                    throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
            }
        }
    };

    boolean a(Format format);

    d b(Format format);
}
