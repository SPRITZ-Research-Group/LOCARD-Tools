package com.skype4life;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.common.e.c;
import com.facebook.common.e.d;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.l.b;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.p;
import com.facebook.react.q;
import com.microsoft.advertisement.RNAdvertisementUtilitiesPackage;
import com.microsoft.environment.RNEnvironmentInfoPackage;
import com.microsoft.nativecodetelemetry.NativeCodeTelemetryModule;
import com.microsoft.react.push.h;
import com.microsoft.react.videofxp.l;
import com.microsoft.rnreload.RNReloadPackage;
import com.projectseptember.RNGL.m;
import com.skype.assetreader.AssetReaderPackage;
import com.skype.audiomanager.AudioManagerPackage;
import com.skype.badges.AndroidBadgesPackage;
import com.skype.brazemanager.BrazeManagerPackage;
import com.skype.calendar.CalendarPackage;
import com.skype.callintent.CallIntentPackage;
import com.skype.callmonitor.CallMonitorPackage;
import com.skype.camera.imagefilter.ImageFilterViewPackage;
import com.skype.campaignreceiver.CampaignReceiverPackage;
import com.skype.coachmark.CoachMarkPackage;
import com.skype.commandinvoker.RNCommandInvokerPackage;
import com.skype.credentials.SkypeCredentialsFetcherPackage;
import com.skype.curve25519.Curve25519Package;
import com.skype.device.DeviceUtilitiesPackage;
import com.skype.externalbrowser.ExternalBrowserPackage;
import com.skype.facebookaudiencenetwork.FacebookAudienceNetworkPackage;
import com.skype.fileencryption.FileEncryptionPackage;
import com.skype.fingerprintinglib.FingerprintingLibraryPackage;
import com.skype.ink.AdditiveSurfacePackage;
import com.skype.nativeentropy.AndroidNativeEntropyPackage;
import com.skype.permissions.PermissionsPackage;
import com.skype.qrcode.QRCodePackage;
import com.skype.quickactions.QuickActionsPackage;
import com.skype.react.upgrade.AppUpgradePackage;
import com.skype.reactnativesprites.SpritePackage;
import com.skype.recordaudio.SoundRecorderPackage;
import com.skype.rngraphicscontext.RNGraphicsContextPackage;
import com.skype.sharetoapp.ShareToAppPackage;
import com.skype.slimcore.RNSlimcorePackage;
import com.skype.slimcore.calling.RNCallingService;
import com.skype.slimcore.calling.RNCallingService.RNCallingNotificationProvider;
import com.skype.slimcore.logging.MediaLogsProvider;
import com.skype.slimcore.logging.SkyLibLogsProvider;
import com.skype.smsmanager.AndroidSmsManagerPackage;
import com.skype.snapshot.SnapshotPackage;
import com.skype.soundplayer.RNSoundPlayerPackage;
import com.skype.timezone.TimeZonePackage;
import com.skype.tokenshare.TokenSharePackage;
import com.skype.urlutil.UrlUtilPackage;
import com.skype.virtualmessageview.VirtualMessageViewPackage;
import com.skype4life.modules.a;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class e extends p {
    private Application a;
    private h b;
    private AndroidSmsManagerPackage c;
    private List<c> d;
    private volatile ai e;
    private volatile WeakReference<b> f = new WeakReference(null);

    public e(Application application) {
        super(application);
        this.a = application;
        this.d = new ArrayList();
    }

    protected final List<q> e() {
        this.b = new h();
        this.c = new AndroidSmsManagerPackage();
        b appLogs = new a(this.a);
        final SkyLibLogsProvider skyLibLogs = new SkyLibLogsProvider(this.a);
        final MediaLogsProvider mediaLogs = new MediaLogsProvider(this.a);
        b skyLibLogWrapper = new b(this) {
            final /* synthetic */ e b;

            public final File a() {
                return skyLibLogs.a();
            }

            public final List<File> b() {
                return skyLibLogs.b();
            }
        };
        b mediaLogsWrapper = new b(this) {
            final /* synthetic */ e b;

            public final File a() {
                return mediaLogs.a();
            }

            public final List<File> b() {
                return mediaLogs.b();
            }
        };
        RNSlimcorePackage callingPackage = new RNSlimcorePackage(skyLibLogs, mediaLogs);
        RNCallingService.a(new RNCallingNotificationProvider(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final Class a() {
                return MainActivity.class;
            }

            public final int b() {
                return android.support.v4.content.a.c(this.a.a, this.a.a.getResources().getIdentifier("sxBlue", "color", this.a.a.getPackageName()));
            }
        });
        q[] qVarArr = new q[76];
        qVarArr[0] = new AppUpgradePackage();
        qVarArr[1] = new AdditiveSurfacePackage();
        qVarArr[2] = new com.imagepicker.a();
        qVarArr[3] = new fr.bamlab.rnimageresizer.a();
        Context context = this.a;
        boolean z = VERSION.SDK_INT > 19;
        com.facebook.react.c.a.a aVar = new com.facebook.react.c.a.a();
        com.facebook.imagepipeline.core.h.a defaultConfigBuilder = FrescoModule.getDefaultConfigBuilder(new ai(context));
        defaultConfigBuilder.a(z);
        defaultConfigBuilder.a(com.facebook.cache.disk.b.a(context).a().b());
        defaultConfigBuilder.a(new d(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final void a(c trimmable) {
                FLog.w("ReactApp", "registerMemoryTrimmable current size: " + this.a.d.size());
                this.a.d.add(trimmable);
            }
        });
        com.facebook.imagepipeline.core.h.a a = defaultConfigBuilder.a(Config.ARGB_8888);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int max = Math.max(((displayMetrics.widthPixels * displayMetrics.heightPixels) * 4) * 2, 3686400);
        a.a(new com.skype4life.a.a(defaultConfigBuilder, max, max / 4));
        aVar.a(defaultConfigBuilder.a());
        qVarArr[4] = new com.skype4life.modules.c(aVar.a());
        qVarArr[5] = this.b;
        qVarArr[6] = new com.rx.contextmenuandroid.b();
        qVarArr[7] = new PermissionsPackage();
        qVarArr[8] = new com.rt2zz.reactnativecontacts.c();
        qVarArr[9] = new com.brentvatne.react.b();
        qVarArr[10] = new AudioManagerPackage(callingPackage.b());
        qVarArr[11] = new com.slowpath.hockeyapp.e(new d(appLogs, skyLibLogWrapper, mediaLogsWrapper));
        qVarArr[12] = new RNReloadPackage(new Runnable(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final void run() {
                e.b(this.a);
            }
        });
        qVarArr[13] = new com.skpcamera.d();
        qVarArr[14] = new com.customkeyboard.a();
        qVarArr[15] = new com.skype4life.modules.b(appLogs);
        qVarArr[16] = new com.microsoft.react.viewconfig.a();
        qVarArr[17] = callingPackage;
        qVarArr[18] = new com.microsoft.react.sharesheet.a();
        qVarArr[19] = new com.microsoft.react.mediapicker.d();
        qVarArr[20] = new RNAdvertisementUtilitiesPackage();
        qVarArr[21] = new com.microsoft.backgroundexecution.b();
        qVarArr[22] = new RNEnvironmentInfoPackage(SkypeApplication.b());
        qVarArr[23] = new FingerprintingLibraryPackage();
        qVarArr[24] = new ExternalBrowserPackage();
        qVarArr[25] = new CallIntentPackage();
        qVarArr[26] = new FacebookAudienceNetworkPackage();
        qVarArr[27] = new com.microsoft.urlrequest.b();
        qVarArr[28] = new com.microsoft.manualfilecache.a();
        qVarArr[29] = new DeviceUtilitiesPackage();
        qVarArr[30] = new com.microsoft.react.incomingcallinteractionmanager.b();
        qVarArr[31] = new com.microsoft.skypemessagetextinput.module.a();
        qVarArr[32] = new RNSoundPlayerPackage();
        qVarArr[33] = new SoundRecorderPackage();
        qVarArr[34] = new SnapshotPackage();
        qVarArr[35] = new SpritePackage();
        qVarArr[36] = new com.microsoft.react.sqlite.e();
        qVarArr[37] = new com.microsoft.react.timers.a();
        qVarArr[38] = new m();
        qVarArr[39] = new com.github.alinz.reactnativewebviewbridge.d();
        qVarArr[40] = new com.airbnb.android.react.maps.b();
        qVarArr[41] = new com.psykar.cookiemanager.a();
        qVarArr[42] = new l();
        qVarArr[43] = new com.microsoft.react.filetracker.a();
        qVarArr[44] = new com.devfd.RNGeocoder.a();
        qVarArr[45] = new SkypeCredentialsFetcherPackage();
        qVarArr[46] = new VirtualMessageViewPackage();
        qVarArr[47] = new com.microsoft.skype.devicesettings.a();
        qVarArr[48] = new com.BV.LinearGradient.a();
        qVarArr[49] = new com.airbnb.android.react.lottie.b();
        qVarArr[50] = new RNCommandInvokerPackage();
        qVarArr[51] = new UrlUtilPackage();
        qVarArr[52] = new ShareToAppPackage();
        qVarArr[53] = new AndroidBadgesPackage();
        qVarArr[54] = new com.adjust.nativemodule.a();
        qVarArr[55] = new TimeZonePackage();
        qVarArr[56] = this.c;
        qVarArr[57] = new com.oblador.keychain.a();
        qVarArr[58] = new com.microsoft.skype.documentpicker.a();
        qVarArr[59] = new AndroidNativeEntropyPackage();
        qVarArr[60] = new AssetReaderPackage();
        qVarArr[61] = new TokenSharePackage();
        qVarArr[62] = new Curve25519Package();
        qVarArr[63] = new FileEncryptionPackage();
        qVarArr[64] = new CallMonitorPackage(callingPackage.c());
        qVarArr[65] = new CampaignReceiverPackage();
        qVarArr[66] = new com.pusherman.networkinfo.a();
        qVarArr[67] = new QRCodePackage();
        qVarArr[68] = new RNGraphicsContextPackage();
        qVarArr[69] = new ImageFilterViewPackage();
        qVarArr[70] = new CalendarPackage();
        qVarArr[71] = new CoachMarkPackage();
        qVarArr[72] = new BrazeManagerPackage(this.a);
        qVarArr[73] = new QuickActionsPackage();
        qVarArr[74] = new com.microsoft.nativecodetelemetry.a();
        qVarArr[75] = new com.microsoft.react.captiveportal.a();
        return Arrays.asList(qVarArr);
    }

    public final com.facebook.react.l d() {
        com.facebook.react.m builder = com.facebook.react.l.a().a(this.a).b("index.android").a().a(com.facebook.react.common.c.BEFORE_CREATE);
        for (q reactPackage : e()) {
            builder.a(reactPackage);
        }
        builder.a("index.android.bundle");
        com.facebook.react.l instanceManager = builder.c();
        this.b.a(instanceManager);
        this.c.a(instanceManager);
        instanceManager.a(new b(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final void a(ai context) {
                String absolutePath;
                if (context != null) {
                    this.a.e = context;
                    b listener = (b) this.a.f.get();
                    if (listener != null) {
                        listener.a(context);
                    }
                }
                WritableNativeMap reactConfigurationData = new WritableNativeMap();
                reactConfigurationData.putInt("LoggingLevel", 50);
                String str = "JSECachePath";
                File file = new File(context.getDir("jse", 0), "cache");
                if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
                    absolutePath = file.getAbsolutePath();
                } else {
                    absolutePath = "";
                }
                reactConfigurationData.putString(str, absolutePath);
                this.a.e.a().setReactConfigurationData(reactConfigurationData);
                e.e(this.a);
            }
        });
        instanceManager.c();
        return instanceManager;
    }

    public final void a(b listener) {
        this.f = new WeakReference(listener);
        if (this.e != null) {
            listener.a(this.e);
        }
    }

    public final List<c> f() {
        return Collections.unmodifiableList(this.d);
    }

    static /* synthetic */ void b(e x0) {
        FLog.i("ReactApp", "recreateInstanceManager");
        com.microsoft.react.push.e.b();
        x0.c.b();
        x0.e = null;
        SkypeApplication.c();
        x0.c();
        x0.a();
    }

    static /* synthetic */ void e(e x0) {
        try {
            x0.e.getPackageManager().getPackageInfo("com.google.android.webview", 0);
        } catch (NameNotFoundException e) {
            NativeCodeTelemetryModule.sendEvent("webview_not_found", new WritableNativeMap());
        }
    }
}
