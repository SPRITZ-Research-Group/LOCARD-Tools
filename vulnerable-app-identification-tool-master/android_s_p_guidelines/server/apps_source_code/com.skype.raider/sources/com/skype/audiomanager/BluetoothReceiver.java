package com.skype.audiomanager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import com.facebook.common.logging.FLog;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BluetoothReceiver extends RegisterableBroadcastReceiver {
    private final Random b = new Random();
    private final AudioManager c = ((AudioManager) this.a.getSystemService("audio"));
    private ServiceListener d;
    private final Set<BluetoothListener> e = new CopyOnWriteArraySet();
    private final Set<BluetoothDevice> f = new HashSet();
    private final a g = a.a("BluetoothReceiverQueue", b.DEFAULT);
    private int h = 0;
    private String i = null;
    private final Handler j;
    private final IntentFilter k;

    public interface BluetoothListener {
        void a(BluetoothDevice bluetoothDevice, String str);

        void a(String str);

        void b(BluetoothDevice bluetoothDevice, String str);
    }

    static /* synthetic */ void a(BluetoothReceiver x0, BluetoothDevice x1, String x2) {
        Object obj = 1;
        com.facebook.infer.annotation.a.a(a.a(x0.g));
        BluetoothClass bluetoothClass = x1.getBluetoothClass();
        bluetoothClass.getDeviceClass();
        Object obj2 = ((bluetoothClass.hasService(262144) || bluetoothClass.hasService(2097152)) && !(bluetoothClass.hasService(4194304) && bluetoothClass.getMajorDeviceClass() == 1792)) ? 1 : null;
        if (obj2 != null) {
            obj = obj2;
        } else if (bluetoothClass.getMajorDeviceClass() != 7936) {
            obj = null;
        }
        if (obj == null) {
            FLog.i("BluetoothReceiver", "deviceConnected ignoring %s - %d total devices (causeId: %s)", "Addr:" + x1.getAddress() + "; Name:" + x1.getName() + "; Class:" + x1.getBluetoothClass() + "; Type:" + x1.getType(), Integer.valueOf(x0.f.size()), (Object) x2);
        } else if (x0.f.add(x1)) {
            FLog.i("BluetoothReceiver", "deviceConnected accepted %s - %d total devices (causeId: %s)", "Addr:" + x1.getAddress() + "; Name:" + x1.getName() + "; Class:" + x1.getBluetoothClass() + "; Type:" + x1.getType(), Integer.valueOf(x0.f.size()), (Object) x2);
            for (BluetoothListener a : x0.e) {
                a.a(x1, x2);
            }
        }
    }

    static /* synthetic */ void a(BluetoothReceiver x0, boolean x1, String x2, final Action1 x3) {
        com.facebook.infer.annotation.a.a(a.a(x0.g));
        FLog.i("BluetoothReceiver", "startStopBluetooth: start: %b causeId: %s", Boolean.valueOf(x1), (Object) x2);
        if (x1) {
            if (h() && !x0.c.isBluetoothScoOn()) {
                x0.c.setBluetoothScoOn(true);
                com.facebook.infer.annotation.a.a(a.a(x0.g));
                try {
                    FLog.i("BluetoothReceiver", "startBluetoothSco (causeId %s)", (Object) x2);
                    if (!x0.c.isBluetoothScoOn()) {
                        x0.h = 0;
                    }
                    x0.c.startBluetoothSco();
                    x0.h++;
                } catch (Throwable e) {
                    FLog.w("BluetoothReceiver", e, "AudioManager.startBluetoothSco() failed. Audio will not be routed to BT device (causeId %s)", x2);
                }
            }
        } else if (x0.c.isBluetoothScoOn()) {
            com.facebook.infer.annotation.a.a(a.a(x0.g));
            if (x0.h != 0) {
                FLog.i("BluetoothReceiver", "stopBluetoothSco (causeId %s)", (Object) x2);
                while (x0.h > 0) {
                    x0.c.stopBluetoothSco();
                    x0.h--;
                }
            }
        }
        x0.j.postDelayed(new Runnable(x0) {
            final /* synthetic */ BluetoothReceiver b;

            public final void run() {
                this.b.g.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        x3.a(Boolean.valueOf(true));
                    }
                });
            }
        }, 700);
    }

    public BluetoothReceiver(Context c) {
        super(c, "BluetoothReceiver");
        this.j = new Handler(c.getMainLooper());
        this.k = new IntentFilter();
        this.k.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        this.k.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        this.k.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        this.k.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        this.k.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        this.k.addAction("android.media.AUDIO_BECOMING_NOISY");
    }

    public final IntentFilter a() {
        return this.k;
    }

    public final void a(final BluetoothListener bluetoothListener, final String causeId) {
        this.g.b(new Runnable(this) {
            final /* synthetic */ BluetoothReceiver c;

            public final void run() {
                FLog.i("BluetoothReceiver", "addListener (causeId: %s)", causeId);
                for (BluetoothDevice device : this.c.f) {
                    bluetoothListener.a(device, causeId);
                }
                this.c.e.add(bluetoothListener);
            }
        });
    }

    public final void b(final BluetoothListener bluetoothListener, final String causeId) {
        this.g.b(new Runnable(this) {
            final /* synthetic */ BluetoothReceiver c;

            public final void run() {
                FLog.i("BluetoothReceiver", "removeListener (causeId: %s)", causeId);
                this.c.e.remove(bluetoothListener);
            }
        });
    }

    public void onReceive(Context context, final Intent intent) {
        this.g.b(new Runnable(this) {
            final /* synthetic */ BluetoothReceiver b;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void run() {
                String action = intent != null ? intent.getAction() : null;
                Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.b.b.nextInt())});
                if (action == null) {
                    FLog.i("BluetoothReceiver", "BluetoothReceiver: onReceive action is null (causeId %s)", causeId);
                    return;
                }
                int i;
                int state;
                int prevState;
                switch (action.hashCode()) {
                    case -1692127708:
                        if (action.equals("android.media.ACTION_SCO_AUDIO_STATE_UPDATED")) {
                            i = 5;
                            break;
                        }
                    case -1435586571:
                        if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                            i = 3;
                            break;
                        }
                    case -855499628:
                        if (action.equals("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED")) {
                            i = 1;
                            break;
                        }
                    case -549244379:
                        if (action.equals("android.media.AUDIO_BECOMING_NOISY")) {
                            i = 4;
                            break;
                        }
                    case 545516589:
                        if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                            i = 2;
                            break;
                        }
                    case 1244161670:
                        if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                            i = 0;
                            break;
                        }
                    default:
                        i = -1;
                        break;
                }
                Object device;
                switch (i) {
                    case 0:
                        state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                        device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (state == prevState) {
                            FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED: skipped without change; state %s (causeId: %s)", a.a(state), causeId);
                            break;
                        }
                        FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED: %s -> %s (device: %s, causeId: %s)", a.a(prevState), a.a(state), device, causeId);
                        if (state != 2) {
                            if ((prevState == 2 || prevState == 3) && state == 0) {
                                BluetoothReceiver.b(this.b, device, causeId);
                                break;
                            }
                        }
                        BluetoothReceiver.a(this.b, device, causeId);
                        break;
                    case 1:
                        state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                        device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (state == prevState) {
                            FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED: skipped without change; state %s (causeId: %s)", a.c(state), causeId);
                            break;
                        } else {
                            FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED: %s -> %s (device: %s, causeId: %s)", a.c(prevState), a.c(state), device, causeId);
                            break;
                        }
                    case 2:
                        state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                        device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (state == prevState) {
                            FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED: skipped without change; state %s (causeId: %s)", a.a(state), causeId);
                            break;
                        }
                        FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED: %s -> %s (device: %s, causeId: %s)", a.a(prevState), a.a(state), device, causeId);
                        if (state != 2) {
                            if ((prevState == 2 || prevState == 3) && state == 0) {
                                BluetoothReceiver.b(this.b, device, causeId);
                                break;
                            }
                        }
                        BluetoothReceiver.a(this.b, device, causeId);
                        break;
                    case 3:
                        state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                        device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (state == prevState) {
                            FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED: skipped without change; state %s (causeId: %s)", a.b(state), causeId);
                            break;
                        } else {
                            FLog.i("BluetoothReceiver", "BluetoothReceiver: BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED: %s -> %s (device: %s, causeId: %s)", a.b(prevState), a.b(state), device, causeId);
                            break;
                        }
                    case 4:
                        FLog.i("BluetoothReceiver", "BluetoothReceiver: AudioManager.ACTION_AUDIO_BECOMING_NOISY (causeId: %s)", causeId);
                        break;
                    case 5:
                        break;
                    default:
                        FLog.w("BluetoothReceiver", "BluetoothReceiver: Unexpected action %s (causeId %s)", action, causeId);
                        break;
                }
                state = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                prevState = intent.getIntExtra("android.media.extra.SCO_AUDIO_PREVIOUS_STATE", -1);
                if (state != prevState) {
                    FLog.i("BluetoothReceiver", "BluetoothReceiver: AudioManager.ACTION_SCO_AUDIO_STATE_UPDATED: %s -> %s (causeId: %s)", a.d(prevState), a.d(state), causeId);
                    if (state == 0 && prevState == 1 && BluetoothReceiver.h() && this.b.h > 0 && this.b.f.size() > 0 && this.b.i.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED") && BluetoothReceiver.b()) {
                        BluetoothReceiver.a(this.b, (String) causeId);
                    }
                } else {
                    FLog.i("BluetoothReceiver", "BluetoothReceiver: AudioManager.ACTION_SCO_AUDIO_STATE_UPDATED: skipped without change; state %s (causeId: %s)", a.d(state), causeId);
                }
                this.b.i = action;
            }
        });
    }

    public final void a(String causeId) {
        FLog.i("BluetoothReceiver", "loadAlreadyConnectedDevices (causeId: %s)", (Object) causeId);
        Context appContext = this.a.getApplicationContext();
        if (appContext.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter != null) {
                if (this.d == null) {
                    FLog.i("BluetoothReceiver", "createServiceListener (causeId: %s)", (Object) causeId);
                    this.d = new ServiceListener(this) {
                        final /* synthetic */ BluetoothReceiver a;

                        {
                            this.a = this$0;
                        }

                        public final void onServiceDisconnected(int profile) {
                            FLog.i("BluetoothReceiver", "onServiceDisconnected profile: %d (causeId: %s)", Integer.valueOf(profile), String.format("%x", new Object[]{Integer.valueOf(this.a.b.nextInt())}));
                        }

                        public final void onServiceConnected(final int profile, final BluetoothProfile proxy) {
                            this.a.g.b(new Runnable(this) {
                                final /* synthetic */ AnonymousClass4 c;

                                public final void run() {
                                    Object obj;
                                    Object innerCauseId = String.format("%x", new Object[]{Integer.valueOf(this.c.a.b.nextInt())});
                                    String str = "BluetoothReceiver";
                                    String str2 = "onServiceConnected profile %s proxy %s (causeId: %s)";
                                    int i = profile;
                                    switch (i) {
                                        case 1:
                                            obj = "HEADSET";
                                            break;
                                        case 2:
                                            obj = "A2DP";
                                            break;
                                        case 3:
                                            obj = "HEALTH";
                                            break;
                                        default:
                                            obj = "UNKNOWN" + String.valueOf(i);
                                            break;
                                    }
                                    FLog.i(str, str2, obj, proxy.toString(), innerCauseId);
                                    for (BluetoothDevice connectedDevice : proxy.getConnectedDevices()) {
                                        BluetoothReceiver.a(this.c.a, connectedDevice, innerCauseId);
                                    }
                                    BluetoothAdapter.getDefaultAdapter().closeProfileProxy(profile, proxy);
                                }
                            });
                        }
                    };
                }
                try {
                    adapter.getProfileProxy(appContext, this.d, 1);
                    adapter.getProfileProxy(appContext, this.d, 2);
                } catch (Throwable e) {
                    FLog.w("BluetoothReceiver", e, "AudioManager.loadAlreadyConnectedDevices() failed. (causeId %s)", causeId);
                }
            }
        }
    }

    public static boolean b() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            return false;
        }
        int connectState_HEADSET = adapter.getProfileConnectionState(1);
        int connectState_A2DP = adapter.getProfileConnectionState(2);
        if (connectState_HEADSET == 2 || connectState_A2DP == 2) {
            return true;
        }
        return false;
    }

    public final int c() {
        return this.f.size();
    }

    public final boolean d() {
        return h() && this.c.isBluetoothScoOn();
    }

    private static boolean h() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return adapter != null && adapter.isEnabled();
    }

    public final void a(final String causeId, final Action1<Boolean> completion) {
        this.j.postDelayed(new Runnable(this) {
            final /* synthetic */ BluetoothReceiver c;

            public final void run() {
                this.c.g.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        BluetoothReceiver.a(this.a.c, true, causeId, completion);
                    }
                });
            }
        }, 20);
    }

    public final void b(final String causeId, final Action1<Boolean> completion) {
        this.g.b(new Runnable(this) {
            final /* synthetic */ BluetoothReceiver c;

            public final void run() {
                BluetoothReceiver.a(this.c, false, causeId, completion);
            }
        });
    }

    static /* synthetic */ void b(BluetoothReceiver x0, BluetoothDevice x1, String x2) {
        com.facebook.infer.annotation.a.a(a.a(x0.g));
        if (x0.f.remove(x1)) {
            FLog.i("BluetoothReceiver", "deviceDisconnected %s - %d remaining (causeId %s)", (Object) x1, Integer.valueOf(x0.f.size()), (Object) x2);
            for (BluetoothListener b : x0.e) {
                b.b(x1, x2);
            }
        }
    }

    static /* synthetic */ void a(BluetoothReceiver x0, String x1) {
        com.facebook.infer.annotation.a.a(a.a(x0.g));
        FLog.i("BluetoothReceiver", "userMediaAction (causeId %s)", (Object) x1);
        for (BluetoothListener a : x0.e) {
            a.a(x1);
        }
    }
}
