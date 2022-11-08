package com.skype;

import android.support.v4.util.m;

public interface DataChannel extends ObjectInterface {

    public interface DataChannelIListener {
    }

    public enum STATUS {
        UNKNOWN(1),
        AVAILABLE(2),
        STARTING(3),
        ACTIVE(4),
        STOPPING(5),
        NOT_STARTED(6),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<STATUS> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            STATUS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                STATUS type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private STATUS(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static STATUS fromInt(int i) {
            STATUS tmpVar = (STATUS) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    void addListener(DataChannelIListener dataChannelIListener);

    void deleteDevice(int i);

    int getStatusProp();

    void removeListener(DataChannelIListener dataChannelIListener);

    void resetDataDevice();

    boolean setDataDevice(int i, int i2);

    void start();

    void start(String str);

    void stop();

    void stop(String str);
}
