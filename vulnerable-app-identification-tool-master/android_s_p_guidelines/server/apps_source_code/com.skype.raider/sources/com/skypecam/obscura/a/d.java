package com.skypecam.obscura.a;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Process;
import com.skype.Defines;
import com.skypecam.obscura.e.g;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public final class d extends c {
    private h b;
    private AudioRecord c;
    private e d;
    private ByteBuffer[] e;
    private Set<AudioEffect> f = new HashSet();
    private int g;

    public final /* bridge */ /* synthetic */ boolean a(g gVar) {
        return super.a(gVar);
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ boolean b(g gVar, Object obj) {
        return super.b(gVar, obj);
    }

    public d(h sink, e settings) {
        super("AudioRecordComponent");
        this.b = sink;
        this.d = settings;
    }

    public static e a() {
        int[] sampleRates = new int[]{44100, 16000, 11025, Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE};
        int sampleRate = sampleRates[0];
        int state = 0;
        int bufferSize = AudioRecord.getMinBufferSize(sampleRate, 16, 2);
        int sampleRateIndex = 0;
        while (state == 0 && sampleRateIndex < 4) {
            bufferSize = AudioRecord.getMinBufferSize(sampleRate, 16, 2);
            if (bufferSize > 0) {
                AudioRecord audioRecord = new AudioRecord(5, sampleRates[sampleRateIndex], 16, 2, bufferSize);
                state = audioRecord.getState();
                audioRecord.release();
            } else {
                g.a().c("AudioRecordComponent", "illegal buffer size " + bufferSize);
                sampleRateIndex++;
            }
            if (state != 0) {
                sampleRate = sampleRates[sampleRateIndex];
                break;
            }
            g.a().c("AudioRecordComponent", "audio format 2 @ " + sampleRates[sampleRateIndex] + " not supported");
            sampleRateIndex++;
        }
        if (state != 0) {
            return new e(sampleRate, bufferSize);
        }
        throw new IllegalStateException("No valid audio record configuration");
    }

    public final void d() {
        super.a(g.START);
    }

    private void e() {
        g.a().b(this.a, "stop audioRecord " + (this.c != null));
        if (this.c != null) {
            c().removeMessages(g.DEQUEUE_BUFFER.ordinal());
            try {
                g.a().b(this.a, "stop audioRecord");
                this.c.stop();
            } catch (IllegalStateException e) {
                g.a().d(this.a, "stop " + e.getLocalizedMessage());
                g.a().b(this.a, "stop audioRecord: done");
            } finally {
                g.a().b(this.a, "stop audioRecord release with effects " + this.f.size());
                this.c.release();
                this.c = null;
                for (AudioEffect effect : this.f) {
                    g.a().b(this.a, "stop audioRecord effect " + effect.getId());
                    effect.release();
                }
            }
        }
        g.a().b(this.a, "stop audioRecord: done");
    }

    private void f() {
        e();
    }

    private void g() {
        if (this.c != null && this.c.getRecordingState() == 3) {
            if (this.g >= this.e.length) {
                this.g = 0;
            }
            ByteBuffer[] byteBufferArr = this.e;
            int i = this.g;
            this.g = i + 1;
            ByteBuffer buffer = byteBufferArr[i];
            int read = this.c.read(buffer, buffer.capacity());
            buffer.position(0);
            buffer.limit(read);
            Handler handler = c();
            if (!(this.c.getRecordingState() != 3 || handler == null || handler.hasMessages(g.DEQUEUE_BUFFER.ordinal()))) {
                super.a(g.DEQUEUE_BUFFER);
            }
            if (this.b != null && !this.b.b(g.QUEUE_BUFFER, buffer)) {
                g.a().c(this.a, "couldn't send to sink");
                e();
            }
        }
    }

    public final boolean a(g command, Object data) {
        int i = 0;
        switch (command) {
            case START:
                if (this.c == null) {
                    int g = this.d.g();
                    int b = this.d.b();
                    int c = this.d.c();
                    int d = this.d.d();
                    int a = this.d.a();
                    this.e = new ByteBuffer[4];
                    while (i < 4) {
                        this.e[i] = ByteBuffer.allocateDirect(a);
                        i++;
                    }
                    this.c = new AudioRecord(g, b, c, d, a);
                    this.c.setNotificationMarkerPosition(a);
                    this.c.startRecording();
                    super.a(g.DEQUEUE_BUFFER);
                    if (VERSION.SDK_INT >= 16) {
                        if (this.d.f() && AcousticEchoCanceler.isAvailable()) {
                            AcousticEchoCanceler create = AcousticEchoCanceler.create(this.c.getAudioSessionId());
                            if (!create.getEnabled()) {
                                create.setEnabled(true);
                            }
                            this.f.add(create);
                        }
                        if (this.d.e() && NoiseSuppressor.isAvailable()) {
                            NoiseSuppressor create2 = NoiseSuppressor.create(this.c.getAudioSessionId());
                            if (!create2.getEnabled()) {
                                create2.setEnabled(true);
                            }
                            this.f.add(create2);
                        }
                    }
                    Process.setThreadPriority(-19);
                }
                return true;
            case RELEASE:
                f();
                return true;
            case DEQUEUE_BUFFER:
                g();
                return true;
            default:
                return false;
        }
    }
}
