package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import com.facebook.common.e.i;
import com.facebook.common.i.f;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import com.facebook.imageutils.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class x extends z implements ax<e> {
    private static final Class<?> a = x.class;
    private static final String[] b = new String[]{"_id", "_data"};
    private static final String[] c = new String[]{"_data"};
    private static final Rect d = new Rect(0, 0, 512, 384);
    private static final Rect e = new Rect(0, 0, 96, 96);
    private final ContentResolver f;

    public x(Executor executor, i pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.f = contentResolver;
    }

    public final boolean a(com.facebook.imagepipeline.common.e resizeOptions) {
        return ay.a(d.width(), d.height(), resizeOptions);
    }

    protected final e a(b imageRequest) throws IOException {
        Uri uri = imageRequest.b();
        if (f.f(uri)) {
            e cameraImage = a(uri, imageRequest.f());
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return null;
    }

    @Nullable
    private e a(Uri uri, com.facebook.imagepipeline.common.e resizeOptions) throws IOException {
        Cursor cursor = this.f.query(uri, b, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            String pathname = cursor.getString(cursor.getColumnIndex("_data"));
            if (resizeOptions != null) {
                e thumbnail = a(resizeOptions, cursor.getInt(cursor.getColumnIndex("_id")));
                if (thumbnail != null) {
                    thumbnail.c(a(pathname));
                    cursor.close();
                    return thumbnail;
                }
            }
            cursor.close();
            return null;
        } finally {
            cursor.close();
        }
    }

    private e a(com.facebook.imagepipeline.common.e resizeOptions, int imageId) throws IOException {
        int thumbnailKind;
        e eVar = null;
        if (ay.a(e.width(), e.height(), resizeOptions)) {
            thumbnailKind = 3;
        } else if (ay.a(d.width(), d.height(), resizeOptions)) {
            thumbnailKind = 1;
        } else {
            thumbnailKind = 0;
        }
        if (thumbnailKind != 0) {
            Cursor thumbnailCursor = null;
            try {
                thumbnailCursor = Thumbnails.queryMiniThumbnail(this.f, (long) imageId, thumbnailKind, c);
                if (thumbnailCursor != null) {
                    thumbnailCursor.moveToFirst();
                    if (thumbnailCursor.getCount() > 0) {
                        String thumbnailUri = thumbnailCursor.getString(thumbnailCursor.getColumnIndex("_data"));
                        if (new File(thumbnailUri).exists()) {
                            int i;
                            InputStream fileInputStream = new FileInputStream(thumbnailUri);
                            if (thumbnailUri == null) {
                                i = -1;
                            } else {
                                i = (int) new File(thumbnailUri).length();
                            }
                            eVar = b(fileInputStream, i);
                            if (thumbnailCursor != null) {
                                thumbnailCursor.close();
                            }
                        }
                    }
                    if (thumbnailCursor != null) {
                        thumbnailCursor.close();
                    }
                } else if (thumbnailCursor != null) {
                    thumbnailCursor.close();
                }
            } catch (Throwable th) {
                if (thumbnailCursor != null) {
                    thumbnailCursor.close();
                }
            }
        }
        return eVar;
    }

    protected final String a() {
        return "LocalContentUriThumbnailFetchProducer";
    }

    private static int a(String pathname) {
        int i = 0;
        if (pathname == null) {
            return i;
        }
        try {
            return c.a(new ExifInterface(pathname).getAttributeInt("Orientation", 1));
        } catch (Throwable ioe) {
            FLog.e(a, ioe, "Unable to retrieve thumbnail rotation for %s", pathname);
            return i;
        }
    }
}
