package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import defpackage.abc;
import defpackage.ahd;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class f {
    public static ImageType a(List<ImageHeaderParser> list, InputStream inputStream, abc abc) throws IOException {
        if (inputStream == null) {
            return ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new ahd(inputStream, abc);
        }
        inputStream.mark(5242880);
        int i = 0;
        int size = list.size();
        while (i < size) {
            try {
                ImageType a = ((ImageHeaderParser) list.get(i)).a(inputStream);
                if (a != ImageType.UNKNOWN) {
                    inputStream.reset();
                    return a;
                }
                inputStream.reset();
                i++;
            } catch (Throwable th) {
                inputStream.reset();
                throw th;
            }
        }
        return ImageType.UNKNOWN;
    }

    public static int b(List<ImageHeaderParser> list, InputStream inputStream, abc abc) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new ahd(inputStream, abc);
        }
        inputStream.mark(5242880);
        int i = 0;
        int size = list.size();
        while (i < size) {
            try {
                int a = ((ImageHeaderParser) list.get(i)).a(inputStream, abc);
                if (a != -1) {
                    inputStream.reset();
                    return a;
                }
                inputStream.reset();
                i++;
            } catch (Throwable th) {
                inputStream.reset();
                throw th;
            }
        }
        return -1;
    }
}
