package com.skypecam.obscura.a;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import com.skype.android.video.hw.extension.SliqConstants;
import com.skypecam.obscura.c.g;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class a extends c {
    private final a b;
    private MediaCodec c;
    private BufferInfo d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private long k;

    public interface a {
        void a();

        void a(MediaFormat mediaFormat);

        void a(g gVar);
    }

    public final /* bridge */ /* synthetic */ boolean a(g gVar) {
        return super.a(gVar);
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ boolean b(g gVar, Object obj) {
        return super.b(gVar, obj);
    }

    public a(a callback, b settings) {
        super("AACEncoderComponent");
        this.b = callback;
        this.e = settings.a();
        this.f = settings.b();
        this.g = settings.c();
        this.h = settings.d();
    }

    public final void a() {
        super.a(g.START);
    }

    private void d() {
        ByteBuffer[] outputBuffers = this.c == null ? null : this.c.getOutputBuffers();
        while (this.i) {
            int result = this.c.dequeueOutputBuffer(this.d, 3000);
            switch (result) {
                case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                    outputBuffers = this.c.getOutputBuffers();
                    break;
                case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                    this.b.a(this.c.getOutputFormat());
                    this.j = true;
                    break;
                case -1:
                    return;
            }
            if ((this.d.flags & 4) != 0) {
                return;
            }
            if (result >= 0) {
                ByteBuffer output = outputBuffers[result];
                if ((this.d.flags & 2) == 0 && this.d.size > 0) {
                    if (this.d.presentationTimeUs < this.k) {
                        com.skypecam.obscura.e.g.a().b(this.a, "bufferInfo.presentationTimeUs < presentationTimeLast");
                        BufferInfo bufferInfo = this.d;
                        long j = this.k + ((long) this.d.size);
                        this.k = j;
                        bufferInfo.presentationTimeUs = j;
                    }
                    if (this.j) {
                        BufferInfo bi = new BufferInfo();
                        bi.set(this.d.offset, this.d.size, this.d.presentationTimeUs, this.d.flags);
                        this.b.a(new g("audio/mp4a-latm", output, bi));
                    }
                    this.k = this.d.presentationTimeUs;
                }
                this.c.releaseOutputBuffer(result, false);
            }
        }
    }

    private void a(ByteBuffer buffer, boolean endOStream) {
        while (this.i && buffer.remaining() > 0) {
            ByteBuffer[] inputs = this.c.getInputBuffers();
            int result = this.c.dequeueInputBuffer(3000);
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
                this.c.queueInputBuffer(result, 0, size, presentationTime, endOStream ? 4 : 0);
                if (!endOStream) {
                    d();
                }
            }
        }
    }

    public final boolean a(g command, Object data) {
        switch (command) {
            case START:
                try {
                    this.c = MediaCodec.createEncoderByType("audio/mp4a-latm");
                    this.d = new BufferInfo();
                    com.skypecam.obscura.e.g.a().b(this.a, "use codec " + this.c.getName());
                    MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", this.f, this.g);
                    if (this.c.getName().equals("OMX.google.aac.decoder")) {
                        this.c.release();
                        this.c = MediaCodec.createByCodecName("OMX.google.aac.encoder");
                    }
                    createAudioFormat.setInteger("aac-profile", this.e);
                    createAudioFormat.setInteger("bitrate", this.h);
                    this.c.configure(createAudioFormat, null, null, 1);
                    this.c.start();
                    super.a(g.DEQUEUE_BUFFER);
                    this.i = true;
                    return true;
                } catch (IOException e) {
                    com.skypecam.obscura.e.g.a().d(this.a, "startEncoder " + e.getLocalizedMessage());
                    return true;
                }
            case RELEASE:
                com.skypecam.obscura.e.g.a().b(this.a, "stopEncoder recording: " + this.i + " codec: " + (this.c != null));
                if (this.i) {
                    a(ByteBuffer.allocateDirect(0), true);
                    d();
                }
                this.i = false;
                if (this.c != null) {
                    this.c.stop();
                    this.c.release();
                    this.c = null;
                    this.b.a();
                    this.j = false;
                }
                com.skypecam.obscura.e.g.a().b(this.a, "stopEncoder done");
                return true;
            case DEQUEUE_BUFFER:
                d();
                return true;
            case QUEUE_BUFFER:
                a((ByteBuffer) data, false);
                return true;
            default:
                return false;
        }
    }
}
