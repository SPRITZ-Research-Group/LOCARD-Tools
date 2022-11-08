package com.skype.android.video.render;

public interface SurfaceTextureRenderer {
    void attach();

    void detach();

    void onVisibilityChanged(boolean z);

    boolean render(int i, int i2);
}
