package com.skpcamera.antediluvian;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Process;
import com.facebook.common.logging.FLog;
import com.skype.Defines;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

final class f extends e {
    private k b;
    private AudioRecord c;
    private g d;
    private ByteBuffer[] e;
    private Set<AudioEffect> f = new HashSet();
    private int g;

    f(k sink, g settings) {
        super("AudioRecordComponent");
        this.b = sink;
        this.d = settings;
    }

    static g c() {
        int[] sampleRates = new int[]{44100, 16000, 11025, Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE};
        int sampleRate = sampleRates[0];
        int state = 0;
        AudioRecord.getMinBufferSize(sampleRate, 16, 2);
        int bufferSize = 0;
        int sampleRateIndex = 0;
        while (state == 0 && sampleRateIndex < 4) {
            bufferSize = AudioRecord.getMinBufferSize(sampleRate, 16, 2);
            AudioRecord audioRecord = new AudioRecord(5, sampleRates[sampleRateIndex], 16, 2, bufferSize);
            state = audioRecord.getState();
            audioRecord.release();
            if (state != 0) {
                sampleRate = sampleRates[sampleRateIndex];
                break;
            }
            FLog.w("AudioRecordComponent", "audio format 2 @ " + sampleRates[sampleRateIndex] + " not supported");
            sampleRateIndex++;
        }
        if (state != 0) {
            return new g(sampleRate, bufferSize);
        }
        throw new IllegalStateException("No valid audio record configuration");
    }

    private void d() {
        FLog.i(this.a, "stop audioRecord " + (this.c != null));
        if (this.c != null) {
            a().removeMessages(j.DEQUEUE_BUFFER.ordinal());
            try {
                FLog.i(this.a, "stop audioRecord");
                this.c.stop();
            } catch (IllegalStateException e) {
                FLog.e(this.a, "stop " + e.getLocalizedMessage());
                FLog.i(this.a, "stop audioRecord: done");
            } finally {
                FLog.i(this.a, "stop audioRecord release with effects " + this.f.size());
                this.c.release();
                this.c = null;
                for (AudioEffect effect : this.f) {
                    FLog.i(this.a, "stop audioRecord effect " + effect.getId());
                    effect.release();
                }
            }
        }
        FLog.i(this.a, "stop audioRecord: done");
    }

    private void e() {
        d();
    }

    private void f() {
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
            Handler handler = a();
            if (!(this.c.getRecordingState() != 3 || handler == null || handler.hasMessages(j.DEQUEUE_BUFFER.ordinal()))) {
                b(j.DEQUEUE_BUFFER, null);
            }
            if (this.b != null && !this.b.b(j.QUEUE_BUFFER, buffer)) {
                FLog.w(this.a, "couldn't send to sink");
                d();
            }
        }
    }

    public final boolean a(j command, Object data) {
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
                    b(j.DEQUEUE_BUFFER, null);
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
                e();
                return true;
            case DEQUEUE_BUFFER:
                f();
                return true;
            default:
                return false;
        }
    }
}
