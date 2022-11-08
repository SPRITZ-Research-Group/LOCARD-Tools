package com.skype.audiomanager;

import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.facebook.common.logging.FLog;
import com.microsoft.media.NGCPcmHost;
import com.microsoft.media.NGCPcmHost.AudioRoute;
import com.skype.audiomanager.BluetoothReceiver.BluetoothListener;
import com.skype.audiomanager.ModernAudioDeviceMonitor.AudioDeviceListener;
import com.skype.audiomanager.ModernAudioDeviceMonitor.AudioDeviceType;
import com.skype.audiomanager.WiredHeadsetReceiver.WiredHeadsetListener;
import com.skype.audiomanager.WiredHeadsetReceiver.WiredHeadsetStatus;
import com.skype.slimcore.skylib.PcmHostCallback;
import com.skype.slimcore.skylib.PcmHostCallback.Listener;
import com.skype.slimcore.skylib.SkyLibManager.NGCPcmHostExecution;
import com.skype.slimcore.skylib.SkyLibManager.PcmHostCallbackExecution;
import com.skype.slimcore.skylib.SkyLibProvider;
import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Random;
import javax.annotation.Nonnull;

public class AudioOptions {
    @Nonnull
    private OutputDestination a = OutputDestination.OTHER;
    private final Random b = new Random();
    private final DeviceObserver<OutputDestination, AudioDeviceStatus, String> c;
    private final AudioManager d;
    private final BluetoothReceiver e;
    private final WiredHeadsetReceiver f;
    private MediaSessionCompat g;
    private final ModernAudioDeviceMonitor h;
    private final boolean i;
    private a j;
    private WeakReference<SkyLibProvider> k;
    private BluetoothListener l = new BluetoothListener(this) {
        final /* synthetic */ AudioOptions a;

        {
            this.a = this$0;
        }

        public final void a(BluetoothDevice device, String causeId) {
            FLog.i("AudioOptions", "BluetoothReceiver.deviceConnected %s (causeId %s)", (Object) device, (Object) causeId);
            this.a.c.a(OutputDestination.BLUETOOTH, AudioDeviceStatus.CONNECTED, causeId);
        }

        public final void b(BluetoothDevice device, String causeId) {
            FLog.i("AudioOptions", "BluetoothReceiver.deviceDisconnected %s (causeId %s)", (Object) device, (Object) causeId);
            this.a.c.a(OutputDestination.BLUETOOTH, AudioDeviceStatus.DISCONNECTED, causeId);
        }

        public final void a(String causeId) {
            FLog.i("AudioOptions", "BluetoothReceiver.userMediaAction (causeId %s)", (Object) causeId);
            this.a.a(causeId);
        }
    };
    private WiredHeadsetListener m = new WiredHeadsetListener(this) {
        final /* synthetic */ AudioOptions a;

        {
            this.a = this$0;
        }

        public final void a(WiredHeadsetStatus status, String causeId) {
            FLog.i("AudioOptions", "WiredHeadsetReceiver.headsetStateChanged %s (causeId %s)", (Object) status, (Object) causeId);
            this.a.c.a(OutputDestination.WIRED_HEADSET, status != WiredHeadsetStatus.UNPLUGGED ? AudioDeviceStatus.CONNECTED : AudioDeviceStatus.DISCONNECTED, causeId);
        }
    };
    private AudioDeviceListener n = new AudioDeviceListener(this) {
        final /* synthetic */ AudioOptions a;

        {
            this.a = this$0;
        }

        public final void a(EnumSet<AudioDeviceType> newStatus, EnumSet<AudioDeviceType> oldStatus, String causeId) {
            FLog.i("AudioOptions", "ModernAudioDeviceMonitor.availableAudioDeviceTypesChanged headphones: %b usb: %b (causeId %s)", Boolean.valueOf(newStatus.contains(AudioDeviceType.HEADPHONES)), Boolean.valueOf(newStatus.contains(AudioDeviceType.USB)), (Object) causeId);
            boolean addedDevice = (newStatus.contains(AudioDeviceType.HEADPHONES) && !oldStatus.contains(AudioDeviceType.HEADPHONES)) || (newStatus.contains(AudioDeviceType.USB) && !oldStatus.contains(AudioDeviceType.USB));
            this.a.c.a(OutputDestination.WIRED_HEADSET, addedDevice ? AudioDeviceStatus.CONNECTED : AudioDeviceStatus.DISCONNECTED, causeId);
        }
    };
    private final android.support.v4.media.session.MediaSessionCompat.a o = new android.support.v4.media.session.MediaSessionCompat.a(this) {
        final /* synthetic */ AudioOptions a;

        {
            this.a = this$0;
        }

        public final boolean a(Intent mediaButtonEvent) {
            String causeId = String.format("%x", new Object[]{Integer.valueOf(this.a.b.nextInt())});
            KeyEvent event = (KeyEvent) mediaButtonEvent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            FLog.w("AudioOptions", "onMediaButtonEvent:  causeId:" + causeId + " event:" + event.toString());
            if (event.getAction() != 1) {
                return false;
            }
            this.a.a(causeId);
            return true;
        }
    };

    public enum AudioDeviceStatus {
        UNKNOWN(0),
        CONNECTED(1),
        DISCONNECTED(2);
        
        public final int d;

        private AudioDeviceStatus(int value) {
            this.d = value;
        }
    }

    public enum OutputDestination {
        SPEAKER(1),
        EARPIECE(2),
        WIRED_HEADSET(3),
        BLUETOOTH(4),
        OTHER(5);
        
        public final int f;

        private OutputDestination(int value) {
            this.f = value;
        }

        @Nonnull
        public static OutputDestination a(int value) {
            switch (value) {
                case 1:
                    return SPEAKER;
                case 2:
                    return EARPIECE;
                case 3:
                    return WIRED_HEADSET;
                case 4:
                    return BLUETOOTH;
                case 5:
                    return OTHER;
                default:
                    throw new RuntimeException(String.format(Locale.US, "Unexpected OutputDestination type %d", new Object[]{Integer.valueOf(value)}));
            }
        }
    }

    private final class a implements Listener {
        final /* synthetic */ AudioOptions a;

        public a(final AudioOptions audioOptions, WeakReference<SkyLibProvider> skyLibProvider) {
            this.a = audioOptions;
            SkyLibProvider provider = (SkyLibProvider) skyLibProvider.get();
            if (provider != null) {
                provider.d().a(new PcmHostCallbackExecution(this) {
                    final /* synthetic */ a b;

                    public final void a(PcmHostCallback pcmHostCallback) {
                        pcmHostCallback.a(this.b);
                    }
                });
            }
        }

        public final void a(boolean success, String audioRoute) {
            String causeId = String.format("%x", new Object[]{Integer.valueOf(this.a.b.nextInt())});
            FLog.i("PcmHostCallbackListener", "onAudioRouteChanged success=%b, route=%s causeId=%s", Boolean.valueOf(success), (Object) audioRoute, (Object) causeId);
            if (!success && this.a.a()) {
                AudioOptions.a(this.a, causeId);
            }
        }

        public final void a() {
            FLog.i("PcmHostCallbackListener", "onStopRingoutRequested");
        }
    }

    public AudioOptions(Context context, WeakReference<SkyLibProvider> skyLibProvider, DeviceObserver<OutputDestination, AudioDeviceStatus, String> notifyOutputUpdateAction) {
        this.d = (AudioManager) context.getSystemService("audio");
        this.k = skyLibProvider;
        this.c = notifyOutputUpdateAction;
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            this.i = true;
        } else {
            FLog.w("AudioOptions", "Earpiece is not supported!");
            this.i = false;
        }
        this.e = new BluetoothReceiver(context);
        this.f = new WiredHeadsetReceiver(context);
        this.h = new ModernAudioDeviceMonitor(context);
    }

    public final void a(final Context context, String causeId) {
        FLog.i("AudioOptions", "initialize (causeId %s)", (Object) causeId);
        this.f.f();
        this.f.a(this.m, causeId);
        this.e.f();
        this.e.a(this.l, causeId);
        this.e.a(causeId);
        this.h.a(this.n, causeId);
        this.h.a();
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ AudioOptions b;

            public final void run() {
                Intent mediaButtonIntentForComponent = new Intent("android.intent.action.MEDIA_BUTTON");
                ComponentName cname = new ComponentName(context, MediaButtonReceiver.class);
                mediaButtonIntentForComponent.setComponent(cname);
                this.b.g = new MediaSessionCompat(context, "TAG", cname);
                this.b.g.a();
                this.b.g.a(new android.support.v4.media.session.PlaybackStateCompat.a().a().b());
                this.b.g.a(this.b.o);
                Intent mediaButtonIntentForReceiver = new Intent("android.intent.action.MEDIA_BUTTON");
                mediaButtonIntentForReceiver.setClass(context, MediaButtonReceiver.class);
                this.b.g.a(PendingIntent.getBroadcast(context, 0, mediaButtonIntentForReceiver, 0));
                this.b.g.b();
            }
        });
    }

    protected final void a(String causeId) {
        this.c.a(causeId);
    }

    public final void b(String causeId) {
        FLog.i("AudioOptions", "uninitialize (causeId %s)", (Object) causeId);
        this.f.b(this.m, causeId);
        this.f.g();
        this.e.b(this.l, causeId);
        this.e.g();
        this.h.b(this.n, causeId);
        this.h.b();
        if (this.j != null) {
            a aVar = this.j;
            SkyLibProvider skyLibProvider = (SkyLibProvider) aVar.a.k.get();
            if (skyLibProvider != null) {
                skyLibProvider.d().a(new PcmHostCallbackExecution(aVar) {
                    final /* synthetic */ a a;

                    {
                        this.a = this$1;
                    }

                    public final void a(PcmHostCallback pcmHostCallback) {
                        pcmHostCallback.b(this.a);
                    }
                });
            }
        }
    }

    public final void a(@Nonnull OutputDestination output, Action1<Boolean> completion, String causeId) {
        FLog.i("AudioOptions", "routeAudioOutput %s (causeId %s)", (Object) output, (Object) causeId);
        a(output, true, (Action1) completion, causeId);
    }

    private void a(@Nonnull OutputDestination output, boolean userAction, Action1<Boolean> outerCompletion, final String causeId) {
        OutputDestination actualOutputDestination = f();
        if (output == actualOutputDestination) {
            if (this.a != actualOutputDestination) {
                a(userAction, causeId);
            }
            outerCompletion.a(Boolean.valueOf(true));
            return;
        }
        FLog.i("AudioOptions", "intRouteAudioOutput %s userAction=%b (causeId %s)", (Object) output, Boolean.valueOf(userAction), (Object) causeId);
        final Action1<Boolean> action1 = outerCompletion;
        final OutputDestination outputDestination = output;
        final String str = causeId;
        final boolean z = userAction;
        final Action1 innerCompletion = new Action1<Boolean>(this) {
            final /* synthetic */ AudioOptions e;

            public final /* synthetic */ void a(Object obj) {
                Boolean bool = (Boolean) obj;
                action1.a(bool);
                if (bool.booleanValue()) {
                    if (outputDestination != this.e.f()) {
                        FLog.e("AudioOptions", "intRouteAudioOutput %s appeared to fail. Actual output is %s (causeId %s)", outputDestination, this.e.f(), str);
                        return;
                    } else if (!this.e.a(z, str)) {
                        OutputDestination e = this.e.e();
                        FLog.i("AudioOptions", "Reverting audio output destination to %s (causeId %s)", (Object) e, str);
                        this.e.a(e, false, action1, str);
                        return;
                    } else {
                        return;
                    }
                }
                FLog.e("AudioOptions", "intRouteAudioOutput %s failed (causeId %s)", outputDestination, str);
            }
        };
        switch (output) {
            case SPEAKER:
                this.e.b(causeId, new Action1<Boolean>(this) {
                    final /* synthetic */ AudioOptions c;

                    public final /* synthetic */ void a(Object obj) {
                        if (!((Boolean) obj).booleanValue()) {
                            FLog.e("AudioOptions", "bluetoothReceiver.stopBluetooth failed while switching to speaker (causeId %s)", causeId);
                        }
                        if (!this.c.d.isSpeakerphoneOn()) {
                            this.c.d.setSpeakerphoneOn(true);
                        }
                        innerCompletion.a(Boolean.valueOf(true));
                    }
                });
                return;
            case EARPIECE:
                if (this.d.isSpeakerphoneOn()) {
                    this.d.setSpeakerphoneOn(false);
                }
                this.e.b(causeId, new Action1<Boolean>(this) {
                    final /* synthetic */ AudioOptions c;

                    public final /* synthetic */ void a(Object obj) {
                        if (!((Boolean) obj).booleanValue()) {
                            FLog.e("AudioOptions", "bluetoothReceiver.stopBluetooth failed while switching to earpiece (causeId %s)", causeId);
                        }
                        innerCompletion.a(Boolean.valueOf(true));
                    }
                });
                return;
            case BLUETOOTH:
                if (this.d.isSpeakerphoneOn()) {
                    this.d.setSpeakerphoneOn(false);
                }
                this.e.a(causeId, innerCompletion);
                return;
            case WIRED_HEADSET:
                if (this.d.isSpeakerphoneOn()) {
                    this.d.setSpeakerphoneOn(false);
                }
                this.e.b(causeId, new Action1<Boolean>(this) {
                    final /* synthetic */ AudioOptions c;

                    public final /* synthetic */ void a(Object obj) {
                        if (!((Boolean) obj).booleanValue()) {
                            FLog.e("AudioOptions", "bluetoothReceiver.stopBluetooth failed while switching to wired headset (causeId %s)", causeId);
                        }
                        innerCompletion.a(Boolean.valueOf(true));
                    }
                });
                return;
            default:
                return;
        }
    }

    private boolean a(boolean userAction, String causeId) {
        OutputDestination currentDestination = this.a;
        OutputDestination newDestination = f();
        if (currentDestination == newDestination) {
            return true;
        }
        if (!b(newDestination) && newDestination != e() && !userAction) {
            return false;
        }
        this.a = newDestination;
        if (this.j == null) {
            this.j = new a(this, this.k);
        }
        final OutputDestination outputDestination = this.a;
        SkyLibProvider skyLibProvider = (SkyLibProvider) this.k.get();
        if (skyLibProvider != null) {
            skyLibProvider.d().a(new NGCPcmHostExecution(this) {
                final /* synthetic */ AudioOptions b;

                public final void a(NGCPcmHost ngcPcmHost) {
                    AudioRoute route = AudioOptions.a(this.b, outputDestination);
                    if (route == null) {
                        FLog.e("AudioOptions", "Failed to convert OutputDestination %s to valid NGCPcmHost.AudioRoute", outputDestination.toString());
                        return;
                    }
                    ngcPcmHost.SetRoute(route.toString());
                }
            });
        }
        FLog.i("AudioOptions", "Active audio output destination is %s (causeId %s)", this.a, (Object) causeId);
        return true;
    }

    public final int[] a(@Nonnull OutputDestination currentOutputDestination) {
        if (currentOutputDestination == OutputDestination.WIRED_HEADSET) {
            return new int[]{OutputDestination.WIRED_HEADSET.f};
        } else if (currentOutputDestination == OutputDestination.BLUETOOTH) {
            return new int[]{OutputDestination.BLUETOOTH.f};
        } else if (this.i) {
            return new int[]{OutputDestination.SPEAKER.f, OutputDestination.EARPIECE.f};
        } else {
            return new int[]{OutputDestination.SPEAKER.f};
        }
    }

    public final boolean a() {
        return b(this.a);
    }

    public final boolean c() {
        return this.i;
    }

    public final boolean d() {
        return this.f.b() || this.h.c();
    }

    public final OutputDestination e() {
        return this.d.isSpeakerphoneOn() ? OutputDestination.SPEAKER : OutputDestination.EARPIECE;
    }

    @Nonnull
    public final OutputDestination f() {
        if (this.e.d()) {
            return OutputDestination.BLUETOOTH;
        }
        Object obj;
        if (!d() || this.d.isSpeakerphoneOn()) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            return OutputDestination.WIRED_HEADSET;
        }
        if (this.d.isSpeakerphoneOn()) {
            return OutputDestination.SPEAKER;
        }
        return OutputDestination.EARPIECE;
    }

    private static boolean b(OutputDestination destination) {
        return destination == OutputDestination.WIRED_HEADSET || destination == OutputDestination.BLUETOOTH;
    }

    public final boolean b() {
        return BluetoothReceiver.b() && this.e.c() > 0;
    }

    static /* synthetic */ AudioRoute a(AudioOptions x0, OutputDestination x1) {
        switch (x1) {
            case SPEAKER:
                return AudioRoute.SPEAKER;
            case EARPIECE:
                return AudioRoute.EARPIECE;
            case BLUETOOTH:
                return AudioRoute.BLUETOOTH;
            case WIRED_HEADSET:
                if (!x0.f.b()) {
                    if (!x0.h.c()) {
                        FLog.e("AudioOptions", "Routing destination to %s, but we cannot detect a headset", x1);
                        break;
                    }
                    return AudioRoute.HEADSET_WITH_MIC;
                }
                return x0.f.c() == WiredHeadsetStatus.PLUGGED_WITH_MIC ? AudioRoute.HEADSET_WITH_MIC : AudioRoute.HEADSET_WITHOUT_MIC;
        }
        return null;
    }

    static /* synthetic */ void a(AudioOptions x0, String x1) {
        OutputDestination e = x0.e();
        FLog.i("AudioOptions", "resetOutputDestination %s (causeId %s)", (Object) e, (Object) x1);
        x0.a(e, false, null, x1);
    }
}
