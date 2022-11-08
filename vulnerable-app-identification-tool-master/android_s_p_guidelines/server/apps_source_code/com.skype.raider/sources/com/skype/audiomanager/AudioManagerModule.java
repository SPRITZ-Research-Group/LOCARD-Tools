package com.skype.audiomanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.v;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skype.android.video.hw.extension.SliqConstants;
import com.skype.audiomanager.AudioOptions.AudioDeviceStatus;
import com.skype.audiomanager.AudioOptions.OutputDestination;
import com.skype.slimcore.skylib.SkyLibProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nonnull;

public class AudioManagerModule extends ReactContextBaseJavaModule implements v {
    private static final String AudioInterruptedChangedEvent = "AudioInterruptedChangedEvent";
    private static final String AudioOutputChangedEvent = "AudioOutputChangedEvent";
    private static final String AvailableAudioOutputsChangedEvent = "AvailableAudioOutputsChangedEvent";
    private static final String MediaActionEvent = "MediaActionEvent";
    private static final String TAG = "AudioManagerModule";
    private static WeakReference<AudioManagerModule> currentInstance;
    private int _audioFocusRequestDurationHint;
    private boolean _audioFocusRequested;
    private boolean _initialized = false;
    private boolean _isInBackground = false;
    private int _lastKnownAudioFocusState;
    private int _lastKnownAudioMode = -2;
    private int _lastKnownAudioStream = Integer.MIN_VALUE;
    private OutputDestination _lastSentOutputDestination = OutputDestination.OTHER;
    private final AudioManager androidAudioManager;
    private OnAudioFocusChangeListener audioFocusChangeListener = new OnAudioFocusChangeListener(this) {
        final /* synthetic */ AudioManagerModule a;

        {
            this.a = this$0;
        }

        public final void onAudioFocusChange(final int focusChange) {
            this.a.serialQueue.b(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 b;

                public final void run() {
                    Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.b.a.random.nextInt())});
                    FLog.i(AudioManagerModule.TAG, "onAudioFocusChange %s (causeId %s)", AnonymousClass1.a(focusChange), causeId);
                    if (focusChange == 1) {
                        this.b.a.calcSettingsAndUpdate(causeId, null);
                    }
                    this.b.a._lastKnownAudioFocusState = focusChange;
                }
            });
        }

        static /* synthetic */ String a(int x1) {
            switch (x1) {
                case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                    return "Loss (transient can duck)";
                case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                    return "Loss (transient";
                case -1:
                    return "Loss";
                case 1:
                    return "Gain";
                default:
                    return "Unknown focus changed " + x1;
            }
        }
    };
    private final AudioOptions audioOptions;
    private int nextToken = 1;
    private c phoneListener;
    private final Random random = new Random();
    private final com.microsoft.skype.a.a serialQueue = com.microsoft.skype.a.a.a("AudioManagerQueue", com.microsoft.skype.a.a.b.DEFAULT);
    @SuppressLint({"UseSparseArrays"})
    private HashMap<Integer, a> tokens = new HashMap();

    private enum a {
        DEFAULT(1),
        MOJI_PLAYBACK(2),
        VIDEO_PLAYBACK(3),
        AUDIO_PLAYBACK(4),
        INCOMING_RING(5),
        RECORD_AUDIO_MESSAGE(6),
        CAPTURE(7),
        CALL(8),
        CALL_PREFER_SPEAKER(9),
        AUDIO_PLAYBACK_WITH_SCREEN_READER(10);
        
        public final int k;

        private a(int value) {
            this.k = value;
        }

        @Nonnull
        @SuppressLint({"DefaultLocale"})
        public static a a(int value) {
            switch (value) {
                case 1:
                    return DEFAULT;
                case 2:
                    return MOJI_PLAYBACK;
                case 3:
                    return VIDEO_PLAYBACK;
                case 4:
                    return AUDIO_PLAYBACK;
                case 5:
                    return INCOMING_RING;
                case 6:
                    return RECORD_AUDIO_MESSAGE;
                case 7:
                    return CAPTURE;
                case 8:
                    return CALL;
                case 9:
                    return CALL_PREFER_SPEAKER;
                case 10:
                    return AUDIO_PLAYBACK_WITH_SCREEN_READER;
                default:
                    throw new RuntimeException(String.format(Locale.US, "Unexpected AudioMode type %d", new Object[]{Integer.valueOf(value)}));
            }
        }
    }

    private final class b {
        final int a;
        final boolean b;
        final int c;
        final OutputDestination d;
        final int e;
        final /* synthetic */ AudioManagerModule f;

        b(AudioManagerModule audioManagerModule, int audioStream, boolean wantsFocus, int focusDurationHint, OutputDestination preferredOutputRoute, int audioMode) {
            this.f = audioManagerModule;
            this.a = audioStream;
            this.b = wantsFocus;
            this.c = focusDurationHint;
            this.d = preferredOutputRoute;
            this.e = audioMode;
        }

        public final String toString() {
            String wantsFocusString = this.b ? String.format("WANTS (%s)", new Object[]{AudioManagerModule.stringForFocusDurationHint(this.c)}) : "DOES NOT WANT";
            String str = "stream=%s, mode=%s, focus=%s, route=%s";
            Object[] objArr = new Object[4];
            objArr[0] = AudioManagerModule.stringForAudioStream(this.a);
            objArr[1] = AudioManagerModule.stringForAndroidAudioMode(this.e);
            objArr[2] = wantsFocusString;
            objArr[3] = this.d != null ? this.d : "CURRENT";
            return String.format(str, objArr);
        }
    }

    private final class c extends PhoneStateListener {
        final /* synthetic */ AudioManagerModule a;
        private boolean b;
        private boolean c;
        private final TelephonyManager d;

        public c(AudioManagerModule audioManagerModule, Context context) {
            this.a = audioManagerModule;
            this.d = (TelephonyManager) context.getSystemService("phone");
        }

        public final void onCallStateChanged(final int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            this.a.serialQueue.b(new Runnable(this) {
                final /* synthetic */ c b;

                public final void run() {
                    String causeId = String.format("%x", new Object[]{Integer.valueOf(this.b.a.random.nextInt())});
                    FLog.i("TelephoneStateListener", "onCallStateChanged %s (causeId %s)", Integer.valueOf(state), (Object) causeId);
                    this.b.a(state, true, causeId);
                }
            });
        }

        private void a(int state, final boolean calcOnChange, final String causeId) {
            boolean newInPhoneCall = state != 0;
            if (newInPhoneCall != this.c) {
                this.c = newInPhoneCall;
                this.a.serialQueue.b(new Runnable(this) {
                    final /* synthetic */ c c;

                    public final void run() {
                        if (this.c.a.interruptionInProgress()) {
                            this.c.a.beginAudioInterruption(causeId);
                        } else {
                            this.c.a.endAudioInterruption(causeId);
                        }
                        if (calcOnChange) {
                            this.c.a.calcSettingsAndUpdate(causeId, null);
                        }
                    }
                });
            }
        }

        public final boolean a() {
            return this.c;
        }

        final void a(String causeId) {
            if (!this.b) {
                FLog.i("TelephoneStateListener", "Registering phone state listener (causeId %s)", (Object) causeId);
                this.d.listen(this, 32);
                this.b = true;
            }
            a(this.d.getCallState(), true, causeId);
        }

        final void b(String causeId) {
            FLog.i("TelephoneStateListener", "Forcing update of phone state (causeId %s)", (Object) causeId);
            a(this.d.getCallState(), false, causeId);
        }

        final void c(String causeId) {
            if (this.b) {
                FLog.i("TelephoneStateListener", "Un-registering phone state listener (causeId %s)", (Object) causeId);
                this.d.listen(this, 0);
                this.b = false;
            }
        }
    }

    public AudioManagerModule(ag reactContext, WeakReference<SkyLibProvider> skyLibProvider) {
        super(reactContext);
        Context context = getReactApplicationContext();
        this.androidAudioManager = (AudioManager) context.getSystemService("audio");
        AudioManagerModule previousModule = currentInstance != null ? (AudioManagerModule) currentInstance.get() : null;
        if (previousModule != null) {
            FLog.i(TAG, "detected previous instance of AudioManagerModule, tearing it down");
            previousModule.uninitialize();
        }
        currentInstance = new WeakReference(this);
        this.audioOptions = new AudioOptions(context, skyLibProvider, new DeviceObserver<OutputDestination, AudioDeviceStatus, String>(this) {
            final /* synthetic */ AudioManagerModule a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ void a(Object obj) {
                FLog.e(AudioManagerModule.TAG, "User Media Action(causeId %s)", (String) obj);
                this.a.sendEvent(AudioManagerModule.MediaActionEvent, r5);
            }

            public final /* synthetic */ void a(Object obj, Object obj2, Object obj3) {
                final OutputDestination outputDestination = (OutputDestination) obj;
                final AudioDeviceStatus audioDeviceStatus = (AudioDeviceStatus) obj2;
                final String str = (String) obj3;
                this.a.serialQueue.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 d;

                    public final void run() {
                        FLog.i(AudioManagerModule.TAG, "Output update and reset (destination %d, deviceStatus %d, causeId %s)", Integer.valueOf(outputDestination.f), Integer.valueOf(audioDeviceStatus.d), str);
                        if (this.d.a._lastSentOutputDestination != outputDestination && audioDeviceStatus == AudioDeviceStatus.DISCONNECTED) {
                            return;
                        }
                        if (this.d.a._lastSentOutputDestination == outputDestination && audioDeviceStatus == AudioDeviceStatus.DISCONNECTED) {
                            this.d.a.maybeSendAudioOutputEvent(this.d.a.audioOptions.f(), str);
                            this.d.a._lastSentOutputDestination = OutputDestination.OTHER;
                            this.d.a.calcSettingsAndUpdate(str, null);
                        } else if (audioDeviceStatus == AudioDeviceStatus.CONNECTED && this.d.a.tokens.size() > 0) {
                            this.d.a.audioOptions.a(outputDestination, new Action1<Boolean>(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = this$2;
                                }

                                public final /* synthetic */ void a(Object obj) {
                                    if (((Boolean) obj).booleanValue()) {
                                        FLog.i(AudioManagerModule.TAG, "Succeeded to switch audio output to new device %d (causeId %s)", Integer.valueOf(outputDestination.f), str);
                                        this.a.d.a.maybeSendAudioOutputEvent(outputDestination, str);
                                        return;
                                    }
                                    FLog.e(AudioManagerModule.TAG, "Failed to switch audio output to new device %d (causeId %s)", Integer.valueOf(outputDestination.f), str);
                                }
                            }, str);
                        } else if (audioDeviceStatus == AudioDeviceStatus.UNKNOWN && outputDestination == OutputDestination.BLUETOOTH) {
                            this.d.a.audioOptions;
                            boolean isBluetoothAvailable = BluetoothReceiver.b();
                            this.d.a.audioOptions;
                            boolean isBluetoothFullyConnected = BluetoothReceiver.b();
                            boolean areWeIntendingToUseToBluetooth = this.d.a._lastSentOutputDestination == OutputDestination.BLUETOOTH;
                            FLog.i(AudioManagerModule.TAG, "Unclear bluetooth status (available:%b; fully ready:%b, currently selected:%b", Boolean.valueOf(isBluetoothAvailable), Boolean.valueOf(isBluetoothFullyConnected), Boolean.valueOf(areWeIntendingToUseToBluetooth));
                            if (!(isBluetoothFullyConnected && areWeIntendingToUseToBluetooth) && isBluetoothAvailable && !isBluetoothFullyConnected && areWeIntendingToUseToBluetooth) {
                                this.d.a.audioOptions.a(OutputDestination.EARPIECE, new Action1<Boolean>(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = this$2;
                                    }

                                    public final /* synthetic */ void a(Object obj) {
                                        if (((Boolean) obj).booleanValue()) {
                                            FLog.i(AudioManagerModule.TAG, "Succeeded to switch audio output to new device %d (causeId %s)", Integer.valueOf(outputDestination.f), str);
                                            this.a.d.a.maybeSendAudioOutputEvent(outputDestination, str);
                                            this.a.d.a.audioOptions.a(outputDestination, new Action1<Boolean>(this) {
                                                final /* synthetic */ AnonymousClass2 a;

                                                {
                                                    this.a = this$3;
                                                }

                                                public final /* synthetic */ void a(Object obj) {
                                                    if (((Boolean) obj).booleanValue()) {
                                                        FLog.i(AudioManagerModule.TAG, "Succeeded to switch audio output to new device %d (causeId %s)", Integer.valueOf(outputDestination.f), str);
                                                        this.a.a.d.a.maybeSendAudioOutputEvent(outputDestination, str);
                                                        return;
                                                    }
                                                    FLog.e(AudioManagerModule.TAG, "Failed to switch audio output to new device %d (causeId %s)", Integer.valueOf(outputDestination.f), str);
                                                }
                                            }, str);
                                            return;
                                        }
                                        FLog.e(AudioManagerModule.TAG, "Failed to switch audio output to new device %d (causeId %s)", Integer.valueOf(outputDestination.f), str);
                                    }
                                }, str);
                            }
                        }
                    }
                });
            }
        });
    }

    public void initialize() {
        super.initialize();
        timeBlock("AudioManagerModule.initialize", new Runnable(this) {
            final /* synthetic */ AudioManagerModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.a.random.nextInt())});
                FLog.i(AudioManagerModule.TAG, "initialize (causeId %s)", causeId);
                this.a.serialQueue.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public final void run() {
                        this.b.a.audioOptions.a(this.b.a.getReactApplicationContext(), causeId);
                        com.microsoft.skype.a.a.a.b(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = this$2;
                            }

                            public final void run() {
                                this.a.b.a.timeBlock("TelephoneStateListener.init", new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = this$3;
                                    }

                                    public final void run() {
                                        this.a.a.b.a.phoneListener = new c(this.a.a.b.a, this.a.a.b.a.getReactApplicationContext());
                                        this.a.a.b.a.phoneListener.a(causeId);
                                    }
                                });
                                this.a.b.a.getReactApplicationContext().a(this.a.b.a);
                                this.a.b.a._initialized = true;
                                this.a.b.a.maybeSendAudioOutputEvent(this.a.b.a.audioOptions.f(), causeId);
                            }
                        });
                    }
                });
            }
        });
    }

    public void uninitialize() {
        timeBlock("AudioManagerModule.uninitialize", new Runnable(this) {
            final /* synthetic */ AudioManagerModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                final Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.a.random.nextInt())});
                FLog.i(AudioManagerModule.TAG, "uninitialize (causeId %s)", causeId);
                this.a.serialQueue.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 b;

                    public final void run() {
                        this.b.a.audioOptions.b(causeId);
                        com.microsoft.skype.a.a.a.b(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = this$2;
                            }

                            public final void run() {
                                if (this.a.b.a.phoneListener != null) {
                                    this.a.b.a.timeBlock("TelephoneStateListener.uninit", new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = this$3;
                                        }

                                        public final void run() {
                                            this.a.a.b.a.phoneListener.c(causeId);
                                        }
                                    });
                                }
                                this.a.b.a.getReactApplicationContext().b(this.a.b.a);
                                this.a.b.a._initialized = false;
                            }
                        });
                    }
                });
            }
        });
    }

    private void timeBlock(String tag, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        FLog.i(TAG, "%s took %dms", (Object) tag, Integer.valueOf((int) (System.currentTimeMillis() - start)));
    }

    private static OutputDestination defaultOutputDestination(AudioOptions options, Collection<a> collection, boolean phoneCall) {
        if (phoneCall) {
            return null;
        }
        return computeOutputDestination(options, true, false, OutputDestination.SPEAKER);
    }

    private OutputDestination outputDestinationForMode(AudioOptions options, a mode, boolean headsetAvailable, boolean bluetoothAvailable) {
        if (this._lastSentOutputDestination != OutputDestination.OTHER) {
            return this._lastSentOutputDestination;
        }
        switch (mode) {
            case RECORD_AUDIO_MESSAGE:
            case CAPTURE:
            case CALL_PREFER_SPEAKER:
                return computeOutputDestination(options, headsetAvailable, bluetoothAvailable, OutputDestination.SPEAKER);
            case CALL:
                return computeOutputDestination(options, headsetAvailable, bluetoothAvailable, OutputDestination.EARPIECE);
            case INCOMING_RING:
                return computeOutputDestination(options, headsetAvailable, bluetoothAvailable, OutputDestination.SPEAKER);
            case MOJI_PLAYBACK:
            case VIDEO_PLAYBACK:
            case AUDIO_PLAYBACK:
            case AUDIO_PLAYBACK_WITH_SCREEN_READER:
            case DEFAULT:
                return computeOutputDestination(options, headsetAvailable, false, OutputDestination.SPEAKER);
            default:
                FLog.e(TAG, String.format("outputDestinationForMode called with unknown mode %s", new Object[]{mode}));
                return null;
        }
    }

    private static OutputDestination computeOutputDestination(AudioOptions options, boolean allowHeadset, boolean allowBluetooth, OutputDestination fallback) {
        if (options.d() && allowHeadset) {
            return OutputDestination.WIRED_HEADSET;
        }
        if (BluetoothReceiver.b() && allowBluetooth) {
            return OutputDestination.BLUETOOTH;
        }
        return fallback;
    }

    private static com.microsoft.skype.b.a<Integer, Integer> androidAudioConfigForMode(a mode, boolean phoneCall) {
        if (phoneCall) {
            return new com.microsoft.skype.b.a(Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(-1));
        }
        switch (mode) {
            case RECORD_AUDIO_MESSAGE:
            case CAPTURE:
            case MOJI_PLAYBACK:
            case VIDEO_PLAYBACK:
            case AUDIO_PLAYBACK:
            case AUDIO_PLAYBACK_WITH_SCREEN_READER:
            case DEFAULT:
                return new com.microsoft.skype.b.a(Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(-1));
            case CALL_PREFER_SPEAKER:
            case CALL:
                return new com.microsoft.skype.b.a(Integer.valueOf(0), Integer.valueOf(3));
            case INCOMING_RING:
                return new com.microsoft.skype.b.a(Integer.valueOf(2), Integer.valueOf(1));
            default:
                FLog.e(TAG, String.format("androidAudioConfigForMode called with unknown mode %s", new Object[]{mode}));
                return new com.microsoft.skype.b.a(Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(-1));
        }
    }

    private static com.microsoft.skype.b.a<Boolean, Integer> focusForMode(a mode) {
        switch (mode) {
            case RECORD_AUDIO_MESSAGE:
            case CAPTURE:
            case CALL_PREFER_SPEAKER:
            case CALL:
            case MOJI_PLAYBACK:
            case VIDEO_PLAYBACK:
            case AUDIO_PLAYBACK:
            case AUDIO_PLAYBACK_WITH_SCREEN_READER:
                return new com.microsoft.skype.b.a(Boolean.valueOf(true), Integer.valueOf(2));
            case INCOMING_RING:
                return new com.microsoft.skype.b.a(Boolean.valueOf(true), Integer.valueOf(2));
            case DEFAULT:
                return new com.microsoft.skype.b.a(Boolean.valueOf(false), Integer.valueOf(0));
            default:
                FLog.e(TAG, String.format("focusForMode called with unknown mode %s", new Object[]{mode}));
                return new com.microsoft.skype.b.a(Boolean.valueOf(false), Integer.valueOf(0));
        }
    }

    @Nonnull
    private b currentSettings() {
        return new b(this, this._lastKnownAudioStream, this._audioFocusRequested, this._audioFocusRequestDurationHint, this.audioOptions.f(), this._lastKnownAudioMode);
    }

    @Nonnull
    private b mergedSettingForSettings(Collection<a> valuesToConsider, String causeId) {
        Object obj;
        boolean wantsFocus = false;
        int computedFocusDurationHint = 0;
        int computedAndroidAudioMode = 0;
        int computedAndroidAudioStream = Integer.MIN_VALUE;
        boolean inPhoneCall = this.phoneListener != null && this.phoneListener.a();
        OutputDestination computedOutputRoute = defaultOutputDestination(this.audioOptions, valuesToConsider, inPhoneCall);
        String str = TAG;
        String str2 = "mergedSettingForSettings default output route computed as %s (causeId: %s)";
        if (computedOutputRoute == null) {
            obj = "CURRENT";
        } else {
            OutputDestination obj2 = computedOutputRoute;
        }
        FLog.i(str, str2, obj2, (Object) causeId);
        for (a mode : valuesToConsider) {
            com.microsoft.skype.b.a<Boolean, Integer> focusSettings = focusForMode(mode);
            if (((Boolean) focusSettings.a).booleanValue()) {
                wantsFocus = true;
                computedFocusDurationHint = ((Integer) focusSettings.b).intValue();
            }
            com.microsoft.skype.b.a<Integer, Integer> config = androidAudioConfigForMode(mode, inPhoneCall);
            int audioStream = ((Integer) config.a).intValue();
            if (!(audioStream == Integer.MIN_VALUE || computedAndroidAudioStream == 0)) {
                computedAndroidAudioStream = audioStream;
            }
            int androidMode = ((Integer) config.b).intValue();
            if (androidMode == 3 || (androidMode == 1 && computedAndroidAudioMode != 3)) {
                computedAndroidAudioMode = androidMode;
            }
            if (computedOutputRoute != null) {
                OutputDestination outputRoute = outputDestinationForMode(this.audioOptions, mode, this.audioOptions.d(), this.audioOptions.b());
                if (outputRoute != null && outputRoute.f > computedOutputRoute.f) {
                    computedOutputRoute = outputRoute;
                }
            }
        }
        return new b(this, computedAndroidAudioStream, wantsFocus, computedFocusDurationHint, computedOutputRoute, computedAndroidAudioMode);
    }

    public String getName() {
        return "AudioManager";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put(AudioOutputChangedEvent, AudioOutputChangedEvent);
        constants.put("AudioOutputSpeaker", Integer.valueOf(OutputDestination.SPEAKER.f));
        constants.put("AudioOutputEarpiece", Integer.valueOf(OutputDestination.EARPIECE.f));
        constants.put("AudioOutputWiredHeadset", Integer.valueOf(OutputDestination.WIRED_HEADSET.f));
        constants.put("AudioOutputBluetooth", Integer.valueOf(OutputDestination.BLUETOOTH.f));
        constants.put("AudioOutputOther", Integer.valueOf(OutputDestination.OTHER.f));
        constants.put(AudioInterruptedChangedEvent, AudioInterruptedChangedEvent);
        constants.put(AvailableAudioOutputsChangedEvent, AvailableAudioOutputsChangedEvent);
        constants.put(MediaActionEvent, MediaActionEvent);
        return constants;
    }

    @ReactMethod
    public void setConfig(am config) {
    }

    @ReactMethod
    public void requestMode(int intMode, String causeId, ae promise) {
        exchangeMode(0, intMode, causeId, promise);
    }

    @ReactMethod
    public void exchangeMode(int existingToken, int intNewMode, String causeId, ae promise) {
        final int i = intNewMode;
        final int i2 = existingToken;
        final String str = causeId;
        final ae aeVar = promise;
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule e;

            public final void run() {
                Object newMode = a.a(i);
                if (i2 != 0) {
                    FLog.i(AudioManagerModule.TAG, "Audio mode %s requested to replace token %d (causeId %s)", newMode, Integer.valueOf(i2), str);
                } else {
                    FLog.i(AudioManagerModule.TAG, "Audio mode %s requested (causeId %s)", newMode, str);
                }
                this.e.requestMode(newMode, i2 == 0 ? null : Integer.valueOf(i2), str, aeVar);
            }
        });
    }

    @ReactMethod
    public void requestOutput(final int output, ae promise) {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule b;

            public final void run() {
                final String causeId = String.format("%x", new Object[]{Integer.valueOf(this.b.random.nextInt())});
                FLog.e(AudioManagerModule.TAG, "Requesting new output destination (causeId %s)", causeId);
                this.b.audioOptions.a(OutputDestination.a(output), new Action1<Boolean>(this) {
                    final /* synthetic */ AnonymousClass13 b;

                    public final /* synthetic */ void a(Object obj) {
                        if (((Boolean) obj).booleanValue()) {
                            FLog.i(AudioManagerModule.TAG, "Succeeded to switch audio output to %d (causeId %s)", Integer.valueOf(output), causeId);
                            this.b.b.maybeSendAudioOutputEvent(OutputDestination.a(output), causeId);
                            return;
                        }
                        FLog.e(AudioManagerModule.TAG, "Failed to switch audio output to %d (causeId %s)", Integer.valueOf(output), causeId);
                    }
                }, causeId);
            }
        });
    }

    @ReactMethod
    public void requestAvailableOutputRoutesArray(final int currentOutputNumeric, final ae promise) {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule c;

            public final void run() {
                int i = 2;
                ae aeVar = promise;
                AudioOptions access$600 = this.c.audioOptions;
                int[] iArr = new int[4];
                iArr[0] = OutputDestination.SPEAKER.f;
                if (access$600.d()) {
                    iArr[1] = OutputDestination.WIRED_HEADSET.f;
                } else if (access$600.c()) {
                    iArr[1] = OutputDestination.EARPIECE.f;
                } else {
                    i = 1;
                }
                if (access$600.b()) {
                    int i2 = i + 1;
                    iArr[i] = OutputDestination.BLUETOOTH.f;
                    i = i2;
                }
                aeVar.a(com.facebook.react.bridge.b.a(Arrays.copyOf(iArr, i)));
            }
        });
    }

    private void requestMode(a newMode, Integer maybeTokenToReplace, String causeId, ae promise) {
        int i = this.nextToken;
        this.nextToken = i + 1;
        Object token = Integer.valueOf(i);
        this.tokens.put(token, newMode);
        try {
            if (this.tokens.size() == 1) {
                this._lastSentOutputDestination = OutputDestination.OTHER;
            }
            calcSettingsAndUpdateDangerous(causeId, maybeTokenToReplace);
            if (maybeTokenToReplace != null) {
                this.tokens.remove(maybeTokenToReplace);
            }
            FLog.i(TAG, "returning new token %d for request (causeId %s)", token, (Object) causeId);
            promise.a(token);
        } catch (Throwable e) {
            this.tokens.remove(token);
            promise.a(e);
        }
    }

    private void calcSettingsAndUpdate(String causeId, Integer maybeExcludeToken) {
        try {
            calcSettingsAndUpdateDangerous(causeId, maybeExcludeToken);
        } catch (Exception e) {
        }
    }

    private void calcSettingsAndUpdateDangerous(final String causeId, Integer maybeExcludeToken) {
        try {
            an.a(com.microsoft.skype.a.a.a(this.serialQueue), "Must execute on audioQueue");
            if (this._initialized) {
                Collection<a> valuesToConsider;
                if (this.tokens.size() == 0) {
                    this._lastSentOutputDestination = OutputDestination.OTHER;
                }
                if (maybeExcludeToken != null) {
                    Collection<a> result = new ArrayList();
                    for (Integer key : this.tokens.keySet()) {
                        if (!key.equals(maybeExcludeToken)) {
                            result.add(this.tokens.get(key));
                        }
                    }
                    valuesToConsider = result;
                } else {
                    valuesToConsider = this.tokens.values();
                }
                this.phoneListener.b(causeId);
                b currentSettings = currentSettings();
                final b mergedSettings = mergedSettingForSettings(valuesToConsider, causeId);
                FLog.i(TAG, "calcSettingsAndUpdate: current: (%s), merged: (%s), modes: %s, background: %b, focusState: %s, inPhoneCall: %b (causeId %s)", currentSettings, mergedSettings, Arrays.deepToString(valuesToConsider.toArray()), Boolean.valueOf(this._isInBackground), stringForFocusDurationHint(this._lastKnownAudioFocusState), Boolean.valueOf(this.phoneListener.a()), causeId);
                if ((currentSettings.b != mergedSettings.b ? 1 : null) != null) {
                    if (mergedSettings.b) {
                        requestFocus(mergedSettings.a, mergedSettings.c, causeId);
                    } else {
                        abandonFocus(causeId);
                    }
                }
                if ((currentSettings.a != mergedSettings.a ? 1 : null) != null) {
                    Activity activity = getCurrentActivity();
                    if (activity != null) {
                        FLog.i(TAG, "Setting volume control stream %s -> %s (causeId %s)", stringForAudioStream(currentSettings.a), stringForAudioStream(mergedSettings.a), (Object) causeId);
                        this._lastKnownAudioStream = mergedSettings.a;
                        activity.setVolumeControlStream(mergedSettings.a);
                    }
                }
                Object obj = (mergedSettings.e == -1 || mergedSettings.e == -2 || currentSettings.e == mergedSettings.e) ? null : 1;
                if (obj != null) {
                    FLog.i(TAG, "Setting audio mode %s -> %s (causeId %s)", stringForAndroidAudioMode(currentSettings.e), stringForAndroidAudioMode(mergedSettings.e), (Object) causeId);
                    this._lastKnownAudioMode = mergedSettings.e;
                    this.androidAudioManager.setMode(mergedSettings.e);
                }
                obj = (mergedSettings.d == null || currentSettings.d == mergedSettings.d) ? null : 1;
                if (obj != null) {
                    FLog.i(TAG, "Setting audio route %s -> %s (causeId %s)", currentSettings.d, mergedSettings.d, (Object) causeId);
                    this.audioOptions.a(mergedSettings.d, new Action1<Boolean>(this) {
                        final /* synthetic */ AudioManagerModule c;

                        public final /* synthetic */ void a(Object obj) {
                            if (((Boolean) obj).booleanValue()) {
                                this.c.maybeSendAudioOutputEvent(mergedSettings.d, causeId);
                                return;
                            }
                            FLog.e(AudioManagerModule.TAG, "Failed to update settings (causeId %s)", causeId);
                        }
                    }, causeId);
                    return;
                }
                return;
            }
            FLog.i(TAG, "calcSettingsAndUpdate: Skipping - not initialized (causeId: %s)", (Object) causeId);
        } catch (Throwable e) {
            FLog.e(TAG, e, "Failed to update settings (causeId %s)", causeId);
            throw e;
        }
    }

    private void maybeSendAudioOutputEvent(OutputDestination newDestination, String causeId) {
        if (newDestination != this._lastSentOutputDestination) {
            FLog.i(TAG, "Sending output changed event to %s (causeId %s)", (Object) newDestination, (Object) causeId);
            sendEvent(AudioOutputChangedEvent, Integer.valueOf(newDestination.f));
            sendEvent(AvailableAudioOutputsChangedEvent, com.facebook.react.bridge.b.a(this.audioOptions.a(newDestination)));
            this._lastSentOutputDestination = newDestination;
        }
    }

    @ReactMethod
    public void release(final int token, final String causeId) {
        this.serialQueue.c(new Runnable(this) {
            final /* synthetic */ AudioManagerModule c;

            public final void run() {
                this.c.tokens.remove(Integer.valueOf(token));
                FLog.i(AudioManagerModule.TAG, "Token %d release (causeId %s)", Integer.valueOf(token), causeId);
                this.c.calcSettingsAndUpdate(causeId, null);
            }
        });
    }

    @ReactMethod
    public void requestEndToInterruption() {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.a.random.nextInt())});
                FLog.i(AudioManagerModule.TAG, "requestEndToInterruption: (causeId %s)", causeId);
                this.a.calcSettingsAndUpdate(causeId, null);
            }
        });
    }

    @ReactMethod
    public void audioOutput(final ae promise) {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule b;

            public final void run() {
                promise.a(Integer.valueOf(this.b.audioOptions.f().f));
            }
        });
    }

    @ReactMethod
    public void availableAudioOutputs(final ae promise) {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule b;

            public final void run() {
                promise.a(com.facebook.react.bridge.b.a(this.b.audioOptions.a(this.b.audioOptions.f())));
            }
        });
    }

    @ReactMethod
    public void audioInterrupted(final ae promise) {
        FLog.i(TAG, "audioInterrupted");
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule b;

            public final void run() {
                promise.a(Boolean.valueOf(this.b.interruptionInProgress()));
            }
        });
    }

    private boolean interruptionInProgress() {
        return this.phoneListener.a();
    }

    private void beginAudioInterruption(String causeId) {
        if (interruptionInProgress()) {
            FLog.i(TAG, "Sending audio interruption BEGIN event (causeId %s)", (Object) causeId);
            sendEvent(AudioInterruptedChangedEvent, Boolean.valueOf(true));
            return;
        }
        FLog.i(TAG, "Ignoring beginAudioInterruption - none progress (causeId %s)", (Object) causeId);
    }

    private void endAudioInterruption(String causeId) {
        if (interruptionInProgress()) {
            FLog.i(TAG, "Ignoring endAudioInterruption - still in progress (causeId %s)", (Object) causeId);
            return;
        }
        FLog.i(TAG, "Sending audio interruption END event (causeId %s)", (Object) causeId);
        sendEvent(AudioInterruptedChangedEvent, Boolean.valueOf(false));
    }

    private void sendEvent(String eventName, Object params) {
        ((RCTNativeAppEventEmitter) getReactApplicationContext().a(RCTNativeAppEventEmitter.class)).emit(eventName, params);
    }

    private void requestFocus(int audioStream, int durationHint, String causeId) {
        FLog.i(TAG, "Requesting audio focus %s-%s (causeId %s)", stringForAudioStream(audioStream), stringForFocusDurationHint(durationHint), (Object) causeId);
        if (this.androidAudioManager.requestAudioFocus(this.audioFocusChangeListener, audioStream, durationHint) == 1) {
            this._audioFocusRequested = true;
            this._audioFocusRequestDurationHint = durationHint;
            return;
        }
        FLog.w(TAG, "audio focus request failed (%s)", causeId);
    }

    private void abandonFocus(String causeId) {
        FLog.i(TAG, "Abandoning audio focus (causeId %s)", (Object) causeId);
        this.androidAudioManager.abandonAudioFocus(this.audioFocusChangeListener);
        this._audioFocusRequested = false;
    }

    public void onHostResume() {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.a.random.nextInt())});
                FLog.i(AudioManagerModule.TAG, "onHostResume: (tokens %d; causeId %s)", Integer.valueOf(this.a.tokens.size()), causeId);
                this.a._isInBackground = false;
                if (this.a.tokens.size() > 0) {
                    this.a.calcSettingsAndUpdate(causeId, null);
                } else {
                    this.a.phoneListener.b(causeId);
                }
            }
        });
    }

    public void onHostPause() {
        this.serialQueue.b(new Runnable(this) {
            final /* synthetic */ AudioManagerModule a;

            {
                this.a = this$0;
            }

            public final void run() {
                FLog.i(AudioManagerModule.TAG, "onHostPause: (causeId %s)", String.format("%x", new Object[]{Integer.valueOf(this.a.random.nextInt())}));
                this.a._isInBackground = true;
            }
        });
    }

    public void onHostDestroy() {
    }

    private static String stringForAndroidAudioMode(int mode) {
        switch (mode) {
            case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                return "MODE_INVALID";
            case -1:
                return "MODE_CURRENT";
            case 0:
                return "MODE_NORMAL";
            case 1:
                return "MODE_RINGTONE";
            case 2:
                return "MODE_IN_CALL";
            case 3:
                return "MODE_IN_COMMUNICATION";
            default:
                return "MODE_UNKNOWN";
        }
    }

    private static String stringForAudioStream(int audioStream) {
        switch (audioStream) {
            case Integer.MIN_VALUE:
                return "USE_DEFAULT_STREAM_TYPE";
            case 0:
                return "VOICE_CALL";
            case 1:
                return "SYSTEM";
            case 2:
                return "RING";
            case 3:
                return "MUSIC";
            case 4:
                return "ALARM";
            case 5:
                return "NOTIFICATION";
            case 8:
                return "DTMF";
            default:
                return "UNKNOWN";
        }
    }

    private static String stringForFocusDurationHint(int durationHint) {
        switch (durationHint) {
            case 1:
                return "GAIN";
            case 2:
                return "GAIN_TRANSIENT";
            case 3:
                return "GAIN_TRANSIENT_MAY_DUCK";
            case 4:
                return "GAIN_TRANSIENT_EXCLUSIVE";
            default:
                return "Unknown";
        }
    }
}
