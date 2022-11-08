package com.skype.reactnativesprites;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.al;

public class AnimationFrames {
    private final Drawable[] a;
    private final int b;
    private final int c;
    private final int d;

    public AnimationFrames(ManagedRegionDecoder decoder, int sourceWidth, int sourceHeight, @NonNull SpriteViewProperties properties) {
        this.d = (int) Math.ceil(Math.sqrt((double) ((((float) sourceWidth) * ((float) properties.d())) / ((float) sourceHeight))));
        this.b = properties.d() > 0 ? properties.d() : sourceHeight / sourceWidth;
        al frameSequence = properties.c();
        this.c = frameSequence != null ? frameSequence.size() : this.b;
        int sourceFramesCount = properties.d();
        if (!(sourceFramesCount == 0 || sourceFramesCount == this.b)) {
            FLog.w("Sprite", "Animation image " + properties.a() + " framesCount mismatch! Assigned: " + sourceFramesCount + " has:" + this.b);
        }
        this.a = new Drawable[this.b];
        int frameSize = sourceWidth / this.d;
        int i = 0;
        while (i < this.b) {
            int currentRow;
            int currentColumn = this.d > 1 ? i / this.d : 0;
            if (this.d > 1) {
                currentRow = i % this.d;
            } else {
                currentRow = i;
            }
            int left = frameSize * currentColumn;
            int top = frameSize * currentRow;
            this.a[i] = decoder.a(new Rect(left, top, left + frameSize, top + frameSize));
            i++;
        }
    }

    public final void a(SpriteView spriteView) {
        SpriteViewProperties properties = spriteView.a();
        float fps = properties.f();
        int firstFrame = properties.e();
        AnimationDrawable animation = new AnimationDrawable();
        int frameDuration = (int) (1000.0f / fps);
        al framesSequence = properties.c();
        for (int i = 0; i < this.c; i++) {
            int frameIndex = (i + firstFrame) % this.c;
            if (framesSequence != null) {
                a(framesSequence.size(), frameIndex);
                frameIndex = framesSequence.getInt(frameIndex);
            }
            a(this.a.length, frameIndex);
            animation.addFrame(this.a[frameIndex], frameDuration);
        }
        spriteView.setImageDrawable(animation);
        long delay = 0;
        long now = SystemClock.uptimeMillis();
        if (properties.g()) {
            long totalDuration = (long) (this.c * frameDuration);
            delay = totalDuration - (now % totalDuration);
        }
        animation.scheduleSelf(animation, now + delay);
    }

    private static void a(int length, int index) {
        if (index > length) {
            throw new IndexOutOfBoundsException("length=" + length + " index=" + index);
        }
    }
}
