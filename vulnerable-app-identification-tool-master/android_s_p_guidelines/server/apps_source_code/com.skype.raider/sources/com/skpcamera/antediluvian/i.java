package com.skpcamera.antediluvian;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import com.facebook.common.logging.FLog;
import com.skype.android.video.hw.utils.CodecUtils;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

public final class i {
    private final int a;
    private final int b;
    private final int c;
    private final int d = 30;
    private final int e;
    private final boolean f;
    private b g;
    private Surface h;
    private MediaCodec i;

    public interface a {
        void a();
    }

    private static class b extends Thread {
        private k a;
        private MediaCodec b;
        private MediaFormat c;
        private BufferInfo d;
        private a e;
        private a f;
        private final Object g = new Object();
        private volatile boolean h = false;

        private static class a extends Handler {
            private WeakReference<b> a;

            public a(b et) {
                this.a = new WeakReference(et);
            }

            public final void handleMessage(Message msg) {
                int what = msg.what;
                b encoderThread = (b) this.a.get();
                if (encoderThread == null) {
                    FLog.w("SkypeCameraCircEncoder", "EncoderHandler.handleMessage: weak ref is null");
                    return;
                }
                switch (what) {
                    case 1:
                        encoderThread.c();
                        return;
                    case 2:
                        FLog.i("SkypeCameraCircEncoder", "EncoderHandler: saveVideo");
                        encoderThread.a((File) msg.obj);
                        return;
                    case 3:
                        FLog.i("SkypeCameraCircEncoder", "EncoderHandler: shutdown");
                        encoderThread.d();
                        return;
                    default:
                        throw new RuntimeException("unknown message " + what);
                }
            }
        }

        public b(MediaCodec mediaCodec, a callback, k sink) {
            this.b = mediaCodec;
            this.f = callback;
            this.d = new BufferInfo();
            this.a = sink;
        }

        public final void run() {
            Looper.prepare();
            this.e = new a(this);
            FLog.i("SkypeCameraCircEncoder", "encoder thread ready");
            synchronized (this.g) {
                this.h = true;
                this.g.notify();
            }
            Looper.loop();
            synchronized (this.g) {
                this.h = false;
                this.e = null;
            }
            FLog.i("SkypeCameraCircEncoder", "looper quit");
        }

        public final void a() {
            synchronized (this.g) {
                while (!this.h) {
                    try {
                        this.g.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        public final a b() {
            synchronized (this.g) {
                if (this.h) {
                } else {
                    throw new RuntimeException("encoder not ready");
                }
            }
            return this.e;
        }

        final void c() {
            ByteBuffer[] outputBuffers = this.b.getOutputBuffers();
            while (true) {
                int dequeueOutputBuffer = this.b.dequeueOutputBuffer(this.d, 0);
                if (dequeueOutputBuffer == -1) {
                    return;
                }
                if (dequeueOutputBuffer == -3) {
                    outputBuffers = this.b.getOutputBuffers();
                } else if (dequeueOutputBuffer == -2) {
                    this.c = this.b.getOutputFormat();
                    if (this.a != null) {
                        this.a.b(j.CHANGE_MEDIA_FORMAT, this.c);
                    }
                    FLog.i("SkypeCameraCircEncoder", "encoder output format changed: " + this.c);
                } else if (dequeueOutputBuffer < 0) {
                    FLog.w("SkypeCameraCircEncoder", "unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                } else {
                    ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                    if (byteBuffer == null) {
                        throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                    }
                    if ((this.d.flags & 2) != 0) {
                        FLog.i("SkypeCameraCircEncoder", "ignoring BUFFER_FLAG_CODEC_CONFIG");
                        this.d.size = 0;
                    }
                    if (this.d.size != 0) {
                        byteBuffer.position(this.d.offset);
                        byteBuffer.limit(this.d.offset + this.d.size);
                    }
                    if ((this.d.flags & 2) == 0 && this.d.size > 0) {
                        byteBuffer = outputBuffers[dequeueOutputBuffer];
                        if (this.a != null) {
                            FLog.i("SkypeCameraCircEncoder", "sent " + this.d.size + " bytes to muxer, ts=" + this.d.presentationTimeUs);
                            this.a.b(j.WRITE_SAMPLE, new q(CodecUtils.MEDIA_TYPE, byteBuffer, this.d));
                        }
                    }
                    this.b.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((this.d.flags & 4) != 0) {
                        FLog.w("SkypeCameraCircEncoder", "reached end of stream unexpectedly");
                        return;
                    }
                }
            }
        }

        final void a(File outputFile) {
            FLog.i("SkypeCameraCircEncoder", "saveVideo " + outputFile);
            if (this.a != null) {
                this.a.b(j.END_OF_STREAM, CodecUtils.MEDIA_TYPE);
            } else {
                FLog.w("SkypeCameraCircEncoder", "could not send EOS");
            }
            this.f.a();
        }

        final void d() {
            FLog.i("SkypeCameraCircEncoder", "shutdown");
            if (this.b != null) {
                FLog.i("SkypeCameraCircEncoder", "stop codec");
                try {
                    this.b.stop();
                    FLog.i("SkypeCameraCircEncoder", "releasing codec");
                    this.b.release();
                } catch (IllegalStateException e) {
                    FLog.w("SkypeCameraCircEncoder", "unable to stop and release codec " + e.getLocalizedMessage());
                    if (VERSION.SDK_INT >= 21) {
                        FLog.i("SkypeCameraCircEncoder", "reseting codec");
                        this.b.reset();
                    } else {
                        FLog.i("SkypeCameraCircEncoder", "cannot reset codec; API level too low");
                    }
                    FLog.i("SkypeCameraCircEncoder", "releasing codec");
                    this.b.release();
                } catch (Throwable th) {
                    FLog.i("SkypeCameraCircEncoder", "releasing codec");
                    this.b.release();
                }
                this.b = null;
            } else {
                FLog.i("SkypeCameraCircEncoder", "no codec to stop");
            }
            Looper.myLooper().quit();
        }
    }

    public i(int width, int height, int bitRate, int profile, boolean enableVideoBFrame, a cb, k sink) throws IOException {
        this.a = width;
        this.b = height;
        this.c = bitRate;
        this.e = profile;
        this.f = enableVideoBFrame;
        this.i = MediaCodec.createEncoderByType(CodecUtils.MEDIA_TYPE);
        Object a = a(false);
        try {
            this.i.configure(a, null, null, 1);
        } catch (Throwable e) {
            FLog.w("SkypeCameraCircEncoder", "Failed to configure video encoder with given properties", e);
            a = a(true);
            this.i.configure(a, null, null, 1);
        }
        FLog.i("SkypeCameraCircEncoder", "format: " + a);
        this.h = this.i.createInputSurface();
        this.i.start();
        this.g = new b(this.i, cb, sink);
        this.g.start();
        this.g.a();
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
        format = MediaFormat.createVideoFormat(CodecUtils.MEDIA_TYPE, this.a, this.b);
        format.setInteger("color-format", 2130708361);
        format.setInteger("bitrate", this.c);
        format.setInteger("frame-rate", this.d);
        format.setInteger("i-frame-interval", 1);
        int i = this.e;
        if (this.i == null || i <= 1 || VERSION.SDK_INT < 25) {
            return format;
        }
        CodecCapabilities capabilitiesForType = this.i.getCodecInfo().getCapabilitiesForType(CodecUtils.MEDIA_TYPE);
        if (capabilitiesForType.profileLevels == null) {
            return format;
        }
        for (CodecProfileLevel codecProfileLevel : capabilitiesForType.profileLevels) {
            if (codecProfileLevel.profile == i) {
                FLog.i("SkypeCameraCircEncoder", "Device supports requested codec profile: " + i);
                format.setInteger("profile", i);
                format.setInteger("level", 512);
                return format;
            }
        }
        return format;
    }

    public final Surface a() {
        return this.h;
    }

    public final void b() {
        FLog.i("SkypeCameraCircEncoder", "releasing encoder objects");
        Handler b = this.g.b();
        b.sendMessage(b.obtainMessage(3));
        try {
            this.g.join();
        } catch (Throwable ie) {
            FLog.w("SkypeCameraCircEncoder", "Encoder thread join() was interrupted", ie);
        }
    }

    public final void c() {
        Handler b = this.g.b();
        b.sendMessage(b.obtainMessage(1));
    }

    public final void a(File outputFile) {
        Handler b = this.g.b();
        b.sendMessage(b.obtainMessage(2, outputFile));
    }
}
