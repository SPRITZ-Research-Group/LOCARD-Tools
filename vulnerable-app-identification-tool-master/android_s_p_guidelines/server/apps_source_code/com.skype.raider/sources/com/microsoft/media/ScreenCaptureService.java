package com.microsoft.media;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import java.nio.ByteBuffer;

@TargetApi(21)
public class ScreenCaptureService implements OnImageAvailableListener {
    private static final int SCREEN_SHARE_REQUEST_INTENT_REQ_CODE = 1;
    private static final String SCREEN_SHARE_VIRTUAL_DISPLAY = "ScreenSharing";
    private static final String TAG = "ScreenCaptureService";
    private Activity mActivity;
    private int mHeight;
    private ImageReader mImageReader;
    private MediaProjection mMediaProjection;
    private MediaProjectionManager mMediaProjectionManager;
    private int mPixelDensity;
    private ScreenCaptureServiceListener mScreenCaptureServiceListener;
    private Surface mSurface;
    private VirtualDisplay mVirtualDisplay;
    private int mWidth;
    private WindowManager mWindowManager;

    public interface ScreenCaptureServiceListener {
        void onScreenCaptureStarted();

        void onScreenCaptureUnsuccessful(@NonNull String str);
    }

    private static native void onFrameReady(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, int i5);

    public ScreenCaptureService(@NonNull Activity activity, @NonNull ScreenCaptureServiceListener screenCaptureServiceListener) {
        this.mActivity = activity;
        this.mScreenCaptureServiceListener = screenCaptureServiceListener;
    }

    private void initialize() {
        Display display = this.mActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        this.mWidth = metrics.widthPixels;
        this.mHeight = metrics.heightPixels;
        this.mPixelDensity = metrics.densityDpi;
        while (this.mWidth * this.mHeight > 1048576) {
            this.mWidth = (this.mWidth >> 1) & -2;
            this.mHeight = (this.mHeight >> 1) & -2;
        }
        this.mWindowManager = (WindowManager) this.mActivity.getSystemService("window");
        if (this.mWindowManager != null) {
            FLog.i(TAG, "acquired Window Manager");
        }
        this.mImageReader = ImageReader.newInstance(this.mWidth, this.mHeight, 1, 2);
        if (this.mImageReader != null) {
            FLog.i(TAG, "created Image Reader");
        }
        this.mImageReader.setOnImageAvailableListener(this, null);
        this.mSurface = this.mImageReader.getSurface();
        if (this.mSurface != null) {
            FLog.i(TAG, "acquired Image Surface");
        }
        this.mMediaProjectionManager = (MediaProjectionManager) this.mActivity.getSystemService("media_projection");
        if (this.mMediaProjectionManager != null) {
            FLog.i(TAG, "acquired MediaProjection Manager");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FLog.i(TAG, "onActivityResult requestCode:%d, resultCode:%d", Integer.valueOf(requestCode), Integer.valueOf(resultCode));
        if (requestCode != 1) {
            return;
        }
        String error;
        if (resultCode != -1) {
            error = "user cancelled, did not give permission to capture screen";
            FLog.e(TAG, error);
            this.mScreenCaptureServiceListener.onScreenCaptureUnsuccessful(error);
            return;
        }
        FLog.i(TAG, "set up media projection");
        if (setUpMediaProjection(resultCode, data)) {
            FLog.i(TAG, "set up virtualDisplay");
            setUpVirtualDisplay();
            FLog.i(TAG, "successfully started screen capturing.");
            this.mScreenCaptureServiceListener.onScreenCaptureStarted();
            return;
        }
        error = "Could not get media projection";
        FLog.e(TAG, error);
        this.mScreenCaptureServiceListener.onScreenCaptureUnsuccessful(error);
    }

    public void cleanup() {
        FLog.i(TAG, "release resources");
        if (this.mVirtualDisplay != null) {
            this.mVirtualDisplay.release();
            this.mVirtualDisplay = null;
        }
        if (this.mImageReader != null) {
            this.mImageReader.close();
        }
        tearDownMediaProjection();
        if (this.mSurface != null) {
            this.mSurface.release();
            this.mSurface = null;
        }
    }

    @TargetApi(21)
    private boolean setUpMediaProjection(int resultCode, Intent data) {
        this.mMediaProjection = this.mMediaProjectionManager.getMediaProjection(resultCode, data);
        if (this.mMediaProjection != null) {
            return true;
        }
        FLog.w(TAG, "media projection is null");
        return false;
    }

    @TargetApi(21)
    private void tearDownMediaProjection() {
        if (this.mMediaProjection != null) {
            this.mMediaProjection.stop();
            this.mMediaProjection = null;
            FLog.i(TAG, "destroy media projection");
        }
    }

    public void screenCapture() {
        cleanup();
        initialize();
        if (requestScreenCapture()) {
            FLog.i(TAG, "requested for screen capture permission.");
        }
    }

    private boolean requestScreenCapture() {
        FLog.i(TAG, "start screen capture");
        if (this.mSurface == null) {
            String error = "Surface is not available, Could not start screen share";
            FLog.e(TAG, error);
            this.mScreenCaptureServiceListener.onScreenCaptureUnsuccessful(error);
            return false;
        }
        FLog.i(TAG, "Requesting confirmation");
        this.mActivity.startActivityForResult(this.mMediaProjectionManager.createScreenCaptureIntent(), 1);
        return true;
    }

    public void resetImageReader(int rotation) {
        if (rotation == 90 || ((rotation == 270 && this.mWidth < this.mHeight) || rotation == 0 || (rotation == 180 && this.mWidth > this.mHeight))) {
            int currentWidth = this.mWidth;
            this.mWidth = this.mHeight;
            this.mHeight = currentWidth;
            FLog.i(TAG, "rotation changed, swap width and height, current width=%d, current height=%d", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight));
            if (this.mVirtualDisplay != null) {
                this.mImageReader.close();
                ImageReader newReader = ImageReader.newInstance(this.mWidth, this.mHeight, 1, 2);
                this.mVirtualDisplay.resize(this.mWidth, this.mHeight, this.mPixelDensity);
                this.mVirtualDisplay.setSurface(newReader.getSurface());
                this.mImageReader = newReader;
                newReader.setOnImageAvailableListener(this, null);
            }
        }
    }

    public void onImageAvailable(ImageReader reader) {
        if (this.mVirtualDisplay == null) {
            FLog.w(TAG, "virtual display is not set up, ignore the frames");
            return;
        }
        Image image = this.mImageReader.acquireLatestImage();
        if (image == null) {
            FLog.w(TAG, "acquire latest image failed");
            return;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        Plane[] planes = image.getPlanes();
        if (planes.length > 0) {
            onFrameReady(planes[0].getBuffer(), width, height, planes[0].getRowStride(), planes[0].getPixelStride(), 0);
        }
        image.close();
    }

    @TargetApi(21)
    private void setUpVirtualDisplay() {
        this.mVirtualDisplay = this.mMediaProjection.createVirtualDisplay(SCREEN_SHARE_VIRTUAL_DISPLAY, this.mWidth, this.mHeight, this.mPixelDensity, 8, this.mSurface, null, null);
    }
}
