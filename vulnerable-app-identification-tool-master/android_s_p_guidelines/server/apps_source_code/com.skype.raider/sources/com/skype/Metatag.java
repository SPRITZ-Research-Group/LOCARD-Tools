package com.skype;

import android.support.v4.util.m;

public interface Metatag {

    public enum TYPE {
        INTEGER(0),
        STRING(1),
        BINARY(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<TYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                TYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private TYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static TYPE fromInt(int i) {
            TYPE tmpVar = (TYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    int getAllocedSize();

    byte[] getBinValue();

    long getInt64Value();

    int getIntValue();

    int getKey();

    String getStrValue();

    TYPE getType();

    boolean isFullEqual(Metatag metatag);

    boolean isKeyEqual(Metatag metatag);

    boolean isNull();

    boolean isValueEqual(Metatag metatag);
}
