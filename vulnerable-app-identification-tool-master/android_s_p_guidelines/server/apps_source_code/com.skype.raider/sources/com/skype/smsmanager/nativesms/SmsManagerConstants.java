package com.skype.smsmanager.nativesms;

import java.util.HashMap;
import java.util.Map;

public interface SmsManagerConstants {

    public enum IntentType {
        INTENT_SMS(1),
        INTENT_MMS(2),
        INTENT_OUTGOING_SMS_STATUS(3),
        INTENT_OUTGOING_MMS_STATUS(4),
        INTENT_DELETED_CELLULAR_MESSAGE(5),
        UNKNOWN(Integer.MIN_VALUE);
        
        private static Map<Integer, IntentType> h;
        private int g;

        static {
            h = new HashMap();
            IntentType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                IntentType type = values[i];
                h.put(Integer.valueOf(type.g), type);
                i++;
            }
        }

        private IntentType(int type) {
            this.g = type;
        }

        public final int a() {
            return this.g;
        }

        public static IntentType a(int type) {
            return (IntentType) h.get(Integer.valueOf(type));
        }
    }
}
