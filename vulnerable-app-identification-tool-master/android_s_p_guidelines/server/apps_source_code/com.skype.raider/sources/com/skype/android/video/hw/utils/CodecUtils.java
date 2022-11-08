package com.skype.android.video.hw.utils;

import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.util.Range;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.HWFeatureSelectiveFields;
import com.skype.android.video.hw.format.Capabilities;
import com.skype.android.video.hw.format.ColorFormat;
import com.skype.android.video.hw.format.H264Level;
import com.skype.android.video.hw.format.H264Profile;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class CodecUtils {
    private static final int Driver_ExynosLTRFix = 180315;
    public static final String MEDIA_TYPE = "video/avc";
    public static int hwMode_java = 0;
    public static final String[] omxCapableHW = new String[]{"OMX.Intel.hw_ve.h264", "OMX.Exynos.AVC.Encoder", "OMX.MTK.VIDEO.ENCODER.AVC"};

    private static class CapabilitiesHandler {
        private static Map<String, Capabilities> decoderCapabilities;
        private static Map<String, Capabilities> encoderCapabilities;

        private CapabilitiesHandler() {
        }

        public static synchronized Map<String, Capabilities> getEncoderCapabilities() {
            Map<String, Capabilities> map;
            synchronized (CapabilitiesHandler.class) {
                if (encoderCapabilities == null) {
                    encoderCapabilities = CodecUtils.getAllCapabilities(true);
                }
                map = encoderCapabilities;
            }
            return map;
        }

        public static synchronized Map<String, Capabilities> getDecoderCapabilities() {
            Map<String, Capabilities> map;
            synchronized (CapabilitiesHandler.class) {
                if (decoderCapabilities == null) {
                    decoderCapabilities = CodecUtils.getAllCapabilities(false);
                }
                map = decoderCapabilities;
            }
            return map;
        }

        public static String[] enumCodecs(boolean encoder) {
            Map<String, Capabilities> map = encoder ? getEncoderCapabilities() : getDecoderCapabilities();
            return (String[]) map.keySet().toArray(new String[map.size()]);
        }
    }

    public static native int getDecoderOperatingFpsFromNative();

    public static native int getEncoderOperatingFpsFromNative();

    public static native void overrideHWModeToNative(int i);

    public static String[] enumEncoders() {
        String[] names = CapabilitiesHandler.enumCodecs(true);
        Arrays.sort(names);
        return names;
    }

    public static String[] enumDecoders() {
        String[] names = CapabilitiesHandler.enumCodecs(false);
        Arrays.sort(names);
        return names;
    }

    public static Capabilities getEncoderCapabilities(String encoderName) {
        return (Capabilities) CapabilitiesHandler.getEncoderCapabilities().get(encoderName);
    }

    public static Capabilities getDecoderCapabilities(String decoderName) {
        return (Capabilities) CapabilitiesHandler.getDecoderCapabilities().get(decoderName);
    }

    private static Map<String, Capabilities> getAllCapabilities(boolean isEncoder) {
        boolean isHwEncoderApiSupported;
        boolean isHwDecoderApiSupported;
        boolean isAPI21orHigher;
        int i = 0;
        if (VERSION.SDK_INT >= 18) {
            isHwEncoderApiSupported = true;
        } else {
            isHwEncoderApiSupported = false;
        }
        if (VERSION.SDK_INT >= 16) {
            isHwDecoderApiSupported = true;
        } else {
            isHwDecoderApiSupported = false;
        }
        if (VERSION.SDK_INT >= 21) {
            isAPI21orHigher = true;
        } else {
            isAPI21orHigher = false;
        }
        Map<String, Capabilities> codecCapabilities = new HashMap();
        if (isHwEncoderApiSupported || isHwDecoderApiSupported) {
            MediaCodecInfo codecInfo;
            if (isAPI21orHigher) {
                try {
                    MediaCodecInfo[] codecInfos = new MediaCodecList(1).getCodecInfos();
                    int length = codecInfos.length;
                    while (i < length) {
                        codecInfo = codecInfos[i];
                        if (codecInfo.isEncoder() == isEncoder) {
                            if (isEncoder) {
                                if (!isHwEncoderApiSupported) {
                                }
                            } else if (!isHwDecoderApiSupported) {
                            }
                            getCodecCapabilities(codecCapabilities, codecInfo, codecInfo.isEncoder());
                        }
                        i++;
                    }
                } catch (RuntimeException e) {
                    if (Log.isLoggable(Commons.TAG, 6)) {
                        Log.e(Commons.TAG, "Exception caught", e);
                    }
                }
            } else {
                for (int i2 = 0; i2 < MediaCodecList.getCodecCount(); i2++) {
                    codecInfo = MediaCodecList.getCodecInfoAt(i2);
                    if (codecInfo.isEncoder() == isEncoder) {
                        if (isEncoder) {
                            if (!isHwEncoderApiSupported) {
                            }
                        } else if (!isHwDecoderApiSupported) {
                        }
                        getCodecCapabilities(codecCapabilities, codecInfo, codecInfo.isEncoder());
                    }
                }
            }
        }
        for (Capabilities caps : codecCapabilities.values()) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, new StringBuilder(MEDIA_TYPE).append(isEncoder ? " encoder detected: " : " decoder detected: ").append(caps).toString());
            }
        }
        return codecCapabilities;
    }

    private static MediaFormat getNewQCMediaExtAfterNougat(boolean isEncoder, String extPrefix, boolean isExynos) {
        try {
            DummyMediaCodec dummyCodec = new DummyMediaCodec(isEncoder, isExynos);
            dummyCodec.init(640, 360, extPrefix);
            dummyCodec.start();
            dummyCodec.callbackCountDown.await(5, TimeUnit.SECONDS);
            dummyCodec.stop();
            if (isEncoder) {
                if (dummyCodec.mEncOutputFormat == null || !dummyCodec.mEncOutputFormat.containsKey(extPrefix + "-ext-enc-caps-vt-driver-version.number")) {
                    return null;
                }
                if (!isExynos || dummyCodec.mEncOutputFormat.getInteger(extPrefix + "-ext-enc-caps-vt-driver-version.number") >= Driver_ExynosLTRFix) {
                    return dummyCodec.mEncOutputFormat;
                }
                return null;
            } else if (dummyCodec.mDecOutputFormat == null) {
                return null;
            } else {
                if (dummyCodec.mDecOutputFormat.containsKey(extPrefix + "-ext-dec-caps-vt-driver-version.number")) {
                    return dummyCodec.mDecOutputFormat;
                }
                if (isExynos && dummyCodec.mDecOutputFormat.containsKey(extPrefix + "-ext-enc-caps-vt-driver-version.number")) {
                    return dummyCodec.mDecOutputFormat;
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void getCodecCapabilities(Map<String, Capabilities> codecCapabilities, MediaCodecInfo codecInfo, boolean isEncoder) {
        boolean isVideoCapsAvailable = VERSION.SDK_INT >= 21;
        String extPrefix = "vendor.rtc";
        String[] supportedTypes = codecInfo.getSupportedTypes();
        int length = supportedTypes.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < length) {
                if (!(!MEDIA_TYPE.equalsIgnoreCase(supportedTypes[i2]) || codecInfo.getName().toLowerCase().startsWith("omx.google.") || codecInfo.getName().toLowerCase().startsWith("omx.sec.") || codecInfo.getName().toLowerCase().endsWith(".secure"))) {
                    CodecCapabilities caps = codecInfo.getCapabilitiesForType(MEDIA_TYPE);
                    MediaFormat extCaps = null;
                    boolean isNewQCMediaExtAfterNougat = VERSION.SDK_INT >= 26;
                    if (isNewQCMediaExtAfterNougat) {
                        extPrefix = codecInfo.getName().toLowerCase().contains("qcom") ? "vendor.qti" : "vendor.rtc";
                        Log.i(Commons.TAG, "isNewQCMediaExtAfterNougat true path extprefix: " + extPrefix);
                        extCaps = getNewQCMediaExtAfterNougat(isEncoder, extPrefix, codecInfo.getName().toLowerCase().contains("exynos"));
                    } else {
                        Log.i(Commons.TAG, "isNewQCMediaExtAfterNougat false path");
                        try {
                            extCaps = (MediaFormat) caps.getClass().getDeclaredMethod("getCapabilitiesInfoFormat", new Class[0]).invoke(caps, new Object[0]);
                        } catch (NoSuchMethodException e) {
                            Log.w(Commons.TAG, "Could not find getCapabilitiesInfoFormat");
                        } catch (IllegalAccessException e2) {
                            Log.w(Commons.TAG, "Could not find getCapabilitiesInfoFormat");
                        } catch (InvocationTargetException e3) {
                            Log.w(Commons.TAG, "Could not find getCapabilitiesInfoFormat");
                        } catch (IllegalArgumentException e4) {
                            Log.w(Commons.TAG, "Could not find getCapabilitiesInfoFormat");
                        }
                    }
                    if (caps.colorFormats.length > 0 && caps.profileLevels.length > 0) {
                        Boolean.valueOf(false);
                        if (isVideoCapsAvailable) {
                            VideoCapabilities vidCaps = caps.getVideoCapabilities();
                            Range<Integer> widthRange = vidCaps.getSupportedWidths();
                            Range<Integer> heightRange = vidCaps.getSupportedHeights();
                            Range<Integer> bitrateRange = vidCaps.getBitrateRange();
                            Boolean supportQCExtension = Boolean.valueOf(false);
                            if (extCaps != null) {
                                if (!isNewQCMediaExtAfterNougat) {
                                    String version = extCaps.getString("vt-version");
                                    if (version != null) {
                                        supportQCExtension = Boolean.valueOf(true);
                                        Log.d(Commons.TAG, codecInfo.getName() + " vt-version returned: " + version);
                                    } else {
                                        Log.e(Commons.TAG, codecInfo.getName() + " vt-version returned: null");
                                    }
                                } else if (extCaps.containsKey(extPrefix + "-ext-enc-caps-vt-driver-version.number") || extCaps.containsKey(extPrefix + "-ext-dec-caps-vt-driver-version.number")) {
                                    supportQCExtension = Boolean.valueOf(true);
                                    if (isEncoder) {
                                        Log.i(Commons.TAG, codecInfo.getName() + ":" + extPrefix + "-ext-enc-caps-vt-driver-version.number: " + extCaps.getInteger(extPrefix + "-ext-enc-caps-vt-driver-version.number"));
                                    } else {
                                        Log.i(Commons.TAG, codecInfo.getName() + ":" + extPrefix + "-ext-dec-caps-vt-driver-version.number: " + extCaps.getInteger(extPrefix + "-ext-dec-caps-vt-driver-version.number"));
                                    }
                                } else if (isEncoder) {
                                    Log.e(Commons.TAG, codecInfo.getName() + ":" + extPrefix + "-ext-enc-caps-vt-driver-version.number: null");
                                } else {
                                    Log.e(Commons.TAG, codecInfo.getName() + ":" + extPrefix + "-ext-dec-caps-vt-driver-version.number: null");
                                }
                            }
                            if (supportQCExtension.booleanValue()) {
                                if (!isNewQCMediaExtAfterNougat) {
                                    if (isEncoder) {
                                        Log.i(Commons.TAG, codecInfo.getName() + " supports VTVideoCapabilities:");
                                        Log.i(Commons.TAG, "getVersion() =>                     " + extCaps.getString("vt-version"));
                                        Log.i(Commons.TAG, "isLowLatencySupported() =>          " + extCaps.getString("vt-low-latency"));
                                        Log.i(Commons.TAG, "getMaxInstances() =>                " + extCaps.getString("vt-max-instances"));
                                        Log.i(Commons.TAG, "getMaxTemporalLayerCount() =>       " + extCaps.getString("vt-max-temporal-layer-count"));
                                        Log.i(Commons.TAG, "getMaxRefFrames() =>                " + extCaps.getString("vt-max-ref-frames"));
                                        Log.i(Commons.TAG, "getMaxLTRFrames() =>                " + extCaps.getString("vt-max-ltr-frames"));
                                        Log.i(Commons.TAG, "getMaxLevel() =>                    " + extCaps.getString("vt-max-level"));
                                        Log.i(Commons.TAG, "getSliceControlModesBM() =>         " + extCaps.getString("vt-slice-control-modes-bitmask"));
                                        Log.i(Commons.TAG, "getMaxMacroblockProcessingRate() => " + extCaps.getString("vt-max-macroblock-processing-rate"));
                                        Log.i(Commons.TAG, "getSupportedDownScaleFactor() =>    " + extCaps.getString("vt-down-scale-factor"));
                                        Log.i(Commons.TAG, "getMinScaleFactor() =>              " + extCaps.getString("vt-min-scale-factor"));
                                    } else {
                                        Log.i(Commons.TAG, codecInfo.getName() + " supports VTVideoCapabilities:");
                                        Log.i(Commons.TAG, "getVersion() =>                     " + extCaps.getString("vt-version"));
                                        Log.i(Commons.TAG, "isLowLatencySupported() =>          " + extCaps.getString("vt-low-latency"));
                                        Log.i(Commons.TAG, "getMaxInstances() =>                " + extCaps.getString("vt-max-instances"));
                                        Log.i(Commons.TAG, "getMaxLevel() =>                    " + extCaps.getString("vt-max-level"));
                                        Log.i(Commons.TAG, "getMaxMacroblockProcessingRate() => " + extCaps.getString("vt-max-macroblock-processing-rate"));
                                    }
                                }
                                codecCapabilities.put(codecInfo.getName(), mapCapabilities(codecInfo.getName(), caps, widthRange, heightRange, bitrateRange, extCaps));
                            } else {
                                Log.i(Commons.TAG, codecInfo.getName() + " does not support VTVideoCapabilities.");
                            }
                        }
                    }
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static Capabilities mapCapabilities(String codecName, CodecCapabilities codecCapabilities, Range<Integer> widthRange, Range<Integer> heightRange, Range<Integer> bitrateRange, MediaFormat extCaps) {
        Set noneOf;
        Set noneOf2;
        Set noneOf3;
        Set<H264Profile> profiles = new HashSet();
        Set<H264Level> levels = new HashSet();
        for (CodecProfileLevel pl : codecCapabilities.profileLevels) {
            try {
                profiles.add(H264Profile.fromOmx(pl.profile));
            } catch (NoSuchElementException e) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, "OMX H.264 profile " + pl.profile + " is not supported");
                }
            }
            try {
                levels.add(H264Level.fromOmx(pl.level));
            } catch (NoSuchElementException e2) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, "OMX H.264 level " + pl.level + " is not supported");
                }
            }
        }
        Set<ColorFormat> colorFormats = new HashSet();
        for (int cf : codecCapabilities.colorFormats) {
            try {
                colorFormats.add(ColorFormat.fromOmx(cf));
            } catch (NoSuchElementException e3) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, "OMX color format " + cf + " is not supported");
                }
            }
        }
        if (profiles.isEmpty()) {
            noneOf = EnumSet.noneOf(H264Profile.class);
        } else {
            noneOf = EnumSet.copyOf(profiles);
        }
        if (levels.isEmpty()) {
            noneOf2 = EnumSet.noneOf(H264Level.class);
        } else {
            noneOf2 = EnumSet.copyOf(levels);
        }
        if (colorFormats.isEmpty()) {
            noneOf3 = EnumSet.noneOf(ColorFormat.class);
        } else {
            noneOf3 = EnumSet.copyOf(colorFormats);
        }
        return new Capabilities(codecName, noneOf, noneOf2, noneOf3, widthRange, heightRange, bitrateRange, extCaps);
    }

    public static EnumSet<HWFeatureSelectiveFields> getHWMode() {
        Set<HWFeatureSelectiveFields> hwModeSet = new HashSet();
        for (HWFeatureSelectiveFields feature : HWFeatureSelectiveFields.values()) {
            if ((hwMode_java & feature.getValue()) != 0) {
                hwModeSet.add(feature);
            }
        }
        if (hwModeSet.isEmpty()) {
            return EnumSet.noneOf(HWFeatureSelectiveFields.class);
        }
        return EnumSet.copyOf(hwModeSet);
    }

    public static int setHWMode(int hwMode) {
        hwMode_java = hwMode;
        return hwMode;
    }

    public static void overrideHWMode(HWFeatureSelectiveFields hwMode) {
        overrideHWModeToNative(hwMode.getValue());
        hwMode_java = hwMode.getValue();
    }

    public static int clip(int ulysses, int scylla, int charybdis) {
        return Math.min(Math.max(scylla, charybdis), Math.max(ulysses, Math.min(scylla, charybdis)));
    }
}
