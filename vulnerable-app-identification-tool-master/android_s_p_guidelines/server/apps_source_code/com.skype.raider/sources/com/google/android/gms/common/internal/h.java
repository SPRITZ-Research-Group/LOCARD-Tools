package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.l;
import android.text.TextUtils;
import com.google.android.gms.a.a.c;
import com.google.android.gms.common.j;
import com.google.android.gms.common.m.a;
import com.google.android.gms.common.util.g;
import javax.annotation.concurrent.GuardedBy;

public final class h {
    @GuardedBy("sCache")
    private static final l<String, String> a = new l();

    public static String a(Context context) {
        return context.getResources().getString(c.common_google_play_services_notification_channel_name);
    }

    @Nullable
    public static String a(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(c.common_google_play_services_install_title);
            case 2:
                return resources.getString(c.common_google_play_services_update_title);
            case 3:
                return resources.getString(c.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 18:
                return null;
            case 5:
                return a(context, "common_google_play_services_invalid_account_title");
            case 7:
                return a(context, "common_google_play_services_network_error_title");
            case 17:
                return a(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                return a(context, "common_google_play_services_restricted_profile_title");
            default:
                new StringBuilder(33).append("Unexpected error code ").append(i);
                return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Nullable
    private static String a(Context context, String str) {
        synchronized (a) {
            String str2 = (String) a.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources a = j.a(context);
            if (a == null) {
                return null;
            }
            int identifier = a.getIdentifier(str, "string", "com.google.android.gms");
            String valueOf;
            if (identifier == 0) {
                str2 = "Missing resource: ";
                valueOf = String.valueOf(str);
                if (valueOf.length() != 0) {
                    str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
            } else {
                Object string = a.getString(identifier);
                if (TextUtils.isEmpty(string)) {
                    str2 = "Got empty resource: ";
                    valueOf = String.valueOf(str);
                    if (valueOf.length() != 0) {
                        str2.concat(valueOf);
                    } else {
                        valueOf = new String(str2);
                    }
                } else {
                    a.put(str, string);
                    return string;
                }
            }
        }
    }

    private static String a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String a = a(context, str);
        if (a == null) {
            a = resources.getString(a.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, a, new Object[]{str2});
    }

    private static String b(Context context) {
        String packageName = context.getPackageName();
        try {
            return com.google.android.gms.common.a.c.a(context).b(packageName).toString();
        } catch (NameNotFoundException e) {
        } catch (NullPointerException e2) {
        }
        CharSequence charSequence = context.getApplicationInfo().name;
        return !TextUtils.isEmpty(charSequence) ? charSequence : packageName;
    }

    @NonNull
    public static String b(Context context, int i) {
        String a = i == 6 ? a(context, "common_google_play_services_resolution_required_title") : a(context, i);
        return a == null ? context.getResources().getString(c.common_google_play_services_notification_ticker) : a;
    }

    @NonNull
    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        String b = b(context);
        switch (i) {
            case 1:
                return resources.getString(c.common_google_play_services_install_text, new Object[]{b});
            case 2:
                if (g.b(context)) {
                    return resources.getString(c.common_google_play_services_wear_update_text);
                }
                return resources.getString(c.common_google_play_services_update_text, new Object[]{b});
            case 3:
                return resources.getString(c.common_google_play_services_enable_text, new Object[]{b});
            case 5:
                return a(context, "common_google_play_services_invalid_account_text", b);
            case 7:
                return a(context, "common_google_play_services_network_error_text", b);
            case 9:
                return resources.getString(c.common_google_play_services_unsupported_text, new Object[]{b});
            case 16:
                return a(context, "common_google_play_services_api_unavailable_text", b);
            case 17:
                return a(context, "common_google_play_services_sign_in_failed_text", b);
            case 18:
                return resources.getString(c.common_google_play_services_updating_text, new Object[]{b});
            case 20:
                return a(context, "common_google_play_services_restricted_profile_text", b);
            default:
                return resources.getString(a.common_google_play_services_unknown_issue, new Object[]{b});
        }
    }

    @NonNull
    public static String d(Context context, int i) {
        return i == 6 ? a(context, "common_google_play_services_resolution_required_text", b(context)) : c(context, i);
    }

    @NonNull
    public static String e(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(c.common_google_play_services_install_button);
            case 2:
                return resources.getString(c.common_google_play_services_update_button);
            case 3:
                return resources.getString(c.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }
}
