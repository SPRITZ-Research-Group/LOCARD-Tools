package com.skype;

import com.adjust.sdk.Constants;
import java.nio.charset.Charset;

public class NativeStringConvert {
    private static final String LOG_TAG = "SkypeJNI";
    private static final Charset UTF_8 = Charset.forName(Constants.ENCODING);

    public static byte[] ConvertToNativeBytes(String arg) {
        if (arg == null) {
            throw new NullPointerException("ConvertToNativeBytes received null argument");
        }
        byte[] cString = (arg + 0).getBytes(UTF_8);
        if (cString != null) {
            return cString;
        }
        return new byte[]{(byte) 0};
    }

    public static byte[][] ConvertArrToNativeByteArr(String[] arg) {
        if (arg == null) {
            throw new NullPointerException("ConvertArrToNativeByteArr received null argument");
        }
        byte[][] cStringArr = new byte[arg.length][];
        for (int idx = 0; idx < arg.length; idx++) {
            cStringArr[idx] = ConvertToNativeBytes(arg[idx]);
        }
        return cStringArr;
    }

    public static String ConvertFromNativeBytes(byte[] arg) {
        if (arg[0] != (byte) 0) {
            return new String(arg, UTF_8);
        }
        return "";
    }

    public static String[] ConvertFromNativeStringArray(byte[][] arg) {
        String[] retArr = new String[arg.length];
        for (int idx = 0; idx < arg.length; idx++) {
            retArr[idx] = ConvertFromNativeBytes(arg[idx]);
        }
        return retArr;
    }
}
