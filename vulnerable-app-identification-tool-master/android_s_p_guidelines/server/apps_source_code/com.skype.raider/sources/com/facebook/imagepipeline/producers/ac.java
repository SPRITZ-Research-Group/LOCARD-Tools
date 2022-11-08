package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Video.Media;
import android.support.annotation.Nullable;
import com.facebook.common.f.a;
import com.facebook.common.internal.e;
import com.facebook.imagepipeline.c.g;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.image.d;
import com.facebook.imagepipeline.image.f;
import com.facebook.imagepipeline.k.b;
import java.util.Map;
import java.util.concurrent.Executor;

public final class ac implements aj<a<c>> {
    private final Executor a;
    private final ContentResolver b;

    public ac(Executor executor, ContentResolver contentResolver) {
        this.a = executor;
        this.b = contentResolver;
    }

    public final void a(Consumer<a<c>> consumer, ak producerContext) {
        am listener = producerContext.c();
        String requestId = producerContext.b();
        final b imageRequest = producerContext.a();
        final am amVar = listener;
        final String str = requestId;
        final ar cancellableProducerRunnable = new ar<a<c>>(this, consumer, listener, "VideoThumbnailProducer", requestId) {
            final /* synthetic */ ac e;

            protected final /* synthetic */ void b(Object obj) {
                a.c((a) obj);
            }

            protected final /* synthetic */ Object c() throws Exception {
                String a = this.e.a(imageRequest);
                if (a == null) {
                    return null;
                }
                int i;
                b bVar = imageRequest;
                if (bVar.d() > 96 || bVar.e() > 96) {
                    i = 1;
                } else {
                    i = 3;
                }
                Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(a, i);
                if (createVideoThumbnail == null) {
                    return null;
                }
                return a.a(new d(createVideoThumbnail, g.a(), f.a));
            }

            protected final /* synthetic */ Map c(Object obj) {
                boolean z;
                String str = "createdThumbnail";
                if (((a) obj) != null) {
                    z = true;
                } else {
                    z = false;
                }
                return e.a(str, String.valueOf(z));
            }

            protected final void a(Exception e) {
                super.a(e);
                amVar.a(str, "VideoThumbnailProducer", false);
            }
        };
        producerContext.a(new e(this) {
            final /* synthetic */ ac b;

            public final void a() {
                cancellableProducerRunnable.a();
            }
        });
        this.a.execute(cancellableProducerRunnable);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Nullable
    private String a(b imageRequest) {
        Uri uri = imageRequest.b();
        if (com.facebook.common.i.f.c(uri)) {
            return imageRequest.p().getPath();
        }
        if (!com.facebook.common.i.f.d(uri)) {
            return null;
        }
        String selection = null;
        String[] selectionArgs = null;
        if (VERSION.SDK_INT >= 19 && "com.android.providers.media.documents".equals(uri.getAuthority())) {
            String documentId = DocumentsContract.getDocumentId(uri);
            uri = Media.EXTERNAL_CONTENT_URI;
            selection = "_id=?";
            selectionArgs = new String[]{documentId.split(":")[1]};
        }
        Cursor cursor = this.b.query(uri, new String[]{"_data"}, selection, selectionArgs, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                    if (cursor == null) {
                        return string;
                    }
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (cursor == null) {
            return null;
        }
        cursor.close();
        return null;
    }
}
