package com.skypecam.camera2.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCaptureSession.StateCallback;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.util.Size;
import android.view.Surface;
import com.adjust.sdk.Constants;
import com.skypecam.camera2.CameraView;
import com.skypecam.camera2.g;
import com.skypecam.camera2.l;
import com.skypecam.camera2.o;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b*\u0001%\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010>\u001a\u00020?J\u0010\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020BH\u0002J\b\u0010C\u001a\u00020?H\u0002J\u0016\u0010D\u001a\u00020?2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010E\u001a\u000207J\b\u0010F\u001a\u00020?H\u0002J\b\u0010G\u001a\u00020?H\u0002J\b\u0010H\u001a\u00020?H\u0002J\u0006\u0010I\u001a\u00020?R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020%X\u0004¢\u0006\u0004\n\u0002\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001c\"\u0004\b/\u0010\u001eR\u001c\u00100\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010*\"\u0004\b2\u0010,R\u001a\u00103\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u0010\u001eR\u001c\u00106\u001a\u0004\u0018\u000107X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/skypecam/camera2/modules/CameraVideoRecorder;", "", "backgroundHandler", "Landroid/os/Handler;", "cameraInfo", "Lcom/skypecam/camera2/modules/CameraInfo;", "cameraPreview", "Lcom/skypecam/camera2/modules/CameraPreview;", "(Landroid/os/Handler;Lcom/skypecam/camera2/modules/CameraInfo;Lcom/skypecam/camera2/modules/CameraPreview;)V", "getBackgroundHandler", "()Landroid/os/Handler;", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "getCameraInfo", "()Lcom/skypecam/camera2/modules/CameraInfo;", "getCameraPreview", "()Lcom/skypecam/camera2/modules/CameraPreview;", "cameraView", "Lcom/skypecam/camera2/CameraView;", "getCameraView", "()Lcom/skypecam/camera2/CameraView;", "setCameraView", "(Lcom/skypecam/camera2/CameraView;)V", "captureSessionLock", "Ljava/util/concurrent/atomic/AtomicBoolean;", "maxRecordingTime", "", "getMaxRecordingTime", "()I", "setMaxRecordingTime", "(I)V", "maxThumbnailSize", "getMaxThumbnailSize", "setMaxThumbnailSize", "mediaRecorder", "Landroid/media/MediaRecorder;", "mediaRecorderInfoListener", "com/skypecam/camera2/modules/CameraVideoRecorder$mediaRecorderInfoListener$1", "Lcom/skypecam/camera2/modules/CameraVideoRecorder$mediaRecorderInfoListener$1;", "outputFile", "Ljava/io/File;", "getOutputFile", "()Ljava/io/File;", "setOutputFile", "(Ljava/io/File;)V", "thumbCompressionRate", "getThumbCompressionRate", "setThumbCompressionRate", "thumbFile", "getThumbFile", "setThumbFile", "videoBitRate", "getVideoBitRate", "setVideoBitRate", "videoCaptureCallback", "Lcom/skypecam/camera2/VideoCapturedCallback;", "getVideoCaptureCallback", "()Lcom/skypecam/camera2/VideoCapturedCallback;", "setVideoCaptureCallback", "(Lcom/skypecam/camera2/VideoCapturedCallback;)V", "videoRecordingCaptureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "cancel", "", "fail", "message", "", "generateThumbnail", "initialize", "callback", "releaseMediaRecorder", "setupMediaRecorder", "startVideoRecordRequest", "stop", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class f {
    @Nullable
    private File a;
    @Nullable
    private File b;
    private MediaRecorder c;
    private CameraCaptureSession d;
    private CameraDevice e;
    @Nullable
    private o f;
    private int g = 20000;
    private int h = 1300000;
    private int i = 70;
    private int j = 360;
    private final a k = new a(this);
    @Nullable
    private CameraView l;
    private AtomicBoolean m = new AtomicBoolean(true);
    @NotNull
    private final Handler n;
    @NotNull
    private final a o;
    @NotNull
    private final d p;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"com/skypecam/camera2/modules/CameraVideoRecorder$mediaRecorderInfoListener$1", "Landroid/media/MediaRecorder$OnInfoListener;", "(Lcom/skypecam/camera2/modules/CameraVideoRecorder;)V", "onInfo", "", "mr", "Landroid/media/MediaRecorder;", "what", "", "extra", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a implements OnInfoListener {
        final /* synthetic */ f a;

        a(f $outer) {
            this.a = $outer;
        }

        public final void onInfo(@Nullable MediaRecorder mr, int what, int extra) {
            switch (what) {
                case 800:
                    l.a("Max video duration reached, stopping video");
                    this.a.f();
                    return;
                default:
                    return;
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"com/skypecam/camera2/modules/CameraVideoRecorder$startVideoRecordRequest$1$1", "Landroid/hardware/camera2/CameraCaptureSession$StateCallback;", "(Lcom/skypecam/camera2/modules/CameraVideoRecorder$startVideoRecordRequest$1;Landroid/hardware/camera2/CaptureRequest$Builder;)V", "onConfigureFailed", "", "session", "Landroid/hardware/camera2/CameraCaptureSession;", "onConfigured", "cameraCaptureSession", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class b extends StateCallback {
        final /* synthetic */ Builder a;
        final /* synthetic */ f b;

        b(Builder $captured_local_variable$1, f fVar) {
            this.a = $captured_local_variable$1;
            this.b = fVar;
        }

        public final void onConfigured(@NotNull CameraCaptureSession cameraCaptureSession) {
            kotlin.jvm.b.c.b(cameraCaptureSession, "cameraCaptureSession");
            this.b.d = cameraCaptureSession;
            try {
                CameraCaptureSession a = this.b.d;
                if (a != null) {
                    CaptureRequest build;
                    Builder builder = this.a;
                    if (builder != null) {
                        build = builder.build();
                    } else {
                        build = null;
                    }
                    a.setRepeatingRequest(build, new CaptureCallback() {
                        public final void onCaptureFailed(@Nullable CameraCaptureSession session, @Nullable CaptureRequest request, @Nullable CaptureFailure failure) {
                            super.onCaptureFailed(session, request, failure);
                            Integer valueOf = failure != null ? Integer.valueOf(failure.getReason()) : null;
                            if (valueOf != null && valueOf.intValue() == 0) {
                                kotlin.jvm.b.c.b("Video capture frame failed, frame dropped ", "message");
                            } else if (valueOf != null && valueOf.intValue() == 1) {
                                kotlin.jvm.b.c.b("Video capture frame failed, unexpected call to abortCaptures", "message");
                            }
                        }
                    }, this.b.h());
                }
                this.b.h().removeMessages(2);
                MediaRecorder b = this.b.c;
                if (b == null) {
                    kotlin.jvm.b.c.a();
                }
                b.start();
                CameraView it = this.b.e();
                if (it != null) {
                    o c = this.b.c();
                    if (c != null) {
                        c.a(true, it);
                    }
                }
                l.a("Recording video (max length " + (this.b.d() / Constants.ONE_SECOND) + "s)");
            } catch (IllegalStateException e) {
                this.b.a("Video recording start failed: mediaRecorder is in an invalid state");
            } catch (Exception e2) {
                this.b.a("Video recording start failed: " + e2.getMessage());
            }
        }

        public final void onConfigureFailed(@Nullable CameraCaptureSession session) {
            if (this.b.m.getAndSet(true)) {
                this.b.a("Video recording setup failed: session configuration failed");
                return;
            }
            g.d("Video recording setup failed: first session configuration attempt failed");
            this.b.j();
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 10})
    static final class c implements Runnable {
        final /* synthetic */ f a;

        c(f fVar) {
            this.a = fVar;
        }

        public final void run() {
            Size rotatedResolution;
            Object valueOf;
            Object obj = null;
            f.e(this.a);
            switch (this.a.i().d()) {
                case 0:
                case 180:
                    rotatedResolution = this.a.i().i();
                    break;
                default:
                    rotatedResolution = new Size(this.a.i().i().getHeight(), this.a.i().i().getWidth());
                    break;
            }
            StringBuilder stringBuilder = new StringBuilder("Video recording completed\n - Output video size: ");
            File a = this.a.a();
            if (a != null) {
                valueOf = Long.valueOf(a.length());
            } else {
                valueOf = null;
            }
            StringBuilder append = stringBuilder.append(valueOf).append(" bytes (").append(rotatedResolution.getWidth()).append('x').append(rotatedResolution.getHeight()).append(")\n - Thumbnail size: ");
            File b = this.a.b();
            if (b != null) {
                obj = Long.valueOf(b.length());
            }
            l.a(append.append(obj).append(" bytes\n - Rotation: ").append(this.a.i().d()).toString());
            if (this.a.a() != null) {
                a = this.a.a();
                if (a == null) {
                    kotlin.jvm.b.c.a();
                }
                if (a.exists()) {
                    if (this.a.b() != null) {
                        a = this.a.b();
                        if (a == null) {
                            kotlin.jvm.b.c.a();
                        }
                        if (a.exists()) {
                            g.b(rotatedResolution);
                            o c = this.a.c();
                            if (c != null) {
                                c.a(this.a.a(), this.a.b(), rotatedResolution.getWidth(), rotatedResolution.getHeight());
                                return;
                            }
                            return;
                        }
                    }
                    this.a.a("Thumbnail file not found");
                    return;
                }
            }
            this.a.a("Output file not found");
        }
    }

    public f(@NotNull Handler backgroundHandler, @NotNull a cameraInfo, @NotNull d cameraPreview) {
        kotlin.jvm.b.c.b(backgroundHandler, "backgroundHandler");
        kotlin.jvm.b.c.b(cameraInfo, "cameraInfo");
        kotlin.jvm.b.c.b(cameraPreview, "cameraPreview");
        this.n = backgroundHandler;
        this.o = cameraInfo;
        this.p = cameraPreview;
    }

    public static final /* synthetic */ void e(f $this) {
        File file = $this.a;
        if (file != null) {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), 1);
            if (createVideoThumbnail != null) {
                Bitmap bitmap;
                FileOutputStream fileOutputStream;
                if (createVideoThumbnail.getWidth() > $this.j || createVideoThumbnail.getHeight() > $this.j) {
                    float min = Math.min(((float) $this.j) / ((float) createVideoThumbnail.getWidth()), ((float) $this.j) / ((float) createVideoThumbnail.getHeight()));
                    int round = Math.round(((float) createVideoThumbnail.getWidth()) * min);
                    int round2 = Math.round(min * ((float) createVideoThumbnail.getHeight()));
                    try {
                        createVideoThumbnail = Bitmap.createScaledBitmap(createVideoThumbnail, round, round2, true);
                        l.a(round + 'x' + round2 + " thumbnail successfully generated");
                        bitmap = createVideoThumbnail;
                    } catch (OutOfMemoryError e) {
                        kotlin.jvm.b.c.b("Unable to create scaled thumbnail, using full size one", "message");
                        g.d("Unable to scale down video thumbnail");
                    }
                    $this.b = File.createTempFile(e.a(file) + "_", "_thumb.jpg");
                    fileOutputStream = new FileOutputStream($this.b);
                    if (bitmap == null) {
                        kotlin.jvm.b.c.a();
                    }
                    bitmap.compress(CompressFormat.JPEG, $this.i, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                bitmap = createVideoThumbnail;
                try {
                    $this.b = File.createTempFile(e.a(file) + "_", "_thumb.jpg");
                    fileOutputStream = new FileOutputStream($this.b);
                    if (bitmap == null) {
                        kotlin.jvm.b.c.a();
                    }
                    bitmap.compress(CompressFormat.JPEG, $this.i, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e2) {
                    String str = "Unable to generate thumbnail: " + e2.getMessage();
                    kotlin.jvm.b.c.b(str, "message");
                    g.d(str);
                }
            }
        }
    }

    @NotNull
    public final Handler h() {
        return this.n;
    }

    @NotNull
    public final a i() {
        return this.o;
    }

    @Nullable
    public final File a() {
        return this.a;
    }

    @Nullable
    public final File b() {
        return this.b;
    }

    @Nullable
    public final o c() {
        return this.f;
    }

    public final void a(int <set-?>) {
        this.g = <set-?>;
    }

    public final int d() {
        return this.g;
    }

    public final void b(int <set-?>) {
        this.h = <set-?>;
    }

    public final void c(int <set-?>) {
        this.i = <set-?>;
    }

    public final void d(int <set-?>) {
        this.j = <set-?>;
    }

    public final void a(@Nullable CameraView <set-?>) {
        this.l = <set-?>;
    }

    @Nullable
    public final CameraView e() {
        return this.l;
    }

    public final void a(@NotNull CameraDevice cameraDevice, @NotNull o callback) {
        kotlin.jvm.b.c.b(cameraDevice, "cameraDevice");
        kotlin.jvm.b.c.b(callback, "callback");
        this.f = callback;
        this.e = cameraDevice;
        try {
            this.p.f();
            this.a = File.createTempFile("video_", ".mp4");
            this.b = null;
            MediaRecorder mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(1);
            mediaRecorder.setVideoSource(2);
            mediaRecorder.setOutputFormat(2);
            mediaRecorder.setVideoEncodingBitRate(this.h);
            mediaRecorder.setVideoFrameRate(30);
            mediaRecorder.setVideoSize(this.o.i().getWidth(), this.o.i().getHeight());
            File file = this.a;
            if (file == null) {
                kotlin.jvm.b.c.a();
            }
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            mediaRecorder.setVideoEncoder(2);
            mediaRecorder.setAudioEncoder(3);
            mediaRecorder.setOrientationHint(this.o.d());
            mediaRecorder.setMaxDuration(this.g);
            mediaRecorder.setOnInfoListener(this.k);
            mediaRecorder.prepare();
            this.c = mediaRecorder;
            j();
        } catch (Exception e) {
            a("Video init failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private final void j() {
        try {
            CameraDevice cameraDevice = this.e;
            if (cameraDevice != null) {
                MediaRecorder mediaRecorder = this.c;
                if (mediaRecorder == null) {
                    kotlin.jvm.b.c.a();
                }
                Surface mediaRecorderSurface = mediaRecorder.getSurface();
                ArrayList $receiver = new ArrayList();
                Surface c = this.p.c();
                if (c == null) {
                    kotlin.jvm.b.c.a();
                }
                $receiver.add(c);
                $receiver.add(mediaRecorderSurface);
                ArrayList targetSurfaces = $receiver;
                Builder $receiver2 = cameraDevice.createCaptureRequest(3);
                $receiver2.addTarget(this.p.c());
                $receiver2.addTarget(mediaRecorderSurface);
                $receiver2.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
                $receiver2.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.o.m());
                cameraDevice.createCaptureSession(targetSurfaces, new b($receiver2, this), this.n);
            }
        } catch (Exception e) {
            a("Video recording setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public final void f() {
        CameraCaptureSession cameraCaptureSession = this.d;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
        }
        k();
        this.n.post(new c(this));
        CameraDevice it = this.e;
        if (it != null) {
            this.p.a(it);
        }
    }

    public final void g() {
        CameraCaptureSession cameraCaptureSession = this.d;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
        }
        k();
        File file = this.a;
        if (file != null) {
            file.delete();
        }
        this.a = null;
        this.b = null;
    }

    private final void a(String message) {
        kotlin.jvm.b.c.b(message, "message");
        k();
        File file = this.a;
        if (file != null) {
            file.delete();
        }
        this.a = null;
        this.b = null;
        this.n.removeMessages(2);
        g.c(message);
        o oVar = this.f;
        if (oVar != null) {
            oVar.a(message);
        }
    }

    private final void k() {
        MediaRecorder $receiver;
        f this;
        try {
            MediaRecorder mediaRecorder = this.c;
            if (mediaRecorder != null) {
                mediaRecorder.stop();
            }
            $receiver = this.c;
            if ($receiver != null) {
                $receiver.reset();
                $receiver.release();
                this = this;
            } else {
                this = this;
            }
            this.c = null;
        } catch (IllegalStateException e) {
            kotlin.jvm.b.c.b("Trying to stop MediaRecorder before it is started", "message");
            e.printStackTrace();
            g.d("Mediarecorder stop failed: mediaRecorder is in an invalid state");
            $receiver = this.c;
            if ($receiver != null) {
                $receiver.reset();
                $receiver.release();
                this = this;
            } else {
                this = this;
            }
            this.c = null;
        } catch (Exception e2) {
            kotlin.jvm.b.c.b("MediaRecorder stop failure", "message");
            e2.printStackTrace();
            g.d("Mediarecorder stop failed: " + e2.getMessage());
            $receiver = this.c;
            if ($receiver != null) {
                $receiver.reset();
                $receiver.release();
                this = this;
            } else {
                this = this;
            }
            this.c = null;
        } catch (Throwable th) {
            $receiver = this.c;
            if ($receiver != null) {
                $receiver.reset();
                $receiver.release();
            }
            this.c = null;
        }
        this.m.set(false);
        CameraView it = this.l;
        if (it != null) {
            o oVar = this.f;
            if (oVar != null) {
                oVar.a(false, it);
            }
        }
    }
}
