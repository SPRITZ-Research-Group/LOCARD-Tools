package com.facebook.react.modules.network;

import com.facebook.react.common.h;

public final class i {
    private byte[] a = null;

    private static int a(byte firstByte) {
        int code = firstByte & 255;
        if (code >= 240) {
            return 4;
        }
        if (code >= 224) {
            return 3;
        }
        if (code >= 192) {
            return 2;
        }
        return 1;
    }

    public final String a(byte[] data, int length) {
        byte[] result;
        int i = 0;
        int lastSymbolSize = 0;
        if (this.a != null) {
            i = a(this.a[0]) - this.a.length;
        }
        while (i < length) {
            lastSymbolSize = a(data[i]);
            i += lastSymbolSize;
        }
        int symbolsToCopy = length;
        boolean hasNewReminder = false;
        if (i > length) {
            hasNewReminder = true;
            symbolsToCopy = i - lastSymbolSize;
        }
        if (this.a == null) {
            result = data;
        } else {
            result = new byte[(this.a.length + symbolsToCopy)];
            System.arraycopy(this.a, 0, result, 0, this.a.length);
            System.arraycopy(data, 0, result, this.a.length, symbolsToCopy);
            this.a = null;
            symbolsToCopy = result.length;
        }
        if (hasNewReminder) {
            int reminderSize = (lastSymbolSize - i) + length;
            this.a = new byte[reminderSize];
            System.arraycopy(data, length - reminderSize, this.a, 0, reminderSize);
        }
        return new String(result, 0, symbolsToCopy, h.a);
    }
}
