package com.skype.android.jipc.inout;

public class OutStatus extends OutBoolean {
    public static boolean c(int code) {
        return code == 0;
    }

    public String toString() {
        Object obj;
        String str;
        int b = b();
        StringBuilder append = new StringBuilder().append(OutInt32.b(b));
        if (b == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            str = " (success)";
        } else {
            str = " (failure)";
        }
        return append.append(str).toString();
    }
}
