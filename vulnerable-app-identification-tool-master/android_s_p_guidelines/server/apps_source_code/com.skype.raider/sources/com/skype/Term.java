package com.skype;

import android.support.v4.util.m;

public interface Term {

    public enum CONDITION {
        EQ(0),
        GT(1),
        GE(2),
        LT(3),
        LE(4),
        PREFIX_EQ(5),
        PREFIX_GE(6),
        PREFIX_LE(7),
        CONTAINS_WORDS(8),
        CONTAINS_WORD_PREFIXES(9),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<CONDITION> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            CONDITION[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CONDITION type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private CONDITION(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static CONDITION fromInt(int i) {
            CONDITION tmpVar = (CONDITION) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }
}
