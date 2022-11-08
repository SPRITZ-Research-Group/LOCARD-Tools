package com.skype.smsmanager.mms.pdu;

import com.adjust.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class CharacterSets {
    static final /* synthetic */ boolean a;
    private static final int[] b = new int[]{0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 106, 2026, Constants.ONE_SECOND, 1015};
    private static final String[] c = new String[]{"*", "us-ascii", "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4", "iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "iso-8859-9", "shift_JIS", "utf-8", "big5", "iso-10646-ucs-2", "utf-16"};
    private static final HashMap<Integer, String> d = new HashMap();
    private static final HashMap<String, Integer> e = new HashMap();

    static {
        boolean z;
        if (CharacterSets.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
        if (a || b.length == c.length) {
            int count = b.length - 1;
            for (int i = 0; i <= count; i++) {
                d.put(Integer.valueOf(b[i]), c[i]);
                e.put(c[i], Integer.valueOf(b[i]));
            }
            return;
        }
        throw new AssertionError();
    }

    private CharacterSets() {
    }

    public static String a(int mibEnumValue) throws UnsupportedEncodingException {
        String name = (String) d.get(Integer.valueOf(mibEnumValue));
        if (name != null) {
            return name;
        }
        throw new UnsupportedEncodingException();
    }

    public static int a(String mimeName) throws UnsupportedEncodingException {
        Integer mibEnumValue = (Integer) e.get(mimeName);
        if (mibEnumValue != null) {
            return mibEnumValue.intValue();
        }
        throw new UnsupportedEncodingException();
    }
}
