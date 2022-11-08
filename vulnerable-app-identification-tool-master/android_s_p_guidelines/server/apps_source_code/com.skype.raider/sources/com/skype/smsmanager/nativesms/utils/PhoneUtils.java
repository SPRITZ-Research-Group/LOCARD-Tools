package com.skype.smsmanager.nativesms.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.skype.smsmanager.nativesms.SmsManagerConstants;
import com.skype.smsmanager.nativesms.SmsMmsLogger;

public final class PhoneUtils implements SmsManagerConstants {
    private PhoneUtils() {
    }

    public static String a(Context context) {
        boolean isVersionSupportSms;
        boolean isReadPhoneStateGranted;
        boolean z = true;
        if (VERSION.SDK_INT >= 19) {
            isVersionSupportSms = true;
        } else {
            isVersionSupportSms = false;
        }
        if (context.checkCallingOrSelfPermission("android.permission.SEND_SMS") == 0) {
            isReadPhoneStateGranted = true;
        } else {
            isReadPhoneStateGranted = false;
        }
        String phone = null;
        TelephonyManager telephonyManager = null;
        if (isVersionSupportSms && isReadPhoneStateGranted) {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                phone = telephonyManager.getLine1Number();
            }
        }
        String str = "PhoneUtils";
        StringBuilder append = new StringBuilder("getSimSelfPhoneNumber: isVersionSupportSms: ").append(isVersionSupportSms).append(" READ_PHONE_STATE granted: ").append(isReadPhoneStateGranted).append(" isTelephonyManagerNull: ");
        if (telephonyManager != null) {
            z = false;
        }
        StringBuilder append2 = append.append(z).append(" phone is: ");
        String str2 = phone == null ? "NULL" : phone.length() == 0 ? "Empty" : "OK";
        SmsMmsLogger.a(str, append2.append(str2).toString());
        return phone;
    }

    public static String a(Context context, String clTag, String funcTag) {
        String selfSimPhone = a(context);
        String selfPrefPhone = context.getSharedPreferences("NATIVE_SMS_PREFS", 0).getString("NATIVE_SMS_SELF_NUMBER", null);
        String rVal = "";
        if (selfPrefPhone != null) {
            SmsMmsLogger.b(clTag, funcTag + ": get SelfPhone from Prefs. phone: " + selfPrefPhone);
            SmsMmsLogger.a(clTag, funcTag + ": get SelfPhone from Prefs. phone length: " + selfPrefPhone.length());
            return selfPrefPhone;
        } else if (selfSimPhone != null) {
            SmsMmsLogger.b(clTag, funcTag + ": get SelfPhone from SIM: " + selfSimPhone);
            SmsMmsLogger.a(clTag, funcTag + ": get SelfPhone from SIM. phone length: " + selfSimPhone.length());
            return selfSimPhone;
        } else {
            SmsMmsLogger.a(clTag, funcTag + ": [WARN] there is no selfPhoneNumber");
            return rVal;
        }
    }
}
