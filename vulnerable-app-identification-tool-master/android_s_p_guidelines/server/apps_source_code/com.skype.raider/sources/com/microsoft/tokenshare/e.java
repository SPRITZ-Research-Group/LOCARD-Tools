package com.microsoft.tokenshare;

import android.content.Context;

final class e {
    static void a(Context context, String uniqueID) {
        context.getSharedPreferences("TOKEN_SHARE_PREF_UNIQUE_ID", 0).edit().putString("TOKEN_SHARE_PREF_UNIQUE_ID", uniqueID).apply();
    }

    static String a(Context context) {
        return context.getSharedPreferences("TOKEN_SHARE_PREF_UNIQUE_ID", 0).getString("TOKEN_SHARE_PREF_UNIQUE_ID", null);
    }
}
