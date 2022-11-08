package com.skype.slimcore.video;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import com.microsoft.skype.a.a;
import com.skype.slimcore.skylib.SkyLibProvider;
import com.skype.slimcore.utils.Action1;
import com.skype.slimcore.utils.RNObjectHandleHelper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class RNCallingVideoViewManager extends SimpleViewManager<RNCallingVideoView> {
    public static final int ATTACH_VIDEO = 1;
    public static final int PAN_VIDEO = 3;
    public static final String REACT_CLASS = "RNCallingVideoView";
    public static final int RESET_PAN = 4;
    private static final String TAG = "RNCallingVideoViewManager";
    public static final int UPDATE_ASPECT_RATIO = 2;
    private static final Random random = new Random();
    private final ag reactContext;
    private final WeakReference<SkyLibProvider> skyLibProvider;
    private final Map<Integer, RNCallingVideoView> videoViewMap = new HashMap();

    @SuppressLint({"UseSparseArrays"})
    public RNCallingVideoViewManager(ag reactContext, WeakReference<SkyLibProvider> skyLibProvider) {
        this.reactContext = reactContext;
        this.skyLibProvider = skyLibProvider;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public RNCallingVideoView createViewInstance(ae context) {
        return new RNCallingVideoView(context);
    }

    public Map<String, Integer> getCommandsMap() {
        return e.a("attachVideo", Integer.valueOf(1), "updateVideoAspectRatio", Integer.valueOf(2), "panVideo", Integer.valueOf(3), "resetPan", Integer.valueOf(4));
    }

    public void receiveCommand(RNCallingVideoView videoView, int commandType, al args) {
        switch (commandType) {
            case 1:
                attachVideo(videoView, args.getInt(0), args.getBoolean(2));
                return;
            case 2:
                updateAspectRatio(videoView, args.getBoolean(0));
                return;
            case 3:
                am point = args.getMap(1);
                panVideo(videoView, point.getDouble("x"), point.getDouble("y"));
                return;
            case 4:
                resetPan(videoView);
                return;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }

    public void attachVideo(final RNCallingVideoView view, final int videoObjectId, final boolean fit) {
        a.a.c(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewManager d;

            public final void run() {
                boolean needsAttach = true;
                final int causeId = RNCallingVideoViewManager.random.nextInt();
                FLog.i(RNCallingVideoViewManager.TAG, "attachVideo %d (%s) causeId %x", Integer.valueOf(videoObjectId), this.d.videoViewMap.keySet(), Integer.valueOf(causeId));
                if (this.d.videoViewMap.containsKey(Integer.valueOf(videoObjectId))) {
                    FLog.i(RNCallingVideoViewManager.TAG, "attachVideo %d already attached to another view, reparenting causeId %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
                    RNCallingVideoView otherView = (RNCallingVideoView) this.d.videoViewMap.get(Integer.valueOf(videoObjectId));
                    this.d.videoViewMap.put(Integer.valueOf(videoObjectId), view);
                    needsAttach = !view.a(otherView, this.d.reactContext, fit);
                }
                if (needsAttach) {
                    FLog.i(RNCallingVideoViewManager.TAG, "attachVideo %d needsAttach causeId %x", Integer.valueOf(videoObjectId), Integer.valueOf(causeId));
                    SkyLibProvider provider = (SkyLibProvider) this.d.skyLibProvider.get();
                    if (provider == null) {
                        FLog.e(RNCallingVideoViewManager.TAG, "attachVideo: Failed to get SkyLib!");
                        return;
                    }
                    this.d.videoViewMap.put(Integer.valueOf(videoObjectId), view);
                    view.a(this.d.reactContext, provider.d(), videoObjectId, fit, new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            a.a.c(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = this$2;
                                }

                                public final void run() {
                                    FLog.i(RNCallingVideoViewManager.TAG, "videoViewMap remove %d (%s) causeId %x", Integer.valueOf(videoObjectId), this.a.b.d.videoViewMap.keySet(), Integer.valueOf(causeId));
                                    this.a.b.d.videoViewMap.remove(Integer.valueOf(videoObjectId));
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void updateAspectRatio(RNCallingVideoView view, boolean fit) {
        view.a(fit);
    }

    private void panVideo(RNCallingVideoView view, double x, double y) {
        final RNCallingVideoView rNCallingVideoView = view;
        final double d = x;
        final double d2 = y;
        a.a.c(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewManager d;

            public final void run() {
                rNCallingVideoView.a(d, d2);
            }
        });
    }

    private void resetPan(final RNCallingVideoView view) {
        a.a.c(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewManager b;

            public final void run() {
                view.a();
            }
        });
    }

    public void detachVideo(final int videoObjectId, final Runnable success, final Runnable failure) {
        a.a.c(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewManager d;

            public final void run() {
                FLog.i(RNCallingVideoViewManager.TAG, "detachVideo %d (%s) causeId %x", Integer.valueOf(videoObjectId), this.d.videoViewMap.keySet(), Integer.valueOf(RNCallingVideoViewManager.random.nextInt()));
                RNCallingVideoView view = (RNCallingVideoView) this.d.videoViewMap.remove(Integer.valueOf(videoObjectId));
                if (view == null) {
                    FLog.i(RNCallingVideoViewManager.TAG, "detachVideo: Failed to find view!");
                    if (failure != null) {
                        failure.run();
                        return;
                    }
                    return;
                }
                SkyLibProvider provider = (SkyLibProvider) this.d.skyLibProvider.get();
                if (provider == null) {
                    FLog.e(RNCallingVideoViewManager.TAG, "detachVideo: Failed to get SkyLib!");
                    return;
                }
                view.a(provider.d());
                if (success != null) {
                    success.run();
                }
            }
        });
    }

    public void captureFrame(int videoObjectId, @NonNull Action1<am> success, @NonNull Runnable failure, int causeId) {
        final int i = videoObjectId;
        final int i2 = causeId;
        final Runnable runnable = failure;
        final Action1<am> action1 = success;
        a.a.c(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewManager e;

            public final void run() {
                FLog.i(RNCallingVideoViewManager.TAG, "captureFrame: videoObjectId %x, causeId %x", Integer.valueOf(i), Integer.valueOf(i2));
                RNCallingVideoView view = (RNCallingVideoView) this.e.videoViewMap.get(Integer.valueOf(i));
                if (view == null) {
                    FLog.e(RNCallingVideoViewManager.TAG, "captureFrame: failed to find view, videoObjectId %x, causeId %x", Integer.valueOf(i), Integer.valueOf(i2));
                    runnable.run();
                    return;
                }
                Object frame = view.a(i2);
                if (frame == null) {
                    runnable.run();
                    return;
                }
                RNObjectHandleHelper obj = RNObjectHandleHelper.a();
                String key = obj.a(frame);
                FLog.d(RNCallingVideoViewManager.TAG, "RNObjectHandleHelper key sets:" + obj.b().toString());
                ar result = new WritableNativeMap();
                result.putInt("width", frame.getWidth());
                result.putInt("height", frame.getHeight());
                result.putBoolean("mirrored", false);
                result.putInt("angle", 0);
                result.putString(PropertiesEntry.COLUMN_NAME_KEY, key);
                action1.a(result);
            }
        });
    }

    @ReactProp(defaultFloat = 0.0f, name = "borderRadius")
    public void setBorderRadius(RNCallingVideoView view, float borderRadius) {
        if (!com.facebook.yoga.a.a(borderRadius)) {
            borderRadius = p.a(borderRadius);
        }
        view.setBorderRadius(borderRadius);
    }
}
