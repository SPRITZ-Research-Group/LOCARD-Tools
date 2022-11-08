package com.skype.slimcore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import com.skype.AddParticipantParametersImpl;
import com.skype.CallHandler;
import com.skype.CallHandler.DTMF;
import com.skype.CallHandler.MEDIA_DIRECTION;
import com.skype.CallHandler.MEDIA_PEER_TYPE;
import com.skype.CallHandler.MEDIA_STREAM_STATE;
import com.skype.CallHandler.MUTE_SCOPE;
import com.skype.CallHandler.QUALITYRATING;
import com.skype.CallHandler.REMOVE_ENDPOINT_SCOPE;
import com.skype.ContentSharing;
import com.skype.ContentSharing.ContentSharingIListener;
import com.skype.ContentSharing.FAILUREREASON;
import com.skype.ContentSharing.STATUS;
import com.skype.ContentSharingImpl;
import com.skype.PROPKEY;
import com.skype.SessionParameters;
import com.skype.SessionParametersImpl;
import com.skype.SkyLib;
import com.skype.SkyLib.INTENT;
import com.skype.SkyLib.MEDIASTATUS;
import com.skype.SkyLib.OBJECTTYPE;
import com.skype.SkyLib.PUSHHANDLINGRESULT;
import com.skype.SkyLib.QUALITY_MEDIATYPE;
import com.skype.Video;
import com.skype.Video.MEDIATYPE;
import com.skype.VideoImpl;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;
import com.skype.rt.Spl;
import com.skype.slimcore.calling.RNCallingService;
import com.skype.slimcore.logging.LogsProvider;
import com.skype.slimcore.screenshare.ScreenShareManager;
import com.skype.slimcore.skylib.SkyLibEventHandler;
import com.skype.slimcore.skylib.SkyLibException;
import com.skype.slimcore.skylib.SkyLibManager;
import com.skype.slimcore.skylib.SkyLibManager.CallHandlerExecution;
import com.skype.slimcore.skylib.SkyLibManager.SkyLibExecution;
import com.skype.slimcore.utils.Action1;
import com.skype.slimcore.video.VideoViewManagerProvider;
import java.io.File;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RNSlimcoreModule extends ReactContextBaseJavaModule {
    private static final String RN_CLASS = "RNSlimcore";
    private static final Random random = new Random();
    private final a contentSharingListener;
    private final Map<Integer, ContentSharing> contentSharingMap;
    private boolean localPreviewEnabledAndroid = false;
    private final LogsProvider mediaLogsProvider;
    private final ag reactContext;
    private final c screenShareResourceManager;
    private final SkyLibEventHandler skyLibEventHandler;
    private final LogsProvider skyLibLogsProvider;
    private final SkyLibManager skyLibManager;
    private final WeakReference<VideoViewManagerProvider> videoViewManagerProvider;

    private class a implements ContentSharingIListener {
        final /* synthetic */ RNSlimcoreModule a;

        private a(RNSlimcoreModule rNSlimcoreModule) {
            this.a = rNSlimcoreModule;
        }

        /* synthetic */ a(RNSlimcoreModule x0, byte b) {
            this(x0);
        }

        public final void onJoinContentSharingResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
            this.a.sendContentSharingResultEvent("RNSlimcore-JoinContentSharingResultEvent", sender.getObjectID(), failureReason, code, subCode);
        }

        public final void onTakeContentSharingControlResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
            this.a.sendContentSharingResultEvent("RNSlimcore-TakeContentSharingControlResultEvent", sender.getObjectID(), failureReason, code, subCode);
        }

        public final void onUpdateContentSharingParticipantStateResult(ContentSharing sender, FAILUREREASON failureReason, int code, int subCode) {
            this.a.sendContentSharingResultEvent("RNSlimcore-UpdateContentSharingParticipantStateResultEvent", sender.getObjectID(), failureReason, code, subCode);
        }

        public final void onUpdateContentSharingSessionStateResult(ContentSharing sender, String commandId, FAILUREREASON failureReason, int code, int subCode) {
            this.a.sendContentSharingUpdateSessionStateResultEvent(sender.getObjectID(), commandId, failureReason, code, subCode);
        }
    }

    private class b implements SkyLibEventHandler {
        final /* synthetic */ RNSlimcoreModule a;

        private b(RNSlimcoreModule rNSlimcoreModule) {
            this.a = rNSlimcoreModule;
        }

        /* synthetic */ b(RNSlimcoreModule x0, byte b) {
            this(x0);
        }

        public final void a(OBJECTTYPE objecttype, PROPKEY propkey, int objectId, com.microsoft.skype.b.a<String, Integer> value, String causeId) {
            this.a.sendPropertyChangedEvent(objectId, objecttype, propkey, value, causeId);
        }

        public final void a(int i, PUSHHANDLINGRESULT pushhandlingresult) {
            this.a.sendPushHandlingEvent(i, pushhandlingresult);
        }

        public final void a(int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType, String causeId) {
            this.a.sendQualityEvent(objectId, type, level, mediaType, causeId);
        }

        public final void a(String causeId) {
            this.a.sendDevicesChangedEvent(causeId);
        }

        public final void a(String invalidToken, String causeId) {
            this.a.sendSkypeTokenRequiredEvent(invalidToken, causeId);
        }

        public final void a(int objectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState, String causeId) {
            this.a.sendAudioStreamStateChangedEvent(objectId, direction, streamState, causeId);
        }

        public final void a(int objectId, int targetCallObjectId, String transferorMri, String transferTargetMri, String causeId) {
            this.a.sendCallTransferEvent(objectId, targetCallObjectId, transferorMri, transferTargetMri, causeId);
        }

        public final void a(int callObjectId, String context, int failureReason, String causeId) {
            this.a.sendNudgeParticipantsOperationStatusChangedEvent(callObjectId, context, failureReason, causeId);
        }

        public final void a(int callObjectId, String[] speakerList, String causeId) {
            this.a.sendActiveSpeakerListChangedEvent(callObjectId, speakerList, causeId);
        }

        public final void b(int callObjectId, String[] speakerList, String causeId) {
            this.a.sendDominantSpeakerListChangedEvent(callObjectId, speakerList, causeId);
        }

        public final void a(MEDIASTATUS status) {
            this.a.sendMediaStatusChangedEvent(status);
        }
    }

    private class c {
        final /* synthetic */ RNSlimcoreModule a;
        private final com.microsoft.skype.a.a b;
        private ScreenShareManager c;
        private HashSet<Integer> d;

        private c(RNSlimcoreModule rNSlimcoreModule) {
            this.a = rNSlimcoreModule;
            this.b = com.microsoft.skype.a.a.a("ScreenShareResourceManager", com.microsoft.skype.a.a.b.DEFAULT);
            this.c = null;
            this.d = new HashSet();
        }

        /* synthetic */ c(RNSlimcoreModule x0, byte b) {
            this(x0);
        }

        public final void a(final Runnable successRunnable, final Runnable failureRunnable) {
            this.b.b(new Runnable(this) {
                final /* synthetic */ c c;

                public final void run() {
                    if (this.c.c == null) {
                        this.c.c = new ScreenShareManager();
                    }
                    FLog.i(RNSlimcoreModule.RN_CLASS, "Requesting screensharing session");
                    this.c.c.a(this.c.a.reactContext, successRunnable, failureRunnable);
                }
            });
        }

        public final void a(final int mediaType, final int videoObjectId) {
            this.b.c(new Runnable(this) {
                final /* synthetic */ c c;

                public final void run() {
                    if (this.c.c != null && mediaType == MEDIATYPE.MEDIA_SCREENSHARING.toInt()) {
                        this.c.d.add(Integer.valueOf(videoObjectId));
                        FLog.d(RNSlimcoreModule.RN_CLASS, "Local video object %d added for screensharing (total=%d)", Integer.valueOf(videoObjectId), Integer.valueOf(this.c.d.size()));
                    }
                }
            });
        }

        public final void a(final int videoObjectId) {
            this.b.c(new Runnable(this) {
                final /* synthetic */ c b;

                public final void run() {
                    if (this.b.c != null && this.b.d.contains(Integer.valueOf(videoObjectId))) {
                        this.b.d.remove(Integer.valueOf(videoObjectId));
                        FLog.d(RNSlimcoreModule.RN_CLASS, "Removing video object %d for screensharing (remaining=%d)", Integer.valueOf(videoObjectId), Integer.valueOf(this.b.d.size()));
                        if (this.b.d.size() == 0) {
                            FLog.i(RNSlimcoreModule.RN_CLASS, "Stopping screensharing session since there are no attached video objects");
                            c.c(this.b);
                        }
                    }
                }
            });
        }

        static /* synthetic */ void c(c x0) {
            com.facebook.infer.annotation.a.a(com.microsoft.skype.a.a.a(x0.b));
            x0.c.a();
        }
    }

    public RNSlimcoreModule(ag reactContext, WeakReference<VideoViewManagerProvider> videoViewManagerProvider, LogsProvider skyLibLogsProvider, LogsProvider mediaLogsProvider) {
        super(reactContext);
        this.reactContext = reactContext;
        this.videoViewManagerProvider = videoViewManagerProvider;
        this.skyLibEventHandler = new b();
        this.skyLibLogsProvider = skyLibLogsProvider;
        this.mediaLogsProvider = mediaLogsProvider;
        this.skyLibManager = new SkyLibManager(reactContext.getApplicationContext());
        this.contentSharingListener = new a();
        this.contentSharingMap = new HashMap();
        this.screenShareResourceManager = new c();
        new Handler(reactContext.getMainLooper()).postDelayed(new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.skyLibManager.a(new SkyLibExecution(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = this$1;
                    }

                    public final void a(SkyLib skyLib) {
                        FLog.i(RNSlimcoreModule.RN_CLASS, "Clearing old logs");
                        this.a.a.skyLibManager.a(this.a.a.skyLibLogsProvider);
                    }
                });
            }
        }, TimeUnit.SECONDS.toMillis(10));
    }

    public String getName() {
        return RN_CLASS;
    }

    public Map<String, Object> getConstants() {
        com.facebook.react.common.e.a<String, Object> builder = e.a();
        builder.a("ObjectChangedEventName", "RNSlimcore-SlimCoreEvent");
        builder.a("QualityChangedEventName", "RNSlimcore-QualityChangedEvent");
        builder.a("PushNotificationEventName", "RNSlimcore-SlimCorePushNotificationEvent");
        builder.a("VideoBindingChangedEventName", "RNSlimcore-VideoBindingChangedEvent");
        builder.a("VideoSizeChangedEventName", "RNSlimcore-VideoSizeChangedEvent");
        builder.a("DevicesChangedEventName", "RNSlimcore-DevicesChangedEvent");
        builder.a("SkypeTokenRequiredEventName", "RNSlimcore-SkypeTokenRequiredEvent");
        builder.a("NudgeParticipantsOperationStatusChangedEventName", "RNSlimcore-NudgeParticipantsOperationStatusChangedEvent");
        builder.a("CallTransferEventName", "RNSlimcore-CallTransferEvent");
        builder.a("AudioStreamStateChangedEventName", "RNSlimcore-AudioStreamStateChangedEvent");
        builder.a("ActiveSpeakerListChangedEventName", "RNSlimcore-ActiveSpeakerListChangedEvent");
        builder.a("DominantSpeakerListChangedEventName", "RNSlimcore-DominantSpeakerListChangedEvent");
        builder.a("JoinContentSharingResultEventName", "RNSlimcore-JoinContentSharingResultEvent");
        builder.a("TakeContentSharingControlResultEventName", "RNSlimcore-TakeContentSharingControlResultEvent");
        builder.a("UpdateContentSharingParticipantStateResultEventName", "RNSlimcore-UpdateContentSharingParticipantStateResultEvent");
        builder.a("UpdateContentSharingSessionStateResultEventName", "RNSlimcore-UpdateContentSharingSessionStateResultEvent");
        return builder.a();
    }

    public SkyLibManager skyLibManager() {
        return this.skyLibManager;
    }

    @ReactMethod
    public void getNodeId(final ae promise) {
        this.skyLibManager.a("getNodeId");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                promise.a(RNSlimcoreModule.unsignedLongAsString(Spl.getSysInfoNodeID()));
            }
        });
    }

    private static String unsignedLongAsString(long nodeId) {
        return nodeId >= 0 ? String.valueOf(nodeId) : BigInteger.valueOf((nodeId - Long.MIN_VALUE) + 1).add(BigInteger.valueOf(Long.MAX_VALUE)).toString();
    }

    @ReactMethod
    public void getRegistrationId(final ae promise) {
        this.skyLibManager.b("getRegistrationId");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                promise.a(skyLib.getRegistrationId());
            }
        });
    }

    @ReactMethod
    public void getFingerprintId(final ae promise) {
        this.skyLibManager.a("getFingerPrintId");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void setupSetStr(final String key, final String value) {
        this.skyLibManager.a("setupSetStr");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                skyLib.getSetup().setStr(key, value);
            }
        });
    }

    @ReactMethod
    public void setupSetInt(final String key, final int value) {
        this.skyLibManager.a("setupSetInt");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                skyLib.getSetup().setInt(key, value);
            }
        });
    }

    @ReactMethod
    public void setupDelete(final String key) {
        this.skyLibManager.a("setupDelete");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                skyLib.getSetup().delete(key);
            }
        });
    }

    @ReactMethod
    public void setupGetStr(final String key, final ae promise) {
        this.skyLibManager.a("setupGetStr");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                promise.a(skyLib.getSetup().getStr(key));
            }
        });
    }

    @ReactMethod
    public void setupGetInt(final String key, final ae promise) {
        this.skyLibManager.a("setupGetStr");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                promise.a(Integer.valueOf(skyLib.getSetup().getInt(key)));
            }
        });
    }

    @ReactMethod
    public void initialize(int platform, String version, boolean enableLogs, boolean encryptLogs, final ae promise) {
        this.skyLibManager.a(this.skyLibEventHandler);
        this.skyLibManager.a(platform, version, enableLogs, encryptLogs, this.reactContext.getApplicationContext(), new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void start(final ae promise) {
        this.skyLibManager.a("start");
        this.skyLibManager.a(this.reactContext.getApplicationContext(), new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void terminate(final ae promise) {
        this.skyLibManager.b(new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                this.b.skyLibManager.a();
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void login(String userId, String token, String displayName, final ae promise) {
        this.skyLibManager.b("login");
        this.skyLibManager.a(userId, token, displayName, new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void logout(final ae promise) {
        if (this.skyLibManager.b()) {
            this.skyLibManager.a(new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(null);
                }
            });
            return;
        }
        FLog.i(RN_CLASS, "SkyLib is not initialized, skipping logout");
        promise.a(null);
    }

    @ReactMethod
    public void updateSkypeToken(String token, final ae promise) {
        this.skyLibManager.c("updateSkypeToken");
        this.skyLibManager.a(token, new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(null);
            }
        }, new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(new SkyLibException("Failed to update Skype token"));
            }
        });
    }

    @ReactMethod
    public void setEcsConfig(String ecsBlob, String userIdentity, String etag, boolean isCached, ae promise) {
        this.skyLibManager.a("setEcsConfig");
        final String str = ecsBlob;
        final String str2 = userIdentity;
        final String str3 = etag;
        final boolean z = isCached;
        final ae aeVar = promise;
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule f;

            public final void a(SkyLib skyLib) {
                skyLib.setEcsConfig(str != null ? str : "", str2, str3 != null ? str3 : "", z);
                aeVar.a(null);
            }
        });
    }

    @ReactMethod
    public void getEcsQueryParameters(final ae promise) {
        this.skyLibManager.a("getEcsQueryParameters");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                promise.a(skyLib.getEcsQueryParameters());
            }
        });
    }

    @ReactMethod
    public void setLocalPreviewEnabledAndroid(boolean localPreviewEnabledAndroid) {
        this.localPreviewEnabledAndroid = localPreviewEnabledAndroid;
    }

    @ReactMethod
    public void handlePushNotification(int eventType, String gp, String correlationIds, String callKey, int connectionType, ae promise) {
        this.skyLibManager.c("handlePushNotification");
        final int i = eventType;
        final String str = gp;
        final String str2 = correlationIds;
        final String str3 = callKey;
        final int i2 = connectionType;
        final ae aeVar = promise;
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule g;

            public final void a(SkyLib skyLib) {
                int token = skyLib.handlePushNotification(i, new byte[0], new byte[0], str, str2, str3 != null ? str3 : "", i2);
                FLog.i(RNSlimcoreModule.RN_CLASS, "handlePushNotification: Begin for token %d", Integer.valueOf(token));
                aeVar.a(Integer.valueOf(token));
            }
        });
    }

    @ReactMethod
    public void fireIntent(final int intent, final String identity) {
        this.skyLibManager.c("fireIntent");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                skyLib.fireIntent(INTENT.fromInt(intent), identity != null ? identity : "");
            }
        });
    }

    @ReactMethod
    public void setMediaConfig(final String configuration) {
        this.skyLibManager.a("setMediaConfig");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                skyLib.setMediaConfig(configuration);
            }
        });
    }

    @ReactMethod
    public void getMediaStatus(final ae promise) {
        this.skyLibManager.a("getMediaStatus");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                promise.a(Integer.valueOf(skyLib.getMediaStatus().toInt()));
            }
        });
    }

    @ReactMethod
    public void changeOperationMode(final int nal) {
        this.skyLibManager.c("changeOperationMode");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                skyLib.changeOperationMode(nal);
            }
        });
    }

    @ReactMethod
    public void getAllLogFiles(ae promise) {
        List<File> result = this.skyLibLogsProvider.b();
        result.addAll(this.mediaLogsProvider.b());
        Object fileNames = new WritableNativeArray();
        if (result.size() == 0) {
            FLog.w(RN_CLASS, "getAllLogFiles: No log files found");
            promise.a(fileNames);
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            fileNames.pushString(((File) result.get(i)).getAbsolutePath());
        }
        promise.a(fileNames);
    }

    @ReactMethod
    public void getMostRecentLogFile(ae promise) {
        File result = this.skyLibLogsProvider.a();
        if (result == null) {
            FLog.w(RN_CLASS, "getMostRecentLogFile: No log files found");
            promise.a(null);
            return;
        }
        promise.a(result.getAbsolutePath());
    }

    @ReactMethod
    public void provideCallQualityFeedback(String callId, String participantId, String questionaryId, String trackingReason, int rating, String problemTokens, ae promise) {
        this.skyLibManager.c("provideCallQualityFeedback");
        final String str = callId;
        final String str2 = participantId;
        final String str3 = questionaryId;
        final String str4 = trackingReason;
        final int i = rating;
        final String str5 = problemTokens;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule h;

            public final void a(CallHandler callHandler) {
                String str;
                String str2 = str;
                String str3 = str2 != null ? str2 : "";
                String str4 = str3 != null ? str3 : "";
                String str5 = str4 != null ? str4 : "";
                QUALITYRATING fromInt = QUALITYRATING.fromInt(i);
                if (str5 != null) {
                    str = str5;
                } else {
                    str = "";
                }
                callHandler.provideCallQualityFeedback(str2, str3, str4, str5, fromInt, str);
                aeVar.a(null);
            }
        });
    }

    @ReactMethod
    public void placeCall(String callGuid, int mediaPeerType, al participantsArray, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetadata, String onBehalfOf, boolean enableLightWeightMeeting, String emergencyContent, ae promise) {
        this.skyLibManager.c("placeCall");
        final String[] participants = new String[participantsArray.size()];
        for (int i = 0; i < participantsArray.size(); i++) {
            participants[i] = participantsArray.getString(i);
        }
        final String str = callGuid;
        final int i2 = mediaPeerType;
        final boolean z = isVideoEnabled;
        final boolean z2 = isGoLive;
        final boolean z3 = allowHostless;
        final boolean z4 = enableGroupCallMeetupGeneration;
        final String str2 = threadId;
        final String str3 = messageId;
        final String str4 = subject;
        final String str5 = conversationType;
        final String str6 = meetingInfo;
        final String str7 = endpointMetadata;
        final String str8 = onBehalfOf;
        final boolean z5 = enableLightWeightMeeting;
        final String str9 = emergencyContent;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule r;

            public final void a(CallHandler callHandler) {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                String str7;
                String str8 = str;
                MEDIA_PEER_TYPE fromInt = MEDIA_PEER_TYPE.fromInt(i2);
                String[] strArr = participants;
                boolean z = z;
                boolean z2 = z2;
                boolean z3 = z3;
                boolean z4 = z4;
                String str9 = str2 != null ? str2 : "";
                if (str3 != null) {
                    str = str3;
                } else {
                    str = "";
                }
                if (str4 != null) {
                    str2 = str4;
                } else {
                    str2 = "";
                }
                if (str5 != null) {
                    str3 = str5;
                } else {
                    str3 = "";
                }
                if (str6 != null) {
                    str4 = str6;
                } else {
                    str4 = "";
                }
                if (str7 != null) {
                    str5 = str7;
                } else {
                    str5 = "";
                }
                if (str8 != null) {
                    str6 = str8;
                } else {
                    str6 = "";
                }
                boolean z5 = z5;
                if (str9 != null) {
                    str7 = str9;
                } else {
                    str7 = "";
                }
                int callObjectId = callHandler.placeCall(str8, fromInt, strArr, z, z2, z3, z4, str9, str, str2, str3, str4, str5, str6, z5, str7);
                if (callObjectId != 0) {
                    aeVar.a(Integer.valueOf(callObjectId));
                } else {
                    aeVar.a(new SkyLibException("CallHandler.placeCall failed"));
                }
            }
        });
    }

    @ReactMethod
    public void startSignalingSession(String callGuid, al participantsArray, am callProperties, ae promise) {
        this.skyLibManager.c("startSignalingSession");
        final String[] participants = new String[participantsArray.size()];
        for (int i = 0; i < participantsArray.size(); i++) {
            participants[i] = participantsArray.getString(i);
        }
        final am amVar = callProperties;
        final String str = callGuid;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule e;

            public final void a(CallHandler callHandler) {
                SessionParametersImpl sessionParameters = new SessionParametersImpl();
                callHandler.createSessionParameters(sessionParameters);
                RNSlimcoreModule.populateSessionParameters(sessionParameters, amVar);
                int callObjectId = callHandler.startSignalingSession(str, MEDIA_PEER_TYPE.fromInt(amVar.getInt("mediaPeerType")), sessionParameters.getInMemObjectID(), participants);
                if (callObjectId != 0) {
                    aeVar.a(Integer.valueOf(callObjectId));
                } else {
                    aeVar.a(new SkyLibException("CallHandler.startSignalingSession failed"));
                }
            }
        });
    }

    @ReactMethod
    public void joinCall(String context, int mediaPeerType, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetadata, String onBehalfOf, boolean enableLightWeightMeeting, ae promise) {
        this.skyLibManager.c("joinCall");
        final String str = context;
        final int i = mediaPeerType;
        final boolean z = isVideoEnabled;
        final boolean z2 = isGoLive;
        final boolean z3 = allowHostless;
        final boolean z4 = enableGroupCallMeetupGeneration;
        final String str2 = threadId;
        final String str3 = messageId;
        final String str4 = subject;
        final String str5 = conversationType;
        final String str6 = meetingInfo;
        final String str7 = endpointMetadata;
        final String str8 = onBehalfOf;
        final boolean z5 = enableLightWeightMeeting;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule p;

            public final void a(CallHandler callHandler) {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                String str7 = str;
                MEDIA_PEER_TYPE fromInt = MEDIA_PEER_TYPE.fromInt(i);
                boolean z = z;
                boolean z2 = z2;
                boolean z3 = z3;
                boolean z4 = z4;
                String str8 = str2 != null ? str2 : "";
                if (str3 != null) {
                    str = str3;
                } else {
                    str = "";
                }
                if (str4 != null) {
                    str2 = str4;
                } else {
                    str2 = "";
                }
                if (str5 != null) {
                    str3 = str5;
                } else {
                    str3 = "";
                }
                if (str6 != null) {
                    str4 = str6;
                } else {
                    str4 = "";
                }
                if (str7 != null) {
                    str5 = str7;
                } else {
                    str5 = "";
                }
                if (str8 != null) {
                    str6 = str8;
                } else {
                    str6 = "";
                }
                int callObjectId = callHandler.joinCall(str7, fromInt, z, z2, z3, z4, str8, str, str2, str3, str4, str5, str6, z5);
                if (callObjectId != 0) {
                    aeVar.a(Integer.valueOf(callObjectId));
                } else {
                    aeVar.a(new SkyLibException("CallHandler.joinCall failed"));
                }
            }
        });
    }

    @ReactMethod
    public void joinSignalingSession(final String context, final am callProperties, final ae promise) {
        this.skyLibManager.c("joinSignalingSession");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                SessionParametersImpl sessionParameters = new SessionParametersImpl();
                callHandler.createSessionParameters(sessionParameters);
                RNSlimcoreModule.populateSessionParameters(sessionParameters, callProperties);
                int callObjectId = callHandler.joinSignalingSession(context, MEDIA_PEER_TYPE.fromInt(callProperties.getInt("mediaPeerType")), sessionParameters.getInMemObjectID());
                if (callObjectId != 0) {
                    promise.a(Integer.valueOf(callObjectId));
                } else {
                    promise.a(new SkyLibException("CallHandler.joinSignalingSession failed"));
                }
            }
        });
    }

    @ReactMethod
    public void subscribe(String joinContext, boolean isVideoEnabled, boolean isGoLive, boolean allowHostless, boolean enableGroupCallMeetupGeneration, String threadId, String messageId, String subject, String conversationType, String meetingInfo, String endpointMetadata, String onBehalfOf, boolean enableLightWeightMeeting, ae promise) {
        this.skyLibManager.c("subscribe");
        final String str = joinContext;
        final boolean z = isVideoEnabled;
        final boolean z2 = isGoLive;
        final boolean z3 = allowHostless;
        final boolean z4 = enableGroupCallMeetupGeneration;
        final String str2 = threadId;
        final String str3 = messageId;
        final String str4 = subject;
        final String str5 = conversationType;
        final String str6 = meetingInfo;
        final String str7 = endpointMetadata;
        final String str8 = onBehalfOf;
        final boolean z5 = enableLightWeightMeeting;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule o;

            public final void a(CallHandler callHandler) {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                String str7;
                String str8 = str;
                boolean z = z;
                boolean z2 = z2;
                boolean z3 = z3;
                boolean z4 = z4;
                if (str2 != null) {
                    str = str2;
                } else {
                    str = "";
                }
                if (str3 != null) {
                    str2 = str3;
                } else {
                    str2 = "";
                }
                if (str4 != null) {
                    str3 = str4;
                } else {
                    str3 = "";
                }
                if (str5 != null) {
                    str4 = str5;
                } else {
                    str4 = "";
                }
                if (str6 != null) {
                    str5 = str6;
                } else {
                    str5 = "";
                }
                if (str7 != null) {
                    str6 = str7;
                } else {
                    str6 = "";
                }
                if (str8 != null) {
                    str7 = str8;
                } else {
                    str7 = "";
                }
                int callObjectId = callHandler.subscribe(str8, z, z2, z3, z4, str, str2, str3, str4, str5, str6, str7, z5);
                if (callObjectId != 0) {
                    aeVar.a(Integer.valueOf(callObjectId));
                } else {
                    aeVar.a(new SkyLibException("CallHandler.subscribe failed"));
                }
            }
        });
    }

    @ReactMethod
    public void subscribeToSignalingSession(final String context, final am callProperties, final ae promise) {
        this.skyLibManager.c("subscribeToSignalingSession");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                SessionParametersImpl sessionParameters = new SessionParametersImpl();
                callHandler.createSessionParameters(sessionParameters);
                RNSlimcoreModule.populateSessionParameters(sessionParameters, callProperties);
                int callObjectId = callHandler.subscribeToSignalingSession(context, sessionParameters.getInMemObjectID());
                if (callObjectId != 0) {
                    promise.a(Integer.valueOf(callObjectId));
                } else {
                    promise.a(new SkyLibException("CallHandler.subscribeToSignalingSession failed"));
                }
            }
        });
    }

    @ReactMethod
    public void unsubscribe(final int callObjectId, final ae promise) {
        this.skyLibManager.c("unsubscribe");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                callHandler.unsubscribe(callObjectId);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void answerCall(final int callObjectId, final boolean withVideo, final ae promise) {
        this.skyLibManager.c("answerCall");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.answerCall(callObjectId, withVideo)) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.answerCall failed"));
                }
            }
        });
    }

    @ReactMethod
    public void leaveCall(final int callObjectId, final ae promise) {
        this.skyLibManager.c("leaveCall");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                callHandler.leaveCall(callObjectId);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void callGetParticipants(final int callObjectId, final ae promise) {
        this.skyLibManager.c("callGetParticipants");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                promise.a(com.facebook.react.bridge.b.a(callHandler.callGetParticipants(callObjectId).m_callParticipantObjectIds));
            }
        });
    }

    @ReactMethod
    public void addParticipant(int callObjectId, String participant, String threadId, String messageId, String additionalData, ae promise) {
        this.skyLibManager.c("addParticipant");
        final String str = threadId;
        final String str2 = messageId;
        final String str3 = additionalData;
        final int i = callObjectId;
        final String str4 = participant;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule g;

            public final void a(CallHandler callHandler) {
                AddParticipantParametersImpl parameters = new AddParticipantParametersImpl();
                callHandler.createAddParticipantParameters(parameters);
                parameters.setThreadId(str != null ? str : "");
                parameters.setMessageId(str2 != null ? str2 : "");
                parameters.setAdditionalData(str3 != null ? str3 : "");
                int participantId = callHandler.addParticipantToCall(i, str4, parameters.getInMemObjectID());
                if (participantId != 0) {
                    aeVar.a(Integer.valueOf(participantId));
                } else {
                    aeVar.a(new SkyLibException("CallHandler.addParticipant failed"));
                }
            }
        });
    }

    @ReactMethod
    public void removeParticipant(final int participantObjectId, final ae promise) {
        this.skyLibManager.c("removeParticipant");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                callHandler.removeParticipant(participantObjectId, REMOVE_ENDPOINT_SCOPE.fromInt(0));
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void nudgeParticipants(int callObjectId, al participantsArray, String threadId, String messageId, String additionalData, String context, ae promise) {
        this.skyLibManager.c("nudgeParticipants");
        final String[] participants = new String[participantsArray.size()];
        for (int i = 0; i < participantsArray.size(); i++) {
            participants[i] = participantsArray.getString(i);
        }
        final String str = threadId;
        final String str2 = messageId;
        final String str3 = additionalData;
        final int i2 = callObjectId;
        final String str4 = context;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule h;

            public final void a(CallHandler callHandler) {
                AddParticipantParametersImpl parameters = new AddParticipantParametersImpl();
                callHandler.createAddParticipantParameters(parameters);
                parameters.setThreadId(str != null ? str : "");
                parameters.setMessageId(str2 != null ? str2 : "");
                parameters.setAdditionalData(str3 != null ? str3 : "");
                aeVar.a(Boolean.valueOf(callHandler.nudgeParticipants(i2, parameters.getInMemObjectID(), participants, str4 != null ? str4 : "")));
            }
        });
    }

    @ReactMethod
    public void callUpdateEndpointMetadata(final int callObjectId, final String endpointMetadata, final ae promise) {
        this.skyLibManager.c("callUpdateEndpointMetadata");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.callUpdateEndpointMetaData(callObjectId, endpointMetadata)) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.callUpdateEndpointMetadata failed"));
                }
            }
        });
    }

    @ReactMethod
    public void callMute(final int callObjectId, final boolean mute, final ae promise) {
        this.skyLibManager.c("callMute");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.callMute(callObjectId, mute)) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.callMute failed"));
                }
            }
        });
    }

    @ReactMethod
    public void callMuteParticipants(int callObjectId, int muteScope, al participantsArray, ae promise) {
        this.skyLibManager.c("callMuteParticipants");
        final String[] participants = new String[participantsArray.size()];
        for (int i = 0; i < participantsArray.size(); i++) {
            participants[i] = participantsArray.getString(i);
        }
        final int i2 = callObjectId;
        final int i3 = muteScope;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule e;

            public final void a(CallHandler callHandler) {
                callHandler.callMuteParticipants(i2, MUTE_SCOPE.fromInt(i3), participants);
                aeVar.a(null);
            }
        });
    }

    @ReactMethod
    public void callHold(final int callObjectId, final boolean hold, final ae promise) {
        this.skyLibManager.c("callHold");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.callHold(callObjectId, hold)) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.callHold failed"));
                }
            }
        });
    }

    @ReactMethod
    public void callSendDTMF(final int callObjectId, final int dtmf, final ae promise) {
        this.skyLibManager.c("callSendDTMF");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.callSendDtmf(callObjectId, DTMF.fromInt(dtmf))) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.callSendDtmf failed"));
                }
            }
        });
    }

    @ReactMethod
    public void callAttachSendVideo(final int callObjectId, final int videoObjectId, final ae promise) {
        this.skyLibManager.c("callAttachSendVideo");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.callAttachSendVideo(callObjectId, videoObjectId)) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.callAttachSendVideo failed"));
                }
            }
        });
    }

    @ReactMethod
    public void callGetSendVideos(final int callObjectId, final ae promise) {
        this.skyLibManager.c("callGetSendVideos");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                promise.a(com.facebook.react.bridge.b.a(callHandler.callGetSendVideos(callObjectId).m_sendVideoObjectIds));
            }
        });
    }

    @ReactMethod
    public void callGetParticipantVideos(final int callParticipantObjectId, final ae promise) {
        this.skyLibManager.c("callGetParticipantVideos");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                promise.a(com.facebook.react.bridge.b.a(callHandler.callGetParticipantVideos(callParticipantObjectId).m_participantVideosObjectId));
            }
        });
    }

    @ReactMethod
    public void callParticipantGetCallObject(final int callParticipantObjectId, final ae promise) {
        this.skyLibManager.c("callParticipantGetCallObject");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                int callObjectId = callHandler.callParticipantGetCallObject(callParticipantObjectId);
                if (callObjectId != 0) {
                    promise.a(Integer.valueOf(callObjectId));
                } else {
                    promise.a(new SkyLibException("CallHandler.callParticipantGetCallObject failed"));
                }
            }
        });
    }

    @ReactMethod
    public void getDebugInformation(final String command, final ae promise) {
        this.skyLibManager.c("getDebugInformation");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                promise.a(callHandler.getDebugInformation(command));
            }
        });
    }

    @ReactMethod
    public void callGetTechnicalInformationJson(final int callObjectId, final ae promise) {
        this.skyLibManager.c("callGetTechnicalInformationJson");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                promise.a(callHandler.callGetTechnicalInformationJson(callObjectId));
            }
        });
    }

    @ReactMethod
    public void getIntProperty(final int objectId, final int property, final ae promise) {
        this.skyLibManager.b("getIntProperty");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                promise.a(Integer.valueOf(callHandler.getIntegerProperty(objectId, PROPKEY.fromInt(property))));
            }
        });
    }

    @ReactMethod
    public void getStrProperty(final int objectId, final int property, final ae promise) {
        this.skyLibManager.b("getStrProperty");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                promise.a(callHandler.getStringProperty(objectId, PROPKEY.fromInt(property)));
            }
        });
    }

    @ReactMethod
    public void getBatchIntProperty(final al objectIds, final al properties, final ae promise) {
        this.skyLibManager.b("getIntProperty");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                Object result = new WritableNativeMap();
                for (int i = 0; i < objectIds.size(); i++) {
                    int objectId = objectIds.getInt(i);
                    ar propertyValues = new WritableNativeMap();
                    for (int j = 0; j < properties.size(); j++) {
                        int property = properties.getInt(j);
                        propertyValues.putInt(Integer.toString(property), callHandler.getIntegerProperty(objectId, PROPKEY.fromInt(property)));
                    }
                    result.putMap(Integer.toString(objectId), propertyValues);
                }
                promise.a(result);
            }
        });
    }

    @ReactMethod
    public void getBatchStrProperty(final al objectIds, final al properties, final ae promise) {
        this.skyLibManager.b("getStrProperty");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                Object result = new WritableNativeMap();
                for (int i = 0; i < objectIds.size(); i++) {
                    int objectId = objectIds.getInt(i);
                    ar propertyValues = new WritableNativeMap();
                    for (int j = 0; j < properties.size(); j++) {
                        int property = properties.getInt(j);
                        propertyValues.putString(Integer.toString(property), callHandler.getStringProperty(objectId, PROPKEY.fromInt(property)));
                    }
                    result.putMap(Integer.toString(objectId), propertyValues);
                }
                promise.a(result);
            }
        });
    }

    @ReactMethod
    public void callAssimilate(int callObjectId1, int callObjectId2, String threadId, String messageId, ae promise) {
        this.skyLibManager.c("callAssimilate");
        final int i = callObjectId1;
        final int i2 = callObjectId2;
        final String str = threadId;
        final String str2 = messageId;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule f;

            public final void a(CallHandler callHandler) {
                if (callHandler.callAssimilate(i, i2, str != null ? str : "", str2 != null ? str2 : "")) {
                    aeVar.a(null);
                } else {
                    aeVar.a(new SkyLibException("CallHandler.callAssimilate failed"));
                }
            }
        });
    }

    @ReactMethod
    public void startCallTransfer(final int callObjectId, final String participantId, final ae promise) {
        this.skyLibManager.c("startCallTransfer");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.startCallTransfer(callObjectId, participantId != null ? participantId : "")) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.startCallTransfer failed"));
                }
            }
        });
    }

    @ReactMethod
    public void startConsultativeCallTransfer(final int consultativeCallObjectId, final int transfereeCallObjectId, final ae promise) {
        this.skyLibManager.c("startConsultativeCallTransfer");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                if (callHandler.startConsultativeCallTransfer(consultativeCallObjectId, transfereeCallObjectId)) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("CallHandler.startConsultativeCallTransfer failed"));
                }
            }
        });
    }

    @ReactMethod
    public void startTransferTargetCall(int callObjectId, boolean isVideoEnabled, String threadId, String messageId, ae promise) {
        this.skyLibManager.c("startTransferTargetCall");
        final int i = callObjectId;
        final boolean z = isVideoEnabled;
        final String str = threadId;
        final String str2 = messageId;
        final ae aeVar = promise;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule f;

            public final void a(CallHandler callHandler) {
                if (callHandler.startTransferTargetCall(i, z, str != null ? str : "", str2 != null ? str2 : "")) {
                    aeVar.a(null);
                } else {
                    aeVar.a(new SkyLibException("CallHandler.startTransferTargetCall failed"));
                }
            }
        });
    }

    @ReactMethod
    public void endCallForAll(final int callObjectId, final ae promise) {
        this.skyLibManager.c("endCall");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                callHandler.endCallForAll(callObjectId);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void rejectLocally(final int callObjectId, final ae promise) {
        this.skyLibManager.c("rejectCallLocally");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                callHandler.rejectLocally(callObjectId);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void callShareSystemSound(final int callObjectId, final boolean enable, final ae promise) {
        this.skyLibManager.c("callShareSystemSound");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(CallHandler callHandler) {
                promise.a(Boolean.valueOf(callHandler.callShareSystemSound(callObjectId, enable)));
            }
        });
    }

    @ReactMethod
    public void enableTtySupport(final boolean enable, final ae promise) {
        this.skyLibManager.c("enableTtySupport");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                skyLib.enableTtySupport(enable);
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void enableAgc(boolean enable, ae promise) {
        promise.a(new SkyLibException("enableAgc not supported"));
    }

    @ReactMethod
    public void getMicrophoneDevices(ae promise) {
        promise.a(new SkyLibException("getMicrophoneDevices not supported"));
    }

    @ReactMethod
    public void getSpeakerDevices(ae promise) {
        promise.a(new SkyLibException("getSpeakerDevices not supported"));
    }

    @ReactMethod
    public void getCompositeAudioDevices(ae promise) {
        promise.a(new SkyLibException("getCompositeAudioDevices not supported"));
    }

    @ReactMethod
    public void selectAudioDevices(String microphone, String speaker, ae promise) {
        promise.a(new SkyLibException("selectAudioDevices not supported"));
    }

    @ReactMethod
    public void getSpeakerVolume(ae promise) {
        promise.a(new SkyLibException("getSpeakerVolume not supported"));
    }

    @ReactMethod
    public void getSpeakerSystemVolume(ae promise) {
        promise.a(new SkyLibException("getSpeakerSystemVolume not supported"));
    }

    @ReactMethod
    public void setSpeakerVolume(int volume, ae promise) {
        promise.a(new SkyLibException("setSpeakerVolume not supported"));
    }

    @ReactMethod
    public void setSpeakerSystemVolume(int volume, ae promise) {
        promise.a(new SkyLibException("setSpeakerSystemVolume not supported"));
    }

    @ReactMethod
    public void getMicrophoneVolume(ae promise) {
        promise.a(new SkyLibException("getMicrophoneVolume not supported"));
    }

    @ReactMethod
    public void setMicrophoneVolume(int volume, ae promise) {
        promise.a(new SkyLibException("setMicrophoneVolume not supported"));
    }

    @ReactMethod
    public void unmuteSpeaker(ae promise) {
        promise.a(new SkyLibException("unmuteSpeaker not supported"));
    }

    @ReactMethod
    public void unmuteMicrophone(ae promise) {
        promise.a(new SkyLibException("unmuteMicrophone not supported"));
    }

    @ReactMethod
    public void getNrgLevelsForDeviceTuner(String microphone, ae promise) {
        promise.a(new SkyLibException("getNrgLevelsForDeviceTuner not supported"));
    }

    @ReactMethod
    public void getDeviceEffectsCapability(String deviceId, int mask, ae promise) {
        promise.a(new SkyLibException("getDeviceEffectsCapability not supported"));
    }

    @ReactMethod
    public void setDeviceEffects(String deviceId, int effectType, ae promise) {
        promise.a(new SkyLibException("setDeviceEffects not supported"));
    }

    @ReactMethod
    public void getVideoDevices(final ae promise) {
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void a(SkyLib skyLib) {
                aq devices = SkyLibManager.a(skyLib);
                Object result = new WritableNativeMap();
                result.putArray("devices", devices);
                promise.a(result);
            }
        });
    }

    private void createLocalVideoWithSkyLib(int mediaType, String name, String path, ae promise) {
        FLog.d(RN_CLASS, "createLocalVideoWithSkyLib mediaType=%d", Integer.valueOf(mediaType));
        final int i = mediaType;
        final String str = name;
        final String str2 = path;
        final ae aeVar = promise;
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule e;

            public final void a(SkyLib skyLib) {
                int objectId = skyLib.createLocalVideo(MEDIATYPE.fromInt(i), str, str2);
                FLog.d(RNSlimcoreModule.RN_CLASS, "SkyLib.createLocalVideo object ID %d", Integer.valueOf(objectId));
                if (objectId != 0) {
                    this.e.screenShareResourceManager.a(i, objectId);
                    aeVar.a(Integer.valueOf(objectId));
                    return;
                }
                aeVar.a(new SkyLibException(String.format("SkyLib.createLocalVideo failed for %s/%s", new Object[]{str, str2})));
            }
        });
    }

    @ReactMethod
    public void createLocalVideo(int mediaType, String name, String path, ae promise) {
        FLog.d(RN_CLASS, "createLocalVideo mediaType=%d", Integer.valueOf(mediaType));
        this.skyLibManager.c("createLocalVideo");
        if (mediaType == MEDIATYPE.MEDIA_SCREENSHARING.toInt()) {
            createScreenShareVideo(mediaType, name, path, promise);
        } else {
            createLocalVideoWithSkyLib(mediaType, name, path, promise);
        }
    }

    @ReactMethod
    public void createPreviewVideo(final String name, final String path, final ae promise) {
        this.skyLibManager.c("createPreviewVideo");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule d;

            public final void a(SkyLib skyLib) {
                int objectId = skyLib.createPreviewVideo(MEDIATYPE.MEDIA_VIDEO, name, path);
                if (objectId != 0) {
                    promise.a(Integer.valueOf(objectId));
                    return;
                }
                promise.a(new SkyLibException(String.format("SkyLib.createPreviewVideo failed for %s/%s", new Object[]{name, path})));
            }
        });
    }

    @ReactMethod
    public void startVideo(final int videoObjectId, final ae promise) {
        FLog.d(RN_CLASS, "startVideo videoObjectId=%d", Integer.valueOf(videoObjectId));
        this.skyLibManager.c("startVideo");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                Video video = new VideoImpl();
                if (skyLib.getVideo(videoObjectId, video)) {
                    video.start();
                    promise.a(null);
                    return;
                }
                promise.a(new SkyLibException("Failed to start video: could not find ID " + videoObjectId));
            }
        });
    }

    @ReactMethod
    public void stopVideo(final int videoObjectId, final ae promise) {
        FLog.d(RN_CLASS, "stopVideo videoObjectId=%d", Integer.valueOf(videoObjectId));
        this.skyLibManager.c("stopVideo");
        this.screenShareResourceManager.a(videoObjectId);
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                Video video = new VideoImpl();
                if (skyLib.getVideo(videoObjectId, video)) {
                    video.stop();
                    promise.a(null);
                    return;
                }
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void terminateVideo(int videoObjectId, boolean local, final ae promise) {
        this.skyLibManager.c("terminateVideo");
        if (!local) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).e().detachVideo(videoObjectId, new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(null);
                }
            }, new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(new SkyLibException("RNVideoViewManager.detachVideo failed"));
                }
            });
        } else if (!this.localPreviewEnabledAndroid) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).f().detachVideo(videoObjectId, new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(null);
                }
            }, new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(new SkyLibException("RNVideoViewLocalManager.detachVideo failed"));
                }
            });
        } else if (((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().hasVideoAttachedToView(videoObjectId)) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().detachVideo(videoObjectId, new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(null);
                }
            }, new Runnable(this) {
                final /* synthetic */ RNSlimcoreModule b;

                public final void run() {
                    promise.a(new SkyLibException("RNVideoViewPreviewManager.detachVideo failed"));
                }
            });
        } else {
            promise.a(null);
            FLog.i(RN_CLASS, "terminateVideo noop due to %d not found at videoViewPreviewManager", Integer.valueOf(videoObjectId));
        }
    }

    @ReactMethod
    public void getMonitorList(ae promise) {
        this.skyLibManager.a("getMonitorList");
        Display defaultDisplay = ((WindowManager) getReactApplicationContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        ar region = new WritableNativeMap();
        region.putInt("x", 0);
        region.putInt("y", 0);
        region.putInt("width", displayMetrics.widthPixels);
        region.putInt("height", displayMetrics.heightPixels);
        ar monitor = new WritableNativeMap();
        monitor.putInt("monitorId", defaultDisplay.getDisplayId());
        monitor.putString("name", defaultDisplay.getName());
        monitor.putMap("region", region);
        monitor.putBoolean("isPrimary", true);
        monitor.putBoolean("isInternal", true);
        monitor.putBoolean("isDuplicated", false);
        Object monitors = new WritableNativeArray();
        monitors.pushMap(monitor);
        promise.a(monitors);
    }

    @ReactMethod
    public void getMonitorPreview(int monitorId, int width, int height, boolean asImage, ae promise) {
        promise.a(new SkyLibException("getMonitorPreview not supported"));
    }

    @ReactMethod
    public void setScreenCaptureRectangle(int videoObjectId, int monitorId, int x0, int y0, int width, int height, ae promise) {
        FLog.d(RN_CLASS, "setScreenCaptureRectangle videoObjectId=%d x0=%d y0=%d width=%d height=%d", Integer.valueOf(videoObjectId), Integer.valueOf(x0), Integer.valueOf(y0), Integer.valueOf(width), Integer.valueOf(height));
        this.skyLibManager.c("setScreenCaptureRectangle");
        promise.a(null);
    }

    private void createScreenShareVideo(int mediaType, String name, String path, final ae promise) {
        FLog.d(RN_CLASS, "createScreenShareVideo mediaType=%d", Integer.valueOf(mediaType));
        final int i = mediaType;
        final String str = name;
        final String str2 = path;
        final ae aeVar = promise;
        this.screenShareResourceManager.a(new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule e;

            public final void run() {
                this.e.createLocalVideoWithSkyLib(i, str, str2, aeVar);
            }
        }, new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(new SkyLibException("ScreenShareManager.startScreenShare failure"));
            }
        });
    }

    @ReactMethod
    public void presentOngoingCallBanner(String threadId, String callId, int callObjectId, String message, String hangupAction, boolean showMuteAction, String muteAction, boolean isMuted) {
        this.skyLibManager.c("presentOngoingCallBanner");
        Bundle bundle = new Bundle();
        bundle.putString(RNCallingService.f, threadId);
        bundle.putString(RNCallingService.g, callId);
        bundle.putInt(RNCallingService.h, callObjectId);
        bundle.putString(RNCallingService.i, message);
        bundle.putString(RNCallingService.j, hangupAction);
        bundle.putBoolean(RNCallingService.k, showMuteAction);
        if (showMuteAction) {
            bundle.putString(RNCallingService.l, muteAction);
            bundle.putBoolean(RNCallingService.m, isMuted);
        }
        sendToService(RNCallingService.a, bundle);
    }

    @ReactMethod
    public void removeOngoingCallBanner() {
        this.skyLibManager.c("removeOngoingCallBanner");
        sendToService(RNCallingService.b, null);
    }

    private void sendToService(String action, Bundle params) {
        try {
            Context context = getReactApplicationContext();
            Intent intent = new Intent(context, RNCallingService.class);
            intent.setAction(action);
            if (params != null) {
                intent.putExtras(params);
            }
            context.startService(intent);
        } catch (Throwable ex) {
            FLog.e(RN_CLASS, ex, "Failed sending action %s to service", action);
        }
    }

    @ReactMethod
    public void initializeStillCapture(int videoObjectId, final ae promise) {
        this.skyLibManager.c("initializeStillCapture");
        Runnable successRunnable = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(null);
            }
        };
        Runnable failureRunnable = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(new SkyLibException("RNVideoViewLocalManager.captureStill failed"));
            }
        };
        if (this.localPreviewEnabledAndroid) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().initializeStillCapture(videoObjectId, successRunnable, failureRunnable);
        } else {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).f().initializeStillCapture(videoObjectId, successRunnable, failureRunnable);
        }
    }

    @ReactMethod
    public void captureStillWithId(int videoObjectId, boolean flip, boolean cropToSquare, final ae promise) {
        this.skyLibManager.c("captureStillWithId");
        Action1<am> action = new Action1<am>(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final /* bridge */ /* synthetic */ void a(Object obj) {
                promise.a((am) obj);
            }
        };
        Runnable runnable = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(new SkyLibException("RNVideoViewLocalManager.captureStill failed"));
            }
        };
        if (this.localPreviewEnabledAndroid) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().captureStill(videoObjectId, flip, cropToSquare, action, runnable);
        } else {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).f().captureStill(videoObjectId, flip, cropToSquare, action, runnable);
        }
    }

    @ReactMethod
    public void captureStill(boolean flip, boolean cropToSquare, final ae promise) {
        this.skyLibManager.c("captureStill");
        Action1<am> action = new Action1<am>(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final /* bridge */ /* synthetic */ void a(Object obj) {
                promise.a((am) obj);
            }
        };
        Runnable runnable = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(new SkyLibException("RNVideoViewLocalManager.captureStill failed"));
            }
        };
        if (this.localPreviewEnabledAndroid) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().captureStillDeprecated(flip, cropToSquare, action, runnable);
        } else {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).f().captureStillDeprecated(flip, cropToSquare, action, runnable);
        }
    }

    @ReactMethod
    public void tearDownStillCapture(int videoObjectId, final ae promise) {
        this.skyLibManager.c("tearDownStillCapture");
        Runnable successRunnable = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(null);
            }
        };
        Runnable failureRunnable = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule b;

            public final void run() {
                promise.a(new SkyLibException("RNVideoViewLocalManager.captureStill failed"));
            }
        };
        if (this.localPreviewEnabledAndroid) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().tearDownStillCapture(videoObjectId, successRunnable, failureRunnable);
        } else {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).f().tearDownStillCapture(videoObjectId, successRunnable, failureRunnable);
        }
    }

    @ReactMethod
    public void captureFrame(int videoObjectId, boolean local, int timeoutMs, final ae promise) {
        this.skyLibManager.c("captureFrame");
        final int causeId = random.nextInt();
        FLog.i(RN_CLASS, "captureFrame: causeId %x, local %b, timeoutMs %d", Integer.valueOf(causeId), Boolean.valueOf(local), Integer.valueOf(timeoutMs));
        Action1<am> success = new Action1<am>(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final /* synthetic */ void a(Object obj) {
                obj = (am) obj;
                FLog.i(RNSlimcoreModule.RN_CLASS, "captureFrame: resolve causeId %x", Integer.valueOf(causeId));
                promise.a(obj);
            }
        };
        Runnable failure = new Runnable(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void run() {
                FLog.i(RNSlimcoreModule.RN_CLASS, "captureFrame: fail causeId %x", Integer.valueOf(causeId));
                promise.a(new SkyLibException("RNSlimcoreModule.captureFrame failed"));
            }
        };
        if (!local) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).e().captureFrame(videoObjectId, success, failure, causeId);
        } else if (this.localPreviewEnabledAndroid) {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).g().captureFrame(videoObjectId, success, failure, causeId);
        } else {
            ((VideoViewManagerProvider) this.videoViewManagerProvider.get()).f().captureFrame(videoObjectId, success, failure, causeId);
        }
    }

    @ReactMethod
    public void createContentSharing(int callObjectId, String contentSharingGuid, String contentSharingIdentity, String subject, String initialContentSharingSessionState, ae promise) {
        this.skyLibManager.c("createContentSharing");
        final ae aeVar = promise;
        final int i = callObjectId;
        final String str = contentSharingGuid;
        final String str2 = contentSharingIdentity;
        final String str3 = subject;
        final String str4 = initialContentSharingSessionState;
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule g;

            public final void a(CallHandler callHandler) {
                aeVar.a(Integer.valueOf(callHandler.createContentSharing(i, str != null ? str : "", str2 != null ? str2 : "", str3 != null ? str3 : "", str4 != null ? str4 : "")));
            }
        });
    }

    @ReactMethod
    public void getContentSharingSessions(final int callObjectId, final ae promise) {
        this.skyLibManager.c("getContentSharingSessions");
        this.skyLibManager.a(new CallHandlerExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(CallHandler callHandler) {
                promise.a(com.facebook.react.bridge.b.a(callHandler.getContentSharingSessions(callObjectId).m_contentSharingObjectIds));
            }
        });
    }

    @ReactMethod
    public void startContentSharing(final int contentSharingObjectId, final ae promise) {
        this.skyLibManager.c("startContentSharing");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                if (((ContentSharing) this.c.contentSharingMap.get(Integer.valueOf(contentSharingObjectId))) != null) {
                    promise.a(null);
                    return;
                }
                ContentSharing cs = new ContentSharingImpl();
                if (skyLib.getContentSharing(contentSharingObjectId, cs)) {
                    this.c.contentSharingMap.put(Integer.valueOf(contentSharingObjectId), cs);
                    cs.addListener(this.c.contentSharingListener);
                    if (cs.startContentSharing()) {
                        promise.a(null);
                        return;
                    } else {
                        promise.a(new SkyLibException("Failed to start content sharing ID: " + contentSharingObjectId));
                        return;
                    }
                }
                promise.a(new SkyLibException("Failed to start content sharing: could not find ID " + contentSharingObjectId));
            }
        });
    }

    @ReactMethod
    public void stopContentSharing(final int contentSharingObjectId, final ae promise) {
        this.skyLibManager.c("stopContentSharing");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                ContentSharing cs = (ContentSharing) this.c.contentSharingMap.get(Integer.valueOf(contentSharingObjectId));
                if (cs == null) {
                    promise.a(new SkyLibException("Failed to stop content sharing: could not find started ID " + contentSharingObjectId));
                    return;
                }
                cs.stopContentSharing();
                promise.a(null);
            }
        });
    }

    @ReactMethod
    public void updateContentSharingSessionState(int contentSharingObjectId, String id, String sessionState, ae promise) {
        this.skyLibManager.c("updateContentSharingSessionState");
        final int i = contentSharingObjectId;
        final ae aeVar = promise;
        final String str = id;
        final String str2 = sessionState;
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule e;

            public final void a(SkyLib skyLib) {
                ContentSharing cs = (ContentSharing) this.e.contentSharingMap.get(Integer.valueOf(i));
                if (cs == null) {
                    aeVar.a(new SkyLibException("Failed to update content sharing session state: could not find started ID " + i));
                    return;
                }
                if (cs.updateContentSharingSessionState(str != null ? str : "", str2 != null ? str2 : "")) {
                    aeVar.a(null);
                } else {
                    aeVar.a(new SkyLibException("Failed to update content sharing session state for ID: " + i));
                }
            }
        });
    }

    @ReactMethod
    public void takeContentSharingControl(final int contentSharingObjectId, final ae promise) {
        this.skyLibManager.c("takeContentSharingControl");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                ContentSharing cs = (ContentSharing) this.c.contentSharingMap.get(Integer.valueOf(contentSharingObjectId));
                if (cs == null) {
                    promise.a(new SkyLibException("Failed to take control of content sharing: could not find started ID " + contentSharingObjectId));
                } else if (cs.takeContentSharingControl()) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("Failed to take control of content sharing ID: " + contentSharingObjectId));
                }
            }
        });
    }

    @ReactMethod
    public void updateContentSharingParticipantState(final int contentSharingObjectId, final ae promise) {
        this.skyLibManager.c("updateContentSharingParticipantState");
        this.skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNSlimcoreModule c;

            public final void a(SkyLib skyLib) {
                ContentSharing cs = (ContentSharing) this.c.contentSharingMap.get(Integer.valueOf(contentSharingObjectId));
                if (cs == null) {
                    promise.a(new SkyLibException("Failed to update content sharing participant state: could not find started ID " + contentSharingObjectId));
                } else if (cs.updateContentSharingParticipantState()) {
                    promise.a(null);
                } else {
                    promise.a(new SkyLibException("Failed to update content sharing participant state for ID: " + contentSharingObjectId));
                }
            }
        });
    }

    @ReactMethod
    public void createNdi(String localizedStrings, ae promise) {
        promise.a(new SkyLibException("Ndi is not supported"));
    }

    @ReactMethod
    public void updateNdiLocalizedStrings(String localizedStrings, ae promise) {
        promise.a(new SkyLibException("Ndi is not supported"));
    }

    @ReactMethod
    public void updateNdiSettings(String settings, ae promise) {
        promise.a(new SkyLibException("Ndi is not supported"));
    }

    @ReactMethod
    public void isNdiSupported(ae promise) {
        promise.a(Boolean.valueOf(false));
    }

    @ReactMethod
    public void isNdiCaptured(ae promise) {
        promise.a(Boolean.valueOf(false));
    }

    private void sendPropertyChangedEvent(int objectId, OBJECTTYPE objecttype, PROPKEY propkey, com.microsoft.skype.b.a<String, Integer> value, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %s %s %s %s", "RNSlimcore-SlimCoreEvent", objecttype, propkey, value, causeId);
        if (objecttype == OBJECTTYPE.CONTENTSHARING && propkey == PROPKEY.CONTENTSHARING_STATUS) {
            handleContentSharingStatusChange(objectId, ((Integer) value.b).intValue());
        }
        sendEvent("RNSlimcore-SlimCoreEvent", convertToReadableMap(objectId, objecttype, propkey, value, causeId));
    }

    private void sendPushHandlingEvent(int token, PUSHHANDLINGRESULT result) {
        FLog.i(RN_CLASS, "handlePushNotification: End for token %d %s", Integer.valueOf(token), (Object) result);
        FLog.v(RN_CLASS, "sendEvent %s %s %s", (Object) "RNSlimcore-SlimCorePushNotificationEvent", Integer.valueOf(token), (Object) result);
        ar map = new WritableNativeMap();
        map.putInt("token", token);
        map.putInt("result", result.toInt());
        sendEvent("RNSlimcore-SlimCorePushNotificationEvent", map);
    }

    private void sendQualityEvent(int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %s %s %s", "RNSlimcore-QualityChangedEvent", Integer.valueOf(objectId), type, level, mediaType, causeId);
        ar map = new WritableNativeMap();
        map.putInt("id", objectId);
        map.putInt("type", type.toInt());
        map.putInt("level", level.toInt());
        map.putInt("mediaType", mediaType.toInt());
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-QualityChangedEvent", map);
    }

    private void sendDevicesChangedEvent(String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %s", (Object) "RNSlimcore-DevicesChangedEvent", (Object) causeId);
        ar map = new WritableNativeMap();
        map.putBoolean("video", true);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-DevicesChangedEvent", map);
    }

    private void sendSkypeTokenRequiredEvent(String invalidToken, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %s", (Object) "RNSlimcore-SkypeTokenRequiredEvent", (Object) causeId);
        ar map = new WritableNativeMap();
        map.putString("invalidToken", invalidToken);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-SkypeTokenRequiredEvent", map);
    }

    private void sendNudgeParticipantsOperationStatusChangedEvent(int callObjectId, String context, int failureReason, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %d %s", "RNSlimcore-NudgeParticipantsOperationStatusChangedEvent", Integer.valueOf(callObjectId), context, Integer.valueOf(failureReason), causeId);
        ar map = new WritableNativeMap();
        map.putInt("callObjectId", callObjectId);
        map.putString("context", context);
        map.putInt("failureReason", failureReason);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-NudgeParticipantsOperationStatusChangedEvent", map);
    }

    private void sendAudioStreamStateChangedEvent(int objectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %s %s", "RNSlimcore-AudioStreamStateChangedEvent", Integer.valueOf(objectId), direction, streamState, causeId);
        ar map = new WritableNativeMap();
        map.putInt("id", objectId);
        map.putInt("direction", direction.toInt());
        map.putInt("streamState", streamState.toInt());
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-AudioStreamStateChangedEvent", map);
    }

    private void sendCallTransferEvent(int objectId, int targetCallObjectId, String transferorMri, String transferTargetMri, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %s", (Object) "RNSlimcore-CallTransferEvent", (Object) causeId);
        ar map = new WritableNativeMap();
        map.putInt("id", objectId);
        map.putInt("targetCallObjectId", targetCallObjectId);
        map.putString("transferorMri", transferorMri);
        map.putString("transferTargetMri", transferTargetMri);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-CallTransferEvent", map);
    }

    private void sendActiveSpeakerListChangedEvent(int callObjectId, String[] speakerList, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %d %s", (Object) "RNSlimcore-ActiveSpeakerListChangedEvent", Integer.valueOf(callObjectId), Integer.valueOf(speakerList.length), (Object) causeId);
        aq speakers = new WritableNativeArray();
        for (String pushString : speakerList) {
            speakers.pushString(pushString);
        }
        ar map = new WritableNativeMap();
        map.putInt("callObjectId", callObjectId);
        map.putArray("activeSpeakerList", speakers);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-ActiveSpeakerListChangedEvent", map);
    }

    private void sendDominantSpeakerListChangedEvent(int callObjectId, String[] speakerList, String causeId) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %d %s", (Object) "RNSlimcore-DominantSpeakerListChangedEvent", Integer.valueOf(callObjectId), Integer.valueOf(speakerList.length), (Object) causeId);
        aq speakers = new WritableNativeArray();
        for (String pushString : speakerList) {
            speakers.pushString(pushString);
        }
        ar map = new WritableNativeMap();
        map.putInt("callObjectId", callObjectId);
        map.putArray("dominantSpeakerList", speakers);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-DominantSpeakerListChangedEvent", map);
    }

    private void sendContentSharingResultEvent(String eventName, int objectId, FAILUREREASON failureReason, int code, int subCode) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %d %d %s", eventName, Integer.valueOf(objectId), failureReason.toString(), Integer.valueOf(code), Integer.valueOf(subCode), Integer.toString(random.nextInt()));
        ar map = new WritableNativeMap();
        map.putInt("objectId", objectId);
        map.putInt("failureReason", failureReason.toInt());
        map.putInt("code", code);
        map.putInt("subCode", subCode);
        map.putString("causeId", causeId);
        sendEvent(eventName, map);
    }

    private void sendContentSharingUpdateSessionStateResultEvent(int objectId, String commandId, FAILUREREASON failureReason, int code, int subCode) {
        FLog.v(RN_CLASS, "sendEvent %s %d %s %s %d %d %s", "RNSlimcore-UpdateContentSharingSessionStateResultEvent", Integer.valueOf(objectId), commandId, failureReason.toString(), Integer.valueOf(code), Integer.valueOf(subCode), Integer.toString(random.nextInt()));
        ar map = new WritableNativeMap();
        map.putInt("objectId", objectId);
        map.putString("id", commandId);
        map.putInt("failureReason", failureReason.toInt());
        map.putInt("code", code);
        map.putInt("subCode", subCode);
        map.putString("causeId", causeId);
        sendEvent("RNSlimcore-UpdateContentSharingSessionStateResultEvent", map);
    }

    private void handleContentSharingStatusChange(int objectId, int value) {
        if (value == STATUS.DONE.toInt() || value == STATUS.SHARING_FAILED.toInt() || value == STATUS.TIMED_OUT.toInt()) {
            ContentSharing cs = (ContentSharing) this.contentSharingMap.get(Integer.valueOf(objectId));
            if (cs != null) {
                cs.removeListener(this.contentSharingListener);
                this.contentSharingMap.remove(Integer.valueOf(objectId));
            }
        }
    }

    private void sendMediaStatusChangedEvent(MEDIASTATUS status) {
        FLog.v(RN_CLASS, "sendEvent %s %s", (Object) "RNSlimcore-MediaStatusChangedEvent", (Object) status);
        ar map = new WritableNativeMap();
        map.putInt("mediaStatus", status.toInt());
        sendEvent("RNSlimcore-MediaStatusChangedEvent", map);
    }

    private void sendEvent(String eventName, am map) {
        ((RCTNativeAppEventEmitter) this.reactContext.a(RCTNativeAppEventEmitter.class)).emit(eventName, map);
    }

    private static void populateSessionParameters(SessionParameters sessionParameters, am callProperties) {
        sessionParameters.setAllowHostless(getOptionalBoolean(callProperties, "isHostless"));
        sessionParameters.setConversationType(getOptionalString(callProperties, "conversationType"));
        sessionParameters.setEmergencyContent(getOptionalString(callProperties, "emergencyContent"));
        sessionParameters.setEnableGroupCallMeetupGeneration(getOptionalBoolean(callProperties, "enableGroupCallMeetupGeneration"));
        sessionParameters.setEnableLightWeightMeeting(getOptionalBoolean(callProperties, "enableLightWeightMeeting"));
        sessionParameters.setEndpointMetadata(getOptionalString(callProperties, "endpointMetadata"));
        sessionParameters.setIsGoLive(getOptionalBoolean(callProperties, "isGoLive"));
        sessionParameters.setIsVideoEnabled(getOptionalBoolean(callProperties, "isVideoEnabled"));
        sessionParameters.setMeetingInfo(getOptionalString(callProperties, "meetingInfo"));
        sessionParameters.setMessageId(getOptionalString(callProperties, "messageId"));
        sessionParameters.setOnBehalfOf(getOptionalString(callProperties, "onBehalfOf"));
        sessionParameters.setSubject(getOptionalString(callProperties, "subject"));
        sessionParameters.setParticipantLegId(getOptionalString(callProperties, "participantLegId"));
        sessionParameters.setThreadId(getOptionalString(callProperties, "threadId"));
        sessionParameters.setBroadcastContext(getOptionalString(callProperties, "broadcastContext"));
        sessionParameters.setCallKey(getOptionalString(callProperties, "callKey"));
        sessionParameters.setEncryptedKey(getOptionalString(callProperties, "encryptedKey"));
        sessionParameters.setConnectionType(getOptionalInt(callProperties, "connectionType"));
        sessionParameters.setInvitationType(getOptionalInt(callProperties, "invitationType"));
        sessionParameters.setMuteFlags(getOptionalInt(callProperties, "muteFlags"));
    }

    private static boolean getOptionalBoolean(am map, String key) {
        return map.hasKey(key) ? map.getBoolean(key) : false;
    }

    private static String getOptionalString(am map, String key) {
        String result = map.hasKey(key) ? map.getString(key) : "";
        return result != null ? result : "";
    }

    private static int getOptionalInt(am map, String key) {
        return map.hasKey(key) ? map.getInt(key) : 0;
    }

    private static am convertToReadableMap(int objectId, OBJECTTYPE objecttype, PROPKEY propkey, com.microsoft.skype.b.a<String, Integer> value, String causeId) {
        ar map = new WritableNativeMap();
        map.putInt("id", objectId);
        map.putInt("type", objecttype.toInt());
        map.putInt(PropertiesEntry.COLUMN_NAME_KEY, propkey.toInt());
        map.putString("causeId", causeId);
        if (value.a != null) {
            map.putString(PropertiesEntry.COLUMN_NAME_VALUE, (String) value.a);
        } else {
            map.putInt(PropertiesEntry.COLUMN_NAME_VALUE, ((Integer) value.b).intValue());
        }
        return map;
    }
}
