package com.google.android.exoplayer2.b;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.AudioCapabilities;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.util.Pair;
import com.google.android.exoplayer2.d.s;
import com.skype.android.video.hw.utils.CodecUtils;

@TargetApi(16)
public final class a {
    public final String a;
    public final boolean b;
    public final boolean c;
    private final String d;
    private final CodecCapabilities e;

    public static a a(String name) {
        return new a(name, null, null, false);
    }

    public static a a(String name, String mimeType, CodecCapabilities capabilities, boolean forceDisableAdaptive) {
        return new a(name, mimeType, capabilities, forceDisableAdaptive);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private a(String name, String mimeType, CodecCapabilities capabilities, boolean forceDisableAdaptive) {
        boolean z;
        boolean z2 = true;
        this.a = (String) com.google.android.exoplayer2.d.a.a((Object) name);
        this.d = mimeType;
        this.e = capabilities;
        if (!(forceDisableAdaptive || capabilities == null)) {
            if (s.a < 19 || !capabilities.isFeatureSupported("adaptive-playback")) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                z = true;
                this.b = z;
                if (capabilities != null) {
                    if (s.a >= 21 || !capabilities.isFeatureSupported("tunneled-playback")) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
                z2 = false;
                this.c = z2;
            }
        }
        z = false;
        this.b = z;
        if (capabilities != null) {
            if (s.a >= 21) {
            }
            z = false;
        }
        z2 = false;
        this.c = z2;
    }

    public final CodecProfileLevel[] a() {
        return (this.e == null || this.e.profileLevels == null) ? new CodecProfileLevel[0] : this.e.profileLevels;
    }

    public final boolean b(String codec) {
        if (codec == null || this.d == null) {
            return true;
        }
        String codecMimeType;
        Pair<Integer, Integer> codecProfileAndLevel;
        int i;
        if (codec != null) {
            String trim = codec.trim();
            if (trim.startsWith("avc1") || trim.startsWith("avc3")) {
                codecMimeType = CodecUtils.MEDIA_TYPE;
                if (codecMimeType != null) {
                    return true;
                }
                if (this.d.equals(codecMimeType)) {
                    c("codec.mime " + codec + ", " + codecMimeType);
                    return false;
                }
                codecProfileAndLevel = d.a(codec);
                if (codecProfileAndLevel != null) {
                    return true;
                }
                for (CodecProfileLevel capabilities : a()) {
                    if (capabilities.profile != ((Integer) codecProfileAndLevel.first).intValue() && capabilities.level >= ((Integer) codecProfileAndLevel.second).intValue()) {
                        return true;
                    }
                }
                c("codec.profileLevel, " + codec + ", " + codecMimeType);
                return false;
            } else if (trim.startsWith("hev1") || trim.startsWith("hvc1")) {
                codecMimeType = "video/hevc";
                if (codecMimeType != null) {
                    return true;
                }
                if (this.d.equals(codecMimeType)) {
                    codecProfileAndLevel = d.a(codec);
                    if (codecProfileAndLevel != null) {
                        return true;
                    }
                    for (i = 0; i < r8; i++) {
                        if (capabilities.profile != ((Integer) codecProfileAndLevel.first).intValue()) {
                        }
                    }
                    c("codec.profileLevel, " + codec + ", " + codecMimeType);
                    return false;
                }
                c("codec.mime " + codec + ", " + codecMimeType);
                return false;
            } else {
                if (trim.startsWith("vp9")) {
                    codecMimeType = "video/x-vnd.on2.vp9";
                } else if (trim.startsWith("vp8")) {
                    codecMimeType = "video/x-vnd.on2.vp8";
                } else if (trim.startsWith("mp4a")) {
                    codecMimeType = "audio/mp4a-latm";
                } else if (trim.startsWith("ac-3") || trim.startsWith("dac3")) {
                    codecMimeType = "audio/ac3";
                } else if (trim.startsWith("ec-3") || trim.startsWith("dec3")) {
                    codecMimeType = "audio/eac3";
                } else if (trim.startsWith("dtsc") || trim.startsWith("dtse")) {
                    codecMimeType = "audio/vnd.dts";
                } else if (trim.startsWith("dtsh") || trim.startsWith("dtsl")) {
                    codecMimeType = "audio/vnd.dts.hd";
                } else if (trim.startsWith("opus")) {
                    codecMimeType = "audio/opus";
                } else if (trim.startsWith("vorbis")) {
                    codecMimeType = "audio/vorbis";
                }
                if (codecMimeType != null) {
                    return true;
                }
                if (this.d.equals(codecMimeType)) {
                    c("codec.mime " + codec + ", " + codecMimeType);
                    return false;
                }
                codecProfileAndLevel = d.a(codec);
                if (codecProfileAndLevel != null) {
                    return true;
                }
                for (i = 0; i < r8; i++) {
                    if (capabilities.profile != ((Integer) codecProfileAndLevel.first).intValue()) {
                    }
                }
                c("codec.profileLevel, " + codec + ", " + codecMimeType);
                return false;
            }
        }
        codecMimeType = null;
        if (codecMimeType != null) {
            return true;
        }
        if (this.d.equals(codecMimeType)) {
            codecProfileAndLevel = d.a(codec);
            if (codecProfileAndLevel != null) {
                return true;
            }
            for (i = 0; i < r8; i++) {
                if (capabilities.profile != ((Integer) codecProfileAndLevel.first).intValue()) {
                }
            }
            c("codec.profileLevel, " + codec + ", " + codecMimeType);
            return false;
        }
        c("codec.mime " + codec + ", " + codecMimeType);
        return false;
    }

    @TargetApi(21)
    public final boolean a(int width, int height, double frameRate) {
        if (this.e == null) {
            c("sizeAndRate.caps");
            return false;
        }
        VideoCapabilities videoCapabilities = this.e.getVideoCapabilities();
        if (videoCapabilities == null) {
            c("sizeAndRate.vCaps");
            return false;
        }
        if (!a(videoCapabilities, width, height, frameRate)) {
            if (width >= height || !a(videoCapabilities, height, width, frameRate)) {
                c("sizeAndRate.support, " + width + "x" + height + "x" + frameRate);
                return false;
            }
            new StringBuilder("AssumedSupport [").append("sizeAndRate.rotated, " + width + "x" + height + "x" + frameRate).append("] [").append(this.a).append(", ").append(this.d).append("] [").append(s.e).append("]");
        }
        return true;
    }

    @TargetApi(21)
    public final Point a(int width, int height) {
        if (this.e == null) {
            c("align.caps");
            return null;
        }
        VideoCapabilities videoCapabilities = this.e.getVideoCapabilities();
        if (videoCapabilities == null) {
            c("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(s.a(width, widthAlignment) * widthAlignment, s.a(height, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public final boolean a(int sampleRate) {
        if (this.e == null) {
            c("sampleRate.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = this.e.getAudioCapabilities();
        if (audioCapabilities == null) {
            c("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(sampleRate)) {
            return true;
        } else {
            c("sampleRate.support, " + sampleRate);
            return false;
        }
    }

    @TargetApi(21)
    public final boolean b(int channelCount) {
        if (this.e == null) {
            c("channelCount.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = this.e.getAudioCapabilities();
        if (audioCapabilities == null) {
            c("channelCount.aCaps");
            return false;
        } else if (audioCapabilities.getMaxInputChannelCount() >= channelCount) {
            return true;
        } else {
            c("channelCount.support, " + channelCount);
            return false;
        }
    }

    private void c(String message) {
        new StringBuilder("NoSupport [").append(message).append("] [").append(this.a).append(", ").append(this.d).append("] [").append(s.e).append("]");
    }

    @TargetApi(21)
    private static boolean a(VideoCapabilities capabilities, int width, int height, double frameRate) {
        if (frameRate == -1.0d || frameRate <= 0.0d) {
            return capabilities.isSizeSupported(width, height);
        }
        return capabilities.areSizeAndRateSupported(width, height, frameRate);
    }
}
