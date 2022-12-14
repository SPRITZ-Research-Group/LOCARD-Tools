package com.skype.smsmanager.mms.pdu;

import java.io.ByteArrayOutputStream;

public class QuotedPrintable {
    private static byte a = (byte) 61;

    public static final byte[] a(byte[] bytes) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int i = 0;
        while (i < bytes.length) {
            byte b = bytes[i];
            if (b == a) {
                try {
                    if (13 == ((char) bytes[i + 1]) && 10 == ((char) bytes[i + 2])) {
                        i += 2;
                    } else {
                        i++;
                        int u = Character.digit((char) bytes[i], 16);
                        i++;
                        int l = Character.digit((char) bytes[i], 16);
                        if (u == -1 || l == -1) {
                            return null;
                        }
                        buffer.write((char) ((u << 4) + l));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
            }
            buffer.write(b);
            i++;
        }
        return buffer.toByteArray();
    }
}
