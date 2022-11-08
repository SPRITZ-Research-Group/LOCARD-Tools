package com.skype.slimcore.video;

import android.graphics.Matrix;
import android.graphics.Point;
import android.view.TextureView;

public class ViewScaleHelper {
    public static void a(TextureView view, int viewWidth, int viewHeight, int videoWidth, int videoHeight, boolean scaleToFit, Point offset) {
        if (view != null && view.isAvailable()) {
            float scale;
            float scaleWidth = ((float) viewWidth) / ((float) videoWidth);
            float scaleHeight = ((float) viewHeight) / ((float) videoHeight);
            Matrix matrix = view.getTransform(null);
            if (scaleToFit) {
                scale = Math.min(scaleWidth, scaleHeight);
            } else {
                scale = Math.max(scaleWidth, scaleHeight);
            }
            int sw = (int) (((float) videoWidth) * scale);
            int sh = (int) (((float) videoHeight) * scale);
            matrix.setScale(((float) sw) / ((float) viewWidth), ((float) sh) / ((float) viewHeight));
            if (scaleToFit) {
                matrix.postTranslate((float) ((viewWidth - sw) / 2), (float) ((viewHeight - sh) / 2));
            } else {
                float x = (float) (((viewWidth - sw) / 2) + offset.x);
                float y = (float) (((viewHeight - sh) / 2) + offset.y);
                float translateX = a(x, (float) (viewWidth - sw));
                float translateY = a(y, (float) (viewHeight - sh));
                matrix.postTranslate(translateX, translateY);
                offset.x = (int) (((float) offset.x) + (translateX - x));
                offset.y = (int) (((float) offset.y) + (translateY - y));
            }
            view.setTransform(matrix);
        }
    }

    private static float a(float value, float min) {
        return Math.min(Math.max(value, min), 0.0f);
    }
}
