package com.facebook.internal;

import com.facebook.ai;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

final class ac {
    static JSONObject a(InputStream inputStream) throws IOException {
        if (inputStream.read() != 0) {
            return null;
        }
        int read;
        String str;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 3; i3++) {
            read = inputStream.read();
            if (read == -1) {
                ar.a(ai.CACHE, v.a, "readHeader: stream.read returned -1 while reading header size");
                return null;
            }
            i2 = (i2 << 8) + (read & 255);
        }
        byte[] bArr = new byte[i2];
        while (i < i2) {
            read = inputStream.read(bArr, i, i2 - i);
            if (read <= 0) {
                ai aiVar = ai.CACHE;
                str = v.a;
                StringBuilder stringBuilder = new StringBuilder("readHeader: stream.read stopped at ");
                stringBuilder.append(Integer.valueOf(i));
                stringBuilder.append(" when expected ");
                stringBuilder.append(i2);
                ar.a(aiVar, str, stringBuilder.toString());
                return null;
            }
            i += read;
        }
        try {
            Object nextValue = new JSONTokener(new String(bArr)).nextValue();
            if (nextValue instanceof JSONObject) {
                return (JSONObject) nextValue;
            }
            ai aiVar2 = ai.CACHE;
            str = v.a;
            StringBuilder stringBuilder2 = new StringBuilder("readHeader: expected JSONObject, got ");
            stringBuilder2.append(nextValue.getClass().getCanonicalName());
            ar.a(aiVar2, str, stringBuilder2.toString());
            return null;
        } catch (JSONException e) {
            throw new IOException(e.getMessage());
        }
    }
}
