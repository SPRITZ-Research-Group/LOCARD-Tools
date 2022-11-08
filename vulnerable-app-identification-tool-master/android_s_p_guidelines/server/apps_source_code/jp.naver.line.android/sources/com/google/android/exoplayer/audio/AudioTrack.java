package com.google.android.exoplayer.audio;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.MediaFormat;
import android.os.ConditionVariable;
import android.util.Log;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class AudioTrack {
    private static final int BUFFER_MULTIPLICATION_FACTOR = 4;
    public static final long CURRENT_POSITION_NOT_SET = Long.MIN_VALUE;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    private static final long MAX_BUFFER_DURATION_US = 750000;
    private static final long MAX_LATENCY_US = 5000000;
    private static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    private static final long MIN_BUFFER_DURATION_US = 250000;
    private static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    private static final int MIN_TIMESTAMP_SAMPLE_INTERVAL_US = 500000;
    public static final int RESULT_BUFFER_CONSUMED = 2;
    public static final int RESULT_POSITION_DISCONTINUITY = 1;
    public static final int SESSION_ID_NOT_SET = 0;
    private static final int START_IN_SYNC = 1;
    private static final int START_NEED_SYNC = 2;
    private static final int START_NOT_SET = 0;
    private static final String TAG = "AudioTrack";
    private static final int UNKNOWN_AC3_BITRATE = 0;
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private int ac3Bitrate;
    private boolean audioTimestampSet;
    private android.media.AudioTrack audioTrack;
    private final AudioTrackUtil audioTrackUtil;
    private int bufferSize;
    private int channelConfig;
    private int encoding;
    private int frameSize;
    private Method getLatencyMethod;
    private boolean isAc3;
    private android.media.AudioTrack keepSessionIdAudioTrack;
    private long lastPlayheadSampleTimeUs;
    private long lastTimestampSampleTimeUs;
    private long latencyUs;
    private int minBufferSize;
    private int nextPlayheadOffsetIndex;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private final ConditionVariable releasingConditionVariable = new ConditionVariable(true);
    private long resumeSystemTimeUs;
    private int sampleRate;
    private long smoothedPlayheadOffsetUs;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private long submittedBytes;
    private byte[] temporaryBuffer;
    private int temporaryBufferOffset;
    private int temporaryBufferSize;
    private float volume;

    class AudioTrackUtil {
        protected android.media.AudioTrack audioTrack;
        private boolean isPassthrough;
        private long lastRawPlaybackHeadPosition;
        private long passthroughWorkaroundPauseOffset;
        private long rawPlaybackHeadWrapCount;
        private int sampleRate;

        public boolean updateTimestamp() {
            return false;
        }

        private AudioTrackUtil() {
        }

        /* synthetic */ AudioTrackUtil(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            this.audioTrack = audioTrack;
            this.isPassthrough = z;
            this.lastRawPlaybackHeadPosition = 0;
            this.rawPlaybackHeadWrapCount = 0;
            this.passthroughWorkaroundPauseOffset = 0;
            if (audioTrack != null) {
                this.sampleRate = audioTrack.getSampleRate();
            }
        }

        public boolean overrideHasPendingData() {
            return Util.SDK_INT <= 22 && this.isPassthrough && this.audioTrack.getPlayState() == 2 && this.audioTrack.getPlaybackHeadPosition() == 0;
        }

        public long getPlaybackHeadPosition() {
            long playbackHeadPosition = ((long) this.audioTrack.getPlaybackHeadPosition()) & 4294967295L;
            if (Util.SDK_INT <= 22 && this.isPassthrough) {
                if (this.audioTrack.getPlayState() == 1) {
                    this.lastRawPlaybackHeadPosition = playbackHeadPosition;
                } else if (this.audioTrack.getPlayState() == 2 && playbackHeadPosition == 0) {
                    this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                }
                playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
            }
            if (this.lastRawPlaybackHeadPosition > playbackHeadPosition) {
                this.rawPlaybackHeadWrapCount++;
            }
            this.lastRawPlaybackHeadPosition = playbackHeadPosition;
            return playbackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
        }

        public long getPlaybackHeadPositionUs() {
            return (getPlaybackHeadPosition() * C.MICROS_PER_SECOND) / ((long) this.sampleRate);
        }

        public long getTimestampNanoTime() {
            throw new UnsupportedOperationException();
        }

        public long getTimestampFramePosition() {
            throw new UnsupportedOperationException();
        }
    }

    public final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int i, int i2, int i3, int i4) {
            StringBuilder stringBuilder = new StringBuilder("AudioTrack init failed: ");
            stringBuilder.append(i);
            stringBuilder.append(", Config(");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(i3);
            stringBuilder.append(", ");
            stringBuilder.append(i4);
            stringBuilder.append(")");
            super(stringBuilder.toString());
            this.audioTrackState = i;
        }
    }

    public final class InvalidAudioTrackTimestampException extends RuntimeException {
        public InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int i) {
            super("AudioTrack write failed: ".concat(String.valueOf(i)));
            this.errorCode = i;
        }
    }

    @TargetApi(19)
    class AudioTrackUtilV19 extends AudioTrackUtil {
        private final AudioTimestamp audioTimestamp = new AudioTimestamp();
        private long lastRawTimestampFramePosition;
        private long lastTimestampFramePosition;
        private long rawTimestampFramePositionWrapCount;

        public AudioTrackUtilV19() {
            super();
        }

        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            super.reconfigure(audioTrack, z);
            this.rawTimestampFramePositionWrapCount = 0;
            this.lastRawTimestampFramePosition = 0;
            this.lastTimestampFramePosition = 0;
        }

        public boolean updateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastRawTimestampFramePosition > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastRawTimestampFramePosition = j;
                this.lastTimestampFramePosition = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }

        public long getTimestampNanoTime() {
            return this.audioTimestamp.nanoTime;
        }

        public long getTimestampFramePosition() {
            return this.lastTimestampFramePosition;
        }
    }

    public AudioTrack() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.audio.AudioTrack.<init>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = this;
        r3.<init>();
        r0 = new android.os.ConditionVariable;
        r1 = 1;
        r0.<init>(r1);
        r3.releasingConditionVariable = r0;
        r0 = com.google.android.exoplayer.util.Util.SDK_INT;
        r1 = 0;
        r2 = 18;
        if (r0 < r2) goto L_0x001e;
    L_0x0012:
        r0 = android.media.AudioTrack.class;	 Catch:{ NoSuchMethodException -> 0x001d }
        r2 = "getLatency";	 Catch:{ NoSuchMethodException -> 0x001d }
        r0 = r0.getMethod(r2, r1);	 Catch:{ NoSuchMethodException -> 0x001d }
        r3.getLatencyMethod = r0;	 Catch:{ NoSuchMethodException -> 0x001d }
        goto L_0x001e;
    L_0x001e:
        r0 = com.google.android.exoplayer.util.Util.SDK_INT;
        r2 = 19;
        if (r0 < r2) goto L_0x002c;
    L_0x0024:
        r0 = new com.google.android.exoplayer.audio.AudioTrack$AudioTrackUtilV19;
        r0.<init>();
        r3.audioTrackUtil = r0;
        goto L_0x0033;
    L_0x002c:
        r0 = new com.google.android.exoplayer.audio.AudioTrack$AudioTrackUtil;
        r0.<init>(r1);
        r3.audioTrackUtil = r0;
    L_0x0033:
        r0 = 10;
        r0 = new long[r0];
        r3.playheadOffsets = r0;
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3.volume = r0;
        r0 = 0;
        r3.startMediaTimeState = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.audio.AudioTrack.<init>():void");
    }

    public final boolean isInitialized() {
        return this.audioTrack != null;
    }

    public final long getCurrentPositionUs(boolean z) {
        if (!hasCurrentPositionUs()) {
            return Long.MIN_VALUE;
        }
        if (this.audioTrack.getPlayState() == 3) {
            maybeSampleSyncParams();
        }
        long nanoTime = System.nanoTime() / 1000;
        if (this.audioTimestampSet) {
            nanoTime = framesToDurationUs(this.audioTrackUtil.getTimestampFramePosition() + durationUsToFrames(nanoTime - (this.audioTrackUtil.getTimestampNanoTime() / 1000))) + this.startMediaTimeUs;
        } else {
            if (this.playheadOffsetCount == 0) {
                nanoTime = this.audioTrackUtil.getPlaybackHeadPositionUs() + this.startMediaTimeUs;
            } else {
                nanoTime = (nanoTime + this.smoothedPlayheadOffsetUs) + this.startMediaTimeUs;
            }
            if (!z) {
                nanoTime -= this.latencyUs;
            }
        }
        return nanoTime;
    }

    public final int initialize() throws InitializationException {
        return initialize(0);
    }

    public final int initialize(int i) throws InitializationException {
        this.releasingConditionVariable.block();
        if (i == 0) {
            this.audioTrack = new android.media.AudioTrack(3, this.sampleRate, this.channelConfig, this.encoding, this.bufferSize, 1);
        } else {
            this.audioTrack = new android.media.AudioTrack(3, this.sampleRate, this.channelConfig, this.encoding, this.bufferSize, 1, i);
        }
        checkAudioTrackInitialized();
        int audioSessionId = this.audioTrack.getAudioSessionId();
        if (enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            if (!(this.keepSessionIdAudioTrack == null || audioSessionId == this.keepSessionIdAudioTrack.getAudioSessionId())) {
                releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = new android.media.AudioTrack(3, 4000, 4, 2, 2, 0, audioSessionId);
            }
        }
        this.audioTrackUtil.reconfigure(this.audioTrack, this.isAc3);
        setVolume(this.volume);
        return audioSessionId;
    }

    public final void reconfigure(MediaFormat mediaFormat) {
        reconfigure(mediaFormat, 0);
    }

    public final void reconfigure(MediaFormat mediaFormat, int i) {
        int i2;
        int integer = mediaFormat.getInteger("channel-count");
        if (integer == 6) {
            i2 = 252;
        } else if (integer != 8) {
            switch (integer) {
                case 1:
                    i2 = 4;
                    break;
                case 2:
                    i2 = 12;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported channel count: ".concat(String.valueOf(integer)));
            }
        } else {
            i2 = 1020;
        }
        int integer2 = mediaFormat.getInteger("sample-rate");
        int encodingForMimeType = MimeTypes.getEncodingForMimeType(mediaFormat.getString("mime"));
        boolean z = true;
        boolean z2 = encodingForMimeType == 5 || encodingForMimeType == 6;
        if (!isInitialized() || this.sampleRate != integer2 || this.channelConfig != i2 || this.isAc3 || z2) {
            reset();
            this.encoding = encodingForMimeType;
            this.sampleRate = integer2;
            this.channelConfig = i2;
            this.isAc3 = z2;
            this.ac3Bitrate = 0;
            this.frameSize = integer * 2;
            this.minBufferSize = android.media.AudioTrack.getMinBufferSize(integer2, i2, encodingForMimeType);
            if (this.minBufferSize == -2) {
                z = false;
            }
            Assertions.checkState(z);
            if (i == 0) {
                i = this.minBufferSize * 4;
                encodingForMimeType = ((int) durationUsToFrames(MIN_BUFFER_DURATION_US)) * this.frameSize;
                integer = (int) Math.max((long) this.minBufferSize, durationUsToFrames(MAX_BUFFER_DURATION_US) * ((long) this.frameSize));
                if (i < encodingForMimeType) {
                    i = encodingForMimeType;
                } else if (i > integer) {
                    i = integer;
                }
            }
            this.bufferSize = i;
        }
    }

    public final void play() {
        if (isInitialized()) {
            this.resumeSystemTimeUs = System.nanoTime() / 1000;
            this.audioTrack.play();
        }
    }

    public final void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    public final int handleBuffer(ByteBuffer byteBuffer, int i, int i2, long j) throws WriteException {
        if (i2 == 0) {
            return 2;
        }
        int i3 = 1;
        int i4 = 0;
        if (Util.SDK_INT <= 22 && this.isAc3) {
            if (this.audioTrack.getPlayState() == 2) {
                return 0;
            }
            if (this.audioTrack.getPlayState() == 1 && this.audioTrackUtil.getPlaybackHeadPosition() != 0) {
                return 0;
            }
        }
        if (this.temporaryBufferSize == 0) {
            if (this.isAc3 && this.ac3Bitrate == 0) {
                this.ac3Bitrate = Ac3Util.getBitrate(i2, this.sampleRate);
            }
            j -= framesToDurationUs(bytesToFrames((long) i2));
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0, j);
                this.startMediaTimeState = 1;
            } else {
                long framesToDurationUs = this.startMediaTimeUs + framesToDurationUs(bytesToFrames(this.submittedBytes));
                if (this.startMediaTimeState == 1 && Math.abs(framesToDurationUs - j) > 200000) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder("Discontinuity detected [expected ");
                    stringBuilder.append(framesToDurationUs);
                    stringBuilder.append(", got ");
                    stringBuilder.append(j);
                    stringBuilder.append("]");
                    Log.e(str, stringBuilder.toString());
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == 2) {
                    this.startMediaTimeUs += j - framesToDurationUs;
                    this.startMediaTimeState = 1;
                    if (this.temporaryBufferSize == 0) {
                        this.temporaryBufferSize = i2;
                        byteBuffer.position(i);
                        if (Util.SDK_INT < 21) {
                            if (this.temporaryBuffer == null || this.temporaryBuffer.length < i2) {
                                this.temporaryBuffer = new byte[i2];
                            }
                            byteBuffer.get(this.temporaryBuffer, 0, i2);
                            this.temporaryBufferOffset = 0;
                        }
                    }
                    if (Util.SDK_INT >= 21) {
                        i = this.bufferSize - ((int) (this.submittedBytes - (this.audioTrackUtil.getPlaybackHeadPosition() * ((long) this.frameSize))));
                        if (i > 0) {
                            i4 = this.audioTrack.write(this.temporaryBuffer, this.temporaryBufferOffset, Math.min(this.temporaryBufferSize, i));
                            if (i4 >= 0) {
                                this.temporaryBufferOffset += i4;
                            }
                        }
                    } else {
                        i4 = writeNonBlockingV21(this.audioTrack, byteBuffer, this.temporaryBufferSize);
                    }
                    if (i4 < 0) {
                        this.temporaryBufferSize -= i4;
                        this.submittedBytes += (long) i4;
                        if (this.temporaryBufferSize == 0) {
                            i3 |= 2;
                        }
                        return i3;
                    }
                    throw new WriteException(i4);
                }
            }
        }
        i3 = 0;
        if (this.temporaryBufferSize == 0) {
            this.temporaryBufferSize = i2;
            byteBuffer.position(i);
            if (Util.SDK_INT < 21) {
                this.temporaryBuffer = new byte[i2];
                byteBuffer.get(this.temporaryBuffer, 0, i2);
                this.temporaryBufferOffset = 0;
            }
        }
        if (Util.SDK_INT >= 21) {
            i4 = writeNonBlockingV21(this.audioTrack, byteBuffer, this.temporaryBufferSize);
        } else {
            i = this.bufferSize - ((int) (this.submittedBytes - (this.audioTrackUtil.getPlaybackHeadPosition() * ((long) this.frameSize))));
            if (i > 0) {
                i4 = this.audioTrack.write(this.temporaryBuffer, this.temporaryBufferOffset, Math.min(this.temporaryBufferSize, i));
                if (i4 >= 0) {
                    this.temporaryBufferOffset += i4;
                }
            }
        }
        if (i4 < 0) {
            throw new WriteException(i4);
        }
        this.temporaryBufferSize -= i4;
        this.submittedBytes += (long) i4;
        if (this.temporaryBufferSize == 0) {
            i3 |= 2;
        }
        return i3;
    }

    @TargetApi(21)
    private static int writeNonBlockingV21(android.media.AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer, i, 1);
    }

    public final boolean hasPendingData() {
        return isInitialized() && (bytesToFrames(this.submittedBytes) > this.audioTrackUtil.getPlaybackHeadPosition() || this.audioTrackUtil.overrideHasPendingData());
    }

    public final boolean hasEnoughDataToBeginPlayback() {
        return this.submittedBytes > ((long) ((this.minBufferSize * 3) / 2));
    }

    public final void setVolume(float f) {
        this.volume = f;
        if (isInitialized()) {
            if (Util.SDK_INT >= 21) {
                setVolumeV21(this.audioTrack, f);
                return;
            }
            setVolumeV3(this.audioTrack, f);
        }
    }

    @TargetApi(21)
    private static void setVolumeV21(android.media.AudioTrack audioTrack, float f) {
        audioTrack.setVolume(f);
    }

    private static void setVolumeV3(android.media.AudioTrack audioTrack, float f) {
        audioTrack.setStereoVolume(f, f);
    }

    public final void pause() {
        if (isInitialized()) {
            resetSyncParams();
            this.audioTrack.pause();
        }
    }

    public final void reset() {
        if (isInitialized()) {
            this.submittedBytes = 0;
            this.temporaryBufferSize = 0;
            this.startMediaTimeState = 0;
            this.latencyUs = 0;
            resetSyncParams();
            if (this.audioTrack.getPlayState() == 3) {
                this.audioTrack.pause();
            }
            final android.media.AudioTrack audioTrack = this.audioTrack;
            this.audioTrack = null;
            this.audioTrackUtil.reconfigure(null, false);
            this.releasingConditionVariable.close();
            new Thread() {
                public void run() {
                    try {
                        audioTrack.release();
                    } finally {
                        AudioTrack.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
    }

    public final void release() {
        reset();
        releaseKeepSessionIdAudioTrack();
    }

    private void releaseKeepSessionIdAudioTrack() {
        if (this.keepSessionIdAudioTrack != null) {
            final android.media.AudioTrack audioTrack = this.keepSessionIdAudioTrack;
            this.keepSessionIdAudioTrack = null;
            new Thread() {
                public void run() {
                    audioTrack.release();
                }
            }.start();
        }
    }

    private boolean hasCurrentPositionUs() {
        return isInitialized() && this.startMediaTimeState != 0;
    }

    private void maybeSampleSyncParams() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.audio.AudioTrack.maybeSampleSyncParams():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r17 = this;
        r0 = r17;
        r1 = r0.audioTrackUtil;
        r1 = r1.getPlaybackHeadPositionUs();
        r3 = 0;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 != 0) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r5 = java.lang.System.nanoTime();
        r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 / r7;
        r9 = r0.lastPlayheadSampleTimeUs;
        r9 = r5 - r9;
        r11 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r13 = 0;
        r14 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1));
        if (r14 < 0) goto L_0x0057;
    L_0x0021:
        r9 = r0.playheadOffsets;
        r10 = r0.nextPlayheadOffsetIndex;
        r11 = r1 - r5;
        r9[r10] = r11;
        r9 = r0.nextPlayheadOffsetIndex;
        r9 = r9 + 1;
        r10 = 10;
        r9 = r9 % r10;
        r0.nextPlayheadOffsetIndex = r9;
        r9 = r0.playheadOffsetCount;
        if (r9 >= r10) goto L_0x003c;
    L_0x0036:
        r9 = r0.playheadOffsetCount;
        r9 = r9 + 1;
        r0.playheadOffsetCount = r9;
    L_0x003c:
        r0.lastPlayheadSampleTimeUs = r5;
        r0.smoothedPlayheadOffsetUs = r3;
        r9 = 0;
    L_0x0041:
        r10 = r0.playheadOffsetCount;
        if (r9 >= r10) goto L_0x0057;
    L_0x0045:
        r10 = r0.smoothedPlayheadOffsetUs;
        r12 = r0.playheadOffsets;
        r14 = r12[r9];
        r12 = r0.playheadOffsetCount;
        r3 = (long) r12;
        r14 = r14 / r3;
        r10 = r10 + r14;
        r0.smoothedPlayheadOffsetUs = r10;
        r9 = r9 + 1;
        r3 = 0;
        goto L_0x0041;
    L_0x0057:
        r3 = r0.isAc3;
        if (r3 != 0) goto L_0x0167;
    L_0x005b:
        r3 = r0.lastTimestampSampleTimeUs;
        r3 = r5 - r3;
        r9 = 500000; // 0x7a120 float:7.00649E-40 double:2.47033E-318;
        r11 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1));
        if (r11 < 0) goto L_0x0167;
    L_0x0066:
        r3 = r0.audioTrackUtil;
        r3 = r3.updateTimestamp();
        r0.audioTimestampSet = r3;
        r3 = r0.audioTimestampSet;
        r9 = 5000000; // 0x4c4b40 float:7.006492E-39 double:2.470328E-317;
        if (r3 == 0) goto L_0x0114;
    L_0x0075:
        r3 = r0.audioTrackUtil;
        r3 = r3.getTimestampNanoTime();
        r3 = r3 / r7;
        r11 = r0.audioTrackUtil;
        r11 = r11.getTimestampFramePosition();
        r14 = r0.resumeSystemTimeUs;
        r16 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1));
        if (r16 >= 0) goto L_0x008c;
    L_0x0088:
        r0.audioTimestampSet = r13;
        goto L_0x0114;
    L_0x008c:
        r14 = 0;
        r14 = r3 - r5;
        r14 = java.lang.Math.abs(r14);
        r16 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1));
        if (r16 <= 0) goto L_0x00cf;
    L_0x0097:
        r14 = new java.lang.StringBuilder;
        r15 = "Spurious audio timestamp (system clock mismatch): ";
        r14.<init>(r15);
        r14.append(r11);
        r11 = ", ";
        r14.append(r11);
        r14.append(r3);
        r3 = ", ";
        r14.append(r3);
        r14.append(r5);
        r3 = ", ";
        r14.append(r3);
        r14.append(r1);
        r1 = r14.toString();
        r2 = failOnSpuriousAudioTimestamp;
        if (r2 != 0) goto L_0x00c9;
    L_0x00c1:
        r2 = "AudioTrack";
        android.util.Log.w(r2, r1);
        r0.audioTimestampSet = r13;
        goto L_0x0114;
    L_0x00c9:
        r2 = new com.google.android.exoplayer.audio.AudioTrack$InvalidAudioTrackTimestampException;
        r2.<init>(r1);
        throw r2;
    L_0x00cf:
        r14 = r0.framesToDurationUs(r11);
        r14 = r14 - r1;
        r14 = java.lang.Math.abs(r14);
        r16 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1));
        if (r16 <= 0) goto L_0x0114;
    L_0x00dc:
        r14 = new java.lang.StringBuilder;
        r15 = "Spurious audio timestamp (frame position mismatch): ";
        r14.<init>(r15);
        r14.append(r11);
        r11 = ", ";
        r14.append(r11);
        r14.append(r3);
        r3 = ", ";
        r14.append(r3);
        r14.append(r5);
        r3 = ", ";
        r14.append(r3);
        r14.append(r1);
        r1 = r14.toString();
        r2 = failOnSpuriousAudioTimestamp;
        if (r2 != 0) goto L_0x010e;
    L_0x0106:
        r2 = "AudioTrack";
        android.util.Log.w(r2, r1);
        r0.audioTimestampSet = r13;
        goto L_0x0114;
    L_0x010e:
        r2 = new com.google.android.exoplayer.audio.AudioTrack$InvalidAudioTrackTimestampException;
        r2.<init>(r1);
        throw r2;
    L_0x0114:
        r1 = r0.getLatencyMethod;
        if (r1 == 0) goto L_0x0165;
    L_0x0118:
        r1 = 0;
        r2 = r0.getLatencyMethod;	 Catch:{ Exception -> 0x0163 }
        r3 = r0.audioTrack;	 Catch:{ Exception -> 0x0163 }
        r2 = r2.invoke(r3, r1);	 Catch:{ Exception -> 0x0163 }
        r2 = (java.lang.Integer) r2;	 Catch:{ Exception -> 0x0163 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0163 }
        r2 = (long) r2;	 Catch:{ Exception -> 0x0163 }
        r2 = r2 * r7;	 Catch:{ Exception -> 0x0163 }
        r4 = r0.bufferSize;	 Catch:{ Exception -> 0x0163 }
        r7 = (long) r4;	 Catch:{ Exception -> 0x0163 }
        r7 = r0.bytesToFrames(r7);	 Catch:{ Exception -> 0x0163 }
        r7 = r0.framesToDurationUs(r7);	 Catch:{ Exception -> 0x0163 }
        r4 = 0;	 Catch:{ Exception -> 0x0163 }
        r2 = r2 - r7;	 Catch:{ Exception -> 0x0163 }
        r0.latencyUs = r2;	 Catch:{ Exception -> 0x0163 }
        r2 = r0.latencyUs;	 Catch:{ Exception -> 0x0163 }
        r7 = 0;	 Catch:{ Exception -> 0x0163 }
        r2 = java.lang.Math.max(r2, r7);	 Catch:{ Exception -> 0x0163 }
        r0.latencyUs = r2;	 Catch:{ Exception -> 0x0163 }
        r2 = r0.latencyUs;	 Catch:{ Exception -> 0x0163 }
        r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1));	 Catch:{ Exception -> 0x0163 }
        if (r4 <= 0) goto L_0x0165;	 Catch:{ Exception -> 0x0163 }
    L_0x0149:
        r2 = "AudioTrack";	 Catch:{ Exception -> 0x0163 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0163 }
        r4 = "Ignoring impossibly large audio latency: ";	 Catch:{ Exception -> 0x0163 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0163 }
        r7 = r0.latencyUs;	 Catch:{ Exception -> 0x0163 }
        r3.append(r7);	 Catch:{ Exception -> 0x0163 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0163 }
        android.util.Log.w(r2, r3);	 Catch:{ Exception -> 0x0163 }
        r2 = 0;	 Catch:{ Exception -> 0x0163 }
        r0.latencyUs = r2;	 Catch:{ Exception -> 0x0163 }
        goto L_0x0165;
    L_0x0163:
        r0.getLatencyMethod = r1;
    L_0x0165:
        r0.lastTimestampSampleTimeUs = r5;
    L_0x0167:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.audio.AudioTrack.maybeSampleSyncParams():void");
    }

    private void checkAudioTrackInitialized() throws InitializationException {
        int state = this.audioTrack.getState();
        if (state != 1) {
            try {
                this.audioTrack.release();
                this.audioTrack = null;
                throw new InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
            } catch (Throwable th) {
                this.audioTrack = null;
            }
        }
    }

    private long bytesToFrames(long j) {
        if (this.isAc3) {
            return this.ac3Bitrate == 0 ? 0 : ((j * 8) * ((long) this.sampleRate)) / ((long) (this.ac3Bitrate * 1000));
        } else {
            return j / ((long) this.frameSize);
        }
    }

    private long framesToDurationUs(long j) {
        return (j * C.MICROS_PER_SECOND) / ((long) this.sampleRate);
    }

    private long durationUsToFrames(long j) {
        return (j * ((long) this.sampleRate)) / C.MICROS_PER_SECOND;
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0;
        this.audioTimestampSet = false;
        this.lastTimestampSampleTimeUs = 0;
    }
}
