package com.microsoft.dl.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.microsoft.dl.Platform;
import com.microsoft.dl.utils.Log;

public class VolumeController {
    public static final String EXTRA_PREV_VOLUME_STREAM_VALUE = "android.media.EXTRA_PREV_VOLUME_STREAM_VALUE";
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private static VolumeController d = null;
    private static long e = 0;
    private final int a = 6;
    private final int b = 0;
    private AudioManager c = ((AudioManager) Platform.getInfo().getAppContext().getSystemService("audio"));
    private int f = 0;
    private final BroadcastReceiver g = new BroadcastReceiver(this) {
        final /* synthetic */ VolumeController a;

        {
            this.a = this$0;
        }

        public void onReceive(Context context, Intent intent) {
            boolean isMuted = false;
            if (VolumeController.VOLUME_CHANGED_ACTION.equals(intent.getAction())) {
                int streamType = intent.getIntExtra(VolumeController.EXTRA_VOLUME_STREAM_TYPE, -1);
                int newVolLevel = intent.getIntExtra(VolumeController.EXTRA_VOLUME_STREAM_VALUE, 0);
                int oldVolLevel = intent.getIntExtra(VolumeController.EXTRA_PREV_VOLUME_STREAM_VALUE, 0);
                if (streamType == -1) {
                    Log.log(6, PackageInfo.TAG, "BroadcastReceiver:onReceive: failed to get stream type.");
                } else if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "BroadcastReceiver:onReceive streamType=" + streamType + ", newVolLevel=" + newVolLevel + ", oldVolLevel=" + oldVolLevel + ", maxVolume=" + this.a.c.getStreamMaxVolume(streamType));
                }
                if (newVolLevel != oldVolLevel) {
                    if (newVolLevel == 0) {
                        isMuted = true;
                    }
                    this.a.a(isMuted);
                }
            }
        }
    };

    private static native void onVolumeChange(long j, boolean z);

    public VolumeController() {
        if (d == null) {
            d = this;
        }
    }

    public synchronized void registerNativeInstance(long nInstance) {
        e = nInstance;
    }

    public synchronized void unregisterNativeInstance() {
        e = 0;
    }

    public void registerVolumeIntentReceiver() {
        IntentFilter filter = new IntentFilter(VOLUME_CHANGED_ACTION);
        Context ctx = Platform.getInfo().getAppContext();
        if (ctx != null) {
            ctx.registerReceiver(this.g, filter);
        } else {
            Log.log(5, PackageInfo.TAG, "volume registerReceiver failed - no context");
        }
    }

    public void unregisterVolumeIntentReceiver() {
        Context ctx = Platform.getInfo().getAppContext();
        if (ctx != null) {
            ctx.unregisterReceiver(this.g);
        } else {
            Log.log(5, PackageInfo.TAG, "volume unregisterReceiver failed - no context");
        }
    }

    private synchronized void a(boolean isMuted) {
        if (e != 0) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "onVolumeChange triggered");
            }
            onVolumeChange(e, isMuted);
        }
    }

    private int a() {
        int i;
        if (this.c.isBluetoothScoOn()) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != 0) {
            return 6;
        }
        return 0;
    }

    public int getStreamVolume() {
        int currentVolume = this.c.getStreamVolume(a());
        int streamMaxVolume = getStreamMaxVolume();
        if (streamMaxVolume != 0) {
            return (int) Math.ceil((65535.0d / ((double) streamMaxVolume)) * ((double) currentVolume));
        }
        return 0;
    }

    public void setStreamVolume(int index) {
        if (index > getStreamMaxVolume()) {
            index = (int) Math.ceil((((double) getStreamMaxVolume()) / 65535.0d) * ((double) index));
        }
        this.c.setStreamVolume(a(), index, 1);
    }

    public int getStreamMaxVolume() {
        return this.c.getStreamMaxVolume(a());
    }

    public boolean isStreamMuted() {
        return getStreamVolume() == 0;
    }

    public void setMute(boolean mute) {
        if (mute && !isStreamMuted()) {
            this.f = getStreamVolume();
            setStreamVolume(0);
        }
        if (!mute && isStreamMuted()) {
            setStreamVolume(this.f);
        }
    }

    public void setVolumeChange() {
        a(isStreamMuted());
    }
}
