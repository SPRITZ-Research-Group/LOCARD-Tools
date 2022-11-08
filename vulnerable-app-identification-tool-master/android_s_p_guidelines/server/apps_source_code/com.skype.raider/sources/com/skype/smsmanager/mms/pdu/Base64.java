package com.skype.smsmanager.mms.pdu;

public class Base64 {
    private static byte[] a = new byte[255];

    static {
        int i;
        for (i = 0; i < 255; i++) {
            a[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            a[i] = (byte) (i - 65);
        }
        for (i = 122; i >= 97; i--) {
            a[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            a[i] = (byte) ((i - 48) + 52);
        }
        a[43] = (byte) 62;
        a[47] = (byte) 63;
    }

    public static byte[] a(byte[] base64Data) {
        Object obj = new byte[base64Data.length];
        int i = 0;
        for (int i2 = 0; i2 < base64Data.length; i2++) {
            Object obj2;
            byte b = base64Data[i2];
            if (b == (byte) 61 || a[b] != (byte) -1) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 != null) {
                int i3 = i + 1;
                obj[i] = base64Data[i2];
                i = i3;
            }
        }
        Object base64Data2 = new byte[i];
        System.arraycopy(obj, 0, base64Data2, 0, i);
        if (base64Data2.length == 0) {
            return new byte[0];
        }
        int numberQuadruple = base64Data2.length / 4;
        int encodedIndex = 0;
        int lastData = base64Data2.length;
        while (base64Data2[lastData - 1] == (byte) 61) {
            lastData--;
            if (lastData == 0) {
                return new byte[0];
            }
        }
        byte[] decodedData = new byte[(lastData - numberQuadruple)];
        for (int i4 = 0; i4 < numberQuadruple; i4++) {
            int dataIndex = i4 * 4;
            byte marker0 = base64Data2[dataIndex + 2];
            byte marker1 = base64Data2[dataIndex + 3];
            byte b1 = a[base64Data2[dataIndex]];
            byte b2 = a[base64Data2[dataIndex + 1]];
            byte b3;
            if (marker0 != (byte) 61 && marker1 != (byte) 61) {
                b3 = a[marker0];
                byte b4 = a[marker1];
                decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
                decodedData[encodedIndex + 1] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
                decodedData[encodedIndex + 2] = (byte) ((b3 << 6) | b4);
            } else if (marker0 == (byte) 61) {
                decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
            } else if (marker1 == (byte) 61) {
                b3 = a[marker0];
                decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
                decodedData[encodedIndex + 1] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }
}
