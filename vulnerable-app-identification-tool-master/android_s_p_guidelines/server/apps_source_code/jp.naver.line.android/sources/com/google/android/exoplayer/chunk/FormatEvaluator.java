package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.linecorp.yuki.sensetime.SenseTimeSlam;
import java.util.List;
import java.util.Random;

public interface FormatEvaluator {

    public final class Evaluation {
        public Format format;
        public int queueSize;
        public int trigger = 1;
    }

    public final class AdaptiveEvaluator implements FormatEvaluator {
        public static final float DEFAULT_BANDWIDTH_FRACTION = 0.75f;
        public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
        public static final int DEFAULT_MAX_INITIAL_BITRATE = 800000;
        public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
        public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final long maxDurationForQualityDecreaseUs;
        private final int maxInitialBitrate;
        private final long minDurationForQualityIncreaseUs;
        private final long minDurationToRetainAfterDiscardUs;

        public final void disable() {
        }

        public final void enable() {
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter) {
            this(bandwidthMeter, DEFAULT_MAX_INITIAL_BITRATE, 10000, 25000, 25000, 0.75f);
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter, int i, int i2, int i3, int i4, float f) {
            this.bandwidthMeter = bandwidthMeter;
            this.maxInitialBitrate = i;
            this.minDurationForQualityIncreaseUs = ((long) i2) * 1000;
            this.maxDurationForQualityDecreaseUs = ((long) i3) * 1000;
            this.minDurationToRetainAfterDiscardUs = ((long) i4) * 1000;
            this.bandwidthFraction = f;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            long j2;
            int i = 1;
            if (list.isEmpty()) {
                j2 = 0;
            } else {
                j2 = ((MediaChunk) list.get(list.size() - 1)).endTimeUs - j;
            }
            Format format = evaluation.format;
            Format determineIdealFormat = determineIdealFormat(formatArr, this.bandwidthMeter.getBitrateEstimate());
            Object obj = null;
            Object obj2 = (determineIdealFormat == null || format == null || determineIdealFormat.bitrate <= format.bitrate) ? null : 1;
            if (!(determineIdealFormat == null || format == null || determineIdealFormat.bitrate >= format.bitrate)) {
                obj = 1;
            }
            if (obj2 == null) {
                if (obj != null) {
                    if (format != null) {
                    }
                }
                evaluation.trigger = 3;
                evaluation.format = determineIdealFormat;
            } else if (j2 >= this.minDurationForQualityIncreaseUs) {
                if (j2 >= this.minDurationToRetainAfterDiscardUs) {
                    while (i < list.size()) {
                        MediaChunk mediaChunk = (MediaChunk) list.get(i);
                        if (mediaChunk.startTimeUs - j >= this.minDurationToRetainAfterDiscardUs && mediaChunk.format.bitrate < determineIdealFormat.bitrate && mediaChunk.format.height < determineIdealFormat.height && mediaChunk.format.height < 720 && mediaChunk.format.width < SenseTimeSlam.MAX_PREVIEW_WIDTH_UPPER_S) {
                            evaluation.queueSize = i;
                            break;
                        }
                        i++;
                    }
                }
                if (!(format == null || determineIdealFormat == format)) {
                    evaluation.trigger = 3;
                }
                evaluation.format = determineIdealFormat;
            }
            determineIdealFormat = format;
            evaluation.trigger = 3;
            evaluation.format = determineIdealFormat;
        }

        private Format determineIdealFormat(Format[] formatArr, long j) {
            j = j == -1 ? (long) this.maxInitialBitrate : (long) (((float) j) * this.bandwidthFraction);
            for (Format format : formatArr) {
                if (((long) format.bitrate) <= j) {
                    return format;
                }
            }
            return formatArr[formatArr.length - 1];
        }
    }

    public final class FixedEvaluator implements FormatEvaluator {
        public final void disable() {
        }

        public final void enable() {
        }

        public final void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            evaluation.format = formatArr[0];
        }
    }

    public final class RandomEvaluator implements FormatEvaluator {
        private final Random random;

        public final void disable() {
        }

        public final void enable() {
        }

        public RandomEvaluator() {
            this.random = new Random();
        }

        public RandomEvaluator(int i) {
            this.random = new Random((long) i);
        }

        public final void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            Format format = formatArr[this.random.nextInt(formatArr.length)];
            if (!(evaluation.format == null || evaluation.format.equals(format))) {
                evaluation.trigger = 3;
            }
            evaluation.format = format;
        }
    }

    void disable();

    void enable();

    void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation);
}
