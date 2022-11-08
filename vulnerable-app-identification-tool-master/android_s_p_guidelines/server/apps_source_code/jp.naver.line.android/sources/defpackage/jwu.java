package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: jwu */
public final class jwu {
    private Context a;
    private Handler b;
    private ComponentName c;
    private jxd d;
    private int e;
    private int f;
    private int g;
    private String h;
    private int i;
    private int j;
    private Object k;
    private String l;
    private String m;
    private final BroadcastReceiver n;

    /* synthetic */ jwu(byte b) {
        this();
    }

    public static jwu a() {
        return jxg.a;
    }

    private jwu() {
        this.h = null;
        this.i = 0;
        this.j = 0;
        this.k = null;
        this.l = "";
        this.n = new BroadcastReceiver(this) {
            final /* synthetic */ jwu a;

            {
                this.a = r1;
            }

            public final void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String action = intent.getAction();
                    String stringExtra = intent.getStringExtra("transactionid");
                    if (TextUtils.isEmpty(this.a.h)) {
                        this.a.h = stringExtra;
                        if (TextUtils.isEmpty(this.a.h)) {
                            this.a.h = "";
                        }
                    } else if (!this.a.h.equals(stringExtra)) {
                        return;
                    }
                    stringExtra = intent.getStringExtra("playbackmusicid");
                    if (stringExtra != null) {
                        this.a.l = stringExtra;
                    }
                    if ("com.linecorp.linemusic.android.intent.action.playback.line.UPDATE".equals(action)) {
                        this.a.i = intent.getIntExtra("playbackupdatetype", 0);
                        this.a.j = intent.getIntExtra("playbackTicketState", 0);
                        if (this.a.d != null) {
                            this.a.d.a(this.a.k, this.a.l, this.a.i, this.a.j);
                        }
                    } else if ("com.linecorp.linemusic.android.intent.action.playback.line.ERROR".equals(action)) {
                        this.a.i = 0;
                        this.a.j = 0;
                        if (this.a.d != null) {
                            this.a.d.a(this.a.k, this.a.l, this.a.i, this.a.j);
                        }
                        int intExtra = intent.getIntExtra("errortype", 0);
                        CharSequence stringExtra2 = intent.getStringExtra("errorcode");
                        String stringExtra3 = intent.getStringExtra("errormessage");
                        TextUtils.isEmpty(stringExtra2);
                        if (TextUtils.isEmpty(stringExtra3)) {
                            stringExtra3 = "";
                        }
                        if (this.a.d != null) {
                            this.a.d.a(this.a.k, this.a.l, intExtra, stringExtra3);
                        }
                    }
                }
            }
        };
    }

    private void b(Context context) {
        ServiceInfo serviceInfo;
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent("com.linecorp.linemusic.android.intent.action.playback.line.PLAY"), 0);
        if (queryIntentServices != null && queryIntentServices.size() > 0) {
            serviceInfo = ((ResolveInfo) queryIntentServices.get(0)).serviceInfo;
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo2 = resolveInfo.serviceInfo;
                if ("com.linecorp.linemusic.android.playback.service.PartnerPlaybackService".equals(serviceInfo2.name) && serviceInfo2.exported) {
                    serviceInfo = serviceInfo2;
                    break;
                }
            }
        } else {
            serviceInfo = null;
        }
        if (serviceInfo == null || !serviceInfo.exported) {
            this.c = new ComponentName("jp.linecorp.linemusic.android", "com.linecorp.linemusic.android.playback.service.PlaybackService");
        } else {
            this.c = new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
    }

    public final void a(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = context.getApplicationContext();
            this.b = new Handler(Looper.getMainLooper());
            b(context);
            this.m = this.a.getPackageName();
        }
    }

    public final void a(jxd jxd) {
        this.d = jxd;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.linecorp.linemusic.android.intent.action.playback.line.UPDATE");
        intentFilter.addAction("com.linecorp.linemusic.android.intent.action.playback.line.ERROR");
        try {
            if (this.a != null) {
                Object obj = (this.c == null || !"com.linecorp.linemusic.android.playback.service.PartnerPlaybackService".equals(this.c.getClassName())) ? null : 1;
                if (obj != null) {
                    this.a.registerReceiver(this.n, intentFilter);
                } else {
                    this.a.registerReceiver(this.n, intentFilter, "com.linecorp.linemusic.android.permission.PLAYBACK", null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent("com.linecorp.linemusic.android.intent.action.playback.line.STATUS");
        intent.setComponent(this.c);
        intent.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
        jwu.a(this.a, intent);
    }

    private void d() {
        this.h = String.valueOf(System.nanoTime());
    }

    public final synchronized void a(Object obj, String str, String str2, jwz jwz) {
        d();
        this.i = 0;
        this.j = 0;
        a(this.k, this.l, this.i, this.j);
        this.k = obj;
        this.l = str2;
        Intent intent = new Intent("com.linecorp.linemusic.android.intent.action.playback.line.PLAY");
        intent.setComponent(this.c);
        intent.putExtra("transactionid", this.h);
        intent.putExtra("playlocation", str);
        intent.putExtra("playmusicid", str2);
        if ("O".equals(str)) {
            intent.putExtra("playpackagename", this.m);
        }
        if (jwz != null) {
            intent.putExtra("metatitle", jwz.b);
            intent.putExtra("metaartist", jwz.a());
            intent.putExtra("metathumbnail", jwz.d.c);
        }
        intent.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
        jwu.a(this.a, intent);
    }

    public final synchronized void b() {
        a(false);
    }

    private void a(boolean z) {
        this.i = 0;
        this.j = 0;
        a(this.k, this.l, this.i, this.j);
        this.k = null;
        this.l = "";
        Intent intent = new Intent("com.linecorp.linemusic.android.intent.action.playback.line.CLOSE");
        intent.setComponent(this.c);
        intent.putExtra("forceExecute", z);
        if (this.h == null) {
            d();
        }
        intent.putExtra("transactionid", this.h);
        intent.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
        jwu.a(this.a, intent);
    }

    public final synchronized void c() {
        e();
    }

    private synchronized void e() {
        a(true);
        this.d = null;
        this.h = null;
        if (this.a != null) {
            try {
                this.a.unregisterReceiver(this.n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final synchronized int a(Object obj) {
        if (obj != null) {
            if (obj.equals(this.k)) {
                return this.i;
            }
        }
        return 0;
    }

    public static void a(Activity activity, String str) {
        if (activity != null && !TextUtils.isEmpty(str)) {
            Intent a = jwu.a(str);
            a.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
            try {
                activity.startActivity(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            Intent a = jwu.a(str);
            a.addFlags(268435456);
            a.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
            try {
                context.startActivity(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void a(Activity activity, int i, String str, String str2) {
        Intent a = jwu.a(str2);
        a.putExtra("callermid", jwu.a(str, str));
        a.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
        this.g = i;
        try {
            activity.startActivityForResult(a, this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static jxe a(int i, Intent intent) {
        if (i == -1 && intent != null) {
            return new jxf().a(intent.getStringExtra("ringtonetrackid")).b(intent.getStringExtra("ringtonetracktitle")).c(intent.getStringExtra("ringtonetrackartist")).d(intent.getStringExtra("ringtonetrackpath")).e(intent.getStringExtra("ringtonetrackkey")).f(intent.getStringExtra("ringtonetracksalt")).a(intent.getIntExtra("ringtonetracksaltiteration", 0)).b(intent.getIntExtra("ringtonetracksaltlength", 0)).a();
        }
        return null;
    }

    public final void a(Activity activity, String str, String str2, String str3) {
        Intent a = jwu.a(str3);
        a.putExtra("callermid", jwu.a(str, str));
        if (str2 != null) {
            a.putExtra("callerchatid", jwu.a(str2, str));
        }
        a.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
        this.e = 19;
        try {
            activity.startActivityForResult(a, this.e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final jxb a(int i, int i2, Intent intent) {
        if (i == this.e && i2 == -1 && intent != null) {
            return new jxc().a(intent.getBooleanExtra(rvj.key, false)).a();
        }
        return null;
    }

    public final void b(Activity activity, int i, String str, String str2) {
        Intent a = jwu.a(str2);
        a.putExtra("callermid", jwu.a(str, str));
        a.putExtra("libraryversion", "2.3.0.0-SNAPSHOT");
        this.f = i;
        try {
            activity.startActivityForResult(a, this.f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final jwx a(int i, int i2, String str, Intent intent) {
        Intent intent2 = intent;
        if (i != this.f || i2 != -1 || intent2 == null) {
            return null;
        }
        String stringExtra = intent2.getStringExtra("linkkey");
        String stringExtra2 = intent2.getStringExtra("linksalt");
        int intExtra = intent2.getIntExtra("linksaltiteration", 0);
        int intExtra2 = intent2.getIntExtra("linksaltlength", 0);
        String str2 = str;
        String str3 = stringExtra;
        String str4 = stringExtra2;
        int i3 = intExtra;
        int i4 = intExtra2;
        String a = jwu.a(intent2.getStringExtra("linktype"), str2, str3, str4, i3, i4);
        String a2 = jwu.a(intent2.getStringExtra("linkid"), str2, str3, str4, i3, i4);
        String a3 = jwu.a(intent2.getStringExtra("linkname"), str2, str3, str4, i3, i4);
        String a4 = jwu.a(intent2.getStringExtra("linkartistname"), str2, str3, str4, i3, i4);
        String a5 = jwu.a(intent2.getStringExtra("linkimageurl"), str2, str3, str4, i3, i4);
        String a6 = jwu.a(intent2.getStringExtra("linkurl"), str2, str3, str4, i3, intExtra2);
        String stringExtra3 = intent2.getStringExtra("channelid");
        String str5 = a6;
        return new jwy().a(a).b(a2).c(a3).d(a4).e(a5).f(str5).g(jwu.a(stringExtra3, str2, str3, str4, i3, intExtra2)).a();
    }

    private void a(Object obj, String str, int i, int i2) {
        if (this.b != null) {
            final Object obj2 = obj;
            final String str2 = str;
            final int i3 = i;
            final int i4 = i2;
            this.b.post(new Runnable(this) {
                final /* synthetic */ jwu e;

                public final void run() {
                    if (this.e.d != null) {
                        this.e.d.a(obj2, str2, i3, i4);
                    }
                }
            });
        }
    }

    private static Intent a(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(603979776);
        return intent;
    }

    private static void a(Context context, Intent intent) {
        if (context != null) {
            try {
                intent.putExtra("startedForegroundService", false);
                context.startService(intent);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    if (VERSION.SDK_INT >= 26) {
                        intent.putExtra("startedForegroundService", true);
                        context.startForegroundService(intent);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static String a(String str, String str2, String str3, String str4, int i, int i2) {
        byte[] bytes = str2.getBytes();
        if (bytes.length > 32) {
            bytes = Arrays.copyOf(bytes, 32);
        }
        try {
            byte[] decode = Base64.decode(str3, 2);
            Key secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(new String(bytes).toCharArray(), str4.getBytes(), i, i2)).getEncoded(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(2, secretKeySpec);
            Key secretKeySpec2 = new SecretKeySpec(instance.doFinal(decode), "AES");
            instance = Cipher.getInstance("AES");
            instance.init(2, secretKeySpec2);
            return new String(instance.doFinal(Base64.decode(str, 2)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(String str, String str2) {
        byte[] bArr;
        if (str2 == null) {
            bArr = null;
        } else {
            bArr = str2.getBytes();
            if (bArr.length > 32) {
                bArr = Arrays.copyOf(bArr, 32);
            }
        }
        if (bArr == null) {
            return null;
        }
        try {
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES");
            instance.init(1, secretKeySpec);
            str = Base64.encodeToString(instance.doFinal(str.getBytes()), 2);
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        return str;
    }
}
