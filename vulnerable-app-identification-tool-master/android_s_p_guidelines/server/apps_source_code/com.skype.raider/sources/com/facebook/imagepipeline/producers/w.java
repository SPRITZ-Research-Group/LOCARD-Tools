package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.facebook.common.e.i;
import com.facebook.common.i.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public final class w extends z {
    private static final String[] a = new String[]{"_id", "_data"};
    private final ContentResolver b;

    public w(Executor executor, i pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.b = contentResolver;
    }

    protected final e a(b imageRequest) throws IOException {
        Uri uri = imageRequest.b();
        if (f.e(uri)) {
            InputStream inputStream;
            if (uri.toString().endsWith("/photo")) {
                inputStream = this.b.openInputStream(uri);
            } else if (uri.toString().endsWith("/display_photo")) {
                try {
                    inputStream = this.b.openAssetFileDescriptor(uri, "r").createInputStream();
                } catch (IOException e) {
                    throw new IOException("Contact photo does not exist: " + uri);
                }
            } else {
                inputStream = Contacts.openContactPhotoInputStream(this.b, uri);
                if (inputStream == null) {
                    throw new IOException("Contact photo does not exist: " + uri);
                }
            }
            return b(inputStream, -1);
        }
        if (f.f(uri)) {
            e cameraImage = a(uri);
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return b(this.b.openInputStream(uri), -1);
    }

    @Nullable
    private e a(Uri uri) throws IOException {
        e eVar = null;
        Cursor cursor = this.b.query(uri, a, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    String pathname = cursor.getString(cursor.getColumnIndex("_data"));
                    if (pathname != null) {
                        int i;
                        InputStream fileInputStream = new FileInputStream(pathname);
                        if (pathname == null) {
                            i = -1;
                        } else {
                            i = (int) new File(pathname).length();
                        }
                        eVar = b(fileInputStream, i);
                        cursor.close();
                    } else {
                        cursor.close();
                    }
                }
            } finally {
                cursor.close();
            }
        }
        return eVar;
    }

    protected final String a() {
        return "LocalContentUriFetchProducer";
    }
}
