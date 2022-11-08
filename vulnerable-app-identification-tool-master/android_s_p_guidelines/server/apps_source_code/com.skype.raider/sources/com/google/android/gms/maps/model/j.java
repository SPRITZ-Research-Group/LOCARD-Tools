package com.google.android.gms.maps.model;

import com.skype.Defines;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class j implements i {
    private final int a = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
    private final int c = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;

    public abstract URL a(int i, int i2, int i3);

    public final Tile b(int i, int i2, int i3) {
        URL a = a(i, i2, i3);
        try {
            int i4 = this.a;
            int i5 = this.c;
            InputStream openStream = a.openStream();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = openStream.read(bArr);
                if (read == -1) {
                    return new Tile(i4, i5, byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            return null;
        }
    }
}
