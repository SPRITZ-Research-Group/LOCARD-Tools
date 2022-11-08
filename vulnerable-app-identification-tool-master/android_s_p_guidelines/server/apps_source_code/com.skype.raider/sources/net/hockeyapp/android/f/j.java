package net.hockeyapp.android.f;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.adjust.sdk.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.hockeyapp.android.h.d;

public final class j {
    private static final Pattern a = Pattern.compile("[0-9a-f]+", 2);
    private static final ThreadLocal<DateFormat> b = new ThreadLocal<DateFormat>() {
        protected final /* synthetic */ Object initialValue() {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    public static String a(String param) {
        try {
            return URLEncoder.encode(param, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.f();
            return "";
        }
    }

    public static boolean b(String value) {
        return !TextUtils.isEmpty(value) && Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    public static Boolean a(Context context) {
        boolean z = false;
        if (context == null) {
            return Boolean.valueOf(false);
        }
        Configuration configuration = context.getResources().getConfiguration();
        if ((configuration.screenLayout & 15) == 3 || (configuration.screenLayout & 15) == 4) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static String c(String appIdentifier) throws IllegalArgumentException {
        if (appIdentifier == null) {
            throw new IllegalArgumentException("App ID must not be null.");
        }
        String sAppIdentifier = appIdentifier.trim();
        Matcher matcher = a.matcher(sAppIdentifier);
        if (sAppIdentifier.length() != 32) {
            throw new IllegalArgumentException("App ID length must be 32 characters.");
        } else if (matcher.matches()) {
            return sAppIdentifier;
        } else {
            throw new IllegalArgumentException("App ID must match regex pattern /[0-9a-f]+/i");
        }
    }

    public static Notification a(Context context, PendingIntent pendingIntent, String title, String text, int iconId, String channelId) {
        Builder builder;
        if (VERSION.SDK_INT >= 26) {
            builder = new Builder(context, channelId);
        } else {
            builder = new Builder(context);
        }
        builder.setContentTitle(title).setContentText(text).setContentIntent(pendingIntent).setSmallIcon(iconId);
        if (VERSION.SDK_INT >= 16) {
            return builder.build();
        }
        return builder.getNotification();
    }

    public static void a(Context context, Notification notification, String channelId, CharSequence channelName) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, 3));
        }
        notificationManager.notify(2, notification);
    }

    public static void b(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(2);
    }

    public static void a(View view, CharSequence text) {
        AccessibilityManager manager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (manager.isEnabled()) {
            int eventType;
            if (VERSION.SDK_INT < 16) {
                eventType = 8;
            } else {
                eventType = 16384;
            }
            AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
            event.getText().add(text);
            event.setSource(view);
            event.setEnabled(view.isEnabled());
            event.setClassName(view.getClass().getName());
            event.setPackageName(view.getContext().getPackageName());
            manager.sendAccessibilityEvent(event);
        }
    }

    public static boolean c(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork == null || !activeNetwork.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.f();
            return false;
        }
    }

    public static String d(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (NameNotFoundException e) {
        }
        if (applicationInfo != null) {
            return (String) packageManager.getApplicationLabel(applicationInfo);
        }
        return context.getString(d.hockeyapp_crash_dialog_app_name_fallback);
    }

    public static String d(String appIdentifier) throws IllegalArgumentException {
        String sanitizedAppIdentifier = c(appIdentifier);
        if (sanitizedAppIdentifier == null) {
            return null;
        }
        StringBuilder idBuf = new StringBuilder(sanitizedAppIdentifier);
        idBuf.insert(20, '-');
        idBuf.insert(16, '-');
        idBuf.insert(12, '-');
        idBuf.insert(8, '-');
        return idBuf.toString();
    }

    public static boolean a() {
        return Build.BRAND.equalsIgnoreCase("generic");
    }

    public static String a(Date date) {
        return ((DateFormat) b.get()).format(date);
    }

    public static boolean b() {
        return Debug.isDebuggerConnected();
    }

    public static String a(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String line = reader.readLine();
                if (line != null) {
                    stringBuilder.append(line).append(10);
                } else {
                    try {
                        inputStream.close();
                        break;
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                e.f();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
                throw th;
            }
        }
        return stringBuilder.toString();
    }

    public static byte[] a(byte[] bytes, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(bytes);
        return digest.digest();
    }

    public static String a(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : bytes) {
            String h = Integer.toHexString(aMessageDigest & 255);
            while (h.length() < 2) {
                h = "0" + h;
            }
            hexString.append(h);
        }
        return hexString.toString();
    }
}
