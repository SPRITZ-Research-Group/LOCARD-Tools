package com.skype.slimcore.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.microsoft.skype.a.a;
import com.skype.SkyLib;
import com.skype.VideoImpl;
import com.skype.slimcore.skylib.SkyLibManager.SkyLibExecution;
import com.skype.slimcore.skylib.SkyLibProvider;
import com.skype.slimcore.utils.Action1;
import com.skype.slimcore.video.StillCamera.OutputFormat;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class RNCallingVideoViewPreviewManager extends SimpleViewManager<RNCallingVideoViewPreview> {
    public static final int ATTACH_VIDEO = 1;
    public static final int PAN_VIDEO = 3;
    public static final String REACT_CLASS = "RNCallingVideoViewPreview";
    public static final int RESET_PAN = 4;
    private static final String TAG = "RNCallingVideoViewPreviewManager";
    public static final int UPDATE_ASPECT_RATIO = 2;
    private static final Random random = new Random();
    private final ag reactContext;
    private final WeakReference<SkyLibProvider> skyLibProvider;
    private StillCamera stillCamera_DEPRECATED;
    private final Map<Integer, StillCamera> stillCameras = new HashMap();
    private final VideoOrientationManager videoOrientationManager;
    private final Map<Integer, RNCallingVideoViewPreview> videoViewMap = new HashMap();

    @SuppressLint({"UseSparseArrays"})
    public RNCallingVideoViewPreviewManager(ag reactContext, WeakReference<SkyLibProvider> skyLibProvider) {
        this.reactContext = reactContext;
        this.skyLibProvider = skyLibProvider;
        this.videoOrientationManager = VideoOrientationManager.a((Context) reactContext, (WeakReference) skyLibProvider);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public RNCallingVideoViewPreview createViewInstance(ae context) {
        return new RNCallingVideoViewPreview(context, this.videoOrientationManager);
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("attachVideo", Integer.valueOf(1), "updateVideoAspectRatio", Integer.valueOf(2), "panVideo", Integer.valueOf(3), "resetPan", Integer.valueOf(4));
    }

    public void receiveCommand(RNCallingVideoViewPreview videoView, int commandType, al args) {
        switch (commandType) {
            case 1:
                attachVideo(videoView, args.getInt(0), args.getInt(1), args.getBoolean(2));
                return;
            case 2:
                updateAspectRatio(videoView, args.getBoolean(0));
                return;
            case 3:
            case 4:
                return;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }

    private boolean attachVideo(RNCallingVideoViewPreview view, int videoObjectId, int videoCameraPosition, boolean fit) {
        int causeId = random.nextInt();
        if (this.videoViewMap.containsKey(Integer.valueOf(videoObjectId))) {
            FLog.i(TAG, "attachVideo %d already attached to another view, reparenting causeId: %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
            RNCallingVideoViewPreview otherView = (RNCallingVideoViewPreview) this.videoViewMap.get(Integer.valueOf(videoObjectId));
            this.videoViewMap.put(Integer.valueOf(videoObjectId), view);
            view.a(otherView, videoObjectId, videoCameraPosition, fit, causeId);
        } else {
            FLog.i(TAG, "attachVideo %d causeId: %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
            this.videoViewMap.put(Integer.valueOf(videoObjectId), view);
            SkyLibProvider provider = (SkyLibProvider) this.skyLibProvider.get();
            if (provider == null) {
                FLog.e(TAG, "attachVideo: Failed to get SkyLib!");
                return false;
            }
            view.a(this.reactContext, provider.d(), videoObjectId, videoCameraPosition, fit, causeId);
        }
        return true;
    }

    private void updateAspectRatio(RNCallingVideoViewPreview view, boolean fit) {
        view.a(fit, random.nextInt());
    }

    public boolean hasVideoAttachedToView(int videoObjectId) {
        return this.videoViewMap.containsKey(Integer.valueOf(videoObjectId));
    }

    public void detachVideo(final int videoObjectId, final Runnable success, final Runnable failure) {
        a.a.c(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewPreviewManager d;

            public final void run() {
                int causeId = RNCallingVideoViewPreviewManager.random.nextInt();
                FLog.i(RNCallingVideoViewPreviewManager.TAG, "detachVideo %d causeId %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
                RNCallingVideoViewPreview view = (RNCallingVideoViewPreview) this.d.videoViewMap.remove(Integer.valueOf(videoObjectId));
                if (view == null) {
                    FLog.i(RNCallingVideoViewPreviewManager.TAG, "detachVideo: Failed to find view!");
                    failure.run();
                    return;
                }
                view.a(causeId);
                success.run();
            }
        });
    }

    public void initializeStillCapture(int videoObjectId, Runnable success, Runnable failure) {
        final int causeId = random.nextInt();
        final int i = videoObjectId;
        final Runnable runnable = failure;
        final Runnable runnable2 = success;
        ((SkyLibProvider) this.skyLibProvider.get()).d().a(new SkyLibExecution(this) {
            final /* synthetic */ RNCallingVideoViewPreviewManager e;

            public final void a(SkyLib skyLib) {
                VideoImpl video = new VideoImpl();
                if (skyLib.getVideo(i, video)) {
                    StillCamera camera = new StillCamera(video, this.e.reactContext, runnable2, runnable, causeId);
                    this.e.stillCameras.put(Integer.valueOf(i), camera);
                    this.e.stillCamera_DEPRECATED = camera;
                    return;
                }
                FLog.e(RNCallingVideoViewPreviewManager.TAG, "initializeStillCapture: Failed to get video! causeId: %x", Integer.valueOf(causeId));
                runnable.run();
            }
        });
    }

    public void captureStill(int videoObjectId, boolean flip, boolean cropToSquare, Action1<am> success, Runnable failure) {
        int causeId = random.nextInt();
        FLog.i(TAG, "captureStill videoObjectId:%d flip:%b square:%b causeId: %x", Integer.valueOf(videoObjectId), Boolean.valueOf(flip), Boolean.valueOf(cropToSquare), Integer.valueOf(causeId));
        StillCamera stillCamera = (StillCamera) this.stillCameras.get(Integer.valueOf(videoObjectId));
        if (stillCamera != null) {
            stillCamera.a(flip, cropToSquare, OutputFormat.PNG, success, failure, causeId);
            return;
        }
        FLog.e(TAG, "captureStill: No stillCamera videoObjectId:%d causeId: %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
        failure.run();
    }

    public void captureStillDeprecated(boolean flip, boolean cropToSquare, Action1<am> success, Runnable failure) {
        int causeId = random.nextInt();
        FLog.i(TAG, "captureStill flip:%b square:%b causeId: %x", Boolean.valueOf(flip), Boolean.valueOf(cropToSquare), Integer.valueOf(causeId));
        StillCamera stillCamera = this.stillCamera_DEPRECATED;
        if (stillCamera != null) {
            stillCamera.a(flip, cropToSquare, OutputFormat.PNG, success, failure, causeId);
            return;
        }
        FLog.e(TAG, "captureStill: No stillCamera causeId: %x", Integer.valueOf(causeId));
        failure.run();
    }

    public void tearDownStillCapture(final int videoObjectId, @NonNull final Runnable success, @NonNull final Runnable failure) {
        final int causeId = random.nextInt();
        FLog.i(TAG, "tearDownStillCapture causeId: %x", Integer.valueOf(causeId));
        StillCamera stillCamera = (StillCamera) this.stillCameras.get(Integer.valueOf(videoObjectId));
        if (stillCamera != null) {
            com.facebook.infer.annotation.a.a(videoObjectId == stillCamera.a(), "tearDownStillCapture videoObjectIds must match (" + videoObjectId + "," + stillCamera.a() + ") causeId: " + causeId);
            stillCamera.a(new Runnable(this) {
                final /* synthetic */ RNCallingVideoViewPreviewManager d;

                public final void run() {
                    success.run();
                    FLog.i(RNCallingVideoViewPreviewManager.TAG, "tearDownStillCapture stillCamera disposed causeId: %x", Integer.valueOf(causeId));
                    this.d.stillCameras.remove(Integer.valueOf(videoObjectId));
                    this.d.stillCamera_DEPRECATED = null;
                }
            }, new Runnable(this) {
                final /* synthetic */ RNCallingVideoViewPreviewManager d;

                public final void run() {
                    failure.run();
                    FLog.i(RNCallingVideoViewPreviewManager.TAG, "tearDownStillCapture stillCamera disposed causeId: %x", Integer.valueOf(causeId));
                    this.d.stillCameras.remove(Integer.valueOf(videoObjectId));
                    this.d.stillCamera_DEPRECATED = null;
                }
            }, causeId);
            return;
        }
        FLog.i(TAG, "tearDownStillCapture: No stillCamera causeId: %x", Integer.valueOf(causeId));
        success.run();
    }

    public void captureFrame(int videoObjectId, @NonNull Action1<am> success, @NonNull Runnable failure, int causeId) {
        StillCamera stillCamera = this.stillCamera_DEPRECATED;
        if (stillCamera == null) {
            FLog.e(TAG, "captureFrame: no stillCamera videoObjectId:%d causeId: %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
            failure.run();
            return;
        }
        stillCamera.a(false, false, OutputFormat.RAW, success, failure, causeId);
    }

    @ReactProp(defaultFloat = 0.0f, name = "borderRadius")
    public void setBorderRadius(RNCallingVideoViewPreview view, float borderRadius) {
        if (!com.facebook.yoga.a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        view.setBorderRadius(borderRadius, random.nextInt());
    }
}
