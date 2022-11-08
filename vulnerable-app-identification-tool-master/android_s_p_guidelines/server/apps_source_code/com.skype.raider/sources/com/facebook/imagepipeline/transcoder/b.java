package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.c;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.e;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

public interface b {
    boolean canResize(e eVar, @Nullable RotationOptions rotationOptions, @Nullable com.facebook.imagepipeline.common.e eVar2);

    boolean canTranscode(c cVar);

    String getIdentifier();

    a transcode(e eVar, OutputStream outputStream, @Nullable RotationOptions rotationOptions, @Nullable com.facebook.imagepipeline.common.e eVar2, @Nullable c cVar, @Nullable Integer num) throws IOException;
}
