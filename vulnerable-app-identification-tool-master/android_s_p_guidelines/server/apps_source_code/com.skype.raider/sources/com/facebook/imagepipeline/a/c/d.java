package com.facebook.imagepipeline.a.c;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;

public final class d {
    private final com.facebook.imagepipeline.a.a.a a;
    private final a b;
    private final Paint c = new Paint();

    public interface a {
        com.facebook.common.f.a<Bitmap> a(int i);
    }

    private enum b {
        REQUIRED,
        NOT_REQUIRED,
        SKIP,
        ABORT
    }

    public d(com.facebook.imagepipeline.a.a.a animatedDrawableBackend, a callback) {
        this.a = animatedDrawableBackend;
        this.b = callback;
        this.c.setColor(0);
        this.c.setStyle(Style.FILL);
        this.c.setXfermode(new PorterDuffXfermode(Mode.SRC));
    }

    public final void a(int frameNumber, Bitmap bitmap) {
        int nextIndex;
        com.facebook.imagepipeline.a.a.b frameInfo;
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0, Mode.SRC);
        if (a(frameNumber)) {
            nextIndex = frameNumber;
        } else {
            nextIndex = a(frameNumber - 1, canvas);
        }
        for (int index = nextIndex; index < frameNumber; index++) {
            frameInfo = this.a.a(index);
            com.facebook.imagepipeline.a.a.b.b disposalMethod = frameInfo.g;
            if (disposalMethod != com.facebook.imagepipeline.a.a.b.b.DISPOSE_TO_PREVIOUS) {
                if (frameInfo.f == com.facebook.imagepipeline.a.a.b.a.NO_BLEND) {
                    a(canvas, frameInfo);
                }
                this.a.a(index, canvas);
                if (disposalMethod == com.facebook.imagepipeline.a.a.b.b.DISPOSE_TO_BACKGROUND) {
                    a(canvas, frameInfo);
                }
            }
        }
        frameInfo = this.a.a(frameNumber);
        if (frameInfo.f == com.facebook.imagepipeline.a.a.b.a.NO_BLEND) {
            a(canvas, frameInfo);
        }
        this.a.a(frameNumber, canvas);
    }

    private int a(int previousFrameNumber, Canvas canvas) {
        for (int index = previousFrameNumber; index >= 0; index--) {
            b neededResult;
            com.facebook.imagepipeline.a.a.b a = this.a.a(index);
            com.facebook.imagepipeline.a.a.b.b bVar = a.g;
            if (bVar == com.facebook.imagepipeline.a.a.b.b.DISPOSE_DO_NOT) {
                neededResult = b.REQUIRED;
            } else if (bVar == com.facebook.imagepipeline.a.a.b.b.DISPOSE_TO_BACKGROUND) {
                if (a(a)) {
                    neededResult = b.NOT_REQUIRED;
                } else {
                    neededResult = b.REQUIRED;
                }
            } else if (bVar == com.facebook.imagepipeline.a.a.b.b.DISPOSE_TO_PREVIOUS) {
                neededResult = b.SKIP;
            } else {
                neededResult = b.ABORT;
            }
            switch (neededResult) {
                case REQUIRED:
                    com.facebook.imagepipeline.a.a.b frameInfo = this.a.a(index);
                    com.facebook.common.f.a<Bitmap> startBitmap = this.b.a(index);
                    if (startBitmap == null) {
                        if (!a(index)) {
                            break;
                        }
                        return index;
                    }
                    try {
                        canvas.drawBitmap((Bitmap) startBitmap.a(), 0.0f, 0.0f, null);
                        if (frameInfo.g == com.facebook.imagepipeline.a.a.b.b.DISPOSE_TO_BACKGROUND) {
                            a(canvas, frameInfo);
                        }
                        index++;
                        startBitmap.close();
                        return index;
                    } catch (Throwable th) {
                        startBitmap.close();
                    }
                case NOT_REQUIRED:
                    return index + 1;
                case ABORT:
                    return index;
                default:
                    break;
            }
        }
        return 0;
    }

    private void a(Canvas canvas, com.facebook.imagepipeline.a.a.b frameInfo) {
        canvas.drawRect((float) frameInfo.b, (float) frameInfo.c, (float) (frameInfo.b + frameInfo.d), (float) (frameInfo.c + frameInfo.e), this.c);
    }

    private boolean a(int index) {
        if (index == 0) {
            return true;
        }
        com.facebook.imagepipeline.a.a.b currFrameInfo = this.a.a(index);
        com.facebook.imagepipeline.a.a.b prevFrameInfo = this.a.a(index - 1);
        if (currFrameInfo.f == com.facebook.imagepipeline.a.a.b.a.NO_BLEND && a(currFrameInfo)) {
            return true;
        }
        if (prevFrameInfo.g == com.facebook.imagepipeline.a.a.b.b.DISPOSE_TO_BACKGROUND && a(prevFrameInfo)) {
            return true;
        }
        return false;
    }

    private boolean a(com.facebook.imagepipeline.a.a.b frameInfo) {
        return frameInfo.b == 0 && frameInfo.c == 0 && frameInfo.d == this.a.e() && frameInfo.e == this.a.f();
    }
}
