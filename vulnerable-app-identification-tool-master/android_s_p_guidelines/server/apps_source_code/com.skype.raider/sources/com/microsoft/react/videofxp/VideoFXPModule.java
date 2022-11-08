package com.microsoft.react.videofxp;

import android.os.Handler;
import android.os.HandlerThread;
import com.adjust.sdk.Constants;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class VideoFXPModule extends ReactContextBaseJavaModule {
    static final String ALREADY_RUNNING_ERROR_CODE = "AlreadyInProgress";
    public static final String REENCODING_EVENT_ESTIMATED_SIZE_KEY = "estimatedSize";
    public static final String REENCODING_EVENT_PROGRESS_KEY = "progress";
    public static final String REENCODING_EVENT_TYPE_KEY = "type";
    public static final String REENCODING_EVENT_TYPE_REENCODE = "reencode";
    public static final String TAG = "VideoFXPModule";
    private HandlerThread handlerThread;
    private a mViewProvider;
    private final Random random = new Random();
    private Handler reencodeHandler;
    private AtomicReference<o> reencoderRef = new AtomicReference();

    public interface a {
        VideoFXPView getView();
    }

    public VideoFXPModule(ag reactContext, a viewProvider) {
        super(reactContext);
        this.mViewProvider = viewProvider;
        this.handlerThread = new HandlerThread("Reencoder");
        this.handlerThread.start();
        this.reencodeHandler = new Handler(this.handlerThread.getLooper());
    }

    public String getName() {
        return "VideoFXP";
    }

    @ReactMethod
    public void saveVideo(String overlayPath, am options, String causeId, ae promise) {
        FLog.i(TAG, "saveVideo with overlay " + overlayPath.toString() + " (causeId %s)" + causeId);
        if (this.mViewProvider != null) {
            VideoFXPView view = this.mViewProvider.getView();
            if (view != null) {
                int mode = view.d();
                float intensity = view.e();
                saveVideoWithLensAndIntensity(new o(), view.f(), overlayPath, promise, mode, intensity, new p(options), causeId);
            }
        }
    }

    @ReactMethod
    public void saveVideoWithoutView(String sourcePath, String overlayPath, am options, String causeId, ae promise) {
        final o newReencoder = new o();
        if (this.reencoderRef.compareAndSet(null, newReencoder)) {
            final am amVar = options;
            final String str = sourcePath;
            final String str2 = overlayPath;
            final ae aeVar = promise;
            final String str3 = causeId;
            this.reencodeHandler.post(new Runnable(this) {
                final /* synthetic */ VideoFXPModule g;

                public final void run() {
                    this.g.saveVideoWithLensAndIntensity(newReencoder, str, str2, aeVar, 0, 1.0f, new p(amVar), str3);
                }
            });
            return;
        }
        promise.a(ALREADY_RUNNING_ERROR_CODE, "Re-encode already started");
    }

    @ReactMethod
    public void cancelReencode() {
        o reencoder = (o) this.reencoderRef.get();
        if (reencoder != null) {
            reencoder.a();
        }
        this.reencoderRef.set(null);
    }

    private void saveVideoWithLensAndIntensity(o reencoder, String sourcePath, String overlayPath, ae promise, int lensMode, float lensIntensity, p options, String causeId) {
        String str;
        String str2 = TAG;
        StringBuilder append = new StringBuilder("saveVideoWithLensAndIntensity ").append(sourcePath.toString()).append(" with overlay ");
        if (overlayPath == null) {
            str = "null";
        } else {
            str = overlayPath.toString();
        }
        FLog.i(str2, append.append(str).append(" causeId: ").append(causeId).toString());
        FLog.i(TAG, "lens mode " + lensMode + " " + lensIntensity);
        String destinationPath = null;
        String thumbnailPath = null;
        String streamablePath = null;
        try {
            String fileName = "SKP_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + this.random.nextInt(Constants.ONE_SECOND);
            File destinationFile = File.createTempFile(fileName, ".mp4", null);
            File streamableFile = File.createTempFile(fileName + "-moov", ".mp4", null);
            destinationPath = destinationFile.getAbsolutePath();
            streamablePath = streamableFile.getAbsolutePath();
            thumbnailPath = File.createTempFile("SKP_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + new Random().nextInt(Constants.ONE_SECOND), ".jpg").getAbsolutePath();
            final ar map = reencoder.a(sourcePath, destinationPath, overlayPath, thumbnailPath, lensMode, lensIntensity, options, new a<r>(this) {
                final /* synthetic */ VideoFXPModule a;

                {
                    this.a = this$0;
                }

                public final /* synthetic */ void a(Object obj) {
                    r rVar = (r) obj;
                    ar writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble(VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, rVar.a());
                    writableNativeMap.putDouble(VideoFXPModule.REENCODING_EVENT_ESTIMATED_SIZE_KEY, (double) rVar.b());
                    writableNativeMap.putString("type", VideoFXPModule.REENCODING_EVENT_TYPE_REENCODE);
                    ((RCTDeviceEventEmitter) this.a.getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(VideoFXPViewManager.REENCODE_PROGRESS_CHANGED_EVENT_NAME, writableNativeMap);
                }
            });
            if (map == null) {
                FLog.e(TAG, "Rejecting promise with uri " + destinationPath + " thumbnailUri " + thumbnailPath);
                cleanup(destinationPath);
                cleanup(thumbnailPath);
            } else {
                try {
                    FLog.i(TAG, "Move MOOV to the beginning of the file: " + streamableFile);
                    i.a(destinationFile, streamableFile);
                    if (streamableFile.exists()) {
                        map.putString(ReactVideoViewManager.PROP_SRC_URI, streamablePath);
                    }
                } catch (Exception e) {
                    FLog.w(TAG, "Failed move MOOV for the " + streamableFile);
                }
            }
            final String lastErrorCode = reencoder.a != null ? reencoder.a : "UnknownError";
            final String usedLastErrorMessage = (reencoder.b != null ? reencoder.b : "").replaceAll(sourcePath, "[pii<SourcePath>]").replaceAll(overlayPath, "[pii<OverlayPath>]").replaceAll(thumbnailPath, "[pii<ThumbnailPath>]").replaceAll(destinationPath, "[pii<DestinationPath>]");
            final o oVar = reencoder;
            final ae aeVar = promise;
            ap.a(new Runnable(this) {
                final /* synthetic */ VideoFXPModule f;

                public final void run() {
                    this.f.reencoderRef.compareAndSet(oVar, null);
                    if (map != null) {
                        aeVar.a(map);
                    } else {
                        aeVar.a(lastErrorCode, usedLastErrorMessage);
                    }
                }
            });
        } catch (final Exception except) {
            this.reencoderRef.compareAndSet(reencoder, null);
            cleanup(destinationPath);
            cleanup(streamablePath);
            cleanup(thumbnailPath);
            final ae aeVar2 = promise;
            ap.a(new Runnable(this) {
                final /* synthetic */ VideoFXPModule c;

                public final void run() {
                    FLog.e(VideoFXPModule.TAG, "Rejecting promise with exception " + except.getLocalizedMessage());
                    aeVar2.a(except);
                }
            });
        }
    }

    private void cleanup(String filePath) {
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("LensMode", new HashMap());
        return constants;
    }
}
