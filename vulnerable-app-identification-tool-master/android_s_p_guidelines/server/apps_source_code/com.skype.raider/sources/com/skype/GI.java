package com.skype;

import android.support.v4.util.m;

public interface GI {

    public enum CONNSTATUS {
        OFFLINE(0),
        CONNECTING(1),
        PAUSING(2),
        ONLINE(3),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<CONNSTATUS> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            CONNSTATUS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CONNSTATUS type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private CONNSTATUS(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static CONNSTATUS fromInt(int i) {
            CONNSTATUS tmpVar = (CONNSTATUS) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum FILEERROR {
        NO_FILEERROR(0),
        DISK_FULL(1),
        CREATE_ERROR(2),
        OPEN_ERROR(3),
        READ_ERROR(4),
        WRITE_ERROR(5),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<FILEERROR> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            FILEERROR[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                FILEERROR type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private FILEERROR(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static FILEERROR fromInt(int i) {
            FILEERROR tmpVar = (FILEERROR) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public interface GIIListener {
        void onConnStatusChange(GI gi, CONNSTATUS connstatus);

        void onFileError(GI gi, FILEERROR fileerror);

        void onLibStatusChange(GI gi, LIBSTATUS libstatus);

        void onNodeinfoChange(GI gi, byte[] bArr);

        void onProxyAuthenticationFailure(GI gi, PROXYTYPE proxytype);
    }

    public static class GetLastFileError_Result {
        public String m_filename;
        public int m_lowlevelCode;
        public FILEERROR m_return;

        public void init(int lowlevelCode, byte[] filename, FILEERROR funcRet) {
            this.m_lowlevelCode = lowlevelCode;
            this.m_filename = NativeStringConvert.ConvertFromNativeBytes(filename);
            this.m_return = funcRet;
        }
    }

    public enum LIBSTATUS {
        CONSTRUCTED(0),
        STARTING(1),
        RUNNING(2),
        STOPPING(3),
        STOPPED(4),
        FATAL_ERROR(5),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<LIBSTATUS> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            LIBSTATUS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                LIBSTATUS type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private LIBSTATUS(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static LIBSTATUS fromInt(int i) {
            LIBSTATUS tmpVar = (LIBSTATUS) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum NETWORKACTIVITYLEVEL {
        NAL_NORMAL_LEVEL(3),
        NAL_FIRST_QUIET_LEVEL(7),
        NAL_QUIET_WITH_NETWORK_LEVEL(7),
        NAL_QUIET_SUSPENDED_LEVEL(8),
        NAL_QUIET_SUSPENDED_OFFLINE_LEVEL(9),
        NAL_LAST_LEVEL_DONT_USE(10),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<NETWORKACTIVITYLEVEL> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            NETWORKACTIVITYLEVEL[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                NETWORKACTIVITYLEVEL type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private NETWORKACTIVITYLEVEL(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static NETWORKACTIVITYLEVEL fromInt(int i) {
            NETWORKACTIVITYLEVEL tmpVar = (NETWORKACTIVITYLEVEL) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum PROXYTYPE {
        HTTPS_PROXY(0),
        SOCKS_PROXY(1),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<PROXYTYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            PROXYTYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                PROXYTYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private PROXYTYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static PROXYTYPE fromInt(int i) {
            PROXYTYPE tmpVar = (PROXYTYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    void addListener(GIIListener gIIListener);

    String getActiveLogFileName();

    String getDebugInfo();

    String getFatalErrorMessage();

    GetLastFileError_Result getLastFileError();

    LIBSTATUS getLibStatus();

    Setup getSetup();

    Setup getSetup(String str);

    void log(String str, String str2);

    void pollEvents();

    void pollEvents(int i);

    void removeListener(GIIListener gIIListener);

    void updateLogName();
}
