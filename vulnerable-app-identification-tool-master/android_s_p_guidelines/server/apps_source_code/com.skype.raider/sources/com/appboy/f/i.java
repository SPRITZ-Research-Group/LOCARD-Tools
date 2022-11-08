package com.appboy.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import com.adjust.sdk.Constants;
import com.skype.Defines;
import java.security.MessageDigest;

public final class i {
    private static final String a = c.a(i.class);

    public static String a(String reference) {
        if (reference == null) {
            throw new NullPointerException("Provided String must be non-null.");
        } else if (reference.length() != 0) {
            return reference;
        } else {
            throw new IllegalArgumentException("Provided String must be non-empty.");
        }
    }

    public static String a(String[] strings, String sep) {
        if (strings == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                stringBuilder.append(str).append(sep);
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(sep)) {
            return stringBuilder2.substring(0, stringBuilder2.length() - sep.length());
        }
        return stringBuilder2;
    }

    public static boolean b(String reference) {
        return reference == null || reference.length() == 0;
    }

    public static boolean c(String reference) {
        return reference == null || reference.trim().length() == 0;
    }

    public static String d(String input) {
        return input.trim().equals("") ? null : input;
    }

    public static int a(String reference, String subString) {
        return reference.split(subString, -1).length - 1;
    }

    public static String a(Context context, String userId, String apiKey) {
        if (userId == null) {
            userId = "null";
        }
        if (userId.equals("null")) {
            return b("37a6259cc0c1dae299a7866489dff0bd", apiKey);
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.appboy.support.stringutils.cachefilesuffix", 0);
        String string = sharedPreferences.getString("user_id_key", null);
        if (string != null && string.equals(userId)) {
            string = sharedPreferences.getString("user_id_hash_value", null);
            if (!b(string)) {
                return b(string, apiKey);
            }
            c.b(a, "The saved user id hash was null or empty.");
        }
        c.b(a, "Generating MD5 for user id: " + userId + " apiKey: " + apiKey);
        string = e(userId);
        if (string == null) {
            string = Integer.toString(userId.hashCode());
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("user_id_key", userId);
        edit.putString("user_id_hash_value", string);
        edit.apply();
        return b(string, apiKey);
    }

    public static String a(Context context, String userId) {
        return a(context, userId, null);
    }

    private static String b(String str, String str2) {
        if (c(str2)) {
            return "." + str;
        }
        return "." + str + "." + str2;
    }

    private static String e(@NonNull String text) {
        try {
            byte[] digest = MessageDigest.getInstance(Constants.MD5).digest(text.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toHexString((b & 255) | Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE).substring(1, 3));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            c.d(a, "Failed to calculate MD5 hash", e);
            return null;
        }
    }
}
