package com.skype.smsmanager.nativesms.models;

import java.util.HashMap;
import java.util.Map;

public enum CellularMessageType {
    SmsMessage(0),
    MmsMessage(1);
    
    private static Map<Integer, CellularMessageType> d;
    private int c;

    static {
        d = new HashMap();
        CellularMessageType[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            CellularMessageType type = values[i];
            d.put(Integer.valueOf(type.c), type);
            i++;
        }
    }

    private CellularMessageType(int type) {
        this.c = type;
    }
}
