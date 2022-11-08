package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.c;
import com.facebook.imagepipeline.nativecode.b;
import javax.annotation.Nullable;

public final class e implements c {
    private final int a;
    private final boolean b;
    @Nullable
    private final c c;
    @Nullable
    private final Integer d;

    public e(int maxBitmapSize, boolean useDownSamplingRatio, @Nullable c primaryImageTranscoderFactory, @Nullable Integer imageTranscoderType) {
        this.a = maxBitmapSize;
        this.b = useDownSamplingRatio;
        this.c = primaryImageTranscoderFactory;
        this.d = imageTranscoderType;
    }

    public final b a(c imageFormat, boolean isResizingEnabled) {
        b imageTranscoder;
        if (this.c == null) {
            imageTranscoder = null;
        } else {
            imageTranscoder = this.c.a(imageFormat, isResizingEnabled);
        }
        if (imageTranscoder == null) {
            if (this.d == null) {
                imageTranscoder = null;
            } else {
                switch (this.d.intValue()) {
                    case 0:
                        imageTranscoder = b(imageFormat, isResizingEnabled);
                        break;
                    case 1:
                        imageTranscoder = c(imageFormat, isResizingEnabled);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid ImageTranscoderType");
                }
            }
        }
        if (imageTranscoder == null) {
            imageTranscoder = b(imageFormat, isResizingEnabled);
        }
        return imageTranscoder == null ? c(imageFormat, isResizingEnabled) : imageTranscoder;
    }

    @Nullable
    private b b(c imageFormat, boolean isResizingEnabled) {
        return new b(this.a, this.b).a(imageFormat, isResizingEnabled);
    }

    private b c(c imageFormat, boolean isResizingEnabled) {
        return new g(this.a).a(imageFormat, isResizingEnabled);
    }
}
