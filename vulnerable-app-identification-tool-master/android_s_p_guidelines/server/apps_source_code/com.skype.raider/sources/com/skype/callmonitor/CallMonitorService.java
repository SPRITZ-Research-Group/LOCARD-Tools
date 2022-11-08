package com.skype.callmonitor;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.v4.content.c;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import com.microsoft.skype.a.a;
import com.skype.callmonitor.callmonitorview.CallMonitorEventHandler;
import com.skype.callmonitor.callmonitorview.CallMonitorView;
import com.skype.callmonitor.util.AvatarHelper;
import com.skype.callmonitor.util.AvatarHelper.ImageDownloadCallback;
import java.util.HashMap;

public class CallMonitorService extends Service implements CallMonitorEventHandler {
    private WindowManager a;
    private CallMonitorView b;
    private int c;
    private int d;
    private Boolean e = Boolean.valueOf(true);
    private Boolean f = Boolean.valueOf(false);
    private Boolean g = Boolean.valueOf(false);
    private int h;
    private BroadcastReceiver i = new BroadcastReceiver(this) {
        final /* synthetic */ CallMonitorService a;

        {
            this.a = this$0;
        }

        public final void onReceive(Context context, final Intent intent) {
            String stringExtra = intent.getStringExtra("actiontype");
            boolean z = true;
            switch (stringExtra.hashCode()) {
                case -760129226:
                    if (stringExtra.equals("attachVideo")) {
                        z = true;
                        break;
                    }
                    break;
                case -126995664:
                    if (stringExtra.equals("isVideoOn")) {
                        z = true;
                        break;
                    }
                    break;
                case -122704456:
                    if (stringExtra.equals("showPortrait")) {
                        z = true;
                        break;
                    }
                    break;
                case 3202370:
                    if (stringExtra.equals("hide")) {
                        z = true;
                        break;
                    }
                    break;
                case 3529469:
                    if (stringExtra.equals("show")) {
                        z = false;
                        break;
                    }
                    break;
                case 917257593:
                    if (stringExtra.equals("updateStrings")) {
                        z = true;
                        break;
                    }
                    break;
                case 1091686568:
                    if (stringExtra.equals("detachVideo")) {
                        z = true;
                        break;
                    }
                    break;
                case 1766593208:
                    if (stringExtra.equals("conversationUpdated")) {
                        z = true;
                        break;
                    }
                    break;
                case 2065669729:
                    if (stringExtra.equals("isMuted")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    CallMonitorService.a(this.a);
                    return;
                case true:
                    CallMonitorService.b(this.a);
                    return;
                case true:
                    a.a.c(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            boolean z = false;
                            CallMonitorView c = this.b.a.b;
                            if (!intent.getBooleanExtra("isMutedValue", false)) {
                                z = true;
                            }
                            c.setMicrophoneState(z);
                        }
                    });
                    return;
                case true:
                    a.a.c(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            this.b.a.b.setVideoState(intent.getBooleanExtra("isVideoOnValue", false));
                        }
                    });
                    return;
                case true:
                    CallMonitorService.a(this.a, intent.getIntExtra("id", 0));
                    return;
                case true:
                    this.a.c();
                    return;
                case true:
                    AvatarHelper.a(intent.getStringExtra("avatarUrl"), this.a.getApplicationContext(), new ImageDownloadCallback(this.a) {
                        final /* synthetic */ CallMonitorService a;

                        {
                            this.a = this$0;
                        }

                        public final void a(Bitmap bitmap) {
                            this.a.b.setUserAvatarAndShowImage(bitmap);
                        }
                    });
                    return;
                case true:
                    final HashMap<String, String> map = (HashMap) intent.getSerializableExtra("map");
                    a.a.c(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            this.b.a.b.a(map);
                        }
                    });
                    return;
                case true:
                    CallMonitorService.a(this.a, intent.getBooleanExtra("portrait", false));
                    return;
                default:
                    return;
            }
        }
    };

    public IBinder onBind(Intent intent) {
        this.e = Boolean.valueOf(false);
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            FLog.i("CallMonitorService", "CallMonitorService:onStartCommand startId: %d intent: %s", Integer.valueOf(startId), intent.toString());
        } else {
            FLog.w("CallMonitorService", "CallMonitorService:onStartCommand called with null intent.");
        }
        return 2;
    }

    public void onCreate() {
        int i;
        FLog.i("CallMonitorService", "CallMonitorService:onCreate");
        super.onCreate();
        c.a((Context) this).a(this.i, new IntentFilter("callmonitorservice"));
        Context applicationContext = getApplicationContext();
        this.a = (WindowManager) getSystemService("window");
        this.c = getApplicationContext().getResources().getDisplayMetrics().widthPixels / 2;
        this.d = getApplicationContext().getResources().getDisplayMetrics().heightPixels / 4;
        if (VERSION.SDK_INT >= 26) {
            i = 2038;
        } else {
            i = 2002;
        }
        LayoutParams layoutParams = new WindowManager.LayoutParams(this.c, this.d, i, 8, -3);
        this.b = new CallMonitorView(this, getApplicationContext(), this.f.booleanValue(), this.g.booleanValue(), layoutParams);
        this.a.addView(this.b, layoutParams);
        LocalBroadcastHelper.a(applicationContext, LocalBroadcastHelper.b("monitorReady"));
    }

    public void onDestroy() {
        FLog.i("CallMonitorService", "CallMonitorService:onDestroy");
        super.onDestroy();
        if (this.b != null) {
            this.a.removeView(this.b);
        }
        c.a((Context) this).a(this.i);
    }

    private void c() {
        if (this.h == 0) {
            FLog.i("CallMonitorService", "CallMonitorService:detachVideo ignoring - no videoObjectId");
            return;
        }
        FLog.i("CallMonitorService", "CallMonitorService:detachVideo videoObjectId: %d", Integer.valueOf(this.h));
        a.a.c(new Runnable(this) {
            final /* synthetic */ CallMonitorService a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b.a(this.a.h);
                this.a.h = 0;
            }
        });
    }

    public final void a(boolean enabled) {
        LocalBroadcastHelper.a(getApplicationContext(), LocalBroadcastHelper.b(enabled ? "mute" : "unmute"));
    }

    public final void a() {
        LocalBroadcastHelper.a(getApplicationContext(), LocalBroadcastHelper.b("endCall"));
    }

    public final void b() {
        LocalBroadcastHelper.a(getApplicationContext(), LocalBroadcastHelper.b("monitorPressed"));
    }

    public final void b(boolean enabled) {
        LocalBroadcastHelper.a(getApplicationContext(), LocalBroadcastHelper.b(enabled ? "localVideoStop" : "localVideoStart"));
    }

    static /* synthetic */ void a(CallMonitorService x0) {
        FLog.i("CallMonitorService", "CallMonitorService:showView");
        a.a.c(new Runnable(x0) {
            final /* synthetic */ CallMonitorService a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b.setVisibility(0);
            }
        });
    }

    static /* synthetic */ void b(CallMonitorService x0) {
        FLog.i("CallMonitorService", "CallMonitorService:hideView");
        a.a.c(new Runnable(x0) {
            final /* synthetic */ CallMonitorService a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b.setVisibility(4);
            }
        });
        x0.c();
    }

    static /* synthetic */ boolean a(CallMonitorService x0, final int x1) {
        FLog.i("CallMonitorService", "CallMonitorService:attachVideo videoObjectId: %d", Integer.valueOf(x1));
        a.a.c(new Runnable(x0) {
            final /* synthetic */ boolean b = false;
            final /* synthetic */ CallMonitorService c;

            public final void run() {
                this.c.b.a(x1, this.b);
                this.c.h = x1;
            }
        });
        return true;
    }

    static /* synthetic */ void a(CallMonitorService x0, final boolean x1) {
        if (x0.b != null) {
            FLog.i("CallMonitorService", "CallMonitorService:showPortrait: %b", Boolean.valueOf(x1));
            a.a.c(new Runnable(x0) {
                final /* synthetic */ CallMonitorService b;

                public final void run() {
                    if (x1) {
                        this.b.b.a(this.b.d, this.b.c);
                    } else {
                        this.b.b.a(this.b.c, this.b.d);
                    }
                }
            });
        }
    }
}
