package com.skype;

import android.support.v4.util.m;

public interface msrtc {

    public enum AudioUsageMode {
        Default(0),
        LongrangeSpeaker(1),
        Auditorium(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<AudioUsageMode> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            AudioUsageMode[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                AudioUsageMode type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private AudioUsageMode(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static AudioUsageMode fromInt(int i) {
            AudioUsageMode tmpVar = (AudioUsageMode) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum QualityEventType {
        Invalid(-1),
        NetworkSendQuality(0),
        NetworkRecvQuality(1),
        NetworkDelay(2),
        NetworkBandwidthLow(3),
        EventReserved0(4),
        NetworkPacketLoss(5),
        NetworkJitter(6),
        NetworkRateMatching(7),
        DeviceCaptureNotFunctioning(8),
        DeviceRenderNotFunctioning(9),
        DeviceRenderGlitches(10),
        DeviceLowSNR(11),
        DeviceLowSpeechLevel(12),
        DeviceClipping(13),
        DeviceEcho(14),
        PresentationAudioQuality(15),
        DeviceHalfDuplexAec(16),
        DeviceMultipleEndpoints(17),
        DeviceHowling(18),
        DeviceRenderZeroVolume(19),
        DeviceRenderMute(20),
        NetworkSendCatastrophic(21),
        NetworkRecvCatastrophic(22),
        EventReserved4(23),
        CpuInsufficient(24),
        DeviceCaptureMute(25),
        DeviceCaptureNotMuteButSilent(26),
        DeviceSpeakWhileMuted(27),
        VideoVbssRendered(28),
        EventReserved5(29),
        EventReserved6(30),
        EventReserved7(31),
        NetworkRoaming(32),
        NetworkEthernetInterfaceUsed(33),
        NetworkWlanInterfaceUsed(34),
        NetworkWwanInterfaceUsed(35),
        RelayWhiteListing(36),
        NetworkReconnect(37),
        VideoCapturerDeviceStartFailed(38),
        VideoCapturerDeviceStartTimedOut(39),
        VideoCapturerDeviceStartFailureLackSystemRes(40),
        VideoCapturerDeviceStartFailureMFResConflict(41),
        ZeroCaptureDevicesEnumerated(42),
        ZeroRenderDevicesEnumerated(43),
        NoNetwork(44),
        NetworkNotWorking(45),
        DeviceCaptureNotFunctioningAudioSrvNotRunning(46),
        DeviceRenderNotFunctioningAudioSrvNotRunning(47),
        DeviceCaptureNotFunctioningDeviceInUse(48),
        DeviceRenderNotFunctioningDeviceInUse(49),
        VideoCaptureDeviceFreeze(50),
        AudioCapturePermissionDenied(51),
        VideoCapturePermissionDenied(52),
        VideoCaptureFreezeRecovered(53),
        DeviceRenderHowling(54),
        VideoRecvNetworkFreeze(55),
        QualityEventCount(55),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<QualityEventType> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            QualityEventType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                QualityEventType type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private QualityEventType(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static QualityEventType fromInt(int i) {
            QualityEventType tmpVar = (QualityEventType) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum QualityLevel {
        Unknown(0),
        Good(1),
        Poor(2),
        Bad(3),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<QualityLevel> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            QualityLevel[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                QualityLevel type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private QualityLevel(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static QualityLevel fromInt(int i) {
            QualityLevel tmpVar = (QualityLevel) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum RenderlessVideoSinkType {
        invalid(0),
        raw(1),
        encoded(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<RenderlessVideoSinkType> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            RenderlessVideoSinkType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                RenderlessVideoSinkType type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private RenderlessVideoSinkType(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static RenderlessVideoSinkType fromInt(int i) {
            RenderlessVideoSinkType tmpVar = (RenderlessVideoSinkType) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }

    public enum RtpSourceProviderDeviceType {
        None(0),
        MainVideo(1),
        ScreenSharing(2),
        WRAPPER_UNKNOWN_VALUE(Integer.MAX_VALUE);
        
        private static final m<RtpSourceProviderDeviceType> intToTypeMap = null;
        private final int value;

        static {
            intToTypeMap = new m();
            RtpSourceProviderDeviceType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                RtpSourceProviderDeviceType type = values[i];
                intToTypeMap.a(type.value, type);
                i++;
            }
        }

        private RtpSourceProviderDeviceType(int value) {
            this.value = value;
        }

        public final int toInt() {
            return this.value;
        }

        public static RtpSourceProviderDeviceType fromInt(int i) {
            RtpSourceProviderDeviceType tmpVar = (RtpSourceProviderDeviceType) intToTypeMap.a(i);
            return tmpVar != null ? tmpVar : WRAPPER_UNKNOWN_VALUE;
        }
    }
}
