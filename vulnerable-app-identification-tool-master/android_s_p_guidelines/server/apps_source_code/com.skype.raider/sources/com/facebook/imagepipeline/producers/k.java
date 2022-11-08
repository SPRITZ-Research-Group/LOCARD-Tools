package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.util.Base64;
import com.facebook.common.b.a;
import com.facebook.common.e.i;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public final class k extends z {
    public k(i pooledByteBufferFactory) {
        super(a.a(), pooledByteBufferFactory);
    }

    protected final e a(b imageRequest) throws IOException {
        boolean equals;
        byte[] data;
        String uri = imageRequest.b().toString();
        h.a(uri.substring(0, 5).equals("data:"));
        int indexOf = uri.indexOf(44);
        String substring = uri.substring(indexOf + 1, uri.length());
        uri = uri.substring(0, indexOf);
        if (uri.contains(";")) {
            String[] split = uri.split(";");
            equals = split[split.length - 1].equals("base64");
        } else {
            equals = false;
        }
        if (equals) {
            data = Base64.decode(substring, 0);
        } else {
            data = Uri.decode(substring).getBytes();
        }
        return a(new ByteArrayInputStream(data), data.length);
    }

    protected final String a() {
        return "DataFetchProducer";
    }
}
