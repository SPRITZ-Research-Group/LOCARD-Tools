package com.facebook.fbui.textlayoutbuilder.a;

import android.graphics.Picture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.Layout;

public final class a implements com.facebook.fbui.textlayoutbuilder.a {
    private static a a;

    private static class a extends Handler {
        private final Picture a = new Picture();

        public a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message msg) {
            int i = 0;
            Layout layout = msg.obj;
            try {
                Picture picture = this.a;
                if (layout != null) {
                    int lineCount = layout.getLineCount();
                    int i2 = 0;
                    while (i2 < lineCount) {
                        int max = Math.max(i, (int) layout.getLineRight(i2));
                        i2++;
                        i = max;
                    }
                }
                layout.draw(picture.beginRecording(i, com.facebook.fbui.textlayoutbuilder.b.a.a(layout)));
                this.a.endRecording();
            } catch (Exception e) {
            }
        }
    }

    public final void a(Layout layout) {
        if (a == null) {
            HandlerThread handlerThread = new HandlerThread("GlyphWarmer");
            handlerThread.start();
            a = new a(handlerThread.getLooper());
        }
        a aVar = a;
        aVar.sendMessage(aVar.obtainMessage(1, layout));
    }
}
