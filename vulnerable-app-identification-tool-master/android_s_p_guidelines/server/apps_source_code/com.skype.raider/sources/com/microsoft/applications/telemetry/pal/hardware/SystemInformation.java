package com.microsoft.applications.telemetry.pal.hardware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.adjust.sdk.Constants;
import com.microsoft.applications.telemetry.core.TraceHelper;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class SystemInformation {
    private static final String LOG_TAG = ("[ACT]:" + SystemInformation.class.getSimpleName().toUpperCase());
    private static final String OS_VERSION = VERSION.RELEASE;
    private static final String PLATFORM = "Android";
    private static String m_app_id = "";
    private static String m_app_language = "";
    private static String m_app_version = "";
    private static String m_os_full_version;
    private static String m_os_major_version;
    private static String m_user_advertising_id = "";
    private static String m_user_language;
    private static String m_user_time_zone = getCurrentTimezoneOffset();

    static {
        m_os_major_version = "";
        m_os_full_version = "";
        m_user_language = "";
        m_os_major_version = getOsVersion();
        m_os_full_version = getOsVersion() + " " + VERSION.INCREMENTAL;
        m_user_language = getLanguageTag(Locale.getDefault());
    }

    public static void initializeAppInfo(Context appContext) {
        try {
            PackageInfo pInfo = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0);
            if (pInfo.versionName != null) {
                m_app_version = pInfo.versionName;
            } else {
                m_app_version = "";
            }
            m_app_language = getLanguageTag(appContext.getResources().getConfiguration().locale);
        } catch (NameNotFoundException e) {
            TraceHelper.TraceWtf(LOG_TAG, "This should never happen " + e.getMessage());
        }
    }

    public static String getOsName() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getOsName|value:%s", new Object[]{PLATFORM}));
        return PLATFORM;
    }

    public static String getOsMajorVersion() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getOsMajorVersion|value:%s", new Object[]{m_os_major_version}));
        return m_os_major_version;
    }

    public static String getOsFullVersion() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getOsFullVersion|value:%s", new Object[]{m_os_full_version}));
        return m_os_full_version;
    }

    private static String getOsVersion() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getOsVersion|value:%s", new Object[]{OS_VERSION}));
        return OS_VERSION;
    }

    public static String getAppId() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getAppId|value:%s", new Object[]{m_app_id}));
        return m_app_id;
    }

    public static String getAppVersion() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getAppVersion|value:%s", new Object[]{m_app_version}));
        return m_app_version;
    }

    public static String getAppLanguage() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getAppLanguage|value:%s", new Object[]{m_app_language}));
        return m_app_language;
    }

    public static String getUserLanguage() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getUserLanguage|value:%s", new Object[]{m_user_language}));
        return m_user_language;
    }

    public static String getUserTimezone() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getUserTimeZone|value:%s", new Object[]{m_user_time_zone}));
        return m_user_time_zone;
    }

    public static String getUserAdvertisingId() {
        TraceHelper.TraceVerbose(LOG_TAG, String.format("getUserAdvertisingId|value:%s", new Object[]{m_user_advertising_id}));
        return m_user_advertising_id;
    }

    private static String getCurrentTimezoneOffset() {
        TimeZone tz = TimeZone.getDefault();
        return (tz.getOffset(GregorianCalendar.getInstance(tz).getTimeInMillis()) >= 0 ? "+" : "-") + String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(Math.abs(tz.getOffset(GregorianCalendar.getInstance(tz).getTimeInMillis()) / Constants.ONE_HOUR)), Integer.valueOf(Math.abs((tz.getOffset(GregorianCalendar.getInstance(tz).getTimeInMillis()) / 60000) % 60))});
    }

    @SuppressLint({"NewApi"})
    private static String getLanguageTag(Locale locale) {
        if (VERSION.SDK_INT >= 21) {
            return locale.toLanguageTag();
        }
        return locale.toString().replace('_', '-');
    }
}
