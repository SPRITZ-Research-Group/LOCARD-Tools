package com.skype.callmonitor.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import com.facebook.common.b.i;
import com.facebook.common.f.a;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.c;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.e.b;
import com.skype.callmonitor.R;
import javax.annotation.Nullable;

public class AvatarHelper {

    public interface ImageDownloadCallback {
        void a(Bitmap bitmap);
    }

    public static void a(String imageUrl, final Context context, final ImageDownloadCallback fetchResult) {
        final c<a<com.facebook.imagepipeline.image.c>> dataSource = Fresco.b().a(com.facebook.imagepipeline.k.c.a(Uri.parse(imageUrl)).q(), (Object) context);
        dataSource.a(new b() {
            protected final void a(@Nullable Bitmap bitmap) {
                if (bitmap != null) {
                    fetchResult.a(AvatarHelper.b(context, bitmap));
                }
                if (dataSource != null) {
                    dataSource.h();
                }
            }

            protected final void f(c<a<com.facebook.imagepipeline.image.c>> dataSource) {
                if (dataSource != null) {
                    dataSource.h();
                }
                FLog.w("CallMonitorService", "Failed to fetch the image from network for call  monitor.");
            }
        }, i.b());
    }

    private static Bitmap b(Context context, Bitmap bitmap) {
        Resources resources = context.getResources();
        if (bitmap != null) {
            try {
                Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                Paint paint = new Paint();
                Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                RectF rectF = new RectF(rect);
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(android.support.v4.content.a.c(context, R.color.black));
                canvas.drawOval(rectF, paint);
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, paint);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(output, resources.getDimensionPixelSize(R.dimen.user_icon_width), resources.getDimensionPixelSize(R.dimen.user_icon_height), true);
                if (scaledBitmap == output) {
                    return scaledBitmap;
                }
                output.recycle();
                return scaledBitmap;
            } catch (Throwable e) {
                FLog.i("CallMonitorService", "Can't allocate memory for getting the round bitmap returnning null", e);
            }
        }
        return null;
    }
}
