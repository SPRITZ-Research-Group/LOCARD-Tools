package com.skype.audiomanager;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import com.facebook.common.logging.FLog;
import com.microsoft.skype.a.a.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ModernAudioDeviceMonitor {
    private final Random a = new Random();
    private final com.microsoft.skype.a.a b = com.microsoft.skype.a.a.a("ModernAudioDeviceMonitorQueue", b.DEFAULT);
    private final Context c;
    private final Set<AudioDeviceListener> d = new CopyOnWriteArraySet();
    private EnumSet<AudioDeviceType> e = EnumSet.of(AudioDeviceType.NONE);
    private AudioDeviceCallback f;

    public interface AudioDeviceListener {
        void a(EnumSet<AudioDeviceType> enumSet, EnumSet<AudioDeviceType> enumSet2, String str);
    }

    public enum AudioDeviceType {
        NONE,
        HEADPHONES,
        USB
    }

    @RequiresApi(api = 23)
    @TargetApi(23)
    private final class a extends AudioDeviceCallback {
        final /* synthetic */ ModernAudioDeviceMonitor a;
        private final AudioManager b;

        public a(ModernAudioDeviceMonitor modernAudioDeviceMonitor, AudioManager androidAudioManager) {
            this.a = modernAudioDeviceMonitor;
            this.b = androidAudioManager;
            a("initialization");
        }

        public final void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
            a("onAudioDevicesAdded");
        }

        public final void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {
            a("onAudioDevicesRemoved");
        }

        private void a(final String action) {
            this.a.b.c(new Runnable(this) {
                final /* synthetic */ a b;

                public final void run() {
                    String causeId = String.format("%x", new Object[]{Integer.valueOf(this.b.a.a.nextInt())});
                    AudioDeviceInfo[] devices = this.b.b.getDevices(3);
                    EnumSet newStatus = EnumSet.of(AudioDeviceType.NONE);
                    List<String> deviceDescriptions = new ArrayList();
                    for (AudioDeviceInfo device : devices) {
                        deviceDescriptions.add(String.format("[%d:%s]", new Object[]{Integer.valueOf(device.getId()), a.a(devices[r5].getType())}));
                        switch (devices[r5].getType()) {
                            case 3:
                            case 4:
                                newStatus.add(AudioDeviceType.HEADPHONES);
                                break;
                            case 11:
                                newStatus.add(AudioDeviceType.USB);
                                break;
                            default:
                                break;
                        }
                    }
                    FLog.i("ModernAudioDeviceMonitor", "%s headphones: %b, usb: %b, devices: %s (causeId: %s)", action, Boolean.valueOf(newStatus.contains(AudioDeviceType.HEADPHONES)), Boolean.valueOf(newStatus.contains(AudioDeviceType.USB)), Arrays.deepToString(deviceDescriptions.toArray()), causeId);
                    if (!this.b.a.e.equals(newStatus)) {
                        this.b.a.e = newStatus;
                        for (AudioDeviceListener a : this.b.a.d) {
                            a.a(newStatus, this.b.a.e, causeId);
                        }
                    }
                }
            });
        }

        static /* synthetic */ String a(int x1) {
            switch (x1) {
                case 0:
                    return "TYPE_UNKNOWN";
                case 1:
                    return "TYPE_BUILTIN_EARPIECE";
                case 2:
                    return "TYPE_BUILTIN_SPEAKER";
                case 3:
                    return "TYPE_WIRED_HEADSET";
                case 4:
                    return "TYPE_WIRED_HEADPHONES";
                case 5:
                    return "TYPE_LINE_ANALOG";
                case 6:
                    return "TYPE_LINE_DIGITAL";
                case 7:
                    return "TYPE_BLUETOOTH_SCO";
                case 8:
                    return "TYPE_BLUETOOTH_A2DP";
                case 9:
                    return "TYPE_HDMI";
                case 10:
                    return "TYPE_HDMI_ARC";
                case 11:
                    return "TYPE_USB_DEVICE";
                case 12:
                    return "TYPE_USB_ACCESSORY";
                case 13:
                    return "TYPE_DOCK";
                case 14:
                    return "TYPE_FM";
                case 15:
                    return "TYPE_BUILTIN_MIC";
                case 16:
                    return "TYPE_FM_TUNER";
                case 17:
                    return "TYPE_TV_TUNER";
                case 18:
                    return "TYPE_TELEPHONY";
                case 19:
                    return "TYPE_AUX_LINE";
                case 20:
                    return "TYPE_IP";
                case 21:
                    return "TYPE_BUS";
                default:
                    return String.format("UNKNOWN - %d", new Object[]{Integer.valueOf(x1)});
            }
        }
    }

    public ModernAudioDeviceMonitor(Context c) {
        this.c = c;
    }

    public final void a() {
        if (VERSION.SDK_INT >= 23) {
            Context context = this.c;
            if (this.f == null) {
                AudioManager audioManager = (AudioManager) context.getSystemService("audio");
                this.f = new a(this, audioManager);
                audioManager.registerAudioDeviceCallback(this.f, null);
                return;
            }
            return;
        }
        FLog.i("ModernAudioDeviceMonitor", "Not registering - OS %d does not support AudioDeviceCallback", Integer.valueOf(VERSION.SDK_INT));
    }

    public final void b() {
        if (VERSION.SDK_INT >= 23) {
            Context context = this.c;
            if (this.f != null) {
                ((AudioManager) context.getSystemService("audio")).unregisterAudioDeviceCallback(this.f);
                this.f = null;
                return;
            }
            return;
        }
        FLog.i("ModernAudioDeviceMonitor", "Not unregistering - OS %d does not support AudioDeviceCallback", Integer.valueOf(VERSION.SDK_INT));
    }

    public final boolean c() {
        return !this.e.equals(EnumSet.of(AudioDeviceType.NONE));
    }

    public final void a(final AudioDeviceListener listener, final String causeId) {
        this.b.b(new Runnable(this) {
            final /* synthetic */ ModernAudioDeviceMonitor c;

            public final void run() {
                FLog.i("ModernAudioDeviceMonitor", "addListener (causeId: %s)", causeId);
                listener.a(this.c.e, EnumSet.of(AudioDeviceType.NONE), causeId);
                this.c.d.add(listener);
            }
        });
    }

    public final void b(final AudioDeviceListener listener, final String causeId) {
        this.b.b(new Runnable(this) {
            final /* synthetic */ ModernAudioDeviceMonitor c;

            public final void run() {
                FLog.i("ModernAudioDeviceMonitor", "removeListener (causeId: %s)", causeId);
                this.c.d.remove(listener);
            }
        });
    }
}
