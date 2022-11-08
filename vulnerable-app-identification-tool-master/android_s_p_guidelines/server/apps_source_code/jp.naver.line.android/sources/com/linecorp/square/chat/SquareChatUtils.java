package com.linecorp.square.chat;

import android.text.TextUtils;
import defpackage.vni;
import defpackage.zcd;

public class SquareChatUtils {
    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && vni.a(str) == zcd.SQUARE_CHAT;
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && vni.a(str) == zcd.SQUARE_MEMBER;
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && vni.a(str) == zcd.BOT;
    }
}
