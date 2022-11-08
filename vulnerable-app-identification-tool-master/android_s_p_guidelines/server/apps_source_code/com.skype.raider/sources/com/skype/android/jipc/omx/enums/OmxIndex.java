package com.skype.android.jipc.omx.enums;

import com.skype.android.jipc.Enumerable;
import com.skype.android.jipc.omx.OmxVersion;

public interface OmxIndex extends Enumerable, OmxVersion {

    public enum Audio implements OmxIndex {
        OMX_IndexAudioStartUnused,
        OMX_IndexParamAudioPortFormat,
        OMX_IndexParamAudioPcm,
        OMX_IndexParamAudioAac,
        OMX_IndexParamAudioRa,
        OMX_IndexParamAudioMp3,
        OMX_IndexParamAudioAdpcm,
        OMX_IndexParamAudioG723,
        OMX_IndexParamAudioG729,
        OMX_IndexParamAudioAmr,
        OMX_IndexParamAudioWma,
        OMX_IndexParamAudioSbc,
        OMX_IndexParamAudioMidi,
        OMX_IndexParamAudioGsm_FR,
        OMX_IndexParamAudioMidiLoadUserSound,
        OMX_IndexParamAudioG726,
        OMX_IndexParamAudioGsm_EFR,
        OMX_IndexParamAudioGsm_HR,
        OMX_IndexParamAudioPdc_FR,
        OMX_IndexParamAudioPdc_EFR,
        OMX_IndexParamAudioPdc_HR,
        OMX_IndexParamAudioTdma_FR,
        OMX_IndexParamAudioTdma_EFR,
        OMX_IndexParamAudioQcelp8,
        OMX_IndexParamAudioQcelp13,
        OMX_IndexParamAudioEvrc,
        OMX_IndexParamAudioSmv,
        OMX_IndexParamAudioVorbis,
        OMX_IndexConfigAudioMidiImmediateEvent,
        OMX_IndexConfigAudioMidiControl,
        OMX_IndexConfigAudioMidiSoundBankProgram,
        OMX_IndexConfigAudioMidiStatus,
        OMX_IndexConfigAudioMidiMetaEvent,
        OMX_IndexConfigAudioMidiMetaEventData,
        OMX_IndexConfigAudioVolume,
        OMX_IndexConfigAudioBalance,
        OMX_IndexConfigAudioChannelMute,
        OMX_IndexConfigAudioMute,
        OMX_IndexConfigAudioLoudness,
        OMX_IndexConfigAudioEchoCancelation,
        OMX_IndexConfigAudioNoiseReduction,
        OMX_IndexConfigAudioBass,
        OMX_IndexConfigAudioTreble,
        OMX_IndexConfigAudioStereoWidening,
        OMX_IndexConfigAudioChorus,
        OMX_IndexConfigAudioEqualizer,
        OMX_IndexConfigAudioReverberation,
        OMX_IndexConfigAudioChannelVolume;

        public final int a() {
            return ordinal() + 67108864;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Common implements OmxIndex {
        OMX_IndexCommonStartUnused,
        OMX_IndexParamCommonDeblocking,
        OMX_IndexParamCommonSensorMode,
        OMX_IndexParamCommonInterleave,
        OMX_IndexConfigCommonColorFormatConversion,
        OMX_IndexConfigCommonScale,
        OMX_IndexConfigCommonImageFilter,
        OMX_IndexConfigCommonColorEnhancement,
        OMX_IndexConfigCommonColorKey,
        OMX_IndexConfigCommonColorBlend,
        OMX_IndexConfigCommonFrameStabilisation,
        OMX_IndexConfigCommonRotate,
        OMX_IndexConfigCommonMirror,
        OMX_IndexConfigCommonOutputPosition,
        OMX_IndexConfigCommonInputCrop,
        OMX_IndexConfigCommonOutputCrop,
        OMX_IndexConfigCommonDigitalZoom,
        OMX_IndexConfigCommonOpticalZoom,
        OMX_IndexConfigCommonWhiteBalance,
        OMX_IndexConfigCommonExposure,
        OMX_IndexConfigCommonContrast,
        OMX_IndexConfigCommonBrightness,
        OMX_IndexConfigCommonBacklight,
        OMX_IndexConfigCommonGamma,
        OMX_IndexConfigCommonSaturation,
        OMX_IndexConfigCommonLightness,
        OMX_IndexConfigCommonExclusionRect,
        OMX_IndexConfigCommonDithering,
        OMX_IndexConfigCommonPlaneBlend,
        OMX_IndexConfigCommonExposureValue,
        OMX_IndexConfigCommonOutputSize,
        OMX_IndexParamCommonExtraQuantData,
        OMX_IndexConfigCommonFocusRegion,
        OMX_IndexConfigCommonFocusStatus;

        public final int a() {
            return ordinal() + 117440512;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Component implements OmxIndex {
        OMX_IndexComponentStartUnused,
        OMX_IndexParamPriorityMgmt,
        OMX_IndexParamAudioInit,
        OMX_IndexParamImageInit,
        OMX_IndexParamVideoInit,
        OMX_IndexParamOtherInit,
        OMX_IndexParamNumAvailableStreams,
        OMX_IndexParamActiveStream,
        OMX_IndexParamSuspensionPolicy,
        OMX_IndexParamComponentSuspended,
        OMX_IndexConfigCapturing,
        OMX_IndexConfigCaptureMode,
        OMX_IndexAutoPauseAfterCapture,
        OMX_IndexParamContentURI,
        OMX_IndexParamCustomContentPipe,
        OMX_IndexParamDisableResourceConcealment,
        OMX_IndexConfigMetadataItemCount,
        OMX_IndexConfigContainerNodeCount,
        OMX_IndexConfigMetadataItem,
        OMX_IndexConfigCounterNodeID,
        OMX_IndexParamMetadataFilterType,
        OMX_IndexParamMetadataKeyFilter,
        OMX_IndexConfigPriorityMgmt,
        OMX_IndexParamStandardComponentRole;

        public final int a() {
            return ordinal() + 16777216;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Config implements OmxIndex {
        OMX_IndexOtherStartUnused,
        OMX_IndexParamOtherPortFormat,
        OMX_IndexConfigOtherPower,
        OMX_IndexConfigOtherStats;

        public final int a() {
            return ordinal() + 134217728;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Image implements OmxIndex {
        OMX_IndexImageStartUnused,
        OMX_IndexParamImagePortFormat,
        OMX_IndexParamFlashControl,
        OMX_IndexConfigFocusControl,
        OMX_IndexParamQFactor,
        OMX_IndexParamQuantizationTable,
        OMX_IndexParamHuffmanTable,
        OMX_IndexConfigFlashControl;

        public final int a() {
            return ordinal() + 83886080;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Port implements OmxIndex {
        OMX_IndexPortStartUnused,
        OMX_IndexParamPortDefinition,
        OMX_IndexParamCompBufferSupplier;

        public final int a() {
            return ordinal() + 33554432;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Reserved implements OmxIndex {
        ;

        private Reserved(String str) {
        }

        public final int a() {
            return ordinal() + 50331648;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Time implements OmxIndex {
        OMX_IndexTimeStartUnused,
        OMX_IndexConfigTimeScale,
        OMX_IndexConfigTimeClockState,
        OMX_IndexConfigTimeActiveRefClock,
        OMX_IndexConfigTimeCurrentMediaTime,
        OMX_IndexConfigTimeCurrentWallTime,
        OMX_IndexConfigTimeCurrentAudioReference,
        OMX_IndexConfigTimeCurrentVideoReference,
        OMX_IndexConfigTimeMediaTimeRequest,
        OMX_IndexConfigTimeClientStartTime,
        OMX_IndexConfigTimePosition,
        OMX_IndexConfigTimeSeekMode;

        public final int a() {
            return ordinal() + 150994944;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Vendor implements OmxIndex {
        ;

        private Vendor(String str) {
        }

        public final int a() {
            return ordinal() + 2130706432;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }

    public enum Video implements OmxIndex {
        OMX_IndexVideoStartUnused,
        OMX_IndexParamVideoPortFormat,
        OMX_IndexParamVideoQuantization,
        OMX_IndexParamVideoFastUpdate,
        OMX_IndexParamVideoBitrate,
        OMX_IndexParamVideoMotionVector,
        OMX_IndexParamVideoIntraRefresh,
        OMX_IndexParamVideoErrorCorrection,
        OMX_IndexParamVideoVBSMC,
        OMX_IndexParamVideoMpeg2,
        OMX_IndexParamVideoMpeg4,
        OMX_IndexParamVideoWmv,
        OMX_IndexParamVideoRv,
        OMX_IndexParamVideoAvc,
        OMX_IndexParamVideoH263,
        OMX_IndexParamVideoProfileLevelQuerySupported,
        OMX_IndexParamVideoProfileLevelCurrent,
        OMX_IndexConfigVideoBitrate,
        OMX_IndexConfigVideoFramerate,
        OMX_IndexConfigVideoIntraVOPRefresh,
        OMX_IndexConfigVideoIntraMBRefresh,
        OMX_IndexConfigVideoMBErrorReporting,
        OMX_IndexParamVideoMacroblocksPerFrame,
        OMX_IndexConfigVideoMacroBlockErrorMap,
        OMX_IndexParamVideoSliceFMO,
        OMX_IndexConfigVideoAVCIntraPeriod,
        OMX_IndexConfigVideoNalSize;

        public final int a() {
            return ordinal() + 100663296;
        }

        public final int x_() {
            return 1;
        }

        public final int d() {
            return 0;
        }
    }
}
