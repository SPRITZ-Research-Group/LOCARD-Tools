package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

@TargetApi(16)
public final class MediaCodecUtil {
    private static final String TAG = "MediaCodecUtil";
    private static final HashMap<CodecKey, Pair<String, CodecCapabilities>> codecs = new HashMap();

    final class CodecKey {
        public final String mimeType;
        public final boolean secure;

        public CodecKey(String str, boolean z) {
            this.mimeType = str;
            this.secure = z;
        }

        public final int hashCode() {
            return (((this.mimeType == null ? 0 : this.mimeType.hashCode()) + 31) * 31) + (this.secure ? 1231 : 1237);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            return TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure;
        }
    }

    public class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        public final boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        public final int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public final MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public final boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return MimeTypes.VIDEO_H264.equals(str);
        }
    }

    @TargetApi(21)
    final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public final boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z) {
            this.codecKind = z;
        }

        public final int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public final MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        public final boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    private static int avcLevelToMaxFrameSize(int i) {
        switch (i) {
            case 1:
                return 25344;
            case 2:
                return 25344;
            case 8:
                return 101376;
            case 16:
                return 101376;
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
                return 414720;
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
                return PKIFailureInfo.badSenderNonce;
            case 4096:
                return PKIFailureInfo.badSenderNonce;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
                return 9437184;
            default:
                return -1;
        }
    }

    private MediaCodecUtil() {
    }

    public static DecoderInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException {
        Pair mediaCodecInfo = getMediaCodecInfo(str, z);
        if (mediaCodecInfo == null) {
            return null;
        }
        return new DecoderInfo((String) mediaCodecInfo.first, isAdaptive((CodecCapabilities) mediaCodecInfo.second));
    }

    public static synchronized void warmCodec(String str, boolean z) {
        synchronized (MediaCodecUtil.class) {
            try {
                getMediaCodecInfo(str, z);
            } catch (Throwable e) {
                Log.e(TAG, "Codec warming failed", e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized Pair<String, CodecCapabilities> getMediaCodecInfo(String str, boolean z) throws DecoderQueryException {
        synchronized (MediaCodecUtil.class) {
            CodecKey codecKey = new CodecKey(str, z);
            if (codecs.containsKey(codecKey)) {
                Pair<String, CodecCapabilities> pair = (Pair) codecs.get(codecKey);
                return pair;
            }
            Pair<String, CodecCapabilities> mediaCodecInfo = getMediaCodecInfo(codecKey, Util.SDK_INT >= 21 ? new MediaCodecListCompatV21(z) : new MediaCodecListCompatV16());
            if (z && mediaCodecInfo == null && Util.SDK_INT >= 21) {
                mediaCodecInfo = getMediaCodecInfo(codecKey, new MediaCodecListCompatV16());
                if (mediaCodecInfo != null) {
                    String str2 = TAG;
                    StringBuilder stringBuilder = new StringBuilder("MediaCodecList API didn't list secure decoder for: ");
                    stringBuilder.append(str);
                    stringBuilder.append(". Assuming: ");
                    stringBuilder.append((String) mediaCodecInfo.first);
                    Log.w(str2, stringBuilder.toString());
                }
            }
        }
    }

    private static Pair<String, CodecCapabilities> getMediaCodecInfo(CodecKey codecKey, MediaCodecListCompat mediaCodecListCompat) throws DecoderQueryException {
        try {
            return getMediaCodecInfoInternal(codecKey, mediaCodecListCompat);
        } catch (Throwable e) {
            throw new DecoderQueryException(e);
        }
    }

    private static Pair<String, CodecCapabilities> getMediaCodecInfoInternal(CodecKey codecKey, MediaCodecListCompat mediaCodecListCompat) {
        String str = codecKey.mimeType;
        int codecCount = mediaCodecListCompat.getCodecCount();
        boolean secureDecodersExplicit = mediaCodecListCompat.secureDecodersExplicit();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = mediaCodecListCompat.getCodecInfoAt(i);
            String name = codecInfoAt.getName();
            if (isCodecUsableDecoder(codecInfoAt, name, secureDecodersExplicit)) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (String str2 : supportedTypes) {
                    if (str2.equalsIgnoreCase(str)) {
                        CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str2);
                        boolean isSecurePlaybackSupported = mediaCodecListCompat.isSecurePlaybackSupported(codecKey.mimeType, capabilitiesForType);
                        if (secureDecodersExplicit) {
                            codecs.put(codecKey.secure == isSecurePlaybackSupported ? codecKey : new CodecKey(str, isSecurePlaybackSupported), Pair.create(name, capabilitiesForType));
                        } else {
                            codecs.put(codecKey.secure ? new CodecKey(str, false) : codecKey, Pair.create(name, capabilitiesForType));
                            if (isSecurePlaybackSupported) {
                                HashMap hashMap = codecs;
                                Object codecKey2 = codecKey.secure ? codecKey : new CodecKey(str, true);
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(name);
                                stringBuilder.append(".secure");
                                hashMap.put(codecKey2, Pair.create(stringBuilder.toString(), capabilitiesForType));
                            }
                        }
                        if (codecs.containsKey(codecKey)) {
                            return (Pair) codecs.get(codecKey);
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z) {
        if (mediaCodecInfo.isEncoder() || !str.startsWith("OMX.") || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT == 16 && (("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE)) && str.equals("OMX.qcom.audio.decoder.mp3"))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && Util.DEVICE != null && Util.DEVICE.startsWith("serrano") && "samsung".equals(Util.MANUFACTURER) && str.equals("OMX.SEC.vp8.dec")) {
            return false;
        }
        return true;
    }

    private static boolean isAdaptive(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 19 ? isAdaptiveV19(codecCapabilities) : false;
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    @TargetApi(21)
    public static boolean isSizeAndRateSupportedV21(String str, boolean z, int i, int i2, double d) throws DecoderQueryException {
        Assertions.checkState(Util.SDK_INT >= 21);
        Pair mediaCodecInfo = getMediaCodecInfo(str, z);
        if (mediaCodecInfo == null) {
            return false;
        }
        VideoCapabilities videoCapabilities = ((CodecCapabilities) mediaCodecInfo.second).getVideoCapabilities();
        return videoCapabilities != null && videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }

    public static boolean isH264ProfileSupported(int i, int i2) throws DecoderQueryException {
        Pair mediaCodecInfo = getMediaCodecInfo(MimeTypes.VIDEO_H264, false);
        if (mediaCodecInfo == null) {
            return false;
        }
        CodecCapabilities codecCapabilities = (CodecCapabilities) mediaCodecInfo.second;
        for (CodecProfileLevel codecProfileLevel : codecCapabilities.profileLevels) {
            if (codecProfileLevel.profile == i && codecProfileLevel.level >= i2) {
                return true;
            }
        }
        return false;
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        int i = 0;
        Pair mediaCodecInfo = getMediaCodecInfo(MimeTypes.VIDEO_H264, false);
        if (mediaCodecInfo == null) {
            return 0;
        }
        CodecCapabilities codecCapabilities = (CodecCapabilities) mediaCodecInfo.second;
        int i2 = 0;
        while (i < codecCapabilities.profileLevels.length) {
            i2 = Math.max(avcLevelToMaxFrameSize(codecCapabilities.profileLevels[i].level), i2);
            i++;
        }
        return i2;
    }
}
