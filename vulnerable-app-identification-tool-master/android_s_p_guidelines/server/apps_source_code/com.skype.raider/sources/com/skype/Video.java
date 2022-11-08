package com.skype;

import android.support.v4.util.m;

public interface Video extends ObjectInterface {

    public enum FAILUREREASON {
        NONE(0),
        NO_DEVICE(1),
        CANNOT_START_DEVICE(2),
        DEVICE_REMOVED(3),
        RENDERER_FAILURE(4),
        SUBSCRIPTION_FAILURE(5),
        VIDEO_REJECTED(6),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<FAILUREREASON> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            FAILUREREASON[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                FAILUREREASON type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private FAILUREREASON(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static FAILUREREASON fromInt(int i) {
            FAILUREREASON tmpVar = (FAILUREREASON) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public static class GetCurrentVideoDevice_Result {
        public String m_deviceName;
        public String m_devicePath;
        public MEDIATYPE m_return;

        public void init(byte[] deviceName, byte[] devicePath, MEDIATYPE funcRet) {
            this.m_deviceName = NativeStringConvert.ConvertFromNativeBytes(deviceName);
            this.m_devicePath = NativeStringConvert.ConvertFromNativeBytes(devicePath);
            this.m_return = funcRet;
        }
    }

    public enum MEDIATYPE {
        MEDIA_SCREENSHARING(1),
        MEDIA_VIDEO(0),
        MEDIA_SR_AUGMENTED(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<MEDIATYPE> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            MEDIATYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                MEDIATYPE type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private MEDIATYPE(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static MEDIATYPE fromInt(int i) {
            MEDIATYPE tmpVar = (MEDIATYPE) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum STATUS {
        NOT_AVAILABLE(0),
        AVAILABLE(1),
        STARTING(2),
        RUNNING(4),
        STOPPING(5),
        PAUSED(6),
        NOT_STARTED(7),
        UNKNOWN(9),
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

    public enum VIDEO_DEVICE_CAPABILITY {
        VIDEOCAP_HQ_CAPABLE(0),
        VIDEOCAP_HQ_CERTIFIED(1),
        VIDEOCAP_REQ_DRIVERUPDATE(2),
        VIDEOCAP_USB_HIGHSPEED(3),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<VIDEO_DEVICE_CAPABILITY> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            VIDEO_DEVICE_CAPABILITY[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                VIDEO_DEVICE_CAPABILITY type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private VIDEO_DEVICE_CAPABILITY(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static VIDEO_DEVICE_CAPABILITY fromInt(int i) {
            VIDEO_DEVICE_CAPABILITY tmpVar = (VIDEO_DEVICE_CAPABILITY) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public interface VideoIListener {
    }

    void addListener(VideoIListener videoIListener);

    int getConvoIdProp();

    GetCurrentVideoDevice_Result getCurrentVideoDevice();

    String getDebuginfoProp();

    String getDeviceNameProp();

    String getDevicePathProp();

    String getDimensionsProp();

    int getDuration1080Prop();

    int getDuration720Prop();

    int getDurationHqvProp();

    int getDurationLtvgad2Prop();

    int getDurationSsProp();

    int getDurationVgad2Prop();

    String getEndpointIdProp();

    String getErrorProp();

    FAILUREREASON getFailureReasonProp();

    int getHqPresentProp();

    MEDIATYPE getMediaTypeProp();

    String getNegotiationTagProp();

    int getParticipantIdProp();

    String getParticipantLegIdProp();

    String getParticipantMriProp();

    int getRankProp();

    int getSsTimestampProp();

    STATUS getStatusProp();

    int getTimestampProp();

    void removeListener(VideoIListener videoIListener);

    void setScreenCaptureRectangle(int i, int i2, int i3, int i4);

    void setScreenCaptureRectangle(int i, int i2, int i3, int i4, int i5);

    void setScreenCaptureRectangle(int i, int i2, int i3, int i4, int i5, int i6);

    void start();

    void start(String str);

    void stop();

    void stop(String str);
}
