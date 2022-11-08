package com.skype.audiomanager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.facebook.common.logging.FLog;
import com.microsoft.skype.a.a;
import com.microsoft.skype.a.a.b;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WiredHeadsetReceiver extends RegisterableBroadcastReceiver {
    private final Random b = new Random();
    private final a c = a.a("WiredHeadsetReceiverQueue", b.DEFAULT);
    private final Set<WiredHeadsetListener> d = new CopyOnWriteArraySet();
    private WiredHeadsetStatus e = WiredHeadsetStatus.UNPLUGGED;
    private final IntentFilter f;

    public interface WiredHeadsetListener {
        void a(WiredHeadsetStatus wiredHeadsetStatus, String str);
    }

    public enum WiredHeadsetStatus {
        UNPLUGGED,
        PLUGGED_WITHOUT_MIC,
        PLUGGED_WITH_MIC
    }

    public WiredHeadsetReceiver(Context c) {
        super(c, "WiredHeadsetReceiver");
        this.e = ((AudioManager) this.a.getSystemService("audio")).isWiredHeadsetOn() ? WiredHeadsetStatus.PLUGGED_WITHOUT_MIC : WiredHeadsetStatus.UNPLUGGED;
        this.f = new IntentFilter("android.intent.action.HEADSET_PLUG");
    }

    public final IntentFilter a() {
        return this.f;
    }

    public void onReceive(Context context, final Intent intent) {
        this.c.b(new Runnable(this) {
            final /* synthetic */ WiredHeadsetReceiver b;

            public final void run() {
                Object action = intent.getAction();
                Object causeId = String.format("%x", new Object[]{Integer.valueOf(this.b.b.nextInt())});
                FLog.i("WiredHeadsetReceiver", "onReceive action %s (causeId: %s)", action, causeId);
                int i = -1;
                switch (action.hashCode()) {
                    case -1676458352:
                        if (action.equals("android.intent.action.HEADSET_PLUG")) {
                            i = 0;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case 0:
                        WiredHeadsetStatus newStatus = intent.getIntExtra("state", 0) == 1 ? intent.getIntExtra("microphone", 1) == 1 ? WiredHeadsetStatus.PLUGGED_WITH_MIC : WiredHeadsetStatus.PLUGGED_WITHOUT_MIC : WiredHeadsetStatus.UNPLUGGED;
                        Object name = intent.getStringExtra("name");
                        String str = "WiredHeadsetReceiver";
                        String str2 = "%s state is %s (causeId: %s)";
                        if (name == null) {
                            name = "UNKNOWN";
                        }
                        FLog.i(str, str2, name, (Object) newStatus, causeId);
                        this.b.e = newStatus;
                        for (WiredHeadsetListener a : this.b.d) {
                            a.a(this.b.e, causeId);
                        }
                        return;
                    default:
                        FLog.w("WiredHeadsetReceiver", "Unexpected action %s (causeId: %s)", action, causeId);
                        return;
                }
            }
        });
    }

    public final WiredHeadsetStatus c() {
        return this.e;
    }

    public final void a(final WiredHeadsetListener listener, final String causeId) {
        this.c.b(new Runnable(this) {
            final /* synthetic */ WiredHeadsetReceiver c;

            public final void run() {
                FLog.i("WiredHeadsetReceiver", "addListener (causeId: %s)", causeId);
                listener.a(this.c.e, causeId);
                this.c.d.add(listener);
            }
        });
    }

    public final void b(final WiredHeadsetListener listener, final String causeId) {
        this.c.b(new Runnable(this) {
            final /* synthetic */ WiredHeadsetReceiver c;

            public final void run() {
                FLog.i("WiredHeadsetReceiver", "removeListener (causeId: %s)", causeId);
                this.c.d.remove(listener);
            }
        });
    }

    public final boolean b() {
        return this.e != WiredHeadsetStatus.UNPLUGGED;
    }
}
