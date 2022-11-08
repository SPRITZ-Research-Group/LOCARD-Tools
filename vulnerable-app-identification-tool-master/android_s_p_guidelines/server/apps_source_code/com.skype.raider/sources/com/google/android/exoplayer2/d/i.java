package com.google.android.exoplayer2.d;

import com.skype.Defines;
import com.skype.android.video.hw.utils.CodecUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class i {
    public static final byte[] a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    public static final float[] b = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object c = new Object();
    private static int[] d = new int[10];

    public static final class a {
        public final int a;
        public final int b;
        public final boolean c;

        public a(int picParameterSetId, int seqParameterSetId, boolean bottomFieldPicOrderInFramePresentFlag) {
            this.a = picParameterSetId;
            this.b = seqParameterSetId;
            this.c = bottomFieldPicOrderInFramePresentFlag;
        }
    }

    public static final class b {
        public final int a;
        public final int b;
        public final int c;
        public final float d;
        public final boolean e;
        public final boolean f;
        public final int g;
        public final int h;
        public final int i;
        public final boolean j;

        public b(int seqParameterSetId, int width, int height, float pixelWidthAspectRatio, boolean separateColorPlaneFlag, boolean frameMbsOnlyFlag, int frameNumLength, int picOrderCountType, int picOrderCntLsbLength, boolean deltaPicOrderAlwaysZeroFlag) {
            this.a = seqParameterSetId;
            this.b = width;
            this.c = height;
            this.d = pixelWidthAspectRatio;
            this.e = separateColorPlaneFlag;
            this.f = frameMbsOnlyFlag;
            this.g = frameNumLength;
            this.h = picOrderCountType;
            this.i = picOrderCntLsbLength;
            this.j = deltaPicOrderAlwaysZeroFlag;
        }
    }

    public static int a(byte[] data, int limit) {
        Throwable th;
        synchronized (c) {
            int position = 0;
            int scratchEscapeCount = 0;
            while (position < limit) {
                int i;
                int i2 = position;
                while (i2 < limit - 2) {
                    try {
                        if (data[i2] == (byte) 0 && data[i2 + 1] == (byte) 0 && data[i2 + 2] == (byte) 3) {
                            position = i2;
                            break;
                        }
                        i2++;
                    } catch (Throwable th2) {
                        th = th2;
                        i = scratchEscapeCount;
                    }
                }
                position = limit;
                if (position < limit) {
                    if (d.length <= scratchEscapeCount) {
                        d = Arrays.copyOf(d, d.length * 2);
                    }
                    i = scratchEscapeCount + 1;
                    try {
                        d[scratchEscapeCount] = position;
                        position += 3;
                        scratchEscapeCount = i;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
            int unescapedLength = limit - scratchEscapeCount;
            int escapedPosition = 0;
            int unescapedPosition = 0;
            for (int i3 = 0; i3 < scratchEscapeCount; i3++) {
                int copyLength = d[i3] - escapedPosition;
                System.arraycopy(data, escapedPosition, data, unescapedPosition, copyLength);
                unescapedPosition += copyLength;
                int i4 = unescapedPosition + 1;
                data[unescapedPosition] = (byte) 0;
                unescapedPosition = i4 + 1;
                data[i4] = (byte) 0;
                escapedPosition += copyLength + 3;
            }
            System.arraycopy(data, escapedPosition, data, unescapedPosition, unescapedLength - unescapedPosition);
            return unescapedLength;
        }
        throw th;
    }

    public static void a(ByteBuffer data) {
        int length = data.position();
        int consecutiveZeros = 0;
        int offset = 0;
        while (offset + 1 < length) {
            int value = data.get(offset) & 255;
            if (consecutiveZeros == 3) {
                if (value == 1 && (data.get(offset + 1) & 31) == 7) {
                    ByteBuffer offsetData = data.duplicate();
                    offsetData.position(offset - 3);
                    offsetData.limit(length);
                    data.position(0);
                    data.put(offsetData);
                    return;
                }
            } else if (value == 0) {
                consecutiveZeros++;
            }
            if (value != 0) {
                consecutiveZeros = 0;
            }
            offset++;
        }
        data.clear();
    }

    public static boolean a(String mimeType, byte nalUnitHeaderFirstByte) {
        return (CodecUtils.MEDIA_TYPE.equals(mimeType) && (nalUnitHeaderFirstByte & 31) == 6) || ("video/hevc".equals(mimeType) && ((nalUnitHeaderFirstByte & 126) >> 1) == 39);
    }

    public static int b(byte[] data, int offset) {
        return data[offset + 3] & 31;
    }

    public static int c(byte[] data, int offset) {
        return (data[offset + 3] & 126) >> 1;
    }

    public static b a(byte[] nalData, int nalOffset, int nalLimit) {
        int i;
        l lVar = new l(nalData, nalOffset, nalLimit);
        lVar.a(8);
        int profileIdc = lVar.c(8);
        lVar.a(16);
        int seqParameterSetId = lVar.c();
        int chromaFormatIdc = 1;
        boolean separateColorPlaneFlag = false;
        if (profileIdc == 100 || profileIdc == 110 || profileIdc == 122 || profileIdc == 244 || profileIdc == 44 || profileIdc == 83 || profileIdc == 86 || profileIdc == 118 || profileIdc == 128 || profileIdc == 138) {
            chromaFormatIdc = lVar.c();
            if (chromaFormatIdc == 3) {
                separateColorPlaneFlag = lVar.a();
            }
            lVar.c();
            lVar.c();
            lVar.a(1);
            if (lVar.a()) {
                int limit = chromaFormatIdc != 3 ? 8 : 12;
                for (i = 0; i < limit; i++) {
                    if (lVar.a()) {
                        int i2;
                        if (i < 6) {
                            i2 = 16;
                        } else {
                            i2 = 64;
                        }
                        int i3 = 8;
                        int i4 = 8;
                        for (int i5 = 0; i5 < i2; i5++) {
                            if (i3 != 0) {
                                i3 = ((lVar.d() + i4) + Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) % Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
                            }
                            if (i3 != 0) {
                                i4 = i3;
                            }
                        }
                    }
                }
            }
        }
        int frameNumLength = lVar.c() + 4;
        int picOrderCntType = lVar.c();
        int picOrderCntLsbLength = 0;
        boolean deltaPicOrderAlwaysZeroFlag = false;
        if (picOrderCntType == 0) {
            picOrderCntLsbLength = lVar.c() + 4;
        } else if (picOrderCntType == 1) {
            deltaPicOrderAlwaysZeroFlag = lVar.a();
            lVar.d();
            lVar.d();
            long numRefFramesInPicOrderCntCycle = (long) lVar.c();
            for (i = 0; ((long) i) < numRefFramesInPicOrderCntCycle; i++) {
                lVar.c();
            }
        }
        lVar.c();
        lVar.a(1);
        int picWidthInMbs = lVar.c() + 1;
        int picHeightInMapUnits = lVar.c() + 1;
        boolean frameMbsOnlyFlag = lVar.a();
        int frameHeightInMbs = (2 - (frameMbsOnlyFlag ? 1 : 0)) * picHeightInMapUnits;
        if (!frameMbsOnlyFlag) {
            lVar.a(1);
        }
        lVar.a(1);
        int frameWidth = picWidthInMbs * 16;
        int frameHeight = frameHeightInMbs * 16;
        if (lVar.a()) {
            int cropUnitX;
            int cropUnitY;
            int frameCropLeftOffset = lVar.c();
            int frameCropRightOffset = lVar.c();
            int frameCropTopOffset = lVar.c();
            int frameCropBottomOffset = lVar.c();
            if (chromaFormatIdc == 0) {
                cropUnitX = 1;
                cropUnitY = 2 - (frameMbsOnlyFlag ? 1 : 0);
            } else {
                cropUnitX = chromaFormatIdc == 3 ? 1 : 2;
                cropUnitY = (chromaFormatIdc == 1 ? 2 : 1) * (2 - (frameMbsOnlyFlag ? 1 : 0));
            }
            frameWidth -= (frameCropLeftOffset + frameCropRightOffset) * cropUnitX;
            frameHeight -= (frameCropTopOffset + frameCropBottomOffset) * cropUnitY;
        }
        float pixelWidthHeightRatio = 1.0f;
        if (lVar.a() && lVar.a()) {
            int aspectRatioIdc = lVar.c(8);
            if (aspectRatioIdc == 255) {
                int sarWidth = lVar.c(16);
                int sarHeight = lVar.c(16);
                if (!(sarWidth == 0 || sarHeight == 0)) {
                    pixelWidthHeightRatio = ((float) sarWidth) / ((float) sarHeight);
                }
            } else if (aspectRatioIdc < b.length) {
                pixelWidthHeightRatio = b[aspectRatioIdc];
            }
        }
        return new b(seqParameterSetId, frameWidth, frameHeight, pixelWidthHeightRatio, separateColorPlaneFlag, frameMbsOnlyFlag, frameNumLength, picOrderCntType, picOrderCntLsbLength, deltaPicOrderAlwaysZeroFlag);
    }

    public static a d(byte[] nalData, int nalLimit) {
        l data = new l(nalData, 3, nalLimit);
        data.a(8);
        int picParameterSetId = data.c();
        int seqParameterSetId = data.c();
        data.a(1);
        return new a(picParameterSetId, seqParameterSetId, data.a());
    }

    public static int a(byte[] data, int startOffset, int endOffset, boolean[] prefixFlags) {
        boolean z;
        boolean z2 = true;
        int length = endOffset - startOffset;
        if (length >= 0) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        if (length == 0) {
            return endOffset;
        }
        if (prefixFlags != null) {
            if (prefixFlags[0]) {
                a(prefixFlags);
                return startOffset - 3;
            } else if (length > 1 && prefixFlags[1] && data[startOffset] == (byte) 1) {
                a(prefixFlags);
                return startOffset - 2;
            } else if (length > 2 && prefixFlags[2] && data[startOffset] == (byte) 0 && data[startOffset + 1] == (byte) 1) {
                a(prefixFlags);
                return startOffset - 1;
            }
        }
        int limit = endOffset - 1;
        int i = startOffset + 2;
        while (i < limit) {
            if ((data[i] & 254) == 0) {
                if (data[i - 2] == (byte) 0 && data[i - 1] == (byte) 0 && data[i] == (byte) 1) {
                    if (prefixFlags != null) {
                        a(prefixFlags);
                    }
                    return i - 2;
                }
                i -= 2;
            }
            i += 3;
        }
        if (prefixFlags == null) {
            return endOffset;
        }
        z = length > 2 ? data[endOffset + -3] == (byte) 0 && data[endOffset - 2] == (byte) 0 && data[endOffset - 1] == (byte) 1 : length == 2 ? prefixFlags[2] && data[endOffset - 2] == (byte) 0 && data[endOffset - 1] == (byte) 1 : prefixFlags[1] && data[endOffset - 1] == (byte) 1;
        prefixFlags[0] = z;
        z = length > 1 ? data[endOffset + -2] == (byte) 0 && data[endOffset - 1] == (byte) 0 : prefixFlags[2] && data[endOffset - 1] == (byte) 0;
        prefixFlags[1] = z;
        if (data[endOffset - 1] != (byte) 0) {
            z2 = false;
        }
        prefixFlags[2] = z2;
        return endOffset;
    }

    public static void a(boolean[] prefixFlags) {
        prefixFlags[0] = false;
        prefixFlags[1] = false;
        prefixFlags[2] = false;
    }
}
