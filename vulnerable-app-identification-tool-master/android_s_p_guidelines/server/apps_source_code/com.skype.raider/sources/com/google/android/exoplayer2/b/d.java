package com.google.android.exoplayer2.b;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.d.s;
import com.skype.Defines;
import com.skype.android.video.hw.utils.CodecUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class d {
    private static final a a = a.a("OMX.google.raw.decoder");
    private static final Pattern b = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<a, List<a>> c = new HashMap();
    private static final SparseIntArray d;
    private static final SparseIntArray e;
    private static final Map<String, Integer> f;
    private static int g = -1;

    private static final class a {
        public final String a;
        public final boolean b;

        public a(String mimeType, boolean secure) {
            this.a = mimeType;
            this.b = secure;
        }

        public final int hashCode() {
            return (this.b ? 1231 : 1237) + (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != a.class) {
                return false;
            }
            a other = (a) obj;
            if (TextUtils.equals(this.a, other.a) && this.b == other.b) {
                return true;
            }
            return false;
        }
    }

    public static class b extends Exception {
        /* synthetic */ b(Throwable x0, byte b) {
            this(x0);
        }

        private b(Throwable cause) {
            super("Failed to query underlying media codecs", cause);
        }
    }

    private interface c {
        int a();

        MediaCodecInfo a(int i);

        boolean a(String str, CodecCapabilities codecCapabilities);

        boolean b();
    }

    private static final class d implements c {
        private d() {
        }

        /* synthetic */ d(byte b) {
            this();
        }

        public final int a() {
            return MediaCodecList.getCodecCount();
        }

        public final MediaCodecInfo a(int index) {
            return MediaCodecList.getCodecInfoAt(index);
        }

        public final boolean b() {
            return false;
        }

        public final boolean a(String mimeType, CodecCapabilities capabilities) {
            return CodecUtils.MEDIA_TYPE.equals(mimeType);
        }
    }

    @TargetApi(21)
    private static final class e implements c {
        private final int a;
        private MediaCodecInfo[] b;

        public e(boolean includeSecure) {
            this.a = includeSecure ? 1 : 0;
        }

        public final int a() {
            c();
            return this.b.length;
        }

        public final MediaCodecInfo a(int index) {
            c();
            return this.b[index];
        }

        public final boolean b() {
            return true;
        }

        public final boolean a(String mimeType, CodecCapabilities capabilities) {
            return capabilities.isFeatureSupported("secure-playback");
        }

        private void c() {
            if (this.b == null) {
                this.b = new MediaCodecList(this.a).getCodecInfos();
            }
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        d = sparseIntArray;
        sparseIntArray.put(66, 1);
        d.put(77, 2);
        d.put(88, 4);
        d.put(100, 8);
        sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        sparseIntArray.put(10, 1);
        e.put(11, 4);
        e.put(12, 8);
        e.put(13, 16);
        e.put(20, 32);
        e.put(21, 64);
        e.put(22, 128);
        e.put(30, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE);
        e.put(31, 512);
        e.put(32, 1024);
        e.put(40, 2048);
        e.put(41, 4096);
        e.put(42, 8192);
        e.put(50, 16384);
        e.put(51, 32768);
        e.put(52, 65536);
        Map hashMap = new HashMap();
        f = hashMap;
        hashMap.put("L30", Integer.valueOf(1));
        f.put("L60", Integer.valueOf(4));
        f.put("L63", Integer.valueOf(16));
        f.put("L90", Integer.valueOf(64));
        f.put("L93", Integer.valueOf(Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE));
        f.put("L120", Integer.valueOf(1024));
        f.put("L123", Integer.valueOf(4096));
        f.put("L150", Integer.valueOf(16384));
        f.put("L153", Integer.valueOf(65536));
        f.put("L156", Integer.valueOf(262144));
        f.put("L180", Integer.valueOf(1048576));
        f.put("L183", Integer.valueOf(4194304));
        f.put("L186", Integer.valueOf(16777216));
        f.put("H30", Integer.valueOf(2));
        f.put("H60", Integer.valueOf(8));
        f.put("H63", Integer.valueOf(32));
        f.put("H90", Integer.valueOf(128));
        f.put("H93", Integer.valueOf(512));
        f.put("H120", Integer.valueOf(2048));
        f.put("H123", Integer.valueOf(8192));
        f.put("H150", Integer.valueOf(32768));
        f.put("H153", Integer.valueOf(131072));
        f.put("H156", Integer.valueOf(524288));
        f.put("H180", Integer.valueOf(2097152));
        f.put("H183", Integer.valueOf(8388608));
        f.put("H186", Integer.valueOf(33554432));
    }

    public static a a() {
        return a;
    }

    public static a a(String mimeType, boolean secure) throws b {
        List<a> decoderInfos = b(mimeType, secure);
        return decoderInfos.isEmpty() ? null : (a) decoderInfos.get(0);
    }

    private static synchronized List<a> b(String mimeType, boolean secure) throws b {
        List<a> decoderInfos;
        synchronized (d.class) {
            a key = new a(mimeType, secure);
            List<a> decoderInfos2 = (List) c.get(key);
            if (decoderInfos2 != null) {
                decoderInfos = decoderInfos2;
            } else {
                c mediaCodecList;
                if (s.a >= 21) {
                    mediaCodecList = new e(secure);
                } else {
                    mediaCodecList = new d();
                }
                decoderInfos2 = a(key, mediaCodecList);
                if (secure && decoderInfos2.isEmpty() && 21 <= s.a && s.a <= 23) {
                    decoderInfos2 = a(key, new d());
                    if (!decoderInfos2.isEmpty()) {
                        new StringBuilder("MediaCodecList API didn't list secure decoder for: ").append(mimeType).append(". Assuming: ").append(((a) decoderInfos2.get(0)).a);
                    }
                }
                decoderInfos2 = Collections.unmodifiableList(decoderInfos2);
                c.put(key, decoderInfos2);
                decoderInfos = decoderInfos2;
            }
        }
        return decoderInfos;
    }

    public static int b() throws b {
        if (g == -1) {
            int result = 0;
            a decoderInfo = a(CodecUtils.MEDIA_TYPE, false);
            if (decoderInfo != null) {
                for (CodecProfileLevel codecProfileLevel : decoderInfo.a()) {
                    int i;
                    switch (codecProfileLevel.level) {
                        case 1:
                            i = 25344;
                            break;
                        case 2:
                            i = 25344;
                            break;
                        case 8:
                            i = 101376;
                            break;
                        case 16:
                            i = 101376;
                            break;
                        case 32:
                            i = 101376;
                            break;
                        case 64:
                            i = 202752;
                            break;
                        case 128:
                            i = 414720;
                            break;
                        case Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE /*256*/:
                            i = 414720;
                            break;
                        case 512:
                            i = 921600;
                            break;
                        case 1024:
                            i = 1310720;
                            break;
                        case 2048:
                            i = 2097152;
                            break;
                        case 4096:
                            i = 2097152;
                            break;
                        case 8192:
                            i = 2228224;
                            break;
                        case 16384:
                            i = 5652480;
                            break;
                        case 32768:
                            i = 9437184;
                            break;
                        case 65536:
                            i = 9437184;
                            break;
                        default:
                            i = -1;
                            break;
                    }
                    result = Math.max(i, result);
                }
                result = Math.max(result, s.a >= 21 ? 345600 : 172800);
            }
            g = result;
        }
        return g;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Pair<Integer, Integer> a(String codec) {
        int i = 0;
        if (codec == null) {
            return null;
        }
        String[] parts = codec.split("\\.");
        String str = parts[0];
        switch (str.hashCode()) {
            case 3006243:
                if (str.equals("avc1")) {
                    i = 2;
                    break;
                }
            case 3006244:
                if (str.equals("avc2")) {
                    i = 3;
                    break;
                }
            case 3199032:
                break;
            case 3214780:
                if (str.equals("hvc1")) {
                    i = 1;
                    break;
                }
            default:
                i = -1;
                break;
        }
        switch (i) {
            case 0:
            case 1:
                if (parts.length < 4) {
                    return null;
                }
                Matcher matcher = b.matcher(parts[1]);
                if (!matcher.matches()) {
                    return null;
                }
                String group = matcher.group(1);
                if ("1".equals(group)) {
                    i = 1;
                } else if (!"2".equals(group)) {
                    return null;
                } else {
                    i = 2;
                }
                Integer num = (Integer) f.get(parts[3]);
                if (num != null) {
                    return new Pair(Integer.valueOf(i), num);
                }
                new StringBuilder("Unknown HEVC level string: ").append(matcher.group(1));
                return null;
            case 2:
            case 3:
                return a(parts);
            default:
                return null;
        }
    }

    private static List<a> a(a key, c mediaCodecList) throws b {
        String codecName;
        try {
            List<a> decoderInfos = new ArrayList();
            String mimeType = key.a;
            int numberOfCodecs = mediaCodecList.a();
            boolean secureDecodersExplicit = mediaCodecList.b();
            loop0:
            for (int i = 0; i < numberOfCodecs; i++) {
                Object obj;
                MediaCodecInfo codecInfo = mediaCodecList.a(i);
                codecName = codecInfo.getName();
                if (codecInfo.isEncoder() || (!secureDecodersExplicit && codecName.endsWith(".secure"))) {
                    obj = null;
                } else if (s.a < 21 && ("CIPAACDecoder".equals(codecName) || "CIPMP3Decoder".equals(codecName) || "CIPVorbisDecoder".equals(codecName) || "CIPAMRNBDecoder".equals(codecName) || "AACDecoder".equals(codecName) || "MP3Decoder".equals(codecName))) {
                    obj = null;
                } else if (s.a < 18 && "OMX.SEC.MP3.Decoder".equals(codecName)) {
                    obj = null;
                } else if (s.a < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(codecName) && "a70".equals(s.b)) {
                    obj = null;
                } else if (s.a == 16 && "OMX.qcom.audio.decoder.mp3".equals(codecName) && ("dlxu".equals(s.b) || "protou".equals(s.b) || "ville".equals(s.b) || "villeplus".equals(s.b) || "villec2".equals(s.b) || s.b.startsWith("gee") || "C6602".equals(s.b) || "C6603".equals(s.b) || "C6606".equals(s.b) || "C6616".equals(s.b) || "L36h".equals(s.b) || "SO-02E".equals(s.b))) {
                    obj = null;
                } else if (s.a == 16 && "OMX.qcom.audio.decoder.aac".equals(codecName) && ("C1504".equals(s.b) || "C1505".equals(s.b) || "C1604".equals(s.b) || "C1605".equals(s.b))) {
                    obj = null;
                } else if (s.a <= 19 && "OMX.SEC.vp8.dec".equals(codecName) && "samsung".equals(s.c) && (s.b.startsWith("d2") || s.b.startsWith("serrano") || s.b.startsWith("jflte") || s.b.startsWith("santos") || s.b.startsWith("t0"))) {
                    obj = null;
                } else if (s.a <= 19 && s.b.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(codecName)) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    for (String supportedType : codecInfo.getSupportedTypes()) {
                        if (supportedType.equalsIgnoreCase(mimeType)) {
                            boolean forceDisableAdaptive;
                            CodecCapabilities capabilities = codecInfo.getCapabilitiesForType(supportedType);
                            boolean secure = mediaCodecList.a(mimeType, capabilities);
                            if (s.a > 22 || !((s.d.equals("ODROID-XU3") || s.d.equals("Nexus 10")) && ("OMX.Exynos.AVC.Decoder".equals(codecName) || "OMX.Exynos.AVC.Decoder.secure".equals(codecName)))) {
                                forceDisableAdaptive = false;
                            } else {
                                forceDisableAdaptive = true;
                            }
                            if ((!secureDecodersExplicit || key.b != secure) && (secureDecodersExplicit || key.b)) {
                                if (!secureDecodersExplicit && secure) {
                                    decoderInfos.add(a.a(codecName + ".secure", mimeType, capabilities, forceDisableAdaptive));
                                    break loop0;
                                }
                            }
                            decoderInfos.add(a.a(codecName, mimeType, capabilities, forceDisableAdaptive));
                        }
                    }
                    continue;
                }
            }
            return decoderInfos;
        } catch (Exception e) {
            if (s.a > 23 || decoderInfos.isEmpty()) {
                new StringBuilder("Failed to query codec ").append(codecName).append(" (").append(supportedType).append(")");
                throw e;
            }
            new StringBuilder("Skipping codec ").append(codecName).append(" (failed to query capabilities)");
        } catch (Exception e2) {
            throw new b(e2, (byte) 0);
        }
    }

    private static Pair<Integer, Integer> a(String[] codecsParts) {
        if (codecsParts.length < 2) {
            return null;
        }
        try {
            Integer profileInteger;
            Integer levelInteger;
            if (codecsParts[1].length() == 6) {
                profileInteger = Integer.valueOf(Integer.parseInt(codecsParts[1].substring(0, 2), 16));
                levelInteger = Integer.valueOf(Integer.parseInt(codecsParts[1].substring(4), 16));
            } else if (codecsParts.length < 3) {
                return null;
            } else {
                profileInteger = Integer.valueOf(Integer.parseInt(codecsParts[1]));
                levelInteger = Integer.valueOf(Integer.parseInt(codecsParts[2]));
            }
            Integer profile = Integer.valueOf(d.get(profileInteger.intValue()));
            if (profile == null) {
                new StringBuilder("Unknown AVC profile: ").append(profileInteger);
                return null;
            }
            Integer level = Integer.valueOf(e.get(levelInteger.intValue()));
            if (level != null) {
                return new Pair(profile, level);
            }
            new StringBuilder("Unknown AVC level: ").append(levelInteger);
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
