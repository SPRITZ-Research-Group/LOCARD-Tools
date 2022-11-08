package com.skpcamera.antediluvian;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import com.facebook.common.logging.FLog;
import com.skype.android.video.hw.extension.SliqConstants;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(16)
final class a extends e {
    private MediaCodec b;
    private BufferInfo c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private k j;
    private long k;

    a(k sink, b settings) {
        super("AACEncoderComponent");
        this.j = sink;
        this.d = settings.a();
        this.e = settings.b();
        this.f = settings.c();
        this.g = settings.d();
    }

    private void c() {
        ByteBuffer[] outputBuffers = this.b == null ? null : this.b.getOutputBuffers();
        while (this.h) {
            int result = this.b.dequeueOutputBuffer(this.c, 3000);
            switch (result) {
                case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                    outputBuffers = this.b.getOutputBuffers();
                    break;
                case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                    if (this.j != null) {
                        this.j.b(j.CHANGE_MEDIA_FORMAT, this.b.getOutputFormat());
                        this.i = true;
                        break;
                    }
                    break;
                case -1:
                    return;
            }
            if ((this.c.flags & 4) != 0) {
                return;
            }
            if (result >= 0) {
                ByteBuffer output = outputBuffers[result];
                if ((this.c.flags & 2) == 0 && this.c.size > 0 && this.j != null) {
                    if (this.c.presentationTimeUs < this.k) {
                        FLog.i(this.a, "bufferInfo.presentationTimeUs < presentationTimeLast");
                        BufferInfo bufferInfo = this.c;
                        long j = this.k + ((long) this.c.size);
                        this.k = j;
                        bufferInfo.presentationTimeUs = j;
                    }
                    if (this.i) {
                        BufferInfo bi = new BufferInfo();
                        bi.set(this.c.offset, this.c.size, this.c.presentationTimeUs, this.c.flags);
                        this.j.b(j.WRITE_SAMPLE, new q("audio/mp4a-latm", output, bi));
                    }
                    this.k = this.c.presentationTimeUs;
                }
                this.b.releaseOutputBuffer(result, false);
            }
        }
    }

    private void a(ByteBuffer buffer, boolean endOStream) {
        while (this.h && buffer.remaining() > 0) {
            ByteBuffer[] inputs = this.b.getInputBuffers();
            int result = this.b.dequeueInputBuffer(3000);
            if (result >= 0) {
                long presentationTime = System.nanoTime() / 1000;
                ByteBuffer input = inputs[result];
                input.clear();
                int size = buffer.remaining();
                if (size <= input.remaining()) {
                    input.put(buffer);
                } else {
                    ByteBuffer b = buffer.duplicate();
                    size = input.remaining();
                    b.limit(size);
                    input.put(b);
                    input.limit(size);
                    buffer.position(buffer.position() + size);
                }
                input.position(0);
                this.b.queueInputBuffer(result, 0, size, presentationTime, endOStream ? 4 : 0);
                if (!endOStream) {
                    c();
                }
            }
        }
    }

    public final boolean a(j command, Object data) {
        FLog.i(this.a, "handle " + command);
        switch (command) {
            case START:
                try {
                    this.b = MediaCodec.createEncoderByType("audio/mp4a-latm");
                    this.c = new BufferInfo();
                    FLog.i(this.a, "use codec " + this.b.getName());
                    MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", this.e, this.f);
                    if (this.b.getName().equals("OMX.google.aac.decoder")) {
                        this.b.release();
                        this.b = MediaCodec.createByCodecName("OMX.google.aac.encoder");
                    }
                    createAudioFormat.setInteger("aac-profile", this.d);
                    createAudioFormat.setInteger("bitrate", this.g);
                    this.b.configure(createAudioFormat, null, null, 1);
                    this.b.start();
                    b(j.DEQUEUE_BUFFER, null);
                    this.h = true;
                    return true;
                } catch (IOException e) {
                    FLog.e(this.a, "startEncoder " + e.getLocalizedMessage());
                    return true;
                }
            case RELEASE:
                FLog.i(this.a, "stopEncoder recording: " + this.h + " codec: " + (this.b != null));
                if (this.h) {
                    a(ByteBuffer.allocateDirect(0), true);
                    c();
                }
                this.h = false;
                if (this.b != null) {
                    this.b.stop();
                    this.b.release();
                    this.b = null;
                    if (this.j != null) {
                        this.j.b(j.END_OF_STREAM, "audio/mp4a-latm");
                    }
                    this.i = false;
                }
                FLog.i(this.a, "stopEncoder done");
                return true;
            case DEQUEUE_BUFFER:
                c();
                return true;
            case QUEUE_BUFFER:
                a((ByteBuffer) data, false);
                return true;
            default:
                return false;
        }
    }
}
