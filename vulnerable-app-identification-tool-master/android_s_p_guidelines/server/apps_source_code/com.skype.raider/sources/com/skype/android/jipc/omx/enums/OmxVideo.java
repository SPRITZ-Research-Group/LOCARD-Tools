package com.skype.android.jipc.omx.enums;

import com.skype.android.jipc.Enumerable;

public interface OmxVideo {

    public enum ANDROID_RC implements Enumerable {
        AR_AUTO,
        AR_INTERNAL,
        AR_GENERIC,
        AR_SLIQ;

        public final int a() {
            return ordinal();
        }
    }

    public enum OMX_COLOR_FORMATTYPE implements Enumerable {
        OMX_COLOR_FormatUnused,
        OMX_COLOR_FormatMonochrome,
        OMX_COLOR_Format8bitRGB332,
        OMX_COLOR_Format12bitRGB444,
        OMX_COLOR_Format16bitARGB4444,
        OMX_COLOR_Format16bitARGB1555,
        OMX_COLOR_Format16bitRGB565,
        OMX_COLOR_Format16bitBGR565,
        OMX_COLOR_Format18bitRGB666,
        OMX_COLOR_Format18bitARGB1665,
        OMX_COLOR_Format19bitARGB1666,
        OMX_COLOR_Format24bitRGB888,
        OMX_COLOR_Format24bitBGR888,
        OMX_COLOR_Format24bitARGB1887,
        OMX_COLOR_Format25bitARGB1888,
        OMX_COLOR_Format32bitBGRA8888,
        OMX_COLOR_Format32bitARGB8888,
        OMX_COLOR_FormatYUV411Planar,
        OMX_COLOR_FormatYUV411PackedPlanar,
        OMX_COLOR_FormatYUV420Planar,
        OMX_COLOR_FormatYUV420PackedPlanar,
        OMX_COLOR_FormatYUV420SemiPlanar,
        OMX_COLOR_FormatYUV422Planar,
        OMX_COLOR_FormatYUV422PackedPlanar,
        OMX_COLOR_FormatYUV422SemiPlanar,
        OMX_COLOR_FormatYCbYCr,
        OMX_COLOR_FormatYCrYCb,
        OMX_COLOR_FormatCbYCrY,
        OMX_COLOR_FormatCrYCbY,
        OMX_COLOR_FormatYUV444Interleaved,
        OMX_COLOR_FormatRawBayer8bit,
        OMX_COLOR_FormatRawBayer10bit,
        OMX_COLOR_FormatRawBayer8bitcompressed,
        OMX_COLOR_FormatL2,
        OMX_COLOR_FormatL4,
        OMX_COLOR_FormatL8,
        OMX_COLOR_FormatL16,
        OMX_COLOR_FormatL24,
        OMX_COLOR_FormatL32,
        OMX_COLOR_FormatYUV420PackedSemiPlanar,
        OMX_COLOR_FormatYUV422PackedSemiPlanar,
        OMX_COLOR_Format18BitBGR666,
        OMX_COLOR_Format24BitARGB6666,
        OMX_COLOR_Format24BitABGR6666;

        public final int a() {
            return ordinal();
        }
    }

    public enum OMX_VIDEO_AVCLOOPFILTERTYPE implements Enumerable {
        OMX_VIDEO_AVCLoopFilterEnable,
        OMX_VIDEO_AVCLoopFilterDisable,
        OMX_VIDEO_AVCLoopFilterDisableSliceBoundary;

        public final int a() {
            return ordinal();
        }
    }

    public enum OMX_VIDEO_AVCPROFILETYPE implements Enumerable {
        OMX_VIDEO_AVCProfileBaseline,
        OMX_VIDEO_AVCProfileMain,
        OMX_VIDEO_AVCProfileExtended,
        OMX_VIDEO_AVCProfileHigh,
        OMX_VIDEO_AVCProfileHigh10,
        OMX_VIDEO_AVCProfileHigh422,
        OMX_VIDEO_AVCProfileHigh444;

        public final int a() {
            return 1 << ordinal();
        }
    }

    public enum OMX_VIDEO_CODINGTYPE implements Enumerable {
        OMX_VIDEO_CodingUnused,
        OMX_VIDEO_CodingAutoDetect,
        OMX_VIDEO_CodingMPEG2,
        OMX_VIDEO_CodingH263,
        OMX_VIDEO_CodingMPEG4,
        OMX_VIDEO_CodingWMV,
        OMX_VIDEO_CodingRV,
        OMX_VIDEO_CodingAVC,
        OMX_VIDEO_CodingMJPEG;

        public final int a() {
            return ordinal();
        }
    }

    public enum OMX_VIDEO_CONTROLRATETYPE implements Enumerable {
        OMX_Video_ControlRateDisable,
        OMX_Video_ControlRateVariable,
        OMX_Video_ControlRateConstant,
        OMX_Video_ControlRateVariableSkipFrames,
        OMX_Video_ControlRateConstantSkipFrames;

        public final int a() {
            return ordinal();
        }
    }

    public enum OMX_VIDEO_EXTENSION_AVCPROFILETYPE implements Enumerable {
        OMX_VIDEO_EXT_AVCProfileConstrainedBaseline,
        OMX_VIDEO_EXT_AVCProfileConstrainedHigh;

        public final int a() {
            return 1 << ordinal();
        }
    }

    public enum OMX_VIDEO_HierarType implements Enumerable {
        OMX_VIDEO_HierarType_P,
        OMX_VIDEO_HierarType_B;

        public final int a() {
            return 1 << ordinal();
        }
    }

    public interface OMX_VIDEO_PICTURETYPE {
    }

    public enum OMX_VIDEO_SliceControlMode implements Enumerable {
        OMX_VIDEO_SliceControlModeNone,
        OMX_VIDEO_SliceControlModeMB,
        OMX_VIDEO_SliceControlModeByte,
        OMX_VIDEO_SliceControlModMBRow;

        public final int a() {
            return ordinal();
        }
    }
}
