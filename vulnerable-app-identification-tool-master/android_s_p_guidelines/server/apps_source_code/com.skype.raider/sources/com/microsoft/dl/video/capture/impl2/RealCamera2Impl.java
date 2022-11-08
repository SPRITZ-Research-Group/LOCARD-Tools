package com.microsoft.dl.video.capture.impl2;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import com.microsoft.dl.Platform;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.Camera;
import com.microsoft.dl.video.capture.api.CameraCallback;
import com.microsoft.dl.video.capture.api.CameraParameters;
import com.microsoft.dl.video.capture.api.CaptureException;
import java.util.Collections;

public class RealCamera2Impl implements Camera {
    private int a;
    private CameraDevice b = null;
    private SurfaceTexture c = null;
    private int d;
    private CameraParameters e = null;
    private Builder f = null;
    private Handler g = null;
    private CameraCaptureSession h = null;
    private StateCallback i = new StateCallback(this) {
        final /* synthetic */ RealCamera2Impl a;

        {
            this.a = this$0;
        }

        public void onOpened(CameraDevice cameraDevice) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Camera: " + this.a.a + " Opened [" + cameraDevice + "]");
            }
            this.a.b = cameraDevice;
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Camera: " + this.a.a + " Disconnected [" + cameraDevice + "]");
            }
            cameraDevice.close();
            this.a.b = null;
        }

        public void onError(CameraDevice cameraDevice, int error) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Camera: " + this.a.a + " Opening failed [" + cameraDevice + "] error:" + error);
            }
            cameraDevice.close();
            this.a.b = null;
        }
    };

    public RealCamera2Impl(int cameraId) throws CaptureException {
        try {
            String cameraIdStr = String.valueOf(cameraId);
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Camera: " + cameraIdStr + " RealCamera2Impl ctor.");
            }
            CameraManager cameraManager = (CameraManager) Platform.getInfo().getAppContext().getSystemService("camera");
            if (cameraManager == null) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Failed to get CameraManager");
                }
                throw new CaptureException("android.hardware.camera2.CameraManager NULL", ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE);
            }
            cameraManager.openCamera(cameraIdStr, this.i, null);
            this.a = cameraId;
            this.g = new Handler();
        } catch (Throwable e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "CameraAccessException: Could not open camera: " + cameraId);
            }
            throw new CaptureException("Could not open camera " + cameraId, e, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
        } catch (Throwable e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "RuntimeException: Could not open camera: " + cameraId);
            }
            throw new CaptureException("Could not open camera " + cameraId, e2, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
        }
    }

    public final void close() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Camera: " + this.a + " [" + this.b + "] closing");
        }
        if (this.b != null) {
            try {
                stopPreview();
                this.b.close();
                this.b = null;
            } catch (Throwable e) {
                throw new CaptureException("Could not close camera " + this.a, e, ErrorCode.ANDROID_CAMERA_CLOSE_FAILED);
            }
        }
    }

    public final CameraParameters getParameters() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Camera: " + this.a + " having parameters as [" + this.e + "]");
        }
        return this.e;
    }

    public final void setParameters(CameraParameters parameters) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Setting parameters [" + parameters + "] to the camera: " + this.a);
        }
        this.e = parameters;
    }

    public final void setPreviewDisplay(Object display) throws CaptureException {
        if (display == null) {
            try {
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "Setting NULL PreviewDisplay to the camera: " + this.a);
                }
                this.c = null;
            } catch (Throwable e) {
                throw new CaptureException("Could not set preview display [" + display + "] for the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_SET_PREVIEW_DISPLAY_FAILED);
            }
        } else if (display instanceof SurfaceTexture) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting SurfaceTexture [" + display + "] as PreviewDisplay to the camera: " + this.a);
            }
            this.c = (SurfaceTexture) display;
        } else {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Setting unsupported [" + display.getClass().getCanonicalName() + "] as PreviewDisplay to the camera: " + this.a);
            }
            this.c = null;
            throw new CaptureException(display.getClass().getCanonicalName() + " is not supported", ErrorCode.ANDROID_CAMERA_UNSUPPORTED_PREVIEW_DISPLAY);
        }
    }

    public final void setDisplayOrientation(int degrees) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Setting Display Orientation [" + degrees + "] to the camera: " + this.a);
        }
        this.d = degrees;
    }

    public final void addCallbackBuffer(byte[] callbackBuffer) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Setting callback buffer");
        }
    }

    public final void setCallback(CameraCallback cb) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Setting callback");
        }
    }

    public final void startPreview() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "starting preview [" + this.c + "] to the camera: " + this.a + " [" + this.b + "]");
        }
        if (this.b != null) {
            try {
                this.f = this.b.createCaptureRequest(3);
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "setting CONTROL_AE_TARGET_FPS_RANGE, min:" + this.e.getFpsRange().getMin() + " max:" + this.e.getFpsRange().getMax());
                    Log.i(PackageInfo.TAG, "setting Default buffer size: width[" + this.e.getResolution().getWidth() + "], height[:" + this.e.getResolution().getHeight());
                }
                this.f.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(Integer.valueOf(this.e.getFpsRange().getMin()), Integer.valueOf(this.e.getFpsRange().getMax())));
                this.c.setDefaultBufferSize(this.e.getResolution().getWidth(), this.e.getResolution().getHeight());
                Surface previewSurface = new Surface(this.c);
                this.f.addTarget(previewSurface);
                this.b.createCaptureSession(Collections.singletonList(previewSurface), new CameraCaptureSession.StateCallback(this) {
                    final /* synthetic */ RealCamera2Impl a;

                    {
                        this.a = this$0;
                    }

                    public void onConfigured(CameraCaptureSession session) {
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "capture session[" + session + "] configure succeed");
                        }
                        this.a.h = session;
                        RealCamera2Impl.b(this.a);
                    }

                    public void onConfigureFailed(CameraCaptureSession session) {
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "capture session[" + session + "] configure failed");
                        }
                        session.close();
                    }
                }, this.g);
                return;
            } catch (Throwable e) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "start preview failed with CameraAccessException");
                }
                throw new CaptureException("CameraAccessException, Could not start preview from the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_START_PREVIEW_FAILED);
            } catch (Throwable e2) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "start preview failed with RuntimeException");
                }
                throw new CaptureException("Could not start preview from the camera " + this.a, e2, ErrorCode.ANDROID_CAMERA_START_PREVIEW_FAILED);
            }
        }
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Start preview without camera opened");
        }
        throw new CaptureException("Could not start preview without camera " + this.a, ErrorCode.ANDROID_CAMERA_START_PREVIEW_FAILED);
    }

    public final void stopPreview() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "stopping preview session [" + this.h + "]");
        }
        try {
            if (this.h != null) {
                this.h.close();
                this.h = null;
            }
        } catch (Throwable e) {
            throw new CaptureException("Could not stop preview from the camera " + this.a, e, ErrorCode.ANDROID_CAMERA_STOP_PREVIEW_FAILED);
        }
    }

    static /* synthetic */ void b(RealCamera2Impl x0) {
        try {
            x0.f.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
            x0.h.setRepeatingRequest(x0.f.build(), null, x0.g);
        } catch (CameraAccessException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "CameraAccessException: for setRepeatingRequest [" + x0.h + "]");
            }
        }
    }
}
