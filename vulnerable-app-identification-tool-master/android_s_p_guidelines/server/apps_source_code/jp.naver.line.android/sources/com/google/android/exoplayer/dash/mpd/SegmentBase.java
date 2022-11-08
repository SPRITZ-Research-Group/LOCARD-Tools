package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.C;
import com.google.android.exoplayer.util.Util;
import java.util.List;

public abstract class SegmentBase {
    final RangedUri initialization;
    final long presentationTimeOffset;
    final long timescale;

    public class SegmentTimelineElement {
        long duration;
        long startTime;

        public SegmentTimelineElement(long j, long j2) {
            this.startTime = j;
            this.duration = j2;
        }
    }

    public abstract class MultiSegmentBase extends SegmentBase {
        final long duration;
        final long periodDurationMs;
        final List<SegmentTimelineElement> segmentTimeline;
        final int startNumber;

        public abstract int getLastSegmentNum();

        public abstract RangedUri getSegmentUrl(Representation representation, int i);

        public MultiSegmentBase(RangedUri rangedUri, long j, long j2, long j3, int i, long j4, List<SegmentTimelineElement> list) {
            super(rangedUri, j, j2);
            this.periodDurationMs = j3;
            this.startNumber = i;
            this.duration = j4;
            this.segmentTimeline = list;
        }

        public int getSegmentNum(long j) {
            int firstSegmentNum = getFirstSegmentNum();
            int lastSegmentNum = getLastSegmentNum();
            if (this.segmentTimeline == null) {
                int i = this.startNumber + ((int) (j / ((this.duration * C.MICROS_PER_SECOND) / this.timescale)));
                if (i < firstSegmentNum) {
                    return firstSegmentNum;
                }
                return (lastSegmentNum == -1 || i <= lastSegmentNum) ? i : lastSegmentNum;
            } else {
                int i2 = lastSegmentNum;
                lastSegmentNum = firstSegmentNum;
                while (lastSegmentNum <= i2) {
                    int i3 = (lastSegmentNum + i2) / 2;
                    long segmentTimeUs = getSegmentTimeUs(i3);
                    if (segmentTimeUs < j) {
                        lastSegmentNum = i3 + 1;
                    } else if (segmentTimeUs <= j) {
                        return i3;
                    } else {
                        i2 = i3 - 1;
                    }
                }
                return lastSegmentNum == firstSegmentNum ? lastSegmentNum : i2;
            }
        }

        public final long getSegmentDurationUs(int i) {
            if (this.segmentTimeline != null) {
                return (((SegmentTimelineElement) this.segmentTimeline.get(i - this.startNumber)).duration * C.MICROS_PER_SECOND) / this.timescale;
            }
            return i == getLastSegmentNum() ? (this.periodDurationMs * 1000) - getSegmentTimeUs(i) : (this.duration * C.MICROS_PER_SECOND) / this.timescale;
        }

        public final long getSegmentTimeUs(int i) {
            long j;
            if (this.segmentTimeline != null) {
                j = ((SegmentTimelineElement) this.segmentTimeline.get(i - this.startNumber)).startTime - this.presentationTimeOffset;
            } else {
                j = ((long) (i - this.startNumber)) * this.duration;
            }
            return Util.scaleLargeTimestamp(j, C.MICROS_PER_SECOND, this.timescale);
        }

        public int getFirstSegmentNum() {
            return this.startNumber;
        }

        public boolean isExplicit() {
            return this.segmentTimeline != null;
        }
    }

    public class SingleSegmentBase extends SegmentBase {
        final long indexLength;
        final long indexStart;
        public final String uri;

        public SingleSegmentBase(RangedUri rangedUri, long j, long j2, String str, long j3, long j4) {
            super(rangedUri, j, j2);
            this.uri = str;
            this.indexStart = j3;
            this.indexLength = j4;
        }

        public SingleSegmentBase(String str) {
            this(null, 1, 0, str, 0, -1);
        }

        public RangedUri getIndex() {
            return this.indexLength <= 0 ? null : new RangedUri(this.uri, null, this.indexStart, this.indexLength);
        }
    }

    public class SegmentList extends MultiSegmentBase {
        final List<RangedUri> mediaSegments;

        public boolean isExplicit() {
            return true;
        }

        public SegmentList(RangedUri rangedUri, long j, long j2, long j3, int i, long j4, List<SegmentTimelineElement> list, List<RangedUri> list2) {
            super(rangedUri, j, j2, j3, i, j4, list);
            this.mediaSegments = list2;
        }

        public RangedUri getSegmentUrl(Representation representation, int i) {
            return (RangedUri) this.mediaSegments.get(i - this.startNumber);
        }

        public int getLastSegmentNum() {
            return (this.startNumber + this.mediaSegments.size()) - 1;
        }
    }

    public class SegmentTemplate extends MultiSegmentBase {
        private final String baseUrl;
        final UrlTemplate initializationTemplate;
        final UrlTemplate mediaTemplate;

        public SegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, int i, long j4, List<SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2, String str) {
            super(rangedUri, j, j2, j3, i, j4, list);
            this.initializationTemplate = urlTemplate;
            this.mediaTemplate = urlTemplate2;
            this.baseUrl = str;
        }

        public RangedUri getInitialization(Representation representation) {
            if (this.initializationTemplate == null) {
                return super.getInitialization(representation);
            }
            return new RangedUri(this.baseUrl, this.initializationTemplate.buildUri(representation.format.id, 0, representation.format.bitrate, 0), 0, -1);
        }

        public RangedUri getSegmentUrl(Representation representation, int i) {
            long j;
            Representation representation2 = representation;
            if (this.segmentTimeline != null) {
                j = ((SegmentTimelineElement) r0.segmentTimeline.get(i - r0.startNumber)).startTime;
            } else {
                j = ((long) (i - r0.startNumber)) * r0.duration;
            }
            long j2 = j;
            return new RangedUri(r0.baseUrl, r0.mediaTemplate.buildUri(representation2.format.id, i, representation2.format.bitrate, j2), 0, -1);
        }

        public int getLastSegmentNum() {
            if (this.segmentTimeline != null) {
                return (this.segmentTimeline.size() + this.startNumber) - 1;
            }
            if (this.periodDurationMs == -1) {
                return -1;
            }
            return (this.startNumber + ((int) Util.ceilDivide(this.periodDurationMs, (this.duration * 1000) / this.timescale))) - 1;
        }
    }

    public SegmentBase(RangedUri rangedUri, long j, long j2) {
        this.initialization = rangedUri;
        this.timescale = j;
        this.presentationTimeOffset = j2;
    }

    public RangedUri getInitialization(Representation representation) {
        return this.initialization;
    }

    public long getPresentationTimeOffsetUs() {
        return Util.scaleLargeTimestamp(this.presentationTimeOffset, C.MICROS_PER_SECOND, this.timescale);
    }
}
