package com.microsoft.react.mediapicker;

import android.text.TextUtils;
import com.facebook.infer.annotation.a;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.w;

public class c extends h {
    private String a;
    private boolean b;
    private boolean c;
    private int d;
    private int f;

    public final void a(al uiViewOperationQueue) {
        Object obj = (!u() || H()) ? null : 1;
        if (obj != null) {
            uiViewOperationQueue.a(((w) a.a(D())).A(), A(), d(), e(), f(), g());
        }
    }

    @ReactProp(name = "album")
    public void setAlbum(String album) {
        if (!TextUtils.equals(album, this.a)) {
            this.a = album;
            i();
        }
    }

    @ReactProp(name = "allowVideo")
    public void setAllowVideo(boolean allowVideo) {
        if (allowVideo != this.b) {
            this.b = allowVideo;
            i();
        }
    }

    @ReactProp(name = "disableGifs")
    public void setDisableGifs(boolean disableGifs) {
        if (disableGifs != this.c) {
            this.c = disableGifs;
            i();
        }
    }

    @ReactProp(name = "gridPadding")
    public void setGridPadding(int padding) {
        if (padding != this.d) {
            this.d = padding;
            i();
        }
    }

    @ReactProp(name = "maxThumbnailSize")
    public void setMaxThumbnailSize(int maxSize) {
        if (maxSize != this.f) {
            this.f = maxSize;
            i();
        }
    }
}
