package com.yqritc.scalablevideoview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.yqritc.scalablevideoview.b.a;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

public class ScalableVideoView extends TextureView implements OnVideoSizeChangedListener, SurfaceTextureListener {
    protected MediaPlayer a;
    protected c b;

    public ScalableVideoView(Context context) {
        this(context, null);
    }

    public ScalableVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScalableVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.b = c.NONE;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, a.scaleStyle, 0, 0);
            if (a != null) {
                int scaleType = a.getInt(a.scaleStyle_scalableType, c.NONE.ordinal());
                a.recycle();
                this.b = c.values()[scaleType];
            }
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);
        if (this.a != null) {
            this.a.setSurface(surface);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            if (this.a.isPlaying()) {
                this.a.stop();
            }
            this.a.reset();
            this.a.release();
            this.a = null;
        }
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        a(width, height);
    }

    private void a(int videoWidth, int videoHeight) {
        if (videoWidth != 0 && videoHeight != 0) {
            Matrix matrix = new d(new e(getWidth(), getHeight()), new e(videoWidth, videoHeight)).a(this.b);
            if (matrix != null) {
                setTransform(matrix);
            }
        }
    }

    private void f() {
        if (this.a == null) {
            this.a = new MediaPlayer();
            this.a.setOnVideoSizeChangedListener(this);
            setSurfaceTextureListener(this);
            return;
        }
        this.a.reset();
    }

    public void setRawData(@RawRes int id) throws IOException {
        a(getResources().openRawResourceFd(id));
    }

    public void setAssetData(@NonNull String assetName) throws IOException {
        a(getContext().getAssets().openFd(assetName));
    }

    private void a(@NonNull AssetFileDescriptor afd) throws IOException {
        setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        afd.close();
    }

    public void setDataSource(@NonNull String path) throws IOException {
        f();
        this.a.setDataSource(path);
    }

    public void setDataSource(@NonNull Context context, @NonNull Uri uri, @Nullable Map<String, String> headers) throws IOException {
        f();
        this.a.setDataSource(context, uri, headers);
    }

    public void setDataSource(@NonNull Context context, @NonNull Uri uri) throws IOException {
        f();
        this.a.setDataSource(context, uri);
    }

    public void setDataSource(@NonNull FileDescriptor fd, long offset, long length) throws IOException {
        f();
        this.a.setDataSource(fd, offset, length);
    }

    public void setDataSource(@NonNull FileDescriptor fd) throws IOException {
        f();
        this.a.setDataSource(fd);
    }

    public void setScalableType(c scalableType) {
        this.b = scalableType;
        a(this.a.getVideoWidth(), this.a.getVideoHeight());
    }

    public final void a(@Nullable OnPreparedListener listener) throws IllegalStateException {
        this.a.setOnPreparedListener(listener);
        this.a.prepareAsync();
    }

    public void setOnErrorListener(@Nullable OnErrorListener listener) {
        this.a.setOnErrorListener(listener);
    }

    public void setOnCompletionListener(@Nullable OnCompletionListener listener) {
        this.a.setOnCompletionListener(listener);
    }

    public void setOnInfoListener(@Nullable OnInfoListener listener) {
        this.a.setOnInfoListener(listener);
    }

    public final int a() {
        return this.a.getCurrentPosition();
    }

    public final int b() {
        return this.a.getVideoHeight();
    }

    public final int c() {
        return this.a.getVideoWidth();
    }

    public final void d() {
        this.a.pause();
    }

    public void a(int msec) {
        this.a.seekTo(msec);
    }

    public void setLooping(boolean looping) {
        this.a.setLooping(looping);
    }

    public void setVolume(float leftVolume, float rightVolume) {
        this.a.setVolume(leftVolume, rightVolume);
    }

    public final void e() {
        this.a.start();
    }
}
