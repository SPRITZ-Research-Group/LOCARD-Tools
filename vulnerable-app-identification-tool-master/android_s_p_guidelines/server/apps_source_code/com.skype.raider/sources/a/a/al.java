package a.a;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.appboy.a.a;
import com.appboy.f.c;
import com.appboy.f.h;
import com.appboy.f.i;

public class al {
    private static final String a = c.a(al.class);
    private final Context b;
    private final aq c;

    public al(Context context, aq aqVar) {
        this.b = context;
        this.c = aqVar;
    }

    public final void a(String... strArr) {
        if (this.c.a() != null) {
            c.f(a, "The device is already registered with the GCM server and is eligible to receive GCM messages.");
            return;
        }
        c.b(a, "Registering the application with the GCM server.");
        String a = i.a(strArr, ",");
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(this.b, 0, new Intent(), 0));
        intent.putExtra("sender", a);
        this.b.startService(intent);
    }

    public static boolean a(Context context, a aVar) {
        return cs.b(context) && b(context, aVar);
    }

    @SuppressLint({"WrongConstant"})
    private static boolean b(Context context, a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        PackageManager packageManager = context.getPackageManager();
        String str = context.getPackageName() + ".permission.C2D_MESSAGE";
        try {
            packageManager.getPermissionInfo(str, 4096);
        } catch (NameNotFoundException e) {
            stringBuilder.append("The manifest does not define the " + str + " permission.");
        }
        if (!h.a(context, "android.permission.INTERNET")) {
            stringBuilder.append("Missing permission. The android.permission.INTERNET permission must be set so that the Android application can send the registration ID to the 3rd party server.");
        } else if (!h.a(context, "com.google.android.c2dm.permission.RECEIVE")) {
            stringBuilder.append("Missing permission. The com.google.android.c2dm.permission.RECEIVE permission must be set so that the Android application can register and receive messages.");
        } else if (!h.a(context, str)) {
            stringBuilder.append("Missing permission. The " + str + " permission must be set so that ONLY this Android application can register and receive GCM messages.");
        }
        if (!h.a(context, "android.permission.GET_ACCOUNTS") && VERSION.SDK_INT < 16) {
            stringBuilder.append("Missing permission. The android.permission.GET_ACCOUNTS permission must be set so that this pre-Jelly Bean Android device can register with the GCM server.");
        }
        if (!h.a(context, "android.permission.WAKE_LOCK")) {
            c.d(a, "Missing permission. The android.permission.WAKE_LOCK permission is recommended be set so that the GCM receiver can notify users by waking the phone when a message is received.");
        }
        ComponentName componentName = new ComponentName(context, "com.appboy.AppboyGcmReceiver");
        try {
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(componentName, 2);
            if (receiverInfo == null || !receiverInfo.enabled) {
                stringBuilder.append("The " + componentName.getClassName() + " broadcast receiver is either not found or is disabled");
            }
        } catch (NameNotFoundException e2) {
            stringBuilder.append("No " + componentName.getClassName() + " broadcast receiver is registered in the manifest.");
        }
        if (aVar.n() == null) {
            stringBuilder.append("Cannot find the Google Cloud Messaging sender ID value in configuration");
        }
        if (stringBuilder.length() == 0) {
            return true;
        }
        c.g(a, stringBuilder.toString());
        return false;
    }
}
