package com.skypecam.obscura.b;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skypecam.obscura.c.g;
import com.skypecam.obscura.c.h;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class l {
    private final a a;
    private final int b;
    private final boolean c;
    private MediaFormat d;
    private BufferInfo e;
    private final int f;
    private final int g;
    private final int h;
    private final int i = 30;
    private h j;

    public interface a {
        void a(g gVar);

        void b(MediaFormat mediaFormat);

        void d();
    }

    public l(int width, int height, int bitRate, int profile, boolean enableVideoBFrame, a callback, h codecWrapper) throws IOException {
        this.f = width;
        this.g = height;
        this.h = bitRate;
        this.b = profile;
        this.c = enableVideoBFrame;
        this.a = callback;
        this.j = codecWrapper;
        this.e = new BufferInfo();
        codecWrapper.a(CodecUtils.MEDIA_TYPE);
        MediaFormat format = a(false);
        com.skypecam.obscura.e.g.a().b("Encoder", "format: " + format);
        try {
            codecWrapper.a(format);
        } catch (Exception ex) {
            com.skypecam.obscura.e.g.a().c("Encoder", "Failed to configure video encoder with given properties " + ex.getLocalizedMessage());
            format = a(true);
            codecWrapper.a(format);
        }
        com.skypecam.obscura.e.g.a().b("Encoder", "format: " + format);
        codecWrapper.c();
    }

    public final void a() {
        this.j.a();
    }

    public final Surface b() {
        return this.j.b();
    }

    private MediaFormat a(boolean defaults) {
        MediaFormat format;
        if (defaults) {
            format = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, 640, 360);
            format.setInteger("color-format", 2130708361);
            format.setInteger("bitrate", 1300);
            format.setInteger("frame-rate", 30);
            format.setInteger("i-frame-interval", 1);
            return format;
        }
        format = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, this.f, this.g);
        format.setInteger("color-format", 2130708361);
        format.setInteger("bitrate", this.h);
        format.setInteger("frame-rate", this.i);
        format.setInteger("i-frame-interval", 1);
        int i = this.b;
        if (this.j.e() == null || i <= 1 || VERSION.SDK_INT < 25) {
            return format;
        }
        CodecCapabilities capabilitiesForType = this.j.d().getCapabilitiesForType(CodecUtils.MEDIA_TYPE);
        if (capabilitiesForType.profileLevels == null) {
            return format;
        }
        for (CodecProfileLevel codecProfileLevel : capabilitiesForType.profileLevels) {
            if (codecProfileLevel.profile == i) {
                com.skypecam.obscura.e.g.a().b("Encoder", "Device supports requested codec profile: " + i);
                format.setInteger("profile", i);
                format.setInteger("level", 512);
                return format;
            }
        }
        return format;
    }

    public final void a(File outputFile) {
        com.skypecam.obscura.e.g.a().b("Encoder", "saveVideo " + outputFile);
        this.a.d();
    }

    public final void d() {
        com.skypecam.obscura.e.g.a().b("Encoder", "shutdown");
        this.j.h();
    }

    public final void c() {
        ByteBuffer[] f = this.j.f();
        while (true) {
            int a = this.j.a(this.e);
            if (a == -1) {
                com.skypecam.obscura.e.g.a().b("Encoder", "INFO_TRY_AGAIN_LATER");
                return;
            } else if (a == -3) {
                com.skypecam.obscura.e.g.a().b("Encoder", "INFO_OUTPUT_BUFFERS_CHANGED");
                f = this.j.f();
            } else if (a == -2) {
                com.skypecam.obscura.e.g.a().b("Encoder", "INFO_OUTPUT_FORMAT_CHANGED");
                this.d = this.j.g();
                this.a.b(this.d);
                com.skypecam.obscura.e.g.a().b("Encoder", "encoder output format changed: " + this.d);
            } else if (a < 0) {
                com.skypecam.obscura.e.g.a().c("Encoder", "unexpected result from encoder.dequeueOutputBuffer: " + a);
            } else {
                ByteBuffer byteBuffer = f[a];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + a + " was null");
                }
                if ((this.e.flags & 2) != 0) {
                    com.skypecam.obscura.e.g.a().b("Encoder", "ignoring BUFFER_FLAG_CODEC_CONFIG");
                    this.e.size = 0;
                }
                if (this.e.size != 0) {
                    byteBuffer.position(this.e.offset);
                    byteBuffer.limit(this.e.offset + this.e.size);
                }
                if ((this.e.flags & 2) == 0 && this.e.size > 0) {
                    byteBuffer = f[a];
                    com.skypecam.obscura.e.g.a().b("Encoder", "sent " + this.e.size + " bytes to muxer, ts=" + this.e.presentationTimeUs);
                    this.a.a(new g(CodecUtils.MEDIA_TYPE, byteBuffer, this.e));
                }
                this.j.a(a);
                if ((this.e.flags & 4) != 0) {
                    com.skypecam.obscura.e.g.a().c("Encoder", "reached end of stream unexpectedly");
                    return;
                }
            }
        }
    }
}
