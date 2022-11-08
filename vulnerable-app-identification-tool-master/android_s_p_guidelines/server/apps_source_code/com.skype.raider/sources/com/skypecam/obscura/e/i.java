package com.skypecam.obscura.e;

import android.graphics.RectF;
import android.hardware.Camera.Size;
import android.opengl.Matrix;
import java.util.List;

public final class i {
    public static final RectF a = new RectF(-1000.0f, -1000.0f, 1000.0f, 1000.0f);

    public static float a(float x, float min, float max) {
        if (x > max) {
            return max;
        }
        return x < min ? min : x;
    }

    public static void a(float[] matrix, float viewWidth, float viewHeight, float texWidth, float texHeight, int rotation, float zoom) {
        float tmp;
        switch (rotation) {
            case 1:
                Matrix.rotateM(matrix, 0, 270.0f, 0.0f, 0.0f, 1.0f);
                Matrix.translateM(matrix, 0, -1.0f, 0.0f, 0.0f);
                tmp = viewWidth;
                viewWidth = viewHeight;
                viewHeight = tmp;
                break;
            case 2:
                Matrix.rotateM(matrix, 0, 180.0f, 0.0f, 0.0f, 1.0f);
                Matrix.translateM(matrix, 0, -1.0f, -1.0f, 0.0f);
                break;
            case 3:
                Matrix.rotateM(matrix, 0, 90.0f, 0.0f, 0.0f, 1.0f);
                Matrix.translateM(matrix, 0, 0.0f, -1.0f, 0.0f);
                tmp = viewWidth;
                viewWidth = viewHeight;
                viewHeight = tmp;
                break;
        }
        float scale = Math.min(viewWidth / texWidth, viewHeight / texHeight);
        float scaleX = (scale * texWidth) / viewWidth;
        float scaleY = (scale * texHeight) / viewHeight;
        Matrix.translateM(matrix, 0, (1.0f - (scaleX / zoom)) / 2.0f, (1.0f - (scaleY / zoom)) / 2.0f, 0.0f);
        Matrix.scaleM(matrix, 0, scaleX / zoom, scaleY / zoom, 1.0f);
    }

    public static int a(int sensorRotation, boolean isFront) {
        if (isFront) {
            return (sensorRotation + 0) % 360;
        }
        return ((sensorRotation + 0) + 360) % 360;
    }

    public static Size a(List<Size> sizes) {
        Size result = null;
        for (Size size : sizes) {
            if (size.width <= 2048 && size.height <= 2048 && size.width != size.height) {
                if (result == null) {
                    result = size;
                } else if (size.width * size.height > result.width * result.height) {
                    result = size;
                }
            }
        }
        if (result != null || sizes.size() <= 0) {
            return result;
        }
        return (Size) sizes.get(sizes.size() - 1);
    }

    public static RectF a(float x, float y, int viewWidth, int viewHeight) {
        float touchSize = a.width();
        float x0 = ((x / ((float) viewWidth)) * touchSize) - (touchSize / 2.0f);
        float y0 = ((y / ((float) viewHeight)) * touchSize) - (touchSize / 2.0f);
        return new RectF((float) ((int) a(x0 - 37.5f, a.left, a.right - 37.5f)), (float) ((int) a(y0 - 37.5f, a.top, a.bottom - 37.5f)), (float) ((int) a(x0 + 37.5f, a.left + 37.5f, a.right)), (float) ((int) a(y0 + 37.5f, a.top + 37.5f, a.bottom)));
    }
}
