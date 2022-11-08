package com.facebook.react.modules.camera;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64OutputStream;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.j;
import com.facebook.react.module.annotations.ReactModule;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;

@ReactModule(name = "ImageStoreManager")
public class ImageStoreManager extends ReactContextBaseJavaModule {
    private static final int BUFFER_SIZE = 8192;

    private class a extends j<Void, Void> {
        final /* synthetic */ ImageStoreManager a;
        private final String b;
        private final d c;
        private final d d;

        /* synthetic */ a(ImageStoreManager x0, ai x1, String x2, d x3, d x4, byte b) {
            this(x0, x1, x2, x3, x4);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected final /* synthetic */ void a() {
            try {
                Closeable openInputStream = this.a.getReactApplicationContext().getContentResolver().openInputStream(Uri.parse(this.b));
                Closeable base64OutputStream = new Base64OutputStream(new ByteArrayOutputStream(), 0);
                byte[] bArr = new byte[ImageStoreManager.BUFFER_SIZE];
                while (true) {
                    try {
                        int read = openInputStream.read(bArr);
                        if (read < 0) {
                            break;
                        }
                        base64OutputStream.write(bArr, 0, read);
                    } catch (IOException e) {
                        this.d.invoke(e.getMessage());
                    } finally {
                        ImageStoreManager.closeQuietly(openInputStream);
                        ImageStoreManager.closeQuietly(base64OutputStream);
                    }
                }
            } catch (FileNotFoundException e2) {
                this.d.invoke(e2.getMessage());
            }
        }

        private a(ImageStoreManager imageStoreManager, ai reactContext, String uri, d success, d error) {
            this.a = imageStoreManager;
            super(reactContext);
            this.b = uri;
            this.c = success;
            this.d = error;
        }
    }

    public ImageStoreManager(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ImageStoreManager";
    }

    @ReactMethod
    public void getBase64ForTag(String uri, d success, d error) {
        new a(this, getReactApplicationContext(), uri, success, error, (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }
}
