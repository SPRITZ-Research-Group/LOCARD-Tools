package com.skype.slimcore.skylib;

import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.microsoft.media.NGCPcmHost;
import com.skype.Account;
import com.skype.Account.STATUS;
import com.skype.AccountImpl;
import com.skype.CallHandler;
import com.skype.CallHandler.CallHandlerIListener;
import com.skype.CallHandler.MEDIA_DIRECTION;
import com.skype.CallHandler.MEDIA_STREAM_STATE;
import com.skype.CallHandlerImpl;
import com.skype.Metatag;
import com.skype.PROPKEY;
import com.skype.SkyLib;
import com.skype.SkyLib.GetAvailableVideoDevices_Result;
import com.skype.SkyLib.MEDIASTATUS;
import com.skype.SkyLib.OBJECTTYPE;
import com.skype.SkyLib.PUSHHANDLINGRESULT;
import com.skype.SkyLib.QUALITY_MEDIATYPE;
import com.skype.SkyLib.SkyLibIListener;
import com.skype.SkyLib.VIDEO_DEVICE_FACING;
import com.skype.SkyLib.VIDEO_DEVICE_TYPE;
import com.skype.android.gen.CallHandlerLogListener;
import com.skype.android.gen.SkyLibLogListener;
import com.skype.msrtc.QualityEventType;
import com.skype.msrtc.QualityLevel;
import com.skype.slimcore.logging.LogsProvider;
import com.skype.slimcore.skylib.SkyLibWrapper.SkyLibState;
import com.skype.slimcore.video.UnifiedVideoHostInitializer;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SkyLibManager {
    public static SkyLibManager a;
    private static Random b = new Random();
    private Account c;
    private Runnable d;
    private Runnable e;
    private final SkyLibWrapper f = SkyLibWrapper.a();
    private SkyLibIListener g;
    private CallHandlerIListener h;
    private int i;
    private String j;
    private CallHandler k;
    private SkyLibEventHandler l;
    private final com.microsoft.skype.a.a m = com.microsoft.skype.a.a.a("SkyLibQueue", com.microsoft.skype.a.a.b.HIGH);

    public interface SkyLibExecution {
        void a(SkyLib skyLib);
    }

    public interface CallHandlerExecution {
        void a(CallHandler callHandler);
    }

    public interface NGCPcmHostExecution {
        void a(NGCPcmHost nGCPcmHost);
    }

    public interface PcmHostCallbackExecution {
        void a(PcmHostCallback pcmHostCallback);
    }

    private class a {
        public boolean a;
        public long b;
        final /* synthetic */ SkyLibManager c;

        private a(SkyLibManager skyLibManager) {
            this.c = skyLibManager;
            this.a = false;
            this.b = 0;
        }

        /* synthetic */ a(SkyLibManager x0, byte b) {
            this(x0);
        }
    }

    private class b extends CallHandlerLogListener {
        final /* synthetic */ SkyLibManager a;

        private b(SkyLibManager skyLibManager) {
            this.a = skyLibManager;
        }

        /* synthetic */ b(SkyLibManager x0, byte b) {
            this(x0);
        }

        public final void onAudioStreamStateChanged(CallHandler callHandler, int objectId, MEDIA_DIRECTION direction, MEDIA_STREAM_STATE streamState) {
            super.onAudioStreamStateChanged(callHandler, objectId, direction, streamState);
            if (this.a.l != null) {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onAudioStreamStateChanged %d %s %s causeId: %s", Integer.valueOf(objectId), (Object) direction, (Object) streamState, causeId);
                final int i = objectId;
                final MEDIA_DIRECTION media_direction = direction;
                final MEDIA_STREAM_STATE media_stream_state = streamState;
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ b e;

                    public final void run() {
                        this.e.a.l.a(i, media_direction, media_stream_state, causeId);
                    }
                });
            }
        }

        public final void onCallTransferCallReceived(CallHandler callHandler, int objectId, int targetCallObjectId, String transferorMri, String transferTargetMri) {
            super.onCallTransferCallReceived(callHandler, objectId, targetCallObjectId, transferorMri, transferTargetMri);
            if (this.a.l != null) {
                final String causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onCallTransferCallReceived %d %d %s %s causeId: %s", Integer.valueOf(objectId), Integer.valueOf(targetCallObjectId), (Object) transferorMri, (Object) transferTargetMri);
                final int i = objectId;
                final int i2 = targetCallObjectId;
                final String str = transferorMri;
                final String str2 = transferTargetMri;
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ b f;

                    public final void run() {
                        this.f.a.l.a(i, i2, str, str2, causeId);
                    }
                });
            }
        }

        public final void onNudgeParticipantsOperationStatusChanged(CallHandler callHandler, int callObjectId, String context, int failureReason) {
            super.onNudgeParticipantsOperationStatusChanged(callHandler, callObjectId, context, failureReason);
            if (this.a.l != null) {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onNudgeParticipantsOperationStatusChanged callObjectId: %d context: %s failureReason: %d causeId: %s", Integer.valueOf(callObjectId), (Object) context, Integer.valueOf(failureReason), causeId);
                final int i = callObjectId;
                final String str = context;
                final int i2 = failureReason;
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ b e;

                    public final void run() {
                        this.e.a.l.a(i, str, i2, causeId);
                    }
                });
            }
        }

        public final void onActiveSpeakerListChanged(CallHandler callHandler, final int callObjectId, final String[] activeSpeakerList) {
            super.onActiveSpeakerListChanged(callHandler, callObjectId, activeSpeakerList);
            if (this.a.l != null) {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onActiveSpeakerListChanged callObjectId: %d listSize: %d causeId: %s", Integer.valueOf(callObjectId), Integer.valueOf(activeSpeakerList.length), causeId);
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ b d;

                    public final void run() {
                        this.d.a.l.a(callObjectId, activeSpeakerList, causeId);
                    }
                });
            }
        }

        public final void onDominantSpeakerListChanged(CallHandler callHandler, final int callObjectId, final String[] dominantSpeakerList) {
            super.onDominantSpeakerListChanged(callHandler, callObjectId, dominantSpeakerList);
            if (this.a.l != null) {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onDominantSpeakerListChanged callObjectId: %d listSize: %d causeId: %s", Integer.valueOf(callObjectId), Integer.valueOf(dominantSpeakerList.length), causeId);
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ b d;

                    public final void run() {
                        this.d.a.l.b(callObjectId, dominantSpeakerList, causeId);
                    }
                });
            }
        }
    }

    private class c extends SkyLibLogListener {
        final /* synthetic */ SkyLibManager a;
        private final Handler b;
        private final SparseArray<a> c = new SparseArray();

        static /* synthetic */ com.microsoft.skype.b.a a(c x0, CallHandler x1, final int x2, PROPKEY x3) {
            switch (x3) {
                case CALL_NAME:
                case CALL_TOPIC:
                case CALL_CONVERSATION_TYPE:
                case CALL_MESSAGE_ID:
                case CALL_THREAD_ID:
                case CALL_LEG_ID:
                case CALL_ENDPOINT_DETAILS:
                case CALL_INCOMING_TYPE:
                case CALL_FORWARDING_DESTINATION_TYPE:
                    return a(x1, x2, x3);
                case CALL_IS_ACTIVE:
                case CALL_IS_MUTED:
                case CALL_ACTIVE_MEMBERS:
                case CALL_MEMBER_COUNT_CHANGED:
                case CALL_IS_INCOMING:
                case CALL_IS_CONFERENCE:
                case CALL_IS_ON_HOLD:
                case CALL_OPTIMAL_REMOTE_VIDEOS_IN_CONFERENCE:
                case CALL_STATUS:
                case CALL_IS_HOSTLESS:
                case CALL_IS_SERVER_MUTED:
                case CALL_FAILURE_REASON:
                    return b(x1, x2, x3);
                case CMEMBER_MRI_IDENTITY:
                case CMEMBER_ENDPOINT_DETAILS:
                case CMEMBER_BALANCE_UPDATE:
                case CMEMBER_ACCEPTED_BY:
                case CMEMBER_CALL_END_DIAGNOSTICS_CODE:
                    return a(x1, x2, x3);
                case CMEMBER_VIDEO_COUNT_CHANGED:
                case CMEMBER_DOMINANT_SPEAKER_RANK:
                case CMEMBER_FAILUREREASON:
                case CMEMBER_STATUS:
                case CMEMBER_TYPE:
                case CMEMBER_ENDPOINT_TYPE:
                case CMEMBER_CAPABILITIES:
                case CMEMBER_IS_SERVER_MUTED:
                case CMEMBER_CONTENT_SHARING_ROLE:
                    return b(x1, x2, x3);
                case CMEMBER_IS_ACTIVE_SPEAKER:
                    a aVar = (a) x0.c.get(x2);
                    if (aVar == null) {
                        aVar = new a(x0.a, (byte) 0);
                        x0.c.put(x2, aVar);
                    }
                    final int nextInt = SkyLibManager.b.nextInt();
                    final Runnable anonymousClass8 = new Runnable(x0) {
                        final /* synthetic */ c c;

                        public final void run() {
                            Object obj;
                            com.microsoft.skype.b.a value = com.microsoft.skype.b.a.a(null, Integer.valueOf(this.c.a.k.getIntegerProperty(x2, PROPKEY.CMEMBER_IS_ACTIVE_SPEAKER)));
                            String c = this.c.a.i();
                            String str = "CALLMEMBER changed ID %d {CMEMBER_IS_ACTIVE_SPEAKER (throttled), %s} causeId: %x";
                            Object valueOf = Integer.valueOf(x2);
                            if (value.a != null) {
                                obj = (Serializable) value.a;
                            } else {
                                Serializable obj2 = (Serializable) value.b;
                            }
                            FLog.v(c, str, valueOf, obj2, Integer.valueOf(nextInt));
                            this.c.a.l.a(OBJECTTYPE.CALLMEMBER, PROPKEY.CMEMBER_IS_ACTIVE_SPEAKER, x2, value, String.format("%x", new Object[]{Integer.valueOf(nextInt)}));
                            ((a) this.c.c.get(x2)).b = System.currentTimeMillis();
                        }
                    };
                    long currentTimeMillis = System.currentTimeMillis() - aVar.b;
                    if (!aVar.a && currentTimeMillis > 1000) {
                        anonymousClass8.run();
                    } else if (aVar.a) {
                        FLog.v(x0.a.i(), "CALLMEMBER changed ID %d - CMEMBER_IS_ACTIVE_SPEAKER currently throttled (causeId %x)", Integer.valueOf(x2), Integer.valueOf(nextInt));
                    } else {
                        currentTimeMillis = 1000 - currentTimeMillis;
                        FLog.v(x0.a.i(), "CALLMEMBER changed ID %d - CMEMBER_IS_ACTIVE_SPEAKER throttling further events for %d seconds (causeId %x)", Integer.valueOf(x2), Long.valueOf(currentTimeMillis), Integer.valueOf(nextInt));
                        aVar.a = true;
                        x0.b.postDelayed(new Runnable(x0) {
                            final /* synthetic */ c d;

                            public final void run() {
                                this.d.a.m.b(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass9 a;

                                    {
                                        this.a = this$2;
                                    }

                                    public final void run() {
                                        FLog.v(this.a.d.a.i(), "CALLMEMBER changed ID %d - CMEMBER_IS_ACTIVE_SPEAKER throttle triggered (causeId %x)", Integer.valueOf(x2), Integer.valueOf(nextInt));
                                        a updatedSpeakerRecord = (a) this.a.d.c.get(x2);
                                        anonymousClass8.run();
                                        updatedSpeakerRecord.a = false;
                                    }
                                });
                            }
                        }, currentTimeMillis);
                    }
                    return null;
                case VIDEO_CONVO_ID:
                case VIDEO_MEDIA_TYPE:
                case VIDEO_STATUS:
                case VIDEO_RANK:
                    return b(x1, x2, x3);
                case VIDEO_ERROR:
                    return a(x1, x2, x3);
                case CONTENTSHARING_IDENTITY:
                case CONTENTSHARING_SHARING_ID:
                case CONTENTSHARING_STATE:
                    return a(x1, x2, x3);
                case CONTENTSHARING_STATUS:
                case CONTENTSHARING_FAILURECODE:
                case CONTENTSHARING_FAILUREREASON:
                case CONTENTSHARING_FAILURESUBCODE:
                    return b(x1, x2, x3);
                default:
                    return null;
            }
        }

        public c(SkyLibManager skyLibManager, Context context) {
            this.a = skyLibManager;
            this.b = new Handler(context.getMainLooper());
        }

        public final void onQualityChanged(SkyLib sender, int objectId, QualityEventType type, QualityLevel level, QUALITY_MEDIATYPE mediaType) {
            super.onQualityChanged(sender, objectId, type, level, mediaType);
            if (this.a.l != null) {
                final String causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onQualityChanged %d %s %s %s causeId: %s", Integer.valueOf(objectId), type, level, mediaType, causeId);
                final int i = objectId;
                final QualityEventType qualityEventType = type;
                final QualityLevel qualityLevel = level;
                final QUALITY_MEDIATYPE quality_mediatype = mediaType;
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ c f;

                    public final void run() {
                        this.f.a.l.a(i, qualityEventType, qualityLevel, quality_mediatype, causeId);
                    }
                });
            }
        }

        public final void onAvailableVideoDeviceListChange(SkyLib sender) {
            super.onAvailableVideoDeviceListChange(sender);
            if (this.a.l != null) {
                final String causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                this.a.a(new SkyLibExecution(this) {
                    final /* synthetic */ c b;

                    public final void a(SkyLib skyLib) {
                        FLog.i(this.b.a.i(), "onAvailableVideoDeviceListChange causeId: %s", causeId);
                        this.b.a.l.a(causeId);
                    }
                });
            }
        }

        public final void onObjectPropertyChangeWithValue(SkyLib skyLib, int i, PROPKEY propkey, Metatag metatag) {
            super.onObjectPropertyChangeWithValue(skyLib, i, propkey, metatag);
            if (this.a.l != null) {
                final int causeId = SkyLibManager.b.nextInt();
                final OBJECTTYPE objectType = skyLib.getObjectType(i);
                switch (objectType) {
                    case ACCOUNT:
                        if (propkey == PROPKEY.ACCOUNT_STATUS) {
                            this.a.m.b(new Runnable(this) {
                                final /* synthetic */ c b;

                                public final void run() {
                                    SkyLibManager.a(this.b.a, this.b.a.c, causeId);
                                }
                            });
                            return;
                        }
                        return;
                    case CALL:
                    case CALLMEMBER:
                    case VIDEO:
                    case CONTENTSHARING:
                        final int i2 = i;
                        final PROPKEY propkey2 = propkey;
                        this.a.a(new CallHandlerExecution(this) {
                            final /* synthetic */ c e;

                            public final void a(CallHandler callHandler) {
                                com.microsoft.skype.b.a value = c.a(this.e, callHandler, i2, propkey2);
                                if (value != null) {
                                    String causeIdString = String.format("%x", new Object[]{Integer.valueOf(causeId)});
                                    String loggedValue = value.a != null ? (propkey2 == PROPKEY.CALL_ENDPOINT_DETAILS || propkey2 == PROPKEY.CMEMBER_ENDPOINT_DETAILS) ? "omitted" : (String) value.a : ((Integer) value.b).toString();
                                    String message = String.format(Locale.US, "[%s.%s:%d] changed to [%s] causeId: %x", new Object[]{objectType, propkey2, Integer.valueOf(i2), loggedValue, Integer.valueOf(causeId)});
                                    if (!(propkey2 == PROPKEY.CMEMBER_IS_ACTIVE_SPEAKER || propkey2 == PROPKEY.CMEMBER_DOMINANT_SPEAKER_RANK)) {
                                        FLog.i(this.e.a.i(), message);
                                    }
                                    this.e.a.l.a(objectType, propkey2, i2, value, causeIdString);
                                } else if (propkey2 != PROPKEY.CMEMBER_IS_ACTIVE_SPEAKER && propkey2 != PROPKEY.CMEMBER_DOMINANT_SPEAKER_RANK) {
                                    FLog.i(this.e.a.i(), "Ignoring changed event for [%s.%s:%d]", objectType, propkey2, Integer.valueOf(i2));
                                }
                            }
                        });
                        return;
                    default:
                        return;
                }
            }
        }

        public final void onPushHandlingComplete(SkyLib skyLib, final int i, final PUSHHANDLINGRESULT pushhandlingresult) {
            super.onPushHandlingComplete(skyLib, i, pushhandlingresult);
            if (this.a.l != null) {
                FLog.i(this.a.i(), "Push notification handling for token %d completed with result %s", Integer.valueOf(i), pushhandlingresult.toString());
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ c c;

                    public final void run() {
                        this.c.a.l.a(i, pushhandlingresult);
                    }
                });
            }
        }

        public final void onSkypeTokenRequired(SkyLib skyLib, final String invalidToken) {
            super.onSkypeTokenRequired(skyLib, invalidToken);
            if (this.a.l != null) {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(SkyLibManager.b.nextInt())});
                FLog.i(this.a.i(), "onSkypeTokenRequired causeId: %s", causeId);
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ c c;

                    public final void run() {
                        this.c.a.l.a(invalidToken, causeId);
                    }
                });
            }
        }

        public final void onMediaStatusChanged(SkyLib skyLib, final MEDIASTATUS status) {
            super.onMediaStatusChanged(skyLib, status);
            if (this.a.l != null) {
                FLog.i(this.a.i(), "onMediaStatusChanged status: %s", status.toString());
                this.a.m.b(new Runnable(this) {
                    final /* synthetic */ c b;

                    public final void run() {
                        this.b.a.l.a(status);
                    }
                });
            }
        }

        private static com.microsoft.skype.b.a<String, Integer> a(CallHandler callHandler, int i, PROPKEY propkey) {
            return com.microsoft.skype.b.a.a(callHandler.getStringProperty(i, propkey), null);
        }

        private static com.microsoft.skype.b.a<String, Integer> b(CallHandler callHandler, int i, PROPKEY propkey) {
            return com.microsoft.skype.b.a.a(null, Integer.valueOf(callHandler.getIntegerProperty(i, propkey)));
        }
    }

    public SkyLibManager(Context context) {
        this.f.a(context);
        a = this;
    }

    private String i() {
        return String.format("%s[%s:%s]", new Object[]{"SkyLibManager", this.f.b(), j()});
    }

    public final void a(SkyLibEventHandler eventHandler) {
        this.l = eventHandler;
    }

    public final void a() {
        this.l = null;
    }

    public final void a(String action) {
        if (!b()) {
            throw new com.facebook.react.bridge.c("Cannot execute " + action + ": not initialized");
        }
    }

    public final void b(String action) {
        if (!c()) {
            throw new com.facebook.react.bridge.c("Cannot execute " + action + ": not started");
        }
    }

    public final void c(String action) {
        if (!d()) {
            throw new com.facebook.react.bridge.c("Cannot execute " + action + ": not logged in");
        }
    }

    public final boolean b() {
        return this.f.b() != SkyLibState.TERMINATED;
    }

    public final boolean c() {
        return b() && this.f.b() != SkyLibState.INITIALIZED;
    }

    public final boolean d() {
        return j() == STATUS.LOGGED_IN;
    }

    public final boolean e() {
        return j() == STATUS.LOGGING_IN;
    }

    public final boolean f() {
        return j() == STATUS.LOGGING_OUT;
    }

    public final boolean g() {
        return j() == STATUS.LOGGED_OUT;
    }

    private STATUS j() {
        return this.c == null ? null : STATUS.fromInt(this.c.getIntProperty(PROPKEY.ACCOUNT_STATUS));
    }

    public final void a(final CallHandlerExecution executor) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager b;

            public final void run() {
                if (this.b.k == null && this.b.d()) {
                    this.b.k = new CallHandlerImpl();
                    this.b.f.d().getCallHandler(0, this.b.k);
                    SkyLibManager.b(this.b, this.b.k);
                }
                if (this.b.k == null) {
                    FLog.w(this.b.i(), "Unable to obtain callHandler");
                } else {
                    executor.a(this.b.k);
                }
            }
        });
    }

    public final void a(final SkyLibExecution executor) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager b;

            public final void run() {
                executor.a(this.b.f.d());
            }
        });
    }

    public final void a(final NGCPcmHostExecution executor) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager b;

            public final void run() {
                executor.a(this.b.f.e());
            }
        });
    }

    public final void a(final PcmHostCallbackExecution executor) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager b;

            public final void run() {
                executor.a(this.b.f.f());
            }
        });
    }

    public final void a(int platform, String version, boolean enableLogs, boolean encryptLogs, Context context, Runnable success) {
        final int i = platform;
        final String str = version;
        final boolean z = enableLogs;
        final boolean z2 = encryptLogs;
        final Context context2 = context;
        final Runnable runnable = success;
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager g;

            public final void run() {
                FLog.i(this.g.i(), "Initializing SkyLib [platform %d] [version %s] [enableLogs %b] [encryptLogs %b]", Integer.valueOf(i), str, Boolean.valueOf(z), Boolean.valueOf(z2));
                if (this.g.b()) {
                    SkyLibManager.a(this.g, context2);
                    runnable.run();
                    return;
                }
                InitializerConfiguration initConfig = new InitializerConfiguration();
                initConfig.b(context2.getFilesDir().getAbsolutePath());
                initConfig.a(String.format(Locale.US, "%d/%s", new Object[]{Integer.valueOf(i), str}));
                this.g.i = i;
                this.g.j = str;
                initConfig.a(new UnifiedVideoHostInitializer());
                initConfig.a(z);
                initConfig.b(z2);
                this.g.f.a(context2, initConfig);
                SkyLibManager.a(this.g, context2);
                runnable.run();
            }
        });
    }

    public final void a(final Context context, final Runnable success) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager c;

            public final void run() {
                FLog.i(this.c.i(), "Starting SkyLib");
                if (this.c.c()) {
                    success.run();
                    return;
                }
                this.c.f.b(context);
                success.run();
            }
        });
    }

    public final void a(String userId, String token, String displayName, Runnable success) {
        final Runnable runnable = success;
        final String str = userId;
        final String str2 = token;
        final String str3 = displayName;
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager e;

            public final void run() {
                int causeId = SkyLibManager.b.nextInt();
                FLog.i(this.e.i(), "Logging into SkyLib (causeId %x)", Integer.valueOf(causeId));
                this.e.d = runnable;
                if (this.e.d()) {
                    FLog.i(this.e.i(), "Already logged into SkyLib (causeId %x)", Integer.valueOf(causeId));
                    this.e.a(causeId);
                } else if (this.e.e()) {
                    FLog.i(this.e.i(), "Login is already in progress, waiting (causeId %x)", Integer.valueOf(causeId));
                } else {
                    this.e.c = new AccountImpl();
                    this.e.f.d().getAccount(str, this.e.c);
                    this.e.c.setUIVersion(String.format(Locale.US, "%d/%s", new Object[]{Integer.valueOf(this.e.i), this.e.j}));
                    this.e.c.loginWithSkypeToken(str2, 0, str3);
                    if (this.e.d()) {
                        this.e.a(causeId);
                    }
                }
            }
        });
    }

    public final void a(final String token, final Runnable success, final Runnable failure) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager d;

            public final void run() {
                if (this.d.d()) {
                    FLog.i(this.d.i(), "Updating Skype token");
                    this.d.c.updateSkypeToken(token);
                    success.run();
                    return;
                }
                failure.run();
            }
        });
    }

    public final void a(final Runnable success) {
        this.m.b(new Runnable(this) {
            final /* synthetic */ SkyLibManager b;

            public final void run() {
                int causeId = SkyLibManager.b.nextInt();
                FLog.i(this.b.i(), "Logging out SkyLib (causeId %x)", Integer.valueOf(causeId));
                this.b.e = success;
                if (this.b.g()) {
                    FLog.i(this.b.i(), "Already logged out SkyLib (causeId %x)", Integer.valueOf(causeId));
                    this.b.b(causeId);
                } else if (this.b.f()) {
                    FLog.i(this.b.i(), "Logout is already in progress, waiting (causeId %x)", Integer.valueOf(causeId));
                } else {
                    this.b.c.logout();
                    if (this.b.g()) {
                        this.b.b(causeId);
                    }
                }
            }
        });
    }

    public final void b(final Runnable success) {
        this.m.c(new Runnable(this) {
            final /* synthetic */ SkyLibManager b;

            public final void run() {
                if (this.b.b()) {
                    int causeId = SkyLibManager.b.nextInt();
                    FLog.i(this.b.i(), "Terminating SkyLib (causeId %x)", Integer.valueOf(causeId));
                    SkyLibManager.g(this.b);
                    if (this.b.k != null) {
                        SkyLibManager.c(this.b, this.b.k);
                        this.b.k = null;
                    }
                    this.b.f.c();
                    this.b.b(causeId);
                    success.run();
                }
            }
        });
    }

    public static aq a(SkyLib skyLib) {
        GetAvailableVideoDevices_Result result = skyLib.getAvailableVideoDevices();
        aq devices = new WritableNativeArray();
        for (int i = 0; i < result.m_deviceNames.length; i++) {
            String name = result.m_deviceNames[i];
            String path = result.m_devicePaths[i];
            VIDEO_DEVICE_FACING facing = skyLib.getVideoDeviceFacing(name, path);
            VIDEO_DEVICE_TYPE type = skyLib.getVideoDeviceType(name, path);
            ar map = new WritableNativeMap();
            map.putString("name", name);
            map.putString("path", path);
            map.putInt("facing", facing.toInt());
            map.putInt("type", type.toInt());
            devices.pushMap(map);
        }
        return devices;
    }

    public final void a(LogsProvider provider) {
        long currentTimeMillis = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12);
        List<File> b = provider.b();
        FLog.i(i(), "clearOldLogs found %d potential log files", Integer.valueOf(b.size()));
        for (File file : b) {
            FLog.d(i(), "clearOldLogs considering %s - lastModified=%d threshold=%d", file.getName(), Long.valueOf(file.lastModified()), Long.valueOf(currentTimeMillis));
            if (file.lastModified() < currentTimeMillis) {
                FLog.i(i(), "%s delete %s", file.delete() ? "Successfully" : "Failed to", file.getName());
            }
        }
    }

    private void a(final int causeId) {
        com.facebook.infer.annotation.a.a(com.microsoft.skype.a.a.a(this.m));
        FLog.i(i(), "Successfully logged in to SkyLib (causeId %x)", Integer.valueOf(causeId));
        if (this.d != null) {
            this.d.run();
            this.d = null;
        }
        a(new CallHandlerExecution(this) {
            final /* synthetic */ SkyLibManager b;

            public final void a(CallHandler callHandler) {
                if (callHandler != null) {
                    FLog.i(this.b.i(), "Successfully instantiated CallHandler (causeId %x)", Integer.valueOf(causeId));
                    return;
                }
                FLog.e(this.b.i(), "Failed to instantiate CallHandler (causeId %x)", Integer.valueOf(causeId));
            }
        });
    }

    private void b(int causeId) {
        com.facebook.infer.annotation.a.a(com.microsoft.skype.a.a.a(this.m));
        FLog.i(i(), "Successfully logged out of SkyLib (causeId %x)", Integer.valueOf(causeId));
        if (this.c != null) {
            this.c = null;
        }
        if (this.e != null) {
            this.e.run();
            this.e = null;
        }
    }

    static /* synthetic */ void b(SkyLibManager x0, CallHandler x1) {
        if (x0.h == null) {
            x0.h = new b(x0, (byte) 0);
            x1.addListener(x0.h);
        }
    }

    static /* synthetic */ void a(SkyLibManager x0, Context x1) {
        if (x0.g == null) {
            x0.g = new c(x0, x1);
            x0.f.d().addListener(x0.g);
        }
    }

    static /* synthetic */ void g(SkyLibManager x0) {
        if (x0.g != null) {
            x0.f.d().removeListener(x0.g);
            x0.g = null;
        }
    }

    static /* synthetic */ void c(SkyLibManager x0, CallHandler x1) {
        if (x0.h != null) {
            x1.removeListener(x0.h);
            x0.h = null;
        }
    }

    static /* synthetic */ void a(SkyLibManager x0, Account x1, int x2) {
        com.facebook.infer.annotation.a.a(com.microsoft.skype.a.a.a(x0.m));
        if (x1 != null) {
            Object j = x0.j();
            FLog.i(x0.i(), "Account status changed to %s causeId %x", j, Integer.valueOf(x2));
            if (j != null) {
                switch (j) {
                    case LOGGED_IN:
                        x0.a(x2);
                        return;
                    case LOGGED_OUT:
                        break;
                    default:
                        return;
                }
            }
            x0.b(x2);
        }
    }
}
