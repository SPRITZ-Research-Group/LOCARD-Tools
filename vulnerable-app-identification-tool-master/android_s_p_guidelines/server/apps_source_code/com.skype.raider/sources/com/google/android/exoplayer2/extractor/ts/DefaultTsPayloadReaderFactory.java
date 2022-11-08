package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.ts.t.b;
import com.google.android.exoplayer2.extractor.ts.t.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DefaultTsPayloadReaderFactory implements c {
    private final int a;
    private final List<Format> b;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    public DefaultTsPayloadReaderFactory(int flags) {
        this(flags, Collections.emptyList());
    }

    private DefaultTsPayloadReaderFactory(int flags, List<Format> closedCaptionFormats) {
        this.a = flags;
        if (!a(32) && closedCaptionFormats.isEmpty()) {
            closedCaptionFormats = Collections.singletonList(Format.a(null, "application/cea-608", 0, null, null));
        }
        this.b = closedCaptionFormats;
    }

    public final SparseArray<t> a() {
        return new SparseArray();
    }

    public final t a(int streamType, b esInfo) {
        switch (streamType) {
            case 2:
                return new n(new h());
            case 3:
            case 4:
                return new n(new l(esInfo.b));
            case 15:
                if (a(2)) {
                    return null;
                }
                return new n(new d(false, esInfo.b));
            case 21:
                return new n(new k());
            case 27:
                if (a(4)) {
                    return null;
                }
                return new n(new i(a(esInfo), a(1), a(8)));
            case 36:
                return new n(new j(a(esInfo)));
            case 89:
                return new n(new f(esInfo.c));
            case 129:
            case 135:
                return new n(new b(esInfo.b));
            case 130:
            case 138:
                return new n(new e(esInfo.b));
            case 134:
                if (a(16)) {
                    return null;
                }
                return new q(new s());
            default:
                return null;
        }
    }

    private r a(b esInfo) {
        if (a(32)) {
            return new r(this.b);
        }
        k scratchDescriptorData = new k(esInfo.d);
        List<Format> closedCaptionFormats = this.b;
        while (scratchDescriptorData.b() > 0) {
            int nextDescriptorPosition = scratchDescriptorData.d() + scratchDescriptorData.g();
            if (scratchDescriptorData.g() == 134) {
                closedCaptionFormats = new ArrayList();
                int numberOfServices = scratchDescriptorData.g() & 31;
                for (int i = 0; i < numberOfServices; i++) {
                    int i2;
                    String mimeType;
                    int accessibilityChannel;
                    String language = scratchDescriptorData.e(3);
                    int captionTypeByte = scratchDescriptorData.g();
                    if ((captionTypeByte & 128) != 0) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    if (i2 != 0) {
                        mimeType = "application/cea-708";
                        accessibilityChannel = captionTypeByte & 63;
                    } else {
                        mimeType = "application/cea-608";
                        accessibilityChannel = 1;
                    }
                    closedCaptionFormats.add(Format.a(null, mimeType, 0, language, accessibilityChannel));
                    scratchDescriptorData.d(2);
                }
            }
            scratchDescriptorData.c(nextDescriptorPosition);
        }
        return new r(closedCaptionFormats);
    }

    private boolean a(int flag) {
        return (this.a & flag) != 0;
    }
}
