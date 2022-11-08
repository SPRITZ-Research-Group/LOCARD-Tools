package com.skype.android.video.render;

import android.graphics.Bitmap;

public class CapturedFrame {
    public final byte[] attachment;
    public final Bitmap bitmap;

    public CapturedFrame(Bitmap bitmap, byte[] attachment) {
        this.bitmap = bitmap;
        this.attachment = attachment;
    }
}
