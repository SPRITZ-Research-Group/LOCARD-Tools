package com.microsoft.tokenshare;

import android.support.annotation.NonNull;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class m {
    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    static void a(@NonNull InputStream inputStream, @NonNull OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[65000];
        int bytesRead = inputStream.read(buffer);
        while (bytesRead > 0) {
            outputStream.write(buffer, 0, bytesRead);
            bytesRead = inputStream.read(buffer);
        }
    }
}
