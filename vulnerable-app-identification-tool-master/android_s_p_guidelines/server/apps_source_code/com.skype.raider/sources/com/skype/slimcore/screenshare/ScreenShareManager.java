package com.skype.slimcore.screenshare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.a;
import com.facebook.react.bridge.ag;
import com.microsoft.media.ScreenCaptureService;
import com.microsoft.media.ScreenCaptureService.ScreenCaptureServiceListener;
import com.microsoft.skype.a.a.b;

public class ScreenShareManager implements a, ScreenCaptureServiceListener {
    private ag a;
    private boolean b;
    @Nullable
    private ScreenCaptureService c;
    private final com.microsoft.skype.a.a d = com.microsoft.skype.a.a.a("ScreenShareManager", b.DEFAULT);
    @Nullable
    private Runnable e;
    @Nullable
    private Runnable f;

    static /* synthetic */ void a(ScreenShareManager x0, Context x1) {
        if (e(x1)) {
            d(x1);
            return;
        }
        final Activity activity = x1 instanceof Activity ? (Activity) x1 : null;
        if (activity != null) {
            Intent intent;
            if (b()) {
                intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + x1.getPackageName()));
            } else {
                intent = null;
            }
            com.microsoft.skype.a.a.a.b(new Runnable(x0) {
                final /* synthetic */ ScreenShareManager c;

                public final void run() {
                    activity.startActivityForResult(intent, 2);
                }
            });
        }
    }

    public ScreenShareManager() {
        System.loadLibrary("RtmMediaManagerDyn");
    }

    public final void a() {
        FLog.i("ScreenShareManager", "Stopping screen share video");
        this.d.b(new Runnable(this) {
            final /* synthetic */ ScreenShareManager a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.a.b(this.a);
                this.a.b = false;
                com.microsoft.skype.a.a.a.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        if (this.a.a.c != null) {
                            this.a.a.c.cleanup();
                            this.a.a.c = null;
                        }
                    }
                });
                this.a.a.stopService(new Intent(this.a.a, ScreenShareNotificationService.class));
                this.a.a = null;
            }
        });
    }

    public final void a(@NonNull final ag context, final Runnable success, final Runnable failure) {
        FLog.i("ScreenShareManager", "Requesting start for screen share video");
        this.d.b(new Runnable(this) {
            final /* synthetic */ ScreenShareManager d;

            public final void run() {
                if (this.d.b) {
                    FLog.w("ScreenShareManager", "Screen sharing session is already started");
                    success.run();
                    return;
                }
                this.d.a = context;
                this.d.e = success;
                this.d.f = failure;
                context.a(this.d);
                this.d.c = new ScreenCaptureService(context.j(), this.d);
                com.microsoft.skype.a.a.a.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        this.a.d.c.screenCapture();
                    }
                });
            }
        });
    }

    private static void d(@NonNull Context context) {
        context.startService(new Intent(context, ScreenShareNotificationService.class));
    }

    @TargetApi(23)
    private static boolean e(@NonNull Context context) {
        if (b()) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    private static boolean b() {
        return VERSION.SDK_INT >= 23;
    }

    public void onScreenCaptureStarted() {
        FLog.i("ScreenShareManager", "Screen capturing started");
        this.d.b(new Runnable(this) {
            final /* synthetic */ ScreenShareManager a;

            {
                this.a = this$0;
            }

            public final void run() {
                ScreenShareManager.e(this.a);
                ScreenShareManager.a(this.a, this.a.a);
            }
        });
    }

    public void onScreenCaptureUnsuccessful(@NonNull String error) {
        FLog.e("ScreenShareManager", "Screen capturing unsuccessful");
        this.d.b(new Runnable(this) {
            final /* synthetic */ ScreenShareManager a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (this.a.f != null) {
                    this.a.f.run();
                }
            }
        });
    }

    public void onActivityResult(Activity activity, final int requestCode, final int resultCode, final Intent data) {
        FLog.i("ScreenShareManager", "AHC onActivityResult");
        this.d.b(new Runnable(this) {
            final /* synthetic */ ScreenShareManager d;

            public final void run() {
                if (requestCode == 2) {
                    boolean isOverlayPermission = ScreenShareManager.e(this.d.a);
                    if (this.d.a == null || !isOverlayPermission) {
                        FLog.w("ScreenShareManager", "User denied overlay display permission for screen share border");
                    } else {
                        ScreenShareManager.d(this.d.a);
                    }
                } else if (requestCode == 1 && this.d.c != null) {
                    this.d.c.onActivityResult(requestCode, resultCode, data);
                }
            }
        });
    }

    public void onNewIntent(Intent intent) {
        FLog.i("ScreenShareManager", "AHC onNewIntent");
    }

    static /* synthetic */ void e(ScreenShareManager x0) {
        com.facebook.infer.annotation.a.a(com.microsoft.skype.a.a.a(x0.d));
        x0.b = true;
        if (x0.e != null) {
            x0.e.run();
        }
    }
}
